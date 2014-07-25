package com.mobiledi.appstrender.serviceget;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.achartengine.GraphicalView;
import org.codehaus.jackson.map.ObjectMapper;

import android.R.drawable;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mobiledi.appstrender.AppObject;
import com.mobiledi.appstrender.PInfo;
import com.mobiledi.appstrender.R;
import com.mobiledi.appstrender.datausagetabs.DataUsageTabs;
import com.mobiledi.appstrender.datausagetabs.MonthFragment;
import com.mobiledi.appstrender.graph.ShowBarChart;
//do this wherever you are wanting to POST
public class GETRequest{
	
	
	public Context con;
	public  ArrayList<AppObject> returnObject=new ArrayList<AppObject>();
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
		MakeRequest request = new MakeRequest();
		request.execute(url, tosendJSON, MODE);
		//returnObject=request;//.get();
		Log.d("I got something GET REQUEST", returnObject.toString());		
	}
	
	
	
	
}

class MakeRequest extends AsyncTask<String, Void, ArrayList<AppObject>> {
	HttpURLConnection urlConnection = null;
	ArrayList<AppObject> returnObjectFormtask;
/*	private ProgressDialog dialog;
	private Context context;
	private int source;*/
	public final int FROM_ALL=3;
	public final int FROM_MONTH=2;
	public final int FROM_WEEK=1;
	
/*	
	public MakeRequest(Context context, int source){
		this.context=context;
		this.source=source;
	}
	*/
	@Override
	protected void onPreExecute() {		
		Log.d("GET RESQUEST", "REACHED HERE");
	    }   
	@Override
	protected ArrayList<AppObject> doInBackground(String... url) {

		if (url[2] == "GETTING") { // GET OPERATION
			try {
				URL urlToRequest = new URL(url[0]);
				urlConnection = (HttpURLConnection) urlToRequest
						.openConnection();
				urlConnection.setRequestMethod("GET");
				urlConnection.setReadTimeout(5 * 1000);
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
				for(AppObject x: myObjects){
					Log.d("App Loaded"+x.getAppName(),String.valueOf("Recieved"+x.getRecieved()));
				}
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
		returnObjectFormtask=result;
		return;
}
	}