package org.mycode.storage.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "movements")
public class Movement {
    @Id
    private int id;
    private Date date;
    @ManyToOne
    private Storage storage;
    @ManyToOne
    private Product product;
    private int quantity;

    public Movement() { }

    public Movement(int id, Date date, Storage storage, Product product, int quantity) {
        this.id = id;
        this.date = date;
        this.storage = storage;
        this.product = product;
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

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Movement{" +
                "id=" + id +
                ", date=" + date +
                ", storage=" + storage +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movement movement = (Movement) o;
        return id == movement.id &&
                quantity == movement.quantity &&
                Objects.equals(date, movement.date) &&
                Objects.equals(storage, movement.storage) &&
                Objects.equals(product, movement.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, storage, product, quantity);
    }
}
