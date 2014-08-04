package com.mobiledi.appstrender.graph;

import java.util.ArrayList;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

import com.mobiledi.appstrender.AppObject;

//import android.R;

public class ShowBarChart {
	private ArrayList<AppObject> returnedList = new ArrayList<AppObject>();
	private Context basecon;
	int sent[];
	int recieved[];
	int[] count;
	String[] appName;
	int totalS = 0, totalR = 0;

	public ShowBarChart(Context context, ArrayList<AppObject> filledList) {
		// TODO Auto-generated constructor stub
		this.basecon = context;	
		for (AppObject x : filledList) {
			if (x.getSent() != 0) {
				this.returnedList.add(x);
				this.totalS = (int) (this.totalS + x.getSent());
				this.totalR = (int) (this.totalR + x.getRecieved());
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
			sent[i] = (int) ((returnedList.get(i).getSent() * 100) / totalS);
			recieved[i] = (int) ((returnedList.get(i).getRecieved() * 100) / totalR);
			count[i] = i;
		}
		// openChart();
	}

	public GraphicalView openChart() {
		// Creating an XYSeries for Income
		// CategorySeries incomeSeries = new CategorySeries("Income");
		XYSeries sentSeries = new XYSeries("Sent in  %");
		// Creating an XYSeries for Income
		XYSeries recievedSeries = new XYSeries("Recieved in %");
		// Adding data to Income and Expense Series
		for (int i = 0; i < count.length; i++) {
			sentSeries.add(i, sent[i]);
			recievedSeries.add(i, recieved[i]);
		}

		// Creating a dataset to hold each series
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		// Adding Income Series to the dataset
		dataset.addSeries(sentSeries);
		// Adding Expense Series to dataset
		dataset.addSeries(recievedSeries);

		// Creating XYSeriesRenderer to customize incomeSeries
		XYSeriesRenderer sentRenderer = new XYSeriesRenderer();
		sentRenderer.setColor(Color.parseColor("#0d90d1"));
		sentRenderer.setFillPoints(true);
		sentRenderer.setLineWidth(1);
		sentRenderer.setPointStyle(PointStyle.DIAMOND);
		sentRenderer.setDisplayChartValues(true);
		sentRenderer.setChartValuesSpacing((float) 0);
		//sentRenderer.setLineWidth((float)0);

		// Creating XYSeriesRenderer to customize expenseSeries
		XYSeriesRenderer recievedRenderer = new XYSeriesRenderer();
		recievedRenderer.setColor(Color.parseColor("#ee5a56"));
		recievedRenderer.setFillPoints(true);
		recievedRenderer.setPointStyle(PointStyle.SQUARE);
		recievedRenderer.setDisplayChartValues(true);
		recievedRenderer.setChartValuesSpacing((float) 0);
		recievedRenderer.setLineWidth(1);
		//recievedRenderer.setLineWidth((float) 0);

		//Creating a XYMultipleSeriesRenderer to customize the whole chart
		XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
		multiRenderer.setBackgroundColor(Color.parseColor("#fcf8e3"));
		multiRenderer.setApplyBackgroundColor(true);
		multiRenderer.setLabelsTextSize(15);
		multiRenderer.setChartTitle("Data Usage");
		multiRenderer.setXTitle("All Application");
		multiRenderer.setYTitle("Data Usage in %");
		multiRenderer.setLabelsColor(Color.parseColor("#e3e3e4"));
		multiRenderer.setPanEnabled(true, false);
	//  multiRenderer.setXLabelsPadding(2);
	//  multiRenderer.setXLabelsAngle(45);
		multiRenderer.setShowGrid(true);
	//	multiRenderer.setBarWidth(100);
	//  multiRenderer.setZoomRate(1.1f);
		multiRenderer.setZoomEnabled(false);
	multiRenderer.setPointSize(1);
		multiRenderer.setBarWidth(50f);
	//  multiRenderer.setInScroll(true);
		multiRenderer.setXAxisMin(0);
		multiRenderer.setXAxisMax(appName.length);
		multiRenderer.setYAxisMin(0);
		multiRenderer.setYAxisMax(100);
		//multiRenderer.setScale(1);
		multiRenderer.setBarSpacing(1);  
		for (int i = 0; i < count.length; i++) {
			multiRenderer.addXTextLabel(i, appName[i]);
		}
		multiRenderer.addSeriesRenderer(sentRenderer);
		multiRenderer.addSeriesRenderer(recievedRenderer);
		return ChartFactory.getBarChartView(basecon, dataset, multiRenderer,
				Type.DEFAULT);
	}

}