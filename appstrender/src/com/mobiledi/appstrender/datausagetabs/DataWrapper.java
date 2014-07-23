package com.mobiledi.appstrender.datausagetabs;

import java.io.Serializable;
import java.util.ArrayList;

import com.mobiledi.appstrender.AppObject;

public class DataWrapper implements Serializable {
	private static final long serialVersionUID = 5382846133043252674L;
	private ArrayList<ArrayList<AppObject>> result;

	public ArrayList<ArrayList<AppObject>> getResult() {
		return result;
	}

	public DataWrapper(ArrayList<ArrayList<AppObject>> result) {
		this.result = result;
	}
	
}
