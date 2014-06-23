package com.mobiledi.appstrender.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.mobiledi.appstrender.AppObject;

import android.os.AsyncTask;
import android.util.Log;

//do this wherever you are wanting to POST
public class RestRequest {
	
	private String tosendJSON;
	private String url;
	private String MODE; // 1=post,2=get,3=put,4=delete;

	public RestRequest(String _url, String _tosendJSON,String _MODE) {
		// TODO Auto-generated constructor stub
		this.url = _url;
		this.tosendJSON = _tosendJSON;
		this.MODE=_MODE;
		
		MakeRequest request= new MakeRequest();
		request.execute(url,tosendJSON,MODE);
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
		
		if(url[2]=="POSTING"){ //POST OPERATION
		
		try {
				URL urlToRequest = new URL(url[0]);
				urlConnection = (HttpURLConnection) urlToRequest.openConnection();
			if (url[1] != null) {
				urlConnection.setDoOutput(true);
				urlConnection.setRequestMethod("POST");
				urlConnection.setFixedLengthStreamingMode(url[1].getBytes().length);
				urlConnection.setRequestProperty("Content-Type","application/json");
				urlConnection.setReadTimeout(30*1000);
				//urlConnection.connect();
				//Log.d("date: ","SOYEAH I AM HERE");///String.valueOf(urlConnection.getDate()));
				PrintWriter out;
				out = new PrintWriter(urlConnection.getOutputStream());
				out.print(url[1]);
				//out.pr
				int statusCode = urlConnection.getResponseCode();
				//urlConnection.getRe
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
		
		
		} /// END POST OPERATION
		if(url[2]=="GETTING"){ //GET OPERATION
			try {
				URL urlToRequest = new URL(url[0]);
				urlConnection = (HttpURLConnection) urlToRequest.openConnection();
				urlConnection.setRequestMethod("GET");
				urlConnection.setReadTimeout(30*1000);
				urlConnection.connect();
              
                 // Get the server response 
                   
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                 StringBuilder sb = new StringBuilder();
                 String line = null;
                
                   // Read Server Response
                   while((line = reader.readLine()) != null)
                       {
                              // Append server response in string
                              sb.append(line + "");
                       }
                    
                   // Append Server Response To Content String 
                 String Content = sb.toString();
                 ObjectMapper mapper=new ObjectMapper();
                 List<AppObject> myObjects = mapper.readValue(Content, mapper.getTypeFactory().constructCollectionType(List.class, AppObject.class));
                 
                 
                 for(int i=0;i<myObjects.size();i++){
                	 Log.d(myObjects.get(i).getAppName(),String.valueOf(myObjects.get(i).getAppUid()));
                 }
                 
                 //Log.d("The Content from DB Content", Content);
              
		/////////////		
				
				
				
				
				
				
			
			}
			
			
			catch(Exception e){
				e.printStackTrace();
			}
			
			
			
			
		}
			
		
		return null;
	}
	@Override
	protected void onPostExecute(String n) {
		if (urlConnection != null) {
			urlConnection.disconnect();
		}
	}
			
}