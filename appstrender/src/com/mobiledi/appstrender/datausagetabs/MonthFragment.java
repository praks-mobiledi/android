package com.mobiledi.appstrender.datausagetabs;

import java.sql.Timestamp;
import java.util.ArrayList;
import org.achartengine.GraphicalView;
import org.joda.time.DateTime;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mobiledi.appstrender.AllAppsFramgment;
import com.mobiledi.appstrender.AppObject;
import com.mobiledi.appstrender.R;
import com.mobiledi.appstrender.graph.ShowBarChart;

public class MonthFragment extends Fragment {


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_datamonth,
				container, false);
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

		BarGraphCalled bcg=new BarGraphCalled(getActivity());
		bcg.callGraph();
		if (bcg.getResponseStatus(2)) {
		//ArrayList<AppObject> toplot = new ArrayList<AppObject>();
		ShowBarChart s = new ShowBarChart(getActivity(), bcg.getResponseAppsList(2));
		GraphicalView Gv = s.openChart();
		LinearLayout layout = (LinearLayout) getActivity().findViewById(
				R.id.dashboard_chart_layoutmonth);
		layout.removeAllViews();
		layout.addView(Gv, new LayoutParams(960, LayoutParams.MATCH_PARENT));
		}
		else {
			Toast.makeText(getActivity(),
					"Not Connected to Remote Server",
					Toast.LENGTH_LONG).show();
		}
	}
}