package com.techland.paypay.user.persistence;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.stereotype.Component;

@Table
@Component
public class EventFailure {

	 @PrimaryKeyColumn(
		      name = "eventFailureId", 
		      ordinal = 2, 
		      type = PrimaryKeyType.PARTITIONED, 
		      ordering = Ordering.DESCENDING)
	private String eventFailureId;
	@Column
	private String eventSubscriber;
	@Column
	private String failureEvent;
	
	public String getEventFailureId() {
		return eventFailureId;
	}
	public void setEventFailureId(String eventFailureId) {
		this.eventFailureId = eventFailureId;
	}
	public String getEventSubscriber() {
		return eventSubscriber;
	}
	public void setEventSubscriber(String eventSubscriber) {
		this.eventSubscriber = eventSubscriber;
	}
	public String getFailureEvent() {
		return failureEvent;
	}
	public void setFailureEvent(String failureEvent) {
		this.failureEvent = failureEvent;
	}
		
	
	
	

}
