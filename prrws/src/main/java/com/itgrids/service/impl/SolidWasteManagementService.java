package com.itgrids.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dto.DrainsVO;
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
				webResource = commonMethodsUtilService.getWebResourceObject("https://pris.ap.gov.in/survey/api/swmapi.php?getSwmInfo=1");
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
	        					solidWasteManagementVO.setSwmCollection(!jObj.getString("swmCollection").equalsIgnoreCase("null") ? jObj.getLong("swmCollection") : 0l);
	        					solidWasteManagementVO.setNadap(!jObj.getString("nadap").equalsIgnoreCase("null") ? jObj.getLong("nadap") : 0l);
	        					solidWasteManagementVO.setVermi(!jObj.getString("vermi").equalsIgnoreCase("null") ? jObj.getLong("vermi") : 0l);
	        					solidWasteManagementVO.setVermiStock(!jObj.getString("vermiStock").equalsIgnoreCase("null") ? jObj.getLong("vermiStock") : 0l);
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
	
}