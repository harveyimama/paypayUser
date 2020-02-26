package com.techland.paypay.user.impl;


import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

import org.springframework.stereotype.Component;

import com.techland.paypay.user.config.PayPayThread;
import com.techland.paypay.user.config.Settings;
import com.techland.paypay.user.contracts.UserEvent;
import com.techland.paypay.user.events.UserAddedEvent;

import com.techland.paypay.user.persistence.Journal;
import com.techland.paypay.user.persistence.JournalRepository;
import com.techland.paypay.user.persistence.Snapshot;
import com.techland.paypay.user.persistence.SnapshotRepository;
import com.techland.paypay.user.persistence.User;
import com.techland.paypay.user.persistence.UserRepository;


@Component
public final class UserEntity {

	private Journal journal;
	private JournalRepository journalRepository;
	private Snapshot snapshot;
	private SnapshotRepository snapshotRepository;
	private UserRepository userRepo;

	public UserEntity(Journal journal, JournalRepository journalRepository, SnapshotRepository snapshotRepository,
			Snapshot snapshot, UserRepository userRep) {
		this.journal = journal;
		this.journalRepository = journalRepository;
		this.snapshotRepository = snapshotRepository;
		this.snapshot = snapshot;
		this.userRepo = userRep;
	}

	public UserState getState(final String userId) {
		
		Optional<Snapshot> sshot = getSnapshot(userId);
		List<Journal> events = null;
		
		if(sshot.isPresent())
			events = getEvents(sshot.get().getJournalId(), userId);
		else
			events = getAllEvents(userId);
		
		UserState userState = new UserState();
		if(events.stream().count() ==0)
		{
			return userState.getState(snapshot.getUserState());
		}
		
		
		events.stream().forEach(event -> {
			userState.addEvent(event.getUserEvent());
		});
		return userState;
	}

	public User login(final String username, final String password) {

		return userRepo.findByUsernameAndPassword(username, password);
	}

	private Optional<Snapshot> getSnapshot(final String userId) {
		return snapshotRepository.findBySnapshotUserId(userId);
	}

	public boolean ifExists(final String userId) {

		if (journalRepository.findByUserId(userId) != null)
			return true;
		else
			return false;
	}

	private List<Journal> getEvents(final String eventId, final String userId) {

		return journalRepository.findAllByUserIdAndEventIdGreaterThan(userId, eventId);

	}
	
	private List<Journal> getAllEvents( final String userId) {

		return journalRepository.findAllByUserId(userId);

	}

	public <T extends UserEvent> boolean addEvent(final String id, final T event, final String EventId) {
		
		boolean isSuccess = false;

		try {
			
			journal.setEventId(EventId);
			journal.setUserId(id);
			journal.setUserEvent(event.toString());
			
			Journal ret = journalRepository.save(journal);
			
			if (ret != null)
				isSuccess = true;
			
		} catch (Exception e) {
		}
		
		ExecutorService executer = PayPayThread.startThreader();
		
		executer.execute(new Runnable() {
			@Override
			public void run() {

				Optional<Snapshot> sshot = getSnapshot(id);
				List<Journal> events = null;
				
				if(sshot.isPresent())
					events = getEvents(sshot.get().getJournalId(), id);
				else
					events = getAllEvents(id);

				if (events.stream().count() >= Settings.CHECKPOINT_LIMIT) {
					
					snapshot.setJournalId(journal.getEventId());
					snapshot.setSnapshotUserId(journal.getUserId());
					snapshot.setUserState(getState(journal.getUserId()).toString());

					snapshotRepository.save(snapshot);
				}
			}
		});

		return isSuccess;
	}

	/*@SuppressWarnings("unchecked")
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

	}*/

}
