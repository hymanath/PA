package com.itgrids.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.ILightInstallationTargetDAO;
import com.itgrids.dao.ILightMonitoringDAO;
import com.itgrids.dao.ILightMonitoringDetailsDAO;
import com.itgrids.dao.ILightWattageDAO;
import com.itgrids.dao.ILightsVendorDAO;
import com.itgrids.dao.IPanchayatDAO;
import com.itgrids.dao.ITehsilDAO;
import com.itgrids.dto.AddressVO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LedOverviewVo;
import com.itgrids.dto.LightMonitoringVO;
import com.itgrids.dto.LightWattageVO;
import com.itgrids.dto.ResultVO;
import com.itgrids.model.LightMonitoring;
import com.itgrids.model.LightMonitoringDetails;
import com.itgrids.model.LightWattage;
import com.itgrids.model.LightsVendor;
import com.itgrids.service.ILightMonitoring;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.DateUtilService;
import com.sun.jersey.api.client.ClientResponse;

import io.swagger.models.auth.In;

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
	
	@Autowired
	private ILightInstallationTargetDAO lightInstallationTargetDAO;
	
	@Autowired
	private ITehsilDAO tehsilDAO;
	
	@Autowired
	private IPanchayatDAO panchayatDAO;
	
	@Autowired
	private ILightMonitoringDetailsDAO lightMonitoringDetailsDAO;
		   
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
	
	
	 public List<LightMonitoringVO> getTimePeriodWiseLightsDetails(String startDate,String endDate, String locationType, List<Long> locationValues,List<Long> lightMonitoringIds) {
		   List<LightMonitoringVO> list = new ArrayList<LightMonitoringVO>() ;
		try{	
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			DateUtilService dateUtilService = new DateUtilService();
			Date today = dateUtilService.getCurrentDateAndTime();
			
			List<Object[]> lightMonitorindDtlsObjList = lightMonitoringDAO.getDateWiseLightMonitoringDtls(getRequiredDateByPassingDays(62),today,locationType,locationValues);
			List<LightsVendor> vendorList = lightsVendorDAO.getAll();
			
			 //getting total lights
			 List<Object[]> objList = lightInstallationTargetDAO.getLedTargetVendorWise(today);
			 Map<String,LightMonitoringVO> targetMap = getLedTargetDateWise(objList);
			
			Map<String,LightMonitoringVO>  dateWiseLightsDtlsMap = prepareDateWiseLightMonitoringDtls(lightMonitorindDtlsObjList,vendorList);
			String[] templateArr = {"Today Total","Last 1 Day","Last 2 Day","Last 3 Day","Last 7 Day","Last 14 Day","Last 30 Day","Last 60 Day"};
			  
			 for (String string : templateArr) {
				  String dateStr = sdf.format(getRequiredDateByPassingDays(getDayPassingTimePeriod(string)));
				  LightMonitoringVO dateVO = dateWiseLightsDtlsMap.get(dateStr);
				  if (dateVO != null )  {
					  	dateVO.setTimePeriod(string);
					  if (!string.equalsIgnoreCase("Today Total")) {
						  if (dateVO.getSubList() != null && dateVO.getSubList().size() > 0) {
							  for(LightMonitoringVO vo:dateVO.getSubList()) {
								  LightMonitoringVO matchVO = getMatchVO(list.get(0).getSubList(), vo.getLightVendorId());
								   if (matchVO != null ) {
									   if (vo.getTotalLights() > 0) {
										  //new added light achievement
										   vo.setNewAddedLightcount(matchVO.getTotalLights()-vo.getTotalLights());
										   dateVO.setNewAddedLightcount(dateVO.getNewAddedLightcount()+vo.getNewAddedLightcount());
									   }
									   if (vo.getTotalPanels() > 0) {
										 //new added panel achievement
										   vo.setNewAddedPanelCount(matchVO.getTotalPanels()-vo.getTotalPanels());
										   dateVO.setNewAddedPanelCount(dateVO.getNewAddedPanelCount()+vo.getNewAddedPanelCount());
									   }
								   }
								   
									    String todayDateStr = sdf.format(today);
									    int noOfDay = getDayPassingTimePeriod(string);
									    int targetSize = targetMap.size();
									    if (targetSize >= noOfDay) {
									    	 List<String> dayList = DateUtilService.getDaysBetweenDatesStringFormat(getRequiredDateByPassingDays(--noOfDay), sdf.parse(todayDateStr));
											    if (dayList != null) {
											    	for (String stringDate : dayList) {
											    		  LightMonitoringVO targetVO = targetMap.get(stringDate);
											    		  if (targetVO != null ) {
											    			  LightMonitoringVO targetMatchvO = getMatchVO(targetVO.getSubList(), vo.getLightVendorId());   
															   if (targetMatchvO != null ) {
																   //setting light target
																   vo.setLightTarget(vo.getLightTarget() + targetMatchvO.getLightTarget());
																   dateVO.setLightTarget(dateVO.getLightTarget() + targetMatchvO.getLightTarget());
																   //setting panel target
																   vo.setPanelTarget(vo.getPanelTarget() + targetMatchvO.getPanelTarget());
																   dateVO.setPanelTarget(dateVO.getPanelTarget() + targetMatchvO.getPanelTarget());
															   }  
											    		  }
											    	}
											    }
									    }
								   }
							  
						  }
					  }
					  list.add(dateVO);	  
				  }
			}
			 //checking is new lights has added on not.if new lights has not added in sequence of date then placing zero.
			 if (list.size() > 0) {
				 for(int i = list.size()-1; i>0; i--) {
					 int j = i;
					 LightMonitoringVO oldDateVO = list.get(i);
					 LightMonitoringVO newDateVO = list.get(--j);
					 Long totalLight =  oldDateVO.getTotalLights()- newDateVO.getTotalLights();
					 if (totalLight == 0l) {
						 newDateVO.setNewAddedLightcount(0l);
					 }
					 Long totalPanel =  oldDateVO.getTotalPanels()- newDateVO.getTotalPanels();
					 if (totalPanel == 0l) {
						 newDateVO.setNewAddedPanelCount(0l);
					 }
					  if (newDateVO.getSubList() != null && newDateVO.getSubList().size() > 0) {
						  for(LightMonitoringVO vo:newDateVO.getSubList()) {
							  LightMonitoringVO matchVO = getMatchVO(oldDateVO.getSubList(), vo.getLightVendorId());
							   if (matchVO != null ) {
								   Long totalLghts = matchVO.getTotalLights()-vo.getTotalLights();
								   if (totalLight == 0l) {
									    if (totalLghts == 0l) {
									    	vo.setNewAddedLightcount(0l);
									    } 
								   }
								   Long ttlPanel = matchVO.getTotalPanels()-vo.getTotalPanels();
								   if (totalPanel == 0l) {
									    if (ttlPanel == 0l) {
									    	vo.setNewAddedPanelCount(0l);
									    } 
								   }
							   }
						  }
					  }
				 }
			 }
			
			// calculating overall target and achievement vendor wise
			  if (targetMap != null && targetMap.size() > 0) {
				  LightMonitoringVO overallDtlsVO = new LightMonitoringVO();
				    overallDtlsVO.setSubList(getVendorTemplate(vendorList));//setting template
				    setOverAllTargt(overallDtlsVO,targetMap);//setting over all target
				    
				     List<String> dateList = new ArrayList<String>(targetMap.keySet());
					 List<Object[]> lightsDtlsObjList = lightMonitoringDAO.getDateWiseLightMonitoringDtls(getOneDayBackDate(dateList.get(dateList.size()-1)),getOneDayBackDate(dateList.get(dateList.size()-1)),locationType,locationValues);
					 Map<String,LightMonitoringVO>  achivementMap = prepareDateWiseLightMonitoringDtls(lightsDtlsObjList,vendorList);
					 List<LightMonitoringVO> backDataVOList = null;
					 if (achivementMap != null ) {
						 backDataVOList = new ArrayList<>(achivementMap.values());
					 }
				
					 String dateStr = sdf.format(getRequiredDateByPassingDays(getDayPassingTimePeriod("Today Total")));
					 LightMonitoringVO todayDataVO = dateWiseLightsDtlsMap.get(dateStr);
					 if (todayDataVO != null ) {
						 for (LightMonitoringVO vo : todayDataVO.getSubList()) {
								LightMonitoringVO vendorMatchVO = getMatchVO(backDataVOList.get(0).getSubList(),vo.getLightVendorId());
								LightMonitoringVO resultMatchVO = getMatchVO(overallDtlsVO.getSubList(),vo.getLightVendorId());
								if (resultMatchVO != null) {
									if (vo.getTotalLights() > 0) {
										resultMatchVO.setNewAddedLightcount(vo.getTotalLights()-vendorMatchVO.getTotalLights());
									}
									if (vo.getTotalPanels() > 0 ) {
										resultMatchVO.setNewAddedPanelCount(vo.getTotalPanels()-vendorMatchVO.getTotalPanels());
									}
								}
						}
					 }
					 // calculating overall percentage
					 calculateOverllPercentage(overallDtlsVO);
					 SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
					 if (list.size() > 0) {
						 overallDtlsVO.setName(sdf1.format(sdf.parse(dateList.get(dateList.size()-1)))+" to "+sdf1.format(sdf.parse(dateList.get(0))));
						 list.get(0).setOverDtlsVO(overallDtlsVO);
					 }
			  }
			 
			 
	  }catch (Exception e) {
   	   LOG.error("Exception raised at getTimePeriodWiseLightsDetails - LightMonitoringService service", e);
      }
		return list;
      } 
	 public void calculateOverllPercentage(LightMonitoringVO overallDtlsVO) {
		  try {
			  if (overallDtlsVO.getSubList() != null ) {
					 for (LightMonitoringVO vendorVO : overallDtlsVO.getSubList()) {
						 vendorVO.setLightPercentage(calculatePercantage(vendorVO.getNewAddedLightcount(), vendorVO.getLightTarget()));
						 vendorVO.setPanelPercentage(calculatePercantage(vendorVO.getNewAddedPanelCount(), vendorVO.getPanelTarget()));
						 //overall 
						 overallDtlsVO.setNewAddedLightcount(overallDtlsVO.getNewAddedLightcount()+vendorVO.getNewAddedLightcount());
						 overallDtlsVO.setNewAddedPanelCount(overallDtlsVO.getNewAddedPanelCount()+vendorVO.getNewAddedPanelCount());
						 overallDtlsVO.setLightTarget(overallDtlsVO.getLightTarget()+vendorVO.getLightTarget());
						 overallDtlsVO.setPanelTarget(overallDtlsVO.getPanelTarget()+vendorVO.getPanelTarget());
					}
					 overallDtlsVO.setLightPercentage(calculatePercantage(overallDtlsVO.getNewAddedLightcount(), overallDtlsVO.getLightTarget()));
					 overallDtlsVO.setPanelPercentage(calculatePercantage(overallDtlsVO.getNewAddedPanelCount(), overallDtlsVO.getPanelTarget()));
				 }
		  } catch (Exception e) {
			  LOG.error("Exception raised at calculateOverllPercentage - LightMonitoringService service", e);
		  }
	 }

	public void setOverAllTargt(LightMonitoringVO overallDtlsVO,Map<String, LightMonitoringVO> targetMap) {
		try {
			if (targetMap != null) {
				for (Entry<String, LightMonitoringVO> entry : targetMap.entrySet()) {
					if (entry.getValue() != null && entry.getValue().getSubList().size() > 0) {
						for (LightMonitoringVO vo : entry.getValue().getSubList()) {
							LightMonitoringVO vendorVO = getMatchVO(overallDtlsVO.getSubList(),vo.getLightVendorId());
							if (vendorVO != null) {
								vendorVO.setLightTarget(vendorVO.getLightTarget() + vo.getLightTarget());
								vendorVO.setPanelTarget(vendorVO.getPanelTarget() + vo.getPanelTarget());
							}
						}

					}

				}
			}

		} catch (Exception e) {
			LOG.error("Exception raised at setOverAllTargt - LightMonitoringService service",e);
		}
	}
	//not used presently
	public LightMonitoringVO getOverAllAchievementDetails(Map<String, LightMonitoringVO> targetMap,Map<String, LightMonitoringVO> achivementMap,List<LightsVendor> vendorList) {
		LightMonitoringVO overAllDtlsVO = new LightMonitoringVO();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (targetMap != null && targetMap.size() > 0) {
				overAllDtlsVO.setSubList(getVendorTemplate(vendorList));//setting template
				//setting achievement count date wise
				for (Entry<String, LightMonitoringVO> entry : targetMap.entrySet()) {
					LightMonitoringVO achivementVO = achivementMap.get(entry.getKey());
					if (entry.getValue().getSubList() != null) {
						for (LightMonitoringVO vendorVO : entry.getValue().getSubList()) {
							if (achivementVO != null) {
								LightMonitoringVO vendorMatchVO = getMatchVO(achivementVO.getSubList(),vendorVO.getLightVendorId());
								if (vendorVO != null) {
									vendorVO.setTotalLights(vendorMatchVO.getTotalLights());
									vendorVO.setTotalPanels(vendorMatchVO.getTotalPanels());
								}
							}
						}
					}

				}
				//setting new added light by subtracting count date wise
				List<LightMonitoringVO> tempList = new ArrayList<>(targetMap.values());
				if (tempList.size() > 0) {
					 for(int i = 0; i< tempList.size()-1; i++) {
						 int j = i;
						 LightMonitoringVO newDateVO = tempList.get(i);
						 LightMonitoringVO oldDateVO = tempList.get(++j);
						 if (j == tempList.size()-1) { //if last object,.
							 
							 /*if it is last target date then we are taking one day back 
							 from your target date achievement data and subtracting from that 
							 to take how many new light and panel has added on that day.*/
							if (getOneDayBackDate(oldDateVO.getName()) != null ) {
								String dateStr = sdf.format(getOneDayBackDate(oldDateVO.getName()));
								LightMonitoringVO oneDayBackDateFromTarget = achivementMap.get(dateStr);
								setRequiredDate(overAllDtlsVO, oldDateVO, oneDayBackDateFromTarget); //setting overall values	 
							}
						 } else {
							 setRequiredDate(overAllDtlsVO, newDateVO, oldDateVO); //setting overall values	 
						 }
						
					 }
					 
					 //calculating percentage
					 calculateOverllPercentage(overAllDtlsVO);
				 }
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getOverAllAchievementDetails - LightMonitoringService service",e);
		}
		return overAllDtlsVO;
	}
	private void setRequiredDate(LightMonitoringVO overAllDtlsVO, LightMonitoringVO newDateVO,LightMonitoringVO oldDateVO) {
		 try {
			 if (newDateVO.getSubList() != null && newDateVO.getSubList().size() > 0) {
				  for(LightMonitoringVO vo:newDateVO.getSubList()) {
					  LightMonitoringVO matchVO = getMatchVO(oldDateVO.getSubList(), vo.getLightVendorId());
					   if (matchVO != null ) {
						   LightMonitoringVO  vendorMatchVO = getMatchVO(overAllDtlsVO.getSubList(), vo.getLightVendorId());//this match VO is from overall VO making final result
						   if (vendorMatchVO != null ) {
							   if (vo.getTotalLights() > 0) {
								   System.out.println("New Date "+newDateVO.getName()+" count "+vo.getTotalLights());
								   System.out.println("OLD Date "+oldDateVO.getName()+" count "+matchVO.getTotalLights());
								   Long totalLghts = vo.getTotalLights()-matchVO.getTotalLights();
								   System.out.println("New added light "+totalLghts);
								   if (StringUtils.isNumeric(totalLghts.toString())) {
									   vendorMatchVO.setNewAddedLightcount(vendorMatchVO.getNewAddedLightcount()+totalLghts);
								   }
								 
							   } 
							   if (vo.getTotalPanels() > 0) {
								   Long ttlPanel = vo.getTotalPanels()-matchVO.getTotalPanels();
								   if (StringUtils.isNumeric(ttlPanel.toString())) { 
									   vendorMatchVO.setNewAddedPanelCount(vendorMatchVO.getNewAddedPanelCount()+ttlPanel); 
								   }
							   }
							   vendorMatchVO.setLightTarget(vendorMatchVO.getLightTarget() + vo.getLightTarget());
							   vendorMatchVO.setPanelTarget(vendorMatchVO.getPanelTarget() + vo.getPanelTarget());
						   }
					   }
				  }
			  }
		 } catch (Exception e) {
			 LOG.error("Exception raised at setRequiredDate - LightMonitoringService service",e);
		 }
	}

	public Map<String, LightMonitoringVO> getLedTargetDateWise(List<Object[]> objList) {
		HashMap<String, LightMonitoringVO> targetMap = new LinkedHashMap<String,LightMonitoringVO>();
		try {
			if (objList != null && objList.size() > 0) {
				List<LightsVendor> vendorList = lightsVendorDAO.getAll();
				for (Object[] param : objList) {
					LightMonitoringVO targetVO = targetMap.get(commonMethodsUtilService.getStringValueForObject(param[0]));
					if (targetVO == null) {
						targetVO = new LightMonitoringVO();
						targetVO.setName(commonMethodsUtilService.getStringValueForObject(param[0]));
						targetVO.setSubList(getVendorTemplate(vendorList));
						targetMap.put(targetVO.getName(), targetVO);
					}
					Long lightVendorId = commonMethodsUtilService.getLongValueForObject(param[1]);
					LightMonitoringVO vendorVO = getMatchVO(targetVO.getSubList(), lightVendorId);
					if (vendorVO != null) {
						vendorVO.setLightTarget(commonMethodsUtilService.getLongValueForObject(param[2]));
						vendorVO.setPanelTarget(commonMethodsUtilService.getLongValueForObject(param[3]));
					}

				}
			}

		} catch (Exception e) {
			LOG.error("Exception raised at getLedTargetDateWise - LightMonitoringService service",e);
		}
		return targetMap;
	}
		
	 
	private Map<String, LightMonitoringVO> prepareDateWiseLightMonitoringDtls(List<Object[]> objList,List<LightsVendor> lightsVendor) {
		Map<String, LightMonitoringVO> dateWiseMap = new HashMap<String, LightMonitoringVO>();
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					LightMonitoringVO dateVO = dateWiseMap.get(commonMethodsUtilService.getStringValueForObject(param[0]));
					if (dateVO == null) {
						dateVO = new LightMonitoringVO();
						dateVO.setSubList(new ArrayList<LightMonitoringVO>());
						dateVO.setSubList(getVendorTemplate(lightsVendor));
						dateVO.setName(commonMethodsUtilService.getStringValueForObject(param[0]));
						dateWiseMap.put(commonMethodsUtilService.getStringValueForObject(param[0]), dateVO);
					}
					Long vendorId = commonMethodsUtilService.getLongValueForObject(param[1]);
					LightMonitoringVO vendorVO = getMatchVO(dateVO.getSubList(), vendorId);
					if (vendorVO != null ) {
						vendorVO.setTotalLights(commonMethodsUtilService.getLongValueForObject(param[3]));
						vendorVO.setTotalPanels(commonMethodsUtilService.getLongValueForObject(param[4]));
						dateVO.setTotalLights(dateVO.getTotalLights()+ vendorVO.getTotalLights());
						dateVO.setTotalPanels(dateVO.getTotalPanels()+ vendorVO.getTotalPanels());
							
					}
				}
			}

		} catch (Exception e) {
			LOG.error("Exception raised at prepareDateWiseLightMonitoringDtls - LightMonitoringService service",e);
		}
		return dateWiseMap;
	}

	private List<LightMonitoringVO> getVendorTemplate(List<LightsVendor> list) {
		List<LightMonitoringVO> vendorList = new ArrayList<>();
		try {
			if (list != null && list.size() > 0) {
				for (LightsVendor lightsVendorVO : list) {
					vendorList.add(new LightMonitoringVO(lightsVendorVO.getLightsVendorId(), lightsVendorVO.getVendorName()));
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getVendorTemplate - LightMonitoringService service",e);
		}
		return vendorList;
	}
	
    private int getDayPassingTimePeriod(String timePeriod) {
		int dayCount = 0;
		try {
			switch (timePeriod) {
			case "Today Total":
				dayCount = 0;
				break;
			case "Last 1 Day":
				dayCount = 1;
				break;
			case "Last 2 Day":
				dayCount = 2;
				break;
			case "Last 3 Day":
				dayCount = 3;
				break;
			case "Last 7 Day":
				dayCount = 7;
				break;
			case "Last 14 Day":
				dayCount = 14;
				break;
			case "Last 30 Day":
				dayCount = 30;
				break;
			case "Last 60 Day":
				dayCount = 60;
				break;
			default:
				dayCount = 0;
			}

		} catch (Exception e) {
			LOG.error("Exception raised at getDayPassingTimePeriod - BioMetricService service",e);
		}
		return dayCount;
	}
	private LightMonitoringVO getMatchVO(List<LightMonitoringVO> subList,Long vendorId) {
		 try {
			  if (subList == null || subList.size() == 0) {
				  return null;
			  }
			  for (LightMonitoringVO lightMonitoringVO : subList) {
				if (lightMonitoringVO.getLightVendorId() == vendorId) {
					return lightMonitoringVO;
				}
			}
			 
			 
		 } catch (Exception e) {
			 LOG.error("Exception raised at getMatchVO - BioMetricService service",e);
		 }
		 return null;
	}
	private Date getOneDayBackDate(String dateStr) {
		 try {
			   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar now = Calendar.getInstance();
				now.setTime(sdf.parse(dateStr));
				now.add(Calendar.DATE, -1);
				Date pastDate = now.getTime();
				String dateString = sdf.format(pastDate);
				return sdf.parse(dateString);
			 
		 } catch (Exception e) {
			 LOG.error("Exception raised at getOneDayBackDate - BioMetricService service",e);
		 }
		 return null;
	}
	private static Date getRequiredDateByPassingDays(int noOfDays) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar now = Calendar.getInstance();
				now.add(Calendar.DATE, -noOfDays);
				Date pastDate = now.getTime();
				String dateStr = sdf.format(pastDate);			
				return sdf.parse(dateStr);

			} catch (Exception e) {
				LOG.error("Exception raised at getDateBeforeNDays - LightMonitoringService service",e);
			}
			return null;
	} 
	 private Double calculatePercantage(Long subCount,Long totalCount){
		    Double d=0.0d;
		    if(subCount.longValue()>0l && totalCount.longValue()==0l)
		    LOG.error("Sub Count Value is "+subCount+" And Total Count Value  "+totalCount);

		    if(totalCount.longValue() > 0l){
		       d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();   
		    }
		    return d;
	 }
	 public String getLatestInsertedTime(){
		 String latestTime = "";
		 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm a ");
		  try {
			  Date dateTime = lightMonitoringDAO.getLatestInsertedTime();
			  if (dateTime != null ) {
				  latestTime = sdf.format(dateTime);
			  }
		  } catch (Exception e) {
			  LOG.error("Exception raised at getLatestInsertedTime - LightMonitoringService service",e);
		  }
		  return latestTime;
	 }
	 
 	/**
	 * @author Nandhini.k
	 * @description {This service is get Mandals For District.}
	 * @return List<IdNameVO>
	 * @Date 01-02-2018
	 */
	public List<IdNameVO> getMandalsByDistrict(InputVO inputVO){
		 List<IdNameVO> returnList = new ArrayList<IdNameVO>(0);
		 try {
			 List<Object[]> mndalList = tehsilDAO.getTehsilsFrDistrict(inputVO.getLocationId());
			 if(mndalList != null && !mndalList.isEmpty()){
				 for (Object[] param : mndalList) {
					 IdNameVO vo = new IdNameVO();
					 vo.setId(Long.valueOf(param[0] != null ? param[0].toString():"0"));
					 vo.setName(param[1] != null ? param[1].toString():"");
					 returnList.add(vo);
				}
			 }
			
		} catch (Exception e) {
			LOG.error("Exception raised at getMandalsByDistrict - LightMonitoringService service",e);
		}
	 return returnList;
	}
	
	/**
	 * @author Nandhini.k
	 * @description {This service is get Panchayats For Mandals.}
	 * @return List<IdNameVO>
	 * @Date 01-02-2018
	 */
	public List<IdNameVO> getPanchayatsByTehsil(InputVO inputVO){
		 List<IdNameVO> returnList = new ArrayList<IdNameVO>(0);
		 try {
			 List<Object[]> mndalList = panchayatDAO.getPanchayatsByMandald(inputVO.getLocationId());
			 if(mndalList != null && !mndalList.isEmpty()){
				 for (Object[] param : mndalList) {
					 IdNameVO vo = new IdNameVO();
					 vo.setId(Long.valueOf(param[0] != null ? param[0].toString():"0"));
					 vo.setName(param[1] != null ? param[1].toString():"");
					 returnList.add(vo);
				}
			 }
			
		} catch (Exception e) {
			LOG.error("Exception raised at getPanchayatsByTehsil - LightMonitoringService service",e);
		}
	 return returnList;
	}
	/**
	 * @author Nandhini.k
	 * @description {This service is Save Led Vendor Wise Details.}
	 * @return List<IdNameVO>
	 * @Date 01-02-2018
	 */
	public IdNameVO saveLEDVendorWiseDetails(InputVO inputVO,Long userId,Long vendorId){
		IdNameVO statusVO = new IdNameVO();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			LightMonitoringDetails model = new LightMonitoringDetails();
			if(inputVO.getLocationId() != null && inputVO.getLocationId().longValue() > 0L){
				model.setPanchayatId(inputVO.getLocationId());
			}
			if(vendorId != null && vendorId.longValue() > 0L){
				model.setLightVendorId(vendorId);
			}
			if(inputVO.getFromDate() != null){
				model.setWorkDate(sdf.parse(inputVO.getFromDate()));
			}
			if(inputVO.getSourceId() != null && inputVO.getSourceId().longValue() > 0L){
				model.setLightsFitted(inputVO.getSourceId());
			}
			if(inputVO.getSchemeId() != null && inputVO.getSchemeId().longValue() > 0L){
				model.setTeamWorked(inputVO.getSchemeId());
			}
			if(userId != null && userId.longValue() > 0L){
				model.setInsertedBy(userId);
			}
			model.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			lightMonitoringDetailsDAO.save(model);
			statusVO.setName("SUCCESS");
			
		} catch (Exception e) {
			LOG.error("Exception raised at getPanchayatsByTehsil - LightMonitoringService service",e);
		}
		return statusVO;
	}
	
	/**
	 * @author Nandhini.k
	 * @description {This service is get Today Vendor Details}
	 * @return LightMonitoringVO
	 * @Date 01-02-2018
	 */
	public List<LightMonitoringVO> getTodayVendorDetails(Long vendorId){
		List<LightMonitoringVO> returnList = new ArrayList<LightMonitoringVO>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date todayDate = dateUtilService.getCurrentDateAndTime();
			String todayDateStr = sdf.format(todayDate);
			Date toDate = null;
			if(todayDate != null){
				toDate = sdf.parse(todayDateStr);
			}
			
			//Today Data
			List<Object[]> todayList = lightMonitoringDetailsDAO.getTodayVendorDetails(toDate,vendorId);
			if(todayList != null && !todayList.isEmpty()){
				for (Object[] param : todayList) {
					LightMonitoringVO vo = new LightMonitoringVO();
					vo.setDistrictId(Long.valueOf(param[0] != null ? param[0].toString():"0"));
					vo.setDistrictName(param[1] != null ? param[1].toString():"");
					vo.setPanchayatId(Long.valueOf(param[2] != null ? param[2].toString():"0"));
					vo.setPanchayatName(param[3] != null ? param[3].toString():"");
					vo.setLightCount(Long.valueOf(param[4] != null ? param[4].toString():"0"));
					vo.setTeamCount(Long.valueOf(param[5] != null ? param[5].toString():"0"));
					returnList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at getTodayVendorDetails - LightMonitoringService service",e);
		}
		return returnList;
	}
	/**
	 * @author Nandhini.k
	 * @description {This service is get Level Wise Vendor Details}
	 * @return List<LightMonitoringVO>
	 * @Date 01-02-2018
	 */
	public List<LightMonitoringVO> getLevelWiseVendorDetails(InputVO inputVO,Long lightVendorId){
		List<LightMonitoringVO> returnList = new ArrayList<LightMonitoringVO>(0);
		try {
			Long locationId = 0L;
			String locationName = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date fromDate = null;
			Date toDate = null;
			if(inputVO.getFromDate() != null && inputVO.getToDate() != null){
				fromDate = sdf.parse(inputVO.getFromDate());
				toDate = sdf.parse(inputVO.getToDate());
			}
			Map<Long,LightMonitoringVO> locationMap = new HashMap<Long,LightMonitoringVO>(0);
			List<Object[]> locationList = lightMonitoringDetailsDAO.getLightVendorDetailsByLevelType(inputVO.getType(), lightVendorId,fromDate,toDate);
			if(locationList != null && !locationList.isEmpty()){
				for (Object[] param : locationList) {
					if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("district")){
						locationId = Long.valueOf(param[1] != null ? param[1].toString():"0");
					}else if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("panchayat")){
						locationId = Long.valueOf(param[3] != null ? param[3].toString():"0");
					}
					LightMonitoringVO locationVO = locationMap.get(locationId);
					if(locationVO == null){
						locationVO = new LightMonitoringVO();
						//if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("district")){
							locationVO.setDistrictId(Long.valueOf(param[1] != null ? param[1].toString():"0"));
							locationVO.setDistrictName(param[2] != null ? param[2].toString():"");
						 if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("panchayat")){
							locationVO.setPanchayatId(Long.valueOf(param[3] != null ? param[3].toString():"0"));
							locationVO.setPanchayatName(param[4] != null ? param[4].toString():"");
						}
						locationVO.setLightCount(Long.valueOf(param[5] != null ? param[5].toString():"0"));
						locationVO.setTeamCount(Long.valueOf(param[6] != null ? param[6].toString():"0"));
						locationMap.put(locationId, locationVO);
					}else{
						locationVO.setLightCount(locationVO.getLightCount()+Long.valueOf(param[5] != null ? param[5].toString():"0"));
						locationVO.setTeamCount(locationVO.getTeamCount()+Long.valueOf(param[6] != null ? param[6].toString():"0"));
					}
				}
			}
			
			
			if(commonMethodsUtilService.isMapValid(locationMap)){
				returnList = new ArrayList<LightMonitoringVO>(locationMap.values());
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at getLevelWiseVendorDetails - LightMonitoringService service",e);
		}
		return returnList;
	}
	/**
	 * @author Nandhini.k
	 * @description {This service is get Date Wise Vendor Details}
	 * @return LightMonitoringVO
	 * @Date 01-02-2018
	 */
	public LightMonitoringVO getDateWiseVendorDetails(InputVO inputVO,Long vendorId){
		LightMonitoringVO finalVO = new LightMonitoringVO();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date fromDate = null;
			Date toDate = null;
			if(inputVO.getFromDate() != null && inputVO.getToDate() != null){
				fromDate = sdf.parse(inputVO.getFromDate());
				toDate = sdf.parse(inputVO.getToDate());
			}
			
			List<Object[]> dateWiseList = lightMonitoringDetailsDAO.getLightsFittedCountBetweenDates(fromDate, toDate,vendorId);
			if(dateWiseList != null && !dateWiseList.isEmpty()){
				for (Object[] param : dateWiseList) {
					finalVO.setTotalVillages(Long.valueOf(param[0] != null ? param[0].toString():"0"));
					finalVO.setLightCount(Long.valueOf(param[1] != null ? param[1].toString():"0"));
					finalVO.setTeamCount(Long.valueOf(param[2] != null ? param[2].toString():"0"));
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at getTodayVendorDetails - LightMonitoringService service",e);
		}
		return finalVO;
	}
	/**
	 * @author Nandhini.k
	 * @description {This service is get Vendor DashBoard Details}
	 * @return LightMonitoringVO
	 * @Date 31-02-2018
	 */
	public List<LightMonitoringVO> getVendorDashBoardOverviewDetails(InputVO inputVO){
		List<LightMonitoringVO> finalList = new ArrayList<LightMonitoringVO>();
		try {
			Long vendorId = null; 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date fromDate = null;
			Date toDate = null;
			if(inputVO.getFromDate() != null && inputVO.getToDate() != null){
				fromDate = sdf.parse(inputVO.getFromDate());
				toDate = sdf.parse(inputVO.getToDate());
			}
			
			Map<Long,LightMonitoringVO> vendorMap = new HashMap<Long,LightMonitoringVO>(0);
			List<Object[]> panelCountList = lightMonitoringDAO.getVendorsCount(fromDate,toDate);
			if(panelCountList != null && !panelCountList.isEmpty()){
				for (Object[] param : panelCountList) {
					vendorId = Long.valueOf(param[0] != null ? param[0].toString():"0");
					LightMonitoringVO vendorVO = vendorMap.get(vendorId);
					if(vendorVO == null){
						vendorVO = new LightMonitoringVO();
						vendorVO.setLightVendorId(vendorId);
						vendorVO.setLightVendorName(param[1] != null ? param[1].toString():"");
						vendorVO.setTotalPanels(Long.valueOf(param[2] != null ? param[2].toString():"0"));
						vendorVO.setLightCount(Long.valueOf(param[3] != null ? param[3].toString():"0"));;
						vendorMap.put(vendorId, vendorVO);
					}
				}
				
			}
			
			List<Object[]> lightFittedList = lightMonitoringDetailsDAO.getVendorDetails(fromDate,toDate);
			if(lightFittedList != null && !lightFittedList.isEmpty()){
				for (Object[] param : lightFittedList) {
					vendorId = Long.valueOf(param[0] != null ? param[0].toString():"0");
					LightMonitoringVO vendorVO = vendorMap.get(vendorId);
					if(vendorVO != null){
						//vendorVO.setLightCount(Long.valueOf(param[1] != null ? param[1].toString():"0"));
						vendorVO.setTeamCount(Long.valueOf(param[2] != null ? param[2].toString():"0"));
						vendorVO.setLightCount(vendorVO.getLightCount()+Long.valueOf(param[1] != null ? param[1].toString():"0"));
					}
				}
				
			}
			
			if(vendorMap != null && !vendorMap.isEmpty()){
				finalList = new ArrayList<LightMonitoringVO>(vendorMap.values());
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at getVendorDashBoardOverviewDetails - LightMonitoringService service",e);
		}
		return finalList;
	}
	
	/**
	 * @author Nandhini.k
	 * @description {This service is get Level Wise Vendor Details}
	 * @return List<LightMonitoringVO>
	 * @Date 31-02-2018
	 */
	public List<LightMonitoringVO> getDashBoardLevelWiseVendorDetails(InputVO inputVO){
		List<LightMonitoringVO> returnList = new ArrayList<LightMonitoringVO>(0);
		try {
			Long locationId = 0L;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date fromDate = null;
			Date toDate = null;
			if(inputVO.getFromDate() != null && inputVO.getToDate() != null){
				fromDate = sdf.parse(inputVO.getFromDate());
				toDate = sdf.parse(inputVO.getToDate());
			}
			
			Map<Long,Map<Long,LightMonitoringVO>> ccmsCuntMap = new LinkedHashMap<Long,Map<Long,LightMonitoringVO>>(0);
			List<Object[]> locationList = lightMonitoringDetailsDAO.getAllVendorDetailsByLevelType(inputVO.getType(),fromDate,toDate);
			List<Object[]> ccmsCuntList = lightMonitoringDAO.getAllLevelCCMSVendorsCount(inputVO.getType(),fromDate,toDate);
				if(locationList != null && !locationList.isEmpty()){
					for (Object[] param : locationList) {
						LightMonitoringVO locationVO = new LightMonitoringVO();
						locationVO.setLightVendorId(Long.valueOf(param[0] != null ? param[0].toString():"0"));
						locationVO.setLightVendorName(param[1] != null ? param[1].toString():"");
						locationVO.setDistrictId(Long.valueOf(param[2] != null ? param[2].toString():"0"));
						locationVO.setDistrictName(param[3] != null ? param[3].toString():"");
						if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("panchayat")){
							locationVO.setPanchayatId(Long.valueOf(param[4] != null ? param[4].toString():"0"));
							locationVO.setPanchayatName(param[5] != null ? param[5].toString():"");
						}
						locationVO.setLightCount(Long.valueOf(param[6] != null ? param[6].toString():"0"));
						locationVO.setTeamCount(Long.valueOf(param[7] != null ? param[7].toString():"0"));
						returnList.add(locationVO);
					}
				}
				if(ccmsCuntList != null && !ccmsCuntList.isEmpty()){
					for (Object[] param : ccmsCuntList) {
						if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("district")){
							locationId = Long.valueOf(param[0] != null ? param[0].toString():"0");
						}else if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("panchayat")){
							locationId = Long.valueOf(param[2] != null ? param[2].toString():"0");
						}
						
						Long vendorId = Long.valueOf(param[4] != null ? param[4].toString():"0");
						Long count = Long.valueOf(param[5] != null ? param[5].toString():"0");
						
						Map<Long,LightMonitoringVO> vendorMap = ccmsCuntMap.get(locationId);
						if(vendorMap == null){
							vendorMap = new LinkedHashMap<Long,LightMonitoringVO>();
							LightMonitoringVO vo = new LightMonitoringVO();
							vo.setLightVendorId(vendorId);
							vo.setLightVendorName(param[6] != null ? param[6].toString():"");
							vo.setDistrictId(locationId);
							vo.setDistrictName(param[1] != null ? param[1].toString():"");
							if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("panchayat")){
								vo.setPanchayatId(locationId);
								vo.setPanchayatName(param[3] != null ? param[3].toString():"");
							}
							vo.setTotalPanels(count);
							vo.setTotalLights(Long.valueOf(param[7] != null ? param[7].toString():"0"));
							vendorMap.put(vendorId, vo);
							ccmsCuntMap.put(locationId, vendorMap);
						}else{
							LightMonitoringVO vo = vendorMap.get(vendorId);
							if(vo == null){
								vo = new LightMonitoringVO();
								vo.setLightVendorId(vendorId);
								vo.setLightVendorName(param[6] != null ? param[6].toString():"");
								vo.setDistrictId(locationId);
								vo.setDistrictName(param[1] != null ? param[1].toString():"");
								if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("panchayat")){
									vo.setPanchayatId(locationId);
									vo.setPanchayatName(param[3] != null ? param[3].toString():"");
								}
								vo.setTotalPanels(count);
								vo.setTotalLights(Long.valueOf(param[7] != null ? param[7].toString():"0"));
								vendorMap.put(vendorId, vo);
							}else{
								vo.setTotalPanels(vo.getTotalPanels()+count);
							}
						}
					}
				}
				
				/*if(ccmsCuntMap != null && !ccmsCuntMap.isEmpty()){
					for (Entry<Long, Map<Long, LightMonitoringVO>> entry : ccmsCuntMap.entrySet()) {
						 Map<Long, LightMonitoringVO> vendorMap = entry.getValue();
						 for (Entry<Long, LightMonitoringVO> vendorEntry : vendorMap.entrySet()) {
							 LightMonitoringVO ccmsCountDetailsVO = vendorEntry.getValue();
							 LightMonitoringVO ccmCountVO = getMatchedVOByVendorId(returnList,entry.getKey(),vendorEntry.getKey(),inputVO.getType());
							 if(ccmCountVO != null){
								 ccmCountVO.setTotalPanels(ccmsCountDetailsVO.getTotalPanels());
							 }else{
								 ccmCountVO = new LightMonitoringVO();
								 ccmCountVO.setLightVendorId(ccmsCountDetailsVO.getLightVendorId());
								 ccmCountVO.setLightVendorName(ccmsCountDetailsVO.getLightVendorName());
								 ccmCountVO.setDistrictId(ccmsCountDetailsVO.getDistrictId());
								 ccmCountVO.setDistrictName(ccmsCountDetailsVO.getDistrictName());
								 if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("panchayat")){
									 ccmCountVO.setPanchayatId(ccmsCountDetailsVO.getPanchayatId()); 
									 ccmCountVO.setPanchayatName(ccmsCountDetailsVO.getPanchayatName());
								 }
								 ccmCountVO.setTotalPanels(ccmsCountDetailsVO.getTotalPanels());
								 returnList.add(ccmCountVO);
							 }
						 }
					}
				}*/
				
				if(returnList != null && !returnList.isEmpty()){
					for (LightMonitoringVO vo : returnList) {
						Long lctnId = 0L;
						if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("district"))
							lctnId = vo.getDistrictId();
						else if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("panchayat"))
							lctnId = vo.getPanchayatId();
						Map<Long,LightMonitoringVO> vendorMap = ccmsCuntMap.get(lctnId);
						if(vendorMap != null){
							LightMonitoringVO ccmsvo = vendorMap.get(vo.getLightVendorId());
							if(ccmsvo != null){
								vo.setTotalPanels(ccmsvo.getTotalPanels());
								vo.setLightCount(vo.getLightCount()+ccmsvo.getTotalLights());
							}
						}
					}
				}
			
		} catch (Exception e) {
			LOG.error("Exception raised at getDashBoardLevelWiseVendorDetails - LightMonitoringService service",e);
		}
		return returnList;
	}
	/**
	 * @author Nandhini.k
	 * @description {This service is get District Wise Vendor Details}
	 * @return List<LightMonitoringVO>
	 * @Date 31-02-2018
	 */
	public List<LightMonitoringVO> getDashBoardVendorDetailsByLocationId(InputVO inputVO){
		List<LightMonitoringVO> returnList = new ArrayList<LightMonitoringVO>(0);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date fromDate = null;
			Date toDate = null;
			if(inputVO.getFromDate() != null && inputVO.getToDate() != null){
				fromDate = sdf.parse(inputVO.getFromDate());
				toDate = sdf.parse(inputVO.getToDate());
			}
			String dateStr = null;
			Map<String,LightMonitoringVO> dateMap = new LinkedHashMap<String,LightMonitoringVO>(0);
			List<Object[]> lightFitList = lightMonitoringDetailsDAO.getVendorDetailsForLocation(inputVO.getType(), fromDate, toDate, inputVO.getLocationId(), inputVO.getLightVendorId());
			if(lightFitList != null && !lightFitList.isEmpty()){
				for (Object[] param : lightFitList) {
					dateStr = param[0] != null ? param[0].toString():"";
					LightMonitoringVO vo = dateMap.get(dateStr);
					if(vo == null){
						vo = new LightMonitoringVO();
						vo.setWorkDate(dateStr);
						vo.setLightCount(Long.valueOf(param[1] != null ? param[1].toString():"0"));
						vo.setTeamCount(Long.valueOf(param[2] != null ? param[2].toString():"0"));
						dateMap.put(dateStr, vo);
					}else{
						vo.setLightCount(vo.getLightCount()+Long.valueOf(param[1] != null ? param[1].toString():"0"));
						vo.setTeamCount(vo.getTeamCount()+Long.valueOf(param[2] != null ? param[2].toString():"0"));
					}
				}
				
			}
			
		  List<Object[]> ccmsCuntList = lightMonitoringDAO.getCCMSVendorsCountForLocation(inputVO.getType(), fromDate, toDate, inputVO.getLocationId(), inputVO.getLightVendorId());
		  if(ccmsCuntList != null && !ccmsCuntList.isEmpty()){
			  for (Object[] param : ccmsCuntList) {
				dateStr = param[0] != null ? param[0].toString():"";
				LightMonitoringVO lightFittedVO = dateMap.get(dateStr);
				if(lightFittedVO != null){
					lightFittedVO.setTotalPanels(Long.valueOf(param[1] != null ? param[1].toString():"0"));
				}else{
					lightFittedVO = new LightMonitoringVO();
					lightFittedVO.setWorkDate(dateStr);
					lightFittedVO.setTotalPanels(Long.valueOf(param[1] != null ? param[1].toString():"0"));
					dateMap.put(dateStr, lightFittedVO);
				}
			}
		  }
		
		  if(dateMap != null && !dateMap.isEmpty()){
			  returnList = new ArrayList<LightMonitoringVO>(dateMap.values());
		  }
		
		} catch (Exception e) {
			LOG.error("Exception raised at getDashBoardVendorDetailsByLocationId - LightMonitoringService service",e);
		}
		return returnList;
	}
	
	public LightMonitoringVO getMatchedVOByVendorId(List<LightMonitoringVO> list,Long locationId,Long vendorId,String levelType){
		try {
			Long locationId1 = 0L;
			if(list != null && !list.isEmpty()){
				for (LightMonitoringVO lightMonitoringVO : list) {
					if(levelType != null && levelType.trim().equalsIgnoreCase("district")){
						locationId1 = lightMonitoringVO.getDistrictId();
					}else if(levelType != null && levelType.trim().equalsIgnoreCase("panchayat")){
						locationId1 = lightMonitoringVO.getPanchayatId();
					}
					if(locationId1 != null && locationId1.longValue() == locationId.longValue() 
					  && lightMonitoringVO.getLightVendorId() != null && vendorId != null && lightMonitoringVO.getLightVendorId().longValue() > 0L && vendorId.longValue() > 0L
					  && lightMonitoringVO.getLightVendorId().longValue() == vendorId.longValue()){
						return lightMonitoringVO;
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getMatchedVOByVendorId - LightMonitoringService service",e);
		} 
		return null;
	}
	/**
	 * @author Nandhini.k
	 * @description {This service is get Date Wise Vendor Details For Location}
	 * @return List<LightMonitoringVO>
	 * @Date 31-02-2018
	 */
	public List<LightMonitoringVO> getDateWiseVendorDetailsByLocationId(InputVO inputVO){
		List<LightMonitoringVO> returnList = new ArrayList<LightMonitoringVO>(0);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date fromDate = null;
			Date toDate = null;
			if(inputVO.getFromDate() != null && inputVO.getToDate() != null){
				fromDate = sdf.parse(inputVO.getFromDate());
				toDate = sdf.parse(inputVO.getToDate());
			}
			String dateStr = null;
			Map<String,LightMonitoringVO> dateMap = new LinkedHashMap<String,LightMonitoringVO>(0);
			List<Object[]> lightFitList = lightMonitoringDetailsDAO.getVendorDetailsForLocation(inputVO.getType(), fromDate, toDate, inputVO.getLocationId(), inputVO.getLightVendorId());
			if(lightFitList != null && !lightFitList.isEmpty()){
				for (Object[] param : lightFitList) {
					dateStr = param[0] != null ? param[0].toString():"";
					LightMonitoringVO vo = dateMap.get(dateStr);
					if(vo == null){
						vo = new LightMonitoringVO();
						vo.setWorkDate(dateStr);
						vo.setLightCount(Long.valueOf(param[1] != null ? param[1].toString():"0"));
						vo.setTeamCount(Long.valueOf(param[2] != null ? param[2].toString():"0"));
						dateMap.put(dateStr, vo);
					}else{
						vo.setLightCount(vo.getLightCount()+Long.valueOf(param[1] != null ? param[1].toString():"0"));
						vo.setTeamCount(vo.getTeamCount()+Long.valueOf(param[2] != null ? param[2].toString():"0"));
					}
				}
				
			}
		
		  if(dateMap != null && !dateMap.isEmpty()){
			  returnList = new ArrayList<LightMonitoringVO>(dateMap.values());
		  }
		
		} catch (Exception e) {
			LOG.error("Exception raised at getDashBoardVendorDetailsByLocationId - LightMonitoringService service",e);
		}
		return returnList;
	}
 }
	
	        	
