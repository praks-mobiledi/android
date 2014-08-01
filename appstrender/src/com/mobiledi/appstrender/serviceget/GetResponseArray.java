package com.mobiledi.appstrender.serviceget;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.mobiledi.appstrender.AppObject;
import com.mobiledi.appstrender.R;

public class GetResponseArray {
private Context con;

 public GetResponseArray(Context con) {
	this.con = con;
 		}

 public ArrayList<AppObject> getArray(String url){
	AsyncHttpClient client = new AsyncHttpClient();
	//final ;
	client.get(con,url, new JsonHttpResponseHandler(){
		  private ProgressDialog dialog;

		@Override
          public void onProgress(int position, int length) {
              Log.d("progress", "pos: " + position + " len: " + length);
          }

		@Override
		public void onFailure(int statusCode, Header[] headers,
				String responseString, Throwable throwable) {
			System.out.println(responseString);
		}

		@Override
		public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				 ArrayList<AppObject> myObjects = mapper.readValue(response.toString(),mapper.getTypeFactory().constructCollectionType(
								List.class, AppObject.class));
				for(AppObject x: myObjects){
					Log.d("App in GET ARRAY "+x.getAppName(),String.valueOf("Recieved"+x.getRecieved()));
				}
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				//return myObjects;
		
		}

		
	});	 
	
	return null;
 }	
	
	
	
	
	
	
	
}
