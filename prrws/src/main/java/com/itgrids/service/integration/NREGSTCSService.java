package com.itgrids.service.integration;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
import com.itgrids.dto.NregaLocationOverviewVO;
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
import com.sun.jersey.api.client.WebResource;

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
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Burial Ground"))
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
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("SMC Trench"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/StaggeredService/StaggeredOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Imp to CD"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/ImprovmentsService/ImprovmentsOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("MPT_PT"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MiniPercolationService/MiniPercolationOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("GC Works"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/GCWorkService/GCWorkOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("CD_CW"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CheckDamService/CheckDamOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("WaterBudget"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/WaterBudgetService/WaterBudgetOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR Rural House"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NTRRuralService/NTRRuralOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("OPGK-Perinnials"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/PernnialService/PernnialOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("OPGK-Annuals"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AnnualsService/AnnualsOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("UGDrainage"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/UGDrainageService/UGDrainageOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("GH"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/GreeningHillocksService/GreeningHillocksOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Check Dam"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CheckDamServiceNew/CheckDamOverviewNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Rock fill dams"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/RockfillDamService/RockfillDamOverview";
			
			String str = convertingInputVOToString(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject Obj = new JSONObject(output);
	 	    		if(Obj!=null && Obj.length()>0){
	 	    			/*if(inputVO.getDivType() != null && (inputVO.getDivType().trim().toString().equalsIgnoreCase("Average Wage") || 
 	    					inputVO.getDivType().trim().toString().equalsIgnoreCase("Average Days of Employment") || 
 	    					inputVO.getDivType().trim().toString().equalsIgnoreCase("HH Completed 100 Days") || 
 	    					inputVO.getDivType().trim().toString().equalsIgnoreCase("Timely Payment") || 
 	    					inputVO.getDivType().trim().toString().equalsIgnoreCase("Horticulture") || inputVO.getDivType().trim().toString().equalsIgnoreCase("Avenue") ||
 	    					inputVO.getDivType().trim().toString().equalsIgnoreCase("Nurseries") ||	inputVO.getDivType().trim().toString().equalsIgnoreCase("FAperformance") ||
 	    					inputVO.getDivType().trim().toString().equalsIgnoreCase("WaterBudget") || inputVO.getDivType().trim().toString().equalsIgnoreCase("GH") ||
 	    					(inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR Rural House") && inputVO.getLocationType() != null && !inputVO.getLocationType().trim().equalsIgnoreCase("state")) ||
 	    					(inputVO.getDivType().trim().toString().equalsIgnoreCase("OPGK-Perinnials") && inputVO.getLocationType() != null && !inputVO.getLocationType().trim().equalsIgnoreCase("state")) ||
 	    					(inputVO.getDivType().trim().toString().equalsIgnoreCase("OPGK-Annuals") && inputVO.getLocationType() != null && !inputVO.getLocationType().trim().equalsIgnoreCase("state")) ||
 	    					(inputVO.getDivType().trim().toString().equalsIgnoreCase("Cattle Drinking Water Troughs") && inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("district")) ||
 	    					(inputVO.getDivType().trim().toString().equalsIgnoreCase("UGDrainage") && inputVO.getLocationType() != null && !inputVO.getLocationType().trim().equalsIgnoreCase("state")))){*/
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
	 	    			/*}else{
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
	 	    			}*/
	 	    				
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
				if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("district")){
					if(inputVO.getLocationId().longValue() > 0l && inputVO.getLocationId().longValue() <= 9l)
						inputVO.setLocationIdStr("0"+inputVO.getLocationId().toString());
				}else if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("constituency")){
					if(inputVO.getDistrictId().longValue() > 0l && inputVO.getDistrictId().longValue() <= 9l){
						if(inputVO.getLocationId().longValue() > 0l)
							inputVO.setLocationIdStr("0"+inputVO.getLocationId().toString());
					}
				}
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
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Burial Ground"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BurialGroundsServices/BurialGroundsData";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BurialService/BurialData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Agriculture Activities"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AgricultureServices/AgricultureData";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AgriService/AgriData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Drying Platforms"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FishDryingServiceNew/FishDryingDataNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FishDryingPlatformService/FishDryingPlatformData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Ponds"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FishPondServiceNew/FishPondDataNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FishPondsService/FishPondsData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("SMC Trench"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/StaggeredService/StaggeredData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Imp to CD"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/ImprovmentsService/ImprovmentsData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("MPT_PT"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MiniPercolationService/MiniPercolationData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("GC Works"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/GCWorkService/GCWorkData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("CD_CW"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CheckDamService/CheckDamData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR Rural House"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NTRRuralService/NTRRuralData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("OPGK-Perinnials"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/PernnialService/PernnialData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("OPGK-Annuals"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AnnualsService/AnnualsData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("GH"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/GreeningHillocksService/GreeningHillocksData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Check Dam"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CheckDamServiceNew/CheckDamDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Rock fill dams"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/RockfillDamService/RockfillDamData";
			
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
	 	    				if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("GH")){
								if(inputVO.getSublocationType() != null && (inputVO.getSublocationType().trim().equalsIgnoreCase("state") 
									|| inputVO.getSublocationType().trim().equalsIgnoreCase("district")))
	 	    					vo.setTarget(jObj.getLong("DISTRICT_TARGET"));
	 	    					
	 	    					vo.setSanctionedTarget(jObj.getString("SANCTION_TARGET"));
		 	    				vo.setPittingKMS(jObj.getString("PITTING_EXT"));
		 	    				vo.setPlantingKMS(jObj.getString("PLNTNG_EXT"));
		 	    				vo.setTotalExpenditure(jObj.getString("EXPN"));
		 	    				if(vo.getPlantingKMS() != null && Double.valueOf(vo.getPlantingKMS()) > 0 && vo.getSanctionedTarget() != null
										&& Double.valueOf(vo.getSanctionedTarget()) > 0){
		 	    					vo.setPercentage(new BigDecimal(Double.valueOf(vo.getPlantingKMS()) * 100.00
											/ Double.valueOf(vo.getSanctionedTarget()))
													.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
								} else {
									vo.setPercentage("0.00");
								}
							}
	 	    				else{
	 	    					if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery"))
	 	    						vo.setMulbTarget(jObj.getString("TARGET"));
	 	    					else
	 	    						vo.setTarget(jObj.getLong("TARGET"));
		 	    				vo.setGrounded(jObj.getString("GROUNDED"));
		 	    				if(jObj.getString("NOTGROUNDED").trim().contains("-"))
		 	    					vo.setNotGrounded("0");
		 	    				else
		 	    					vo.setNotGrounded(jObj.getString("NOTGROUNDED"));
		 	    				if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery"))
		 	    					vo.setMulbInprogress(jObj.getString("INPROGRESS"));
	 	    					else
	 	    						vo.setInProgress(jObj.getLong("INPROGRESS"));
		 	    				if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery"))
		 	    					vo.setMulbCompleted(jObj.getString("COMPLETED"));
	 	    					else
	 	    						vo.setCompleted(jObj.getLong("COMPLETED"));
		 	    				
		 	    				vo.setPercentage(jObj.getString("PERCENTAGE"));
		 	    				if((inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery")
		 	    						|| inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Silk worms")
		 	    						|| inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Cattle drinking water troughs")
		 	    						|| inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Raising of Perinnial Fodders"))
		 	    							&& inputVO.getSublocationType().trim().toString().equalsIgnoreCase("state")){
		 	    					vo.setSanctionedTarget(jObj.getString("SANCTIONEDTARGET"));
		 	    					vo.setSanctionedPerventage(jObj.getString("SANCTIONEDPERCENTAGE"));
		 	    					
		 	    					if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery"))
		 	    						vo.setPercSant(new BigDecimal(Double.valueOf(vo.getMulbCompleted())*100.00/Double.valueOf(vo.getSanctionedTarget())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    					else
		 	    						vo.setPercSant(new BigDecimal(vo.getCompleted()*100.00/Double.valueOf(vo.getSanctionedTarget())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    					
		 	    					if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery")){
		 	    						if(vo.getMulbCompleted() != null && Double.valueOf(vo.getMulbCompleted()) > 0 && vo.getMulbTarget() != null && Double.valueOf(vo.getMulbTarget()) > 0)
			 	    						vo.setSanctionedPerc(new BigDecimal(Double.valueOf(vo.getMulbCompleted())*100.00/Double.valueOf(vo.getMulbTarget())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			 	    					else
			 	    						vo.setSanctionedPerc("0.00");
		 	    					}else{
		 	    						if(vo.getCompleted() != null && vo.getCompleted().longValue() > 0l && vo.getTarget() != null && vo.getTarget() > 0l)
			 	    						vo.setSanctionedPerc(new BigDecimal(vo.getCompleted()*100.00/vo.getTarget()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			 	    					else
			 	    						vo.setSanctionedPerc("0.00");
		 	    					}
		 	    					
		 	    				}
		 	    				
		 	    				if((inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Ponds")
		 	    						|| inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Drying Platforms")
		 	    						|| inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Anganwadi Buildings") 
		 	    						|| inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("SMC Trench")
		 	    						|| inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Imp to CD")
		 	    						|| inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("MPT_PT")
		 	    						|| inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("GC Works")
		 	    						|| inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("CD_CW"))
		 	    						&& (inputVO.getSublocationType().trim().toString().equalsIgnoreCase("state")
		 	    								|| inputVO.getSublocationType().trim().toString().equalsIgnoreCase("district"))){
		 	    					vo.setSanctionedTarget(jObj.getString("SANCTIONEDTARGET"));
		 	    					vo.setSanctionedPerventage(jObj.getString("SANCTIONEDPERCENTAGE"));
		 	    					if(vo.getCompleted() != null && vo.getCompleted().longValue() > 0l && vo.getSanctionedTarget() != null && Double.valueOf(vo.getSanctionedTarget()) > 0)
		 	    						vo.setPercSant(new BigDecimal(vo.getCompleted()*100.00/Double.valueOf(vo.getSanctionedTarget())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    					else
		 	    						vo.setPercSant("0.00");
		 	    				}
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
	 	    				vo.setTargetPersonDays(jObj.getString("TARGETPERSONDAYS"));
	 	    				vo.setGeneratedPersonDays(jObj.getString("GENERATEDPERSONDAYS"));
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
	public List<NregsDataVO> getNregsConstCuntDetails(String output,Map<String,NregsDataVO> cntMap,String divType,String locationType,String subLocationType){
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
	    					if(subLocationType != null && subLocationType.trim().equalsIgnoreCase("district")){
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
						}else if((divType != null && divType.trim().toString().equalsIgnoreCase("Fish Drying Platforms") || divType != null && divType.trim().toString().equalsIgnoreCase("Fish Ponds") ||
								divType != null && divType.trim().toString().equalsIgnoreCase("SMC Trench") || divType != null && divType.trim().toString().equalsIgnoreCase("Imp to CD") ||
								divType != null && divType.trim().toString().equalsIgnoreCase("MPT_PT") || divType != null && divType.trim().toString().equalsIgnoreCase("GC Works") ||
								divType != null && divType.trim().toString().equalsIgnoreCase("CD_CW") ||divType != null && divType.trim().toString().equalsIgnoreCase("Anganwadi Buildings")) && 
								(subLocationType != null && subLocationType.trim().equalsIgnoreCase("district"))){
							percValue = new BigDecimal(jObj.getLong("COMPLETED")*100.00/Long.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("GH")) {
							percValue = new BigDecimal(Double.valueOf(jObj.getString("PLNTNG_EXT")) * 100.00
									/ Double.valueOf(jObj.getString("SANCTION_TARGET")))
											.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else{
							percValue = new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						}
						
		    				if(vo != null){
			    				if(Double.valueOf(percValue)  < 50){
		    						vo.setConstiInRed(vo.getConstiInRed()+1l);
		    					}else if(Double.valueOf(percValue)  >=50 && Double.valueOf(percValue) <80){
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
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Burial Ground"))
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
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("SMC Trench"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/StaggeredService/StaggeredData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Imp to CD"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/ImprovmentsService/ImprovmentsData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("MPT_PT"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MiniPercolationService/MiniPercolationData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("GC Works"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/GCWorkService/GCWorkData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("CD_CW"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CheckDamService/CheckDamData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR Rural House"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NTRRuralService/NTRRuralData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("OPGK-Perinnials"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/PernnialService/PernnialData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("OPGK-Annuals"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AnnualsService/AnnualsData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("UGDrainage"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/UGDrainageService/UGDrainageData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("GH"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/GreeningHillocksService/GreeningHillocksData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Check Dam"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CheckDamServiceNew/CheckDamDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Rock fill dams"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/RockfillDamService/RockfillDamData";
			
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
			 	    				vo.setTargetPersonDays(jObj.getString("TARGETPERSONDAYS"));
			 	    				vo.setGeneratedPersonDays(jObj.getString("GENERATEDPERSONDAYS"));
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
				 	    				vo.setTargetPersonDays(jObj.getString("TARGET"));
				 	    				if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Agriculture Activities"))
				 	    					vo.setArgicultureExpenditure(jObj.getString("COMPLETED"));
				 	    				else
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
		 	    					 inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Ponds") || 
		 	    					 inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("SMC Trench") ||
		 	    					 inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Imp to CD") ||
		 	    					 inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("MPT_PT") ||
		 	    					 inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("GC Works") ||
		 	    					 inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("CD_CW") ||
		 	    					 inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Anganwadi Buildings")){
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
				 	    				
				 	    				if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("district")){
				 	    					nregsDataVO.setSanctionedTarget(jObj.getString("SANCTIONEDTARGET"));
				 	    					nregsDataVO.setSanctionedPerventage(jObj.getString("SANCTIONEDPERCENTAGE"));
				 	    					if(nregsDataVO.getCompleted() != null && nregsDataVO.getCompleted().longValue() > 0l && nregsDataVO.getSanctionedTarget() != null && Double.valueOf(nregsDataVO.getSanctionedTarget()) > 0){
				 	    						nregsDataVO.setPercSant(new BigDecimal(nregsDataVO.getCompleted()*100.00/Double.valueOf(nregsDataVO.getSanctionedTarget())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				 	    						nregsDataVO.setPercentage(new BigDecimal(nregsDataVO.getCompleted()*100.00/Double.valueOf(nregsDataVO.getSanctionedTarget())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				 	    					}else{
				 	    						nregsDataVO.setPercSant("0.00");
				 	    						nregsDataVO.setPercentage("0.00");
				 	    					}
					 	    				nregsDataVO.setAvgTotMarks(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				 	    				}else{
				 	    					nregsDataVO.setPercentage(jObj.getString("PERCENTAGE"));
				 	    				}
				 	    				
				 	    				list.add(nregsDataVO);	 
				 	    			}
			 	    		}else if(inputVO.getDivType() != null && (inputVO.getDivType().trim().toString().equalsIgnoreCase("CC Roads") || inputVO.getDivType().trim().toString().equalsIgnoreCase("UGDrainage"))){
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
			 	    				if(nregsDataVO.getCompletedKMS() != null && Double.valueOf(nregsDataVO.getCompletedKMS()) > 0 && nregsDataVO.getSanctionedKMS() != null && Long.valueOf(nregsDataVO.getSanctionedKMS()) > 0l)
			 	    					nregsDataVO.setPercSant(new BigDecimal(Double.valueOf(nregsDataVO.getCompletedKMS())*100.00/Long.valueOf(nregsDataVO.getSanctionedKMS())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    					else
		 	    						nregsDataVO.setPercSant("0.00");
			 	    				
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
			 	    				if(inputVO.getSublocationType().trim().toString().equalsIgnoreCase("district")){
			 	    					if(vo.getPlantingKMS() != null && Double.valueOf(vo.getPlantingKMS()) > 0l && vo.getTargetKMS() != null && Double.valueOf(vo.getTargetKMS()) > 0l)
			 	    						vo.setSanctionedPerc(new BigDecimal(Double.valueOf(vo.getPlantingKMS())*100.00/Double.valueOf(vo.getTargetKMS())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			 	    					else
			 	    						vo.setSanctionedPerc("0.00");
				 	    			}
			 	    				
			 	    				list.add(vo);
			 	    			}
			 	    		}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("FAperformance")){
			 	    				for(int i=0;i<finalArray.length();i++){
				 	    				NregsDataVO vo = new NregsDataVO();
				 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
				 	    				vo.setUniqueId(jObj.getLong("UNIQUEID"));
				 	    				if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("district"))
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
			 	    		}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("GH")){
								for (int i = 0; i < finalArray.length(); i++){
									NregsDataVO nregsDataVO = new NregsDataVO();
									JSONObject jObj = (JSONObject) finalArray.get(i);
									nregsDataVO.setUniqueId(jObj.getLong("UNIQUEID"));
									nregsDataVO.setDistrict(jObj.getString("DISTRICT"));
									nregsDataVO.setConstituency(jObj.getString("CONSTITUENCY"));
									nregsDataVO.setMandal(jObj.getString("MANDAL"));
									nregsDataVO.setPanchayat(jObj.getString("PANCHAYAT"));
									if(inputVO.getSublocationType() != null && (inputVO.getSublocationType().trim().equalsIgnoreCase("state") 
											|| inputVO.getSublocationType().trim().equalsIgnoreCase("district")))
										nregsDataVO.setTarget(jObj.getLong("DISTRICT_TARGET"));
			 	    					
									nregsDataVO.setSanctionedTarget(jObj.getString("SANCTION_TARGET"));
									nregsDataVO.setPittingKMS(jObj.getString("PITTING_EXT"));
									nregsDataVO.setPlantingKMS(jObj.getString("PLNTNG_EXT"));
									nregsDataVO.setTotalExpenditure(jObj.getString("EXPN"));
									if(nregsDataVO.getPlantingKMS() != null
											&& Double.valueOf(nregsDataVO.getPlantingKMS()) > 0
											&& nregsDataVO.getSanctionedTarget() != null
											&& Double.valueOf(nregsDataVO.getSanctionedTarget()) > 0){
										nregsDataVO.setPercentage(new BigDecimal(Double.valueOf(nregsDataVO.getPlantingKMS()) * 100.00
												/ Double.valueOf(nregsDataVO.getSanctionedTarget()))
														.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
									}else{
										nregsDataVO.setPercentage("0.00");
									}
									list.add(nregsDataVO);
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
		 	    		String sublocationType = inputVO.getSublocationType();
		 	    		/*if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("FAperformance"))
		 	    			inputVO.setLocationType("constituency");
		 	    		else*/
		 	    			inputVO.setSublocationType("constituency");
			 	    		
			 	    		str = convertingInputVOToString(inputVO);
				 	    	ClientResponse constResponse = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
				 	    	if(constResponse.getStatus() != 200){
					 	    	  throw new RuntimeException("Failed : HTTP error code : "+ constResponse.getStatus());
					 	      }else{
					 	    	 String constOutput = constResponse.getEntity(String.class);
					 	    	 
					 	    	 disConslist = getNregsConstCuntDetails(constOutput,distConstMap,inputVO.getDivType(),inputVO.getLocationType(),inputVO.getSublocationType());
					 	      }
				 	    	
				 	    	distConstMap.clear();
				 	    	if(list != null && !list.isEmpty()){
				 	    		 for (NregsDataVO nregsDataVO : list) {
				 	    			 NregsDataVO filterVo = new NregsDataVO();
				 	    			 filterVo.setPercentage(nregsDataVO.getPercentage());
									distConstMap.put(nregsDataVO.getDistrict(),filterVo);
								}
				 	    	 }
				 	    	/*if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("FAperformance"))
				 	    		inputVO.setLocationType("mandal");
				 	    	else*/
				 	    		inputVO.setSublocationType("mandal");
				 	    	str = convertingInputVOToString(inputVO);
				 	    	ClientResponse mandalResponse = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
				 	    	if(mandalResponse.getStatus() != 200){
					 	    	  throw new RuntimeException("Failed : HTTP error code : "+ mandalResponse.getStatus());
					 	      }else{
					 	    	 String mandalOutput = mandalResponse.getEntity(String.class);
					 	    	 
					 	    	  disMandallist = getNregsMandalsCuntFrDistrict(mandalOutput,distConstMap,inputVO.getDivType(),inputVO.getSublocationType());
					 	      }
				 	    	
				 	    	distConstMap.clear();
				 	    	if(list != null && !list.isEmpty()){
				 	    		 for (NregsDataVO nregsDataVO : list) {
				 	    			 NregsDataVO filterVo = new NregsDataVO();
				 	    			 filterVo.setPercentage(nregsDataVO.getPercentage());
									distConstMap.put(nregsDataVO.getDistrict(),filterVo);
								}
				 	    	 }
				 	    	/*if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("FAperformance"))
				 	    		inputVO.setLocationType("panchayat");
				 	    	else*/
				 	    		inputVO.setSublocationType("panchayat");
				 	    	str = convertingInputVOToString(inputVO);
				 	    	ClientResponse panchayatResponse = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
				 	    	if(panchayatResponse.getStatus() != 200){
					 	    	  throw new RuntimeException("Failed : HTTP error code : "+ panchayatResponse.getStatus());
					 	      }else{
					 	    	 String panchayatOutput = panchayatResponse.getEntity(String.class);
					 	    	 
					 	    	disVillageslist = getNregsVillageCuntFrDistrict(panchayatOutput,distConstMap,inputVO.getDivType(),inputVO.getSublocationType());
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
				 	    	/*if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("FAperformance"))
				 	    		inputVO.setLocationType("mandal");
				 	    	else*/
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
				 	    	/*if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("FAperformance"))
				 	    		inputVO.setLocationType("panchayat");
				 	    	else*/
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
			 	    		/*if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("FAperformance"))
				 	    		inputVO.setLocationType("panchayat");
			 	    		else*/
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
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("GH")) {
							percValue = new BigDecimal(Double.valueOf(jObj.getString("PLNTNG_EXT")) * 100.00
									/ Double.valueOf(jObj.getString("SANCTION_TARGET")))
											.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else{
							percValue = new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						} 
		    				if(vo != null) {
			    				if(Double.valueOf(percValue)  < 50){
		    						vo.setMandalsInRed(vo.getMandalsInRed()+1l);
		    					}else if(Double.valueOf(percValue)  >=50 && Double.valueOf(percValue) <80){
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
	    					constName = jObj.getString("ASSEMBLY");
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
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("GH")) {
							percValue = new BigDecimal(Double.valueOf(jObj.getString("PLNTNG_EXT")) * 100.00
									/ Double.valueOf(jObj.getString("SANCTION_TARGET")))
											.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else{
							percValue = new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						} 
		    				if(vo != null)
		    				{
			    				if(Double.valueOf(percValue)  < 50){
		    						vo.setMandalsInRed(vo.getMandalsInRed()+1l);
		    					}else if(Double.valueOf(percValue)  >=50 && Double.valueOf(percValue) <80){
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
				else if(type.trim().equalsIgnoreCase("orange") && Double.valueOf(nregsDataVO.getPercentage()) <80 && Double.valueOf(nregsDataVO.getPercentage()) >=50)
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
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("GH")) {
							percValue = new BigDecimal(Double.valueOf(jObj.getString("PLNTNG_EXT")) * 100.00
									/ Double.valueOf(jObj.getString("SANCTION_TARGET")))
											.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else{
							percValue = new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						} 
		    				if(vo != null) {
			    				if(Double.valueOf(percValue)  < 50){
		    						vo.setVillagesInRed(vo.getVillagesInRed()+1l);
		    					}else if(Double.valueOf(percValue)  >=50 && Double.valueOf(percValue) <80){
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
	    					constName = jObj.getString("ASSEMBLY");
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
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("GH")) {
							percValue = new BigDecimal(Double.valueOf(jObj.getString("PLNTNG_EXT")) * 100.00
									/ Double.valueOf(jObj.getString("SANCTION_TARGET")))
											.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else{
							percValue = new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						} 
		    				if(vo != null)
		    				{
			    				if(Double.valueOf(percValue)  < 50){
		    						vo.setVillagesInRed(vo.getVillagesInRed()+1l);
		    					}else if(Double.valueOf(percValue)  >=50 && Double.valueOf(percValue) <80){
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
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("GH")) {
							percValue = new BigDecimal(Double.valueOf(jObj.getString("PLNTNG_EXT")) * 100.00
									/ Double.valueOf(jObj.getString("SANCTION_TARGET")))
											.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else{
							percValue = new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						} 
		    				if(vo != null)
		    				{
			    				if(Double.valueOf(percValue)  < 50){
		    						vo.setVillagesInRed(vo.getVillagesInRed()+1l);
		    					}else if(Double.valueOf(percValue)  >=50 && Double.valueOf(percValue) <80){
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
		 	    				if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("HH Completed 100 Days")){
		 	    					if(inputVO.getSublocationType().trim().equalsIgnoreCase("state"))
		 	    						vo.setTarget(jObj.getLong("TERGET"));
		 	    					else
		 	    						vo.setTarget(jObj.getLong("TARGET"));
		 	    				}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Nurseries"))
		 	    					vo.setTargetPersonDays(jObj.getString("TARGET"));
		 	    				else
		 	    					vo.setTarget(jObj.getLong("TARGET"));
		 	    				if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Nurseries"))
		 	    					vo.setAchivement(new BigDecimal(jObj.getString("COMPLETED")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    				else
		 	    					vo.setAchivement(jObj.getString("ACHIVEMENT"));
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
		 	    				vo.setTargetPersonDays(jObj.getString("TARGET"));
		 	    				vo.setArgicultureExpenditure(jObj.getString("COMPLETED"));
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
		 	    				vo.setTargetPersonDays(jObj.getString("TARGET"));
		 	    				vo.setArgicultureExpenditure(jObj.getString("COMPLETED"));
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
		 	    				vo.setTargetPittingPerc(new BigDecimal((Double.valueOf(jObj.getString("PITTINGAREA"))*100.00)/Double.valueOf(jObj.getString("TARGETACRES"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				}
	 	    				vo.setSanctionedACRES(jObj.getString("SANCTIONEDACRES"));
	 	    				vo.setPittingArea(jObj.getString("PITTINGAREA"));
	 	    				vo.setPlantingArea(jObj.getString("PLANTINGAREA"));
	 	    				vo.setPencentageOfPlanting(jObj.getString("PERCENTAGEOFPLANTING"));
	 	    				if(jObj.getString("PITTINGAREA") != null && Double.valueOf(jObj.getString("PITTINGAREA")) >0 && jObj.getString("SANCTIONEDACRES") != null && Double.valueOf(jObj.getString("SANCTIONEDACRES")) > 0)
	 	    					vo.setSancTrgtPittingPerc(new BigDecimal((Double.valueOf(jObj.getString("PITTINGAREA"))*100.00)/Double.valueOf(jObj.getString("SANCTIONEDACRES"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				else
	 	    					vo.setSancTrgtPittingPerc("0.00");
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
	 	    				if(inputVO.getSublocationType().trim().toString().equalsIgnoreCase("state") || inputVO.getSublocationType().trim().toString().equalsIgnoreCase("district")){
	 	    					if(vo.getPlantingKMS() != null && Double.valueOf(vo.getPlantingKMS()) > 0l && vo.getTargetKMS() != null && Double.valueOf(vo.getTargetKMS()) > 0l)
	 	    						vo.setSanctionedPerc(new BigDecimal(Double.valueOf(vo.getPlantingKMS())*100.00/Double.valueOf(vo.getTargetKMS())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    					else
	 	    						vo.setSanctionedPerc("0.00");
		 	    			}
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
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("UGDrainage"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/UGDrainageService/UGDrainageData";
			
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
	 	    				if(vo.getCompletedKMS() != null && Double.valueOf(vo.getCompletedKMS()) > 0 && vo.getSanctionedKMS() != null && Long.valueOf(vo.getSanctionedKMS()) > 0l)
 	    						vo.setPercSant(new BigDecimal(Double.valueOf(vo.getCompletedKMS())*100.00/Long.valueOf(vo.getSanctionedKMS())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
 	    					else
 	    						vo.setPercSant("0.00");
	 	    				
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
			 	    				vo.setTargetPersonDays(jObj.getString("TARGETPERSONDAYS"));
			 	    				vo.setGeneratedPersonDays(jObj.getString("GENERATEDPERSONDAYS"));
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
				 	    				vo.setTargetPersonDays(jObj.getString("TARGET"));
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
								finalVO.setNotGrounded(String.valueOf(Long.valueOf(vo.getNotGrounded())+Long.valueOf(finalVO.getNotGrounded())));
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
	 	    	  if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("Burial Ground"))
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
			String str = null;
			ClientResponse response = null;
			if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("GH"))
				str = convertingInputVOToStringForIWMP(inputVO);
			else
				str = convertingInputVOToString(inputVO);
			
			if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("GH"))
				response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/WaterBudgetService/AbstractDataIwmp", str);
			else
				response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CMDashBoard/AbstractNew", str);
			
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
	 	    				
	 	    				if(inputVO.getType().toString().trim().equalsIgnoreCase("Nurseries") && (inputVO.getLocationType().toString().trim().equalsIgnoreCase("state") || inputVO.getLocationType().toString().trim().equalsIgnoreCase("constituency")))
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
	 	    				else if(inputVO.getType().toString().trim().equalsIgnoreCase("GH")){
								vo.setTarget(jObj.getString("TARGET"));
								vo.setCompleted(jObj.getString("ACHMT"));
							}
	 	    				else{
	 	    					vo.setTarget(jObj.getString("TARGET"));
		 	    				vo.setCompleted(jObj.getString("COMPLETED"));
	 	    				}
	 	    				
	 	    				if(inputVO.getType().toString().trim().equalsIgnoreCase("Agriculture Activities")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("SMC Trench") || inputVO.getType().toString().trim().equalsIgnoreCase("Imp to CD")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("MPT_PT") || inputVO.getType().toString().trim().equalsIgnoreCase("GC Works")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("CD_CW")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("Check Dam") || inputVO.getType().toString().trim().equalsIgnoreCase("Rock fill dams"))
	 	    					vo.setPercentage(jObj.getString("PERC"));
	 	    				else if(inputVO.getType().toString().trim().equalsIgnoreCase("Average Wage")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("Average Days of Employment")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("HH Completed 100 Days")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("Timely Payment")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("Horticulture") || inputVO.getType().toString().trim().equalsIgnoreCase("Avenue")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("GH"))
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
		 	    				if(inputVO.getLocationType() != null && (inputVO.getLocationType().trim().equalsIgnoreCase("state") && 
		 	    				   inputVO.getSublocationType().trim().equalsIgnoreCase("state") || inputVO.getSublocationType().trim().equalsIgnoreCase("district") || inputVO.getSublocationType().trim().equalsIgnoreCase("constituency") || inputVO.getSublocationType().trim().equalsIgnoreCase("mandal")) ||
		 	    						(inputVO.getLocationType().trim().equalsIgnoreCase("district") && inputVO.getSublocationType().trim().equalsIgnoreCase("district")|| inputVO.getSublocationType().trim().equalsIgnoreCase("constituency") || inputVO.getSublocationType().trim().equalsIgnoreCase("mandal")) ||
		 	    						inputVO.getLocationType().trim().equalsIgnoreCase("constituency")){
			 	    					vo.setType(jObj.getString("TYPE"));
			 	    					if(inputVO.getSublocationType() != null && (inputVO.getSublocationType().trim().equalsIgnoreCase("constituency") 
			 	    							|| inputVO.getSublocationType().trim().equalsIgnoreCase("mandal"))){
			 	    						vo.setTotalPayments(jObj.getString("T_WS_CNT"));
			 	    						
			 	    						vo.setGeneratedPendingQuantity(jObj.getString("FNG_WS_CNT"));
			 	    						vo.setGeneratedQuantity(jObj.getString("FG_WS_CNT"));
			 	    						
			 	    						vo.setUploadPendingQunatity(jObj.getString("FNU_WS_CNT"));
			 	    						vo.setUploadQuantity(jObj.getString("FU_WS_CNT"));
			 	    						
			 	    						vo.setSentToBankPendingQuantity(jObj.getString("FNS_WS_CNT"));
			 	    						vo.setSentToBankQuantity(jObj.getString("FS_WS_CNT"));
			 	    						
			 	    						vo.setFailedTransactionQuantity(jObj.getString("FR_WS_CNT"));
			 	    						vo.setPendingAtBankQuantity(jObj.getString("FRP_WS_CNT"));
			 	    						vo.setSentToBankSuccess(jObj.getString("FP_WS_CNT"));
			 	    					}
			 	    					else{
			 	    						vo.setTotalPayments(convertRupeesIntoLakhes(jObj.getString("T_WS_CNT")));
			 	    						
			 	    						vo.setGeneratedPendingQuantity(convertRupeesIntoLakhes(jObj.getString("FNG_WS_CNT")));
			 	    						vo.setGeneratedQuantity(convertRupeesIntoLakhes(jObj.getString("FG_WS_CNT")));
			 	    						
			 	    						vo.setUploadPendingQunatity(convertRupeesIntoLakhes(jObj.getString("FNU_WS_CNT")));
			 	    						vo.setUploadQuantity(convertRupeesIntoLakhes(jObj.getString("FU_WS_CNT")));
			 	    						
			 	    						vo.setSentToBankPendingQuantity(convertRupeesIntoLakhes(jObj.getString("FNS_WS_CNT")));
			 	    						vo.setSentToBankQuantity(convertRupeesIntoLakhes(jObj.getString("FS_WS_CNT")));
			 	    						
			 	    						vo.setFailedTransactionQuantity(convertRupeesIntoLakhes(jObj.getString("FR_WS_CNT")));
			 	    						vo.setPendingAtBankQuantity(convertRupeesIntoLakhes(jObj.getString("FRP_WS_CNT")));
			 	    						vo.setSentToBankSuccess(convertRupeesIntoLakhes(jObj.getString("FP_WS_CNT")));
			 	    					}
			 	    					
				 	    				vo.setTotalAmount(convertRupeesIntoCrores(jObj.getString("T_WS_AMT")));
				 	    				
				 	    				vo.setGeneratedPendingAmount(convertRupeesIntoCrores(jObj.getString("FNG_WS_AMT")));
				 	    				vo.setGeneratedAmount(convertRupeesIntoCrores(jObj.getString("FG_WS_AMT")));
				 	    				
				 	    				vo.setUploadPendingAmount(convertRupeesIntoCrores(jObj.getString("FNU_WS_AMT")));
				 	    				vo.setUploadAmount(convertRupeesIntoCrores(jObj.getString("FU_WS_AMT")));
				 	    				
				 	    				vo.setSentToBankPendingAmount(convertRupeesIntoCrores(jObj.getString("FNS_WS_AMT")));
				 	    				vo.setSentToBankAmount(convertRupeesIntoCrores(jObj.getString("FS_WS_AMT")));
				 	    				
				 	    				vo.setFailedTransactionAmount(convertRupeesIntoCrores(jObj.getString("FR_WS_AMT")));
				 	    				vo.setPendingAtBankAmount(convertRupeesIntoCrores(jObj.getString("FRP_WS_AMT")));
				 	    				vo.setSentToBankSuccessAmt(convertRupeesIntoCrores(jObj.getString("FP_WS_AMT")));
				 	    				
								 	    vo.setTotalPendings(convertRupeesIntoLakhes(String.valueOf(Long.valueOf(jObj.getString("FNG_WS_CNT"))+Long.valueOf(jObj.getString("FNU_WS_CNT"))+Long.valueOf(jObj.getString("FNS_WS_CNT"))+Long.valueOf(jObj.getString("FRP_WS_CNT")))));
								 	    vo.setTotalPendinAmount(convertRupeesIntoCrores(String.valueOf(Long.valueOf(jObj.getString("FNG_WS_AMT"))+Long.valueOf(jObj.getString("FNU_WS_AMT"))+Long.valueOf(jObj.getString("FNS_WS_AMT"))+Long.valueOf(jObj.getString("FRP_WS_AMT")))));
		 	    				}else{
			 	    					vo.setType(jObj.getString("TYPE"));
			 	    					
				 	    				vo.setTotalPayments(jObj.getString("T_WS_CNT"));
				 	    				vo.setTotalAmount(jObj.getString("T_WS_AMT"));
				 	    				
				 	    				vo.setGeneratedPendingQuantity(jObj.getString("FNG_WS_CNT"));
				 	    				vo.setGeneratedPendingAmount(jObj.getString("FNG_WS_AMT"));
				 	    				
				 	    				vo.setGeneratedQuantity(jObj.getString("FG_WS_CNT"));
				 	    				vo.setGeneratedAmount(jObj.getString("FG_WS_AMT"));
				 	    				
				 	    				vo.setUploadPendingQunatity(jObj.getString("FNU_WS_CNT"));
				 	    				vo.setUploadPendingAmount(jObj.getString("FNU_WS_AMT"));
				 	    				
				 	    				vo.setUploadQuantity(jObj.getString("FU_WS_CNT"));
				 	    				vo.setUploadAmount(jObj.getString("FU_WS_AMT"));
				 	    				
				 	    				vo.setSentToBankPendingQuantity(jObj.getString("FNS_WS_CNT"));
				 	    				vo.setSentToBankPendingAmount(jObj.getString("FNS_WS_AMT"));
				 	    				
				 	    				vo.setSentToBankQuantity(jObj.getString("FS_WS_CNT"));
				 	    				vo.setSentToBankAmount(jObj.getString("FS_WS_AMT"));
				 	    				
				 	    				vo.setFailedTransactionQuantity(jObj.getString("FR_WS_CNT"));
				 	    				vo.setFailedTransactionAmount(jObj.getString("FR_WS_AMT"));
				 	    				
				 	    				vo.setPendingAtBankQuantity(jObj.getString("FRP_WS_CNT"));
				 	    				vo.setPendingAtBankAmount(jObj.getString("FRP_WS_AMT"));
				 	    				
				 	    				vo.setSentToBankSuccess(jObj.getString("FP_WS_CNT"));
				 	    				vo.setSentToBankSuccessAmt(jObj.getString("FP_WS_AMT"));
				 	    				
				 	    				vo.setTotalPendings(convertRupeesIntoLakhes(String.valueOf(Long.valueOf(jObj.getString("FNG_WS_CNT"))+Long.valueOf(jObj.getString("FNU_WS_CNT"))+Long.valueOf(jObj.getString("FNS_WS_CNT"))+Long.valueOf(jObj.getString("FRP_WS_CNT")))));
								 	    vo.setTotalPendinAmount(convertRupeesIntoCrores(String.valueOf(Long.valueOf(jObj.getString("FNG_WS_AMT"))+Long.valueOf(jObj.getString("FNU_WS_AMT"))+Long.valueOf(jObj.getString("FNS_WS_AMT"))+Long.valueOf(jObj.getString("FRP_WS_AMT")))));
		 	    				}
		 	    				
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
					if(levelId != null && levelId == IConstants.STATE_LEVEL_SCOPE_ID){
						LocationFundDetailsVO locationFundDetailsVO = new LocationFundDetailsVO();
							locationFundDetailsVO.setType(commonMethodsUtilService.getStringValueForObject(objects[0]));//District_code
							locationFundDetailsVO.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
							detailsVOs.add(locationFundDetailsVO);
					}else if(levelId != null && levelId == IConstants.DISTRICT_LEVEL_SCOPE_ID){
						LocationFundDetailsVO locationFundDetailsVO = new LocationFundDetailsVO();
							locationFundDetailsVO.setType(commonMethodsUtilService.getStringValueForObject(objects[0]));//constituency code
							locationFundDetailsVO.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
							locationFundDetailsVO.setYear(commonMethodsUtilService.getStringValueForObject(objects[2]));//District_code
							detailsVOs.add(locationFundDetailsVO);
					}
					
						
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
	 	    			if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("state") && inputVO.getSublocationType().trim().equalsIgnoreCase("state")){
	 	    				for(int i=0;i<finalArray.length();i++){
		 	    				NregsDataVO vo = new NregsDataVO();
		 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
		 	    				vo.setState(jObj.getString("UNIQUEID"));
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
		 	    				if(inputVO.getLocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("district"))
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
	/*
	 * Date : 15/07/2017
	 * Author :Nandhini
	 * @description : getNregaParliamentDataFrpayments
	 */
	public List<NregaPaymentsVO> getNregaParliamentDataFrpayments(InputVO inputVO){
		List<NregaPaymentsVO> finalVOList = new ArrayList<NregaPaymentsVO>(0);
		try {
			String webServiceUrl = null;
			Map<Long,List<String>> consParlMap = new HashMap<Long,List<String>>(0);
			List<Long> parlIds = new ArrayList<Long>(0);
			Map<Long,String> parlmentMap = new HashMap<Long,String>(0);
			List<Long> constuIds = new ArrayList<Long>(0);
			List<NregaPaymentsVO> consList = new ArrayList<NregaPaymentsVO>(0);
			
			String str = null;
			if(inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().toString().length() > 0l)
				inputVO.setSublocationType(inputVO.getSublocaType().trim());
			
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Payments"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/PaymentsDataNewServices/PaymentsDataNew";
			
			 str = convertingInputVOToString(inputVO);
			 
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
	       
			if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
		 	    	if(output != null && !output.isEmpty()){
		 	    		JSONArray finalArray = new JSONArray(output);
		 	    		if(finalArray!=null && finalArray.length()>0){
		 	    				for(int i=0;i<finalArray.length();i++){
		 	    					NregaPaymentsVO nregaPaymentsVO = new NregaPaymentsVO();
		 	    					JSONObject jObj = (JSONObject) finalArray.get(i);
		 	    					nregaPaymentsVO.setId(jObj.getString("UNIQUEID"));
		 	    					nregaPaymentsVO.setDistrictName(jObj.getString("DISTRICT"));
		 	    					nregaPaymentsVO.setConstName(jObj.getString("CONSTITUENCY"));
		 	    					nregaPaymentsVO.setType(jObj.getString("TYPE"));
		 	    					nregaPaymentsVO.setGeneratedQuantity(jObj.getString("T_WS_CNT"));
		 	    					nregaPaymentsVO.setGeneratedAmount(jObj.getString("T_WS_AMT"));
		 	    					nregaPaymentsVO.setGeneratedPendingQuantity(jObj.getString("FNG_WS_CNT"));
		 	    					nregaPaymentsVO.setGeneratedPendingAmount(jObj.getString("FNG_WS_AMT"));
		 	    					nregaPaymentsVO.setUploadQuantity(jObj.getString("FG_WS_CNT"));
		 	    					nregaPaymentsVO.setUploadAmount(jObj.getString("FG_WS_AMT"));
		 	    					nregaPaymentsVO.setUploadPendingQunatity(jObj.getString("FNU_WS_CNT"));
		 	    					nregaPaymentsVO.setUploadPendingAmount(jObj.getString("FNU_WS_AMT"));
		 	    					nregaPaymentsVO.setSentToBankQuantity(jObj.getString("FU_WS_CNT"));
		 	    					nregaPaymentsVO.setSentToBankAmount(jObj.getString("FU_WS_AMT"));
		 	    					nregaPaymentsVO.setSentToBankPendingQuantity(jObj.getString("FNS_WS_CNT"));
		 	    					nregaPaymentsVO.setSentToBankPendingAmount(jObj.getString("FNS_WS_AMT"));
		 	    					nregaPaymentsVO.setFailedTransactionQuantity(jObj.getString("FR_WS_CNT"));
		 	    					nregaPaymentsVO.setFailedTransactionAmount(jObj.getString("FR_WS_AMT"));
		 	    					nregaPaymentsVO.setFailedTransactionPendingQuantity(jObj.getString("FRP_WS_CNT"));
		 	    					nregaPaymentsVO.setFailedTransactionPendingAmount(jObj.getString("FRP_WS_AMT"));
		 	    					nregaPaymentsVO.setFtoSentToBank(jObj.getString("FS_WS_CNT"));
		 	    					nregaPaymentsVO.setFtoSentToAmount(jObj.getString("FS_WS_AMT"));
		 	    					nregaPaymentsVO.setSentToBankSuccess(jObj.getString("FP_WS_CNT"));
		 	    					nregaPaymentsVO.setSentToBankSuccessAmt(jObj.getString("FP_WS_AMT"));
			 	    					 	    				
			 	    				consList.add(nregaPaymentsVO);	 
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
				 String[] typeArr = {"w","M","Sub Total"};
				 for (String string : typeArr) {
					 NregaPaymentsVO paymentParlVO = new NregaPaymentsVO();
					 if(commonMethodsUtilService.isListOrSetValid(constIds)){
						 for (String constId : constIds) {
						 NregaPaymentsVO vo = getMatchedVOList(consList,constId,string);
						 if(vo != null){
								paymentParlVO.setType(vo.getType());
								paymentParlVO.setGeneratedQuantity(String.valueOf(Long.valueOf(vo.getGeneratedQuantity())+Long.valueOf(paymentParlVO.getGeneratedQuantity())));
								paymentParlVO.setGeneratedAmount(String.valueOf(Long.valueOf(vo.getGeneratedAmount())+Long.valueOf(paymentParlVO.getGeneratedAmount())));
								paymentParlVO.setGeneratedPendingQuantity(String.valueOf(Long.valueOf(vo.getGeneratedPendingQuantity())+Long.valueOf(paymentParlVO.getGeneratedPendingQuantity())));
								paymentParlVO.setGeneratedPendingAmount(String.valueOf(Long.valueOf(vo.getGeneratedPendingAmount())+Long.valueOf(paymentParlVO.getGeneratedPendingAmount())));
								paymentParlVO.setUploadQuantity(String.valueOf(Long.valueOf(vo.getUploadQuantity())+Long.valueOf(paymentParlVO.getUploadPendingQunatity())));
								paymentParlVO.setUploadAmount(String.valueOf(Long.valueOf(vo.getUploadAmount())+Long.valueOf(paymentParlVO.getUploadAmount())));
								paymentParlVO.setUploadPendingQunatity(String.valueOf(Long.valueOf(vo.getUploadPendingQunatity())+Long.valueOf(paymentParlVO.getUploadPendingQunatity())));
								paymentParlVO.setUploadPendingAmount(String.valueOf(Long.valueOf(vo.getUploadPendingAmount())+Long.valueOf(paymentParlVO.getUploadPendingAmount())));
								paymentParlVO.setSentToBankQuantity(String.valueOf(Long.valueOf(vo.getSentToBankQuantity())+Long.valueOf(paymentParlVO.getSentToBankQuantity())));
								paymentParlVO.setSentToBankAmount(String.valueOf(Long.valueOf(vo.getSentToBankAmount())+Long.valueOf(paymentParlVO.getSentToBankAmount())));
								paymentParlVO.setSentToBankPendingQuantity(String.valueOf(Long.valueOf(vo.getSentToBankPendingQuantity())+Long.valueOf(paymentParlVO.getSentToBankPendingQuantity())));
								paymentParlVO.setSentToBankPendingAmount(String.valueOf(Long.valueOf(vo.getSentToBankPendingAmount())+Long.valueOf(paymentParlVO.getSentToBankPendingAmount())));
								paymentParlVO.setFailedTransactionQuantity(String.valueOf(Long.valueOf(vo.getFailedTransactionQuantity())+Long.valueOf(paymentParlVO.getFailedTransactionQuantity())));
								paymentParlVO.setFailedTransactionAmount(String.valueOf(Long.valueOf(vo.getFailedTransactionAmount())+Long.valueOf(paymentParlVO.getFailedTransactionAmount())));
								paymentParlVO.setFailedTransactionPendingQuantity(String.valueOf(Long.valueOf(vo.getFailedTransactionPendingQuantity())+Long.valueOf(paymentParlVO.getFailedTransactionPendingQuantity())));
								paymentParlVO.setFailedTransactionPendingAmount(String.valueOf(Long.valueOf(vo.getFailedTransactionPendingAmount())+Long.valueOf(paymentParlVO.getFailedTransactionPendingAmount())));
								paymentParlVO.setFtoSentToBank(String.valueOf(Long.valueOf(vo.getFtoSentToBank())+Long.valueOf(paymentParlVO.getFtoSentToBank())));
								paymentParlVO.setFtoSentToAmount(String.valueOf(Long.valueOf(vo.getFtoSentToAmount())+Long.valueOf(paymentParlVO.getFtoSentToAmount())));
								paymentParlVO.setSentToBankSuccess(String.valueOf(Long.valueOf(vo.getSentToBankSuccess())+Long.valueOf(paymentParlVO.getSentToBankSuccess())));
								paymentParlVO.setSentToBankSuccessAmt(String.valueOf(Long.valueOf(vo.getSentToBankSuccessAmt())+Long.valueOf(paymentParlVO.getSentToBankSuccessAmt())));
								
							}
						 }
					 }
					 paymentParlVO.setParliamentId(parlmentId);
					paymentParlVO.setParliamentName(parlmentMap.get(parlmentId));
					finalVOList.add(paymentParlVO);
				 	}
				 }
				}
	 	    	
		 	 }
	 	   }
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaParliamentDataFrpayments - NREGSTCSService service", e);
		}
		
		return finalVOList;
	}
	/*
	 * Date : 14/07/2017
	 * Author :Nandhini
	 * @description : getMatchedVOList
	 */
	public NregaPaymentsVO getMatchedVOList(List<NregaPaymentsVO> list,String constId,String type){
		try{
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for (NregaPaymentsVO nregaPaymentsVO : list) {
					if(nregaPaymentsVO.getId().equals(constId) && nregaPaymentsVO.getType().trim().equalsIgnoreCase(type)){
						return nregaPaymentsVO;
					}
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception raised at getMatchedVOList - NREGSTCSService service", e);
		}
		return null;
	}
	/*
	 * Date : 18/07/2017
	 * Author :Nandhini
	 * @description : getNREGSProjectsAbstractNewFrConstituency
	 */
	 
	public List<NregsProjectsVO> getNREGSProjectsAbstractNewFrConstituency(InputVO inputVO){
		List<NregsProjectsVO> voList = new ArrayList<NregsProjectsVO>(0);
		try {
			String projectType = null;
			Long constituencyId = inputVO.getLocationId();
			Long districtId = inputVO.getDistrictId();
			
			String str = convertingInputVOToString(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CMDashBoard/AbstractNew", str);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	  if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("Burial Ground"))
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
	 	    					projectType = jObj.getString("CAT_NAME");
	 	    				
	 	    				String[]  projectTypeArr = projectType.split("_");
	 	    				if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase(projectTypeArr[0])){
	 	    					vo.setParameter(jObj.getString("CAT_NAME"));
	 	    					vo.setType("cONSTITUENCY");
	 	    					vo.setTarget(jObj.getString("TARGET"));
		 	    				vo.setCompleted(jObj.getString("COMPLETED"));
		 	    				vo.setPercentage(jObj.getString("PERC"));
		 	    				voList.add(vo);
	 	    				}
	 	    			}
	 	    		}
	 	    	}
	 	    	 
	 	     }
	        
	        inputVO.setLocationType("district");
			inputVO.setLocationId(districtId);
			if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("Burial Ground"))
	    		  inputVO.setType("Burial Ground");
	    	  else if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("Anganwadi"))
	    		  inputVO.setType("Anganwadi Buildings");
	    	  else if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("Gram Panchayat Buildings"))
	    		  inputVO.setType("GP Buildings");
	    	 else if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("Silk worm"))
	    		  inputVO.setType("Silk Worms");
	    	 else if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("Cattle drinking water trough"))
	    		  inputVO.setType("Cattle Drinking Water Troughs");
	    	 else if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("Raising of Perinnial Fodder"))
	 	    	  inputVO.setType("Raising of Perinnial Fodders");
			String str1 = convertingInputVOToString(inputVO);
			
			ClientResponse distResponse = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CMDashBoard/AbstractNew", str1);
	       
	        if(distResponse.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ distResponse.getStatus());
	 	      }else{
	 	    	  if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("Burial Ground"))
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
	 	    	  
	 	    	 String output = distResponse.getEntity(String.class);

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
		 	    				
		 	    				if(voList != null && voList.size() > 0l){
		 	    					voList.get(0).getSubList().add(vo);
		 	    				}else{
		 	    					NregsProjectsVO newVO = new NregsProjectsVO();
		 	    					newVO.getSubList().add(vo);
		 	    					voList.add(newVO);
		 	    				}
	 	    				}
	 	    			}
	 	    		}
	 	    	}
	 	    	 
	 	     }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSProjectsAbstractNewFrConstituency - NREGSTCSService service", e);
		}
		
		return voList;
	}
	
	/*
	 * Date : 17/07/2017
	 * Author :Nandhini
	 * @description : getNregaPaymentsAbsAndOverview
	 */
	public NregaPaymentsVO getNregaPaymentsAbsAndOverview(InputVO inputVO){
		NregaPaymentsVO returnVO = new NregaPaymentsVO();
		try {
			if(inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().toString().length() > 0l)
				inputVO.setSublocationType(inputVO.getSublocaType().trim());
			
			String webServiceUrl = null;
			
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Payments"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/PaywgpmatExpenditureNew/PaywgpmatExpOverviewNew";
			
			String str = convertingInputVOToString(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
    				JSONObject jObj = new JSONObject(output);
		 	    	 returnVO.setTotalPayments(convertRupeesIntoLakhes(jObj.getString("TOTAL_PAYMENTS")));
		 	    	 returnVO.setTotalAmount(convertRupeesIntoCrores(jObj.getString("TOTAL_AMOUNT")));
		 	    	//Total Wages Spliting
		 	    	 returnVO.setTotalWage(convertRupeesIntoLakhes(jObj.getString("TOTAL_WAGE").split(" ")[0]));
		 	    	 //Total Material Spliting
		 	    	 returnVO.setTotalMaterial(convertRupeesIntoLakhes(jObj.getString("TOTAL_MATERIAL").split(" ")[0]));
		 	    	//Completed Wage Spliting
		 	    	 returnVO.setCompletedWage(convertRupeesIntoLakhes(jObj.getString("COMPLETED_WAGE").split(" ")[0]));
		 	    	 returnVO.setCompletedWageAmount(convertRupeesIntoCrores(jObj.getString("COMPLETED_WAGE").split(" ")[1].trim().replace(")", "").replace("(", "")));
		 	    	//Completed Material Spliting
		 	    	 returnVO.setCompletedMaterial(convertRupeesIntoLakhes(jObj.getString("COMPLETED_MATERIAL").split(" ")[0]));
		 	    	 returnVO.setCompletedMaterialAmount(convertRupeesIntoCrores(jObj.getString("COMPLETED_MATERIAL").split(" ")[1].trim().replace(")", "").replace("(", "")));
		 	    	//Pending Wage Spliting
		 	    	 returnVO.setPendingWage(convertRupeesIntoLakhes(jObj.getString("PENDING_WAGE").split(" ")[0]));
		 	    	 returnVO.setPendingWageAmount(convertRupeesIntoCrores(jObj.getString("PENDING_WAGE").split(" ")[1].trim().replace(")", "").replace("(", "")));
		 	    	//Pending Material Spliting
		 	    	 returnVO.setPendingMaterial(convertRupeesIntoLakhes(jObj.getString("PENDING_MATERIAL").split(" ")[0]));
		 	    	 returnVO.setPendingMaterialAmount(convertRupeesIntoCrores(jObj.getString("PENDING_MATERIAL").split(" ")[1].trim().replace(")", "").replace("(", "")));
		 	    	//Failed Wage Spliting
		 	    	 returnVO.setFailedWage(convertRupeesIntoLakhes(jObj.getString("FAILED_WAGE").split(" ")[0]));
		 	    	 returnVO.setFailedWageAmount(convertRupeesIntoCrores(jObj.getString("FAILED_WAGE").split(" ")[1].trim().replace(")", "").replace("(", "")));
		 	    	//Failed Material Spliting
		 	    	 returnVO.setFailedMaterial(jObj.getString("FAILED_MATERIAL").split(" ")[0]);
		 	    	 returnVO.setFailedMaterialAmount(convertRupeesIntoCrores(jObj.getString("FAILED_MATERIAL").split(" ")[1].trim().replace(")", "").replace("(", "")));
		 	    	//Generated Wage Spliting
		 	    	 returnVO.setGeneratedWage(convertRupeesIntoLakhes(jObj.getString("GENERATED_WAGE").split(" ")[0]));
		 	    	 returnVO.setGeneratedWageAmount(convertRupeesIntoCrores(jObj.getString("GENERATED_WAGE").split(" ")[1].trim().replace(")", "").replace("(", "")));
		 	    	//Generated Material Spliting
		 	    	 returnVO.setGeneratedMaterial(convertRupeesIntoLakhes(jObj.getString("GENERATED_MATERIAL").split(" ")[0]));
		 	    	 returnVO.setGeneratedMaterialAmount(convertRupeesIntoCrores(jObj.getString("GENERATED_MATERIAL").split(" ")[1].trim().replace(")", "").replace("(", "")));
		 	    	 returnVO.setTotalGenerates(convertRupeesIntoLakhes(String.valueOf(Long.valueOf(jObj.getString("GENERATED_WAGE").split(" ")[0])+Long.valueOf(jObj.getString("GENERATED_MATERIAL").split(" ")[0]))));
		 	    	//Uploaded Wage Spliting
		 	    	 returnVO.setUploadWage(convertRupeesIntoLakhes(jObj.getString("UPLOADED_WAGE").split(" ")[0]));
		 	    	 returnVO.setUploadedWageAmount(convertRupeesIntoCrores(jObj.getString("UPLOADED_WAGE").split(" ")[1].trim().replace(")", "").replace("(", "")));
		 	    	//Uploaded Material Spliting
		 	    	 returnVO.setUploadMaterial(convertRupeesIntoLakhes(jObj.getString("UPLOADED_MATERIAL").split(" ")[0]));
		 	    	 returnVO.setUploadedMaterialAmount(convertRupeesIntoCrores(jObj.getString("UPLOADED_MATERIAL").split(" ")[1].trim().replace(")", "").replace("(", "")));
		 	    	 returnVO.setTotalUploads(convertRupeesIntoLakhes(String.valueOf(Long.valueOf(jObj.getString("UPLOADED_WAGE").split(" ")[0])+Long.valueOf(jObj.getString("UPLOADED_MATERIAL").split(" ")[0]))));
		 	    	//SentBank Wage Spliting
		 	    	 returnVO.setSentBankWage(convertRupeesIntoLakhes(jObj.getString("SENTBANK_WAGE").split(" ")[0]));
		 	    	 returnVO.setSentBankWageAmount(convertRupeesIntoCrores(jObj.getString("SENTBANK_WAGE").split(" ")[1].trim().replace(")", "").replace("(", "")));
		 	    	//SentBank Material Spliting
		 	    	 returnVO.setSentBankMaterial(convertRupeesIntoLakhes(jObj.getString("SENTBANK_MATERIAL").split(" ")[0]));
		 	    	 returnVO.setSentBankMaterialAmount(convertRupeesIntoCrores(jObj.getString("SENTBANK_MATERIAL").split(" ")[1].trim().replace(")", "").replace("(", "")));
		 	    	 returnVO.setTotalSentBankS(convertRupeesIntoLakhes(String.valueOf(Long.valueOf(jObj.getString("SENTBANK_WAGE").split(" ")[0])+Long.valueOf(jObj.getString("SENTBANK_MATERIAL").split(" ")[0]))));
		 	    	 returnVO.setTotalPendings(convertRupeesIntoLakhes(String.valueOf(Long.valueOf(jObj.getString("PENDING_WAGE").split(" ")[0])+Long.valueOf(jObj.getString("PENDING_MATERIAL").split(" ")[0]))));
		 	    	 returnVO.setTotalGeneratesAmount(convertRupeesIntoCrores(String.valueOf(Long.valueOf(jObj.getString("GENERATED_WAGE").split(" ")[1].trim().replace(")", "").replace("(", ""))+Long.valueOf(jObj.getString("GENERATED_MATERIAL").split(" ")[1].trim().replace(")", "").replace("(", "")))));
		 	    	 returnVO.setTotalUploadsAmount(convertRupeesIntoCrores(String.valueOf(Long.valueOf(jObj.getString("UPLOADED_WAGE").split(" ")[1].trim().replace(")", "").replace("(", ""))+Long.valueOf(jObj.getString("UPLOADED_MATERIAL").split(" ")[1].trim().replace(")", "").replace("(", "")))));
		 	    	 returnVO.setTotalSentBankAmount(convertRupeesIntoCrores(String.valueOf(Long.valueOf(jObj.getString("SENTBANK_WAGE").split(" ")[1].trim().replace(")", "").replace("(", ""))+Long.valueOf(jObj.getString("SENTBANK_MATERIAL").split(" ")[1].trim().replace(")", "").replace("(", "")))));
		 	    	returnVO.setTotalPendinAmount(convertRupeesIntoCrores(String.valueOf(Long.valueOf(jObj.getString("PENDING_WAGE").split(" ")[1].trim().replace(")", "").replace("(", ""))+Long.valueOf(jObj.getString("PENDING_MATERIAL").split(" ")[1].trim().replace(")", "").replace("(", "")))));
		 	    	}
	 	      }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaPaymentsAbsAndOverview - NREGSTCSService service", e);
		}
		
		return returnVO;
	}
	public String convertRupeesIntoLakhes(String value){
		String returnVal = null;
		try {
			if(value != null){
				returnVal = new BigDecimal(Long.valueOf(value)/100000.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				returnVal = returnVal+" L";
			}
		} catch (Exception e) {
			LOG.error("Exception raised at convertRupeesIntoLakhes - NREGSTCSService service", e);
		}
		return returnVal;
	}
	/*
	 * Date : 18/07/2017
	 * Author :Nandhini
	 * @description : getNREGSAbstractDataByTypeFrConstituency
	 */
	public List<NregsProjectsVO> getNREGSAbstractDataByTypeFrConstituency(InputVO inputVO){
		List<NregsProjectsVO> returnList = new ArrayList<>();
		try {
			Long constituencyId = inputVO.getLocationId();
			Long districtId = inputVO.getDistrictId();
			
			String str = null;
			ClientResponse response = null;
			if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("GH"))
				str = convertingInputVOToStringForIWMP(inputVO);
			else
				str = convertingInputVOToString(inputVO);
			
			if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("GH"))
				response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/WaterBudgetService/AbstractDataIwmp", str);
			else
				response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CMDashBoard/AbstractNew", str);
			
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
	 	    				
	 	    				if(inputVO.getType().toString().trim().equalsIgnoreCase("Nurseries"))
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
	 	    				else if(inputVO.getType().toString().trim().equalsIgnoreCase("Horticulture") || inputVO.getType().toString().trim().equalsIgnoreCase("Avenue")){
	 	    					vo.setTarget(jObj.getString("TARGET"));
		 	    				vo.setCompleted(jObj.getString("ACHIVEMENT"));
	 	    				}else if(inputVO.getType().toString().trim().equalsIgnoreCase("GH")){
								vo.setTarget(jObj.getString("TARGET"));
								vo.setCompleted(jObj.getString("ACHMT"));
							}
	 	    				else{
	 	    					vo.setTarget(jObj.getString("TARGET"));
		 	    				vo.setCompleted(jObj.getString("COMPLETED"));
	 	    				}
	 	    				
	 	    				if(inputVO.getType().toString().trim().equalsIgnoreCase("Agriculture Activities")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("SMC Trench") || inputVO.getType().toString().trim().equalsIgnoreCase("Imp to CD")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("MPT_PT") || inputVO.getType().toString().trim().equalsIgnoreCase("GC Works")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("CD_CW")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("Check Dam") || inputVO.getType().toString().trim().equalsIgnoreCase("Rock fill dams"))
	 	    					vo.setPercentage(jObj.getString("PERC"));
	 	    				else if(inputVO.getType().toString().trim().equalsIgnoreCase("Average Wage")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("Average Days of Employment")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("HH Completed 100 Days")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("Timely Payment")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("Horticulture") || inputVO.getType().toString().trim().equalsIgnoreCase("Avenue")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("GH"))
	 	    					vo.setPercentage(jObj.getString("PER"));
	 	    				else
	 	    					vo.setPercentage(jObj.getString("PERCENTAGE"));
	 	    					vo.setType("CONSTITUENCY");
	 	    				
	 	    				returnList.add(vo);
	    				}
	 	    		}
	 	    	}
	 	    }
			
			inputVO.setLocationType("district");
			inputVO.setLocationId(districtId);
			String str1 = null;
			ClientResponse distResponse = null;
			
			if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("GH"))
				str1 = convertingInputVOToStringForIWMP(inputVO);
			else
				str1 = convertingInputVOToString(inputVO);
			
			if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("GH"))
				distResponse = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/WaterBudgetService/AbstractDataIwmp", str1);
			else
				distResponse = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CMDashBoard/AbstractNew", str1);
			
			if(distResponse.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ distResponse.getStatus());
	 	      }else{
	 	    	 String output = distResponse.getEntity(String.class);
	 	    	 
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
	 	    				else if(inputVO.getType().toString().trim().equalsIgnoreCase("GH")){
								vo.setTarget(jObj.getString("TARGET"));
								vo.setCompleted(jObj.getString("ACHMT"));
							}
	 	    				else{
	 	    					vo.setTarget(jObj.getString("TARGET"));
		 	    				vo.setCompleted(jObj.getString("COMPLETED"));
	 	    				}
	 	    				
	 	    				if(inputVO.getType().toString().trim().equalsIgnoreCase("Agriculture Activities")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("SMC Trench") || inputVO.getType().toString().trim().equalsIgnoreCase("Imp to CD")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("MPT_PT") || inputVO.getType().toString().trim().equalsIgnoreCase("GC Works")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("CD_CW")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("Check Dam") || inputVO.getType().toString().trim().equalsIgnoreCase("Rock fill dams"))
	 	    					vo.setPercentage(jObj.getString("PERC"));
	 	    				else if(inputVO.getType().toString().trim().equalsIgnoreCase("Average Wage")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("Average Days of Employment")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("HH Completed 100 Days")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("Timely Payment")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("Horticulture") || inputVO.getType().toString().trim().equalsIgnoreCase("Avenue")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("GH"))
	 	    					vo.setPercentage(jObj.getString("PER"));
	 	    				else
	 	    					vo.setPercentage(jObj.getString("PERCENTAGE"));
	 	    				
	 	    				if(inputVO.getLocationType().trim().equalsIgnoreCase("state"))
	 	    					vo.setType("STATE");
	 	    				else if(vo.getParameter().contains("state") || vo.getParameter().contains("State") || vo.getParameter().contains("STATE"))
	 	    					vo.setType("STATE");
	 	    				else if(vo.getParameter().contains("district") || vo.getParameter().contains("District") || vo.getParameter().contains("DISTRICT"))
	 	    					vo.setType("DISTRICT");
	 	    				
	 	    				if(returnList != null && returnList.size() > 0l){
	 	    					returnList.get(0).getSubList().add(vo);
	 	    				}else{
	 	    					NregsProjectsVO newVO = new NregsProjectsVO();
	 	    					newVO.getSubList().add(vo);
	 	    					returnList.add(newVO);
	 	    				}
	    				}
	 	    		}
	 	    	}
	 	    }
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSAbstractDataByTypeFrConstituency - NREGSTCSService service", e);
		}
		return returnList;
	}
	
	/*
	 * Date : 19/07/2017
	 * Author :Sravanth
	 * @description : getNregaPanchatVsExpLevelWiseCountsData
	 */
	public List<NregsDataVO> getNregaPanchatVsExpLevelWiseCountsData(InputVO inputVO){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
			String str = convertingInputVOToString(inputVO); 
			
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/LabourBudgetServiceNew/LabourBudgetDataPanchayatNew", str);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	Map<String,NregsDataVO> districtMap = new LinkedHashMap<String,NregsDataVO>();
	 	    	String output = response.getEntity(String.class);
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				NregsDataVO vo = new NregsDataVO();
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				
	 	    				NregsDataVO distvo = districtMap.get(jObj.getString("DISTRICT"));
	 	    				if(distvo != null){
	 	    					
	 	    					NregsDataVO constvo = getConstituencyMatchedVO(distvo.getSubList(), jObj.getString("ASSEMBLY"));
	 	    					if(constvo != null){
	 	    						
	 	    						NregsDataVO mandvo = getMandalMatchedVO(constvo.getSubList(), jObj.getString("MANDAL"));
	 	    						if(mandvo != null){
	 	    							
	 	    							NregsDataVO pancvo = getPanchayatMatchedVO(mandvo.getSubList(), jObj.getString("PANCHAYAT"));
	 	    							if(pancvo != null){
	 	    								pancvo.setCount(pancvo.getCount()+1l);
	 	    							}
	 	    							else{
	 	    								pancvo = new NregsDataVO();
	 	    								pancvo.setPanchayat(jObj.getString("PANCHAYAT"));
	 	    								pancvo.setCount(1l);
	 	    							
	 	    								mandvo.getSubList().add(pancvo);
	 	    							}
	 	    							mandvo.setCount(mandvo.getCount()+1l);
	 	    						}
	 	    						else{
	 	    							mandvo = new NregsDataVO();
	 	    							mandvo.setMandal(jObj.getString("MANDAL"));
	 	    							mandvo.setCount(1l);
	 	    							
	 	    								NregsDataVO pancvo = new NregsDataVO();
	 	    								pancvo.setPanchayat(jObj.getString("PANCHAYAT"));
	 	    								pancvo.setCount(1l);
	 	    							
	 	    								mandvo.getSubList().add(pancvo);
	 	    							
	 	    							constvo.getSubList().add(mandvo);
	 	    						}
	 	    						constvo.setCount(constvo.getCount()+1l);
	 	    					}
	 	    					else{
	 	    						constvo = new NregsDataVO();
	 	    						constvo.setConstituency(jObj.getString("ASSEMBLY"));
	 	    						constvo.setCount(1l);
	 	    							
	 	    							NregsDataVO mandvo = new NregsDataVO();
	 	    							mandvo.setMandal(jObj.getString("MANDAL"));
	 	    							mandvo.setCount(1l);
	 	    							
	 	    								NregsDataVO pancvo = new NregsDataVO();
	 	    								pancvo.setPanchayat(jObj.getString("PANCHAYAT"));
	 	    								pancvo.setCount(1l);
	 	    							
	 	    								mandvo.getSubList().add(pancvo);
	 	    							
	 	    							constvo.getSubList().add(mandvo);
	 	    						distvo.getSubList().add(constvo);
	 	    					}
	 	    					
	 	    					distvo.setCount(distvo.getCount()+1l);
	 	    				}
	 	    				else{
	 	    					distvo = new NregsDataVO();
	 	    					distvo.setDistrict(jObj.getString("DISTRICT"));
	 	    					
	 	    						NregsDataVO constvo = new NregsDataVO();
	 	    						constvo.setConstituency(jObj.getString("ASSEMBLY"));
	 	    						constvo.setCount(1l);
	 	    							
	 	    							NregsDataVO mandvo = new NregsDataVO();
	 	    							mandvo.setMandal(jObj.getString("MANDAL"));
	 	    							mandvo.setCount(1l);
	 	    							
	 	    								NregsDataVO pancvo = new NregsDataVO();
	 	    								pancvo.setPanchayat(jObj.getString("PANCHAYAT"));
	 	    								pancvo.setCount(1l);
	 	    							
	 	    							mandvo.getSubList().add(pancvo);
	 	    							
	 	    						constvo.getSubList().add(mandvo);
	 	    						
	 	    					distvo.getSubList().add(constvo);
	 	    					distvo.setCount(1l);
	 	    					districtMap.put(jObj.getString("DISTRICT"), distvo);
	 	    				}
	 	    			}
	 	    		}
	 	    	}
	 	    	if(districtMap != null)
	 	    		voList = new ArrayList<NregsDataVO>(districtMap.values());
	 	      }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaPanchatVsExpLevelWiseCountsData - NREGSTCSService service", e);
		}
		
		return voList;
	}
	
	/*
	 * Date : 19/07/2017
	 * Author :Sravanth
	 * @description : getConstituencyMatchedVO
	 */
	public NregsDataVO getConstituencyMatchedVO(List<NregsDataVO> list,String constituency){
		try{
			if(list != null && !list.isEmpty()){
				for (NregsDataVO nregsDataVO : list) {
					if(nregsDataVO.getConstituency().trim().equalsIgnoreCase(constituency.trim())){
						return nregsDataVO;
					}
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception raised at getConstituencyMatchedVO - NREGSTCSService service", e);
		}
		return null;
	}
	
	/*
	 * Date : 19/07/2017
	 * Author :Sravanth
	 * @description : getMandalMatchedVO
	 */
	public NregsDataVO getMandalMatchedVO(List<NregsDataVO> list,String mandal){
		try{
			if(list != null && !list.isEmpty()){
				for (NregsDataVO nregsDataVO : list) {
					if(nregsDataVO.getMandal().trim().equalsIgnoreCase(mandal.trim())){
						return nregsDataVO;
					}
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception raised at getMandalMatchedVO - NREGSTCSService service", e);
		}
		return null;
	}
	
	/*
	 * Date : 19/07/2017
	 * Author :Sravanth
	 * @description : getPanchayatMatchedVO
	 */
	public NregsDataVO getPanchayatMatchedVO(List<NregsDataVO> list,String panchayat){
		try{
			if(list != null && !list.isEmpty()){
				for (NregsDataVO nregsDataVO : list) {
					if(nregsDataVO.getPanchayat().trim().equalsIgnoreCase(panchayat.trim())){
						return nregsDataVO;
					}
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception raised at getPanchayatMatchedVO - NREGSTCSService service", e);
		}
		return null;
	}
	 /**
	  * @param  InputVO inputVO which contain fromDateStr,toDateStr,location and locationId
	  * @return NregaLocationOverviewVO
	  * @author Santosh 
	  * @Description :This Service Method is used to get IHHL abstract data.
	  * @since 24-JULY-2017
	  */
	public NregaLocationOverviewVO getIhhlAbstractData(InputVO inputVO){
		NregaLocationOverviewVO resultVO = new NregaLocationOverviewVO();
		try {
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://125.17.121.167/rwsapwebapi/api/values/Abstact_IHHLData?fromMonth="+inputVO.getFromDateStr()+"&toMonth="+inputVO.getToDateStr()+"&Location="+inputVO.getLocation()+"&locationID="+inputVO.getLocationIdStr());
	        ClientResponse response = webResource.accept("application/json").type("application/json").get(ClientResponse.class);
		        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject jsonObject = new JSONObject(output);
	 	    		JSONArray overViewArr = jsonObject.getJSONArray("MinisterDashBoardStateOverView");
	 	    		JSONArray locationOverviewArr = jsonObject.getJSONArray("MinisterDashBoardLocOverView");
	 	    		//overall overview
	 	    		if(overViewArr != null && overViewArr.length() > 0){
	 	    			resultVO.setSubList1(new ArrayList<NregaLocationOverviewVO>());
	 	    			for(int i=0;i<overViewArr.length();i++){
	 	    				JSONObject jObj = (JSONObject) overViewArr.get(i);
	 	    				NregaLocationOverviewVO vo = getIhhlSummaryData(jObj);
	 	    				resultVO.getSubList1().add(vo);
	 	    			}	
	 	    		}
	 	    		//location wise overview
	 	    		if(locationOverviewArr != null && locationOverviewArr.length() > 0){
	 	    			resultVO.setSubList2(new ArrayList<NregaLocationOverviewVO>());
	 	    			for(int i=0;i<locationOverviewArr.length();i++){
	 	    				JSONObject jObj = (JSONObject) locationOverviewArr.get(i);
	 	    				NregaLocationOverviewVO vo = getIhhlLocationWiseData(jObj);
	 	    				resultVO.getSubList2().add(vo);
	 	    			}	
	 	    		}
	 	    	}
	 	      }  
		} catch (Exception e) {
			LOG.error("Exception raised at getIhhlOverviewData - NREGSTCSService service", e);
		}
		return resultVO;
	}
	/**
	  * @param  InputVO inputVO which contain fromDateStr,toDateStr,location and locationId
	  * @return NregaLocationOverviewVO
	  * @author Santosh 
	  * @Description :This Service Method is used to get IHHL overview data.
	  * @since 24-JULY-2017
	  */
	public NregaLocationOverviewVO getIhhlOverviewData(InputVO inputVO){
		NregaLocationOverviewVO resultVO = new NregaLocationOverviewVO();
		try {
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://125.17.121.167/rwsapwebapi/api/values/Overview_IHHLData?fromMonth="+inputVO.getFromDateStr()+"&toMonth="+inputVO.getToDateStr()+"&Location="+inputVO.getLocation()+"&locationID="+inputVO.getLocationIdStr());
	        ClientResponse response = webResource.accept("application/json").type("application/json").get(ClientResponse.class);
		        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject jsonObject = new JSONObject(output);
	 	    		JSONArray overViewArr = jsonObject.getJSONArray("MinisterDashBoardStateOverView");
	 	    		JSONArray locationOverviewArr = jsonObject.getJSONArray("MinisterDashBoardLocOverView");
	 	    		//overall overview
	 	    		if(overViewArr != null && overViewArr.length() > 0){
	 	    			resultVO.setSubList1(new ArrayList<NregaLocationOverviewVO>());
	 	    			for(int i=0;i<overViewArr.length();i++){
	 	    				JSONObject jObj = (JSONObject) overViewArr.get(i);
	 	    				NregaLocationOverviewVO vo = getIhhlSummaryData(jObj);
	 	    				resultVO.getSubList1().add(vo);
	 	    			}	
	 	    		}
	 	    		//location wise overview
	 	    		if(locationOverviewArr != null && locationOverviewArr.length() > 0){
	 	    			resultVO.setSubList2(new ArrayList<NregaLocationOverviewVO>());
	 	    			for(int i=0;i<locationOverviewArr.length();i++){
	 	    				JSONObject jObj = (JSONObject) locationOverviewArr.get(i);
	 	    				NregaLocationOverviewVO vo = getIhhlLocationWiseData(jObj);
	 	    				resultVO.getSubList2().add(vo);
	 	    			}	
	 	    		}
	 	    	}
	 	      }  
		} catch (Exception e) {
			LOG.error("Exception raised at getIhhlOverviewData - NREGSTCSService service", e);
		}
		return resultVO;
	}
	public NregaLocationOverviewVO getIhhlSummaryData(JSONObject jObj){
		NregaLocationOverviewVO vo = new NregaLocationOverviewVO();
		try {
			    vo.setTarget(jObj.getLong("TARGET"));
				vo.setGrounded(jObj.getLong("GROUNDED"));
				vo.setNoTGrounded(jObj.getLong("NOT_GROUNDED"));
				vo.setCompleted(jObj.getLong("COMPLETED"));
				vo.setPercentage(new BigDecimal(jObj.getString("PERC")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		} catch(Exception e){
			LOG.error("Exception raised at getIhhlSummaryData() - NREGSTCSService service", e);
		}
		return vo;
	}
	public NregaLocationOverviewVO getIhhlLocationWiseData(JSONObject jObj){
		NregaLocationOverviewVO vo = new NregaLocationOverviewVO();
		try {
			    vo.setType(jObj.getString("TYPE"));
				vo.setGreen(jObj.getLong("GREEN"));
				vo.setRed(jObj.getLong("RED"));
				vo.setOrange(jObj.getLong("ORANGE"));
				vo.setTotal(jObj.getLong("TOTAL"));
		} catch(Exception e){
			LOG.error("Exception raised at getIhhlLocationWiseData() - NREGSTCSService service", e);
		}
		return vo;
	}
	/**
	  * @param  InputVO inputVO which contain fromDateStr,toDateStr,location and locationId
	  * @return List<NregaLocationOverviewVO>
	  * @author Santosh 
	  * @Description :This Service Method is used to get IHHL location wise details.
	  * @since 24-JULY-2017
	  */
	public List<NregaLocationOverviewVO> getLocationIhhlData(InputVO inputVO){
		List<NregaLocationOverviewVO> resultList = new ArrayList<NregaLocationOverviewVO>(0);
		try {
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://125.17.121.167/rwsapwebapi/api/values/Location_IHHLData?fromMonth="+inputVO.getFromDateStr()+"&toMonth="+inputVO.getToDateStr()+"&Location="+inputVO.getLocation()+"&subLocation="+inputVO.getSubLocation()+"&locationID="+inputVO.getLocationIdStr());
	        ClientResponse response = webResource.accept("application/json").type("application/json").get(ClientResponse.class);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject jsonObject = new JSONObject(output);
	 	    		JSONArray locationOverviewDtlsArr = jsonObject.getJSONArray("MinisterDashBoardLocationData");
	 	    		if(locationOverviewDtlsArr != null && locationOverviewDtlsArr.length() > 0){
	 	    			for(int i=0;i<locationOverviewDtlsArr.length();i++){
	 	    				NregaLocationOverviewVO locationVO = new NregaLocationOverviewVO();
	 	    				JSONObject jObj = (JSONObject) locationOverviewDtlsArr.get(i);
	 	    				locationVO.setUniqueId(jObj.getString("UNIQUEID"));
	 	    				locationVO.setDistrict(jObj.getString("DISTRICT"));
	 	    				locationVO.setConstituency(jObj.getString("CONSTITUENCY"));
	 	    				locationVO.setMandal(jObj.getString("MANDAL"));
	 	    				locationVO.setPanchayt(jObj.getString("PANCHAYAT"));
	 	    				locationVO.setTarget(jObj.getLong("TARGET"));
	 	    				locationVO.setGrounded(jObj.getLong("GROUNDED"));
	 	    				locationVO.setNoTGrounded(jObj.getLong("NOT_GROUNDED"));
	 	    				locationVO.setCompleted(jObj.getLong("COMPLETED"));
	 	    				locationVO.setPercentage(new BigDecimal(jObj.getString("PERC")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				resultList.add(locationVO);
	 	    			}	
	 	    		}
	 	    	}
	 	      }  
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationIhhlData() - NREGSTCSService service", e);
		}
		return resultList;
	}
	
	public List<NregsProjectsVO> getNregsProjectsIWMPAbstract(InputVO inputVO){
		List<NregsProjectsVO> returnList = new ArrayList<NregsProjectsVO>();
		try {
			String projectType = null;
			String str = convertingInputVOToStringForIWMP(inputVO);
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/WaterBudgetService/AbstractDataIwmp", str);
	        
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
	 	    				vo.setTarget(jObj.getString("TARGET"));
	 	    				vo.setCompleted(jObj.getString("ACHMT"));
	 	    				vo.setPercentage(jObj.getString("PER"));
	 	    				
	 	    				returnList.add(vo);
	 	    			}
	 	    		}
	 	    	 }
	 	      }
		} catch (Exception e) {
			LOG.error("Exception raised at getNregsProjectsIWMPAbstract() - NREGSTCSService service", e);
		}
		return returnList;
	}
	
	public String convertingInputVOToStringForIWMP(InputVO inputVO){
		String str = "";
		try {
			if(inputVO.getLocationId() != null)
				if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("district")){
					if(inputVO.getLocationId().longValue() > 0l && inputVO.getLocationId().longValue() <= 9l)
						inputVO.setLocationIdStr("0"+inputVO.getLocationId().toString());
				}else if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("constituency")){
					if(inputVO.getLocationId().longValue() > 0l)
						inputVO.setLocationIdStr("0"+inputVO.getLocationId().toString());
				}
				
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
			if(inputVO.getType() != null)
				str += "\"Type\" : \""+inputVO.getType()+"\",";
			
			if(str.length() > 1)
				str = str.substring(0,str.length()-1);
			
			str += "}";
			
		} catch (Exception e) {
			LOG.error("Exception raised at convertingInputVOToStringForIWMP - NREGSTCSService service", e);
		}
		return str;
	}
	
	public List<NregsDataVO> getLocationWiseWaterBudgetDetails(InputVO inputVO){
		List<NregsDataVO> returnList = new ArrayList<NregsDataVO>();
		try {
			if(inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().toString().length() > 0l)
				inputVO.setSublocationType(inputVO.getSublocaType().trim());
			
			String str = convertingInputVOToString(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/WaterBudgetService/WaterBudgetData", str);
	        
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
	 	    				vo.setTarget(jObj.getLong("TGT"));
	 	    				vo.setAchivement(jObj.getString("ACHMT"));
	 	    				vo.setAchmtGT0(jObj.getString("ACHMTGT0"));
	 	    				vo.setAchmtLT0(jObj.getString("ACHMTLT0"));
	 	    				vo.setBalance(jObj.getString("BALANCE"));
	 	    				vo.setArea(jObj.getString("AREA"));
	 	    				vo.setGross(jObj.getString("GROSS"));
	 	    				vo.setStroageCap(jObj.getString("STRG_CAP"));
	 	    				vo.setBalanceRunOff(jObj.getString("BALANCERUNOFF"));
	 	    				
	 	    				returnList.add(vo);
	 	    			}
	 	    		}
	 	    	}
	 	    }
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationWiseWaterBudgetDetails - NREGSTCSService service", e);
		}
		return returnList;
	}
	 /**
	  * @param  InputVO inputVO which contain InputVO inputVO which contain fromDate,toDate,locationType,Year and locationId
	  * @return List<NregaPaymentsVO>
	  * @author Santosh 
	  * @Description :This Service Method is used to get payment overview details.
	  * @since 31-JULY-2017
	  */
	public List<NregaPaymentsVO> getNregaPaymentsAbsAndOverviewDtls(InputVO inputVO) {
		List<NregaPaymentsVO> resultList = new ArrayList<NregaPaymentsVO>(0);
		try {
			if (inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().toString().length() > 0l) {
				inputVO.setSublocationType(inputVO.getSublocaType().trim());
			}
				
			String  webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/APPaymentsService/APPaymentsOverview";
			
			String str = convertingInputVOToString(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
	        
	        if (response.getStatus() != 200) {
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      } else {
	 	    	 String output = response.getEntity(String.class);
	 	    	 if (output != null && output.length() > 0) {
	 	    		 JSONArray paymentOverviewDataArr = new JSONArray(output);	 
	 	    		 if (paymentOverviewDataArr != null && paymentOverviewDataArr.length() > 0) {
	 	    			  for (int i = 0 ;i < paymentOverviewDataArr.length() ;i++) {
	 	    				  
	 	    				 NregaPaymentsVO paymentDtlsVO = new NregaPaymentsVO();
	 	    				 JSONObject jsonObj = (JSONObject) paymentOverviewDataArr.get(i);
	 	    				  
	 	    				paymentDtlsVO.setId(jsonObj.getString("UNIQUEID"));
	 	    				paymentDtlsVO.setTotalAmount(cnvrtRupeesIntoCrores(jsonObj.getString("TOTAL_AMOUNT")));
	 	    				paymentDtlsVO.setTotalWage(cnvrtRupeesIntoCrores(jsonObj.getString("TOTAL_WAGE_AMT")));
	 	    				paymentDtlsVO.setTotalMaterial(cnvrtRupeesIntoCrores(jsonObj.getString("TOTAL_MATERIAL_AMT")));
	 	    				  
	 	    				paymentDtlsVO.setGeneratedWageAmount(cnvrtRupeesIntoCrores(jsonObj.getString("GENERATED_WAGES_AMT")));
	 	    				paymentDtlsVO.setGeneratedMaterialAmount(cnvrtRupeesIntoCrores(jsonObj.getString("GENERATED_MATERIAL_AMT")));
	 	    				  
	 	    				paymentDtlsVO.setTotalGeneratesAmount(cnvrtRupeesIntoCrores(String.valueOf(Double.valueOf(jsonObj.getString("GENERATED_WAGES_AMT"))+Double.valueOf(jsonObj.getString("GENERATED_MATERIAL_AMT")))));
	 	    				  
	 	    				paymentDtlsVO.setNotGeneratedWagesAmount(cnvrtRupeesIntoCrores(jsonObj.getString("NOT_GENERATED_WAGES_AMT")));
	 	    				paymentDtlsVO.setNotGeneratedMaterialAmount(cnvrtRupeesIntoCrores(jsonObj.getString("NOT_GENERATED_MATERIAL_AMT")));
	 	    				  
	 	    				paymentDtlsVO.setTotalNotGeneratedAmount(cnvrtRupeesIntoCrores(String.valueOf(Double.valueOf(jsonObj.getString("NOT_GENERATED_WAGES_AMT"))+Double.valueOf(jsonObj.getString("NOT_GENERATED_MATERIAL_AMT")))));
	 	    				  
	 	    				paymentDtlsVO.setUploadedWageAmount(cnvrtRupeesIntoCrores(jsonObj.getString("UPLOADED_WAGES_AMT")));
	 	    				paymentDtlsVO.setUploadedMaterialAmount(cnvrtRupeesIntoCrores(jsonObj.getString("UPLOADED_MATERIAL_AMT")));
	 	    				  
	 	    				paymentDtlsVO.setTotalUploadsAmount(cnvrtRupeesIntoCrores(String.valueOf(Double.valueOf(jsonObj.getString("UPLOADED_WAGES_AMT"))+Double.valueOf(jsonObj.getString("UPLOADED_MATERIAL_AMT")))));
	 	    				  
	 	    				paymentDtlsVO.setNotUploadedWagesAmount(cnvrtRupeesIntoCrores(jsonObj.getString("NOT_UPLOADED_WAGES_AMT")));
	 	    				paymentDtlsVO.setNotUploadedMaterialAmount(cnvrtRupeesIntoCrores(jsonObj.getString("NOT_UPLOADED_MATERIAL_AMT")));
	 	    				  
	 	    				paymentDtlsVO.setTotalNotUploadedAmount(cnvrtRupeesIntoCrores(String.valueOf(Double.valueOf(jsonObj.getString("NOT_UPLOADED_WAGES_AMT"))+Double.valueOf(jsonObj.getString("NOT_UPLOADED_MATERIAL_AMT")))));
	 	    				  
	 	    				paymentDtlsVO.setSentBankWageAmount(cnvrtRupeesIntoCrores(jsonObj.getString("SENTPFMS_WAGES_AMT")));
	 	    				paymentDtlsVO.setSentBankMaterialAmount(cnvrtRupeesIntoCrores(jsonObj.getString("SENTPFMS_MATERIAL_AMT")));
	 	    				  
	 	    				paymentDtlsVO.setTotalSentBankAmount(cnvrtRupeesIntoCrores(String.valueOf(Double.valueOf(jsonObj.getString("SENTPFMS_WAGES_AMT"))+Double.valueOf(jsonObj.getString("SENTPFMS_MATERIAL_AMT")))));
	 	    				  
	 	    				paymentDtlsVO.setNotSentBankWageAmount(cnvrtRupeesIntoCrores(jsonObj.getString("NOT_SENTPFMS_WAGES_AMT")));
	 	    				paymentDtlsVO.setNotSentBankMaterialAmount(cnvrtRupeesIntoCrores(jsonObj.getString("NOT_SENTPFMS_MATERIAL_AMT")));
	 	    				  
	 	    				paymentDtlsVO.setTotalNotSentBankAmount(cnvrtRupeesIntoCrores(String.valueOf(Double.valueOf(jsonObj.getString("NOT_SENTPFMS_WAGES_AMT"))+Double.valueOf(jsonObj.getString("NOT_SENTPFMS_MATERIAL_AMT")))));
	 	    				  
	 	    				paymentDtlsVO.setCompletedWageAmount(cnvrtRupeesIntoCrores(jsonObj.getString("COMPLETED_WAGES_AMT")));
	 	    				paymentDtlsVO.setCompletedMaterialAmount(cnvrtRupeesIntoCrores(jsonObj.getString("COMPLETED_MATERIAL_AMT")));
	 	    				  
	 	    				paymentDtlsVO.setTotalCompletedAmount(cnvrtRupeesIntoCrores(String.valueOf(Double.valueOf(jsonObj.getString("COMPLETED_WAGES_AMT"))+Double.valueOf(jsonObj.getString("COMPLETED_MATERIAL_AMT")))));
	 	    				  
	 	    				paymentDtlsVO.setRejectedWagesAmount(cnvrtRupeesIntoCrores(jsonObj.getString("REJECT_WAGES_AMT")));
	 	    				paymentDtlsVO.setRejectedMaterialAmount(cnvrtRupeesIntoCrores(jsonObj.getString("REJECT_MATERIAL_AMT")));
	 	    				  
	 	    				paymentDtlsVO.setTotalRejectedAmount(cnvrtRupeesIntoCrores(String.valueOf(Double.valueOf(jsonObj.getString("REJECT_WAGES_AMT"))+Double.valueOf(jsonObj.getString("REJECT_MATERIAL_AMT")))));
	 	    				
	 	    				paymentDtlsVO.setReleasePendingWageAmount(cnvrtRupeesIntoCrores(jsonObj.getString("RELEASE_PENDING_WAGES_AMT")));
	 	    				paymentDtlsVO.setReleasePendingMaterialAmount(cnvrtRupeesIntoCrores(jsonObj.getString("RELEASE_PENDING_MATERIAL_AMT")));
	 	    				 
	 	    				paymentDtlsVO.setTotalReleasePendingAmount(cnvrtRupeesIntoCrores(String.valueOf(Double.valueOf(jsonObj.getString("RELEASE_PENDING_WAGES_AMT"))+Double.valueOf(jsonObj.getString("RELEASE_PENDING_MATERIAL_AMT")))));
	 	    				 
	 	    				paymentDtlsVO.setResponsePendingWageAmount(cnvrtRupeesIntoCrores(jsonObj.getString("RESPONSE_PENDING_WAGES_AMT")));
	 	    				paymentDtlsVO.setResponsePendingMaterialAmount(cnvrtRupeesIntoCrores(jsonObj.getString("RESPONSE_PENDING_MATERIAL_AMT")));
	 	    				
	 	    				paymentDtlsVO.setTotalResponsePendingAmount(cnvrtRupeesIntoCrores(String.valueOf(Double.valueOf(jsonObj.getString("RESPONSE_PENDING_WAGES_AMT"))+Double.valueOf(jsonObj.getString("RESPONSE_PENDING_MATERIAL_AMT")))));
	 	    				
	 	    				paymentDtlsVO.setReprocessPendingWageAmount(cnvrtRupeesIntoCrores(jsonObj.getString("REPROCESS_PENDING_WAGES_AMT")));
	 	    				paymentDtlsVO.setReprocessPendingMaterialAmount(cnvrtRupeesIntoCrores(jsonObj.getString("REPROCESS_PENDING_MATERIAL_AMT")));
	 	    				
	 	    				paymentDtlsVO.setTotalReprocessPendingAmount(cnvrtRupeesIntoCrores(String.valueOf(Double.valueOf(jsonObj.getString("REPROCESS_PENDING_WAGES_AMT"))+Double.valueOf(jsonObj.getString("REPROCESS_PENDING_MATERIAL_AMT")))));
	 	    				
	 	    				paymentDtlsVO.setTotalPendinAmount(cnvrtRupeesIntoCrores(String.valueOf(Double.valueOf(jsonObj.getString("NOT_GENERATED_WAGES_AMT"))+Double.valueOf(jsonObj.getString("NOT_GENERATED_MATERIAL_AMT"))
	 	    				+Double.valueOf(jsonObj.getString("NOT_UPLOADED_WAGES_AMT"))+Double.valueOf(jsonObj.getString("NOT_UPLOADED_MATERIAL_AMT"))
	 	    				+Double.valueOf(jsonObj.getString("NOT_SENTPFMS_WAGES_AMT"))+Double.valueOf(jsonObj.getString("NOT_SENTPFMS_MATERIAL_AMT"))
	 	    				+Double.valueOf(jsonObj.getString("REJECT_WAGES_AMT"))+Double.valueOf(jsonObj.getString("REJECT_MATERIAL_AMT"))
	 	    				+Double.valueOf(jsonObj.getString("RELEASE_PENDING_WAGES_AMT"))+Double.valueOf(jsonObj.getString("RELEASE_PENDING_MATERIAL_AMT")))));
	 	    				
	 	    				paymentDtlsVO.setPendingWage(cnvrtRupeesIntoCrores(String.valueOf(Double.valueOf(jsonObj.getString("NOT_GENERATED_WAGES_AMT"))
	 	    				+Double.valueOf(jsonObj.getString("NOT_UPLOADED_WAGES_AMT"))
	 	    				+Double.valueOf(jsonObj.getString("NOT_SENTPFMS_WAGES_AMT"))
	 	    				+Double.valueOf(jsonObj.getString("REJECT_WAGES_AMT"))
	 	    				+Double.valueOf(jsonObj.getString("RELEASE_PENDING_WAGES_AMT")))));
	 	    				
	 	    				paymentDtlsVO.setPendingMaterial(cnvrtRupeesIntoCrores(String.valueOf(Double.valueOf(jsonObj.getString("NOT_GENERATED_MATERIAL_AMT"))
	 	    				+Double.valueOf(jsonObj.getString("NOT_UPLOADED_MATERIAL_AMT"))
	 	    				+Double.valueOf(jsonObj.getString("NOT_SENTPFMS_MATERIAL_AMT"))
	 	    				+Double.valueOf(jsonObj.getString("REJECT_MATERIAL_AMT"))
	 	    				+Double.valueOf(jsonObj.getString("RELEASE_PENDING_MATERIAL_AMT")))));
	 	    				  
	 	    				resultList.add(paymentDtlsVO);
	 	    				  
	 	    			  }
	 	    		 }
	 	    	 }
	 	    	 
	 	      }
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaPaymentsAbsAndOverviewDtls - NREGSTCSService service", e);
		}
		return resultList;
	}
	 /**
	  * @param  InputVO inputVO which contain fromDate,toDate,locationType,Year,Type and locationId
	  * @return List<NregaPaymentsVO>
	  * @author Santosh 
	  * @Description :This Service Method is used to get payment details location wise.
	  * @since 31-JULY-2017
	  */
	public List<NregaPaymentsVO> getNregaPaymentsDtlsLocationWise(InputVO inputVO) {
		List<NregaPaymentsVO> resultList = new ArrayList<NregaPaymentsVO>(0);
		try {
			if (inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().toString().length() > 0l) {
				inputVO.setSublocationType(inputVO.getSublocaType().trim());
			}
				
			String  webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/APPaymentsService/APPaymentsData";
			
			String str = convertingInputVOToString(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
	        
	        if(response.getStatus() != 200) {
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	    }else {
	 	    	 String output = response.getEntity(String.class);
	 	    	 if (output != null && !output.isEmpty()) {
	 	    		 JSONArray paymentDataArr = new JSONArray(output);	 
	 	    		 if (paymentDataArr != null && paymentDataArr.length() > 0) {
	 	    			  for (int i = 0 ;i < paymentDataArr.length() ;i++) {
	 	    				 JSONObject jsonObj = (JSONObject) paymentDataArr.get(i);
	 	    				 String locationId = jsonObj.getString("UNIQUEID");
	 	    				 String type = jsonObj.getString("TYPE");
	 	    				 String subLocation = inputVO.getSublocaType().trim();
	 	    				 if (inputVO != null && inputVO.getType().equalsIgnoreCase(type)) {//in the case of M-Material ,W-Wages and subTotal
	 	    					     NregaPaymentsVO locationVO = new NregaPaymentsVO();
	 	    					     setBaseLocationByLocationType(jsonObj,locationVO,subLocation);
		 	    					 locationVO.setId(locationId);
		 	    					 locationVO.setType(type);
		 	    					 setPaymentDtlsData(jsonObj,locationVO,subLocation);//setting payment details data
		 	    					 resultList.add(locationVO);
		 	    			 } 
	 	    				 if (inputVO != null && inputVO.getType().equalsIgnoreCase("All")) {//overAll
			 	    				 NregaPaymentsVO locationVO = new NregaPaymentsVO();
	 	    					     setBaseLocationByLocationType(jsonObj,locationVO,subLocation);
		 	    					 locationVO.setType(type);
		 	    					 locationVO.setId(locationId);
		 	    					 setPaymentDtlsData(jsonObj,locationVO,subLocation);//setting payment details data
		 	    					 resultList.add(locationVO);
	 	    				 }
	 	    			  }
	 	    		 } 	
	 	    	 }
	 	      }
		} catch (Exception e){
			LOG.error("Exception raised at getNregaPaymentsDtlsLocationWise - NREGSTCSService service", e);
		}
		return resultList;
	}
	public void setPaymentDtlsData(JSONObject jsonObj,NregaPaymentsVO paymentDtlsVO, String subLocationType){
		try {
			 if (subLocationType != null && subLocationType.equalsIgnoreCase("mandal") || subLocationType.equalsIgnoreCase("panchayat")) {
			 	paymentDtlsVO.setTotalAmount(jsonObj.getString("TOTAL_AMOUNT"));
				paymentDtlsVO.setGeneratedWageAmount(jsonObj.getString("GENERATED_AMT"));
				paymentDtlsVO.setNotGeneratedWagesAmount(jsonObj.getString("NOT_GENERATED_AMT"));
				paymentDtlsVO.setUploadedWageAmount(jsonObj.getString("UPLOADED_AMT"));
				paymentDtlsVO.setNotUploadedWagesAmount(jsonObj.getString("NOT_UPLOADED_AMT"));
				paymentDtlsVO.setSentBankWageAmount(jsonObj.getString("SENTPFMS_AMT"));
				paymentDtlsVO.setNotSentBankWageAmount(jsonObj.getString("NOT_SENTPFMS_AMT"));
				paymentDtlsVO.setCompletedWageAmount(jsonObj.getString("COMPLETED_AMT"));
				paymentDtlsVO.setRejectedWagesAmount(jsonObj.getString("REJECT_AMT"));
				paymentDtlsVO.setReleasePendingWageAmount(jsonObj.getString("RELEASE_PENDING_AMT"));
				paymentDtlsVO.setResponsePendingWageAmount(jsonObj.getString("RESPONSE_PENDING_AMT"));
				paymentDtlsVO.setReprocessPendingWageAmount(jsonObj.getString("REPROCESS_PENDING_AMT"));
				
				paymentDtlsVO.setTotalPendinAmount(String.valueOf(Long.valueOf(jsonObj.getString("TOTAL_AMOUNT")) - Long.valueOf(jsonObj.getString("COMPLETED_AMT"))));
				paymentDtlsVO.setPendingAtBankAmount(String.valueOf(Long.valueOf(jsonObj.getString("RELEASE_PENDING_AMT")) + Long.valueOf(jsonObj.getString("RESPONSE_PENDING_AMT"))));
			 } else {
				paymentDtlsVO.setTotalAmount(cnvrtRupeesIntoCrores(jsonObj.getString("TOTAL_AMOUNT")));
				paymentDtlsVO.setGeneratedWageAmount(cnvrtRupeesIntoCrores(jsonObj.getString("GENERATED_AMT")));
				paymentDtlsVO.setNotGeneratedWagesAmount(cnvrtRupeesIntoCrores(jsonObj.getString("NOT_GENERATED_AMT")));
				paymentDtlsVO.setUploadedWageAmount(cnvrtRupeesIntoCrores(jsonObj.getString("UPLOADED_AMT")));
				paymentDtlsVO.setNotUploadedWagesAmount(cnvrtRupeesIntoCrores(jsonObj.getString("NOT_UPLOADED_AMT")));
				paymentDtlsVO.setSentBankWageAmount(cnvrtRupeesIntoCrores(jsonObj.getString("SENTPFMS_AMT")));
				paymentDtlsVO.setNotSentBankWageAmount(cnvrtRupeesIntoCrores(jsonObj.getString("NOT_SENTPFMS_AMT")));
				paymentDtlsVO.setCompletedWageAmount(cnvrtRupeesIntoCrores(jsonObj.getString("COMPLETED_AMT")));
				paymentDtlsVO.setRejectedWagesAmount(cnvrtRupeesIntoCrores(jsonObj.getString("REJECT_AMT")));
				paymentDtlsVO.setReleasePendingWageAmount(cnvrtRupeesIntoCrores(jsonObj.getString("RELEASE_PENDING_AMT")));
				paymentDtlsVO.setResponsePendingWageAmount(cnvrtRupeesIntoCrores(jsonObj.getString("RESPONSE_PENDING_AMT")));
				paymentDtlsVO.setReprocessPendingWageAmount(cnvrtRupeesIntoCrores(jsonObj.getString("REPROCESS_PENDING_AMT")));
				
				paymentDtlsVO.setTotalPendinAmount(cnvrtRupeesIntoCrores(String.valueOf(Long.valueOf(jsonObj.getString("TOTAL_AMOUNT")) - Long.valueOf(jsonObj.getString("COMPLETED_AMT")))));
				paymentDtlsVO.setPendingAtBankAmount(cnvrtRupeesIntoCrores(String.valueOf(Long.valueOf(jsonObj.getString("RELEASE_PENDING_AMT")) + Long.valueOf(jsonObj.getString("RESPONSE_PENDING_AMT")))));
				}
		} catch (Exception e) {
			LOG.error("Exception raised at getPaymentDtlsData - NREGSTCSService service", e);
		}
	}
	
  public void setBaseLocationByLocationType(JSONObject jObj,NregaPaymentsVO locatioVO,String subLocation){
		try {
				if (subLocation != null && subLocation.trim().equalsIgnoreCase("district") || subLocation.trim().equalsIgnoreCase("constituency") || subLocation != null && subLocation.trim().equalsIgnoreCase("mandal") || subLocation.trim().equalsIgnoreCase("panchayat")){
					locatioVO.setDistrictName(jObj.getString("DISTRICT"));
				}
				if (subLocation != null && subLocation.trim().equalsIgnoreCase("constituency") || subLocation != null && subLocation.trim().equalsIgnoreCase("mandal") || subLocation.trim().equalsIgnoreCase("panchayat")){
					locatioVO.setConstName(jObj.getString("CONSTITUENCY"));
				}
				if (subLocation != null && subLocation.trim().equalsIgnoreCase("mandal") || subLocation.trim().equalsIgnoreCase("panchayat")){
					locatioVO.setMandalName(jObj.getString("MANDAL"));
				}
				if (subLocation != null && subLocation.trim().equalsIgnoreCase("panchayat")){
					locatioVO.setPanchayatName(jObj.getString("PANCHAYAT"));
				}
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaPaymentsDtlsLocationWise - NREGSTCSService service", e);
		}
	}
	public String cnvrtRupeesIntoCrores(String value){
		String returnVal = "0";
		try {
			if(value != null) {
				returnVal = new BigDecimal(Double.valueOf(value)/10000000.00d).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				returnVal = returnVal + " CR";
			}
		} catch (Exception e) {
			LOG.error("Exception raised at cnvrtRupeesIntoCrores - NREGSTCSService service", e);
		}
		return returnVal.trim();
	}
}
