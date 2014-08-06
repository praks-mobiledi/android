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

//do this wherever you are wish to POST
public class PushRequest {
	public ArrayList<AppObject> returnObject;
	private String tosendJSON;
	private String url;
	//private Context context; // 1=post,2=get,3=put,4=delete;

	public PushRequest(String _url, String _tosendJSON)//, Context _context)
			throws InterruptedException, ExecutionException {
		this.url = _url;
		this.tosendJSON = _tosendJSON;
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
						Log.d("REsponse from Server: ", "UPLOAD FAILED");
						}

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							String responseString) {
						Log.d("REsponse from Server: ", "SUCCESSFULLY UPLOADED");
					}		
				});	
			
			} catch (UnsupportedEncodingException e) {

				e.printStackTrace();
			}
	}	
}