package org.mycode.snapshotmanager.client;

import org.mycode.snapshotmanager.model.Snapshot;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@FeignClient(value = "snapshot", decode404 = true)
public interface SnapshotClient {
    @GetMapping("api/v1/snapshots/{date}")
    ResponseEntity<Snapshot> getSnapshotByDate(@PathVariable String date);

    @PostMapping("api/v1/snapshots")
    @ResponseStatus(HttpStatus.CREATED)
    void createSnapshot(Snapshot snapshot);
}
