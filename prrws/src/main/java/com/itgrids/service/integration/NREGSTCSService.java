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
import com.itgrids.dao.IDistrictDAO;
import com.itgrids.dao.INregaComponentCommentsDAO;
import com.itgrids.dao.INregaComponentCommentsHistoryDAO;
import com.itgrids.dao.INregaComponentStatusDAO;
import com.itgrids.dao.INregaFATypeDAO;
import com.itgrids.dao.INregaFAVacantPanchayatDAO;
import com.itgrids.dao.IParliamentAssemblyDAO;
import com.itgrids.dao.IPrConstituencyDAO;
import com.itgrids.dao.IPrDistrictDAO;
import com.itgrids.dao.IPrPanchayatDAO;
import com.itgrids.dao.IPrTehsilDAO;
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
import com.itgrids.dto.WaterTanksClorinationVO;
import com.itgrids.dto.WebserviceDetailsVO;
import com.itgrids.model.NregaComponentComments;
import com.itgrids.model.NregaComponentCommentsHistory;
import com.itgrids.model.NregaComponentStatus;
import com.itgrids.model.NregaFAType;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.itgrids.service.integration.impl.INREGSTCSService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.DateUtilService;
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
	@Autowired
	private INregaComponentCommentsDAO nregaComponentCommentsDAO;
	@Autowired
	private DateUtilService dateUtilService;
	@Autowired
	private INregaComponentStatusDAO nregaComponentStatusDAO;
	
	private String sessionToken;
	@Autowired
	private IPrTehsilDAO prTehsilDAO;
	@Autowired
	private IPrPanchayatDAO prPanchayatDAO;
	@Autowired
	private INregaComponentCommentsHistoryDAO nregaComponentCommentsHistoryDAO;
	@Autowired
	private IDistrictDAO districtDAO;
	@Autowired
	private INregaFAVacantPanchayatDAO nregaFAVacantPanchayatDAO;
	@Autowired
	private INregaFATypeDAO nregaFATypeDAO;
	
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
    				returnvo.setDistrictsInGold(jObj.getLong("DISTRICTSINGOLD"));
    				returnvo.setTotalConstituencies(jObj.getLong("TOTALCONSTITUENCIES"));
    				returnvo.setConstituenciesInRed(jObj.getLong("CONSTITUENCIESINRED"));
    				returnvo.setConstituenciesInOrange(jObj.getLong("CONSTITUENCIESINORANGE"));
    				returnvo.setConstituenciesInGreen(jObj.getLong("CONSTITUENCIESINGREEN"));
    				returnvo.setConstituenciesInGold(jObj.getLong("CONSTITUENCIESINGOLD"));
    				returnvo.setTotalMandals(jObj.getLong("TOTALMANDALS"));
    				returnvo.setMandalsInRed(jObj.getLong("MANDALSINRED"));
    				returnvo.setMandalsInOrange(jObj.getLong("MANDALSINORANGE"));
    				returnvo.setMandalsInGreen(jObj.getLong("MANDALSINGREEN"));
    				returnvo.setMandalsInGold(jObj.getLong("MANDALSINGOLD"));
    				returnvo.setTotalVillages(jObj.getLong("TOTALVILLAGES"));
    				returnvo.setVillagesInRed(jObj.getLong("VILLAGESINRED"));
    				returnvo.setVillagesInOrange(jObj.getLong("VILLAGESINORANGE"));
    				returnvo.setVillagesInGreen(jObj.getLong("VILLAGESINGREEN"));
    				returnvo.setVillagesInGold(jObj.getLong("VILLAGESINGOLD"));
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
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/APLabourBugetServiceNew/APLabourBdgtExpenditureNew", str);
	        
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
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("GP Buildings1"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/GpBuildingServiceNew/GpBuildingOverviewNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/GPBuildingService/GPBuildingOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR Jala Siri"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NtrsService/NtrsOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("CC Roads1"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CCRoadsServicesNew/CCRoadsOverviewNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CCRoadsService/CCRoadsOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Anganwadi"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AnganawadiServiceNew/AnganawadiOverviewNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AnganwadiService/AnganwadiOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mandal buildings1"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MandalBuildingServiceNew/MandalBuildingOverviewNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MandalBuildingService/MandalBuildingOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR 90 Days"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HousingServiceNew/HousingOverviewNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HousingService/HousingOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Production of Bricks"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BricksServiceNew/BricksOverviewNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BricksService/BricksOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery New"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MulberyServiceNew/MulberyOverviewNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SericultureService/SericultureOverview
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Silk worm New"))
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
	 	    				if(Obj.has("DISTRICTSINGOLD")){
	 	    					finalVO.setDistrictsInGold(Obj.getLong("DISTRICTSINGOLD"));
	 	    					finalVO.setConstituenciesInGold(Obj.getLong("CONSTITUENCIESINGOLD"));
	 	    					finalVO.setMandalsInGold(Obj.getLong("MANDALSINGOLD"));
	 	    					finalVO.setVillagesInGold(Obj.getLong("VILLAGESINGOLD"));
	 	    				}
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
			//if(inputVO.getDivType() != null && !inputVO.getDivType().trim().equalsIgnoreCase("MonthWise Expenditure")){
				if(inputVO.getLocationId() != null){
					if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("district")){
						if(inputVO.getLocationId().longValue() > 0l && inputVO.getLocationId().longValue() <= 9l)
							inputVO.setLocationIdStr("0"+inputVO.getLocationId().toString());
					}else if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("constituency")){
						if(inputVO.getDistrictId().longValue() > 0l && inputVO.getDistrictId().longValue() <= 9l){
							if(inputVO.getLocationId().longValue() > 0l)
								inputVO.setLocationIdStr("0"+inputVO.getLocationId().toString());
						}
					}
				}
			//}
			
				
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
			if(inputVO.getProgram() != null)
				str += "\"program\" : \""+inputVO.getProgram()+"\",";
			if(inputVO.getCategory() != null)
				str += "\"categoryName\" : \""+inputVO.getCategory()+"\",";
			if(inputVO.getGroupName() != null)
				str += "\"groupName\" : \""+inputVO.getGroupName()+"\",";
			if(inputVO.getMonth() != null)
				str += "\"month\" : \""+inputVO.getMonth()+"\",";
			if(inputVO.getpType() != null)
				str += "\"pType\" : \""+inputVO.getpType()+"\",";
			
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
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("GP Buildings1"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/GpBuildingServiceNew/GpBuildingDataNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/GPBuildingService/GPBuildingData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR Jala Siri"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NtrsService/NtrsData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Anganwadi"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AnganawadiServiceNew/AnganawadiDataNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AnganwadiService/AnganwadiData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mandal buildings1"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MandalBuildingServiceNew/MandalBuildingDataNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MandalBuildingService/MandalBuildingData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR 90 Days"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HousingServiceNew/HousingDataNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HousingService/HousingData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Production of Bricks"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BricksServiceNew/BricksDataNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BricksService/BricksData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery New"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MulberyServiceNew/MulberyDataNew";//http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SericultureService/SericultureData
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Silk worm New"))
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
			/*else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("PR ITDA Others"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/DCCPRService/DCCPRData";*/
			
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
	 	    					if(inputVO.getDivType() != null && (inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery New") || inputVO.getDivType().trim().toString().equalsIgnoreCase("Silk worm New")) && 
	 	    							(inputVO.getSublocationType().trim().equalsIgnoreCase("state") || inputVO.getSublocationType().trim().equalsIgnoreCase("district") || inputVO.getSublocationType().trim().equalsIgnoreCase("constituency")))
	 	    						vo.setMulbTarget(jObj.getString("TARGET"));
	 	    					else if(inputVO.getDivType() != null && (inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery New") || inputVO.getDivType().trim().toString().equalsIgnoreCase("Silk worm New")) && 
	 	    							(inputVO.getSublocationType().trim().equalsIgnoreCase("mandal") || inputVO.getSublocationType().trim().equalsIgnoreCase("panchayat")))
	 	    						vo.setMulbTarget(jObj.getString("TARGETNEW"));
	 	    					else if(inputVO.getDivType() != null && 
	 	    							(inputVO.getDivType().trim().toString().equalsIgnoreCase("OPGK-Perinnials") || inputVO.getDivType().trim().toString().equalsIgnoreCase("OPGK-Annuals"))
	 	    									&& (inputVO.getSublocationType().trim().equalsIgnoreCase("state") || inputVO.getSublocationType().trim().equalsIgnoreCase("district") || inputVO.getSublocationType().trim().equalsIgnoreCase("constituency")))
	 	    						vo.setOpgkTarget(jObj.getString("TARGET"));
	 	    					else if(inputVO.getDivType() != null && 
	 	    							(inputVO.getDivType().trim().toString().equalsIgnoreCase("OPGK-Perinnials") || inputVO.getDivType().trim().toString().equalsIgnoreCase("OPGK-Annuals"))
	 	    									&& (inputVO.getSublocationType().trim().equalsIgnoreCase("mandal") || inputVO.getSublocationType().trim().equalsIgnoreCase("panchayat")))
	 	    						vo.setOpgkTarget(jObj.getString("TARGETNEW"));
	 	    					else if(inputVO.getDivType() != null && (inputVO.getDivType().trim().toString().equalsIgnoreCase("Mandal buildings1") && inputVO.getSublocationType().trim().equalsIgnoreCase("mandal")))
	 	    						vo.setTarget(jObj.getLong("TARGETNEW"));
	 	    					else if(inputVO.getDivType() != null && (inputVO.getDivType().trim().toString().equalsIgnoreCase("GP buildings1") && (inputVO.getSublocationType().trim().equalsIgnoreCase("mandal") 
	 	    							|| inputVO.getSublocationType().trim().equalsIgnoreCase("panchayat"))))
	 	    						vo.setTarget(jObj.getLong("TARGETNEW"));
	 	    					else
	 	    						vo.setTarget(jObj.getLong("TARGET"));
		 	    				vo.setGrounded(jObj.getString("GROUNDED"));
		 	    				if(jObj.getString("NOTGROUNDED").trim().contains("-"))
		 	    					vo.setNotGrounded("0");
		 	    				else
		 	    					vo.setNotGrounded(jObj.getString("NOTGROUNDED"));
		 	    				if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery New"))
		 	    					vo.setMulbInprogress(jObj.getString("INPROGRESS"));
		 	    				else if(inputVO.getDivType() != null && (inputVO.getDivType().trim().toString().equalsIgnoreCase("OPGK-Perinnials") ||  inputVO.getDivType().trim().toString().equalsIgnoreCase("OPGK-Annuals")))
	 	    						vo.setOpgkInProgress(jObj.getString("INPROGRESS"));
		 	    				else
	 	    						vo.setInProgress(jObj.getLong("INPROGRESS"));
		 	    				if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery New"))
		 	    					vo.setMulbCompleted(jObj.getString("COMPLETED"));
	 	    					else
	 	    						vo.setCompleted(jObj.getLong("COMPLETED"));
		 	    				
		 	    				vo.setPercentage(jObj.getString("PERCENTAGE"));
		 	    				//(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery")
		 	    				if((inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Cattle drinking water troughs")
		 	    						|| inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Raising of Perinnial Fodders"))
		 	    							&& inputVO.getSublocationType().trim().toString().equalsIgnoreCase("state")){
		 	    					vo.setSanctionedTarget(jObj.getString("SANCTIONEDTARGET"));
		 	    					vo.setSanctionedPerventage(jObj.getString("SANCTIONEDPERCENTAGE"));
		 	    					
		 	    					if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery New"))
		 	    						vo.setPercSant(new BigDecimal(Double.valueOf(vo.getMulbCompleted())*100.00/Double.valueOf(vo.getSanctionedTarget())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    					else
		 	    						vo.setPercSant(new BigDecimal(vo.getCompleted()*100.00/Double.valueOf(vo.getSanctionedTarget())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    					
		 	    					if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery New")){
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
		 	    						|| inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Anganwadi") 
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
		 	    				
		 	    				if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Silk worm New")){
		 	    					if(vo.getCompleted() != null && vo.getCompleted().longValue() > 0l && vo.getMulbTarget() != null && Double.valueOf(vo.getMulbTarget()) > 0)
		 	    						vo.setPercentage(new BigDecimal(vo.getCompleted()*100.00/Double.valueOf(vo.getMulbTarget())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    					else
		 	    						vo.setPercentage("0.00");
		 	    				}
		 	    				
		 	    				if(inputVO.getDivType() != null && (inputVO.getDivType().trim().toString().equalsIgnoreCase("Farm Ponds") 
		 	    						|| inputVO.getDivType().trim().toString().equalsIgnoreCase("IHHL")
		 	    						|| inputVO.getDivType().trim().toString().equalsIgnoreCase("Vermi Compost")
		 	    						|| inputVO.getDivType().trim().toString().equalsIgnoreCase("Burial Ground")
		 	    						|| inputVO.getDivType().trim().toString().equalsIgnoreCase("Solid Waste Management")
		 	    						|| inputVO.getDivType().trim().toString().equalsIgnoreCase("Play Fields")
		 	    						|| inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR 90 Days")
		 	    						|| inputVO.getDivType().trim().toString().equalsIgnoreCase("Production of Bricks")
		 	    						|| inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery New")
		 	    						|| inputVO.getDivType().trim().toString().equalsIgnoreCase("Silk worm New"))){
		 	    					vo.setWageExpenditure(jObj.getString("WAGE_EXP"));
		 	    					vo.setMaterialExpenditure(jObj.getString("MAT_EXP"));
		 	    					vo.setTotalExpenditure(jObj.getString("TOT_EXP"));
		 	    				}
		 	    			}
	 	    				
	 	    				if(inputVO.getDivType() != null && (inputVO.getDivType().trim().equalsIgnoreCase("Anganwadi") 
	 	    						|| inputVO.getDivType().trim().equalsIgnoreCase("GP Buildings1") || inputVO.getDivType().trim().equalsIgnoreCase("Mandal buildings1"))){
	 	    					if(inputVO.getDivType().trim().equalsIgnoreCase("Anganwadi"))
	 	    						vo.setIcdsExpenditure(jObj.getString("ICDS_EXP"));
	 	    					vo.setEgsExpenditure(jObj.getString("EGS_EXP"));
	 	    					vo.setConvergenceExpn(jObj.getString("CONVERGENCE_EXP"));
	 	    					vo.setTotalExpenditure(jObj.getString("TOT_EXP"));
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
						}/*else if((divType != null && divType.trim().toString().equalsIgnoreCase("Fish Drying Platforms") || divType != null && divType.trim().toString().equalsIgnoreCase("Fish Ponds") ||
								divType != null && divType.trim().toString().equalsIgnoreCase("SMC Trench") || divType != null && divType.trim().toString().equalsIgnoreCase("Imp to CD") ||
								divType != null && divType.trim().toString().equalsIgnoreCase("MPT_PT") || divType != null && divType.trim().toString().equalsIgnoreCase("GC Works") ||
								divType != null && divType.trim().toString().equalsIgnoreCase("CD_CW") ||divType != null && divType.trim().toString().equalsIgnoreCase("Anganwadi")) && 
								(subLocationType != null && subLocationType.trim().equalsIgnoreCase("district"))){
							percValue = new BigDecimal(jObj.getLong("COMPLETED")*100.00/Long.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						}*/else if(divType != null && divType.trim().toString().equalsIgnoreCase("GH")) {
							percValue = new BigDecimal(Double.valueOf(jObj.getString("PLNTNG_EXT")) * 100.00
									/ Double.valueOf(jObj.getString("SANCTION_TARGET")))
											.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("coffee")){
							percValue = new BigDecimal(jObj.getString("PLANT_PERC")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						}else if(divType != null && (divType.trim().toString().equalsIgnoreCase("Rock fill dams") || divType.trim().toString().equalsIgnoreCase("Raising and Maintenance of Nursery") ||
								divType.trim().toString().equalsIgnoreCase("Desilting of Perculation Tanks and Check Dams") || divType.trim().toString().equalsIgnoreCase("Mini Percolation Tanks") ||
								divType.trim().toString().equalsIgnoreCase("Continuous Contour Trenches") || divType.trim().toString().equalsIgnoreCase("Check Dams") || divType.trim().toString().equalsIgnoreCase("Avenue Plantation") 
								|| divType.trim().toString().equalsIgnoreCase("Forest Others") || divType.trim().toString().equalsIgnoreCase("Scooping and Dibbling of seed"))){
							percValue = new BigDecimal(jObj.getString("PERC")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						
						}else{
							percValue = new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						}
						
		    				if(vo != null){
			    				if(Double.valueOf(percValue)  < 60){
		    						vo.setConstiInRed(vo.getConstiInRed()+1l);
		    					}else if(Double.valueOf(percValue)  >=60 && Double.valueOf(percValue) <90){
		    						vo.setConstiInOrange(vo.getConstiInOrange()+1l);
	    						}else if(Double.valueOf(percValue)  >=90 && Double.valueOf(percValue) <100){
		    						vo.setConstiInGreen(vo.getConstiInGreen()+1l);
		    					}else if(Double.valueOf(percValue)  >=100){
		    						vo.setConstiInGold(vo.getConstiInGold()+1);
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
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("GP Buildings1"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/GpBuildingServiceNew/GpBuildingDataNew";
			/*else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR Jala Siri"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NtrsService/NtrsData";*/
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("CC Roads1"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CCRoadsServicesNew/CCRoadsDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Anganwadi"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AnganawadiServiceNew/AnganawadiDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mandal Buildings1"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MandalBuildingServiceNew/MandalBuildingDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR 90 Days"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/HousingServiceNew/HousingDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Production of Bricks"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BricksServiceNew/BricksDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery New"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MulberyServiceNew/MulberyDataNew";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Silk worm New"))
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
			else if(inputVO.getDivType() != null && (inputVO.getDivType().trim().toString().equalsIgnoreCase("Rock fill dams") || inputVO.getDivType().trim().toString().equalsIgnoreCase("Raising and Maintenance of Nursery") ||
					inputVO.getDivType().trim().toString().equalsIgnoreCase("Desilting of Perculation Tanks and Check Dams") || inputVO.getDivType().trim().toString().equalsIgnoreCase("Mini Percolation Tanks") ||
					inputVO.getDivType().trim().toString().equalsIgnoreCase("Continuous Contour Trenches") || inputVO.getDivType().trim().toString().equalsIgnoreCase("Check Dams") || 
					inputVO.getDivType().trim().toString().equalsIgnoreCase("Avenue Plantation") || inputVO.getDivType().trim().toString().equalsIgnoreCase("Forest Others") || inputVO.getDivType().trim().toString().equalsIgnoreCase("Scooping and Dibbling of seed"))){
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/ForestService/ForestData";
				inputVO.setCategory(inputVO.getDivType());
			}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("coffee"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CoffeeService/CoffeeData";
			
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
			 	    				//if(inputVO.getSublocationType().trim().toString().equalsIgnoreCase("state") || inputVO.getSublocationType().trim().toString().equalsIgnoreCase("district")){
			 	    					vo.setTargetACRES(jObj.getString("TARGETACRES"));
				 	    				vo.setSanctionedPerventage(jObj.getString("SANCTIONEDPERCENTAGE"));
				 	    				if(jObj.getString("PITTINGAREA") != null && Double.valueOf(jObj.getString("PITTINGAREA")) > 0l && jObj.getString("TARGETACRES") != null && Double.valueOf(jObj.getString("TARGETACRES")) > 0l)
				 	    					vo.setTargetPittingPerc(new BigDecimal((Double.valueOf(jObj.getString("PITTINGAREA"))*100.00)/Double.valueOf(jObj.getString("TARGETACRES"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				 	    				else
				 	    					vo.setTargetPittingPerc("0.00");
				 	    				//}
			 	    				vo.setSanctionedACRES(jObj.getString("SANCTIONEDACRES"));
			 	    				vo.setPittingArea(jObj.getString("PITTINGAREA"));
			 	    				vo.setPlantingArea(jObj.getString("PLANTINGAREA"));
			 	    				vo.setPencentageOfPlanting(jObj.getString("PERCENTAGEOFPLANTING"));
			 	    				/*if(jObj.getString("PITTINGAREA") != null && Double.valueOf(jObj.getString("PITTINGAREA")) >0 && jObj.getString("SANCTIONEDACRES") != null && Double.valueOf(jObj.getString("SANCTIONEDACRES")) > 0)
			 	    					vo.setSancTrgtPittingPerc(new BigDecimal((Double.valueOf(jObj.getString("PITTINGAREA"))*100.00)/Double.valueOf(jObj.getString("SANCTIONEDACRES"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			 	    				else
			 	    					vo.setSancTrgtPittingPerc("0.00");*/
			 	    				
			 	    				if(jObj.getString("PLANTINGAREA") != null && Double.valueOf(jObj.getString("PLANTINGAREA")) > 0l && jObj.getString("TARGETACRES") != null && Double.valueOf(jObj.getString("TARGETACRES")) > 0l)
			 	    					vo.setTargetPalnting(new BigDecimal((Double.valueOf(jObj.getString("PLANTINGAREA"))*100.00)/Double.valueOf(jObj.getString("TARGETACRES"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			 	    				else
			 	    					vo.setTargetPalnting("0.00");
			 	    				vo.setPercentage(vo.getTargetPalnting());
			 	    				
			 	    				list.add(vo);
			 	    			}
		 	    			}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Drying Platforms") ||
		 	    					 inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Fish Ponds") || 
		 	    					 inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("SMC Trench") ||
		 	    					 inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Imp to CD") ||
		 	    					 inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("MPT_PT") ||
		 	    					 inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("GC Works") ||
		 	    					 inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("CD_CW") ||
		 	    					 inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Anganwadi")){
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
				 	    				if(inputVO.getDivType() != null && inputVO.getDivType().trim().equalsIgnoreCase("Anganwadi")){
				 	    					nregsDataVO.setIcdsExpenditure(jObj.getString("ICDS_EXP"));
				 	    					nregsDataVO.setEgsExpenditure(jObj.getString("EGS_EXP"));
				 	    					nregsDataVO.setConvergenceExpn(jObj.getString("CONVERGENCE_EXP"));
				 	    					nregsDataVO.setTotalExpenditure(jObj.getString("TOT_EXP"));
				 	    				}
				 	    				if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().equalsIgnoreCase("district")){
				 	    					nregsDataVO.setSanctionedTarget(jObj.getString("SANCTIONEDTARGET"));
				 	    					nregsDataVO.setSanctionedPerventage(jObj.getString("SANCTIONEDPERCENTAGE"));
				 	    					if(nregsDataVO.getCompleted() != null && nregsDataVO.getCompleted().longValue() > 0l && nregsDataVO.getSanctionedTarget() != null && Double.valueOf(nregsDataVO.getSanctionedTarget()) > 0){
				 	    						nregsDataVO.setPercSant(new BigDecimal(nregsDataVO.getCompleted()*100.00/Double.valueOf(nregsDataVO.getSanctionedTarget())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				 	    						nregsDataVO.setPercentage(new BigDecimal(nregsDataVO.getCompleted()*100.00/Double.valueOf(nregsDataVO.getTarget())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
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
			 	    		}else if(inputVO.getDivType() != null && (inputVO.getDivType().trim().toString().equalsIgnoreCase("CC Roads1") || inputVO.getDivType().trim().toString().equalsIgnoreCase("UGDrainage"))){
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
			 	    				if(vo.getCompletedKMS() != null && Double.valueOf(vo.getCompletedKMS()) > 0 && vo.getSanctionedKMS() != null && Double.valueOf(vo.getSanctionedKMS()) > 0)
		 	    						vo.setPercSant(new BigDecimal(Double.valueOf(vo.getCompletedKMS())*100.00/Double.valueOf(vo.getSanctionedKMS())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    					else
		 	    						vo.setPercSant("0.00");
			 	    				
			 	    				list.add(vo);
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
			 	    				//if(inputVO.getSublocationType().trim().toString().equalsIgnoreCase("state") || inputVO.getSublocationType().trim().toString().equalsIgnoreCase("district")){
			 	    					vo.setTargetKMS(jObj.getString("TARGETACRES"));//TARGETKMS
				 	    				vo.setSanctionedPerventage(jObj.getString("SANCTIONEDPERCENTAGE"));//SANCTIONEDPERCENTAGE
				 	    				if(jObj.getString("PITTINGAREA") != null && Double.valueOf(jObj.getString("PITTINGAREA")) > 0l && vo.getTargetKMS() != null && Double.valueOf(vo.getTargetKMS()) > 0l)
				 	    					vo.setTargetPittingPerc(new BigDecimal(Double.valueOf(jObj.getString("PITTINGAREA"))*100.00/Double.valueOf(vo.getTargetKMS())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				 	    				else
				 	    					vo.setTargetPittingPerc("0.00");
				 	    				//}
			 	    				vo.setSanctionedKMS(jObj.getString("SANCTIONEDACRES"));//SANCTIONEDKMS
			 	    				vo.setPittingKMS(jObj.getString("PITTINGAREA"));//PITTINGKMS
			 	    				vo.setPlantingKMS(jObj.getString("PLANTINGAREA"));//PLANTINGKMS
			 	    				//if(inputVO.getSublocationType().trim().toString().equalsIgnoreCase("state") || inputVO.getSublocationType().trim().toString().equalsIgnoreCase("district")){
			 	    					if(vo.getPlantingKMS() != null && Double.valueOf(vo.getPlantingKMS()) > 0l && vo.getTargetKMS() != null && Double.valueOf(vo.getTargetKMS()) > 0l)
			 	    						vo.setSanctionedPerc(new BigDecimal(Double.valueOf(vo.getPlantingKMS())*100.00/Double.valueOf(vo.getTargetKMS())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			 	    					else
			 	    						vo.setSanctionedPerc("0.00");
				 	    			//}
			 	    				vo.setPercentage(vo.getSanctionedPerc());//PERCENTAGEOFPLANTING
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
							}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("coffee")){
								for(int i=0;i<finalArray.length();i++){
			 	    				NregsDataVO vo = new NregsDataVO();
			 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
			 	    				vo.setUniqueId(jObj.getLong("UNIQUEID"));
			 	    				vo.setDistrict(jObj.getString("DISTRICT"));
			 	    				vo.setConstituency(jObj.getString("CONSTITUENCY"));
			 	    				vo.setMandal(jObj.getString("MANDAL"));
			 	    				vo.setPanchayat(jObj.getString("PANCHAYAT"));
			 	    				vo.setTargetACRES(jObj.getString("TARGET_AREA"));
			 	    				vo.setPittingArea(jObj.getString("PIT_AREA"));
			 	    				vo.setPlantingArea(jObj.getString("PLANT_AREA"));
			 	    				vo.setPencentageOfPlanting(jObj.getString("PLANT_PERC"));
			 	    				vo.setTotalExpenditure(jObj.getString("TOTAL_EXP"));
			 	    				vo.setPittingExp(jObj.getString("PIT_PERC"));
			 	    				vo.setPlantingExp(jObj.getString("PLANT_EXP"));
			 	    				vo.setPitingPerc(jObj.getString("PIT_PERC"));
			 	    				
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
			 	    				
			 	    				if(inputVO.getDivType() != null && (inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery New") || inputVO.getDivType().trim().toString().equalsIgnoreCase("Silk worm New")) && 
		 	    							(inputVO.getSublocationType().trim().equalsIgnoreCase("state") || inputVO.getSublocationType().trim().equalsIgnoreCase("district") || inputVO.getSublocationType().trim().equalsIgnoreCase("constituency")))
			 	    					nregsDataVO.setMulbTarget(jObj.getString("TARGET"));
		 	    					else if(inputVO.getDivType() != null && (inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery New") || inputVO.getDivType().trim().toString().equalsIgnoreCase("Silk worm New")) && 
		 	    							(inputVO.getSublocationType().trim().equalsIgnoreCase("mandal") || inputVO.getSublocationType().trim().equalsIgnoreCase("panchayat")))
		 	    						nregsDataVO.setMulbTarget(jObj.getString("TARGETNEW"));
			 	    				else if(inputVO.getDivType() != null && (inputVO.getDivType().trim().toString().equalsIgnoreCase("OPGK-Perinnials") || inputVO.getDivType().trim().toString().equalsIgnoreCase("OPGK-Annuals")) 
		 	    							&& (inputVO.getSublocationType().trim().equalsIgnoreCase("state") || inputVO.getSublocationType().trim().equalsIgnoreCase("district") || inputVO.getSublocationType().trim().equalsIgnoreCase("constituency"))){
		 	    						nregsDataVO.setOpgkTarget(jObj.getString("TARGET"));
		 	    					}else if(inputVO.getDivType() != null && 
		 	    							(inputVO.getDivType().trim().toString().equalsIgnoreCase("OPGK-Perinnials") || inputVO.getDivType().trim().toString().equalsIgnoreCase("OPGK-Annuals"))
		 	    									&& (inputVO.getSublocationType().trim().equalsIgnoreCase("mandal") || inputVO.getSublocationType().trim().equalsIgnoreCase("panchayat"))){
		 	    						nregsDataVO.setOpgkTarget(jObj.getString("TARGETNEW"));
		 	    					}
		 	    						
		 	    					else if(inputVO.getDivType() != null && (inputVO.getDivType().trim().toString().equalsIgnoreCase("Mandal buildings1") && inputVO.getSublocationType().trim().equalsIgnoreCase("mandal")))
		 	    						nregsDataVO.setTarget(jObj.getLong("TARGETNEW"));
		 	    					else if(inputVO.getDivType() != null && (inputVO.getDivType().trim().toString().equalsIgnoreCase("GP buildings1") && (inputVO.getSublocationType().trim().equalsIgnoreCase("mandal") 
		 	    							|| inputVO.getSublocationType().trim().equalsIgnoreCase("panchayat"))))
		 	    						nregsDataVO.setTarget(jObj.getLong("TARGETNEW"));
		 	    					else
		 	    						nregsDataVO.setTarget(jObj.getLong("TARGET"));
			 	    				
			 	    				nregsDataVO.setGrounded(jObj.getString("GROUNDED"));
			 	    				if(jObj.getString("NOTGROUNDED").trim().contains("-"))
			 	    					nregsDataVO.setNotGrounded("0");
			 	    				else
			 	    					nregsDataVO.setNotGrounded(jObj.getString("NOTGROUNDED"));
			 	    				
			 	    				if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery New"))
			 	    					nregsDataVO.setMulbInprogress(jObj.getString("INPROGRESS"));
			 	    				else if(inputVO.getDivType() != null && (inputVO.getDivType().trim().toString().equalsIgnoreCase("OPGK-Perinnials") ||  inputVO.getDivType().trim().toString().equalsIgnoreCase("OPGK-Annuals")))
			 	    					nregsDataVO.setOpgkInProgress(jObj.getString("INPROGRESS"));
			 	    				else
			 	    					nregsDataVO.setInProgress(jObj.getLong("INPROGRESS"));
			 	    				if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery New"))
			 	    					nregsDataVO.setMulbCompleted(jObj.getString("COMPLETED"));
		 	    					else
		 	    						nregsDataVO.setCompleted(jObj.getLong("COMPLETED"));
			 	    				
			 	    			   if(inputVO.getDivType() != null && (inputVO.getDivType().trim().toString().equalsIgnoreCase("Rock fill dams") || inputVO.getDivType().trim().toString().equalsIgnoreCase("Raising and Maintenance of Nursery") ||
			 	    				  inputVO.getDivType().trim().toString().equalsIgnoreCase("Desilting of Perculation Tanks and Check Dams") || inputVO.getDivType().trim().toString().equalsIgnoreCase("Mini Percolation Tanks") ||
			 	    				  inputVO.getDivType().trim().toString().equalsIgnoreCase("Continuous Contour Trenches") || inputVO.getDivType().trim().toString().equalsIgnoreCase("Check Dams") ||
			 	    				 inputVO.getDivType().trim().toString().equalsIgnoreCase("Avenue Plantation") || inputVO.getDivType().trim().toString().equalsIgnoreCase("Forest Others") || inputVO.getDivType().trim().toString().equalsIgnoreCase("Scooping and Dibbling of seed"))){
			 	    				   	nregsDataVO.setPercentage(new BigDecimal(jObj.getString("PERC")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			 	    			   }else{
			 	    				  nregsDataVO.setPercentage(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			 	    			   }
			 	    				
			 	    				
			 	    				if(inputVO.getDivType() != null && (inputVO.getDivType().trim().toString().equalsIgnoreCase("Farm Ponds") 
			 	    						|| inputVO.getDivType().trim().toString().equalsIgnoreCase("IHHL")
			 	    						|| inputVO.getDivType().trim().toString().equalsIgnoreCase("Vermi Compost")
			 	    						|| inputVO.getDivType().trim().toString().equalsIgnoreCase("Burial Ground")
			 	    						|| inputVO.getDivType().trim().toString().equalsIgnoreCase("Solid Waste Management")
			 	    						|| inputVO.getDivType().trim().toString().equalsIgnoreCase("Play Fields")
			 	    						|| inputVO.getDivType().trim().toString().equalsIgnoreCase("NTR 90 Days")
			 	    						|| inputVO.getDivType().trim().toString().equalsIgnoreCase("Production of Bricks")
			 	    						|| inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery New")
			 	    						|| inputVO.getDivType().trim().toString().equalsIgnoreCase("Silk worm New"))){
			 	    					nregsDataVO.setWageExpenditure(jObj.getString("WAGE_EXP"));
			 	    					nregsDataVO.setMaterialExpenditure(jObj.getString("MAT_EXP"));
			 	    					nregsDataVO.setTotalExpenditure(jObj.getString("TOT_EXP"));
			 	    				}
			 	    				
			 	    				
			 	    				if(inputVO.getDivType().trim().equalsIgnoreCase("GP Buildings1") || inputVO.getDivType().trim().equalsIgnoreCase("Mandal buildings1")){
			 	    					nregsDataVO.setEgsExpenditure(jObj.getString("EGS_EXP"));
			 	    					nregsDataVO.setConvergenceExpn(jObj.getString("CONVERGENCE_EXP"));
			 	    					nregsDataVO.setTotalExpenditure(jObj.getString("TOT_EXP"));
			 	    				}
			 	    				
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
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("coffee")){
							percValue = new BigDecimal(jObj.getString("PLANT_PERC")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						}else if(divType != null && (divType.trim().toString().equalsIgnoreCase("Rock fill dams") || divType.trim().toString().equalsIgnoreCase("Raising and Maintenance of Nursery") ||
								divType.trim().toString().equalsIgnoreCase("Desilting of Perculation Tanks and Check Dams") || divType.trim().toString().equalsIgnoreCase("Mini Percolation Tanks") ||
								divType.trim().toString().equalsIgnoreCase("Continuous Contour Trenches") || divType.trim().toString().equalsIgnoreCase("Check Dams") || divType.trim().toString().equalsIgnoreCase("Avenue Plantation") 
								|| divType.trim().toString().equalsIgnoreCase("Forest Others") || divType.trim().toString().equalsIgnoreCase("Scooping and Dibbling of seed"))){
							percValue = new BigDecimal(jObj.getString("PERC")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						
						}else{
							percValue = new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						} 
		    				if(vo != null) {
			    				if(Double.valueOf(percValue)  < 60){
		    						vo.setMandalsInRed(vo.getMandalsInRed()+1l);
		    					}else if(Double.valueOf(percValue)  >=60 && Double.valueOf(percValue) <90){
		    						vo.setMandalsInOrange(vo.getMandalsInOrange()+1l);
	    						}else if(Double.valueOf(percValue)  >=90 && Double.valueOf(percValue) <100){
		    						vo.setMandalsInGreen(vo.getMandalsInGreen()+1l);
		    					}else if(Double.valueOf(percValue)  >=100){
		    						vo.setMandalsInGold(vo.getMandalsInGold()+1l);
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
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("coffee")){
							percValue = new BigDecimal(jObj.getString("PLANT_PERC")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						}else if(divType != null && (divType.trim().toString().equalsIgnoreCase("Rock fill dams") || divType.trim().toString().equalsIgnoreCase("Raising and Maintenance of Nursery") ||
								divType.trim().toString().equalsIgnoreCase("Desilting of Perculation Tanks and Check Dams") || divType.trim().toString().equalsIgnoreCase("Mini Percolation Tanks") ||
								divType.trim().toString().equalsIgnoreCase("Continuous Contour Trenches") || divType.trim().toString().equalsIgnoreCase("Check Dams") || divType.trim().toString().equalsIgnoreCase("Avenue Plantation") 
								|| divType.trim().toString().equalsIgnoreCase("Forest Others") || divType.trim().toString().equalsIgnoreCase("Scooping and Dibbling of seed"))){
							percValue = new BigDecimal(jObj.getString("PERC")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						
						}else{
							percValue = new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						} 
		    				if(vo != null)
		    				{
			    				if(Double.valueOf(percValue)  < 60){
		    						vo.setMandalsInRed(vo.getMandalsInRed()+1l);
		    					}else if(Double.valueOf(percValue)  >=60 && Double.valueOf(percValue) <90){
		    						vo.setMandalsInOrange(vo.getMandalsInOrange()+1l);
	    						}else if(Double.valueOf(percValue)  >=90 && Double.valueOf(percValue) <100){
		    						vo.setMandalsInGreen(vo.getMandalsInGreen()+1l);
		    					}else if(Double.valueOf(percValue)  >=100){
		    						vo.setMandalsInGold(vo.getMandalsInGold()+1l);
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
				if(type.trim().equalsIgnoreCase("red") && Double.valueOf(nregsDataVO.getPercentage()) < 60)
					filterList.add(nregsDataVO);
				else if(type.trim().equalsIgnoreCase("orange") && Double.valueOf(nregsDataVO.getPercentage()) <90 && Double.valueOf(nregsDataVO.getPercentage()) >=60)
					filterList.add(nregsDataVO);
				else if(type.trim().equalsIgnoreCase("green") && Double.valueOf(nregsDataVO.getPercentage()) <100 && Double.valueOf(nregsDataVO.getPercentage()) >=90)
					filterList.add(nregsDataVO);
				else if(type.trim().equalsIgnoreCase("gold") && Double.valueOf(nregsDataVO.getPercentage()) >=100)
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
						detailsVO.setTotalTimeInMin(commonMethodsUtilService.roundUptoThreeDecimalPoint(detailsVO.getTotalTime()/60.0D));
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
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("coffee")){
							percValue = new BigDecimal(jObj.getString("PLANT_PERC")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						}else if(divType != null && (divType.trim().toString().equalsIgnoreCase("Rock fill dams") || divType.trim().toString().equalsIgnoreCase("Raising and Maintenance of Nursery") ||
								divType.trim().toString().equalsIgnoreCase("Desilting of Perculation Tanks and Check Dams") || divType.trim().toString().equalsIgnoreCase("Mini Percolation Tanks") ||
								divType.trim().toString().equalsIgnoreCase("Continuous Contour Trenches") || divType.trim().toString().equalsIgnoreCase("Check Dams") || divType.trim().toString().equalsIgnoreCase("Avenue Plantation") 
								|| divType.trim().toString().equalsIgnoreCase("Forest Others") || divType.trim().toString().equalsIgnoreCase("Scooping and Dibbling of seed"))){
							percValue = new BigDecimal(jObj.getString("PERC")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						
						}else{
							percValue = new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						} 
		    				if(vo != null) {
			    				if(Double.valueOf(percValue)  < 60){
		    						vo.setVillagesInRed(vo.getVillagesInRed()+1l);
		    					}else if(Double.valueOf(percValue)  >=60 && Double.valueOf(percValue) <90){
		    						vo.setVillagesInOrange(vo.getVillagesInOrange()+1l);
	    						}else if(Double.valueOf(percValue)  >=90 && Double.valueOf(percValue) <100){
		    						vo.setVillagesInGreen(vo.getVillagesInGreen()+1l);
		    					}else if(Double.valueOf(percValue)  >=100){
		    						vo.setVillagesInGold(vo.getVillagesInGold()+1l);
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
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("coffee")){
							percValue = new BigDecimal(jObj.getString("PLANT_PERC")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						}else if(divType != null && (divType.trim().toString().equalsIgnoreCase("Rock fill dams") || divType.trim().toString().equalsIgnoreCase("Raising and Maintenance of Nursery") ||
								divType.trim().toString().equalsIgnoreCase("Desilting of Perculation Tanks and Check Dams") || divType.trim().toString().equalsIgnoreCase("Mini Percolation Tanks") ||
								divType.trim().toString().equalsIgnoreCase("Continuous Contour Trenches") || divType.trim().toString().equalsIgnoreCase("Check Dams") || divType.trim().toString().equalsIgnoreCase("Avenue Plantation") 
								|| divType.trim().toString().equalsIgnoreCase("Forest Others") || divType.trim().toString().equalsIgnoreCase("Scooping and Dibbling of seed"))){
							percValue = new BigDecimal(jObj.getString("PERC")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						
						}else{
							percValue = new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						} 
		    				if(vo != null)
		    				{
			    				if(Double.valueOf(percValue)  < 60){
		    						vo.setVillagesInRed(vo.getVillagesInRed()+1l);
		    					}else if(Double.valueOf(percValue)  >=60 && Double.valueOf(percValue) <90){
		    						vo.setVillagesInOrange(vo.getVillagesInOrange()+1l);
	    						}else if(Double.valueOf(percValue)  >=90 && Double.valueOf(percValue) <100){
		    						vo.setVillagesInGreen(vo.getVillagesInGreen()+1l);
		    					}else if(Double.valueOf(percValue)  >=100){
		    						vo.setVillagesInGold(vo.getVillagesInGold()+1l);
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
						}else if(divType != null && divType.trim().toString().equalsIgnoreCase("coffee")){
							percValue = new BigDecimal(jObj.getString("PLANT_PERC")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						}else if(divType != null && (divType.trim().toString().equalsIgnoreCase("Rock fill dams") || divType.trim().toString().equalsIgnoreCase("Raising and Maintenance of Nursery") ||
								divType.trim().toString().equalsIgnoreCase("Desilting of Perculation Tanks and Check Dams") || divType.trim().toString().equalsIgnoreCase("Mini Percolation Tanks") ||
								divType.trim().toString().equalsIgnoreCase("Continuous Contour Trenches") || divType.trim().toString().equalsIgnoreCase("Check Dams") || divType.trim().toString().equalsIgnoreCase("Avenue Plantation") 
								|| divType.trim().toString().equalsIgnoreCase("Forest Others") || divType.trim().toString().equalsIgnoreCase("Scooping and Dibbling of seed"))){
							percValue = new BigDecimal(jObj.getString("PERC")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						
						}else{
							percValue = new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
						} 
		    				if(vo != null)
		    				{
			    				if(Double.valueOf(percValue)  < 60){
		    						vo.setVillagesInRed(vo.getVillagesInRed()+1l);
		    					}else if(Double.valueOf(percValue)  >=60 && Double.valueOf(percValue) <90){
		    						vo.setVillagesInOrange(vo.getVillagesInOrange()+1l);
	    						}else if(Double.valueOf(percValue)  >=90 && Double.valueOf(percValue) <100){
		    						vo.setVillagesInGreen(vo.getVillagesInGreen()+1l);
		    					}else if(Double.valueOf(percValue)  >=100){
		    						vo.setVillagesInGold(vo.getVillagesInGold()+1l);
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
	 	    				//if(inputVO.getSublocationType().trim().toString().equalsIgnoreCase("state") || inputVO.getSublocationType().trim().toString().equalsIgnoreCase("district")){
	 	    					vo.setTargetACRES(jObj.getString("TARGETACRES"));
		 	    				vo.setSanctionedPerventage(jObj.getString("SANCTIONEDPERCENTAGE"));
		 	    				if(jObj.getString("PITTINGAREA") != null && Double.valueOf(jObj.getString("PITTINGAREA")) > 0l && jObj.getString("TARGETACRES") != null && Double.valueOf(jObj.getString("TARGETACRES")) > 0l)
		 	    					vo.setTargetPittingPerc(new BigDecimal((Double.valueOf(jObj.getString("PITTINGAREA"))*100.00)/Double.valueOf(jObj.getString("TARGETACRES"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    				else
		 	    					vo.setTargetPittingPerc("0.00");
		 	    				//}
	 	    				vo.setSanctionedACRES(jObj.getString("SANCTIONEDACRES"));
	 	    				vo.setPittingArea(jObj.getString("PITTINGAREA"));
	 	    				vo.setPlantingArea(jObj.getString("PLANTINGAREA"));
	 	    				vo.setPencentageOfPlanting(jObj.getString("PERCENTAGEOFPLANTING"));
	 	    				/*if(jObj.getString("PITTINGAREA") != null && Double.valueOf(jObj.getString("PITTINGAREA")) >0 && jObj.getString("SANCTIONEDACRES") != null && Double.valueOf(jObj.getString("SANCTIONEDACRES")) > 0)
	 	    					vo.setSancTrgtPittingPerc(new BigDecimal((Double.valueOf(jObj.getString("PITTINGAREA"))*100.00)/Double.valueOf(jObj.getString("SANCTIONEDACRES"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				else
	 	    					vo.setSancTrgtPittingPerc("0.00");*/
	 	    				
	 	    				if(jObj.getString("PLANTINGAREA") != null && Double.valueOf(jObj.getString("PLANTINGAREA")) > 0l && jObj.getString("TARGETACRES") != null && Double.valueOf(jObj.getString("TARGETACRES")) > 0l)
	 	    					vo.setTargetPalnting(new BigDecimal((Double.valueOf(jObj.getString("PLANTINGAREA"))*100.00)/Double.valueOf(jObj.getString("TARGETACRES"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				else
	 	    					vo.setTargetPalnting("0.00");
	 	    				
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
	 	    				//if(inputVO.getSublocationType().trim().toString().equalsIgnoreCase("state") || inputVO.getSublocationType().trim().toString().equalsIgnoreCase("district")){
	 	    					vo.setTargetKMS(jObj.getString("TARGETACRES"));//TARGETKMS
		 	    				vo.setSanctionedPerventage(jObj.getString("SANCTIONEDPERCENTAGE"));//SANCTIONEDPERCENTAGE
		 	    				if(jObj.getString("PITTINGAREA") != null && Double.valueOf(jObj.getString("PITTINGAREA")) > 0l && vo.getTargetKMS() != null && Double.valueOf(vo.getTargetKMS()) > 0l)
		 	    					vo.setTargetPittingPerc(new BigDecimal(Double.valueOf(jObj.getString("PITTINGAREA"))*100.00/Double.valueOf(vo.getTargetKMS())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    				else
		 	    					vo.setTargetPittingPerc("0.00");
		 	    				//}
	 	    				vo.setSanctionedKMS(jObj.getString("SANCTIONEDACRES"));//SANCTIONEDKMS
	 	    				vo.setPittingKMS(jObj.getString("PITTINGAREA"));//PITTINGKMS
	 	    				vo.setPlantingKMS(jObj.getString("PLANTINGAREA"));//PLANTINGKMS
	 	    				//if(inputVO.getSublocationType().trim().toString().equalsIgnoreCase("state") || inputVO.getSublocationType().trim().toString().equalsIgnoreCase("district")){
	 	    					if(vo.getPlantingKMS() != null && Double.valueOf(vo.getPlantingKMS()) > 0l && vo.getTargetKMS() != null && Double.valueOf(vo.getTargetKMS()) > 0l)
	 	    						vo.setSanctionedPerc(new BigDecimal(Double.valueOf(vo.getPlantingKMS())*100.00/Double.valueOf(vo.getTargetKMS())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    					else
	 	    						vo.setSanctionedPerc("0.00");
		 	    			//}
	 	    				vo.setPencentageOfPlanting(jObj.getString("PERCENTAGEOFPLANTING"));//PERCENTAGEOFPLANTING
	 	    				/*if(jObj.getString("PITTINGAREA") != null && Double.valueOf(jObj.getString("PITTINGAREA")) > 0l && vo.getSanctionedKMS() != null && Double.valueOf(vo.getSanctionedKMS()) > 0l)
	 	    					vo.setSancTrgtPittingPerc(new BigDecimal(Double.valueOf(jObj.getString("PITTINGAREA"))*100.00/Double.valueOf(vo.getSanctionedKMS())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				else
 	    						vo.setSancTrgtPittingPerc("0.00");*/
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
			
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("CC Roads1"))
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
	 	    				if(vo.getCompletedKMS() != null && Double.valueOf(vo.getCompletedKMS()) > 0 && vo.getSanctionedKMS() != null && Double.valueOf(vo.getSanctionedKMS()) > 0)
 	    						vo.setPercSant(new BigDecimal(Double.valueOf(vo.getCompletedKMS())*100.00/Double.valueOf(vo.getSanctionedKMS())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
 	    					else
 	    						vo.setPercSant("0.00");
	 	    				
	 	    				if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("CC Roads1")){
	 	    					vo.setEgsExpenditure(jObj.getString("EXP_EGS"));
	 	    					vo.setConvergenceExpn(jObj.getString("CONV_EXP"));
	 	    				}
	 	    				
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
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Mulbery New"))
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
	 	    	  else if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("GP Buildings1"))
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
	
	public List<NregsProjectsVO> getLocationWiseEmptyVacenciesDetails(InputVO inputVO){
		List<NregsProjectsVO> returnList = new ArrayList<NregsProjectsVO>(0);
		try {
			Map<Long,NregsProjectsVO> locationMap = new LinkedHashMap<Long,NregsProjectsVO>();
			if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("state")){
				inputVO.setLocationId(1L);
			}else if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("district")){
				inputVO.setLocationId(districtDAO.getDistrictIdFromPRDistrictCode(inputVO.getLocationIdStr()));
			}else if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("constituency")){
				List<Object[]> lst = constituencyDAO.getConstituencyIdFromPRConstituencyId(inputVO.getLocationIdStr());
				if(lst != null && !lst.isEmpty()){
					inputVO.setLocationId(Long.valueOf(lst.get(0)[0] != null ? lst.get(0)[0].toString():"0"));
					inputVO.setDistrictId(Long.valueOf(lst.get(0)[1] != null ? lst.get(0)[1].toString():"0"));
				}
			}
			
			List<NregaFAType> typeList = nregaFATypeDAO.getAll();
			
			List<Object[]> list = nregaFAVacantPanchayatDAO.getLocationWiseEmptyVacencies(inputVO.getLocationType(), inputVO.getLocationId(), inputVO.getSublocationType());
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					Long locationId = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
					Long typeId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					NregsProjectsVO locationvo = locationMap.get(locationId);
					if(locationvo == null){
						locationvo = new NregsProjectsVO();
						locationvo.setId(locationId);
						locationvo.setName(obj[4] != null ? obj[4].toString():"");
						locationvo.setSubList(setTypeListToSubList(typeList));
							NregsProjectsVO subvo = getMatchedNregsProjectsVO(locationvo.getSubList(), typeId);
							subvo.setCount(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
						locationvo.setCount(subvo.getCount());
						
						if(inputVO.getSublocationType() != null && inputVO.getSublocationType().trim().length() > 0L){
							locationvo.setState(obj[4] != null ? obj[4].toString():"");
							if(!inputVO.getSublocationType().trim().equalsIgnoreCase("state"))
								locationvo.setDistrict(obj[5] != null ? obj[5].toString():"");
							if(!inputVO.getSublocationType().trim().equalsIgnoreCase("state") && !inputVO.getSublocationType().trim().equalsIgnoreCase("district"))
								locationvo.setConstituency(obj[6] != null ? obj[6].toString():"");
							if(inputVO.getSublocationType().trim().equalsIgnoreCase("mandal") || inputVO.getSublocationType().trim().equalsIgnoreCase("panchayat"))
								locationvo.setMandal(obj[7] != null ? obj[7].toString():"");
							if(inputVO.getSublocationType().trim().equalsIgnoreCase("panchayat"))
								locationvo.setPanchayat(obj[8] != null ? obj[8].toString():"");
						}
						
						locationMap.put(locationId, locationvo);
					}else{
						NregsProjectsVO subvo = getMatchedNregsProjectsVO(locationvo.getSubList(), typeId);
						subvo.setCount(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
						locationvo.setCount(locationvo.getCount()+subvo.getCount());
					}
				}
			}
			
			if(locationMap != null)
				returnList = new ArrayList<NregsProjectsVO>(locationMap.values());
			
			if(inputVO.getLocationType() != null && (inputVO.getLocationType().trim().equalsIgnoreCase("district") || inputVO.getLocationType().trim().equalsIgnoreCase("constituency"))
					&& inputVO.getSector() != null && inputVO.getSector().trim().equalsIgnoreCase("abstract")){
				if(inputVO.getLocationType().trim().equalsIgnoreCase("constituency")){
					Long districtCount = nregaFAVacantPanchayatDAO.getLocationWiseEmptyVacencyCount("district", inputVO.getDistrictId());
					NregsProjectsVO vo = new NregsProjectsVO();
					vo.setCount(districtCount);
					returnList.get(0).getSubList1().add(vo);
				}
				Long stateCount = nregaFAVacantPanchayatDAO.getLocationWiseEmptyVacencyCount("state", 1L);
				NregsProjectsVO vo = new NregsProjectsVO();
				vo.setCount(stateCount);
				returnList.get(0).getSubList1().add(vo);
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationWiseEmptyVacenciesDetails - NREGSTCSService service", e);
		}
		
		return returnList;
	}
	
	public NregsProjectsVO getMatchedNregsProjectsVO(List<NregsProjectsVO> list,Long id){
		try{
			if(list != null && !list.isEmpty()){
				for (NregsProjectsVO nregaPaymentsVO : list) {
					if(nregaPaymentsVO.getId().equals(id)){
						return nregaPaymentsVO;
					}
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception raised at getMatchedVOList - NREGSTCSService service", e);
		}
		return null;
	}
	
	public List<NregsProjectsVO> setTypeListToSubList(List<NregaFAType> typeList){
		List<NregsProjectsVO> returnList = new ArrayList<NregsProjectsVO>(0);
		try {
			if(typeList != null && !typeList.isEmpty()){
				for (NregaFAType nregaFAType : typeList) {
					NregsProjectsVO vo = new NregsProjectsVO();
					vo.setId(nregaFAType.getNregaFaTypeId());
					vo.setName(nregaFAType.getType());
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at setTypeListToSubList - NREGSTCSService service", e);
		}
		return returnList;
	}
	
	public List<NregsProjectsVO> getNREGSAbstractDataByType(InputVO inputVO){
		List<NregsProjectsVO> returnList = new ArrayList<NregsProjectsVO>();
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
	 	    				else if(inputVO.getType().toString().trim().equalsIgnoreCase("coffee")){
								vo.setTarget(jObj.getString("TARGET_AREA"));
								vo.setCompleted(jObj.getString("PLANT_AREA"));
							}
	 	    				else{
	 	    					vo.setTarget(jObj.getString("TARGET"));
		 	    				vo.setCompleted(jObj.getString("COMPLETED"));
	 	    				}
	 	    				
	 	    				if(inputVO.getType().toString().trim().equalsIgnoreCase("Agriculture Activities")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("SMC Trench") || inputVO.getType().toString().trim().equalsIgnoreCase("Imp to CD")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("MPT_PT") || inputVO.getType().toString().trim().equalsIgnoreCase("GC Works")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("CD_CW")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("Check Dam") || inputVO.getType().toString().trim().equalsIgnoreCase("Rock fill dams")
	 	    						|| inputVO.getType().toString().trim().equalsIgnoreCase("Others MCC"))
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
	 	    				else if(inputVO.getType().toString().trim().equalsIgnoreCase("coffee"))
	 	    					vo.setPercentage(jObj.getString("PLANT_PERC"));
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
			
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/APLabourBudgetPanchayats/APLabourBdgtPanchayats", str);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	List<String> uniqueCodeStr=new ArrayList<String>();
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
	 	    				vo.setUniqueCode(jObj.getString("UNIQUEID"));
	 	    				uniqueCodeStr.add(vo.getUniqueCode());
	 	    				voList.add(vo);
	 	    				
	 	    			}
	 	    			
	 	    		}
	 	    	}
	 	    	List<Object[]> nregaComments= nregaComponentCommentsDAO.getNregaComponentComments(uniqueCodeStr);
 				if(nregaComments != null && nregaComments.size()>0){
 					for(Object[] param : nregaComments){
 						NregsDataVO matchedVo= getMatchedVoForUniqueCode(voList,commonMethodsUtilService.getStringValueForObject(param[3]));
 						if(matchedVo != null){
 							matchedVo.setStatus(commonMethodsUtilService.getStringValueForObject(param[0]));
 							matchedVo.setComments(commonMethodsUtilService.getStringValueForObject(param[1]));
 							matchedVo.setActionPlan(commonMethodsUtilService.getStringValueForObject(param[2]));
 							matchedVo.setStatusId(commonMethodsUtilService.getLongValueForObject(param[4]));
 							matchedVo.setComponentId(commonMethodsUtilService.getLongValueForObject(param[5]));
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
	 	    	  else if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("GP Buildings1"))
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
	    		  inputVO.setType("GP Buildings1");
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
	 	    	  else if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("GP Buildings1"))
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
		List<NregsProjectsVO> returnList = new ArrayList<NregsProjectsVO>();
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
	 	    				+Double.valueOf(jsonObj.getString("REJECT_WAGES_AMT"))+Double.valueOf(jsonObj.getString("REJECT_MATERIAL_AMT")))));
	 	    				//-Double.valueOf(jsonObj.getString("RESPONSE_PENDING_WAGES_AMT"))-Double.valueOf(jsonObj.getString("RESPONSE_PENDING_MATERIAL_AMT")))));
	 	    				//+Double.valueOf(jsonObj.getString("RELEASE_PENDING_WAGES_AMT"))+Double.valueOf(jsonObj.getString("RELEASE_PENDING_MATERIAL_AMT")))));
	 	    				
	 	    				paymentDtlsVO.setPendingWage(cnvrtRupeesIntoCrores(String.valueOf(Double.valueOf(jsonObj.getString("NOT_GENERATED_WAGES_AMT"))
	 	    				+Double.valueOf(jsonObj.getString("NOT_UPLOADED_WAGES_AMT"))
	 	    				+Double.valueOf(jsonObj.getString("NOT_SENTPFMS_WAGES_AMT"))
	 	    				+Double.valueOf(jsonObj.getString("REJECT_WAGES_AMT")))));
	 	    				//-Double.valueOf(jsonObj.getString("RESPONSE_PENDING_WAGES_AMT")))));
	 	    				//+Double.valueOf(jsonObj.getString("RELEASE_PENDING_WAGES_AMT")))));
	 	    				
	 	    				paymentDtlsVO.setPendingMaterial(cnvrtRupeesIntoCrores(String.valueOf(Double.valueOf(jsonObj.getString("NOT_GENERATED_MATERIAL_AMT"))
	 	    				+Double.valueOf(jsonObj.getString("NOT_UPLOADED_MATERIAL_AMT"))
	 	    				+Double.valueOf(jsonObj.getString("NOT_SENTPFMS_MATERIAL_AMT"))
	 	    				+Double.valueOf(jsonObj.getString("REJECT_MATERIAL_AMT")))));
	 	    				//-Double.valueOf(jsonObj.getString("RESPONSE_PENDING_MATERIAL_AMT")))));
	 	    				//+Double.valueOf(jsonObj.getString("RELEASE_PENDING_MATERIAL_AMT")))));
	 	    				  if(!jsonObj.getString("UNIQUEID").equalsIgnoreCase("-"))
	 	    					  resultList.add(paymentDtlsVO);
	 	    				  
	 	    			  }
	 	    		 }
	 	    	 }
	 	    	 
	 	      }
	        
	        if(inputVO.getLocationType() != null && (inputVO.getLocationType().trim().equalsIgnoreCase("district") || inputVO.getLocationType().trim().equalsIgnoreCase("constituency"))
					&& inputVO.getSector() != null && inputVO.getSector().trim().equalsIgnoreCase("abstract")){
	        	if(inputVO.getLocationType().trim().equalsIgnoreCase("constituency")){
	        		//district
		        	inputVO.setLocationType("district");
		        	inputVO.setSublocationType("district");
		        	inputVO.setLocationId(inputVO.getDistrictId());
		        	
		        	str = convertingInputVOToString(inputVO);
					
					response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
			        
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
			 	    				
			 	    				paymentDtlsVO.setTotalPendinAmount(cnvrtRupeesIntoCrores(String.valueOf(Double.valueOf(jsonObj.getString("NOT_GENERATED_WAGES_AMT"))+Double.valueOf(jsonObj.getString("NOT_GENERATED_MATERIAL_AMT"))
			 	    				+Double.valueOf(jsonObj.getString("NOT_UPLOADED_WAGES_AMT"))+Double.valueOf(jsonObj.getString("NOT_UPLOADED_MATERIAL_AMT"))
			 	    				+Double.valueOf(jsonObj.getString("NOT_SENTPFMS_WAGES_AMT"))+Double.valueOf(jsonObj.getString("NOT_SENTPFMS_MATERIAL_AMT"))
			 	    				+Double.valueOf(jsonObj.getString("REJECT_WAGES_AMT"))+Double.valueOf(jsonObj.getString("REJECT_MATERIAL_AMT")))));
			 	    				
			 	    				paymentDtlsVO.setPendingWage(cnvrtRupeesIntoCrores(String.valueOf(Double.valueOf(jsonObj.getString("NOT_GENERATED_WAGES_AMT"))
			 	    				+Double.valueOf(jsonObj.getString("NOT_UPLOADED_WAGES_AMT"))
			 	    				+Double.valueOf(jsonObj.getString("NOT_SENTPFMS_WAGES_AMT"))
			 	    				+Double.valueOf(jsonObj.getString("REJECT_WAGES_AMT")))));
			 	    				
			 	    				paymentDtlsVO.setPendingMaterial(cnvrtRupeesIntoCrores(String.valueOf(Double.valueOf(jsonObj.getString("NOT_GENERATED_MATERIAL_AMT"))
			 	    				+Double.valueOf(jsonObj.getString("NOT_UPLOADED_MATERIAL_AMT"))
			 	    				+Double.valueOf(jsonObj.getString("NOT_SENTPFMS_MATERIAL_AMT"))
			 	    				+Double.valueOf(jsonObj.getString("REJECT_MATERIAL_AMT")))));
			 	    				if(!jsonObj.getString("UNIQUEID").equalsIgnoreCase("-"))
			 	    					resultList.get(0).getSubList().add(paymentDtlsVO);
			 	    				  
			 	    			  }
			 	    		 }
			 	    	 }
			 	    	 
			 	      }
	        	}
	        	//state
	        	inputVO.setLocationType("state");
	        	inputVO.setSublocationType("state");
	        	inputVO.setLocationId(1L);
	        	
	        	str = convertingInputVOToString(inputVO);
				
				response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
		        
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
		 	    				
		 	    				paymentDtlsVO.setTotalPendinAmount(cnvrtRupeesIntoCrores(String.valueOf(Double.valueOf(jsonObj.getString("NOT_GENERATED_WAGES_AMT"))+Double.valueOf(jsonObj.getString("NOT_GENERATED_MATERIAL_AMT"))
		 	    				+Double.valueOf(jsonObj.getString("NOT_UPLOADED_WAGES_AMT"))+Double.valueOf(jsonObj.getString("NOT_UPLOADED_MATERIAL_AMT"))
		 	    				+Double.valueOf(jsonObj.getString("NOT_SENTPFMS_WAGES_AMT"))+Double.valueOf(jsonObj.getString("NOT_SENTPFMS_MATERIAL_AMT"))
		 	    				+Double.valueOf(jsonObj.getString("REJECT_WAGES_AMT"))+Double.valueOf(jsonObj.getString("REJECT_MATERIAL_AMT")))));
		 	    				
		 	    				paymentDtlsVO.setPendingWage(cnvrtRupeesIntoCrores(String.valueOf(Double.valueOf(jsonObj.getString("NOT_GENERATED_WAGES_AMT"))
		 	    				+Double.valueOf(jsonObj.getString("NOT_UPLOADED_WAGES_AMT"))
		 	    				+Double.valueOf(jsonObj.getString("NOT_SENTPFMS_WAGES_AMT"))
		 	    				+Double.valueOf(jsonObj.getString("REJECT_WAGES_AMT")))));
		 	    				
		 	    				paymentDtlsVO.setPendingMaterial(cnvrtRupeesIntoCrores(String.valueOf(Double.valueOf(jsonObj.getString("NOT_GENERATED_MATERIAL_AMT"))
		 	    				+Double.valueOf(jsonObj.getString("NOT_UPLOADED_MATERIAL_AMT"))
		 	    				+Double.valueOf(jsonObj.getString("NOT_SENTPFMS_MATERIAL_AMT"))
		 	    				+Double.valueOf(jsonObj.getString("REJECT_MATERIAL_AMT")))));
		 	    				if(!jsonObj.getString("UNIQUEID").equalsIgnoreCase("-"))
		 	    					resultList.get(0).getSubList().add(paymentDtlsVO);
		 	    				  
		 	    			  }
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
				
				//paymentDtlsVO.setTotalPendinAmount(String.valueOf(Long.valueOf(jsonObj.getString("TOTAL_AMOUNT")) - Long.valueOf(jsonObj.getString("COMPLETED_AMT"))));
				paymentDtlsVO.setTotalPendinAmount(new BigDecimal(Double.valueOf(jsonObj.getString("NOT_GENERATED_AMT"))
						+Double.valueOf(jsonObj.getString("NOT_UPLOADED_AMT"))+Double.valueOf(jsonObj.getString("NOT_SENTPFMS_AMT"))
						+Double.valueOf(jsonObj.getString("REJECT_AMT"))).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
						//-Double.valueOf(jsonObj.getString("RESPONSE_PENDING_AMT"))));
						//+Double.valueOf(jsonObj.getString("RELEASE_PENDING_AMT"))).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
				
				//new BigDecimal(Double.valueOf(value)/10000000.00d).setScale(2, BigDecimal.ROUND_HALF_UP).toString()
				
				paymentDtlsVO.setPendingAtBankAmount(String.valueOf(Long.valueOf(jsonObj.getString("RELEASE_PENDING_AMT")) + Long.valueOf(jsonObj.getString("RESPONSE_PENDING_AMT"))));
			 } else {
				paymentDtlsVO.setTotalAmount(cnvrtRupeesIntoCroresNew(jsonObj.getString("TOTAL_AMOUNT")));
				paymentDtlsVO.setGeneratedWageAmount(cnvrtRupeesIntoCroresNew(jsonObj.getString("GENERATED_AMT")));
				paymentDtlsVO.setNotGeneratedWagesAmount(cnvrtRupeesIntoCroresNew(jsonObj.getString("NOT_GENERATED_AMT")));
				paymentDtlsVO.setUploadedWageAmount(cnvrtRupeesIntoCroresNew(jsonObj.getString("UPLOADED_AMT")));
				paymentDtlsVO.setNotUploadedWagesAmount(cnvrtRupeesIntoCroresNew(jsonObj.getString("NOT_UPLOADED_AMT")));
				paymentDtlsVO.setSentBankWageAmount(cnvrtRupeesIntoCroresNew(jsonObj.getString("SENTPFMS_AMT")));
				paymentDtlsVO.setNotSentBankWageAmount(cnvrtRupeesIntoCroresNew(jsonObj.getString("NOT_SENTPFMS_AMT")));
				paymentDtlsVO.setCompletedWageAmount(cnvrtRupeesIntoCroresNew(jsonObj.getString("COMPLETED_AMT")));
				paymentDtlsVO.setRejectedWagesAmount(cnvrtRupeesIntoCroresNew(jsonObj.getString("REJECT_AMT")));
				paymentDtlsVO.setReleasePendingWageAmount(cnvrtRupeesIntoCroresNew(jsonObj.getString("RELEASE_PENDING_AMT")));
				paymentDtlsVO.setResponsePendingWageAmount(cnvrtRupeesIntoCroresNew(jsonObj.getString("RESPONSE_PENDING_AMT")));
				paymentDtlsVO.setReprocessPendingWageAmount(cnvrtRupeesIntoCroresNew(jsonObj.getString("REPROCESS_PENDING_AMT")));
				
				//paymentDtlsVO.setTotalPendinAmount(cnvrtRupeesIntoCrores(String.valueOf(Long.valueOf(jsonObj.getString("TOTAL_AMOUNT")) - Long.valueOf(jsonObj.getString("COMPLETED_AMT")))));
				paymentDtlsVO.setTotalPendinAmount(cnvrtRupeesIntoCroresNew(String.valueOf(Double.valueOf(jsonObj.getString("NOT_GENERATED_AMT"))
						+Double.valueOf(jsonObj.getString("NOT_UPLOADED_AMT"))+Double.valueOf(jsonObj.getString("NOT_SENTPFMS_AMT"))
						+Double.valueOf(jsonObj.getString("REJECT_AMT")))));
						//-Double.valueOf(jsonObj.getString("RESPONSE_PENDING_AMT")))));
						//+Double.valueOf(jsonObj.getString("RELEASE_PENDING_AMT")))));
				paymentDtlsVO.setPendingAtBankAmount(cnvrtRupeesIntoCroresNew(String.valueOf(Long.valueOf(jsonObj.getString("RELEASE_PENDING_AMT")) + Long.valueOf(jsonObj.getString("RESPONSE_PENDING_AMT")))));
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
				returnVal = returnVal + " Cr";
			}
		} catch (Exception e) {
			LOG.error("Exception raised at cnvrtRupeesIntoCrores - NREGSTCSService service", e);
		}
		return returnVal.trim();
	}
	
	/*
	 * Date : 30/10/2017
	 * Author :Nandhini
	 * @description : getNREGSForestAbstact
	 */
	public List<NregsProjectsVO> getNREGSForestAbstact(InputVO inputVO){
		List<NregsProjectsVO> voList = new ArrayList<NregsProjectsVO>(0);
		try {
			String  webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/ForestAbstractService/ForestAbstractData";
			
			if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("Construction of Checkdam across peeraiah vanka Check Dam 1"))
				inputVO.setType("Construction of Checkdam across peeraiah vanka(Check Dam-1)");
			else if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("Construction of Checkdam across peeraiah vanka Check Dam 2"))
				inputVO.setType("Construction of Checkdam across peeraiah vanka(Check Dam-2)");
			else if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("Construction of Checkdam across peeraiah vanka Check Dam 3"))
				inputVO.setType("Construction of Checkdam across peeraiah vanka(Check Dam-3)");
			else if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("Construction of Checkdam across peeraiah vanka Check Dam 4"))
				inputVO.setType("Construction of Checkdam across peeraiah vanka(Check Dam-4)");
			else if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("Construction of Checkdam across peeraiah vanka Check Dam 5"))
				inputVO.setType("Construction of Checkdam across peeraiah vanka(Check Dam-5)");
			else if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("Enrichment Plantation with 4 7 Bag seedlings or Eucalyptus Clones"))
				inputVO.setType("Enrichment Plantation with 4x7 Bag seedlings or Eucalyptus Clones");
			else if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("HILL AFFORESTATION 3 3 Plantation"))
				inputVO.setType("HILL AFFORESTATION 3x3 Plantation");
			else if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("SMM 3 2 Plantation"))
				inputVO.setType("SMM 3x2 Plantation");
			else if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("SMM 3 3 Plantation"))
				inputVO.setType("SMM 3X3 Plantation");
			else if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("SMM 5 5 Plantation"))
				inputVO.setType("SMM 5X5 Plantation");
			else if(inputVO.getType() != null && inputVO.getType().trim().equalsIgnoreCase("Work Site Facilities1"))
				inputVO.setType("Work Site Facilities");
			
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
	 	    				NregsProjectsVO vo = new NregsProjectsVO();
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				vo.setParameter(jObj.getString("PARAMETER"));
	 	    				vo.setTarget(jObj.getString("TARGET"));
	 	    				vo.setCompleted(jObj.getString("COMPLETED"));
	 	    				vo.setPercentage(new BigDecimal(jObj.getString("PERC")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				voList.add(vo);
	 	    			}
	 	    		}
	 	    	}
	 	    }
			
			if(inputVO.getLocationType() != null && (inputVO.getLocationType().trim().equalsIgnoreCase("district") || inputVO.getLocationType().trim().equalsIgnoreCase("constituency"))){
	        	if(inputVO.getLocationType().trim().equalsIgnoreCase("constituency")){
	        		//district
		        	inputVO.setLocationType("district");
		        	//inputVO.setSublocationType("district");
		        	inputVO.setLocationId(inputVO.getDistrictId());
		        	
		        	str = convertingInputVOToString(inputVO);
					
					response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
			        
			        if (response.getStatus() != 200) {
			 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			 	      } else {
			 	    	 String output = response.getEntity(String.class);
			 	    	 if (output != null && output.length() > 0) {
			 	    		 JSONArray finalArray = new JSONArray(output);	 
			 	    		 if (finalArray != null && finalArray.length() > 0) {
			 	    			  for (int i = 0 ;i < finalArray.length() ;i++) {
			 	    				  
			 	    				 NregsProjectsVO vo = new NregsProjectsVO();
			 	    				 JSONObject jsonObj = (JSONObject) finalArray.get(i);
			 	    				  
			 	    				vo.setPercentage(new BigDecimal(jsonObj.getString("PERC")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			 	    				vo.setType("DISTRICT");
			 	    				voList.get(0).getSubList().add(vo);
			 	    				  
			 	    			  }
			 	    		 }
			 	    	 }
			 	    	 
			 	      }
	        	}
	        	//state
	        	inputVO.setLocationType("state");
	        	//inputVO.setSublocationType("state");
	        	inputVO.setLocationIdStr("1");
	        	
	        	str = convertingInputVOToString(inputVO);
				
				response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
		        
		        if (response.getStatus() != 200) {
		 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		 	      } else {
		 	    	 String output = response.getEntity(String.class);
		 	    	 if (output != null && output.length() > 0) {
		 	    		 JSONArray finalArray = new JSONArray(output);	 
		 	    		 if (finalArray != null && finalArray.length() > 0) {
		 	    			  for (int i = 0 ;i < finalArray.length() ;i++) {
		 	    				  
		 	    				 NregsProjectsVO vo = new NregsProjectsVO();
		 	    				 JSONObject jsonObj = (JSONObject) finalArray.get(i);
		 	    				  
		 	    				vo.setPercentage(new BigDecimal(jsonObj.getString("PERC")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    				vo.setType("STATE");
		 	    				voList.get(0).getSubList().add(vo);
		 	    				  
		 	    			  }
		 	    		 }
		 	    	 }
		 	      }
	        }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSForestAbstact - NREGSTCSService service", e);
		}
		
		return voList;
	}
	
	/*
	 * Date : 30/10/2017
	 * Author :Nandhini
	 * @description : getNregasForestOverview
	 */
	public NregsOverviewVO getNregasForestOverview(InputVO inputVO){
		NregsOverviewVO finalVO = new NregsOverviewVO();
		try {
			String webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/ForestService/ForestOverview";
			
			if(inputVO.getCategory() != null && inputVO.getCategory().trim().equalsIgnoreCase("Construction of Checkdam across peeraiah vanka Check Dam 1"))
				inputVO.setCategory("Construction of Checkdam across peeraiah vanka(Check Dam-1)");
			else if(inputVO.getCategory() != null && inputVO.getCategory().trim().equalsIgnoreCase("Construction of Checkdam across peeraiah vanka Check Dam 2"))
				inputVO.setCategory("Construction of Checkdam across peeraiah vanka(Check Dam-2)");
			else if(inputVO.getCategory() != null && inputVO.getCategory().trim().equalsIgnoreCase("Construction of Checkdam across peeraiah vanka Check Dam 3"))
				inputVO.setCategory("Construction of Checkdam across peeraiah vanka(Check Dam-3)");
			else if(inputVO.getCategory() != null && inputVO.getCategory().trim().equalsIgnoreCase("Construction of Checkdam across peeraiah vanka Check Dam 4"))
				inputVO.setCategory("Construction of Checkdam across peeraiah vanka(Check Dam-4)");
			else if(inputVO.getCategory() != null && inputVO.getCategory().trim().equalsIgnoreCase("Construction of Checkdam across peeraiah vanka Check Dam 5"))
				inputVO.setCategory("Construction of Checkdam across peeraiah vanka(Check Dam-5)");
			else if(inputVO.getCategory() != null && inputVO.getCategory().trim().equalsIgnoreCase("Enrichment Plantation with 4 7 Bag seedlings or Eucalyptus Clones"))
				inputVO.setCategory("Enrichment Plantation with 4x7 Bag seedlings or Eucalyptus Clones");
			else if(inputVO.getCategory() != null && inputVO.getCategory().trim().equalsIgnoreCase("HILL AFFORESTATION 3 3 Plantation"))
				inputVO.setCategory("HILL AFFORESTATION 3x3 Plantation");
			else if(inputVO.getCategory() != null && inputVO.getCategory().trim().equalsIgnoreCase("SMM 3 2 Plantation"))
				inputVO.setCategory("SMM 3x2 Plantation");
			else if(inputVO.getCategory() != null && inputVO.getCategory().trim().equalsIgnoreCase("SMM 3 3 Plantation"))
				inputVO.setCategory("SMM 3X3 Plantation");
			else if(inputVO.getCategory() != null && inputVO.getCategory().trim().equalsIgnoreCase("SMM 5 5 Plantation"))
				inputVO.setCategory("SMM 5X5 Plantation");
			else if(inputVO.getCategory() != null && inputVO.getCategory().trim().equalsIgnoreCase("Work Site Facilities1"))
				inputVO.setCategory("Work Site Facilities");
			
			String str = convertingInputVOToString(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject Obj = new JSONObject(output);
	 	    		if(Obj!=null && Obj.length()>0){
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
			LOG.error("Exception raised at getNregasForestOverview - NREGSTCSService service", e);
		}
		
		return finalVO;
	}
	
	/*
	 * Date : 30/10/2017
	 * Author :Nandhini
	 * @description : getNregaForestLevelData
	 */
	public List<NregsDataVO> getNregaForestLevelData(InputVO inputVO){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
			if(inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().toString().length() > 0l)
				inputVO.setSublocationType(inputVO.getSublocaType().trim());
			
			String webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/ForestService/ForestData";
			
			if(inputVO.getCategory() != null && inputVO.getCategory().trim().equalsIgnoreCase("Construction of Checkdam across peeraiah vanka Check Dam 1"))
				inputVO.setCategory("Construction of Checkdam across peeraiah vanka(Check Dam-1)");
			else if(inputVO.getCategory() != null && inputVO.getCategory().trim().equalsIgnoreCase("Construction of Checkdam across peeraiah vanka Check Dam 2"))
				inputVO.setCategory("Construction of Checkdam across peeraiah vanka(Check Dam-2)");
			else if(inputVO.getCategory() != null && inputVO.getCategory().trim().equalsIgnoreCase("Construction of Checkdam across peeraiah vanka Check Dam 3"))
				inputVO.setCategory("Construction of Checkdam across peeraiah vanka(Check Dam-3)");
			else if(inputVO.getCategory() != null && inputVO.getCategory().trim().equalsIgnoreCase("Construction of Checkdam across peeraiah vanka Check Dam 4"))
				inputVO.setCategory("Construction of Checkdam across peeraiah vanka(Check Dam-4)");
			else if(inputVO.getCategory() != null && inputVO.getCategory().trim().equalsIgnoreCase("Construction of Checkdam across peeraiah vanka Check Dam 5"))
				inputVO.setCategory("Construction of Checkdam across peeraiah vanka(Check Dam-5)");
			else if(inputVO.getCategory() != null && inputVO.getCategory().trim().equalsIgnoreCase("Enrichment Plantation with 4 7 Bag seedlings or Eucalyptus Clones"))
				inputVO.setCategory("Enrichment Plantation with 4x7 Bag seedlings or Eucalyptus Clones");
			else if(inputVO.getCategory() != null && inputVO.getCategory().trim().equalsIgnoreCase("HILL AFFORESTATION 3 3 Plantation"))
				inputVO.setCategory("HILL AFFORESTATION 3x3 Plantation");
			else if(inputVO.getCategory() != null && inputVO.getCategory().trim().equalsIgnoreCase("SMM 3 2 Plantation"))
				inputVO.setCategory("SMM 3x2 Plantation");
			else if(inputVO.getCategory() != null && inputVO.getCategory().trim().equalsIgnoreCase("SMM 3 3 Plantation"))
				inputVO.setCategory("SMM 3X3 Plantation");
			else if(inputVO.getCategory() != null && inputVO.getCategory().trim().equalsIgnoreCase("SMM 5 5 Plantation"))
				inputVO.setCategory("SMM 5X5 Plantation");
			else if(inputVO.getCategory() != null && inputVO.getCategory().trim().equalsIgnoreCase("Work Site Facilities1"))
				inputVO.setCategory("Work Site Facilities");
			
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
	 	    				vo.setNotGrounded(jObj.getString("NOTGROUNDED"));
	 	    				vo.setInProgress(jObj.getLong("INPROGRESS"));
	 	    				vo.setCompleted(jObj.getLong("COMPLETED"));
		 	    			vo.setPercentage(jObj.getString("PERC"));
		 	    			voList.add(vo);
	 	    			}
	 	    		}
	 	    	}
	 	      }
	    } catch (Exception e) {
			LOG.error("Exception raised at getNregaForestLevelData - NREGSTCSService service", e);
		}
		
		return voList;
	}
	
	public String cnvrtRupeesIntoCroresNew(String value){
		String returnVal = "0";
		try {
			if(value != null) {
				returnVal = new BigDecimal(Double.valueOf(value)/10000000.00d).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				//returnVal = returnVal + " CR";
			}
		} catch (Exception e) {
			LOG.error("Exception raised at cnvrtRupeesIntoCroresNew - NREGSTCSService service", e);
		}
		return returnVal.trim();
	}
	/*
	 * Date : 31/10/2017
	 * Author :Nandhini
	 * @description : getSessionTokenDetails
	 */
	
	public WaterTanksClorinationVO getSessionTokenDetails(InputVO inputVO){
		WaterTanksClorinationVO finaVO = new WaterTanksClorinationVO();
		try {
			String str = convertingInputVOToStringFrWBDashBoard(inputVO);
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://115.112.122.116/api/v2/user/session");
			ClientResponse response = webResource.accept("application/json").type("application/json").header("X-DreamFactory-Api-Key","f13c6aa6edc82e5ad15f2c43de44294bc3ce3443af0fb320bba3898acede1a08").post(ClientResponse.class, str);
			
			if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	    }else{
				String output = response.getEntity(String.class);
				if(output != null && !output.isEmpty()){
					JSONObject jObj = new JSONObject(output);
					finaVO.setSessionToken(jObj.getString("session_token"));
					finaVO.setSessionId(jObj.getString("session_id"));
					finaVO.setId(jObj.getLong("id"));
					finaVO.setName(jObj.getString("name"));
					finaVO.setFirstName(jObj.getString("first_name"));
					finaVO.setLastName(jObj.getString("last_name"));
					finaVO.setEmail(jObj.getString("email"));
					finaVO.setIsSysAdmin(jObj.getString("is_sys_admin"));
					finaVO.setLastLoginDate(jObj.getString("last_login_date"));
					finaVO.setHost(jObj.getString("host"));
					sessionToken = jObj.getString("session_token");
				}
	 	    }
		} catch (Exception e) {
			LOG.error("Exception raised at getSessionTokenDetails - NREGSTCSService service", e);
		}
		return finaVO;
	}
	
	public String convertingInputVOToStringFrWBDashBoard(InputVO inputVO){
		String str = "";
		try {
			str = "{";
			
			if(inputVO.getLeadName() != null )
				str += "\"email\" : \""+inputVO.getLeadName()+"\",";
			if(inputVO.getCategory() != null)
				str += "\"password\" : \""+inputVO.getCategory()+"\",";
			if(inputVO.getYear() != null)
				str += "\"duration\" : \""+inputVO.getYear()+"\",";
			
			
			if(str.length() > 1)
				str = str.substring(0,str.length()-1);
			
			str += "}";
			
		} catch (Exception e) {
			LOG.error("Exception raised at convertingInputVOToStringFrWBDashBoard - NREGSTCSService service", e);
		}
		return str;
	}
	
	/*
	 * Date : 01/11/2017
	 * Author :Sravanth
	 * @description : getDistrictWiseClorinationDetails
	 */
	
	public List<WaterTanksClorinationVO> getDistrictWiseClorinationDetails(InputVO inputVO){
		List<WaterTanksClorinationVO> returnList = new ArrayList<WaterTanksClorinationVO>(0);
		try {
			Map<Long,WaterTanksClorinationVO> districtMap = new LinkedHashMap<Long,WaterTanksClorinationVO>();
			List<WaterTanksClorinationVO> fnalList = new ArrayList<WaterTanksClorinationVO>(0);
			String url = null;
			WebResource webResource = null;
			ClientResponse response = null;
			Long nextCount = 0L;
			Long offsetValue =0L;
			
			url = "http://115.112.122.116/api/v2/vhop_add_on/_table/waterbody_dist_wise?filter=(visit_date%20%3E%3D%20"+inputVO.getFromDateStr()+")%20AND%20(visit_date%20%3C%3D%20"+inputVO.getToDateStr()+")&limit=0&offset=0";
			webResource = commonMethodsUtilService.getWebResourceObject(url);
			response = webResource.accept("application/json").type("application/json").header("X-DreamFactory-Api-Key","f13c6aa6edc82e5ad15f2c43de44294bc3ce3443af0fb320bba3898acede1a08").header("X-DreamFactory-Session-Token", inputVO.getSession()).get(ClientResponse.class);
			
			if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	    }else{
				String output = response.getEntity(String.class);
				if(output != null && !output.isEmpty()){
					JSONObject jsonObject = new JSONObject(output);
					if(jsonObject.has("meta")){
						JSONObject metaObject = jsonObject.getJSONObject("meta");
						if(metaObject.has("next")){
							nextCount = Long.valueOf(metaObject.getString("count"));
							JSONArray finalArray = jsonObject.getJSONArray("resource");
							List<WaterTanksClorinationVO> dataList = setDistrictLevelDataToVO(finalArray);
							fnalList.addAll(dataList);
						}
					}else{
						JSONArray finalArray = jsonObject.getJSONArray("resource");
						List<WaterTanksClorinationVO> dataList = setDistrictLevelDataToVO(finalArray);
						fnalList.addAll(dataList);
					}
				}	
	 	    }
			
			if(nextCount != null && nextCount.longValue() >0L){			
			  for(int i=1000;i<=nextCount;i=i+1000){
				offsetValue = Long.valueOf(i);
				url = "http://115.112.122.116/api/v2/vhop_add_on/_table/waterbody_dist_wise?filter=(visit_date%20%3E%3D%20"+inputVO.getFromDateStr()+")%20AND%20(visit_date%20%3C%3D%20"+inputVO.getToDateStr()+")&limit=0&offset="+offsetValue+"";
				webResource = commonMethodsUtilService.getWebResourceObject(url);
				response = webResource.accept("application/json").type("application/json").header("X-DreamFactory-Api-Key","f13c6aa6edc82e5ad15f2c43de44294bc3ce3443af0fb320bba3898acede1a08").header("X-DreamFactory-Session-Token", inputVO.getSession()).get(ClientResponse.class);
				
				if(response.getStatus() != 200){
		 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		 	    }else{
					String output = response.getEntity(String.class);
					if(output != null && !output.isEmpty()){
						JSONObject jsonObject = new JSONObject(output);
						JSONArray finalArray = jsonObject.getJSONArray("resource");
						List<WaterTanksClorinationVO> dataList = setDistrictLevelDataToVO(finalArray);
						fnalList.addAll(dataList);
					}
				}	
		    }
		}
						
			if(fnalList != null && !fnalList.isEmpty()){
				for (WaterTanksClorinationVO dataVO : fnalList) {
				Long districtId = dataVO.getDistrictId();
				WaterTanksClorinationVO distvo = districtMap.get(districtId);
				if(distvo == null){
					distvo = new WaterTanksClorinationVO();
					distvo.setDistrictId(districtId);
					distvo.setDistrictName(dataVO.getDistrictName());
					distvo.setNoOfSPs(dataVO.getNoOfSPs());
					distvo.setChecked(dataVO.getChecked());
					distvo.setClorinated(dataVO.getClorinated());
					distvo.setNotClorinated(dataVO.getNotClorinated());
					
					districtMap.put(districtId, distvo);
				}else{
					distvo.setNoOfSPs(distvo.getNoOfSPs()+dataVO.getNoOfSPs());
					distvo.setChecked(distvo.getChecked()+dataVO.getChecked());
					distvo.setClorinated(distvo.getClorinated()+dataVO.getClorinated());
					distvo.setNotClorinated(distvo.getNotClorinated()+dataVO.getNotClorinated());
				}
			}
		}
	 	    	
    	if(districtMap != null)
    		returnList = new ArrayList<WaterTanksClorinationVO>(districtMap.values());
	 	    
		} catch (Exception e) {
			LOG.error("Exception raised at getDistrictWiseClorinationDetails - NREGSTCSService service", e);
		}
		return returnList;
	}
	
	/*
	 * Date : 01/11/2017
	 * Author :Sravanth
	 * @description : getSpWiseClorinationDetails
	 */
	
	public List<WaterTanksClorinationVO> getLocationWiseClorinationDetails(InputVO inputVO){
		List<WaterTanksClorinationVO> returnList = new ArrayList<WaterTanksClorinationVO>(0);
		try {
			List<WaterTanksClorinationVO> fnalList = new ArrayList<WaterTanksClorinationVO>(0);
			String url = null;
			WebResource webResource = null;
			ClientResponse response = null;
			Long nextCount = 0L;
			Long offsetValue =0L;
						
			url = "http://115.112.122.116/api/v2/vhop_add_on/_table/waterbody?filter=(visit_date%20%3E%3D%20"+inputVO.getFromDateStr()+")%20AND%20(visit_date%20%3C%3D%20"+inputVO.getToDateStr()+")&limit=0&offset=0";
			webResource = commonMethodsUtilService.getWebResourceObject(url);
			response = webResource.accept("application/json").type("application/json").header("X-DreamFactory-Api-Key","f13c6aa6edc82e5ad15f2c43de44294bc3ce3443af0fb320bba3898acede1a08").header("X-DreamFactory-Session-Token", inputVO.getSession()).get(ClientResponse.class);
			
			if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	    }else{
				String output = response.getEntity(String.class);
				if(output != null && !output.isEmpty()){
					JSONObject jsonObject = new JSONObject(output);
					if(jsonObject.has("meta")){
						JSONObject metaObject = jsonObject.getJSONObject("meta");
						if(metaObject.has("next")){
							nextCount = Long.valueOf(metaObject.getString("count"));
							JSONArray finalArray = jsonObject.getJSONArray("resource");
							List<WaterTanksClorinationVO> dataList = setDataToVO(finalArray);
							fnalList.addAll(dataList);
						}
					}else{
						JSONArray finalArray = jsonObject.getJSONArray("resource");
						List<WaterTanksClorinationVO> dataList = setDataToVO(finalArray);
						fnalList.addAll(dataList);
					}
				}	
	 	    }
			if(nextCount != null && nextCount.longValue() >0L){			
			   for(int i=1000;i<=nextCount;i=i+1000){
				offsetValue = Long.valueOf(i);
				url = "http://115.112.122.116/api/v2/vhop_add_on/_table/waterbody?filter=(visit_date%20%3E%3D%20"+inputVO.getFromDateStr()+")%20AND%20(visit_date%20%3C%3D%20"+inputVO.getToDateStr()+")&limit=0&offset="+offsetValue+"";
				webResource = commonMethodsUtilService.getWebResourceObject(url);
				response = webResource.accept("application/json").type("application/json").header("X-DreamFactory-Api-Key","f13c6aa6edc82e5ad15f2c43de44294bc3ce3443af0fb320bba3898acede1a08").header("X-DreamFactory-Session-Token", inputVO.getSession()).get(ClientResponse.class);
				
				if(response.getStatus() != 200){
		 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		 	    }else{
					String output = response.getEntity(String.class);
					if(output != null && !output.isEmpty()){
						JSONObject jsonObject = new JSONObject(output);
						JSONArray finalArray = jsonObject.getJSONArray("resource");
						List<WaterTanksClorinationVO> dataList = setDataToVO(finalArray);
						fnalList.addAll(dataList);
					}
				}	
		    }
		}
	 	    		
			if(fnalList != null && !fnalList.isEmpty()){
    			for (WaterTanksClorinationVO dataVO : fnalList) {
    				WaterTanksClorinationVO distvo = new WaterTanksClorinationVO();
    				distvo.setVisitDate(dataVO.getVisitDate());
					distvo.setDistrictId(dataVO.getDistrictId());
					distvo.setDistrictName(dataVO.getDistrictName());
					distvo.setAreaId(dataVO.getAreaId());
					distvo.setAreaName(dataVO.getAreaName());
					distvo.setServicePointId(dataVO.getServicePointId());
					distvo.setServicePointName(dataVO.getServicePointName());
					distvo.setVanId(dataVO.getVanId());
					distvo.setVanNo(dataVO.getVanNo());
					distvo.setNoOfSPs(1L);
					distvo.setChecked(dataVO.getChecked());
					distvo.setClorinated(dataVO.getClorinated());
					distvo.setNotClorinated(dataVO.getNotClorinated());
					//distvo.setVisitDate(jObj.getString("visit_date"));
					
					returnList.add(distvo);
    			}
    		}
	 	    
	 	 } catch (Exception e) {
			LOG.error("Exception raised at getLocationWiseClorinationDetails - NREGSTCSService service", e);
		}
		return returnList;
	}
	
	/*
	 * Date : 01/11/2017
	 * Author :Nandhini
	 * @description : getWaterBodyCumulativeCounts
	 */
	
	public WaterTanksClorinationVO getWaterBodyCumulativeCounts(InputVO inputVO){
		WaterTanksClorinationVO finaVO = new WaterTanksClorinationVO();
		try {
			List<WaterTanksClorinationVO> fnalList = new ArrayList<WaterTanksClorinationVO>(0);
			Map<String,Long> districtCuntMap = new HashMap<String,Long>(0);
			Map<String,Long> spsCuntMap = new HashMap<String,Long>(0);
			Long noOfDistricts = 0L;
			Long noOfSPs = 0L;
			JSONArray list = new JSONArray();
			String url = null;
			WebResource webResource = null;
			ClientResponse response = null;
			Long nextCount = 0L;
			Long offsetValue =0L;
			//JSONArray locationDtlsArr = null;
			
			//http://115.112.122.116/api/v2/vhop_add_on/_table/waterbody_state_wise?filter=(visit_date >= 2017-11-07) AND (visit_date <= 2017-11-14)& limit=0&offset=0
			url = "http://115.112.122.116/api/v2/vhop_add_on/_table/waterbody_state_wise?filter=(visit_date%20%3E%3D%20"+inputVO.getFromDateStr()+")%20AND%20(visit_date%20%3C%3D%20"+inputVO.getToDateStr()+")&limit=0&offset=0";
			webResource = commonMethodsUtilService.getWebResourceObject(url);
			response = webResource.accept("application/json").type("application/json").header("X-DreamFactory-Api-Key","f13c6aa6edc82e5ad15f2c43de44294bc3ce3443af0fb320bba3898acede1a08").header("X-DreamFactory-Session-Token", inputVO.getSession()).get(ClientResponse.class);
			
			if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	    }else{
				String output = response.getEntity(String.class);
				if(output != null && !output.isEmpty()){
					JSONObject jsonObject = new JSONObject(output);
					if(jsonObject.has("meta")){
						JSONObject metaObject = jsonObject.getJSONObject("meta");
						if(metaObject.has("next")){
							nextCount = Long.valueOf(metaObject.getString("count"));
							JSONArray finalArray = jsonObject.getJSONArray("resource");
							List<WaterTanksClorinationVO> dataList = setStateLevelDataToVO(finalArray);
							fnalList.addAll(dataList);
						}
					}else{
						JSONArray finalArray = jsonObject.getJSONArray("resource");
						List<WaterTanksClorinationVO> dataList = setStateLevelDataToVO(finalArray);
						fnalList.addAll(dataList);
					}
				}	
	 	    }
			
			if(nextCount != null && nextCount.longValue() >0L){
			  for(int i=1000;i<=nextCount;i=i+1000){
				offsetValue = Long.valueOf(i);
				url = "http://115.112.122.116/api/v2/vhop_add_on/_table/waterbody_state_wise?filter=(visit_date%20%3E%3D%20"+inputVO.getFromDateStr()+")%20AND%20(visit_date%20%3C%3D%20"+inputVO.getToDateStr()+")&limit=0&offset="+offsetValue+"";
				webResource = commonMethodsUtilService.getWebResourceObject(url);
				response = webResource.accept("application/json").type("application/json").header("X-DreamFactory-Api-Key","f13c6aa6edc82e5ad15f2c43de44294bc3ce3443af0fb320bba3898acede1a08").header("X-DreamFactory-Session-Token", inputVO.getSession()).get(ClientResponse.class);
				
				if(response.getStatus() != 200){
		 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		 	    }else{
					String output = response.getEntity(String.class);
					if(output != null && !output.isEmpty()){
						JSONObject jsonObject = new JSONObject(output);
						JSONArray finalArray = jsonObject.getJSONArray("resource");
						List<WaterTanksClorinationVO> dataList = setStateLevelDataToVO(finalArray);
						fnalList.addAll(dataList);
					}
				}	
		    }
		}
			
			if(fnalList != null && !fnalList.isEmpty()){
				for (WaterTanksClorinationVO dataVO : fnalList) {
					/*String districtName = dataVO.getDistrictName();//Districts Count
    				String spName = dataVO.getServicePointName();
    				Long distCunt = districtCuntMap.get(districtName);
    				if(distCunt == null){
    					noOfDistricts++;
    					districtCuntMap.put(districtName, noOfDistricts);
    				}
    				Long spCunt = spsCuntMap.get(spName);//SpS Count
    				if(spCunt == null){
    					noOfSPs++;
    					spsCuntMap.put(spName, noOfSPs);
    				}*/
    				finaVO.setChecked(finaVO.getChecked()+dataVO.getChecked());
    				finaVO.setClorinated(finaVO.getClorinated()+dataVO.getClorinated());
    				finaVO.setNotClorinated(finaVO.getNotClorinated()+dataVO.getNotClorinated());
    				finaVO.setNoOfSPs(finaVO.getNoOfSPs()+dataVO.getNoOfSPs());
				}
			}
		/*finaVO.setNoOfDistricts(noOfDistricts);
		finaVO.setNoOfSPs(noOfSPs);*/
	 	    
		} catch (Exception e) {
			LOG.error("Exception raised at getWaterBodyCumulativeCounts - NREGSTCSService service", e);
		}
		return finaVO;
	}
	
	/*
	 * Date : 2/11/2017
	 * Author :Nandhini
	 * @description : getNregaOtherMCCLevelData
	 */
	public List<NregsDataVO> getNregaOtherMCCLevelData(InputVO inputVO){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
			if(inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().toString().length() > 0l)
				inputVO.setSublocationType(inputVO.getSublocaType().trim());
			
			if(inputVO.getGroupName() != null && inputVO.getGroupName().trim().equalsIgnoreCase("Greenary works Chettu"))
				inputVO.setGroupName("Greenary works (Chettu)");
			else if(inputVO.getGroupName() != null && inputVO.getGroupName().trim().equalsIgnoreCase("Soil Moisture Conservation works Neeru"))
				inputVO.setGroupName("Soil Moisture Conservation works (Neeru)");
			else if(inputVO.getGroupName() != null && inputVO.getGroupName().trim().equalsIgnoreCase("Water Harvesting Structures Neeru"))
				inputVO.setGroupName("Water Harvesting Structures (Neeru)");
			
			String webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MCCOthersService/MCCOthersData";
			
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
	 	    				vo.setNotGrounded(jObj.getString("NOTGROUNDED"));
	 	    				vo.setInProgress(jObj.getLong("INPROGRESS"));
	 	    				vo.setCompleted(jObj.getLong("COMPLETED"));
		 	    			vo.setPercentage(jObj.getString("PERCENTAGE"));
		 	    			vo.setParameter("");
		 	    			voList.add(vo);
	 	    			}
	 	    		}
	 	    	}
	 	      }
	    } catch (Exception e) {
			LOG.error("Exception raised at getNregaOtherMCCLevelData - NREGSTCSService service", e);
		}
		
		return voList;
	}
	
	/*
	 * Date :02/11/2017
	 * Author :Nandhini
	 * @description : getNregaLevelsWiseDataFrCoffeePlantation
	 */
	public List<NregsDataVO> getNregaLevelsWiseDataFrCoffeePlantation(InputVO inputVO){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
			if(inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().toString().length() > 0l)
				inputVO.setSublocationType(inputVO.getSublocaType().trim());
			
			String webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CoffeeService/CoffeeData";
			
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
	 	    				vo.setTargetACRES(jObj.getString("TARGET_AREA"));
	 	    				vo.setPittingArea(jObj.getString("PIT_AREA"));
	 	    				vo.setPlantingArea(jObj.getString("PLANT_AREA"));
	 	    				vo.setPencentageOfPlanting(jObj.getString("PLANT_PERC"));
	 	    				vo.setTotalExpenditure(jObj.getString("TOTAL_EXP"));
	 	    				vo.setPittingExp(jObj.getString("PIT_PERC"));
	 	    				vo.setPlantingExp(jObj.getString("PLANT_EXP"));
	 	    				vo.setPitingPerc(jObj.getString("PIT_PERC"));
	 	    				
	 	    				voList.add(vo);
	 	    			}
	 	    		}
	 	    	}
	 	   }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaLevelsWiseDataFrCoffeePlantation - NREGSTCSService service", e);
		}
		
		return voList;
	}
	
	/*
	 * Date : 4/11/2017
	 * Author :Nandhini
	 * @description : getNregaMCCNewComponetsLevelData
	 */
	public List<NregsDataVO> getNregaMCCNewComponetsLevelData(InputVO inputVO){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
			if(inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().toString().length() > 0l)
				inputVO.setSublocationType(inputVO.getSublocaType().trim());
			
			if(inputVO.getGroupName().trim().toString().equalsIgnoreCase("Comprehensive Restoration of minor Irrigation Tank1")){
				inputVO.setGroupName("Comprehensive Restoration of minor Irrigation Tank");
			}
			
			String webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/AHOthersService/AHOthersData";
			
			if(inputVO.getGroupName() != null && inputVO.getGroupName().trim().equalsIgnoreCase("Comprehensive Restoration of minor Irrigation Tank1"))
				inputVO.setGroupName("Comprehensive Restoration of minor Irrigation Tank");
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
	 	    				vo.setNotGrounded(jObj.getString("NOTGROUNDED"));
	 	    				vo.setInProgress(jObj.getLong("INPROGRESS"));
	 	    				vo.setCompleted(jObj.getLong("COMPLETED"));
		 	    			vo.setPercentage(jObj.getString("PERCENTAGE"));
		 	    			vo.setParameter("");
		 	    			voList.add(vo);
	 	    			}
	 	    		}
	 	    	}
	 	      }
	    } catch (Exception e) {
			LOG.error("Exception raised at getNregaMCCNewComponetsLevelData - NREGSTCSService service", e);
		}
		
		return voList;
	}
	
	/*
	 * Date : 4/11/2017
	 * Author :Nandhini
	 * @description : getNregaCovergancePROtherLevelData
	 */
	public List<NregsDataVO> getNregaCovergancePROtherLevelData(InputVO inputVO){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
			if(inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().toString().length() > 0l)
				inputVO.setSublocationType(inputVO.getSublocaType().trim());
			
			if(inputVO.getGroupName() != null && inputVO.getGroupName().trim().equalsIgnoreCase("Construction of Post Harvest facilities Drying Platform including Pucca storage facilities of 100MT"))
				inputVO.setGroupName("Construction of Post Harvest facilities (Drying Platform) including Pucca storage facilities of 100MT");
			else if(inputVO.getGroupName() != null && inputVO.getGroupName().trim().equalsIgnoreCase("Renovation and Improvements to existing Check Dams Check Wall"))
				inputVO.setGroupName("Renovation and Improvements to existing Check Dams / Check Wall");
			else if(inputVO.getGroupName() != null && inputVO.getGroupName().trim().equalsIgnoreCase("Renovation and Improvements to existing Percolation Tank Mini Percolation tank"))
				inputVO.setGroupName("Renovation and Improvements to existing Percolation Tank / Mini Percolation tank");
			else if(inputVO.getGroupName() != null && inputVO.getGroupName().trim().equalsIgnoreCase("Construction of Crematoria Burial Grounds"))
				inputVO.setGroupName("Construction of Crematoria/Burial Grounds");
			else if(inputVO.getGroupName() != null && inputVO.getGroupName().trim().equalsIgnoreCase("Raising of Fodder Maize Fodder Jowar Nutrifeed Sugargraze"))
				inputVO.setGroupName("Raising of Fodder Maize/Fodder Jowar/Nutrifeed/Sugargraze");
			else if(inputVO.getGroupName() != null && inputVO.getGroupName().trim().equalsIgnoreCase("Fodder trough for Cattle Cattle drinking water trough"))
				inputVO.setGroupName("Fodder trough for Cattle/Cattle drinking water trough");
			
			String webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/DCCPRService/DCCPRData";
			
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
	 	    				vo.setNotGrounded(jObj.getString("NOTGROUNDED"));
	 	    				vo.setInProgress(jObj.getLong("INPROGRESS"));
	 	    				vo.setCompleted(jObj.getLong("COMPLETED"));
		 	    			vo.setPercentage(jObj.getString("PERCENTAGE"));
		 	    			vo.setParameter("");
		 	    			voList.add(vo);
	 	    			}
	 	    		}
	 	    	}
	 	      }
	        
	        if(inputVO.getLocationType() != null && (inputVO.getLocationType().trim().equalsIgnoreCase("district") || inputVO.getLocationType().trim().equalsIgnoreCase("constituency"))
					&& inputVO.getSector() != null && inputVO.getSector().trim().equalsIgnoreCase("abstract")){
	        	if(inputVO.getLocationType().trim().equalsIgnoreCase("constituency")){
	        		//district
		        	inputVO.setLocationType("district");
		        	inputVO.setSublocationType("district");
		        	inputVO.setLocationId(inputVO.getDistrictId());
		        	
		        	str = convertingInputVOToString(inputVO);
					
					response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
			        
			        if (response.getStatus() != 200) {
			 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			 	      } else {
			 	    	 String output = response.getEntity(String.class);
			 	    	 if (output != null && output.length() > 0) {
			 	    		 JSONArray finalArray = new JSONArray(output);	 
			 	    		 if (finalArray != null && finalArray.length() > 0) {
			 	    			  for (int i = 0 ;i < finalArray.length() ;i++) {
			 	    				  
			 	    				 NregsDataVO vo = new NregsDataVO();
			 	    				 JSONObject jsonObj = (JSONObject) finalArray.get(i);
			 	    				  
			 	    				vo.setPercentage(jsonObj.getString("PERCENTAGE"));
			 	    				vo.setType("DISTRICT");
			 	    				voList.get(0).getSubList().add(vo);
			 	    				  
			 	    			  }
			 	    		 }
			 	    	 }
			 	    	 
			 	      }
	        	}
	        	//state
	        	inputVO.setLocationType("state");
	        	inputVO.setSublocationType("state");
	        	inputVO.setLocationIdStr("1");
	        	
	        	str = convertingInputVOToString(inputVO);
				
				response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
		        
		        if (response.getStatus() != 200) {
		 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		 	      } else {
		 	    	 String output = response.getEntity(String.class);
		 	    	 if (output != null && output.length() > 0) {
		 	    		 JSONArray finalArray = new JSONArray(output);	 
		 	    		 if (finalArray != null && finalArray.length() > 0) {
		 	    			  for (int i = 0 ;i < finalArray.length() ;i++) {
		 	    				  
		 	    				 NregsDataVO vo = new NregsDataVO();
		 	    				 JSONObject jsonObj = (JSONObject) finalArray.get(i);
		 	    				  
		 	    				vo.setPercentage(jsonObj.getString("PERCENTAGE"));
		 	    				vo.setType("STATE");
		 	    				voList.get(0).getSubList().add(vo);
		 	    				  
		 	    			  }
		 	    		 }
		 	    	 }
		 	      }
	        }
	        
	    } catch (Exception e) {
			LOG.error("Exception raised at getNregaMCCNewComponetsLevelData - NREGSTCSService service", e);
		}
		
		return voList;
	}
	
	/*
	 * Date : 14/11/2017
	 * Author :Sravanth
	 * @description : getWorkWiseAbstractForMCCOthers
	 */
	public List<NregsDataVO> getWorkWiseAbstractForMCCOthers(InputVO inputVO){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
			if(inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().toString().length() > 0l)
				inputVO.setSublocationType(inputVO.getSublocaType().trim());
			
			if(inputVO.getGroupName() != null && inputVO.getGroupName().trim().equalsIgnoreCase("Greenary works Chettu"))
				inputVO.setGroupName("Greenary works (Chettu)");
			else if(inputVO.getGroupName() != null && inputVO.getGroupName().trim().equalsIgnoreCase("Soil Moisture Conservation works Neeru"))
				inputVO.setGroupName("Soil Moisture Conservation works (Neeru)");
			else if(inputVO.getGroupName() != null && inputVO.getGroupName().trim().equalsIgnoreCase("Water Harvesting Structures Neeru"))
				inputVO.setGroupName("Water Harvesting Structures (Neeru)");
			
			String webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MCCForestService/MCCForestAbstract";
			
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
	 	    				if(jObj.has("UNIQUE_ID"))
	 	    					vo.setUniqueId(Long.valueOf((jObj.getString("UNIQUE_ID").toString().trim().length() > 0 ? jObj.getString("UNIQUE_ID") : "1").toString()));
	 	    				else if(jObj.has("UNIQUEID"))
	 	    					vo.setUniqueId(Long.valueOf((jObj.getString("UNIQUEID").toString().trim().length() > 0 ? jObj.getString("UNIQUEID") : "1").toString()));
	 	    				vo.setWorkName(jObj.getString("WORK_NAME"));
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
		 	    			vo.setParameter("");
		 	    			voList.add(vo);
	 	    			}
	 	    		}
	 	    	}
	 	      }
	    } catch (Exception e) {
			LOG.error("Exception raised at getWorkWiseAbstractForMCCOthers - NREGSTCSService service", e);
		}
		
		return voList;
	}
	
	/*
	 * Date : 17/11/2017
	 * Author :Nandhini
	 * @description : getNregaOtherMCCAbstarctData
	 */
	public List<NregsDataVO> getNregaOtherMCCAbstarctData(InputVO inputVO){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
			if(inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().toString().length() > 0l)
				inputVO.setSublocationType(inputVO.getSublocaType().trim());
			
			if(inputVO.getGroupName() != null && inputVO.getGroupName().trim().equalsIgnoreCase("Greenary works Chettu"))
				inputVO.setGroupName("Greenary works (Chettu)");
			else if(inputVO.getGroupName() != null && inputVO.getGroupName().trim().equalsIgnoreCase("Soil Moisture Conservation works Neeru"))
				inputVO.setGroupName("Soil Moisture Conservation works (Neeru)");
			else if(inputVO.getGroupName() != null && inputVO.getGroupName().trim().equalsIgnoreCase("Water Harvesting Structures Neeru"))
				inputVO.setGroupName("Water Harvesting Structures (Neeru)");
			
			String webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MCCOthersNewService/MCCOthersAbstract";
			
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
	 	    				vo.setNotGrounded(jObj.getString("NOTGROUNDED"));
	 	    				vo.setInProgress(jObj.getLong("INPROGRESS"));
	 	    				vo.setCompleted(jObj.getLong("COMPLETED"));
		 	    			vo.setPercentage(jObj.getString("PERCENTAGE"));
		 	    			vo.setParameter("");
		 	    			voList.add(vo);
	 	    			}
	 	    		}
	 	    	}
	 	      }
	        
	        if(inputVO.getLocationType() != null && (inputVO.getLocationType().trim().equalsIgnoreCase("district") || inputVO.getLocationType().trim().equalsIgnoreCase("constituency"))
					&& inputVO.getSector() != null && inputVO.getSector().trim().equalsIgnoreCase("abstract")){
	        	if(inputVO.getLocationType().trim().equalsIgnoreCase("constituency")){
	        		//district
		        	inputVO.setLocationType("district");
		        	inputVO.setSublocationType("district");
		        	inputVO.setLocationId(inputVO.getDistrictId());
		        	
		        	str = convertingInputVOToString(inputVO);
					
					response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
			        
			        if (response.getStatus() != 200) {
			 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			 	      } else {
			 	    	 String output = response.getEntity(String.class);
			 	    	 if (output != null && output.length() > 0) {
			 	    		 JSONArray finalArray = new JSONArray(output);	 
			 	    		 if (finalArray != null && finalArray.length() > 0) {
			 	    			  for (int i = 0 ;i < finalArray.length() ;i++) {
			 	    				  
			 	    				 NregsDataVO vo = new NregsDataVO();
			 	    				 JSONObject jsonObj = (JSONObject) finalArray.get(i);
			 	    				  
			 	    				vo.setPercentage(jsonObj.getString("PERCENTAGE"));
			 	    				vo.setType("DISTRICT");
			 	    				voList.get(0).getSubList().add(vo);
			 	    				  
			 	    			  }
			 	    		 }
			 	    	 }
			 	    	 
			 	      }
	        	}
	        	//state
	        	inputVO.setLocationType("state");
	        	inputVO.setSublocationType("state");
	        	inputVO.setLocationIdStr("1");
	        	
	        	str = convertingInputVOToString(inputVO);
				
				response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
		        
		        if (response.getStatus() != 200) {
		 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		 	      } else {
		 	    	 String output = response.getEntity(String.class);
		 	    	 if (output != null && output.length() > 0) {
		 	    		 JSONArray finalArray = new JSONArray(output);	 
		 	    		 if (finalArray != null && finalArray.length() > 0) {
		 	    			  for (int i = 0 ;i < finalArray.length() ;i++) {
		 	    				  
		 	    				 NregsDataVO vo = new NregsDataVO();
		 	    				 JSONObject jsonObj = (JSONObject) finalArray.get(i);
		 	    				  
		 	    				vo.setPercentage(jsonObj.getString("PERCENTAGE"));
		 	    				vo.setType("STATE");
		 	    				voList.get(0).getSubList().add(vo);
		 	    				  
		 	    			  }
		 	    		 }
		 	    	 }
		 	      }
	        }
	        
	    } catch (Exception e) {
			LOG.error("Exception raised at getNregaOtherMCCAbstarctData - NREGSTCSService service", e);
		}
		
		return voList;
	}
	public List<WaterTanksClorinationVO> setDataToVO(JSONArray finalArray){
		List<WaterTanksClorinationVO> retrunList = new ArrayList<WaterTanksClorinationVO>(0);
		try{
			if(finalArray!=null && finalArray.length()>0){
	    		for(int i=0;i<finalArray.length();i++){
    				JSONObject jObj = (JSONObject) finalArray.get(i);
    				if(jObj != null && !jObj.getString("dist_id").equalsIgnoreCase("null") && jObj.getLong("dist_id") > 0L){
	    				WaterTanksClorinationVO vo = new WaterTanksClorinationVO();
	    				vo.setVisitDate(jObj.getString("visit_date"));
	    				vo.setDistrictId(jObj.getLong("dist_id"));
	    				vo.setDistrictName(jObj.getString("dist_name"));
	    				vo.setAreaId(jObj.getLong("area_id"));
	    				vo.setAreaName(jObj.getString("area_name"));
	    				vo.setServicePointId(jObj.getLong("sp_id"));
	 					vo.setServicePointName(jObj.getString("sp_name"));
	 					vo.setVanId(jObj.getLong("van_id"));
	 					vo.setVanNo(jObj.getString("van_no"));
	 					vo.setNoOfSPs(1L);
	 					vo.setChecked(jObj.getLong("wb_checked"));
	 					vo.setClorinated(jObj.getLong("wb_chlorinated"));
	 					vo.setNotClorinated(jObj.getLong("wb_nil_chlorine"));
	 					vo.setVisitDate(jObj.getString("visit_date"));
	 					retrunList.add(vo);
    					}
    				}
	    		}
		}catch (Exception e) {
			LOG.error("Exception raised at setDataToVO - NREGSTCSService service", e);
		}
		return retrunList;
	}
	
	public List<WaterTanksClorinationVO> setStateLevelDataToVO(JSONArray finalArray){
		List<WaterTanksClorinationVO> retrunList = new ArrayList<WaterTanksClorinationVO>(0);
		try{
			if(finalArray!=null && finalArray.length()>0){
	    		for(int i=0;i<finalArray.length();i++){
    				JSONObject jObj = (JSONObject) finalArray.get(i);
    				WaterTanksClorinationVO vo = new WaterTanksClorinationVO();
    				vo.setNoOfSPs(jObj.getLong("sp_count"));
 					vo.setChecked(jObj.getLong("wb_checked"));
 					vo.setClorinated(jObj.getLong("wb_chlorinated"));
 					vo.setNotClorinated(jObj.getLong("wb_nil_chlorine"));
 					vo.setVisitDate(jObj.getString("visit_date"));
 					retrunList.add(vo);
    			}
	    	}
		}catch (Exception e) {
			LOG.error("Exception raised at setStateLevelDataToVO - NREGSTCSService service", e);
		}
		return retrunList;
	}
	
	public List<WaterTanksClorinationVO> setDistrictLevelDataToVO(JSONArray finalArray){
		List<WaterTanksClorinationVO> retrunList = new ArrayList<WaterTanksClorinationVO>(0);
		try{
			if(finalArray!=null && finalArray.length()>0){
	    		for(int i=0;i<finalArray.length();i++){
    				JSONObject jObj = (JSONObject) finalArray.get(i);
    				if(jObj != null && !jObj.getString("dist_id").equalsIgnoreCase("null") && jObj.getLong("dist_id") > 0L){
    					WaterTanksClorinationVO vo = new WaterTanksClorinationVO();
        				vo.setDistrictId(jObj.getLong("dist_id"));
        				vo.setDistrictName(jObj.getString("dist_name"));
        				vo.setNoOfSPs(jObj.getLong("sp_count"));
     					vo.setChecked(jObj.getLong("wb_checked"));
     					vo.setClorinated(jObj.getLong("wb_chlorinated"));
     					vo.setNotClorinated(jObj.getLong("wb_nil_chlorine"));
     					vo.setVisitDate(jObj.getString("visit_date"));
     					retrunList.add(vo);
    				}
    			}
	    	}
		}catch (Exception e) {
			LOG.error("Exception raised at setDistrictLevelDataToVO - NREGSTCSService service", e);
		}
		return retrunList;
	}
	/*
	 * Date : 21/11/2017
	 * Author :Nandhini
	 * @description : getManWorkDaysOfNrega
	 */
	public List<NregsDataVO> getManWorkDaysOfNrega(InputVO inputVO){
		List<NregsDataVO> returnList = new ArrayList<NregsDataVO>(0);
		try{
			
			String webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/APMandaysService/APMandaysAbstract";
			
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
	 	    				vo.setUniqueId(Long.valueOf((jObj.getString("UNIQUE_ID").toString().trim().length() > 0 ? jObj.getString("UNIQUE_ID") : "1").toString()));
	 	    				vo.setDistrict(jObj.getString("DISTRICT"));
	 	    				vo.setConstituency(jObj.getString("CONSTITUENCY"));
	 	    				vo.setMandal(jObj.getString("MANDAL"));
	 	    				vo.setPanchayat(jObj.getString("PANCHAYAT"));
	 	    				vo.setThisMonth(jObj.getString("THIS_MONTH"));
	 	    				vo.setFinAsOfToday(jObj.getString("FIN_AS_OF_TODAY"));
	 	    				vo.setLastFin(jObj.getString("LAST_FIN"));
	 	    				vo.setLastFinSameDay(jObj.getString("LAST_FIN_SAMEDAY"));
	 	    				vo.setFrom2014(jObj.getString("FROM_2014"));
	 	    				if(vo.getFinAsOfToday() != null && Double.valueOf(vo.getFinAsOfToday()) >0 && vo.getLastFinSameDay() != null && Double.valueOf(vo.getLastFinSameDay()) > 0){
	 	    					vo.setAchivementPercentage(new BigDecimal((Double.valueOf(vo.getFinAsOfToday()) * 100.00)/ Double.valueOf(vo.getLastFinSameDay())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				}else{
	 	    					vo.setAchivementPercentage("0.00");
	 	    				}
	 	    				if(vo.getAchivementPercentage() != null && Double.valueOf(vo.getAchivementPercentage()) > 0)
	 	    					vo.setPercentage(new BigDecimal(Double.valueOf(vo.getAchivementPercentage())-Double.valueOf("100.00")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				else
	 	    					vo.setPercentage("0.00");
	 	    				returnList.add(vo);
	 	    			}
	 	    		}
	 	    	}
	 	      }
		}catch(Exception e){
			LOG.error("Exception raised at getManWorkDaysOfNrega - NREGSTCSService service", e);
		}
		return returnList;
	}
	
	public NregsDataVO getMatchedVoForUniqueCode(List<NregsDataVO> list,String uniqueCode){
		try{
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for (NregsDataVO nregaPaymentsVO : list) {
					if(nregaPaymentsVO.getUniqueCode().equals(uniqueCode)){
						return nregaPaymentsVO;
					}
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception raised at getMatchedVo - NREGSTCSService service", e);
		}
		return null;
	}
	public InputVO saveNregaComponentComments(Long componentComentId,Long statusId,String comment,String actionType,String uniqueCode){
		InputVO vo=new InputVO();
		try {
			NregaComponentComments 	componentComent =null;
			if(componentComentId != 0){
			 	componentComent = nregaComponentCommentsDAO.get(componentComentId);
			}
			NregaComponentStatus componentStatus=nregaComponentStatusDAO.get(statusId);
			NregaComponentComments coments =null;
			if(componentComent != null){
				componentComent.setIsDeleted("Y");
				coments = new NregaComponentComments();
					coments.setNregaComponentId(componentComent.getNregaComponentId());
					if(componentStatus != null){
					coments.setNregaComponentStatusId(componentStatus.getNregaComponentStatusId());
					}
					if(comment != null){
					coments.setComment(comment);
					}
					if(actionType != null){
					coments.setActionPlan(actionType);
					}
					if(uniqueCode != null){
					coments.setUniqueCode(uniqueCode);
					}
					coments.setInsertedBy(componentComent.getInsertedBy());
					coments.setUpdatedBy(componentComent.getUpdatedBy());
					coments.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					coments.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					coments.setIsDeleted("N");
			 }else{
				 coments = new NregaComponentComments();
				if(componentStatus != null){
				coments.setNregaComponentStatusId(componentStatus.getNregaComponentStatusId());
				}
				if(comment != null){
				coments.setComment(comment);
				}
				if(actionType != null){
				coments.setActionPlan(actionType);
				}
				if(uniqueCode != null){
				coments.setUniqueCode(uniqueCode);
				}
				coments.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				coments.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				coments.setIsDeleted("N");
			 }
				coments =nregaComponentCommentsDAO.save(coments);
			
			
			vo.setDisplayType("success");
			
		} catch (Exception e) {
			LOG.error("Exception raised at saveNregaComponentComments in KaizalaInfoService Class ", e);
			vo.setDisplayType("failure");
		}
		return vo;
	}
	public List<InputVO> getNregaComponentStatus(InputVO vo){
		List<InputVO> returnList= new ArrayList<InputVO>();
		try{
			List<Object[]> componentStatus= nregaComponentStatusDAO.getNregaComponentStatus();
			if(componentStatus != null && componentStatus.size()>0){
				for(Object[] param : componentStatus){
					InputVO inputVo = new InputVO();
					inputVo.setSourceId(commonMethodsUtilService.getLongValueForObject(param[0]));
					inputVo.setDisplayType(commonMethodsUtilService.getStringValueForObject(param[1]));
					returnList.add(inputVo);
				}
			}
			
			
		}catch(Exception e){
			LOG.error("Exception raised at getNregaComponentStatus in KaizalaInfoService Class ", e);
		}
		return returnList;
		
	}
	
	/*
	 * Date : 11/12/2017
	 * Author :Nandhini
	 * @description : getManWorkDaysOfNregaMonthWise
	 */
	public List<NregsDataVO> getManWorkDaysOfNregaMonthWise(InputVO inputVO){
		List<NregsDataVO> returnList = new ArrayList<NregsDataVO>(0);
		try{
			
			String webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/APMandaysServiceNew/APMandaysAbstractNew";
			
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
	 	    				vo.setUniqueId(Long.valueOf((jObj.getString("UNIQUE_ID").toString().trim().length() > 0 ? jObj.getString("UNIQUE_ID") : "1").toString()));
	 	    				vo.setDistrict(jObj.getString("DISTRICT"));
	 	    				vo.setConstituency(jObj.getString("CONSTITUENCY"));
	 	    				vo.setMandal(jObj.getString("MANDAL"));
	 	    				vo.setPanchayat(jObj.getString("PANCHAYAT"));
	 	    				vo.setThisMonth(jObj.getString("THIS_MONTH"));
	 	    				vo.setFinAsOfToday(jObj.getString("FIN_AS_OF_TODAY"));
	 	    				vo.setLastFin(jObj.getString("LAST_FIN"));
	 	    				vo.setLastFinSameDay(jObj.getString("LAST_FIN_SAMEDAY"));
	 	    				vo.setFrom2014(jObj.getString("FROM_2014"));
	 	    				if(vo.getFinAsOfToday() != null && Double.valueOf(vo.getFinAsOfToday()) >0 && vo.getLastFinSameDay() != null && Double.valueOf(vo.getLastFinSameDay()) > 0){
	 	    					vo.setAchivementPercentage(new BigDecimal((Double.valueOf(vo.getFinAsOfToday()) * 100.00)/ Double.valueOf(vo.getLastFinSameDay())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				}else{
	 	    					vo.setAchivementPercentage("0.00");
	 	    				}
	 	    				if(vo.getAchivementPercentage() != null && Double.valueOf(vo.getAchivementPercentage()) > 0)
	 	    					vo.setPercentage(new BigDecimal(Double.valueOf(vo.getAchivementPercentage()) - Double.valueOf("100.00")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				else
	 	    					vo.setPercentage("0.00");
	 	    				if (vo.getPercentage() != null && vo.getPercentage().contains("-")) {
								vo.setParameter("Decrement");
								String[] Arr = vo.getPercentage().split("-");
								vo.setPercentage(Arr[1]);
							} else {
								vo.setParameter("Increment");
							}
	 	    				returnList.add(vo);
	 	    			}
	 	    		}
	 	    	}
	 	      }
	        
	        if(inputVO.getLocationType() != null && (inputVO.getLocationType().trim().equalsIgnoreCase("district") || inputVO.getLocationType().trim().equalsIgnoreCase("constituency"))
					&& inputVO.getSector() != null && inputVO.getSector().trim().equalsIgnoreCase("abstract")){
	        	if(inputVO.getLocationType().trim().equalsIgnoreCase("constituency")){
	        		//district
		        	inputVO.setLocationType("district");
		        	inputVO.setSublocationType("district");
		        	inputVO.setLocationId(inputVO.getDistrictId());
		        	
		        	str = convertingInputVOToString(inputVO);
					
					response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
			        
			        if (response.getStatus() != 200) {
			 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			 	      } else {
			 	    	 String output = response.getEntity(String.class);
			 	    	 if (output != null && output.length() > 0) {
			 	    		 JSONArray finalArray = new JSONArray(output);	 
			 	    		 if (finalArray != null && finalArray.length() > 0) {
			 	    			  for (int i = 0 ;i < finalArray.length() ;i++) {
			 	    				  
			 	    				 NregsDataVO vo = new NregsDataVO();
			 	    				 JSONObject jObj = (JSONObject) finalArray.get(i);
			 	    				  
			 	    				 vo.setFinAsOfToday(jObj.getString("FIN_AS_OF_TODAY"));
			 	    				 vo.setLastFinSameDay(jObj.getString("LAST_FIN_SAMEDAY"));
			 	    				 vo.setType("district");
			 	    				 vo.setAchivementPercentage(new BigDecimal((Double.valueOf(vo.getFinAsOfToday()) * 100.00)/ Double.valueOf(vo.getLastFinSameDay())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			 	    				 vo.setPercentage(new BigDecimal(Double.valueOf(vo.getAchivementPercentage()) - Double.valueOf("100.00")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			 	    				 if (vo.getPercentage() != null && vo.getPercentage().contains("-")) {
			 	    					 vo.setParameter("Decrement");
			 	    					String[] Arr = vo.getPercentage().split("-");
										vo.setPercentage(Arr[1]);
			 	    				 } else {
			 	    					 vo.setParameter("Increment");
			 	    				 }
			 	    				 returnList.get(0).getSubList().add(vo);
			 	    				  
			 	    			  }
			 	    		 }
			 	    	 }
			 	    	 
			 	      }
	        	}
	        	//state
	        	inputVO.setLocationType("state");
	        	inputVO.setSublocationType("state");
	        	inputVO.setLocationIdStr("1");
	        	
	        	str = convertingInputVOToString(inputVO);
				
				response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
		        
		        if (response.getStatus() != 200) {
		 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		 	      } else {
		 	    	 String output = response.getEntity(String.class);
		 	    	 if (output != null && output.length() > 0) {
		 	    		 JSONArray finalArray = new JSONArray(output);	 
		 	    		 if (finalArray != null && finalArray.length() > 0) {
		 	    			  for (int i = 0 ;i < finalArray.length() ;i++) {
		 	    				  
		 	    				 NregsDataVO vo = new NregsDataVO();
		 	    				 JSONObject jObj = (JSONObject) finalArray.get(i);
		 	    				  
		 	    				 vo.setFinAsOfToday(jObj.getString("FIN_AS_OF_TODAY"));
		 	    				 vo.setLastFinSameDay(jObj.getString("LAST_FIN_SAMEDAY"));
		 	    				 vo.setType("state");
		 	    				 vo.setAchivementPercentage(new BigDecimal((Double.valueOf(vo.getFinAsOfToday()) * 100.00)/ Double.valueOf(vo.getLastFinSameDay())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    				 vo.setPercentage(new BigDecimal(Double.valueOf(vo.getAchivementPercentage()) - Double.valueOf("100.00")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    				 if (vo.getPercentage() != null && vo.getPercentage().contains("-")) {
		 	    					 vo.setParameter("Decrement");
		 	    					String[] Arr = vo.getPercentage().split("-");
									vo.setPercentage(Arr[1]);
		 	    				 } else {
		 	    					 vo.setParameter("Increment");
		 	    				 }
		 	    				 returnList.get(0).getSubList().add(vo);
		 	    				  
		 	    			  }
		 	    		 }
		 	    	 }
		 	      }
	        }
	        
		}catch(Exception e){
			LOG.error("Exception raised at getManWorkDaysOfNregaMonthWise - NREGSTCSService service", e);
		}
		return returnList;
	}
	
	/*
	 * Date : 13/12/2017 
	 * Author :Nandhini
	 * @description : getManWorksExpenditureDetails
	 */
	public List<NregsDataVO> getManWorksExpenditureDetails(InputVO inputVO) {
		List<NregsDataVO> returnList = new ArrayList<NregsDataVO>(0);
		try {

			String webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/APYearWiseService/APYearWiseData";

			String str = convertingInputVOToString(inputVO);

			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			} else {
				String output = response.getEntity(String.class);

				if (output != null && !output.isEmpty()) {
					JSONArray finalArray = new JSONArray(output);
					if (finalArray != null && finalArray.length() > 0) {
						for (int i = 0; i < finalArray.length(); i++) {
							NregsDataVO vo = new NregsDataVO();
							JSONObject jObj = (JSONObject) finalArray.get(i);
							vo.setUniqueId(Long.valueOf((jObj.getString("UNIQUE_ID").toString().trim().length() > 0 ? jObj.getString("UNIQUE_ID") : "1").toString()));
							vo.setDistrict(jObj.getString("DISTRICT"));
							vo.setConstituency(jObj.getString("CONSTITUENCY"));
							vo.setMandal(jObj.getString("MANDAL"));
							vo.setPanchayat(jObj.getString("PANCHAYAT"));
							vo.setThisMonth(jObj.getString("MONTH"));
							vo.setPerDays1516(convertRupeesIntoLakhesFrDoubleValue(jObj.getString("PER_DAYS_1516")));
							vo.setWageExp1516(convertRupeesIntoLakhesFrDoubleValue(jObj.getString("WAGE_EXP_1516")));
							vo.setMatExp1516(convertRupeesIntoLakhesFrDoubleValue(jObj.getString("MAT_EXP_1516")));
							vo.setTotal1516(convertRupeesIntoLakhesFrDoubleValue(jObj.getString("TOT_1516")));
							vo.setPerDays1617(convertRupeesIntoLakhesFrDoubleValue(jObj.getString("PER_DAYS_1617")));
							vo.setWageExp1617(convertRupeesIntoLakhesFrDoubleValue(jObj.getString("WAGE_EXP_1617")));
							vo.setMatExp1617(convertRupeesIntoLakhesFrDoubleValue(jObj.getString("MAT_EXP_1617")));
							vo.setTotal1617(convertRupeesIntoLakhesFrDoubleValue(jObj.getString("TOT_1617")));
							vo.setPerDays1718(convertRupeesIntoLakhesFrDoubleValue(jObj.getString("PER_DAYS_1718")));
							vo.setWageExp1718(convertRupeesIntoLakhesFrDoubleValue(jObj.getString("WAGE_EXP_1718")));
							vo.setMatExp1718(convertRupeesIntoLakhesFrDoubleValue(jObj.getString("MAT_EXP_1718")));
							vo.setTotal1718(convertRupeesIntoLakhesFrDoubleValue(jObj.getString("TOT_1718")));
							/*vo.setAchivementPercentage(new BigDecimal((Double.valueOf(vo.getTotal1718()) * 100.00) / Double.valueOf(vo.getTotal1617())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
							vo.setPercentage(new BigDecimal(Double.valueOf(vo.getAchivementPercentage()) - Double.valueOf("100.00")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());*/
							returnList.add(vo);
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getManWorksExpenditureDetails - NREGSTCSService service", e);
		}
		return returnList;
	}

	/*
	 * Date : 13/12/2017 
	 * Author :Nandhini
	 * @description : getManWorksExpenditureAbstarct
	 */
	public List<NregsDataVO> getManWorksExpenditureAbstarct(InputVO inputVO) {
		List<NregsDataVO> returnList = new ArrayList<NregsDataVO>(0);
		try {

			String webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/APYearWiseService/APYearWiseData";

			String str = convertingInputVOToString(inputVO);

			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			} else {
				String output = response.getEntity(String.class);

				if (output != null && !output.isEmpty()) {
					JSONArray finalArray = new JSONArray(output);
					if (finalArray != null && finalArray.length() > 0) {
						for (int i = 0; i < finalArray.length(); i++) {
							NregsDataVO vo = new NregsDataVO();
							JSONObject jObj = (JSONObject) finalArray.get(i);
							if (inputVO.getMonthType() != null
									&& inputVO.getMonthType().trim().equalsIgnoreCase(jObj.getString("MONTH"))) {
								vo.setUniqueId(Long.valueOf((jObj.getString("UNIQUE_ID").toString().trim().length() > 0 ? jObj.getString("UNIQUE_ID") : "1").toString()));
								// vo.setMatExp1617(jObj.getLong("MAT_EXP_1617"));
								vo.setTotal1617(convertRupeesIntoLakhesFrDoubleValue(jObj.getString("TOT_1617")));
								vo.setWageExp1718(convertRupeesIntoLakhesFrDoubleValue(jObj.getString("WAGE_EXP_1718")));
								vo.setMatExp1718(convertRupeesIntoLakhesFrDoubleValue(jObj.getString("MAT_EXP_1718")));
								vo.setTotal1718(convertRupeesIntoLakhesFrDoubleValue(jObj.getString("TOT_1718")));
								vo.setAchivementPercentage(new BigDecimal((Double.valueOf(vo.getTotal1718()) * 100.00)/ Double.valueOf(vo.getTotal1617())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
								vo.setPercentage(new BigDecimal(Double.valueOf(vo.getAchivementPercentage()) - Double.valueOf("100.00")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
								if (vo.getPercentage() != null && vo.getPercentage().contains("-")) {
									vo.setParameter("Decrement");
									String[] Arr = vo.getPercentage().split("-");
									vo.setPercentage(Arr[1]);
								} else {
									vo.setParameter("Increment");
									vo.setPercentage(vo.getPercentage());
								}
								returnList.add(vo);
							}
						}
					}
				}
			}
			
			if(inputVO.getLocationType() != null && (inputVO.getLocationType().trim().equalsIgnoreCase("district") || inputVO.getLocationType().trim().equalsIgnoreCase("constituency"))
					&& inputVO.getSector() != null && inputVO.getSector().trim().equalsIgnoreCase("abstract")){
	        	if(inputVO.getLocationType().trim().equalsIgnoreCase("constituency")){
	        		//district
		        	inputVO.setLocationType("district");
		        	inputVO.setSublocationType("district");
		        	inputVO.setLocationId(inputVO.getDistrictId());
		        	
		        	str = convertingInputVOToString(inputVO);
					
					response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
			        
			        if (response.getStatus() != 200) {
			 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			 	      } else {
			 	    	 String output = response.getEntity(String.class);
			 	    	 if (output != null && output.length() > 0) {
			 	    		 JSONArray finalArray = new JSONArray(output);	 
			 	    		 if (finalArray != null && finalArray.length() > 0) {
			 	    			  for (int i = 0 ;i < finalArray.length() ;i++) {
			 	    				  
			 	    				 NregsDataVO vo = new NregsDataVO();
			 	    				 JSONObject jObj = (JSONObject) finalArray.get(i);
			 	    				if (inputVO.getMonthType() != null
											&& inputVO.getMonthType().trim().equalsIgnoreCase(jObj.getString("MONTH"))) {
			 	    					vo.setTotal1617(convertRupeesIntoLakhesFrDoubleValue(jObj.getString("TOT_1617")));
				 	    				 vo.setTotal1718(convertRupeesIntoLakhesFrDoubleValue(jObj.getString("TOT_1718")));
				 	    				 vo.setType("district");
				 	    				 vo.setAchivementPercentage(new BigDecimal((Double.valueOf(vo.getTotal1718()) * 100.00)/ Double.valueOf(vo.getTotal1617())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				 	    				 vo.setPercentage(new BigDecimal(Double.valueOf(vo.getAchivementPercentage()) - Double.valueOf("100.00")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				 	    				 if (vo.getPercentage() != null && vo.getPercentage().contains("-")) {
				 	    					 vo.setParameter("Decrement");
				 	    					String[] Arr = vo.getPercentage().split("-");
				 	    					vo.setPercentage(Arr[1]);
				 	    				 } else {
				 	    					 vo.setParameter("Increment");
				 	    					 vo.setPercentage(vo.getPercentage());
				 	    				 }
				 	    				 returnList.get(0).getSubList().add(vo);
			 	    				}
			 	    			}
			 	    		 }
			 	    	 }
			 	    	 
			 	      }
	        	}
	        	//state
	        	inputVO.setLocationType("state");
	        	inputVO.setSublocationType("state");
	        	inputVO.setLocationIdStr("1");
	        	
	        	str = convertingInputVOToString(inputVO);
				
				response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
		        
		        if (response.getStatus() != 200) {
		 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		 	      } else {
		 	    	 String output = response.getEntity(String.class);
		 	    	 if (output != null && output.length() > 0) {
		 	    		 JSONArray finalArray = new JSONArray(output);	 
		 	    		 if (finalArray != null && finalArray.length() > 0) {
		 	    			  for (int i = 0 ;i < finalArray.length() ;i++) {
		 	    				  
		 	    				 NregsDataVO vo = new NregsDataVO();
		 	    				 JSONObject jObj = (JSONObject) finalArray.get(i);
		 	    				if (inputVO.getMonthType() != null
										&& inputVO.getMonthType().trim().equalsIgnoreCase(jObj.getString("MONTH"))) {
		 	    					vo.setTotal1617(convertRupeesIntoLakhesFrDoubleValue(jObj.getString("TOT_1617")));
			 	    				 vo.setTotal1718(convertRupeesIntoLakhesFrDoubleValue(jObj.getString("TOT_1718")));
			 	    				 vo.setType("state");
			 	    				 vo.setAchivementPercentage(new BigDecimal((Double.valueOf(vo.getTotal1718()) * 100.00)/ Double.valueOf(vo.getTotal1617())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			 	    				 vo.setPercentage(new BigDecimal(Double.valueOf(vo.getAchivementPercentage()) - Double.valueOf("100.00")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			 	    				 if (vo.getPercentage() != null && vo.getPercentage().contains("-")) {
			 	    					 vo.setParameter("Decrement");
			 	    					 String[] Arr = vo.getPercentage().split("-");
			 	    					 vo.setPercentage(Arr[1]);
			 	    				 } else {
			 	    					 vo.setParameter("Increment");
			 	    					 vo.setPercentage(vo.getPercentage());
			 	    				 }
			 	    				 returnList.get(0).getSubList().add(vo);
		 	    				}
		 	    			}
		 	    		 }
		 	    	 }
		 	      }
	        }
			
		} catch (Exception e) {
			LOG.error("Exception raised at getManWorksExpenditureDetails - NREGSTCSService service", e);
		}
		return returnList;
	}

	/*
	 * Date : 13/12/2017
	 *  Author :Nandhini
	 * @description : getAllDistricts
	 */
	public List<IdNameVO> getAllDistricts() {
		List<IdNameVO> finalList = new ArrayList<IdNameVO>();
		try {
			List<Object[]> districtList = prDistrictDAO.getAllDistrictsFrState();
			if (commonMethodsUtilService.isListOrSetValid(districtList)) {
				for (Object[] param : districtList) {
					IdNameVO locationVO = new IdNameVO();
					locationVO.setLocationIdStr(commonMethodsUtilService.getStringValueForObject(param[0]));
					locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
					finalList.add(locationVO);
				}
			}

		} catch (Exception e) {
			LOG.error("Exception raised at getAllDistricts - NREGSTCSService service", e);
		}
		return finalList;
	}

	/*
	 * Date : 13/12/2017 
	 * Author :Nandhini
	 * @description : getConstituiences For District
	 */
	public List<IdNameVO> getAllConstituiencesFrDistrict(InputVO inputVO) {
		List<IdNameVO> finalList = new ArrayList<IdNameVO>();
		try {
			List<Object[]> constitueicesList = prConstituencyDAO.getAllConstutiensFrDistrict(inputVO.getLocationIdStr());
			if (commonMethodsUtilService.isListOrSetValid(constitueicesList)) {
				for (Object[] param : constitueicesList) {
					IdNameVO locationVO = new IdNameVO();
					locationVO.setLocationIdStr(commonMethodsUtilService.getStringValueForObject(param[0]));
					locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
					finalList.add(locationVO);
				}
			}

		} catch (Exception e) {
			LOG.error("Exception raised at getAllConstituiencesFrDistrict - NREGSTCSService service", e);
		}
		return finalList;
	}

	/*
	 * Date : 13/12/2017
	 *  Author :Nandhini
	 * @description : getTehsiles For Constituency
	 */
	public List<IdNameVO> getTehsilesFrConstituency(InputVO inputVO) {
		List<IdNameVO> finalList = new ArrayList<IdNameVO>();
		try {
			List<Object[]> tehsilList = prTehsilDAO.getTehsilFrConstituency(inputVO.getLocationIdStr());
			if (commonMethodsUtilService.isListOrSetValid(tehsilList)) {
				for (Object[] param : tehsilList) {
					IdNameVO locationVO = new IdNameVO();
					locationVO.setLocationIdStr(commonMethodsUtilService.getStringValueForObject(param[0]));
					locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
					finalList.add(locationVO);
				}
			}

		} catch (Exception e) {
			LOG.error("Exception raised at getTehsilesFrConstituency - NREGSTCSService service", e);
		}
		return finalList;
	}

	/*
	 * Date : 13/12/2017 
	 * Author :Nandhini
	 * @description : getPanchayts For Tehsil
	 */
	public List<IdNameVO> getPanchayatsFrTehsil(InputVO inputVO) {
		List<IdNameVO> finalList = new ArrayList<IdNameVO>();
		try {
			List<Object[]> panchayatList = prPanchayatDAO.getpanchayatsFrTehsil(inputVO.getLocationIdStr());
			if (commonMethodsUtilService.isListOrSetValid(panchayatList)) {
				for (Object[] param : panchayatList) {
					IdNameVO locationVO = new IdNameVO();
					locationVO.setLocationIdStr(commonMethodsUtilService.getStringValueForObject(param[0]));
					locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
					finalList.add(locationVO);
				}
			}

		} catch (Exception e) {
			LOG.error("Exception raised at getPanchayatsFrTehsil - NREGSTCSService service", e);
		}
		return finalList;
	}
	
	/*
	 * Date : 15/122017
	 * Author :Nandhini
	 * @description : getPanchayatsExpenditure
	 */
	public List<IdNameVO> getPanchayatsExpenditure(InputVO inputVO,String locationId,Long levelId){
		List<IdNameVO> voList = new ArrayList<IdNameVO>(0);
		try {
			
			if(levelId != null && levelId.longValue() > 0L && levelId.longValue() == 1L){
				inputVO.setLocationType("state");
			}else if(levelId != null && levelId.longValue() > 0L && levelId.longValue() == 2L){
				inputVO.setLocationType("district");
			}else if(levelId != null && levelId.longValue() > 0L && levelId.longValue() == 3L){
				inputVO.setLocationType("constituency");
			}
			if(locationId != null){
				inputVO.setLocationIdStr(locationId);
			}
			
			String str = convertingInputVOToString(inputVO);
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/APLabourBugetServiceNew/APLabourBdgtExpenditureNew", str);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
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
			LOG.error("Exception raised at getPanchayatsExpenditure - NREGSTCSService service", e);
		}
		
		return voList;
	}
	
	/*
	 * Date : 15/12/2017
	 * Author :Nandhini
	 * @description : getPanchatVsExpData
	 */
	public List<NregsDataVO> getPanchatVsExpData(InputVO inputVO,String locationId,Long levelId){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
			List<String> uniqueCodeStr=new ArrayList<String>();
			if(levelId != null && levelId.longValue() > 0L && levelId.longValue() == 1L){
				inputVO.setLocationType("state");
			}else if(levelId != null && levelId.longValue() > 0L && levelId.longValue() == 2L){
				inputVO.setLocationType("district");
			}else if(levelId != null && levelId.longValue() > 0L && levelId.longValue() == 3L){
				inputVO.setLocationType("constituency");
			}
			if(locationId != null){
				inputVO.setLocationIdStr(locationId);
			}
			
				String[] rangeArr = {"0-0","0-1","1-5","5-10","10-20"};
				if(rangeArr != null){
					for (String string : rangeArr) {
						String[] rangeValues = string.split("-");
						inputVO.setFromRange(Long.valueOf(rangeValues[0]));
						inputVO.setToRange(Long.valueOf(rangeValues[1]));
						
						String str = convertingInputVOToString(inputVO); 
						ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/APLabourBudgetPanchayats/APLabourBdgtPanchayats", str);
				        
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
				 	    				vo.setUniqueCode(jObj.getString("UNIQUEID"));
				 	    				if(Long.valueOf(rangeValues[1]) != null && Long.valueOf(rangeValues[1]).longValue() == 0L){
				 	    					vo.setRange("0");
				 	    				}else if(Long.valueOf(rangeValues[1]) != null && Long.valueOf(rangeValues[1]).longValue() == 1L){
				 	    					vo.setRange("Below 1");
				 	    				}else if(Long.valueOf(rangeValues[1]) != null && Long.valueOf(rangeValues[1]).longValue() == 5L){
				 	    					vo.setRange("1-5");
				 	    				}else if(Long.valueOf(rangeValues[1]) != null && Long.valueOf(rangeValues[1]).longValue() == 10L){
				 	    					vo.setRange("5-10");
				 	    				}else if(Long.valueOf(rangeValues[1]) != null && Long.valueOf(rangeValues[1]).longValue() == 20L){
				 	    					vo.setRange("10-20");
				 	    				}
				 	    				uniqueCodeStr.add(vo.getUniqueCode());
				 	    				voList.add(vo);
				 	    				
				 	    			}
				 	    		}
				 	    	}
				 	      }
						}
					}
	        
	        List<Object[]> nregaComments= nregaComponentCommentsDAO.getNregaComponentComments(uniqueCodeStr);
				if(nregaComments != null && nregaComments.size()>0){
					for(Object[] param : nregaComments){
						NregsDataVO matchedVo= getMatchedVoForUniqueCode(voList,commonMethodsUtilService.getStringValueForObject(param[3]));
						if(matchedVo != null){
							matchedVo.setStatus(commonMethodsUtilService.getStringValueForObject(param[0]));
							matchedVo.setComments(commonMethodsUtilService.getStringValueForObject(param[1]));
							matchedVo.setActionPlan(commonMethodsUtilService.getStringValueForObject(param[2]));
							matchedVo.setStatusId(commonMethodsUtilService.getLongValueForObject(param[4]));
							matchedVo.setComponentId(commonMethodsUtilService.getLongValueForObject(param[5]));
						}
					}
				}
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaPanchatVsExpData - NREGSTCSService service", e);
		}
		
		return voList;
	}
	
	public String convertRupeesIntoLakhesFrDoubleValue(String value){
		String returnVal = null;
		try {
			if(value != null){
				returnVal = new BigDecimal(Double.valueOf(value)/100000.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				returnVal = returnVal;
			}
		} catch (Exception e) {
			LOG.error("Exception raised at convertRupeesIntoLakhesFrDoubleValue - NREGSTCSService service", e);
		}
		return returnVal;
	}
	/*
	 * Date : 16/12/2017
	 * Author :Nandhini
	 * @description : savePanchayatComponentComments
	 */
	public InputVO savePanchayatComponentComments(Long componentComentId,Long statusId,String comment,String actionType,String uniqueCode,Long userId){
		InputVO vo=new InputVO();
		try {
			NregaComponentComments 	componentComent =null;
			if(componentComentId != 0){
			 	componentComent = nregaComponentCommentsDAO.get(componentComentId);
			}
			NregaComponentStatus componentStatus=nregaComponentStatusDAO.get(statusId);
			//If Record Is There We Mve that Into History
			if(componentComent != null){
				NregaComponentCommentsHistory model = new NregaComponentCommentsHistory();
				if(componentComent.getNregaComponentId() != null){
					model.setNregaComponentId(componentComent.getNregaComponentId());
				}else{
					model.setNregaComponentId(1L);
				}
					
					model.setNregaComponentStatusId(componentComent.getNregaComponentStatusId());
					model.setComment(componentComent.getComment());
					model.setActionPlan(componentComent.getActionPlan());
				    model.setUniqueCode(componentComent.getUniqueCode());
					model.setInsertedBy(componentComent.getInsertedBy());
					model.setUpdatedBy(componentComent.getUpdatedBy());
					model.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					model.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					model.setIsDeleted(componentComent.getIsDeleted());
					model.setNregaComponentCommentsId(componentComentId);
					nregaComponentCommentsHistoryDAO.save(model);
					
					//Update In Main Table
					if(componentStatus != null){
						componentComent.setNregaComponentStatusId(componentStatus.getNregaComponentStatusId());
					}
					if(comment != null){
						componentComent.setComment(comment);
					}
					if(actionType != null){
						componentComent.setActionPlan(actionType);
					}
					if(uniqueCode != null){
						componentComent.setUniqueCode(uniqueCode);
					}
					componentComent.setNregaComponentId(1L);
					componentComent.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					componentComent.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					componentComent.setUpdatedBy(userId);
					componentComent.setInsertedBy(userId);
					componentComent.setIsDeleted("N");
			 }else{
				 componentComent = new NregaComponentComments();
				 if(componentStatus != null){
						componentComent.setNregaComponentStatusId(componentStatus.getNregaComponentStatusId());
					}
					if(comment != null){
						componentComent.setComment(comment);
					}
					if(actionType != null){
						componentComent.setActionPlan(actionType);
					}
					if(uniqueCode != null){
						componentComent.setUniqueCode(uniqueCode);
					}
					componentComent.setNregaComponentId(1L);
					componentComent.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					componentComent.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					componentComent.setUpdatedBy(userId);
					componentComent.setInsertedBy(userId);
					componentComent.setIsDeleted("N");
			 	}
				componentComent = nregaComponentCommentsDAO.save(componentComent);
			
			vo.setDisplayType("success");
			
		} catch (Exception e) {
			LOG.error("Exception raised at savePanchayatComponentComments() -  NREGSTCSService Class ", e);
			vo.setDisplayType("failure");
		}
		return vo;
	}
	
	/*
	 * Date : 18/12/2017 
	 * Author :Nandhini
	 * @description : getFieldManDaysWorkDetails
	 */
	public List<NregsDataVO> getFieldManDaysWorkDetails(InputVO inputVO) {
		List<NregsDataVO> returnList = new ArrayList<NregsDataVO>(0);
		try {

			String webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/APMandaysAnalysisService/APMandaysAnalysis";

			String str = convertingInputVOToString(inputVO);

			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			} else {
				String output = response.getEntity(String.class);

				if (output != null && !output.isEmpty()) {
					JSONArray finalArray = new JSONArray(output);
					if (finalArray != null && finalArray.length() > 0) {
						for (int i = 0; i < finalArray.length(); i++) {
							NregsDataVO vo = new NregsDataVO();
							JSONObject jObj = (JSONObject) finalArray.get(i);
							vo.setUniqueId(Long.valueOf((jObj.getString("UNIQUE_ID").toString().trim().length() > 0 ? jObj.getString("UNIQUE_ID") : "1").toString()));
							vo.setDistrict(jObj.getString("DISTRICT"));
							vo.setConstituency(jObj.getString("CONSTITUENCY"));
							vo.setMandal(jObj.getString("MANDAL"));
							vo.setPanchayat(jObj.getString("PANCHAYAT"));
							vo.setToday(jObj.getString("TODAY"));
							vo.setYesterday(jObj.getString("YESTERDAY"));
							vo.setThisWeek(jObj.getString("FOR_THIS_WEEK"));
							vo.setLastWeek(jObj.getString("FOR_LAST_WEEK"));
							vo.setThisMonth(jObj.getString("THIS_MONTH"));
							vo.setLastMonth(jObj.getString("LAST_MONTH"));
							vo.setLast3Months(jObj.getString("LAST_3_MONTHS"));
							vo.setLast6Months(jObj.getString("LAST_6_MONTHS"));
							vo.setThisFinYear(jObj.getString("THIS_FIN_YEAR"));
							returnList.add(vo);
						}
					}
				}
			}
			
			if(inputVO.getLocationType() != null && (inputVO.getLocationType().trim().equalsIgnoreCase("district") || inputVO.getLocationType().trim().equalsIgnoreCase("constituency"))
					&& inputVO.getSector() != null && inputVO.getSector().trim().equalsIgnoreCase("abstract")){
	        	if(inputVO.getLocationType().trim().equalsIgnoreCase("constituency")){
	        		//district
		        	inputVO.setLocationType("district");
		        	inputVO.setSublocationType("district");
		        	inputVO.setLocationId(inputVO.getDistrictId());
		        	
		        	str = convertingInputVOToString(inputVO);
					
					response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
			        
			        if (response.getStatus() != 200) {
			 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			 	      } else {
			 	    	 String output = response.getEntity(String.class);
			 	    	 if (output != null && output.length() > 0) {
			 	    		 JSONArray finalArray = new JSONArray(output);	 
			 	    		 if (finalArray != null && finalArray.length() > 0) {
			 	    			  for (int i = 0 ;i < finalArray.length() ;i++) {
			 	    				  
			 	    				 NregsDataVO vo = new NregsDataVO();
			 	    				 JSONObject jObj = (JSONObject) finalArray.get(i);
			 	    				  
			 	    				 vo.setType("district");
			 	    				 vo.setToday(jObj.getString("TODAY"));
			 	    				 returnList.get(0).getSubList().add(vo);
			 	    				  
			 	    			  }
			 	    		 }
			 	    	 }
			 	    	 
			 	      }
	        	}
	        	//state
	        	inputVO.setLocationType("state");
	        	inputVO.setSublocationType("state");
	        	inputVO.setLocationIdStr("1");
	        	
	        	str = convertingInputVOToString(inputVO);
				
				response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
		        
		        if (response.getStatus() != 200) {
		 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		 	      } else {
		 	    	 String output = response.getEntity(String.class);
		 	    	 if (output != null && output.length() > 0) {
		 	    		 JSONArray finalArray = new JSONArray(output);	 
		 	    		 if (finalArray != null && finalArray.length() > 0) {
		 	    			  for (int i = 0 ;i < finalArray.length() ;i++) {
		 	    				  
		 	    				 NregsDataVO vo = new NregsDataVO();
		 	    				 JSONObject jObj = (JSONObject) finalArray.get(i);
		 	    				  
		 	    				 vo.setType("state");
		 	    				 vo.setToday(jObj.getString("TODAY"));
		 	    				 returnList.get(0).getSubList().add(vo);
		 	    				  
		 	    			  }
		 	    		 }
		 	    	 }
		 	      }
	        }
			
		} catch (Exception e) {
			LOG.error("Exception raised at getFieldManDaysWorkDetails - NREGSTCSService service", e);
		}
		return returnList;
	}
}
