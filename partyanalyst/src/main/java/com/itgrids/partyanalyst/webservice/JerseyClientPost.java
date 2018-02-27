package com.itgrids.partyanalyst.webservice;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClientPost {

	public static void main(String[] args)
	{
		JerseyClientPost jerseyClientPost = new JerseyClientPost();
		
		jerseyClientPost.callWebService("{\"versionNo\":\"WARD-77\",\"wardId\":\"31880\",\"firstResult\":\"0\",\"maxResults\":\"10000\"}");
		jerseyClientPost.callWebService("{\"versionNo\":\"WARD-81\",\"wardId\":\"31884\",\"firstResult\":\"0\",\"maxResults\":\"10000\"}");
		jerseyClientPost.callWebService("{\"versionNo\":\"WARD-86\",\"wardId\":\"31889\",\"firstResult\":\"0\",\"maxResults\":\"10000\"}");
		jerseyClientPost.callWebService("{\"versionNo\":\"WARD-127\",\"wardId\":\"31930\",\"firstResult\":\"0\",\"maxResults\":\"10000\"}");

	}
	
	public void callWebService(String input)
	{
		try{
			Client client = Client.create();
			
			WebResource webResource = client.resource("http://localhost:8080/PartyAnalyst/WebService/sendSmsForCadre/");
			
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

					if (response.getStatus() != 201) {
						throw new RuntimeException("Failed : HTTP error code : "
						     + response.getStatus());
					}

					System.out.println("Output from Server .... \n");
					String output = response.getEntity(String.class);
					System.out.println(output);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
