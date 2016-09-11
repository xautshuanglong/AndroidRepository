package com.shuanglong.turingrobot;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpData extends AsyncTask<String, Void, String>
{
	private HttpClient mHttpClient;
	private HttpResponse mHttpResponse;
	private HttpGet mHttpGet;
	private HttpEntity mHttpEntity;
	private InputStream inputStream;
	private HttpGetDataListener mListener;
	private String mUrl;
	
	public HttpData(String url, HttpGetDataListener listener)
	{
		this.mUrl = url;
		this.mListener = listener;
	}
	
	@Override
	protected String doInBackground(String... params)
	{
		try
		{
			mHttpClient = new DefaultHttpClient();
			mHttpGet = new HttpGet(mUrl);
			mHttpResponse = mHttpClient.execute(mHttpGet);
			mHttpEntity = mHttpResponse.getEntity();
			inputStream = mHttpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			StringBuffer sb = new StringBuffer();
			
			while((line = br.readLine()) != null)
			{
				sb.append(line);
			}
			
			return sb.toString();
		}
		catch (ClientProtocolException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	protected void onPostExecute(String result)
	{
		// TODO Auto-generated method stub
		this.mListener.getDataUrl(result);
		super.onPostExecute(result);
	}
}
