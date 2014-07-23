package com.mobiledi.appstrender.datausagetabs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;

import com.mobiledi.appstrender.AppObject;
import com.mobiledi.appstrender.Home;
import com.mobiledi.appstrender.R;

/*public class BarGraphCalled {
 Context con;
 private TelephonyManager tm;
 public static ArrayList<AppObject> responseAppsListAll,responseAppsListMonth,responseAppsListWeek;
 public  boolean isSetResListAll = false;
 public  boolean isSetResListMonth = false;
 public  boolean isSetResListWeek = false;
 AlertDialog.Builder ab;
 AlertDialog abs;
 public BarGraphCalled(Context con) {
 this.con = con;
 tm=(TelephonyManager)con.getSystemService(Context.TELEPHONY_SERVICE);
 }*/
public class BarGraphCalled extends
		AsyncTask<Void, Integer, ArrayList<ArrayList<AppObject>>> {

	private ArrayList<ArrayList<AppObject>> finalList= new ArrayList<ArrayList<AppObject>>();

	private TelephonyManager tm;
	private Context context;
	private ProgressDialog dialog;
	ArrayList<String> dates = new ArrayList<String>();
	String endDateForAll = "2014-01-01%2000:00:00";
	//HttpURLConnection urlConnection = null;
	public BarGraphCalled(Context context) {
		super();
		this.context = context;
		this.tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		dialog = new ProgressDialog(context);
		dialog.setMessage("Fetching Data ...");
		dialog.setIcon(R.drawable.loading);
		dialog.setTitle("#");
		dialog.setIndeterminate(false);
		dialog.setMax(100);
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		dialog.setProgress(3);
		dialog.show();

		String endDate = "2014-01-01%2000:00:00";
		dates.add(endDate); // firstDate

		LocalDate dt = LocalDate.now();
		dt = dt.minusWeeks(1);
		endDate = dt.toString() + "%20" + LocalTime.now();
		dates.add(endDate);

		dt = LocalDate.now();
		dt = dt.minusMonths(1);
		endDate = dt.toString() + "%20" + LocalTime.now();
		dates.add(endDate);

		
	}

	@Override
	protected ArrayList<ArrayList<AppObject>> doInBackground(Void... args) {
		int total = 1;
		
		for(String date:dates){

		try {
			  	HttpGet request = new HttpGet();
		        HttpParams httpParameters = new BasicHttpParams();
		        HttpConnectionParams.setConnectionTimeout(httpParameters, 4000);
		        HttpConnectionParams.setSoTimeout(httpParameters, 6000);
		        HttpClient httpclient = new DefaultHttpClient(httpParameters);
		        URI website;
			website = new URI(Home.SERVER_URL_ADD + "readById/"
					+ tm.getDeviceId() + "/"+ date);//2014-01-01%2000:00:00");
			request.setURI(website);
	
	        HttpResponse response = httpclient.execute(request);
	        
	        BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	        String line;
			StringBuilder sb = new StringBuilder();
			
			while ((line = in.readLine()) != null) {
				// Append server response in string
				sb.append(line + "");
				total+=line.length();
			}
			// Append Server Response To Content String
			String Content=  sb.toString();	
			publishProgress(total);
			
			ObjectMapper mapper = new ObjectMapper();
			ArrayList<AppObject> myObjects = mapper.readValue(
					Content,
					mapper.getTypeFactory().constructCollectionType(
							List.class, AppObject.class));
			finalList.add(myObjects);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			finalList.add(null);
					
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			finalList.add(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			finalList.add(null);			
		}
		
		}
		return finalList;
	}

	@Override
	protected void onPostExecute(ArrayList<ArrayList<AppObject>> result) {		
		
		if(dialog!=null || dialog.isShowing()){
			
			dialog.dismiss();
			
		}
			
		Intent i = new Intent(context,DataUsageTabs.class);
		i.putExtra("result", new DataWrapper(result));	
		context.startActivity(i);	
	}

	@Override
	protected void onProgressUpdate(Integer...values) {
		// TODO Auto-generated method stub
		dialog.setProgress(values[0]);
	}

}

