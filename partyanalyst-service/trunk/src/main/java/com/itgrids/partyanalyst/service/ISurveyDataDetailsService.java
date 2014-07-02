package com.itgrids.partyanalyst.service;

import java.util.Date;

import java.util.List;

import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SurveyResponceVO;

public interface ISurveyDataDetailsService 
{
	public ResultStatus saveSurveyUserType(String userTypeDescription);
	public ResultStatus saveSurveyUser(String firstName,String lastName,String userName,String password,String address,String mobileNo,Long userTypeId);
	public ResultStatus saveSurveyUserTabAssign(Long surveyUserId,String tabNo,String remarks,Date date);
	public ResultStatus saveSurvetUserBoothAssign(Long surveyUserId,Long constituencyId,Long panchayatId,List<Long> boothIds);
	public ResultStatus saveServeyUserRelationDetails(Long userTypeId,Long surveyUserId,Long leaderId,Long constituencyId);
	public ResultStatus saveSurveyUserTrackingDetails(Long surveyUserId,String longitude , String latitude,Date date);
	public ResultStatus deactivateUser(Long userId,String remarks);
	public ResultStatus saveSurveyDataDetailsInfo(List<SurveyResponceVO> surveyResponceList);
	public Long getUserDetailsForCheck(String userName,String password);
	public List<GenericVO> getUserTypes();
	public List<GenericVO> getSurveyUsersByUserType(Long userTypeId);
	public List<GenericVO> getSurveyUsersByLeades(Long leaderId,Long constituencyId);
	public List<GenericVO> getConstituencyWiseLeaders();
}
