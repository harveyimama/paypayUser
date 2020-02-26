package com.techland.paypay.user.util;

import org.springframework.stereotype.Component;

import com.techland.paypay.user.config.Settings;

@Component
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
				if(info != null)
				returnString = returnString.concat(info).concat("|");
				else
					returnString = returnString.concat("Null info received").concat("|");
			}
			returnString =	returnString.concat(" =======> ").concat(info);
		
		 
		return returnString;
	}
	
	public void process()
	{
		System.out.println(this.toString());
	}

}
