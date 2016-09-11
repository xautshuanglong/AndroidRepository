package com.shuanglong.turingrobot;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TextAdapter extends BaseAdapter
{
	private List<ListData> mListDatas;
	private Context mContext;
	private RelativeLayout layout;
	
	public TextAdapter(List<ListData> lists, Context context)
	{
		// TODO Auto-generated constructor stub
		this.mListDatas = lists;
		this.mContext = context;
	}
	
	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return mListDatas.size();
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return mListDatas.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(mContext);
		if (mListDatas.get(position).getFlag() == ListData.RECEIVE)
		{
			layout = (RelativeLayout)inflater.inflate(R.layout.leftitem, null);
		}
		else
		{
			layout = (RelativeLayout)inflater.inflate(R.layout.rightitem, null);
		}
		TextView textView = (TextView)layout.findViewById(R.id.tv);
		textView.setText(mListDatas.get(position).getContent());
		
		return layout;
	}

}
