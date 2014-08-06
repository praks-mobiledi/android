package com.mobiledi.appstrender.graph;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import com.mobiledi.appstrender.AppObject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.TypedValue;
///returns a pie chart
public class ShowPieChart {
	long dataUsage[] = { 0, 0 };
	long totalUsage=0;
	String AppName;
	Context context;
	
	public ShowPieChart(Context context, AppObject s, long usage) {
		this.context = context;
		this.dataUsage[0] =  s.getSent();
		this.dataUsage[1] =  s.getRecieved();
		this.AppName = s.getAppName();
		this.totalUsage=usage;
	}

	//public void openChart() {
	public GraphicalView openChart() {
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		float val = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, metrics);
		float val2 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18, metrics);
		//float val3 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 25, metrics);
		CategorySeries series = new CategorySeries("pie"); 
		/////////
		long sent=dataUsage[0]/1048576;
		long recieved=dataUsage[1]/1048576;
		if(recieved > 0){
			if(sent>0){
				series.add("Sent " +  sent + "MB", dataUsage[0]);
			}
			else{
				series.add("Sent " +  dataUsage[0]/1024 + "KB", dataUsage[0]);
			}
			series.add("Rec. "  + recieved + "MB", dataUsage[1]);
		
		}
		else{
			series.add("Sent " +  dataUsage[0]/1024 + "KB", dataUsage[0]);
			series.add("Rec. "  + dataUsage[1]/1024 + "KB", dataUsage[1]);
		}
	
		///////////
		
		
		
		/*series.add("Sent " +  (dataUsage[0]/1024) + "KB", dataUsage[0]);
		series.add("Rec. "  + (dataUsage[1]/1024) + "KB", dataUsage[1]);*/
		//int[] colors = new int[] { Color.parseColor("#E6E6DC"), Color.parseColor("#00628B") };
		int[] colors = new int[] { Color.parseColor("#ee5f5b"), Color.parseColor("#29a1cb")};
		// set style for series
		DefaultRenderer renderer = new DefaultRenderer();
		for (int color : colors) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			//r.setChartValuesSpacing(5);
			r.setColor(color);
			//r.setDisplayBoundingPoints(true);
			//
			r.setDisplayChartValuesDistance(3);
			r.setDisplayBoundingPoints(false);
		//
			r.setDisplayChartValues(true);
			r.setChartValuesTextSize(val);
			r.setDisplayChartValuesDistance(4);
			renderer.addSeriesRenderer(r);
		}
		
		//renderer.setBackgroundColor(Color.parseColor("#fcf8e3"));
		renderer.setBackgroundColor(Color.TRANSPARENT);
		renderer.setShowAxes(true);
		renderer.isInScroll();
		//renderer.setZoomButtonsVisible(true); // set zoom button in Graph
		renderer.setApplyBackgroundColor(true);
		//renderer.setBackgroundColor(Color.BLACK); // set background color
		long x= (dataUsage[0] + dataUsage[1]);
		double check=x/1048576.00;
		if(check>1)
		renderer.setChartTitle(this.AppName+" data usage : "
				+ ( Math.round(check * 100.0) / 100.0) + "MB"+"\n Total usage since last reboot: " + (totalUsage/1048576) + "MB");
		else {
			renderer.setChartTitle(this.AppName+" data usage : "
					+ ((Math.round((x/1024.00) * 100.0) / 100.0) + "KB"+"\n Total data usage since reboot: " + (totalUsage/1048576) + "MB"));
		}	
		renderer.setLabelsColor(Color.BLACK);
		renderer.setChartTitleTextSize(val2);
		renderer.setShowLabels(true);
		renderer.setStartAngle(47);
		renderer.setLabelsTextSize(val);
		//renderer.setLegendTextSize(val);
		//renderer.setDisplayValues(true);
		renderer.setShowCustomTextGrid(true);
		renderer.setPanEnabled(false);
		renderer.setShowLegend(false);
		//renderer.
	/*	Intent intent = ChartFactory.getPieChartIntent(context, series,
				renderer, AppName);
		context.startActivity(intent);*/
		return ChartFactory.getPieChartView(context, series, renderer);
	}
}