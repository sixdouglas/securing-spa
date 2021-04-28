package org.sixdouglas.quarkus.cart;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.sixdouglas.quarkus.catalog.Product;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class CartLine extends PanacheEntity {
    @ManyToOne
    public Product product;

    @Override
    public String toString() {
        return "CartLine{" +
                "product=" + product +
                "} " + super.toString();
    }
}
