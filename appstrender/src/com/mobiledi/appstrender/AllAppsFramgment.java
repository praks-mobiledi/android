package com.mobiledi.appstrender;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.util.Log;
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
import com.mobiledi.appstrender.networkutil.PingIP;

public class AllAppsFramgment extends Fragment {
	ImageButton allGraph;
//	static Boolean result=false;
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

		
allGraph.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				
				if(NetworkUtil.getConnectivityStatus(getActivity())==0) {
					Toast.makeText(getActivity(),
							"Check Your Internet",
							Toast.LENGTH_LONG).show();
					return;
				}
				else{
					
					//PingIP tester= new PingIP(getActivity());
					try {
					BarGraphCalled bgc= new	BarGraphCalled(getActivity());
					bgc.execute();
						} catch(Exception e){
								e.printStackTrace();
		
						}
			}
	return;
	}
		});
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getActivity().getMenuInflater();
		inflater.inflate(R.menu.home, menu);
	}
}
