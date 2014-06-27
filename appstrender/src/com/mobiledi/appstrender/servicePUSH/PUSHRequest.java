package com.mobiledi.appstrender.servicePUSH;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.codehaus.jackson.map.ObjectMapper;

import com.mobiledi.appstrender.AppObject;

import android.app.DownloadManager.Request;
import android.os.AsyncTask;
import android.util.Log;

//do this wherever you are wanting to POST
public class PUSHRequest {
	public ArrayList<AppObject> returnObject;
	private String tosendJSON;
	private String url;
	private String MODE; // 1=post,2=get,3=put,4=delete;

	public PUSHRequest(String _url, String _tosendJSON, String _MODE)
			throws InterruptedException, ExecutionException {

		this.url = _url;
		this.tosendJSON = _tosendJSON;
		this.MODE = _MODE;

		final MakeRequest request = new MakeRequest();
		request.execute(url, tosendJSON, MODE);
		returnObject = request.get();
		Log.d(_url, tosendJSON);
	}

}

class MakeRequest extends AsyncTask<String, Void, ArrayList<AppObject>> {
	HttpURLConnection urlConnection = null;
	ArrayList<AppObject> returnObject;

	@Override
	protected ArrayList<AppObject> doInBackground(String... url) {
		// super.onPostExecute(result)

		if (url[2] == "POSTING") { // POST OPERATION

			try {
				URL urlToRequest = new URL(url[0]);
				urlConnection = (HttpURLConnection) urlToRequest
						.openConnection();
				if (url[1] != null) {
					urlConnection.setDoOutput(true);
					urlConnection.setRequestMethod("POST");
					urlConnection
							.setFixedLengthStreamingMode(url[1].getBytes().length);
					urlConnection.setRequestProperty("Content-Type",
							"application/json");
					urlConnection.setReadTimeout(5 * 1000);
					PrintWriter out;
					out = new PrintWriter(urlConnection.getOutputStream());
					out.print(url[1]);
					/*
					 * int statusCode = urlConnection.getResponseCode(); if
					 * (statusCode != HttpURLConnection.HTTP_OK) {
					 * Log.d("ASync Task", "Something Wrong"); // throw some
					 * exception }
					 */
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			// handle issues
			return null;
		} // / END POST OPERATION
		if (url[2] == "GETTING") { // GET OPERATION
			try {
				URL urlToRequest = new URL(url[0]);
				urlConnection = (HttpURLConnection) urlToRequest
						.openConnection();
				urlConnection.setRequestMethod("GET");
				urlConnection.setReadTimeout(30 * 1000);
				urlConnection.connect();
				// Get the server response
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
				/*
				 * Cycle through objects for(int i=0;i<myObjects.size();i++){
				 * Log.d(myObjects.get(i).getAppName
				 * (),String.valueOf(myObjects.get(i).getAppUid())); }
				 */// Log.d("The Content from DB Content", Content);
			}

			catch (Exception e) {
				e.printStackTrace();
			}

		}
		return null;
	}

	@Override
	protected void onPostExecute(ArrayList<AppObject> result) {
		if (urlConnection != null) {
			urlConnection.disconnect();
		}
	}

}