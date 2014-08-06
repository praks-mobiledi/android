package com.mobiledi.appstrender;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.TrafficStats;

public class PInfo {
	Context con;
	// List<String> listOfApps;
	ArrayList<AppObject> listofApps = new ArrayList<AppObject>();
	public PInfo(Context _con) {
		con = _con;
		// getInstalledComponentList();
	}

	// mode 0=all,1=systemapps,2=downloaded
	public ArrayList<AppObject> getInstalledComponentList(int MODE)
			throws NameNotFoundException {
		final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		List<ResolveInfo> ril = con.getPackageManager().queryIntentActivities(
				mainIntent, 0);
		// List<String> componentList = new ArrayList<String>();
		// List<Drawable> icons = null;

		String name = null;

		for (ResolveInfo ri : ril) {
			if (ri.activityInfo != null) {
				AppObject tempObj = new AppObject();

				Resources res = con.getPackageManager()
						.getResourcesForApplication(
								ri.activityInfo.applicationInfo);
				Drawable icon = con.getPackageManager().getApplicationIcon(
						ri.activityInfo.applicationInfo);

				int uid = ri.activityInfo.applicationInfo.uid;
				long recieved = (TrafficStats.getUidRxBytes(uid));
				long send = TrafficStats.getUidTxBytes(uid);

				if (recieved < 0) {
					recieved = 0;
					send = 0;
				}

				// String
				// info=con.getPackageManager().getApplicationInfo(ri.activityInfo.applicationInfo);
				if (ri.activityInfo.labelRes != 0) {
					name = res.getString(ri.activityInfo.labelRes);
				} else {
					name = ri.activityInfo.applicationInfo.loadLabel(
							con.getPackageManager()).toString();
				}

				switch (MODE) {
				case 0:
					tempObj.setAppUid(uid);
					tempObj.setRecieved(recieved);
					tempObj.setSent(send);
					tempObj.setAppName(name);
					tempObj.setIcon(icon);
					// componentList.add(name);
					// icons.add(icon);
					listofApps.add(tempObj);
					break;
				// return listofApps;
				case 1:
					if ((ri.activityInfo.applicationInfo.flags & ri.activityInfo.applicationInfo.FLAG_SYSTEM) != 0) {
						tempObj.setAppUid(uid);
						tempObj.setRecieved(recieved);
						tempObj.setSent(send);
						tempObj.setAppName(name);
						tempObj.setIcon(icon);
						listofApps.add(tempObj);
						// return listofSystemApps;
					}
					break;
				case 2:
					if ((ri.activityInfo.applicationInfo.flags & ri.activityInfo.applicationInfo.FLAG_SYSTEM) != 0) {

					} else {
						tempObj.setAppUid(uid);
						tempObj.setRecieved(recieved);
						tempObj.setSent(send);
						tempObj.setAppName(name);
						tempObj.setIcon(icon);
						// componentList.add(name);
						// icons.add(icon);
						listofApps.add(tempObj);
					}
					break;
				}
				/*Log.d("New Pack Info",
						tempObj.getAppUid() + " " + tempObj.getAppName());*/
				// ////////Check for system packages

			}
		}
		return listofApps;
	}
}
