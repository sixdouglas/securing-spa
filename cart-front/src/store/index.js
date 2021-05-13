import { writable, derived } from "svelte/store";

export const error = writable();
export const products = writable([]);
export const cart = writable();
