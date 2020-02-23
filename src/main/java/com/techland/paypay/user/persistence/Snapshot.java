package com.techland.paypay.user.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Snapshot  {

	@Id
	String userId;
	String UserState;
	String journalId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getJournalId() {
		return journalId;
	}
	public void setJournalId(String journalId) {
		this.journalId = journalId;
	}
	public String getUserState() {
		return UserState;
	}
	public void setUserState(String userState) {
		UserState = userState;
	}
	
}
