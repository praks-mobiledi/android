package com.mobiledi.appstrender.datausagetabs;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.mobiledi.appstrender.AppObject;
import com.mobiledi.appstrender.Home;
import com.mobiledi.appstrender.networkutil.NetworkUtil;
import com.mobiledi.appstrender.serviceget.GETRequest;

public class BarGraphCalled {
	Context con;
	private TelephonyManager tm;
	public static ArrayList<AppObject> responseAppsListAll,responseAppsListMonth,responseAppsListWeek;
	public  boolean isSetResListAll = false;
	public  boolean isSetResListMonth = false;
	public  boolean isSetResListWeek = false;
	AlertDialog.Builder ab;
	AlertDialog abs;
	public BarGraphCalled(Context con) {
		this.con = con;
	}	

	public void callGraphAll() {
		preExecute();
		if(NetworkUtil.getConnectivityStatus(con)!=0){
			GETRequest getRequestAll;
			try {		
				String endDate="2014-01-01%2000:00:00";
				System.out.println("ALL: " + endDate);
				
				getRequestAll = new GETRequest(Home.SERVER_URL_ADD
						+ "readAllById/"+ tm.getDeviceId()+ "/"+ endDate, "Fetching Data from Appstrender Server..", "GETTING");
				responseAppsListAll = getRequestAll.returnObject;
				isSetResListAll = (responseAppsListAll==null? false : true);
				
			
			} catch (Exception e) {		
				//Toast.makeText(con, "Oops! Couldn't connect to Server", Toast.LENGTH_LONG).show();
			e.printStackTrace();
			}
			}
		abs.dismiss();
	}
	
	public void callGraphMonth(){
		preExecute();
		if(NetworkUtil.getConnectivityStatus(con)!=0){
			GETRequest getRequestMonth;
			LocalDate dt=LocalDate.now();
			dt=dt.minusMonths(1);
			String endDate=dt.toString()+"%20"+LocalTime.now();
			Log.d("EndDate Month Value", endDate);		
			try {
				getRequestMonth = new GETRequest(Home.SERVER_URL_ADD
						+ "readAllById/"+ tm.getDeviceId()+ "/"+ endDate, "Fetching Data from Appstrender Server..", "GETTING");
				responseAppsListMonth = getRequestMonth.returnObject;
				isSetResListMonth = (responseAppsListMonth==null ? false : true);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
	}
		abs.dismiss();
	}

	public void callGraphWeek(){
		preExecute();
		LocalDate dt = LocalDate.now();
		dt=dt.minusWeeks(1);
		String endDate = dt.toString()+"%20"+LocalTime.now();
		System.out.println("Week: " + endDate);
		//endDate="2014-07-07%2001:00:00";		
		GETRequest getRequestWeek;
		try {
			getRequestWeek = new GETRequest(Home.SERVER_URL_ADD
					+ "readAllById/"+ tm.getDeviceId()+ "/"+ endDate, "Fetching Data from Appstrender Server..", "GETTING");
			responseAppsListWeek = getRequestWeek.returnObject;
			isSetResListWeek = (responseAppsListWeek==null ? false : true);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		abs.dismiss();
	}

	public  boolean getResponseStatus(int from){
		switch(from){
		case(1):return isSetResListAll;	
		case(2):return isSetResListMonth;	
		case(3):return isSetResListWeek;	
		}
		return false;
		
	}
	public ArrayList<AppObject> getResponseAppsList(int from) {	
		switch(from){
			case(1):return responseAppsListAll;
			case(2):return responseAppsListMonth;
			case(3):return responseAppsListWeek;				
		}
		return null;
	}
	protected void preExecute() {
		ab= new Builder(con);
		ab.setMessage("Loading Data Please Wait");
		ab.setCancelable(true);
		abs=ab.create();
		abs.show();
		tm=(TelephonyManager)con.getSystemService(Context.TELEPHONY_SERVICE);
	}
}
