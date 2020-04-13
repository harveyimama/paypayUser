package com.techland.paypay.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.techland.paypay.TechLandScheduler;
<<<<<<< HEAD
import com.techland.paypay.contracts.EventSubscriber;
import com.techland.paypay.contracts.StateSubscriber;
import com.techland.paypay.user.factories.SubscriberFactory;
=======
import com.techland.paypay.annotations.SubscriberFactory;
import com.techland.paypay.contracts.EventSubscriber;
import com.techland.paypay.contracts.StateSubscriber;
>>>>>>> 7e68b3d61c30fea8f28c44eda299cf0934a677e1
import com.techland.paypay.user.helper.Settings;
import com.techland.paypay.user.impl.UserState;

public class UserScheduler {
	

	@Autowired
	private SubscriberFactory subscriberFactory;
	@Autowired
	TechLandScheduler techLandSchedular;
	

	
	@Scheduled(fixedRate = Settings.SELF_HEAL_RUN_TIME)
	public void eventSelfHeal() {

		List<EventSubscriber> subscribers = subscriberFactory.getAllEventSubscribers();
		
		techLandSchedular.eventSelfHeal(subscribers);

	
	}

	@Scheduled(fixedRate = Settings.SELF_HEAL_RUN_TIME)
	public void stateSelfHeal() {

		List<StateSubscriber> subscribers = subscriberFactory.getAllStateSubscribers();
		techLandSchedular.stateSelfHeal(subscribers,new UserState());
		
	}

}
