package com.mobiledi.appstrender.service;

import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import com.mobiledi.appstrender.AppObject;
import com.mobiledi.appstrender.PInfo;

public class RestfulService extends Service{
	 private final IBinder mBinder = new MyBinder();
	 @Override
	  public IBinder onBind(Intent arg0) {
	    return mBinder;
	  }

	  public class MyBinder extends Binder {
	    public RestfulService getService() {
	     
	    	return RestfulService.this;
	    }
	  }
	  

	  public int onStartCommand(Intent intent, int flags, int startId) {
		//  Log.d("Prakash :", "This is a Timed Service");
		  try {
  			ArrayList<AppObject> returnedList= new PInfo(getApplication().getApplicationContext()).getInstalledComponentList(0);
  			//Log.d("Context",getApplicationContext().toString());
  			String json="[";
  			for(int i=0;i<returnedList.size();i++){
  				AppObject tempObj= returnedList.get(i);
  				tempObj.setDeviceId("23nznzhujh132");
  				tempObj.setCategory("System");
  				ObjectMapper mapper = new ObjectMapper();	 
  				json=  json.concat(","+mapper.writeValueAsString(tempObj)); 						
  			}
  			json=json.concat("]");	
  			String toSendJSON = json.substring(0, 1) + json.substring(1 + 1);	
  			Log.d("App Detail",+ toSendJSON.length()+toSendJSON);
  			//new RestRequest("http://192.168.1.2:8080/appstrender_service/appstrender/appdata/insert/makaa", toSendJSON);
  			new RestRequest("http://192.168.1.2:8080/appstrender_service/appstrender/appdata/insert/makaa", "[{\"appName\":\"JUST A TEST MAN\",\"timeStamp\":null,\"carrier\":null,\"category\":\"Downloaded\",\"deviceId\":\"23nznzhujh132\",\"sent\":0,\"recieved\":0,\"phoneNum\":0,\"appUid\":10085}]");
  		  
		  }
		  catch (Exception e){
			 Log.d("ERROR",e.getMessage()); 
		  }
  return Service.START_NOT_STICKY;
	  }







}
