package com.techland.paypay.user.persistence;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface StateFailureRepository extends  CassandraRepository<StateFailure,String> {

	List<StateFailure> findBySubscriber(String simpleName);

}
