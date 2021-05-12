<script>
	import Catalog from "./catalog/Catalog.svelte";
	import Cart from "./cart/Cart.svelte"

	import { onMount } from "svelte";
	import auth from "./security/authService";
	import { isAuthenticated, user, auth0Client } from "./store";

	let auth0ClientPromise = auth.createClient();
	let auth0Cli;

	onMount(async () => {
		auth0ClientPromise
				.then(auth0ClientValue => {
					auth0Cli = auth0ClientValue;
					$auth0Client = auth0ClientValue;
					$auth0Client.isAuthenticated()
							.then(isAuthenticatedValue => isAuthenticated.set(isAuthenticatedValue));
					$auth0Client.getUser()
							.then(userValue => user.set(userValue));
				});
	});

	function login() {
		auth.loginWithPopup(auth0Cli);
	}

	function logout() {
		auth.logout(auth0Cli);
	}
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
			<div class="navbar-nav mr-auto user-details">
				{#if $isAuthenticated}
					<span class="text-white">&nbsp;&nbsp;{$user.name} ({$user.email})</span>
				{:else}
					<span>&nbsp;</span>
				{/if}
			</div>
			<div class="navbar-text">
				<ul class="navbar-nav float-right">
					{#if $isAuthenticated}
						<li class="nav-item">
							<a class="nav-link" href="/#" on:click="{logout}">Log Out</a>
						</li>
					{:else}
						<li class="nav-item">
							<a class="nav-link" href="/#" on:click="{login}">Log In</a>
						</li>
					{/if}
				</ul>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col">
				<Catalog />
			</div>
			{#if $isAuthenticated}
			<div class="col-3 border-left">
				<Cart />
			</div>
			{/if}
		</div>
	</div>
</main>

<style>
</style>