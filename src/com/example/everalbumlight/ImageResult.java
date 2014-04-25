package com.example.everalbumlight;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Borrowed and modified this class from my ImageSearch project
 * @author Owner
 *
 */
public class ImageResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static char SUFFIX = 'n';
	
	String imageUrl;

	public ImageResult (JSONObject json) {
		try {
			String farmId = json.getString("farm");
			String serverId = json.getString("server");
			String id = json.getString("id");
			String secret = json.getString("secret");
			/* 
			 * There is an extra field option for the small size photo url.
			 * However, to give more flexibility, I chose to have a constant to represent the size
			 * that can be changed by the programmer here, and construct the url manually
			 * for the extra control.
			 */
			imageUrl = "http://farm"+farmId+".staticflickr.com/"+serverId+"/"+id+"_"+secret+"_"+SUFFIX+".jpg";
		} catch (JSONException e) {
			imageUrl = null;
		}
	}

	
	public String getImageUrl(){
		return imageUrl;
	}
	public static ArrayList<ImageResult> fromJSONArray(
			JSONArray imageJsonResults) {
		ArrayList<ImageResult> results =  new ArrayList<ImageResult>();
		for (int index = 0; index < imageJsonResults.length(); index++){
			try {
				results.add(new ImageResult(imageJsonResults.getJSONObject(index)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return results;
	}
}
