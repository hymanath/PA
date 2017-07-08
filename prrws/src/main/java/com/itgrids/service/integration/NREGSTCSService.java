package com.itgrids.service.integration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import com.itgrids.dao.IConstituencyDAO;
import com.itgrids.dao.IParliamentAssemblyDAO;
import com.itgrids.dao.IWebserviceCallDetailsDAO;
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
	@Autowired
	private IConstituencyDAO constituencyDAO;
	@Autowired
	private IParliamentAssemblyDAO parliamentAssemblyDAO;
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
	 	    				/*if(vo.getParameter() != null && vo.getParameter().equalsIgnoreCase("Labour Budget")){
	 	    					vo.setTarget(new BigDecimal(Double.valueOf(vo.getTarget())/10000000.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    					vo.setTarget(vo.getTarget()+" Cr");
	 	    					vo.setCompleted(new BigDecimal(Double.valueOf(vo.getCompleted())/10000000.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    					vo.setCompleted(vo.getCompleted()+" Cr");
	 	    				}*/
	 	    				
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
	 	    		returnvo.setAchievementPerc(new BigDecimal(jObj.getLong("GENERATEDPERSONDAYS")*100.0/jObj.getLong("TARGETTEDPERSONDAYS")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
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
	 	    	 
	 	    	 Long totalCount = 0l;
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				IdNameVO vo = new IdNameVO();
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				vo.setName(jObj.getString("RANGE"));
	 	    				vo.setCount(jObj.getLong("GPSCOUNT"));
	 	    				totalCount = totalCount+vo.getCount();
	 	    				
	 	    				voList.add(vo);
	 	    			}
	 	    		}
	 	    		
	 	    		if(voList != null && !voList.isEmpty()){
	 	    			for (IdNameVO vo : voList) {
	 	    				if(vo.getCount() != null && vo.getCount() > 0)
	 	    					vo.setTotl(new BigDecimal(vo.getCount()*100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				else
	 	    					vo.setTotl("0.00");
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
	 * Date : 29/06/2017
	 * Author :Sravanth
	 * @description : getNregasOverview
	 */
	public NregsOverviewVO getNregasOverview(InputVO inputVO){
		NregsOverviewVO finalVO = new NregsOverviewVO();
		try {
			String webServiceUrl = null;
			
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Farm Ponds"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FarmPondService_new/FarmPondOverview_new";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FarmPondService/FarmPondOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("IHHL"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/IHHLService_new/IHHLOverview_new";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/IHHLService/IHHLOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Vermi Compost"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/VermiService_new/VermiOverview_new";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/VermiService/VermiOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("GP Buildings"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/GPBuildingService/GPBuildingOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR Jala Siri"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NtrsService/NtrsOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("CC Roads"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CCRoadsService/CCRoadsOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Anganwadi Buildings"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AnganwadiService/AnganwadiOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mandal Buildings"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MandalBuildingService/MandalBuildingOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR 90 Days"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HousingService/HousingOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Production of Bricks"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BricksService/BricksOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SericultureService/SericultureOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Silk Worms"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SilkwormService/SilkwormOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Cattle Drinking Water Troughs"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AHService/AHOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Raising of Perinnial Fodders"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FodderService/FodderOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Solid Waste Management"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SolidWasteManagementServices/SolidWasteManagementOverview";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SWMService/SWMOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Play Fields"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/PlayFieldsServices/PlayFieldsOverview";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/PlayFields/PlayFieldsOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Burial Grounds"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BurialGroundsServices/BurialGroundsOverview";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BurialService/BurialOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Agriculture Activities"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AgricultureServices/AgricultureOverview";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AgriService/AgriOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Average Wage"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AverageWageService/AvgWageOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Average Days of Employment"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AverageDaysService/AvgDaysOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("HH Completed 100 Days"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AP100DaysService/AP100DaysOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Timely Payment"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/TimePaymentService/TPOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Nurseries"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NurseriesService/NurseriesOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Horticulture"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HorticultureService/HorticultureOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Avenue"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AvenueService/AvenueOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Drying Platforms"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FishDryingPlatformService/FishDryingPlatformOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Ponds"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FishPondsService/FishPondsOverview";
			 
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject Obj = new JSONObject(output);
	 	    		if(Obj!=null && Obj.length()>0){
	 	    			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Average Wage") || 
 	    					inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Average Days of Employment") || 
 	    					inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("HH Completed 100 Days") || 
 	    					inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Timely Payment") || 
 	    					inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Horticulture") ||
 	    					inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Avenue") ||
 	    					inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Nurseries")){
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
	 	    			}else{
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
	 	    }
	 	    	 
		} catch (Exception e) {
			LOG.error("Exception raised at getNregasOverview - NREGSTCSService service", e);
		}
		
		return finalVO;
	}
	
	/*
	 * Date : 28/06/2017
	 * Author :Sravanth
	 * @description : getNregaLevelsWiseData
	 */
	public List<NregsDataVO> getNregaLevelsWiseData(InputVO inputVO){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
			if(inputVO.getSublocaType().trim() != null && inputVO.getSublocaType().trim().toString().length() > 0l)
				inputVO.setSublocationType(inputVO.getSublocaType().trim());
			
			String webServiceUrl = null;
			
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Farm Ponds"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FarmPondService_new/FarmPondData_new";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FarmPondService/FarmPondData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("IHHL"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/IHHLService_new/IHHLData_new";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/IHHLService/IHHLData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Vermi Compost"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/VermiService_new/VermiData_new";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/VermiService/VermiData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("GP Buildings"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/GPBuildingService/GPBuildingData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR Jala Siri"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NtrsService/NtrsData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Anganwadi Buildings"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AnganwadiService/AnganwadiData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mandal Buildings"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MandalBuildingService/MandalBuildingData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR 90 Days"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HousingService/HousingData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Production of Bricks"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BricksService/BricksData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SericultureService/SericultureData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Silk Worms"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SilkwormService/SilkwormData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Cattle Drinking Water Troughs"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AHService/AHData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Raising of Perinnial Fodders"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FodderService/FodderData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Solid Waste Management"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SWMService/SWMData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Play Fields"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/PlayFieldsServices/PlayFieldsData";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/PlayFields/PlayFieldsData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Burial Grounds"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BurialGroundsServices/BurialGroundsData";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BurialService/BurialData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Agriculture Activities"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AgricultureServices/AgricultureData";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AgriService/AgriData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Drying Platforms"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FishDryingPlatformService/FishDryingPlatformData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Ponds"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FishPondsService/FishPondsData";
			 
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), inputVO);
	        
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
	 	    				//vo.setUniqueId(Long.valueOf((jObj.getString("UNIQUEID") != " " ? jObj.getString("UNIQUEID") : "1").toString()));
	 	    				vo.setUniqueId(Long.valueOf((jObj.getString("UNIQUEID").toString().trim().length() > 0 ? jObj.getString("UNIQUEID") : "1").toString()));
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
	 	    				if((inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery")
	 	    						|| inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Silk worm")
	 	    						|| inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Cattle drinking water trough")
	 	    						|| inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Raising of Perinnial Fodder"))
	 	    							&& inputVO.getLocationType().trim().toString().equalsIgnoreCase("state")){
	 	    					vo.setSanctionedTarget(jObj.getString("SANCTIONEDTARGET"));
	 	    					vo.setSanctionedPerventage(jObj.getString("SANCTIONEDPERCENTAGE"));
	 	    				}
	 	    				
	 	    				if((inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Ponds")
	 	    						|| inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Drying Platforms"))
	 	    						&& (inputVO.getLocationType().trim().toString().equalsIgnoreCase("state")
	 	    								|| inputVO.getLocationType().trim().toString().equalsIgnoreCase("district"))){
	 	    					vo.setSanctionedTarget(jObj.getString("SANCTIONEDTARGET"));
	 	    					vo.setSanctionedPerventage(jObj.getString("SANCTIONEDPERCENTAGE"));
	 	    				}
	 	    				
	 	    				voList.add(vo);
	 	    			}
	 	    		}
	 	    	}
	 	      }
	    } catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseData - NREGSTCSService service", e);
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
	 	    				vo.setWageExpenditure(jObj.getString("WAGEEXPENDITURE"));
	 	    				vo.setMaterialExpenditure(jObj.getString("MATERIALEXPENDITURE"));
	 	    				vo.setTotalExpenditure(jObj.getString("TOTALEXPENDITURE"));
	 	    				vo.setMaterialExpenditurePerc(jObj.getString("MATPERCENTAGE"));
	 	    				//vo.setPercentage(new BigDecimal(jObj.getString("PER_APP_LB")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
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
	 * Date : 22/06/2017
	 * Author :Nandhini
	 * @description : getNregsConstCuntDetails 
	 */

	@Override
	public List<NregsDataVO> getNregsConstCuntDetails(String output,Map<String,NregsDataVO> cntMap,String divType){
		List<NregsDataVO> retVOList = new ArrayList<NregsDataVO>(0);
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
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("Agriculture Activities")){
							percValue = new BigDecimal(jObj.getString("ACHEIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("Horticulture") || divType.trim().toString().equalsIgnoreCase("Avenue")){
							percValue = new BigDecimal(jObj.getString("PERCENTAGEOFPLANTING")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
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
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Farm Ponds"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FarmPondService/FarmPondData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("IHHL"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/IHHLService/IHHLData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Vermi Compost"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/VermiService/VermiData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("GP Buildings"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/GPBuildingService/GPBuildingData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR Jala Siri"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NtrsService/NtrsData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("CC Roads"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CCRoadsService/CCRoadsData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Anganwadi Buildings"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AnganwadiService/AnganwadiData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mandal Buildings"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MandalBuildingService/MandalBuildingData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR 90 Days"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HousingService/HousingData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Production of Bricks"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BricksService/BricksData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SericultureService/SericultureData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Silk Worms"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SilkwormService/SilkwormData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Cattle Drinking Water Troughs"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AHService/AHData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Raising of Perinnial Fodders"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FodderService/FodderData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Solid Waste Management"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SWMService/SWMData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Play Fields"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/PlayFields/PlayFieldsData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Burial Grounds"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BurialService/BurialData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Agriculture Activities"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AgriService/AgriData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Average Wage"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AverageWageService/AvgWageData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Average Days of Employment"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AverageDaysService/AvgDaysData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("HH Completed 100 Days"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AP100DaysService/AP100DaysData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Timely Payment"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/TimePaymentService/TPData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Horticulture"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HorticultureService/HorticultureData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Avenue"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AvenueService/AvenueData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Nurseries"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NurseriesService/NurseriesData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Drying Platforms"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FishDryingPlatformService/FishDryingPlatformData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Ponds"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FishPondsService/FishPondsData";
			
			 
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
		 	    			}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Average Wage") || 
		 	    					inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Avg days of emp per HH") ||
		 	    					inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("HH Comp 100 days") || 
		 	    					inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Timely Payments") ||
		 	    					inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Nurseries")){
			 	    				for(int i=0;i<finalArray.length();i++){
				 	    				NregsDataVO vo = new NregsDataVO();
				 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
				 	    				vo.setUniqueId(jObj.getLong("UNIQUEID"));
				 	    				vo.setDistrict(jObj.getString("DISTRICT"));
				 	    				vo.setConstituency(jObj.getString("CONSTITUENCY"));
				 	    				vo.setMandal(jObj.getString("MANDAL"));
				 	    				vo.setPanchayat(jObj.getString("PANCHAYAT"));
				 	    				vo.setTarget(jObj.getLong("TARGET"));
				 	    				vo.setAchivement(new BigDecimal(jObj.getString("ACHIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				 	    				vo.setPercentage(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				 	    				list.add(vo);
				 	    		}
		 	    			}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Agriculture Activities")){
		 	    				for(int i=0;i<finalArray.length();i++){
				 	    				NregsDataVO vo = new NregsDataVO();
				 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
				 	    				vo.setUniqueId(jObj.getLong("UNIQUEID"));
				 	    				vo.setDistrict(jObj.getString("DISTRICT"));
				 	    				vo.setConstituency(jObj.getString("CONSTITUENCY"));
				 	    				vo.setMandal(jObj.getString("MANDAL"));
				 	    				vo.setPanchayat(jObj.getString("PANCHAYAT"));
				 	    				vo.setTarget(jObj.getLong("TARGET"));
				 	    				vo.setCompleted(jObj.getLong("COMPLETED"));
				 	    				vo.setAchivement(new BigDecimal(jObj.getString("ACHEIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				 	    				vo.setPercentage(new BigDecimal(jObj.getString("ACHEIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				 	    				list.add(vo);
				 	    			}
		 	    			}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Horticulture")){
		 	    				for(int i=0;i<finalArray.length();i++){
			 	    				NregsDataVO vo = new NregsDataVO();
			 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
			 	    				vo.setUniqueId(jObj.getLong("UNIQUEID"));
			 	    				vo.setDistrict(jObj.getString("DISTRICT"));
			 	    				vo.setConstituency(jObj.getString("CONSTITUENCY"));
			 	    				vo.setMandal(jObj.getString("MANDAL"));
			 	    				vo.setPanchayat(jObj.getString("PANCHAYAT"));
			 	    				if(inputVO.getLocationType().trim().toString().equalsIgnoreCase("state") || inputVO.getLocationType().trim().toString().equalsIgnoreCase("district")){
			 	    					vo.setTargetACRES(jObj.getString("TARGETACRES"));
				 	    				vo.setSanctionedPerventage(jObj.getString("SANCTIONEDPERCENTAGE"));
			 	    				}
			 	    				vo.setSanctionedACRES(jObj.getString("SANCTIONEDACRES"));
			 	    				vo.setPittingArea(jObj.getString("PITTINGAREA"));
			 	    				vo.setPlantingArea(jObj.getString("PLANTINGAREA"));
			 	    				vo.setPencentageOfPlanting(jObj.getString("PERCENTAGEOFPLANTING"));
			 	    				vo.setPercentage(jObj.getString("PERCENTAGEOFPLANTING"));
			 	    				list.add(vo);
			 	    			}
		 	    			}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Drying Platforms") ||
		 	    					inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Ponds")){
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
				 	    				
				 	    				if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("state") ||
						 	    		   inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("district")){
				 	    					nregsDataVO.setSanctionedTarget(jObj.getString("SANCTIONEDTARGET"));
				 	    					nregsDataVO.setSanctionedPerventage(jObj.getString("SANCTIONEDPERCENTAGE"));
				 	    				}
				 	    					
				 	    				nregsDataVO.setPercentage(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());	 	    				
				 	    				list.add(nregsDataVO);	 
				 	    			}
			 	    		}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("CC Roads")){
			 	    			for(int i=0;i<finalArray.length();i++){
			 	    				NregsDataVO nregsDataVO=new NregsDataVO();
			 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
			 	    				nregsDataVO.setUniqueId(jObj.getLong("UNIQUEID"));
			 	    				nregsDataVO.setDistrict(jObj.getString("DISTRICT"));
			 	    				nregsDataVO.setConstituency(jObj.getString("CONSTITUENCY"));
			 	    				nregsDataVO.setMandal(jObj.getString("MANDAL"));
			 	    				nregsDataVO.setPanchayat(jObj.getString("PANCHAYAT"));
			 	    				if(inputVO.getLocationType().trim().toString().equalsIgnoreCase("state") || inputVO.getLocationType().trim().toString().equalsIgnoreCase("district")){
			 	    					nregsDataVO.setTargetKMS(jObj.getString("TARGETKMS"));
			 	    					nregsDataVO.setSanctionedPerventage(jObj.getString("SANCTIONEDPERCENTAGE"));
			 	    				}
			 	    				if(inputVO.getLocationType().trim().toString().equalsIgnoreCase("mandal") || inputVO.getLocationType().trim().toString().equalsIgnoreCase("panchayat"))
			 	    					nregsDataVO.setSanctionedAmount(jObj.getString("SANCTIONED_AMOUNT"));
			 	    				else
			 	    					nregsDataVO.setSanctionedAmount(jObj.getString("SANCTIONEDAMOUNT"));
			 	    				nregsDataVO.setSanctionedKMS(jObj.getString("SANCTIONEDKMS"));
			 	    				nregsDataVO.setExpenditureAmount(jObj.getString("EXPENDITUREAMOUNT"));
			 	    				nregsDataVO.setCompletedKMS(jObj.getString("COMPLETEDKMS"));
			 	    				nregsDataVO.setPercentage(jObj.getString("PERCENTAGE"));
			 	    				
			 	    				list.add(nregsDataVO);
			 	    			}
			 	    		}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Avenue")){
				 	    		  for(int i=0;i<finalArray.length();i++){
			 	    				NregsDataVO vo = new NregsDataVO();
			 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
			 	    				vo.setUniqueId(jObj.getLong("UNIQUEID"));
			 	    				vo.setDistrict(jObj.getString("DISTRICT"));
			 	    				vo.setConstituency(jObj.getString("CONSTITUENCY"));
			 	    				vo.setMandal(jObj.getString("MANDAL"));
			 	    				vo.setPanchayat(jObj.getString("PANCHAYAT"));
			 	    				if(inputVO.getLocationType().trim().toString().equalsIgnoreCase("state") || inputVO.getLocationType().trim().toString().equalsIgnoreCase("district")){
			 	    					vo.setTargetKMS(jObj.getString("TARGETKMS"));
				 	    				vo.setSanctionedPerventage(jObj.getString("SANCTIONEDPERCENTAGE"));
			 	    				}
			 	    				vo.setSanctionedKMS(jObj.getString("SANCTIONEDKMS"));
			 	    				vo.setPittingKMS(jObj.getString("PITTINGKMS"));
			 	    				vo.setPlantingKMS(jObj.getString("PLANTINGKMS"));
			 	    				vo.setPencentageOfPlanting(jObj.getString("PERCENTAGEOFPLANTING"));
			 	    				vo.setPercentage(jObj.getString("PERCENTAGEOFPLANTING"));
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
		 	    	List<NregsDataVO> disVillageslist = null;
		 	    	List<NregsDataVO> consVillagelist = null;
		 	    	List<NregsDataVO> mandalVillagelist = null;
		 	    	
		 	    	if(inputVO.getLocationType().trim().equalsIgnoreCase("district"))
		 	    	{
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
				 	    	
				 	    	distConstMap.clear();
				 	    	if(list != null && !list.isEmpty()){
				 	    		 for (NregsDataVO nregsDataVO : list) {
				 	    			 NregsDataVO filterVo = new NregsDataVO();
				 	    			 filterVo.setPercentage(nregsDataVO.getPercentage());
									distConstMap.put(nregsDataVO.getDistrict(),filterVo);
								}
				 	    	 }
				 	    	inputVO.setLocationType("panchayat");
				 	    	ClientResponse panchayatResponse = webServiceUtilService.callWebService(webServiceUrl.toString(), inputVO);
				 	    	if(panchayatResponse.getStatus() != 200){
					 	    	  throw new RuntimeException("Failed : HTTP error code : "+ panchayatResponse.getStatus());
					 	      }else{
					 	    	 String panchayatOutput = panchayatResponse.getEntity(String.class);
					 	    	 
					 	    	disVillageslist = getNregsVillageCuntFrDistrict(panchayatOutput,distConstMap,inputVO.getDivType());
					 	      }
				 	    	
				 	    	
				 	    	finalVO.getDistConsCuntList().addAll(disConslist);//How many Consti Contain red,Green,Orange In Districts
				 	    	finalVO.getDistMandalCuntList().addAll(disMandallist);//How many Mandals Contain red,Green,Orange In Districts
				 	    	finalVO.getDistMandalList().addAll(disVillageslist);//How many Panchayts Contain red,Green,Orange In Districts
				 	    	finalVO.getDistList().addAll(list);//District Details
				 	    	
		 	    	}else if(inputVO.getLocationType().trim().equalsIgnoreCase("constituency"))
		 	    	{
			 	    		Map<String,NregsDataVO> constMandMap = new LinkedHashMap<String,NregsDataVO>();
			 	    		Map<String,NregsDataVO> consCuntMap  = new LinkedHashMap<String,NregsDataVO>();
				 	    		//distConstMap.clear();
			 	    		if(list != null && !list.isEmpty()){
				 	    		 for (NregsDataVO nregsDataVO : list) {
				 	    			String  distrName = nregsDataVO.getDistrict();
				 	    			NregsDataVO vo  = consCuntMap.get(distrName);
				 	    			if(vo == null ){
				 	    				vo = new NregsDataVO();
				 	    				vo.setDistrict(distrName);
				 	    				vo.setCount(vo.getCount() +1);
				 	    				consCuntMap.put(distrName,vo);
				 	    			}else{
				 	    				vo.setCount(vo.getCount()+1);
				 	    				consCuntMap.put(distrName,vo);
				 	    			}
								}
				 	    	 }
				 	    	
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
				 	    	constMandMap.clear();
				 	    	if(list != null && !list.isEmpty()){
				 	    		 for (NregsDataVO nregsDataVO : list) {
				 	    			 NregsDataVO filterVo = new NregsDataVO();
				 	    			 filterVo.setPercentage(nregsDataVO.getPercentage());
				 	    			constMandMap.put(nregsDataVO.getConstituency(),filterVo);
								}
				 	    	 }
				 	    	inputVO.setLocationType("panchayat");
				 	    	ClientResponse panchaytResponse = webServiceUtilService.callWebService(webServiceUrl.toString(), inputVO);
				 	    	if(panchaytResponse.getStatus() != 200){
					 	    	  throw new RuntimeException("Failed : HTTP error code : "+ panchaytResponse.getStatus());
					 	      }else{
					 	    	 String panchaytOutput = panchaytResponse.getEntity(String.class);
					 	    	 
					 	    	consVillagelist = getNregsVillagesCuntFrConstituncies(panchaytOutput,constMandMap,inputVO.getDivType());
					 	      }
				 	    	
				 	    	
				 	    	finalVO.getCountList().addAll(new ArrayList<NregsDataVO>(consCuntMap.values()));// Consti  In Districts
				 	    	finalVO.getDistMandalCuntList().addAll(consMandallist);// How many Mandals Contain red,Green,Orange In Constituencies
				 	    	finalVO.getDistMandalList().addAll(consVillagelist);//How many Panchayt Contain red,Green,Orange In Constituencies
				 	    	finalVO.getDistList().addAll(list);//Constuency Details
				 	    	
		 	    	  }else if(inputVO.getLocationType().trim().equalsIgnoreCase("mandal"))
		 	    	  	{
			 	    		 Map<String,NregsDataVO> disMandalCuntMap  = new LinkedHashMap<String,NregsDataVO>();
			 	    		 Map<String,NregsDataVO> consMandalCuntMap  = new LinkedHashMap<String,NregsDataVO>();
			 	    		 Map<String,NregsDataVO> mandalVillaMap  = new LinkedHashMap<String,NregsDataVO>();
			 	    		 
			 	    		 if(list != null && !list.isEmpty()){
				 	    		 for (NregsDataVO nregsDataVO : list) {
				 	    			String  distrName = nregsDataVO.getDistrict();
				 	    			NregsDataVO vo  = disMandalCuntMap.get(distrName);
				 	    			if(vo == null ){
				 	    				vo = new NregsDataVO();
				 	    				vo.setDistrict(distrName);
				 	    				vo.setCount(vo.getCount() +1);
				 	    				disMandalCuntMap.put(distrName,vo);
				 	    			}else{
				 	    				vo.setCount(vo.getCount()+1);
				 	    			}
								}
				 	    	 }
			 	    		 
			 	    		if(list != null && !list.isEmpty()){
				 	    		 for (NregsDataVO nregsDataVO : list) {
				 	    			String  constName = nregsDataVO.getConstituency();
				 	    			NregsDataVO vo  = consMandalCuntMap.get(constName);
				 	    			if(vo == null ){
				 	    				vo = new NregsDataVO();
				 	    				vo.setConstituency(constName);
				 	    				vo.setCount(vo.getCount() +1);
				 	    				consMandalCuntMap.put(constName,vo);
				 	    			}else{
				 	    				vo.setCount(vo.getCount()+1);
				 	    				//disMandalCuntMap.put(constName,vo);
				 	    			}
								}
				 	    	 }
			 	    		
			 	    		if(list != null && !list.isEmpty()){
				 	    		 for (NregsDataVO nregsDataVO : list) {
				 	    			 NregsDataVO filterVo = new NregsDataVO();
				 	    			 filterVo.setPercentage(nregsDataVO.getPercentage());
				 	    			mandalVillaMap.put(nregsDataVO.getMandal(),filterVo);
								}
				 	    	 }
				 	    	
			 	    		inputVO.setLocationType("panchayat");
				 	    	ClientResponse panchayResponse = webServiceUtilService.callWebService(webServiceUrl.toString(), inputVO);
				 	    	if(panchayResponse.getStatus() != 200){
					 	    	  throw new RuntimeException("Failed : HTTP error code : "+ panchayResponse.getStatus());
					 	      }else{
					 	    	 String panchayOutput = panchayResponse.getEntity(String.class);
					 	    	 
					 	    	mandalVillagelist = getNregsVillagesCuntFrMandals(panchayOutput,mandalVillaMap,inputVO.getDivType());
					 	      }
				 	    	
				 	    	
			 	    		finalVO.getCountList().addAll(new ArrayList<NregsDataVO>(disMandalCuntMap.values()));// Mandals  In Districts
			 	    		finalVO.getConsMandalList().addAll(new ArrayList<NregsDataVO>(consMandalCuntMap.values()));//Mandals In Constituency
			 	    		finalVO.getDistMandalList().addAll(mandalVillagelist);//How many Panchayt Contain red,Green,Orange In Mandals
				 	    	finalVO.getDistList().addAll(list);//Mandal Details
				 	    	
			 	    }else if(inputVO.getLocationType().trim().equalsIgnoreCase("panchayat"))
			 	    {
		 	    		 Map<String,NregsDataVO> disPanchyCuntMap  = new LinkedHashMap<String,NregsDataVO>();
		 	    		 Map<String,NregsDataVO> consPanchaytCuntMap  = new LinkedHashMap<String,NregsDataVO>();
		 	    		 Map<String,NregsDataVO> mandalPanchaytCuntMap  = new LinkedHashMap<String,NregsDataVO>();
		 	    		 
		 	    		 if(list != null && !list.isEmpty()){
			 	    		 for (NregsDataVO nregsDataVO : list) {
			 	    			String  distrName = nregsDataVO.getDistrict();
			 	    			NregsDataVO vo  = disPanchyCuntMap.get(distrName);
			 	    			if(vo == null ){
			 	    				vo = new NregsDataVO();
			 	    				vo.setDistrict(distrName);
			 	    				vo.setCount(vo.getCount() +1);
			 	    				disPanchyCuntMap.put(distrName,vo);
			 	    			}else{
			 	    				vo.setCount(vo.getCount()+1);
			 	    			}
							}
			 	    	 }
		 	    		 
		 	    		if(list != null && !list.isEmpty()){
			 	    		 for (NregsDataVO nregsDataVO : list) {
			 	    			String  constName = nregsDataVO.getConstituency();
			 	    			NregsDataVO vo  = consPanchaytCuntMap.get(constName);
			 	    			if(vo == null ){
			 	    				vo = new NregsDataVO();
			 	    				vo.setConstituency(constName);
			 	    				vo.setCount(vo.getCount() +1);
			 	    				consPanchaytCuntMap.put(constName,vo);
			 	    			}else{
			 	    				vo.setCount(vo.getCount()+1);
			 	    			}
							}
			 	    	 }
		 	    		
		 	    		if(list != null && !list.isEmpty()){
			 	    		 for (NregsDataVO nregsDataVO : list) {
			 	    			String  mandalName = nregsDataVO.getMandal();
			 	    			NregsDataVO vo  = mandalPanchaytCuntMap.get(mandalName);
			 	    			if(vo == null ){
			 	    				vo = new NregsDataVO();
			 	    				vo.setMandal(mandalName);
			 	    				vo.setCount(vo.getCount() +1);
			 	    				mandalPanchaytCuntMap.put(mandalName,vo);
			 	    			}else{
			 	    				vo.setCount(vo.getCount()+1);
			 	    			}
							}
			 	    	 }
			 	    	
			 	    	
		 	    		finalVO.getCountList().addAll(new ArrayList<NregsDataVO>(disPanchyCuntMap.values()));// Panchayats  In Districts
		 	    		finalVO.getConsMandalList().addAll(new ArrayList<NregsDataVO>(consPanchaytCuntMap.values()));// Panchayats  In Constituency
		 	    		finalVO.getMandalVillageList().addAll(new ArrayList<NregsDataVO>(mandalPanchaytCuntMap.values()));// Panchayats  In Mandals
			 	    	finalVO.getDistList().addAll(list);//Panchayat details
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
		List<NregsDataVO> retVOList = new ArrayList<NregsDataVO>(0);
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
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("Agriculture Activities")){
							percValue = new BigDecimal(jObj.getString("ACHEIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("Horticulture") || divType.trim().toString().equalsIgnoreCase("Avenue")){
							percValue = new BigDecimal(jObj.getString("PERCENTAGEOFPLANTING")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
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
		List<NregsDataVO> retVOList = new ArrayList<NregsDataVO>(0);
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
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("Agriculture Activities")){
							percValue = new BigDecimal(jObj.getString("ACHEIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("Horticulture") || divType.trim().toString().equalsIgnoreCase("Avenue")){
							percValue = new BigDecimal(jObj.getString("PERCENTAGEOFPLANTING")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
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
	
	/*
	 * Date : 28/06/2017
	 * Author :Nandhini
	 * @description : getNregsVillageCuntFrDistrict 
	 */

	@Override
	public List<NregsDataVO> getNregsVillageCuntFrDistrict(String output,Map<String,NregsDataVO> cntMap,String divType){
		List<NregsDataVO> retVOList = new ArrayList<NregsDataVO>(0);
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
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("Agriculture Activities")){
							percValue = new BigDecimal(jObj.getString("ACHEIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("Horticulture") || divType.trim().toString().equalsIgnoreCase("Avenue")){
							percValue = new BigDecimal(jObj.getString("PERCENTAGEOFPLANTING")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						}else{
							percValue = new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						} 
		    				if(vo != null) {
			    				if(Double.valueOf(percValue)  < 50){
		    						vo.setVillagesInRed(vo.getVillagesInRed()+1l);
		    					}else if(Double.valueOf(percValue)  >50 && Double.valueOf(percValue) <80){
		    						vo.setVillagesInOrange(vo.getVillagesInOrange()+1l);
	    						}else if(Double.valueOf(percValue)  >80){
		    						vo.setVillagesInGreen(vo.getVillagesInGreen()+1l);
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
					LOG.error("Exception raised at getNregsVillageCuntFrDistrict -NREGSTCSService service", e);
				}
			return retVOList;
	 }
	
	/*
	 * Date : 23/06/2017
	 * Author :Nandhini
	 * @description : getNregsVillagesCuntFrConstituncies 
	 */

	@Override
	public List<NregsDataVO> getNregsVillagesCuntFrConstituncies(String output,Map<String,NregsDataVO> cntMap,String divType){
		List<NregsDataVO> retVOList = new ArrayList<NregsDataVO>(0);
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
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("Agriculture Activities")){
							percValue = new BigDecimal(jObj.getString("ACHEIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("Horticulture") || divType.trim().toString().equalsIgnoreCase("Avenue")){
							percValue = new BigDecimal(jObj.getString("PERCENTAGEOFPLANTING")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						}else{
							percValue = new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						} 
		    				if(vo != null)
		    				{
			    				if(Double.valueOf(percValue)  < 50){
		    						vo.setVillagesInRed(vo.getVillagesInRed()+1l);
		    					}else if(Double.valueOf(percValue)  >50 && Double.valueOf(percValue) <80){
		    						vo.setVillagesInOrange(vo.getVillagesInOrange()+1l);
	    						}else if(Double.valueOf(percValue)  >80){
		    						vo.setVillagesInGreen(vo.getVillagesInGreen()+1l);
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
					LOG.error("Exception raised at getNregsVillagesCuntFrConstituncies -NREGSTCSService service", e);
				}
			return retVOList;
	 }
	
	/*
	 * Date : 23/06/2017
	 * Author :Nandhini
	 * @description : getNregsVillagesCuntFrMandals 
	 */

	@Override
	public List<NregsDataVO> getNregsVillagesCuntFrMandals(String output,Map<String,NregsDataVO> cntMap,String divType){
		List<NregsDataVO> retVOList = new ArrayList<NregsDataVO>(0);
		try{
			//Map<String,NregsDataVO> cntMap = new HashMap<String,NregsDataVO>(0);
			String percValue = null;
			if(output != null && !output.isEmpty()){
	    		JSONArray finalArray = new JSONArray(output);
	    		if(finalArray!=null && finalArray.length()>0){
	    			for(int i=0;i<finalArray.length();i++){
	    				JSONObject jObj = (JSONObject) finalArray.get(i);
		    			String mandalName = jObj.getString("MANDAL");
						NregsDataVO vo = cntMap.get(mandalName);
						if(divType != null && divType.trim().equalsIgnoreCase("Labour Budget")){
							percValue = new BigDecimal(jObj.getString("PER_APP_LB")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("Agriculture Activities")){
							percValue = new BigDecimal(jObj.getString("ACHEIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("Horticulture") || divType.trim().toString().equalsIgnoreCase("Avenue")){
							percValue = new BigDecimal(jObj.getString("PERCENTAGEOFPLANTING")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						}else{
							percValue = new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						} 
		    				if(vo != null)
		    				{
			    				if(Double.valueOf(percValue)  < 50){
		    						vo.setVillagesInRed(vo.getVillagesInRed()+1l);
		    					}else if(Double.valueOf(percValue)  >50 && Double.valueOf(percValue) <80){
		    						vo.setVillagesInOrange(vo.getVillagesInOrange()+1l);
	    						}else if(Double.valueOf(percValue)  >80){
		    						vo.setVillagesInGreen(vo.getVillagesInGreen()+1l);
		    					}
			    				vo.setTotal(vo.getTotal()+1l);
			    				vo.setDistrict(mandalName);
			    			}
	    				}   	    							
	    			}
    			}
			
					if(cntMap != null){
						retVOList = new ArrayList<NregsDataVO>(cntMap.values());
					}
								
				}catch(Exception e){
					LOG.error("Exception raised at getNregsVillagesCuntFrMandals -NREGSTCSService service", e);
				}
			return retVOList;
	 }
	
	/*
	 * Date : 03/07/2017
	 * Author :Nandhini
	 * @description : getNregaLevelsWiseDataFrNewCalls
	 */
	public List<NregsDataVO> getNregaLevelsWiseDataFrNewCalls(InputVO inputVO){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
			String webServiceUrl = null;
			
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Average Wage"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AverageWageService/AvgWageData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Average Days of Employment"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AverageDaysService/AvgDaysData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("HH Completed 100 Days"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AP100DaysService/AP100DaysData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Timely Payment"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/TimePaymentService/TPData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Nurseries"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NurseriesService/NurseriesData";
			
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), inputVO);
	        
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
		 	    				if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("HH Completed 100 Days"))
		 	    					if(inputVO.getLocationType().trim().equalsIgnoreCase("state"))
		 	    						vo.setTarget(jObj.getLong("TERGET"));
		 	    					else
		 	    						vo.setTarget(jObj.getLong("TARGET"));
		 	    				else
		 	    					vo.setTarget(jObj.getLong("TARGET"));
		 	    				if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Nurseries"))
		 	    					vo.setAchivement(new BigDecimal(jObj.getString("COMPLETED")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    				else
		 	    					vo.setAchivement(new BigDecimal(jObj.getString("ACHIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    				vo.setPercentage(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    				voList.add(vo);
		 	    			}
	 	    			}
	 	    		}
	 	      }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseDataFrNewCalls - NREGSTCSService service", e);
		}
		
		return voList;
	}
	
	/*
	 * Date : 03/07/2017
	 * Author :Nandhini
	 * @description : getNregaLevelsWiseDataFrAgriculture
	 */
	public List<NregsDataVO> getNregaLevelsWiseDataFrAgriculture(InputVO inputVO){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
		
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AgriService/AgriData", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("state")){
	 	    				for(int i=0;i<finalArray.length();i++){
		 	    				NregsDataVO vo = new NregsDataVO();
		 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
		 	    				vo.setUniqueId(jObj.getLong("UNIQUEID"));
		 	    				vo.setTarget(jObj.getLong("TARGET"));
		 	    				vo.setCompleted(jObj.getLong("COMPLETED"));
		 	    				vo.setAchivement(new BigDecimal(jObj.getString("ACHEIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    				vo.setPercentage(new BigDecimal(jObj.getString("ACHEIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    				voList.add(vo);
		 	    				
		 	    			}
	 	    			}else{
	 	    				for(int i=0;i<finalArray.length();i++){
		 	    				NregsDataVO vo = new NregsDataVO();
		 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
		 	    				vo.setUniqueId(jObj.getLong("UNIQUEID"));
		 	    				vo.setDistrict(jObj.getString("DISTRICT"));
		 	    				vo.setConstituency(jObj.getString("CONSTITUENCY"));
		 	    				vo.setMandal(jObj.getString("MANDAL"));
		 	    				vo.setPanchayat(jObj.getString("PANCHAYAT"));
		 	    				vo.setTarget(jObj.getLong("TARGET"));
		 	    				vo.setCompleted(jObj.getLong("COMPLETED"));
		 	    				vo.setAchivement(new BigDecimal(jObj.getString("ACHEIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    				vo.setPercentage(new BigDecimal(jObj.getString("ACHEIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    				voList.add(vo);
		 	    				
		 	    			}
	 	    			}
	 	    				
	 	    		}
	 	    	}
	 	   }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseDataFrAgriculture - NREGSTCSService service", e);
		}
		
		return voList;
	}
	
	/*
	 * Date : 06/07/2017
	 * Author :Sravanth
	 * @description : getNregaLevelsWiseDataFrHorticulture
	 */
	public List<NregsDataVO> getNregaLevelsWiseDataFrHorticulture(InputVO inputVO){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
			String webServiceUrl = null;
			
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Horticulture"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HorticultureService/HorticultureData";
			
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), inputVO);
	        
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
	 	    				if(inputVO.getLocationType().trim().toString().equalsIgnoreCase("state") || inputVO.getLocationType().trim().toString().equalsIgnoreCase("district")){
	 	    					vo.setTargetACRES(jObj.getString("TARGETACRES"));
		 	    				vo.setSanctionedPerventage(jObj.getString("SANCTIONEDPERCENTAGE"));
	 	    				}
	 	    				vo.setSanctionedACRES(jObj.getString("SANCTIONEDACRES"));
	 	    				vo.setPittingArea(jObj.getString("PITTINGAREA"));
	 	    				vo.setPlantingArea(jObj.getString("PLANTINGAREA"));
	 	    				vo.setPencentageOfPlanting(jObj.getString("PERCENTAGEOFPLANTING"));
	 	    				voList.add(vo);
	 	    			}
	 	    		}
	 	    	}
	 	   }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseDataFrHorticulture - NREGSTCSService service", e);
		}
		
		return voList;
	}
	
	/*
	 * Date : 06/07/2017
	 * Author :Sravanth
	 * @description : getNregaLevelsWiseDataFrAvenue
	 */
	public List<NregsDataVO> getNregaLevelsWiseDataFrAvenue(InputVO inputVO){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
			String webServiceUrl = null;
			
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Avenue"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AvenueService/AvenueData";
			
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), inputVO);
	        
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
	 	    				if(inputVO.getLocationType().trim().toString().equalsIgnoreCase("state") || inputVO.getLocationType().trim().toString().equalsIgnoreCase("district")){
	 	    					vo.setTargetKMS(jObj.getString("TARGETKMS"));
		 	    				vo.setSanctionedPerventage(jObj.getString("SANCTIONEDPERCENTAGE"));
	 	    				}
	 	    				vo.setSanctionedKMS(jObj.getString("SANCTIONEDKMS"));
	 	    				vo.setPittingKMS(jObj.getString("PITTINGKMS"));
	 	    				vo.setPlantingKMS(jObj.getString("PLANTINGKMS"));
	 	    				vo.setPencentageOfPlanting(jObj.getString("PERCENTAGEOFPLANTING"));
	 	    				voList.add(vo);
	 	    			}
	 	    		}
	 	    	}
	 	   }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseDataFrAvenue - NREGSTCSService service", e);
		}
		
		return voList;
	}
	
	/*
	 * Date : 06/07/2017
	 * Author :Sravanth
	 * @description : getNregaLevelsWiseDataForCCRoads
	 */
	public List<NregsDataVO> getNregaLevelsWiseDataForCCRoads(InputVO inputVO){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
			String webServiceUrl = null;
			
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("CC Roads"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CCRoadsService/CCRoadsData";
			 
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), inputVO);
	        
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
	 	    				if(inputVO.getLocationType().trim().toString().equalsIgnoreCase("state") || inputVO.getLocationType().trim().toString().equalsIgnoreCase("district")){
	 	    					vo.setTargetKMS(jObj.getString("TARGETKMS"));
	 	    					vo.setSanctionedPerventage(jObj.getString("SANCTIONEDPERCENTAGE"));
	 	    				}
	 	    				if(inputVO.getLocationType().trim().toString().equalsIgnoreCase("mandal") || inputVO.getLocationType().trim().toString().equalsIgnoreCase("panchayat"))
	 	    					vo.setSanctionedAmount(jObj.getString("SANCTIONED_AMOUNT"));
	 	    				else
	 	    					vo.setSanctionedAmount(jObj.getString("SANCTIONEDAMOUNT"));
	 	    				vo.setSanctionedKMS(jObj.getString("SANCTIONEDKMS"));
	 	    				vo.setExpenditureAmount(jObj.getString("EXPENDITUREAMOUNT"));
	 	    				vo.setCompletedKMS(jObj.getString("COMPLETEDKMS"));
	 	    				vo.setPercentage(jObj.getString("PERCENTAGE"));
	 	    				
	 	    				voList.add(vo);
	 	    			}
	 	    		}
	 	    	}
	 	      }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseDataForCCRoads - NREGSTCSService service", e);
		}
		
		return voList;
	}
	/*
	 * Date : 06/07/2017
	 * Author :Nandhini
	 * @description : getNregaParliamentData
	 */
	/*public List<NregsDataVO> getNregaParliamentData(InputVO inputVO){
		List<NregsDataVO> finalVOList = new ArrayList<NregsDataVO>(0);
		try {
			String webServiceUrl = null;
			Map<Long,List<Long>> consParlMap = new HashMap<Long,List<Long>>(0);
			List<Long> parlIds = new ArrayList<Long>(0);
			Map<Long,String> parlmentMap = new HashMap<Long,String>(0);
			List<Long> constuIds = new ArrayList<Long>(0);
			List<NregsDataVO> consList = new ArrayList<NregsDataVO>(0);
			
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Farm Pond"))
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
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Solid Waste Management"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SWMService/SWMData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Play Fields"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/PlayFields/PlayFieldsData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Burial Ground"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BurialService/BurialData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Drying Platforms"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FishDryingPlatformService/FishDryingPlatformData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Ponds"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FishPondsService/FishPondsData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Avg Wage"))//Specific Format
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AverageWageService/AvgWageData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Avg days of emp per HH"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AverageDaysService/AvgDaysData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("HH Comp 100 days"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AP100DaysService/AP100DaysData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Timely Payments"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/TimePaymentService/TPData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Nurseries"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NurseriesService/Nurseries";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Horticulture"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HorticultureService/HorticultureData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Avenue"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AvenueService/AvenueData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Agriculture"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AgriService/AgriData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Labour Budget"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CMDashBoard/LabourBudgetData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Horticulture"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HorticultureService/HorticultureData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Avenue"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AvenueService/AvenueData";
			 
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Avg Wage") ||
	 	    			   inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Avg days of emp per HH") ||
	 	    			   inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("HH Comp 100 days") ||
	 	    			   inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Timely Payments") ||
	 	    			   inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Nurseries")){
	 	    				for(int i=0;i<finalArray.length();i++){
		 	    				NregsDataVO vo = new NregsDataVO();
		 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
		 	    				vo.setUniqueId(jObj.getLong("UNIQUEID"));
		 	    				vo.setDistrict(jObj.getString("DISTRICT"));
		 	    				vo.setConstituency(jObj.getString("CONSTITUENCY"));
		 	    				vo.setMandal(jObj.getString("MANDAL"));
		 	    				vo.setPanchayat(jObj.getString("PANCHAYAT"));
		 	    				if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("HH Comp 100 days"))
		 	    					if(inputVO.getLocationType().trim().equalsIgnoreCase("state"))
		 	    						vo.setTarget(jObj.getLong("TERGET"));
		 	    					else
		 	    						vo.setTarget(jObj.getLong("TARGET"));
		 	    				else
		 	    					vo.setTarget(jObj.getLong("TARGET"));
		 	    				if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Nurseries"))
		 	    					vo.setAchivement(new BigDecimal(jObj.getString("COMPLETED")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    				else
		 	    					vo.setAchivement(new BigDecimal(jObj.getString("ACHIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    				vo.setPercentage(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    				consList.add(vo);
		 	    			}
	 	    			}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Horticulture") ||
	 	    					 inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Avenue")){
	 	    				for(int i=0;i<finalArray.length();i++){
		 	    				NregsDataVO vo = new NregsDataVO();
		 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
		 	    				vo.setUniqueId(jObj.getLong("UNIQUEID"));
		 	    				vo.setDistrict(jObj.getString("DISTRICT"));
		 	    				vo.setConstituency(jObj.getString("CONSTITUENCY"));
		 	    				vo.setMandal(jObj.getString("MANDAL"));
		 	    				vo.setPanchayat(jObj.getString("PANCHAYAT"));
		 	    				
		 	    				if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("state") ||
		 	    				   inputVO.getLocationType().trim().equalsIgnoreCase("district"))
		 	    				
		 	    				vo.setCompleted(jObj.getLong("PITTINGAREA"));//Pitting Area
		 	    				vo.setCount(jObj.getLong("PLANTINGAREA"));//plating Area
		 	    				
		 	    				vo.setTarget(jObj.getLong("TARGETACRES"));
		 	    				vo.setTotal(jObj.getLong("SANCTIONEDACRES"));//sanctionedAcrs
		 	    				vo.setSanctionedpercentage(new BigDecimal(jObj.getString("SANCTIONEDPERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    				vo.setPercentage(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    				consList.add(vo);
		 	    			}
	 	    			}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Agriculture")){
	 	    				if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("state")){
		 	    				for(int i=0;i<finalArray.length();i++){
			 	    				NregsDataVO vo = new NregsDataVO();
			 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
			 	    				vo.setUniqueId(jObj.getLong("UNIQUEID"));
			 	    				vo.setTarget(jObj.getLong("TARGET"));
			 	    				vo.setCompleted(jObj.getLong("COMPLETED"));
			 	    				vo.setAchivement(new BigDecimal(jObj.getString("ACHEIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			 	    				vo.setPercentage(new BigDecimal(jObj.getString("ACHEIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			 	    				consList.add(vo);
			 	    				
			 	    			}
		 	    			}else{
		 	    				for(int i=0;i<finalArray.length();i++){
			 	    				NregsDataVO vo = new NregsDataVO();
			 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
			 	    				vo.setUniqueId(jObj.getLong("UNIQUEID"));
			 	    				vo.setDistrict(jObj.getString("DISTRICT"));
			 	    				vo.setConstituency(jObj.getString("CONSTITUENCY"));
			 	    				vo.setMandal(jObj.getString("MANDAL"));
			 	    				vo.setPanchayat(jObj.getString("PANCHAYAT"));
			 	    				vo.setTarget(jObj.getLong("TARGET"));
			 	    				vo.setCompleted(jObj.getLong("COMPLETED"));
			 	    				vo.setAchivement(new BigDecimal(jObj.getString("ACHEIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			 	    				vo.setPercentage(new BigDecimal(jObj.getString("ACHEIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			 	    				consList.add(vo);
			 	    				
			 	    			}
		 	    			}
	 	    			}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Labour Budget")){
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
		 	    				consList.add(vo);
		 	    			}
	 	    			}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("CC Roads")){
	 	    				for(int i=0;i<finalArray.length();i++){
		 	    				NregsDataVO vo = new NregsDataVO();
		 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
		 	    				vo.setUniqueId(jObj.getLong("UNIQUEID"));
		 	    				vo.setDistrict(jObj.getString("DISTRICT"));
		 	    				vo.setConstituency(jObj.getString("CONSTITUENCY"));
		 	    				vo.setMandal(jObj.getString("MANDAL"));
		 	    				vo.setPanchayat(jObj.getString("PANCHAYAT"));
		 	    				vo.setTarget(jObj.getLong("TARGETKMS"));
		 	    				vo.setSanctionedAmount(new BigDecimal(jObj.getString("SANCTIONEDAMOUNT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    				vo.setSanctionedKms(new BigDecimal(jObj.getString("SANCTIONEDKMS")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    				vo.setSanctionedpercentage(new BigDecimal(jObj.getString("SANCTIONEDPERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    				vo.setTotalExpenditure(new BigDecimal(jObj.getString("EXPENDITUREAMOUNT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    				vo.setCompletedKms(new BigDecimal(jObj.getString("COMPLETEDKMS")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    				vo.setPercentage(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    				consList.add(vo);
		 	    			}
	 	    			}else{
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
		 	    				consList.add(vo);
		 	    			}
	 	    				
	 	    			}
	 	    			}
	 	    		}
	 	      	}
	        List<Object[]> constParlList = parliamentAssemblyDAO.getConsParlimentIds();
			if(constParlList != null && constParlList.size() > 0l){
				for (Object[] objects : constParlList) {
					Long parlmentId = commonMethodsUtilService.getLongValueForObject(objects[1]);
					parlIds.add(parlmentId);
					List<Long> constIdsList = consParlMap.get(parlmentId);
					if(constIdsList == null){
						constIdsList = new ArrayList<Long>(0);
						constIdsList.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
						consParlMap.put(parlmentId,constIdsList);
					}else{
						constIdsList.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
					}
					
				}
			}
			//Getting parlId,parlmentName
			List<Object[]> parlList = constituencyDAO.getParlmentNames(parlIds);
			if(commonMethodsUtilService.isListOrSetValid(parlList)){
				for (Object[] objects : parlList) {
					parlmentMap.put(commonMethodsUtilService.getLongValueForObject(objects[0]), commonMethodsUtilService.getStringValueForObject(objects[1]));
				}
			}
			
			if(commonMethodsUtilService.isMapValid(consParlMap)){
				for(Entry<Long,List<Long>> param : consParlMap.entrySet()){
					NregsDataVO finalVO = new NregsDataVO();
					Long parlmentId = param.getKey();
					List<Long> constIds  = param.getValue();
					if(commonMethodsUtilService.isListOrSetValid(constIds)){
						for (Long constId : constIds) {
							NregsDataVO vo = getMatchedVO(consList,constId);
							if(vo != null){
								finalVO.setTarget(vo.getTarget()+finalVO.getTarget());
								finalVO.setGrounded(vo.getGrounded()+finalVO.getGrounded());
								finalVO.setNotGrounded(vo.getNotGrounded()+finalVO.getNotGrounded());
								finalVO.setInProgress(vo.getInProgress()+finalVO.getInProgress());
								finalVO.setCompleted(vo.getCompleted()+finalVO.getCompleted());
							}
						}
					}
					finalVO.setParliamentId(parlmentId);
					finalVO.setParliamentName(parlmentMap.get(parlmentId));
					finalVO.setPercentage(new BigDecimal(finalVO.getCompleted()*100.00/finalVO.getTarget()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					finalVOList.add(finalVO);
				}
				
			}
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaParliamentData - NREGSTCSService service", e);
		}
		
		return finalVOList;
	}
	/*
	 * Date : 06/07/2017
	 * Author :Nandhini
	 * @description : getMatchedVO
	 */
	/*public NregsDataVO getMatchedVO(List<NregsDataVO> list,Long constId){
		try{
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for (NregsDataVO nregsDataVO : list) {
					if(nregsDataVO.getUniqueId().longValue() == constId.longValue()){
						return nregsDataVO;
					}
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception raised at getMatchedVO - NREGSTCSService service", e);
		}
		return null;
	}*/
	/*
	 * Date : 16/06/2017
	 * Author :Nandhini
	 * @description : getNREGSProjectsAbstractNew
	 */
	public List<NregsProjectsVO> getNREGSProjectsAbstractNew(InputVO inputVO){
		List<NregsProjectsVO> voList = new ArrayList<NregsProjectsVO>(0);
		try {
			String projectType = null;
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CMDashBoard/AbstractNew", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	  if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("Burial Grounds"))
	 	    		  inputVO.setType("Burial Ground");
	 	    	  else if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("Anganwadi Buildings"))
	 	    		  inputVO.setType("Anganwadi");
	 	    	  else if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("GP Buildings"))
	 	    		  inputVO.setType("Gram Panchayat Buildings");
	 	    	 else if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("Silk Worms"))
	 	    		  inputVO.setType("Silk worm");
	 	    	 else if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("Cattle Drinking Water Troughs"))
	 	    		  inputVO.setType("Cattle drinking water trough");
	 	    	 else if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("Raising of Perinnial Fodders"))
		 	    	  inputVO.setType("Raising of Perinnial Fodder");
	 	    	  
	 	    	 String output = response.getEntity(String.class);
	 	    	 if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				NregsProjectsVO vo = new NregsProjectsVO();
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				if(inputVO.getLocationType().trim().equalsIgnoreCase("district"))
	 	    					projectType = jObj.getString("CAT_NAME||'_DISTRICT'");
	 	    				else if(inputVO.getLocationType().trim().equalsIgnoreCase("state"))
	 	    					projectType = jObj.getString("CAT_NAME");
	 	    				
	 	    				String[]  projectTypeArr = projectType.split("_");
	 	    				if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase(projectTypeArr[0])){
	 	    					if(inputVO.getLocationType().trim().equalsIgnoreCase("district"))
	 	    						vo.setParameter(jObj.getString("CAT_NAME||'_DISTRICT'"));
	 	    					else if(inputVO.getLocationType().trim().equalsIgnoreCase("state"))
	 	    						vo.setParameter(jObj.getString("CAT_NAME"));
	 	    					
	 	    					vo.setTarget(jObj.getString("TARGET"));
		 	    				vo.setCompleted(jObj.getString("COMPLETED"));
		 	    				vo.setPercentage(jObj.getString("PERC"));
		 	    				voList.add(vo);
	 	    				}
	 	    			}
	 	    		}
	 	    	}
	 	    	 
	 	     }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSProjectsAbstractNew - NREGSTCSService service", e);
		}
		
		return voList;
	}
	
	public List<NregsProjectsVO> getNREGSAbstractDataByType(InputVO inputVO){
		List<NregsProjectsVO> returnList = new ArrayList<>();
		try {
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CMDashBoard/AbstractNew", inputVO);
			
			if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	    				for(int i=0;i<finalArray.length();i++){
	    					NregsProjectsVO vo = new NregsProjectsVO();
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				
	 	    				vo.setParameter(jObj.getString("PARAMETER"));
	 	    				
	 	    				if(inputVO.getType().toString().trim().equalsIgnoreCase("Average Wage")){
	 	    					vo.setTarget(jObj.getString("MAX(T.AVG_WAGE_TARGET)"));
		 	    				vo.setCompleted(jObj.getString("AVG_WAGE"));
	 	    				}
	 	    				else if(inputVO.getType().toString().trim().equalsIgnoreCase("Average Days of Employment")){
	 	    					vo.setTarget(jObj.getString("MAX(T.AVG_DAYS_TARGET)"));
		 	    				vo.setCompleted(jObj.getString("AVG_DAYS"));
	 	    				}
	 	    				else if(inputVO.getType().toString().trim().equalsIgnoreCase("HH Completed 100 Days")){
	 	    					vo.setTarget(jObj.getString("ROUND(NVL(SUM(HH_WORKING)*0.2,0),0)"));
		 	    				vo.setCompleted(jObj.getString("COMP_100"));
	 	    				}
	 	    				else if(inputVO.getType().toString().trim().equalsIgnoreCase("Timely Payment")){
	 	    					vo.setTarget(jObj.getString("MAX(T.UPLOAD_5_TARGET)"));
		 	    				vo.setCompleted(jObj.getString("UPLOAD_5"));
	 	    				}
	 	    				/*else if(inputVO.getType().toString().trim().equalsIgnoreCase("Timely Payment")){
	 	    					vo.setTarget(jObj.getString("MAX(T.UPLOAD_5_TARGET)"));
		 	    				vo.setCompleted(jObj.getString("UPLOAD_5"));
	 	    				}*/
	 	    				else if(inputVO.getType().toString().trim().equalsIgnoreCase("Horticulture") || inputVO.getType().toString().trim().equalsIgnoreCase("Avenue")){
	 	    					vo.setTarget(jObj.getString("TARGET"));
		 	    				vo.setCompleted(jObj.getString("ACHIVEMENT"));
	 	    				}
	 	    				else{
	 	    					vo.setTarget(jObj.getString("TARGET"));
		 	    				vo.setCompleted(jObj.getString("COMPLETED"));
	 	    				}
	 	    				
	 	    				if(inputVO.getType().toString().trim().equalsIgnoreCase("IHHL") || inputVO.getType().toString().trim().equalsIgnoreCase("Vermi Compost")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("Agriculture Activities")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("Farm Ponds"))
	 	    					vo.setPercentage(jObj.getString("PERC"));
	 	    				else if(inputVO.getType().toString().trim().equalsIgnoreCase("Average Wage")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("Average Days of Employment")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("HH Completed 100 Days")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("Timely Payment")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("Horticulture") || inputVO.getType().toString().trim().equalsIgnoreCase("Avenue"))
	 	    					vo.setPercentage(jObj.getString("PER"));
	 	    				else
	 	    					vo.setPercentage(jObj.getString("PERCENTAGE"));
	 	    				
	 	    				returnList.add(vo);
	    				}
	 	    		}
	 	    	}
	 	    }
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSAbstractDataByType - NREGSTCSService service", e);
		}
		return returnList;
	}
	
	/*
	 * Date : 28/06/2017
	 * Author :Sravanth
	 * @description : getNregaLevelsWiseDataForTimelyPayments
	 */
	public List<NregsDataVO> getNregaLevelsWiseDataForTimelyPayments(InputVO inputVO){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
			String webServiceUrl = null;
			
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Payments"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/PaymentsDataService/PaymentsData";
			
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), inputVO);
	        
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
		 	    				vo.setFtoNotGenCnt(jObj.getString("FTO_NOT_GEN_CNT"));
		 	    				vo.setFtoNotGenAmt(jObj.getString("FTO_NOT_GEN_AMT"));
		 	    				vo.setFtoNotUploadCnt(jObj.getString("FTO_NOT_UPLOAD_CNT"));
		 	    				vo.setFtoNotUploadAmt(jObj.getString("FTO_NOT_UPLOAD_AMT"));
		 	    				vo.setFtoNotSentCnt(jObj.getString("FTO_NOT_SENT_CNT"));
		 	    				vo.setFtoNotSentAmt(jObj.getString("FTO_NOT_SENT_AMT"));
		 	    				vo.setRejectCnt(jObj.getString("REJECT_CNT"));
		 	    				vo.setRejectAmt(jObj.getString("REJECT_AMT"));
		 	    				vo.setPendingResponseCnt(jObj.getString("PENDING_RESPONSE_CNT"));
		 	    				vo.setPendingResponseAmt(jObj.getString("PENDING_RESPONSE_AMT"));
		 	    				if(inputVO.getLocationType().trim().toString().equalsIgnoreCase("district")){
		 	    					vo.setdId(jObj.getString("DID"));
		 	    					vo.setDistrict(jObj.getString("DNAME"));
		 	    				}
		 	    				else if(inputVO.getLocationType().trim().toString().equalsIgnoreCase("mandal")){
		 	    					vo.setmId(jObj.getString("MID"));
		 	    					vo.setMandal(jObj.getString("MANDAL_DESCRIPTION"));
		 	    				}
		 	    				voList.add(vo);
		 	    			}
	 	    			}
	 	    		}
	 	      }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseDataForTimelyPayments - NREGSTCSService service", e);
		}
		
		return voList;
	}
}
