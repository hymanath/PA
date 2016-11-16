package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.AppDbDataVO;
import com.itgrids.partyanalyst.dto.CadreBasicInformationVO;
import com.itgrids.partyanalyst.dto.CadreDashboardVO;
import com.itgrids.partyanalyst.dto.CadreDataSourceTypeVO;
import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.RegistrationCountVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyTransactionVO;
import com.itgrids.partyanalyst.dto.WSResultVO;

public interface ICadreDashBoardService {
	
	

	public List<CadreRegisterInfo> getDashBoardBasicInfo(String accessType,Long accessValue);
	
	public  List<CadreRegisterInfo> getRecentlyRegisteredCadresInfo();
	
	public List<CadreRegisterInfo> getAssemblyWiseCompletedPercentage(Long assemblyId,Long stateId, String accessType, String accessValue, String percType);
	
	public List<CadreRegisterInfo> getDistrictWiseCompletedPercentage(Long districtId,Long stateId, String accessType, String accessValue, String percType);
	
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

	public List<SurveyTransactionVO> getLocationswiseUsersList(String usersType,String areaType, Long stateTypeId,String FdateStr, String TdateStr);
	public WSResultVO gettingUserDetailsByLocation(String location,Long locationId,String type,Date date);
	//public SurveyTransactionVO getDaywiseWebUserDetails(Long userId,String FdateStr, String TdateStr);
	public WSResultVO getAllParliamentsForAssembly();
	public void getAnalysisData();
	public List<CadreRegisterInfo> getCandidateDataCollectionInfoForOnlineUsers(Long locationType,List<Long> locationIds,Date fromDate,Date toDate,String sourceType,Long stateTypeId);
	public List<CadreRegisterInfo> getCandidateDataCollectionInfo(Long locationType,List<Long> locationIds,Date fromDate,Date toDate,String sourceType);
	public List<GenericVO> getInactiveUsersListDetails(String hours, String accessType, String accessValue);
	public String updateTabAllocationDetails(Long authId,String cause,Long userId);
	
	public List<CadreRegisterInfo> getAuthDetails(Long id,String variable);
	
	public String registerAllUsers(RegistrationVO user);

	public List<CadreRegisterInfo> getLocationWiseAgeRangeAndGenderCount(String type,Long stateId,Long constituencyId);
	
	public List<CadreRegisterInfo> getRegisteredCountByUserForHourWise(Date fromDate,Date toDate);
	
	public List<CadreRegisterInfo> getDistrictWiseAgeRangeCountByAccess(Long districtId,String accessType,String accessValue);
	public List<CadreRegisterInfo> getCastGroupWiseCadreCountByAccess(Long districtId,String accessType,String accessValue);
	public List<CadreRegisterInfo> getDistrictWiseGenderCadreCountByAccess(Long districtId,String accessType,String accessValue);
	public List<CadreRegisterInfo> getDistrictWiseCastCadreCountByAccess(Long districtId,String accessType,String accessValue);
	
	public CadreRegisterInfo getTotalRegisterCadreInfo();
	
	public List<CadreRegisterInfo> getGHMCRegisteredCountDetails(String type);
	 public CadreRegisterInfo getDashBoardBasicRegistrationInfo(String accessType,Long accessValue,Long stateId);
	 public SurveyTransactionVO getDaywiseWebUserDetails(Long userId,String FdateStr, String TdateStr,Long memberTypeId);
	 public List<CadreDashboardVO> get2016LocationWiseRegisteredCounts(String type,Long locationScopeId,String locationType);
	 public List<CadreDataSourceTypeVO> getDataSourceTypeWiseRegisteredDetails();
	 public List<List<CadreDashboardVO>> get2016LocationWiseRegisteredCountsForPreviligedUser(Long userId, String locationType, String type);
	 public List<RegistrationCountVO> getRegistrationCountDtls(String location, Long constId, String scope);
	 public List<IdAndNameVO> getCadreRegistrationCountByConstituency(Long constituencyId,String fromDateStr,String toDateStr);
	 public List<IdAndNameVO> getDataSourceTypeWiseCountsByType(String type);
	 public List<CadreDashboardVO> get2016LocationWiseRegisteredCountsForConstitunecy(String type,Long locationScopeId,String locationType,Long districId);
}
