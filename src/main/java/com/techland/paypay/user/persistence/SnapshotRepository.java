package com.techland.paypay.user.persistence;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface SnapshotRepository extends  CassandraRepository<Snapshot,String> {

}
