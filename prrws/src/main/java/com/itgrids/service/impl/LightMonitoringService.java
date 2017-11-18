package com.itgrids.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.ILightMonitoringDAO;
import com.itgrids.dao.ILightWattageDAO;
import com.itgrids.dao.ILightsVendorDAO;
import com.itgrids.dto.AddressVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LedOverviewVo;
import com.itgrids.dto.LightMonitoringVO;
import com.itgrids.dto.LightWattageVO;
import com.itgrids.dto.ResultVO;
import com.itgrids.model.LightMonitoring;
import com.itgrids.model.LightWattage;
import com.itgrids.model.LightsVendor;
import com.itgrids.service.ILightMonitoring;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.DateUtilService;
import com.sun.jersey.api.client.ClientResponse;

@Service
@Transactional
public class LightMonitoringService  implements ILightMonitoring{    

	private static final Logger LOG = Logger.getLogger(LightMonitoringService.class);


	@Autowired
	private WebServiceUtilService webServiceUtilService;

	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	
	@Autowired
	private ILightWattageDAO lightWattageDAO; 
	
	@Autowired
	private DateUtilService dateUtilService;
	
	@Autowired
	private ILightMonitoringDAO lightMonitoringDAO;
	
	@Autowired 
	private ILightsVendorDAO lightsVendorDAO;
		   
