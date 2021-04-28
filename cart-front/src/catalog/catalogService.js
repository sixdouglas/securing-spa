import api from "../api";
import cartService from "../cart/cartService"
import {products} from "../store";

function getProductList() {
    api.get("/catalog")
        .then(productList => {
            products.set(productList);
        })
        .catch(reason => console.error(reason));
}

function addToCart(productId) {
    cartService.addToCart(productId);
}

const catalogService = {
    getProductList,
    addToCart
};

export default catalogService;