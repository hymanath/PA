package com.itgrids.partyanalyst;

import com.itgrids.partyanalyst.dto.CadrePrintInputVO;
import com.itgrids.partyanalyst.dto.CadreTravelsVO;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class JerseyClientGet {
	public static void main(String[] args) {
		try {

			ClientConfig clientConfig = new DefaultClientConfig();

	         clientConfig.getFeatures().put(
	                  JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	         Client client = Client.create(clientConfig);
	         
	         CadrePrintInputVO cp = new CadrePrintInputVO();
	         //cp.setConstituecyNo(164l);
	         cp.setConstituecyNo(1l);

			//String input = panchayatId.toString();
			WebResource webResource = client
					.resource("http://localhost:8080/PartyAnalyst/WebService/getTDPCadreDetailsBySearch");
			
			ClientResponse response = webResource.accept("application/json")
	                  .type("application/json").post(ClientResponse.class, cp);

			
			

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			String output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	
	
	/*public static void main(String[] args) {
		try {

			ClientConfig clientConfig = new DefaultClientConfig();

	         clientConfig.getFeatures().put(
	                  JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	         Client client = Client.create(clientConfig);
	         
	         CadreTravelsVO cp = new CadreTravelsVO();
	         cp.setMembershipNo("AP1424180867");
	         cp.setCustomerId("5");
	         cp.setDateOfJourney("2015-01-06 19:30:00");
	         cp.setTicketsCount("6");
	         cp.setTicketCost("700");
	         cp.setDiscountPerc("10.506");
			
			WebResource webResource = client.resource("http://localhost:8080/PartyAnalyst/WebService/updateCadreTravelDiscountDetails");
			
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, cp);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			String output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}*/
}
