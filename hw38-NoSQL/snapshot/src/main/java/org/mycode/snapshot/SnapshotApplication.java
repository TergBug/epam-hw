package org.mycode.snapshot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SnapshotApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnapshotApplication.class, args);
    }

}
