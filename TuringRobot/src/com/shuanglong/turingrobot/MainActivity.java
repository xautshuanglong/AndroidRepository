package com.shuanglong.turingrobot;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.shuanglong.logger.Logger;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements HttpGetDataListener, OnClickListener
{
	private HttpData mHttpData;
	private List<ListData> mListDatas;
	private TextAdapter mTextAdapter;
	
	private ListView lv;
	private EditText sendText;
	private Button sendBtn;
	
	private String infoString;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		
		Logger.init().hideThreadInfo();
	}
	
	private void initView()
	{
		lv = (ListView)findViewById(R.id.lv);
		sendText = (EditText)findViewById(R.id.sendText);
		sendBtn = (Button)findViewById(R.id.sendBtn);
		sendBtn.setOnClickListener(this);
		
		mListDatas = new ArrayList<ListData>();
		mTextAdapter = new TextAdapter(mListDatas, this);
		lv.setAdapter(mTextAdapter);
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
		try
		{
			JSONObject jData = new JSONObject(data);
			int code = jData.getInt("code");
			String text = jData.getString("text");
			
			Logger.d("code --> %d\ntext --> %s", code, text);
			
			ListData listData = new ListData(text, ListData.RECEIVE);
			mListDatas.add(listData);
			mTextAdapter.notifyDataSetChanged();
		}
		catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.e(e.toString());
		}
	}

	@SuppressLint("ShowToast")
	@Override
	public void onClick(View v)
	{
		Logger.d("OnClicked ..........");
		// TODO Auto-generated method stub
		infoString = sendText.getText().toString();
		if (infoString.isEmpty())
		{
			Toast.makeText(this, "发送内容不能为空", Toast.LENGTH_SHORT);
			return;
		}
		sendText.setText("");
		
		ListData listData = new ListData(infoString, ListData.SEND);
		mListDatas.add(listData);
		mTextAdapter.notifyDataSetChanged();
		
		mHttpData = new HttpData("http://www.tuling123.com/openapi/api?key=ce7e39014a844b56a5cc01033fdfd81f&info="+infoString, this);
		mHttpData.execute();
	}
}
