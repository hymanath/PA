package com.itgrids.service.impl;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.service.IRuralWaterSupplyDashBoardService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

@Service
@Transactional
public class RuralWaterSupplyDashBoardService implements IRuralWaterSupplyDashBoardService{
	private static final Logger LOG = Logger.getLogger(RuralWaterSupplyDashBoardService.class);
	
	
	public void getHabitationCoverageByStatusByLocationType(){
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	        Client client = Client.create(clientConfig);
	         
	        WebResource webResource = client.resource("http://DomainName/Rwss/cd/getHabitationCoverageByStatusByLocationType");
	        
	        /*String jsonInString = new ObjectMapper().writeValueAsString(vo);
	        System.out.println(jsonInString);*/
	        
	        /*ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, vo);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	//output = "[{"locationName":"Andhra Pradesh","streedHabitationCount":25412,"totalCount":48363,"statusList":[{"status":"NSS","count":479,"percentage":0.99},{"status":"PC1","count":3061,"percentage":6.33},{"status":"PC2","count":5855,"percentage":12.11},{"status":"PC3","count":7002,"percentage":14.48},{"status":"PC4","count":8157,"percentage":16.87},{"status":"FC","count":23480,"percentage":48.55},{"status":"NC","count":329,"percentage":0.68}]}]";
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				create vo here
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				
	 	    				

	 	    			}
	 	    		}
	 	    	}
	 	    	 
	 	    	  
	 	      }*/
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getHabitationCoverageByStatusByLocationType - RuralWaterSupplyDashBoardService service", e);
		}
	}
}
