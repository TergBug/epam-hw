package com.epam.springcloud.products.controller;

import com.epam.springcloud.products.exception.NotFoundProductException;
import com.epam.springcloud.products.model.Product;
import com.epam.springcloud.products.repository.ProductsRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductsController {
    private ProductsRepository repository;

    @Autowired
    public ProductsController(ProductsRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Product createProduct() {
        String name = RandomStringUtils.randomAlphabetic(16);
        Long quantity = RandomUtils.nextLong(1, 10);
        repository.add(name, quantity);
        return new Product(name, quantity);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return repository.getAll();
    }

    @GetMapping("/{productName}")
    public Product getProduct(@PathVariable String productName) {
        return new Product(productName, repository.getQuantity(productName));
    }

    @PutMapping("/{productName}")
    public Product removeOneProduct(@PathVariable String productName) {
        Long quantity = repository.getQuantity(productName);
        if (quantity <= 0) {
            throw new NotFoundProductException();
        }
        quantity = repository.removeOneByName(productName);
        return new Product(productName, quantity);
    }
}
