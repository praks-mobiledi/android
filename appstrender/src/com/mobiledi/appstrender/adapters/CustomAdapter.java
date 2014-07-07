package com.mobiledi.appstrender.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobiledi.appstrender.AppObject;
import com.mobiledi.appstrender.R;

public class CustomAdapter extends BaseAdapter {
	Context _con;
	ArrayList<AppObject> myList;
	int resId;
	long sent;
	long recieve;

	public CustomAdapter(Context con, ArrayList<AppObject> filledList, int resId) {
		// TODO Auto-generated constructor stub
		this._con = con;
		this.myList = filledList;
		this.resId = resId;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return myList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return myList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View v = arg1;
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) _con
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(resId, null);
		}

		TextView text = (TextView) v.findViewById(R.id.srTv);
		TextView data = (TextView) v.findViewById(R.id.srdata);
		text.setText(myList.get(arg0).getAppName());
		if (myList.get(arg0).getRecieved() != -1
				&& myList.get(arg0).getSent() != -1) {
			
			sent=myList.get(arg0).getSent()/1048576;
			recieve=myList.get(arg0).getRecieved()/1048576;
			if(sent>1 && recieve>1){
			data.setText("Recv/Sent: " + recieve + "/"
					+ sent + " (MB)");}
			else{
				data.setText("Recv/Sent: " + (myList.get(arg0).getRecieved()/1024) + "/"
						+ (myList.get(arg0).getSent()/1024) + " (KB)");
			}

		} else {
			data.setText("Recv/Sent: 0/0(kb)");

		}
		ImageView icon = (ImageView) v.findViewById(R.id.srIv);
		icon.setImageDrawable(myList.get(arg0).getIcon());

		return v;
	}

}
