package com.techland.paypay.user.persistence;

import javax.persistence.Column;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.stereotype.Component;

@Table
@Component
public class Journal  {
		
	 @PrimaryKeyColumn(
		      name = "eventId", 
		      ordinal = 2, 
		      type = PrimaryKeyType.PARTITIONED, 
		      ordering = Ordering.DESCENDING)
	private String eventId;
	 @Column
	private String userEvent;
	 @Column
	private String userId;
	
		
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getUserEvent() {
		return userEvent;
	}
	public void setUserEvent(String userEvent) {
		this.userEvent = userEvent;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
