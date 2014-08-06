package com.mobiledi.appstrender.datausagetabs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.util.Log;

import com.mobiledi.appstrender.AppObject;

public class GetTopApps {
	ArrayList<AppObject> response;
	
	int top;
	
	GetTopApps(ArrayList<AppObject> response, int top) {
		this.response = response;
		this.top = top;
	}
	
	public ArrayList<AppObject> getTop(){
		List<AppObject> sublist = new ArrayList<AppObject>();
		
		Collections.sort(response, new Comparator<AppObject>(){
		    public int compare(AppObject s1, AppObject s2) {
		        return -(Long.valueOf(s1.getSent()+s1.getRecieved()).compareTo(s2.getSent()+s2.getRecieved())); 
		    }
		});
		if(response.size()>this.top){
			sublist = new ArrayList<AppObject>(response.subList(0, this.top));
			for(AppObject x:sublist){
				Log.d(x.getAppName(), String.valueOf(x.getSent()+x.getRecieved()));
			}
			return (ArrayList<AppObject>) sublist;
		}
		else
			return response;
		}
		
		
		
		
}
