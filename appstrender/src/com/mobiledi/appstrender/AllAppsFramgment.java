package com.mobiledi.appstrender;

import java.util.ArrayList;

import org.achartengine.chart.BarChart;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.text.format.Time;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import com.mobiledi.appstrender.adapters.CustomAdapter;
import com.mobiledi.appstrender.datausagetabs.BarGraphCalled;
import com.mobiledi.appstrender.datausagetabs.DataUsageTabs;
import com.mobiledi.appstrender.graph.PieChartActivity;
import com.mobiledi.appstrender.networkutil.NetworkUtil;
import com.mobiledi.appstrender.serviceget.GETRequest;

public class AllAppsFramgment extends Fragment {
	ImageButton allGraph;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_all_apps, container,
				false);
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		ListView mainLV = (ListView) getView().findViewById(R.id.mainLv);
		registerForContextMenu(mainLV);
		allGraph = (ImageButton) getView().findViewById(R.id.imageButton1);
		// setHasOptionsMenu(true);
		try {
			final ArrayList<AppObject> returnedList = new PInfo(getActivity())
					.getInstalledComponentList(0);
			CustomAdapter adapter = new CustomAdapter(getActivity(),
					returnedList, R.layout.single_row);
			mainLV.setAdapter(adapter);
			mainLV.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {

					if (returnedList.get(arg2).getSent() != 0) {
						Intent s = new Intent(getActivity(),
								PieChartActivity.class);
						// returnedList.get(arg2).getAppName();
						s.putExtra("SELECT", returnedList.get(arg2)
								.getAppName());
						//s.put
						startActivity(s);
					} else {
						Toast.makeText(
								getActivity(),
								returnedList.get(arg2).getAppName()
										+ " have no data Usage History",
								Toast.LENGTH_LONG).show();

					}
				}
			});

		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Builder ab = new Builder(getActivity());
		ab.setMessage("Connecting to Server...");
		ab.setCancelable(true);
		ab.setTitle("Info");
		ab.setIcon(R.drawable.loading);
		final AlertDialog abs = ab.create();
		
		allGraph.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				abs.show();
				(new Thread() {	public void run() {	
					try {sleep(3000);abs.dismiss();	} 
				catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						abs.dismiss();}}}).start();
				if (NetworkUtil.getConnectivityStatus(getActivity())!=0) {
					Intent s = new Intent(getActivity(), DataUsageTabs.class);
					startActivity(s);
				} else {
					Toast.makeText(getActivity(),
							"Not Connected to Appstrender Server",
							Toast.LENGTH_LONG).show();
				}
			}});
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getActivity().getMenuInflater();
		inflater.inflate(R.menu.home, menu);
	}
/*
	@Override
	public boolean onContextItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.allApps:
			ShowBarChart showGraph;
			try {
				showGraph = new ShowBarChart(getActivity(), new PInfo(
						getActivity()).getInstalledComponentList(0));
				showGraph.openChart();

			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}*/

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		/*tm=(TelephonyManager)getActivity().getSystemService(Context.TELEPHONY_SERVICE);
		GETRequest getRequest,getRequest2,getRequest3;
		if(NetworkUtil.getConnectivityStatus(getActivity())!=0){
		try {
			Time now = new Time();
			now.setToNow();
			//String endDate=now.year+"-"+now.month+"-"+now.monthDay+"%20"+now.hour+":"+now.minute+":"+now.second;
			String endDate="1970-01-01%2001:00:00";		
			getRequest = new GETRequest(Home.SERVER_URL_ADD
					+ "readAllById/"+ tm.getDeviceId()+ "/"+ endDate, "Fetching Data from Appstrender Server..", "GETTING");
			allResponseAppsList = getRequest.returnObject;
			isSetResListAll = (allResponseAppsList.size() > 0 ? true : false);
		////
			// endDate=now.year+"-"+now.month+"-"+now.monthDay+"%20"+now.hour+":"+now.minute+":"+now.second;		
			endDate="2014-06-14%2001:00:00";		
			
			getRequest2 = new GETRequest(Home.SERVER_URL_ADD
					+ "readAllById/"+ tm.getDeviceId()+ "/"+ endDate, "Fetching Data from Appstrender Server..", "GETTING");
			responseAppsList2 = getRequest2.returnObject;
			isSetResListMonth = (responseAppsList2.size() > 0 ? true : false);
			
			////
			
			endDate="2014-07-07%2001:00:00";		
			getRequest3 = new GETRequest(Home.SERVER_URL_ADD
					+ "readAllById/"+ tm.getDeviceId()+ "/"+ endDate, "Fetching Data from Appstrender Server..", "GETTING");
			responseAppsListWeek = getRequest3.returnObject;
			isSetResListWeek = (responseAppsListWeek.size() > 0 ? true : false);
			
			////
		
		
		
		} catch (Exception e) {		
			Toast.makeText(getActivity(), "Oops! Couldn't connect to Server", Toast.LENGTH_LONG).show();
		e.printStackTrace();
		updateWithDefault();
		}
		}
		else{
			Toast.makeText(getActivity(), "You are Not Connected to the Internet", Toast.LENGTH_LONG).show();
			updateWithDefault();
		}*/
	}
	/*public void updateWithDefault(){
		try {
			allResponseAppsList= new PInfo(
					getActivity()).getInstalledComponentList(0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	/*public static boolean getResponseStatus(int from){
		switch(from){
		case(1):
			return (isSetResListAll==true?true:false);	
		case(2):
			return (isSetResListMonth==true?true:false);	
		case(3):
			return (isSetResListWeek==true?true:false);	
		}
		return false;
		
	}
	public static ArrayList<AppObject> getResponseAppsList(int from) {
		
		switch(from){
			case(1):
		return allResponseAppsList;
			case(2):
		return responseAppsList2;
			case(3):
		return responseAppsListWeek;
					
		}
		return null;
	}*/

}
