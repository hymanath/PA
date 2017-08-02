package com.itgrids.service.lmd.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.ILightMonitoringDAO;
import com.itgrids.dao.ILightWattageDAO;
import com.itgrids.dao.IWebserviceCallDetailsDAO;
import com.itgrids.dto.LightMonitoringVO;
import com.itgrids.dto.LightWattageVO;
import com.itgrids.dto.ResultVO;
import com.itgrids.led.service.ILightMonitoring;
import com.itgrids.model.LightMonitoring;
import com.itgrids.model.LightWattage;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.itgrids.utils.CommonMethodsUtilService;
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
	private ILightMonitoringDAO lightMonitoringDAO;
	@Autowired
	private ILightWattageDAO lightWattageDAO; 
	
		   
	/*
	@Override
	public List<LightMonitoringVO> getVillageIdBasedDetails() {
		LightMonitoring  lightMonitoring = new LightMonitoring();
		List<LightMonitoringVO>listOfVillages = new ArrayList<LightMonitoringVO>();	
		List<Object[]> lightMonitoringIds  =  lightMonitoringDAO.getVillagesDetails();
		if(lightMonitoringIds!=null && lightMonitoringIds.size()>0 && !lightMonitoringIds.isEmpty()){
			LightMonitoringVO lightMonitoringVO= new LightMonitoringVO();
			for (Object[] objects : lightMonitoringIds) {
			    lightMonitoringVO.setTotalPanels( lightMonitoring.getTotalPanels()+(Long)objects[0]);
				lightMonitoringVO.setTotalLights( lightMonitoring.getTotalLights()+(Long)objects[1]);
				lightMonitoringVO.setTotalPoles( lightMonitoring.getTotalPoles()+(Long)objects[2]);
				lightMonitoringVO.setWorkingLights( lightMonitoring.getWorkingLights()+(Long)objects[3]);
				lightMonitoringVO.setNotWorkingLights( lightMonitoring.getNotWorkingLights()+(Long)objects[4]);
				lightMonitoringVO.setOnLights( lightMonitoring.getOnLights()+(Long)objects[5]);
				lightMonitoringVO.setOffLights( lightMonitoring.getOffLights()+(Long)objects[6]);
				
				  List<Object[]> wattegeData = lightMonitoringDAO.getVillageBasedWattege();
				  LightMonitoringVO wattagVO= new LightMonitoringVO();
				  if(wattegeData!=null && wattegeData.size()>0 && !wattegeData.isEmpty()) 
					 	  for (Object[] objects2 : wattegeData) {
						  wattagVO.setWattage(wattagVO.getWattage()+(Long)objects2[0]);
                          wattagVO.setLightCount(wattagVO.getLightCount()+(Long)objects2[1]);
                         lightMonitoringVO.getList().add(wattagVO);
					  }
					  listOfVillages.add(lightMonitoringVO);		  
						  					
					}
				  }			
			return listOfVillages;
	       }*
	
	/*
	 * Date : 02/08/2017
	 * Author :Swapna
	 * @description : saveRealtimeStatusByVillages
	 */
	public ResultVO saveRealtimeStatusByVillages() {
		ResultVO status=new ResultVO();
		try {			
			ClientResponse response = webServiceUtilService.callWebService("http://54.254.103.213/PremiumDev/api/RestRealtimeAPI/GetRealtimeStatusByVillages","");
	        
	        if(response.getStatus() != 200)
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	        else{
	 	    	 String output = response.getEntity(String.class);
	 	    	  
	 	    	 if(output != null && !output.isEmpty())
	 	    	 {
	 	    		List<LightMonitoringVO> resultData = processLightData(output);
	 	    		 
	 	    		 if(resultData != null && resultData.size() > 0)
	 	    		 {
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
		 		 	    	            lightWattageDAO.save(wattage);	 		 		 	    		
		 		 		 	    	}
		 		 		 	    }	 		 	    				 	    				
		 		 	    	}
	 	    			 catch (Exception e) {
	 	    				 LOG.error(e);
	 	    			 }
	 	    			 
	 	    		}
	 	    		   status.setStatusCode(0);
	 		 		   status.setMessage("SUCCESS");
	 	    	  }
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
	    	 
	    		 JSONArray finalArray = new JSONArray(output);
	    		 
	    		 if(finalArray != null && finalArray.length() > 0)
 	    		 {
	    			 resultData = new ArrayList<LightMonitoringVO>(0);
	    			 
	    			 for(int i=0;i<finalArray.length();i++)
	    			 {
	    				 try{
	    					 
	    					 JSONObject jObj = (JSONObject) finalArray.get(i);
	    					 Long panchayatId = jObj.getLong("VillageId");
	    					 
	    					 if(panchayatId != null)
	    					 {
	    						 LightMonitoringVO lightMonitoringVO = new LightMonitoringVO();
	    						 lightMonitoringVO.setPanchayatId(panchayatId);
	    						 lightMonitoringVO.setTotalPanels(jObj.getLong("TotalPanels"));
	    						 lightMonitoringVO.setTotalPoles(jObj.getLong("TotalPoles"));
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
	    		 
	    	 }catch (Exception e) {
	    		 LOG.error(e);
	    	 }
	    	 return resultData;
	     }

		

	 	
		}

		 
	
