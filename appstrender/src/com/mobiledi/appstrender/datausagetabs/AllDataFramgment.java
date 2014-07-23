package com.mobiledi.appstrender.datausagetabs;

import java.util.ArrayList;

import org.achartengine.GraphicalView;

import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mobiledi.appstrender.AppObject;
import com.mobiledi.appstrender.PInfo;
import com.mobiledi.appstrender.R;
import com.mobiledi.appstrender.graph.PieChartActivity;
import com.mobiledi.appstrender.graph.ShowBarChart;

public class AllDataFramgment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_alldata, container,
				false);
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		DataWrapper dw = (DataWrapper) getActivity().getIntent().getSerializableExtra("result");
		ArrayList<ArrayList<AppObject>> list = dw.getResult();
		ArrayList<AppObject> response = list.get(2);
		
		
		
		if(response!=null){
			
			ShowBarChart s = new ShowBarChart(getActivity(),response);
			GraphicalView Gv = s.openChart();
			LinearLayout layout = (LinearLayout) getActivity().findViewById(
						R.id.dashboard_chart_layout);
			layout.removeAllViews();
			layout.addView(Gv, new LayoutParams(960, LayoutParams.MATCH_PARENT));
			}
			else {
				Toast.makeText(getActivity(),
						"No Data to Display",
						Toast.LENGTH_LONG).show();
		}	
		//while()
/*	      try {
	    	  ArrayList<AppObject> listofApps = null; 
	  		ArrayList<AppObject> responseAppsList = new PInfo(
	  					getActivity()).getInstalledComponentList(0);
	  	    for(AppObject app: responseAppsList){
	          	if(app.getSent()>0){
	          		      
	          		listofApps.add(app);
	       
	          		}
	          }
	  	    
	  	  ShowBarChart s = new ShowBarChart(getActivity(),
					listofApps);
			GraphicalView Gv = s.openChart();
			LinearLayout layout = (LinearLayout) getActivity()
					.findViewById(R.id.dashboard_chart_layout);
			layout.removeAllViews();
			layout.addView(Gv, new LayoutParams(960,
					LayoutParams.MATCH_PARENT));
		} 
	  	    		
	  		
	  catch (NameNotFoundException e) {
	  		// TODO Auto-generated catch block
	  		e.printStackTrace();
	  	}
		
		
		
	*/	
		
		
		
		
		
		
		
		
		

		/*if (bcg.getResponseStatus(1)) {
			ShowBarChart s = new ShowBarChart(getActivity(),
					bcg.getResponseAppsList(1));
			GraphicalView Gv = s.openChart();
			LinearLayout layout = (LinearLayout) getActivity()
					.findViewById(R.id.dashboard_chart_layout);
			layout.removeAllViews();
			layout.addView(Gv, new LayoutParams(960,
					LayoutParams.MATCH_PARENT));
		} else {
			Toast.makeText(getActivity(), "No Data to Display",
					Toast.LENGTH_LONG).show();
		}
		*/
		
		
	}


	
/*	public void loadView(ArrayList<AppObject> returnObj){
		
		if(returnObj!=null){
			ShowBarChart s = new ShowBarChart(getActivity(),
				returnObj);
		GraphicalView Gv = s.openChart();
		LinearLayout layout = (LinearLayout) getActivity()
				.findViewById(R.id.dashboard_chart_layout);
		layout.removeAllViews();
		layout.addView(Gv, new LayoutParams(960,
				LayoutParams.MATCH_PARENT));
	} else {
		Toast.makeText(getActivity(), "No Data to Display",
				Toast.LENGTH_LONG).show();
		
	}
	
	
	}*/
}

