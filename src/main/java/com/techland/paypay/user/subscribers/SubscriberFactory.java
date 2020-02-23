package com.techland.paypay.user.subscribers;

import java.util.List;

import com.techland.paypay.user.contracts.EventSubscriber;
import com.techland.paypay.user.contracts.StateSubscriber;
import com.techland.paypay.user.contracts.Subscriber;
import com.techland.paypay.user.contracts.UserEvent;
import com.techland.paypay.user.events.UserAddedEvent;
import com.techland.paypay.user.events.UserStatusChangedEvent;

public class SubscriberFactory {
	private static Emailer emailer;
	private static UserPersistance userPersistance;

	private static List<Subscriber> list;
	private static List<EventSubscriber> AllEventlist;
	private static List<StateSubscriber> AllStatelist;

	private SubscriberFactory(Emailer emailers, UserPersistance userPersistances) {
		emailer = emailers;
		userPersistance = userPersistances;
	}

	public static <R extends UserEvent> List<Subscriber> getInstance(R userEvent) {
		if (userEvent instanceof UserAddedEvent) {
			list.add(emailer);
			list.add(userPersistance);
		}
		
		if (userEvent instanceof UserStatusChangedEvent) {
			list.add(userPersistance);
		}

		return list;
	}

	public static List<EventSubscriber> getAllEventSubscribers() {
		
		if (AllEventlist.isEmpty()) {
			
			AllEventlist.add(emailer);
			
		}
		return AllEventlist;
	}
	
	
public static List<StateSubscriber> getAllStateSubscribers() {
		
		if (AllStatelist.isEmpty()) {
			
			AllStatelist.add(userPersistance);
			
		}
		return AllStatelist;
	}

}
