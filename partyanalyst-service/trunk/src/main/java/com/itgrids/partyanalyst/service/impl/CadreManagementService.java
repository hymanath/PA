package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyTehsilDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.CadreRegionInfoVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserCadresInfoVO;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.CadreLevel;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyTehsil;
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

	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private ITehsilDAO tehsilDAO;
	private ITownshipDAO townshipDAO;
	private IRegistrationDAO registrationDAO;
	private IConstituencyDAO constituencyDAO;
	private IConstituencyTehsilDAO constituencyTehsilDAO;
	
	
	
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

	public void setConstituencyTehsilDAO(IConstituencyTehsilDAO constituencyTehsilDAO) {
		this.constituencyTehsilDAO = constituencyTehsilDAO;
	}
	


	public Long saveCader(CadreInfo cadreInfo){
		
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
		cadre.setRegistration(registrationDAO.get(cadreInfo.getUserID()));
		cadre = cadreDAO.save(cadre);
		return cadre.getCadreId();
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
		if("MLA".equals(userAccessType)){ 
			List mandals = cadreDAO.findMandalsByConstituencyID(accessID);
			StringBuilder sbMandals = getFormatedData(mandals,userAccessMandals);
			userCadreInfo.setUserAccessMandals(userAccessMandals);

			accessID = sbMandals.substring(0, sbMandals.length()-1);
			
			List cadreSizeMandalWise = cadreDAO.findCadreSizeMandalWise(userCadreInfo.getUserID());
			long mandalLevelZeroCadres = userCadreInfo.getUserAccessMandals().size() - cadreSizeMandalWise.size();//getZeroSize(cadreSizeZero4Mandal);
			if(mandalLevelZeroCadres > 0)
				regionLevelZeroCadres.put("MANDAL", mandalLevelZeroCadres);
		}
		 
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
				 || "DISTRICT".equals(userAccessType) || "MANDAL".equals(userAccessType)
				 || "MLA".equals(userAccessType)){
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
	//Narender 28th October 2009
	@SuppressWarnings("unchecked")
	public List<CadreRegionInfoVO> getConstituencyAllMandalsCadres(Long constituencyID, Long userID){
		List<ConstituencyTehsil> constituencyTehsils = constituencyTehsilDAO.getTehsilsByConstituency(constituencyID);
		StringBuilder mandalIDs = new StringBuilder();
		for(ConstituencyTehsil constituencyTehsil: constituencyTehsils){
			mandalIDs.append(",").append(constituencyTehsil.getTehsil().getTehsilId());
		}
		String strMandalIDs = mandalIDs.toString().substring(1);
		List mandalCadres = cadreDAO.findMandalCadresByMandals(strMandalIDs, userID);
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
			cadreInfo.setMobile(cadre.getMobile());
			cadreInfo.setLandLineNo("-");
			cadreInfo.setEmail(cadre.getEmail());
			cadreInfo.setCadreLevel(cadre.getCadreLevel().getCadreLevelID());
			cadreInfo.setCadreLevelValue(cadre.getCadreLevelValue());
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
		List<Constituency> names = constituencyDAO.findByConstituencyId(constituencyID);
		String name = "";
		if(names!=null && names.size()>0)
			name=names.get(0).getName();
		return name;
	}
}
