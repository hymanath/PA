package com.itgrids.partyanalyst.utils;
import java.io.BufferedReader;




import java.io.File;
import java.io.FileReader;

import javax.xml.ws.WebServiceRef;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
public class JerseyClientPost {
 public static void main(String[] args) {

		 try {
			 
				Client client = Client.create();
				
				
				WebResource webResource = client
				   .resource("http://localhost:8080/PartyAnalyst/WebService/android/fieldData/genarateOTPForCadreTxn");
			    String input = "{\"constituencyId\":\"282\",\"sinkedRecords\":\"10\",\"pendingRecords\":\"10\",\"totalAmount\":\"100\",\"paidAmount\":\"80\",\"pendingAmount\":\"20\"" +
		 		",\"surveyTime\":\"2014-10-29\",\"id\":\"1\",\"userId\":\"1\",\"mobileNo\":\"9032411640\",\"uniqueKey\":\"123456\"}";
				
		 
				/*ClientResponse response = webResource.accept("application/json")
		                   .get(ClientResponse.class);*/
			    ClientResponse response = webResource.type("application/json")
						   .post(ClientResponse.class, input);
				if (response.getStatus() != 201) {
					throw new RuntimeException("Failed : HTTP error code : "
					     + response.getStatus());
				}
		 
				System.out.println("Output from Server .... \n");
				String output = response.getEntity(String.class);
				System.out.println(output);
			
		 
			  } catch (Exception e) {
		 
				e.printStackTrace();
		 
			  }
		 
			}
	
		}
