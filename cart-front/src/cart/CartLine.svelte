<script>
    import cartService from "./cartService";

    export let line = {};
    export let cartId = {};

    function removeFromCart(idCart, idLine) {
        cartService.removeFromCart(idCart, idLine);
    }
</script>

<style>
</style>

<div class="list-group-item list-group-item-action">
    <div class="d-flex w-100 justify-content-between">
        <h5 class="mb-1">{line.product.name}</h5>
        <small>{line.product.id}</small>
    </div>
    <p class="mb-1">Une courte description ici.</p>
    <span class="btn btn-outline-warning btn-sm" on:click="{removeFromCart(cartId, line.id)}">Remove from cart</span>
</div>
