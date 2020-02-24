package com.techland.paypay.user.persistence;

import java.util.List;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface EventFailureRepository extends  CassandraRepository<EventFailure,String> {

	List<EventFailure> findAllByEventSubscriber(String eventSubscriber);
	

}
