package com.dove.webservice.soap.endpoint;

import com.dove.webservice.soap.SimpleSoapWeb;
 
public class SimpleSoapWebImpl implements SimpleSoapWeb {
 
    public String sayHello(String name) {
        String result = "Hello " + name;
        return result;
    }
}
