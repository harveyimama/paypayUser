package com.techland.paypay.user.subscribers;

import com.techland.paypay.user.contracts.EventSubscriber;
import com.techland.paypay.user.contracts.UserEvent;
import com.techland.paypay.user.events.UserAddedEvent;
import com.techland.paypay.user.helper.Email;
import com.techland.paypay.user.persistence.EventFailure;
import com.techland.paypay.user.persistence.EventFailureRepository;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public final class Emailer implements EventSubscriber {

	private EventFailure failure;
	private EventFailureRepository failureRepo;
	private JavaMailSender javaMailSender;

	public Emailer(EventFailure failure, EventFailureRepository failureRepo, JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
		this.failureRepo = failureRepo;
		this.failure = failure;
	}

	@Override
	public boolean isState() {
		return false;
	}

	@Override
	public <T extends UserEvent> void process(T userEvent) {

		if (userEvent instanceof UserAddedEvent) {
			sendVericiationEmail((UserAddedEvent) userEvent);
		}

	}

	private void sendVericiationEmail(UserAddedEvent userAdded) {

		System.out.print("Sending veirifcation Email");

		try {

			MimeMessage msg = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);

			helper.setTo(userAdded.getEmail());
			helper.setSubject(Email.WELCOME_SUBJECT);
			helper.setFrom(Email.WELCOME_SENDER);
			helper.setText(Email.WELCOME_EMAIL);
			// helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

			javaMailSender.send(msg);

		} catch (Exception e) {
			handleError(userAdded, failure, failureRepo);
		}

	}

	@Override
	public <T extends UserEvent> void handleError(T userEvent, EventFailure failure,
			EventFailureRepository failureRepo) {
		failure.setEvent(userEvent);
		failure.setSubscriber(this.getClass().getSimpleName());
		failureRepo.save(failure);
	}

}