	public ResultVO saveRealtimeStatusByVillages() {
		ResultVO stausVO = new ResultVO();
		List<LightMonitoringVO> resultData = new ArrayList<LightMonitoringVO>(0);
		try {
			String URL = "http://greenlightt.monitormymeter.com/api/RestRealtimeAPI/GetRealtimeStatusByVillages";
			String differenceVendorURL = "http://182.18.173.26:8080/apgovt/panchayatVillageData.php";
			
			resultData = getLightMonitoringDataInRequiredFormat("AP_GOVT",URL);
			stausVO = saveLightsMonitoringData(resultData, 2l);
			List<LightMonitoringVO> esslDataList = getLightMonitoringDataInRequiredFormat("AP_EESL",URL);
			List<LightMonitoringVO> esslDiffVndrDataList = getLightMonitoringDataInRequiredFormat("differenceVendor",differenceVendorURL);
			resultData.clear();
			if (esslDataList != null) {
				resultData.addAll(esslDataList);
			}	
			if (esslDiffVndrDataList != null) {
				resultData.addAll(esslDiffVndrDataList);
			}
			stausVO = saveLightsMonitoringData(resultData, 1l);
			
		} catch (Exception e) {
			stausVO.setStatusCode(1);
			stausVO.setMessage("FALURE");
 			LOG.error("Exception raised at saveRealtimeStatusByVillages - LED service", e);
		}
		return stausVO;
		
	}
	/*
	 * Date : 02/08/2017
	 * Author :Swapna
	 */
	public List<LightMonitoringVO> getLightMonitoringDataInRequiredFormat(String key,String URL) {
		List<LightMonitoringVO> resultData = new ArrayList<LightMonitoringVO>(0);
		try {			
			   String inputStr = "";
			   if (!key .equalsIgnoreCase("differenceVendor") ) {
				   inputStr = "{";
				   inputStr += "\"ClientId\" : \""+key+"\"";
				   inputStr += "}";
			   } else {
				   inputStr = null;
			   }
			   
			ClientResponse response = webServiceUtilService.callWebService(URL,inputStr);
	        if(response.getStatus() != 200)
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	        else {
	 	    	 String output = response.getEntity(String.class);
	 	    	 JSONObject responseJsonObj = new JSONObject(output);
	 	    	 JSONObject dataObj = responseJsonObj.getJSONObject("responseData");
	 	    	 JSONObject statusObj = (dataObj != null && dataObj.has("requestStatus")) ? dataObj.getJSONObject("requestStatus"):null;
	 	    	 Long statusCode = (statusObj != null && statusObj.has("StatusCode")) ? statusObj.getLong("StatusCode"):null;
	 	    	 String statusMessage = (statusObj != null && statusObj.has("Message")) ? statusObj.getString("Message"):null;
	 	    	 
	 	    	 if(output != null && !output.isEmpty() && statusCode!= null && statusCode.longValue() ==0l && statusMessage != null && statusMessage.equalsIgnoreCase("Success"))
	 	    	 {
	 	    		 resultData = processLightData(key,output);
	 	    	 } 
	 	       }	  
	 	     } catch (Exception e) {
				
	 			LOG.error("Exception raised at saveLightMonitoringData - LED service", e);
	 		}
		return resultData;    	
	 }
	   private ResultVO saveLightsMonitoringData(List<LightMonitoringVO> resultData,Long lightVendorId)  {
			ResultVO statusVO = new ResultVO();
		    try {
		    	 
 	    		if(resultData != null && resultData.size() > 0)
 	    		 {
 	    			   /*Update lightMonitoring Data */
 	    			    List<Long> lighMonitoringIds = lightMonitoringDAO.getLightMonitroingIds(dateUtilService.getCurrentDateAndTime(),lightVendorId);
    					int updatedCount = lightMonitoringDAO.updateLightMoitoringData(dateUtilService.getCurrentDateAndTime(),lightVendorId);
    					
    					if (lighMonitoringIds != null && lighMonitoringIds.size() > 0 ) {
    						int updatedLightWattageCount = lightMonitoringDAO.updateLightWattageMoitoringData(lighMonitoringIds);	
    					}	
    					
 	    			 for(LightMonitoringVO lightMonitoringVO : resultData)
 	    			 {
 	    				 try{
 	    					
 	    					LightMonitoring lightMonitoring = new LightMonitoring();
	 	    				
	 		 	    		lightMonitoring.setPanchayatId(lightMonitoringVO.getPanchayatId());
	 		 	    		lightMonitoring.setTotalPanels(lightMonitoringVO.getTotalPanels());
	 		 	    		lightMonitoring.setTotalPoles(lightMonitoringVO.getTotalPoles());
	 		 	    		lightMonitoring.setTotalLights(lightMonitoringVO.getTotalLights());
	 		 	    		lightMonitoring.setNotWorkingLights(lightMonitoringVO.getNotWorkingLights());
	 		 	    		lightMonitoring.setWorkingLights(lightMonitoringVO.getWorkingLights());
	 		 	    		lightMonitoring.setOnLights(lightMonitoringVO.getOnLights());
	 		 	    		lightMonitoring.setOffLights(lightMonitoringVO.getOffLights());
	 		 	    		lightMonitoring.setInsertedTime(dateUtilService.getCurrentDateAndTime());
	 		 	    		lightMonitoring.setSurveyDate(dateUtilService.getCurrentDateAndTime());
	 		 	    		lightMonitoring.setIsDeleted("N");
	 		 	    		lightMonitoring.setLightsVendorId(lightVendorId);
							lightMonitoring = lightMonitoringDAO.save(lightMonitoring);
	 		 	    		 
	 		 	    		List<LightWattageVO> wattageList =  lightMonitoringVO.getWattageList();
	 		 	    		
	 		 	    		if(wattageList != null && wattageList.size()>0)
	 		 	    		{
	 		 	    			for(LightWattageVO lightWattageVO : wattageList)
	 		 	    			{
									LightWattage wattage = new LightWattage();
									wattage.setWattage(lightWattageVO.getWattage());
	 		 	    				wattage.setLightCount(lightWattageVO.getLightCount());
	 		 	    				wattage.setLightMonitoringId(lightMonitoring.getLightMonitoringId());
	 		 	    				wattage.setInsertedTime(dateUtilService.getCurrentDateAndTime());
	 		 	    				wattage.setIsDeleted("N");
	 		 	    				lightWattageDAO.save(wattage);	 		 		 	    		
	 		 		 	    	}
	 		 		 	    }	 		 	    				 	    				
	 		 	    	}catch (Exception e) {
	 		 	    		statusVO.setStatusCode(1);
	 		 	    		statusVO.setMessage("FALURE");
 	    				    LOG.error(e);
 	    			 }
 	    			 
 	    		}
 	    			statusVO.setStatusCode(0);
 	    			statusVO.setMessage("SUCCESS");
 	    		 }
		    	
		    	
		    } catch (Exception e) {
		    	statusVO.setStatusCode(1);
		    	statusVO.setMessage("FALURE");
		    	e.printStackTrace();
		    }
		    return statusVO;
	   }
	     public List<LightMonitoringVO> processLightData(String key,String output)
	     {
	    	 List<LightMonitoringVO> resultData = null;
	    	 try{
	    	 
	    		 JSONObject responseJsonObj = new JSONObject(output);
	    		 
	    		 if(responseJsonObj != null && responseJsonObj.length() > 0)
 	    		 {
	    			 resultData = new ArrayList<LightMonitoringVO>(0);
	    			 JSONObject dataObj = responseJsonObj.getJSONObject("responseData");
	    			 JSONArray finalArray = null;
	    			 if (dataObj != null && dataObj.length() > 0 ){
	    				  finalArray = (dataObj.has("VillageInfo")) ? dataObj.getJSONArray("VillageInfo"):null;
	    			 }
	    			 if (finalArray != null && finalArray.length() > 0 ){
	    				 for(int i=0;i<finalArray.length();i++)
		    			 {
		    				 try{
		    					 
		    					 JSONObject jObj = (JSONObject) finalArray.get(i);
		    					 Long panchayatId = (jObj != null && jObj.has("VillageId")) ? jObj.getLong("VillageId"):0l;
		    					 
		    					 if(panchayatId != null && panchayatId.longValue() > 0l)
		    					 {
		    						   LightMonitoringVO lightMonitoringVO = new LightMonitoringVO();
		    						   lightMonitoringVO.setPanchayatId(panchayatId);
		    						   lightMonitoringVO.setTotalPanels(jObj.has("TotalPanels") ? jObj.getLong("TotalPanels") :0l);
		    						   //lightMonitoringVO.setTotalPoles(jObj.getLong("TotalPoles"));
		    						   lightMonitoringVO.setTotalPoles(jObj.has("TotalLights") ? jObj.getLong("TotalLights"):0l);//total light is nothing but total poles
		    						   lightMonitoringVO.setTotalLights(jObj.has("TotalLights") ? jObj.getLong("TotalLights"):0l);
			    					   lightMonitoringVO.setNotWorkingLights(jObj.has("NonOperationalLights") ? jObj.getLong("NonOperationalLights"):0l);
			    					   lightMonitoringVO.setWorkingLights(jObj.has("OperationalLights") ? jObj.getLong("OperationalLights"):0l);
			    					   lightMonitoringVO.setOnLights(jObj.has("ONLights") ? jObj.getLong("ONLights"):0l);
			    					   lightMonitoringVO.setOffLights(jObj.has("OFFLights") ? jObj.getLong("OFFLights"):0l);
			    					 
			    					 JSONArray arr =  jObj.has("Wattages") ? jObj.getJSONArray("Wattages"):null;
				 		 	    		
			 		 	    		if(arr != null && arr.length() > 0)
			 		 	    		{
			 		 	    			List<LightWattageVO> wattageList = new ArrayList<LightWattageVO>(0);
			 		 	    			
			 		 	    			for(int j=0;j<arr.length();j++)
			 		 	    			{
			 		 	    				try{
			 		 	    				JSONObject jsonob = (JSONObject) arr.get(j);
			 		 	    				LightWattageVO lightWattageVO = null;
			 		 	    				
			 		 	    				if(jsonob != null)
			 		 	    				{
												lightWattageVO = new LightWattageVO();
												 if (key .equalsIgnoreCase("differenceVendor") ){
														lightWattageVO.setWattage(jsonob.has("Wattage") ? Long.valueOf(jsonob.getString("Wattage").substring(1, jsonob.getString("Wattage").length())):0l);
														lightWattageVO.setLightCount(jsonob.has("LightCount") ? jsonob.getLong("LightCount"):0l);
											 
												 } else {
														lightWattageVO.setWattage(jsonob.has("Wattage") ? jsonob.getLong("Wattage"):0l);
														lightWattageVO.setLightCount(jsonob.has("LightCount") ? jsonob.getLong("LightCount"):0l);
										
												 }
												wattageList.add(lightWattageVO);
			 		 	    				}
			 		 	    			}catch (Exception e) {
			 		 	    				LOG.error("Exception occureat while preparing light wattage details in processLightData()",e);
			 		 	    			}
			 		 		 	     }
			 		 	    			lightMonitoringVO.setWattageList(wattageList);
			 		 		 	    }
			 		 	    		resultData.add(lightMonitoringVO);
		    					}
		    				 }catch (Exception e) {
		    					 LOG.error(e);
		    				 }
		    			 }
	    			 }
	    			
 	    		 }
	    	 }catch (Exception e) {
	    		 LOG.error("Exception raised at processLightData - LightMonitoringService service", e);
	    	 }
	    	 return resultData;
	     }
	     /*
	 	 * Date : 03/08/2017
	 	 * Author :Swapna
	 	 */
	 @Override
	public List<LightMonitoringVO> getBasicLedOverviewDetails(String startDate,String endDate, String locationType,final Long locationValue,List<Long> lightMonitoringIds) {
		   List<LightMonitoringVO> list = new ArrayList<LightMonitoringVO>() ;
		 
		try{	
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			
			List<Long> loccationIds = new ArrayList<Long>();
			if (locationValue != null && locationValue.longValue() > 0l) {
				loccationIds.add(locationValue);
			}
			
			
			if(startDate != null && startDate.trim().length() > 0 && endDate != null && endDate.trim().length() > 0){
				fromDate = sdf.parse(startDate);
				toDate = sdf.parse(endDate);
			}
		     List<Object[]> lightMonitoringData  =  lightMonitoringDAO.getTotalVillagesDetails(fromDate,toDate,locationType,loccationIds,lightMonitoringIds,"No");
			     if(lightMonitoringData!=null && lightMonitoringData.size()>0 && !lightMonitoringData.isEmpty()){
			    	 LightMonitoringVO lightMonitoringVO= new LightMonitoringVO();
				     for (Object[] objects : lightMonitoringData) {			
				    	     lightMonitoringVO.setTotalLights(lightMonitoringVO.getTotalLights()+commonMethodsUtilService.getLongValueForObject(objects[2]));
				    	     lightMonitoringVO.setTotalPanels(lightMonitoringVO.getTotalPanels()+commonMethodsUtilService.getLongValueForObject(objects[3]));	
						     lightMonitoringVO.setTotalPoles(lightMonitoringVO.getTotalPoles()+commonMethodsUtilService.getLongValueForObject(objects[4]));
						     lightMonitoringVO.setWorkingLights(lightMonitoringVO.getWorkingLights()+commonMethodsUtilService.getLongValueForObject(objects[5]));
						     lightMonitoringVO.setOnLights(lightMonitoringVO.getOnLights()+commonMethodsUtilService.getLongValueForObject(objects[6]));
						     lightMonitoringVO.setOffLights(lightMonitoringVO.getOffLights()+commonMethodsUtilService.getLongValueForObject(objects[7]));
					         lightMonitoringVO.setNotWorkingLights(lightMonitoringVO.getNotWorkingLights()+commonMethodsUtilService.getLongValueForObject(objects[8]));
			        }
			       List<Object[]> wattegeCount = lightWattageDAO.getTotalWattege(fromDate,toDate,locationType,loccationIds,lightMonitoringIds,"No");
			       if(wattegeCount!=null && wattegeCount.size()>0 && !wattegeCount.isEmpty()){
			    	   for (Object[] param : wattegeCount) {	
			    		   LightWattageVO wattagVO = new LightWattageVO();
					    	wattagVO.setWattage(commonMethodsUtilService.getLongValueForObject(param[2]));
					    	wattagVO.setLightCount(commonMethodsUtilService.getLongValueForObject(param[3]));		    	
					       	lightMonitoringVO.getWattageList().add(wattagVO); 	
					    }
			        }
			       list.add(lightMonitoringVO);
		  }
       }catch (Exception e) {
    	   LOG.error("Exception raised at getBasicLedOverviewDetails - LightMonitoringService service", e);
       }
		return list;
   }
	     /*
	 	 * Date : 03/08/2017
	 	 * Author :Swapna
	  	 */
	public List<LedOverviewVo> getLedOverviewForStartedLocationsDetailsCounts(String startDate, String endDate, String locationType,final Long locationValue, List<Long> lightVendorIdList) {
		List<LedOverviewVo> listVO = new ArrayList<LedOverviewVo>(0);
		try {
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if (startDate != null && startDate.trim().length() > 0 && endDate != null && endDate.trim().length() > 0) {
				fromDate = sdf.parse(startDate);
				toDate = sdf.parse(endDate);
			}
			List<Long> loccationIds = new ArrayList<Long>();
			if (locationValue != null && locationValue.longValue() > 0l) {
				loccationIds.add(locationValue);
			}
			List<Object[]> counts = lightMonitoringDAO.getTotalSurveyDetails(fromDate, toDate, locationType, loccationIds,lightVendorIdList,"No");
			if (counts != null && counts.size() > 0 && !counts.isEmpty()) {
				LedOverviewVo resultVo = new LedOverviewVo();
				for (Object[] objects : counts) {
					resultVo.setTotalDistCnt( resultVo.getTotalDistCnt() + commonMethodsUtilService.getLongValueForObject(objects[2]));// No of districts
					resultVo.setTotalConstituencyCnt(resultVo.getTotalConstituencyCnt() + commonMethodsUtilService.getLongValueForObject(objects[3]));// no of constituencies
					resultVo.setTotalMandalCnt(resultVo.getTotalMandalCnt() + commonMethodsUtilService.getLongValueForObject(objects[4]));// no of mandal
					resultVo.setTotalpanchayatCnt(resultVo.getTotalpanchayatCnt() + commonMethodsUtilService.getLongValueForObject(objects[5]));// No of panchayats
				}
				listVO.add(resultVo);
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getLedOverviewForStatedLocationsDetailsCounts - LightMonitoringService service",e);
		}
		return listVO;
	}
	  
	/*
 	 * Date : 11/08/2017
 	 * Author :Swapna
 	 */
	@Override
	public List<LightMonitoringVO> getAllLevelWiseDataOverView(String locationType,String filterType, List<Long> filterValues,String fromDateStr,String toDateStr,List<Long> lightVendorIdList) {
		List<LightMonitoringVO> returnList = new ArrayList<LightMonitoringVO>();
		try {
				Date fromDate = null;
				Date toDate = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
				}
			      List<Object[]> totalLocObj =lightMonitoringDAO.getLocationsForLEDDashboard(locationType, filterType, filterValues, "", null, null, null);//getting location template
			      List<Object[]> lightMonObjLst =   lightMonitoringDAO.getLocationWiseDataForLEDDashboard(locationType,filterType,filterValues,fromDate,toDate,lightVendorIdList) ;//getting survey data	
			      List<Object[]> lightWattageObjLst = lightWattageDAO.getLocationWiseLightWattageDtls(locationType, filterType, filterValues, fromDate, toDate,lightVendorIdList);//getting location wise wattage details.
			      
			      Map<Long,Map<Long,List<LightWattageVO>>> lightWattageMap = getLightWattageDtls(lightWattageObjLst);
			      Map<Long,List<LightMonitoringVO>> lightVendorMap = getLightVendorWiseLightMonitroingDetails(lightMonObjLst);
			      Map<Long,LightMonitoringVO> locationMap = setStartedSurveryDataLocationWise(totalLocObj,lightVendorMap,lightWattageMap,locationType);
			     
			    if (locationMap != null && locationMap.size() > 0 ) {
			    	returnList.addAll(locationMap.values());
			    }
       }catch (Exception e) {
			LOG.error("Exception raised at getAllLevelWiseDataOverView - LightMonitoringService service", e);
		}
		return returnList;
	}
	private Map<Long,List<LightMonitoringVO>> getLightVendorWiseLightMonitroingDetails(List<Object[]> objList) {
		Map<Long,List<LightMonitoringVO>> lightVendorMap = new HashMap<>();
		 try {
			  if (objList != null && objList.size() > 0) {
				  for (Object[] param : objList) {
					List<LightMonitoringVO> vendorTypeList = lightVendorMap.get(commonMethodsUtilService.getLongValueForObject(param[9]));//locationId
					  if (vendorTypeList == null ) {
						  vendorTypeList = new ArrayList<>(0);
						  lightVendorMap.put(commonMethodsUtilService.getLongValueForObject(param[9]), vendorTypeList);
					  }
					  LightMonitoringVO lightDtlsVO = new LightMonitoringVO();
					  lightDtlsVO.setSurveyStartedtotalMandals(commonMethodsUtilService.getLongValueForObject(param[0]));
					  lightDtlsVO.setSurveyStartedtotalGps(commonMethodsUtilService.getLongValueForObject(param[1]));  
					  lightDtlsVO.setTotalPoles(commonMethodsUtilService.getLongValueForObject(param[2]));
					  lightDtlsVO.setTotalPanels(commonMethodsUtilService.getLongValueForObject(param[3]));
					  lightDtlsVO.setTotalLights(commonMethodsUtilService.getLongValueForObject(param[4]));
					  lightDtlsVO.setWorkingLights(commonMethodsUtilService.getLongValueForObject(param[5]));
					  lightDtlsVO.setOnLights(commonMethodsUtilService.getLongValueForObject(param[6]));	
					  lightDtlsVO.setOffLights(commonMethodsUtilService.getLongValueForObject(param[7])); 
					  lightDtlsVO.setNotWorkingLights(commonMethodsUtilService.getLongValueForObject(param[8]));
					  lightDtlsVO.setTotalLedLIghtInstalledCount(lightDtlsVO.getOnLights()+lightDtlsVO.getOffLights());
					  lightDtlsVO.setLightVendorId(commonMethodsUtilService.getLongValueForObject(param[10]));
					  lightDtlsVO.setLightVendorName(commonMethodsUtilService.getStringValueForObject(param[11]));
					  vendorTypeList.add(lightDtlsVO);
				}
			  }
			 
		 } catch (Exception e) {
			 LOG.error("Exception raised at getLightVendorWiseLightsDetails - LightMonitoringService service", e);
		 }
		 return lightVendorMap;
	}
	public Map<Long,LightMonitoringVO> setStartedSurveryDataLocationWise(List<Object[]> objList,Map<Long,List<LightMonitoringVO>> vendorTypeMap,Map<Long,Map<Long,List<LightWattageVO>>> wattageMap,String locationType) {
		Map<Long,LightMonitoringVO> locationDtlsMap = new HashMap<>(0);
		 try {
			  if (objList != null && objList.size() > 0 ){
				   for (Object[] param : objList) {
					   LightMonitoringVO locationVO = new LightMonitoringVO();
					   locationVO.setTotalMandals(commonMethodsUtilService.getLongValueForObject(param[0]));//survey started mandal
					   locationVO.setTotalGps(commonMethodsUtilService.getLongValueForObject(param[1]));//survey started village
					   locationVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[2]));
					   locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[3]));
					   locationVO.setSubList(new ArrayList<LightMonitoringVO>());
					   /*Setting light vendor type and wattage list location wise */
						if (vendorTypeMap != null) {
							List<LightMonitoringVO> vendorTypeList = vendorTypeMap.get(locationVO.getLocationId());
							if (vendorTypeList != null) {
								for (LightMonitoringVO lightMonitoringVO : vendorTypeList) {
									if (wattageMap != null && wattageMap.get(locationVO.getLocationId()) != null) {
										lightMonitoringVO.setWattageList(wattageMap.get(locationVO.getLocationId()).get(lightMonitoringVO.getLightVendorId()));
										locationVO.getSubList().add(lightMonitoringVO);
									} else {
										locationVO.getSubList().add(lightMonitoringVO);
									}
								}
							}
						}
					    AddressVO addressVO = new AddressVO();
						addressVO.setStateId(commonMethodsUtilService.getLongValueForObject(param[4]));
						addressVO.setStateName(commonMethodsUtilService.getStringValueForObject(param[5]));
						addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[6]));
						addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[7]));
						addressVO.setAssemblyId(commonMethodsUtilService.getLongValueForObject(param[8]));
						addressVO.setAssemblyName(commonMethodsUtilService.getStringValueForObject(param[9]));
						addressVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(param[10]));
						addressVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[11]));
						addressVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[12]));
						addressVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(param[13]));
						if (locationType != null && locationType.equalsIgnoreCase("panchayat")) {
							addressVO.setPanchayatId(commonMethodsUtilService.getLongValueForObject(param[14]));
							addressVO.setPanchayatName(commonMethodsUtilService.getStringValueForObject(param[15]));
						}
						locationVO.setAddressVO(addressVO);
					   
					   locationDtlsMap.put(locationVO.getLocationId(), locationVO);
				}
			 }
		 } catch (Exception e ){
			 LOG.error("Exception raised at setStartedSurveryDataLocationWise - LightMonitoringService service", e);
		 }
		 return locationDtlsMap;
	 }
	 private Map<Long,Map<Long,List<LightWattageVO>>> getLightWattageDtls(List<Object[]> objList) {
		 Map<Long,Map<Long,List<LightWattageVO>>> lightWattageDtlsMap = new HashMap<>();
		  try {
			   if (objList != null && !objList.isEmpty() ) {
				    for (Object[] param : objList) {
						 Long locationId = commonMethodsUtilService.getLongValueForObject(param[2]);
						  Map<Long,List<LightWattageVO>> lightVendorMap = lightWattageDtlsMap.get(locationId);
						  if (lightVendorMap == null ) {
							  lightVendorMap = new HashMap<>();
							  lightWattageDtlsMap.put(locationId, lightVendorMap);
						  }
						    Long lightVendorId = commonMethodsUtilService.getLongValueForObject(param[3]);
						    List<LightWattageVO> wattageList = lightVendorMap.get(lightVendorId);
						     if (wattageList == null ) {
						    	 wattageList = new ArrayList<>();
						    	 lightVendorMap.put(lightVendorId, wattageList);
						     }
						    LightWattageVO wattagVO = new LightWattageVO();
							wattagVO.setWattage(commonMethodsUtilService.getLongValueForObject(param[0]));
							wattagVO.setLightCount(commonMethodsUtilService.getLongValueForObject(param[1]));
							wattageList.add(wattagVO);
					}
			   }
		  } catch (Exception e) {
			  LOG.error("Exception raised at getLightWattageDtls - LightMonitoringService service", e);
		  }
		  return lightWattageDtlsMap;
	 }
	/*
 	 * Date : 17/08/2017
 	 * Author :Swapna
 	 */
	@Override
	public List<LightMonitoringVO> getLocationBasedOnSelection(String locationType,String filterType, List<Long> filterValues,String subLocationType,String fromDateStr,String toDateStr,List<Long> lighsVendorIds) {
		List<LightMonitoringVO> returnList = new ArrayList<LightMonitoringVO>();
		try{
			  Date fromDate = null;
			  Date toDate = null;
			  SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			  if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
			 }
			 List<Object[]> totalFilterObj =lightMonitoringDAO.getLocationsForLEDDashboard(locationType, filterType, filterValues,"filter",fromDate,toDate,lighsVendorIds);
			if(totalFilterObj!=null && totalFilterObj.size()>0){
				Map<String,LightMonitoringVO> locationMap = new TreeMap<String,LightMonitoringVO>();
		    for (Object[] objects : totalFilterObj) {
		       LightMonitoringVO locationVO = new LightMonitoringVO();
		       locationVO.setLocationId(commonMethodsUtilService.getLongValueForObject(objects[2]));
		       locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(objects[3]));
		       locationMap.put(locationVO.getLocationName(), locationVO);
		    }
		    if (locationMap.size() > 0) {
		    	 returnList.addAll(locationMap.values());	
		    }
		 }		
	         }catch (Exception e) {
			LOG.error("Exception raised at getLocationBasedOnSelection - LightMonitoringService service", e);
		}		
		return returnList;
	}
	
 	public List<LightWattageVO> getLightWattageList(List<Object[]> objList) {
		List<LightWattageVO> lightWattageList = new ArrayList<>(0);
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					LightWattageVO wattagVO = new LightWattageVO();
					wattagVO.setWattage(param[0] != null ? (Long) param[0] : 0l);
					wattagVO.setLightCount(param[1] != null ? (Long) param[1]: 0l);
					lightWattageList.add(wattagVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getLightWattageList - LightMonitoringService service",e);
		}
		return lightWattageList;
	}

    public InputVO checkIdDataExist(String startDate,String endDate) {
    	InputVO statusVO = new InputVO();
    	 try {
    		Date fromDate = null;
 			Date toDate = null;
 			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
 			if(startDate != null && startDate.trim().length() > 0 && endDate != null && endDate.trim().length() > 0){
 				fromDate = sdf.parse(startDate);
 				toDate = sdf.parse(endDate);
 			}
 			List<Long> lightMonitoringIds = lightMonitoringDAO.isDataExist(fromDate, toDate);
 			 if (lightMonitoringIds != null && lightMonitoringIds.size() > 0){
 				statusVO.setStatus("YES");
 			 } else {
 				statusVO.setStatus("NO");
 			 }
    	 }  catch (Exception e) {
    		 LOG.error("Exception raised at setSurveyStartedLocation - LightMonitoringService service",e);
    	 }
    	 return statusVO;
    }
    public LightMonitoringVO getCompanyWiseLightMonitoringDtls(String startDate,String endDate,String locationType,List<Long> locationValues,List<Long> lightVendorIds){
    	 LightMonitoringVO finalVO = new LightMonitoringVO();
		 try{	
					 Date fromDate = null;
					 Date toDate = null;
					 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					 if(startDate != null && startDate.trim().length() > 0 && endDate != null && endDate.trim().length() > 0){
						fromDate = sdf.parse(startDate);
						toDate = sdf.parse(endDate);
					 }	
					 
				     List<Object[]>  lightDtlsObjList  =  lightMonitoringDAO.getTotalVillagesDetails(fromDate,toDate,locationType,locationValues,lightVendorIds,"Yes");
				     List<Object[]> wattgedtlsObjList = lightWattageDAO.getTotalWattege(fromDate,toDate,locationType,locationValues,lightVendorIds,"Yes");
				     List<Object[]> surveyStartedLctnDtlsObjList = lightMonitoringDAO.getTotalSurveyDetails(fromDate,toDate, locationType, locationValues,lightVendorIds,"Yes"); 
				    
				     Map<Long,LightMonitoringVO> lightsVendorWiseDtlsMap = getSurveyStartedLocation(surveyStartedLctnDtlsObjList);
				     Map<Long,List<LightWattageVO>> wattageDtlsMap = getLightVendorWiseLightWattageDtls(wattgedtlsObjList);
				     Map<Long,LightMonitoringVO> lightsDtlsMap = getLightVendorWiseLightsDetails(lightDtlsObjList);
				     
				     if (lightsVendorWiseDtlsMap != null && lightsVendorWiseDtlsMap.size() > 0) {
				    	 for (Entry<Long, LightMonitoringVO> vendorTypeEntry : lightsVendorWiseDtlsMap.entrySet()) {
				    		 vendorTypeEntry.getValue().setWattageList(wattageDtlsMap.get(vendorTypeEntry.getKey()));
				    		 LightMonitoringVO lightDtlsVO = lightsDtlsMap.get(vendorTypeEntry.getKey());
				    		  if (lightDtlsVO != null ) {
				    			  vendorTypeEntry.getValue().setTotalLights(lightDtlsVO.getTotalLights());
				    			  vendorTypeEntry.getValue().setTotalPanels(lightDtlsVO.getTotalPanels());
				    			  vendorTypeEntry.getValue().setTotalPoles(lightDtlsVO.getTotalPoles());
				    			  vendorTypeEntry.getValue().setWorkingLights(lightDtlsVO.getWorkingLights());
				    			  vendorTypeEntry.getValue().setOnLights(lightDtlsVO.getOnLights());
				    			  vendorTypeEntry.getValue().setOffLights(lightDtlsVO.getOffLights());
				    			  vendorTypeEntry.getValue().setNotWorkingLights(lightDtlsVO.getNotWorkingLights());
				    		  }
							 if (vendorTypeEntry.getKey() == 1l) {
								 finalVO.setEeslVO(vendorTypeEntry.getValue());
							 } else if(vendorTypeEntry.getKey() == 2l){
								 finalVO.setNredcapVO(vendorTypeEntry.getValue());
							 }
						}
				     }
		      
	   } catch (Exception e) {
    	   LOG.error("Exception raised at getIndividualCompanyDetails - LightMonitoringService service", e);
       }
    	return finalVO;
    }
    private Map<Long,LightMonitoringVO> getSurveyStartedLocation(List<Object[]> objList) {
    	Map<Long,LightMonitoringVO> companyDtlsMap = new HashMap<Long,LightMonitoringVO>();
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
				     LightMonitoringVO lightsVendorDtlsVO = new LightMonitoringVO();
				     lightsVendorDtlsVO.setSurveyStartedtotalDistricts(commonMethodsUtilService.getLongValueForObject(param[2]));// No of districts
				     lightsVendorDtlsVO.setSurveyStartedtotalConstituencys(commonMethodsUtilService.getLongValueForObject(param[3]));// no of constituencies
				     lightsVendorDtlsVO.setSurveyStartedtotalMandals(commonMethodsUtilService.getLongValueForObject(param[4]));// no of mandal
				     lightsVendorDtlsVO.setSurveyStartedtotalGps(commonMethodsUtilService.getLongValueForObject(param[5]));// No of panchayats
				     companyDtlsMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), lightsVendorDtlsVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getSurveyStartedLocation - LightMonitoringService service",e);
		}
		return companyDtlsMap;
	}
    private Map<Long,List<LightWattageVO>> getLightVendorWiseLightWattageDtls(List<Object[]> objList) {
    	Map<Long,List<LightWattageVO>> companyWiseLightWattageDtlsMap = new HashMap<>();
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
				      List<LightWattageVO> wattageList = companyWiseLightWattageDtlsMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				       if (wattageList == null ) {
				    	   wattageList = new ArrayList<LightWattageVO>(0);
				    	   companyWiseLightWattageDtlsMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), wattageList);
				       }
				    	LightWattageVO wattagVO = new LightWattageVO();
						wattagVO.setWattage(commonMethodsUtilService.getLongValueForObject(param[2]));
						wattagVO.setLightCount(commonMethodsUtilService.getLongValueForObject(param[3]));
						wattageList.add(wattagVO);
				     
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getLightVendorWiseLightWattageDtls - LightMonitoringService service",e);
		}
		return companyWiseLightWattageDtlsMap;
	}
    
    private Map<Long,LightMonitoringVO> getLightVendorWiseLightsDetails(List<Object[]> objList) {
    	Map<Long,LightMonitoringVO> companyDtlsMap = new HashMap<Long,LightMonitoringVO>();
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
				     LightMonitoringVO lightsVendorDtlsVO = new LightMonitoringVO();
				     lightsVendorDtlsVO.setTotalLights(commonMethodsUtilService.getLongValueForObject(param[2]));
				     lightsVendorDtlsVO.setTotalPanels(commonMethodsUtilService.getLongValueForObject(param[3]));
				     lightsVendorDtlsVO.setTotalPoles(commonMethodsUtilService.getLongValueForObject(param[4]));
				     lightsVendorDtlsVO.setWorkingLights(commonMethodsUtilService.getLongValueForObject(param[5]));
				     lightsVendorDtlsVO.setOnLights(commonMethodsUtilService.getLongValueForObject(param[6]));
				     lightsVendorDtlsVO.setOffLights(commonMethodsUtilService.getLongValueForObject(param[7]));
				     lightsVendorDtlsVO.setNotWorkingLights(commonMethodsUtilService.getLongValueForObject(param[8]));
				     companyDtlsMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), lightsVendorDtlsVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getLightVendorWiseLightsDetails - LightMonitoringService service",e);
		}
		return companyDtlsMap;
	}

	public List<LightsVendor> getLightsVendorList() {
		try {
			return lightsVendorDAO.getAll();
		} catch (Exception e) {
			LOG.error("Exception raised at getLightsVendorList - LightMonitoringService service",e);
		}
		return null;
	}
 
}
	
	        	
