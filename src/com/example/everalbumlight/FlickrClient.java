package com.example.everalbumlight;

import org.scribe.builder.api.Api;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;

public class FlickrClient extends OAuthBaseClient {
	public static final Class<? extends Api> REST_API_CLASS = FlickrApi.class;
	public static final String REST_URL = "https://api.flickr.com/services/rest"; 
	public static final String REST_CONSUMER_KEY = "3d7c410ce675b230c6ebd19f3d28b0ef"; // Change this
	public static final String REST_CONSUMER_SECRET = "d6281ffb3e8664e2";
	public static final String REST_CALLBACK_URL = "https://www.flickr.com/auth-72157644230008386"; 

	
	
	public FlickrClient(Context context) {
		super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY,
				REST_CONSUMER_SECRET, REST_CALLBACK_URL);
	}
}
