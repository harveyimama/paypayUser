package com.techland.paypay.user.config;

import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class PayPayLogger  implements Runnable {
	
	final Logger logger = LoggerFactory.getLogger(PayPayLogger.class);
		

	private  StringBuilder logs = new StringBuilder();
	
	public  void doLog(final String serviceName,final String... items)
	{
		ExecutorService  service = PayPayThread.startThreader();
		logs.append("\r\n".concat("====Logs Start here=====").concat("\r\n").concat(serviceName).concat("\r\n")); 
		
		if (items.length == 0)
			logs.append("====Logs end here=======");
		else
		for(String  item  :  items )
		{
			if (item !=null)
			logs.append(item.concat("\r\n"));
		}
		logs.append("====Logs end here=======");
			
		service.execute(this);
		
	}
	
	@Override
	public void run() {
		logger.info( this.logs.toString());
	}

	
	

}
