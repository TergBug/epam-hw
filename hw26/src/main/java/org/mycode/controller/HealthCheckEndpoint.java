package org.mycode.controller;

import org.mycode.model.HealthAndAppName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.health.Health;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("application.properties")
@Component
@Endpoint(id = "healthCheck")
public class HealthCheckEndpoint {
    @Value("${spring.application.name}")
    private String appName;

    @ReadOperation
    public HealthAndAppName healthCheck() {
        return new HealthAndAppName(appName, Health.up().build().getStatus().toString());
    }
}
