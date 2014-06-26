package com.mobiledi.appstrender.datausagetabs;

import com.mobiledi.appstrender.R;
import com.mobiledi.appstrender.adapters.TabsPagerAdapter;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;


public class DataUsageTabs extends FragmentActivity implements ActionBar.TabListener{

	 private ViewPager viewPager;
	    private DataUsageTabsPagerAdapter mAdapter;
	    private ActionBar actionBar;
	    private String[] tabs = { "Total", "Week", "Month" };
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_datausage);
	        viewPager = (ViewPager) findViewById(R.id.pager2);
	        actionBar = getActionBar();
	        mAdapter = new DataUsageTabsPagerAdapter(getSupportFragmentManager());
	 
	        viewPager.setAdapter(mAdapter);	       
	        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);        
	        // Adding Tabs
	        for (String tab_name : tabs) {
	            actionBar.addTab(actionBar.newTab().setText(tab_name)
	                    .setTabListener(this));
	        } 
	       
	        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
	            @Override
	            public void onPageSelected(int position) {
	                actionBar.setSelectedNavigationItem(position);
	            }
	        });  
	        
	        
	        
	        
	        
	        
	    }
	
	
	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {

		
	}

	@Override
	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {

		
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {

		
	}
	
	
	
	
	
	
	
}
