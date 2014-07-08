package com.mobiledi.appstrender.servicepush;

import java.sql.Timestamp;

import org.joda.time.DateTime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class PushServiceStarter extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
	Intent service = new Intent(context, UploadService.class);
		context.startService(service);
		
	}
}