package com.dove.webservice.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
 
@WebService (name = "Greeting", serviceName="Greeting", targetNamespace = "http://ws.atpath.com")
public interface GreetingService {
 
    @WebMethod
    public String sayHello(String name);
}
