package com.mobiledi.appstrender.serviceget;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.achartengine.GraphicalView;
import org.codehaus.jackson.map.ObjectMapper;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mobiledi.appstrender.AppObject;
import com.mobiledi.appstrender.R;
import com.mobiledi.appstrender.graph.ShowBarChart;

//do this wherever you are wanting to POST
public class GETRequest {
	public Context con;
	public ArrayList<AppObject> returnObject;
	private String tosendJSON;
	private String url;
	private String MODE; // 1=post,2=get,3=put,4=delete;
	int source;
	
	public GETRequest(String _url, String _tosendJSON, String _MODE,  Context cont,int source)
			throws InterruptedException, ExecutionException {
		this.url = _url;
		this.tosendJSON = _tosendJSON;
		this.MODE = _MODE;
		this.con=cont;
		this.source=source;
		
		final MakeRequest request = new MakeRequest(con,source);
		request.execute(url, tosendJSON, MODE);
		//returnObject = request.get();
		Log.d(_url, tosendJSON);
	}
}

class MakeRequest extends AsyncTask<String, Void, ArrayList<AppObject>> {
	HttpURLConnection urlConnection = null;
	ArrayList<AppObject> returnObject;
	private ProgressDialog dialog;
	private Context context;
	private int source;
	public final int FROM_ALL=3;
	public final int FROM_MONTH=2;
	public final int FROM_WEEK=1;
	
	
	
	public MakeRequest(Context context, int source){
		this.context=context;
		this.source=source;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();	
		dialog = new ProgressDialog(context);
	    dialog.setMessage("Fetching Data ...");
	    dialog.setIcon(R.drawable.loading);
	    dialog.setTitle(" ");
	    dialog.setIndeterminate(true);
	    dialog.show();    
	    }   
	@Override
	protected ArrayList<AppObject> doInBackground(String... url) {

		if (url[2] == "GETTING") { // GET OPERATION
			try {
				URL urlToRequest = new URL(url[0]);
				urlConnection = (HttpURLConnection) urlToRequest
						.openConnection();
				urlConnection.setRequestMethod("GET");
				urlConnection.setReadTimeout(3 * 1000);
				urlConnection.connect();
				if(urlConnection.getResponseCode()==200){
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(urlConnection.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line = null;
				// Read Server Response
				while ((line = reader.readLine()) != null) {
					// Append server response in string
					sb.append(line + "");
				}
				// Append Server Response To Content String
				String Content = sb.toString();
				ObjectMapper mapper = new ObjectMapper();
				ArrayList<AppObject> myObjects = mapper.readValue(
						Content,
						mapper.getTypeFactory().constructCollectionType(
								List.class, AppObject.class));

				return myObjects;
				}	
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			finally{
				if (urlConnection != null) 
					urlConnection.disconnect();		
			}

		}
		return null;

		// return null;
	}

	@Override
	protected void onPostExecute(ArrayList<AppObject> result) {
		if (urlConnection != null)
			urlConnection.disconnect();
		try {
	        dialog.dismiss();
	        dialog = null;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		if(result==null){
			Toast.makeText(context, "Oops! No Data to Display", Toast.LENGTH_LONG).show();
			return;
		}
		ShowBarChart s;
		GraphicalView Gv;
		LinearLayout layout = null;
		LayoutInflater  mInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);;
		View view ;
		
		returnObject=result;
		s = new ShowBarChart(context,result);
		Gv = s.openChart();
		switch(source){
		case(FROM_WEEK):{
			view = mInflater.inflate(R.layout.fragment_week,null);
			 layout = (LinearLayout)view.findViewById(
					R.id.dashboard_chart_layoutWeek);
			
			break;	
		}
		case(FROM_MONTH):{
			view = mInflater.inflate(R.layout.fragment_datamonth, null);
			 layout = (LinearLayout)view.findViewById(
					R.id.dashboard_chart_layoutmonth);
			
			break;	
		}
		case(FROM_ALL):{
			view = mInflater.inflate(R.layout.fragment_alldata, null);
			layout = (LinearLayout)view.findViewById(
					R.id.dashboard_chart_layout);
			break;	
		}
		}
		
		layout.removeAllViews();
		layout.addView(Gv, new LayoutParams(960, LayoutParams.MATCH_PARENT));
	
	}
}