package org.sixdouglas.quarkus.cart;

import io.smallrye.common.constraint.Assert;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.sixdouglas.quarkus.catalog.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {

    @Inject
    JsonWebToken jwt;

    private static final Logger LOGGER = LoggerFactory.getLogger(CartResource.class);

    @Transactional
    @GET
    @RolesAllowed("read:cart")
    public Cart getUserCart() {
        return Cart.findByUserId(jwt.getSubject())
                .orElseGet(this::createNewAndSaveCart);
    }

    @Transactional
    @PUT
    @Path("/{cartId}")
    @RolesAllowed("add:cart")
    public Cart addProductToUserCart(@PathParam("cartId") Long cartId, Product product) {
        Cart userCart = Cart.<Cart>findByIdOptional(cartId)
                .orElseGet(this::createNewAndSaveCart);

        Assert.assertTrue(userCart.userId.equals(jwt.getSubject()));

        Product.<Product>findByIdOptional(product.id)
                .ifPresent(foundProduct -> addProductAndSaveCart(foundProduct, userCart));

        return userCart;
    }

    @Transactional
    @DELETE
    @Path("/{cartId}/lines/{lineId}")
    @RolesAllowed("delete:cart")
    public Cart removeProductFromUserCart(@PathParam("cartId") Long cartId, @PathParam("lineId") Long lineId) {
        Cart userCart = Cart.<Cart>findByIdOptional(cartId)
                .orElseGet(this::createNewAndSaveCart);

        Assert.assertTrue(userCart.userId.equals(jwt.getSubject()));

        userCart.cartLines.stream()
                .filter(cartLine -> cartLine.id.equals(lineId))
                .findFirst()
                .ifPresent(cartLine -> removeLineAndSaveCart(cartLine, userCart));

        return userCart;
    }

    private Cart createNewAndSaveCart() {
        LOGGER.info("Get New Cart");
        final Cart cart = new Cart();
        cart.userId = jwt.getSubject();
        cart.persist();
        return cart;
    }

    private void addProductAndSaveCart(Product product, Cart userCart) {
        LOGGER.info("Add product {} to Cart {}", product, userCart);
        final boolean productNotInCart = userCart.cartLines.stream()
                .noneMatch(cartLine -> cartLine.product == product);
        if (productNotInCart) {
            LOGGER.info("Product {} not in Cart {}", product, userCart);
            final CartLine line = new CartLine();
            line.product = product;
            userCart.cartLines.add(line);
            userCart.persist();
        }
    }

    private void removeLineAndSaveCart(CartLine cartLine, Cart userCart) {
        LOGGER.info("Remove line {} from Cart {}", cartLine, userCart);
        userCart.cartLines.remove(cartLine);
        userCart.persist();
    }
}
