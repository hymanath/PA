package com.itgrids.tpi.rws.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dto.BasicVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.dto.StatusVO;
import com.itgrids.tpi.rws.service.IRWSNICService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

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
			 
	        /*WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://DomainName/Rwss/cd/getHabitationCoverageByStatusByLocationType");
	        
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
									
									JSONObject jobj1 = (JSONObject) statusListArray.get(j);
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
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("https://mytdp.com/WebService/getAlertDetailsOfCategoryByStatusWise");
	        
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
			LOG.error("Exception raised at getAlertDetailsOfCategoryByStatusWise - RuralWaterSupplyDashBoardService service", e);
		}
		return voList;
	}
	
	public List<StatusVO> getAlertFeedbackStatusDetails(InputVO inputVO){
		List<StatusVO> voList = new ArrayList<StatusVO>(0);
		try {
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("https://mytdp.com/WebService/getAlertFeedbackStatusDetails");
	        
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
			LOG.error("Exception raised at getAlertFeedbackStatusDetails - RuralWaterSupplyDashBoardService service", e);
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
			/*WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://DomainName/Rwss/cd/getAssetsinfo");
	        
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
			LOG.error("Exception raised at getWaterSourceInfo - RuralWaterSupplyDashBoardService service", e);
		}
		
		
		return waterSourceInfo;
	
	
	}
}
