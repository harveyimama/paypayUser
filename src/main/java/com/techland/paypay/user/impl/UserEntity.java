package com.techland.paypay.user.impl;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Optional;

import com.techland.paypay.user.config.Settings;
import com.techland.paypay.user.contracts.UserEvent;
import com.techland.paypay.user.persistence.Journal;
import com.techland.paypay.user.persistence.JournalRepository;
import com.techland.paypay.user.persistence.Snapshot;
import com.techland.paypay.user.persistence.SnapshotRepository;

public class UserEntity {
	
	private Journal journal;
	private JournalRepository journalRepository;
	private Snapshot snapshot;
	private SnapshotRepository snapshotRepository;
	
	public UserEntity(Journal journal,JournalRepository journalRepository,SnapshotRepository snapshotRepository,Snapshot snapshot)
	{
		this.journal = journal;
		this.journalRepository = journalRepository;
		this.snapshotRepository= snapshotRepository;
		this.snapshot= snapshot;
	}

	public UserState getState(String userId) {
		snapshot = getSnapshot(userId).get();
		List<String> events = getEvents(snapshot.getJournalId());
		UserState userState = new UserState();
		events.stream().forEach(event -> {
			userState.addEvent(deSerializer(event));
		});
		return userState;
	}

	private Optional<Snapshot> getSnapshot(String userId) {
		return snapshotRepository.findById(userId);
	}
	
	public boolean ifExists(String userId) {
		
		if ( journalRepository.findById(userId) != null)
			return true;
			else 
				return false;
	}

	private List<String> getEvents(String journalId) {
		//TODO write findAllafterID
		return null;
	}
	
	public <T extends UserEvent> void addEvent(String id,T event,String EventId) {
		
		journal.setEventId(EventId);
		journal.setUserId(id);
		journal.setUserEvent(event);
		
		journalRepository.save(journal);
		
		Optional<Snapshot> sshot = getSnapshot(id);
		List<String> events = getEvents(sshot.get().getJournalId());
		
		if(events.stream().count() >= Settings.CHECKPOINT_LIMIT)
		{   
			snapshot.setJournalId(journal.getEventId());
			snapshot.setUserId(journal.getUserId());
			snapshot.setUserState(getState(journal.getUserId()));
			
			snapshotRepository.save(snapshot);
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
