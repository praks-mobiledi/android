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
import android.view.LayoutInflater;
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

public class SocialFragment extends Fragment {
	
	ImageButton allGraph;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_social, container,
				false);

		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		allGraph=(ImageButton) getView().findViewById(R.id.imageButton3);
		
		ListView mainLV = (ListView) getView().findViewById(R.id.mainLvS);

		try {
			final ArrayList<AppObject> returnedList = new PInfo(getActivity())
					.getInstalledComponentList(1);
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
						  } else {
					  Toast
					  .makeText(getActivity(),returnedList.get(arg2).getAppName
					 ()+" have no data Usage History",
					  Toast.LENGTH_LONG).show(); 
					  }

				}
			});
		} catch (NameNotFoundException e) {
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
						//Intent s = new Intent(getActivity(), DataUsageTabs.class);
						///getActivity().startActivity(s);
						//Log.d("TESTURL Soc",Home.SERVER_URL_ADD.substring(7));
						
						//TelephonyManager tm = (TelephonyManager)getActivity().getSystemService(Context.TELEPHONY_SERVICE); 	
						//tester.execute("192.168.43.180:8080/appstrender_service/appstrender/appdata/readServerState/"+tm.getDeviceId());
						//Log.d("TESTURL",Home.SERVER_URL_ADD.substring(7));
					} catch(Exception e){
								e.printStackTrace();
							
						}

			}
	return;
		}
		});
	}
}
