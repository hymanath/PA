package com.itgrids.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IDistrictDAO;
import com.itgrids.dao.ILightMonitoringDAO;
import com.itgrids.dao.ILightWattageDAO;
import com.itgrids.dao.IWebserviceCallDetailsDAO;
import com.itgrids.dto.AddressVO;
import com.itgrids.dto.LedOverviewVo;
import com.itgrids.dto.LightMonitoringVO;
import com.itgrids.dto.LightWattageVO;
import com.itgrids.dto.ResultVO;
import com.itgrids.model.LightMonitoring;
import com.itgrids.model.LightWattage;
import com.itgrids.service.ILightMonitoring;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.DateUtilService;
import com.itgrids.utils.IConstants;
import com.sun.jersey.api.client.ClientResponse;

@Service
@Transactional
public class LightMonitoringService  implements ILightMonitoring{    

	private static final Logger LOG = Logger.getLogger(LightMonitoringService.class);


	@Autowired
	private WebServiceUtilService webServiceUtilService;
	@Autowired
	private IWebserviceCallDetailsDAO webserviceCallDetailsDAO;
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	@Autowired
	private IDistrictDAO districtDAO;
	@Autowired
	private ILightWattageDAO lightWattageDAO; 
	@Autowired
	private DateUtilService dateUtilService;
	@Autowired
	private ILightMonitoringDAO lightMonitoringDAO;
		   
