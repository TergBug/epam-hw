package com.epam.springcloud.products.repository;

import com.epam.springcloud.products.model.Product;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ProductsRepository {
    private Map<String, Long> productCatalog = new HashMap<>();

    public void add(String name, Long quantity) {
        productCatalog.put(name, quantity);
    }

    public Long getQuantity(String name) {
        return productCatalog.getOrDefault(name, 0L);
    }

    public Long removeOneByName(String name) {
        Long quantity = productCatalog.computeIfPresent(name, (key, value) -> --value);
        if (quantity <= 0) {
            productCatalog.remove(name);
        }
        return quantity;
    }

    public List<Product> getAll() {
        return productCatalog.entrySet().stream()
                .map(entry -> new Product(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}