<script>
    import catalogService from "./catalogService";
    export let product = {};

    function addToCart(productId) {
        catalogService.addToCart(productId);
    }
</script>

<style>
    .card-img-top {
        margin: auto;
        width: 150px;
    }
    .product-card {
        margin-left: 10px;
        margin-top: 20px;
        width: 16rem;
    }
</style>

<div class="card product-card shadow-sm">
    <img src="./images/no-image-available.png" class="card-img-top" alt="Nothing for the moment">
    <div class="card-body">
        <h5 class="card-title">{product.name} <small>[{product.id}]</small></h5>
        <p class="card-text">Il devrait y avoir une description du production ici.</p>
        <span class="btn btn-warning" on:click="{addToCart(product.id)}">Add to cart</span>
    </div>
</div>
