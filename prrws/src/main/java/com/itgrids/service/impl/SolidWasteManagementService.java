package com.itgrids.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.SolidWasteManagementVO;
import com.itgrids.service.ISolidWasteManagementService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


@Service
@Transactional
public class SolidWasteManagementService implements ISolidWasteManagementService {
	

	private static final Logger LOG = Logger.getLogger( SolidWasteManagementService.class);

	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	
	/*@Autowired
	private WebServiceUtilService webServiceUtilService;*/

	/*
	 * Date : 7/11/2017
	 * Author :Swapna
	*/
	@Override
	public List<SolidWasteManagementVO> getSolidInfoLocationWise(InputVO inputVO) {
		List<SolidWasteManagementVO> finalList = new ArrayList<SolidWasteManagementVO>(0);	
		try {
			WebResource webResource = null;
				
				if(inputVO.getSubFilterType() != null && !inputVO.getSubFilterType().trim().isEmpty())
					webResource = commonMethodsUtilService.getWebResourceObject("https://pris.ap.gov.in/survey/api/swmapi.php?getSwmInfo=true&locationId="+inputVO.getLocationId()+"&locationType="+inputVO.getLocationType()+"&filterType="+inputVO.getFilterType()+"&filterId="+inputVO.getFilterId()+"&subFilterType="+inputVO.getSubFilterType()+"&subFilterId="+inputVO.getSubFilterId()+"&fromDate="+inputVO.getFromDate()+"&toDate="+inputVO.getToDate());
				else if(inputVO.getFilterType() != null && !inputVO.getFilterType().trim().isEmpty())
					webResource = commonMethodsUtilService.getWebResourceObject("https://pris.ap.gov.in/survey/api/swmapi.php?getSwmInfo=true&locationId="+inputVO.getLocationId()+"&locationType="+inputVO.getLocationType()+"&filterType="+inputVO.getFilterType()+"&filterId="+inputVO.getFilterId()+"&fromDate="+inputVO.getFromDate()+"&toDate="+inputVO.getToDate());
				else	
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
	        					SolidWasteManagementVO solidWasteManagementVO=new SolidWasteManagementVO();
	        					solidWasteManagementVO.setId(!jObj.getString("id").equalsIgnoreCase("null") ? jObj.getString("id"):"");
	        					solidWasteManagementVO.setName(!jObj.getString("name").equalsIgnoreCase("null") ? jObj.getString("name") : "");	        					
	        					solidWasteManagementVO.setRfidTags(!jObj.getString("rfid_tags").equalsIgnoreCase("null") ? jObj.getLong("rfid_tags") : 0l);
	        					solidWasteManagementVO.setFarmers(!jObj.getString("farmers").equalsIgnoreCase("null") ? jObj.getLong("farmers") : 0l);
	        					solidWasteManagementVO.setRfidTracking(!jObj.getString("rfidTracking").equalsIgnoreCase("null") ? jObj.getLong("rfidTracking") : 0l);
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
	        			}
	        			}
	        			}
	        			}
	            		} catch (Exception e) {
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
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("https://pris.ap.gov.in/survey/api/swmapi.php?getSwmInfo=true");
	        
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
		}     		
	 	    				
	 	catch (Exception e) {
			LOG.error("Exception raised at getDrainsInfobyLocation - DrainsService service", e);
		}	
		return solidWasteManagementVO;
	}
	
	
}