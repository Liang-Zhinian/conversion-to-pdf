package com.dove.webservice.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService (name = "SimpleService", serviceName="SimpleService", targetNamespace = "http://ws.atpath.com")
public interface SimpleSoapWeb {
 
    @WebMethod
    public String sayHello(String name);
}
