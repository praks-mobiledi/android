package com.mobiledi.appstrender.graph;

import org.achartengine.GraphicalView;

import com.mobiledi.appstrender.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
public class PieChartFragment extends Fragment{
	
	int mCurrentPage;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/** Getting the arguments to the Bundle object */
		Bundle data = getArguments();
		
		/** Getting integer data of the key current_page from the bundle */
		mCurrentPage = data.getInt("current_page", 0);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.piechart_layout, container,false);				
		//TextView tv = (TextView ) v.findViewById(R.id.tv);
		
		ShowPieChart sp = new ShowPieChart(getActivity(),
				PieChartActivity.listofApps.get(mCurrentPage));
		Log.d("Curr Sent Values", String.valueOf(mCurrentPage));
		Log.d("Curr Sent Values", String.valueOf(PieChartActivity.listofApps.get(mCurrentPage).getSent()));
		Log.d("Curr Rcv Values", String.valueOf(PieChartActivity.listofApps.get(mCurrentPage).getRecieved()));
		
		GraphicalView Gv = sp.openChart();
		LinearLayout layout = (LinearLayout) v.findViewById(
				R.id.showgraph);
		layout.removeAllViews();
		layout.addView(Gv, new LayoutParams(960, LayoutParams.MATCH_PARENT));
		
		//tv.setText("You are viewing the page #" + mCurrentPage + "\n\n" + "Swipe Horizontally left / right");	
		return v;		
	}
}
