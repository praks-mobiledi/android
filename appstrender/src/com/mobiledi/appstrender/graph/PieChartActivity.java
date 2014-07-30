package com.mobiledi.appstrender.graph;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.mobiledi.appstrender.AllAppsFramgment;
import com.mobiledi.appstrender.AppObject;
import com.mobiledi.appstrender.PInfo;
import com.mobiledi.appstrender.R;
import com.mobiledi.appstrender.adapters.PieChartPagerAdapter;
import com.mobiledi.appstrender.datausagetabs.BarGraphCalled;
import com.mobiledi.appstrender.datausagetabs.DataUsageTabs;

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
     // get action bar   
        ActionBar actionBar = this.getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        
      try {
		responseAppsList= new PInfo(
					PieChartActivity.this).getInstalledComponentList(0);
	    for(AppObject app: responseAppsList){
        	if(app.getSent()>0){           
        		listofApps.add(app);
        		if(app.getAppName().equals(setSelected)){
        			selectedItem=listofApps.size();
    
        		}
        		}
        }
	    
	} catch (NameNotFoundException e) {
		e.printStackTrace();
	}
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
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater mi = getMenuInflater();
    mi.inflate(R.menu.home , menu);
    return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
	switch(item.getItemId()){
	
	case(android.R.id.home):{
		onBackPressed();
       // return true;
	}
	
	break;
	case(R.id.exit):
	{
	finish();
	break;
	}
	case(R.id.about):
	final Dialog dialog=new Dialog(PieChartActivity.this);	
	dialog.setContentView(R.layout.about_dialog);
	dialog.setCanceledOnTouchOutside(true);
	dialog.setTitle("About us");
	dialog.show();
	Button bok=(Button) dialog.findViewById(R.id.dialogok);
	bok.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			dialog.dismiss();
		}
	});
	break;
	}
	return true;
	
}
}
