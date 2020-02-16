package com.techland.paypay.user.subscribers;

import com.techland.paypay.user.contracts.Subscriber;
import com.techland.paypay.user.contracts.User;
import com.techland.paypay.user.events.UserAddedEvent;
import com.techland.paypay.user.helper.Constants;
import com.techland.paypay.user.response.ServiceResponse;
import com.techland.paypay.user.services.SendEmailMessenger;
import com.techland.paypay.user.users.UserFactory;
import com.techland.paypay.user.users.UserTypes;

public final class Emailer implements Subscriber {
	private SendEmailMessenger sendEmailMessenger;
	private ServiceResponse resp;
	
	Emailer (SendEmailMessenger sendEmailMessenger,ServiceResponse resp)
	{
		this.sendEmailMessenger = sendEmailMessenger;
		this.resp = resp ;
	}
	

	@Override
	public void process(String topic, UserAddedEvent user) {
		
		if (topic.equals(Constants.ADD_USER)) {
			
			resp  = sendVericiationEmail();
			if(resp.isSuccess())
			{
			User userType = UserFactory.getInstance(UserTypes.valueOf(user.getUserType()));
			userType.updateAccountStatus();
			sendEmailMessenger.sendMessage(user);	
			}
		}

	}

	private ServiceResponse sendVericiationEmail() {
		System.out.print("Sending veirifcation Email");
		return null;
	}

}
