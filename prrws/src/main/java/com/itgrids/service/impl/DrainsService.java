package com.itgrids.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import com.itgrids.dto.DrainsVO;
import com.itgrids.dto.InputVO;
import com.itgrids.service.IDrainsService;
import com.itgrids.service.integration.external.WebServiceUtilService;

import com.sun.jersey.api.client.ClientResponse;

import jdk.nashorn.internal.scripts.JO;

@Transactional
public class DrainsService implements IDrainsService {
	

	private static final Logger LOG = Logger.getLogger( DrainsService.class);

	@Autowired
	private WebServiceUtilService webServiceUtilService;
	/*
	 * Date : 11/07/2017
	 * Author :Swapna
	 * @description : getPIRSSurveyInfo
	 */

	@Override
	public List<DrainsVO> getDrainsInfobyLocation(InputVO inputVO) {
		List<DrainsVO> drainsVOList= new ArrayList<DrainsVO>();
		List<DrainsVO> totalOverViewList=new ArrayList<DrainsVO>();
		List<DrainsVO> list=new ArrayList<DrainsVO>();
		
		try {
			    ClientResponse response = webServiceUtilService.callWebService("http://45.114.245.209/api/drains/?getDrainsInfobyLocation=true&locationId="+inputVO.getLocationId()+"&locationType="+inputVO.getLocationType()+"&fromDate="+inputVO.getFromDate()+"&toDate="+inputVO.getToDate(), inputVO);

	        	     if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				DrainsVO drainsVO=new DrainsVO(); 
	 	    				drainsVO.setId(jObj.getLong("id")); 
	 	    				drainsVO.setName(jObj.getString("name"));
	 	    				
	 	    				List<DrainsVO> volist = new ArrayList<DrainsVO>();
	 	    				
	 	    				drainsVO.setKacha(jObj.getLong("kacha"));
	 	    				drainsVO.setKachhaKM(jObj.getDouble("kachhaKM"));
	 	    				drainsVO.setKachaCleaned(jObj.getLong("kachaCleaned"));
	 	    				drainsVO.setKachaCleanedKM(jObj.getDouble("kachaCleanedKM"));
	 	    				drainsVO.setKachaPercentage(jObj.getDouble("kachaPercentage"));
	 	    				drainsVO.setPakka(jObj.getLong("pakka") );
	 	    				drainsVO.setPakkaKM(jObj.getDouble("pakkaKM"));
	 	    				drainsVO.setPakkaCleaned(jObj.getLong("pakkaCleaned"));
	 	    			    drainsVO.setPakkaCleanedKM(jObj.getDouble("pakkaCleanedKM"));
	 	    			    drainsVO.setUnderground(jObj.getLong("underground"));
	 	    			    drainsVO.setUndergroundCleaned(jObj.getLong("undergroundCleaned"));
	 	    			    drainsVO.setUndergroundCleanedKM(jObj.getDouble("undergroundKM"));
	 	    			    drainsVO.setUndergroundPercentage(jObj.getDouble("undergroundPercentage"));
	 	    			    
	 	    			    drainsVO.setCleaned(jObj.getLong("cleaned"));
	 	    			    drainsVO.setCleanedKM(jObj.getDouble("cleanedKM"));
	 	    			    drainsVO.setAvailability(jObj.getLong("availability"));
	 	    			    drainsVO.setAvailabilityKM(jObj.getDouble("availabilityKM"));
	 	    			    drainsVO.setPercentage(jObj.getDouble("percentage"));	
	 	    			    
	 	    			    
	 	    			    drainsVOList.add(drainsVO);		  
	 	    			    			        			    
	 	    			        			    
	 	    			}
	 	    		}
	 	    	}
	 	      }
		}     
	        	     
	        	
	 	      			
	 	    				
	 	  	catch (Exception e) {
		 	    				LOG.error("Exception raised at getDrainsInfobyLocation - DrainsService service", e);
		 	    				}	
		
	
		return drainsVOList;
	}


}
	





