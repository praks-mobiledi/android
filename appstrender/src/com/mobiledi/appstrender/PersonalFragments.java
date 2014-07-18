package com.mobiledi.appstrender;

import java.util.ArrayList;

import com.mobiledi.appstrender.adapters.CustomAdapter;
import com.mobiledi.appstrender.datausagetabs.BarGraphCalled;
import com.mobiledi.appstrender.datausagetabs.DataUsageTabs;
import com.mobiledi.appstrender.graph.PieChartActivity;
import com.mobiledi.appstrender.networkutil.NetworkUtil;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class PersonalFragments extends Fragment {
	ImageButton allGraph;
	//public static boolean isSetResListAll=false;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_personal, container,
				false);

		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		allGraph=(ImageButton) getView().findViewById(R.id.imageButton2);
		
		ListView mainLV = (ListView) getView().findViewById(R.id.mainLvP);

		try {
			final ArrayList<AppObject> returnedList = new PInfo(getActivity())
					.getInstalledComponentList(2);
			CustomAdapter adapter = new CustomAdapter(getActivity(),
					returnedList, R.layout.single_row);
			mainLV.setAdapter(adapter);
			mainLV.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					  if(returnedList.get(arg2).getSent()!=0){ 
						  Intent s = new Intent(getActivity(),
									PieChartActivity.class);
							// returnedList.get(arg2).getAppName();
							s.putExtra("SELECT", returnedList.get(arg2)
									.getAppName());
							startActivity(s);  
					  } 
					  else {
					  Toast
					  .makeText(getActivity(),returnedList.get(arg2).getAppName
					 ()+" have no data Usage History",
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
			}
		});
		
	}
	
}
