package org.mycode.snapshot.controller;

import org.mycode.snapshot.model.Snapshot;
import org.mycode.snapshot.service.SnapshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/snapshots")
public class SnapshotController {
    private SnapshotService service;

    @Autowired
    public SnapshotController(SnapshotService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Snapshot>> getByDate(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("{date}")
    public ResponseEntity<Snapshot> getByDate(@PathVariable String date){
        java.util.Date dateIn = new java.util.Date(java.sql.Date.valueOf(date).getTime());
        return new ResponseEntity<>(service.getByDate(dateIn), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void create(@RequestBody Snapshot snapshot){
        service.put(snapshot);
    }
}
