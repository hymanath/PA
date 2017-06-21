package com.itgrids.tpi.rws.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dto.BasicVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.dto.RangeVO;
import com.itgrids.dto.StatusVO;
import com.itgrids.tpi.rws.service.IRWSNICService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import sun.misc.BASE64Encoder;

@Service
@Transactional
public class RWSNICService implements IRWSNICService{
	private static final Logger LOG = Logger.getLogger(RWSNICService.class);
	
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	/*
	 * Date : 15/06/2017
	 * Author :Sandeep
	 * @description : getHabitationCoverageByStatusByLocationType
	 */
	public List<LocationVO> getHabitationCoverageByStatusByLocationType(InputVO inputVO){
		List<LocationVO> voList = new ArrayList<LocationVO>(0);
		try {
			 
	        WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://rwss.ap.nic.in/rwscore/cd/getHabitationCoverageByStatusByLocationType");
	        
	        /*String jsonInString = new ObjectMapper().writeValueAsString(inputVO);
	        System.out.println(jsonInString);*/
	        
	        String name = "admin";
	        String password = "admin@123";
	        String authString = name + ":" + password;
	        String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
	        
	        ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);//null;
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		if(inputVO.getLocationType() != null && inputVO.getLocationType().equalsIgnoreCase("mandal")){
	 	    			//build mandal level data
	 	    		}else{
	 	    			JSONArray finalArray = new JSONArray(output);
		 	    		if(finalArray!=null && finalArray.length()>0){
		 	    			for(int i=0;i<finalArray.length();i++){
		 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
		 	    				if(jObj.length() > 0){
		 	    					LocationVO vo = new LocationVO();
		 	    					vo.setLocationName(jObj.getString("locationName"));
			 	    				vo.setStreetHabitationCount(jObj.getLong("stressedHabitationCount"));
			 	    				vo.setTotalCount(jObj.getLong("totalCount"));
			 	    				
			 	    				JSONArray statusListArray = jObj.getJSONArray("statusList");
			 	    				
			 	    				if(statusListArray != null && statusListArray.length() > 0){
			 	    					for (int j = 0; j < statusListArray.length(); j++) {
											StatusVO statusVO = new StatusVO();
											
											JSONObject jobj1 = (JSONObject) statusListArray.get(j);
											statusVO.setStatus(jobj1.getString("status"));
											statusVO.setCount(jobj1.getLong("count"));
											statusVO.setPercentage(jobj1.getDouble("percentage"));
											vo.getStatusList().add(statusVO);
										}
			 	    				}
			 	    				voList.add(vo);
		 	    				}
		 	    			}
		 	    		}
	 	    		}
	 	    	}
	 	      }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getHabitationCoverageByStatusByLocationType - RuralWaterSupplyDashBoardService service", e);
		}
		
		return voList;
	}
	/*
	 * Date : 15/06/2017
	 * Author :Sandeep
	 * @description : getLabTestDetails
	 */
	public BasicVO getLabTestDetails(InputVO inputVO){
		BasicVO basicVO = null;
		try {
			/*
	        WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://DomainName/Rwss/cd/getHabitationCoverageByStatusByLocationType");
	        
	        String jsonInString = new ObjectMapper().writeValueAsString(inputVO);
	        System.out.println(jsonInString);
	        
	        ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	    }else{*/
	 	    	 String output = null;//response.getEntity(String.class);
	 	    	 output = "{\"physicalTestCount\":13665,\"bacterialTestCount\":27622}";
	 	    	 
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
	
	/*
	 * Date : 15/06/2017
	 * Author :Balu
	 * @description : getHabitationSupplyDetails
	 */
	public BasicVO getHabitationSupplyDetails(InputVO VO){
		BasicVO finalVO = new BasicVO();
		try {
			
			/*
	        WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://DomainName/Rwss/cd/gethabitationsupplyDetails");

	        String jsonInString = new ObjectMapper().writeValueAsString(VO);
	        
	        ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, VO);
			
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);*/
	 	    	 
	 	    	String output ="{'safeMLD':2.94,'unsafeMLD':0.12}";
	 	    	 
	 	    	 if(output != null && !output.isEmpty()){
	 	    		JSONObject jObj = new JSONObject(output);	 	    		
	 	    				finalVO.setSafeMLD(new BigDecimal(jObj.getString("safeMLD")));
	 	    				finalVO.setUnsafeMLD(new BigDecimal(jObj.getString("unsafeMLD")));
	 	    			}
	 	    	//}
	 	    	
		} catch (Exception e) {
			LOG.error("Exception raised at getHabitationSupplyDetails - RuralWaterSupplyDashBoardService service", e);
		}
		return finalVO;
	}
	
	/*
	 * Date : 15/06/2017
	 * Author :Balu
	 * @description : getSchemesDetails
	 */
	public List<BasicVO> getSchemesDetails(InputVO VO){
		List<BasicVO> finalList = new ArrayList<BasicVO>();
		try {
			
			/*
	        WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://DomainName/Rwss/cd/getSchemesDetails");

	        String jsonInString = new ObjectMapper().writeValueAsString(VO);
	        
	        ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, VO);
			
	        
	       if(response.getStatus() != 200){
	 	      throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);*/
	 	    	 
	 	    	String output = "[{'assetType':'PWS','count':1583},{'assetType':'CPWS','count':8},{'assetType':'SCHOOLS','count':3},{'assetType':'SUSTAINABILITY','count':26}]";
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray jsonArray = new JSONArray(output);	 	    
		 	    		if(jsonArray !=null && jsonArray.length()>0){
		 	    			for (int i = 0;i<jsonArray.length();i++) {		 	    				
		 	    				JSONObject jObj = (JSONObject)jsonArray.get(i);		 	    				
		 	    				BasicVO Vo = new BasicVO();		 	    				
		 	    				Vo.setAssetType(jObj.getString("assetType"));
		 	    				Vo.setCount(jObj.getLong("count"));
		 	    				
		 	    				finalList.add(Vo);
							}
		 	    		}	 	    			
	 	    	//}
			
	 	      }
		} catch (Exception e) {
			LOG.error("Exception raised at getSchemesDetails - RuralWaterSupplyDashBoardService service", e);
		}
		return finalList;
	}
	
	/*
	 * Date : 15/06/2017
	 * Author :Balu
	 * @description : getSchemeWiseWorkDetails
	 */
	public List<BasicVO> getSchemeWiseWorkDetails(InputVO VO){
		List<BasicVO> finalList = new ArrayList<BasicVO>();
		try {
			
			/*
	        WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://DomainName/Rwss/cd/getSchemeWiseWorkDetails");

	        String jsonInString = new ObjectMapper().writeValueAsString(VO);
	        
	        ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, VO);
			
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	    }else{
	 	    	 String output = response.getEntity(String.class);*/
	 	    	 
	 	    	 
			String output = "[{'assetType':'SUSTAINABILITY','workOngoingCount':10,'workComissionedCount':35,'workCompletedCount':46,'workNotGroundedCount':0},{'assetType':'SCHOOLS','workOngoingCount':6,'workComissionedCount':12,'workCompletedCount':17,'workNotGroundedCount':0},{'assetType':'PWS','workOngoingCount':162,'workComissionedCount':979,'workCompletedCount':1310,'workNotGroundedCount':0},{'assetType':'CPWS','workOngoingCount':25,'workComissionedCount':42,'workCompletedCount':51,'workNotGroundedCount':0}]";
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		
	 	    		JSONArray jsonArray = new JSONArray(output);	 	    
	 	    		if(jsonArray !=null && jsonArray.length()>0){
	 	    			for (int i = 0;i<jsonArray.length();i++) {		 	    				
	 	    				JSONObject jObj = (JSONObject)jsonArray.get(i);		 	    				
	 	    				BasicVO Vo = new BasicVO();		 	    				
	 	    				Vo.setAssetType(jObj.getString("assetType"));
	 	    				Vo.setWorkOngoingCount(jObj.getLong("workOngoingCount"));
	 	    				Vo.setWorkComissionedCount(jObj.getLong("workComissionedCount"));
	 	    				Vo.setWorkCompletedCount(jObj.getLong("workCompletedCount"));
	 	    				Vo.setWorkNotGroundedCount(jObj.getLong("workNotGroundedCount"));
	 	    				
	 	    				
	 	    				finalList.add(Vo);
						}
	 	    		//}
	 	    		
	 	    	}
	 	    }
			
		} catch (Exception e) {
			LOG.error("Exception raised at getSchemeWiseWorkDetails - RuralWaterSupplyDashBoardService service", e);
		}
		
		return finalList;
	}
	/*
	 * Date : 15/06/2017
	 * Author :Nagarjuna
	 * @description : getAssetsInfo
	 */
	public List<BasicVO> getAssetsInfo(InputVO vo) {
		List<BasicVO> assetsList = new ArrayList<BasicVO>(0);
		try {
		
			/*WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://DomainName/Rwss/cd/getAssetsinfo");
        
        	String jsonInString = new ObjectMapper().writeValueAsString(inputVO);
        	System.out.println(jsonInString);
        
        	ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
        
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{*/
				String output = null;//response.getEntity(String.class);
				output="[{'assetType':'DIRECT PUMPING','count':155},"
							+ "{'assetType':'OPEN WELLS','count':3},"
						    + "{'assetType':'SHALLOW HAND PUMPS','count':5},"
							+ "{'assetType':'HANDPUMPS','count':778},"
							+ "{'assetType':'PWS','count':164},"
					        + "{'assetType':'OTHERS','count':24},"
					        + "{'assetType':'MPWS','count':166},"
					        + "{'assetType':'CPWS','count':17}]";
 	    	if(output != null && !output.isEmpty()){
 	    		JSONArray finalArray = new JSONArray(output);
 	    		if(finalArray!=null && finalArray.length()>0){
 	    			for(int i=0;i<finalArray.length();i++){
 	    				BasicVO basicVO = new BasicVO();
 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
 	    				basicVO.setAssetType(jObj.getString("assetType"));
 	    				basicVO.setCount(jObj.getLong("count"));
 	    				assetsList.add(basicVO);
 	    			//}
 	    		}
 	    	}
 	    	 
 	    	  
 	      }
        
		} catch (Exception e) {
			LOG.error("Exception raised at getAssetsInfo - RuralWaterSupplyDashBoardService service", e);
		}
		
		return assetsList;

	}
	
	public List<StatusVO> getAlertDetailsOfCategoryByStatusWise(InputVO inputVO){
		List<StatusVO> voList = new ArrayList<StatusVO>(0);
		try {
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://192.168.11.143:8080/PartyAnalyst/WebService/getAlertDetailsOfCategoryByStatusWise");
	        
        	ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
        
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				
				if(output != null && !output.isEmpty()){
					JSONArray arr = new JSONArray(output);
					
					if(arr != null && arr.length() > 0){
						for (int i = 0; i < arr.length(); i++) {
							//id,name,color,count
							JSONObject jobj = (JSONObject)arr.get(i);
							StatusVO vo = new StatusVO();
							vo.setId(jobj.getLong("id"));
							vo.setName(jobj.getString("name"));
							vo.setColor(jobj.getString("color"));
							vo.setCount(jobj.getLong("count"));
							voList.add(vo);
						}
					}
				}
 	      	}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised at getAlertDetailsOfCategoryByStatusWise - RWSNICService service", e);
		}
		return voList;
	}
	
	public List<StatusVO> getAlertFeedbackStatusDetails(InputVO inputVO){
		List<StatusVO> voList = new ArrayList<StatusVO>(0);
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://192.168.11.143:8080/PartyAnalyst/WebService/getAlertFeedbackStatusDetails");
	        
        	ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);

        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				
				if(output != null && !output.isEmpty()){
					JSONArray arr = new JSONArray(output);
					
					if(arr != null && arr.length() > 0){
						for (int i = 0; i < arr.length(); i++) {
							//id,name,color,count
							JSONObject jobj = (JSONObject)arr.get(i);
							StatusVO vo = new StatusVO();
							vo.setId(jobj.getLong("id"));
							vo.setName(jobj.getString("name"));
							vo.setCount(jobj.getLong("count"));
							voList.add(vo);
						}
					}
				}
 	      	}
		} catch (Exception e) {
			LOG.error("Exception raised at getAlertFeedbackStatusDetails - RWSNICService service", e);
		}
		return voList;
	}
	
	
	/*
	 * Date : 18/06/2017
	 * Author :Nagarjuna
	 * @description : getWaterSourceInfo
	 */
	public List<StatusVO> getWaterSourceInfo(InputVO vo) {
		List<StatusVO> waterSourceInfo = new ArrayList<StatusVO>(0);
		try{
			/*WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://DomainName/Rwss/cd/getWaterSourceInfo");
	        
        	String jsonInString = new ObjectMapper().writeValueAsString(inputVO);
        	System.out.println(jsonInString);
        
        	ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
        
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{*/
				String output = null;//response.getEntity(String.class);
				output="[{'source' : 'ground','value'  : '10000'},"
						 +"{'source' : 'surface','value'  : '10000'}]";
 	    	if(output != null && !output.isEmpty()){
 	    		JSONArray finalArray = new JSONArray(output);
 	    		if(finalArray!=null && finalArray.length()>0){
 	    			for(int i=0;i<finalArray.length();i++){
 	    				StatusVO statusVO = new StatusVO();
 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
 	    				statusVO.setName(jObj.getString("source"));
 	    				statusVO.setCount(jObj.getLong("value"));
 	    				waterSourceInfo.add(statusVO);
 	    			//}
 	    		}
 	    	}
 	    	 
 	    	  
 	      }
			
		}catch (Exception e) {
			LOG.error("Exception raised at getWaterSourceInfo - RWSNICService service", e);
		}
		
		
		return waterSourceInfo;
	}
	/*
	 * Date : 18/06/2017
	 * Author :R Nagarjuna Gowd
	 * @description : getKeyPerformanceIndicatorsInfo
	 */
	public List<LocationVO> getKeyPerformanceIndicatorsInfo(InputVO inputVO){
		List<LocationVO> voList = new ArrayList<LocationVO>(0);
		try {
			 
	        /*WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://DomainName/Rwss/cd/getKeyPerformanceIndicatorsInfo");
	        
	        String jsonInString = new ObjectMapper().writeValueAsString(inputVO);
	        System.out.println(jsonInString);
	        
	        ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{*/
	 	    	 String output = null;//response.getEntity(String.class);
	 	    	if(inputVO.getLocationType().equalsIgnoreCase("state"))
	 	    		output ="[{\"locationId\":\"1\",\"locationName\":\"Andhra Pradhesh\","
	 	    	             +"\"statusData\":[{\"key\" : \"Partially Covered\",\"target\"  : \"10000\",\"achieved\"  : \"8000\"},"
	 	    	             + "{\"key\" : \"Partially Covered\",\"target\"  : \"10000\",\"achieved\" : \"8000\"},"
	 	    	             + "{\"key\" : \"Community Treatment Plants\",\"target\"  : \"10000\",\"achieved\" : \"8000\"},"
	 	    	             + "{\"key\" : \"Chronic kidney Diseses\",\"target\"  : \"10000\",\"achieved\" : \"8000\"}]}]";
	 	    	else if(inputVO.getLocationType().equalsIgnoreCase("district"))
	 	    		output ="[{\"locationId\":\"1\",\"locationName\":\"Nellore\","
	 	    	             +"\"statusData\":[{\"key\" : \"Partially Covered\",\"target\"  : \"10000\",\"achieved\"  : \"8000\"},"
	 	    	             + "{\"key\" : \"Partially Covered\",\"target\"  : \"10000\",\"achieved\" : \"8000\"},"
	 	    	             + "{\"key\" : \"Community Treatment Plants\",\"target\"  : \"10000\",\"achieved\" : \"8000\"},"
	 	    	             + "{\"key\" : \"Chronic kidney Diseses\",\"target\"  : \"10000\",\"achieved\" : \"8000\"}]},"
	 	    	             + "{\"locationId\":\"1\",\"locationName\":\"Jagtial Dist\","
	 	    	             +"\"statusData\":[{\"key\" : \"Partially Covered\",\"target\"  : \"10000\",\"achieved\"  : \"8000\"},"
	 	    	             + "{\"key\" : \"Partially Covered\",\"target\"  : \"10000\",\"achieved\" : \"8000\"},"
	 	    	             + "{\"key\" : \"Community Treatment Plants\",\"target\"  : \"10000\",\"achieved\" : \"8000\"},"
	 	    	             + "{\"key\" : \"Chronic kidney Diseses\",\"target\"  : \"10000\",\"achieved\" : \"8000\"}]}]";
	 	    	else if(inputVO.getLocationType().equalsIgnoreCase("constituency"))
	 	    		output ="[{\"locationId\":\"1\",\"locationName\":\"Kavalai\","
	 	    	             +"\"statusData\":[{\"key\" : \"Partially Covered\",\"target\"  : \"10000\",\"achieved\"  : \"8000\"},"
	 	    	             + "{\"key\" : \"Partially Covered\",\"target\"  : \"10000\",\"achieved\" : \"8000\"},"
	 	    	             + "{\"key\" : \"Community Treatment Plants\",\"target\"  : \"10000\",\"achieved\" : \"8000\"},"
	 	    	             + "{\"key\" : \"Chronic kidney Diseses\",\"target\"  : \"10000\",\"achieved\" : \"8000\"}]},"
	 	    	             + "{\"locationId\":\"1\",\"locationName\":\"Jagtial Const\","
	 	    	             +"\"statusData\":[{\"key\" : \"Partially Covered\",\"target\"  : \"10000\",\"achieved\"  : \"8000\"},"
	 	    	             + "{\"key\" : \"Partially Covered\",\"target\"  : \"10000\",\"achieved\" : \"8000\"},"
	 	    	             + "{\"key\" : \"Community Treatment Plants\",\"target\"  : \"10000\",\"achieved\" : \"8000\"},"
	 	    	             + "{\"key\" : \"Chronic kidney Diseses\",\"target\"  : \"10000\",\"achieved\" : \"8000\"}]}]";
	 	    	else if(inputVO.getLocationType().equalsIgnoreCase("mandal"))
	 	    		output ="[{\"locationId\":\"1\",\"locationName\":\"Alluru\","
	 	    	             +"\"statusData\":[{\"key\" : \"Partially Covered\",\"target\"  : \"10000\",\"achieved\"  : \"8000\"},"
	 	    	             + "{\"key\" : \"Partially Covered\",\"target\"  : \"10000\",\"achieved\" : \"8000\"},"
	 	    	             + "{\"key\" : \"Community Treatment Plants\",\"target\"  : \"10000\",\"achieved\" : \"8000\"},"
	 	    	             + "{\"key\" : \"Chronic kidney Diseses\",\"target\"  : \"10000\",\"achieved\" : \"8000\"}]},"
	 	    	             + "{\"locationId\":\"1\",\"locationName\":\"Jagtial Mandal\","
	 	    	             +"\"statusData\":[{\"key\" : \"Partially Covered\",\"target\"  : \"10000\",\"achieved\"  : \"8000\"},"
	 	    	             + "{\"key\" : \"Partially Covered\",\"target\"  : \"10000\",\"achieved\" : \"8000\"},"
	 	    	             + "{\"key\" : \"Community Treatment Plants\",\"target\"  : \"10000\",\"achieved\" : \"8000\"},"
	 	    	             + "{\"key\" : \"Chronic kidney Diseses\",\"target\"  : \"10000\",\"achieved\" : \"8000\"}]}]";
	 	    	 
	 	    	 
	 	    	 	    		 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				LocationVO vo = new LocationVO();
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				vo.setLocationName(jObj.getString("locationName"));
	 	    				
	 	    				
	 	    				JSONArray statusListArray = jObj.getJSONArray("statusData");
	 	    				
	 	    				if(statusListArray != null && statusListArray.length() > 0){
	 	    					for (int j = 0; j < statusListArray.length(); j++) {
									StatusVO statusVO = new StatusVO();
									
									JSONObject jobj1 = (JSONObject) statusListArray.get(j);
									statusVO.setStatus(jobj1.getString("key"));
									statusVO.setTarget(jobj1.getLong("target"));
									statusVO.setAchived(jobj1.getLong("achieved"));
									vo.getStatusList().add(statusVO);
								}
	 	    				}
	 	    				voList.add(vo);
	 	    			//}
	 	    		}
	 	    	}
	 	    	 
	 	    	  
	 	      }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getKeyPerformanceIndicatorsInfo - RWSNICService service", e);
		}
		
		return voList;
	}
	
	/*
	 * Date : 18/06/2017
	 * Author :Nagarjuna
	 * @description : getStressedHabitationsInfoByLocationType
	 */
	public List<StatusVO> getStressedHabitationsInfoByLocationType(InputVO vo) {
		List<StatusVO> stressedHabitation = new ArrayList<StatusVO>(0);
		try{
			/*WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://DomainName/Rwss/cd/getStressedHabitationsInfoByLocationType");
	        
        	String jsonInString = new ObjectMapper().writeValueAsString(inputVO);
        	System.out.println(jsonInString);
        
        	ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
        
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{*/
				String output = null;//response.getEntity(String.class);
				output="[{'locationName' : 'Srikakulam','totalHubs'  : '1000','stressedHubs'  : '80','population' : '987654','budgetPreparedHubs' : '70','popInStressedHubs' : '8760'  },"
                      +" {'locationName' : 'Vizianagarm','totalHubs'  : '1000','stressedHubs'  : '80','population' : '987654','budgetPreparedHubs' : '70','popInStressedHubs' : '8760' }]";
 	    	if(output != null && !output.isEmpty()){
 	    		JSONArray finalArray = new JSONArray(output);
 	    		if(finalArray!=null && finalArray.length()>0){
 	    			for(int i=0;i<finalArray.length();i++){
 	    				StatusVO statusVO = new StatusVO();
 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
 	    				statusVO.setName(jObj.getString("locationName"));
 	    				statusVO.setCount(jObj.getLong("totalHubs"));
 	    				statusVO.setStressedCount(jObj.getLong("stressedHubs"));
 	    				statusVO.setPopulation(jObj.getLong("population"));
 	    				statusVO.setBudgetPreparedHubs(jObj.getLong("budgetPreparedHubs"));
 	    				statusVO.setPopInStressedHubs(jObj.getLong("popInStressedHubs"));
 	    				stressedHabitation.add(statusVO);
 	    			//}
 	    		}
 	    	}
 	    	 
 	    	  
 	      }
			
		}catch (Exception e) {
			LOG.error("Exception raised at getStressedHabitationsInfoByLocationType - RuralWaterSupplyDashBoardService service", e);
		}
		
		
		return stressedHabitation;
	}
	
	public StatusVO getPlanofActionForStressedHabitations(InputVO vo){
		StatusVO statusVO = null;
		try {
			/*WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://DomainName/Rwss/cd/getPlanofActionForStressedHabitations");
	        
	        String jsonInString = new ObjectMapper().writeValueAsString(inputVO);
	        System.out.println(jsonInString);
	        
	        ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{*/
	 	    	 String output = null;//response.getEntity(String.class);
	 	    	 output = "{\"target\":\"10000\",\"achieved\":\"9000\",\"population\":\"25920\"}";
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject jobj = new JSONObject(output);
	 	    		statusVO = new StatusVO();
	 	    		statusVO.setTarget(jobj.getLong("target"));
	 	    		statusVO.setAchived(jobj.getLong("achieved"));
	 	    		statusVO.setPopulation(jobj.getLong("population"));
	 	    	}

	 	      //}
		} catch (Exception e) {
			LOG.error("Exception raised at getPlanofActionForStressedHabitations - RWSNICService service", e);
		}
		return statusVO;
	}
	
	public List<LocationVO> getLocationWiseAlertStatusCounts(InputVO inputVO){
		List<LocationVO> voList = new ArrayList<LocationVO>(0);
		try {
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://192.168.11.143:8080/PartyAnalyst/WebService/getLocationWiseAlertStatusCounts");
	        
	        String jsonInString = new ObjectMapper().writeValueAsString(inputVO);
	        System.out.println(jsonInString);
	        
	        ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
				String output = response.getEntity(String.class);
				if(output != null && !output.isEmpty()){
					JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				LocationVO locationVO = new LocationVO();
	 	    				JSONObject jobj = (JSONObject)finalArray.get(i);
	 	    				
	 	    				locationVO.setLocationLevelId(jobj.getLong("scopeValue"));
	 	    				locationVO.setLocationId(jobj.getLong("id"));
	 	    				locationVO.setLocationName(jobj.getString("name"));
	 	    				
	 	    				JSONArray statusArr = jobj.getJSONArray("list");
	 	    				if(statusArr != null && statusArr.length() > 0){
	 	    					for (int j = 0; j < statusArr.length(); j++) {
									StatusVO statusVO = new StatusVO();
									JSONObject jobj1 = (JSONObject) statusArr.get(j);
									statusVO.setId(jobj1.getLong("id"));
									statusVO.setName(jobj1.getString("name"));
									statusVO.setCount(jobj1.getLong("count"));
									locationVO.getStatusList().add(statusVO);
								}
	 	    				}
	 	    				
	 	    				voList.add(locationVO);
	 	    			}
	 	    		}
	 	    	}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationWiseAlertStatusCounts - RWSNICService service", e);
		}
		return voList;
	}
	
	public List<RangeVO> getLocationBasedOnSelection(InputVO inputVO){
		List<RangeVO> rangeVOList = new ArrayList<RangeVO>(0);
		try {
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://rwss.ap.nic.in/rwscore/cd/getLocationBasedOnSelection");
	        
	       /* String jsonInString = new ObjectMapper().writeValueAsString(inputVO);
	        System.out.println(jsonInString);*/
			String name = "admin";
	        String password = "admin@123";
	        String authString = name + ":" + password;
	        String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
	        
	        ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	    }else{
				String output = response.getEntity(String.class);
				if(output != null && !output.isEmpty()){
					JSONArray finalArray = new JSONArray(output);
					for(int i=0;i<finalArray.length();i++){
						RangeVO vo = new RangeVO();
						JSONObject jobj = (JSONObject)finalArray.get(i);
						
						vo.setId(jobj.getLong("locationId"));
						vo.setName(jobj.getString("locationName"));
						
						rangeVOList.add(vo);
					}
				}
	 	    }
					
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationBasedOnSelection - RWSNICService service", e);
		}
		return rangeVOList;
	}
}
