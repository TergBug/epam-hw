package org.mycode.si.model;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface DeliveryService {
    @Gateway(requestChannel = "input")
    void takePackage(Package pack);
}
