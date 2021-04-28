package org.sixdouglas.quarkus.cart;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class Cart  extends PanacheEntity {
    public String userId;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<CartLine> cartLines = new ArrayList<>();

    public static Optional<Cart> findByUserId(String userId){
        return find("userId", userId).firstResultOptional();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "userId='" + userId + '\'' +
                ", cartLines=" + cartLines +
                "} " + super.toString();
    }
}
