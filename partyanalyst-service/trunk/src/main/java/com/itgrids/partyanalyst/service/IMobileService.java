package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreVoterVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.MobileAppUserDetailsVO;
import com.itgrids.partyanalyst.dto.MobileAppUserVoterVO;
import com.itgrids.partyanalyst.dto.MobileUserVO;
import com.itgrids.partyanalyst.dto.MobileVO;
import com.itgrids.partyanalyst.dto.PollManagementSummaryVO;
import com.itgrids.partyanalyst.dto.PollManagementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TabDetailsVO;
import com.itgrids.partyanalyst.dto.VoterDetailsVO;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserLocationTrackingVo;

public interface IMobileService {
	
 //public ResultStatus createDataDumpFileForSelectedConstituency(Long constituencyId,String path);
 public ResultStatus createDataDumpFileForSelectedConstituency(Long constituencyId,String path,final RegistrationVO reVo);
 
 public List<SelectOptionVO> getConstituencyList();
 
 public ResultStatus saveUserData(final RegistrationVO registrationVO);
 
	public ResultStatus checkAuthenticateUserAndUpdateLastAuthorisedTime(String userId,String macAddressId);
	
	public ResultStatus sendSmsToMobileAppUser(String mobileNo,Long mobileAppuserId,String accessKey,Long userID,String type);
	
	public List<SelectOptionVO> getMobileAppUsers();
	
 	public List<RegistrationVO> getMobileAppUserDetails();
 	
	public ResultStatus enableOrdisableAccessByUniqueCode(List<Long> uniqueCodes,String type);
	
	public List<RegistrationVO> getMobileAppUserDetailInfo(Long userId);
	
	public Long saveSuperAdminInfoInMobileAppUser(String uname,String pwd,String uniqueCode);
	
  	public List<SelectOptionVO> getSuperAdminMobileAppUsers();
  	
  	public List<SelectOptionVO> getPingDetails(Long mobileAppUserId);
  	
	public List<RegistrationVO> getMobileAppUserPopulateData(Long userId);
  	public VoterDetailsVO getVoterDetailsBasedOnVoterId(String voterCardNo);

	
	public ResultStatus createDataDumpFileForAConstituency(RegistrationVO reVo);
	
	public ResultStatus createDataDumpFileForAParliamnetConstituency(RegistrationVO reVo);
	public List<SelectOptionVO> getPCConstituencyList();
	
	public String replaceSpecialChars(String str);
	
	public ResultStatus getMobileAppLastAuthorisedTime();
	
	public ResultStatus populateVoterData();
	//public MobileVO getIvrMobileNumbers(Long scopeId,List<Long> locationIDs,Long fileFormatVal,int maxIndex,boolean multipleFiles,int NoOfFile);
	public List<SelectOptionVO> getDistrictsList(Long stateId)	;
	public List<SelectOptionVO> getConstituencyList(List<Long> distictIds);
	public MobileVO getMobileNumbersByLocations(Long scopeId,List<Long> locationIds,Long fileFormatVal,int maxIndex,int checkedTypeVal,int noOfFile,List<String> checkedLevels);
	public List<SelectOptionVO> getTehsilList(List<Long> distictIds);
	public List<SelectOptionVO> getpcconstituencyList(Long regionId);
	public MobileVO getLocationWiseMobileNumbersCountByRegionAndScope(Long scopeId,String regionType);
	public ResultStatus resetAllMobileNos();
	public ResultStatus createCadreDataSqliteFileForAParliamnetConstituency(RegistrationVO reVo);
	public ResultStatus sendSmsToUserForUpdations(String message,String mobileNo);
	public ResultStatus createSurveySqliteFileForAParliamnetConstituency(RegistrationVO reVo);
	
	//public MobileAppUserDetailsVO getUserWiseDivisionSummary(Long locationId, String locationType, String startDate, String endDate,List<String> userType);
	public List<MobileUserVO> locationWiseOverView(String startDateString,String endDateString,List<Long> locationIds,String locationType,List<String> userTypes);
	public MobileUserVO overAllDivisionsSummary(String startDateString,String endDateString,List<Long> locationIds,List<String> userTypes);
	public List<TabDetailsVO> showMapForMobileAppUserVoter(Long userId,Long divisonId,List<String> dateStrList);
	public String saveUserLocationData(List<UserLocationTrackingVo> userLocationDetails);
	public List<TabDetailsVO> getUserTrackingDetails(Long userId);
	 public List<IdNameVO> getAssignedWardsByUser(Long userId);
	public PollManagementVO overAllPollManagementSummary( List<Long> locationIds);
	public PollManagementVO overAllPollManagementSummaryByDivisionOrWard(Long wardId);
	public List<PollManagementVO> getNotYetPolledMembers(String resultType,Long locationId);
	public List<PollManagementSummaryVO> divisionWiseVotingActivity(List<Long> locationIds);
	public List<PollManagementSummaryVO> boothWiseVotingActivity(Long wardId);
	public PollManagementVO getDivisonOverView(MobileAppUserVoterVO inputVO);
	public List<CadreVoterVO> getVoterInfoForBooth(MobileAppUserVoterVO inputVO);
	
	public ResultStatus create2016CadreDataSqliteFileForAParliamnetConstituency(RegistrationVO reVo);
	public MobileAppUserDetailsVO getUserWiseDivisionSummary(Long locationId,Long levelId,String startDateString, String endDateString,Long publicationDateId,Long electionYearId,List<String> userType);
	public List<LocationWiseBoothDetailsVO> getAssignedWardsByUser(String accessValue,Long userId);
	public List<TabDetailsVO> showMapForMobileAppUserVoter(Long userId,Long locationId,Long levelId,List<String> dateStrList);
}
