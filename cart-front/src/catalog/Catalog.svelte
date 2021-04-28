<script>
    import { onMount } from "svelte";
    import { products } from "../store";
    import catalogService from "./catalogService";
    import Product from "./Product.svelte";

    onMount(async () => {
        catalogService.getProductList();
    });
</script>

<style>
</style>

<main>
    <div class="container-fluid">
        <div class="row">
            <div class="col text-center pt-1">
                <h5>Products</h5>
            </div>
        </div>
        <div class="row">
            {#each $products as item (item.id)}
                <Product product="{item}"/>
            {/each}
        </div>
    </div>
</main>