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
import android.graphics.Paint.Align;
import android.util.DisplayMetrics;
import android.util.TypedValue;
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

	public GraphicalView openChart(int chartType,float max,boolean pan) {
		DisplayMetrics metrics = basecon.getResources().getDisplayMetrics();
		float val = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, metrics);
		float val2 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, metrics);
		float barsize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 45, metrics);
		// Creating an XYSeries for Income
		// CategorySeries incomeSeries = new CategorySeries("Income");
		XYSeries sentSeries = new XYSeries("Sent in  %");
		// Creating an XYSeries for Income
		XYSeries recievedSeries = new XYSeries("Recieved in %");
		// Adding data to Income and Expense Series
		for (int i = 0; i < count.length; i++) {
			if(sent[i]==0 && recieved[i]==0){}
			else{	
			sentSeries.add(i, sent[i]);
			recievedSeries.add(i, recieved[i]);}
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
		sentRenderer.setPointStrokeWidth(1);
		//10
		sentRenderer.setLineWidth(2);
		sentRenderer.setPointStyle(PointStyle.DIAMOND);
		sentRenderer.setDisplayChartValues(true);
		sentRenderer.setChartValuesSpacing((float) 2);
		sentRenderer.setDisplayBoundingPoints(true);
		sentRenderer.setHighlighted(true);
		//sentRenderer.

		// Creating XYSeriesRenderer to customize expenseSeries
		XYSeriesRenderer recievedRenderer = new XYSeriesRenderer();
		recievedRenderer.setColor(Color.parseColor("#ee5a56"));
		recievedRenderer.setFillPoints(true);
		recievedRenderer.setPointStyle(PointStyle.SQUARE);
		recievedRenderer.setPointStrokeWidth(1);
		recievedRenderer.setDisplayChartValues(true);
		recievedRenderer.setChartValuesSpacing((float)2);
		recievedRenderer.setLineWidth(2);
		recievedRenderer.setDisplayBoundingPoints(true);
		//Creating a XYMultipleSeriesRenderer to customize the whole chart
		XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
		multiRenderer.setBackgroundColor(Color.parseColor("#fcf8e3"));
		multiRenderer.setAxesColor(Color.RED);
		 multiRenderer.setYLabelsAlign(Align.LEFT);
		multiRenderer.setApplyBackgroundColor(true);
		
		multiRenderer.setChartTitle("All time usage");
		multiRenderer.setYTitle("Data Usage in %");
		multiRenderer.setXLabelsAngle(30);
		//multiRenderer.setXLabelsColor(Color.parseColor("#0d90d1"));
		multiRenderer.setXLabelsColor(Color.BLACK);
	//multiRenderer.setYLabelsColor(0, Color.parseColor("#ee5a56"));
		multiRenderer.setYLabelsColor(0,Color.BLACK);
		multiRenderer.setLabelsTextSize(val);
		multiRenderer.setShowGrid(true);
		multiRenderer.setZoomEnabled(pan,pan);
		multiRenderer.setPanEnabled(true, pan);
		multiRenderer.setPointSize(3);
		//multiRenderer.setMarginsColor(Color.TRANSPARENT);
		multiRenderer.setBarWidth(barsize);
		multiRenderer.setInScroll(true);
		multiRenderer.setXAxisMin(-0.70);
		multiRenderer.setXAxisMax(max);	
		System.out.println((sent[0]+recieved[0]));
		multiRenderer.setYAxisMax(95);
		multiRenderer.setYAxisMin(0.0);
		multiRenderer.setXLabelsAlign(Align.LEFT);
		multiRenderer.setLegendTextSize(val);
		multiRenderer.setChartTitleTextSize(val2);
		multiRenderer.setBarSpacing(0.5f);
		multiRenderer.setMarginsColor(Color.WHITE);
		multiRenderer.setMargins(new int[] {1,1,50,1});
		for (int i = 0; i < count.length; i++) {
			multiRenderer.addXTextLabel(i, appName[i]); 
		}
		multiRenderer.addSeriesRenderer(sentRenderer);
		multiRenderer.addSeriesRenderer(recievedRenderer);
		multiRenderer.setXLabels(0);
		if(chartType==1){
			return ChartFactory.getBarChartView(basecon, dataset, multiRenderer,
					Type.STACKED);
		}
		else 
			return ChartFactory.getLineChartView(basecon, dataset, multiRenderer);				
		
	}
	

}