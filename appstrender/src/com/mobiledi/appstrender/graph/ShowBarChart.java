package com.mobiledi.appstrender.graph;

import java.util.ArrayList;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import com.mobiledi.appstrender.AppObject;

//import android.R;

public class ShowBarChart {
	private ArrayList<AppObject> returnedList = new ArrayList<AppObject>();
	private Context basecon;
	int sent[];
	int recieved[];
	int[] count;
	String[] appName;
	int totalS=0,totalR=0;
	public ShowBarChart(Context context, ArrayList<AppObject> filledList) {
		// TODO Auto-generated constructor stub
		this.basecon = context;
		for (AppObject x : filledList) {
			if (x.getSent() != 0) {
				this.returnedList.add(x);
				this.totalS= (int) (this.totalS+x.getSent());
				this.totalR=(int) (this.totalR+x.getRecieved());
			}
		}
		setVariables();
	}

	public void setVariables() {
		int size = returnedList.size();
		appName = new String[size];
		sent = new int[size];
		recieved = new int[size];
		count = new int[size];
		
		for (int i = 0; i < returnedList.size(); i++) {

			appName[i] = returnedList.get(i).getAppName();
			sent[i] = (int) ((returnedList.get(i).getSent()*100)/totalS);
			recieved[i] = (int) ((returnedList.get(i).getRecieved()*100)/totalR);
			count[i] = i;
		}
		// openChart();
	}

	// Getting reference to the button btn_chart
	// Button btnChart = (Button) findViewById(R.id.btn_chart);

	// Defining click event listener for the button btn_chart

	// Setting event click listener for the button btn_chart of the MainActivity
	// layout
	// btnChart.setOnClickListener(clickListener);

	public GraphicalView openChart() {
		// Creating an XYSeries for Income
		// CategorySeries incomeSeries = new CategorySeries("Income");
		XYSeries incomeSeries = new XYSeries("Sent in  %");
		// Creating an XYSeries for Income
		XYSeries expenseSeries = new XYSeries("Recieved in %");
		// Adding data to Income and Expense Series
		for (int i = 0; i < count.length; i++) {
			incomeSeries.add(i, sent[i]);
			expenseSeries.add(i, recieved[i]);
		}

		// Creating a dataset to hold each series
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		// Adding Income Series to the dataset
		dataset.addSeries(incomeSeries);
		// Adding Expense Series to dataset
		dataset.addSeries(expenseSeries);

		// Creating XYSeriesRenderer to customize incomeSeries
		XYSeriesRenderer sentRenderer = new XYSeriesRenderer();
		sentRenderer.setColor(Color.rgb(0, 174, 255));
		sentRenderer.setFillPoints(true);
		sentRenderer.setLineWidth(2);
		sentRenderer.setDisplayChartValues(true);

		// Creating XYSeriesRenderer to customize expenseSeries
		XYSeriesRenderer recievedRenderer = new XYSeriesRenderer();
		recievedRenderer.setColor(Color.rgb(5, 245, 61));
		recievedRenderer.setFillPoints(true);
		recievedRenderer.setLineWidth(2);
		recievedRenderer.setDisplayChartValues(true);

		// Creating a XYMultipleSeriesRenderer to customize the whole chart
		XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
		multiRenderer.setXLabels(0);
		multiRenderer.setChartTitle("Data Usage");
		multiRenderer.setXTitle("All Application");
		multiRenderer.setYTitle("Data Usage in %");
		multiRenderer.setZoomButtonsVisible(true);
		multiRenderer.setPanEnabled(true, false);
		multiRenderer.setXLabelsPadding(7);
		
		for (int i = 0; i < count.length; i++) {
			multiRenderer.addXTextLabel(i, appName[i]);
		}

		// Adding incomeRenderer and expenseRenderer to multipleRenderer
		// Note: The order of adding dataseries to dataset and renderers to
		// multipleRenderer
		// should be same
		multiRenderer.addSeriesRenderer(sentRenderer);
		multiRenderer.addSeriesRenderer(recievedRenderer);
		// ChartFactory.getBarChartView(context, dataset, multiRenderer, type)
		// Creating an intent to plot bar chart using dataset and
		// multipleRenderer
		// ///Intent intent = ChartFactory.getBarChartIntent(basecon, dataset,
		// multiRenderer, Type.DEFAULT);

		// /// Start Activity
		// ///basecon.startActivity(intent);
		//multiRenderer.set`
		//ChartFactory.
		return ChartFactory.getBarChartView(basecon, dataset, multiRenderer,
				Type.DEFAULT);
	}

}