package com.itgrids.service.integration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IWebserviceCallDetailsDAO;
import com.itgrids.dto.FarmPondOverviewVO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LabourBudgetOverViewVO;
import com.itgrids.dto.NregsDataVO;
import com.itgrids.dto.NregsOverviewVO;
import com.itgrids.dto.NregsProjectsVO;
import com.itgrids.dto.WebserviceDetailsVO;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.itgrids.service.integration.impl.INREGSTCSService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.sun.jersey.api.client.ClientResponse;

@Service
@Transactional
public class NREGSTCSService implements INREGSTCSService{

	private static final Logger LOG = Logger.getLogger(NREGSTCSService.class);
	
	@Autowired
	private WebServiceUtilService webServiceUtilService;
	@Autowired
	private IWebserviceCallDetailsDAO webserviceCallDetailsDAO;
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	/*
	 * Date : 16/06/2017
	 * Author :Sravanth
	 * @description : getNREGSProjectsOverview
	 */
	public List<NregsProjectsVO> getNREGSProjectsOverview(InputVO inputVO){
		List<NregsProjectsVO> voList = new ArrayList<NregsProjectsVO>(0);
		try {
			 
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CMDashBoard/Abstract", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 /*output = "[{\"PARAMETER\":\"Labour Budget\",\"TARGET\":\"135033896\",\"COMPLETED\":\"110050490\",\"PERCENTAGE\":\"81.5\"},"
	 	    	 		+ "{\"PARAMETER\":\"Farm Pond\",\"TARGET\":\"38291\",\"COMPLETED\":\"28710\",\"PERCENTAGE\":\"74.98\"},"
	 	    	 		+ "{\"PARAMETER\":\"IHHL\",\"TARGET\":\"26709\",\"COMPLETED\":\"23310\",\"PERCENTAGE\":\"87.27\"},"
	 	    	 		+ "{\"PARAMETER\":\"VERMI\",\"TARGET\":\"18755\",\"COMPLETED\":\"9510\",\"PERCENTAGE\":\"50.71\"}]";*/
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				NregsProjectsVO vo = new NregsProjectsVO();
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				vo.setParameter(jObj.getString("PARAMETER"));
	 	    				vo.setTarget(jObj.getString("TARGET"));
	 	    				vo.setCompleted(jObj.getString("COMPLETED"));
	 	    				vo.setPercentage(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				
	 	    				voList.add(vo);
	 	    			}
	 	    		}
	 	    	}
	 	    	 
	 	    	  
	 	      }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSProjectsOverview - NREGSTCSService service", e);
		}
		
