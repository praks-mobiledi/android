package com.mobiledi.appstrender;

import java.util.ArrayList;

import com.mobiledi.appstrender.adapters.CustomAdapter;

import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class SocialFragment extends Fragment {
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_social, container, false);
         
        return rootView;
    }
    
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		 ListView mainLV=(ListView)getView().findViewById(R.id.mainLvS);
	        
	        try {
	    			ArrayList<AppObject> returnedList= new PInfo(getActivity()).getInstalledComponentList(1);
	    			CustomAdapter adapter= new CustomAdapter(getActivity(),returnedList,R.layout.single_row);
	    		    mainLV.setAdapter(adapter); 
	}catch (NameNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}
}
 
