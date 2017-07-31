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
import com.itgrids.dto.NregsProjectsVO;
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
	public NregsProjectsVO getNtrJalaSiriAbstract(InputVO inputVO){
		NregsProjectsVO returnVO = new NregsProjectsVO();
		try{
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NtrDashBoardService/AbstractData", inputVO);
			
			if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				returnVO.setParameter(jObj.getString("PARAMETER"));
	 	    				returnVO.setPercentage(jObj.getString("PERCENTAGE"));
	 	    				returnVO.setTarget(jObj.getString("TARGET"));
	 	    				returnVO.setCompleted(jObj.getString("GROUNDED"));
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
	 * Author :Nandhini
	 * @description : getNtrJalaSiriOverview
	 */
	public NregsOverviewVO getNtrJalaSiriOverview(InputVO inputVO){
		NregsOverviewVO finalVO = new NregsOverviewVO();
		try {
			
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NtrDashBoardService/NtrOverviewNew", inputVO);
	        
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
 	    				finalVO.setTotalBudget1(Obj.getString("TOTALBUDGET1"));
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
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/NtrDashBoardService/NtrDataNew", inputVO);
			
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
	 	    				vo.setBorewellsDrilled(jObj.getLong("BOREWELLS_DRILLED_SUCCESS"));
	 	    				vo.setLtFiles(jObj.getString("LT_FILES"));
	 	    				vo.setSentToTransco(jObj.getString("SENT_TO_TRANSCO"));
	 	    				if(inputVO.getLocationType() != null && (inputVO.getLocationType().equalsIgnoreCase("constituency") || inputVO.getLocationType().equalsIgnoreCase("mandal")))
	 	    					vo.setBeneficaryContribution(jObj.getString("BENEFICIARY_CONTRI"));
	 	    				else
	 	    					vo.setBeneficaryContribution(jObj.getString("BENEFICARY_CONTRIBUTION"));
	 	    				vo.setAmountPaidTransco(jObj.getString("AMOUNT_PAID_TRANSCO"));
	 	    				vo.setBorewellenergisation(jObj.getString("BOREWELL_ENERGISATION"));
	 	    				if(vo.getTarget() != null && vo.getTarget() > 0l && vo.getBorewellsDrilled() != null && vo.getBorewellsDrilled() > 0l)
	 	    					vo.setPercentage(new BigDecimal(vo.getBorewellsDrilled()*100.00/vo.getTarget()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
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
	public String convertingInputVOToString(InputVO inputVO){
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
			LOG.error("Exception raised at convertingInputVOToString - RuralDevelopmentService", e);
		}
		return str;
	}
	/*
	 * Date : 31/07/2017
	 * Author :Nandhini
	 * @description : getRDLevelsWiseData
	 */
	public List<NregsDataVO> getRDLevelsWiseData(InputVO inputVO){
		List<NregsDataVO> voList = new ArrayList<NregsDataVO>(0);
		try {
			if(inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().toString().length() > 0l)
				inputVO.setSublocationType(inputVO.getSublocaType().trim());
			
			String webServiceUrl = null;
			String str = null;
			
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("SMC Trench"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/StaggeredService/StaggeredData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Imp to CD"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/ImprovmentsService/ImprovmentsData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("MPT_PT"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/MiniPercolationService/MiniPercolationData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("GC Works"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/GCWorkService/GCWorkData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("CD_CW"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/CheckDamService/CheckDamData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("WaterBudget"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/WaterBudgetService/WaterBudgetData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("GH"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/GreeningHillocksService/GreeningHillocksData";
			
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().equalsIgnoreCase("WaterBudget"))
				str = convertingInputVOToStringForIWMP(inputVO);
			else 
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
	 	    				NregsDataVO vo = new NregsDataVO();
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				vo.setUniqueId(Long.valueOf((jObj.getString("UNIQUEID").toString().trim().length() > 0 ? jObj.getString("UNIQUEID") : "1").toString()));
	 	    				vo.setDistrict(jObj.getString("DISTRICT"));
	 	    				vo.setConstituency(jObj.getString("CONSTITUENCY"));
	 	    				vo.setMandal(jObj.getString("MANDAL"));
	 	    				vo.setPanchayat(jObj.getString("PANCHAYAT"));
	 	    				if(inputVO.getDivType() != null && inputVO.getDivType().trim().equalsIgnoreCase("WaterBudget")){
	 	    					vo.setTarget(jObj.getLong("TGT"));
	 	    					vo.setAchivement(jObj.getString("ACHMT"));
		 	    				vo.setAchmtGT0(jObj.getString("ACHMTGT0"));
		 	    				vo.setAchmtLT0(jObj.getString("ACHMTLT0"));
		 	    				vo.setBalance(jObj.getString("BALANCE"));
		 	    				vo.setArea(jObj.getString("AREA"));
		 	    				vo.setGross(jObj.getString("GROSS"));
		 	    				vo.setStroageCap(jObj.getString("STRG_CAP"));
		 	    				vo.setBalanceRunOff(jObj.getString("BALANCERUNOFF"));
	 	    				}else if(inputVO.getDivType() != null && inputVO.getDivType().trim().equalsIgnoreCase("GH")){
	 	    					
	 	    					if(inputVO.getLocationType() != null && (inputVO.getLocationType().trim().equalsIgnoreCase("state") || inputVO.getLocationType().trim().equalsIgnoreCase("district")) &&
	 	    					   inputVO.getSublocationType() != null && (inputVO.getSublocationType().trim().equalsIgnoreCase("state") || inputVO.getSublocationType().trim().equalsIgnoreCase("district")))
	 	    							vo.setTarget(jObj.getLong("DISTRICT_TARGET"));
	 	    					
	 	    					vo.setSanctionedTarget(jObj.getString("SANCTION_TARGET"));
		 	    				vo.setPittingKMS(jObj.getString("PITTING_EXT"));
		 	    				vo.setPlantingKMS(jObj.getString("PLNTNG_EXT"));
		 	    				vo.setPercentage(jObj.getString("EXPN"));
	 	    				}else{
	 	    					vo.setTarget(jObj.getLong("TARGET"));
		 	    				vo.setGrounded(jObj.getString("GROUNDED"));
		 	    				if(jObj.getString("NOTGROUNDED").trim().contains("-"))
		 	    					vo.setNotGrounded("0");
		 	    				else
		 	    					vo.setNotGrounded(jObj.getString("NOTGROUNDED"));
		 	    				vo.setInProgress(jObj.getLong("INPROGRESS"));
		 	    				vo.setCompleted(jObj.getLong("COMPLETED"));
		 	    				vo.setPercentage(jObj.getString("PERCENTAGE"));
		 	    				if((inputVO.getSublocationType().trim().toString().equalsIgnoreCase("state") || inputVO.getSublocationType().trim().toString().equalsIgnoreCase("district"))){
		 	    					vo.setSanctionedTarget(jObj.getString("SANCTIONEDTARGET"));
		 	    					vo.setSanctionedPerventage(jObj.getString("SANCTIONEDPERCENTAGE"));
		 	    					if(vo.getCompleted() != null && vo.getCompleted().longValue() > 0l && vo.getSanctionedTarget() != null && Long.valueOf(vo.getSanctionedTarget()) > 0l)
		 	    						vo.setPercSant(new BigDecimal(vo.getCompleted()*100.00/Long.valueOf(vo.getSanctionedTarget())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
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
			LOG.error("Exception raised at getRDLevelsWiseData - RuralDevelopmentService", e);
		}
		
		return voList;
	}
	/*
	 * Date : 31/07/2017
	 * Author :Nandhini
	 * @description : getRDOverview
	 */
	public NregsOverviewVO getRDOverview(InputVO inputVO){
		NregsOverviewVO finalVO = new NregsOverviewVO();
		try {
			String webServiceUrl = null;
			
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("SMC Trench"))
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
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("GH"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/GreeningHillocksService/GreeningHillocksOverview";
			
			String str = convertingInputVOToString(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject Obj = new JSONObject(output);
	 	    		if(Obj!=null && Obj.length()>0){
	 	    			if(inputVO.getDivType() != null && (inputVO.getDivType().trim().toString().equalsIgnoreCase("WaterBudget") || inputVO.getDivType().trim().toString().equalsIgnoreCase("GH"))){
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
			LOG.error("Exception raised at getRDOverview - RuralDevelopmentService", e);
		}
		
		return finalVO;
	}
	/*
	 * Date : 31/07/2017
	 * Author :Nandhini
	 * @description : getRDAbstractDataByType
	 */
	public List<NregsProjectsVO> getRDAbstractDataByType(InputVO inputVO){
		List<NregsProjectsVO> returnList = new ArrayList<>();
		try {
			String str = null;
			ClientResponse response = null;
			if(inputVO.getType() != null && (inputVO.getType().trim().equalsIgnoreCase("WaterBudget") || inputVO.getType().trim().equalsIgnoreCase("GH")))
				str = convertingInputVOToStringForIWMP(inputVO);
			else
			 str = convertingInputVOToString(inputVO);
			
			if(inputVO.getType() != null && (inputVO.getType().trim().equalsIgnoreCase("WaterBudget") || inputVO.getType().trim().equalsIgnoreCase("GH")))
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
	 	    				vo.setParameter(jObj.getString("PARAMETER"));
	 	    				vo.setTarget(jObj.getString("TARGET"));
	 	    				
	 	    				if(inputVO.getType() != null && (inputVO.getType().trim().equalsIgnoreCase("WaterBudget") || inputVO.getType().trim().equalsIgnoreCase("GH")) ){
	 	    					vo.setCompleted(jObj.getString("ACHMT"));
		 	    				vo.setPercentage(jObj.getString("PER"));
	 	    				}else{
		 	    				vo.setCompleted(jObj.getString("COMPLETED"));
		 	    				vo.setPercentage(jObj.getString("PERC"));
	 	    				}
	 	    				
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
			LOG.error("Exception raised at getRDAbstractDataByType - RuralDevelopmentService", e);
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
			LOG.error("Exception raised at convertingInputVOToStringForIWMP - RuralDevelopmentService", e);
		}
		return str;
	}

}
