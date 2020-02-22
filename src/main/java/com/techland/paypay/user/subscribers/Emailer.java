package com.techland.paypay.user.subscribers;

import com.techland.paypay.user.contracts.EventSubscriber;
import com.techland.paypay.user.contracts.UserEvent;
import com.techland.paypay.user.events.UserAddedEvent;
import com.techland.paypay.user.persistence.EventFailure;
import com.techland.paypay.user.persistence.EventFailureRepository;
import com.techland.paypay.user.responses.ServiceResponse;

public final class Emailer implements EventSubscriber {

	private ServiceResponse resp;
	private EventFailure failure;
	private EventFailureRepository failureRepo;

	Emailer(ServiceResponse resp) {
		this.resp = resp;
	}

	@Override
	public boolean isState() {
		return false;
	}

	@Override
	public <T extends UserEvent> void process(T userEvent) {

		if (userEvent instanceof UserAddedEvent) {
			sendVericiationEmail();
			if (!resp.isSuccess())
				handleError(userEvent, failure, failureRepo);
		}

	}

	private ServiceResponse sendVericiationEmail() {
		System.out.print("Sending veirifcation Email");
		return resp;
	}

	@Override
	public <T extends UserEvent> void handleError(T userEvent, EventFailure failure, EventFailureRepository failureRepo) {
		failure.setEvent(userEvent);
		failure.setSubscriber(this.getClass().getSimpleName());
		failureRepo.save(failure);
	}

}
