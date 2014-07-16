package com.mobiledi.appstrender.datausagetabs;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

import com.mobiledi.appstrender.AppObject;
import com.mobiledi.appstrender.Home;
import com.mobiledi.appstrender.PInfo;
import com.mobiledi.appstrender.networkutil.NetworkUtil;
import com.mobiledi.appstrender.serviceget.GETRequest;

public class BarGraphCalled {
	Context con;
	private TelephonyManager tm;
	public static ArrayList<AppObject> responseAppsList,responseAppsList2,responseAppsList3;
	public  boolean isSetResList = false;
	public  boolean isSetResList2 = false;
	public  boolean isSetResList3 = false;
	AlertDialog.Builder ab;
	AlertDialog abs;
	public BarGraphCalled(Context con) {
		this.con = con;
	}	

	public void callGraph() {
		preExecute();
		if(NetworkUtil.getConnectivityStatus(con)!=0){
			GETRequest getRequest,getRequest2,getRequest3;
			try {
				//String endDate="1970-01-01%2001:00:00";		
				String endDate=LocalDate.now().toString()+"%20"+LocalTime.now();
				Log.d("EndDate All Value", endDate);
				
				getRequest = new GETRequest(Home.SERVER_URL_ADD
						+ "readAllById/"+ tm.getDeviceId()+ "/"+ endDate, "Fetching Data from Appstrender Server..", "GETTING");
				responseAppsList = getRequest.returnObject;
				isSetResList = (responseAppsList.size() > 0 ? true : false);
			////
				// endDate=now.year+"-"+now.month+"-"+now.monthDay+"%20"+now.hour+":"+now.minute+":"+now.second;		
				//endDate="2014-06-14%2001:00:00";		
				LocalDate dt=LocalDate.now();
				dt=dt.minusMonths(1);
				endDate=dt.toString()+"%20"+LocalTime.now();
				Log.d("EndDate Month Value", endDate);
				
				getRequest2 = new GETRequest(Home.SERVER_URL_ADD
						+ "readAllById/"+ tm.getDeviceId()+ "/"+ endDate, "Fetching Data from Appstrender Server..", "GETTING");
				responseAppsList2 = getRequest2.returnObject;
				isSetResList2 = (responseAppsList2.size() > 0 ? true : false);
				
				////
				dt=LocalDate.now();
				dt=dt.minusWeeks(1);
				endDate=dt.toString()+"%20"+LocalTime.now();
				Log.d("EndDate Week Value", endDate);
				//endDate="2014-07-07%2001:00:00";		
				getRequest3 = new GETRequest(Home.SERVER_URL_ADD
						+ "readAllById/"+ tm.getDeviceId()+ "/"+ endDate, "Fetching Data from Appstrender Server..", "GETTING");
				responseAppsList3 = getRequest3.returnObject;
				isSetResList3 = (responseAppsList3.size() > 0 ? true : false);
				
				////
			
				abs.dismiss();
			
			} catch (Exception e) {		
				//Toast.makeText(con, "Oops! Couldn't connect to Server", Toast.LENGTH_LONG).show();
			e.printStackTrace();
			updateWithDefault();
			}
			}
			else{
				//Toast.makeText(con, "You are Not Connected to the Internet", Toast.LENGTH_LONG).show();
				updateWithDefault();

			}
		
	}
	public void updateWithDefault(){
		try {
			responseAppsList= new PInfo(
					con).getInstalledComponentList(0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  boolean getResponseStatus(int from){
		switch(from){
		case(1):
			return (isSetResList==true?true:false);	
		case(2):
			return (isSetResList2==true?true:false);	
		case(3):
			return (isSetResList3==true?true:false);	
		}
		return false;
		
	}
	public ArrayList<AppObject> getResponseAppsList(int from) {
		
		switch(from){
			case(1):
		return responseAppsList;
			case(2):
		return responseAppsList2;
			case(3):
		return responseAppsList3;
					
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
