package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.AppDbDataVO;
import com.itgrids.partyanalyst.dto.CadreBasicInformationVO;
import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyTransactionVO;

public interface ICadreDashBoardService {
	
	

	public List<CadreRegisterInfo> getDashBoardBasicInfo(String accessType,Long accessValue);
	
	public  List<CadreRegisterInfo> getRecentlyRegisteredCadresInfo();
	
	public List<CadreRegisterInfo> getAssemblyWiseCompletedPercentage(Long assemblyId,Long stateId, String accessType, String accessValue);
	
	public List<CadreRegisterInfo> getDistrictWiseCompletedPercentage(Long districtId,Long stateId, String accessType, String accessValue);
	
	public CadreRegisterInfo getWorkingMembersInfo(String hours,String accessType,Long accessValue);
	
	public List<CadreRegisterInfo> getCandidateDataCollectionInfo(Long locationType,List<Long> locationIds,Date fromDate,Date toDate);
		
	public List<CadreRegisterInfo> getDetailsForConstituency(Long constituencyId);
	
	public List<CadreRegisterInfo> getDetailsForDistricts(Long districtId);
		
	public List<CadreRegisterInfo> getAssemblyConstituencies(String type);
	
	public List<CadreRegisterInfo> getPanchayatsInConstituencies(Long constituencyId);
	
	public List<CadreRegisterInfo> getBoothsInConstituencies(Long constituencyId);
	
	public List<CadreRegisterInfo> getStateWiseRegistrationInfo(List<Long> stateIds,String fromDate,String toDate,Long userCountValue);
	
	public List<CadreRegisterInfo> getLocationWiseRegistrationInfo(List<Long> ids,String type,String fromDateStr, String toDateStr,boolean reqOthers,Long userCountValue);
	
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

	public  List<CadreRegisterInfo> getRecentlyRegisteredCadresInfo(Integer startIndex,Integer maxIndex,String accessType,Long accessValue);
	public CadreRegisterInfo getRegisteredInfo(Long locationId,String locationType,int startIndex,int maxIndex);
	
	public CadreRegisterInfo getWorkingMembersDetails(String hours);

	public List<CadreRegisterInfo> getWorkStartedConstituencyCount(String accessType,Long accessValue);

	public String getStateBasedOnLocation(String AccessType,String accessValue);
	public List<CadreBasicInformationVO> getConstituencySurveyUsers(Long constituencyId);
	public ResultStatus saveCadreSurveyUserAssignInfo(CadreRegisterInfo vo);
	public List<CadreRegisterInfo> getAssignedUsersForCadresurveyUser(Long constituencyId,Long userId);
	
	public List<CadreRegisterInfo> getSlowUserDetails(Long locationType,List<Long> locationIds,Date fromDate,Date toDate,Long recordsCount);

	public List<SelectOptionVO> getUserTrackingRsults(String searchType,Long stateTypeId,String areaType,String FdateStr, String TdateStr);
	public List<SurveyTransactionVO> getLocationswiseUsersList(String usersType,String areaType, Long stateTypeId,String FdateStr, String TdateStr);
	
}
