package com.itgrids.cadrecardprinting;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class JerseyClientGet {

	/*public static void main(String[] args) {
		try {

			Client client = Client.create();

			WebResource webResource = client
					.resource("http://localhost:8080/PartyAnalyst/WebService/getVCadreDataByPanchayatId/3297");

			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);

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
	
	public static Object getCadredetailsByPanchayat(Long panchayatId,String type)
	{
		Object obj = null;
		try {

			Client client = Client.create();

			String input = panchayatId.toString();
			WebResource webResource = client
					.resource("http://mytdp.com/WebService/getVCadreDataByPanchayatId1/"+input+ "/"+type);

			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			obj = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(obj);

			
		} catch (Exception e) {

			e.printStackTrace();

		}
		return obj;
	}
	
	public static Object getCadredetailsBySelection(CadrePrintInputVO inputVO)
	{
		Object obj = null;
		try {

			//Client client = Client.create();
			
			ClientConfig clientConfig = new DefaultClientConfig();

	         clientConfig.getFeatures().put(
	                  JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	         Client client = Client.create(clientConfig);

			//String input = panchayatId.toString();
			WebResource webResource = client
					.resource("http://localhost:8080/PartyAnalyst/WebService/getVCadreDataBySelection");
			
			ClientResponse response = webResource.accept("application/json")
	                  .type("application/json").post(ClientResponse.class, inputVO);

			/*ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);*/

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			obj = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(obj);

			
		} catch (Exception e) {

			e.printStackTrace();

		}
		return obj;
	}

	
	public static String tagCardIdForNFCReader(String cardNo,Long voterId)
	{
		String status = null;
		try {

			Client client = Client.create();

			WebResource webResource = client
					.resource("http://mytdp.com/WebService/tagCardIdForNFCReader/"+cardNo+ "/"+voterId);

			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			status = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(status);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return status;
	}
	
	public static String getCadreDetailsForPrinting(String memberShipNo)
	{
		String status = null;
		try {

			Client client = Client.create();

			WebResource webResource = client
					.resource("http://192.168.11.94:8080/PartyAnalyst/WebService/getCadreDetailsForPrinting/"+memberShipNo);

			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			status = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(status);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return status;
	}
	
	public static String checkNFCNumberForVoterId(Long voterId)
	{
		String status = null;
		try {

			Client client = Client.create();

			WebResource webResource = client
					.resource("http://mytdp.com/WebService/checkNFCNumberForVoterId/"+voterId);

			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			status = response.getEntity(String.class);

			//System.out.println(status);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return status;
	}
	
	public static String delinkNFCNumber(String cardNo,Long voterId)
	{
		String status = null;
		try {

			Client client = Client.create();

			WebResource webResource = client
					.resource("http://mytdp.com/WebService/delinkNFCNumber/"+cardNo+ "/"+voterId);

			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			status = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(status);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return status;
	}
	
	
	
}