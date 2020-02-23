package com.techland.paypay.user.util;

import com.techland.paypay.user.config.Settings;


public class LogFeed {
	
	private  String domain;
	private String info;
	private String returnString;
	private String[] otherInfo;
	private Class<?> clazz;
	
	
	public LogFeed() {
	
		}

	public LogFeed getInstance(final String info,Class<?> clazz, String ...otherInfo)
	{
		this.domain = Settings.DOMAIN;
		this.info = info;
		this.otherInfo =otherInfo;
		this.clazz = clazz;
		
		return this;
	}

	@Override
	public String toString()
	{
		returnString = domain.concat("|").concat(clazz.getSimpleName()).concat("|");
		
		for(String info : this.otherInfo)
		{
			returnString.concat(info).concat("|");
		}
		returnString.concat(" =======> ").concat(info);
		 
		return returnString;
	}
	
	public void process()
	{
		System.out.println(this.toString());
	}

}
