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
import com.itgrids.dao.IPrConstituencyDAO;
import com.itgrids.dao.IPrDistrictDAO;
import com.itgrids.dao.IWebserviceCallDetailsDAO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LabourBudgetOverViewVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.NregaPaymentsVO;
import com.itgrids.dto.NregsDataVO;
import com.itgrids.dto.NregsOverviewVO;
import com.itgrids.dto.NregsProjectsVO;
import com.itgrids.dto.WebserviceDetailsVO;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.itgrids.service.integration.impl.INREGSTCSService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.IConstants;
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
	@Autowired
	private IPrDistrictDAO prDistrictDAO;
	@Autowired
	private IPrConstituencyDAO prConstituencyDAO;
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
			 String str = convertingInputVOToString(inputVO);
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/LabourBudgetServiceNew/LabourBudgetOverviewNew", str);
	        
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
	 	    		returnvo.setTotalLabour(jObj.getString("TOTALLABOUR"));
	 	    		returnvo.setMaleLabour(jObj.getString("MALELABOUR"));
	 	    		returnvo.setFemaleLabour(jObj.getString("FEMALELABOUR"));
	 	    		returnvo.setTargettedPersonDays(jObj.getString("TARGETTEDPERSONDAYS"));
	 	    		returnvo.setGeneratedPersonDays(jObj.getString("GENERATEDPERSONDAYS"));
	 	    		returnvo.setAchievementPerc(new BigDecimal(jObj.getLong("GENERATEDPERSONDAYS")*100.0/jObj.getLong("TARGETTEDPERSONDAYS")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    		returnvo.setTotalExpenditure(new BigDecimal(jObj.getString("TOTALEXPENDITURE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    		returnvo.setAvgWagePerPerson(new BigDecimal(jObj.getString("AVGWAGEPERPERSON")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    		returnvo.setTotalResponse(jObj.getString("TOTALRESPONSE"));
	 	    		returnvo.setOnRequestWorkAllocated(jObj.getString("ONREQUESTWORKALLOCATED"));
	 	    		returnvo.setGotWorkOccutionally(jObj.getString("GOTWORKOCCUTIONALLY"));
    				returnvo.setHaveNotGotWork(jObj.getString("HAVENOTGOTWORK"));
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
    				//returnvo.setLeader(jObj.getString("LEADER"));
    				//returnvo.setLagger(jObj.getString("LAGGER"));
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
			String str = convertingInputVOToString(inputVO);
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/LabourBudgetServiceNew/LabourBudgetExpenditureNew", str);
	        
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
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/GpBuildingServiceNew/GpBuildingOverviewNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/GPBuildingService/GPBuildingOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR Jala Siri"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NtrsService/NtrsOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("CC Roads"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CCRoadsServicesNew/CCRoadsOverviewNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CCRoadsService/CCRoadsOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Anganwadi Buildings"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AnganawadiServiceNew/AnganawadiOverviewNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AnganwadiService/AnganwadiOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mandal Buildings"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MandalBuildingServiceNew/MandalBuildingOverviewNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MandalBuildingService/MandalBuildingOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR 90 Days"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HousingServiceNew/HousingOverviewNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HousingService/HousingOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Production of Bricks"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BricksServiceNew/BricksOverviewNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BricksService/BricksOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MulberyServiceNew/MulberyOverviewNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SericultureService/SericultureOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Silk Worms"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SilkwarmServiceNew/SilkwarmOverviewNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SilkwormService/SilkwormOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Cattle Drinking Water Troughs"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CattleServiceNew/CattleOverviewNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AHService/AHOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Raising of Perinnial Fodders"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FodderServiceNew/FodderOverviewNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FodderService/FodderOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Solid Waste Management"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SolidWasteManagementServices/SolidWasteManagementOverview";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SWMService/SWMOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Play Fields"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/PlayFieldsServices/PlayFieldsOverview";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/PlayFields/PlayFieldsOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Burial Grounds"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BurialGroundsServices/BurialGroundsOverview";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BurialService/BurialOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Agriculture Activities"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AgricultureServices/AgricultureOverview";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AgriService/AgriOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Average Wage"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AveragewageServicesNew/AveragewageOverviewNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AverageWageService/AvgWageOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Average Days of Employment"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AverageDaysServicesNew/AverageDaysOverviewNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AverageDaysService/AvgDaysOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("HH Completed 100 Days"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HundredHoursServices/HundredHoursOverview";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AP100DaysService/AP100DaysOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Timely Payment"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/TimePaymentsServicesNew/TimePaymentsOverviewNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/TimePaymentService/TPOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Nurseries"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NurseriesServiceNew/NurseriesOverviewNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NurseriesService/NurseriesOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Horticulture"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HorticultureServiceNew/HorticultureOverviewNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HorticultureService/HorticultureOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Avenue"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AvenueServicesNew/AvenueOverviewNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AvenueService/AvenueOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Drying Platforms"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FishDryingServiceNew/FishDryingOverviewNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FishDryingPlatformService/FishDryingPlatformOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Ponds"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FishPondServiceNew/FishPondOverviewNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FishPondsService/FishPondsOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("FAperformance"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FaPerformanceServiceNew/FaPerformanceNew";
			
			String str = convertingInputVOToString(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
	        
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
 	    					inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Nurseries") ||
 	    					inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("FAperformance")){
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
	 	    				finalVO.setTotalBudget(Obj.getString("TOTALBUDGET"));
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
	
	public String convertingInputVOToString(InputVO inputVO){
		String str = "";
		try {
			if(inputVO.getLocationId() != null)
				if(inputVO.getLocationId().longValue() > 0l && inputVO.getLocationId().longValue() <= 9l)
					inputVO.setLocationIdStr("0"+inputVO.getLocationId().toString());
			str = "{";
			
			if(inputVO.getFromDate() != null )
				str += "\"fromDate\" : \""+inputVO.getFromDate()+"\",";
			if(inputVO.getToDate() != null)
				str += "\"toDate\" : \""+inputVO.getToDate()+"\",";
			if(inputVO.getYear() != null)
				str += "\"year\" : \""+inputVO.getYear()+"\",";
			if(inputVO.getLocationType() != null)
				str += "\"locationType\" : \""+inputVO.getLocationType()+"\",";
			if(inputVO.getLocationIdStr() != null)
				str += "\"locationId\" : \""+inputVO.getLocationIdStr()+"\",";
			else if(inputVO.getLocationId() != null)
				str += "\"locationId\" : \""+inputVO.getLocationId()+"\",";
			if(inputVO.getSublocationType() != null)
				str += "\"SublocationType\" : \""+inputVO.getSublocationType()+"\",";
			if(inputVO.getFromRange() != null)
				str += "\"FromRange\" : \""+inputVO.getFromRange()+"\",";
			if(inputVO.getToRange() != null)
				str += "\"ToRange\" : \""+inputVO.getToRange()+"\",";
			if(inputVO.getType() != null)
				str += "\"type\" : \""+inputVO.getType()+"\",";
			
			if(str.length() > 1)
				str = str.substring(0,str.length()-1);
			
			str += "}";
			
		} catch (Exception e) {
			LOG.error("Exception raised at convertingInputVOToString - NREGSTCSService service", e);
		}
		return str;
	}
	
	/*
	 * Date : 28/06/2017
	 * Author :Sravanth
	 * @description : getNregaLevelsWiseData
	 */
	public List<NregsDataVO> getNregaLevelsWiseData(InputVO inputVO){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
			if(inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().toString().length() > 0l)
				inputVO.setSublocationType(inputVO.getSublocaType().trim());
			
			String webServiceUrl = null;
			
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Farm Ponds"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FarmPondService_new/FarmPondData_new";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FarmPondService/FarmPondData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("IHHL"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/IHHLService_new/IHHLData_new";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/IHHLService/IHHLData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Vermi Compost"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/VermiService_new/VermiData_new";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/VermiService/VermiData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("GP Buildings"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/GpBuildingServiceNew/GpBuildingDataNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/GPBuildingService/GPBuildingData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR Jala Siri"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NtrsService/NtrsData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Anganwadi Buildings"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AnganawadiServiceNew/AnganawadiDataNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AnganwadiService/AnganwadiData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mandal Buildings"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MandalBuildingServiceNew/MandalBuildingDataNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MandalBuildingService/MandalBuildingData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR 90 Days"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HousingServiceNew/HousingDataNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HousingService/HousingData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Production of Bricks"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BricksServiceNew/BricksDataNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BricksService/BricksData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MulberyServiceNew/MulberyDataNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SericultureService/SericultureData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Silk Worms"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SilkwarmServiceNew/SilkwarmDataNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SilkwormService/SilkwormData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Cattle Drinking Water Troughs"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CattleServiceNew/CattleDataNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AHService/AHData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Raising of Perinnial Fodders"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FodderServiceNew/FodderDataNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FodderService/FodderData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Solid Waste Management"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SolidWasteManagementServices/SolidWasteManagementData";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SWMService/SWMData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Play Fields"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/PlayFieldsServices/PlayFieldsData";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/PlayFields/PlayFieldsData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Burial Grounds"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BurialGroundsServices/BurialGroundsData";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BurialService/BurialData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Agriculture Activities"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AgricultureServices/AgricultureData";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AgriService/AgriData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Drying Platforms"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FishDryingServiceNew/FishDryingDataNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FishDryingPlatformService/FishDryingPlatformData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Ponds"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FishPondServiceNew/FishPondDataNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FishPondsService/FishPondsData
			 
			String str = convertingInputVOToString(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
	        
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
	 	    				vo.setUniqueId(Long.valueOf((jObj.getString("UNIQUEID").toString().trim().length() > 0 ? jObj.getString("UNIQUEID") : "1").toString()));
	 	    				vo.setDistrict(jObj.getString("DISTRICT"));
	 	    				vo.setConstituency(jObj.getString("CONSTITUENCY"));
	 	    				vo.setMandal(jObj.getString("MANDAL"));
	 	    				vo.setPanchayat(jObj.getString("PANCHAYAT"));
	 	    				vo.setTarget(jObj.getLong("TARGET"));
	 	    				vo.setGrounded(jObj.getString("GROUNDED"));
	 	    				if(jObj.getString("NOTGROUNDED").trim().contains("-"))
	 	    					vo.setNotGrounded("0");
	 	    				else
	 	    					vo.setNotGrounded(jObj.getString("NOTGROUNDED"));
	 	    				vo.setInProgress(jObj.getLong("INPROGRESS"));
	 	    				vo.setCompleted(jObj.getLong("COMPLETED"));
	 	    				vo.setPercentage(jObj.getString("PERCENTAGE"));
	 	    				if((inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery")
	 	    						|| inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Silk worm")
	 	    						|| inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Cattle drinking water trough")
	 	    						|| inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Raising of Perinnial Fodder"))
	 	    							&& inputVO.getSublocationType().trim().toString().equalsIgnoreCase("state")){
	 	    					vo.setSanctionedTarget(jObj.getString("SANCTIONEDTARGET"));
	 	    					vo.setSanctionedPerventage(jObj.getString("SANCTIONEDPERCENTAGE"));
	 	    				}
	 	    				
	 	    				if((inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Ponds")
	 	    						|| inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Drying Platforms"))
	 	    						&& (inputVO.getSublocationType().trim().toString().equalsIgnoreCase("state")
	 	    								|| inputVO.getSublocationType().trim().toString().equalsIgnoreCase("district"))){
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
			if(inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().toString().length() > 0l)
				inputVO.setSublocationType(inputVO.getSublocaType().trim());
			
			String str = convertingInputVOToString(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/LabourBudgetServiceNew/LabourBudgetDataNew", str);
	        
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
	 	    				//vo.setPercentage(jObj.getString("MATPERCENTAGE"));
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
	public List<NregsDataVO> getNregsConstCuntDetails(String output,Map<String,NregsDataVO> cntMap,String divType,String locationType){
		List<NregsDataVO> retVOList = new ArrayList<NregsDataVO>(0);
		try{
			//Map<String,NregsDataVO> cntMap = new HashMap<String,NregsDataVO>(0);
			String percValue = null;
			String distrName = null;
			if(output != null && !output.isEmpty()){
	    		JSONArray finalArray = new JSONArray(output);
	    		if(finalArray!=null && finalArray.length()>0){
	    			for(int i=0;i<finalArray.length();i++){
	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	    				if(divType != null && divType.trim().toString().equalsIgnoreCase("FAperformance")){
	    					if(locationType != null && locationType.trim().equalsIgnoreCase("district")){
	    						distrName = jObj.getString("DISTRICT_DESCRIPTION");
	    					}else{
	    						distrName = jObj.getString("DISTRICT");
	    					}
	    				}else{
	    					distrName = jObj.getString("DISTRICT");
	    				}
	    				NregsDataVO vo = cntMap.get(distrName);
						if(divType != null && divType.trim().equalsIgnoreCase("Labour Budget")){
							percValue = new BigDecimal(jObj.getString("PER_APP_LB")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("Agriculture Activities")){
							percValue = new BigDecimal(jObj.getString("ACHEIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("Horticulture") || divType.trim().toString().equalsIgnoreCase("Avenue")){
							percValue = new BigDecimal(jObj.getString("PERCENTAGEOFPLANTING")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("FAperformance")){
							percValue = new BigDecimal(jObj.getString("AVG_TOT_MARKS")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						}else{
							percValue = new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						}
						
		    				if(vo != null){
			    				if(Double.valueOf(percValue)  < 50){
		    						vo.setConstiInRed(vo.getConstiInRed()+1l);
		    					}else if(Double.valueOf(percValue)  >=50 && Double.valueOf(percValue) <=80){
		    						vo.setConstiInOrange(vo.getConstiInOrange()+1l);
	    						}else if(Double.valueOf(percValue)  >=80){
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
			if(inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().toString().length() > 0l)
				inputVO.setSublocationType(inputVO.getSublocaType().trim());
			
			String webServiceUrl = null;
			List<NregsDataVO> list = new ArrayList<NregsDataVO>(0);
			String str = null;
			
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Labour Budget"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/LabourBudgetServiceNew/LabourBudgetDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Farm Ponds"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FarmPondService_new/FarmPondData_new";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("IHHL"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/IHHLService_new/IHHLData_new";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Vermi Compost"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/VermiService_new/VermiData_new";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("GP Buildings"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/GpBuildingServiceNew/GpBuildingDataNew";
			/*else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR Jala Siri"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NtrsService/NtrsData";*/
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("CC Roads"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CCRoadsServicesNew/CCRoadsDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Anganwadi Buildings"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AnganawadiServiceNew/AnganawadiDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mandal Buildings"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MandalBuildingServiceNew/MandalBuildingDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR 90 Days"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HousingServiceNew/HousingDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Production of Bricks"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BricksServiceNew/BricksDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MulberyServiceNew/MulberyDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Silk Worms"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SilkwarmServiceNew/SilkwarmDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Cattle Drinking Water Troughs"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CattleServiceNew/CattleDataNew";//old AH
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Raising of Perinnial Fodders"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FodderServiceNew/FodderDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Solid Waste Management"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SolidWasteManagementServices/SolidWasteManagementData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Play Fields"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/PlayFieldsServices/PlayFieldsData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Burial Grounds"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BurialGroundsServices/BurialGroundsData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Agriculture Activities"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AgricultureServices/AgricultureData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Average Wage"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AveragewageServicesNew/AveragewageDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Average Days of Employment"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AverageDaysServicesNew/AverageDaysDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("HH Completed 100 Days"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HundredHoursServices/HundredHoursData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Timely Payment"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/TimePaymentsServicesNew/TimePaymentsDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Horticulture"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HorticultureServiceNew/HorticultureDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Avenue"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AvenueServicesNew/AvenueDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Nurseries"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NurseriesServiceNew/NurseriesDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Drying Platforms"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FishDryingServiceNew/FishDryingDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Ponds"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FishPondServiceNew/FishPondDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("FAperformance"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FaPerformanceServiceNew/FaPerformanceDataNew";
			
			 str = convertingInputVOToString(inputVO);
			 
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
	       
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
			 	    				vo.setPerAppLB(jObj.getString("PER_APP_LB"));
			 	    				vo.setWageExpenditure(jObj.getString("WAGEEXPENDITURE"));
			 	    				vo.setMaterialExpenditure(jObj.getString("MATERIALEXPENDITURE"));
			 	    				vo.setTotalExpenditure(jObj.getString("TOTALEXPENDITURE"));
			 	    				vo.setMaterialExpenditurePerc(jObj.getString("MATPERCENTAGE"));
			 	    				vo.setPercentage(jObj.getString("PER_APP_LB"));
			 	    				list.add(vo);
			 	    			}
		 	    			}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Average Wage") || 
		 	    					inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Average Days of Employment") ||
		 	    					inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("HH Completed 100 Days") || 
		 	    					inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Timely Payment")){
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
		 	    			}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Agriculture Activities") ||
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
				 	    				vo.setCompleted(jObj.getLong("COMPLETED"));
				 	    				if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Agriculture Activities")){
				 	    					vo.setAchivement(new BigDecimal(jObj.getString("ACHEIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				 	    					vo.setPercentage(new BigDecimal(jObj.getString("ACHEIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				 	    				}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Nurseries")){
				 	    					vo.setPercentage(jObj.getString("PERCENTAGE"));
				 	    				}
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
			 	    				if(inputVO.getSublocationType().trim().toString().equalsIgnoreCase("state") || inputVO.getSublocationType().trim().toString().equalsIgnoreCase("district")){
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
				 	    				//nregsDataVO.setNotGrounded(jObj.getString("NOTGROUNDED"));
				 	    				if(jObj.getString("NOTGROUNDED").trim().contains("-"))
				 	    					nregsDataVO.setNotGrounded("0");
				 	    				else
				 	    					nregsDataVO.setNotGrounded(jObj.getString("NOTGROUNDED"));
				 	    				nregsDataVO.setInProgress(jObj.getLong("INPROGRESS"));
				 	    				nregsDataVO.setCompleted(jObj.getLong("COMPLETED"));
				 	    				
				 	    				if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("state") ||
						 	    		   inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("district")){
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
			 	    				if(inputVO.getSublocationType().trim().toString().equalsIgnoreCase("state") || inputVO.getSublocationType().trim().toString().equalsIgnoreCase("district")){
			 	    					nregsDataVO.setTargetKMS(jObj.getString("TARGETKMS"));
			 	    					nregsDataVO.setSanctionedPerventage(jObj.getString("SANCTIONEDPERCENTAGE"));
			 	    				}
			 	    				if(inputVO.getSublocationType().trim().toString().equalsIgnoreCase("mandal") || inputVO.getSublocationType().trim().toString().equalsIgnoreCase("panchayat"))
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
			 	    				if(inputVO.getSublocationType().trim().toString().equalsIgnoreCase("state") || inputVO.getSublocationType().trim().toString().equalsIgnoreCase("district")){
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
			 	    		}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("FAperformance")){
			 	    				for(int i=0;i<finalArray.length();i++){
				 	    				NregsDataVO vo = new NregsDataVO();
				 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
				 	    				vo.setUniqueId(jObj.getLong("UNIQUEID"));
				 	    				if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("district"))
				 	    					vo.setDistrict(jObj.getString("DISTRICT_DESCRIPTION"));
				 	    				else
				 	    					vo.setDistrict(jObj.getString("DISTRICT"));
				 	    				
				 	    				vo.setConstituency(jObj.getString("ASSEMBLY"));
				 	    				vo.setMandal(jObj.getString("MANDAL"));
				 	    				vo.setPanchayat(jObj.getString("PANCHAYAT"));
				 	    				vo.setAvgDmdMarks(jObj.getString("AVG_DMD_MARKS"));
				 	    				vo.setAvgDMusterMarks(jObj.getString("AVG_D_MUSTER_MARKS"));
				 	    				vo.setAvgLbMarks(jObj.getString("AVG_LB_MARKS"));
				 	    				vo.setAvgRozgarDivasMarks(jObj.getString("AVG_ROZGAR_DIVAS_MARKS"));
				 	    				vo.setAvgDaysMarks(jObj.getString("AVG_AVG_DAYS_MARKS"));
				 	    				vo.setAvgAvgWageMarks(jObj.getString("AVG_AVG_WAGE_MARKS"));
				 	    				vo.setAvgFlagshipMarks(jObj.getString("AVG_FLAGSHIP_MARKS"));
				 	    				vo.setAvgTotMarks(jObj.getString("AVG_TOT_MARKS"));
				 	    				vo.setPercentage(jObj.getString("AVG_TOT_MARKS"));
				 	    				
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
			 	    				if(jObj.getString("NOTGROUNDED").trim().contains("-"))
			 	    					nregsDataVO.setNotGrounded("0");
			 	    				else
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
		 	    	
		 	    	if(inputVO.getSublocationType().trim().equalsIgnoreCase("district"))
		 	    	{
		 	    		if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("FAperformance"))
		 	    			inputVO.setLocationType("constituency");
		 	    		else
		 	    			inputVO.setSublocationType("constituency");
			 	    		
			 	    		str = convertingInputVOToString(inputVO);
				 	    	ClientResponse constResponse = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
				 	    	if(constResponse.getStatus() != 200){
					 	    	  throw new RuntimeException("Failed : HTTP error code : "+ constResponse.getStatus());
					 	      }else{
					 	    	 String constOutput = constResponse.getEntity(String.class);
					 	    	 
					 	    	 disConslist = getNregsConstCuntDetails(constOutput,distConstMap,inputVO.getDivType(),inputVO.getLocationType());
					 	      }
				 	    	
				 	    	distConstMap.clear();
				 	    	if(list != null && !list.isEmpty()){
				 	    		 for (NregsDataVO nregsDataVO : list) {
				 	    			 NregsDataVO filterVo = new NregsDataVO();
				 	    			 filterVo.setPercentage(nregsDataVO.getPercentage());
									distConstMap.put(nregsDataVO.getDistrict(),filterVo);
								}
				 	    	 }
				 	    	if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("FAperformance"))
				 	    		inputVO.setLocationType("mandal");
				 	    	else
				 	    		inputVO.setSublocationType("mandal");
				 	    	str = convertingInputVOToString(inputVO);
				 	    	ClientResponse mandalResponse = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
				 	    	if(mandalResponse.getStatus() != 200){
					 	    	  throw new RuntimeException("Failed : HTTP error code : "+ mandalResponse.getStatus());
					 	      }else{
					 	    	 String mandalOutput = mandalResponse.getEntity(String.class);
					 	    	 
					 	    	  disMandallist = getNregsMandalsCuntFrDistrict(mandalOutput,distConstMap,inputVO.getDivType(),inputVO.getLocationType());
					 	      }
				 	    	
				 	    	distConstMap.clear();
				 	    	if(list != null && !list.isEmpty()){
				 	    		 for (NregsDataVO nregsDataVO : list) {
				 	    			 NregsDataVO filterVo = new NregsDataVO();
				 	    			 filterVo.setPercentage(nregsDataVO.getPercentage());
									distConstMap.put(nregsDataVO.getDistrict(),filterVo);
								}
				 	    	 }
				 	    	if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("FAperformance"))
				 	    		inputVO.setLocationType("panchayat");
				 	    	else
				 	    		inputVO.setSublocationType("panchayat");
				 	    	str = convertingInputVOToString(inputVO);
				 	    	ClientResponse panchayatResponse = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
				 	    	if(panchayatResponse.getStatus() != 200){
					 	    	  throw new RuntimeException("Failed : HTTP error code : "+ panchayatResponse.getStatus());
					 	      }else{
					 	    	 String panchayatOutput = panchayatResponse.getEntity(String.class);
					 	    	 
					 	    	disVillageslist = getNregsVillageCuntFrDistrict(panchayatOutput,distConstMap,inputVO.getDivType(),inputVO.getLocationType());
					 	      }
				 	    	
				 	    	
				 	    	finalVO.getDistConsCuntList().addAll(disConslist);//How many Consti Contain red,Green,Orange In Districts
				 	    	finalVO.getDistMandalCuntList().addAll(disMandallist);//How many Mandals Contain red,Green,Orange In Districts
				 	    	finalVO.getDistMandalList().addAll(disVillageslist);//How many Panchayts Contain red,Green,Orange In Districts
				 	    	finalVO.getDistList().addAll(list);//District Details
				 	    	
		 	    	}else if(inputVO.getSublocationType().trim().equalsIgnoreCase("constituency"))
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
				 	    	if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("FAperformance"))
				 	    		inputVO.setLocationType("mandal");
				 	    	else
			 	    		inputVO.setSublocationType("mandal");
			 	    		str = convertingInputVOToString(inputVO);
				 	    	ClientResponse mandalResponse = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
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
				 	    	if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("FAperformance"))
				 	    		inputVO.setLocationType("panchayat");
				 	    	else
				 	    		inputVO.setSublocationType("panchayat");
				 	    	str = convertingInputVOToString(inputVO);
				 	    	ClientResponse panchaytResponse = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
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
				 	    	
		 	    	  }else if(inputVO.getSublocationType().trim().equalsIgnoreCase("mandal"))
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
			 	    		if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("FAperformance"))
				 	    		inputVO.setLocationType("panchayat");
			 	    		else
			 	    			inputVO.setSublocationType("panchayat");
			 	    		str = convertingInputVOToString(inputVO);
				 	    	ClientResponse panchayResponse = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
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
				 	    	
			 	    }else if(inputVO.getSublocationType().trim().equalsIgnoreCase("panchayat"))
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
	public List<NregsDataVO> getNregsMandalsCuntFrDistrict(String output,Map<String,NregsDataVO> cntMap,String divType,String locationType){
		List<NregsDataVO> retVOList = new ArrayList<NregsDataVO>(0);
		try{
			//Map<String,NregsDataVO> cntMap = new HashMap<String,NregsDataVO>(0);
			String percValue = null;
			String distrName = null;
			if(output != null && !output.isEmpty()){
	    		JSONArray finalArray = new JSONArray(output);
	    		if(finalArray!=null && finalArray.length()>0){
	    			for(int i=0;i<finalArray.length();i++){
	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	    				if(divType != null && divType.trim().toString().equalsIgnoreCase("FAperformance")){
	    					if(locationType != null && locationType.trim().equalsIgnoreCase("district")){
	    						distrName = jObj.getString("DISTRICT_DESCRIPTION");
	    					}else{
	    						distrName = jObj.getString("DISTRICT");
	    					}
	    				}else{
	    					distrName = jObj.getString("DISTRICT");
	    				}
		    			
						NregsDataVO vo = cntMap.get(distrName);
						if(divType != null && divType.trim().equalsIgnoreCase("Labour Budget")){
							percValue = new BigDecimal(jObj.getString("PER_APP_LB")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("Agriculture Activities")){
							percValue = new BigDecimal(jObj.getString("ACHEIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("Horticulture") || divType.trim().toString().equalsIgnoreCase("Avenue")){
							percValue = new BigDecimal(jObj.getString("PERCENTAGEOFPLANTING")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("FAperformance")){
							percValue = new BigDecimal(jObj.getString("AVG_TOT_MARKS")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						}else{
							percValue = new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						} 
		    				if(vo != null) {
			    				if(Double.valueOf(percValue)  < 50){
		    						vo.setMandalsInRed(vo.getMandalsInRed()+1l);
		    					}else if(Double.valueOf(percValue)  >=50 && Double.valueOf(percValue) <=80){
		    						vo.setMandalsInOrange(vo.getMandalsInOrange()+1l);
	    						}else if(Double.valueOf(percValue)  >=80){
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
			String constName = null;
			if(output != null && !output.isEmpty()){
	    		JSONArray finalArray = new JSONArray(output);
	    		if(finalArray!=null && finalArray.length()>0){
	    			for(int i=0;i<finalArray.length();i++){
	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	    				if(divType != null && divType.trim().toString().equalsIgnoreCase("FAperformance"))
	    					constName = jObj.getString("Assembly");
	    				else
	    					constName = jObj.getString("CONSTITUENCY");
						NregsDataVO vo = cntMap.get(constName);
						if(divType != null && divType.trim().equalsIgnoreCase("Labour Budget")){
							percValue = new BigDecimal(jObj.getString("PER_APP_LB")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("Agriculture Activities")){
							percValue = new BigDecimal(jObj.getString("ACHEIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("Horticulture") || divType.trim().toString().equalsIgnoreCase("Avenue")){
							percValue = new BigDecimal(jObj.getString("PERCENTAGEOFPLANTING")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("FAperformance")){
							percValue = new BigDecimal(jObj.getString("AVG_TOT_MARKS")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						}else{
							percValue = new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						} 
		    				if(vo != null)
		    				{
			    				if(Double.valueOf(percValue)  < 50){
		    						vo.setMandalsInRed(vo.getMandalsInRed()+1l);
		    					}else if(Double.valueOf(percValue)  >=50 && Double.valueOf(percValue) <=80){
		    						vo.setMandalsInOrange(vo.getMandalsInOrange()+1l);
	    						}else if(Double.valueOf(percValue)  >=80){
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
				else if(type.trim().equalsIgnoreCase("orange") && Double.valueOf(nregsDataVO.getPercentage()) <= 80 && Double.valueOf(nregsDataVO.getPercentage()) >=50)
					filterList.add(nregsDataVO);
				else if(type.trim().equalsIgnoreCase("green") && Double.valueOf(nregsDataVO.getPercentage()) >= 80)
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
			//get failure webservice details.
			List<Object[]> failureList = webserviceCallDetailsDAO.getWebserviceFailureDetails(startDate, endDate);
			//create a map for webserviceId and failure count map.
			Map<Long,Long> webserviceIdAndFailureCountMap = new HashMap<Long,Long>();
			if(failureList != null && failureList.size() > 0){
				for(Object[] param : failureList){
					webserviceIdAndFailureCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[1]));
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
						detailsVO.setNoResponce(commonMethodsUtilService.getLongValueForObject(webserviceIdAndFailureCountMap.get(param.getKey())));
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
	public List<NregsDataVO> getNregsVillageCuntFrDistrict(String output,Map<String,NregsDataVO> cntMap,String divType,String locationType){
		List<NregsDataVO> retVOList = new ArrayList<NregsDataVO>(0);
		try{
			//Map<String,NregsDataVO> cntMap = new HashMap<String,NregsDataVO>(0);
			String percValue = null;
			String distrName = null;
			if(output != null && !output.isEmpty()){
	    		JSONArray finalArray = new JSONArray(output);
	    		if(finalArray!=null && finalArray.length()>0){
	    			for(int i=0;i<finalArray.length();i++){
	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	    				if(divType != null && divType.trim().toString().equalsIgnoreCase("FAperformance")){
	    					if(locationType != null && locationType.trim().equalsIgnoreCase("district")){
	    						distrName = jObj.getString("DISTRICT_DESCRIPTION");
	    					}else{
	    						distrName = jObj.getString("DISTRICT");
	    					}
	    				}else{
	    					distrName = jObj.getString("DISTRICT");
	    				}
						NregsDataVO vo = cntMap.get(distrName);
						if(divType != null && divType.trim().equalsIgnoreCase("Labour Budget")){
							percValue = new BigDecimal(jObj.getString("PER_APP_LB")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("Agriculture Activities")){
							percValue = new BigDecimal(jObj.getString("ACHEIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("Horticulture") || divType.trim().toString().equalsIgnoreCase("Avenue")){
							percValue = new BigDecimal(jObj.getString("PERCENTAGEOFPLANTING")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("FAperformance")){
							percValue = new BigDecimal(jObj.getString("AVG_TOT_MARKS")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						}else{
							percValue = new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						} 
		    				if(vo != null) {
			    				if(Double.valueOf(percValue)  < 50){
		    						vo.setVillagesInRed(vo.getVillagesInRed()+1l);
		    					}else if(Double.valueOf(percValue)  >=50 && Double.valueOf(percValue) <=80){
		    						vo.setVillagesInOrange(vo.getVillagesInOrange()+1l);
	    						}else if(Double.valueOf(percValue)  >=80){
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
			String constName = null;
			if(output != null && !output.isEmpty()){
	    		JSONArray finalArray = new JSONArray(output);
	    		if(finalArray!=null && finalArray.length()>0){
	    			for(int i=0;i<finalArray.length();i++){
	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	    				if(divType != null && divType.trim().toString().equalsIgnoreCase("FAperformance"))
	    					constName = jObj.getString("Assembly");
	    				else
	    					constName = jObj.getString("CONSTITUENCY");
						NregsDataVO vo = cntMap.get(constName);
						if(divType != null && divType.trim().equalsIgnoreCase("Labour Budget")){
							percValue = new BigDecimal(jObj.getString("PER_APP_LB")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("Agriculture Activities")){
							percValue = new BigDecimal(jObj.getString("ACHEIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("Horticulture") || divType.trim().toString().equalsIgnoreCase("Avenue")){
							percValue = new BigDecimal(jObj.getString("PERCENTAGEOFPLANTING")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("FAperformance")){
							percValue = new BigDecimal(jObj.getString("AVG_TOT_MARKS")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						}else{
							percValue = new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						} 
		    				if(vo != null)
		    				{
			    				if(Double.valueOf(percValue)  < 50){
		    						vo.setVillagesInRed(vo.getVillagesInRed()+1l);
		    					}else if(Double.valueOf(percValue)  >=50 && Double.valueOf(percValue) <=80){
		    						vo.setVillagesInOrange(vo.getVillagesInOrange()+1l);
	    						}else if(Double.valueOf(percValue)  >=80){
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
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("FAperformance")){
							percValue = new BigDecimal(jObj.getString("AVG_TOT_MARKS")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						}else{
							percValue = new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						} 
		    				if(vo != null)
		    				{
			    				if(Double.valueOf(percValue)  < 50){
		    						vo.setVillagesInRed(vo.getVillagesInRed()+1l);
		    					}else if(Double.valueOf(percValue)  >=50 && Double.valueOf(percValue) <=80){
		    						vo.setVillagesInOrange(vo.getVillagesInOrange()+1l);
	    						}else if(Double.valueOf(percValue)  >=80){
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
			if(inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().toString().length() > 0l)
				inputVO.setSublocationType(inputVO.getSublocaType().trim());
			
			String webServiceUrl = null;
			
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Average Wage"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AveragewageServicesNew/AveragewageDataNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AverageWageService/AvgWageData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Average Days of Employment"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AverageDaysServicesNew/AverageDaysDataNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AverageDaysService/AvgDaysData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("HH Completed 100 Days"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HundredHoursServices/HundredHoursData";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AP100DaysService/AP100DaysData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Timely Payment"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/TimePaymentsServicesNew/TimePaymentsDataNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/TimePaymentService/TPData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Nurseries"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NurseriesServiceNew/NurseriesDataNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NurseriesService/NurseriesData
			
			String str = convertingInputVOToString(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
	        
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
		 	    					if(inputVO.getSublocationType().trim().equalsIgnoreCase("state"))
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
			if(inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().toString().length() > 0l)
				inputVO.setSublocationType(inputVO.getSublocaType().trim());
			
			String str = convertingInputVOToString(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AgricultureServices/AgricultureData", str);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("state")){
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
			if(inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().toString().length() > 0l)
				inputVO.setSublocationType(inputVO.getSublocaType().trim());
			
			String webServiceUrl = null;
			
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Horticulture"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HorticultureServiceNew/HorticultureDataNew";
			
			String str = convertingInputVOToString(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
	        
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
	 	    				if(inputVO.getSublocationType().trim().toString().equalsIgnoreCase("state") || inputVO.getSublocationType().trim().toString().equalsIgnoreCase("district")){
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
			if(inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().toString().length() > 0l)
				inputVO.setSublocationType(inputVO.getSublocaType().trim());
			
			String webServiceUrl = null;
			
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Avenue"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AvenueServicesNew/AvenueDataNew";
			
			String str = convertingInputVOToString(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
	        
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
	 	    				if(inputVO.getSublocationType().trim().toString().equalsIgnoreCase("state") || inputVO.getSublocationType().trim().toString().equalsIgnoreCase("district")){
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
			if(inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().toString().length() > 0l)
				inputVO.setSublocationType(inputVO.getSublocaType().trim());
			
			String webServiceUrl = null;
			
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("CC Roads"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CCRoadsServicesNew/CCRoadsDataNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CCRoadsService/CCRoadsData
			 
			String str = convertingInputVOToString(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
	        
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
	 	    				if(inputVO.getSublocationType().trim().toString().equalsIgnoreCase("state") || inputVO.getSublocationType().trim().toString().equalsIgnoreCase("district")){
	 	    					vo.setTargetKMS(jObj.getString("TARGETKMS"));
	 	    					vo.setSanctionedPerventage(jObj.getString("SANCTIONEDPERCENTAGE"));
	 	    				}
	 	    				if(inputVO.getSublocationType().trim().toString().equalsIgnoreCase("mandal") || inputVO.getSublocationType().trim().toString().equalsIgnoreCase("panchayat"))
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
	public List<NregsDataVO> getNregaParliamentData(InputVO inputVO){
		List<NregsDataVO> finalVOList = new ArrayList<NregsDataVO>(0);
		try {
			String webServiceUrl = null;
			Map<Long,List<String>> consParlMap = new HashMap<Long,List<String>>(0);
			List<Long> parlIds = new ArrayList<Long>(0);
			Map<Long,String> parlmentMap = new HashMap<Long,String>(0);
			List<Long> constuIds = new ArrayList<Long>(0);
			List<NregsDataVO> consList = new ArrayList<NregsDataVO>(0);
			
			String str = null;
			if(inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().toString().length() > 0l)
				inputVO.setSublocationType(inputVO.getSublocaType().trim());
			
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Labour Budget"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/LabourBudgetServiceNew/LabourBudgetDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Farm Ponds"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FarmPondService_new/FarmPondData_new";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("IHHL"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/IHHLService_new/IHHLData_new";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Vermi Compost"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/VermiService_new/VermiData_new";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("GP Buildings"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/GpBuildingServiceNew/GpBuildingDataNew";
			/*else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR Jala Siri"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NtrsService/NtrsData";*/
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("CC Roads"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CCRoadsServicesNew/CCRoadsDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Anganwadi Buildings"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AnganawadiServiceNew/AnganawadiDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mandal Buildings"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MandalBuildingServiceNew/MandalBuildingDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR 90 Days"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HousingServiceNew/HousingDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Production of Bricks"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BricksServiceNew/BricksDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MulberyServiceNew/MulberyDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Silk Worms"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SilkwarmServiceNew/SilkwarmDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Cattle Drinking Water Troughs"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CattleServiceNew/CattleDataNew";//old AH
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Raising of Perinnial Fodders"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FodderServiceNew/FodderDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Solid Waste Management"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SolidWasteManagementServices/SolidWasteManagementData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Play Fields"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/PlayFieldsServices/PlayFieldsData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Burial Grounds"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BurialGroundsServices/BurialGroundsData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Agriculture Activities"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AgricultureServices/AgricultureData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Average Wage"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AveragewageServicesNew/AveragewageDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Average Days of Employment"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AverageDaysServicesNew/AverageDaysDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("HH Completed 100 Days"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HundredHoursServices/HundredHoursData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Timely Payment"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/TimePaymentsServicesNew/TimePaymentsDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Horticulture"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HorticultureServiceNew/HorticultureDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Avenue"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AvenueServicesNew/AvenueDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Nurseries"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NurseriesServiceNew/NurseriesDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Drying Platforms"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FishDryingServiceNew/FishDryingDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Ponds"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FishPondServiceNew/FishPondDataNew";
			
			 str = convertingInputVOToString(inputVO);
			 
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
	       
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
			 	    				vo.setPerAppLB(jObj.getString("PER_APP_LB"));
			 	    				vo.setWageExpenditure(jObj.getString("WAGEEXPENDITURE"));
			 	    				vo.setMaterialExpenditure(jObj.getString("MATERIALEXPENDITURE"));
			 	    				vo.setTotalExpenditure(jObj.getString("TOTALEXPENDITURE"));
			 	    				vo.setMaterialExpenditurePerc(jObj.getString("MATPERCENTAGE"));
			 	    				vo.setPercentage(jObj.getString("PER_APP_LB"));
			 	    				consList.add(vo);
			 	    			}
		 	    			}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Average Wage") || 
		 	    					inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Average Days of Employment") ||
		 	    					inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("HH Completed 100 Days") || 
		 	    					inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Timely Payment")){
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
				 	    				consList.add(vo);
				 	    		}
		 	    			}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Agriculture Activities") ||
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
				 	    				vo.setCompleted(jObj.getLong("COMPLETED"));
				 	    				if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Agriculture Activities")){
				 	    					vo.setAchivement(new BigDecimal(jObj.getString("ACHEIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				 	    					vo.setPercentage(new BigDecimal(jObj.getString("ACHEIVEMENT")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				 	    				}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Nurseries")){
				 	    					vo.setPercentage(jObj.getString("PERCENTAGE"));
				 	    				}
				 	    				consList.add(vo);
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
			 	    				if(inputVO.getSublocationType().trim().toString().equalsIgnoreCase("state") || inputVO.getSublocationType().trim().toString().equalsIgnoreCase("district")){
			 	    					vo.setTargetACRES(jObj.getString("TARGETACRES"));
				 	    				vo.setSanctionedPerventage(jObj.getString("SANCTIONEDPERCENTAGE"));
			 	    				}
			 	    				vo.setSanctionedACRES(jObj.getString("SANCTIONEDACRES"));
			 	    				vo.setPittingArea(jObj.getString("PITTINGAREA"));
			 	    				vo.setPlantingArea(jObj.getString("PLANTINGAREA"));
			 	    				vo.setPencentageOfPlanting(jObj.getString("PERCENTAGEOFPLANTING"));
			 	    				vo.setPercentage(jObj.getString("PERCENTAGEOFPLANTING"));
			 	    				consList.add(vo);
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
				 	    				//nregsDataVO.setNotGrounded(jObj.getString("NOTGROUNDED"));
				 	    				if(jObj.getString("NOTGROUNDED").trim().contains("-"))
				 	    					nregsDataVO.setNotGrounded("0");
				 	    				else
				 	    					nregsDataVO.setNotGrounded(jObj.getString("NOTGROUNDED"));
				 	    				nregsDataVO.setInProgress(jObj.getLong("INPROGRESS"));
				 	    				nregsDataVO.setCompleted(jObj.getLong("COMPLETED"));
				 	    				
				 	    				if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("state") ||
						 	    		   inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("district")){
				 	    					nregsDataVO.setSanctionedTarget(jObj.getString("SANCTIONEDTARGET"));
				 	    					nregsDataVO.setSanctionedPerventage(jObj.getString("SANCTIONEDPERCENTAGE"));
				 	    				}
				 	    					
				 	    				nregsDataVO.setPercentage(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());	 	    				
				 	    				consList.add(nregsDataVO);	 
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
			 	    				if(inputVO.getSublocationType().trim().toString().equalsIgnoreCase("state") || inputVO.getSublocationType().trim().toString().equalsIgnoreCase("district")){
			 	    					nregsDataVO.setTargetKMS(jObj.getString("TARGETKMS"));
			 	    					nregsDataVO.setSanctionedPerventage(jObj.getString("SANCTIONEDPERCENTAGE"));
			 	    				}
			 	    				if(inputVO.getSublocationType().trim().toString().equalsIgnoreCase("mandal") || inputVO.getSublocationType().trim().toString().equalsIgnoreCase("panchayat"))
			 	    					nregsDataVO.setSanctionedAmount(jObj.getString("SANCTIONED_AMOUNT"));
			 	    				else
			 	    					nregsDataVO.setSanctionedAmount(jObj.getString("SANCTIONEDAMOUNT"));
			 	    				nregsDataVO.setSanctionedKMS(jObj.getString("SANCTIONEDKMS"));
			 	    				nregsDataVO.setExpenditureAmount(jObj.getString("EXPENDITUREAMOUNT"));
			 	    				nregsDataVO.setCompletedKMS(jObj.getString("COMPLETEDKMS"));
			 	    				nregsDataVO.setPercentage(jObj.getString("PERCENTAGE"));
			 	    				
			 	    				consList.add(nregsDataVO);
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
			 	    				if(inputVO.getSublocationType().trim().toString().equalsIgnoreCase("state") || inputVO.getSublocationType().trim().toString().equalsIgnoreCase("district")){
			 	    					vo.setTargetKMS(jObj.getString("TARGETKMS"));
				 	    				vo.setSanctionedPerventage(jObj.getString("SANCTIONEDPERCENTAGE"));
			 	    				}
			 	    				vo.setSanctionedKMS(jObj.getString("SANCTIONEDKMS"));
			 	    				vo.setPittingKMS(jObj.getString("PITTINGKMS"));
			 	    				vo.setPlantingKMS(jObj.getString("PLANTINGKMS"));
			 	    				vo.setPencentageOfPlanting(jObj.getString("PERCENTAGEOFPLANTING"));
			 	    				vo.setPercentage(jObj.getString("PERCENTAGEOFPLANTING"));
			 	    				consList.add(vo);
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
			 	    				if(jObj.getString("NOTGROUNDED").trim().contains("-"))
			 	    					nregsDataVO.setNotGrounded("0");
			 	    				else
			 	    					nregsDataVO.setNotGrounded(jObj.getString("NOTGROUNDED"));
			 	    				nregsDataVO.setInProgress(jObj.getLong("INPROGRESS"));
			 	    				nregsDataVO.setCompleted(jObj.getLong("COMPLETED"));
			 	    				nregsDataVO.setPercentage(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());	 	    				
			 	    				consList.add(nregsDataVO);	 
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
						List<String> constIdsList = consParlMap.get(parlmentId);
						if(constIdsList == null){
							constIdsList = new ArrayList<String>(0);
							constIdsList.add(commonMethodsUtilService.getStringValueForObject(objects[0]));
							consParlMap.put(parlmentId,constIdsList);
						}else{
							constIdsList.add(commonMethodsUtilService.getStringValueForObject(objects[0]));
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
			
			List<String> constIds = new ArrayList<String>();
			Long parlmentId = 0l;
			if(commonMethodsUtilService.isMapValid(consParlMap)){
			for(Entry<Long,List<String>> param : consParlMap.entrySet()){
				 parlmentId = param.getKey();
				 constIds  = param.getValue();
				 NregsDataVO finalVO = new NregsDataVO();
				 if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Labour Budget")){
					 if(commonMethodsUtilService.isListOrSetValid(constIds)){
						for (String constId : constIds) {
							NregsDataVO vo = getMatchedVO(consList,Long.valueOf(constId));
							if(vo != null){
								finalVO.setTargetPersonDays(vo.getTargetPersonDays()+finalVO.getTargetPersonDays());
								finalVO.setGeneratedPersonDays(vo.getGeneratedPersonDays()+finalVO.getGeneratedPersonDays());
								finalVO.setPerAppLB(String.valueOf(Double.valueOf(vo.getPerAppLB())+Double.valueOf(finalVO.getPerAppLB())));
								finalVO.setWageExpenditure(String.valueOf(Double.valueOf(vo.getWageExpenditure())+Double.valueOf(finalVO.getWageExpenditure())));
								finalVO.setMaterialExpenditure(String.valueOf(Double.valueOf(vo.getMaterialExpenditure())+Double.valueOf(finalVO.getMaterialExpenditure())));
								finalVO.setTotalExpenditure(String.valueOf(Double.valueOf(vo.getTotalExpenditure())+Double.valueOf(finalVO.getTotalExpenditure())));
								finalVO.setMaterialExpenditurePerc(String.valueOf(Double.valueOf(vo.getMaterialExpenditurePerc())+Double.valueOf(finalVO.getMaterialExpenditurePerc())));
							}
						}
					}
					finalVO.setParliamentId(parlmentId);
					finalVO.setParliamentName(parlmentMap.get(parlmentId));
					finalVOList.add(finalVO);
				 }else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Average Wage") || 
    					inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Average Days of Employment") ||
    					inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("HH Completed 100 Days") || 
    					inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Timely Payment")){
					if(commonMethodsUtilService.isListOrSetValid(constIds)){
						for (String constId : constIds) {
							NregsDataVO vo = getMatchedVO(consList,Long.valueOf(constId));
							if(vo != null){
								finalVO.setTarget(vo.getTarget()+finalVO.getTarget());
								finalVO.setAchivement(String.valueOf(Double.valueOf(vo.getAchivement())+Double.valueOf(finalVO.getAchivement())));
								finalVO.setPercentage(String.valueOf(Double.valueOf(vo.getPercentage())+Double.valueOf(finalVO.getPercentage())));
							}
						}
					}
					finalVO.setParliamentId(parlmentId);
					finalVO.setParliamentName(parlmentMap.get(parlmentId));
					finalVOList.add(finalVO);
				}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Agriculture Activities") ||
	    				 inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Nurseries")){
					if(commonMethodsUtilService.isListOrSetValid(constIds)){
						for (String constId : constIds) {
							NregsDataVO vo = getMatchedVO(consList,Long.valueOf(constId));
							if(vo != null){
								finalVO.setTarget(vo.getTarget()+finalVO.getTarget());
								finalVO.setCompleted(vo.getCompleted()+finalVO.getCompleted());
								if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Agriculture Activities")) 
									finalVO.setAchivement(String.valueOf(Double.valueOf(vo.getAchivement())+Double.valueOf(finalVO.getAchivement())));
								else
									finalVO.setPercentage(String.valueOf(Double.valueOf(vo.getPercentage())+Double.valueOf(finalVO.getPercentage())));
							}
						}
					}
					finalVO.setParliamentId(parlmentId);
					finalVO.setParliamentName(parlmentMap.get(parlmentId));
					finalVOList.add(finalVO);
				}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Horticulture")){
					if(commonMethodsUtilService.isListOrSetValid(constIds)){
						for (String constId : constIds) {
							NregsDataVO vo = getMatchedVO(consList,Long.valueOf(constId));
							if(vo != null){
								finalVO.setSanctionedACRES(String.valueOf(Long.valueOf(vo.getSanctionedACRES())+Long.valueOf(finalVO.getSanctionedACRES())));
								finalVO.setPittingArea(String.valueOf(Double.valueOf(vo.getPittingArea())+Double.valueOf(finalVO.getPittingArea())));
								finalVO.setPlantingArea(String.valueOf(Double.valueOf(vo.getPlantingArea())+Double.valueOf(finalVO.getPlantingArea())));
								finalVO.setPencentageOfPlanting(String.valueOf(Double.valueOf(vo.getPencentageOfPlanting())+Double.valueOf(finalVO.getPencentageOfPlanting())));
							}
						}
					}
					finalVO.setParliamentId(parlmentId);
					finalVO.setParliamentName(parlmentMap.get(parlmentId));
					finalVOList.add(finalVO);
				}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("CC Roads")){
					if(commonMethodsUtilService.isListOrSetValid(constIds)){
						for (String constId : constIds) {
							NregsDataVO vo = getMatchedVO(consList,Long.valueOf(constId));
							if(vo != null){
								finalVO.setSanctionedAmount(String.valueOf(Double.valueOf(vo.getSanctionedAmount())+Double.valueOf(finalVO.getSanctionedAmount())));
								finalVO.setSanctionedKMS(String.valueOf(Long.valueOf(vo.getSanctionedKMS())+Long.valueOf(finalVO.getSanctionedKMS())));
								finalVO.setExpenditureAmount(String.valueOf(Double.valueOf(vo.getExpenditureAmount())+Double.valueOf(finalVO.getExpenditureAmount())));
								finalVO.setCompletedKMS(String.valueOf(Double.valueOf(vo.getCompletedKMS())+Double.valueOf(finalVO.getCompletedKMS())));
								finalVO.setPercentage(String.valueOf(Double.valueOf(vo.getPercentage())+Double.valueOf(finalVO.getPercentage())));
							}
						}
					}
					finalVO.setParliamentId(parlmentId);
					finalVO.setParliamentName(parlmentMap.get(parlmentId));
					finalVOList.add(finalVO);
				}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Avenue")){
					if(commonMethodsUtilService.isListOrSetValid(constIds)){
						for (String constId : constIds) {
							NregsDataVO vo = getMatchedVO(consList,Long.valueOf(constId));
							if(vo != null){
								finalVO.setSanctionedKMS(String.valueOf(Long.valueOf(vo.getSanctionedKMS())+Long.valueOf(finalVO.getSanctionedKMS())));
								finalVO.setPittingKMS(String.valueOf(Long.valueOf(vo.getPittingKMS())+Long.valueOf(finalVO.getPittingKMS())));
								finalVO.setPlantingKMS(String.valueOf(Long.valueOf(vo.getPlantingKMS())+Long.valueOf(finalVO.getPlantingKMS())));
								finalVO.setPencentageOfPlanting(String.valueOf(Double.valueOf(vo.getPencentageOfPlanting())+Double.valueOf(finalVO.getPencentageOfPlanting())));
							}
						}
					}
					finalVO.setParliamentId(parlmentId);
					finalVO.setParliamentName(parlmentMap.get(parlmentId));
					finalVOList.add(finalVO);
	 	    	}else{
					if(commonMethodsUtilService.isListOrSetValid(constIds)){
						for (String constId : constIds) {
							NregsDataVO vo = getMatchedVO(consList,Long.valueOf(constId));
							if(vo != null){
								finalVO.setTarget(vo.getTarget()+finalVO.getTarget());
								finalVO.setGrounded(String.valueOf(Long.valueOf(vo.getGrounded())+Long.valueOf(finalVO.getGrounded())));
								finalVO.setNotGrounded(String.valueOf(Long.valueOf(vo.getNotGrounded())+Double.valueOf(finalVO.getNotGrounded())));
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
	public NregsDataVO getMatchedVO(List<NregsDataVO> list,Long constId){
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
	}
	/*
	 * Date : 16/06/2017
	 * Author :Nandhini
	 * @description : getNREGSProjectsAbstractNew
	 */
	public List<NregsProjectsVO> getNREGSProjectsAbstractNew(InputVO inputVO){
		List<NregsProjectsVO> voList = new ArrayList<NregsProjectsVO>(0);
		try {
			String projectType = null;
			String str = convertingInputVOToString(inputVO);
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CMDashBoard/AbstractNew", str);
	        
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
	 	    					
	 	    					if(inputVO.getLocationType().trim().equalsIgnoreCase("district"))
	 	    						vo.setType(projectTypeArr[1]);
	 	    					else
	 	    						vo.setType("STATE");
	 	    					
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
			String str = convertingInputVOToString(inputVO);
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CMDashBoard/AbstractNew", str);
			
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
	 	    				
	 	    				if(inputVO.getType().toString().trim().equalsIgnoreCase("Nurseries") && inputVO.getLocationType().toString().trim().equalsIgnoreCase("state"))
	 	    					vo.setParameter(jObj.getString("'NURSERIES'"));
	 	    				else
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
	 	    				else if(inputVO.getType().toString().trim().equalsIgnoreCase("Payments")){
	 	    					vo.setFTONOTGENCNT(jObj.getString("FTONOTGENCNT"));
	 	    					vo.setFTONOTUPLOADCNT(jObj.getString("FTONOTUPLOADCNT"));
	 	    					vo.setFTONOTSENTCNT(jObj.getString("FTONOTSENTCNT"));
	 	    					vo.setREJECTCNT(jObj.getString("REJECTCNT"));
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
	 	    				else if(inputVO.getType().toString().trim().equalsIgnoreCase("Payments"))
	 	    					vo.setPENDINGRESPONSECNT(jObj.getString("PENDINGRESPONSECNT"));
	 	    				else
	 	    					vo.setPercentage(jObj.getString("PERCENTAGE"));
	 	    				
	 	    				if(inputVO.getLocationType().trim().equalsIgnoreCase("state"))
	 	    					vo.setType("STATE");
	 	    				else if(vo.getParameter().contains("state") || vo.getParameter().contains("State") || vo.getParameter().contains("STATE"))
	 	    					vo.setType("STATE");
	 	    				else if(vo.getParameter().contains("district") || vo.getParameter().contains("District") || vo.getParameter().contains("DISTRICT"))
	 	    					vo.setType("DISTRICT");
	 	    				
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
	 * @description : getNregaLevelsWiseDataForFTOPayments
	 */
	public List<NregsDataVO> getNregaLevelsWiseDataForTimelyPayments(InputVO inputVO){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
			if(inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().toString().length() > 0l)
				inputVO.setSublocationType(inputVO.getSublocaType().trim());
			
			String webServiceUrl = null;
			
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Payments"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/PaymentsDataService/PaymentsData";
			
			String str = convertingInputVOToString(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
	        
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
	
	/*
	 * Date : 28/06/2017
	 * Author :Sravanth
	 * @description : getNregaLevelsWiseDataForNewFTOPayments
	 */
	public List<NregaPaymentsVO> getNregaLevelsWiseDataForNewFTOPayments(InputVO inputVO){
		List<NregaPaymentsVO> voList = new ArrayList<NregaPaymentsVO>(0);
		try {
			if(inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().toString().length() > 0l)
				inputVO.setSublocationType(inputVO.getSublocaType().trim());
			
			String webServiceUrl = null;
			Map<String,NregaPaymentsVO> namingMap = new HashMap<String,NregaPaymentsVO>(0);
			
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Payments"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/PaymentsDataNewServices/PaymentsDataNew";
			
			String str = convertingInputVOToString(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    				for(int i=0;i<finalArray.length();i++){
	 	    					NregaPaymentsVO vo = new NregaPaymentsVO();
		 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
		 	    				vo.setId(jObj.getString("UNIQUEID"));
		 	    				if(jObj.getString("TYPE") != null && jObj.getString("TYPE").trim().equalsIgnoreCase("Sub Total")){
		 	    					NregaPaymentsVO mapVO = namingMap.get(jObj.getString("UNIQUEID"));
		 	    					if(mapVO != null){
		 	    						if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("state")){
			 	    						if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("district"))
			 	    							vo.setDistrictName(mapVO.getDistrictName());
			 	    						else if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("constituency")){
			 	    							vo.setDistrictName(mapVO.getDistrictName());
			 	    							vo.setConstName(mapVO.getConstName());
			 	    						}else if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("mandal")){
			 	    							vo.setDistrictName(mapVO.getDistrictName());
			 	    							vo.setConstName(mapVO.getConstName());
			 	    							vo.setMandalName(mapVO.getMandalName());
			 	    						}else if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("panchayat")){
			 	    							vo.setDistrictName(mapVO.getDistrictName());
			 	    							vo.setConstName(mapVO.getConstName());
			 	    							vo.setMandalName(mapVO.getMandalName());
			 	    							vo.setPanchayatName(mapVO.getPanchayatName());
			 	    						}
			 	    					}else if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("district")){
			 	    						if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("constituency")){
			 	    							vo.setDistrictName(mapVO.getDistrictName());
			 	    							vo.setConstName(mapVO.getConstName());
			 	    						}else if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("mandal")){
			 	    							vo.setDistrictName(mapVO.getDistrictName());
			 	    							vo.setConstName(mapVO.getConstName());
			 	    							vo.setMandalName(mapVO.getMandalName());
			 	    						}else if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("panchayat")){
			 	    							vo.setDistrictName(mapVO.getDistrictName());
			 	    							vo.setConstName(mapVO.getConstName());
			 	    							vo.setMandalName(mapVO.getMandalName());
			 	    							vo.setPanchayatName(mapVO.getPanchayatName());
			 	    						}
			 	    					}else if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("constituency")){
			 	    						if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("constituency")){
			 	    							vo.setConstName(mapVO.getConstName());
			 	    						}else if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("mandal")){
			 	    							vo.setDistrictName(mapVO.getDistrictName());
			 	    							vo.setConstName(mapVO.getConstName());
			 	    							vo.setMandalName(mapVO.getMandalName());
			 	    						}else if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("panchayat")){
			 	    							vo.setDistrictName(mapVO.getDistrictName());
			 	    							vo.setConstName(mapVO.getConstName());
			 	    							vo.setMandalName(mapVO.getMandalName());
			 	    							vo.setPanchayatName(mapVO.getPanchayatName());
			 	    						}
			 	    					}else if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("mandal")){
			 	    					  if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("panchayat")){
			 	    							vo.setDistrictName(mapVO.getDistrictName());
			 	    							vo.setConstName(mapVO.getConstName());
			 	    							vo.setMandalName(mapVO.getMandalName());
			 	    							vo.setPanchayatName(mapVO.getPanchayatName());
			 	    						}
			 	    					}
		 	    					}
		 	    				}else{
		 	    					if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("state")){
		 	    						if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("district"))
		 	    							vo.setDistrictName(jObj.getString("DISTRICT"));
		 	    						else if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("constituency")){
		 	    							vo.setDistrictName(jObj.getString("DISTRICT"));
		 	    							vo.setConstName(jObj.getString("CONSTITUENCY"));
		 	    						}else if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("mandal")){
		 	    							vo.setDistrictName(jObj.getString("DISTRICT"));
		 	    							vo.setConstName(jObj.getString("CONSTITUENCY"));
		 	    							vo.setMandalName(jObj.getString("MANDAL"));
		 	    						}else if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("panchayat")){
		 	    							vo.setDistrictName(jObj.getString("DISTRICT"));
		 	    							vo.setConstName(jObj.getString("CONSTITUENCY"));
		 	    							vo.setMandalName(jObj.getString("MANDAL"));
		 	    							vo.setPanchayatName(jObj.getString("PANCHAYAT"));
		 	    						}
		 	    					}else if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("district")){
		 	    						if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("constituency")){
		 	    							vo.setDistrictName(jObj.getString("DISTRICT"));
		 	    							vo.setConstName(jObj.getString("CONSTITUENCY"));
		 	    						}else if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("mandal")){
		 	    							vo.setDistrictName(jObj.getString("DISTRICT"));
		 	    							vo.setConstName(jObj.getString("CONSTITUENCY"));
		 	    							vo.setMandalName(jObj.getString("MANDAL"));
		 	    						}else if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("panchayat")){
		 	    							vo.setDistrictName(jObj.getString("DISTRICT"));
		 	    							vo.setConstName(jObj.getString("CONSTITUENCY"));
		 	    							vo.setMandalName(jObj.getString("MANDAL"));
		 	    							vo.setPanchayatName(jObj.getString("PANCHAYAT"));
		 	    						}
		 	    					}else if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("constituency")){
		 	    						if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("constituency")){
		 	    							vo.setConstName(jObj.getString("CONSTITUENCY"));
		 	    						}else if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("mandal")){
		 	    							vo.setDistrictName(jObj.getString("DISTRICT"));
		 	    							vo.setConstName(jObj.getString("CONSTITUENCY"));
		 	    							vo.setMandalName(jObj.getString("MANDAL"));
		 	    						}else if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("panchayat")){
		 	    							vo.setDistrictName(jObj.getString("DISTRICT"));
		 	    							vo.setConstName(jObj.getString("CONSTITUENCY"));
		 	    							vo.setMandalName(jObj.getString("MANDAL"));
		 	    							vo.setPanchayatName(jObj.getString("PANCHAYAT"));
		 	    						}
		 	    					}else if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("mandal")){
		 	    					  if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("panchayat")){
		 	    							vo.setDistrictName(jObj.getString("DISTRICT"));
		 	    							vo.setConstName(jObj.getString("CONSTITUENCY"));
		 	    							vo.setMandalName(jObj.getString("MANDAL"));
		 	    							vo.setPanchayatName(jObj.getString("PANCHAYAT"));
		 	    						}
		 	    					}
		 	    				}
		 	    				vo.setType(jObj.getString("TYPE"));
		 	    				vo.setGeneratedQuantity(jObj.getString("T_WS_CNT"));
		 	    				vo.setGeneratedAmount(jObj.getString("T_WS_AMT"));
		 	    				vo.setGeneratedPendingQuantity(jObj.getString("FNG_WS_CNT"));
		 	    				vo.setGeneratedPendingAmount(jObj.getString("FNG_WS_AMT"));
		 	    				vo.setUploadQuantity(jObj.getString("FG_WS_CNT"));
		 	    				vo.setUploadAmount(jObj.getString("FG_WS_AMT"));
		 	    				vo.setUploadPendingQunatity(jObj.getString("FNU_WS_CNT"));
		 	    				vo.setUploadPendingAmount(jObj.getString("FNU_WS_AMT"));
		 	    				vo.setSentToBankQuantity(jObj.getString("FU_WS_CNT"));
		 	    				vo.setSentToBankAmount(jObj.getString("FU_WS_AMT"));
		 	    				vo.setSentToBankPendingQuantity(jObj.getString("FNS_WS_CNT"));
		 	    				vo.setSentToBankPendingAmount(jObj.getString("FNS_WS_AMT"));
		 	    				vo.setFailedTransactionQuantity(jObj.getString("FR_WS_CNT"));
		 	    				vo.setFailedTransactionAmount(jObj.getString("FR_WS_AMT"));
		 	    				vo.setFailedTransactionPendingQuantity(jObj.getString("FRP_WS_CNT"));
		 	    				vo.setFailedTransactionPendingAmount(jObj.getString("FRP_WS_AMT"));
		 	    				namingMap.put(vo.getId(), vo);
		 	    				if(inputVO.getType() != null && !inputVO.getType().trim().toString().equalsIgnoreCase("All") && inputVO.getType().toString().trim().equalsIgnoreCase("Wage")){
		 	    					if(vo.getType() != null && vo.getType().toString().trim().equalsIgnoreCase("W")){
		 	    						voList.add(vo);
		 	    					}
		 	    				}
		 	    				else if(inputVO.getType() != null && !inputVO.getType().trim().toString().equalsIgnoreCase("All") && inputVO.getType().toString().trim().equalsIgnoreCase("Material")){
		 	    					if(vo.getType() != null && vo.getType().toString().trim().equalsIgnoreCase("M")){
		 	    						voList.add(vo);
		 	    					}
		 	    				}
		 	    				else if(inputVO.getType() != null && !inputVO.getType().trim().toString().equalsIgnoreCase("All") && inputVO.getType().toString().trim().equalsIgnoreCase("Total")){
		 	    					if(vo.getType() != null && (vo.getType().toString().trim().equalsIgnoreCase("Total") || vo.getType().toString().trim().equalsIgnoreCase("Sub Total"))){
		 	    						voList.add(vo);
		 	    					}
		 	    				}
		 	    				else if(inputVO.getType() != null && inputVO.getType().trim().toString().equalsIgnoreCase("All"))
		 	    					voList.add(vo);
		 	    			}
	 	    			}
	 	    		}
	 	      }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseDataForNewFTOPayments - NREGSTCSService service", e);
		}
		
		return voList;
	}
	
	/*
	 * Date : 08/07/2017
	 * Author :Nandhini
	 * @description : getAllNregaSubLocationDetails
	 */
	public List<LocationFundDetailsVO> getAllNregaSubLocationDetails(InputVO inputVO){
		List<LocationFundDetailsVO> detailsVOs = new ArrayList<LocationFundDetailsVO>();
		try{
			List<Object[]> locationList = null;
			Long levelId = inputVO.getSearchLevelId();
			String locationId =inputVO.getMenuLvelValue();
			if(levelId != null && levelId == IConstants.STATE_LEVEL_SCOPE_ID){//get districtIds
				locationList = prDistrictDAO.getAllDistrictsFrState();
			}else if(levelId != null && levelId == IConstants.DISTRICT_LEVEL_SCOPE_ID){//get constituencyIds
				locationList= prConstituencyDAO.getAllConstutiensFrDistrict(locationId);
				/*else
				 locationList =parliamentAssemblyDAO.getParliamentIdAndName(locationId);*/
			}/*else if(levelId != null && levelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){//get tehsilIds
				locationList = tehsilConstituencyDAO.getTehsilIdAndName(locationId);
			}else if(levelId != null && levelId == IConstants.MANDAL_LEVEL_SCOPE_ID){//get panchayatIds
				locationList = panchayatDAO.getPanchayatIdAndName(locationId);
			}else if(levelId != null && levelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){//get  parlaiamentIds
				//locationList =parliamentAssemblyDAO.getParliamentIdAndName(locationId);
				locationList =parliamentAssemblyDAO.getParliamentByConstIdAndName(locationId);
			}*/	
			if(commonMethodsUtilService.isListOrSetValid(locationList)){
				for(Object[] objects : locationList){
					LocationFundDetailsVO locationFundDetailsVO = new LocationFundDetailsVO();
					    locationFundDetailsVO.setType(commonMethodsUtilService.getStringValueForObject(objects[0]));//District_code
						locationFundDetailsVO.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
						detailsVOs.add(locationFundDetailsVO);
				}
			}
		}catch(Exception e){
			LOG.error("Exception Occurred in getAllNregaSubLocationDetails() of NREGSTCSService ", e);
		}
		return detailsVOs;
	}
	/*
	 * Date : 09/07/2017
	 * Author :Nandhini
	 * @description : getNregaPanchatVsExpData
	 */
	public List<NregsDataVO> getNregaPanchatVsExpData(InputVO inputVO){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
			String str = convertingInputVOToString(inputVO); 
			
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/LabourBudgetServiceNew/LabourBudgetDataPanchayatNew", str);
	        
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
	 	    				vo.setConstituency(jObj.getString("ASSEMBLY"));
	 	    				vo.setMandal(jObj.getString("MANDAL"));
	 	    				vo.setPanchayat(jObj.getString("PANCHAYAT"));
	 	    				vo.setTotalExpenditure(jObj.getString("TOTALEXPENDITURE"));
	 	    				voList.add(vo);
	 	    			}
	 	    		}
	 	    	}
	 	      }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaPanchatVsExpData - NREGSTCSService service", e);
		}
		
		return voList;
	}
	
	/*
	 * Date : 11/07/2017
	 * Author :Sravanth
	 * @description : getNregaLevelsWiseDataForFAPerformance
	 */
	public List<NregsDataVO> getNregaLevelsWiseDataForFAPerformance(InputVO inputVO){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
			if(inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().toString().length() > 0l)
				inputVO.setSublocationType(inputVO.getSublocaType().trim());
			
			String webServiceUrl = null;
			
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("FAperformance"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FaPerformanceServiceNew/FaPerformanceDataNew";
			
			String str = convertingInputVOToString(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
	        
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
		 	    				vo.setState(jObj.getString("'STATE'"));
		 	    				vo.setAvgDmdMarks(jObj.getString("AVG_DMD_MARKS"));
		 	    				vo.setAvgDMusterMarks(jObj.getString("AVG_D_MUSTER_MARKS"));
		 	    				vo.setAvgLbMarks(jObj.getString("AVG_LB_MARKS"));
		 	    				vo.setAvgRozgarDivasMarks(jObj.getString("AVG_ROZGAR_DIVAS_MARKS"));
		 	    				vo.setAvgDaysMarks(jObj.getString("AVG_AVG_DAYS_MARKS"));
		 	    				vo.setAvgAvgWageMarks(jObj.getString("AVG_AVG_WAGE_MARKS"));
		 	    				vo.setAvgFlagshipMarks(jObj.getString("AVG_FLAGSHIP_MARKS"));
		 	    				vo.setAvgTotMarks(jObj.getString("AVG_TOT_MARKS"));
		 	    				
		 	    				voList.add(vo);
		 	    			}
	 	    			}
	 	    			else{
	 	    				for(int i=0;i<finalArray.length();i++){
		 	    				NregsDataVO vo = new NregsDataVO();
		 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
		 	    				vo.setUniqueId(jObj.getLong("UNIQUEID"));
		 	    				if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("district"))
		 	    					vo.setDistrict(jObj.getString("DISTRICT_DESCRIPTION"));
		 	    				else
		 	    					vo.setDistrict(jObj.getString("DISTRICT"));
		 	    				
		 	    				vo.setConstituency(jObj.getString("ASSEMBLY"));
		 	    				vo.setMandal(jObj.getString("MANDAL"));
		 	    				vo.setPanchayat(jObj.getString("PANCHAYAT"));
		 	    				vo.setAvgDmdMarks(jObj.getString("AVG_DMD_MARKS"));
		 	    				vo.setAvgDMusterMarks(jObj.getString("AVG_D_MUSTER_MARKS"));
		 	    				vo.setAvgLbMarks(jObj.getString("AVG_LB_MARKS"));
		 	    				vo.setAvgRozgarDivasMarks(jObj.getString("AVG_ROZGAR_DIVAS_MARKS"));
		 	    				vo.setAvgDaysMarks(jObj.getString("AVG_AVG_DAYS_MARKS"));
		 	    				vo.setAvgAvgWageMarks(jObj.getString("AVG_AVG_WAGE_MARKS"));
		 	    				vo.setAvgFlagshipMarks(jObj.getString("AVG_FLAGSHIP_MARKS"));
		 	    				vo.setAvgTotMarks(jObj.getString("AVG_TOT_MARKS"));
		 	    				
		 	    				voList.add(vo);
		 	    			}
	 	    			}
	 	    		}
	 	    	}
	 	    }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseDataForFAPerformance - NREGSTCSService service", e);
		}
		
		return voList;
	}
	
	public String convertRupeesIntoCrores(String value){
		String returnVal = null;
		try {
			if(value != null){
				returnVal = new BigDecimal(Long.valueOf(value)/10000000.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				returnVal = returnVal+" CR";
			}
		} catch (Exception e) {
			LOG.error("Exception raised at convertRupeesIntoCrores - NREGSTCSService service", e);
		}
		return returnVal;
	}
}
