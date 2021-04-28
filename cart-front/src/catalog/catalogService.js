import api from "../api";
import cartService from "../cart/cartService"
import {products} from "../store";

function getProductList() {
    console.info("getProductList(): start");
    api.get("/catalog")
        .then(productList => {
            console.info("getProductList(): set products["+productList.length+"]");
            products.set(productList);
        })
        .catch(reason => console.error(reason));
}

async function addToCart(productId) {
    await cartService.addToCart(productId);
}

const catalogService = {
    getProductList,
    addToCart
};

export default catalogService;