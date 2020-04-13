package org.mycode.si.model;

public class Package {
    private long id;
    private String name;
    private DeliveryType deliveryType;

    public Package(long id, String name, DeliveryType deliveryType) {
        this.id = id;
        this.name = name;
        this.deliveryType = deliveryType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    @Override
    public String toString() {
        return "Package{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deliveryType=" + deliveryType +
                '}';
    }
}
