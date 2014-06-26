package com.mobiledi.appstrender.datausagetabs;

import org.achartengine.GraphicalView;

import com.mobiledi.appstrender.PInfo;
import com.mobiledi.appstrender.R;
import com.mobiledi.appstrender.graph.ShowBarChart;

import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class WeekFragments extends Fragment {


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_alldata, container,
				false);
		// setHasOptionsMenu(true);
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		try {
			ShowBarChart s= new ShowBarChart(getActivity(), new PInfo(getActivity())
						.getInstalledComponentList(0));
		
		GraphicalView Gv=s.openChart();
		
		LinearLayout layout = (LinearLayout) getActivity().findViewById(R.id.dashboard_chart_layout);	    
	    //layout.removeAllViews();
	    layout.addView(Gv, new LayoutParams(960,LayoutParams.MATCH_PARENT));
		
		
		
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