	/*
	 * Date : 02/08/2017
	 * Author :Swapna
	 */
	public ResultVO saveRealtimeStatusByVillages() {
		ResultVO status=new ResultVO();
		try {			
			   String inputStr = "";
			   inputStr = "{";
			   inputStr += "\"ClientId\" : \"AP_GOVT\"";
			   inputStr += "}";
			   
			ClientResponse response = webServiceUtilService.callWebService("http://greenlightt.monitormymeter.com/api/RestRealtimeAPI/GetRealtimeStatusByVillages",inputStr);
			SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
	        if(response.getStatus() != 200)
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	        else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 JSONObject responseJsonObj = new JSONObject(output);
	 	    	 JSONObject dataObj = responseJsonObj.getJSONObject("responseData");
	 	    	 JSONObject statusObj = dataObj.getJSONObject("requestStatus");
	 	    	 Long statusCode = statusObj.getLong("StatusCode");
	 	    	 String statusMessage = statusObj.getString("Message");
	 	    	 if(output != null && !output.isEmpty() && statusCode!= null && statusCode.longValue() ==0l && statusMessage != null && statusMessage.equalsIgnoreCase("Success"))
	 	    	 {
	 	    		List<LightMonitoringVO> resultData = processLightData(output);
	 	    		 
	 	    		if(resultData != null && resultData.size() > 0)
	 	    		 {
	 	    			   /*Update lightMonitoring Data */
	 	    			    List<Long> lighMonitoringIds = lightMonitoringDAO.getLightMonitroingIds(sdf.parse(dateUtilService.getCurrentDateInStringFormat()));
	    					int updatedCount = lightMonitoringDAO.updateLightMoitoringData(sdf.parse(dateUtilService.getCurrentDateInStringFormat()));
	    					
	    					if (lighMonitoringIds != null && lighMonitoringIds.size() > 0 ) {
	    						int updatedLightWattageCount = lightMonitoringDAO.updateLightWattageMoitoringData(lighMonitoringIds);	
	    					}	
	    					
	 	    			 for(LightMonitoringVO lightMonitoringVO : resultData)
	 	    			 {
	 	    				 try{
	 	    					/*List<LightMonitoring> LM = lightMonitoringDAO.getLiveDateForCurrentDateSelection(sdf.parse(dateUtilService.getCurrentDateInStringFormat()));
	 	    					if(LM != null && LM.size() >0){
	 	    						for (LightMonitoring lightMonitoring : LM) {
	 	    							lightMonitoring.setIsDeleted("Y");
	 	    							
	 	    							lightMonitoringDAO.save(lightMonitoring);
									}
	 	    					}*/
	 	    					
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
	 	    				 LOG.error(e);
	 	    			 }
	 	    			 
	 	    		}
	 	    		   status.setStatusCode(0);
	 		 		   status.setMessage("SUCCESS");
	 	    		 }
	 	    	 } else {
	 	    		 status.setStatusCode(1);
		 		 	 status.setMessage("FALURE");
	 	    	 }
	 	       }	  
	 	     } catch (Exception e) {
				 status.setStatusCode(1);
	 		 	 status.setMessage("FALURE");
	 			LOG.error("Exception raised at saveRealtimeStatusByVillages - LED service", e);
	 		}
		return status;    	
	 }
	
	     public List<LightMonitoringVO> processLightData(String output)
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
	    				  finalArray = dataObj.getJSONArray("VillageInfo");
	    			 }
	    			 if (finalArray != null && finalArray.length() > 0 ){
	    				 for(int i=0;i<finalArray.length();i++)
		    			 {
		    				 try{
		    					 
		    					 JSONObject jObj = (JSONObject) finalArray.get(i);
		    					 Long panchayatId = jObj.getLong("VillageId");
		    					 
		    					 if(panchayatId != null && panchayatId.longValue() > 0l)
		    					 {
		    						   LightMonitoringVO lightMonitoringVO = new LightMonitoringVO();
		    						   lightMonitoringVO.setPanchayatId(panchayatId);
		    						   lightMonitoringVO.setTotalPanels(jObj.getLong("TotalPanels"));
		    						   //lightMonitoringVO.setTotalPoles(jObj.getLong("TotalPoles"));
		    						   lightMonitoringVO.setTotalPoles(jObj.getLong("TotalLights"));//total light is nothing but total poles
		    						   lightMonitoringVO.setTotalLights(jObj.getLong("TotalLights"));
			    					   lightMonitoringVO.setNotWorkingLights(jObj.getLong("NonOperationalLights"));
			    					   lightMonitoringVO.setWorkingLights(jObj.getLong("OperationalLights"));
			    					   lightMonitoringVO.setOnLights(jObj.getLong("ONLights"));
			    					   lightMonitoringVO.setOffLights(jObj.getLong("OFFLights"));
			    					 
			    					 JSONArray arr =  jObj.getJSONArray("Wattages");
				 		 	    		
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
												lightWattageVO.setWattage(jsonob.getLong("Wattage"));
												lightWattageVO.setLightCount(jsonob.getLong("LightCount"));
												wattageList.add(lightWattageVO);
			 		 	    				}
			 		 	    			}catch (Exception e) {
			 		 	    				LOG.error(e);
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
	public List<LightMonitoringVO> getBasicLedOverviewDetails(String startDate,String endDate, String locationType,final Long locationValue) {
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
		     List<Object[]> lightMonitoringData  =  lightMonitoringDAO.getTotalVillagesDetails(fromDate,toDate,locationType,loccationIds);
		     if(lightMonitoringData!=null && lightMonitoringData.size()>0 && !lightMonitoringData.isEmpty()){
		    	 LightMonitoringVO lightMonitoringVO= new LightMonitoringVO();
			     for (Object[] objects : lightMonitoringData) {			
			    	     lightMonitoringVO.setTotalLights(objects[0] !=null ? (Long)objects[0]:0l);
			    	     lightMonitoringVO.setTotalPanels(objects[1] !=null ? (Long)objects[1]:0l);	
					     lightMonitoringVO.setTotalPoles(objects[2] !=null ? (Long)objects[2]:0l);
					     lightMonitoringVO.setWorkingLights(objects[3] !=null ? (Long)objects[3]:0l);
					     lightMonitoringVO.setOnLights(objects[4] !=null ? (Long)objects[4]:0l);
					     lightMonitoringVO.setOffLights(objects[5] !=null ?(Long)objects[5]:0l);
				         lightMonitoringVO.setNotWorkingLights(objects[6] !=null ?(Long)objects[6]:0l);
		
				         List<Object[]> wattegeCount = lightWattageDAO.getTotalWattege(fromDate,toDate,locationType,loccationIds);
					       
					       if(wattegeCount!=null && wattegeCount.size()>0 && !wattegeCount.isEmpty()){
					    	   for (Object[] objects2 : wattegeCount) {	
					    		   LightWattageVO wattagVO=new LightWattageVO();
							    	wattagVO.setWattage(objects2[0]!=null?(Long)objects2[0]:0l);
							    	wattagVO.setLightCount(objects2[1]!=null?(Long)objects2[1]:0l);		    	
							       	lightMonitoringVO.getWattageList().add(wattagVO); 	
							    }
					        }
		       list.add(lightMonitoringVO);
		     }
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
	public List<LedOverviewVo> getLedOverviewForStartedLocationsDetailsCounts(String startDate,String endDate,String locationType, final Long locationValue){
	  List<LedOverviewVo>listVO=new ArrayList<LedOverviewVo>(0);
	  try
	  {
		    Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if(startDate != null && startDate.trim().length() > 0 && endDate != null && endDate.trim().length() > 0){
				fromDate = sdf.parse(startDate);
				toDate = sdf.parse(endDate);
			}
			List<Long> loccationIds = new ArrayList<Long>();
			if (locationValue != null && locationValue.longValue() > 0l) {
				loccationIds.add(locationValue);
			}
		  List<Object[]> counts = lightMonitoringDAO.getTotalSurveyDetails(fromDate,toDate, locationType,loccationIds);
			  if (counts!=null && counts.size()>0 &&!counts.isEmpty())
			  {				  
				 for (Object[] objects : counts) {
				  LedOverviewVo  resultVo = new LedOverviewVo();
				  		resultVo.setTotalDistCnt(commonMethodsUtilService.getLongValueForObject(objects[0])!=null?(Long)objects[0]:0l);//No of districts 
				  		resultVo.setTotalConstituencyCnt(commonMethodsUtilService.getLongValueForObject(objects[1])!=null?(Long)objects[1]:0l);//no of constituencies
				  		resultVo.setTotalMandalCnt(commonMethodsUtilService.getLongValueForObject(objects[2])!=null?(Long)objects[2]:0l);// no of mandal	  					
				  		resultVo.setTotalpanchayatCnt(commonMethodsUtilService.getLongValueForObject(objects[3])!=null?(Long)objects[3]:0l);//No of panchayats	  
					  listVO.add(resultVo);
				  }
			  }
		}catch (Exception e) {
			LOG.error("Exception raised at getLedOverviewForStatedLocationsDetailsCounts - LightMonitoringService service", e);
		}
	       return listVO;
	  }
	  
	/*
 	 * Date : 11/08/2017
 	 * Author :Swapna
 	 */
	@Override
	public List<LightMonitoringVO> getAllLevelWiseDataOverView(String locationType,String filterType, Long filterValue,String fromDateStr,String toDateStr) {
		List<LightMonitoringVO> returnList = new ArrayList<LightMonitoringVO>();
		try {
				Date fromDate = null;
				Date toDate = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
				}
			      List<Object[]> totalLocObj =lightMonitoringDAO. getLocationsForLEDDashboard(locationType, filterType, filterValue);//getting location template
			      List<Object[]> lightMonObjLst =   lightMonitoringDAO.getLocationWiseDataForLEDDashboard(locationType,filterType,filterValue,fromDate,toDate) ;//getting survey data	
			      Map<Long,LightMonitoringVO> locationMap = setStartedSurveryDataLocationWise(totalLocObj);
			      
			      if(lightMonObjLst!=null && lightMonObjLst.size()>0) {	 
                	 for (Object[] param : lightMonObjLst) {
                		    Long locationId = commonMethodsUtilService.getLongValueForObject(param[9]);
                		    LightMonitoringVO locationVO = locationMap.get(locationId);
                		      if (locationVO != null ){
                		    	locationVO.setSurveyStartedtotalMandals(commonMethodsUtilService.getLongValueForObject(param[0]));
                      		    locationVO.setSurveyStartedtotalGps(commonMethodsUtilService.getLongValueForObject(param[1]));  
                      		    locationVO.setTotalPoles(commonMethodsUtilService.getLongValueForObject(param[2]));
                      		    locationVO.setTotalPanels(commonMethodsUtilService.getLongValueForObject(param[3]));
                      		    locationVO.setTotalLights(commonMethodsUtilService.getLongValueForObject(param[4]));
                      		    locationVO.setWorkingLights(commonMethodsUtilService.getLongValueForObject(param[5]));
                      		    locationVO.setOnLights(commonMethodsUtilService.getLongValueForObject(param[6]));	
                      		    locationVO.setOffLights(commonMethodsUtilService.getLongValueForObject(param[7])); 
                      		    locationVO.setNotWorkingLights(commonMethodsUtilService.getLongValueForObject(param[8]));
                      		    locationVO.setTotalLedLIghtInstalledCount(locationVO.getOnLights()+locationVO.getOffLights());
                		      }
                    }                 
                   } 	
			    if (locationMap != null && locationMap.size() > 0 ) {
			    	returnList.addAll(locationMap.values());
			    }
       }catch (Exception e) {
			LOG.error("Exception raised at getAllLevelWiseDataOverView - LightMonitoringService service", e);
		}
		return returnList;
	}
	public Map<Long,LightMonitoringVO> setStartedSurveryDataLocationWise(List<Object[]> objList ) {
		Map<Long,LightMonitoringVO> locationDtlsMap = new HashMap<>(0);
		 try {
			  if (objList != null && objList.size() > 0 ){
				   for (Object[] param : objList) {
					   LightMonitoringVO locationVO = new LightMonitoringVO();
					   locationVO.setTotalMandals(commonMethodsUtilService.getLongValueForObject(param[0]));//survey started mandal
					   locationVO.setTotalGps(commonMethodsUtilService.getLongValueForObject(param[1]));//survey started village
					   locationVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[2]));
					   locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[3]));
					   
					    AddressVO addressVO = new AddressVO();
						addressVO.setStateId(commonMethodsUtilService.getLongValueForObject(param[4]));
						addressVO.setStateName(commonMethodsUtilService.getStringValueForObject(param[5]));
						addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[6]));
						addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[7]));
						addressVO.setAssemblyId(commonMethodsUtilService.getLongValueForObject(param[8]));
						addressVO.setAssemblyName(commonMethodsUtilService.getStringValueForObject(param[9]));
						addressVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(param[10]));
						addressVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[11]));
						addressVO.setPanchayatId(commonMethodsUtilService.getLongValueForObject(param[12]));
						addressVO.setPanchayatName(commonMethodsUtilService.getStringValueForObject(param[13]));
						addressVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[14]));
						addressVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(param[15]));
						locationVO.setAddressVO(addressVO);
					   
					   locationDtlsMap.put(locationVO.getLocationId(), locationVO);
				}
			  }
		 } catch (Exception e ){
			 LOG.error("Exception raised at setStartedSurveryDataLocationWise - LightMonitoringService service", e);
		 }
		 return locationDtlsMap;
	 }
	/*
 	 * Date : 17/08/2017
 	 * Author :Swapna
 	 */
	@Override
	public List<LightMonitoringVO> getLocationBasedOnSelection(String locationType,String filterType, Long filterValue) {
		List<LightMonitoringVO> returnList = new ArrayList<LightMonitoringVO>();
		try{
			 List<Object[]> totalFilterObj =lightMonitoringDAO. getLocationsForLEDDashboard(locationType, filterType, filterValue);
			if(totalFilterObj!=null && totalFilterObj.size()>0){
		    for (Object[] objects : totalFilterObj) {
		       LightMonitoringVO locationVO = new LightMonitoringVO();
		       locationVO.setLocationId(commonMethodsUtilService.getLongValueForObject(objects[2]));
		       locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(objects[3]));
		       returnList.add(locationVO);		    				
			  }
		 }		
	         }catch (Exception e) {
			LOG.error("Exception raised at getLocationBasedOnSelection - LightMonitoringService service", e);
		}		
		return returnList;
	}
	
    @Override
	public LightMonitoringVO getCompanyWiseLightMonitoringDtls(String startDate,String endDate, String locationType,Long locationValue) {
		 LightMonitoringVO finalVO = new LightMonitoringVO();
		 List<Long> nredcapDistrictIdList = new ArrayList<Long>(){{add(11l);add(12l);add(13l);add(14l);add(15l);add(16l);add(21l);}};
		 List<Long> eeslDistrictIdList = new ArrayList<Long>(){{add(17l);add(18l);add(19l);add(20l);add(22l);add(23l);}};
		try{	
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if(startDate != null && startDate.trim().length() > 0 && endDate != null && endDate.trim().length() > 0){
				fromDate = sdf.parse(startDate);
				toDate = sdf.parse(endDate);
			}
			 //Prepare Parameter
			 if (locationValue != null && locationValue > 0l) {
				 if (nredcapDistrictIdList.contains(locationValue)) {
					 nredcapDistrictIdList.clear();
					 nredcapDistrictIdList.add(locationValue);
				 } else {
					 nredcapDistrictIdList.clear();
				 }
                 if (eeslDistrictIdList.contains(locationValue)) {
                	 eeslDistrictIdList.clear();
                	 eeslDistrictIdList.add(locationValue);
				 } else {
					 eeslDistrictIdList.clear();
				 }
			 }
			 if(locationType== null || locationType.trim().length()==0){
				 locationType = "district";
			 }
			 
			  if (nredcapDistrictIdList.size() > 0) {
				     List<Object[]>  nredcaplightMonitoringObjLst  =  lightMonitoringDAO.getTotalVillagesDetails(fromDate,toDate,locationType,nredcapDistrictIdList);
				     List<Object[]> nredcapwattegeObjList = lightWattageDAO.getTotalWattege(fromDate,toDate,locationType,nredcapDistrictIdList);
				     List<Object[]> nredcapSurveryStaredLocObjLst = lightMonitoringDAO.getTotalSurveyDetails(fromDate,toDate, locationType, nredcapDistrictIdList); 
				    
				     LightMonitoringVO nredDltsVO = getSurveyStartedLocation(nredcapSurveryStaredLocObjLst);
				     nredDltsVO.setWattageList(getLightWattageList(nredcapwattegeObjList));
				     setRequiredData(nredcaplightMonitoringObjLst,nredDltsVO);
				     finalVO.setNredcapVO(nredDltsVO);
			  }
			  
		      if (eeslDistrictIdList.size() > 0 ) {
		    	     List<Object[]>  eesllightMonitoringObjLst  =  lightMonitoringDAO.getTotalVillagesDetails(fromDate,toDate,locationType,eeslDistrictIdList);
				     List<Object[]> eeslwattegeObjList = lightWattageDAO.getTotalWattege(fromDate,toDate,locationType,eeslDistrictIdList);
				     List<Object[]> eeslSurveryStaredLocObjLst = lightMonitoringDAO.getTotalSurveyDetails(fromDate,toDate, locationType, eeslDistrictIdList); 
				     
				     LightMonitoringVO eeslDltsVO = getSurveyStartedLocation(eeslSurveryStaredLocObjLst);
				     eeslDltsVO.setWattageList(getLightWattageList(eeslwattegeObjList));
				     setRequiredData(eesllightMonitoringObjLst,eeslDltsVO);
				     finalVO.setEeslVO(eeslDltsVO);
		      }
		      
	   } catch (Exception e) {
    	   LOG.error("Exception raised at getCompanyWiseLightMonitoringDtls - LightMonitoringService service", e);
       }
		return finalVO;
   }

	public void setRequiredData(List<Object[]> objList,LightMonitoringVO resultVO) {
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] objects : objList) {
					resultVO.setTotalLights(objects[0] != null ? (Long) objects[0] : 0l);
					resultVO.setTotalPanels(objects[1] != null ? (Long) objects[1] : 0l);
					resultVO.setTotalPoles(objects[2] != null ? (Long) objects[2]  : 0l);
					resultVO.setWorkingLights(objects[3] != null ? (Long) objects[3]: 0l);
					resultVO.setOnLights(objects[4] != null ? (Long) objects[4] : 0l);
					resultVO.setOffLights(objects[5] != null ? (Long) objects[5] : 0l);
					resultVO.setNotWorkingLights(objects[6] != null ? (Long) objects[6] : 0l);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getBasicLedOverviewDetails - LightMonitoringService service",e);
		}
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

	public LightMonitoringVO getSurveyStartedLocation(List<Object[]> objList) {
		LightMonitoringVO surveyStaredDtlsVO = new LightMonitoringVO();
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] objects : objList) {
					surveyStaredDtlsVO.setSurveyStartedtotalDistricts(commonMethodsUtilService.getLongValueForObject(objects[0]) != null ? (Long) objects[0]: 0l);// No of districts
					surveyStaredDtlsVO.setSurveyStartedtotalConstituencys(commonMethodsUtilService.getLongValueForObject(objects[1]) != null ? (Long) objects[1]: 0l);// no of constituencies
					surveyStaredDtlsVO.setSurveyStartedtotalMandals(commonMethodsUtilService.getLongValueForObject(objects[2]) != null ? (Long) objects[2]: 0l);// no of mandal
					surveyStaredDtlsVO.setSurveyStartedtotalGps(commonMethodsUtilService.getLongValueForObject(objects[3]) != null ? (Long) objects[3]: 0l);// No of panchayats
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at setSurveyStartedLocation - LightMonitoringService service",e);
		}
		return surveyStaredDtlsVO;
	}
	  
}
	
	        	
