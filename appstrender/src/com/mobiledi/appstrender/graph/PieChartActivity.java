package com.mobiledi.appstrender.graph;

import java.util.ArrayList;

import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import com.mobiledi.appstrender.AllAppsFramgment;
import com.mobiledi.appstrender.AppObject;
import com.mobiledi.appstrender.PInfo;
import com.mobiledi.appstrender.R;
import com.mobiledi.appstrender.adapters.PieChartPagerAdapter;
import com.mobiledi.appstrender.datausagetabs.BarGraphCalled;

public class PieChartActivity extends FragmentActivity {
public static  ArrayList<AppObject> listofApps=new ArrayList<AppObject>();
static int selectedItem;
private ArrayList<AppObject> responseAppsList;
@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.piechart_activity);
        Intent i= getIntent();
        String setSelected=i.getStringExtra("SELECT");
        Log.d("Selected Item ",setSelected); 
      //  BarGraphCalled bcg= new BarGraphCalled(PieChartActivity.this);
      //  if(AllAppsFramgment.isSetResList){
    //  bcg.callGraph();
      try {
		responseAppsList= new PInfo(
					PieChartActivity.this).getInstalledComponentList(0);
	    for(AppObject app: responseAppsList){
        	if(app.getSent()>0){
        		//Log.d("APP OBJECT ",app.getAppName());           
        		listofApps.add(app);
        		//Log.d("Selected Item ",String.valueOf(listofApps.size()));
        		if(app.getAppName().equals(setSelected)){
        			selectedItem=listofApps.size();
        			//Log.d("Selected Item Number",String.valueOf(selectedItem));
        			//Log.d("Selected Item ",app.getAppName());
        		}
        		}
        }
		
		
		
		
	} catch (NameNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    //  if(bcg.getResponseStatus(1)){
/*        for(AppObject app: bcg.getResponseAppsList(1)){
        	if(app.getSent()>0){
        		//Log.d("APP OBJECT ",app.getAppName());           
        		listofApps.add(app);
        		//Log.d("Selected Item ",String.valueOf(listofApps.size()));
        		if(app.getAppName().equals(setSelected)){
        			selectedItem=listofApps.size();
        			//Log.d("Selected Item Number",String.valueOf(selectedItem));
        			//Log.d("Selected Item ",app.getAppName());
        		}
        		}
        }}
      else{
    	  
    	  
    	  Toast.makeText(getApplicationContext(), "No Data To Display", Toast.LENGTH_LONG).show();
      }*/
        /** Getting a reference to the ViewPager defined the layout file */        
        ViewPager pager = (ViewPager) findViewById(R.id.pager5);
        
        /** Getting fragment manager */
        FragmentManager fm = getSupportFragmentManager();
        
        /** Instantiating FragmentPagerAdapter */
        PieChartPagerAdapter pagerAdapter = new PieChartPagerAdapter(fm);
    
        /** Setting the pagerAdapter to the pager object */
        pager.setAdapter(pagerAdapter);
       
        /** Set Page*/
        pager.setCurrentItem(selectedItem-1);
        /*}
        else{
        	Toast.makeText(this, "No Data Available", Toast.LENGTH_LONG).show();
			this.finish();
        }*/
    }

   
}
