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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.mobiledi.appstrender.adapters.CustomAdapter;
import com.mobiledi.appstrender.datausagetabs.DataUsageTabs;
import com.mobiledi.appstrender.graph.ShowBarChart;
import com.mobiledi.appstrender.graph.ShowPieChart;
import com.mobiledi.appstrender.serviceget.GETRequest;
import com.mobiledi.appstrender.servicepush.PUSHRequest;

public class AllAppsFramgment extends Fragment {
	public static ArrayList<AppObject> responseAppsList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_all_apps, container,
				false);
		// setHasOptionsMenu(true);
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		ListView mainLV = (ListView) getView().findViewById(R.id.mainLv);
		registerForContextMenu(mainLV);
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

					/*
					 * ShowBarChart showGraph=new ShowBarChart(getActivity(),
					 * returnedList); //ArrayList<AppObject> toShow= new
					 * ArrayList<AppObject>();
					 * //toShow.add(returnedList.get(arg2)); //ShowBarChart
					 * showGraph=new ShowBarChart(getActivity(), toShow);
					 * showGraph.openChart();
					 */

					// ///////
					/*
					 * if(returnedList.get(arg2).getSent()!=0){ ShowPieChart
					 * showPie = new ShowPieChart(getActivity(),
					 * returnedList.get(arg2)); showPie.openChart(); } else {
					 * Toast
					 * .makeText(getActivity(),returnedList.get(arg2).getAppName
					 * ()+" have no data Usage History",
					 * Toast.LENGTH_LONG).show();
					 * 
					 * }
					 */
					Intent s = new Intent(getActivity(), DataUsageTabs.class);
					startActivity(s);
				}
			});

		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
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
			getRequest = new GETRequest(
					"http://192.168.1.2:8080/appstrender_service/appstrender/appdata/readAll/Xperia_l",
					"JUST a messaghe", "GETTING");
			responseAppsList = getRequest.returnObject;

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
