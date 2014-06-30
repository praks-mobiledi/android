package com.mobiledi.appstrender.servicepush;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.DateTime;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import com.mobiledi.appstrender.AppObject;
import com.mobiledi.appstrender.PInfo;

public class UploadService extends Service {
	private final IBinder mBinder = new MyBinder();

	@Override
	public IBinder onBind(Intent arg0) {
		return mBinder;
	}

	public class MyBinder extends Binder {
		public UploadService getService() {

			return UploadService.this;
		}
	}

	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("Prakash :", "This is a Timed Service");
		try {
			ArrayList<AppObject> returnedList = new PInfo(getApplication()
					.getApplicationContext()).getInstalledComponentList(0);
			Timestamp ts=new Timestamp(new DateTime().getMillis());
			 Log.d("TimeStampValue",ts.toString());
			String json = "[";
			for (int i = 0; i < returnedList.size(); i++) {
				AppObject tempObj = returnedList.get(i);
				tempObj.setDeviceId("Xperia_M");
				tempObj.setCategory("System");
				tempObj.setTimeStamp(ts);
				ObjectMapper mapper = new ObjectMapper();
				json = json.concat("," + mapper.writeValueAsString(tempObj));
			}
			json = json.concat("]");
			String toSendJSON = json.substring(0, 1) + json.substring(1 + 1);
			Log.d("App Detail", +toSendJSON.length() + toSendJSON);

			// SEND DATA TO SERVER UnCOMMENT WHEN  in PRODUCTION
			/*new PUSHRequest(
					"http://192.168.1.3:8080/appstrender_service/appstrender/appdata/insert/datas",
					toSendJSON, "POSTING");*/


		} catch (Exception e) {
			Log.d("ERROR", e.getMessage());
		}
	
		return Service.START_NOT_STICKY;
	}

}
