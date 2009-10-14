package com.itgrids.partyanalyst.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dto.CadreInfo;
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

	
	public UserCadresInfoVO getUserCadresInfo(UserCadresInfoVO userCadreInfo) throws Exception{
		Long totalUserAccessLevelCaders = cadreDAO.findTotalCadresByUserID(userCadreInfo.getUserID());
		userCadreInfo.setTotalCadres(totalUserAccessLevelCaders);
		
		userCadreInfo = getUserAccessRegions(userCadreInfo);
		userCadreInfo = getRegionLevelCadresCount(userCadreInfo);
		//userCadreInfo = getZeroCadresSizeByRegion(userCadreInfo);
		
		return userCadreInfo;
	}
	
	@SuppressWarnings("unchecked")
	public UserCadresInfoVO getUserAccessRegions(UserCadresInfoVO userCadreInfo) throws Exception{
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
			
			List cadreSizeZero4State = cadreDAO.findTotalStateZeroSizeCadres(sbStates.substring(0, sbStates.length()-1), 
					userCadreInfo.getUserID());
			long stateLevelZeroCadres = userCadreInfo.getUserAccessStates().size() - cadreSizeZero4State.size();//getZeroSize(cadreSizeZero4State);
			if(stateLevelZeroCadres > 0)
				regionLevelZeroCadres.put("STATE", stateLevelZeroCadres);
			
			List districts = cadreDAO.findDistrictsByStateID(sbStates.substring(0, sbStates.length()-1));
			StringBuilder sbDistricts = getFormatedData(districts,userAccessDistricts);
			userCadreInfo.setUserAccessDistricts(userAccessDistricts);
			
			List cadreSizeZero4District = cadreDAO.findTotalDistrictZeroSizeCadres(sbDistricts.substring(0, sbDistricts.length()-1), 
					userCadreInfo.getUserID());
			long districtLevelZeroCadres = userCadreInfo.getUserAccessDistricts().size() - cadreSizeZero4District.size();//getZeroSize(cadreSizeZero4District);
			if(districtLevelZeroCadres > 0)
				regionLevelZeroCadres.put("DISTRICT", districtLevelZeroCadres);
			
			List mandals = cadreDAO.findMandalsByDistrictID(sbDistricts.substring(0, sbDistricts.length()-1));
			StringBuilder sbMandals = getFormatedData(mandals,userAccessDistricts);
			userCadreInfo.setUserAccessMandals(userAccessMandals);

			List cadreSizeZero4Mandal = cadreDAO.findTotalMandalZeroSizeCadres(sbMandals.substring(0, sbMandals.length()-1), 
					userCadreInfo.getUserID());
			long mandalLevelZeroCadres = userCadreInfo.getUserAccessMandals().size() - cadreSizeZero4Mandal.size();//getZeroSize(cadreSizeZero4Mandal);
			if(mandalLevelZeroCadres > 0)
				regionLevelZeroCadres.put("MANDAL", mandalLevelZeroCadres);
			
			
		}else if("STATE".equals(userAccessType)){
			List districts = cadreDAO.findDistrictsByStateID(accessID);
			StringBuilder sbDistricts = getFormatedData(districts,userAccessDistricts);
			userCadreInfo.setUserAccessDistricts(userAccessDistricts);

			List cadreSizeZero4District = cadreDAO.findTotalDistrictZeroSizeCadres(sbDistricts.substring(0, sbDistricts.length()-1), userCadreInfo.getUserID());
			long districtLevelZeroCadres = userCadreInfo.getUserAccessDistricts().size() - cadreSizeZero4District.size();//getZeroSize(cadreSizeZero4District);
			if(districtLevelZeroCadres > 0)
				regionLevelZeroCadres.put("DISTRICT", districtLevelZeroCadres);
			
			List mandals = cadreDAO.findMandalsByDistrictID(sbDistricts.substring(0, sbDistricts.length()-1));
			StringBuilder sbMandals = getFormatedData(mandals,userAccessDistricts);
			userCadreInfo.setUserAccessMandals(userAccessMandals);			

			List cadreSizeZero4Mandal = cadreDAO.findTotalMandalZeroSizeCadres(sbMandals.substring(0, sbMandals.length()-1), 
					userCadreInfo.getUserID());
			long mandalLevelZeroCadres = userCadreInfo.getUserAccessMandals().size() - cadreSizeZero4Mandal.size();//getZeroSize(cadreSizeZero4Mandal);
			if(mandalLevelZeroCadres > 0)
				regionLevelZeroCadres.put("MANDAL", mandalLevelZeroCadres);
			
		}else if("DISTRICT".equals(userAccessType)){
			List mandals = cadreDAO.findMandalsByDistrictID(accessID);
			StringBuilder sbMandals = getFormatedData(mandals,userAccessDistricts);
			userCadreInfo.setUserAccessMandals(userAccessMandals);

			List cadreSizeZero4Mandal = cadreDAO.findTotalMandalZeroSizeCadres(sbMandals.substring(0, sbMandals.length()-1), 
					userCadreInfo.getUserID());
			long mandalLevelZeroCadres = userCadreInfo.getUserAccessMandals().size() - cadreSizeZero4Mandal.size();//getZeroSize(cadreSizeZero4Mandal);
			if(mandalLevelZeroCadres > 0)
				regionLevelZeroCadres.put("MANDAL", mandalLevelZeroCadres);
			
		}/*else if("MLA".equals(userAccessType) || "MP".equals(userAccessType)){
			List states = cadreDAO.findStatesByCountryID(accessID);
			
		}*/
		
		userCadreInfo.setRegionLevelZeroCadres(regionLevelZeroCadres);
		return userCadreInfo;
	}
	
	@SuppressWarnings("unchecked")
	public long getZeroSize(List cadreSizeZero4Region) throws Exception{
		long total =0;
		for(int i=0; i<cadreSizeZero4Region.size(); i++){
			Object[] objInfo = (Object[]) cadreSizeZero4Region.get(i);
			System.out.println(objInfo[0]+"="+objInfo[1]);
			try{
			long size = new Long(objInfo[1].toString()).longValue();
			if(size==0)
				total++;
			}catch(Exception e){
				e.printStackTrace();
				System.out.println(e.getMessage());
				throw e;
			}
		}
		return total;
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
}
