package com.techland.paypay.user.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class PayPayThread {
	
	private static ExecutorService executor;
	private PayPayThread()
	{}
	
	public static ExecutorService startThreader()
	{
		if(executor == null)
			 executor = Executors.newFixedThreadPool( Settings.NUMBER_OF_THREADS ); 
		
		return executor; 
	}


}
