package com.mobiledi.appstrender.datausagetabs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.mobiledi.appstrender.AppObject;
import com.mobiledi.appstrender.Home;
import com.mobiledi.appstrender.R;

public class BarGraphCalled {
	private Context context;
	final private ArrayList<ArrayList<AppObject>> barGraphData = new ArrayList<ArrayList<AppObject>>();
	private ArrayList<String> dates = new ArrayList<String>();
	private TelephonyManager tm;

	public BarGraphCalled(Context context) {
		this.context = context;
		this.tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String endDate = "2014-01-01 00:00:00";
		dates.add(endDate); // firstDate

		LocalDate dt = LocalDate.now();
		dt = dt.minusWeeks(1);
		endDate = dt.toString() + " " + LocalTime.now();
		dates.add(endDate);

		dt = LocalDate.now();
		dt = dt.minusMonths(1);
		endDate = dt.toString() + " " + LocalTime.now();
		dates.add(endDate);

	}

	public ArrayList<ArrayList<AppObject>> getBarGraphData(){
	//String url="http://192.168.1.3:8080/appstrender_service/appstrender/appdata/readAllById/" +tm.getDeviceId()+"/" + dates.get(0) + "/" + dates.get(1) + "/" +dates.get(2) ;
	String url=Home.SERVER_URL_ADD + "readAllById/"+tm.getDeviceId()+"/" + dates.get(0) + "/" + dates.get(1) + "/" +dates.get(2) ;
		AsyncHttpClient client= new AsyncHttpClient();
	client.get(context, url, new JsonHttpResponseHandler(){
	
		private ProgressDialog dialog;

		@Override
		public void onSuccess(int statusCode, Header[] headers,
				JSONArray response) {
			//ArrayList<ArrayList<AppObject>> finalObject= new 		ArrayList<ArrayList<AppObject>>(); 
			System.out.println("App Detail: "+response.toString());
			//System.out.println("App Detail: " +response.getJSONArray(0).length());
		try {		
		//for(int i=0;i<response.length();i++){
			if(response.getJSONArray(0).length()!=0){
					for(int k=0;k<3;k++){							
				//for(int k=0;k<response.getJSONArray(0).length()+1;k++){							
					
					System.out.println("Also k = "+ response.getJSONArray(0).length() +"  "+ response.getJSONArray(k).toString());
						try {
						ObjectMapper mapper=new ObjectMapper();
						ArrayList<AppObject> myObjects = mapper.readValue(
								response.getJSONArray(k).toString(),
								mapper.getTypeFactory().constructCollectionType(
										List.class, AppObject.class));
						barGraphData.add(myObjects);
						/*for(AppObject y:myObjects){
							System.out.println("App in Object: " + y.getAppName() + y.getSent());	
						}*/					
					} catch (JsonParseException e) {
						e.printStackTrace();
						barGraphData.add(null);
					} catch (JsonMappingException e) {
						
						barGraphData.add(null);
						e.printStackTrace();
					} catch (IOException e) {
						barGraphData.add(null);
						e.printStackTrace();
					}
					
					}	
			//}
			}
			else{
				barGraphData.clear();
				barGraphData.add(null);
				barGraphData.add(null);
				barGraphData.add(null);
				
			}
		}catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(context,  "Unable to calculate network utilization at this time", Toast.LENGTH_SHORT).show();
				return;
			}
		
		 Intent  i = new Intent(context,DataUsageTabs.class); 
		 i.putExtra("result", new 
		 DataWrapper(barGraphData)); 
		 context.startActivity(i); 			
		 
		}

		@Override
		public void onFailure(int statusCode, Header[] headers,
				Throwable throwable, JSONObject errorResponse) {
			//System.out.println("Failed: " + responseString);
			Toast.makeText(context,  "Unable to calculate network utilization at this time", Toast.LENGTH_SHORT).show();
		
		}

		@Override
		public void onCancel() {
			// TODO Auto-generated method stub
			super.onCancel();
			if(dialog!=null || dialog.isShowing()){ dialog.dismiss(); }
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			super.onFinish();
			if(dialog!=null || dialog.isShowing()){ dialog.dismiss(); }
		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
			dialog = new ProgressDialog(context);
			dialog.setMessage("Requesting Data"); dialog.setIcon(R.drawable.loading);
			dialog.setTitle("..."); 
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dialog.show();
		}

		});	
	/*if(barGraphData.size()==0){
		Toast.makeText(context,  "Unable to calculate network utilization at this time", Toast.LENGTH_SHORT).show();
	}*/
	return barGraphData;	
}
}

