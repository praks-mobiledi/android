package com.mobiledi.appstrender.servicePUSH;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PushTimerService extends BroadcastReceiver {

	// restart service every 30 minutes
	private static final long REPEAT_TIME = 1000 * 300;

	@Override
	public void onReceive(Context context, Intent intent) {
		/*
		 * AlarmManager service = (AlarmManager) context
		 * .getSystemService(Context.ALARM_SERVICE); Intent i = new
		 * Intent(context, MyStartServiceReceiver.class); PendingIntent pending
		 * = PendingIntent.getBroadcast(context, 0, i,
		 * PendingIntent.FLAG_CANCEL_CURRENT); Calendar cal =
		 * Calendar.getInstance(); // start 30 seconds after boot completed
		 * cal.add(Calendar.SECOND, 30); // fetch every 30 seconds //
		 * InexactRepeating allows Android to optimize the energy consumption
		 * service.setInexactRepeating(AlarmManager.RTC_WAKEUP,
		 * cal.getTimeInMillis(), REPEAT_TIME, pending);
		 */

		// service.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
		// REPEAT_TIME, pending);

		Calendar cur_cal = Calendar.getInstance();
		Intent intent1 = new Intent(context, PushServiceStarter.class);
		PendingIntent pintent = PendingIntent.getBroadcast(context, 0, intent1,
				0);
		AlarmManager alarm = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		alarm.setRepeating(AlarmManager.RTC_WAKEUP, cur_cal.getTimeInMillis(),
				REPEAT_TIME, pintent);

	}
}
