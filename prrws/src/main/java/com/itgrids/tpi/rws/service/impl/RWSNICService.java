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

import com.itgrids.dao.IConstituencyDAO;
import com.itgrids.dao.IDistrictDAO;
import com.itgrids.dao.IRwsConstituencyDAO;
import com.itgrids.dao.IRwsDistrictDAO;
import com.itgrids.dao.IRwsTehsilDAO;
import com.itgrids.dao.ITehsilDAO;
import com.itgrids.dto.AmsVO;
import com.itgrids.dto.BasicVO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.KPIVO;
import com.itgrids.dto.KeyValueVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.dto.NregaLocationOverviewVO;
import com.itgrids.dto.RangeVO;
import com.itgrids.dto.RwsClickVO;
import com.itgrids.dto.StatusVO;
import com.itgrids.dto.WaterSourceVO;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.itgrids.tpi.rws.service.IRWSNICService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.IConstants;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import sun.misc.BASE64Encoder;

@Service
@Transactional
public class RWSNICService implements IRWSNICService{
	private static final Logger LOG = Logger.getLogger(RWSNICService.class);
	
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	@Autowired
	private IDistrictDAO districtDAO;
	@Autowired
	private IConstituencyDAO constituencyDAO;
	@Autowired
	private ITehsilDAO tehsilDAO;
	@Autowired
	private IRwsTehsilDAO rwsTehsilDAO;
	@Autowired
	private IRwsDistrictDAO rwsDistrictDAO;
	@Autowired
	private IRwsConstituencyDAO rwsConstituencyDAO;
	@Autowired
	private WebServiceUtilService webServiceUtilService;
	/*
	 * Date : 15/06/2017
	 * Author :Sandeep
	 * @description : getHabitationCoverageByStatusByLocationType
	 */
	public List<LocationVO> getHabitationCoverageByStatusByLocationType(InputVO inputVO){
		List<LocationVO> voList = new ArrayList<LocationVO>(0);
		try {
			if(inputVO!= null){
				inputVO = setFilterVal(inputVO);
			}
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getHabitationCoverageByStatusByLocationType");
			
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
		 	    			vo.setParentLocationId(distObj.getString("districtId"));
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

	        WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getLabTestDetails");	        
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
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/gethabitationWatersupplyDetails");
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
	 	    				finalVO.setSurfaceWaterSafeMLD(new BigDecimal(jObj.getString("surfaceWaterSafeMLD")));
	 	    				finalVO.setGroundWaterUnSafeMLD(new BigDecimal(jObj.getString("groundWaterUnSafeMLD")));
	 	    				finalVO.setGroundWaterSafeMLD(new BigDecimal(jObj.getString("groundWaterSafeMLD")));
	 	    				finalVO.setSurfaceWaterUnSafeMLD(new BigDecimal(jObj.getString("surfaceWaterUnSafeMLD")));
	 	    				finalVO.setTotalUnSafeWaterInMLD(new BigDecimal(jObj.getString("totalUnSafeWaterInMLD")));
	 	    				finalVO.setTotalSafeWaterInMLD(new BigDecimal(jObj.getString("totalSafeWaterInMLD")));
	 	    				
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
			
			 WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getSchemesDetails");	        
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
			
			if(VO!= null){
				VO = setFilterVal(VO);
			}
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getSchemeWiseWorkDetails");
		     String authStringEnc = getAuthenticationString("admin","admin@123");	        
		     ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, VO);
			
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	    }else{
	 	    		String output = response.getEntity(String.class);
	 	    	 
			//String output = "[{'assetType':'SUSTAINABILITY','workOngoingCount':10,'workComissionedCount':35,'workCompletedCount':46,'workNotGroundedCount':0},{'assetType':'SCHOOLS','workOngoingCount':6,'workComissionedCount':12,'workCompletedCount':17,'workNotGroundedCount':0},{'assetType':'PWS','workOngoingCount':162,'workComissionedCount':979,'workCompletedCount':1310,'workNotGroundedCount':0},{'assetType':'CPWS','workOngoingCount':25,'workComissionedCount':42,'workCompletedCount':51,'workNotGroundedCount':0}]";
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		if(VO.getType() !=null&& !VO.getType().trim().isEmpty() && VO.getType().trim().equalsIgnoreCase("graph")){
	 	    			setStateSchemeWiseWorkDetails(output,finalList);
	 	    		}else if(VO.getLocationType() !=null&& !VO.getLocationType().trim().isEmpty() && VO.getLocationType().trim().equalsIgnoreCase("mandal")){
	 	    			setMandalSchemeWiseWorkDetails(output,finalList);
	 	    		}else{
	 	    			
	 	    			JSONArray jsonArrayMain = new JSONArray(output);	 
	 	    			if(jsonArrayMain !=null && jsonArrayMain.length()>0){
		 	   				for(int j = 0;j<jsonArrayMain.length();j++){
		 	   					JSONObject jObjMain = (JSONObject)jsonArrayMain.get(j);
		 	   					JSONArray jsonArray = jObjMain.getJSONArray("subList");//type
		 	   					
		 	   					BasicVO mainVO = new BasicVO();
		 	   					
		 	   					mainVO.setGoNumber(jObjMain.getString("locationId"));
		 	   					mainVO.setLocationName(jObjMain.getString("locationName"));
		 	   					
		 	   					if(jsonArray !=null && jsonArray.length()>0){
		 	   						for (int i = 0;i<jsonArray.length();i++){		
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
		 	   							
				 	    				mainVO.getBasicList().add(Vo);
				 	    				
									}
		 	   					}
		 	   					finalList.add(mainVO);
		 	   					
		 	   				}
	 	    			}
	 	    			
	 	    		}	
	 	    	}
	 	    	
	 	    }
			
		} catch (Exception e) {
			LOG.error("Exception raised at getSchemeWiseWorkDetails - RuralWaterSupplyDashBoardService service", e);
		}
		
		return finalList;
	}
	public  List<BasicVO> setStateSchemeWiseWorkDetails(String output,List<BasicVO> finalList){
		try {
			JSONArray jsonArray = new JSONArray(output);	 	    
	    		if(jsonArray !=null && jsonArray.length()>0){
	    			for (int i = 0;i<jsonArray.length();i++) {		 	    				
	    				JSONObject jObjMain = (JSONObject)jsonArray.get(i);	
	    				
	    				JSONArray jsonArr = jObjMain.getJSONArray("subList");
	    				
	    				if(jsonArr !=null && jsonArr.length()>0){
			    			for (int j = 0;j<jsonArr.length();j++) {		 	    				
			    				JSONObject jObj = (JSONObject)jsonArr.get(j);	
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
			LOG.error("Exception raised at setStateSchemeWiseWorkDetails - RuralWaterSupplyDashBoardService service", e);
		}
		return finalList;
	}
	public List<BasicVO> setMandalSchemeWiseWorkDetails(String output,List<BasicVO> finalList){
		try {
			JSONArray jsonArrayMain = new JSONArray(output);	 
			if(jsonArrayMain !=null && jsonArrayMain.length()>0){
				for(int j = 0;j<jsonArrayMain.length();j++){
					JSONObject jObjMain = (JSONObject)jsonArrayMain.get(j);
					JSONArray jsonArray = jObjMain.getJSONArray("subList");//mandal
					
					if(jsonArray !=null && jsonArray.length()>0){
			    			for (int k = 0;k<jsonArray.length();k++) {		 	    				
			    				JSONObject jObjSub = (JSONObject)jsonArray.get(k);	
			    				
			    				JSONArray jsonSubArray = jObjSub.getJSONArray("subList");//types
			    				
			    				BasicVO mainVo = new BasicVO();
			    				mainVo.setDistrictId(jObjMain.getString("locationId"));//district			    				
			    				mainVo.setName(jObjSub.getString("name"));//distName
			    				mainVo.setGoNumber(jObjSub.getString("locationId"));//mandalId
			    				mainVo.setLocationName(jObjSub.getString("locationName"));//mandalName
			    				
			    				finalList.add(mainVo);
			    				
			    				if(jsonSubArray !=null && jsonSubArray.length()>0){			    					
			    					for (int i = 0; i < jsonSubArray.length(); i++) {			    						
			    						JSONObject jObj = (JSONObject)jsonSubArray.get(i);				    						
			    						BasicVO Vo = new BasicVO();		 	    				
					    				Vo.setAssetType(jObj.getString("assetType"));
					    				Vo.setWorkOngoingCount(jObj.getLong("workOngoingCount"));
					    				Vo.setWorkComissionedCount(jObj.getLong("workComissionedCount"));
					    				Vo.setWorkCompletedCount(jObj.getLong("workCompletedCount"));
					    				Vo.setWorkNotGroundedCount(jObj.getLong("workNotGroundedCount"));
					    				Vo.setCount(Vo.getWorkOngoingCount()+Vo.getWorkComissionedCount()+Vo.getWorkCompletedCount()+Vo.getWorkNotGroundedCount());
					    				if(Vo.getCount() > 0l){ 
					    					Vo.setPercentageOne(new Double(calculatePercentage(Vo.getCount(),Vo.getWorkOngoingCount())));
						    				Vo.setPercentageTwo(new Double(calculatePercentage(Vo.getCount(),Vo.getWorkComissionedCount())));
						    				Vo.setPercentageThree(new Double(calculatePercentage(Vo.getCount(),Vo.getWorkCompletedCount())));
						    				Vo.setPercentageFour(new Double(calculatePercentage(Vo.getCount(),Vo.getWorkNotGroundedCount())));
					    				}
					    				mainVo.getBasicList().add(Vo);
									}
			    				}
			    				
			    				
			    			}
						}
					
				}
			}
			
			
    			
    		
    		
    		
		
	} catch (Exception e) {
			LOG.error("Exception raised at setMandalSchemeWiseWorkDetails - RuralWaterSupplyDashBoardService service", e);
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
			if(vo!= null){
				vo = setFilterVal(vo);
			}
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getAssetsinfo");          
			String authStringEnc = getAuthenticationString("admin","admin@123");          
			ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, vo);
       
			if(response.getStatus() != 200){
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
           	}else{
           		String output =response.getEntity(String.class);
           		if(output != null && !output.isEmpty()){
           			JSONObject jobj = new JSONObject(output);
              
           			if(jobj.getString("status").equalsIgnoreCase("Success") && jobj.getJSONArray("assetTypeList") != null && jobj.getJSONArray("assetTypeList").length() > 0){
           				if(vo.getLocationType().equalsIgnoreCase("mandal")){
           					buildAssetsDataForMandalLevel(jobj,assetsList);
           				}else{
           					buildAssetsData(jobj,assetsList);
           				}
           				
           			}
           		}
           	}
     	}catch (Exception e) {
     		LOG.error("Exception raised at getAssetsInfo - RuralWaterSupplyDashBoardService service", e);
     	}
     	return assetsList;
     }
	
	public List<BasicVO> getAssetsSkeleton(JSONArray distinctAssetsList){
		List<BasicVO> voList = new ArrayList<BasicVO>(0);
		try {
			if(distinctAssetsList != null && distinctAssetsList.length() > 0){
				for (int i = 0; i < distinctAssetsList.length(); i++) {
					if(!distinctAssetsList.getString(i).equalsIgnoreCase("SCHOOLS") && !distinctAssetsList.getString(i).equalsIgnoreCase("LAB")){
						BasicVO invo = new BasicVO();
						invo.setAssetType(distinctAssetsList.getString(i));
						voList.add(invo);
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getAssetsSkeleton - RuralWaterSupplyDashBoardService service", e);
		}
		
		return voList;
	}
	
	public BasicVO getMatchedAssetVO(List<BasicVO> voList,String assetType){
		if(voList != null && voList.size() > 0){
			for (BasicVO basicVO : voList) {
				if(basicVO.getAssetType().equalsIgnoreCase(assetType)){
					return basicVO;
				}
			}
		}
		return null;
	}
	
	public void buildAssetsDataForMandalLevel(JSONObject jobj,List<BasicVO> assetsList){
		try {
			Map<Long,Map<Long,BasicVO>> finalMap = new HashMap<Long, Map<Long,BasicVO>>(0);//Map<distId,Map<mandalId,BasicVO>>
			JSONArray finalArray = jobj.getJSONArray("assetTypeList");
			for(int i=0;i<finalArray.length();i++){
				JSONObject jObj = (JSONObject) finalArray.get(i);
				if(finalMap.get(jObj.getLong("districtId")) == null){
					Map<Long,BasicVO> inMap = new HashMap<Long, BasicVO>(0);
					BasicVO vo = new BasicVO();
					vo.setParentLocationId(jObj.getString("districtId"));
					vo.setGoNumber(jObj.getString("locationId"));
					vo.setName(jObj.getString("locationName"));
					
					vo.setBasicList(getAssetsSkeleton(jobj.getJSONArray("distinctAssetsList")));
					BasicVO matchedAssetVO =  getMatchedAssetVO(vo.getBasicList(),jObj.getString("assetType"));
					if(matchedAssetVO != null)
						matchedAssetVO.setCount(jObj.getLong("count"));
					/*if(!jObj.getString("assetType").equalsIgnoreCase("SCHOOLS") && !jObj.getString("assetType").equalsIgnoreCase("LAB")){
	   					BasicVO basicVO = new BasicVO();
	   					basicVO.setAssetType(jObj.getString("assetType"));
	   					basicVO.setCount(jObj.getLong("count"));
	   					vo.getBasicList().add(basicVO);
	   				}*/
					
					inMap.put(jObj.getLong("locationId"), vo);
					finalMap.put(jObj.getLong("districtId"),inMap);
				}else{
					Map<Long, BasicVO> distMap = finalMap.get(jObj.getLong("districtId"));
					
					if(distMap.get(jObj.getLong("locationId")) == null){
						BasicVO vo = new BasicVO();
						vo.setParentLocationId(jObj.getString("districtId"));
						vo.setGoNumber(jObj.getString("locationId"));
						vo.setName(jObj.getString("locationName"));
						
						vo.setBasicList(getAssetsSkeleton(jobj.getJSONArray("distinctAssetsList")));
						BasicVO matchedAssetVO =  getMatchedAssetVO(vo.getBasicList(),jObj.getString("assetType"));
						if(matchedAssetVO != null)
							matchedAssetVO.setCount(jObj.getLong("count"));
						/*if(!jObj.getString("assetType").equalsIgnoreCase("SCHOOLS") && !jObj.getString("assetType").equalsIgnoreCase("LAB")){
		   					BasicVO basicVO = new BasicVO();
		   					basicVO.setAssetType(jObj.getString("assetType"));
		   					basicVO.setCount(jObj.getLong("count"));
		   					vo.getBasicList().add(basicVO);
		   				}*/
						finalMap.get(jObj.getLong("districtId")).put(jObj.getLong("locationId"), vo);
						
					}else{
						BasicVO matchedAssetVO =  getMatchedAssetVO(distMap.get(jObj.getLong("locationId")).getBasicList(),jObj.getString("assetType"));
						if(matchedAssetVO != null)
							matchedAssetVO.setCount(jObj.getLong("count"));
						/*if(!jObj.getString("assetType").equalsIgnoreCase("SCHOOLS") && !jObj.getString("assetType").equalsIgnoreCase("LAB")){
		   					BasicVO basicVO = new BasicVO();
		   					basicVO.setAssetType(jObj.getString("assetType"));
		   					basicVO.setCount(jObj.getLong("count"));
		   					distMap.get(jObj.getLong("locationId")).getBasicList().add(basicVO);
		   				}*/
					}
				}
			}
			
			if(finalMap != null && finalMap.size() > 0){
				for (Entry<Long, Map<Long, BasicVO>> distEntry : finalMap.entrySet()) {
					Map<Long, BasicVO> manMap = distEntry.getValue();
					if(manMap != null && manMap.size() > 0){
						for (Entry<Long, BasicVO> manEntry : manMap.entrySet()) {
							assetsList.add(manEntry.getValue());
						}
					}
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at buildAssetsDataForMandalLevel - RuralWaterSupplyDashBoardService service", e);
		}
	}
	
	public void buildAssetsData(JSONObject jobj,List<BasicVO> assetsList){
		try {
			Map<Long,BasicVO> finalMap = new HashMap<Long,BasicVO>(0);
			JSONArray finalArray = jobj.getJSONArray("assetTypeList");
	                
	   		for(int i=0;i<finalArray.length();i++){
	   			JSONObject jObj = (JSONObject) finalArray.get(i);
	   			if(finalMap.get(jObj.getLong("locationId")) == null){
	   				BasicVO inVO = new BasicVO();
	   				inVO.setGoNumber(jObj.getString("locationId"));
	   				inVO.setName(jObj.getString("locationName"));
	             
	   				inVO.setBasicList(getAssetsSkeleton(jobj.getJSONArray("distinctAssetsList")));
					BasicVO matchedAssetVO =  getMatchedAssetVO(inVO.getBasicList(),jObj.getString("assetType"));
					if(matchedAssetVO != null)
						matchedAssetVO.setCount(jObj.getLong("count"));
					
	   				/*if(!jObj.getString("assetType").equalsIgnoreCase("SCHOOLS") && !jObj.getString("assetType").equalsIgnoreCase("LAB")){
	   					BasicVO basicVO = new BasicVO();
	   					basicVO.setAssetType(jObj.getString("assetType"));
	   					basicVO.setCount(jObj.getLong("count"));
	   					inVO.getBasicList().add(basicVO);
	   				}*/
	   						
	   				finalMap.put(jObj.getLong("locationId"), inVO);
	   			}else{
	   				BasicVO matchedAssetVO =  getMatchedAssetVO(finalMap.get(jObj.getLong("locationId")).getBasicList(),jObj.getString("assetType"));
					if(matchedAssetVO != null)
						matchedAssetVO.setCount(jObj.getLong("count"));
	   				/*if(!jObj.getString("assetType").equalsIgnoreCase("SCHOOLS") && !jObj.getString("assetType").equalsIgnoreCase("LAB")){
	   					BasicVO basicVO = new BasicVO();
	   					basicVO.setAssetType(jObj.getString("assetType"));
	   					basicVO.setCount(jObj.getLong("count"));
	   					finalMap.get(jObj.getLong("locationId")).getBasicList().add(basicVO);
	   				}*/
	   			}

	   		}
	   			
	   		if(finalMap.size() > 0)
	   			assetsList.addAll(finalMap.values());
		}catch (Exception e) {
			LOG.error("Exception raised at buildAssetsData - RuralWaterSupplyDashBoardService service", e);
		}		
 	}
		
	public List<StatusVO> getAlertDetailsOfCategoryByStatusWise(InputVO inputVO){
		List<StatusVO> voList = new ArrayList<StatusVO>(0);
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("https://mytdp.com/WebService/getAlertDetailsOfCategoryByStatusWise");
			//WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://192.168.11.143:8080/PartyAnalyst/WebService/getAlertDetailsOfCategoryByStatusWise");
	        
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
	public WaterSourceVO getWaterSourceInfo(InputVO inputVO) {
		WaterSourceVO waterSourceInfo =new WaterSourceVO();
		try{
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getWaterSourceDeatils");	        
		     String authStringEnc = getAuthenticationString("admin","admin@123");	        
		     ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, inputVO);
			
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				/*output="[{'source' : 'ground','value'  : '10000'},"
						 +"{'source' : 'surface','value'  : '10000'}]";*/
				if(output != null && !output.isEmpty()){
 	    		
					JSONObject jObj = new JSONObject(output);
 	    		
					if(jObj !=null){
 	    				
						if(jObj.getString("status").equalsIgnoreCase("Success")){
							waterSourceInfo.setSafeSurfaceWaterSourceCount(jObj.getLong("safeSurfaceWaterSourceCount"));
							waterSourceInfo.setTotalGroundWaterSourceCount(jObj.getLong("totalGroundWaterSourceCount"));
							waterSourceInfo.setUnSafeGroundWaterSourceCount(jObj.getLong("unSafeGroundWaterSourceCount"));
							waterSourceInfo.setSafeGroundWaterSourceCount(jObj.getLong("safeGroundWaterSourceCount"));
							waterSourceInfo.setUnSafeSurfaceWaterSourceCount(jObj.getLong("unSafeSurfaceWaterSourceCount"));
							waterSourceInfo.setTotalSurfaceWaterSourceCount(jObj.getLong("totalSurfaceWaterSourceCount"));
						}	
	 	    				/*waterSourceInfo.setName(jObj.getString("status"));
	 	    				waterSourceInfo.setGroundWaterSourceTotalMlpdCount(jObj.getLong("groundWaterSourceCount"));
	 	    				waterSourceInfo.setSurfaceWaterSourceTotalMlpdCount(jObj.getLong("surfaceWaterSourceCount"));
	 	    				
	 	    				Long total = waterSourceInfo.getGroundWaterSourceTotalMlpdCount() + waterSourceInfo.getSurfaceWaterSourceTotalMlpdCount();
	 	    				
	 	    				waterSourceInfo.setPercentage(calculatePercentage(total, waterSourceInfo.getGroundWaterSourceTotalMlpdCount())
	 	    						 !=null ? Double.parseDouble(calculatePercentage(total, waterSourceInfo.getGroundWaterSourceTotalMlpdCount())):0.0 );//Ground Percentage
	 	    				
	 	    				waterSourceInfo.setPercentageOne(calculatePercentage(total, waterSourceInfo.getSurfaceWaterSourceTotalMlpdCount())
	 	    						 !=null ? Double.parseDouble(calculatePercentage(total, waterSourceInfo.getSurfaceWaterSourceTotalMlpdCount())):0.0 );//Surface Percentage
	 	    				
	 	    				waterSourceInfo.setCount(total);//total*/
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
			if(inputVO!= null){
				inputVO = setFilterVal(inputVO);
			}
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getKpiDeatils");
		    String authStringEnc = getAuthenticationString("admin","admin@123");	        
		    ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, inputVO);
			
	      
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	    }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject jobj = new JSONObject(output);
	 	    		if(jobj.getString("status").equalsIgnoreCase("Success")){
	 	    			if(inputVO.getLocationType().equalsIgnoreCase("mandal")){
	 	    				buildKPIMandalResult(jobj,voList);
	 	    			}else{
	 	    				buildKPIResult(jobj,voList);
	 	    			}
	 	    		}
	 	    		
	 	    		
	 	    	}
	 	    }
		} catch (Exception e) {
			LOG.error("Exception raised at getKeyPerformanceIndicatorsInfo - RWSNICService service", e);
		}
		return voList;
	}
	
	public void buildKPIMandalResult(JSONObject jobj,List<KPIVO> voList){
		try {
			Map<Long,Map<Long,KPIVO>> finalMap = new HashMap<Long, Map<Long,KPIVO>>(0);
			
			if(jobj.getJSONArray("acheieveMentsData") != null && jobj.getJSONArray("acheieveMentsData").length() > 0){
				JSONArray acvhiementArr = jobj.getJSONArray("acheieveMentsData");
				for (int i = 0; i < acvhiementArr.length(); i++) {
					JSONArray indiArr = acvhiementArr.getJSONArray(i);
					if(finalMap.get(indiArr.getLong(5)) == null){
						KPIVO vo = new KPIVO();
						vo.setParentLocationId(indiArr.getString(5));
						vo.setLocationId(indiArr.getString(0));
						vo.setLocationName(indiArr.getString(1));
						
						if(indiArr.getString(2).equalsIgnoreCase("NSS") || indiArr.getString(2).equalsIgnoreCase("NC")){
							vo.setQaAchivement(Long.parseLong(indiArr.getString(3)));
						}else if(indiArr.getString(2).equalsIgnoreCase("PC1") || indiArr.getString(2).equalsIgnoreCase("PC2") 
								|| indiArr.getString(2).equalsIgnoreCase("PC3") || indiArr.getString(2).equalsIgnoreCase("PC4")){
							vo.setPcAchivement(indiArr.getLong(3));
						}
						
						Map<Long,KPIVO> mandalMap = new HashMap<Long, KPIVO>(0);
						mandalMap.put(indiArr.getLong(0), vo);
						finalMap.put(indiArr.getLong(5), mandalMap);
					}else{
						Map<Long, KPIVO> mandlMap = finalMap.get(indiArr.getLong(5));
						if(mandlMap.get(indiArr.getLong(0)) == null){
							KPIVO vo = new KPIVO();
							vo.setParentLocationId(indiArr.getString(5));
							vo.setLocationId(indiArr.getString(0));
							vo.setLocationName(indiArr.getString(1));
							
							if(indiArr.getString(2).equalsIgnoreCase("NSS") || indiArr.getString(2).equalsIgnoreCase("NC")){
								vo.setQaAchivement(Long.parseLong(indiArr.getString(3)));
							}else if(indiArr.getString(2).equalsIgnoreCase("PC1") || indiArr.getString(2).equalsIgnoreCase("PC2") 
									|| indiArr.getString(2).equalsIgnoreCase("PC3") || indiArr.getString(2).equalsIgnoreCase("PC4")){
								vo.setPcAchivement(indiArr.getLong(3));
							}
							
							//Map<Long,KPIVO> mandalMap = new HashMap<Long, KPIVO>(0);
							mandlMap.put(indiArr.getLong(0), vo);
						}else{
							KPIVO vo = mandlMap.get(indiArr.getLong(0));
							if(indiArr.getString(2).equalsIgnoreCase("NSS") || indiArr.getString(2).equalsIgnoreCase("NC")){
								vo.setQaAchivement(vo.getQaAchivement()+Long.parseLong(indiArr.getString(3)));
							}else if(indiArr.getString(2).equalsIgnoreCase("PC1") || indiArr.getString(2).equalsIgnoreCase("PC2") 
									|| indiArr.getString(2).equalsIgnoreCase("PC3") || indiArr.getString(2).equalsIgnoreCase("PC4")){
								vo.setPcAchivement(vo.getPcAchivement()+indiArr.getLong(3));
							}
						}
					}
				}
			}
			
			
			///
			if(jobj.getJSONArray("targetData") != null && jobj.getJSONArray("targetData").length() > 0){
				JSONArray acvhiementArr = jobj.getJSONArray("targetData");
				for (int i = 0; i < acvhiementArr.length(); i++) {
					JSONArray indiArr = acvhiementArr.getJSONArray(i);
					if(finalMap.get(indiArr.getLong(5)) == null){
						KPIVO vo = new KPIVO();
						vo.setParentLocationId(indiArr.getString(5));
						vo.setLocationId(indiArr.getString(0));
						vo.setLocationName(indiArr.getString(1));
						
						if(indiArr.getString(2).equalsIgnoreCase("NSS") || indiArr.getString(2).equalsIgnoreCase("NC")){
							vo.setQaTarget(Long.parseLong(indiArr.getString(3)));
						}else if(indiArr.getString(2).equalsIgnoreCase("PC1") || indiArr.getString(2).equalsIgnoreCase("PC2") 
								|| indiArr.getString(2).equalsIgnoreCase("PC3") || indiArr.getString(2).equalsIgnoreCase("PC4")){
							vo.setPcTarget(indiArr.getLong(3));
						}
						
						Map<Long,KPIVO> mandalMap = new HashMap<Long, KPIVO>(0);
						mandalMap.put(indiArr.getLong(0), vo);
						finalMap.put(indiArr.getLong(5), mandalMap);
					}else{
						Map<Long, KPIVO> mandlMap = finalMap.get(indiArr.getLong(5));
						if(mandlMap.get(indiArr.getLong(0)) == null){
							KPIVO vo = new KPIVO();
							vo.setParentLocationId(indiArr.getString(5));
							vo.setLocationId(indiArr.getString(0));
							vo.setLocationName(indiArr.getString(1));
							
							if(indiArr.getString(2).equalsIgnoreCase("NSS") || indiArr.getString(2).equalsIgnoreCase("NC")){
								vo.setQaTarget(Long.parseLong(indiArr.getString(3)));
							}else if(indiArr.getString(2).equalsIgnoreCase("PC1") || indiArr.getString(2).equalsIgnoreCase("PC2") 
									|| indiArr.getString(2).equalsIgnoreCase("PC3") || indiArr.getString(2).equalsIgnoreCase("PC4")){
								vo.setPcTarget(indiArr.getLong(3));
							}
							
							Map<Long,KPIVO> mandalMap = new HashMap<Long, KPIVO>(0);
							mandalMap.put(indiArr.getLong(0), vo);
						}else{
							KPIVO vo = mandlMap.get(indiArr.getLong(0));
							if(indiArr.getString(2).equalsIgnoreCase("NSS") || indiArr.getString(2).equalsIgnoreCase("NC")){
								vo.setQaTarget(vo.getQaTarget()+Long.parseLong(indiArr.getString(3)));
							}else if(indiArr.getString(2).equalsIgnoreCase("PC1") || indiArr.getString(2).equalsIgnoreCase("PC2") 
									|| indiArr.getString(2).equalsIgnoreCase("PC3") || indiArr.getString(2).equalsIgnoreCase("PC4")){
								vo.setPcTarget(vo.getPcTarget()+indiArr.getLong(3));
							}
						}
					}
				}
			}
			
			if(finalMap != null && finalMap.size() > 0){
				for (Entry<Long, Map<Long, KPIVO>> dist : finalMap.entrySet()) {
					Map<Long, KPIVO>  distMap = dist.getValue();
					if(distMap != null && distMap.size() > 0){
						for (Entry<Long, KPIVO> mandalMap : distMap.entrySet()) {
							voList.add(mandalMap.getValue());
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at buildKPIMandalResult - RWSNICService service", e);
		}
	}
	
	public void buildKPIResult(JSONObject jobj,List<KPIVO> voList){
		try {
			Map<String,KPIVO> finalMap = new HashMap<String, KPIVO>(0);
	  		
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
			
		} catch (Exception e) {
			LOG.error("Exception raised at buildKPIResult - RWSNICService service", e);
		}

	}
	
	/*
	 * Date : 18/06/2017
	 * Author :Nagarjuna
	 * @description : getStressedHabitationsInfoByLocationType
	 */
	public StatusVO getStressedHabitationsInfoByLocationType(InputVO vo) {
		StatusVO statusVO = new StatusVO();					
		try{
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getStressedHabitationInfoInALocation");	        
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
 	 	    			List<StatusVO> tempList =getStatusSkeleton();
 	 	    			for(int i=0;i<statusArray.length();i++){
 	 	    				JSONObject jObj = (JSONObject) statusArray.get(i);
 	 	    				StatusVO subVO = getMatchedStatusVO(tempList,jObj.getString("status"));
 	 	    				if(subVO != null){
 	 	    					subVO.setCount(jObj.getLong("count"));//All Habs
 	 	    					subVO.setStressedCount(jObj.getLong("stressedHabitationCount"));
 	 	    					subVO.setPercentage(jObj.getDouble("percentage"));
 	 	    				}
 	 	    			}
 	 	    			statusVO.getStatusList().addAll(tempList);
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
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getStressedKPIDeatils");	        
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
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getLocationBasedOnSelection");
	        
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
	 	    				amsVo.setAlertLevel(jobj.getString("alertLevel"));//impact Scope
	 	    				amsVo.setCreatedDate(jobj.getString("createdDate"));
	 	    				amsVo.setUpdatedDate(jobj.getString("updatedDate"));
	 	    				amsVo.setStatusId(jobj.getLong("statusId"));
	 	    				amsVo.setStatus(jobj.getString("status"));
	 	    				amsVo.setSevertyColor(jobj.getString("severtyColor"));
	 	    				amsVo.setStatusColor(jobj.getString("statusColor"));
	 	    				amsVo.setSource(jobj.getString("source"));
	 	    				amsVo.setOfficer(jobj.getString("problem"));
	 	    				finalList.add(amsVo);
	 	    				
	 	    			}
	 	    		}
				}
				
 	      	}
			
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getAlertsOfCategoryByStatusWise() method, Exception - ",e);
		}
		return finalList;
	}
	
	public List<RwsClickVO> getOnclickWorkDetails(InputVO vo){
		List<RwsClickVO> finalList = new ArrayList<RwsClickVO>();
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getOnclickWorkDetails");
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
		 	    				if(jobj.has("completionDate")){
		 	    					subVo.setCompletionDate(jobj.getString("completionDate"));
		 	    				}
		 	    				if(jobj.has("targetDate")){
		 	    					subVo.setCompletionDate(jobj.getString("targetDate"));
		 	    				}
		 	    				if(jobj.has("commssionedDate")){
		 	    					subVo.setCompletionDate(jobj.getString("commssionedDate"));
		 	    				}
		 	    				if(jobj.has("groundingDate")){
		 	    					subVo.setGroundingDate(jobj.getString("groundingDate"));
		 	    				}
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
			LOG.error("Exception Occured in getOnclickWorkDetails() method, Exception - ",e);
		}
		return finalList;
	}
	
	public List<RwsClickVO> getOnclickTargetsAcheievementsDetails(InputVO vo){
		List<RwsClickVO> finalList = new ArrayList<RwsClickVO>();
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getOnclickTargetsAcheievementsDetails");
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
			LOG.error("Exception Occured in getOnclickTargetsAcheievementsDetails() method, Exception - ",e);
		}
		return finalList;
	}
	
	public List<RwsClickVO> getOnclickStressedTargetsAcheievementsDetails(InputVO vo){
		List<RwsClickVO> finalList = new ArrayList<RwsClickVO>();
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getOnclickStressedTargetsAcheievementsDetails");
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
			LOG.error("Exception Occured in getOnclickStressedTargetsAcheievementsDetails() method, Exception - ",e);
		}
		return finalList;
	}
	
	public List<RwsClickVO> getOnclickHabitationsupplyDetails(InputVO vo){
		List<RwsClickVO> finalList = new ArrayList<RwsClickVO>();
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getOnclickHabitationsupplyDetails");
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
			LOG.error("Exception Occured in getOnclickHabitationsupplyDetails() method, Exception - ",e);
		}
		return finalList;
	}
	//01-07-2017 
	
	public List<RwsClickVO> getSchemeDetailsByTypeOfAssestName(InputVO vo){
		List<RwsClickVO> finalList = new ArrayList<RwsClickVO>();
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getSchemeDetailsByTypeOfAssestName");
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
			 	    				if(i==0)
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
			LOG.error("Exception Occured in getSchemeDetailsByTypeOfAssestName() method, Exception - ",e);
		}
		return finalList;
	}
	
	public List<RwsClickVO> getAssetDetailsByAssetType(InputVO vo){
		List<RwsClickVO> finalList = new ArrayList<RwsClickVO>();
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getAssetDetailsByAssetType");
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
			 	    				if(i == 0 && jobj.has("totalCount")){
			 	    					subVo.setTotalCount(jobj.getString("totalCount"));
			 	    				}
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
			LOG.error("Exception Occured in getAssetDetailsByAssetType() method, Exception - ",e);
		}
		return finalList;
	}
	
	public List<RwsClickVO> getHabitationDetailsByStatusByLocationType(InputVO vo){
		List<RwsClickVO> finalList = new ArrayList<RwsClickVO>();
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getHabitationDetailsByStatusByLocationType");
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
			LOG.error("Exception Occured in getAssetDetailsByAssetType() method, Exception - ",e);
		}
		return finalList;
	}
	
	public List<RwsClickVO> getWaterSourceDeatilsLocationWise(InputVO vo){
		List<RwsClickVO> finalList = new ArrayList<RwsClickVO>();
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getWaterSourceDeatilsLocationWise");
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
			 	    				subVo.setAssestCode(jobj.getString("assestCode"));
			 	    				finalList.add(subVo);
			 	    			}		 	    				
		 	    			}		 	    		
		 	    		}				
				}				
 	      	}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getWaterSourceDeatilsLocationWise() method, Exception - ",e);
		}
		return finalList;
	}
	
	public List<KeyValueVO> getAllPrrwsDistricts(){
		List<KeyValueVO> voList = new ArrayList<KeyValueVO>(0);
		try {
			List<Object[]> districts = districtDAO.getAllDistricts();
			
			if(districts != null && districts.size() > 0){
				for (Object[] objects: districts) {
					KeyValueVO vo = new KeyValueVO();
					vo.setKey((Long)objects[0]);
					vo.setValue(objects[1].toString());
					voList.add(vo);
				}
				
			}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getAllPrrwsDistricts() method, Exception - ",e);
		}
		return voList;
	}
	
	public List<KeyValueVO> getConstituenciesForDistrict(IdNameVO idNameVO){
		List<KeyValueVO> voList = new ArrayList<KeyValueVO>(0);
		try {
			List<Object[]> constituencies = constituencyDAO.getConstituencies(idNameVO.getId());
			
			if(constituencies != null && constituencies.size() > 0){
				for (Object[] objects : constituencies) {
					KeyValueVO vo = new KeyValueVO();
					vo.setKey((Long)objects[0]);
					vo.setValue(objects[1].toString());
					voList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getConstituenciesForDistrict() method, Exception - ",e);
		}
		return voList;
	}
	
	public List<KeyValueVO> getTehsilsForConstituency(IdNameVO idNameVO){
		List<KeyValueVO> voList = new ArrayList<KeyValueVO>(0);
		try {
			List<Object[]> tehsils = tehsilDAO.getTehsilsForConstituency(idNameVO.getId());
			if(tehsils != null && tehsils.size() > 0){
				for (Object[] objects : tehsils) {
					KeyValueVO vo = new KeyValueVO();
					vo.setKey((Long)objects[0]);
					vo.setValue(objects[1].toString());
					voList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getTehsilsForConstituency() method, Exception - ",e);
		}
		return voList;
	}
	
	public List<WaterSourceVO> getWaterSourceDeatils2(InputVO inputVO){
		List<WaterSourceVO> waterSourceVOList = new ArrayList<WaterSourceVO>(0); 
		try {
			
			if (inputVO != null) {
				inputVO = setFilterVal(inputVO);
			}
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getWaterSourceDeatils2");
	        String authStringEnc = getAuthenticationString("admin","admin@123");
        	ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, inputVO);
        
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				
				if(output != null && !output.isEmpty()){
					JSONArray mainArr = new JSONArray(output);
					
					if(mainArr != null && mainArr.length() > 0){
						JSONObject jobj = (JSONObject)mainArr.get(0);
						if(jobj.getString("status").equalsIgnoreCase("Success")){
							if(inputVO.getLocationType().equalsIgnoreCase("mandal")){
								buildWaterSourceMandalTableData(mainArr,waterSourceVOList);
							}else{
								buildWaterSourceTableData(mainArr,waterSourceVOList);
							}
						}
					}
				}
 	      	}
		} catch (Exception e) {
			LOG.error("Exception raised at getWaterSourceDeatils2 - RuralWaterSupplyDashBoardService service", e);
		}
		return waterSourceVOList;
	} 
	
	public void buildWaterSourceMandalTableData(JSONArray jsonArr,List<WaterSourceVO> voList){
		try {
			if(jsonArr != null && jsonArr.length() > 0){
				for (int i = 0; i < jsonArr.length(); i++) {
					JSONObject mainObj = jsonArr.getJSONObject(i);
					if(mainObj.getJSONArray("subList") != null && mainObj.getJSONArray("subList").length() > 0){
						for (int j = 0; j < mainObj.getJSONArray("subList").length(); j++) {
							JSONObject jobj = mainObj.getJSONArray("subList").getJSONObject(j);
							WaterSourceVO vo = new WaterSourceVO();
							vo.setLocationId(jobj.getLong("locationId"));
							vo.setLocationName(jobj.getString("locationName"));
							vo.setSafeSurfaceWaterSourceCount(jobj.getLong("safeSurfaceCount"));
							vo.setUnSafeSurfaceWaterSourceCount(jobj.getLong("unSafeSurfaceCount"));
							vo.setSafeGroundWaterSourceCount(jobj.getLong("safeGroundCount"));
							vo.setUnSafeGroundWaterSourceCount(jobj.getLong("unSafeGroundCount"));
							vo.setTotalSurfaceWaterSourceCount(jobj.getLong("surfaceTotalCount"));
							vo.setTotalGroundWaterSourceCount(jobj.getLong("groundTotalCount"));
							vo.setParentLocationId(mainObj.getLong("locationId"));
							voList.add(vo);
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at buildWaterSourceMandalTableData - RuralWaterSupplyDashBoardService service", e);
		}
	}
	
	public void buildWaterSourceTableData(JSONArray jsonArr,List<WaterSourceVO> voList){
		try {
			if(jsonArr != null && jsonArr.length() > 0){
				for (int i = 0; i < jsonArr.length(); i++) {
					JSONObject jobj = (JSONObject)jsonArr.get(i);
					WaterSourceVO vo = new WaterSourceVO();
					vo.setLocationId(jobj.getLong("locationId"));
					vo.setLocationName(jobj.getString("locationName"));
					vo.setSafeSurfaceWaterSourceCount(jobj.getLong("safeSurfaceCount"));
					vo.setUnSafeSurfaceWaterSourceCount(jobj.getLong("unSafeSurfaceCount"));
					vo.setSafeGroundWaterSourceCount(jobj.getLong("safeGroundCount"));
					vo.setUnSafeGroundWaterSourceCount(jobj.getLong("unSafeGroundCount"));
					vo.setTotalSurfaceWaterSourceCount(jobj.getLong("surfaceTotalCount"));
					vo.setTotalGroundWaterSourceCount(jobj.getLong("groundTotalCount"));
					voList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at buildWaterSourceTableData - RuralWaterSupplyDashBoardService service", e);
		}
	}
	

	@Override
	public List<RwsClickVO> getHamletWiseIvrStatusList(InputVO vo) {
    List<RwsClickVO> ivraHamletList = new ArrayList<RwsClickVO>(0);
    try{
    	WebResource webResource = commonMethodsUtilService.getWebResourceObject("https://mytdp.com/WebService/getHamletWiseIvrStatusList");
    	ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, vo);
        
        if(response.getStatus() != 200){
 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      }else{
			String output = response.getEntity(String.class);
			
			if(output != null && !output.isEmpty()){
				JSONArray finalArray = new JSONArray(output);//Type Array
 	    		if(finalArray!=null && finalArray.length()>0){
 	    			for(int i=0;i<finalArray.length();i++){
 	    				RwsClickVO hamletList = new RwsClickVO();
 	    				JSONObject jobj = (JSONObject)finalArray.get(i);
 	    				
 	    				hamletList.setId(jobj.getLong("stateId"));
 	    				hamletList.setName(jobj.getString("state"));
 	    				hamletList.setDistrictCode(jobj.getString("districtId"));
 	    				hamletList.setDistrictName(jobj.getString("district"));
 	    				hamletList.setConstituencyCode(jobj.getString("constituencyId"));
 	    				hamletList.setConstituencyName(jobj.getString("constituency"));
 	    				hamletList.setMandalCode(jobj.getString("tehsilId"));
 	    				hamletList.setMandalName(jobj.getString("tehsil"));
 	    				hamletList.setPanchayatId(jobj.getLong("panchayatId"));
 	    				hamletList.setPanchayat(jobj.getString("panchayat"));
 	    				hamletList.setHabitationCode(jobj.getString("hamletId"));
 	    				hamletList.setHabitationName(jobj.getString("hamlet"));
 	    				
 	    				ivraHamletList.add(hamletList);
 	    			}
 	    		}
 	    	}
			
		}
	}catch(Exception e){
		LOG.error("Exception Occured in getHamletWiseIvrStatusList() method, Exception - ",e);
    }
		return ivraHamletList;
	}	

	public InputVO setFilterVal(InputVO inputVO){

		try{
			 String filterValue =null;
			 if(inputVO.getFilterType()!= null && inputVO.getFilterValue()!=null && inputVO.getFilterType().trim().length()>0 && 
					 inputVO.getFilterValue().trim().length()>0){
				 if(inputVO.getFilterType().trim().equalsIgnoreCase(IConstants.MANDAL)){
					 filterValue = rwsTehsilDAO.getRwsCode(Long.valueOf(inputVO.getFilterValue().trim()));
					 inputVO.setFilterValue(filterValue);
				 }else if(inputVO.getFilterType().trim().equalsIgnoreCase(IConstants.DISTRICT)){
					 filterValue = rwsDistrictDAO.getRwsCode(Long.valueOf(inputVO.getFilterValue().trim()));
					 inputVO.setFilterValue(filterValue);
				 }else if(inputVO.getFilterType().trim().equalsIgnoreCase(IConstants.CONSTITUENCY)){
					 filterValue = rwsConstituencyDAO.getRwsCode(Long.valueOf(inputVO.getFilterValue().trim()));
					 inputVO.setFilterValue(filterValue);
				 }
				 
			 }
			 if(inputVO.getDistrictValue()!= null  && inputVO.getDistrictValue().trim().length()>0){
				 String districtValue = rwsDistrictDAO.getRwsCode(Long.valueOf(inputVO.getDistrictValue().trim()));
				 inputVO.setDistrictValue(districtValue);
			 }
			 return inputVO;
		}catch(Exception e){
			LOG.error("Exception Occured in setFilterVal() method, Exception - ",e);
			return inputVO;
		}
	}
	
	public String convertingInputVOToString(InputVO inputVO){
		String str = "";
		try {
			/*if(inputVO.getLocationId() != null)
				if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("district")){
					if(inputVO.getLocationId().longValue() > 0l && inputVO.getLocationId().longValue() <= 9l)
						inputVO.setLocationIdStr("0"+inputVO.getLocationId().toString());
				}else if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("constituency")){
					if(inputVO.getLocationId().longValue() > 0l)
						inputVO.setLocationIdStr("0"+inputVO.getLocationId().toString());
				}*/
				
			str = "{";
			
			if(inputVO.getFromMonth() != null )
				str += "\"fromMonth\" : \""+inputVO.getFromMonth()+"\",";
			if(inputVO.getToMonth() != null)
				str += "\"toMonth\" : \""+inputVO.getToMonth()+"\",";
			if(inputVO.getLocation() != null)
				str += "\"Location\" : \""+inputVO.getLocation()+"\",";
			/*if(inputVO.getLocationIdStr() != null)
				str += "\"locationId\" : \""+inputVO.getLocationIdStr()+"\",";else*/
			 if(inputVO.getLocationId() != null)
				str += "\"locationID\" : \""+inputVO.getLocationId()+"\",";
			if(inputVO.getSubLocation() != null)
				str += "\"subLocation\" : \""+inputVO.getSubLocation()+"\",";
			
			if(str.length() > 1)
				str = str.substring(0,str.length()-1);
			
			str += "}";
			
		} catch (Exception e) {
			LOG.error("Exception Occured in convertingInputVOToString() method, Exception - ",e);
		}
		return str;
	}
	
	/**
	  * @param  InputVO inputVO which contain fromMonth,toMonth,location and locationId
	  * @return NregaLocationOverviewVO
	  * @author Nandhini 
	  * @Description :This Service Method is used to get SwachaBharat(IHHL) Abstract And Overview Data(MGNREGSTCS Service).
	  * @since 29/7/19
	  */
	public NregaLocationOverviewVO getIHHLOverviewData(InputVO inputVO){
		NregaLocationOverviewVO resultVO = new NregaLocationOverviewVO();
		try {
			
			String str = convertingInputVOToString(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService("http://125.17.121.167/rwsapwebapi/api/Home/Overview_IHHLData", str);
			
		    if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject jsonObject = new JSONObject(output);
	 	    		JSONArray abstArr = jsonObject.getJSONArray("MinisterDashBoardStateOverView");
	 	    		JSONArray overViewArr = jsonObject.getJSONArray("MinisterDashBoardLocOverView");
	 	    		//Abstarct
	 	    		if(abstArr != null && abstArr.length() > 0){
	 	    			resultVO.setSubList1(new ArrayList<NregaLocationOverviewVO>());
	 	    			for(int i=0;i<abstArr.length();i++){
	 	    				JSONObject jObj = (JSONObject) abstArr.get(i);
	 	    				NregaLocationOverviewVO vo = getIHHLAbstractData(jObj);
	 	    				resultVO.getSubList1().add(vo);
	 	    			}	
	 	    		}
	 	    		//OverView Data
	 	    		if(overViewArr != null && overViewArr.length() > 0){
	 	    			resultVO.setSubList2(new ArrayList<NregaLocationOverviewVO>());
	 	    			for(int i=0;i<overViewArr.length();i++){
	 	    				JSONObject jObj = (JSONObject) overViewArr.get(i);
	 	    				NregaLocationOverviewVO vo = getIHHLOverviewData(jObj);
	 	    				resultVO.getSubList2().add(vo);
	 	    			}	
	 	    		}
	 	    	}
	 	      }  
		} catch (Exception e) {
			LOG.error("Exception Occured in getIHHLOverviewData() method, Exception - ",e);
		}
		return resultVO;
	}
	public NregaLocationOverviewVO getIHHLAbstractData(JSONObject jObj){
		NregaLocationOverviewVO vo = new NregaLocationOverviewVO();
		try {
			    vo.setTarget(jObj.getLong("TARGET"));
				vo.setGrounded(jObj.getLong("GROUNDED"));
				vo.setNoTGrounded(jObj.getLong("NOT_GROUNDED"));
				vo.setCompleted(jObj.getLong("COMPLETED"));
				vo.setPercentage(new BigDecimal(jObj.getString("PERC")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		} catch(Exception e){
			LOG.error("Exception Occured in getIHHLAbstractData() method, Exception - ",e);
		}
		return vo;
	}
	public NregaLocationOverviewVO getIHHLOverviewData(JSONObject jObj){
		NregaLocationOverviewVO vo = new NregaLocationOverviewVO();
		try {
			    vo.setType(jObj.getString("TYPE"));
				vo.setGreen(jObj.getLong("GREEN"));
				vo.setRed(jObj.getLong("RED"));
				vo.setOrange(jObj.getLong("ORANGE"));
				vo.setTotal(jObj.getLong("TOTAL"));
		} catch(Exception e){
			LOG.error("Exception Occured in getIHHLOverviewData() method, Exception - ",e);
		}
		return vo;
	}
	/**
	  * @param  InputVO inputVO which contain fromMonth,toMonth,location,locationId and subLocation
	  * @return List<NregaLocationOverviewVO>
	  * @author Nandhini 
	  * @Description :This Service Method is used to get SwachhABharat(IHHL) location Level wise details(MGNREGSTCS Service).
	  * @since 29/7/19
	  */
	public List<NregaLocationOverviewVO> getIHHLlocationLvlWiseData(InputVO inputVO){
		List<NregaLocationOverviewVO> resultList = new ArrayList<NregaLocationOverviewVO>(0);
		try {
			
			String str = convertingInputVOToString(inputVO);
			ClientResponse response = webServiceUtilService.callWebService("http://125.17.121.167/rwsapwebapi/api/values/Location_IHHLData", str);
			
			if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject jsonObject = new JSONObject(output);
	 	    		JSONArray locationDtlsArr = jsonObject.getJSONArray("MinisterDashBoardLocationData");
	 	    		if(locationDtlsArr != null && locationDtlsArr.length() > 0){
	 	    			for(int i=0;i<locationDtlsArr.length();i++){
	 	    				NregaLocationOverviewVO locationVO = new NregaLocationOverviewVO();
	 	    				JSONObject jObj = (JSONObject) locationDtlsArr.get(i);
	 	    				locationVO.setUniqueId(jObj.getString("UNIQUEID"));
	 	    				locationVO.setDistrict(jObj.getString("DISTRICT"));
	 	    				locationVO.setConstituency(jObj.getString("CONSTITUENCY"));
	 	    				locationVO.setMandal(jObj.getString("MANDAL"));
	 	    				locationVO.setPanchayt(jObj.getString("PANCHAYAT"));
	 	    				locationVO.setTarget(jObj.getLong("TARGET"));
	 	    				locationVO.setGrounded(jObj.getLong("GROUNDED"));
	 	    				locationVO.setNoTGrounded(jObj.getLong("NOT_GROUNDED"));
	 	    				locationVO.setCompleted(jObj.getLong("COMPLETED"));
	 	    				locationVO.setPercentage(new BigDecimal(jObj.getString("PERC")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				resultList.add(locationVO);
	 	    			}	
	 	    		}
	 	    	}
	 	      }  
		} catch (Exception e) {
			LOG.error("Exception Occured in getIHHLlocationLvlWiseData() method, Exception - ",e);
		}
		return resultList;
	}
	/**
	  * @param  InputVO inputVO which contain fromDate,toDate,location,locationId and subLocationType
	  * @return NregaLocationOverviewVO
	  * @author Nandhini 
	  * @Description :This Service Method is used to get SwachaBharat(Payments) Abstract(MGNREGSTCS Service).
	  * @since 30/08/19
	  */
	public NregaLocationOverviewVO getSBPaymentsAbstract(InputVO inputVO){
		NregaLocationOverviewVO resultVO = new NregaLocationOverviewVO();
		try {
			
			String str = cvertingIpVOToStringFrSBPayments(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService("http://125.17.121.167/rwsapwebapi/api/PayOverview/GetPayOverviewDetails", str);
			
		    if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject jsonObject = new JSONObject(output);
	 	    		JSONArray abstArr = jsonObject.getJSONArray("PayOverviewDetailsData");
	 	    		//JSONArray overViewArr = jsonObject.getJSONArray("MinisterDashBoardLocOverView");
	 	    		//Abstarct
	 	    		if(abstArr != null && abstArr.length() > 0){
	 	    			for(int i=0;i<abstArr.length();i++){
	 	    				JSONObject jObj = (JSONObject) abstArr.get(i);
		 	    				resultVO.setTotalFTO(jObj.getString("TOTAL_FTOS"));
		 	    				resultVO.setTotalAmount(convertRupeesIntoLakhes(jObj.getString("TOTAL_AMOUNT")));
		 	    				resultVO.setPaidFTO(jObj.getString("PAID_FTOS"));
		 	    				resultVO.setPaidAmount(convertRupeesIntoLakhes(jObj.getString("PAID_AMOUNT")));
		 	    				resultVO.setPendingFTO(jObj.getString("YETTOBE_PAID_FTOS"));
		 	    				resultVO.setPendingAmount(convertRupeesIntoLakhes(jObj.getString("YETTOBE_PAID_AMOUNT")));
		 	    				
		 	    				resultVO.setTtlAmt(jObj.getDouble("TOTAL_AMOUNT"));
		 	    				resultVO.setPaidAmt(jObj.getDouble("PAID_AMOUNT"));
		 	    				resultVO.setPndgAmt(jObj.getDouble("YETTOBE_PAID_AMOUNT"));
		 	    				resultVO.setTtlFTO(jObj.getDouble("TOTAL_FTOS"));
		 	    				resultVO.setPaiidFTO(jObj.getDouble("PAID_FTOS"));
		 	    				resultVO.setPndgFTO(jObj.getDouble("YETTOBE_PAID_FTOS"));
	 	    			}	
	 	    		}
	 	    	}
	 	      }  
		} catch (Exception e) {
			LOG.error("Exception Occured in getSBPaymentsAbstract() method, Exception - ",e);
		}
		return resultVO;
	}
	
	public String cvertingIpVOToStringFrSBPayments(InputVO inputVO){
		String str = "";
		try {
			/*if(inputVO.getLocationId() != null)
				if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("district")){
					if(inputVO.getLocationId().longValue() > 0l && inputVO.getLocationId().longValue() <= 9l)
						inputVO.setLocationIdStr("0"+inputVO.getLocationId().toString());
				}else if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("constituency")){
					if(inputVO.getLocationId().longValue() > 0l)
						inputVO.setLocationIdStr("0"+inputVO.getLocationId().toString());
				}*/
				
			str = "{";
			
			if(inputVO.getFromDate() != null )
				str += "\"FROMDATE\" : \""+inputVO.getFromDate()+"\",";
			if(inputVO.getToDate() != null)
				str += "\"TODATE\" : \""+inputVO.getToDate()+"\",";
			if(inputVO.getLocation() != null)
				str += "\"Location\" : \""+inputVO.getLocation()+"\",";
			/*if(inputVO.getLocationIdStr() != null)
				str += "\"locationId\" : \""+inputVO.getLocationIdStr()+"\",";else*/
			 if(inputVO.getLocationId() != null)
				str += "\"LocationID\" : \""+inputVO.getLocationId()+"\",";
			if(inputVO.getSubLocation() != null)
				str += "\"SUBLOCATION\" : \""+inputVO.getSubLocation()+"\",";
			
			if(str.length() > 1)
				str = str.substring(0,str.length()-1);
			
			str += "}";
			
		} catch (Exception e) {
			LOG.error("Exception Occured in cvertingIpVOToStringFrSBPayments() method, Exception - ",e);
		}
		return str;
	}
	public String convertRupeesIntoLakhes(String value){
		String returnVal = null;
		try {
			if(value != null){
				returnVal = new BigDecimal(Long.valueOf(value)/100000.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				returnVal = returnVal+" L";
			}
		} catch (Exception e) {
			LOG.error("Exception raised at convertRupeesIntoLakhes - NREGSTCSService service", e);
		}
		return returnVal;
	}
	
	public String convertRupeesIntoLakhesStr(String value){
		String returnVal = null;
		try {
			if(value != null){
				returnVal = new BigDecimal(Long.valueOf(value)/100000.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				//returnVal = returnVal;
			}
		} catch (Exception e) {
			LOG.error("Exception raised at convertRupeesIntoLakhes - NREGSTCSService service", e);
		}
		return returnVal;
	}
	
	/**
	  * @param  InputVO inputVO which contain fromDate,toDate,location,locationId and subLocationType
	  * @return List<NregaLocationOverviewVO>
	  * @author Nandhini 
	  * @Description :This Service Method is used to get SwachaBharat(Payments) LevelsData(MGNREGSTCS Service).
	  * @since 30/08/19
	  */
	public List<NregaLocationOverviewVO> getSBPaymentsLevelsWiseData(InputVO inputVO){
		List<NregaLocationOverviewVO> returnList = new ArrayList<NregaLocationOverviewVO>();
		try {
			
			String str = cvertingIpVOToStringFrSBPayments(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService("http://125.17.121.167/rwsapwebapi/api/PayOverview/GetPayOverviewDetails", str);
			
		    if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject jsonObject = new JSONObject(output);
	 	    		JSONArray dataArr = jsonObject.getJSONArray("PayOverviewDetailsData");
	 	    		if(dataArr != null && dataArr.length() > 0){
	 	    			for(int i=0;i<dataArr.length();i++){
	 	    				JSONObject jObj = (JSONObject) dataArr.get(i);
	 	    				NregaLocationOverviewVO vo = new NregaLocationOverviewVO();
	 	    				if(inputVO.getSubLocation() != null && !inputVO.getSubLocation().trim().equalsIgnoreCase("state")){
	 	    					vo.setDistrictId(jObj.getString("DID"));
	 	    					vo.setDistrict(jObj.getString("DNAME"));
	 	    				}
	 	    				if(inputVO.getSubLocation() != null && !(inputVO.getSubLocation().trim().equalsIgnoreCase("state") || inputVO.getSubLocation().trim().equalsIgnoreCase("district"))){
	 	    					vo.setConstituencyId(jObj.getString("ACODE"));
	 	    					vo.setConstituency(jObj.getString("ANAME"));
	 	    				}
	 	    				if(inputVO.getSubLocation() != null && !(inputVO.getSubLocation().trim().equalsIgnoreCase("state") || inputVO.getSubLocation().trim().equalsIgnoreCase("district") || inputVO.getSubLocation().trim().equalsIgnoreCase("constituency"))){
	 	    					vo.setMandalId(jObj.getString("MID"));
	 	    					vo.setMandal(jObj.getString("MNAME"));
	 	    				}
	 	    				if(inputVO.getSubLocation() != null && inputVO.getSubLocation().trim().equalsIgnoreCase("panchayat")){
	 	    					vo.setPanchayatId(jObj.getString("PID"));
	 	    					vo.setPanchayt(jObj.getString("GRAMPANCHAYATNAMEENG"));
	 	    				}
	 	    				
	 	    				if(inputVO.getSubLocation() != null && (inputVO.getSubLocation().trim().equalsIgnoreCase("state") || inputVO.getSubLocation().trim().equalsIgnoreCase("district") || inputVO.getSubLocation().trim().equalsIgnoreCase("constituency"))){
	 	    					vo.setTotalAmount(convertRupeesIntoLakhesStr(jObj.getString("TOTAL_AMOUNT")));
	 	    					vo.setPaidAmount(convertRupeesIntoLakhesStr(jObj.getString("PAID_AMOUNT")));
	 	    					vo.setPendingAmount(convertRupeesIntoLakhesStr(jObj.getString("YETTOBE_PAID_AMOUNT")));
	 	    				}
	 	    				else{
	 	    					vo.setTotalAmount(jObj.getString("TOTAL_AMOUNT"));
	 	    					vo.setPaidAmount(jObj.getString("PAID_AMOUNT"));
	 	    					vo.setPendingAmount(jObj.getString("YETTOBE_PAID_AMOUNT"));
	 	    				}
	 	    				vo.setTotalFTO(jObj.getString("TOTAL_FTOS"));
	 	    				vo.setPaidFTO(jObj.getString("PAID_FTOS"));
	 	    				vo.setPendingFTO(jObj.getString("YETTOBE_PAID_FTOS"));
	 	    				/*
	 	    				if(jObj.getString("TOTAL_AMOUNT") != null && jObj.getString("TOTAL_AMOUNT").length() >= 6)
	 	    					vo.setTotalAmount(convertRupeesIntoLakhesStr(jObj.getString("TOTAL_AMOUNT")));
	 	    				else
	 	    					vo.setTotalAmount(jObj.getString("TOTAL_AMOUNT"));
	 	    				vo.setPaidFTO(jObj.getString("PAID_FTOS"));
	 	    				if(jObj.getString("PAID_AMOUNT") != null && jObj.getString("PAID_AMOUNT").length() >= 6)
	 	    					vo.setPaidAmount(convertRupeesIntoLakhesStr(jObj.getString("PAID_AMOUNT")));
	 	    				else
	 	    					vo.setPaidAmount(jObj.getString("PAID_AMOUNT"));
	 	    				vo.setPendingFTO(jObj.getString("YETTOBE_PAID_FTOS"));
	 	    				if(jObj.getString("YETTOBE_PAID_AMOUNT") != null && jObj.getString("YETTOBE_PAID_AMOUNT").length() >= 6)
	 	    					vo.setPendingAmount(convertRupeesIntoLakhesStr(jObj.getString("YETTOBE_PAID_AMOUNT")));
	 	    				else
	 	    					vo.setPendingAmount(jObj.getString("YETTOBE_PAID_AMOUNT"));
	 	    				*/
	 	    				returnList.add(vo);
	 	    			}	
	 	    		}
	 	    	}
	 	      }  
		} catch (Exception e) {
			LOG.error("Exception Occured in getSBPaymentsLevelsWiseData() method, Exception - ",e);
		}
		return returnList;
	}
	
 }
