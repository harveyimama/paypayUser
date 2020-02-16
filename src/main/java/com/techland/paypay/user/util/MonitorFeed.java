package com.techland.paypay.user.util;

import java.sql.Timestamp;

import com.techland.paypay.user.config.Settings;


public class MonitorFeed {
	
	private  String domain;
	private Timestamp timeStart;
	private Timestamp timeDone;
	private String subscriberName;
	private String	eventName;
	
	public MonitorFeed( final Timestamp timeStart,final  Timestamp timeDone, final String subscriberName, 
			final String eventName) {
			this.domain = Settings.DOMAIN;
		this.timeStart = timeStart;
		this.timeDone = timeDone;
		this.subscriberName = subscriberName;
		this.eventName = eventName;
	}

	@Override
	public String toString() {
		return "{\"domain\":\"" + domain + "\", timeStart\":\"" + timeStart + "\", timeDone\":\"" + timeDone
				+ "\", subscriberName\":\"" + subscriberName + "\", eventName\":\"" + eventName + "}";
	}

	
	
	

}
