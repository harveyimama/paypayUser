package com.techland.paypay.user.subscribers;

import java.util.List;

import com.techland.paypay.user.contracts.Subscriber;
import com.techland.paypay.user.contracts.UserEvent;
import com.techland.paypay.user.events.UserAddedEvent;

public class SubscriberFactory {
	private static Emailer emailer;
	private static Logger logger;
	private static Monitor monitor;
	private static UserPersistance  userPersistance ;
	
	private static  List<Subscriber> list ;
	
	private SubscriberFactory(Emailer emailers,Logger loggers,Monitor monitors, UserPersistance  userPersistances)
	{
		emailer = emailers;
		logger = loggers;
		monitor = monitors;
		userPersistance = userPersistances;
	}
	
	public static < R extends UserEvent> List<Subscriber> getInstance(R userEvent)
	{
		if (userEvent.getClass().equals(UserAddedEvent.class)) 
		{
			list.add(emailer);
			list.add(userPersistance);
		}
		
		list.add(logger);
		list.add(monitor);
		
		return list;
	}

}
