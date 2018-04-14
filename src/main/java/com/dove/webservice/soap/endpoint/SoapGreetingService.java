package com.dove.webservice.soap.endpoint;


import com.dove.webservice.soap.GreetingService;
 
public class SoapGreetingService implements GreetingService {
 
    public String sayHello(String name) {
        String result = "Hello " + name;
        return result;
    }
}
