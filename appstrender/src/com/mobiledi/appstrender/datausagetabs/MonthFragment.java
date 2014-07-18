package com.mobiledi.appstrender.datausagetabs;

import org.achartengine.GraphicalView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.mobiledi.appstrender.R;
import com.mobiledi.appstrender.graph.ShowBarChart;

public class MonthFragment extends Fragment {
	BarGraphCalled bcg;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_datamonth,
				container, false);
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		 bcg=new BarGraphCalled(getActivity());			
		bcg.callGraphMonth();
		if (bcg.getResponseStatus(2)) {
		ShowBarChart s = new ShowBarChart(getActivity(), bcg.getResponseAppsList(2));
		GraphicalView Gv = s.openChart();
		LinearLayout layout = (LinearLayout) getActivity().findViewById(
				R.id.dashboard_chart_layoutmonth);
		layout.removeAllViews();
		layout.addView(Gv, new LayoutParams(960, LayoutParams.MATCH_PARENT));
		}
		else {
			Toast.makeText(getActivity(),
					"No Data to Display",
					Toast.LENGTH_LONG).show();
		}
	}
}