package com.mobiledi.appstrender.datausagetabs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.achartengine.GraphicalView;

import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mobiledi.appstrender.AllAppsFramgment;
import com.mobiledi.appstrender.AppObject;
import com.mobiledi.appstrender.PInfo;
import com.mobiledi.appstrender.R;
import com.mobiledi.appstrender.graph.ShowBarChart;
import com.mobiledi.appstrender.servicepush.PUSHRequest;

import android.view.ViewGroup.LayoutParams;

public class AllDataFramgment extends Fragment {

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
		/*
		 * getRequest = new RestRequest(
		 * "http://192.168.1.3:8080/appstrender_service/appstrender/appdata/readAll/Xperia_l"
		 * , "JUST a messaghe","GETTING"); ArrayList<AppObject>
		 * objs=getRequest.returnObject;
		 */
		if (AllAppsFramgment.responseAppsList.size() > 0) {
			ShowBarChart s = new ShowBarChart(getActivity(),
					AllAppsFramgment.responseAppsList);
			GraphicalView Gv = s.openChart();
			LinearLayout layout = (LinearLayout) getActivity().findViewById(
					R.id.dashboard_chart_layout);
			layout.removeAllViews();
			layout.addView(Gv, new LayoutParams(960, LayoutParams.MATCH_PARENT));
		} else {
			Toast.makeText(getActivity(), "No Data to Display",
					Toast.LENGTH_LONG).show();
		}

	}

}
