package com.mobiledi.appstrender.datausagetabs;

import java.util.ArrayList;

import org.achartengine.GraphicalView;

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

import com.mobiledi.appstrender.AppObject;
import com.mobiledi.appstrender.PInfo;
import com.mobiledi.appstrender.R;
import com.mobiledi.appstrender.graph.ShowBarChart;

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
		DataWrapper dw = (DataWrapper) getActivity().getIntent().getSerializableExtra("result");
		/*ArrayList<ArrayList<AppObject>> list = dw.getResult();
		ArrayList<AppObject> response = list.get(1);*/
			ArrayList<AppObject> response = dw.getResult().get(1);
			if(response!=null){
			ShowBarChart s = new ShowBarChart(getActivity(),response);
			GraphicalView Gv = s.openChart();
			LinearLayout layout = (LinearLayout) getActivity().findViewById(
						R.id.dashboard_chart_layoutWeek);
			layout.removeAllViews();
			layout.addView(Gv, new LayoutParams(960, LayoutParams.MATCH_PARENT));
			}
			else {
				Toast.makeText(getActivity(),
						"No utilization data available at this time",
				Toast.LENGTH_SHORT).show();	
			}
}
}		
