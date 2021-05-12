import { writable } from "svelte/store";

export const error = writable();
export const products = writable([]);
export const cart = writable();

export const auth0Client = writable({});

export const isAuthenticated = writable(false);
export const user = writable({});
export const popupOpen = writable(false);
