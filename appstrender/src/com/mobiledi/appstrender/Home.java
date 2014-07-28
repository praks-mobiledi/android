package com.mobiledi.appstrender;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.mobiledi.appstrender.adapters.TabsPagerAdapter;
import com.mobiledi.appstrender.servicepush.UploadService;

public class Home extends FragmentActivity implements ActionBar.TabListener {
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	public static String SERVER_IP="192.168.1.3:8080";
	public static String SERVER_URL_ADD="http://"+ SERVER_IP +"/appstrender_service/appstrender/appdata/";

	private String[] tabs = { "All Apps", "Downloaded", "System" };
	protected Object s;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		UpdateTabs();
		// get action bar   
        ActionBar actionBar = getActionBar();
 
        // Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);
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
			//Toast.makeText(Home.this, "Connected to Local Service", Toast.LENGTH_SHORT).show();
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
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Intent i = new Intent(Home.this, UploadService.class);
		startService(i);
		
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
		case(R.id.exit):
		{
		finish();	
		break;	
		}
		case(R.id.about):
		final Dialog dialog=new Dialog(Home.this);	
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
