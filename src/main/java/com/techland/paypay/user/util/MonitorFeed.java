package com.techland.paypay.user.util;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.techland.paypay.user.config.Settings;
import com.techland.paypay.user.contracts.UserEvent;
import com.techland.paypay.user.impl.UserPayLoad;

@Component
public class MonitorFeed<T extends UserEvent> {
	
	private  String domain;
	private Timestamp timeStart;
	private Timestamp timeDone;
	private String subscriberName;
	private String	eventName;
	
	public MonitorFeed() {
			
	}
	
	public  MonitorFeed<T> getInstance( final UserPayLoad<T> payload,final String subscriberName)
	{
		this.domain = Settings.DOMAIN;
		this.timeStart = payload.getTimestamp();
		this.timeDone = new Timestamp(System.currentTimeMillis());
		this.subscriberName = subscriberName;
		this.eventName = payload.getUserEvent().getClass().getSimpleName();
		
		return this;
	}

	@Override
	public String toString() {
		return "{\"domain\":\"" + domain + "\", timeStart\":\"" + timeStart + "\", timeDone\":\"" + timeDone
				+ "\", subscriberName\":\"" + subscriberName + "\", eventName\":\"" + eventName + "}";
	}

	public void process()
	{
		//TODO use thread to send out 
		System.out.println(this.toString());
	}
	
	

}
