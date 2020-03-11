package org.mycode.si.model;

public class Store {
    private int distanceToDeliveryService;

    public Store(int distanceToDeliveryService) {
        this.distanceToDeliveryService = distanceToDeliveryService;
    }

    public int getDistanceToDeliveryService() {
        return distanceToDeliveryService;
    }

    public void receivePackage(Package pack) {
        System.out.println("Store has got a pack " + pack.getName());
    }
}
