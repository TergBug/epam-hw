package org.mycode.snapshot.dao;

import org.mycode.snapshot.model.Snapshot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface SnapshotDao extends MongoRepository<Snapshot, Integer> {
    Snapshot findByDate(Date date);
}
