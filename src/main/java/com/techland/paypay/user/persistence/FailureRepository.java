package com.techland.paypay.user.persistence;

import org.springframework.data.cassandra.repository.CassandraRepository;


public interface FailureRepository extends  CassandraRepository<Journal,String> {

}
