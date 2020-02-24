package com.techland.paypay.user.persistence;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.stereotype.Component;

@Table
@Component
public class Snapshot  {

	 @PrimaryKeyColumn(
		      name = "snapshotUserId", 
		      ordinal = 2, 		      
		      type = PrimaryKeyType.PARTITIONED, 
		      ordering = Ordering.DESCENDING)
	String snapshotUserId;
	 @Column
	String UserState;
	 @Column
	String journalId;
	
	
	public String getSnapshotUserId() {
		return snapshotUserId;
	}
	public void setSnapshotUserId(String snapshotUserId) {
		this.snapshotUserId = snapshotUserId;
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
