package com.dove.webservice.soap.client;

import java.io.IOException;

import javax.activation.DataHandler;
import javax.jws.WebService;

import com.dove.convertion.FileUploader;
import com.dove.webservice.soap.ConvertionService;

@WebService(name="convert", serviceName="convert")
public class SoapConvertionClient extends SoapClient<ConvertionService> implements ConvertionService {

	public SoapConvertionClient(String endpoint) throws IOException {
		super(endpoint, ConvertionService.class, -1, true, -1);
	}

	public DataHandler convert(FileUploader Dfile) throws Exception {
		return client.convert(Dfile);
	}
}
