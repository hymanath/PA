package com.itgrids.partyanalyst;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.itgrids.partyanalyst.dto.CadrePrintInputVO;
import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.dto.WebServiceResultVO;
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

	         WebServiceResultVO webserviceResultVO = new WebServiceResultVO();
			//String input = panchayatId.toString();
	         WebResource webResource = client.resource("http://mytdp.com/CommunityNewsPortal/webservice/getCandidateAndLocationSummary/15-07-2015/15-07-2015/district/13/44");
				
				ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

				if (response.getStatus() != 200) {
				/*	throw new RuntimeException("Failed : HTTP error code : "
							+ response.getStatus());*/
					webserviceResultVO=null;
				}

				String output = response.getEntity(String.class);
				JSONObject temp = new JSONObject(output);
				
				List<String> deptsList = new ArrayList<String>();
				
				//ITERATE DEPT SUMMARY
				org.json.JSONArray deptSummary = temp.getJSONArray("departmentSummary");
				//Integer totalCount = temp2.getInt("totalCount");
				JSONObject jObjFirst= (JSONObject)deptSummary.get(0);
				int totalCount = jObjFirst.getInt("totalCount");
				for (int i = 0; i < deptSummary.length(); i++){
					JSONObject jObj= (JSONObject)deptSummary.get(i);
					String partyName = jObj.getString("partyName");
					int count =jObj.getInt("count");
					deptsList.add(partyName);
					webserviceResultVO.setName(partyName);
				}
				
				
				//ITERATE CANDIDATE SUMMARY
				JSONObject temp2 = temp.getJSONObject("candidateSummary");
				int candiSummaryTtlCnt = temp2.getInt("totalCount");
				int candiSummaryPosCnt = temp2.getInt("positiveCount");
				int candiSummaryNegCnt = temp2.getInt("negativeCount");
				int candiSummarySmryCnt = temp2.getInt("neutralCount");
				
				
							

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			
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
