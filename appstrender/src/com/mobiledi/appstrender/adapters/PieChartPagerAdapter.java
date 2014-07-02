package com.mobiledi.appstrender.adapters;

import com.mobiledi.appstrender.graph.PieChartActivity;
import com.mobiledi.appstrender.graph.PieChartFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PieChartPagerAdapter extends FragmentPagerAdapter{
	
	final int PAGE_COUNT = PieChartActivity.listofApps.size();
	/** Constructor of the class */
	public PieChartPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	/** This method will be invoked when a page is requested to create */
	@Override
	public Fragment getItem(int arg0) {
		
		PieChartFragment myFragment = new PieChartFragment();
		Bundle data = new Bundle();
		data.putInt("current_page", arg0);
		myFragment.setArguments(data);
		return myFragment;
	}

	/** Returns the number of pages */
	@Override
	public int getCount() {		
		return PAGE_COUNT;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {		
		//return "Page #" + ( position + 1 );
	return PieChartActivity.listofApps.get(position).getAppName();
	
	}
	
	
	
	
}
