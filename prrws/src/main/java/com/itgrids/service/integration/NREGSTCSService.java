package com.itgrids.service.integration;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dto.FarmPondOverviewVO;

import com.google.gson.JsonObject;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.NregsDataVO;
import com.itgrids.dto.NregsOverviewVO;
import com.itgrids.dto.LabourBudgetOverViewVO;
import com.itgrids.dto.NregsDataVO;
import com.itgrids.dto.NregsProjectsVO;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.itgrids.service.integration.impl.INREGSTCSService;
import com.sun.jersey.api.client.ClientResponse;

@Service
@Transactional
public class NREGSTCSService implements INREGSTCSService{

	private static final Logger LOG = Logger.getLogger(NREGSTCSService.class);
	
	@Autowired
	private WebServiceUtilService webServiceUtilService;
	
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
	 	    				vo.setPercentage(jObj.getString("PERCENTAGE"));
	 	    				
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
	 	    		
	 	    		returnvo.setTotalBudget(jObj.getString("TOTALBUDGET"));
	 	    		returnvo.setTotalLabour(jObj.getLong("TOTALLABOUR"));
	 	    		returnvo.setMaleLabour(jObj.getLong("MALELABOUR"));
	 	    		returnvo.setFemaleLabour(jObj.getLong("FEMALELABOUR"));
	 	    		returnvo.setTargettedPersonDays(jObj.getLong("TARGETTEDPERSONDAYS"));
	 	    		returnvo.setGeneratedPersonDays(jObj.getLong("GENERATEDPERSONDAYS"));
	 	    		returnvo.setTotalExpenditure(jObj.getString("TOTALEXPENDITURE"));
	 	    		returnvo.setAvgWagePerPerson(jObj.getString("AVGWAGEPERPERSON"));
	 	    		returnvo.setTotalResponse(jObj.getLong("TOTALRESPONSE"));
	 	    		returnvo.setOnRequestWorkAllocated(jObj.getLong("ONREQUESTWORKALLOCATED"));
	 	    		returnvo.setGotWorkOccutionally(jObj.getLong("GOTWORKOCCUTIONALLY"));
    				returnvo.setHaveNotGotWork(jObj.getLong("HAVENOTGOTWORK"));
    				returnvo.setBudgetForLabour(jObj.getString("BUDGETFORLABOUR"));
    				returnvo.setBudgetForMaterial(jObj.getString("BUDGETFORMATERIAL"));
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
	 	    				vo.setId(jObj.getLong("RANGE"));
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
 	    				//FarmPondOverviewVO 	farmpondoverviewvO =new FarmPondOverviewVO(); 	    				
 	    				farmpondoverviewvO.setAveragePerDistrict(jObj.getString("AVERAGEPERDISTRICT"));
 	    				farmpondoverviewvO.setAveragePerConstituency(jObj.getString("AVERAGEPERCONSTITUENCY"));
 	    				farmpondoverviewvO.setAveragePerMandal(jObj.getString("AVERAGEPERMANDAL"));
 	    				farmpondoverviewvO.setConstituenciesInGreen(jObj.getLong("TOTALBUDGET"));
 	    				farmpondoverviewvO.setConstituenciesInOrgange(jObj.getLong("CONSTITUENCIESINORANGE"));
 	    				farmpondoverviewvO.setConstituenciesInRed(jObj.getLong("CONSTITUENCIESINRED"));
 	    				farmpondoverviewvO.setDistrictsInGreen(jObj.getLong("DISTRICTSINGREEN"));
 	    				farmpondoverviewvO.setDistrictsInOrgange(jObj.getLong("DISTRICTSINORANGE"));
 	    				farmpondoverviewvO.setDistrictsInRed(jObj.getLong("DISTRICTSINRED"));
 	    				farmpondoverviewvO.setMandalsInGreen(jObj.getLong("MANDALSINGREEN"));
 	    				farmpondoverviewvO.setMandalsInOrgange(jObj.getLong("MANDALSINORANGE"));
 	    				farmpondoverviewvO.setMandalsInRed(jObj.getLong("MANDALSINRED"));
 	    				farmpondoverviewvO.setTotalAvgFarmsInConstituency(jObj.getString("TOTALAVGFARMSINCONSTITUENCY"));
 	    				farmpondoverviewvO.setTotalAvgFarmsInDistrict(jObj.getString("TOTALAVGFARMSINDISTRICT"));
 	    				farmpondoverviewvO.setTotalBudget(jObj.getLong("TOTALBUDGET"));
 	    				farmpondoverviewvO.setTotalDistricts(jObj.getLong("TOTALDISTRICTS"));
 	    				farmpondoverviewvO.setTotalMandals(jObj.getLong("TOTALMANDALS"));
 	    				farmpondoverviewvO.setTotalVillages(jObj.getLong("TOTALVILLAGES") );
 	    				farmpondoverviewvO.setVillagesInGreen(jObj.getLong("VILLAGESINGREEN"));
 	    				farmpondoverviewvO.setVillagesInOrgange(jObj.getLong("VILLAGESINORANGE"));
 	    				farmpondoverviewvO.setVillagesInRed(jObj.getLong("VILLAGESINRED"));
 	    				farmpondoverviewvO.setTotalAvgFarmsInMandal(jObj.getString("TOTALAVGFARMSINMANDAL"));
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
	 	    				nregsDataVO.setPercentage(jObj.getString("PERCENTAGE"));	 	    				
	 	    				list.add(nregsDataVO);	 	    							
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
	 	    				finalVO.setAveragePerDistrict(Obj.getString("AVERAGEPERDISTRICT"));
	 	    				finalVO.setAveragePerConstituency(Obj.getString("AVERAGEPERCONSTITUENCY"));
	 	    				finalVO.setAveragePerMandal(Obj.getString("AVERAGEPERMANDAL"));
	 	    				finalVO.setTotalBudget(Obj.getLong("TOTALBUDGET"));
	 	    				finalVO.setTotalAvgFarmsInDistrict(Obj.getString("TOTALAVGFARMSINDISTRICT"));
	 	    				finalVO.setTotalAvgFarmsInConstituency(Obj.getString("TOTALAVGFARMSINCONSTITUENCY"));
	 	    				finalVO.setTotalAvgFarmsInMandal(Obj.getString("TOTALAVGFARMSINMANDAL"));
	 	    				finalVO.setDistrictsInRed(Obj.getLong("DISTRICTSINRED"));
	 	    				finalVO.setDistrictsInOrgange(Obj.getLong("DISTRICTSINORANGE"));
	 	    				finalVO.setDistrictsInGreen(Obj.getLong("DISTRICTSINGREEN"));
	 	    				finalVO.setTotalDistricts(Obj.getLong("TOTALDISTRICTS"));
	 	    				finalVO.setConstituenciesInRed(Obj.getLong("CONSTITUENCIESINRED"));
	 	    				finalVO.setConstituenciesInOrgange(Obj.getLong("CONSTITUENCIESINORANGE"));
	 	    				finalVO.setConstituenciesInGreen(Obj.getLong("CONSTITUENCIESINGREEN"));
	 	    				finalVO.setTotalConstituencies(Obj.getLong("TOTALCONSTITUENCIES"));
	 	    				finalVO.setMandalsInRed(Obj.getLong("MANDALSINRED"));
	 	    				finalVO.setMandalsInOrgange(Obj.getLong("MANDALSINORANGE"));
	 	    				finalVO.setMandalsInGreen(Obj.getLong("MANDALSINGREEN"));
	 	    				finalVO.setTotalMandals(Obj.getLong("TOTALMANDALS"));
	 	    				finalVO.setVillagesInRed(Obj.getLong("VILLAGESINRED"));
	 	    				finalVO.setVillagesInOrgange(Obj.getLong("VILLAGESINORANGE"));
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
	 	    				vo.setPercentage(jObj.getString("PERCENTAGE"));
	 	    				
