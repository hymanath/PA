package com.itgrids.partyanalyst.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.CadreRegionInfoVO;
import com.itgrids.partyanalyst.dto.UserCadresInfoVO;
import com.itgrids.partyanalyst.model.Cadre;
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
	public void saveCader(String cadreInfo){
		
		Cadre cadre = new Cadre();
		cadreDAO.save(cadre);
		//return null;
	}
	

	public void deleteCadre(Long cadreID){
		cadreDAO.remove(cadreID);
	}

	
	public UserCadresInfoVO getUserCadresInfo(UserCadresInfoVO userCadreInfo){
		Long totalUserAccessLevelCaders = cadreDAO.findTotalCadresByUserID(userCadreInfo.getUserID());
		userCadreInfo.setTotalCadres(totalUserAccessLevelCaders);
		
		userCadreInfo = getUserAccessRegions(userCadreInfo);
		userCadreInfo = getRegionLevelCadresCount(userCadreInfo);
		System.out.println(userCadreInfo.getRegionLevelCadres().size());
		
		return userCadreInfo;
	}

	
	@SuppressWarnings("unchecked")
	public UserCadresInfoVO getUserAccessRegions(UserCadresInfoVO userCadreInfo){
		Map<Long, String> userAccessStates = new LinkedHashMap<Long, String>();
		Map<Long, String> userAccessDistricts = new LinkedHashMap<Long, String>();
		Map<Long, String> userAccessMandals = new LinkedHashMap<Long, String>();
		Map<Long, String> userAccessVillages = new LinkedHashMap<Long, String>();
		
		String userAccessType = userCadreInfo.getUserAccessType();
		String accessID = userCadreInfo.getUserAccessValue();
		//Long accessID = new Long(userAccessValue);
		Map<String,Long> regionLevelZeroCadres = userCadreInfo.getRegionLevelZeroCadres(); 
		 
		if("COUNTRY".equals(userAccessType)){
			List states = cadreDAO.findStatesByCountryID(accessID);
			StringBuilder sbStates = getFormatedData(states,userAccessStates);
			userCadreInfo.setUserAccessStates(userAccessStates);
			
			accessID = sbStates.substring(0, sbStates.length()-1);
			
			List cadreSizeStateWise = cadreDAO.findCadreSizeStateWise(userCadreInfo.getUserID());
			long stateLevelZeroCadres = userCadreInfo.getUserAccessStates().size() - cadreSizeStateWise.size();//getZeroSize(cadreSizeZero4State);
			if(stateLevelZeroCadres > 0)
				regionLevelZeroCadres.put("STATE", stateLevelZeroCadres);
			
		}
		if("COUNTRY".equals(userAccessType) || "STATE".equals(userAccessType)){
			List districts = cadreDAO.findDistrictsByStateID(accessID);
			StringBuilder sbDistricts = getFormatedData(districts,userAccessDistricts);
			userCadreInfo.setUserAccessDistricts(userAccessDistricts);

			accessID = sbDistricts.substring(0, sbDistricts.length()-1);
			
			List cadreSizeDistrictWise = cadreDAO.findCadreSizeDistrictWise( userCadreInfo.getUserID());
			long districtLevelZeroCadres = userCadreInfo.getUserAccessDistricts().size() - cadreSizeDistrictWise.size();//getZeroSize(cadreSizeZero4District);
			if(districtLevelZeroCadres > 0)
				regionLevelZeroCadres.put("DISTRICT", districtLevelZeroCadres);
			
		}
		if("COUNTRY".equals(userAccessType) || "STATE".equals(userAccessType) || "DISTRICT".equals(userAccessType)){
			List mandals = cadreDAO.findMandalsByDistrictID(accessID);
			StringBuilder sbMandals = getFormatedData(mandals,userAccessMandals);
			userCadreInfo.setUserAccessMandals(userAccessMandals);

			accessID = sbMandals.substring(0, sbMandals.length()-1);
			
			List cadreSizeMandalWise = cadreDAO.findCadreSizeMandalWise(userCadreInfo.getUserID());
			long mandalLevelZeroCadres = userCadreInfo.getUserAccessMandals().size() - cadreSizeMandalWise.size();//getZeroSize(cadreSizeZero4Mandal);
			if(mandalLevelZeroCadres > 0)
				regionLevelZeroCadres.put("MANDAL", mandalLevelZeroCadres);
			
		}if("COUNTRY".equals(userAccessType) || "STATE".equals(userAccessType)
				 || "DISTRICT".equals(userAccessType) || "MANDAL".equals(userAccessType)){
			List villages = cadreDAO.findVillagesByTehsilID(accessID);
			StringBuilder sbVillages = getFormatedData(villages,userAccessVillages);
			userCadreInfo.setUserAccessVillages(userAccessVillages);

			accessID = sbVillages.substring(0, sbVillages.length()-1);
			List cadreSizeVillageWise = cadreDAO.findCadreSizeVillageWise(userCadreInfo.getUserID());
			long villageLevelZeroCadres = userCadreInfo.getUserAccessVillages().size() - cadreSizeVillageWise.size();//getZeroSize(cadreSizeZero4Mandal);

			if(villageLevelZeroCadres > 0)
				regionLevelZeroCadres.put("VILLAGE", villageLevelZeroCadres);
		}
		
		userCadreInfo.setRegionLevelZeroCadres(regionLevelZeroCadres);
		return userCadreInfo;
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
	public UserCadresInfoVO getRegionLevelCadresCount(UserCadresInfoVO userCadreInfo){
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
		System.out.println("Testing.....Testing...........Testing...............................");
		List<CadreInfo> formattedData = new ArrayList<CadreInfo>();
		List<Cadre> cadresList = cadreDAO.findCadresByVillage(villageID, userID);
		for(Cadre cadre:cadresList){
			System.out.println("MobileNO============="+cadre.getMobile());
			CadreInfo cadreInfo = new CadreInfo();
			cadreInfo.setCadreID(cadre.getCadreId());
			cadreInfo.setFirstName(cadre.getFirstName());
			cadreInfo.setMiddleName(cadre.getMiddleName());
			cadreInfo.setLastName(cadre.getLastName());
			cadreInfo.setGender(cadre.getGender());
			cadreInfo.setMobileNo(cadre.getMobile());
			cadreInfo.setLandLineNo("-");
			cadreInfo.setEmail(cadre.getEmail());
			cadreInfo.setCadreLevel(cadre.getCadreLevel().getLevel());
			formattedData.add(cadreInfo);
		}
		return formattedData;
	}
}
