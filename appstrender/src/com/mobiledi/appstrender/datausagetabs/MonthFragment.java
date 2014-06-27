package com.mobiledi.appstrender.datausagetabs;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.achartengine.GraphicalView;
import org.joda.time.DateTime;

import com.mobiledi.appstrender.AllAppsFramgment;
import com.mobiledi.appstrender.AppObject;
import com.mobiledi.appstrender.PInfo;
import com.mobiledi.appstrender.R;
import com.mobiledi.appstrender.graph.ShowBarChart;

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

public class MonthFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_datamonth,
				container, false);
		// setHasOptionsMenu(true);
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		if (AllAppsFramgment.responseAppsList.size() > 0) {
			ArrayList<AppObject> toplot=new ArrayList<AppObject>();
			for(AppObject x: AllAppsFramgment.responseAppsList){
				if(x.getTimeStamp().after(new Timestamp(new DateTime().minusMonths(1).getMillis()))){
					
				toplot.add(x);
					
					//Log.d("t1",x.getTimeStamp().toString());
				Log.d("t2",new Timestamp(new DateTime().minusMonths(1).getMillis()).toString());//after
				
			}
			
		}
			//
			ShowBarChart s = new ShowBarChart(getActivity(),
					toplot);
			GraphicalView Gv = s.openChart();
			LinearLayout layout = (LinearLayout) getActivity().findViewById(
					R.id.dashboard_chart_layoutmonth);
			layout.removeAllViews();
			layout.addView(Gv, new LayoutParams(960, LayoutParams.MATCH_PARENT));
		} else {
			Toast.makeText(getActivity(), "No Data to Display",
					Toast.LENGTH_LONG).show();
		}

	}
}