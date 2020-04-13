package com.epam.springcloud.orders.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "users", fallback = UsersClient.UsersClientImpl.class)
public interface UsersClient {
    @GetMapping("/{userName}")
    ResponseEntity<?> getUser(@PathVariable String userName);

    @Component("usersClient")
    class UsersClientImpl implements UsersClient {
        @Override
        public ResponseEntity<?> getUser(String name) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
