package com.epam.springcloud.orders.client;

import com.epam.springcloud.orders.model.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "products", fallback = ProductsClient.ProductsClientImpl.class)
public interface ProductsClient {
    @GetMapping("/{productName}")
    ResponseEntity<ProductDto> getProduct(@PathVariable String productName);

    @Component("productsClient")
    class ProductsClientImpl implements ProductsClient {
        @Override
        public ResponseEntity<ProductDto> getProduct(String name) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
