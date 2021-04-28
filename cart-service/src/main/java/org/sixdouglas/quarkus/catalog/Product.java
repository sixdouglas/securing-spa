package org.sixdouglas.quarkus.catalog;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.util.Optional;

@Entity
public class Product extends PanacheEntity {
    public String name;

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }

    public static Optional<Product> findName(String name){
        return find("name", name).firstResultOptional();
    }
}
