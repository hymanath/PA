package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.ConstituencyDetailReportVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyReportVO;
import com.itgrids.partyanalyst.dto.SurveyResponceVO;
import com.itgrids.partyanalyst.dto.UserBoothDetailsVO;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserLocationTrackingVo;

public interface ISurveyDataDetailsService 
{
	public ResultStatus saveSurveyUserType(String userTypeDescription,String userType);
	public ResultStatus saveSurveyUser(String firstName,String lastName,String userName,String password,String address,String mobileNo,Long userTypeId);
	//public ResultStatus saveSurveyUserTabAssign(Long surveyUserId,String tabNo,String remarks,Date date);
	public ResultStatus saveSurveyUserTabAssign(Long surveyUserId,List<BasicVO> tabsinfoList);
	public ResultStatus saveSurveyUserBoothAssign(Long surveyUserId,Long constituencyId,List<Long> boothIds,String saveSurveyUserBoothAssign);
	public ResultStatus saveServeyUserRelationDetails(Long userTypeId,List<Long> surveyUserId,Long leaderId);
	public ResultStatus saveSurveyUserTrackingDetails(Long surveyUserId,String longitude , String latitude,Date date);
	//public ResultStatus deactivateUser(Long userId,String remarks);
	public ResultStatus saveSurveyDataDetailsInfo(SurveyResponceVO inputResponse);
	public Long getUserDetailsForCheck(String userName,String password);
	public List<UserBoothDetailsVO> getAssignedBoothsDetailsByConstituencyIdAndUserId(Long constituencyId,Long userId);
	public List<GenericVO> getUserTypes();
	public List<GenericVO> getSurveyUsersByUserType(Long userTypeId, Long constituency);
	public List<SurveyReportVO> getDayWisereportDetailsByConstituencyId(Long constituencyId,String startDate,String endDate,Long userTypeId);
	public List<SurveyReportVO> getSurveyUsersByLeades(Long leaderId,Long constituencyId);
	public List<GenericVO> getConstituencyWiseLeaders();
	public List<SelectOptionVO> getAllAssemblyConstituenciesByStateId();
	public List<SurveyReportVO> getBoothWiseUserSamplesDetailsByDates(Long userId,String startDate);
	public List<SurveyReportVO> getReportForVerification(Long boothId,String typ);
	public List<GenericVO> getLatLongForUserTrackung(Long surveyUserId,Date date);
	public List<SurveyResponceVO> getLatLongForSurveyDetails(Long surveyUserId,Date date);
	public List<SurveyResponceVO> getDetailsForVerifier(Long surveyUserId,Long boothId);
	public Object[] auhenticateUserandGetUserType(String userName,String password);


	public ResultStatus saveSurveyUserTrackingDetails(UserLocationTrackingVo userLocationTrackingVo);
	public List<GenericVO> getSurveyUsersForAssignToLeader(Long userTypeId);
	public ResultStatus updateServeyUserRelationDetails(Long userTypeId,List<Long> surveyUserIds,Long leaderId,Long dummyLeaderId);
	public List<GenericVO> releaseLeadersWithUser(Long leaderId,Long userType);
	//public List<SurveyResponceVO> getSurveyUserBoothsAndVoterDetails(Long surveyUserId);
	public List<UserBoothDetailsVO> getBoothDetailsByConstituencyId(Long constituencyId);
	public String saveVerifiedRecordsDetails(final List<Long> verifierIds);
	public List<SurveyReportVO> getDayWiseReportByConstituencyIdAndUserType(Long constituencyId, String startDate, String endDate,Long userTypeId,List<Long> boothIds,List<Long> userIds);

	public List<GenericVO> getSurveyConstituencyList();
	
	public List<GenericVO> getSurveyConstituencyLeadersList(Long leaderId);
	
	public GenericVO releaseLeadersWithUserandTabsList(Long leaderId);
	
	public ResultStatus saveSurveyUserTabAssign(List<BasicVO> tabsInfoList);
	
