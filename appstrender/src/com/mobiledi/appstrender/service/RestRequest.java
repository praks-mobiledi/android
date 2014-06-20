package com.mobiledi.appstrender.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;

//do this wherever you are wanting to POST
public class RestRequest {
	
	private String tosendJSON;
	private String url;

	public RestRequest(String _url, String _tosendJSON) {
		// TODO Auto-generated constructor stub
		this.url = _url;
		this.tosendJSON = _tosendJSON;
		MakeRequest request= new MakeRequest();
		request.execute(url,tosendJSON);
		Log.d(_url, tosendJSON);
	}
	
	
	
	
}

class MakeRequest extends AsyncTask<String,Void,String> {
	//String url;
	//static String tosendJSON;
	HttpURLConnection urlConnection = null;
	

	@Override
	protected String doInBackground(String... url) {
		//super.onPostExecute(result)
		try {
				URL urlToRequest = new URL(url[0]);
				urlConnection = (HttpURLConnection) urlToRequest.openConnection();
			
			if (url[1] != null) {
				urlConnection.setDoOutput(true);
				urlConnection.setRequestMethod("POST");
				urlConnection.setFixedLengthStreamingMode(url[1].getBytes().length);
				urlConnection.setRequestProperty("Content-Type","application/json");
				urlConnection.setReadTimeout(30*1000);
				Log.d("date: ","SOYEAH I AM HERE");///String.valueOf(urlConnection.getDate()));
				PrintWriter out;
				out = new PrintWriter(urlConnection.getOutputStream());
				out.print(url[1]);
				out.close();
				int statusCode = urlConnection.getResponseCode();
				if (statusCode != HttpURLConnection.HTTP_OK) {
					
					Log.d("ASync Task","Something Wrong");
			// throw some exception
			 }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// handle issues

		return null;
	}
	@Override
	protected void onPostExecute(String n) {
		if (urlConnection != null) {
			urlConnection.disconnect();
		}
	}
			
}