package com.example.everalbumlight;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.codepath.oauth.OAuthLoginActivity;
import com.gmail.yuyang226.flickr.Flickr;
import com.gmail.yuyang226.flickr.oauth.OAuth;
import com.gmail.yuyang226.flickr.oauth.OAuthToken;

public class LoginActivity extends OAuthLoginActivity<FlickrClient> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	// OAuth authenticated successfully, launch primary authenticated activity
		// i.e Display application "homepage"
		@Override
		public void onLoginSuccess() {
			 Intent i = new Intent(this, MainActivity.class);
			 startActivity(i);
		}

		// OAuth authentication flow failed, handle the error
		// i.e Display an error dialog or toast
		@Override
		public void onLoginFailure(Exception e) {
			e.printStackTrace();
		}

		
		public void loginToRest(View view) {
			//Several attempts at login, both wuth flickrj-android lib and OAuth
			
//			String apiKey = "3d7c410ce675b230c6ebd19f3d28b0ef";
//			String apiSecret = "d6281ffb3e8664e2";
//		    Flickr f = new Flickr(apiKey, apiSecret);
//		    OAuth auth = new OAuth();
//		    auth.setToken(new OAuthToken("access_token", "token_secret"));
			getClient().connect(); //Got me the mini code to input - WHich OAuth should deprecate.  Not sure what's going on.
		}

}
