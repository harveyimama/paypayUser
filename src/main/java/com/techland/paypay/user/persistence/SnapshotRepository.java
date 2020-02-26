package com.techland.paypay.user.persistence;

import java.util.Optional;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface SnapshotRepository extends  CassandraRepository<Snapshot,String> {

	Optional<Snapshot> findBySnapshotUserId(String userId);

}
