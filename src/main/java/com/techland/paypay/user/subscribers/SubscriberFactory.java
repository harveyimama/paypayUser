package com.techland.paypay.user.subscribers;

import java.util.List;

import com.techland.paypay.user.contracts.Subscriber;
import com.techland.paypay.user.contracts.UserEvent;
import com.techland.paypay.user.events.UserAddedEvent;
import com.techland.paypay.user.util.Logger;

public class SubscriberFactory {
	private static Emailer emailer;
	private static Logger logger;
	private static UserPersistance  userPersistance ;
	
	private static  List<Subscriber> list ;
	
	private SubscriberFactory(Emailer emailers,Logger loggers, UserPersistance  userPersistances)
	{
		emailer = emailers;
		logger = loggers;
		userPersistance = userPersistances;
	}
	
	public static < R extends UserEvent> List<Subscriber> getInstance(R userEvent)
	{
		if (userEvent instanceof UserAddedEvent) 
		{
			list.add(emailer);
			list.add(userPersistance);
		}
		
		list.add(logger);
				
		return list;
	}

}
