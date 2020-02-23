package com.techland.paypay.user.helper;

public enum ExternalURL {
	
	
	LOGGER(""),MONITOR("");
	
	private String url;

	private ExternalURL(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	
	
	

}
