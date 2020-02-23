package com.techland.paypay.user.users;

public enum UserTypes {

	CUSTOMER("CUS"), MERCHANT_ADMIN("MADMIN"), PAYPAY_ADMIN("PAYADMIN"), PAY_PAY_USER("PAYUSER"), MERCHANT_USER("MERUSER");
	
	private String userType;
	
	UserTypes (String userType)
	{
		this.userType =userType;
	}

	public String getUserType() {
		return userType;
	}

	
	
	
}
