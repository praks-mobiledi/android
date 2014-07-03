package com.mobiledi.appstrender.graph;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.mobiledi.appstrender.AllAppsFramgment;
import com.mobiledi.appstrender.AppObject;
import com.mobiledi.appstrender.R;
import com.mobiledi.appstrender.adapters.PieChartPagerAdapter;

public class PieChartActivity extends FragmentActivity {
public static  ArrayList<AppObject> listofApps=new ArrayList<AppObject>();
static int selectedItem;
@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.piechart_activity);
        Intent i= getIntent();
        String setSelected=i.getStringExtra("SELECT");
        Log.d("Selected Item ",setSelected);
        for(AppObject app: AllAppsFramgment.responseAppsList){
        	if(app.getSent()>0){
        		Log.d("APP OBJECT ",app.getAppName());           
        		listofApps.add(app);
        		Log.d("Selected Item ",String.valueOf(listofApps.size()));
        		if(app.getAppName().equals(setSelected)){
        			selectedItem=listofApps.size();
        			Log.d("Selected Item Number",String.valueOf(selectedItem));
        			Log.d("Selected Item ",app.getAppName());
        		}
        		}
        }
        //listofApps = AllAppsFramgment.responseAppsList;
/*         for(int i=0;i<6;i++)
         {
        AppObject s=new AppObject();
         s.setAppName("app"+i);
         s.setAppUid(random.nextInt());
         s.setCarrier("airtel");
         s.setCategory("downlaod");
         s.setDeviceId(String.valueOf(random.nextLong()));
         s.setIcon(null);
         s.setPhoneNum(random.nextInt());
         s.setRecieved(random.nextInt(29000)+1200);
         s.setSent(random.nextInt(29000)+1200);
         listofApps.add(s);
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
       
    }

   
}
