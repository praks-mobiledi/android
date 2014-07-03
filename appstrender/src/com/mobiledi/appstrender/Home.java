package com.mobiledi.appstrender;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.mobiledi.appstrender.adapters.TabsPagerAdapter;
import com.mobiledi.appstrender.servicepush.UploadService;

public class Home extends FragmentActivity implements ActionBar.TabListener {
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	public static String SERVER_URL_ADD="http://192.168.1.3:8080/appstrender_service/appstrender/appdata/";

	private String[] tabs = { "All Apps", "Downloaded", "System" };
	protected Object s;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		Intent i = new Intent(Home.this, UploadService.class);
		startService(i);
		UpdateTabs();
		}

	public void UpdateTabs() {

		
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);

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
	protected void onResume() {
		super.onResume();

		Intent intent = new Intent(this, UploadService.class);
		bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onPause() {
		super.onPause();
		unbindService(mConnection);
	}

	private ServiceConnection mConnection = new ServiceConnection() {

		public void onServiceConnected(ComponentName className, IBinder binder) {
			UploadService.MyBinder b = (UploadService.MyBinder) binder;
			s = b.getService();
			Toast.makeText(Home.this, "Connected", Toast.LENGTH_SHORT).show();
		}

		public void onServiceDisconnected(ComponentName className) {
			s = null;
		}
	};

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {


	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {


	}

	@Override
	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {

		Log.d("Tab", arg0.toString());
		viewPager.setCurrentItem(arg0.getPosition());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

}
