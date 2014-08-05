package com.mobiledi.appstrender.graph;

import org.achartengine.GraphicalView;

import com.mobiledi.appstrender.AppObject;
import com.mobiledi.appstrender.R;

import android.content.pm.PackageStats;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
public class PieChartFragment extends Fragment{
	long totalUsage;
	int mCurrentPage;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/** Getting the arguments to the Bundle object */
		Bundle data = getArguments();
		
		/** Getting integer data of the key current_page from the bundle */
		mCurrentPage = data.getInt("current_page", 0);
		totalUsage=getTotalUsage();
	}
	
	private long getTotalUsage() {
		long usage=0;
		for(AppObject obj: PieChartActivity.listofApps){
			
			usage=usage+obj.getSent()+obj.getRecieved();	
		}
		return usage;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.piechart_layout, container,false);				
		TextView tv = (TextView) v.findViewById(R.id.tv1);
		TextView tvS = (TextView) v.findViewById(R.id.tvsent);
		TextView tvR = (TextView) v.findViewById(R.id.tvrecieved);
		ImageView iv= (ImageView) v.findViewById(R.id.icons);
		ShowPieChart sp = new ShowPieChart(getActivity(),
				PieChartActivity.listofApps.get(mCurrentPage),this.totalUsage);
		GraphicalView Gv = sp.openChart();
		LinearLayout layout = (LinearLayout) v.findViewById(
				R.id.showgraph);
		layout.removeAllViews();
		//1st parameter was 960
		layout.addView(Gv, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		long sent=PieChartActivity.listofApps.get(mCurrentPage).getSent()/1048576;
		long recieved=PieChartActivity.listofApps.get(mCurrentPage).getRecieved()/1048576;
		if(recieved > 0){
			tvR.setText("Recieved: " +String.valueOf(recieved)+ " MB");
			if(sent>0){
			tvS.setText("Sent: " + String.valueOf(sent) + " MB");
			}
			else{
				tvS.setText("Sent: " + String.valueOf(PieChartActivity.listofApps.get(mCurrentPage).getSent()/1024)+ " KB");
			}
		}
		else{
			tvS.setText("Sent: " + String.valueOf(PieChartActivity.listofApps.get(mCurrentPage).getSent()/1024)+ " KB");
			tvR.setText("Received: " +String.valueOf(PieChartActivity.listofApps.get(mCurrentPage).getRecieved()/1024)+ " KB");		
		}
		
		tv.setText("# " + (mCurrentPage+1) + "/"+PieChartActivity.listofApps.size()+ " Swipe Left/Right");	
		iv.setImageDrawable(PieChartActivity.listofApps.get(mCurrentPage).getIcon());
		return v;		
	}
}
