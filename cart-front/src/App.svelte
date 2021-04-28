<script>
	import Catalog from "./catalog/Catalog.svelte";
	import Cart from "./cart/Cart.svelte"
</script>

<main>
	<!-- App Bar -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-info border border-secondary rounded">
		<a class="navbar-brand" href="/#">E-Commerce</a>
		<button
				class="navbar-toggler"
				type="button"
				data-toggle="collapse"
				data-target="#navbarText"
				aria-controls="navbarText"
				aria-expanded="false"
				aria-label="Toggle navigation"
		>
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarText">
			<div class="navbar-nav mr-auto user-details"></div>
			<div class="navbar-text"></div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col">
				<Catalog />
			</div>
			<div class="col-3 border-left">
				<Cart />
			</div>
		</div>
	</div>
</main>

<style>
</style>