	public List<SelectOptionVO> getAllAssignedConstituency(Long userTypeId);
	
	public List<SurveyReportVO> getAllAssignedConstituenciesUsers(Long userTypeId);
	
	public ResultStatus assignConstituencyForAUser(Long userId,Long constituencyId);
	public ResultStatus deactivateUser(Long userId,String remarks,Long userTypeId);
	public ResultStatus deactiveSurveyLeader(final Long userId,final String remarks,final Long userTypeId,final Long dummyLeadId);
	
	public ConstituencyDetailReportVO getCosntituencyWiseReportByContiId(Long constituencyId);
	
	public ConstituencyDetailReportVO getBoothWiseDetails(Long boothId,Long constituencyId);
	
	public List<SelectOptionVO> getLatLongForSurveyUsersByConstituency(Long constituencyId,Date date,List<Long> userId);
	
	public List<Long> getDataCollectedCount(Long userId,Long boothId);
	
	public List<GenericVO> getExistedSurveyUsersByUserType(Long userTypeId);
	
	public List<GenericVO> getExistedConstituenciesDetailsByUserId(Long userId);

	public List<SelectOptionVO> getConstituencyListByDistrictId(Long districtId);
	
	public List<SelectOptionVO> getAssignedBoothDetailsByuserId(Long constituencyId,Long surveyUserId);
	
	public List<SurveyReportVO> getSurveyVotersList(Long constituencyId, Long boothId,Long leaderId,String searcyDate,Long userTypeId,Long casteStateId);
	
	public ResultStatus saveSurveyCallStatusDetils(Long userId,List<SurveyReportVO> verifiedList);
	
	public List<SelectOptionVO> getSurveyStartedConstituencyList();
	public List<SelectOptionVO> getsurveyuserConstituencies();
	//public List<SurveyReportVO> getSurveyDetailsForConstituency(Long constituencyId,Long userTypeId);
	public List<GenericVO> getSurveyUserNameAndPasswordByLeader(Long leaderId);
	
	public List<SurveyReportVO> getSurveyUserDetailsByConstituencies(Long constituencyId,Date date,List<Long> userIds);
	
	//public List<SurveyReportVO> getSurveyDetailsForConstituency(Long constituencyId,Long userTypeId,String date,List<Long> userIds);
	
	public GenericVO getAlreadyAssignTabsListForLeader(Long leaderId);
	
	public List<GenericVO> getSurveyConstituencyUsersList(Long constituencyId);
	public List<GenericVO> getAssignedUsersOfAConstituency(Long constituencyId);
	public List<GenericVO> getAllWebMonitoringUsersDetails();
	public String saveWebMonioringAssignDetails(final Long webMonitorId,final List<Long> userIds);
	public SurveyReportVO getTotalCasteCollectedCount();
	public List<SurveyReportVO> getCasteCollectedCountsForDates(String date);
	public List<SurveyReportVO> getSurveyDetailsForConstituency(Long constituencyId,Long userTypeId,String fromDate,List<Long> userIds,String toDate);

	public List<GenericVO> getSurveyUsersByLeader(Long leaderId);
	
	public ResultStatus releaseTabsAndBoothsBySurveyUserId(final  Long surveyUserId,final  Long dummyLeaderId,final String remark);

	public List<GenericVO> getSurveyUsersByUserType(Long userTypeId);

	public List<Long> getUsersForLeader(Long leaderId);
	  public String roundTo2DigitsFloatValue(Float number);
	  
		public List<SurveyReportVO> getAllUsersDetilsByUserIdsForSelectedDate(Long constituencyId,Date date,List<Long> userIds);
	public ResultStatus saveSurveyCallStatusMobileDetils(Long userId,Long voterId,Long boothId,Long surveyUserId,Long userTypeId, String  selectedMobileType, Long  mobileStatusId);
	
	public List<SelectOptionVO> getAssemblyConstituenciesByStateId(Long stateType, Long stateId);
	public List<SelectOptionVO> getAssemblyOfLoggedUser(String accessValue,String accessType);

}
