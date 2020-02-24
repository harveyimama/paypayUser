package com.techland.paypay.user;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.techland.paypay.user.contracts.EventSubscriber;
import com.techland.paypay.user.contracts.StateSubscriber;
import com.techland.paypay.user.events.EventFactory;
import com.techland.paypay.user.impl.UserEntity;
import com.techland.paypay.user.persistence.EventFailure;
import com.techland.paypay.user.persistence.EventFailureRepository;
import com.techland.paypay.user.persistence.StateFailure;
import com.techland.paypay.user.persistence.StateFailureRepository;
import com.techland.paypay.user.subscribers.SubscriberFactory;

@Component
public class PayPayUserScheduler {

	private EventFailureRepository eventRepo;
	private StateFailureRepository stateRepo;
	private UserEntity user;

	PayPayUserScheduler(final EventFailureRepository eventRepo, final StateFailureRepository stateRepo,
			final UserEntity user) {
		this.eventRepo = eventRepo;
		this.stateRepo = stateRepo;
		this.user = user;
	}

	@Scheduled(fixedRate = 1800000)
	public void eventSelfHeal() {

		List<EventSubscriber> subscribers = SubscriberFactory.getAllEventSubscribers();

		subscribers.stream().forEach(subscriber -> {
	
			List<EventFailure> eventFailures = eventRepo.findAllByEventSubscriber(subscriber.getClass().getSimpleName());

			eventFailures.stream().forEach(eventFailure -> {

				subscriber.process(EventFactory.getEvent(eventFailure.getFailureEvent()));

			});

		});

	}

	@Scheduled(fixedRate = 1800000)
	public void stateSelfHeal() {

		List<StateSubscriber> subscribers = SubscriberFactory.getAllStateSubscribers();

		subscribers.stream().forEach(subscriber -> {

			List<StateFailure> stateFailures = stateRepo.findByStateSubscriber(subscriber.getClass().getSimpleName());

			stateFailures.stream().forEach(stateFailure -> {

				subscriber.process(user.getState(stateFailure.getStateUserId()));

			});

		});

	}

}
