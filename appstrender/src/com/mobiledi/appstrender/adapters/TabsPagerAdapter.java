package com.mobiledi.appstrender.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.mobiledi.appstrender.AllAppsFramgment;
import com.mobiledi.appstrender.PersonalFragments;
import com.mobiledi.appstrender.SocialFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Top Rated fragment activity
			Log.d("Called ALL Fragment GetIndex", String.valueOf(index));
			return new AllAppsFramgment();
		case 1:
			// Games fragment activity
			Log.d("Called Pers Fragment GetIndex", String.valueOf(index));
			return new PersonalFragments();
		case 2:
			// Movies fragment activity
			Log.d("Called Soc Fragment GetIndex", String.valueOf(index));
			return new SocialFragment();
			/*
			 * case 3: return games();
			 */}
		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}

}