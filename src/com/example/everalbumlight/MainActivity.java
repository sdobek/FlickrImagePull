package com.example.everalbumlight;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.GridView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

public class MainActivity extends Activity {
	public static final String API_KEY = "3d7c410ce675b230c6ebd19f3d28b0ef";
	public static final String USER_ID = "123900564@N04";

	GridView gvImages;
	PhotoArrayAdapter photoAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		gvImages = (GridView) findViewById(R.id.gvPhotos);
		photoAdapter = new PhotoArrayAdapter(this, new ArrayList<ImageResult>());

		//Found SerialzeObject class online, so I can store the array of Images I already uploaded
		String ser = SerializeObject.ReadSettings(this, "myobject.dat");
		if (ser != null && !ser.equalsIgnoreCase("")) {
			Object obj = SerializeObject.stringToObject(ser);
			if (obj instanceof ArrayList) {
				// Object is cast and added
				photoAdapter.addAll((ArrayList<ImageResult>) obj);
				gvImages.setAdapter(photoAdapter);
			}
		} else {
			/**
			 * So, because it doesn't need authentication,
			 * for the challenge I went with the flickr client photos.getRecent.
			 * There is a version that is getRecentlyUpdated, but it requires me to use OAuth.
			 * In the interest of time, I didn't do it, but that would be the next step I took.
			 * 
			 * That way I would be able to set a min_date field based on the most recent photo
			 * saved, if there is one, and just add and modify the existing array list.
			 * 
			 * For now, it simply pulls 500 photos for the intial run, and saves them ina GridView
			 */
			AsyncHttpClient client = new AsyncHttpClient();
			RequestParams params = new RequestParams();
			params.put("method", "flickr.photos.getRecent");
			params.put("format", "json");
			params.put("per_page", "500");
			//params.put("min_date", "1000");
			params.put("api_key", "3d7c410ce675b230c6ebd19f3d28b0ef");
			client.get("https://www.flickr.com/services/rest/", params,
					/*
					 * Interestingly, depsite being json formatted, 
					 * flickr returns the result as text.
					 * Hence, using TextHttpResponseHandler from Android Asynchronous Http Client (jar/lib)
					 */
					new TextHttpResponseHandler() { 
						@Override
						public void onSuccess(String response) {
							try {
								String jsonString = response.replace(
										"jsonFlickrApi(", "");
								jsonString = jsonString.replace("})", "}");
								JSONObject jo = new JSONObject(jsonString);
								jo = jo.getJSONObject("photos");
								JSONArray ja = jo.getJSONArray("photo");

								ArrayList<ImageResult> images = ImageResult
										.fromJSONArray(ja); //Borrowed this from my ImageSearch project
								photoAdapter.addAll(images);
								// TODO: Learn image caching to give better scrolling
								gvImages.setAdapter(photoAdapter);
								//Writing the serializable data
								String ser = SerializeObject.objectToString(images);
								if (ser != null && !ser.equalsIgnoreCase("")) {
								    SerializeObject.WriteSettings(getBaseContext(), ser, "myobject.dat");
								} else {
								    SerializeObject.WriteSettings(getBaseContext(), "", "myobject.dat");
								}
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}

						public void onFailure(Throwable e, JSONObject error) {
							// Handle the failure and alert the user to retry
							Log.e("ERROR", e.toString());
						}
					});
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
