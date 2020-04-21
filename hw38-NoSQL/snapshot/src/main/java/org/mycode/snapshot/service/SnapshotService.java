package org.mycode.snapshot.service;

import org.mycode.snapshot.model.Snapshot;

import java.util.Date;
import java.util.List;

public interface SnapshotService {
    List<Snapshot> getAll();
    Snapshot getByDate(Date date);
    void put(Snapshot snapshot);
}
