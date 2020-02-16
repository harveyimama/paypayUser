package com.techland.paypay.user.util;

import com.techland.paypay.user.helper.ExternalURL;


public class Logger  {

	
	public  static void process(LogFeed logFeed) {

		System.out.println("send " +logFeed.toString()+" to: "+ExternalURL.LOGGER.getUrl());
	}

	
	
}
