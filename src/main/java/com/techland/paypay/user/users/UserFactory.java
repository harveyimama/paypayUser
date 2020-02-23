package com.techland.paypay.user.users;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.techland.paypay.user.contracts.User;
@Service
public class UserFactory {
	
	private static Map<UserTypes,User> userTypeMap = new HashMap<UserTypes,User>();
	private static Customer customer;
	
	private UserFactory(Customer cust)
	{
		customer = cust;
	}
	
	public static User getInstance(UserTypes userType)
	{
		User utype = userTypeMap.get(userType);
		if (utype == null)
		{
			switch (userType) {

			case MERCHANT_ADMIN:
				utype = null;
			case CUSTOMER:
				utype = customer;
			case MERCHANT_USER:
				utype = null;
			case PAY_PAY_USER:
				utype = null;
			case PAYPAY_ADMIN:
				utype = null;
			}

			userTypeMap.put(userType, utype);
		}
		
		return utype;
	}

}