/*
 * public class BarGraphCalled extends AsyncTask<Void, Integer,
 * ArrayList<ArrayList<AppObject>>> {
 * 
 * private ArrayList<ArrayList<AppObject>> finalList= new
 * ArrayList<ArrayList<AppObject>>();
 * 
 * private TelephonyManager tm; private Context context; private ProgressDialog
 * dialog; ArrayList<String> dates = new ArrayList<String>(); String
 * endDateForAll = "2014-01-01%2000:00:00"; //HttpURLConnection urlConnection =
 * null; public BarGraphCalled(Context context) { super(); this.context =
 * context; this.tm = (TelephonyManager) context
 * .getSystemService(Context.TELEPHONY_SERVICE); }
 * 
 * @Override protected void onPreExecute() { // TODO Auto-generated method stub
 * super.onPreExecute(); dialog = new ProgressDialog(context);
 * dialog.setMessage("Fetching Data ..."); dialog.setIcon(R.drawable.loading);
 * dialog.setTitle("#"); dialog.setIndeterminate(true); dialog.setMax(100);
 * dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
 * //dialog.setProgress(3); dialog.show();
 * 
 * String endDate = "2014-01-01%2000:00:00"; dates.add(endDate); // firstDate
 * 
 * LocalDate dt = LocalDate.now(); dt = dt.minusWeeks(1); endDate =
 * dt.toString() + "%20" + LocalTime.now(); dates.add(endDate);
 * 
 * dt = LocalDate.now(); dt = dt.minusMonths(1); endDate = dt.toString() + "%20"
 * + LocalTime.now(); dates.add(endDate);
 * 
 * 
 * }
 * 
 * @Override protected ArrayList<ArrayList<AppObject>> doInBackground(Void...
 * args) { int total = 1; HttpGet request = new HttpGet();
 * 
 * for(String date:dates){
 * 
 * try { HttpParams httpParameters = new BasicHttpParams();
 * HttpConnectionParams.setConnectionTimeout(httpParameters, 8000);
 * //HttpConnectionParams.setSoTimeout(httpParameters,8000); HttpClient
 * httpclient = new DefaultHttpClient(httpParameters); URI website = new
 * URI(Home.SERVER_URL_ADD + "readById/" + tm.getDeviceId() + "/"+
 * date);//2014-01-01%2000:00:00");
 * 
 * website = new URI(Home.SERVER_URL_ADD + "readAllById/" + tm.getDeviceId() +
 * "/"+
 * dates.get(0)+"/"+dates.get(1)+"/"+dates.get(2));//2014-01-01%2000:00:00");
 * 
 * request.setURI(website); HttpResponse response = httpclient.execute(request);
 * 
 * BufferedReader in = new BufferedReader(new
 * InputStreamReader(response.getEntity().getContent())); String line;
 * StringBuilder sb = new StringBuilder();
 * 
 * while ((line = in.readLine()) != null) { // Append server response in string
 * sb.append(line + ""); total+=line.length(); } // Append Server Response To
 * Content String String Content= sb.toString(); publishProgress(total);
 * ObjectMapper mapper = new ObjectMapper();
 * Log.d("CONTENT VALUE",Content);//.toString() ArrayList<AppObject> myObjects =
 * mapper.readValue( Content, mapper.getTypeFactory().constructCollectionType(
 * List.class, AppObject.class)); finalList.add(myObjects);
 * ArrayList<ArrayList<AppObject>> myObjects = mapper.readValue( Content,
 * mapper.getTypeFactory().constructCollectionType( List.class,
 * DataWrapper.class));
 * 
 * return myObjects;
 * 
 * } catch(ConnectException e){ e.printStackTrace(); fillErrorObject(); } catch
 * (URISyntaxException e1) { // TODO Auto-generated catch block
 * e1.printStackTrace(); fillErrorObject();
 * 
 * } catch (ClientProtocolException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); fillErrorObject(); } catch (IOException e) { // TODO
 * Auto-generated catch block e.printStackTrace(); fillErrorObject(); }
 * catch(Exception e){ e.printStackTrace(); fillErrorObject(); }
 * 
 * } return finalList; //return null; }
 * 
 * private void fillErrorObject(){ ArrayList<AppObject> tempAl= new
 * ArrayList<AppObject>(); AppObject temp= new AppObject();
 * temp.setAppName("ERROR"); temp.setCategory(
 * "Server error: Unable to calculate network utilization at this time");
 * tempAl.add(temp); finalList.add(tempAl); }
 * 
 * 
 * @Override protected void onPostExecute(ArrayList<ArrayList<AppObject>>
 * result) { if(dialog!=null || dialog.isShowing()){ dialog.dismiss(); } Intent
 * i = new Intent(context,DataUsageTabs.class); i.putExtra("result", new
 * DataWrapper(result)); context.startActivity(i); }
 * 
 * 
 * }
 */
