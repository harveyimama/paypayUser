package com.techland.paypay.user.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;
import com.techland.paypay.user.impl.*;

@Entity
public class Snapshot {

	@Id
	String userId;
	UserState UserState;
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
	public UserState getUserState() {
		return UserState;
	}
	public void setUserState(UserState userState) {
		UserState = userState;
	}
	
}
