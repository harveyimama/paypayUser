package com.techland.paypay.user.util;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.techland.paypay.user.config.Settings;
import com.techland.paypay.user.impl.UserPayLoad;

@Component
public class MonitorFeed {
	
	private  String domain;
	private Timestamp timeStart;
	private Timestamp timeDone;
	private String subscriberName;
	private String	eventName;
	
	public MonitorFeed() {
			
	}
	
	public  MonitorFeed getInstance( final UserPayLoad payload,final String subscriberName)
	{
		this.domain = Settings.DOMAIN;
		this.timeStart = payload.getUserEvent().getTimestamp();
		this.timeDone =  new Timestamp(System.currentTimeMillis());
		this.subscriberName = subscriberName;
		this.eventName = payload.getUserEvent().getClass().getSimpleName();
		
		return this;
	}

	
	public void process()
	{
		//TODO use thread to send out 
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		return "{\"class\":\"MonitorFeed\",\"domain\":\"" + domain + "\", \"timeStart\":\"" + timeStart
				+ "\", \"timeDone\":\"" + timeDone + "\", \"subscriberName\":\"" + subscriberName
				+ "\", \"eventName\":\"" + eventName + "\"}";
	}
	
	

}
