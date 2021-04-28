import axios from "axios";

// Create a instance of axios to use the same base url.
const axiosAPI = axios.create({
    baseURL : "http://localhost:8080/api/v1" // it's not recommended to have this info here.
});

function callApi(method, url, request, headers) {
    //using the axios instance to perform the request that received from each http method
    return axiosAPI({
        method,
        url,
        data: request,
        headers
    }).then(res => {
        return Promise.resolve(res.data);
    }).catch(err => {
        return Promise.reject(err);
    });
}

function apiRequest(method, url, request) {
    console.info("call API: '" + url + "' in '" + method + "', with params: {" + request + "}");
    let headers = {};
    return callApi(method, url, request, headers);
}

// function to execute the http get request
const getRequest = (url, request) => apiRequest("get", url, request);

// function to execute the http delete request
const deleteRequest = (url, request) =>  apiRequest("delete", url, request);

// function to execute the http post request
const postRequest = (url, request) => apiRequest("post", url, request);

// function to execute the http put request
const putRequest = (url, request) => apiRequest("put", url, request);

// function to execute the http path request
const patchRequest = (url, request) =>  apiRequest("patch", url, request);

// expose your method to other services or actions
const API ={
    get: getRequest,
    delete: deleteRequest,
    post: postRequest,
    put: putRequest,
    patch: patchRequest
};
export default API;