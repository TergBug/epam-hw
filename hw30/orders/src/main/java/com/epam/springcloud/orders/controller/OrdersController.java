package com.epam.springcloud.orders.controller;

import java.util.ArrayList;
import java.util.List;

import com.epam.springcloud.orders.client.NotificationClient;
import com.epam.springcloud.orders.client.ProductsClient;
import com.epam.springcloud.orders.client.UsersClient;
import com.epam.springcloud.orders.model.Order;
import com.epam.springcloud.orders.model.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static java.util.stream.Collectors.toList;

@RestController
public class OrdersController {
    private List<Order> orderList = new ArrayList<>();
    private UsersClient usersClient;
    private ProductsClient productsClient;
    private NotificationClient notificationClient;

    @Autowired
    public OrdersController(UsersClient usersClient,
                            ProductsClient productsClient,
                            NotificationClient notificationClient) {
        this.usersClient = usersClient;
        this.productsClient = productsClient;
        this.notificationClient = notificationClient;
    }

    @PostMapping
    public ResponseEntity<Order> createNewOrder(@RequestBody Order order) {
        ResponseEntity<?> userResponseEntity = usersClient.getUser(order.getUserName());
        ResponseEntity<ProductDto> productResponseEntity = productsClient.getProduct(order.getProduct());

        if(userResponseEntity.getStatusCode().is5xxServerError()
                || productResponseEntity.getStatusCode().is5xxServerError()){
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } else if(userResponseEntity.getStatusCode().is4xxClientError()
                || productResponseEntity.getBody()==null
                || productResponseEntity.getBody().getQuantity()<=0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderList.add(order);

        if(notificationClient.createNotification(order.getUserName()).getStatusCode().isError()){
            return new ResponseEntity<>(order, HttpStatus.SERVICE_UNAVAILABLE);
        }
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/users/{userName}")
    public List<String> getProductsForUser(@PathVariable String userName) {
        return orderList.stream()
                .filter(order -> userName.equals(order.getUserName()))
                .map(Order::getProduct)
                .collect(toList());
    }
}
