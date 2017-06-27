package com.itgrids.tpi.rws.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dto.BasicVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.KPIVO;
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
	        
	        
	        String authStringEnc = getAuthenticationString("admin","admin@123");
	        
	        ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);//null;
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		if(inputVO.getLocationType() != null && inputVO.getLocationType().equalsIgnoreCase("mandal")){
	 	    			//build mandal level data
	 	    			buildHabitationCoverageByStatusMandalWise(output,voList);
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
			 	    					List<StatusVO> tempList =getStatusSkeleton();
			 	    					for (int j = 0; j < statusListArray.length(); j++) {
			 	    						JSONObject jobj1 = (JSONObject) statusListArray.get(j);
											StatusVO statusVO = getMatchedStatusVO(tempList,jobj1.getString("status"));
											
											if(statusVO != null){
												statusVO.setCount(jobj1.getLong("count"));
												statusVO.setPercentage(jobj1.getDouble("percentage"));
											}
										}
			 	    					vo.setStatusList(tempList);
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
	
	public StatusVO getMatchedStatusVO(List<StatusVO> voList,String status){
		if(voList != null && voList.size() > 0){
			for (StatusVO statusVO : voList) {
				if(statusVO.getStatus().equalsIgnoreCase(status))
					return statusVO;
			}
		}
		return null;
	}
	
	public List<StatusVO> getStatusSkeleton(){
		List<StatusVO> tempList = new ArrayList<StatusVO>(0);
		StatusVO FCVO = new StatusVO();
		FCVO.setStatus("FC");
		tempList.add(0,FCVO);
		
		StatusVO pc4VO = new StatusVO();
		pc4VO.setStatus("PC4");
		tempList.add(1,pc4VO);
		StatusVO pc3VO = new StatusVO();
		pc3VO.setStatus("PC3");
		tempList.add(2,pc3VO);
		StatusVO pc2VO = new StatusVO();
		pc2VO.setStatus("PC2");
		tempList.add(3,pc2VO);
		StatusVO pc1VO = new StatusVO();
		pc1VO.setStatus("PC1");
		tempList.add(4,pc1VO);
		StatusVO nssVO = new StatusVO();
		nssVO.setStatus("NSS");
		tempList.add(5,nssVO);
		return tempList;
	}
	
	public void buildHabitationCoverageByStatusMandalWise(String output,List<LocationVO> voList){
		try {
			JSONArray finalArray = new JSONArray(output);
			if(finalArray!=null && finalArray.length()>0){
	 			for(int i=0;i<finalArray.length();i++){
	 				JSONObject distObj = (JSONObject)finalArray.get(i);
	 				if(distObj != null&& distObj.length() > 0 && distObj.getJSONArray("subList") != null && distObj.getJSONArray("subList").length() > 0){
		 				JSONArray mandalArr = distObj.getJSONArray("subList");
		 				for (int j = 0; j < mandalArr.length(); j++) {
		 					JSONObject mandalObj = (JSONObject) mandalArr.get(j);
		 					LocationVO vo = new LocationVO();
	 	    				vo.setLocationName(mandalObj.getString("locationName"));
		 	    			vo.setStreetHabitationCount(mandalObj.getLong("stressedHabitationCount"));
		 	    			vo.setTotalCount(mandalObj.getLong("totalCount"));
		 	    			vo.setParentLocationId(distObj.getLong("districtId"));
		 	    			JSONArray statusListArray = mandalObj.getJSONArray("statusList");
		 	    				
		 	    			if(statusListArray != null && statusListArray.length() > 0){
	 	    					List<StatusVO> tempList =getStatusSkeleton();
	 	    					for (int s = 0; s < statusListArray.length(); s++) {
	 	    						JSONObject jobj1 = (JSONObject) statusListArray.get(s);
									StatusVO statusVO = getMatchedStatusVO(tempList,jobj1.getString("status"));
									
									if(statusVO != null){
										statusVO.setCount(jobj1.getLong("count"));
										statusVO.setPercentage(jobj1.getDouble("percentage"));
									}
								}
	 	    					vo.setStatusList(tempList);
	 	    				}
		 	    			
		 	    			voList.add(vo);
						}
		 			}
	 			}
	 		}
		} catch (Exception e) {
			LOG.error("Exceptionr aised while building the data mandal wise habitation status counts", e);
		}
	}
	/*
	 * Date : 15/06/2017
	 * Author :Sandeep
	 * @description : getLabTestDetails
	 */
	public BasicVO getLabTestDetails(InputVO inputVO){
		BasicVO basicVO = null;
		try {

	        WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://rwss.ap.nic.in/rwscore/cd/getLabTestDetails");	        
	        String authStringEnc = getAuthenticationString("admin","admin@123");	        
	        ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, inputVO);
	        
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	    }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 //output = "{\"physicalTestCount\":13665,\"bacterialTestCount\":27622}";
	 	    	 
	 	    	 if(output != null && !output.isEmpty()){
	 	    		JSONObject jObj = new JSONObject(output);
	 	    		 basicVO = new BasicVO();
	 	    		basicVO.setPhysicalTestCount(jObj.getLong("physicalTestCount"));
	 	    		basicVO.setBacterialTestCount(jObj.getLong("bacterialTestCount"));
	 	    		 
	 	    	 }
			}
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
			
			
			 WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://rwss.ap.nic.in/rwscore/cd/gethabitationsupplyDetails");	        
		        String authStringEnc = getAuthenticationString("admin","admin@123");	        
		        ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, VO);
		        
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 
	 	    	//String output ="{'safeMLD':2.94,'unsafeMLD':0.12}";
	 	    	 
	 	    	 if(output != null && !output.isEmpty()){
	 	    		JSONObject jObj = new JSONObject(output);	 	    		
	 	    				finalVO.setSafeMLD(new BigDecimal(jObj.getString("safeMLD")));
	 	    				finalVO.setUnsafeMLD(new BigDecimal(jObj.getString("unsafeMLD")));
	 	    			}
	 	    	}
	 	    	
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
			
			 WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://rwss.ap.nic.in/rwscore/cd/getSchemesDetails");	        
		     String authStringEnc = getAuthenticationString("admin","admin@123");	        
		     ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, VO);
		        
	       
	       if(response.getStatus() != 200){
	 	      throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);	 	    	 
	 	    	//String output = "[{'assetType':'PWS','count':1583},{'assetType':'CPWS','count':8},{'assetType':'SCHOOLS','count':3},{'assetType':'SUSTAINABILITY','count':26}]";	 	    	 
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
	 	    	}
			
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
			
			 WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://rwss.ap.nic.in/rwscore/cd/getSchemeWiseWorkDetails");	        
		     String authStringEnc = getAuthenticationString("admin","admin@123");	        
		     ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, VO);
			
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	    }else{
	 	    		String output = response.getEntity(String.class);
	 	    	 
			//String output = "[{'assetType':'SUSTAINABILITY','workOngoingCount':10,'workComissionedCount':35,'workCompletedCount':46,'workNotGroundedCount':0},{'assetType':'SCHOOLS','workOngoingCount':6,'workComissionedCount':12,'workCompletedCount':17,'workNotGroundedCount':0},{'assetType':'PWS','workOngoingCount':162,'workComissionedCount':979,'workCompletedCount':1310,'workNotGroundedCount':0},{'assetType':'CPWS','workOngoingCount':25,'workComissionedCount':42,'workCompletedCount':51,'workNotGroundedCount':0}]";
	 	    	 
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
	 	    		}	 	    		
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
			
			 WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://rwss.ap.nic.in/rwscore/cd/getAssetsinfo");	        
		     String authStringEnc = getAuthenticationString("admin","admin@123");	        
		     ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, vo);
			
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output =response.getEntity(String.class);
				if(output != null && !output.isEmpty()){
	 	    		JSONObject jobj = new JSONObject(output);
	 	    		
	 	    		if(jobj.getJSONArray("assetTypeList") != null && jobj.getJSONArray("assetTypeList").length() > 0){
	 	    			JSONArray finalArray = jobj.getJSONArray("assetTypeList");
	 	 	    		for(int i=0;i<finalArray.length();i++){
	 	 	    			BasicVO basicVO = new BasicVO();
	 	 	    			JSONObject jObj = (JSONObject) finalArray.get(i);
	 	 	    			basicVO.setAssetType(jObj.getString("assetType"));
	 	 	    			basicVO.setCount(jObj.getLong("count"));
	 	 	    			assetsList.add(basicVO);
	 	 	    		}
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
						StatusVO statusVO = new StatusVO();
						for (int i = 0; i < arr.length(); i++) {
							//id,name,color,count
							JSONObject jobj = (JSONObject)arr.get(i);
							StatusVO vo = new StatusVO();
							vo.setId(jobj.getLong("id"));
							vo.setName(jobj.getString("name"));
							vo.setColor(jobj.getString("color"));
							vo.setCount(jobj.getLong("count"));
							statusVO.getStatusList().add(vo);
						}
						voList.add(statusVO);
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
			LOG.error("Exception raised at getAlertFeedbackStatusDetails - RWSNICService service", e);
		}
		return voList;
	}
	
	
	/*
	 * Date : 18/06/2017
	 * Author :Nagarjuna
	 * @description : getWaterSourceInfo
	 */
	public StatusVO getWaterSourceInfo() {
		StatusVO waterSourceInfo =new StatusVO();
		try{
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://rwss.ap.nic.in/rwscore/cd/getWaterSourceDeatils");	        
		     String authStringEnc = getAuthenticationString("admin","admin@123");	        
		     ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).get(ClientResponse.class);
			
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				/*output="[{'source' : 'ground','value'  : '10000'},"
						 +"{'source' : 'surface','value'  : '10000'}]";*/
 	    	if(output != null && !output.isEmpty()){
 	    		
 	    				JSONObject jObj = new JSONObject(output);
 	    		
 	    				if(jObj !=null){
	 	    				waterSourceInfo.setName(jObj.getString("status"));
	 	    				waterSourceInfo.setGroundWaterSourceTotalMlpdCount(jObj.getLong("groundWaterSourceTotalMlpdCount"));
	 	    				waterSourceInfo.setSurfaceWaterSourceTotalMlpdCount(jObj.getLong("surfaceWaterSourceTotalMlpdCount"));
	 	    				
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
	public List<KPIVO> getKeyPerformanceIndicatorsInfo(InputVO inputVO){
		List<KPIVO> voList = new ArrayList<KPIVO>(0);
		try {
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://rwss.ap.nic.in/rwscore/cd/getKpiDeatils");
			//WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://192.168.11.207:8442/rwscore/cd/getKpiDeatils");
		    String authStringEnc = getAuthenticationString("admin","admin@123");	        
		    ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, inputVO);
			
	      
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	    }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject jobj = new JSONObject(output);
	 	    		
	 	    		Map<String,KPIVO> finalMap = new HashMap<String, KPIVO>();
	 	    		
	 	    		if(jobj.getJSONArray("acheieveMentsData") != null && jobj.getJSONArray("acheieveMentsData").length() > 0){
	 	    			JSONArray acvhiementArr = jobj.getJSONArray("acheieveMentsData");
	 	    			for (int i = 0; i < acvhiementArr.length(); i++) {
							JSONArray indiArr = acvhiementArr.getJSONArray(i);
							if(finalMap.get(indiArr.get(0).toString()) == null){
								KPIVO vo = new KPIVO();
								vo.setLocationId(indiArr.get(0).toString());
								vo.setLocationName(indiArr.get(1).toString());
								if(indiArr.get(2).toString().equalsIgnoreCase("NSS") || indiArr.get(2).toString().equalsIgnoreCase("NC")){
									vo.setQaAchivement(Long.parseLong(indiArr.get(3)+""));
								}else if(indiArr.get(2).toString().equalsIgnoreCase("PC1") || indiArr.get(2).toString().equalsIgnoreCase("PC2") 
										|| indiArr.get(2).toString().equalsIgnoreCase("PC3") || indiArr.get(2).toString().equalsIgnoreCase("PC4")){
									vo.setPcAchivement(Long.parseLong(indiArr.get(3)+""));
								}
								finalMap.put(indiArr.get(0).toString(),vo);
							}else{
								KPIVO vo = finalMap.get(indiArr.get(0).toString());
								if(indiArr.get(2).toString().equalsIgnoreCase("NSS") || indiArr.get(2).toString().equalsIgnoreCase("NC")){
									vo.setQaAchivement(vo.getQaAchivement()+Long.parseLong(indiArr.get(3)+""));
								}else if(indiArr.get(2).toString().equalsIgnoreCase("PC1") || indiArr.get(2).toString().equalsIgnoreCase("PC2") 
										|| indiArr.get(2).toString().equalsIgnoreCase("PC3") || indiArr.get(2).toString().equalsIgnoreCase("PC4")){
									vo.setPcAchivement(vo.getPcAchivement()+Long.parseLong(indiArr.get(3)+""));
								}
							}
						}
	 	    		}
	 	    		
	 	    		if(jobj.getJSONArray("targetData") != null && jobj.getJSONArray("targetData").length() > 0){
	 	    			JSONArray targetArr = jobj.getJSONArray("targetData");
	 	    			for (int i = 0; i < targetArr.length(); i++) {
							JSONArray indiArr = targetArr.getJSONArray(i);
							if(finalMap.get(indiArr.get(0).toString()) == null){
								KPIVO vo = new KPIVO();
								vo.setLocationId(indiArr.get(0).toString());
								vo.setLocationName(indiArr.get(1).toString());
								if(indiArr.get(2).toString().equalsIgnoreCase("NSS") || indiArr.get(2).toString().equalsIgnoreCase("NC")){
									vo.setQaTarget(Long.parseLong(indiArr.get(3)+""));
								}else if(indiArr.get(2).toString().equalsIgnoreCase("PC1") || indiArr.get(2).toString().equalsIgnoreCase("PC2") 
										|| indiArr.get(2).toString().equalsIgnoreCase("PC3") || indiArr.get(2).toString().equalsIgnoreCase("PC4")){
									vo.setPcTarget(Long.parseLong(indiArr.get(3)+""));
								}
								finalMap.put(indiArr.get(0).toString(),vo);
							}else{
								KPIVO vo = finalMap.get(indiArr.get(0).toString());
								if(indiArr.get(2).toString().equalsIgnoreCase("NSS") || indiArr.get(2).toString().equalsIgnoreCase("NC")){
									vo.setQaTarget(vo.getQaTarget()+Long.parseLong(indiArr.get(3)+""));
								}else if(indiArr.get(2).toString().equalsIgnoreCase("PC1") || indiArr.get(2).toString().equalsIgnoreCase("PC2") 
										|| indiArr.get(2).toString().equalsIgnoreCase("PC3") || indiArr.get(2).toString().equalsIgnoreCase("PC4")){
									vo.setPcTarget(vo.getPcTarget()+Long.parseLong(indiArr.get(3)+""));
								}
							}
						}
	 	    		}
	 	    		
	 	    		if(finalMap != null && finalMap.size() > 0){
	 	    			for (Entry<String, KPIVO> entry : finalMap.entrySet()) {
							if(entry.getValue().getPcTarget() > 0l){
								entry.getValue().setPcPercentage((entry.getValue().getPcAchivement()/entry.getValue().getPcTarget())*100.00);
							}
							
							if(entry.getValue().getQaTarget() > 0l){
								entry.getValue().setQaPercentage((entry.getValue().getQaAchivement()/entry.getValue().getQaTarget())*100.00);
							}
						}
	 	    			voList.addAll(finalMap.values());
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
	public StatusVO getStressedHabitationsInfoByLocationType(InputVO vo) {
		StatusVO statusVO = new StatusVO();					
		try{
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://rwss.ap.nic.in/rwscore/cd/getStressedHabitationInfoInALocation");	        
		     String authStringEnc = getAuthenticationString("admin","admin@123");	        
		     ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, vo);
			
			
			if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				
 	    	if(output != null && !output.isEmpty()){
 	    		
 	    		JSONObject maonJObj = new JSONObject(output);
 	    		
 	    		statusVO.setId(maonJObj.getLong("locationId"));
 	    		statusVO.setName(maonJObj.getString("locationName"));
 	    		
 	    		JSONArray statusArray = maonJObj.getJSONArray("statusList");
 	    		
 	    		if(statusArray!=null && statusArray.length()>0){
 	    			for(int i=0;i<statusArray.length();i++){
 	    				StatusVO subVO = new StatusVO();
 	    				JSONObject jObj = (JSONObject) statusArray.get(i);
 	    				subVO.setName(jObj.getString("status"));
 	    				subVO.setCount(jObj.getLong("count"));//All Habs
 	    				subVO.setStressedCount(jObj.getLong("stressedHabitationCount"));
 	    				subVO.setPercentage(jObj.getDouble("percentage"));
 	    				
 	    				statusVO.getStatusList().add(subVO);
 	    			}
 	    		}
 	    		
 	    	}
 	    	 
 	    	  
 	      }
			
		}catch (Exception e) {
			LOG.error("Exception raised at getStressedHabitationsInfoByLocationType - RuralWaterSupplyDashBoardService service", e);
		}

		return statusVO;
	}
	
	public List<StatusVO> getPlanofActionForStressedHabitations(InputVO vo){
		List<StatusVO> statusVOList = new ArrayList<StatusVO>(0);
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://rwss.ap.nic.in/rwscore/cd/getStressedKPIDeatils");	        
		     String authStringEnc = getAuthenticationString("admin","admin@123");	        
		     ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, vo);
			
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject jobj = new JSONObject(output);
	 	    		if(jobj.getString("status").equalsIgnoreCase("Success")){
	 	    			 Map<String,StatusVO> locationsMap = new HashMap<String, StatusVO>(0);
	 	    			if(jobj.getJSONArray("stressedHabtationTargetData") != null && jobj.getJSONArray("stressedHabtationTargetData").length() > 0){
	 	    				JSONArray targetArr = jobj.getJSONArray("stressedHabtationTargetData");
	 	    				for(int i=0;i<targetArr.length();i++){
	 	    					JSONArray indiArr = targetArr.getJSONArray(i);
	 	    					StatusVO locationVO = null;
	 	    					if(locationsMap.get(indiArr.getString(0)) == null){
	 	    						locationVO = new StatusVO();
	 	    						locationVO.setStatus(indiArr.getString(0));
	 	    						locationVO.setName(indiArr.getString(1));
	 	    						locationVO.setTarget(indiArr.getLong(3));
	 	    						if(vo.getLocationType().equalsIgnoreCase("state"))
	 	    							locationVO.setTargetPopulation(indiArr.getLong(4));
	 	    						locationsMap.put(indiArr.getString(0),locationVO);
	 	    					}else{
	 	    						locationVO = locationsMap.get(indiArr.getString(0));
	 	    						locationVO.setTarget(locationVO.getTarget()+indiArr.getLong(3));
	 	    						if(vo.getLocationType().equalsIgnoreCase("state"))
	 	    							locationVO.setTargetPopulation(locationVO.getTargetPopulation()+indiArr.getLong(4));
	 	    					}
	 	    					
	 	    				}
	 	    			}
	 	    			
	 	    			if(jobj.getJSONArray("stressedHabtationAcheieveMentsData") != null && jobj.getJSONArray("stressedHabtationAcheieveMentsData").length() > 0){
	 	    				JSONArray achivemntArr = jobj.getJSONArray("stressedHabtationAcheieveMentsData");
	 	    				for(int i=0;i<achivemntArr.length();i++){
	 	    					JSONArray indiArr = achivemntArr.getJSONArray(i);
	 	    					StatusVO locationVO = null;
	 	    					if(locationsMap.get(indiArr.getString(0)) == null){
	 	    						locationVO = new StatusVO();
	 	    						locationVO.setStatus(indiArr.getString(0));
	 	    						locationVO.setName(indiArr.getString(1));
	 	    						locationVO.setAchived(indiArr.getLong(3));
	 	    						if(vo.getLocationType().equalsIgnoreCase("state"))
	 	    							locationVO.setAchivedPopulation(indiArr.getLong(4));
	 	    						locationsMap.put(indiArr.getString(0),locationVO);
	 	    					}else{
	 	    						locationVO = locationsMap.get(indiArr.getString(0));
	 	    						locationVO.setAchived(locationVO.getAchived()+indiArr.getLong(3));
	 	    						if(vo.getLocationType().equalsIgnoreCase("state"))
	 	    							locationVO.setAchivedPopulation(locationVO.getAchivedPopulation()+indiArr.getLong(4));
	 	    					}
	 	    					
	 	    				}
	 	    			}
	 	    			
	 	    			if(locationsMap != null && locationsMap.size() > 0){
	 	    				statusVOList.addAll(locationsMap.values());
	 	    			}
	 	    		}
	 	    	}

	 	      }
		} catch (Exception e) {
			LOG.error("Exception raised at getPlanofActionForStressedHabitations - RWSNICService service", e);
		}
		return statusVOList;
	}
	
	public List<LocationVO> getLocationWiseAlertStatusCounts(InputVO inputVO){
		List<LocationVO> voList = new ArrayList<LocationVO>(0);
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("https://mytdp.com/WebService/getLocationWiseAlertStatusCounts");
			//WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://192.168.11.143:8080/PartyAnalyst/WebService/getLocationWiseAlertStatusCounts");
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
	        
			String authStringEnc = getAuthenticationString("admin","admin@123");
	        
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
	
	public String getAuthenticationString(String name,String password){
		try {			
	        String authString = name + ":" + password;
	        return new BASE64Encoder().encode(authString.getBytes());
			
		} catch (Exception e) {
			LOG.error("Exception raised at getAuthenticationString - RWSNICService service", e);
		}
		return null;
	}
	@Override
	public List<StatusVO> getHamletWiseIvrCounts(InputVO vo) {
		List<StatusVO> voList = new ArrayList<StatusVO>(0);
		try {
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("https://mytdp.com/WebService/getHamletWiseIvrStatusCounts");
			//WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://192.168.11.143:8080/PartyAnalyst/WebService/getHamletWiseIvrStatusCounts");
	        
	        /*String jsonInString = new ObjectMapper().writeValueAsString(vo);
	        System.out.println(jsonInString);*/
	        
	        ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, vo);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
				String output = response.getEntity(String.class);
				
				if(output != null && !output.isEmpty()){
					JSONArray finalArray = new JSONArray(output);//Type Array
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				StatusVO statusVo = new StatusVO();
	 	    				JSONObject jobj = (JSONObject)finalArray.get(i);
	 	    				
	 	    				statusVo.setId(jobj.getLong("id"));
	 	    				statusVo.setName(jobj.getString("name"));
	 	    				
	 	    				JSONArray subListArr  = jobj.getJSONArray("subList1"); // Color Array
	 	    				
	 	    				if(subListArr!=null && subListArr.length()>0){
	 		 	    			for(int j=0;j<subListArr.length();j++){
	 		 	    				
	 		 	    				JSONObject colorJobj = (JSONObject)subListArr.get(j);
	 		 	    				 StatusVO colorVO = new StatusVO();
	 		 	    				colorVO.setName(colorJobj.getString("name"));
	 		 	    				colorVO.setCount(colorJobj.getLong("count"));
	 		 	    				colorVO.setPercentage(colorJobj.getDouble("percentage"));
	 		 	    				statusVo.getStatusList().add(colorVO);
	 		 	    				
	 		 	    			}
	 		 	    		}	 	    				
	 	    				voList.add(statusVo);
	 	    			}
	 	    		}
	 	    	}
				
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getHamletWiseIvrCounts - RWSNICService service", e);
		}
		return voList;
	}
}
