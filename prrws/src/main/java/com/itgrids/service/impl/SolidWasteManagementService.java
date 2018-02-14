package com.itgrids.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonArray;
import com.itgrids.dao.IWebServiceDataDAO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.SolidWasteManagementVO;
import com.itgrids.dto.WebServiceDataVO;
import com.itgrids.model.WebServiceData;
import com.itgrids.service.ISolidWasteManagementService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.DateUtilService;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


@Service
@Transactional
public class SolidWasteManagementService implements ISolidWasteManagementService {
	

	private static final Logger LOG = Logger.getLogger( SolidWasteManagementService.class);

	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	@Autowired
	private DateUtilService dateUtilService;
	@Autowired
	private  IWebServiceDataDAO  webServiceDataDAO;
	
	/*
	 * Date : 7/11/2017
	*/
	@Override
	public List<SolidWasteManagementVO> getSolidInfoLocationWise(InputVO inputVO) {
		List<SolidWasteManagementVO> finalList = new ArrayList<SolidWasteManagementVO>(0);	
		
		try {
			WebResource webResource = null;
			Map<String ,SolidWasteManagementVO> locationMap= new HashMap<String, SolidWasteManagementVO>();
				if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("district"))
					webResource = commonMethodsUtilService.getWebResourceObject("https://pris.ap.gov.in/survey/api/swmapi.php?getSwmInfo=true&locationId="+inputVO.getLocationId()+"&locationType="+inputVO.getLocationType()+"&filterType="+inputVO.getFilterType()+"&filterId="+inputVO.getFilterId()+"&subFilterType="+inputVO.getSubFilterType()+"&subFilterId="+inputVO.getSubFilterId()+"&fromDate="+inputVO.getFromDate()+"&toDate="+inputVO.getToDate());
				else if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("constituency"))
					webResource = commonMethodsUtilService.getWebResourceObject("https://pris.ap.gov.in/survey/api/swmapi.php?getSwmInfo=true&locationId="+inputVO.getLocationId()+"&locationType=assembly&filterType="+inputVO.getFilterType()+"&filterId="+inputVO.getFilterId()+"&fromDate="+inputVO.getFromDate()+"&toDate="+inputVO.getToDate());
				else if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("mandal"))
					webResource = commonMethodsUtilService.getWebResourceObject("https://pris.ap.gov.in/survey/api/swmapi.php?getSwmInfo=true&locationId="+inputVO.getLocationId()+"&locationType="+inputVO.getLocationType()+"&fromDate="+inputVO.getFromDate()+"&toDate="+inputVO.getToDate());
				
			    ClientResponse response = webResource.accept("application/json").type("application/json").get(ClientResponse.class);
		    
	        	if(response.getStatus() != 200){
	        		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	             	}else{
	        		String output = response.getEntity(String.class);
	        		if(output != null && !output.isEmpty()){
	        			JSONArray finalArray = new JSONArray(output);
	        			if(finalArray!=null && finalArray.length()>0){ 
	        				for(int i=0;i<finalArray.length();i++){
	        					JSONObject jObj = (JSONObject) finalArray.get(i);
	        					//solidWasteManagementVO=null;
	        					//SolidWasteManagementVO solidWasteManagementVO = locationMap.get(jObj.getString("name"));	        						
        						//if(solidWasteManagementVO == null){
	        					SolidWasteManagementVO   solidWasteManagementVO=new SolidWasteManagementVO();
	        					solidWasteManagementVO.setId(!jObj.getString("id").equalsIgnoreCase("null") ? jObj.getString("id"):"");
	        					solidWasteManagementVO.setName(!jObj.getString("name").equalsIgnoreCase("null") ? jObj.getString("name") : "");	        					
	        					solidWasteManagementVO.setRfidTags(!jObj.getString("rfid_tags").equalsIgnoreCase("null") ? jObj.getLong("rfid_tags") : 0l);
	        					solidWasteManagementVO.setFarmers(!jObj.getString("farmers").equalsIgnoreCase("null") ? jObj.getLong("farmers") : 0l);
	        					//solidWasteManagementVO.setRfidTracking(!jObj.getString("rfidTracking").equalsIgnoreCase("null") ? jObj.getLong("rfidTracking") : 0l);
	        					solidWasteManagementVO.setSwmCollection(!jObj.getString("swmCollection").equalsIgnoreCase("null") ? jObj.getDouble("swmCollection") : 0.00);
	        					solidWasteManagementVO.setNadap(!jObj.getString("nadap").equalsIgnoreCase("null") ? jObj.getDouble("nadap") : 0.00);
	        					solidWasteManagementVO.setVermi(!jObj.getString("vermi").equalsIgnoreCase("null") ? jObj.getDouble("vermi") : 0.00);
	        					solidWasteManagementVO.setVermiStock(!jObj.getString("vermiStock").equalsIgnoreCase("null") ? jObj.getDouble("vermiStock") : 0.00);     					
	        					solidWasteManagementVO.setHouseCollecion(!jObj.getString("houseCollecion").equalsIgnoreCase("null") ?jObj.getDouble("houseCollecion") : 0.00);  
		 	    				solidWasteManagementVO.setFarmerCollection(!jObj.getString("farmerCollection").equalsIgnoreCase("null") ?jObj.getDouble("farmerCollection") : 0.00);
		 	    				solidWasteManagementVO.setMgnres(!jObj.getString("mgnres").equalsIgnoreCase("null")  ?jObj.getLong("mgnres") : 0l);
		 	    				solidWasteManagementVO.setPr(!jObj.getString("pr").equalsIgnoreCase("null") ?jObj.getLong("pr") : 0l);
		 	    				solidWasteManagementVO.setPublicType(!jObj.getString("public").equalsIgnoreCase("null") ?jObj.getLong("public") : 0l);
		 	    				solidWasteManagementVO.setOnekg(!jObj.getString("1kg").equalsIgnoreCase("null") ?jObj.getDouble("1kg") : 0.00);
		 	    				solidWasteManagementVO.setFivekg(!jObj.getString("5kg").equalsIgnoreCase("null") ?jObj.getDouble("5kg") : 0.00);
		 	    				solidWasteManagementVO.setTenkg(!jObj.getString("10kg").equalsIgnoreCase("null")?jObj.getDouble("10kg") : 0.00);
		 	    				solidWasteManagementVO.setTwentyFivekg(!jObj.getString("25kg").equalsIgnoreCase("null") ?jObj.getDouble("25kg") : 0.00);
		 	    				solidWasteManagementVO.setFiftykg(!jObj.getString("50kg").equalsIgnoreCase("null") ?jObj.getDouble("50kg") : 0.00);
		 	    				solidWasteManagementVO.setTractor(!jObj.getString("tractor").equalsIgnoreCase("null") ?jObj.getLong("tractor") : 0l);
		 	    				solidWasteManagementVO.setAuto(!jObj.getString("auto").equalsIgnoreCase("null") ?jObj.getLong("auto") : 0l);
		 	    				solidWasteManagementVO.setTricycle(!jObj.getString("tricycle").equalsIgnoreCase("null") ?jObj.getLong("tricycle") : 0l);
		 	    				solidWasteManagementVO.setEvehicle(!jObj.getString("evehicle").equalsIgnoreCase("null") ?jObj.getLong("evehicle") : 0l);
		 	    				solidWasteManagementVO.setBlocks(!jObj.getString("blocks").equalsIgnoreCase("null") ?jObj.getLong("blocks") : 0l);
		 	    			    finalList.add(solidWasteManagementVO);
		 	    			    //locationMap.put(jObj.getString("name"), solidWasteManagementVO);
		 	    				// finalList.add(solidWasteManagementVO);
		 	    			     // finalList.addAll(locationMap.values());
	        			          /*}else{
	        			    	   solidWasteManagementVO.setRfidTags(solidWasteManagementVO.getRfidTags()+Long.valueOf(!jObj.getString("rfid_tags").equalsIgnoreCase("null") ? jObj.getLong("rfid_tags") : 0L));
		        					solidWasteManagementVO.setFarmers(solidWasteManagementVO.getFarmers()+Long.valueOf(!jObj.getString("farmers").equalsIgnoreCase("null") ? jObj.getLong("farmers") : 0l));
		        					//solidWasteManagementVO.setRfidTracking(!jObj.getString("rfidTracking").equalsIgnoreCase("null") ? jObj.getLong("rfidTracking") : 0l);
		        					solidWasteManagementVO.setSwmCollection(solidWasteManagementVO.getSwmCollection()+Double.valueOf(!jObj.getString("swmCollection").equalsIgnoreCase("null") ? jObj.getDouble("swmCollection") : 0.00));
		        					solidWasteManagementVO.setNadap(solidWasteManagementVO.getNadap()+Double.valueOf(!jObj.getString("nadap").equalsIgnoreCase("null") ? jObj.getDouble("nadap") : 0.00));
		        					solidWasteManagementVO.setVermi(solidWasteManagementVO.getVermi()+Double.valueOf(!jObj.getString("vermi").equalsIgnoreCase("null") ? jObj.getDouble("vermi") : 0.00));
		        					solidWasteManagementVO.setVermiStock(solidWasteManagementVO.getVermi()+Double.valueOf(!jObj.getString("vermiStock").equalsIgnoreCase("null") ? jObj.getDouble("vermiStock") : 0.00));     					
		        					solidWasteManagementVO.setHouseCollecion(solidWasteManagementVO.getHouseCollecion()+Double.valueOf(!jObj.getString("houseCollecion").equalsIgnoreCase("null") ?jObj.getDouble("houseCollecion") : 0.00));  
			 	    	
		        					
		        					solidWasteManagementVO.setFarmerCollection(solidWasteManagementVO.getFarmerCollection()+Double.valueOf(!jObj.getString("farmerCollection").equalsIgnoreCase("null") ?jObj.getDouble("farmerCollection") : 0.00));
			 	    				solidWasteManagementVO.setMgnres(solidWasteManagementVO.getMgnres()+Long.valueOf(!jObj.getString("mgnres").equalsIgnoreCase("null")  ?jObj.getLong("mgnres") : 0l));
			 	    				solidWasteManagementVO.setPr(solidWasteManagementVO.getPr()+Long.valueOf(!jObj.getString("pr").equalsIgnoreCase("null") ?jObj.getLong("pr") : 0l));
			 	    				solidWasteManagementVO.setPublicType(solidWasteManagementVO.getPublicType()+Long.valueOf(!jObj.getString("public").equalsIgnoreCase("null") ?jObj.getLong("public") : 0l));
			 	    				solidWasteManagementVO.setOnekg(solidWasteManagementVO.getOnekg()+Double.valueOf(!jObj.getString("1kg").equalsIgnoreCase("null") ?jObj.getDouble("1kg") : 0.00));
			 	    				solidWasteManagementVO.setFivekg(solidWasteManagementVO.getFivekg()+Double.valueOf(!jObj.getString("5kg").equalsIgnoreCase("null") ?jObj.getDouble("5kg") : 0.00));
			 	    				solidWasteManagementVO.setTenkg(solidWasteManagementVO.getTenkg()+Double.valueOf(!jObj.getString("10kg").equalsIgnoreCase("null")?jObj.getDouble("10kg") : 0.00));
			 	    				solidWasteManagementVO.setTwentyFivekg(solidWasteManagementVO.getTwentyFivekg()+Double.valueOf(!jObj.getString("25kg").equalsIgnoreCase("null") ?jObj.getDouble("25kg") : 0.00));
			 	    				solidWasteManagementVO.setFiftykg(solidWasteManagementVO.getFiftykg()+Double.valueOf(!jObj.getString("50kg").equalsIgnoreCase("null") ?jObj.getDouble("50kg") : 0.00));
			 	    				solidWasteManagementVO.setTractor(solidWasteManagementVO.getTractor()+Long.valueOf(!jObj.getString("tractor").equalsIgnoreCase("null") ?jObj.getLong("tractor") : 0l));
			 	    				solidWasteManagementVO.setAuto(solidWasteManagementVO.getAuto()+Long.valueOf(!jObj.getString("auto").equalsIgnoreCase("null") ?jObj.getLong("auto") : 0l));
			 	    				solidWasteManagementVO.setTricycle(solidWasteManagementVO.getTricycle()+Long.valueOf(!jObj.getString("tricycle").equalsIgnoreCase("null") ?jObj.getLong("tricycle") : 0l));
			 	    				solidWasteManagementVO.setEvehicle(solidWasteManagementVO.getEvehicle()+Long.valueOf(!jObj.getString("evehicle").equalsIgnoreCase("null") ?jObj.getLong("evehicle") : 0l));
			 	    				solidWasteManagementVO.setBlocks(solidWasteManagementVO.getBlocks()+Long.valueOf(!jObj.getString("blocks").equalsIgnoreCase("null") ?jObj.getLong("blocks") : 0l));
	        			       }*/
	        				}
	        			}
	        		}
	        	}
	          	
				 /*List<SolidWasteManagementVO>  rfidDate = getRfidTrackingOverAllTargets(inputVO);
			     if(rfidDate!=null && !rfidDate.isEmpty()){
			     for (SolidWasteManagementVO solidWasteManagementVO2 : rfidDate) {
			    	 SolidWasteManagementVO  solidWasteManagementVO1 = locationMap.get(solidWasteManagementVO2.getLocationName());	        						
						if(solidWasteManagementVO1 != null){
							solidWasteManagementVO1.setAvertrackingPer(solidWasteManagementVO2.getAvertrackingPer());
							solidWasteManagementVO1.setTotalRfidTags(solidWasteManagementVO2.getTotalRfidTags());
							solidWasteManagementVO1.setTrackingPer(solidWasteManagementVO2.getTrackingPer());
							solidWasteManagementVO1.setBlockNo(solidWasteManagementVO2.getBlockNo());
							solidWasteManagementVO1.setInTime(solidWasteManagementVO2.getInTime());
							solidWasteManagementVO1.setOutTime(solidWasteManagementVO2.getOutTime());
							solidWasteManagementVO1.setOutOfTarget(solidWasteManagementVO2.getOutOfTarget());
							solidWasteManagementVO1.setTarget(solidWasteManagementVO2.getTarget());
							solidWasteManagementVO1.setAverageInTime(solidWasteManagementVO2.getAverageInTime());
							solidWasteManagementVO1.setAverageOutTime(solidWasteManagementVO2.getAverageOutTime());
							solidWasteManagementVO1.setAverageTarget(solidWasteManagementVO2.getAverageTarget());
							solidWasteManagementVO1.setTotalTime(solidWasteManagementVO2.getTotalTime());	
							solidWasteManagementVO1.setGpCnt(solidWasteManagementVO1.getGpCnt()+1);   	
							
						}
					
			     	}
			   }   
	        	
	        if(locationMap!=null){
	        	finalList = new ArrayList<SolidWasteManagementVO>(locationMap.values());
	        }
	        	*/
	       List<SolidWasteManagementVO>   data = getRfidTrackingOverAllTargetsData(inputVO);
	       for (SolidWasteManagementVO rfidData : data) {
	    	   for (SolidWasteManagementVO finalVo : finalList) {
	    		   if(finalVo.getId().trim().toString().equalsIgnoreCase(rfidData.getLocationId().toString().trim())){
	    			   finalVo.setTotalRfidTags(rfidData.getTotalRfidTags());	
	    			   finalVo.setBlockNo(rfidData.getBlockNo());
	    			   finalVo.setInTime(rfidData.getInTime());
	    			   finalVo.setOutTime(rfidData.getOutTime());
	    			   finalVo.setOutOfTarget(rfidData.getOutOfTarget());
	    			   finalVo.setTarget(rfidData.getTarget());
	    			   finalVo.setAchieve(rfidData.getAchieve());
	    			   finalVo.setAverageInTime(rfidData.getAverageInTime());
	    			   finalVo.setAverageOutTime(rfidData.getAverageOutTime());
	    			   finalVo.setAverageTarget(rfidData.getAverageTarget());
	    			  // finalVo.setTotalTime(rfidData.getInTime());
	    			   finalVo.setTrackingPer(rfidData.getTrackingPer());
	    			   finalVo.setAvertrackingPer(rfidData.getAvertrackingPer());
	    			   finalVo.setInTimePer(rfidData.getInTimePer());
	    			   finalVo.setOutTimePer(rfidData.getOutTimePer());
   					   finalVo.setGpCnt(rfidData.getGpCnt()+1);   					
	    		   }
				
			}
			
		}
		}catch (JSONException e) {
	        		 	   LOG.error("Exception raised at getSolidInfoLocationWise - SolidWasteManagementService service", e);
	           			}
	    return  finalList;	
	   }
	
	/*
	 * Date : 07/11/2017
	 * Author :Hymavathi
	 * @description : getSolidWasteManagementOverAllCounts
	 */
	@Override
	public SolidWasteManagementVO getSolidWasteManagementOverAllCounts(InputVO inputVO) {
		SolidWasteManagementVO solidWasteManagementVO= new SolidWasteManagementVO();
		
		try {
			                                                                      
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("https://pris.ap.gov.in/survey/api/swmapi.php?getSwmInfo=true&locationId="+inputVO.getLocationId()+"&locationType="+inputVO.getLocationType()+"&fromDate="+inputVO.getFromDate()+"&toDate="+inputVO.getToDate());
	        ClientResponse response = webResource.accept("application/json").type("application/json").get(ClientResponse.class);
		    
	        if(response.getStatus() != 200){
	        	throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	    }else{
	 	    	  String output = response.getEntity(String.class);
	 	    	 if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				solidWasteManagementVO.setRfidTags(!jObj.getString("rfid_tags").equalsIgnoreCase("null") ? solidWasteManagementVO.getRfidTags()+jObj.getLong("rfid_tags") : solidWasteManagementVO.getRfidTags());
	 	    				solidWasteManagementVO.setFarmers(!jObj.getString("farmers").equalsIgnoreCase("null") ? solidWasteManagementVO.getFarmers()+jObj.getLong("farmers") : solidWasteManagementVO.getFarmers());
	 	    				solidWasteManagementVO.setHouseCollecion(!jObj.getString("houseCollecion").equalsIgnoreCase("null") ? solidWasteManagementVO.getHouseCollecion()+jObj.getDouble("houseCollecion") : solidWasteManagementVO.getHouseCollecion());
	 	    				solidWasteManagementVO.setFarmerCollection(!jObj.getString("farmerCollection").equalsIgnoreCase("null") ? solidWasteManagementVO.getFarmerCollection()+jObj.getDouble("farmerCollection") : solidWasteManagementVO.getFarmerCollection());
	 	    				solidWasteManagementVO.setSwmCollection(!jObj.getString("swmCollection").equalsIgnoreCase("null") ? solidWasteManagementVO.getSwmCollection()+jObj.getDouble("swmCollection") : solidWasteManagementVO.getSwmCollection());
	 	    				solidWasteManagementVO.setMgnres(!jObj.getString("mgnres").equalsIgnoreCase("null") ? solidWasteManagementVO.getMgnres()+jObj.getLong("mgnres") : solidWasteManagementVO.getMgnres());
	 	    				solidWasteManagementVO.setPr(!jObj.getString("pr").equalsIgnoreCase("null") ? solidWasteManagementVO.getPr()+jObj.getLong("pr") : solidWasteManagementVO.getPr());
	 	    				solidWasteManagementVO.setPublicType(!jObj.getString("public").equalsIgnoreCase("null") ? solidWasteManagementVO.getPublicType()+jObj.getLong("public") : solidWasteManagementVO.getPublicType());
	 	    				solidWasteManagementVO.setOnekg(!jObj.getString("1kg").equalsIgnoreCase("null") ? solidWasteManagementVO.getOnekg()+jObj.getDouble("1kg") : solidWasteManagementVO.getOnekg());
	 	    				solidWasteManagementVO.setFivekg(!jObj.getString("5kg").equalsIgnoreCase("null") ? solidWasteManagementVO.getFivekg()+jObj.getDouble("5kg") : solidWasteManagementVO.getFivekg());
	 	    				solidWasteManagementVO.setTenkg(!jObj.getString("10kg").equalsIgnoreCase("null") ? solidWasteManagementVO.getTenkg()+jObj.getDouble("10kg") : solidWasteManagementVO.getTenkg());
	 	    				solidWasteManagementVO.setTwentyFivekg(!jObj.getString("25kg").equalsIgnoreCase("null") ? solidWasteManagementVO.getTwentyFivekg()+jObj.getDouble("25kg") : solidWasteManagementVO.getTwentyFivekg());
	 	    				solidWasteManagementVO.setFiftykg(!jObj.getString("50kg").equalsIgnoreCase("null") ? solidWasteManagementVO.getFiftykg()+jObj.getDouble("50kg") : solidWasteManagementVO.getFiftykg());
	 	    				solidWasteManagementVO.setTractor(!jObj.getString("tractor").equalsIgnoreCase("null") ? solidWasteManagementVO.getTractor()+jObj.getLong("tractor") : solidWasteManagementVO.getTractor());
	 	    				solidWasteManagementVO.setAuto(!jObj.getString("auto").equalsIgnoreCase("null") ? solidWasteManagementVO.getAuto()+jObj.getLong("auto") : solidWasteManagementVO.getAuto());
	 	    				solidWasteManagementVO.setTricycle(!jObj.getString("tricycle").equalsIgnoreCase("null") ? solidWasteManagementVO.getTricycle()+jObj.getLong("tricycle") : solidWasteManagementVO.getTricycle());
	 	    				solidWasteManagementVO.setEvehicle(!jObj.getString("evehicle").equalsIgnoreCase("null") ? solidWasteManagementVO.getEvehicle()+jObj.getLong("evehicle") : solidWasteManagementVO.getEvehicle());
	 	    				solidWasteManagementVO.setNadap(!jObj.getString("nadap").equalsIgnoreCase("null") ? solidWasteManagementVO.getNadap()+jObj.getDouble("nadap") : solidWasteManagementVO.getNadap());
	 	    				solidWasteManagementVO.setVermi(!jObj.getString("vermi").equalsIgnoreCase("null") ? solidWasteManagementVO.getVermi()+jObj.getDouble("vermi") : solidWasteManagementVO.getVermi());
	 	    				solidWasteManagementVO.setVermiStock(!jObj.getString("vermiStock").equalsIgnoreCase("null") ? solidWasteManagementVO.getVermiStock()+jObj.getDouble("vermiStock") : solidWasteManagementVO.getVermiStock());
	 	    				solidWasteManagementVO.setBlocks(!jObj.getString("blocks").equalsIgnoreCase("null") ? solidWasteManagementVO.getBlocks()+jObj.getLong("blocks") : solidWasteManagementVO.getBlocks());
	 	    				solidWasteManagementVO.setRfidTracking(!jObj.getString("rfidTracking").equalsIgnoreCase("null") ? solidWasteManagementVO.getRfidTracking()+jObj.getLong("rfidTracking") : solidWasteManagementVO.getRfidTracking());
	 	    				}
	 	    			    }
	 	    	            }
	 	               }
	                  List<SolidWasteManagementVO>   data = getRfidTrackingOverAllTargetsData(inputVO);
	                   for (SolidWasteManagementVO rfidData : data) {
	                	   solidWasteManagementVO.setTotalRfidTags(solidWasteManagementVO.getTotalRfidTags()+rfidData.getTotalRfidTags());
	                	   solidWasteManagementVO.setTrackingPer(solidWasteManagementVO.getTrackingPer()+rfidData.getTrackingPer());
	                	   solidWasteManagementVO.setGpCnt(solidWasteManagementVO.getGpCnt()+rfidData.getGpCnt());
	                	   solidWasteManagementVO.setBlockNo(solidWasteManagementVO.getBlockNo()+rfidData.getBlockNo());            	   
	                          
	                   } 
	        }			
	 	catch (JSONException e) {
			LOG.error("Exception raised at getDrainsInfobyLocation - DrainsService service", e);
		}	
		return solidWasteManagementVO;
	}
	/*@Override
	public List<SolidWasteManagementVO> getRfidTrackingOverAllTargets(InputVO inputVO) {
		List<SolidWasteManagementVO> finalList = new ArrayList<SolidWasteManagementVO>(0);
	   //SolidWasteManagementVO solidWasteManagementVO=new SolidWasteManagementVO();
		try {
			Map<Long,SolidWasteManagementVO> locationMap= new HashMap<Long, SolidWasteManagementVO>();
			WebResource webResource = null;				
					webResource = commonMethodsUtilService.getWebResourceObject("http://pris.ap.gov.in/api/swm/index.php?getSwmInfo=1&districtId="+inputVO.getDistrictId()+"&fromDate="+inputVO.getFromDate()+"&toDate="+inputVO.getToDate());
 				    ClientResponse response = webResource.accept("application/json").type("application/json").get(ClientResponse.class);
		    
	        	if(response.getStatus() != 200){
	        		throw new  RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	        	}else{
	        		String output = response.getEntity(String.class);
	        		if(output != null && !output.isEmpty()){
	        			JSONArray finalArray = new JSONArray(output);
	        			if(finalArray!=null && finalArray.length()>0){
	        				Long gpCnt=Long.valueOf(finalArray.length());
	        				for(int i=0;i<finalArray.length();i++){
	        					JSONObject jObj = (JSONObject) finalArray.get(i);
	        					SolidWasteManagementVO solidWasteManagementVO= null;
	        					if(inputVO.getLocationType().equalsIgnoreCase("state")){
	        						solidWasteManagementVO = locationMap.get(1l);	        						
	        						if(solidWasteManagementVO == null){
	        							solidWasteManagementVO = new SolidWasteManagementVO();
	        							solidWasteManagementVO.setLocationId(1L);
	        							solidWasteManagementVO.setLocationName("Andra Pradesh");
	        							
										locationMap.put(1L, solidWasteManagementVO);
	        						}
	        					}else if(inputVO.getLocationType().equalsIgnoreCase("district")){
 	        						solidWasteManagementVO = locationMap.get(jObj.getLong("districtID"));	        						
	        						if(solidWasteManagementVO == null){
	        							solidWasteManagementVO = new SolidWasteManagementVO();
	        							solidWasteManagementVO.setLocationId(jObj.getLong("districtID"));
	        							solidWasteManagementVO.setLocationName(jObj.getString("district").trim());
										locationMap.put(jObj.getLong("districtID"), solidWasteManagementVO);
	        						}
	        					}else if(inputVO.getLocationType().equalsIgnoreCase("constituency")){
	        						solidWasteManagementVO = locationMap.get(jObj.getLong("constituencyID"));
									if(solidWasteManagementVO == null){
										solidWasteManagementVO = new SolidWasteManagementVO();
										solidWasteManagementVO.setLocationId(jObj.getLong("constituencyID"));
										solidWasteManagementVO.setLocationName(jObj.getString("constituencyName"));
										locationMap.put(jObj.getLong("constituencyID"), solidWasteManagementVO);
									}
								}else if(inputVO.getLocationType().equalsIgnoreCase("mandal")){
	        						solidWasteManagementVO = locationMap.get(jObj.getLong("mandalID"));
									if(solidWasteManagementVO == null){
										solidWasteManagementVO = new SolidWasteManagementVO();
										solidWasteManagementVO.setLocationId(jObj.getLong("mandalID"));
										solidWasteManagementVO.setLocationName(jObj.getString("mandal"));
										locationMap.put(jObj.getLong("mandalID"), solidWasteManagementVO);
									}
								}else if(inputVO.getLocationType().equalsIgnoreCase("panchayat")){
	        						solidWasteManagementVO = locationMap.get(jObj.getLong("gpID"));
									if(solidWasteManagementVO == null){
										solidWasteManagementVO = new SolidWasteManagementVO();
										solidWasteManagementVO.setLocationId(jObj.getLong("gpID"));
										solidWasteManagementVO.setLocationName(jObj.getString("gp"));
										locationMap.put(jObj.getLong("gpID"), solidWasteManagementVO);
									}
								}
	        					solidWasteManagementVO.setTotalRfidTags(!jObj.getString("totalRfidTags").equalsIgnoreCase("null") ?solidWasteManagementVO.getTotalRfidTags()+jObj.getLong("totalRfidTags") : solidWasteManagementVO.getTotalRfidTags());	
	        					solidWasteManagementVO.setBlockNo(!jObj.getString("blockNo").equalsIgnoreCase("null") ?solidWasteManagementVO.getBlockNo()+jObj.getLong("blockNo") : solidWasteManagementVO.getBlockNo());
	        					solidWasteManagementVO.setInTime(!jObj.getString("inTime").equalsIgnoreCase("null") ?solidWasteManagementVO.getInTime()+jObj.getLong("inTime") :  solidWasteManagementVO.getInTime());
	        					solidWasteManagementVO.setOutTime(!jObj.getString("outTime").equalsIgnoreCase("null") ?solidWasteManagementVO.getOutTime()+jObj.getLong("outTime") : solidWasteManagementVO.getOutTime());
	        					solidWasteManagementVO.setOutOfTarget(!jObj.getString("outOfTarget").equalsIgnoreCase("null") ?solidWasteManagementVO.getOutOfTarget()+jObj.getLong("outOfTarget") : solidWasteManagementVO.getOutOfTarget());
	        					solidWasteManagementVO.setTarget(!jObj.getString("target").equalsIgnoreCase("null") ?solidWasteManagementVO.getTarget()+jObj.getLong("target") : solidWasteManagementVO.getTarget());
	        					solidWasteManagementVO.setAverageInTime(!jObj.getString("averageInTime").equalsIgnoreCase("null") ?solidWasteManagementVO.getAverageInTime()+jObj.getLong("averageInTime") : solidWasteManagementVO.getAverageInTime());
	        					solidWasteManagementVO.setAverageOutTime(!jObj.getString("averageOutTime").equalsIgnoreCase("null") ?solidWasteManagementVO.getAverageOutTime()+jObj.getLong("averageOutTime") : solidWasteManagementVO.getAverageOutTime());
	        					solidWasteManagementVO.setAverageTarget(!jObj.getString("averageTarget").equalsIgnoreCase("null") ?solidWasteManagementVO.getAverageTarget()+jObj.getLong("averageTarget") : solidWasteManagementVO.getAverageTarget());
	        					solidWasteManagementVO.setTotalTime(solidWasteManagementVO.getInTime()+solidWasteManagementVO.getOutTime());
	        					solidWasteManagementVO.setTrackingPer(solidWasteManagementVO.getTarget()>0  ? round(((solidWasteManagementVO.getTotalTime()*100.00)/solidWasteManagementVO.getTarget()),2):0.00);
	        					solidWasteManagementVO.setAvertrackingPer(solidWasteManagementVO.getAverageTarget()>0  ? round(((solidWasteManagementVO.getAverageTime()*100.00)/solidWasteManagementVO.getAverageTarget()),2):0.00);
	        					if (jObj.has("gpID") && jObj.get("gpID").toString().length() > 0) {
	        					solidWasteManagementVO.setGpCnt(solidWasteManagementVO.getGpCnt()+1);   					
	        				   }  
	        				   }
	        			   }	        	
	        			   finalList.addAll(locationMap.values());
						
	        		    }
	        			}
	            		} catch ( JSONException e) {
	        		 	   LOG.error("Exception raised at getSolidInfoLocationWise - SolidWasteManagementService service", e);
	           			}
	        		return  finalList;	
	}
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
*/
	@Override
	public WebServiceDataVO saveRfidTrackingOverAllTargets() {
		
		WebServiceDataVO webServiceDataVO =new WebServiceDataVO();
		
		  String  jsonList = getRfidTrackingOverAllTargetsData();		
		  jsonList = jsonList.replaceAll("\\<.*?\\>", "");
		  jsonList = jsonList.replaceAll("Notice:  Undefined index: districtId in /var/www/html/api/swm/index.php on line 68", "");
		 
		
			try{
				Long webserviceId = webServiceDataDAO.getLatestDataId();
				
	    	   WebServiceData model = new WebServiceData();
	    	   model.setWebserviceId(127L);
	    	  // model.setInputData(inputVO.getInputData());
	    	   model.setResponceData(jsonList);
	    	   model.setIsDeleted("N");
	    	  // model.setDataDate("05-12-2017");
	    	   model.setInsertedTime(dateUtilService.getCurrentDateAndTime());   
	    	  
	    	   WebServiceData  rfidData =  webServiceDataDAO.save(model);
	    	   
	    	   WebServiceData data = webServiceDataDAO.get(webserviceId);
	    	   data.setIsDeleted("Y");
	    	   webServiceDataDAO.save(data);
	    	   if(rfidData != null){
	    		   webServiceDataVO.setStatus("success");
	    	   }else{
	    		   webServiceDataVO.setStatus("failure");
	    	   }
			   }catch (Exception e) {
				LOG.error("Exception occured at saveRfidTrackingOverAllTargets() in SolidWasteManagementService class",e);
				webServiceDataVO.setStatus("failure");
		    
		 }
			return  webServiceDataVO;
		    }
		
		
	 public String   getRfidTrackingOverAllTargetsData() {
		 String output =null;
			try {
				Date fromDate = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");	
				String dateStr = sdf.format(fromDate);
				WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://pris.ap.gov.in/api/swm/index.php?getSwmInfo=1district=0&fromDate="+dateStr+"&toDate="+dateStr+"");
		        ClientResponse response = webResource.accept("application/json").type("application/json").get(ClientResponse.class);
			    if(response.getStatus() != 200){
		        	throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		 	    }else{
		 	    	   output = response.getEntity(String.class);
		 	    	 if(output != null && !output.isEmpty()){
		 	    		JSONArray finalArray = new JSONArray(output);
		 	    		return finalArray.toString();
		 	    	 }
		 	    }
			}catch (Exception e) {
				LOG.error("Exception occured at getRfidTrackingOverAllTargetsData() in  SolidWasteManagementService class",e);
			}
		 return output;
		 
	 }
	 public List<SolidWasteManagementVO> getRfidTrackingOverAllTargetsData(InputVO  inputVO){
		 List<SolidWasteManagementVO> finalList = new ArrayList<SolidWasteManagementVO>(0);
			try {
				Map<Long,SolidWasteManagementVO> locationMap= new HashMap<Long, SolidWasteManagementVO>();
				String rfidList = webServiceDataDAO.getRfidTrackingOverAllTargetsData(inputVO.getWebserviceId());
				 if(rfidList!=null && rfidList.length()>0){
							 String jsonData=rfidList; 
							 if(jsonData != null && !jsonData.isEmpty()){
				        			JSONArray finalArray = new JSONArray(jsonData);
				        			if(finalArray!=null && finalArray.length()>0){
				        				for(int i=0;i<finalArray.length();i++){
				        					JSONObject jObj = (JSONObject) finalArray.get(i);
				        					SolidWasteManagementVO solidWasteManagementVO= null;
							 
				        					if(inputVO.getLocationType().equalsIgnoreCase("state")){
				        						solidWasteManagementVO = locationMap.get(1l);	        						
				        						if(solidWasteManagementVO == null){
				        							solidWasteManagementVO = new SolidWasteManagementVO();
				        							solidWasteManagementVO.setLocationId(1L);
 				        							solidWasteManagementVO.setLocationName("Andra Pradesh");
				        							
													locationMap.put(1L, solidWasteManagementVO);
				        						}
				        					}else if(inputVO.getLocationType().equalsIgnoreCase("district")){
			 	        						solidWasteManagementVO = locationMap.get(jObj.getLong("districtID"));	        						
				        						if(solidWasteManagementVO == null){
				        							solidWasteManagementVO = new SolidWasteManagementVO();
				        							solidWasteManagementVO.setLocationId(jObj.getLong("districtID"));
				        							solidWasteManagementVO.setLocationName(jObj.getString("district").trim());
													locationMap.put(jObj.getLong("districtID"), solidWasteManagementVO);
				        						}
				        					}else if(inputVO.getLocationType().equalsIgnoreCase("constituency")){
				        						solidWasteManagementVO = locationMap.get(jObj.getLong("constituencyID"));
												if(solidWasteManagementVO == null){
													solidWasteManagementVO = new SolidWasteManagementVO();
													solidWasteManagementVO.setLocationId(jObj.getLong("constituencyID"));
													solidWasteManagementVO.setLocationName(jObj.getString("constituencyName"));
													locationMap.put(jObj.getLong("constituencyID"), solidWasteManagementVO);
												}
											}else if(inputVO.getLocationType().equalsIgnoreCase("mandal")){
				        						solidWasteManagementVO = locationMap.get(jObj.getLong("mandalID"));
												if(solidWasteManagementVO == null){
													solidWasteManagementVO = new SolidWasteManagementVO();
													solidWasteManagementVO.setLocationId(jObj.getLong("mandalID"));
													solidWasteManagementVO.setLocationName(jObj.getString("mandal"));
													locationMap.put(jObj.getLong("mandalID"), solidWasteManagementVO);
												}
											}else if(inputVO.getLocationType().equalsIgnoreCase("panchayat")){
				        						solidWasteManagementVO = locationMap.get(jObj.getLong("gpID"));
												if(solidWasteManagementVO == null){
													solidWasteManagementVO = new SolidWasteManagementVO();
													solidWasteManagementVO.setLocationId(jObj.getLong("gpID"));
													solidWasteManagementVO.setLocationName(jObj.getString("gp"));
													locationMap.put(jObj.getLong("gpID"), solidWasteManagementVO);
												}
											}
				        					if(jObj.has("totalRfidTags") && jObj.getLong("totalRfidTags") >0 ){
				        						solidWasteManagementVO.setTotalRfidTags(jObj.getLong("totalRfidTags")+solidWasteManagementVO.getTotalRfidTags());	
				        					}
				        					if(jObj.has("blockNo") && jObj.getLong("blockNo") >0 ){
				        					solidWasteManagementVO.setBlockNo(jObj.getLong("blockNo")+solidWasteManagementVO.getBlockNo());
				        					}
				        					if(jObj.has("inTime") && jObj.getLong("inTime") >0 ){
				        					solidWasteManagementVO.setInTime(jObj.getLong("inTime")+solidWasteManagementVO.getInTime());
				        					}
				        					if(jObj.has("outTime") && jObj.getLong("outTime") >0 ){
				        					solidWasteManagementVO.setOutTime(jObj.getLong("outTime") +solidWasteManagementVO.getOutTime());
				        					}
				        					if(jObj.has("outOfTarget") && jObj.getLong("outOfTarget") >0 ){
				        					solidWasteManagementVO.setOutOfTarget(jObj.getLong("outOfTarget")+solidWasteManagementVO.getOutOfTarget());
				        					}
				        					if(jObj.has("target") && jObj.getLong("target") >0 ){
				        					solidWasteManagementVO.setTarget(jObj.getLong("target")+solidWasteManagementVO.getTarget());
				        					}
				        					if(jObj.has("averageInTime") && jObj.getLong("averageInTime") >0 ){
				        					solidWasteManagementVO.setAverageInTime(jObj.getLong("averageInTime")+solidWasteManagementVO.getAverageInTime());
				        					}
				        					if(jObj.has("averageOutTime") && jObj.getLong("averageOutTime") >0 ){
				        					solidWasteManagementVO.setAverageOutTime(jObj.getLong("averageOutTime")+solidWasteManagementVO.getAverageOutTime());
				        					}
				        					if(jObj.has("averageTarget") && jObj.getLong("averageTarget") >0 ){
				        					solidWasteManagementVO.setAverageTarget(jObj.getLong("averageTarget")+solidWasteManagementVO.getAverageTarget());
				        					}
				        					if(jObj.has("target") && jObj.getLong("target") >0 ){
					        					solidWasteManagementVO.setTarget(jObj.getLong("target")+solidWasteManagementVO.getTarget());
					        				}
				        					if(jObj.has("achieve") && jObj.getLong("achieve") >0 ){
					        					solidWasteManagementVO.setAchieve(jObj.getLong("achieve")+solidWasteManagementVO.getAchieve());
					        				}
				        					solidWasteManagementVO.setInTimePer(solidWasteManagementVO.getTarget()>0  ? round((solidWasteManagementVO.getInTime()*100.00)/solidWasteManagementVO.getTarget(),2):0.00);
				        					solidWasteManagementVO.setOutTimePer(solidWasteManagementVO.getTarget()>0  ? round((solidWasteManagementVO.getOutTime()*100.00)/solidWasteManagementVO.getTarget(),2):0.00);
				        					//solidWasteManagementVO.setTotalTime(solidWasteManagementVO.getInTime()+solidWasteManagementVO.getOutTime());
				        					solidWasteManagementVO.setTrackingPer(solidWasteManagementVO.getTarget()>0  ? round(((solidWasteManagementVO.getAchieve()*100.00)/solidWasteManagementVO.getTarget()),2):0.00);
				        					solidWasteManagementVO.setAvertrackingPer(solidWasteManagementVO.getAverageTarget()>0  ? round(((solidWasteManagementVO.getAverageTime()*100.00)/solidWasteManagementVO.getAverageTarget()),2):0.00);
				        					if (jObj.has("gpID") && jObj.get("gpID").toString().length() > 0) {
				        					solidWasteManagementVO.setGpCnt(solidWasteManagementVO.getGpCnt()+1);   					
				        				   }  
				        				   }
				        		         }
							            }							      
				        			   finalList.addAll(locationMap.values());
						              }
					 
				            		} catch ( JSONException e) {
				        		 	   LOG.error("Exception raised at getSolidInfoLocationWise - SolidWasteManagementService service", e);
				           			}
				        		return  finalList;	
				           }
                           
	 
public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
}

@Override
public List<SolidWasteManagementVO> getRfidTrackingOverAllTargets(InputVO inputVO) {
	// TODO Auto-generated method stub
	return null;
}
}
 
	







