package com.techland.paypay.user.events;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techland.paypay.user.contracts.UserEvent;

public class EventFactory{

	private EventFactory()
	{}
	
	@SuppressWarnings("unchecked")
	public static  <T extends UserEvent>  T getEvent (String eventString)
	{
		ObjectMapper mapper = new ObjectMapper();	
		
		try {
			JsonNode actualObj = mapper.readTree(eventString);
			  
			 
			if (actualObj.get("class").asText().equals("UserAddedEvent"))
			{
			return (T) mapper.readValue(eventString, UserAddedEvent.class);
			}
			else if(actualObj.get("class").asText().equals("UserStatusChangedEvent"))
			{
			return (T) mapper.readValue(eventString, UserStatusChangedEvent.class);
			}
			else
			{
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
		
		
	}
}
