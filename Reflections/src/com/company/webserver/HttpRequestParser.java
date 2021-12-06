package com.company.webserver;

import java.util.ArrayList;
import java.util.HashMap;

public class HttpRequestParser {
    // Http Method
    private HttpMethods method;
    //Query Parameters
    private HashMap<String, String> params;
    //Headers
    private HashMap<String, String> headers;

    public HttpRequestParser(HttpMethods method, HashMap<String, String> params, HashMap<String, String> headers) {
        this.method = method;
        this.params = params;
        this.headers = headers;
    }




}







