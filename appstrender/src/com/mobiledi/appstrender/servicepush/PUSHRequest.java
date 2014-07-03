package com.mobiledi.appstrender.servicepush;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import com.mobiledi.appstrender.AppObject;
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
Log.d("Value of URL 1:" , url[1]);
		if (url[2] == "POSTING") { // POST OPERATION

			try {
				URL urlToRequest = new URL(url[0]);
				
				urlConnection = (HttpURLConnection) urlToRequest.openConnection();
				if (url[1] != null) {
					urlConnection.setDoOutput(true);
					urlConnection.setRequestMethod("POST");
					urlConnection.setFixedLengthStreamingMode(url[1].getBytes().length);
					Log.d("BYTES LENGTH", String.valueOf(url[1].getBytes().length));
					urlConnection.setRequestProperty("Content-Type","application/json");
					urlConnection.setReadTimeout(30 * 1000);
					PrintWriter out;
					out = new PrintWriter(urlConnection.getOutputStream());
					out.print(url[1]);
					out.flush();
					int statusCode = urlConnection.getResponseCode(); 
					if(statusCode != HttpURLConnection.HTTP_OK) {
						Log.d("ASync Task", "Something Wrong"); // throw some  exception 
					  }
					 
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally{
				if (urlConnection != null) {
					urlConnection.disconnect();
				}
			}
			// handle issues
			//return null;
		} // / END POST OPERATION
		return null;
		
	}

	@Override
	protected void onPostExecute(ArrayList<AppObject> result) {
		if (urlConnection != null) {
			urlConnection.disconnect();
		}
	}

}