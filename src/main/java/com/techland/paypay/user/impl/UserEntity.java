package com.techland.paypay.user.impl;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Optional;

import com.techland.paypay.user.config.Settings;
import com.techland.paypay.user.contracts.UserEvent;
import com.techland.paypay.user.events.UserAddedEvent;
import com.techland.paypay.user.persistence.Journal;
import com.techland.paypay.user.persistence.JournalRepository;
import com.techland.paypay.user.persistence.Snapshot;
import com.techland.paypay.user.persistence.SnapshotRepository;
import com.techland.paypay.user.persistence.User;
import com.techland.paypay.user.persistence.UserRepository;

public class UserEntity {
	
	private Journal journal;
	private JournalRepository journalRepository;
	private Snapshot snapshot;
	private SnapshotRepository snapshotRepository;
	private UserRepository userRepo;
	
	public UserEntity(Journal journal,JournalRepository journalRepository,SnapshotRepository snapshotRepository,Snapshot snapshot,
			 UserRepository userRep)
	{
		this.journal = journal;
		this.journalRepository = journalRepository;
		this.snapshotRepository= snapshotRepository;
		this.snapshot= snapshot;
		this.userRepo = userRep;
	}

	public UserState getState(final String userId) {
		snapshot = getSnapshot(userId).get();
		List<Journal> events = getEvents(snapshot.getJournalId(),userId);
		UserState userState = new UserState();
		events.stream().forEach(event -> {
			userState.addEvent((UserAddedEvent) event.getUserEvent());
		});
		return userState;
	}
	
	public User login(final String username,final String password) {
		
		return userRepo.findByUsernameAndPassword(username,password);
	}

	private Optional<Snapshot> getSnapshot(final String userId) {
		return snapshotRepository.findById(userId);
	}
	
	public boolean ifExists(final String userId) {
		
		if ( journalRepository.findByUserId(userId) != null)
			return true;
			else 
				return false;
	}

	private List<Journal> getEvents(final String eventId,final String userId) {
		
		return journalRepository.findAllByUserIdAndIdGreaterThanId(userId,eventId);
		
	}
	
	public <T extends UserEvent> void addEvent(final String id,final T event,final String EventId) {
		
		journal.setEventId(EventId);
		journal.setUserId(id);
		journal.setUserEvent(event);
		
		journalRepository.save(journal);
		
		Optional<Snapshot> sshot = getSnapshot(id);
		List<Journal> events = getEvents(sshot.get().getJournalId(),id);
		
		if(events.stream().count() >= Settings.CHECKPOINT_LIMIT)
		{   
			snapshot.setJournalId(journal.getEventId());
			snapshot.setUserId(journal.getUserId());
			snapshot.setUserState(getState(journal.getUserId()));
			
			snapshotRepository.save(snapshot);
		}
	}

	@SuppressWarnings("unchecked")
	private <T extends UserEvent> T deSerializer(final String eventString) {
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
