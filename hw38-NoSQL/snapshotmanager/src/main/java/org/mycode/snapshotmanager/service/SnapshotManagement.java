package org.mycode.snapshotmanager.service;

import java.sql.Date;

public interface SnapshotManagement {
    void makeSnapshot(int storageId, Date date);
}
