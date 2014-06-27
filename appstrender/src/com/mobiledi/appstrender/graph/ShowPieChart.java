package com.mobiledi.appstrender.graph;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import com.mobiledi.appstrender.AppObject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

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

	public void openChart() {
		// this is my data of performance; data is collected in array.

		// dataUsage = {42, 15, 19}; // [0] for Call, [1] for Meeting, [2] for
		// Email
		CategorySeries series = new CategorySeries("pie"); // adding series to
															// charts. //collect
															// 3 value in array.
															// therefore add
															// three series.
		series.add("Sent", dataUsage[0]);
		series.add("Recieved", dataUsage[1]);
		// series.add("Email",Performance[2]);
		// add three colors for three series respectively
		int[] colors = new int[] { Color.BLUE, Color.GRAY };
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
		renderer.setChartTitle("Total Data Usage by :" + AppName + " "
				+ (dataUsage[0] + dataUsage[1]) / 1024 + "MB");
		renderer.setChartTitleTextSize((float) 25);
		renderer.setShowLabels(true);
		renderer.setLabelsTextSize(20);
		renderer.setLegendTextSize(25);
		renderer.setDisplayValues(true);

		Intent intent = ChartFactory.getPieChartIntent(context, series,
				renderer, AppName);
		context.startActivity(intent);
	}
}