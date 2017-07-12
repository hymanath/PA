package com.itgrids.service.impl;


import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dto.DrainsVO;
import com.itgrids.dto.InputVO;
import com.itgrids.service.IDrainsService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Service
@Transactional
public class DrainsService implements IDrainsService {
	

	private static final Logger LOG = Logger.getLogger( DrainsService.class);

	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	
	/*@Autowired
	private WebServiceUtilService webServiceUtilService;*/
	
	/*
	 * Date : 11/07/2017
	 * Author :Swapna
	 * @description : getPIRSSurveyInfo
	 */

	@Override
	public DrainsVO getDrainsInfobyLocation(InputVO inputVO) {
		DrainsVO drainsVO= new DrainsVO();
		
		try {
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://45.114.245.209/api/drains/?getDrainsInfobyLocation=true&locationId="+inputVO.getLocationId()+"&locationType="+inputVO.getLocationType()+"&fromDate="+inputVO.getFromDate()+"&toDate="+inputVO.getToDate());
	        
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
	 	    				drainsVO.setKachaAvailable(!jObj.getString("kacha").equalsIgnoreCase("null") ? drainsVO.getKachaAvailable()+jObj.getLong("kacha") : drainsVO.getKachaAvailable());
	 	    				drainsVO.setKachaCleaned(!jObj.getString("kachaCleaned").equalsIgnoreCase("null") ? drainsVO.getKachaCleaned()+jObj.getLong("kachaCleaned") : drainsVO.getKachaCleaned());
	 	    				drainsVO.setKachaAvailableKms(!jObj.getString("kachhaKM").equalsIgnoreCase("null") ? round(drainsVO.getKachaAvailableKms()+jObj.getDouble("kachhaKM"),2) : drainsVO.getKachaAvailableKms());
	 	    				drainsVO.setKachaCleanedKM(!jObj.getString("kachaCleanedKM").equalsIgnoreCase("null") ? round(drainsVO.getKachaCleanedKM()+jObj.getDouble("kachaCleanedKM"),2) : drainsVO.getKachaCleanedKM());
	 	    			    //drainsVO.setKachaPercentage(!jObj.getString("kachaPercentage").equalsIgnoreCase("null") ? round(drainsVO.getKachaPercentage()+jObj.getDouble("kachaPercentage"),2) : drainsVO.getKachaPercentage());
	 	    			    
	 	    			    drainsVO.setPakkaAvailable(!jObj.getString("pakka").equalsIgnoreCase("null") ? drainsVO.getPakkaAvailable()+jObj.getLong("pakka") : drainsVO.getPakkaAvailable());
	 	    				drainsVO.setPakkaCleaned(!jObj.getString("pakkaCleaned").equalsIgnoreCase("null") ? drainsVO.getPakkaCleaned()+jObj.getLong("pakkaCleaned") : drainsVO.getPakkaCleaned());
	 	    				drainsVO.setPakkaAvailableKms(!jObj.getString("pakkaKM").equalsIgnoreCase("null") ? round(drainsVO.getPakkaAvailableKms()+jObj.getDouble("pakkaKM"),2) : drainsVO.getPakkaAvailableKms());
	 	    				drainsVO.setPakkaCleanedKM(!jObj.getString("pakkaCleanedKM").equalsIgnoreCase("null") ? round(drainsVO.getPakkaCleanedKM()+jObj.getDouble("pakkaCleanedKM"),2) : drainsVO.getPakkaCleanedKM());
	 	    			    //drainsVO.setPakkaPercentage(!jObj.getString("pakkaPercentage").equalsIgnoreCase("null") ? round(drainsVO.getPakkaPercentage()+jObj.getDouble("pakkaPercentage"),2) : drainsVO.getPakkaPercentage());
	 	    			    
	 	    			    drainsVO.setUgAvailable(!jObj.getString("underground").equalsIgnoreCase("null") ? drainsVO.getUgAvailable()+jObj.getLong("underground") : drainsVO.getUgAvailable());
	 	    				drainsVO.setUgCleaned(!jObj.getString("undergroundCleaned").equalsIgnoreCase("null") ? drainsVO.getUgCleaned()+jObj.getLong("undergroundCleaned") : drainsVO.getUgCleaned());
	 	    				drainsVO.setUgAvailableKms(!jObj.getString("undergroundKM").equalsIgnoreCase("null") ? round(drainsVO.getUgAvailableKms()+jObj.getDouble("undergroundKM"),2) : drainsVO.getUgAvailableKms());
	 	    				drainsVO.setUgCleanedKms(!jObj.getString("undergroundCleanedKM").equalsIgnoreCase("null") ? round(drainsVO.getUgCleanedKms()+jObj.getDouble("undergroundCleanedKM"),2) : drainsVO.getUgCleanedKms());
	 	    			    //drainsVO.setUgPercentage(!jObj.getString("undergroundPercentage").equalsIgnoreCase("null") ? round(drainsVO.getUgPercentage()+jObj.getDouble("undergroundPercentage"),2) : drainsVO.getUgPercentage());
	 	    			    
	 	    			    drainsVO.setTotalAvailable(!jObj.getString("availability").equalsIgnoreCase("null") ? drainsVO.getTotalAvailable()+jObj.getLong("availability") : drainsVO.getTotalAvailable());
	 		 	    		drainsVO.setTotalCleaned(!jObj.getString("cleaned").equalsIgnoreCase("null") ? drainsVO.getTotalCleaned()+jObj.getLong("cleaned") : drainsVO.getTotalCleaned());
	 		 	    		drainsVO.setTotalAvailableKms(!jObj.getString("availabilityKM").equalsIgnoreCase("null") ? round(drainsVO.getTotalAvailableKms()+jObj.getDouble("availabilityKM"),2) : drainsVO.getTotalAvailableKms());
	 		 	    		drainsVO.setTotalCleanedKms(!jObj.getString("cleanedKM").equalsIgnoreCase("null") ? round(drainsVO.getTotalCleanedKms()+jObj.getDouble("cleanedKM"),2) : drainsVO.getTotalCleanedKms());
	 		 	    		//drainsVO.setPercentage(!jObj.getString("percentage").equalsIgnoreCase("null") ? round(drainsVO.getPercentage()+jObj.getDouble("percentage"),2) : drainsVO.getPercentage());
	 	    			}
	 	    		}
	 	    		
	 	    		drainsVO.setKachaPercentage(drainsVO.getKachaAvailable() > 0 ? round(((drainsVO.getKachaCleaned()*100.00)/drainsVO.getKachaAvailable()),2):0.00);
	 	    		drainsVO.setPakkaPercentage(drainsVO.getPakkaAvailable() > 0 ? round(((drainsVO.getPakkaCleaned()*100.00)/drainsVO.getPakkaAvailable()),2):0.00);
	 	    		drainsVO.setUgPercentage(drainsVO.getUgAvailable() > 0 ? round(((drainsVO.getUgCleaned()*100.00)/drainsVO.getUgAvailable()),2):0.00);
	 	    		drainsVO.setPercentage(drainsVO.getTotalAvailable() > 0 ? round(((drainsVO.getTotalCleaned()*100.00)/drainsVO.getTotalAvailable()),2):0.00);
	 	    		
	 	    	 }
	 	      }
		}     		
	 	    				
	 	catch (Exception e) {
			LOG.error("Exception raised at getDrainsInfobyLocation - DrainsService service", e);
		}	
		return drainsVO;
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
	





