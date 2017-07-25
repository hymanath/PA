package com.itgrids.service.integration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.NregaConsolidatedDataVO;
import com.itgrids.dto.NregaConsolidatedInputVO;
import com.itgrids.dto.NregsDataVO;
import com.itgrids.dto.NregsOverviewVO;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.itgrids.service.integration.impl.IRuralDevelopmentService;
import com.sun.jersey.api.client.ClientResponse;

@Service
@Transactional
public class RuralDevelopmentService implements IRuralDevelopmentService{
	
	private static final Logger LOG = Logger.getLogger(RuralDevelopmentService.class);
	
	@Autowired
	private WebServiceUtilService webServiceUtilService;
	
	/**
	 * Date : 25/07/2017
	 * Author :Nandhini
	 * @description : getNtrJalaSiriAbstract
	 * 
	 */
	public NregsDataVO getNtrJalaSiriAbstract(InputVO inputVO){
		NregsDataVO returnVO = new NregsDataVO();
		try{
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NtrsService/NtrsData", inputVO);
			
			if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				returnVO.setPercentage(jObj.getString("PERCENTAGE"));
	 	    				returnVO.setTarget(jObj.getLong("TARGET"));
	 	    				returnVO.setCompleted(jObj.getLong("COMPLETED"));
	 	    			}
	 	    		}
	 	    	}
	 	      }
		}catch(Exception e){
			LOG.error("Exception raised at getNtrJalaSiriAbstract - RuralDevelopmentService", e);
		}
		return  returnVO;
	}
	
	
	/**
	 * Date : 25/07/2017
	 * Author :Nandhiini
	 * @description : getNtrJalaSiriOverview
	 */
	public NregsOverviewVO getNtrJalaSiriOverview(InputVO inputVO){
		NregsOverviewVO finalVO = new NregsOverviewVO();
		try {
			
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NtrsService/NtrsOverview", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject Obj = new JSONObject(output);
	 	    		if(Obj!=null && Obj.length()>0){
 	    				finalVO.setAveragePerDistrict(Obj.getString("AVERAGEPERDISTRICT"));
 	    				finalVO.setAveragePerConstituency(Obj.getString("AVERAGEPERCONSTITUENCY"));
 	    				finalVO.setAveragePerMandal(Obj.getString("AVERAGEPERMANDAL"));
 	    				finalVO.setTotalBudget(Obj.getString("TOTALBUDGET"));
 	    				finalVO.setTotalAvgFarmsInDistrict(Obj.getString("TOTALAVGFARMSINDISTRICT"));
 	    				finalVO.setTotalAvgFarmsInConstituency(Obj.getString("TOTALAVGFARMSINCONSTITUENCY"));
 	    				finalVO.setTotalAvgFarmsInMandal(Obj.getString("TOTALAVGFARMSINMANDAL"));
 	    				finalVO.setDistrictsInRed(Obj.getLong("DISTRICTSINRED"));
 	    				finalVO.setDistrictsInOrange(Obj.getLong("DISTRICTSINORANGE"));
 	    				finalVO.setDistrictsInGreen(Obj.getLong("DISTRICTSINGREEN"));
 	    				finalVO.setTotalDistricts(Obj.getLong("TOTALDISTRICTS"));
 	    				finalVO.setConstituenciesInRed(Obj.getLong("CONSTITUENCIESINRED"));
 	    				finalVO.setConstituenciesInOrange(Obj.getLong("CONSTITUENCIESINORANGE"));
 	    				finalVO.setConstituenciesInGreen(Obj.getLong("CONSTITUENCIESINGREEN"));
 	    				finalVO.setTotalConstituencies(Obj.getLong("TOTALCONSTITUENCIES"));
 	    				finalVO.setMandalsInRed(Obj.getLong("MANDALSINRED"));
 	    				finalVO.setMandalsInOrange(Obj.getLong("MANDALSINORANGE"));
 	    				finalVO.setMandalsInGreen(Obj.getLong("MANDALSINGREEN"));
 	    				finalVO.setTotalMandals(Obj.getLong("TOTALMANDALS"));
	 	    		}
	 	    				
	 	    	}
	 	   } 
		} catch (Exception e) {
			LOG.error("Exception raised at getNtrJalaSiriOverview - RuralDevelopmentService", e);
		}
		
		return finalVO;
	}
	
	/**
	 * Date : 25/07/2017
	 * Author :Nandhini
	 * @description : getNtrJalaSiriLvlWiseData
	 * 
	 */
	public List<NregsDataVO> getNtrJalaSiriLvlWiseData(InputVO inputVO){
		List<NregsDataVO> returnList = new ArrayList<NregsDataVO>(0);
		try{
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NtrsService/NtrsData", inputVO);
			
			if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				NregsDataVO vo = new NregsDataVO();
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				vo.setUniqueId(jObj.getLong("UNIQUEID"));
	 	    				vo.setDistrict(jObj.getString("DISTRICT"));
	 	    				vo.setConstituency(jObj.getString("CONSTITUENCY"));
	 	    				vo.setMandal(jObj.getString("MANDAL"));
	 	    				vo.setPanchayat(jObj.getString("PANCHAYAT"));
	 	    				vo.setTarget(jObj.getLong("TARGET"));
	 	    				vo.setGrounded(jObj.getString("GROUNDED"));
	 	    				vo.setNotGrounded(jObj.getString("NOTGROUNDED"));
	 	    				vo.setInProgress(jObj.getLong("INPROGRESS"));
	 	    				vo.setCompleted(jObj.getLong("COMPLETED"));
	 	    				vo.setPercentage(jObj.getString("PERCENTAGE"));
	 	    				returnList.add(vo);
	 	    			
	 	    			}
	 	    		}
	 	    	}
	 	      }
		}catch(Exception e){
			LOG.error("Exception raised at getNtrJalaSiriAbstract - RuralDevelopmentService", e);
		}
		return  returnList;
	}

}
