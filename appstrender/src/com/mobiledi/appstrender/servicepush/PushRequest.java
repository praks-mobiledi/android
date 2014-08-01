package com.mobiledi.appstrender.servicepush;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.mobiledi.appstrender.AppObject;
///import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;

//do this wherever you are wanting to POST
public class PushRequest {
	public ArrayList<AppObject> returnObject;
	private String tosendJSON;
	private String url;
	//private Context context; // 1=post,2=get,3=put,4=delete;

	public PushRequest(String _url, String _tosendJSON)//, Context _context)
			throws InterruptedException, ExecutionException {

		this.url = _url;
		this.tosendJSON = _tosendJSON;
		//this.context = _context;

		//final MakeRequest request = new MakeRequest();
		//request.execute(url, tosendJSON, MODE);
		//returnObject = request.get();
		//Log.d(_url, tosendJSON);
	}	
	
	public void sendRequest(){
		AsyncHttpClient client=new AsyncHttpClient();
		StringEntity se= null;
			try {
				se=new StringEntity(this.tosendJSON.toString());
				se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,
						"application/json"));
				client.post(null,this.url, se,"application/json", new JsonHttpResponseHandler(){

					@Override
					public void onFailure(int statusCode, Header[] headers,
							String responseString, Throwable throwable) {
						// TODO Auto-generated method stub

						Log.d("REsponse from Server: ", "UPLOAD FAILED");
						}

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							String responseString) {
						// TODO Auto-generated method stub
						Log.d("REsponse from Server: ", "SUCCESSFULLY UPLOADED");
					}		
				
				
				
				
				
				});	
			
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}	
}
/*
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
					urlConnection.setDoInput(true);
					urlConnection.setDoOutput(true);
					urlConnection.setRequestMethod("POST");
					urlConnection.setFixedLengthStreamingMode(url[1].getBytes().length);
					//Log.d("BYTES LENGTH", String.valueOf(url[1].getBytes().length));
					urlConnection.setRequestProperty("Content-Type","application/json");
					//urlConnection.setReadTimeout(10 * 1000);
					urlConnection.setConnectTimeout(5000);
					PrintWriter out;
					out = new PrintWriter(urlConnection.getOutputStream());
					out.print(url[1]);
					out.flush();
					int statusCode = urlConnection.getResponseCode(); 
					if(statusCode != HttpURLConnection.HTTP_OK) {
						Log.d("ASync Task", "Alert"); // throw some  exception 
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

}*/