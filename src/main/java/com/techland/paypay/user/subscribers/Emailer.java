package com.techland.paypay.user.subscribers;

import com.techland.paypay.contracts.EventSubscriber;
import com.techland.paypay.contracts.PayPayEvent;
import com.techland.paypay.contracts.TechLandSubscriber;
import com.techland.paypay.persistence.EventFailure;
import com.techland.paypay.persistence.EventFailureRepository;
import com.techland.paypay.user.commands.StatusChangeCommand;
import com.techland.paypay.user.events.UserAddedEvent;
import com.techland.paypay.user.helper.Email;
import com.techland.paypay.user.helper.Settings;
import com.techland.paypay.user.helper.Status;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
@Component
@TechLandSubscriber(events = {"UserAddedEvent"},isstate = false)   
public final class Emailer implements EventSubscriber {

	@Autowired
	private EventFailure failure;
	@Autowired
	private EventFailureRepository failureRepo;
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private StatusChangeCommand statusCommand;


	@Override
	public boolean isState() {
		return false;
	}

	/*@Override
	public boolean process(String userEventString) {
		ObjectMapper mapper = new ObjectMapper();	
		boolean ret = false;
		try {
			JsonNode actualObj = mapper.readTree(userEventString);
			  
			 
			if (actualObj.get("class").asText().equals("UserAddedEvent"))
			{
				 ret  =sendVericiationEmail(actualObj.get("email").asText(),actualObj.get("id").asText(),
						userEventString,actualObj.get("userType").asText(),true);
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		
		}
		
	}*/
	
	@Override
	public <T extends PayPayEvent> boolean process(final T userEvent) {
		boolean ret = false;
		try {
		if (userEvent instanceof UserAddedEvent) {
			sendVericiationEmail(((UserAddedEvent) userEvent).getEmail(),userEvent.getId(),
					userEvent.toString(),((UserAddedEvent) userEvent).getUserType(),false,userEvent.getObiquitusName());
		}
		return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		
		}

	}
	
	@Override
	public <T extends PayPayEvent> boolean reProcess(final T userEvent) {
		boolean ret = false;
		try {
		if (userEvent instanceof UserAddedEvent) {
			sendVericiationEmail(((UserAddedEvent) userEvent).getEmail(),userEvent.getId(),
					userEvent.toString(),((UserAddedEvent) userEvent).getUserType(),true,userEvent.getObiquitusName());
		}
		return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		
		}

	}

	private boolean sendVericiationEmail(final String email ,final String id,final String userEventString,String uType,boolean isReprocess,String name) {

		System.out.print("Sending veirifcation Email");

		try {

			MimeMessage msg = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);

			helper.setTo(email);
			helper.setSubject(Email.WELCOME_SUBJECT);
			helper.setFrom(Email.WELCOME_SENDER);
			helper.setText(Email.WELCOME_EMAIL);
			// helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

			//send email
			javaMailSender.send(msg);
			
			//send message to subscribers that email was sent
			//Processor userType = UserFactory.getInstance(UserTypes.valueOf(uType));
			statusCommand.setId(id);
			statusCommand.setStatus(Status.VERIFICATIONEMAILSENT);
			//userType.updateAccountStatus(statusCommand);
			return true;

		} catch (Exception e) {
			//e.printStackTrace();
			if(!isReprocess)
			handleError(userEventString, failure, failureRepo,name);
			return false;
		}

	}

	@Override
	public  void handleError(final String userEvent, final EventFailure failure,
			final EventFailureRepository failureRepo,String name) {
		failure.setFailureEvent(userEvent);
		failure.setEventSubscriber(this.getClass().getSimpleName());
		failure.setEventFailureId(Settings.aggregateTag());
		failure.setFailureName(name);
		failureRepo.save(failure);
	}

	

	

	
}
