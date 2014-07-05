package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyReportVO;
import com.itgrids.partyanalyst.dto.SurveyResponceVO;
import com.itgrids.partyanalyst.dto.UserBoothDetailsVO;

public interface ISurveyDataDetailsService 
{
	public ResultStatus saveSurveyUserType(String userTypeDescription,String userType);
	public ResultStatus saveSurveyUser(String firstName,String lastName,String userName,String password,String address,String mobileNo,Long userTypeId);
	public ResultStatus saveSurveyUserTabAssign(Long surveyUserId,String tabNo,String remarks,Date date);
	public ResultStatus saveSurveyUserBoothAssign(Long surveyUserId,Long constituencyId,List<Long> boothIds);
	public ResultStatus saveServeyUserRelationDetails(Long userTypeId,List<Long> surveyUserId,Long leaderId,Long constituencyId);
	public ResultStatus saveSurveyUserTrackingDetails(Long surveyUserId,String longitude , String latitude,Date date);
	public ResultStatus deactivateUser(Long userId,String remarks);
	public ResultStatus saveSurveyDataDetailsInfo(SurveyResponceVO inputResponse);
	public Long getUserDetailsForCheck(String userName,String password);
	public List<UserBoothDetailsVO> getAssignedBoothsDetailsByConstituencyIdAndUserId(Long constituencyId,Long userId);
	public List<GenericVO> getUserTypes();
	public List<GenericVO> getSurveyUsersByUserType(Long userTypeId);
	public List<SurveyReportVO> getDayWisereportDetailsByConstituencyId(Long constituencyId,String startDate,String endDate,Long userTypeId);
	public List<GenericVO> getSurveyUsersByLeades(Long leaderId,Long constituencyId);
	public List<GenericVO> getConstituencyWiseLeaders();
	public List<SelectOptionVO> getAllAssemblyConstituenciesByStateId();
	public List<SurveyReportVO> getBoothWiseUserSamplesDetailsByDates(Long userId,String startDate,String endDate);
	public List<SurveyReportVO> getReportForVerification(Long boothId);
	public List<GenericVO> getLatLongForUserTrackung(Long surveyUserId,Date date);
	public List<SurveyResponceVO> getLatLongForSurveyDetails(Long surveyUserId,Date date);
	public List<SurveyResponceVO> getDetailsForVerifier(Long surveyUserId,Long boothId);
	public Object[] auhenticateUserandGetUserType(String userName,String password);


	public ResultStatus saveSurveyUserTrackingDetails(Long surveyUserId,Date date,String longitude,String latitude);
	public List<GenericVO> getSurveyUsersForAssignToLeader(Long userTypeId);
	public ResultStatus updateServeyUserRelationDetails(Long userTypeId,List<Long> surveyUserIds,Long leaderId,Long constituencyId);
	public List<GenericVO> releaseLeadersWithUser(Long leaderId);
	//public List<SurveyResponceVO> getSurveyUserBoothsAndVoterDetails(Long surveyUserId);
	public List<UserBoothDetailsVO> getBoothDetailsByConstituencyId(Long constituencyId);
	public String saveVerifiedRecordsDetails(final List<Long> verifierIds);


}
