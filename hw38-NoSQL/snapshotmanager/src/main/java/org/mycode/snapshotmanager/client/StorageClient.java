package org.mycode.snapshotmanager.client;

import org.mycode.snapshotmanager.model.MovementInStorage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Component
@FeignClient(value = "storage")
public interface StorageClient {
    @GetMapping("api/v1/movements")
    List<MovementInStorage> getAllMovementInStorage();
}
