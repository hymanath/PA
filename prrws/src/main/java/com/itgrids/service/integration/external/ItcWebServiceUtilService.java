package com.itgrids.service.integration.external;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

@Service
public class ItcWebServiceUtilService {

	private static Logger LOG = Logger.getLogger(ItcWebServiceUtilService.class);

	public ClientResponse callWebService(String url, Object input) {
		ClientResponse response = null;
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
			client.setConnectTimeout(1000 * 60 * 2);
			client.setReadTimeout(1000 * 60 * 3);
			WebResource resource = client.resource(url);
			response = resource.accept(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED_TYPE).type(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, input);
		} catch (Exception e) {
			LOG.error("Exception Occured in calling Webservice, URL - " + url+ " Input - " + input + " Exception - ", e);
		}
		return response;
	}
}
