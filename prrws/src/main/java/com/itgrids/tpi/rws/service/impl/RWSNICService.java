package com.itgrids.tpi.rws.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import sun.misc.BASE64Encoder;

import com.itgrids.dto.AmsVO;
import com.itgrids.dto.BasicVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.KPIVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.dto.RangeVO;
import com.itgrids.dto.RwsClickVO;
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
		 	    					if(inputVO.getLocationType().equalsIgnoreCase("state"))
		 	    						vo.setGoNumber("01");
		 	    					else
		 	    						vo.setGoNumber(jObj.getString("locationId"));
		 	    					
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
		 					vo.setGoNumber(mandalObj.getString("locationId"));
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
	 	    		
	 	    		basicVO.setTotal((basicVO.getPhysicalTestCount() !=null ? basicVO.getPhysicalTestCount():0l) + (basicVO.getBacterialTestCount() !=null ? basicVO.getBacterialTestCount():0l));
	 	    		
	 	    		basicVO.setPercentage(calculatePercentage(basicVO.getTotal(), basicVO.getPhysicalTestCount()) !=null ?
	 	    				Double.parseDouble(calculatePercentage(basicVO.getTotal(), basicVO.getPhysicalTestCount())):0.0d);//PhysicalTestCount Percentage
	 	    		basicVO.setPercentageOne(calculatePercentage(basicVO.getTotal(), basicVO.getBacterialTestCount()) !=null ? 
	 	    				Double.parseDouble(calculatePercentage(basicVO.getTotal(), basicVO.getBacterialTestCount())):0.0d);//BacterialTestCount Percentage
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
	 	    			if(!jObj.getString("status").equalsIgnoreCase("Failure")){
	 	    				finalVO.setSafeMLD(new BigDecimal(jObj.getString("safeMLD")));
	 	    				finalVO.setUnsafeMLD(new BigDecimal(jObj.getString("unsafeMLD")));
	 	    			}
	 	    				
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
		 	    			Long total  = 0l;
		 	    			for (int i = 0;i<jsonArray.length();i++) {		 	    				
		 	    				JSONObject jObj = (JSONObject)jsonArray.get(i);		 	    				
		 	    				BasicVO Vo = new BasicVO();	
		 	    				
		 	    				Vo.setAssetType(jObj.getString("assetType"));
		 	    				Vo.setCount(jObj.getLong("count"));		 	    						 	    				 	    			
		 	    				finalList.add(Vo);
		 	    				
		 	    				total = total + jObj.getLong("count");				 	    				
							}
		 	    			
		 	    			finalList.get(0).setTotal(total);
		 	    			
		 	    			for (BasicVO basicVO : finalList) {								
		 	    				basicVO.setPercentage(Double.parseDouble(calculatePercentage(total, basicVO.getCount())));		 	    				
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
	 	    				Vo.setCount(Vo.getWorkOngoingCount()+Vo.getWorkComissionedCount()+Vo.getWorkCompletedCount()+Vo.getWorkNotGroundedCount());
	 	    				if(Vo.getCount() > 0l){
	 	    					Vo.setPercentageOne((Vo.getWorkOngoingCount()*100.00)/Vo.getCount());
		 	    				Vo.setPercentageTwo((Vo.getWorkComissionedCount()*100.00)/Vo.getCount());
		 	    				Vo.setPercentageThree((Vo.getWorkCompletedCount()*100.00)/Vo.getCount());
		 	    				Vo.setPercentageFour((Vo.getWorkNotGroundedCount()*100.00)/Vo.getCount());
	 	    				}
	 	    				
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
	 	    			Long totalAssets = 0l;
	 	 	    		for(int i=0;i<finalArray.length();i++){
	 	 	    			BasicVO basicVO = new BasicVO();
	 	 	    			JSONObject jObj = (JSONObject) finalArray.get(i);
	 	 	    			basicVO.setAssetType(jObj.getString("assetType"));
	 	 	    			basicVO.setCount(jObj.getLong("count"));
	 	 	    			totalAssets = totalAssets + jObj.getLong("count");
	 	 	    			assetsList.add(basicVO);
	 	 	    		}
	 	 	    		
	 	 	    		if(totalAssets > 0l){
	 	 	    			for (BasicVO basicVO : assetsList) {
	 	 	    				basicVO.setPercentageOne((basicVO.getCount()*100.00)/totalAssets);
							}
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
						
						Long total =0l;
						
						for (int i = 0; i < arr.length(); i++) {
							//id,name,color,count
							JSONObject jobj = (JSONObject)arr.get(i);
							StatusVO vo = new StatusVO();
							vo.setId(jobj.getLong("id"));
							vo.setName(jobj.getString("name"));
							vo.setColor(jobj.getString("color"));
							vo.setCount(jobj.getLong("count"));
							
							total = total + vo.getCount();
							
							statusVO.getStatusList().add(vo);
						}
						
						Collections.sort(statusVO.getStatusList(), statusAscendingOrder);
						
						statusVO.getStatusList().get(0).setTotal(total);
						
						for (StatusVO statusVO2 : statusVO.getStatusList()) {
							statusVO2.setPercentage(calculatePercentage(total, statusVO2.getCount()) !=null ? Double.parseDouble(calculatePercentage(total, statusVO2.getCount())):0.0d );
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
	public static Comparator<StatusVO> statusAscendingOrder = new Comparator<StatusVO>() {
     	public int compare(StatusVO vo2, StatusVO vo1) {
     	Long id2 = vo2.getId();
     	Long id1 = vo1.getId();
     	 return id2.compareTo(id1);
     	}
      };
	
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
						Long total=0l;						
						for (int i = 0; i < arr.length(); i++) {
							//id,name,color,count
							JSONObject jobj = (JSONObject)arr.get(i);
							StatusVO vo = new StatusVO();
							vo.setId(jobj.getLong("id"));
							vo.setName(jobj.getString("name"));
							vo.setCount(jobj.getLong("count"));
							voList.add(vo);
							total = total + vo.getCount() ;
						}
						
						voList.get(0).setTotal(total);
						
						for (StatusVO statusVO : voList) {
							statusVO.setPercentage(calculatePercentage(total, statusVO.getCount()) !=null ? Double.parseDouble(calculatePercentage(total, statusVO.getCount())):0.0d);
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
	 	    				
	 	    				Long total = waterSourceInfo.getGroundWaterSourceTotalMlpdCount() + waterSourceInfo.getSurfaceWaterSourceTotalMlpdCount();
	 	    				
	 	    				waterSourceInfo.setPercentage(calculatePercentage(total, waterSourceInfo.getGroundWaterSourceTotalMlpdCount())
	 	    						 !=null ? Double.parseDouble(calculatePercentage(total, waterSourceInfo.getGroundWaterSourceTotalMlpdCount())):0.0 );//Ground Percentage
	 	    				
	 	    				waterSourceInfo.setPercentageOne(calculatePercentage(total, waterSourceInfo.getSurfaceWaterSourceTotalMlpdCount())
	 	    						 !=null ? Double.parseDouble(calculatePercentage(total, waterSourceInfo.getSurfaceWaterSourceTotalMlpdCount())):0.0 );//Surface Percentage
	 	    				
	 	    				waterSourceInfo.setCount(total);//total
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
								entry.getValue().setPcPercentage((entry.getValue().getPcAchivement()*100.00)/entry.getValue().getPcTarget());
							}
							
							if(entry.getValue().getQaTarget() > 0l){
								entry.getValue().setQaPercentage((entry.getValue().getQaAchivement()*100.00)/entry.getValue().getQaTarget());
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
 	    		
 	    		if(!maonJObj.getString("status").equalsIgnoreCase("Failure")){
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
	 	    				for (Entry<String, StatusVO> entry : locationsMap.entrySet()) {
								Long totalHab = entry.getValue().getTarget()+entry.getValue().getAchived(),
										totalPop = entry.getValue().getTargetPopulation()+entry.getValue().getAchivedPopulation();
								
								entry.getValue().setPercentageOne((entry.getValue().getTarget()*100.00)/totalHab);
								entry.getValue().setAchivedHabPerc((entry.getValue().getAchived()*100.00)/totalHab);
								entry.getValue().setTargetPopPerc((entry.getValue().getTargetPopulation()*100.00)/totalPop);
								entry.getValue().setAchivedPopPerc((entry.getValue().getAchivedPopulation()*100.00)/totalPop);
							}
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
	
	public String calculatePercentage(Long total,Long count)
	{
		try{
			if(total != null && total.longValue() > 0l && count != null && count.longValue()>0L)
			  return (new BigDecimal((count * 100.0)/total.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
			else{
				return "0";
			}
			
		}catch (Exception e) {
			LOG.error("Exception Occured in calculatePercentage() method, Exception - ",e);
		}
		return null;
	}
	
	/*
	 * Date : 30/06/2017
	 * Author :Balu
	 * @description : getStressedHabitationsInfoByLocationType
	 */
	public List<AmsVO> getAlertsOfCategoryByStatusWise(InputVO vo) {
		List<AmsVO> finalList = new ArrayList<AmsVO>();
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("https://mytdp.com/WebService/getAlertsOfCategoryByStatusWise");
	        
        	ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, vo);
        
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				
				if(output != null && !output.isEmpty()){
					JSONArray finalArray = new JSONArray(output);//Type Array
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				AmsVO amsVo = new AmsVO();
	 	    				JSONObject jobj = (JSONObject)finalArray.get(i);
	 	    				
	 	    				amsVo.setId(jobj.getLong("id"));
	 	    				amsVo.setTitle(jobj.getString("title"));
	 	    				amsVo.setAlertLevel(jobj.getString("alertLevel"));
	 	    				amsVo.setCreatedDate(jobj.getString("createdDate"));
	 	    				amsVo.setUpdatedDate(jobj.getString("updatedDate"));
	 	    				amsVo.setStatusId(jobj.getLong("statusId"));
	 	    				amsVo.setStatus(jobj.getString("status"));
	 	    				amsVo.setSevertyColor(jobj.getString("severtyColor"));
	 	    				amsVo.setStatusColor(jobj.getString("statusColor"));
	 	    				amsVo.setProblem(jobj.getString("problem"));
	 	    				amsVo.setRelatedTo(jobj.getString("relatedTo"));
	 	    				amsVo.setSource(jobj.getString("source"));
	 	    				amsVo.setSubTaskCount(jobj.getLong("subTaskCount"));
	 	    				
	 	    				finalList.add(amsVo);
	 	    				
	 	    			}
	 	    		}
				}
				
 	      	}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getAlertsOfCategoryByStatusWise() method, Exception - ",e);
		}
		return finalList;
	}
	
	public List<RwsClickVO> getOnclickWorkDetails(InputVO vo){
		List<RwsClickVO> finalList = new ArrayList<RwsClickVO>();
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://192.168.11.102:8070/rwscore/cd/getOnclickWorkDetails");
			String authStringEnc = getAuthenticationString("admin","admin@123");
        	ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, vo);
        
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				
				if(output != null && !output.isEmpty()){
					
					JSONObject jsonObj = new JSONObject(output);
					
					if(jsonObj.getString("status") !="null" && !jsonObj.getString("status").trim().isEmpty() && 
							jsonObj.getString("status").trim().equalsIgnoreCase("Success")){
						
						JSONArray onClickWorksArray =  jsonObj.getJSONArray("onClickWorksList");//Type Array
						
		 	    		if(onClickWorksArray!=null && onClickWorksArray.length()>0){
		 	    			for(int i=0;i<onClickWorksArray.length();i++){
		 	    				
		 	    				RwsClickVO subVo = new RwsClickVO();
		 	    				
		 	    				JSONObject jobj = (JSONObject)onClickWorksArray.get(i);
		 	    				
		 	    				subVo.setMandalName(jobj.getString("mandalName"));
		 	    				subVo.setConstituencyName(jobj.getString("districtName"));
		 	    				subVo.setDistrictCode(jobj.getString("districtCode"));
		 	    				subVo.setDistrictName(jobj.getString("districtName"));
		 	    				subVo.setConstituencyCode(jobj.getString("constituencyCode"));		 	    				
		 	    				subVo.setSacntionedAmount(jobj.getString("sacntionedAmount"));
		 	    				subVo.setMandalCode(jobj.getString("mandalCode"));
		 	    				subVo.setHabitationName(jobj.getString("habitationName"));
		 	    				subVo.setHabitationCode(jobj.getString("habitationCode"));
		 	    				subVo.setCompletionDate(jobj.getString("habitationCode"));
		 	    				subVo.setWorkName(jobj.getString("workName"));		 	    				
		 	    				subVo.setWorkId(jobj.getString("workId"));
		 	    				//subVo.setSourceCount(jobj.getString("sourceCount"));
		 	    				
		 	    				
		 	    				finalList.add(subVo);
		 	    			}
		 	    		}
						
					}
				}
				
 	      	}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getOnclickWorkDetails() method, Exception - ",e);
		}
		return finalList;
	}
	
	public List<RwsClickVO> getOnclickTargetsAcheievementsDetails(InputVO vo){
		List<RwsClickVO> finalList = new ArrayList<RwsClickVO>();
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://192.168.11.102:8070/rwscore/cd/getOnclickTargetsAcheievementsDetails");
	        String authStringEnc = getAuthenticationString("admin","admin@123");
        	ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, vo);
        
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				
				if(output != null && !output.isEmpty()){
					
					JSONObject jsonObj = new JSONObject(output);
					
					if(jsonObj.getString("status") !="null" && !jsonObj.getString("status").trim().isEmpty() && 
							jsonObj.getString("status").trim().equalsIgnoreCase("Success")){
						
						JSONArray onClickWorksArray =  jsonObj.getJSONArray("onClickWorksList");//Type Array
						
		 	    		if(onClickWorksArray!=null && onClickWorksArray.length()>0){
		 	    			for(int i=0;i<onClickWorksArray.length();i++){
		 	    				
		 	    				RwsClickVO subVo = new RwsClickVO();
		 	    				
		 	    				JSONObject jobj = (JSONObject)onClickWorksArray.get(i);
		 	    				
		 	    				subVo.setMandalName(jobj.getString("mandalName"));
		 	    				subVo.setConstituencyName(jobj.getString("districtName"));
		 	    				//subVo.setSourceCount(jobj.getString("sourceCount"));
		 	    				subVo.setDistrictCode(jobj.getString("districtCode"));
		 	    				subVo.setDistrictName(jobj.getString("districtName"));
		 	    				subVo.setConstituencyCode(jobj.getString("constituencyCode"));
		 	    				subVo.setMandalCode(jobj.getString("mandalCode"));
		 	    				subVo.setHabitationName(jobj.getString("habitationName"));
		 	    				subVo.setHabitationCode(jobj.getString("habitationCode"));
		 	    				subVo.setTotalCount(jobj.getString("totalCount"));
    				
		 	    				
		 	    				finalList.add(subVo);
		 	    			}
		 	    		}
						
					}
				}
				
 	      	}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getOnclickTargetsAcheievementsDetails() method, Exception - ",e);
		}
		return finalList;
	}
	
	public List<RwsClickVO> getOnclickStressedTargetsAcheievementsDetails(InputVO vo){
		List<RwsClickVO> finalList = new ArrayList<RwsClickVO>();
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://192.168.11.102:8070/rwscore/cd/getOnclickStressedTargetsAcheievementsDetails");
	        String authStringEnc = getAuthenticationString("admin","admin@123");
        	ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, vo);
        
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				
				if(output != null && !output.isEmpty()){
					
					JSONObject jsonObj = new JSONObject(output);
					
					if(jsonObj.getString("status") !="null" && !jsonObj.getString("status").trim().isEmpty() && 
							jsonObj.getString("status").trim().equalsIgnoreCase("Success")){
						
						JSONArray onClickWorksArray =  jsonObj.getJSONArray("onClickWorksList");//Type Array
						
		 	    		if(onClickWorksArray!=null && onClickWorksArray.length()>0){
		 	    			for(int i=0;i<onClickWorksArray.length();i++){
		 	    				
		 	    				RwsClickVO subVo = new RwsClickVO();
		 	    				
		 	    				JSONObject jobj = (JSONObject)onClickWorksArray.get(i);
		 	    		
		 	    				subVo.setMandalName(jobj.getString("mandalName"));
		 	    				subVo.setConstituencyName(jobj.getString("constituencyName"));
		 	    				subVo.setToatlPorpualtionCovered(jobj.getString("toatlPorpualtionCovered"));
		 	    				subVo.setDistrictCode(jobj.getString("districtCode"));
		 	    				subVo.setDistrictName(jobj.getString("districtName"));
		 	    				subVo.setConstituencyCode(jobj.getString("constituencyCode"));
		 	    				subVo.setMandalCode(jobj.getString("mandalCode"));
		 	    				subVo.setHabitationName(jobj.getString("habitationName"));
		 	    				subVo.setHabitationCode(jobj.getString("habitationCode"));
		 	    				
		 	    				finalList.add(subVo);
		 	    			}
		 	    		}						
					}
					
					
				}
				
 	      	}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getOnclickStressedTargetsAcheievementsDetails() method, Exception - ",e);
		}
		return finalList;
	}
	
	public List<RwsClickVO> getOnclickHabitationsupplyDetails(InputVO vo){
		List<RwsClickVO> finalList = new ArrayList<RwsClickVO>();
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://192.168.11.102:8070/rwscore/cd/getOnclickHabitationsupplyDetails");
	        String authStringEnc = getAuthenticationString("admin","admin@123");
        	ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, vo);
        
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				
				if(output != null && !output.isEmpty()){
					
					JSONObject jsonObj = new JSONObject(output);
					
					if(jsonObj.getString("status") !="null" && !jsonObj.getString("status").trim().isEmpty() && 
							jsonObj.getString("status").trim().equalsIgnoreCase("Success")){
						
						JSONArray onClickWorksArray =  jsonObj.getJSONArray("onClickWorksList");//Type Array
						
		 	    		if(onClickWorksArray!=null && onClickWorksArray.length()>0){
		 	    			for(int i=0;i<onClickWorksArray.length();i++){
		 	    				
		 	    				RwsClickVO subVo = new RwsClickVO();
		 	    				
		 	    				JSONObject jobj = (JSONObject)onClickWorksArray.get(i);
		 	    		
		 	    				subVo.setMandalName(jobj.getString("mandalName"));
		 	    				subVo.setConstituencyName(jobj.getString("constituencyName"));		 	    				
		 	    				subVo.setDistrictCode(jobj.getString("districtCode"));
		 	    				subVo.setDistrictName(jobj.getString("districtName"));
		 	    				subVo.setLpcd(jobj.getString("lpcd"));
		 	    				subVo.setConstituencyCode(jobj.getString("constituencyCode"));
		 	    				subVo.setMandalCode(jobj.getString("mandalCode"));		 	    			
		 	    				subVo.setHabitationName(jobj.getString("habitationName"));
		 	    				subVo.setHabitationCode(jobj.getString("habitationCode"));
		 	    				
		 	    				finalList.add(subVo);
		 	    			}
		 	    		}
					}
					
					
				}
				
 	      	}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getOnclickHabitationsupplyDetails() method, Exception - ",e);
		}
		return finalList;
	}
	//01-07-2017 
	
	public List<RwsClickVO> getSchemeDetailsByTypeOfAssestName(InputVO vo){
		List<RwsClickVO> finalList = new ArrayList<RwsClickVO>();
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://192.168.11.102:8070/rwscore/cd/getSchemeDetailsByTypeOfAssestName");
	        String authStringEnc = getAuthenticationString("admin","admin@123");
        	ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, vo);
        
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				
				if(output != null && !output.isEmpty()){
					
					JSONArray finalArray = new JSONArray(output);
					
				
		 	    		if(finalArray!=null && finalArray.length()>0){
		 	    			
		 	    			JSONObject firstObj = (JSONObject)finalArray.get(0);
		 	    			
		 	    			if(firstObj.getString("status") !="null" && !firstObj.getString("status").trim().isEmpty() && 
									firstObj.getString("status").trim().equalsIgnoreCase("Success")){
		 	    				
		 	    				for(int i=0;i<finalArray.length();i++){
			 	    				
			 	    				RwsClickVO subVo = new RwsClickVO();
			 	    				
			 	    				JSONObject jobj = (JSONObject)finalArray.get(i);
			 	    			
			 	    		        subVo.setDistrictCode(jobj.getString("districtCode"));
			 	    				subVo.setDistrictName(jobj.getString("districtName"));
			 	    				subVo.setConstituencyCode(jobj.getString("constituencyCode"));
			 	    				subVo.setConstituencyName(jobj.getString("constituencyName"));
			 	    				subVo.setMandalCode(jobj.getString("mandalCode"));	
			 	    				subVo.setMandalName(jobj.getString("mandalName"));
			 	    				subVo.setHabitationCode(jobj.getString("habitationCode"));
			 	    				subVo.setHabitationName(jobj.getString("habitationName")); 	    
			 	    				subVo.setTotalCount(jobj.getString("totalCount"));
			 	    				subVo.setWorkId(jobj.getString("workId"));
			 	    				subVo.setWorkName(jobj.getString("workName"));
			 	    				subVo.setAssetType(jobj.getString("assestType"));
			 	    				
			 	    				finalList.add(subVo);
			 	    			}
		 	    				
		 	    			}
		 	    			
		 	    		}
					
				}
				
 	      	}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getSchemeDetailsByTypeOfAssestName() method, Exception - ",e);
		}
		return finalList;
	}
	
	public List<RwsClickVO> getAssetDetailsByAssetType(InputVO vo){
		List<RwsClickVO> finalList = new ArrayList<RwsClickVO>();
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://192.168.11.102:8070/rwscore/cd/getAssetDetailsByAssetType");
	        String authStringEnc = getAuthenticationString("admin","admin@123");
        	ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, vo);
        
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				
				if(output != null && !output.isEmpty()){
					
					JSONArray finalArray = new JSONArray(output);
					
				
		 	    		if(finalArray!=null && finalArray.length()>0){
		 	    			
		 	    			JSONObject firstObj = (JSONObject)finalArray.get(0);
		 	    			
		 	    			if(firstObj.getString("status") !="null" && !firstObj.getString("status").trim().isEmpty() && 
									firstObj.getString("status").trim().equalsIgnoreCase("Success")){
		 	    				
		 	    				for(int i=0;i<finalArray.length();i++){
			 	    				
			 	    				RwsClickVO subVo = new RwsClickVO();
			 	    				
			 	    				JSONObject jobj = (JSONObject)finalArray.get(i);
			 	    			
			 	    		        subVo.setDistrictCode(jobj.getString("districtCode"));
			 	    				subVo.setDistrictName(jobj.getString("districtName"));
			 	    				subVo.setConstituencyCode(jobj.getString("constituencyCode"));
			 	    				subVo.setConstituencyName(jobj.getString("constituencyName"));
			 	    				subVo.setMandalCode(jobj.getString("mandalCode"));	
			 	    				subVo.setMandalName(jobj.getString("mandalName"));
			 	    				subVo.setHabitationCode(jobj.getString("habitationCode"));
			 	    				subVo.setHabitationName(jobj.getString("habitationName")); 	    
			 	    				subVo.setCoverageStatus(jobj.getString("coverageStatus"));			 	    				
			 	    				subVo.setTotalCount(jobj.getString("totalCount"));
			 	    				subVo.setAssestCode(jobj.getString("assestCode"));
			 	    				subVo.setAssestName(jobj.getString("assestName"));
			 	    				subVo.setAssestCost(jobj.getString("assestCost"));
			 	    				subVo.setAssetType(jobj.getString("assestType"));
			 	    				
			 	    				finalList.add(subVo);
			 	    			}		 	    				
		 	    			}		 	    		
		 	    		}
					
				}
				
 	      	}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getAssetDetailsByAssetType() method, Exception - ",e);
		}
		return finalList;
	}
	
	public List<RwsClickVO> getHabitationDetailsByStatusByLocationType(InputVO vo){
		List<RwsClickVO> finalList = new ArrayList<RwsClickVO>();
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://192.168.11.102:8070/rwscore/cd/getHabitationDetailsByStatusByLocationType");
	        String authStringEnc = getAuthenticationString("admin","admin@123");
        	ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, vo);
        
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				
				if(output != null && !output.isEmpty()){
					
					JSONArray finalArray = new JSONArray(output);
					
				
		 	    		if(finalArray!=null && finalArray.length()>0){
		 	    			
		 	    			JSONObject firstObj = (JSONObject)finalArray.get(0);
		 	    			
		 	    			if(firstObj.getString("status") !="null" && !firstObj.getString("status").trim().isEmpty() && 
									firstObj.getString("status").trim().equalsIgnoreCase("Success")){
		 	    				
		 	    				for(int i=0;i<finalArray.length();i++){
			 	    				
			 	    				RwsClickVO subVo = new RwsClickVO();
			 	    				
			 	    				JSONObject jobj = (JSONObject)finalArray.get(i);
			 	    				
			 	    		        subVo.setDistrictCode(jobj.getString("districtCode"));
			 	    				subVo.setDistrictName(jobj.getString("districtName"));
			 	    				subVo.setConstituencyCode(jobj.getString("constituencyCode"));
			 	    				subVo.setConstituencyName(jobj.getString("constituencyName"));
			 	    				subVo.setMandalCode(jobj.getString("mandalCode"));	
			 	    				subVo.setMandalName(jobj.getString("mandalName"));
			 	    				subVo.setHabitationCode(jobj.getString("habitationCode"));
			 	    				subVo.setHabitationName(jobj.getString("habitationName")); 	    
			 	    				subVo.setCoverageStatus(jobj.getString("coverageStatus"));			 	    				
			 	    				
			 	    				finalList.add(subVo);
			 	    			}		 	    				
		 	    			}		 	    		
		 	    		}
					
				}
				
 	      	}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getAssetDetailsByAssetType() method, Exception - ",e);
		}
		return finalList;
	}
	
	public List<RwsClickVO> getWaterSourceDeatilsLocationWise(InputVO vo){
		List<RwsClickVO> finalList = new ArrayList<RwsClickVO>();
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://192.168.11.102:8070/rwscore/cd/getWaterSourceDeatilsLocationWise");
	        String authStringEnc = getAuthenticationString("admin","admin@123");
        	ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, vo);
        
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				
				if(output != null && !output.isEmpty()){
					
					JSONArray finalArray = new JSONArray(output);
					
				
		 	    		if(finalArray!=null && finalArray.length()>0){
		 	    			
		 	    			JSONObject firstObj = (JSONObject)finalArray.get(0);
		 	    			
		 	    			if(firstObj.getString("status") !="null" && !firstObj.getString("status").trim().isEmpty() && 
									firstObj.getString("status").trim().equalsIgnoreCase("Success")){
		 	    				
		 	    				for(int i=0;i<finalArray.length();i++){
			 	    				
			 	    				RwsClickVO subVo = new RwsClickVO();
			 	    				
			 	    				JSONObject jobj = (JSONObject)finalArray.get(i);
			 	    				
			 	    		        subVo.setDistrictCode(jobj.getString("districtCode"));
			 	    				subVo.setDistrictName(jobj.getString("districtName"));
			 	    				subVo.setConstituencyCode(jobj.getString("constituencyCode"));
			 	    				subVo.setConstituencyName(jobj.getString("constituencyName"));
			 	    				subVo.setMandalCode(jobj.getString("mandalCode"));	
			 	    				subVo.setMandalName(jobj.getString("mandalName"));    
			 	    				subVo.setSourceCount(jobj.getString("sourceCount"));			 	    				
			 	    				
			 	    				finalList.add(subVo);
			 	    			}		 	    				
		 	    			}		 	    		
		 	    		}
					
				}
				
 	      	}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getWaterSourceDeatilsLocationWise() method, Exception - ",e);
		}
		return finalList;
	}
	
	
}
