package org.sixdouglas.quarkus.catalog;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/v1/catalog")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CatalogResource {

    @Transactional
    @PostConstruct
    public void fillDatabase() {
        Product product = new Product();
        product.name = "Chaise";
        product.persist();

        product = new Product();
        product.name = "Table";
        product.persist();

        product = new Product();
        product.name = "Assiete";
        product.persist();

        product = new Product();
        product.name = "Couteau";
        product.persist();

        product = new Product();
        product.name = "Fourchette";
        product.persist();

        product.flush();
    }

    @Transactional
    @GET
    public List<Product> list() {
        return Product.findAll().list();
    }
}
