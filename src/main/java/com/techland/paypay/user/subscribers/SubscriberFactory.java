package com.techland.paypay.user.subscribers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.techland.paypay.user.contracts.EventSubscriber;
import com.techland.paypay.user.contracts.StateSubscriber;
import com.techland.paypay.user.contracts.Subscriber;
import com.techland.paypay.user.contracts.UserEvent;
import com.techland.paypay.user.events.UserAddedEvent;
import com.techland.paypay.user.events.UserStatusChangedEvent;
@Component
public class SubscriberFactory {
	private  Emailer emailer;
	private  UserPersistance userPersistance;


	private  ConcurrentHashMap<String,List<? extends Subscriber>> finalList = new ConcurrentHashMap<>();
	private  List<EventSubscriber> eventlist = new ArrayList<EventSubscriber>();
	private  List<StateSubscriber> statelist = new ArrayList<StateSubscriber>();

	public SubscriberFactory(Emailer emailer, UserPersistance userPersistance) {
		this.emailer = emailer;
		this.userPersistance = userPersistance;

	}

	public  <R extends UserEvent>  ConcurrentHashMap<String,List<? extends Subscriber>> getInstance(R userEvent) {
		if (userEvent instanceof UserAddedEvent) {
			eventlist.add(emailer);
			statelist.add(userPersistance);			
		}

		if (userEvent instanceof UserStatusChangedEvent) {
			statelist.add(userPersistance);
		}
		
		return	getFinalList();
			
	}

	public  List<EventSubscriber> getAllEventSubscribers() {

		if (eventlist.isEmpty()) {

			eventlist.add(emailer);

		}
		return eventlist;
	}

	public  List<StateSubscriber> getAllStateSubscribers() {

		if (statelist.isEmpty()) {

			statelist.add(userPersistance);

		}
		return statelist;
	}
	
	
	private ConcurrentHashMap<String,List<? extends Subscriber>> getFinalList ()
	{
		if (eventlist != null)
		finalList.put("events",eventlist);
		
		if (statelist != null)
		finalList.put("states",statelist);
		
		return finalList;
	}

}
