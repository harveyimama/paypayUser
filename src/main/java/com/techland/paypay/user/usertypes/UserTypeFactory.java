package com.techland.paypay.user.usertypes;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.techland.paypay.user.contracts.UserType;
@Service
public class UserTypeFactory {
	
	private static Map<UserTypes,UserType> userTypeMap = new HashMap<UserTypes,UserType>();
	private static Customer customer;
	
	private UserTypeFactory(Customer cust)
	{
		customer = cust;
	}
	
	public static UserType getInstance(UserTypes userType)
	{
		UserType utype = userTypeMap.get(userType);
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
