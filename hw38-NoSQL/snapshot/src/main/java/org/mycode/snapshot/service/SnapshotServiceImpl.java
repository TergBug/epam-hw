package org.mycode.snapshot.service;

import org.mycode.snapshot.dao.SnapshotDao;
import org.mycode.snapshot.model.Snapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SnapshotServiceImpl implements SnapshotService {
    private SnapshotDao snapshotDao;

    @Autowired
    public SnapshotServiceImpl(SnapshotDao snapshotDao) {
        this.snapshotDao = snapshotDao;
    }

    @Override
    public List<Snapshot> getAll() {
        return snapshotDao.findAll();
    }

    @Override
    public Snapshot getByDate(Date date) {
        return snapshotDao.findByDate(date);
    }

    @Override
    public void put(Snapshot snapshot) {
        snapshotDao.save(snapshot);
    }
}
