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
		CategorySeries series = new CategorySeries("pie"); // adding series to
															// charts. //collect
															// 3 value in array.
															// therefore add
															// three series.
		series.add("Sent", dataUsage[0]);
		series.add("Recieved", dataUsage[1]);
		// series.add("Email",Performance[2]);
		// add three colors for three series respectively
		int[] colors = new int[] { Color.BLUE, Color.RED };
		// set style for series
		DefaultRenderer renderer = new DefaultRenderer();
		for (int color : colors) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(color);
			r.setDisplayBoundingPoints(true);
			r.setDisplayChartValuesDistance(5);
			r.setDisplayChartValues(true);
			r.setChartValuesTextSize(15);
			renderer.addSeriesRenderer(r);
		}
		renderer.isInScroll();
		renderer.setZoomButtonsVisible(true); // set zoom button in Graph
		renderer.setApplyBackgroundColor(true);
		renderer.setBackgroundColor(Color.BLACK); // set background color
		int x=dataUsage[0] + dataUsage[1];
		int check=x/1048576;
		if(check>0)
		renderer.setChartTitle("Total Data Usage by " + AppName + ": "
				+ (check + "MB"));
		else {
			renderer.setChartTitle("Total Data Usage by " + AppName + ": "
					+ (x/1024 + "KB"));
		}		
		renderer.setChartTitleTextSize((float) 25);
		renderer.setShowLabels(true);
		renderer.setLabelsTextSize(20);
		renderer.setLegendTextSize(25);
		int[] margins={0,-90,0,0};
		renderer.setMargins(margins);
		renderer.setDisplayValues(true);

	/*	Intent intent = ChartFactory.getPieChartIntent(context, series,
				renderer, AppName);
		context.startActivity(intent);*/
		return ChartFactory.getPieChartView(context, series, renderer);
	}
}