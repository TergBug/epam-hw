package org.mycode.snapshotmanager.model;

import java.sql.Date;
import java.util.Objects;

public class MovementInStorage {
    private int id;
    private Date date;
    private int storageId;
    private String storageName;
    private int productId;
    private String productName;
    private int quantity;

    public MovementInStorage(int id, Date date, int storageId, String storageName, int productId, String productName,
                             int quantity) {
        this.id = id;
        this.date = date;
        this.storageId = storageId;
        this.storageName = storageName;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStorageId() {
        return storageId;
    }

    public void setStorageId(int storageId) {
        this.storageId = storageId;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "MovementDto{" +
                "id=" + id +
                ", date=" + date +
                ", storageId=" + storageId +
                ", storageName='" + storageName + '\'' +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovementInStorage that = (MovementInStorage) o;
        return id == that.id &&
                storageId == that.storageId &&
                productId == that.productId &&
                quantity == that.quantity &&
                Objects.equals(date, that.date) &&
                Objects.equals(storageName, that.storageName) &&
                Objects.equals(productName, that.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, storageId, storageName, productId, productName, quantity);
    }
}
