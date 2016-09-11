package com.shuanglong.turingrobot;

public class ListData
{
	public static final int SEND = 1;
	public static final int RECEIVE = 2;
	
	private String content;
	private int flag;
	
	public ListData(String contentString,int flag)
	{
		// TODO Auto-generated constructor stub
		setContent(contentString);
		setFlag(flag);
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
	
	public String getContent()
	{
		return content;
	}
	
	public void setFlag(int flag)
	{
		this.flag = flag;
	}
	
	public int getFlag()
	{
		return flag;
	}
}
