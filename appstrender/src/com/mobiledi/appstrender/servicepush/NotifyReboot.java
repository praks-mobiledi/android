package com.mobiledi.appstrender.servicepush;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;
import org.joda.time.DateTime;
import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

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
			new PUSHRequest(
					Home.SERVER_URL_ADD + "insert/RebootRow",
					"[{\"appName\":\"DEVICEREBOOTED\",\"timeStamp\":\""
							+ formattedTime
							+ "\",\"carrier\":\"NA\",\"category\":\"NA\",\"deviceId\":\""
							+ devId
							+ "\",\"sent\":0,\"recieved\":0,\"phoneNum\":0,\"appUid\":0}]",
					"POSTING");
		}

		catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}

	}
}