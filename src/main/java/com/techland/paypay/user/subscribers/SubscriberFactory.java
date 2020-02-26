package com.techland.paypay.user.subscribers;

import java.util.ArrayList;
import java.util.List;

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

	private  List<Subscriber> list = new ArrayList<Subscriber>();
	private  List<EventSubscriber> AllEventlist = new ArrayList<EventSubscriber>();
	private  List<StateSubscriber> AllStatelist = new ArrayList<StateSubscriber>();

	public SubscriberFactory(Emailer emailer, UserPersistance userPersistance) {
		this.emailer = emailer;
		this.userPersistance = userPersistance;

	}

	public  <R extends UserEvent> List<Subscriber> getInstance(R userEvent) {
		if (userEvent instanceof UserAddedEvent) {
			list.add(emailer);
			list.add(userPersistance);
		}

		if (userEvent instanceof UserStatusChangedEvent) {
			list.add(userPersistance);
		}

		return list;
	}

	public  List<EventSubscriber> getAllEventSubscribers() {

		if (AllEventlist.isEmpty()) {

			AllEventlist.add(emailer);

		}
		return AllEventlist;
	}

	public  List<StateSubscriber> getAllStateSubscribers() {

		if (AllStatelist.isEmpty()) {

			AllStatelist.add(userPersistance);

		}
		return AllStatelist;
	}

}
