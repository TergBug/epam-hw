package com.epam.springcloud.orders.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "notification", fallback = NotificationClient.NotificationClientImpl.class)
public interface NotificationClient {
    @PostMapping("/{userName}")
    ResponseEntity<?> createNotification(@PathVariable String userName);

    @Component("notificationClient")
    class NotificationClientImpl implements NotificationClient {
        @Override
        public ResponseEntity<?> createNotification(String name) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
