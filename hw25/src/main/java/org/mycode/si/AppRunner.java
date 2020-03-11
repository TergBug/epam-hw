package org.mycode.si;

import org.mycode.si.config.IntegrationConfig;
import org.mycode.si.model.DeliveryService;
import org.mycode.si.model.Package;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class AppRunner {
    public static void main(String[] args) {
        AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(IntegrationConfig.class);
        DeliveryService deliveryService = applicationContext.getBean(DeliveryService.class);
        Package packageToHome = applicationContext.getBean("packageDTH", Package.class);
        Package packageToStore = applicationContext.getBean("packageDTS", Package.class);
        deliveryService.takePackage(packageToHome);
        deliveryService.takePackage(packageToStore);
        applicationContext.close();
    }
}
