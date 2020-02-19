package com.techland.paypay.user.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import com.techland.paypay.user.impl.*;

@Entity
public class Snapshot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
