package com.mobiledi.appstrender;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
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
import android.widget.Toast;

import com.mobiledi.appstrender.adapters.TabsPagerAdapter;
import com.mobiledi.appstrender.service.RestfulService;

public class Home extends FragmentActivity implements
ActionBar.TabListener{
    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    // Tab titles
    private String[] tabs = { "All Apps", "Downloaded", "System" };
	protected Object s;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent i= new Intent(Home.this,RestfulService.class);
        startService(i);
       // Initilization
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
       }

    @Override
    protected void onResume() {
      super.onResume();
      Intent intent= new Intent(this, RestfulService.class);
      bindService(intent, mConnection,
          Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
      super.onPause();
      unbindService(mConnection);
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className, 
            IBinder binder) {
          RestfulService.MyBinder b = (RestfulService.MyBinder) binder;
          s = b.getService();
          Toast.makeText(Home.this, "Connected", Toast.LENGTH_SHORT)
              .show();
        }
        public void onServiceDisconnected(ComponentName className) {
          s = null;
        }
      };
   /* @Override
    protected void onResume() {
      super.onResume();
      Intent intent= new Intent(Home.this, UploadToRESTService.class);
      bindService(intent, mConnection,
          Context.BIND_AUTO_CREATE);
    }
    private ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className, 
            IBinder binder) {
          UploadToRESTService.MyBinder b = (UploadToRESTService.MyBinder) binder;
          s = b.getService();
          Toast.makeText(Home.this, "Connected", Toast.LENGTH_SHORT)
              .show();
        }
        public void onServiceDisconnected(ComponentName className) {
            s = null;
            Toast.makeText(Home.this, "Disconnected", Toast.LENGTH_SHORT)
            .show();
   
          }
        };
        @Override
        protected void onPause() {
          super.onPause();
          unbindService(mConnection);
        }*/

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		  
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		Log.d("Tab", arg0.toString());
		
		
	}
}
/*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        ListView mainLV=(ListView)findViewById(R.id.mainLv);
        
        try {
			ArrayList<AppObject> returnedList= new PInfo(getApplicationContext()).getInstalledComponentList();
			CustomAdapter adapter= new CustomAdapter(getApplicationContext(),returnedList,R.layout.single_row);
		    mainLV.setAdapter(adapter); 
			/*for(int i=0;i<returnedList.size();i++){
			
			Log.d("Apps Installed",returnedList.get(i).getAppName());
		}
        
        
        } catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        Toast.makeText(getApplicationContext(), "Welcome to Apps Trender", Toast.LENGTH_LONG).show();
        
    }*/