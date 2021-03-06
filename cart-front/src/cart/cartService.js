import api from "../api";
import {cart} from "../store";

function getCart() {
    return api.get("/cart")
            .then(cartObject => {
                cart.set(cartObject);
                return cartObject;
            })
            .catch(reason => console.error(reason))
}

function addToCart(productId) {
    let cartId = 0;
    cart.subscribe(value => cartId = value.id);
    api.put("/cart/" + cartId, {"id": productId})
        .then(cartObject => cart.set(cartObject))
        .catch(reason => console.error(reason))
}

function removeFromCart(idCart, idLine) {
    api.delete("/cart/" + idCart + "/lines/" + idLine)
        .then(cartObject => cart.set(cartObject))
        .catch(reason => console.error(reason))
}

const cartService = {
    getCart,
    addToCart,
    removeFromCart
};

export default cartService;