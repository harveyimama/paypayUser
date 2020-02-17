package com.techland.paypay.user.impl;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.List;

import com.techland.paypay.user.config.Settings;
import com.techland.paypay.user.contracts.UserEvent;

public class UserEntity {

	public UserState getState(String id) {
		String snapshot = getSnapshot(id);
		List<String> events = getEvents(snapshot);
		UserState userState = new UserState();
		events.stream().forEach(event -> {
			userState.addEvent(deSerializer(event));
		});
		return userState;
	}

	private String getSnapshot(String id) {
		return "checkpoint";
	}

	private List<String> getEvents(String checkpoint) {
		return null;
	}
	
	public <T extends UserEvent> void addEvent(String id,T event,String EventId) {
		//TODO persist Event
		
		String snapshot = getSnapshot(id);
		List<String> events = getEvents(snapshot);
		if(events.stream().count() >= Settings.CHECKPOINT_LIMIT)
		{
			//TODO persist new checkpoint
		}
	}

	@SuppressWarnings("unchecked")
	private <T extends UserEvent> T deSerializer(String eventString) {
		try {
			byte[] buf = eventString.getBytes();
			ObjectInputStream objectIn = null;
			if (buf != null)
				objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
			return (T) objectIn.readObject();
		} catch (Exception e) {
			return null;
		}

	}

}
