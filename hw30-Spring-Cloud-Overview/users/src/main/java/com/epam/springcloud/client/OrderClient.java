package com.epam.springcloud.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "orders", fallback = OrderClient.OrderClientImpl.class)
public interface OrderClient {
    @GetMapping("/users/{name}")
    List<String> getProducts(@PathVariable String name);

    @Component("orderClient")
    class OrderClientImpl implements OrderClient {
        @Override
        public List<String> getProducts(String name) {
            return List.of("One", "Two");
        }
    }
}
