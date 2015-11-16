package com.uclbrt;

import java.io.IOException;

import ytx.org.apache.http.HttpEntity;
import ytx.org.apache.http.HttpResponse;
import ytx.org.apache.http.client.ClientProtocolException;
import ytx.org.apache.http.client.HttpClient;
import ytx.org.apache.http.client.methods.HttpGet;
import ytx.org.apache.http.impl.client.DefaultHttpClient;


public class TestPro {

	public static void main(String[] args) {

		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet("http://localhost/");
		HttpResponse response;
		try {
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}
		
	}

}
