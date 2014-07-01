package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.ResultStatus;

public interface ISurveyDataDetailsService 
{
	public ResultStatus saveSurveyUserType(String userTypeDescription);
	public ResultStatus saveSurveyUser(String firstName,String lastName,String userName,String password,String address,String mobileNo,Long userTypeId);
	public ResultStatus saveSurveyUserTabAssign(Long surveyUserId,String tabNo,String remarks,Date date);
	public ResultStatus saveSurvetUserBoothAssign(Long surveyUserId,Long constituencyId,Long panchayatId,List<Long> boothIds);
	public ResultStatus saveServeyUserRelationDetails(Long userTypeId,Long surveyUserId,Long leaderId,Long constituencyId);
	public ResultStatus saveSurveyUserTrackingDetails(Long surveyUserId,String longitude , String latitude,Date date);
	public ResultStatus deactivateUser(Long userId,String remarks);
}
