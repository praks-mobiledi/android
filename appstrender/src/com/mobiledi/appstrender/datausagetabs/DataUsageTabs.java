package com.mobiledi.appstrender.datausagetabs;

import com.mobiledi.appstrender.Home;
import com.mobiledi.appstrender.R;
import com.mobiledi.appstrender.adapters.DataUsageTabsPagerAdapter;
import com.mobiledi.appstrender.adapters.TabsPagerAdapter;

import android.app.ActionBar;
import android.app.Dialog;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DataUsageTabs extends FragmentActivity implements
		ActionBar.TabListener {

	private ViewPager viewPager;
	private DataUsageTabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	private String[] tabs = { "Top 5", "Top 10", "All" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_datausage);	
		viewPager = (ViewPager) findViewById(R.id.pager2);
		actionBar = getActionBar();
		// Enabling Up / Back navigation
	    actionBar.setDisplayHomeAsUpEnabled(true);
		mAdapter = new DataUsageTabsPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(mAdapter);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));}

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
		viewPager.setCurrentItem(arg0.getPosition());
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater mi = getMenuInflater();
	    mi.inflate(R.menu.home , menu);
	    return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		//return super.onOptionsItemSelected(item);
		
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
		final Dialog dialog=new Dialog(DataUsageTabs.this);	
		dialog.setContentView(R.layout.about_dialog);
		dialog.setCanceledOnTouchOutside(true);
		dialog.setTitle("About us");
		dialog.show();
		Button bok=(Button) dialog.findViewById(R.id.dialogok);
		bok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});

		break;
		}
		return true;
		
	}
	

}
