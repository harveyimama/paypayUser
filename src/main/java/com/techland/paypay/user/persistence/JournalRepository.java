package com.techland.paypay.user.persistence;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;


public interface JournalRepository extends  CassandraRepository<Journal,String> {


	List<Journal> findAllByUserIdAndEventIdGreaterThan(String userId, String eventId);

	Object findByUserId(String userId);

}
