package org.mycode.si.model;

public class Home {
    private int distanceToDeliveryService;

    public Home(int distanceToDeliveryService) {
        this.distanceToDeliveryService = distanceToDeliveryService;
    }

    public int getDistanceToDeliveryService() {
        return distanceToDeliveryService;
    }

    public void receivePackage(Package pack) {
        System.out.println("Home has got a pack " + pack.getName());
    }
}
