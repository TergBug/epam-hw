package org.mycode.si.config;

import org.mycode.si.model.DeliveryType;
import org.mycode.si.model.Home;
import org.mycode.si.model.Package;
import org.mycode.si.model.Store;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.MessageChannel;

import java.util.Objects;

@Configuration
@EnableIntegration
@IntegrationComponentScan("org.mycode.si")
public class IntegrationConfig {
    @Value("5")
    private int distanceFormHomeToDeliveryService;
    @Value("10")
    private int distanceFormStoreToDeliveryService;
    @Value("Package-1")
    private String nameOfPackageToHome;
    @Value("Package-2")
    private String nameOfPackageToStore;

    @Bean("packageDTH")
    @Scope(scopeName = "prototype")
    public Package packageDTH() {
        return new Package(1L, nameOfPackageToHome, DeliveryType.DTH);
    }

    @Bean("packageDTS")
    @Scope(scopeName = "prototype")
    public Package packageDTS() {
        return new Package(2L, nameOfPackageToStore, DeliveryType.DTS);
    }

    @Bean
    public Home home() {
        return new Home(distanceFormHomeToDeliveryService);
    }

    @Bean
    public Store store() {
        return new Store(distanceFormStoreToDeliveryService);
    }

    @Bean
    public MessageChannel input() {
        return new DirectChannel();
    }

    @Bean()
    public IntegrationFlow myFlow() {
        return IntegrationFlows.from("input")
                .publishSubscribeChannel(start -> start
                        .subscribe(sub -> sub
                                .handle(m -> System.out.println("\nStart delivering " + m.getPayload()))))
                .<Package, DeliveryType>route(Package::getDeliveryType, mapping -> mapping
                        .subFlowMapping(DeliveryType.DTH, sf -> sf
                                .enrichHeaders(r -> r.header("distance", home()))
                                .publishSubscribeChannel(c -> c
                                        .subscribe(sub -> sub
                                                .handle(m -> ((Package) m.getPayload())
                                                        .setDeliveryType(DeliveryType.TRANSFER)))
                                        .subscribe(sub -> sub
                                                .handle(m -> System.out.println(m.getPayload()
                                                        + " is being delivered to home")))
                                        .subscribe(sub -> sub
                                                .handle(m -> waitingMilliseconds(Objects.requireNonNull(m.getHeaders()
                                                        .get("distance", Home.class))
                                                        .getDistanceToDeliveryService() * 1000)))
                                        .subscribe(sub -> sub
                                                .handle(m -> Objects.requireNonNull(m.getHeaders()
                                                        .get("distance", Home.class))
                                                        .receivePackage((Package) m.getPayload())))
                                        .subscribe(sub -> sub
                                                .handle(m -> ((Package) m.getPayload())
                                                        .setDeliveryType(DeliveryType.DTH))))
                                .bridge())
                        .subFlowMapping(DeliveryType.DTS, sf -> sf
                                .enrichHeaders(r -> r.header("distance", store()))
                                .publishSubscribeChannel(c -> c
                                        .subscribe(sub -> sub
                                                .handle(m -> ((Package) m.getPayload())
                                                        .setDeliveryType(DeliveryType.TRANSFER)))
                                        .subscribe(sub -> sub
                                                .handle(m -> System.out.println(m.getPayload()
                                                        + " is being delivered to store")))
                                        .subscribe(sub -> sub
                                                .handle(m -> waitingMilliseconds(Objects.requireNonNull(m.getHeaders()
                                                        .get("distance", Store.class))
                                                        .getDistanceToDeliveryService() * 1000)))
                                        .subscribe(sub -> sub
                                                .handle(m -> Objects.requireNonNull(m.getHeaders()
                                                        .get("distance", Store.class))
                                                        .receivePackage((Package) m.getPayload())))
                                        .subscribe(sub -> sub
                                                .handle(m -> ((Package) m.getPayload())
                                                        .setDeliveryType(DeliveryType.DTS))))
                                .bridge()))
                .handle(m -> System.out.println("End of delivering " + m.getPayload() + "\n"))
                .get();
    }

    private void waitingMilliseconds(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ignored) {
        }
    }
}
