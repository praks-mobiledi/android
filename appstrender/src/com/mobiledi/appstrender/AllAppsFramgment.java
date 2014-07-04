package com.mobiledi.appstrender;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.mobiledi.appstrender.adapters.CustomAdapter;
import com.mobiledi.appstrender.datausagetabs.DataUsageTabs;
import com.mobiledi.appstrender.graph.PieChartActivity;
import com.mobiledi.appstrender.graph.ShowBarChart;
import com.mobiledi.appstrender.graph.ShowPieChart;
import com.mobiledi.appstrender.serviceget.GETRequest;

public class AllAppsFramgment extends Fragment {
	public static ArrayList<AppObject> responseAppsList;
	public static boolean isSetResList = false;
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
				// TODO Auto-generated method stub
				if (isSetResList) {
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
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		GETRequest getRequest;
		try {
			getRequest = new GETRequest(Home.SERVER_URL_ADD
					+ "readAll/Xperia_M", "Fetching Data from Appstrender Server..", "GETTING");
			responseAppsList = getRequest.returnObject;
			isSetResList = (responseAppsList.size() > 0 ? true : false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// Toast.makeText(getActivity(),
			// "You are Not Connected to the Internet",
			// Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}

	}
	
	public static boolean getResponseStatus(){
		return (isSetResList==true?true:false);
	}
	public static ArrayList<AppObject> getResponseAppsList() {
		return responseAppsList;
	}

}
