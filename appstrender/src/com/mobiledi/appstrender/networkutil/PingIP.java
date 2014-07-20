package com.mobiledi.appstrender.networkutil;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.mobiledi.appstrender.R;
import com.mobiledi.appstrender.datausagetabs.DataUsageTabs;

import android.R.drawable;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class PingIP extends AsyncTask<String,Void,Boolean>{
	private java.net.InetAddress[] inet;
	private String ipAddress;
	Context con;
	private ProgressDialog dialog;
	public static boolean toPass;
	public PingIP(Context con) {
		super();
		this.con = con;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();	
		dialog = new ProgressDialog(con);
	    dialog.setMessage("Connecting Please wait...");
	    dialog.setIcon(R.drawable.loading);
	    dialog.setTitle("...");
	    dialog.setIndeterminate(true);
	    dialog.show();    
	    }   
	@Override
	protected Boolean doInBackground(String... args) {		
		try {
			
			 ipAddress=args[0];
			 inet= InetAddress.getAllByName(ipAddress) ;
			 inet[0].isReachable(3000);
			 for(InetAddress net: inet){
				return true;
			 }
	          }
		 catch (UnknownHostException f) {
			 f.printStackTrace();
          }
			catch (IOException f) {
	        	  f.printStackTrace();	
	          }	
		 return false;
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		Toast.makeText(con, "The Result is: " + result, Toast.LENGTH_SHORT).show(); 
		if (dialog.isShowing()) {
	        dialog.dismiss();
	    }
	  if(result.booleanValue())
	  {
		  Intent s = new Intent(con, DataUsageTabs.class);
		  con.startActivity(s);
		  return;
	  }
	  else	  
		 Toast.makeText(con, "Oops! Server Unavailable", Toast.LENGTH_SHORT).show(); 

}
	
}