		return voList;
	}
	
	/*
	 * Date : 16/06/2017
	 * Author :Sravanth
	 * @description : getLabourBudgetOverview
	 */
	public LabourBudgetOverViewVO getLabourBudgetOverview(InputVO inputVO){
		LabourBudgetOverViewVO returnvo = new LabourBudgetOverViewVO();
		try {
			 
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CMDashBoard/LabourBudgetOverview", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 /*output = "{\"TOTALBUDGET\": \"443361.29\",\"TOTALLABOUR\": \"4980421\",\"MALELABOUR\": \"2225710\",\"FEMALELABOUR\": \"2754711\","
	 	    	 		+ "\"TARGETTEDPERSONDAYS\": \"135033896\",\"GENERATEDPERSONDAYS\": \"109104373\",\"TOTALEXPENDITURE\": \"185762.22\","
	 	    	 		+ "\"AVGWAGEPERPERSON\": \"138.14\",\"TOTALRESPONSE\": \"43822300\",\"ONREQUESTWORKALLOCATED\": \"43822300\","
	 	    	 		+ "\"GOTWORKOCCUTIONALLY\": \"43822300\",\"HAVENOTGOTWORK\": \"0\",\"BUDGETFORLABOUR\": \"266016.78\","
	 	    	 		+ "\"BUDGETFORMATERIAL\": \"177344.52\",\"TOTALDISTRICTS\": \"13\",\"DISTRICTSINRED\": \"0\",\"DISTRICTSINORANGE\": \"6\","
	 	    	 		+ "\"DISTRICTSINGREEN\": \"7\",\"TOTALCONSTITUENCIES\": \"157\",\"CONSTITUENCIESINRED\": \"4\",\"CONSTITUENCIESINORANGE\": \"73\","
	 	    	 		+ "\"CONSTITUENCIESINGREEN\": \"80\",\"TOTALMANDALS\": \"661\",\"MANDALSINRED\": \"68\",\"MANDALSINORANGE\": \"253\","
	 	    	 		+ "\"MANDALSINGREEN\": \"340\",\"TOTALVILLAGES\": \"13089\",\"VILLAGESINRED\": \"3406\",\"VILLAGESINORANGE\": \"3499\","
	 	    	 		+ "\"VILLAGESINGREEN\": \"6184\",\"LEADER\": \"Gopalapuram\",\"LAGGER\": \"Venkatapuram\"}";*/
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject jObj = new JSONObject(output);
	 	    		//LabourBudgetOverViewVO vo = new LabourBudgetOverViewVO();
	 	    		
	 	    		returnvo.setTotalBudget(new BigDecimal(jObj.getString("TOTALBUDGET")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    		returnvo.setTotalLabour(jObj.getLong("TOTALLABOUR"));
	 	    		returnvo.setMaleLabour(jObj.getLong("MALELABOUR"));
	 	    		returnvo.setFemaleLabour(jObj.getLong("FEMALELABOUR"));
	 	    		returnvo.setTargettedPersonDays(jObj.getLong("TARGETTEDPERSONDAYS"));
	 	    		returnvo.setGeneratedPersonDays(jObj.getLong("GENERATEDPERSONDAYS"));
	 	    		returnvo.setTotalExpenditure(new BigDecimal(jObj.getString("TOTALEXPENDITURE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    		returnvo.setAvgWagePerPerson(new BigDecimal(jObj.getString("AVGWAGEPERPERSON")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    		returnvo.setTotalResponse(jObj.getLong("TOTALRESPONSE"));
	 	    		returnvo.setOnRequestWorkAllocated(jObj.getLong("ONREQUESTWORKALLOCATED"));
	 	    		returnvo.setGotWorkOccutionally(jObj.getLong("GOTWORKOCCUTIONALLY"));
    				returnvo.setHaveNotGotWork(jObj.getLong("HAVENOTGOTWORK"));
    				returnvo.setBudgetForLabour(new BigDecimal(jObj.getString("BUDGETFORLABOUR")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
    				returnvo.setBudgetForMaterial(new BigDecimal(jObj.getString("BUDGETFORMATERIAL")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
    				returnvo.setTotalDistricts(jObj.getLong("TOTALDISTRICTS"));
    				returnvo.setDistrictsInRed(jObj.getLong("DISTRICTSINRED"));
    				returnvo.setDistrictsInOrange(jObj.getLong("DISTRICTSINORANGE"));
    				returnvo.setDistrictsInGreen(jObj.getLong("DISTRICTSINGREEN"));
    				returnvo.setTotalConstituencies(jObj.getLong("TOTALCONSTITUENCIES"));
    				returnvo.setConstituenciesInRed(jObj.getLong("CONSTITUENCIESINRED"));
    				returnvo.setConstituenciesInOrange(jObj.getLong("CONSTITUENCIESINORANGE"));
    				returnvo.setConstituenciesInGreen(jObj.getLong("CONSTITUENCIESINGREEN"));
    				returnvo.setTotalMandals(jObj.getLong("TOTALMANDALS"));
    				returnvo.setMandalsInRed(jObj.getLong("MANDALSINRED"));
    				returnvo.setMandalsInOrange(jObj.getLong("MANDALSINORANGE"));
    				returnvo.setMandalsInGreen(jObj.getLong("MANDALSINGREEN"));
    				returnvo.setTotalVillages(jObj.getLong("TOTALVILLAGES"));
    				returnvo.setVillagesInRed(jObj.getLong("VILLAGESINRED"));
    				returnvo.setVillagesInOrange(jObj.getLong("VILLAGESINORANGE"));
    				returnvo.setVillagesInGreen(jObj.getLong("VILLAGESINGREEN"));
    				returnvo.setLeader(jObj.getString("LEADER"));
    				returnvo.setLagger(jObj.getString("LAGGER"));
    			}
	 	   }
	    } catch (Exception e) {
			LOG.error("Exception raised at getLabourBudgetOverview - NREGSTCSService service", e);
		}
		
		return returnvo;
	}
	
	/*
	 * Date : 16/06/2017
	 * Author :Sravanth
	 * @description : getLabourBudgetExpenditure
	 */
	public List<IdNameVO> getLabourBudgetExpenditure(InputVO inputVO){
		List<IdNameVO> voList = new ArrayList<IdNameVO>(0);
		try {
			 
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CMDashBoard/LabourBudgetExpenditure", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 /*output = "[{\"RANGE\": \"0\",\"GPSCOUNT\": \"236\"},{\"RANGE\": \"Below 1\",\"GPSCOUNT\": \"690\"},{\"RANGE\": \"1-5\",\"GPSCOUNT\": \"2975\"},"
	 	    	 		+ "{\"RANGE\": \"5-10\",\"GPSCOUNT\": \"2978\"},{\"RANGE\": \"10-20\",\"GPSCOUNT\": \"3986\"},{\"RANGE\": \"20-30\",\"GPSCOUNT\": \"1694\"},"
	 	    	 		+ "{\"RANGE\": \"30-50\",\"GPSCOUNT\": \"901\"},{\"RANGE\": \"50-100\",\"GPSCOUNT\": \"286\"},{\"RANGE\": \"100-200\",\"GPSCOUNT\": \"21\"},"
	 	    	 		+ "{\"RANGE\": \"200-300\",\"GPSCOUNT\": \"2\"},{\"RANGE\": \"300-400\",\"GPSCOUNT\": \"0\"},{\"RANGE\": \"Above 400\",\"GPSCOUNT\": \"0\"}]";*/
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				IdNameVO vo = new IdNameVO();
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				vo.setName(jObj.getString("RANGE"));
	 	    				vo.setCount(jObj.getLong("GPSCOUNT"));
	 	    				
	 	    				voList.add(vo);
	 	    			}
	 	    		}
	 	    	}
	 	    	 
	 	    	  
	 	      }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getLabourBudgetExpenditure - NREGSTCSService service", e);
		}
		
		return voList;
	}
	
	/*
	 * Date : 16/06/2017
	 * Author :Swapna
	 * @description : getFarmPondOverview
	 */
	
   public FarmPondOverviewVO getFarmPondOverview(InputVO inputVO){
	FarmPondOverviewVO farmpondoverviewvO = new FarmPondOverviewVO();
	try {
		 
		ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FarmPondService/FarmPondOverview", inputVO);
        
        if(response.getStatus() != 200){
 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      }else{
 	    	 String output = response.getEntity(String.class); 	    	 
 	    	if(output != null && !output.isEmpty()){
 	    		JSONObject jObj = new JSONObject(output); 	    		
 	    				  				
 	    				farmpondoverviewvO.setAveragePerDistrict(new BigDecimal(jObj.getString("AVERAGEPERDISTRICT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
 	    				farmpondoverviewvO.setAveragePerConstituency(new BigDecimal(jObj.getString("AVERAGEPERCONSTITUENCY")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
 	    				farmpondoverviewvO.setAveragePerMandal(new BigDecimal(jObj.getString("AVERAGEPERMANDAL")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
 	    				farmpondoverviewvO.setConstituenciesInGreen(jObj.getLong("CONSTITUENCIESINGREEN"));
 	    				farmpondoverviewvO.setConstituenciesInOrange(jObj.getLong("CONSTITUENCIESINORANGE"));
 	    				farmpondoverviewvO.setConstituenciesInRed(jObj.getLong("CONSTITUENCIESINRED"));
 	    				farmpondoverviewvO.setDistrictsInGreen(jObj.getLong("DISTRICTSINGREEN"));
 	    				farmpondoverviewvO.setDistrictsInOrange(jObj.getLong("DISTRICTSINORANGE"));
 	    				farmpondoverviewvO.setDistrictsInRed(jObj.getLong("DISTRICTSINRED"));
 	    				farmpondoverviewvO.setMandalsInGreen(jObj.getLong("MANDALSINGREEN"));
 	    				farmpondoverviewvO.setMandalsInOrange(jObj.getLong("MANDALSINORANGE"));
 	    				farmpondoverviewvO.setMandalsInRed(jObj.getLong("MANDALSINRED"));
 	    				farmpondoverviewvO.setTotalAvgFarmsInConstituency(new BigDecimal(jObj.getString("TOTALAVGFARMSINCONSTITUENCY")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
 	    				farmpondoverviewvO.setTotalAvgFarmsInDistrict(new BigDecimal(jObj.getString("TOTALAVGFARMSINDISTRICT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
 	    				farmpondoverviewvO.setTotalBudget(jObj.getLong("TOTALBUDGET"));
 	    				farmpondoverviewvO.setTotalDistricts(jObj.getLong("TOTALDISTRICTS"));
 	    				farmpondoverviewvO.setTotalMandals(jObj.getLong("TOTALMANDALS"));
 	    				farmpondoverviewvO.setTotalVillages(jObj.getLong("TOTALVILLAGES") );
 	    				farmpondoverviewvO.setVillagesInGreen(jObj.getLong("VILLAGESINGREEN"));
 	    				farmpondoverviewvO.setVillagesInOrange(jObj.getLong("VILLAGESINORANGE"));
 	    				farmpondoverviewvO.setVillagesInRed(jObj.getLong("VILLAGESINRED"));
 	    				farmpondoverviewvO.setTotalAvgFarmsInMandal(new BigDecimal(jObj.getString("TOTALAVGFARMSINMANDAL")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
 	    				farmpondoverviewvO.setTotalConstituencies(jObj.getLong("TOTALCONSTITUENCIES"));
 	    				
 	    			}
 	    		} 	    	
 	      
	             }
 	             catch (Exception e) {
			LOG.error("Exception raised at getFarmPondOverview -FarmPondOverview service", e);
		}
		
		return  farmpondoverviewvO;
	}
   
   /*
	 * Date : 16/06/2017
	 * Author :Swapna
	 * @description : getFarmPondData
	 */
      public List<NregsDataVO> getFarmPondData(InputVO inputVO){
		List<NregsDataVO> list = new ArrayList<NregsDataVO>(0);
		try {
			 
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FarmPondService/FarmPondData", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				NregsDataVO nregsDataVO=new NregsDataVO();
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				nregsDataVO.setUniqueId(jObj.getLong("UNIQUEID"));
	 	    				nregsDataVO.setDistrict(jObj.getString("DISTRICT"));
	 	    				nregsDataVO.setConstituency(jObj.getString("CONSTITUENCY"));
	 	    				nregsDataVO.setMandal(jObj.getString("MANDAL"));
	 	    				nregsDataVO.setPanchayat(jObj.getString("PANCHAYAT"));
	 	    				nregsDataVO.setTarget(jObj.getLong("TARGET"));
	 	    				nregsDataVO.setGrounded(jObj.getString("GROUNDED"));
	 	    				nregsDataVO.setNotGrounded(jObj.getString("NOTGROUNDED"));
	 	    				nregsDataVO.setInProgress(jObj.getLong("INPROGRESS"));
	 	    				nregsDataVO.setCompleted(jObj.getLong("COMPLETED"));
	 	    				nregsDataVO.setPercentage(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());	 	    				
	 	    				list.add(nregsDataVO);	 
	 	    				//getDistrictsConstitByType(list,inputVO.getType());
	 	    			}
	 	    		}
	 	    	}
	 	    	 
	 	    	  
	 	      }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getFarmPondData - getFarmPondData service", e);
		}
		
		return list;
	}
	/*
	 * Date : 16/06/2017
	 * Author :Nandhini
	 * @description : getIHHLOverview
	 */
	public NregsOverviewVO getNregaIHHLOverview(InputVO inputVO){
		NregsOverviewVO finalVO = new NregsOverviewVO();
		try {
			 
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/IHHLService/IHHLOverview", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject Obj = new JSONObject(output);
	 	    		if(Obj!=null && Obj.length()>0){
	 	    				finalVO.setAveragePerDistrict(new BigDecimal(Obj.getString("AVERAGEPERDISTRICT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				finalVO.setAveragePerConstituency(new BigDecimal(Obj.getString("AVERAGEPERCONSTITUENCY")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				finalVO.setAveragePerMandal(new BigDecimal(Obj.getString("AVERAGEPERMANDAL")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				finalVO.setTotalBudget(Obj.getLong("TOTALBUDGET"));
	 	    				finalVO.setTotalAvgFarmsInDistrict(new BigDecimal(Obj.getString("TOTALAVGFARMSINDISTRICT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				finalVO.setTotalAvgFarmsInConstituency(new BigDecimal(Obj.getString("TOTALAVGFARMSINCONSTITUENCY")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				finalVO.setTotalAvgFarmsInMandal(new BigDecimal(Obj.getString("TOTALAVGFARMSINMANDAL")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
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
	 	    				finalVO.setVillagesInRed(Obj.getLong("VILLAGESINRED"));
	 	    				finalVO.setVillagesInOrange(Obj.getLong("VILLAGESINORANGE"));
	 	    				finalVO.setVillagesInGreen(Obj.getLong("VILLAGESINGREEN"));
	 	    				finalVO.setTotalVillages(Obj.getLong("TOTALVILLAGES"));
	 	    			}
	 	    		}
	 	    	}
	 	    	 
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSProjectsOverview - NREGSTCSService service", e);
		}
		
		return finalVO;
	}
	
	/*
	 * Date : 16/06/2017
	 * Author :Nandhini
	 * @description : getNREGSIHHLLvelSOverview
	 */
	public List<NregsDataVO> getNregaLevelsOverviewForIHHL(InputVO inputVO){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
			 
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/IHHLService/IHHLData", inputVO);
	        
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
	 	    				vo.setPercentage(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				
	 	    				voList.add(vo);
	 	    				//getDistrictsConstitByType(voList,inputVO.getType());
	 	    			}
	 	    		}
	 	    	}
	 	      }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsOverviewForIHHL - NREGSTCSService service", e);
		}
		
		return voList;
	}
	
	/*
	 * Date : 16/06/2017
	 * Author :Sravanth
	 * @description : getNregaLevelwiseOverviewForLabourBudgetData
	 */
	public List<NregsDataVO> getNregaLevelwiseOverviewForLabourBudgetData(InputVO inputVO){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
			 
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CMDashBoard/LabourBudgetData", inputVO);
	        
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
	 	    				vo.setTargetPersonDays(jObj.getLong("TARGETPERSONDAYS"));
	 	    				vo.setGeneratedPersonDays(jObj.getLong("GENERATEDPERSONDAYS"));
	 	    				vo.setPerAppLB(new BigDecimal(jObj.getString("PER_APP_LB")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				vo.setAvgWageRate(new BigDecimal(jObj.getString("AVGWAGERATE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				vo.setTotalExpenditure(new BigDecimal(jObj.getString("TOTALEXPENDITURE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				vo.setPercentage(new BigDecimal(jObj.getString("PER_APP_LB")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				voList.add(vo);
	 	    				//getDistrictsConstitByType(voList,inputVO.getType());
	 	    			}
	 	    		}
	 	    	}
	 	      }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelwiseOverviewForLabourBudgetData - NREGSTCSService service", e);
		}
		
		return voList;
	}

	/*
	 * Date : 16/06/2017
	 * Author :Teja
	 * @description :getNregsVermiOverview(Nregs Vermi details)
	 */
	public NregsOverviewVO getNregsVermiOverview(InputVO inputVO){
		NregsOverviewVO returnVo = new NregsOverviewVO();
		try {
			 
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/VermiService/VermiOverview", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String result = response.getEntity(String.class);
	 	    	 
	 	    	if(result != null && !result.isEmpty()){
	 	    		JSONObject jObj = new JSONObject(result);
	 	    		NregsOverviewVO vo = new NregsOverviewVO();
	 	                 
	 	                returnVo.setAveragePerDistrict(new BigDecimal(jObj.getString("AVERAGEPERDISTRICT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	                returnVo.setAveragePerConstituency(new BigDecimal(jObj.getString("AVERAGEPERCONSTITUENCY")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	                returnVo.setAveragePerMandal(new BigDecimal(jObj.getString("AVERAGEPERMANDAL")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	                returnVo.setTotalBudget(jObj.getLong("TOTALBUDGET"));
	 	                returnVo.setTotalAvgFarmsInDistrict(new BigDecimal(jObj.getString("TOTALAVGFARMSINDISTRICT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	                returnVo.setTotalAvgFarmsInConstituency(new BigDecimal(jObj.getString("TOTALAVGFARMSINCONSTITUENCY")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	                returnVo.setTotalAvgFarmsInMandal(new BigDecimal(jObj.getString("TOTALAVGFARMSINMANDAL")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	                returnVo.setDistrictsInRed(jObj.getLong("DISTRICTSINRED"));
	 	                returnVo.setDistrictsInOrange(jObj.getLong("DISTRICTSINORANGE"));
	 	                returnVo.setDistrictsInGreen(jObj.getLong("DISTRICTSINGREEN"));
	 	                returnVo.setTotalDistricts(jObj.getLong("TOTALDISTRICTS"));
	 	                returnVo.setConstituenciesInRed(jObj.getLong("CONSTITUENCIESINRED"));
	 	                returnVo.setConstituenciesInOrange(jObj.getLong("CONSTITUENCIESINORANGE"));
	 	                returnVo.setConstituenciesInGreen(jObj.getLong("CONSTITUENCIESINGREEN"));
	 	                returnVo.setTotalConstituencies(jObj.getLong("TOTALCONSTITUENCIES"));
	 	                returnVo.setMandalsInRed(jObj.getLong("MANDALSINRED"));
	 	                returnVo.setMandalsInOrange(jObj.getLong("MANDALSINORANGE"));
	 	                returnVo.setMandalsInGreen(jObj.getLong("MANDALSINGREEN"));
	 	                returnVo.setTotalMandals(jObj.getLong("TOTALMANDALS"));
	 	                returnVo.setVillagesInRed(jObj.getLong("VILLAGESINRED"));
	 	                returnVo.setVillagesInOrange(jObj.getLong("VILLAGESINORANGE"));
	 	                returnVo.setVillagesInGreen(jObj.getLong("VILLAGESINGREEN"));
	 	                returnVo.setTotalVillages(jObj.getLong("TOTALVILLAGES"));
	 	               }
	 	            }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getNregsVermiOverview - NREGSTCSService service", e);
		}
		return returnVo;
	}
	/*
	 * Date : 16/06/2017
	 * Author :Teja
	 * @description : 
	 */
	public List<NregsDataVO> getNregsVermiData(InputVO inputVO){
		List<NregsDataVO> returnList = new ArrayList<NregsDataVO>(0);
		try {
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/VermiService/VermiData", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String result = response.getEntity(String.class);
	 	    	 
	 	    	if(result != null && !result.isEmpty()){
	 	    		JSONArray resultArray = new JSONArray(result);
	 	    		if(resultArray!=null && resultArray.length()>0){
	 	    			for(int i=0;i<resultArray.length();i++){
	 	    				NregsDataVO vo = new NregsDataVO();
	 	    				
	 	    				JSONObject jObj = (JSONObject) resultArray.get(i);
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
	 	    				vo.setPercentage(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				
	 	    				returnList.add(vo);
	 	    				//getDistrictsConstitByType(returnList,inputVO.getType());
	 	    			}
	 	    		}
	 	    	}
	 	     }
		} catch (Exception e) {
			LOG.error("Exception raised at getNregsVermiData - NREGSTCSService service", e);
		}
		return returnList;
	}
	/*
	 * Date : 16/06/2017
	 * Author :Teja
	 * @description :getNregsNtrsOverview(Nregs Ntrs details)
	 */
	public NregsOverviewVO getNregsNtrsOverview(InputVO inputVO){
		NregsOverviewVO returnVo = new NregsOverviewVO();
		try {
			 
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NtrsService/NtrsOverview", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String result = response.getEntity(String.class);
	 	    	 
	 	    	if(result != null && !result.isEmpty()){
	 	    		JSONObject jObj = new JSONObject(result);
	 	    		NregsOverviewVO vo = new NregsOverviewVO();
	 	                 
	 	                returnVo.setAveragePerDistrict(jObj.getString("AVERAGEPERDISTRICT"));
	 	                returnVo.setAveragePerConstituency(jObj.getString("AVERAGEPERCONSTITUENCY"));
	 	                returnVo.setAveragePerMandal(jObj.getString("AVERAGEPERMANDAL"));
	 	                returnVo.setTotalBudget(jObj.getLong("TOTALBUDGET"));
	 	                returnVo.setTotalAvgFarmsInDistrict(jObj.getString("TOTALAVGFARMSINDISTRICT"));
	 	                returnVo.setTotalAvgFarmsInConstituency(jObj.getString("TOTALAVGFARMSINCONSTITUENCY"));
	 	                returnVo.setTotalAvgFarmsInMandal(jObj.getString("TOTALAVGFARMSINMANDAL"));
	 	                returnVo.setDistrictsInRed(jObj.getLong("DISTRICTSINRED"));
	 	                returnVo.setDistrictsInOrange(jObj.getLong("DISTRICTSINORANGE"));
	 	                returnVo.setDistrictsInGreen(jObj.getLong("DISTRICTSINGREEN"));
	 	                returnVo.setTotalDistricts(jObj.getLong("TOTALDISTRICTS"));
	 	                returnVo.setConstituenciesInRed(jObj.getLong("CONSTITUENCIESINRED"));
	 	                returnVo.setConstituenciesInOrange(jObj.getLong("CONSTITUENCIESINORANGE"));
	 	                returnVo.setConstituenciesInGreen(jObj.getLong("CONSTITUENCIESINGREEN"));
	 	                returnVo.setTotalConstituencies(jObj.getLong("TOTALCONSTITUENCIES"));
	 	                returnVo.setMandalsInRed(jObj.getLong("MANDALSINRED"));
	 	                returnVo.setMandalsInOrange(jObj.getLong("MANDALSINORANGE"));
	 	                returnVo.setMandalsInGreen(jObj.getLong("MANDALSINGREEN"));
	 	                returnVo.setTotalMandals(jObj.getLong("TOTALMANDALS"));
	 	                returnVo.setVillagesInRed(jObj.getLong("VILLAGESINRED"));
	 	                returnVo.setVillagesInOrange(jObj.getLong("VILLAGESINORANGE"));
	 	                returnVo.setVillagesInGreen(jObj.getLong("VILLAGESINGREEN"));
	 	                returnVo.setTotalVillages(jObj.getLong("TOTALVILLAGES"));
	 	               }
	 	            }
				} catch (Exception e) {
					LOG.error("Exception raised at getNregsNtrsOverview - NREGSTCSService service", e);
				}
		return returnVo;
	}
	/*
	 * Date : 16/06/2017
	 * Author :Teja
	 * @description : getNregsNtrsData
	 */
	public List<NregsDataVO> getNregsNtrsData(InputVO inputVO){
		List<NregsDataVO> returnList = new ArrayList<NregsDataVO>(0);
		try {
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NtrsService/NtrsData", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String result = response.getEntity(String.class);
	 	    	 
	 	    	if(result != null && !result.isEmpty()){
	 	    		JSONArray resultArray = new JSONArray(result);
	 	    		if(resultArray!=null && resultArray.length()>0){
	 	    			for(int i=0;i<resultArray.length();i++){
	 	    				NregsDataVO vo = new NregsDataVO();
	 	    				
	 	    				JSONObject jObj = (JSONObject) resultArray.get(i);
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
	 	    				vo.setPercentage(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
 	    					returnList.add(vo);
 	    					//getDistrictsConstitByType(returnList,inputVO.getType());
	 	    					 	    				
	 	    			}
	 	    		}
	 	    	}
	 	     }
		} catch (Exception e) {
			LOG.error("Exception raised at getNregsNtrsData - NREGSTCSService service", e);
		}
		return returnList;
	}
	/*
	 * Date : 16/06/2017
	 * Author :Teja
	 * @description :getNregsAnganwadiOverview(Nregs anganwadi details)
	 */
	public NregsOverviewVO getNregsAnganwadiOverview(InputVO inputVO){
		NregsOverviewVO returnVo = new NregsOverviewVO();
		try {
			 
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AnganwadiService/AnganwadiOverview", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String result = response.getEntity(String.class);
	 	    	 
	 	    	if(result != null && !result.isEmpty()){
	 	    		JSONObject jObj = new JSONObject(result);
	 	    		NregsOverviewVO vo = new NregsOverviewVO();
	 	                 
	 	                returnVo.setAveragePerDistrict(jObj.getString("AVERAGEPERDISTRICT"));
	 	                returnVo.setAveragePerConstituency(jObj.getString("AVERAGEPERCONSTITUENCY"));
	 	                returnVo.setAveragePerMandal(jObj.getString("AVERAGEPERMANDAL"));
	 	                returnVo.setTotalBudget(jObj.getLong("TOTALBUDGET"));
	 	                returnVo.setTotalAvgFarmsInDistrict(jObj.getString("TOTALAVGFARMSINDISTRICT"));
	 	                returnVo.setTotalAvgFarmsInConstituency(jObj.getString("TOTALAVGFARMSINCONSTITUENCY"));
	 	                returnVo.setTotalAvgFarmsInMandal(jObj.getString("TOTALAVGFARMSINMANDAL"));
	 	                returnVo.setDistrictsInRed(jObj.getLong("DISTRICTSINRED"));
	 	                returnVo.setDistrictsInOrange(jObj.getLong("DISTRICTSINORANGE"));
	 	                returnVo.setDistrictsInGreen(jObj.getLong("DISTRICTSINGREEN"));
	 	                returnVo.setTotalDistricts(jObj.getLong("TOTALDISTRICTS"));
	 	                returnVo.setConstituenciesInRed(jObj.getLong("CONSTITUENCIESINRED"));
	 	                returnVo.setConstituenciesInOrange(jObj.getLong("CONSTITUENCIESINORANGE"));
	 	                returnVo.setConstituenciesInGreen(jObj.getLong("CONSTITUENCIESINGREEN"));
	 	                returnVo.setTotalConstituencies(jObj.getLong("TOTALCONSTITUENCIES"));
	 	                returnVo.setMandalsInRed(jObj.getLong("MANDALSINRED"));
	 	                returnVo.setMandalsInOrange(jObj.getLong("MANDALSINORANGE"));
	 	                returnVo.setMandalsInGreen(jObj.getLong("MANDALSINGREEN"));
	 	                returnVo.setTotalMandals(jObj.getLong("TOTALMANDALS"));
	 	                returnVo.setVillagesInRed(jObj.getLong("VILLAGESINRED"));
	 	                returnVo.setVillagesInOrange(jObj.getLong("VILLAGESINORANGE"));
	 	                returnVo.setVillagesInGreen(jObj.getLong("VILLAGESINGREEN"));
	 	                returnVo.setTotalVillages(jObj.getLong("TOTALVILLAGES"));
	 	               }
	 	            }
				} catch (Exception e) {
					LOG.error("Exception raised at getNregsAnganwadiOverview - NREGSTCSService service", e);
				}
		return returnVo;
	}
	/*
	 * Date : 16/06/2017
	 * Author :Teja
	 * @description : getNregsAnganwadiData
	 */
	public List<NregsDataVO> getNregsAnganwadiData(InputVO inputVO){
		List<NregsDataVO> returnList = new ArrayList<NregsDataVO>(0);
		try {
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AnganwadiService/AnganwadiData", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String result = response.getEntity(String.class);
	 	    	 
	 	    	if(result != null && !result.isEmpty()){
	 	    		JSONArray resultArray = new JSONArray(result);
	 	    		if(resultArray!=null && resultArray.length()>0){
	 	    			for(int i=0;i<resultArray.length();i++){
	 	    				NregsDataVO vo = new NregsDataVO();
	 	    				
	 	    				JSONObject jObj = (JSONObject) resultArray.get(i);
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
	 	    				vo.setPercentage(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				returnList.add(vo);
	 	    				//getDistrictsConstitByType(returnList,inputVO.getType());
	 	    			}
	 	    		}
	 	    	}
	 	     }
		} catch (Exception e) {
			LOG.error("Exception raised at getNregsAnganwadiData - NREGSTCSService service", e);
		}
		return returnList;
	}

	/*
	 * Date : 19/06/2017
	 * Author :Swapna
	 * @description : getCCRoadsOverview
	 */
	@Override
	public FarmPondOverviewVO getCCRoadsOverview(InputVO inputVO) {
		FarmPondOverviewVO farmpondoverviewvO = new FarmPondOverviewVO();
		try {
			 ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CCRoadsService/CCRoadsOverview", inputVO);
	           if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class); 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		        JSONObject jObj = new JSONObject(output); 		 	    				 				
	 	    				farmpondoverviewvO.setAveragePerDistrict(new BigDecimal(jObj.getString("AVERAGEPERDISTRICT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				farmpondoverviewvO.setAveragePerConstituency(new BigDecimal(jObj.getString("AVERAGEPERCONSTITUENCY")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				farmpondoverviewvO.setAveragePerMandal(new BigDecimal(jObj.getString("AVERAGEPERMANDAL")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				farmpondoverviewvO.setConstituenciesInGreen(jObj.getLong("CONSTITUENCIESINGREEN"));
	 	    				farmpondoverviewvO.setConstituenciesInOrange(jObj.getLong("CONSTITUENCIESINORANGE"));
	 	    				farmpondoverviewvO.setConstituenciesInRed(jObj.getLong("CONSTITUENCIESINRED"));
	 	    				farmpondoverviewvO.setDistrictsInGreen(jObj.getLong("DISTRICTSINGREEN"));
	 	    				farmpondoverviewvO.setDistrictsInOrange(jObj.getLong("DISTRICTSINORANGE"));
	 	    				farmpondoverviewvO.setDistrictsInRed(jObj.getLong("DISTRICTSINRED"));
	 	    				farmpondoverviewvO.setMandalsInGreen(jObj.getLong("MANDALSINGREEN"));
	 	    				farmpondoverviewvO.setMandalsInOrange(jObj.getLong("MANDALSINORANGE"));
	 	    				farmpondoverviewvO.setMandalsInRed(jObj.getLong("MANDALSINRED"));
	 	    				farmpondoverviewvO.setTotalAvgFarmsInConstituency(new BigDecimal(jObj.getString("TOTALAVGFARMSINCONSTITUENCY")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				farmpondoverviewvO.setTotalAvgFarmsInDistrict(new BigDecimal(jObj.getString("TOTALAVGFARMSINDISTRICT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				farmpondoverviewvO.setTotalBudget(jObj.getLong("TOTALBUDGET"));
	 	    				farmpondoverviewvO.setTotalDistricts(jObj.getLong("TOTALDISTRICTS"));
	 	    				farmpondoverviewvO.setTotalMandals(jObj.getLong("TOTALMANDALS"));
	 	    				farmpondoverviewvO.setTotalVillages(jObj.getLong("TOTALVILLAGES") );
	 	    				farmpondoverviewvO.setVillagesInGreen(jObj.getLong("VILLAGESINGREEN"));
	 	    				farmpondoverviewvO.setVillagesInOrange(jObj.getLong("VILLAGESINORANGE"));
	 	    				farmpondoverviewvO.setVillagesInRed(jObj.getLong("VILLAGESINRED"));
	 	    				farmpondoverviewvO.setTotalAvgFarmsInMandal(new BigDecimal(jObj.getString("TOTALAVGFARMSINMANDAL")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				farmpondoverviewvO.setTotalConstituencies(jObj.getLong("TOTALCONSTITUENCIES")); 	    				
	 	    			}
	 	    		    } 	 
	 	                }
	 	             catch (Exception e) {
				LOG.error("Exception raised at getCCRoadsOverview -CCRoadsOverview service", e);
			}
			
			return  farmpondoverviewvO;

	
}
	/*
	 * Date : 19/06/2017
	 * Author :Swapna
	 * @description : getCCRoadsData
	 */
	
	@Override
	public List<NregsDataVO> getCCRoadsData(InputVO inputVO) {
		List<NregsDataVO> list = new ArrayList<NregsDataVO>(0);
		try {
			 
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CCRoadsService/CCRoadsData", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);	 	    	 
	 	    	 if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				NregsDataVO nregsDataVO=new NregsDataVO();
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				nregsDataVO.setUniqueId(jObj.getLong("UNIQUEID"));
	 	    				nregsDataVO.setDistrict(jObj.getString("DISTRICT"));
	 	    				nregsDataVO.setConstituency(jObj.getString("CONSTITUENCY"));
	 	    				nregsDataVO.setMandal(jObj.getString("MANDAL"));
	 	    				nregsDataVO.setPanchayat(jObj.getString("PANCHAYAT"));
	 	    				nregsDataVO.setTarget(jObj.getLong("TARGET"));
	 	    				nregsDataVO.setGrounded(jObj.getString("GROUNDED"));
	 	    				nregsDataVO.setNotGrounded(jObj.getString("NOTGROUNDED"));
	 	    				nregsDataVO.setInProgress(jObj.getLong("INPROGRESS"));
	 	    				nregsDataVO.setCompleted(jObj.getLong("COMPLETED"));
	 	    				nregsDataVO.setPercentage(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());	 	    				
	 	    				list.add(nregsDataVO);	 	 
	 	    				//getDistrictsConstitByType(list,inputVO.getType());
	 	    			}
	 	    		}
	 	    	}
	 	    	 
	 	    	  
	 	      }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getCCRoadsData -getCCRoadsData service", e);
		}
		
		return list;
	}

	
	/*
	 * Date : 16/06/2017
	 * Author :Teja
	 * @description :getNregsMandalBuildingOverview(Nregs anganwadi details)
	 */
	public NregsOverviewVO getNregsMandalBuildingOverview(InputVO inputVO){
		NregsOverviewVO returnVo = new NregsOverviewVO();
		try {
			 
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MandalBuildingService/MandalBuildingOverview", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String result = response.getEntity(String.class);
	 	    	 
	 	    	if(result != null && !result.isEmpty()){
	 	    		JSONObject jObj = new JSONObject(result);
	 	    		NregsOverviewVO vo = new NregsOverviewVO();
	 	                 
	 	                returnVo.setAveragePerDistrict(new BigDecimal(jObj.getString("AVERAGEPERDISTRICT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	                returnVo.setAveragePerConstituency(new BigDecimal(jObj.getString("AVERAGEPERCONSTITUENCY")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	                returnVo.setAveragePerMandal(new BigDecimal(jObj.getString("AVERAGEPERMANDAL")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	                returnVo.setTotalBudget(jObj.getLong("TOTALBUDGET"));
	 	                returnVo.setTotalAvgFarmsInDistrict(new BigDecimal(jObj.getString("TOTALAVGFARMSINDISTRICT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	                returnVo.setTotalAvgFarmsInConstituency(new BigDecimal(jObj.getString("TOTALAVGFARMSINCONSTITUENCY")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	                returnVo.setTotalAvgFarmsInMandal(new BigDecimal(jObj.getString("TOTALAVGFARMSINMANDAL")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	                returnVo.setDistrictsInRed(jObj.getLong("DISTRICTSINRED"));
	 	                returnVo.setDistrictsInOrange(jObj.getLong("DISTRICTSINORANGE"));
	 	                returnVo.setDistrictsInGreen(jObj.getLong("DISTRICTSINGREEN"));
	 	                returnVo.setTotalDistricts(jObj.getLong("TOTALDISTRICTS"));
	 	                returnVo.setConstituenciesInRed(jObj.getLong("CONSTITUENCIESINRED"));
	 	                returnVo.setConstituenciesInOrange(jObj.getLong("CONSTITUENCIESINORANGE"));
	 	                returnVo.setConstituenciesInGreen(jObj.getLong("CONSTITUENCIESINGREEN"));
	 	                returnVo.setTotalConstituencies(jObj.getLong("TOTALCONSTITUENCIES"));
	 	                returnVo.setMandalsInRed(jObj.getLong("MANDALSINRED"));
	 	                returnVo.setMandalsInOrange(jObj.getLong("MANDALSINORANGE"));
	 	                returnVo.setMandalsInGreen(jObj.getLong("MANDALSINGREEN"));
	 	                returnVo.setTotalMandals(jObj.getLong("TOTALMANDALS"));
	 	                returnVo.setVillagesInRed(jObj.getLong("VILLAGESINRED"));
	 	                returnVo.setVillagesInOrange(jObj.getLong("VILLAGESINORANGE"));
	 	                returnVo.setVillagesInGreen(jObj.getLong("VILLAGESINGREEN"));
	 	                returnVo.setTotalVillages(jObj.getLong("TOTALVILLAGES"));
	 	               }
	 	            }
				} catch (Exception e) {
					LOG.error("Exception raised at getNregsMandalBuildingOverview - NREGSTCSService service", e);
				}
		return returnVo;
	}
	/*
	 * Date : 16/06/2017
	 * Author :Teja
	 * @description : getNregsMandalBuildingData
	 */
	public List<NregsDataVO> getNregsMandalBuildingData(InputVO inputVO){
		List<NregsDataVO> returnList = new ArrayList<NregsDataVO>(0);
		try {
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MandalBuildingService/MandalBuildingData", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String result = response.getEntity(String.class);
	 	    	 
	 	    	if(result != null && !result.isEmpty()){
	 	    		JSONArray resultArray = new JSONArray(result);
	 	    		if(resultArray!=null && resultArray.length()>0){
	 	    			for(int i=0;i<resultArray.length();i++){
	 	    				NregsDataVO vo = new NregsDataVO();
	 	    				
	 	    				JSONObject jObj = (JSONObject) resultArray.get(i);
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
	 	    				vo.setPercentage(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				returnList.add(vo);
	 	    				//getDistrictsConstitByType(returnList,inputVO.getType());
	 	    			}
	 	    		}
	 	    	}
	 	     }
		} catch (Exception e) {
			LOG.error("Exception raised at getNregsMandalBuildingData - NREGSTCSService service", e);
		}
		return returnList;
	}
	/*
	 * Date : 19/06/2017
	 * Author :Nandhini
	 * @description : getNregaGPBuilingsOverview
	 */
	public NregsOverviewVO getNregaGPBuilingsOverview(InputVO inputVO){
		NregsOverviewVO finalVO = new NregsOverviewVO();
		try {
			 
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/GPBuildingService/GPBuildingOverview", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject Obj = new JSONObject(output);
	 	    		if(Obj!=null && Obj.length()>0){
	 	    				finalVO.setAveragePerDistrict(new BigDecimal(Obj.getString("AVERAGEPERDISTRICT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				finalVO.setAveragePerConstituency(new BigDecimal(Obj.getString("AVERAGEPERCONSTITUENCY")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				finalVO.setAveragePerMandal(new BigDecimal(Obj.getString("AVERAGEPERMANDAL")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				finalVO.setTotalBudget(Obj.getLong("TOTALBUDGET"));
	 	    				finalVO.setTotalAvgFarmsInDistrict(new BigDecimal(Obj.getString("TOTALAVGFARMSINDISTRICT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				finalVO.setTotalAvgFarmsInConstituency(new BigDecimal(Obj.getString("TOTALAVGFARMSINCONSTITUENCY")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				finalVO.setTotalAvgFarmsInMandal(new BigDecimal(Obj.getString("TOTALAVGFARMSINMANDAL")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
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
	 	    				finalVO.setVillagesInRed(Obj.getLong("VILLAGESINRED"));
	 	    				finalVO.setVillagesInOrange(Obj.getLong("VILLAGESINORANGE"));
	 	    				finalVO.setVillagesInGreen(Obj.getLong("VILLAGESINGREEN"));
	 	    				finalVO.setTotalVillages(Obj.getLong("TOTALVILLAGES"));
	 	    			}
	 	    		}
	 	    	}
	 	    	 
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaGPBuilingsOverview - NREGSTCSService service", e);
		}
		
		return finalVO;
	}
	/*
	 * Date : 19/06/2017
	 * Author :Nandhini
	 * @description : getNregaLevelsOverviewForGPBuilding
	 */
	public List<NregsDataVO> getNregaLevelsOverviewForGPBuilding(InputVO inputVO){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
			 
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/GPBuildingService/GPBuildingData", inputVO);
	        
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
	 	    				vo.setPercentage(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				
	 	    				voList.add(vo);
	 	    				
	 	    			}
	 	    		}
	 	    	}
	 	      }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsOverviewForGPBuilding - NREGSTCSService service", e);
		}
		
		return voList;
	}

	/*
	 * Date : 20/06/2017
	 * Author :Sravanth
	 * @description : getNregaProductionOfBricksOverview
	 */
	public NregsOverviewVO getNregaProductionOfBricksOverview(InputVO inputVO){
		NregsOverviewVO finalVO = new NregsOverviewVO();
		try {
			 
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BricksService/BricksOverview", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject Obj = new JSONObject(output);
	 	    		if(Obj!=null && Obj.length()>0){
	 	    				finalVO.setAveragePerDistrict(new BigDecimal(Obj.getString("AVERAGEPERDISTRICT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				finalVO.setAveragePerConstituency(new BigDecimal(Obj.getString("AVERAGEPERCONSTITUENCY")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				finalVO.setAveragePerMandal(new BigDecimal(Obj.getString("AVERAGEPERMANDAL")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				finalVO.setTotalBudget(Obj.getLong("TOTALBUDGET"));
	 	    				finalVO.setTotalAvgFarmsInDistrict(new BigDecimal(Obj.getString("TOTALAVGFARMSINDISTRICT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				finalVO.setTotalAvgFarmsInConstituency(new BigDecimal(Obj.getString("TOTALAVGFARMSINCONSTITUENCY")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				finalVO.setTotalAvgFarmsInMandal(new BigDecimal(Obj.getString("TOTALAVGFARMSINMANDAL")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
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
	 	    				finalVO.setVillagesInRed(Obj.getLong("VILLAGESINRED"));
	 	    				finalVO.setVillagesInOrange(Obj.getLong("VILLAGESINORANGE"));
	 	    				finalVO.setVillagesInGreen(Obj.getLong("VILLAGESINGREEN"));
	 	    				finalVO.setTotalVillages(Obj.getLong("TOTALVILLAGES"));
	 	    			}
	 	    		}
	 	    	}
	 	    	 
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaProductionOfBricksOverview - NREGSTCSService service", e);
		}
		
		return finalVO;
	}
	
	/*
	 * Date : 20/06/2017
	 * Author :Sravanth
	 * @description : getNregaRaisingOfPerinnialFodderOverview
	 */
	public NregsOverviewVO getNregaRaisingOfPerinnialFodderOverview(InputVO inputVO){
		NregsOverviewVO finalVO = new NregsOverviewVO();
		try {
			 
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FodderService/FodderOverview", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject Obj = new JSONObject(output);
	 	    		if(Obj!=null && Obj.length()>0){
	 	    				finalVO.setAveragePerDistrict(new BigDecimal(Obj.getString("AVERAGEPERDISTRICT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				finalVO.setAveragePerConstituency(new BigDecimal(Obj.getString("AVERAGEPERCONSTITUENCY")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				finalVO.setAveragePerMandal(new BigDecimal(Obj.getString("AVERAGEPERMANDAL")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				finalVO.setTotalBudget(Obj.getLong("TOTALBUDGET"));
	 	    				finalVO.setTotalAvgFarmsInDistrict(new BigDecimal(Obj.getString("TOTALAVGFARMSINDISTRICT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				finalVO.setTotalAvgFarmsInConstituency(new BigDecimal(Obj.getString("TOTALAVGFARMSINCONSTITUENCY")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				finalVO.setTotalAvgFarmsInMandal(new BigDecimal(Obj.getString("TOTALAVGFARMSINMANDAL")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
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
	 	    				finalVO.setVillagesInRed(Obj.getLong("VILLAGESINRED"));
	 	    				finalVO.setVillagesInOrange(Obj.getLong("VILLAGESINORANGE"));
	 	    				finalVO.setVillagesInGreen(Obj.getLong("VILLAGESINGREEN"));
	 	    				finalVO.setTotalVillages(Obj.getLong("TOTALVILLAGES"));
	 	    			}
	 	    		}
	 	    	}
	 	    	 
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaRaisingOfPerinnialFodderOverview - NREGSTCSService service", e);
		}
		
		return finalVO;
	}
	
	/*
	 * Date : 20/06/2017
	 * Author :Sravanth
	 * @description : getNregaSilkWormOverview
	 */
	public NregsOverviewVO getNregaSilkWormOverview(InputVO inputVO){
		NregsOverviewVO finalVO = new NregsOverviewVO();
		try {
			 
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SilkwormService/SilkwormOverview", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject Obj = new JSONObject(output);
	 	    		if(Obj!=null && Obj.length()>0){
	 	    				finalVO.setAveragePerDistrict(new BigDecimal(Obj.getString("AVERAGEPERDISTRICT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				finalVO.setAveragePerConstituency(new BigDecimal(Obj.getString("AVERAGEPERCONSTITUENCY")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				finalVO.setAveragePerMandal(new BigDecimal(Obj.getString("AVERAGEPERMANDAL")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				finalVO.setTotalBudget(Obj.getLong("TOTALBUDGET"));
	 	    				finalVO.setTotalAvgFarmsInDistrict(new BigDecimal(Obj.getString("TOTALAVGFARMSINDISTRICT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				finalVO.setTotalAvgFarmsInConstituency(new BigDecimal(Obj.getString("TOTALAVGFARMSINCONSTITUENCY")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				finalVO.setTotalAvgFarmsInMandal(new BigDecimal(Obj.getString("TOTALAVGFARMSINMANDAL")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
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
	 	    				finalVO.setVillagesInRed(Obj.getLong("VILLAGESINRED"));
	 	    				finalVO.setVillagesInOrange(Obj.getLong("VILLAGESINORANGE"));
	 	    				finalVO.setVillagesInGreen(Obj.getLong("VILLAGESINGREEN"));
	 	    				finalVO.setTotalVillages(Obj.getLong("TOTALVILLAGES"));
	 	    			}
	 	    		}
	 	    	}
	 	    	 
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaSilkWormOverview - NREGSTCSService service", e);
		}
		
		return finalVO;
	}
	
	/*
	 * Date : 20/06/2017
	 * Author :Sravanth
	 * @description : getNregaLevelsOverviewForProductionOfBricks
	 */
	public List<NregsDataVO> getNregaLevelsOverviewForProductionOfBricks(InputVO inputVO){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
			 
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BricksService/BricksData", inputVO);
	        
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
	 	    				vo.setPercentage(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				
	 	    				voList.add(vo);
	 	    			}
	 	    		}
	 	    	}
	 	      }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsOverviewForProductionOfBricks - NREGSTCSService service", e);
		}
		
		return voList;
	}
	
	/*
	 * Date : 20/06/2017
	 * Author :Sravanth
	 * @description : getNregaLevelsOverviewForRaisingOfPerinnialFodder
	 */
	public List<NregsDataVO> getNregaLevelsOverviewForRaisingOfPerinnialFodder(InputVO inputVO){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
			 
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FodderService/FodderData", inputVO);
	        
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
	 	    				vo.setPercentage(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				voList.add(vo);
	 	    				//getDistrictsConstitByType(voList,inputVO.getType());
	 	    			}
	 	    		}
	 	    	}
	 	      }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsOverviewForRaisingOfPerinnialFodder - NREGSTCSService service", e);
		}
		
		return voList;
	}
	
	/*
	 * Date : 20/06/2017
	 * Author :Sravanth
	 * @description : getNregaLevelsOverviewForSilkWarm
	 */
	public List<NregsDataVO> getNregaLevelsOverviewForSilkWarm(InputVO inputVO){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
			 
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SilkwormService/SilkwormData", inputVO);
	        
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
	 	    				vo.setPercentage(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				voList.add(vo);
	 	    				//getDistrictsConstitByType(voList,inputVO.getType());
	 	    			}
	 	    		}
	 	    	}
	 	      }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsOverviewForSilkWarm - NREGSTCSService service", e);
		}
		
		return voList;
	}
	/*
	 * Date : 19/06/2017
	 * Author :Swathi
	 * @description :getNregsSericultureOverview
	 */
	public NregsOverviewVO getNregsSericultureOverview(InputVO inputVO){
		NregsOverviewVO returnVo = new NregsOverviewVO();
		try {
			 
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SericultureService/SericultureOverview", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String result = response.getEntity(String.class);
	 	    	 
	 	    	if(result != null && !result.isEmpty()){
	 	    		JSONObject jObj = new JSONObject(result);
	 	    		NregsOverviewVO vo = new NregsOverviewVO();
	 	                 
	 	                returnVo.setAveragePerDistrict(jObj.getString("AVERAGEPERDISTRICT"));
	 	                returnVo.setAveragePerConstituency(jObj.getString("AVERAGEPERCONSTITUENCY"));
	 	                returnVo.setAveragePerMandal(jObj.getString("AVERAGEPERMANDAL"));
	 	                returnVo.setTotalBudget(jObj.getLong("TOTALBUDGET"));
	 	                returnVo.setTotalAvgFarmsInDistrict(jObj.getString("TOTALAVGFARMSINDISTRICT"));
	 	                returnVo.setTotalAvgFarmsInConstituency(jObj.getString("TOTALAVGFARMSINCONSTITUENCY"));
	 	                returnVo.setTotalAvgFarmsInMandal(jObj.getString("TOTALAVGFARMSINMANDAL"));
	 	                returnVo.setDistrictsInRed(jObj.getLong("DISTRICTSINRED"));
	 	                returnVo.setDistrictsInOrange(jObj.getLong("DISTRICTSINORANGE"));
	 	                returnVo.setDistrictsInGreen(jObj.getLong("DISTRICTSINGREEN"));
	 	                returnVo.setTotalDistricts(jObj.getLong("TOTALDISTRICTS"));
	 	                returnVo.setConstituenciesInRed(jObj.getLong("CONSTITUENCIESINRED"));
	 	                returnVo.setConstituenciesInOrange(jObj.getLong("CONSTITUENCIESINORANGE"));
	 	                returnVo.setConstituenciesInGreen(jObj.getLong("CONSTITUENCIESINGREEN"));
	 	                returnVo.setTotalConstituencies(jObj.getLong("TOTALCONSTITUENCIES"));
	 	                returnVo.setMandalsInRed(jObj.getLong("MANDALSINRED"));
	 	                returnVo.setMandalsInOrange(jObj.getLong("MANDALSINORANGE"));
	 	                returnVo.setMandalsInGreen(jObj.getLong("MANDALSINGREEN"));
	 	                returnVo.setTotalMandals(jObj.getLong("TOTALMANDALS"));
	 	                returnVo.setVillagesInRed(jObj.getLong("VILLAGESINRED"));
	 	                returnVo.setVillagesInOrange(jObj.getLong("VILLAGESINORANGE"));
	 	                returnVo.setVillagesInGreen(jObj.getLong("VILLAGESINGREEN"));
	 	                returnVo.setTotalVillages(jObj.getLong("TOTALVILLAGES"));
	 	               }
	 	            }
				} catch (Exception e) {
					LOG.error("Exception raised at getNregsSericultureOverview - NREGSTCSService service", e);
				}
		return returnVo;
	}
	/*
	 * Date : 19/06/2017
	 * Author :Swathi
	 * @description : getNregsSericultureData
	 */
	public List<NregsDataVO> getNregsSericultureData(InputVO inputVO){
		List<NregsDataVO> returnList = new ArrayList<NregsDataVO>(0);
		try {	
			
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SericultureService/SericultureData", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String result = response.getEntity(String.class);
	 	    	 
	 	    	if(result != null && !result.isEmpty()){
	 	    		JSONArray resultArray = new JSONArray(result);
	 	    		if(resultArray!=null && resultArray.length()>0){
	 	    			for(int i=0;i<resultArray.length();i++){
	 	    				NregsDataVO vo = new NregsDataVO();	 	    				
	 	    				JSONObject jObj = (JSONObject) resultArray.get(i);
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
	 	    				vo.setPercentage(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				returnList.add(vo);
	 	    				//getDistrictsConstitByType(returnList,inputVO.getType());
	 	    		
	 	    			}
	 	    		}
	 	    	}
	 	      }
	 	    	
		} 	catch (Exception e) {
			LOG.error("Exception raised at getNregsSericultureData - NREGSTCSService service", e);
		}
		return returnList;
	}
	/*
	 * Date : 19/06/2017
	 * Author :Swathi
	 * @description :getNregsHousingOverview
	 */
 
	public NregsOverviewVO getNregsHousingOverview(InputVO inputVO){
		NregsOverviewVO returnVo = new NregsOverviewVO();
		try {
			 
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HousingService/HousingOverview", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String result = response.getEntity(String.class);
	 	    	 
	 	    	if(result != null && !result.isEmpty()){
	 	    		JSONObject jObj = new JSONObject(result);
	 	    		NregsOverviewVO vo = new NregsOverviewVO();
	 	                 
	 	                returnVo.setAveragePerDistrict(jObj.getString("AVERAGEPERDISTRICT"));
	 	                returnVo.setAveragePerConstituency(jObj.getString("AVERAGEPERCONSTITUENCY"));
	 	                returnVo.setAveragePerMandal(jObj.getString("AVERAGEPERMANDAL"));
	 	                returnVo.setTotalBudget(jObj.getLong("TOTALBUDGET"));
	 	                returnVo.setTotalAvgFarmsInDistrict(jObj.getString("TOTALAVGFARMSINDISTRICT"));
	 	                returnVo.setTotalAvgFarmsInConstituency(jObj.getString("TOTALAVGFARMSINCONSTITUENCY"));
	 	                returnVo.setTotalAvgFarmsInMandal(jObj.getString("TOTALAVGFARMSINMANDAL"));
	 	                returnVo.setDistrictsInRed(jObj.getLong("DISTRICTSINRED"));
	 	                returnVo.setDistrictsInOrange(jObj.getLong("DISTRICTSINORANGE"));
	 	                returnVo.setDistrictsInGreen(jObj.getLong("DISTRICTSINGREEN"));
	 	                returnVo.setTotalDistricts(jObj.getLong("TOTALDISTRICTS"));
	 	                returnVo.setConstituenciesInRed(jObj.getLong("CONSTITUENCIESINRED"));
	 	                returnVo.setConstituenciesInOrange(jObj.getLong("CONSTITUENCIESINORANGE"));
	 	                returnVo.setConstituenciesInGreen(jObj.getLong("CONSTITUENCIESINGREEN"));
	 	                returnVo.setTotalConstituencies(jObj.getLong("TOTALCONSTITUENCIES"));
	 	                returnVo.setMandalsInRed(jObj.getLong("MANDALSINRED"));
	 	                returnVo.setMandalsInOrange(jObj.getLong("MANDALSINORANGE"));
	 	                returnVo.setMandalsInGreen(jObj.getLong("MANDALSINGREEN"));
	 	                returnVo.setTotalMandals(jObj.getLong("TOTALMANDALS"));
	 	                returnVo.setVillagesInRed(jObj.getLong("VILLAGESINRED"));
	 	                returnVo.setVillagesInOrange(jObj.getLong("VILLAGESINORANGE"));
	 	                returnVo.setVillagesInGreen(jObj.getLong("VILLAGESINGREEN"));
	 	                returnVo.setTotalVillages(jObj.getLong("TOTALVILLAGES"));
	 	               }
	 	            }
				} catch (Exception e) {
					LOG.error("Exception raised at getNregsHousingOverview - NREGSTCSService service", e);
				}
		return returnVo;
	}
	/*
	 * Date : 19/06/2017
	 * Author :Swathi
	 * @description : getNregsHousingData
	 */
	@Override
	public List<NregsDataVO> getNregsHousingData(InputVO inputVO){
		List<NregsDataVO> returnList = new ArrayList<NregsDataVO>(0);
		try {
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HousingService/HousingData", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String result = response.getEntity(String.class);
	 	    	 
	 	    	if(result != null && !result.isEmpty()){
	 	    		JSONArray resultArray = new JSONArray(result);
	 	    		if(resultArray!=null && resultArray.length()>0){
	 	    			for(int i=0;i<resultArray.length();i++){
	 	    				NregsDataVO vo = new NregsDataVO();
	 	    				
	 	    				JSONObject jObj = (JSONObject) resultArray.get(i);
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
	 	    				vo.setPercentage(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				
	 	    				returnList.add(vo);
	 	    			}
	 	    		}
	 	    	}
	 	     }
		} catch (Exception e) {
			LOG.error("Exception raised at getNregsHousingData - NREGSTCSService service", e);
		}
		return returnList;
	}	
	
	/*
	 * Date : 19/06/2017
	 * Author :Swapna
	 * @description : getAHOverview
	 */
	@Override
	public NregsOverviewVO getAHOverview(InputVO inputVO) {
		NregsOverviewVO returnVo = new NregsOverviewVO();
		try {			 
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AHService/AHOverview", inputVO);
	           if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String result = response.getEntity(String.class);	 	    	 
	 	    	if(result != null && !result.isEmpty()){
	 	    		JSONObject jObj = new JSONObject(result); 		
	 	                returnVo.setAveragePerDistrict(jObj.getString("AVERAGEPERDISTRICT"));
	 	                returnVo.setAveragePerConstituency(jObj.getString("AVERAGEPERCONSTITUENCY"));
	 	                returnVo.setAveragePerMandal(jObj.getString("AVERAGEPERMANDAL"));
	 	                returnVo.setTotalBudget(jObj.getLong("TOTALBUDGET"));
	 	                returnVo.setTotalAvgFarmsInDistrict(jObj.getString("TOTALAVGFARMSINDISTRICT"));
	 	                returnVo.setTotalAvgFarmsInConstituency(jObj.getString("TOTALAVGFARMSINCONSTITUENCY"));
	 	                returnVo.setTotalAvgFarmsInMandal(jObj.getString("TOTALAVGFARMSINMANDAL"));
	 	                returnVo.setDistrictsInRed(jObj.getLong("DISTRICTSINRED"));
	 	                returnVo.setDistrictsInOrange(jObj.getLong("DISTRICTSINORANGE"));
	 	                returnVo.setDistrictsInGreen(jObj.getLong("DISTRICTSINGREEN"));
	 	                returnVo.setTotalDistricts(jObj.getLong("TOTALDISTRICTS"));
	 	                returnVo.setConstituenciesInRed(jObj.getLong("CONSTITUENCIESINRED"));
	 	                returnVo.setConstituenciesInOrange(jObj.getLong("CONSTITUENCIESINORANGE"));
	 	                returnVo.setConstituenciesInGreen(jObj.getLong("CONSTITUENCIESINGREEN"));
	 	                returnVo.setTotalConstituencies(jObj.getLong("TOTALCONSTITUENCIES"));
	 	                returnVo.setMandalsInRed(jObj.getLong("MANDALSINRED"));
	 	                returnVo.setMandalsInOrange(jObj.getLong("MANDALSINORANGE"));
	 	                returnVo.setMandalsInGreen(jObj.getLong("MANDALSINGREEN"));
	 	                returnVo.setTotalMandals(jObj.getLong("TOTALMANDALS"));
	 	                returnVo.setVillagesInRed(jObj.getLong("VILLAGESINRED"));
	 	                returnVo.setVillagesInOrange(jObj.getLong("VILLAGESINORANGE"));
	 	                returnVo.setVillagesInGreen(jObj.getLong("VILLAGESINGREEN"));
	 	                returnVo.setTotalVillages(jObj.getLong("TOTALVILLAGES"));
	 	               }
	 	            }
				} catch (Exception e) {
					LOG.error("Exception raised at getAHOverview - AHOverviewService service", e);
				}
		return returnVo;
	
	}

	/*
	 * Date : 19/06/2017
	 * Author :Swapna
	 * @description : getAHData
	 */

	@Override
	public List<NregsDataVO> getAHData(InputVO inputVO) {
		List<NregsDataVO> list = new ArrayList<NregsDataVO>(0);
		try {
			 
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AHService/AHData", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				NregsDataVO nregsDataVO=new NregsDataVO();
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				nregsDataVO.setUniqueId(jObj.getLong("UNIQUEID"));
	 	    				nregsDataVO.setDistrict(jObj.getString("DISTRICT"));
	 	    				nregsDataVO.setConstituency(jObj.getString("CONSTITUENCY"));
	 	    				nregsDataVO.setMandal(jObj.getString("MANDAL"));
	 	    				nregsDataVO.setPanchayat(jObj.getString("PANCHAYAT"));
	 	    				nregsDataVO.setTarget(jObj.getLong("TARGET"));
	 	    				nregsDataVO.setGrounded(jObj.getString("GROUNDED"));
	 	    				nregsDataVO.setNotGrounded(jObj.getString("NOTGROUNDED"));
	 	    				nregsDataVO.setInProgress(jObj.getLong("INPROGRESS"));
	 	    				nregsDataVO.setCompleted(jObj.getLong("COMPLETED"));
	 	    				nregsDataVO.setPercentage(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());	 	    				
	 	    				list.add(nregsDataVO);	 
	 	    				//getDistrictsConstitByType(list,inputVO.getType());
	 	    			}
	 	    		}
	 	    	}
	 	    	    	  
	 	      }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getAHData -getAHData service", e);
		}
		
		return list;
	}
	/*
	 * Date : 22/06/2017
	 * Author :Nandhini
	 * @description : getNregsConstCuntDetails 
	 */

	@Override
	public List<NregsDataVO> getNregsConstCuntDetails(String output,Map<String,NregsDataVO> cntMap,String divType){
		List<NregsDataVO> retVOList = new ArrayList<>(0);
		try{
			//Map<String,NregsDataVO> cntMap = new HashMap<String,NregsDataVO>(0);
			String percValue = null;
			if(output != null && !output.isEmpty()){
	    		JSONArray finalArray = new JSONArray(output);
	    		if(finalArray!=null && finalArray.length()>0){
	    			for(int i=0;i<finalArray.length();i++){
	    				JSONObject jObj = (JSONObject) finalArray.get(i);
		    			String distrName = jObj.getString("DISTRICT");
						NregsDataVO vo = cntMap.get(distrName);
						if(divType != null && divType.trim().equalsIgnoreCase("Labour Budget")){
							percValue = new BigDecimal(jObj.getString("PER_APP_LB")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else{
							percValue = new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						}
						
		    				if(vo != null){
			    				if(Double.valueOf(percValue)  < 50){
		    						vo.setConstiInRed(vo.getConstiInRed()+1l);
		    					}else if(Double.valueOf(percValue)  >50 && Double.valueOf(percValue) <80){
		    						vo.setConstiInOrange(vo.getConstiInOrange()+1l);
	    						}else if(Double.valueOf(percValue)  >80){
		    						vo.setConstiInGreen(vo.getConstiInGreen()+1l);
		    					}
			    				vo.setTotal(vo.getTotal()+1l);
			    				vo.setDistrict(distrName);
		    				}
	    				}   	    							
	    			}
    			}
			
					if(cntMap != null){
						retVOList = new ArrayList<NregsDataVO>(cntMap.values());
					}
								
				}catch(Exception e){
					LOG.error("Exception raised at getNregsConstCuntDetails -NREGSTCSService service", e);
				}
			return retVOList;
	 }
	
	/*
	 * Date : 22/06/2017
	 * Author :Nandhini
	 * @description : getMGNregsDistrWiseConsti
	 */

	@Override
	public NregsDataVO getMGNregsDistrWiseConsti(InputVO inputVO) {
		NregsDataVO finalVO = new NregsDataVO();
		try {
			String webServiceUrl = null;
			List<NregsDataVO> list = new ArrayList<NregsDataVO>(0);
			
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Labour Budget"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CMDashBoard/LabourBudgetData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Farm Pond"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FarmPondService/FarmPondData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("IHHL"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/IHHLService/IHHLData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("VERMI"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/VermiService/VermiData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Gram Panchayat Buildings"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/GPBuildingService/GPBuildingData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR Jala Siri"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NtrsService/NtrsData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("CC Roads"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CCRoadsService/CCRoadsData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Anganwadi"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AnganwadiService/AnganwadiData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mandal buildings"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MandalBuildingService/MandalBuildingData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR 90 Days"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HousingService/HousingData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Production of Bricks"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BricksService/BricksData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SericultureService/SericultureData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Silk worm"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SilkwormService/SilkwormData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Cattle drinking water trough"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AHService/AHData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Raising of Perinnial Fodder"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FodderService/FodderData";
			
			 
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), inputVO);
	       
			if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
		 	    	if(output != null && !output.isEmpty()){
		 	    		JSONArray finalArray = new JSONArray(output);
		 	    		if(finalArray!=null && finalArray.length()>0){
		 	    			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Labour Budget")){
		 	    				for(int i=0;i<finalArray.length();i++){
		 	    					NregsDataVO vo = new NregsDataVO();
			 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
			 	    				vo.setUniqueId(jObj.getLong("UNIQUEID"));
			 	    				vo.setDistrict(jObj.getString("DISTRICT"));
			 	    				vo.setConstituency(jObj.getString("CONSTITUENCY"));
			 	    				vo.setMandal(jObj.getString("MANDAL"));
			 	    				vo.setPanchayat(jObj.getString("PANCHAYAT"));
			 	    				vo.setTargetPersonDays(jObj.getLong("TARGETPERSONDAYS"));
			 	    				vo.setGeneratedPersonDays(jObj.getLong("GENERATEDPERSONDAYS"));
			 	    				vo.setPerAppLB(new BigDecimal(jObj.getString("PER_APP_LB")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			 	    				vo.setAvgWageRate(new BigDecimal(jObj.getString("AVGWAGERATE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			 	    				vo.setTotalExpenditure(new BigDecimal(jObj.getString("TOTALEXPENDITURE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			 	    				vo.setPercentage(new BigDecimal(jObj.getString("PER_APP_LB")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			 	    				list.add(vo);	
			 	    			}
		 	    			}else{
		 	    				for(int i=0;i<finalArray.length();i++){
			 	    				NregsDataVO nregsDataVO=new NregsDataVO();
			 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
			 	    				nregsDataVO.setUniqueId(jObj.getLong("UNIQUEID"));
			 	    				nregsDataVO.setDistrict(jObj.getString("DISTRICT"));
			 	    				nregsDataVO.setConstituency(jObj.getString("CONSTITUENCY"));
			 	    				nregsDataVO.setMandal(jObj.getString("MANDAL"));
			 	    				nregsDataVO.setPanchayat(jObj.getString("PANCHAYAT"));
			 	    				nregsDataVO.setTarget(jObj.getLong("TARGET"));
			 	    				nregsDataVO.setGrounded(jObj.getString("GROUNDED"));
			 	    				nregsDataVO.setNotGrounded(jObj.getString("NOTGROUNDED"));
			 	    				nregsDataVO.setInProgress(jObj.getLong("INPROGRESS"));
			 	    				nregsDataVO.setCompleted(jObj.getLong("COMPLETED"));
			 	    				nregsDataVO.setPercentage(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());	 	    				
			 	    				list.add(nregsDataVO);	 
			 	    			}
		 	    			}
		 	    		}
		 	    	}
		 	    	
		 	    	list = getDistrictsConstitByType(list,inputVO.getType());
		 	    	Map<String,NregsDataVO> distConstMap = new LinkedHashMap<String,NregsDataVO>();
		 	    	
		 	    	if(list != null && !list.isEmpty()){
		 	    		 for (NregsDataVO nregsDataVO : list) {
		 	    			 NregsDataVO filterVo = new NregsDataVO();
		 	    			 filterVo.setPercentage(nregsDataVO.getPercentage());
							distConstMap.put(nregsDataVO.getDistrict(),filterVo);
						}
		 	    	 }
		 	    	List<NregsDataVO> disConslist = null;
		 	    	List<NregsDataVO> disMandallist = null;
		 	    	List<NregsDataVO> consMandallist = null;
		 	    	if(inputVO.getLocationType().trim().equalsIgnoreCase("district")){
		 	    		inputVO.setLocationType("constituency");
			 	    	ClientResponse constResponse = webServiceUtilService.callWebService(webServiceUrl.toString(), inputVO);
			 	    	if(constResponse.getStatus() != 200){
				 	    	  throw new RuntimeException("Failed : HTTP error code : "+ constResponse.getStatus());
				 	      }else{
				 	    	 String constOutput = constResponse.getEntity(String.class);
				 	    	 
				 	    	 disConslist = getNregsConstCuntDetails(constOutput,distConstMap,inputVO.getDivType());
				 	      }
			 	    	
			 	    	distConstMap.clear();
			 	    	if(list != null && !list.isEmpty()){
			 	    		 for (NregsDataVO nregsDataVO : list) {
			 	    			 NregsDataVO filterVo = new NregsDataVO();
			 	    			 filterVo.setPercentage(nregsDataVO.getPercentage());
								distConstMap.put(nregsDataVO.getDistrict(),filterVo);
							}
			 	    	 }
			 	    	inputVO.setLocationType("mandal");
			 	    	ClientResponse mandalResponse = webServiceUtilService.callWebService(webServiceUrl.toString(), inputVO);
			 	    	if(mandalResponse.getStatus() != 200){
				 	    	  throw new RuntimeException("Failed : HTTP error code : "+ mandalResponse.getStatus());
				 	      }else{
				 	    	 String mandalOutput = mandalResponse.getEntity(String.class);
				 	    	 
				 	    	  disMandallist = getNregsMandalsCuntFrDistrict(mandalOutput,distConstMap,inputVO.getDivType());
				 	      }
			 	    	
			 	    	finalVO.getDistConsCuntList().addAll(disConslist);
			 	    	finalVO.getDistMandalCuntList().addAll(disMandallist);
			 	    	finalVO.getDistList().addAll(list);
		 	    	}else if(inputVO.getLocationType().trim().equalsIgnoreCase("constituency")){
		 	    		Map<String,NregsDataVO> constMandMap = new LinkedHashMap<String,NregsDataVO>();
			 	    	
			 	    	if(list != null && !list.isEmpty()){
			 	    		 for (NregsDataVO nregsDataVO : list) {
			 	    			 NregsDataVO filterVo = new NregsDataVO();
			 	    			 filterVo.setPercentage(nregsDataVO.getPercentage());
			 	    			constMandMap.put(nregsDataVO.getConstituency(),filterVo);
							}
			 	    	 }
			 	    	
		 	    		inputVO.setLocationType("mandal");
			 	    	ClientResponse mandalResponse = webServiceUtilService.callWebService(webServiceUrl.toString(), inputVO);
			 	    	if(mandalResponse.getStatus() != 200){
				 	    	  throw new RuntimeException("Failed : HTTP error code : "+ mandalResponse.getStatus());
				 	      }else{
				 	    	 String mandalOutput = mandalResponse.getEntity(String.class);
				 	    	 
				 	    	consMandallist = getNregsMandalsCuntFrConstituncies(mandalOutput,constMandMap,inputVO.getDivType());
				 	      }
			 	    	
			 	    	finalVO.getDistMandalCuntList().addAll(consMandallist);
			 	    	finalVO.getDistList().addAll(list);
		 	    	  }else if(inputVO.getLocationType().trim().equalsIgnoreCase("mandal")){
			 	    		finalVO.getDistList().addAll(list);
			 	    }  
	 	      }
		} catch (Exception e) {
			LOG.error("Exception raised at getMGNregsDistrWiseConsti -NREGSTCSService service", e);
		}
		
		return finalVO;
	}
	
	/*
	 * Date : 23/06/2017
	 * Author :Nandhini
	 * @description : getNregsMandalsCuntFrDistrict 
	 */

	@Override
	public List<NregsDataVO> getNregsMandalsCuntFrDistrict(String output,Map<String,NregsDataVO> cntMap,String divType){
		List<NregsDataVO> retVOList = new ArrayList<>(0);
		try{
			//Map<String,NregsDataVO> cntMap = new HashMap<String,NregsDataVO>(0);
			String percValue = null;
			if(output != null && !output.isEmpty()){
	    		JSONArray finalArray = new JSONArray(output);
	    		if(finalArray!=null && finalArray.length()>0){
	    			for(int i=0;i<finalArray.length();i++){
	    				JSONObject jObj = (JSONObject) finalArray.get(i);
		    			String distrName = jObj.getString("DISTRICT");
						NregsDataVO vo = cntMap.get(distrName);
						if(divType != null && divType.trim().equalsIgnoreCase("Labour Budget")){
							percValue = new BigDecimal(jObj.getString("PER_APP_LB")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else{
							percValue = new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						} 
		    				if(vo != null) {
			    				if(Double.valueOf(percValue)  < 50){
		    						vo.setMandalsInRed(vo.getMandalsInRed()+1l);
		    					}else if(Double.valueOf(percValue)  >50 && Double.valueOf(percValue) <80){
		    						vo.setMandalsInOrange(vo.getMandalsInOrange()+1l);
	    						}else if(Double.valueOf(percValue)  >80){
		    						vo.setMandalsInGreen(vo.getMandalsInGreen()+1l);
		    					}
			    				vo.setTotal(vo.getTotal()+1l);
			    				vo.setDistrict(distrName);
			    			}
	    				}   	    							
	    			}
    			}
			
					if(cntMap != null){
						retVOList = new ArrayList<NregsDataVO>(cntMap.values());
					}
								
				}catch(Exception e){
					LOG.error("Exception raised at getNregsMandalsCuntFrDistrict -NREGSTCSService service", e);
				}
			return retVOList;
	 }
	
	/*
	 * Date : 23/06/2017
	 * Author :Nandhini
	 * @description : getNregsMandalsCuntFrConstituncies 
	 */

	@Override
	public List<NregsDataVO> getNregsMandalsCuntFrConstituncies(String output,Map<String,NregsDataVO> cntMap,String divType){
		List<NregsDataVO> retVOList = new ArrayList<>(0);
		try{
			//Map<String,NregsDataVO> cntMap = new HashMap<String,NregsDataVO>(0);
			String percValue = null;
			if(output != null && !output.isEmpty()){
	    		JSONArray finalArray = new JSONArray(output);
	    		if(finalArray!=null && finalArray.length()>0){
	    			for(int i=0;i<finalArray.length();i++){
	    				JSONObject jObj = (JSONObject) finalArray.get(i);
		    			String constName = jObj.getString("CONSTITUENCY");
						NregsDataVO vo = cntMap.get(constName);
						if(divType != null && divType.trim().equalsIgnoreCase("Labour Budget")){
							percValue = new BigDecimal(jObj.getString("PER_APP_LB")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else{
							percValue = new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						} 
		    				if(vo != null)
		    				{
			    				if(Double.valueOf(percValue)  < 50){
		    						vo.setMandalsInRed(vo.getMandalsInRed()+1l);
		    					}else if(Double.valueOf(percValue)  >50 && Double.valueOf(percValue) <80){
		    						vo.setMandalsInOrange(vo.getMandalsInOrange()+1l);
	    						}else if(Double.valueOf(percValue)  >80){
		    						vo.setMandalsInGreen(vo.getMandalsInGreen()+1l);
		    					}
			    				vo.setTotal(vo.getTotal()+1l);
			    				vo.setDistrict(constName);
			    			}
	    				}   	    							
	    			}
    			}
			
					if(cntMap != null){
						retVOList = new ArrayList<NregsDataVO>(cntMap.values());
					}
								
				}catch(Exception e){
					LOG.error("Exception raised at getNregsMandalsCuntFrConstituncies -NREGSTCSService service", e);
				}
			return retVOList;
	 }
		

	/*
	 * Date : 23/06/2017
	 * Author :Swapna
	 * @description : getDistrictsConstitByType
	 */
   
	@Override
	public List<NregsDataVO> getDistrictsConstitByType(List<NregsDataVO> returnList,String type)
	{
		
	try{
		List<NregsDataVO> filterList = new ArrayList<NregsDataVO>();
		if(returnList != null && !returnList.isEmpty()){
    		for (NregsDataVO nregsDataVO : returnList) {
			if(type != null && !type.trim().equalsIgnoreCase("total")){
				if(type.trim().equalsIgnoreCase("red") && Double.valueOf(nregsDataVO.getPercentage()) < 50)
					filterList.add(nregsDataVO);
				else if(type.trim().equalsIgnoreCase("orange") && Double.valueOf(nregsDataVO.getPercentage()) < 80 && Double.valueOf(nregsDataVO.getPercentage()) > 50)
					filterList.add(nregsDataVO);
				else if(type.trim().equalsIgnoreCase("green") && Double.valueOf(nregsDataVO.getPercentage()) > 80)
					filterList.add(nregsDataVO);
				}
		     }
    	   }
			   	if(filterList != null && !filterList.isEmpty()){
		    		returnList.clear();
		    		returnList.addAll(filterList);
		    	}
			}
	   	catch (Exception e) {
			LOG.error("Exception raised at getDistrictsConstitByType -getDistrictsDetails service", e);
		}
		return returnList;
		}
	/*
	 * Date : 19/06/2017
	 * Author :Swadhin
	 * @description : returns webservice details
	 */
	@Override
	public List<WebserviceDetailsVO> getWebserviceHealthDetails(InputVO inputVO){
		try{
			Date startDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getFromDate(),"dd/MM/yyyy","");
			Date endDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getToDate(),"dd/MM/yyyy","");
			//get webservice details here 
			List<Object[]> detailsList = webserviceCallDetailsDAO.getWebserviceHealthDetails(startDate, endDate);
			//create a map for webserviceId and providerId
			Map<Long,Long> webserviceIdAndProviderIdMap = new HashMap<Long,Long>();
			//create a map for providerId and providerName map
			Map<Long,String> providerIdAndProviderNameMap = new HashMap<Long,String>();      
			//create a map for webserviceId and moduleId
			Map<Long,Long> webserviceIdAndModuleIdMap = new HashMap<Long,Long>();
			//create a map for moduleId and module name map
			Map<Long,String> moduleIdAndModuleNameMap = new HashMap<Long,String>();
			//create a map for webserviceId and name
			Map<Long,String> webserviceIdAndNameMap = new HashMap<Long,String>();
			//create a map for webserviceId and total call count map
			Map<Long,Long> webserviceIdAneTotalCallCountMap = new HashMap<Long,Long>();
			//create a map for webserviceId and total time taken map
			Map<Long,Long> webserviceIdAndTotalTimeTakenMap = new HashMap<Long,Long>();
			//create  a map for webserviceId and Map for status and total call count map
			Map<Long,Map<String,Long>> webserviceIdAndStatusAndTotalCallCountMap = new HashMap<Long,Map<String,Long>>();
			Map<String,Long> statusAndTotalCallCountMap = null;
			//create a map for webserviceId and Map for status and total time taken map
			Map<Long,Map<String,Long>> webserviceIdAndStatusAndTotalTimeTakenMap = new HashMap<Long,Map<String,Long>>();
			Map<String,Long> statusAndTotalTimeTakenMap = null;
			
			if(detailsList != null && detailsList.size() > 0){
				for(Object[] param : detailsList){
					//create a map for webserviceId and providerId
					webserviceIdAndProviderIdMap.put(commonMethodsUtilService.getLongValueForObject(param[4]), commonMethodsUtilService.getLongValueForObject(param[0]));
					
					//create a map for providerId and providerName map
					providerIdAndProviderNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
					
					//create a map for webserviceId and moduleId
					webserviceIdAndModuleIdMap.put(commonMethodsUtilService.getLongValueForObject(param[4]), commonMethodsUtilService.getLongValueForObject(param[2]));
					
					//create a map for moduleId and module name map
					moduleIdAndModuleNameMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getStringValueForObject(param[3]));
					
					//create a map for webserviceId and name
					webserviceIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[4]), commonMethodsUtilService.getStringValueForObject(param[5]));
					
					//create a map for webserviceId and total call count map
					webserviceIdAneTotalCallCountMap.put(commonMethodsUtilService.getLongValueForObject(param[4]), (commonMethodsUtilService.getLongValueForObject(webserviceIdAneTotalCallCountMap.get(commonMethodsUtilService.getLongValueForObject(param[4])))+commonMethodsUtilService.getLongValueForObject(param[7])));
					
					//create a map for webserviceId and total time taken map
					webserviceIdAndTotalTimeTakenMap.put(commonMethodsUtilService.getLongValueForObject(param[4]), (commonMethodsUtilService.getLongValueForObject(webserviceIdAndTotalTimeTakenMap.get(commonMethodsUtilService.getLongValueForObject(param[4])))+commonMethodsUtilService.getLongValueForObject(param[8])));
					
					//create  a map for webserviceId and Map for status and total call count map
					statusAndTotalCallCountMap = webserviceIdAndStatusAndTotalCallCountMap.get(commonMethodsUtilService.getLongValueForObject(param[4]));
					if(statusAndTotalCallCountMap == null){
						statusAndTotalCallCountMap = new HashMap<String,Long>();
						statusAndTotalCallCountMap.put(commonMethodsUtilService.getStringValueForObject(param[6]), commonMethodsUtilService.getLongValueForObject(param[7]));
						webserviceIdAndStatusAndTotalCallCountMap.put(commonMethodsUtilService.getLongValueForObject(param[4]), statusAndTotalCallCountMap);
					}
					statusAndTotalCallCountMap.put(commonMethodsUtilService.getStringValueForObject(param[6]), commonMethodsUtilService.getLongValueForObject(param[7]));
					
					//create a map for webserviceId and Map for status and total time taken map
					statusAndTotalTimeTakenMap = webserviceIdAndStatusAndTotalTimeTakenMap.get(commonMethodsUtilService.getLongValueForObject(param[4]));
					if(statusAndTotalTimeTakenMap == null){
						statusAndTotalTimeTakenMap = new HashMap<String,Long>();
						statusAndTotalTimeTakenMap.put(commonMethodsUtilService.getStringValueForObject(param[6]), commonMethodsUtilService.getLongValueForObject(param[8]));
						webserviceIdAndStatusAndTotalTimeTakenMap.put(commonMethodsUtilService.getLongValueForObject(param[4]), statusAndTotalTimeTakenMap);
					}
					statusAndTotalTimeTakenMap.put(commonMethodsUtilService.getStringValueForObject(param[6]), commonMethodsUtilService.getLongValueForObject(param[8]));
					
				}
			}
			
			//create VO object for UI
			List<WebserviceDetailsVO> detailsVOs = new ArrayList<WebserviceDetailsVO>();
			WebserviceDetailsVO detailsVO = null;
			
			if(webserviceIdAndStatusAndTotalCallCountMap != null && webserviceIdAndStatusAndTotalCallCountMap.size() > 0){
				for(Entry<Long,Map<String,Long>> param : webserviceIdAndStatusAndTotalCallCountMap.entrySet()){
					if(param.getValue() != null && param.getValue().size() > 0){
						detailsVO = new WebserviceDetailsVO();
						detailsVO.setWebserviceId(param.getKey());
						detailsVO.setWebserviceName(webserviceIdAndNameMap.get(param.getKey()));
						detailsVO.setProviderId(webserviceIdAndProviderIdMap.get(param.getKey()));
						detailsVO.setProviderName(providerIdAndProviderNameMap.get(webserviceIdAndProviderIdMap.get(param.getKey())));
						detailsVO.setModuleId(webserviceIdAndModuleIdMap.get(param.getKey()));
						detailsVO.setModuleName(moduleIdAndModuleNameMap.get(webserviceIdAndModuleIdMap.get(param.getKey())));
						detailsVO.setTotalCalls(webserviceIdAneTotalCallCountMap.get(param.getKey()));
						if(param.getValue() != null){
							if(param.getValue().get("Success") != null){
								detailsVO.setTotalSuccess(param.getValue().get("Success"));
							}
							if(param.getValue().get("Fail") != null){
								detailsVO.setTotalFail(param.getValue().get("Fail"));
							}
						}
						detailsVO.setTotalTime(commonMethodsUtilService.roundUptoThreeDecimalPoint(webserviceIdAndTotalTimeTakenMap.get(param.getKey()).doubleValue()/1000.0D));
						detailsVO.setAverageTime(commonMethodsUtilService.roundUptoThreeDecimalPoint((webserviceIdAndTotalTimeTakenMap.get(param.getKey()).doubleValue()/webserviceIdAneTotalCallCountMap.get(param.getKey()).doubleValue())/1000.0D));
						detailsVOs.add(detailsVO);
					}
				}
			}
			return detailsVOs;
		}catch(Exception e){   
			LOG.error("Exception raised at getWebserviceHealthDetails() -NREGSTCSService service", e);
		}
		return null;
	}
}
