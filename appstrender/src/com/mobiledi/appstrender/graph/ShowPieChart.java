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
	int dataUsage[] = { 0, 0 };
	String AppName;
	Context context;
	
	public ShowPieChart(Context context, AppObject s) {
		this.context = context;
		this.dataUsage[0] = (int) s.getSent();
		this.dataUsage[1] = (int) s.getRecieved();
		this.AppName = s.getAppName();

	}

	//public void openChart() {
	public GraphicalView openChart() {
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		float val = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, metrics);
		float val2 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18, metrics);
		//float val3 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 25, metrics);
		CategorySeries series = new CategorySeries("pie"); 
		series.add("Sent", dataUsage[0]);
		series.add("Recieved", dataUsage[1]);
		//int[] colors = new int[] { Color.parseColor("#E6E6DC"), Color.parseColor("#00628B") };
		int[] colors = new int[] { Color.parseColor("#ee5f5b"), Color.parseColor("#29a1cb")};
		// set style for series
		DefaultRenderer renderer = new DefaultRenderer();
		for (int color : colors) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(color);
			r.setDisplayBoundingPoints(true);
			r.setDisplayChartValuesDistance(5);
			r.setDisplayChartValues(true);
			r.setChartValuesTextSize(val);
			renderer.addSeriesRenderer(r);
		}
		
		renderer.setBackgroundColor(Color.parseColor("#fcf8e3"));
		renderer.setShowAxes(true);
		renderer.isInScroll();
		//renderer.setZoomButtonsVisible(true); // set zoom button in Graph
		renderer.setApplyBackgroundColor(true);
		//renderer.setBackgroundColor(Color.BLACK); // set background color
		int x=dataUsage[0] + dataUsage[1];
		int check=x/1048576;
		if(check>0)
		renderer.setChartTitle("Total Data Usage by " + AppName + ": "
				+ (check + "MB"));
		else {
			renderer.setChartTitle("Total Data Usage by " + AppName + ": "
					+ (x/1024 + "KB"));
		}	
		renderer.setLabelsColor(Color.BLACK);
		renderer.setChartTitleTextSize(val2);
		renderer.setShowLabels(true);
		renderer.setLabelsTextSize(val);
		renderer.setLegendTextSize(val);
		renderer.setDisplayValues(true);
		renderer.setPanEnabled(false);

	/*	Intent intent = ChartFactory.getPieChartIntent(context, series,
				renderer, AppName);
		context.startActivity(intent);*/
		return ChartFactory.getPieChartView(context, series, renderer);
	}
}