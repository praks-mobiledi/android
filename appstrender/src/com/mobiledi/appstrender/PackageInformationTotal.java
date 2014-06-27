/*
 * package com.mobiledi.appstrender;
 * 
 * import java.util.List;
 * 
 * import android.content.Context; import
 * android.content.SharedPreferences.Editor; import
 * android.content.pm.ApplicationInfo; import android.content.pm.PackageManager;
 * import android.content.pm.PackageManager.NameNotFoundException; import
 * android.graphics.drawable.Drawable; import android.net.TrafficStats; import
 * android.util.Log;
 * 
 * public class PackageInformationTotal { Context con; private String name;
 * private String packageName; private Drawable icon; private String totalMB;
 * private String individual_mb; private double totalData; public
 * PackageInformationTotal(Context con_){ this.con=con_;
 * 
 * } public void getPakagesInfoUsingHashMap() { final PackageManager pm =
 * con.getPackageManager(); // get a list of installed apps.
 * List<ApplicationInfo> packages = pm.getInstalledApplications(0);
 * 
 * // loop through the list of installed packages and see if the selected // app
 * is in the list for (ApplicationInfo packageInfo : packages) { // get the UID
 * for the selected app UID = packageInfo.uid; String package_name =
 * packageInfo.packageName; ApplicationInfo app = null; try { app =
 * pm.getApplicationInfo(package_name, 0); } catch (NameNotFoundException e) {
 * // TODO Auto-generated catch block e.printStackTrace(); } String name =
 * (String) pm.getApplicationLabel(app); Drawable icon =
 * pm.getApplicationIcon(app); // internet usage for particular app(sent and
 * received) double received = (double) TrafficStats.getUidRxBytes(UID)
 * 
 * / (1024 * 1024); double send = (double) TrafficStats.getUidTxBytes(UID) /
 * (1024 * 1024); double total = received + send;
 * 
 * if(total>0) { PackageInformationTotal pi=new PackageInformationTotal(con);
 * pi.name=name; pi.packageName=package_name; pi.icon=icon;
 * pi.totalMB=String.format( "%.2f", total )+" MB";
 * pi.individual_mb=String.format( "%.2f", total );
 * totalData+=Double.parseDouble(String.format( "%.2f", total ));
 * List<ApplicationInfo> dataHash; dataHash.add(pi); Log.e(name,String.format(
 * "%.2f", total )+" MB"); }
 * 
 * } Editor edit=shared.edit(); edit.putString("Total",String.format( "%.2f",
 * totalData)); edit.commit(); } }
 */