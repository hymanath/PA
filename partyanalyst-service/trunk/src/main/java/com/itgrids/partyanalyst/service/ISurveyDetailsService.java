package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyReportVO;

public interface ISurveyDetailsService {

	public List<SelectOptionVO> getAllSurveys();
	public ResultStatus saveSurveyDetails(Long userId,Long surveyId);
	public Map<String,String> getSurveyDetailsByRegion(Long regionId);
	public List<GenericVO> getConstituencyWiseLeaders(Long constituencyId);
	public List<SurveyReportVO> getSurveyUserConstituencyDetails(Long surveyUserId);
	public ResultStatus unTagConstituencyForAUser(Long userId,Long constituencyId);
	public List<GenericVO> getAssignedSurveyUsersForWebMontringTeam(Long userId);
	public List<GenericVO> getNotStartedUsersDetails(Long webMonitorUserId,Long constituencyId);

	public GenericVO getSurveyStatusBoothList(Long constituencyId);
}
