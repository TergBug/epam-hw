package com.epam.springcloud.controller;

import java.util.*;

import com.epam.springcloud.client.OrderClient;
import com.epam.springcloud.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    private Map<String, User> registeredUsers = new HashMap<>();
    private OrderClient orderClient;

    @Autowired
    public UsersController(OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    @PostMapping
    public User createUser() {
        User user = new User(RandomStringUtils.randomAlphabetic(13));
        registeredUsers.put(user.getName(), user);
        return user;
    }

    @GetMapping
    private List<User> getAllUsers() {
        return new ArrayList<>(registeredUsers.values());
    }

    @GetMapping("/{userName}")
    public ResponseEntity<User> getUser(@PathVariable String userName) {
        User user = registeredUsers.get(userName);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{userName}/products")
    public List<String> getProductsByUser(@PathVariable String userName) {
        return orderClient.getProducts(userName);
    }
}
