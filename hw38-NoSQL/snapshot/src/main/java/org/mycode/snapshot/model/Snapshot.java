package org.mycode.snapshot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Document(collection = "snapshots")
public class Snapshot {
    @Id
    private int id;
    private Date date;
    private Storage storage;
    private List<Product> products;

    public Snapshot() {
    }

    public Snapshot(Date date, Storage storage, List<Product> products) {
        this.id = 0;
        this.date = date;
        this.storage = storage;
        this.products = products;
    }

    public Snapshot(int id, Date date, Storage storage, List<Product> products) {
        this.id = id;
        this.date = date;
        this.storage = storage;
        this.products = products;
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

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Snapshot{" +
                "id=" + id +
                ", date=" + date +
                ", storage=" + storage +
                ", product=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Snapshot snapshot = (Snapshot) o;
        return id == snapshot.id &&
                Objects.equals(date, snapshot.date) &&
                Objects.equals(storage, snapshot.storage) &&
                Objects.equals(products, snapshot.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, storage, products);
    }
}
