package com.techland.paypay.user.users;

import org.springframework.stereotype.Component;
import com.techland.paypay.user.contracts.User;
@Component
public class UserFactory {
	
	
	private static Customer customer;
	
	private UserFactory(Customer cust)
	{
		customer = cust;
	}
	
	public static User getInstance(UserTypes userType)
	{
		if (userType == UserTypes.MERCHANT_ADMIN)
			return null;
		else if (userType == UserTypes.CUSTOMER)
			return customer;
		else if (userType == UserTypes.MERCHANT_USER)
			return null;
		else if (userType == UserTypes.PAY_PAY_USER)
			return null;
		else if (userType == UserTypes.PAYPAY_ADMIN)
			return null;
		else
			return null;
				
		
	}

}
