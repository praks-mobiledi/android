package com.mobiledi.appstrender.servicepush;

import java.sql.Timestamp;
import java.util.Calendar;

import org.joda.time.DateTime;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class PushTimerService extends BroadcastReceiver {

	// restart service every 30 minutes
	//private static final long REPEAT_TIME =  1800000;
	private static final long REPEAT_TIME =  3600000;//1 hour
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("PUSH TIMER SERVICE ", "START");
		if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())){
			Log.d("REBOOT:", "a reboot has occured @" + new Timestamp(new DateTime().getMillis()).toString());
			NotifyReboot add_reboot_info = new NotifyReboot(context);
			add_reboot_info.addRebootRow();
		}
		Calendar cur_cal = Calendar.getInstance();
		Intent intent1 = new Intent(context, PushServiceStarter.class);
		PendingIntent pintent = PendingIntent.getBroadcast(context, 0, intent1,
				PendingIntent.FLAG_CANCEL_CURRENT);
		AlarmManager alarm = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		alarm.setRepeating(AlarmManager.RTC_WAKEUP, cur_cal.getTimeInMillis(),
				REPEAT_TIME, pintent);

}
}
