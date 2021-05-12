import createAuth0Client from "@auth0/auth0-spa-js";
import {isAuthenticated, popupOpen, user} from "../store";
import config from "./authConfig";

function createClient() {
    return createAuth0Client({
        domain: config.domain,
        client_id: config.clientId,
        audience: config.audience
    });
}

async function loginWithPopup(client, options) {
    popupOpen.set(true);
    try {
        await client.loginWithPopup(options);

        user.set(await client.getUser());
        isAuthenticated.set(true);
    } catch (e) {
        // eslint-disable-next-line
        console.error(e);
    } finally {
        popupOpen.set(false);
    }
}

async function getToken(client, options) {
    try {
        return await client.getTokenSilently(options);
    } catch (e) {
        // eslint-disable-next-line
        console.error(e);
    }
}

function logout(client) {
    client.logout();
    user.set({});
    isAuthenticated.set(false);
}

const auth = {
    createClient,
    loginWithPopup,
    getToken,
    logout
};

export default auth;