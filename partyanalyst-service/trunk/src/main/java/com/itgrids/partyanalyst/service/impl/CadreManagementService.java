package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.CadreRegionInfoVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserCadresInfoVO;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.CadreLevel;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Country;
import com.itgrids.partyanalyst.model.DelimitationConstituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.Tehsil;
/**
 * 
 * @author Narender Akula
 *
 */
public class CadreManagementService {

	private ICadreDAO cadreDAO;
	
	public void setCadreDAO(ICadreDAO cadreDAO) {
		this.cadreDAO = cadreDAO;
	}

	private ICountryDAO countryDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private ITehsilDAO tehsilDAO;
	private ITownshipDAO townshipDAO;
	private IRegistrationDAO registrationDAO;
	private IConstituencyDAO constituencyDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private static final Logger log = Logger.getLogger(CadreManagementService.class);
	
	public void setCountryDAO(ICountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}


	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}


	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}


	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}


	public void setTownshipDAO(ITownshipDAO townshipDAO) {
		this.townshipDAO = townshipDAO;
	}

	public void setRegistrationDAO(IRegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}


	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}


	public Long saveCader(CadreInfo cadreInfo){
		if(log.isDebugEnabled()){
			log.debug("cadrerManagementService.saveCadre():::-constituencyID::"+cadreInfo.getConstituencyID());
		}
		Cadre cadre = new Cadre();
		cadre.setFirstName(cadreInfo.getFirstName());
		cadre.setMiddleName(cadreInfo.getMiddleName());
		cadre.setLastName(cadreInfo.getLastName());
		cadre.setGender(cadreInfo.getGender());
		cadre.setState(stateDAO.get(new Long(cadreInfo.getState())));
		cadre.setDistrict(districtDAO.get(new Long(cadreInfo.getDistrict())));
		cadre.setTehsil(tehsilDAO.get(new Long(cadreInfo.getMandal())));
		cadre.setVillage(townshipDAO.get(new Long(cadreInfo.getVillage())));
		CadreLevel level = new CadreLevel();
		level.setCadreLevelID(cadreInfo.getCadreLevel());
		String[] values = {"","COUNTRY","STATE","DISTRICT","CONSTITUENCY","MANDAL","VILLAGE"};
		level.setLevel(values[cadreInfo.getCadreLevel().intValue()]);
		cadre.setCadreLevel(level);
		cadre.setCadreLevelValue(cadreInfo.getCadreLevelValue());
		cadre.setMobile(cadreInfo.getMobile());
		cadre.setEmail(cadreInfo.getEmail());
		cadre.setConstituency(constituencyDAO.get(cadreInfo.getConstituencyID()));
		cadre.setRegistration(registrationDAO.get(cadreInfo.getUserID()));
		cadre = cadreDAO.save(cadre);
		return cadre.getCadreId();
	}
	

	public void deleteCadre(Long cadreID){
		cadreDAO.remove(cadreID);
	}

	
	public UserCadresInfoVO getUserCadresInfo(UserCadresInfoVO userCadreInfo){
		if(log.isDebugEnabled()){
			log.debug("CadreManagementService.getUserCadresInfo() started");
		}
		ResultStatus resultStatus = new ResultStatus();
		resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		try{
			Long totalUserAccessLevelCaders = cadreDAO.findTotalCadresByUserID(userCadreInfo.getUserID());
			userCadreInfo.setTotalCadres(totalUserAccessLevelCaders);
			userCadreInfo = getUserAccessRegions(userCadreInfo);
			userCadreInfo = getCadreLevelCadresCount(userCadreInfo);
		}catch(Exception e){
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			resultStatus.setExceptionEncountered(e);
		}
		
		return userCadreInfo;
	}

	
	@SuppressWarnings("unchecked")
	public UserCadresInfoVO getUserAccessRegions(UserCadresInfoVO userCadreInfo){
		if(log.isDebugEnabled()){
			log.debug("CadreManagementService.getUserAccessRegions() started");
		}
		Map<Long, String> userAccessStates = new LinkedHashMap<Long, String>();
		Map<Long, String> userAccessDistricts = new LinkedHashMap<Long, String>();
		Map<Long, String> userAccessMandals = new LinkedHashMap<Long, String>();
		Map<Long, String> userAccessVillages = new LinkedHashMap<Long, String>();

		Map<Long, String> zeroCadreStates = new LinkedHashMap<Long, String>();
		Map<Long, String> zeroCadreDistricts = new LinkedHashMap<Long, String>();
		Map<Long, String> zeroCadreMandals = new LinkedHashMap<Long, String>();
		Map<Long, String> zeroCadreVillages = new LinkedHashMap<Long, String>();
		
		String userAccessType = userCadreInfo.getUserAccessType();
		String accessID = userCadreInfo.getUserAccessValue();
		//Long accessID = new Long(userAccessValue);
		List<SelectOptionVO> regionLevelZeroCadres = userCadreInfo.getRegionLevelZeroCadres(); 
		boolean downLevelCadresFlag = true;
		if("MLA".equals(userAccessType)){ 
			if(log.isDebugEnabled()){
				log.debug("CadreManagementService.getUserAccessRegions() if MLA started");
			}
			//List mandals = cadreDAO.findMandalsByConstituencyID(accessID);
			
			List<DelimitationConstituency> delimitationConstituency = delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyID(new Long(accessID));
			Long delimitationConstituencyID = delimitationConstituency.get(0).getDelimitationConstituencyID();
			List<Tehsil> tehsils = delimitationConstituencyMandalDAO.getTehsilsByDelimitationConstituencyID(delimitationConstituencyID);
			/*StringBuilder mandalIDs = new StringBuilder();
			for(Tehsil tehsil : tehsils){
				mandalIDs.append(",").append(tehsil.getTehsilId());
			}*/
			
			
			log.debug("CadreManagementService.getUserAccessRegions() mandals::"+tehsils.size());
			List cadreSizeMandalWise = cadreDAO.findCadreSizeMandalWise(userCadreInfo.getUserID());
			log.debug("CadreManagementService.getUserAccessRegions() cadreSizeMandalWise::"+cadreSizeMandalWise.size());
			if(cadreSizeMandalWise.size() == 0)
				downLevelCadresFlag = false;
			log.debug("downLevelCadresFlag::"+downLevelCadresFlag);
			long mandalLevelZeroCadres = tehsils.size() - cadreSizeMandalWise.size();
			StringBuilder sbMandals = getMLAFormatedData(tehsils,userAccessMandals,cadreSizeMandalWise,zeroCadreMandals);
			log.debug("CadreManagementService.getUserAccessRegions() sbMandals::"+sbMandals);
			userCadreInfo.setUserAccessMandals(userAccessMandals);
			userCadreInfo.setZeroCadreMandals(zeroCadreMandals);
			
			if(sbMandals!=null && sbMandals.length()>0)
				accessID = sbMandals.substring(0, sbMandals.length()-1);
			if(log.isDebugEnabled()){
				log.debug(userCadreInfo.getUserAccessValue()+"::mandalIDs for MLA constituencyID="+accessID);
			}
			SelectOptionVO voObject = new SelectOptionVO(mandalLevelZeroCadres,"MANDAL");
			if(mandalLevelZeroCadres > 0)
				regionLevelZeroCadres.add(voObject);
		}
		 
		if("COUNTRY".equals(userAccessType)){
			if(log.isDebugEnabled()){
			log.debug("CadreManagementService.getUserAccessRegions() if COUNTRY started");
		}
			List states = cadreDAO.findStatesByCountryID(accessID);
			List cadreSizeStateWise = cadreDAO.findCadreSizeStateWise(userCadreInfo.getUserID());
			if(cadreSizeStateWise.size() == 0)
				downLevelCadresFlag = false;
			long stateLevelZeroCadres = states.size() - cadreSizeStateWise.size();
			StringBuilder sbStates = getFormatedData(states,userAccessStates,cadreSizeStateWise,zeroCadreStates);
			userCadreInfo.setUserAccessStates(userAccessStates);
			userCadreInfo.setZeroCadreStates(zeroCadreStates);
			if(log.isDebugEnabled()){
				log.debug("CadreManagementService.getUserAccessRegions() sbStates:"+sbStates);
			}
			if(sbStates!=null && sbStates.length()>0)
				accessID = sbStates.substring(0, sbStates.length()-1);
			
			SelectOptionVO voObject = new SelectOptionVO(stateLevelZeroCadres,"STATE");
			if(stateLevelZeroCadres > 0)
				regionLevelZeroCadres.add(voObject);
			
		}
		if((downLevelCadresFlag) && ("COUNTRY".equals(userAccessType) || "STATE".equals(userAccessType))){
			if(log.isDebugEnabled()){
				log.debug("CadreManagementService.getUserAccessRegions() if STATE started");
			}
			List districts = cadreDAO.findDistrictsByStateID(accessID);
			List cadreSizeDistrictWise = cadreDAO.findCadreSizeDistrictWise( userCadreInfo.getUserID());
			if(cadreSizeDistrictWise.size() == 0)
				downLevelCadresFlag = false;
			long districtLevelZeroCadres = districts.size() - cadreSizeDistrictWise.size();//getZeroSize(cadreSizeZero4District);
			StringBuilder sbDistricts = getFormatedData(districts,userAccessDistricts,cadreSizeDistrictWise,zeroCadreDistricts);
			userCadreInfo.setUserAccessDistricts(userAccessDistricts);
			userCadreInfo.setZeroCadreDistricts(zeroCadreDistricts);
			if(log.isDebugEnabled()){
				log.debug("CadreManagementService.getUserAccessRegions() sbDistricts:"+sbDistricts);
			}
			if(sbDistricts!=null && sbDistricts.length()>0)
				accessID = sbDistricts.substring(0, sbDistricts.length()-1);
			
			SelectOptionVO voObject = new SelectOptionVO(districtLevelZeroCadres,"DISTRICT");
			if(districtLevelZeroCadres > 0)
				regionLevelZeroCadres.add(voObject);
			
		}
		if((downLevelCadresFlag) && ("COUNTRY".equals(userAccessType) || "STATE".equals(userAccessType) || "DISTRICT".equals(userAccessType))){
			if(log.isDebugEnabled()){
				log.debug("CadreManagementService.getUserAccessRegions() if DISTRICT started");
			}
			List mandals = cadreDAO.findMandalsByDistrictID(accessID);
			List cadreSizeMandalWise = cadreDAO.findCadreSizeMandalWise(userCadreInfo.getUserID());
			if(cadreSizeMandalWise.size() == 0)
				downLevelCadresFlag = false;
			long mandalLevelZeroCadres = mandals.size() - cadreSizeMandalWise.size();//getZeroSize(cadreSizeZero4Mandal);
			StringBuilder sbMandals = getFormatedData(mandals,userAccessMandals, cadreSizeMandalWise, zeroCadreMandals);
			userCadreInfo.setUserAccessMandals(userAccessMandals);
			userCadreInfo.setZeroCadreMandals(zeroCadreMandals);
			if(log.isDebugEnabled()){
				log.debug("CadreManagementService.getUserAccessRegions() sbMandals:"+sbMandals);
			}
			if(sbMandals!=null && sbMandals.length()>0)
				accessID = sbMandals.substring(0, sbMandals.length()-1);
			
			SelectOptionVO voObject = new SelectOptionVO(mandalLevelZeroCadres,"MANDAL");
			if(mandalLevelZeroCadres > 0)
				regionLevelZeroCadres.add(voObject);
			
		}
		if((downLevelCadresFlag) && ("COUNTRY".equals(userAccessType) || "STATE".equals(userAccessType)
				 || "DISTRICT".equals(userAccessType) || "MANDAL".equals(userAccessType)
				 || "MLA".equals(userAccessType))){
			if(log.isDebugEnabled()){
				log.debug("CadreManagementService.getUserAccessRegions() if MANDAL started");
			}
			List villages = cadreDAO.findVillagesByTehsilID(accessID);
			List cadreSizeVillageWise = cadreDAO.findCadreSizeVillageWise(userCadreInfo.getUserID());
			long villageLevelZeroCadres = villages.size() - cadreSizeVillageWise.size();//getZeroSize(cadreSizeZero4Mandal);
			
			StringBuilder sbVillages = getFormatedData(villages,userAccessVillages,cadreSizeVillageWise,zeroCadreVillages);
			if(log.isDebugEnabled()){
				log.debug("CadreManagementService.getUserAccessRegions() sbVillages:"+sbVillages);
			}
			userCadreInfo.setUserAccessVillages(userAccessVillages);
			userCadreInfo.setZeroCadreVillages(zeroCadreVillages);
			
			/*if(sbVillages!=null && sbVillages.length()>0)
				accessID = sbVillages.substring(0, sbVillages.length()-1);*/
			
			SelectOptionVO voObject = new SelectOptionVO(villageLevelZeroCadres,"VILLAGE");
			if(villageLevelZeroCadres > 0)
				regionLevelZeroCadres.add(voObject);
		}
		
		userCadreInfo.setRegionLevelZeroCadres(regionLevelZeroCadres);
		return userCadreInfo;
	}
	@SuppressWarnings("unchecked")
	public StringBuilder getFormatedData(List regionData, Map<Long, String> userAccessRegions, 
			List cadreAvailableRegions, Map<Long, String> zeroCadreRegions){
		
		StringBuilder sb = new StringBuilder();
		Map<Long, Object> availableCadreRegions = new HashMap<Long, Object>();
		for(int i=0; i<cadreAvailableRegions.size(); i++){
			Object[] objInfo = (Object[]) cadreAvailableRegions.get(i);
			Long key = new Long(objInfo[0].toString());
			availableCadreRegions.put(key, objInfo[0]);
		}
		
		
		for(int i=0; i<regionData.size(); i++){
			Object[] objInfo = (Object[]) regionData.get(i);
			Long key = new Long(objInfo[0].toString());
			String value = objInfo[1].toString();
			userAccessRegions.put(key, value);
			if(null==availableCadreRegions.get(key)){
				zeroCadreRegions.put(key, value);				
			}else{
				sb.append(key).append(",");
			}
		}
		
		return sb;
	}
	@SuppressWarnings("unchecked")
	public StringBuilder getMLAFormatedData(List<Tehsil> regionData, Map<Long, String> userAccessRegions, 
			List cadreAvailableRegions, Map<Long, String> zeroCadreRegions){
		
		StringBuilder sb = new StringBuilder();
		Map<Long, Object> availableCadreRegions = new HashMap<Long, Object>();
		for(int i=0; i<cadreAvailableRegions.size(); i++){
			Object[] objInfo = (Object[]) cadreAvailableRegions.get(i);
			Long key = new Long(objInfo[0].toString());
			availableCadreRegions.put(key, objInfo[0]);
		}
		
		for(Tehsil tehsil:regionData){
			Long key = tehsil.getTehsilId();
			String value = tehsil.getTehsilName();
			userAccessRegions.put(key, value);
			if(null==availableCadreRegions.get(key)){
				zeroCadreRegions.put(key, value);				
			}else{
				sb.append(key).append(",");
			}
		}
		return sb;
	}
	@SuppressWarnings("unchecked")
	public StringBuilder getFormatedData(List regionData, Map<Long, String> userAccessRegions){
		//userAccessRegions = new LinkedHashMap<Long, String>();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<regionData.size(); i++){
			Object[] objInfo = (Object[]) regionData.get(i);
			userAccessRegions.put(new Long(objInfo[0].toString()), objInfo[1].toString());
			sb.append(objInfo[0]).append(",");
		}
		return sb;
	}

	@SuppressWarnings("unchecked")
	public UserCadresInfoVO getCadreLevelCadresCount(UserCadresInfoVO userCadreInfo){
		List cadresByRegionList = cadreDAO.findCadresByLevels(userCadreInfo.getUserID());
		Map<String,Long> tempMap = new LinkedHashMap<String,Long>();
		for(int i =0; i<cadresByRegionList.size(); i++){
			Object[] objInfo = (Object[]) cadresByRegionList.get(i);
			tempMap.put(objInfo[0].toString(), new Long(objInfo[1].toString()));
		}
		userCadreInfo.setRegionLevelCadres(tempMap);
		return userCadreInfo;
	}
	
	//Ajax calling methods
	
	@SuppressWarnings("unchecked")
	public List<CadreRegionInfoVO> getCountryAllStatesCadres(Long countryID, Long userID){
		List stateCadres = cadreDAO.findStateCadresByCountry(countryID, userID);
		int size = stateCadres.size();
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		for(int i=0; i<size;i++){
			Object[] voObject=(Object[]) stateCadres.get(i);
			CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("STATE");
			regionInfoVo.setRegionId(new Long(voObject[0].toString()));
			regionInfoVo.setRegionName(voObject[1].toString());
			regionInfoVo.setCadreCount(new Long(voObject[2].toString()));
			formattedData.add(regionInfoVo);
		}
		return formattedData;
	}
	
	@SuppressWarnings("unchecked")
	public List<CadreRegionInfoVO> getStateAllDistrictsCadres(Long stateID, Long userID){
		List districtCadres = cadreDAO.findDistCadresByState(stateID, userID);
		int size = districtCadres.size();
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		for(int i=0; i<size;i++){
			Object[] voObject=(Object[]) districtCadres.get(i);
			CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("DISTRICT");
			regionInfoVo.setRegionId(new Long(voObject[0].toString()));
			regionInfoVo.setRegionName(voObject[1].toString());
			regionInfoVo.setCadreCount(new Long(voObject[2].toString()));
			formattedData.add(regionInfoVo);
		}
		return formattedData;
	}
	

	@SuppressWarnings("unchecked")
	public List<CadreRegionInfoVO> getDistrictAllMandalsCadres(Long districtID, Long userID){
		List mandalCadres = cadreDAO.findMandalCadresByDist(districtID, userID);
		int size = mandalCadres.size();
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		for(int i=0; i<size;i++){
			Object[] voObject=(Object[]) mandalCadres.get(i);
			CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("MANDAL");
			regionInfoVo.setRegionId(new Long(voObject[0].toString()));
			regionInfoVo.setRegionName(voObject[1].toString());
			regionInfoVo.setCadreCount(new Long(voObject[2].toString()));
			formattedData.add(regionInfoVo);
		}
		return formattedData;	
	}
	//Narender 28th October 2009
	@SuppressWarnings("unchecked")
	public List<CadreRegionInfoVO> getConstituencyAllMandalsCadres(Long constituencyID, Long userID){
		List<DelimitationConstituency> delimitationConstituency = delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyID(constituencyID);
		Long delimitationConstituencyID = delimitationConstituency.get(0).getDelimitationConstituencyID();
		List<Tehsil> tehsils = delimitationConstituencyMandalDAO.getTehsilsByDelimitationConstituencyID(delimitationConstituencyID);
		StringBuilder mandalIDs = new StringBuilder();
		for(Tehsil tehsil : tehsils){
			mandalIDs.append(",").append(tehsil.getTehsilId());
		}
		
		System.out.println("mandalIDs.toString():::"+mandalIDs.toString());
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		String strMandalIDs = new String();
		if(mandalIDs.length()>0){
			strMandalIDs = mandalIDs.toString().substring(1);
			List mandalCadres = cadreDAO.findMandalCadresByMandals(strMandalIDs, userID);
			int size = mandalCadres.size();
			for(int i=0; i<size;i++){
				Object[] voObject=(Object[]) mandalCadres.get(i);
				CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("MANDAL");
				regionInfoVo.setRegionId(new Long(voObject[0].toString()));
				regionInfoVo.setRegionName(voObject[1].toString());
				regionInfoVo.setCadreCount(new Long(voObject[2].toString()));
				formattedData.add(regionInfoVo);
			}
		}
		return formattedData;	
	}
	
	@SuppressWarnings("unchecked")
	public List<CadreRegionInfoVO> getMandalAllVillagesCadres(Long mandalID, Long userID){
		List villageCadres = cadreDAO.findVillageCadresByMandal(mandalID, userID);
		int size = villageCadres.size();
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		for(int i=0; i<size;i++){
			Object[] voObject=(Object[]) villageCadres.get(i);
			CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("VILLAGE");
			regionInfoVo.setRegionId(new Long(voObject[0].toString()));
			regionInfoVo.setRegionName(voObject[1].toString());
			regionInfoVo.setCadreCount(new Long(voObject[2].toString()));
			formattedData.add(regionInfoVo);
		}
		return formattedData;	
	}
	
	public List<CadreInfo> getCadresByVillage(Long villageID, Long userID){
		List<CadreInfo> formattedData = new ArrayList<CadreInfo>();
		List<Cadre> cadresList = cadreDAO.findCadresByVillage(villageID, userID);
		for(Cadre cadre:cadresList){
			CadreInfo cadreInfo = convertCadreToCadreInfo(cadre);
			formattedData.add(cadreInfo);
		}
		return formattedData;
	}
	
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> findStatesByCountryID(String countryID){
		List states = cadreDAO.findStatesByCountryID(countryID);
		List<SelectOptionVO> stateNames=new ArrayList<SelectOptionVO>();
		
		int size = states.size();
		for(int i=0; i<size;i++){
			Object[] voObject=(Object[]) states.get(i);
			SelectOptionVO objVO = new SelectOptionVO();
			objVO.setId(new Long(voObject[0].toString()));
			objVO.setName(voObject[1].toString());
			stateNames.add(objVO);
		}
		
		return stateNames;
	}
	

	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> findDistrictsByState(String stateID){
		List districts = cadreDAO.findDistrictsByStateID(stateID);
		List<SelectOptionVO> districtNames=new ArrayList<SelectOptionVO>();
		
		int size = districts.size();
		for(int i=0; i<size;i++){
			Object[] voObject=(Object[]) districts.get(i);
			SelectOptionVO objVO = new SelectOptionVO();
			objVO.setId(new Long(voObject[0].toString()));
			objVO.setName(voObject[1].toString());
			districtNames.add(objVO);
		}
		
		return districtNames;
	}
	


	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> findMandalsByDistrict(String districtID){
		List mandals = cadreDAO.findMandalsByDistrictID(districtID);
		List<SelectOptionVO> mandalNames=new ArrayList<SelectOptionVO>();
		
		int size = mandals.size();
		for(int i=0; i<size;i++){
			Object[] voObject=(Object[]) mandals.get(i);
			SelectOptionVO objVO = new SelectOptionVO();
			objVO.setId(new Long(voObject[0].toString()));
			objVO.setName(voObject[1].toString());
			mandalNames.add(objVO);
		}
		
		return mandalNames;
	}
	

	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> findVillagesByTehsilID(String mandalID){
		List villages = cadreDAO.findVillagesByTehsilID(mandalID);
		List<SelectOptionVO> villageNames=new ArrayList<SelectOptionVO>();
		
		int size = villages.size();
		for(int i=0; i<size;i++){
			Object[] voObject=(Object[]) villages.get(i);
			SelectOptionVO objVO = new SelectOptionVO();
			objVO.setId(new Long(voObject[0].toString()));
			objVO.setName(voObject[1].toString());
			villageNames.add(objVO);
		}
		
		return villageNames;
	}
	
	public String getConstituencyName(Long constituencyID){
		Constituency constituency = constituencyDAO.get(constituencyID);
		String name = "";
		if(constituency!=null)
			name=constituency.getName();
		return name;
	}

	public String getStateName(Long stateID){
		State state = stateDAO.get(stateID);
		String name = "";
		if(state!=null)
			name=state.getStateName();
		return name;
	}
	public String getDistrictName(Long districtID){
		District district = districtDAO.get(districtID);
		String name = "";
		if(district!=null)
			name=district.getDistrictName();
		return name;
	}

	public String getMandalName(Long mandalID){
		Tehsil mandal = tehsilDAO.get(mandalID);
		String name = "";
		if(mandal!=null)
			name=mandal.getTehsilName();
		return name;
	}

	public String getCountryName(Long countryID){
		Country country = countryDAO.get(countryID);
		String name = "";
		if(country!=null)
			name=country.getCountryName();
		return name;
	}
	
	public List<CadreInfo> getCadresByCadreLevel(String cadreLevel, Long userID){
		List<CadreInfo> cadreInfoList = new ArrayList<CadreInfo>();
		List<Cadre> cadresList = cadreDAO.findCadresByCadreLevel(cadreLevel,userID);
		for(Cadre cadre:cadresList){
			CadreInfo cadreInfo = convertCadreToCadreInfo(cadre);
			cadreInfoList.add(cadreInfo);
		}
		return cadreInfoList;
	}
	
	public CadreInfo convertCadreToCadreInfo(Cadre cadre){
		CadreInfo cadreInfo = new CadreInfo();
		cadreInfo.setCadreID(cadre.getCadreId());
		cadreInfo.setFirstName(cadre.getFirstName());
		cadreInfo.setMiddleName(cadre.getMiddleName());
		cadreInfo.setLastName(cadre.getLastName());
		cadreInfo.setGender(cadre.getGender());
		cadreInfo.setMobile(cadre.getMobile());
		cadreInfo.setLandLineNo("-");
		cadreInfo.setEmail(cadre.getEmail());
		String level = cadre.getCadreLevel().getLevel();
		cadreInfo.setCadreLevel(cadre.getCadreLevel().getCadreLevelID());
		cadreInfo.setStrCadreLevel(level);
		String levelValue = "";
		Long levelValueID = cadre.getCadreLevelValue();
		if("COUNTRY".equals(level)){
			levelValue = getCountryName(levelValueID);
		}else if("STATE".equals(level)){
			levelValue = getStateName(levelValueID);
		}else if("DISTRICT".equals(level)){
			levelValue = getDistrictName(levelValueID);
		}else if("CONSTITUENCY".equals(level)){
			levelValue = getConstituencyName(levelValueID);
		}else if("MANDAL".equals(level)){
			levelValue = getMandalName(levelValueID);
		}
		cadreInfo.setCadreLevelValue(levelValueID);
		cadreInfo.setStrCadreLevelValue(levelValue);
		
		return cadreInfo;
	}
	

	public List<SelectOptionVO> getStateDistConstituencyMandalByMandalID(Long mandalID){

		List stateDistConstMandal = delimitationConstituencyMandalDAO.getStateDistConstituencyMandalByMandalID(mandalID);
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		SelectOptionVO state = new SelectOptionVO();
		SelectOptionVO district = new SelectOptionVO();
		SelectOptionVO mandal = new SelectOptionVO();
		Object[] objVO = (Object[])stateDistConstMandal.get(0);

		state.setId(new Long(objVO[0].toString()));
		state.setName(objVO[1].toString());

		district.setId(new Long(objVO[2].toString()));
		district.setName(objVO[3].toString());
		
		mandal.setId(mandalID);
		district.setName(objVO[4].toString());

		result.add(state);
		result.add(district);
		
		return result;
	}
}
