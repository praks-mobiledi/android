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
		if(list!=null && response!=null){		
			//if(response!=null){
			String status=(list.get(1).get(0).getAppName()==null?"NODATA":list.get(1).get(0).getAppName());
				if(status.equals("ERROR")==false){
				
			ShowBarChart s = new ShowBarChart(getActivity(),response);
			GraphicalView Gv = s.openChart();
			LinearLayout layout = (LinearLayout) getActivity().findViewById(
						R.id.dashboard_chart_layout);
			layout.removeAllViews();
			layout.addView(Gv, new LayoutParams(960, LayoutParams.MATCH_PARENT));
			}
		else {	
						Toast.makeText(getActivity(),
								"No utilization data available at this time",
						Toast.LENGTH_SHORT).show();	
			}	
	}
	else {
		Toast.makeText(getActivity(),
				"No utilization data available at this time",
				Toast.LENGTH_SHORT).show();	
								}

}
}
