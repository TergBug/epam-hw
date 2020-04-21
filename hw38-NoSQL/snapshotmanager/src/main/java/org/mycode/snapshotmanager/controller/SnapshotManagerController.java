package org.mycode.snapshotmanager.controller;

import org.mycode.snapshotmanager.service.SnapshotManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping(value = "api/v1/snapshotmanager")
public class SnapshotManagerController {
    private SnapshotManagement snapshotManagement;

    @Autowired
    public SnapshotManagerController(SnapshotManagement snapshotManagement) {
        this.snapshotManagement = snapshotManagement;
    }

    @PostMapping("{storageId}/{date}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createSnapshot(@PathVariable int storageId, @PathVariable String date){
        snapshotManagement.makeSnapshot(storageId, Date.valueOf(date));
    }
}
