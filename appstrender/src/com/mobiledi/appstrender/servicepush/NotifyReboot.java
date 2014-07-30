package com.mobiledi.appstrender.servicepush;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.DateTime;
import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.mobiledi.appstrender.AppObject;
import com.mobiledi.appstrender.Home;

public class NotifyReboot {
	Context con;

	public NotifyReboot(Context con) {
		this.con = con;
	}

	@SuppressLint("SimpleDateFormat")
	public void addRebootRow() {

		Timestamp ts = new Timestamp(new DateTime().getMillis());
		TelephonyManager tm = (TelephonyManager) con
				.getSystemService(Context.TELEPHONY_SERVICE);
		String devId = tm.getDeviceId();
		Log.d("ALERT", "PUShING REBOOT");
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
			sdf.setTimeZone(TimeZone.getDefault());
			SimpleDateFormat output = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ss");
			Date d = sdf.parse(ts.toString());
			String formattedTime = output.format(d);
			/*new PUSHRequest(
					Home.SERVER_URL_ADD + "insert/RebootRow",
					"[{\"appName\":\"DEVICEREBOOTED\",\"timeStamp\":\""
							+ formattedTime
							+ "\",\"carrier\":\"NA\",\"category\":\"NA\",\"deviceId\":\""
							+ devId
							+ "\",\"sent\":0,\"recieved\":0,\"phoneNum\":0,\"appUid\":0}]",
					"POSTING");*/
			
			
			AppObject tempObj= new AppObject();
			tempObj.setAppName("DEVICEREBOOTED");
			tempObj.setAppUid(121);
			tempObj.setDeviceId(tm.getDeviceId());
			tempObj.setCarrier(tm.getNetworkOperatorName());
			tempObj.setCategory("Application");
			tempObj.setSent(0);
			tempObj.setRecieved(0);
			tempObj.setPhoneNum(123654);
			tempObj.setTimeStamp(ts);
			ObjectMapper obm= new ObjectMapper();			
			String toSendJson="[";
			toSendJson=toSendJson.concat(obm.writeValueAsString(tempObj));
			//System.out.println("OBM line :" + obm.writeValueAsString(tempObj));
			toSendJson=toSendJson.concat("]");	
			new PUSHRequest(
					Home.SERVER_URL_ADD + "insert/RebootRow",
					toSendJson).sendRequest();
		}

		catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}