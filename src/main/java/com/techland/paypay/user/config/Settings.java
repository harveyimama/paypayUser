package com.techland.paypay.user.config;

import java.time.Duration;

public final class Settings {
	
	final static int NUMBER_OF_THREADS = 100;
	public final static int THREAD_LOCK_TIMEOUT = 8;
	public static boolean MOCK;
	public final static int ASYNC_WAIT_TIME = 2;
	public final static int ASYNC_NUMBER_OF_TRIES= 2;
	public final static String DOMAIN  = "User";
	public final static int CHECKPOINT_LIMIT = 8;
	//public final static Duration CASSANDRA_WAIT_TIME = ;
	public static final String HASH = "SHA-512";
	
	public  static void setTest()
	{
		MOCK = true;
		System.out.println("Set to test mode");
	}

	public  static void setProd()
	{
		MOCK = false;
		System.out.println("Set to live mode");
	}
}
