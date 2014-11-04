package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.AppDbDataVO;
import com.itgrids.partyanalyst.dto.CadreRegisterInfo;

public interface ICadreDashBoardService {
	
	public List<CadreRegisterInfo> getWorkStartedConstituencyCount();

	public List<CadreRegisterInfo> getDashBoardBasicInfo();
	
	public  List<CadreRegisterInfo> getRecentlyRegisteredCadresInfo();
	
	public List<CadreRegisterInfo> getAssemblyWiseCompletedPercentage(Long assemblyId,Long stateId);
	
	public List<CadreRegisterInfo> getDistrictWiseCompletedPercentage(Long districtId,Long stateId);
	
	public CadreRegisterInfo getWorkingMembersInfo(String hours);
	
	public List<CadreRegisterInfo> getCandidateDataCollectionInfo(Long locationType,List<Long> locationIds,Date fromDate,Date toDate);
		
	public List<CadreRegisterInfo> getDetailsForConstituency(Long constituencyId);
	
	public List<CadreRegisterInfo> getDetailsForDistricts(Long districtId);
		
	public List<CadreRegisterInfo> getAssemblyConstituencies(String type);
	
	public List<CadreRegisterInfo> getPanchayatsInConstituencies(Long constituencyId);
	
	public List<CadreRegisterInfo> getBoothsInConstituencies(Long constituencyId);
	
	public List<CadreRegisterInfo> getStateWiseRegistrationInfo(List<Long> stateIds,String fromDate,String toDate);
	
	public List<CadreRegisterInfo> getLocationWiseRegistrationInfo(List<Long> ids,String type,String fromDate,String toDate,boolean reqOthers);
	
	public List<CadreRegisterInfo> getCastGroupWiseCadreCount(Long constituencyId,String type);
	
	public List<CadreRegisterInfo> getConstituencyWiseAgeRangeCount(Long constituencyId);
	
	public List<CadreRegisterInfo> getConstituencyWiseGenderCadreCount(Long constituencyId);
	
	public List<CadreRegisterInfo> getConstituencyWiseCastCadreCount(Long constituencyId);
	
	public List<CadreRegisterInfo> getDistrictWiseAgeRangeCount(Long districtId);
	
	public List<CadreRegisterInfo> getDistrictWiseGenderCadreCount(Long districtId);
	
	public List<CadreRegisterInfo> getDistrictWiseCastCadreCount(Long districtId);
	
	public CadreRegisterInfo getRegisteredDetailsByLocation(String locationType,List<Long> locationIds,int startIndex,int maxIndex,String orderBy,String orderType);
	
	public List<CadreRegisterInfo> getDataForSubLocations(String fromLocation,Long fromLocationId,String toLocation,String fromDateStr, String toDateStr,Long constituencyId);
	
	public AppDbDataVO getAllVersionsOfAnApp(String appName,Double currentVerson,boolean includeTest);
	
	public AppDbDataVO getAllUpdatesByVersion(String appName,Double version);

	public  List<CadreRegisterInfo> getRecentlyRegisteredCadresInfo(Integer startIndex,Integer maxIndex);
	
	public CadreRegisterInfo getWorkingMembersDetails(String hours);
}
