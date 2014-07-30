package com.mobiledi.appstrender.servicepush;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.DateTime;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.mobiledi.appstrender.AppObject;
import com.mobiledi.appstrender.Home;
import com.mobiledi.appstrender.PInfo;
import com.mobiledi.appstrender.networkutil.NetworkUtil;

@SuppressLint("SimpleDateFormat")
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
		TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 		
			
		Log.d("Prakash :", "This is a Timed Service");
		try {
			ArrayList<AppObject> returnedList = new PInfo(getApplication()
					.getApplicationContext()).getInstalledComponentList(0);
			Timestamp ts=new Timestamp(new DateTime().getMillis());
			 //Log.d("TimeStampValue",ts.toString());
			String json = "[";
			for (int i = 0; i < returnedList.size(); i++) {
				AppObject tempObj = returnedList.get(i);
				tempObj.setDeviceId(telephonyManager.getDeviceId());
				tempObj.setCarrier(telephonyManager.getNetworkOperatorName());
				tempObj.setCategory("Application");
				tempObj.setPhoneNum(000000);
				tempObj.setTimeStamp(ts);
				ObjectMapper mapper = new ObjectMapper();
				json = json.concat("," + mapper.writeValueAsString(tempObj));
			}
			json = json.concat("]");
			String toSendJSON = json.substring(0, 1) + json.substring(1 + 1);
			Log.d("App Detail", +toSendJSON.length() + toSendJSON);

			// SEND DATA TO SERVER UnCOMMENT WHEN  in PRODUCTION
			if(NetworkUtil.getConnectivityStatus(UploadService.this)!=0){
				
			/*new PUSHRequest(Home.SERVER_URL_ADD+"insert/datas",
					toSendJSON, "POSTING");	*/
				new PUSHRequest(Home.SERVER_URL_ADD+"insert/datas",
						toSendJSON).sendRequest();	
			//Toast.makeText(UploadService.this, "AppsTrender Data Uploaded", Toast.LENGTH_LONG).show();
			
				/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
				SimpleDateFormat output = new SimpleDateFormat(
						"yyyy-MM-dd'T'HH:mm:ss");
				Date d = sdf.parse(ts.toString());
				String formattedTime = output.format(d);
				Log.d("LocalDate", formattedTime);
				Log.d("TS LocalDate", ts.toString());*/
				//DateTime s=DateTime.now();
			//	String formattedTime=(ts.toString().substring(0, 10).concat("T")).concat(ts.toString().substring(11, ts.toString().length()));
			//	Log.d("AFTERLocalDate", formattedTime);
					
			}
				else{
					Toast.makeText(UploadService.this, "Unable to connect to the internet, please ensure your data or wifi is turned on", Toast.LENGTH_LONG).show();
					
				return 0;}

		} catch (Exception e) {
			Toast.makeText(UploadService.this, "No utilization data available at this time", Toast.LENGTH_LONG).show();
			
			Log.d("ERROR @Upload Service", e.getMessage());
		}
	
		return Service.START_NOT_STICKY;
	}
		
	}


