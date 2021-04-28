<script>
    import { onMount, onDestroy } from "svelte";
    import { cart } from "../store";
    import cartService from "./cartService";
    import CartLine from "./CartLine.svelte";

    console.info("Cart in script");
    let cartId = 0;
    let unsubscribe;
    onMount(async () => {
        console.info("Cart onMount");
        cartService.getCart()
            .then(cartObject => cartId = cartObject.id);
    });
</script>

<style>
    .cart {
        margin-top: 20px;
    }
    H5 {
        text-align: center;
    }
</style>

<main class="pl-1">
    <div class="row">
        <div class="col pt-1">
            <h5>Cart</h5>
        </div>
    </div>
    {#if $cart}
        <div class="list-group cart">
            {#each $cart.cartLines as item (item.id)}
                <CartLine line="{item}" cartId="{cartId}"/>
            {/each}
        </div>
    {/if}
</main>