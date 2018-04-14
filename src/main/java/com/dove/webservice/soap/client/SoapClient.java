package com.dove.webservice.soap.client;


import javax.net.ssl.TrustManager;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.common.gzip.GZIPInInterceptor;
import org.apache.cxf.transport.common.gzip.GZIPOutInterceptor;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;


public abstract class SoapClient<T> {
	protected T client;
	protected String endpoint;
	
	public SoapClient(String endpoint, Class<T> serviceClass, int gzipThreshold, boolean log, int timeout) {
		this.endpoint = endpoint;
		initClient(serviceClass, gzipThreshold, log);

		if (endpoint.toLowerCase().startsWith("https"))
			configureSSL();

		configureTimeout(timeout);
	}

	@SuppressWarnings("unchecked")
	protected void initClient(Class<T> serviceClass, int gzipThreshold, boolean log) {
		// Needed to get rig of CXF exception
		// "Cannot create a secure XMLInputFactory"
		System.setProperty("org.apache.cxf.stax.allowInsecureParser", "true");

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		if (log) {
			factory.getInInterceptors().add(new LoggingInInterceptor());
			factory.getOutInterceptors().add(new LoggingOutInterceptor());
		}

		if (gzipThreshold >= 0) {
			factory.getInInterceptors().add(new GZIPInInterceptor());
			factory.getOutInterceptors().add(new GZIPOutInterceptor(gzipThreshold));
		}

		factory.setServiceClass(serviceClass);
		factory.setAddress(endpoint);
		client = (T) factory.create();
	}

	protected void configureSSL() {
//		TLSClientParameters tlsParams = new TLSClientParameters();
//		tlsParams.setDisableCNCheck(true);
//		tlsParams.setTrustManagers(new TrustManager[] { new EasyX509TrustManager() });
//
//		org.apache.cxf.endpoint.Client cl = ClientProxy.getClient(client);
//		HTTPConduit httpConduit = (HTTPConduit) cl.getConduit();
//		httpConduit.setTlsClientParameters(tlsParams);
	}

	protected void configureTimeout(int timeout) {
		if (timeout <= 0)
			return;

		HTTPClientPolicy policy = new HTTPClientPolicy();
		policy.setConnectionTimeout(timeout * 1000);
		policy.setReceiveTimeout(timeout * 1000);

		org.apache.cxf.endpoint.Client cl = ClientProxy.getClient(client);
		HTTPConduit httpConduit = (HTTPConduit) cl.getConduit();
		httpConduit.setClient(policy);
	}
	
}
