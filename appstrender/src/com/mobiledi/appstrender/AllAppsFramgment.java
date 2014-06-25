package com.mobiledi.appstrender;

import java.util.ArrayList;

import android.app.Activity;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.mobiledi.appstrender.adapters.CustomAdapter;
import com.mobiledi.appstrender.graph.ShowPieChart;

public class AllAppsFramgment extends Fragment {
	  @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
		 
		  View rootView = inflater.inflate(R.layout.fragment_all_apps, container, false);       
		 // setHasOptionsMenu(true);
	        return rootView;
	    }

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		 ListView mainLV=(ListView)getView().findViewById(R.id.mainLv);
		 //setHasOptionsMenu(true); 
	        try {
	    			final ArrayList<AppObject> returnedList= new PInfo(getActivity()).getInstalledComponentList(0);
	    			CustomAdapter adapter= new CustomAdapter(getActivity(),returnedList,R.layout.single_row);
	    		    mainLV.setAdapter(adapter); 
	    		    //registerForContextMenu(mainLV);
	    		    mainLV.setOnItemClickListener(new OnItemClickListener() {

	    				@Override
	    				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
	    						long arg3) {
	    					// TODO Auto-generated method stub
	    					//Toast.makeText(getActivity(), returnedList.get(arg2).getAppName() + String.valueOf(returnedList.get(arg2).getSent()), Toast.LENGTH_LONG).show();
	    				//Intent i= new Intent(getActivity(),ShowBarChart.class);
	    				//startActivity(i);
	    					
	    	/////////////				
	    			/*		ShowBarChart showGraph=new ShowBarChart(getActivity(), returnedList);
	    					//ArrayList<AppObject> toShow= new ArrayList<AppObject>();
	    					//toShow.add(returnedList.get(arg2));
	    					//ShowBarChart showGraph=new ShowBarChart(getActivity(), toShow);
	    					showGraph.openChart();*/
	    					
	    	/////////	
	    					
	    					ShowPieChart showPie= new ShowPieChart(getActivity(), returnedList.get(arg2));
	    					showPie.openChart();
	    					
	    					
	    					//////////////s
	    					
	    					
	    				
	    				}
	    			});

	    		    
	    		    
	    		    
	    		    
	}catch (NameNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	    

	}




}
