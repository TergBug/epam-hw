package org.mycode.snapshot.model;

import java.util.Objects;

public class Storage {
    private int id;
    private String name;

    public Storage() { }

    public Storage(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return id == storage.id &&
                Objects.equals(name, storage.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