	 	    				voList.add(vo);
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
	 	    				vo.setPerAppLB(jObj.getString("PER_APP_LB"));
	 	    				vo.setAvgWageRate(jObj.getString("AVGWAGERATE"));
	 	    				vo.setTotalExpenditure(jObj.getString("TOTALEXPENDITURE"));
	 	    				//vo.setPercentage(jObj.getString("PERCENTAGE"));
	 	    				
	 	    				voList.add(vo);
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
	 	                 
	 	                returnVo.setAveragePerDistrict(jObj.getString("AVERAGEPERDISTRICT"));
	 	                returnVo.setAveragePerConstituency(jObj.getString("AVERAGEPERCONSTITUENCY"));
	 	                returnVo.setAveragePerMandal(jObj.getString("AVERAGEPERMANDAL"));
	 	                returnVo.setTotalBudget(jObj.getLong("TOTALBUDGET"));
	 	                returnVo.setTotalAvgFarmsInDistrict(jObj.getString("TOTALAVGFARMSINDISTRICT"));
	 	                returnVo.setTotalAvgFarmsInConstituency(jObj.getString("TOTALAVGFARMSINCONSTITUENCY"));
	 	                returnVo.setTotalAvgFarmsInMandal(jObj.getString("TOTALAVGFARMSINMANDAL"));
	 	                returnVo.setDistrictsInRed(jObj.getLong("DISTRICTSINRED"));
	 	                returnVo.setDistrictsInOrgange(jObj.getLong("DISTRICTSINORANGE"));
	 	                returnVo.setDistrictsInGreen(jObj.getLong("DISTRICTSINGREEN"));
	 	                returnVo.setTotalDistricts(jObj.getLong("TOTALDISTRICTS"));
	 	                returnVo.setConstituenciesInRed(jObj.getLong("CONSTITUENCIESINRED"));
	 	                returnVo.setConstituenciesInOrgange(jObj.getLong("CONSTITUENCIESINORANGE"));
	 	                returnVo.setConstituenciesInGreen(jObj.getLong("CONSTITUENCIESINGREEN"));
	 	                returnVo.setTotalConstituencies(jObj.getLong("TOTALCONSTITUENCIES"));
	 	                returnVo.setMandalsInRed(jObj.getLong("MANDALSINRED"));
	 	                returnVo.setMandalsInOrgange(jObj.getLong("MANDALSINORANGE"));
	 	                returnVo.setMandalsInGreen(jObj.getLong("MANDALSINGREEN"));
	 	                returnVo.setTotalMandals(jObj.getLong("TOTALMANDALS"));
	 	                returnVo.setVillagesInRed(jObj.getLong("VILLAGESINRED"));
	 	                returnVo.setVillagesInOrgange(jObj.getLong("VILLAGESINORANGE"));
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
	 	    				vo.setPercentage(jObj.getString("PERCENTAGE"));
	 	    				
	 	    				returnList.add(vo);
	 	    			}
	 	    		}
	 	    	}
	 	     }
		} catch (Exception e) {
			LOG.error("Exception raised at getNregsVermiData - NREGSTCSService service", e);
		}
		return returnList;
	}
}
