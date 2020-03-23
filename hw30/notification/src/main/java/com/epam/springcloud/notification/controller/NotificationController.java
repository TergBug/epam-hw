package com.epam.springcloud.notification.controller;

import com.epam.springcloud.notification.model.Notification;
import com.epam.springcloud.notification.client.UsersClient;
import com.epam.springcloud.notification.model.Notifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class NotificationController {
    private final Set<Notification> notifications = new HashSet<>();
    private UsersClient usersClient;

    @Autowired
    public NotificationController(UsersClient usersClient) {
        this.usersClient = usersClient;
    }

    @PostMapping("/{userName}")
    public ResponseEntity<Notification> notify(@PathVariable String userName) {
        if(usersClient.getUser(userName).getStatusCode().isError()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Notification notification =new Notification(userName, Notifier.EMAIL);
        notifications.add(notification);
        return new ResponseEntity<>(notification, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Notification> getNotifications() {
        return new ArrayList<>(notifications);
    }
}
