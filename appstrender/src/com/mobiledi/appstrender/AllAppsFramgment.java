package com.mobiledi.appstrender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.http.Header;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;

import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.mobiledi.appstrender.adapters.CustomAdapter;
import com.mobiledi.appstrender.datausagetabs.BarGraphCalled;
import com.mobiledi.appstrender.graph.PieChartActivity;
import com.mobiledi.appstrender.networkutil.NetworkUtil;
import com.mobiledi.appstrender.serviceget.GetResponseArray;

public class AllAppsFramgment extends Fragment {
	ImageButton allGraph;
//	static Boolean result=false;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_all_apps, container,
				false);
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		ListView mainLV = (ListView) getView().findViewById(R.id.mainLv);
		allGraph = (ImageButton) getView().findViewById(R.id.imageButton1);
		// setHasOptionsMenu(true);
		try {
			final ArrayList<AppObject> returnedList = new PInfo(getActivity())
					.getInstalledComponentList(0);
			Collections.sort(returnedList, new Comparator<AppObject>(){
			    public int compare(AppObject s1, AppObject s2) {
			        return s1.getAppName().compareToIgnoreCase(s2.getAppName());
			    }
			});
			CustomAdapter adapter = new CustomAdapter(getActivity(),
					returnedList, R.layout.single_row);
			mainLV.setAdapter(adapter);
			mainLV.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {

					if (returnedList.get(arg2).getSent() != 0) {
						Intent s = new Intent(getActivity(),
								PieChartActivity.class);
						// returnedList.get(arg2).getAppName();
						s.putExtra("SELECT", returnedList.get(arg2)
								.getAppName());
						//s.put
						startActivity(s);
					} else {
						Toast.makeText(
								getActivity(),
								returnedList.get(arg2).getAppName()
										+ "  has not used data so far",
								Toast.LENGTH_LONG).show();

					}
				}
			});

		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
allGraph.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				
				if(NetworkUtil.getConnectivityStatus(getActivity())==0) {
					Toast.makeText(getActivity(),
							"Unable to connect to the internet, please ensure your data or wifi is turned on",
							Toast.LENGTH_LONG).show();
					return;
				}
				else{
					
	/*				//PingIP tester= new PingIP(getActivity());
					try {
					BarGraphCalled bgc= new	BarGraphCalled(getActivity());
					bgc.execute();
						} catch(Exception e){
								e.printStackTrace();
		
						}*/	
					BarGraphCalled bgc= new BarGraphCalled(getActivity());
					bgc.getBarGraphData();
					
					
				/*	GetResponseArray gRA=new GetResponseArray(getActivity().getApplicationContext());
					gRA.getArray("http://192.168.1.3:8080/appstrender_service/appstrender/appdata/readById/359609052536419/2014-07-27 00:00:00.986");
			*/
/*					String url="http://192.168.1.3:8080/appstrender_service/appstrender/appdata/readById/359609052536419/2014-07-27 00:00:00.986";
				AsyncHttpClient client = new AsyncHttpClient();
				client.get(getActivity(), url, new JsonHttpResponseHandler(){
					
					@Override
					public void onFailure(int statusCode, Header[] headers,
							String responseString, Throwable throwable) {
						System.out.println(responseString);
					}

					@Override
					public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
						System.out.println(response.toString());
						ObjectMapper mapper = new ObjectMapper();
						try {
							 ArrayList<AppObject> myObjects = mapper.readValue(response.toString(),mapper.getTypeFactory().constructCollectionType(
											List.class, AppObject.class));
							for(AppObject x: myObjects){
								Log.d("App in GET ARRAY "+x.getAppName(),String.valueOf("Recieved"+x.getRecieved()));
							}
						} catch (JsonParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (JsonMappingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							//return myObjects;
					
					}	
					
				});*/
				
				
				
				
				}
	return;
	}
		});
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getActivity().getMenuInflater();
		inflater.inflate(R.menu.home, menu);
	}
}
