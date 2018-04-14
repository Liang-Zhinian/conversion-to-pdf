package com.dove.webservice.soap;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.dove.convertion.FileUploader;

@WebService(name = "UploadService", serviceName = "UploadService", targetNamespace = "http://ws.atpath.com")
public interface UploadSEI {

	void uploadFile(@WebParam(name = "Dfile") FileUploader Dfile);
}
