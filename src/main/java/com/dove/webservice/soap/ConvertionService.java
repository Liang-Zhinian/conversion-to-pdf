package com.dove.webservice.soap;

import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.dove.convertion.FileUploader;


@WebService (name = "Convert", serviceName="Convert", targetNamespace = "http://ws.atpath.com")
public interface ConvertionService {

	@WebMethod
	public DataHandler convert(@WebParam(name="Dfile") FileUploader Dfile) throws Exception;
}
