package com.techland.paypay.user.helper;

public enum Status {
	
	ACTIVE("Active"),EMAILNOTVERIFIED("Email-Not-Veirfied"),LOCKED("Locked"),DELETED("deleted"),VERIFICATIONEMAILSENT("Email-Sent"),EMAILAREADYEXIST("Duplicate-Entry");
	
	private String name ;

	private Status(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

		
	
}
