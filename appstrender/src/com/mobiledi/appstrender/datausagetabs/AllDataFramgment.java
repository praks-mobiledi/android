package com.mobiledi.appstrender.datausagetabs;

import org.achartengine.GraphicalView;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mobiledi.appstrender.AllAppsFramgment;
import com.mobiledi.appstrender.R;
import com.mobiledi.appstrender.graph.ShowBarChart;

public class AllDataFramgment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_alldata, container,
				false);
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

		BarGraphCalled bcg=new BarGraphCalled(getActivity());
		bcg.callGraph();

		if (bcg.getResponseStatus(1)) {
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
		
		
		
		}
	}


