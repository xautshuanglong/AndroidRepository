package com.shuanglong.turingrobot;

import com.shuanglong.logger.Logger;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements HttpGetDataListener
{
	private HttpData mHttpData;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Logger.init().hideThreadInfo();
		Logger.d("MainActivity is created...");
		mHttpData = new HttpData("http://www.tuling123.com/openapi/api?key=ce7e39014a844b56a5cc01033fdfd81f&info=ÄãºÃ", this);
		mHttpData.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void getDataUrl(String data)
	{
		// TODO Auto-generated method stub
		System.out.println(data);
	}
}
