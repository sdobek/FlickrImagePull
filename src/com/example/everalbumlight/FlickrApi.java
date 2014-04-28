package com.example.everalbumlight;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.builder.api.TwitterApi;
import org.scribe.builder.api.TwitterApi.SSL;
import org.scribe.model.Token;

public class FlickrApi extends DefaultApi10a
{
	  private static final String AUTHORIZE_URL = "https://www.flickr.com/services/oauth/authorize?oauth_token=%s";
	  private static final String REQUEST_TOKEN_RESOURCE = "www.flickr.com/services/oauth/request_token";
	  private static final String ACCESS_TOKEN_RESOURCE = "www.flickr.com/services/oauth/access_token";
	  
	  @Override
	  public String getAccessTokenEndpoint()
	  {
	    return "https://" + ACCESS_TOKEN_RESOURCE;
	  }

	  @Override
	  public String getRequestTokenEndpoint()
	  {
	    return "https://" + REQUEST_TOKEN_RESOURCE;
	  }

	  @Override
	  public String getAuthorizationUrl(Token requestToken)
	  {
	    return String.format(AUTHORIZE_URL, requestToken.getToken());
	  }

	  public static class SSL extends TwitterApi
	  {
	    @Override
	    public String getAccessTokenEndpoint()
	    {
	      return "https://" + ACCESS_TOKEN_RESOURCE;
	    }

	    @Override
	    public String getRequestTokenEndpoint()
	    {
	      return "https://" + REQUEST_TOKEN_RESOURCE;
	    }
	  }

	  /**
	   * Just an alias to the default (SSL) authorization endpoint.
	   *
	   * Need to include this for symmetry with 'Authenticate' only.
	   */
	  public static class Authorize extends SSL{}
	}