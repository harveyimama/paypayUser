package com.techland.paypay.user.persistence;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.stereotype.Component;
@Table
@Component
public class StateFailure  {

	
	 @PrimaryKeyColumn(
		      name = "stateUserId", 
		      ordinal = 2, 
		      type = PrimaryKeyType.PARTITIONED, 
		      ordering = Ordering.DESCENDING)
	private String stateUserId;
	 @Column
	private String stateSubscriber;
	public String getStateUserId() {
		return stateUserId;
	}
	public void setStateUserId(String stateUserId) {
		this.stateUserId = stateUserId;
	}
	public String getStateSubscriber() {
		return stateSubscriber;
	}
	public void setStateSubscriber(String stateSubscriber) {
		this.stateSubscriber = stateSubscriber;
	}
	
	
	
	
	
	
	

}
