package com.techland.paypay.user.util;

import com.techland.paypay.user.config.Settings;


public class LogFeed {
	
	private  String domain;
	private String info;
	private String subscriberName;
	private String	eventName;
	private String entityId;
	private String id;
	
	
	
	public LogFeed(final String info, final String subscriberName,final  String eventName, final String entityId, final String id) {
	
		this.domain = Settings.DOMAIN;
		this.info = info;
		this.subscriberName = subscriberName;
		this.eventName = eventName;
		this.entityId = entityId;
		this.id = id;
	}



	@Override
	public String toString()
	{
		return domain.concat("|").concat(eventName).concat("|").concat(subscriberName).concat("|").concat(entityId).concat("|").concat(id).concat(" =======> ").concat(info);
	}

}
