package com.mobiledi.appstrender.datausagetabs;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.achartengine.GraphicalView;
import org.joda.time.DateTime;

import com.mobiledi.appstrender.AllAppsFramgment;
import com.mobiledi.appstrender.AppObject;
import com.mobiledi.appstrender.PInfo;
import com.mobiledi.appstrender.R;
import com.mobiledi.appstrender.graph.ShowBarChart;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.Toast;

public class WeekFragments extends Fragment {


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_week, container,
				false);
		// setHasOptionsMenu(true);
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

		BarGraphCalled bcg=new BarGraphCalled(getActivity());
		bcg.callGraph();
		if (bcg.getResponseStatus(3)) {
		ShowBarChart s = new ShowBarChart(getActivity(),bcg.getResponseAppsList(3));
		GraphicalView Gv = s.openChart();
		LinearLayout layout = (LinearLayout) getActivity().findViewById(
					R.id.dashboard_chart_layoutWeek);
		layout.removeAllViews();
		layout.addView(Gv, new LayoutParams(960, LayoutParams.MATCH_PARENT));
		}
		else {
			Toast.makeText(getActivity(),
					"Not Connected to Appstrender Server",
					Toast.LENGTH_LONG).show();
	}
}
}