package com.itgrids.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dto.BasicVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.dto.StatusVO;
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
	
	
	public List<LocationVO> getHabitationCoverageByStatusByLocationType(InputVO inputVO){
		List<LocationVO> voList = new ArrayList<LocationVO>(0);
		try {
			/*ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	        Client client = Client.create(clientConfig);
	         
	        WebResource webResource = client.resource("http://DomainName/Rwss/cd/getHabitationCoverageByStatusByLocationType");
	        
	        String jsonInString = new ObjectMapper().writeValueAsString(inputVO);
	        System.out.println(jsonInString);
	        
	        ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{*/
	 	    	 String output = null;//response.getEntity(String.class);
	 	    	 output = "[{\"locationName\":\"Andhra Pradesh\",\"streedHabitationCount\":25412,\"totalCount\":48363,"
	 	    	 		+ "\"statusList\":[{\"status\":\"NSS\",\"count\":479,\"percentage\":0.99},"
	 	    	 		+ "{\"status\":\"PC1\",\"count\":3061,\"percentage\":6.33},"
	 	    	 		+ "{\"status\":\"PC2\",\"count\":5855,\"percentage\":12.11},"
	 	    	 		+ "{\"status\":\"PC3\",\"count\":7002,\"percentage\":14.48},"
	 	    	 		+ "{\"status\":\"PC4\",\"count\":8157,\"percentage\":16.87},"
	 	    	 		+ "{\"status\":\"FC\",\"count\":23480,\"percentage\":48.55},"
	 	    	 		+ "{\"status\":\"NC\",\"count\":329,\"percentage\":0.68}]}]";
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				LocationVO vo = new LocationVO();
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				vo.setLocationName(jObj.getString("locationName"));
	 	    				vo.setStreetHabitationCount(jObj.getLong("streedHabitationCount"));
	 	    				vo.setTotalCount(jObj.getLong("totalCount"));
	 	    				
	 	    				JSONArray statusListArray = jObj.getJSONArray("statusList");
	 	    				
	 	    				if(statusListArray != null && statusListArray.length() > 0){
	 	    					for (int j = 0; j < statusListArray.length(); j++) {
									StatusVO statusVO = new StatusVO();
									
									JSONObject jobj1 = (JSONObject) statusListArray.get(i);
									statusVO.setStatus(jobj1.getString("status"));
									statusVO.setCount(jobj1.getLong("count"));
									statusVO.setPercentage(jobj1.getDouble("percentage"));
									vo.getStatusList().add(statusVO);
								}
	 	    				}
	 	    				voList.add(vo);
	 	    			//}
	 	    		}
	 	    	}
	 	    	 
	 	    	  
	 	      }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getHabitationCoverageByStatusByLocationType - RuralWaterSupplyDashBoardService service", e);
		}
		
		return voList;
	}
	
	public BasicVO getLabTestDetails(InputVO inputVO){
		BasicVO basicVO = null;
		try {
			/*ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	        Client client = Client.create(clientConfig);
	         
	        WebResource webResource = client.resource("http://DomainName/Rwss/cd/getHabitationCoverageByStatusByLocationType");
	        
	        String jsonInString = new ObjectMapper().writeValueAsString(inputVO);
	        System.out.println(jsonInString);
	        
	        ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	    }else{*/
	 	    	 String output = null;//response.getEntity(String.class);
	 	    	 output = "{\"physicalTestCount\":136657,\"bacterialTestCount\":27622}";
	 	    	 
	 	    	 if(output != null && !output.isEmpty()){
	 	    		JSONObject jObj = new JSONObject(output);
	 	    		 basicVO = new BasicVO();
	 	    		basicVO.setPhysicalTestCount(jObj.getLong("physicalTestCount"));
	 	    		basicVO.setBacterialTestCount(jObj.getLong("bacterialTestCount"));
	 	    		 
	 	    	 }
			//}
		} catch (Exception e) {
			LOG.error("Exception raised at getLabTestDetails - RuralWaterSupplyDashBoardService service", e);
		}
		return basicVO;
	}
}
