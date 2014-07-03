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
	ImageButton allGraph;
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
		allGraph=(ImageButton) getView().findViewById(R.id.imageButton1);
		
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
					
			/*		  if(returnedList.get(arg2).getSent()!=0){ 
						  ShowPieChart  showPie = new ShowPieChart(getActivity(),returnedList.get(arg2));
						  showPie.openChart(); 
						  }
					  else {
					  Toast
					  .makeText(getActivity(),returnedList.get(arg2).getAppName
					 ()+" have no data Usage History",
					  Toast.LENGTH_LONG).show();
					 
					  }*/
					
					/* if(returnedList.get(arg2).getSent()!=0){ 
						 Intent i= new Intent(getActivity(),PieChartActivity.class);
						 i.putExtra("Selected", String.valueOf(arg2));
						 startActivity(i);
						 
					 }*/
					 
					Intent s = new Intent(getActivity(), PieChartActivity.class);
					startActivity(s);
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
		Intent s = new Intent(getActivity(), DataUsageTabs.class);
		startActivity(s);
	}
});
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
			getRequest = new GETRequest(Home.SERVER_URL_ADD+"readAll/Xperia_M",
					"JUST a message", "GETTING");
			responseAppsList = getRequest.returnObject;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(getActivity(), "You are Not Connected to the Internet", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}

	}

}
