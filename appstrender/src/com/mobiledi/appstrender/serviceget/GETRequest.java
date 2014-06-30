package com.mobiledi.appstrender.serviceget;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.codehaus.jackson.map.ObjectMapper;
import android.os.AsyncTask;
import android.util.Log;
import com.mobiledi.appstrender.AppObject;

//do this wherever you are wanting to POST
public class GETRequest {
	public ArrayList<AppObject> returnObject;
	private String tosendJSON;
	private String url;
	private String MODE; // 1=post,2=get,3=put,4=delete;

	public GETRequest(String _url, String _tosendJSON, String _MODE)
			throws InterruptedException, ExecutionException {
		// TODO Auto-generated constructor stub
		this.url = _url;
		this.tosendJSON = _tosendJSON;
		this.MODE = _MODE;

		final MakeRequest request = new MakeRequest();
		request.execute(url, tosendJSON, MODE);
		returnObject = request.get();
		Log.d(_url, tosendJSON);
	}
}

class MakeRequest extends AsyncTask<String, Void, ArrayList<AppObject>> {
	HttpURLConnection urlConnection = null;
	ArrayList<AppObject> returnObject;

	@Override
	protected ArrayList<AppObject> doInBackground(String... url) {

		if (url[2] == "GETTING") { // GET OPERATION
			try {
				URL urlToRequest = new URL(url[0]);
				urlConnection = (HttpURLConnection) urlToRequest
						.openConnection();
				urlConnection.setRequestMethod("GET");
				urlConnection.setReadTimeout(5 * 1000);
				urlConnection.connect();

				// Get the server response

				BufferedReader reader = new BufferedReader(
						new InputStreamReader(urlConnection.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line = null;

				// Read Server Response
				while ((line = reader.readLine()) != null) {
					// Append server response in string
					sb.append(line + "");
				}

				// Append Server Response To Content String
				String Content = sb.toString();
				ObjectMapper mapper = new ObjectMapper();
				ArrayList<AppObject> myObjects = mapper.readValue(
						Content,
						mapper.getTypeFactory().constructCollectionType(
								List.class, AppObject.class));

				return myObjects;

			} catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				if (urlConnection != null) 
					urlConnection.disconnect();
				
			}

		}
		return null;

		// return null;
	}

	@Override
	protected void onPostExecute(ArrayList<AppObject> result) {
		if (urlConnection != null) {
			urlConnection.disconnect();
		}
	}
}