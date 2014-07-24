package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.DcDvCollectedDataVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.PanchayatHamletsCountVo;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyReportVO;
import com.itgrids.partyanalyst.dto.VerificationCompVO;

public interface ISurveyDetailsService {

	public List<SelectOptionVO> getAllSurveys();
	public ResultStatus saveSurveyDetails(Long userId,Long surveyId);
	public Map<String,String> getSurveyDetailsByRegion(Long regionId);
	public List<GenericVO> getConstituencyWiseLeaders(Long constituencyId);
	public List<SurveyReportVO> getSurveyUserConstituencyDetails(Long surveyUserId);
	public ResultStatus unTagConstituencyForAUser(Long userId,Long constituencyId);
	public List<GenericVO> getAssignedSurveyUsersForWebMontringTeam(Long userId);
	public List<GenericVO> getNotStartedUsersDetails(Long webMonitorUserId,Long constituencyId);
	public List<VerificationCompVO> checkForVerifierData(List<Long> boothId);
	
	
	public GenericVO getSurveyStatusBoothList(Long constituencyId);
	public  PanchayatHamletsCountVo   getSurveyDataCountForHamletsByPanchayats(Long panchayatId);

	public List<GenericVO> getUserForAssignedLeader(Long leaderId, Long userTypeId);
	 public List<SurveyReportVO> getSurveyDetailsByBoothIds(List<Long> boothIds);
	 public List<SurveyReportVO> getPanchayatsStatusWiseDataByConstituency(Long constituencyId,String status);
	 public SurveyReportVO getPanchayatsStatusCountByConstituency(Long constituencyId);
	 public List<VerificationCompVO> checkForWebMonitorData(Long boothId);
	 public List<GenericVO> getBoothsInCallStatus(Long constituencyId);
	 public List<GenericVO> getBoothsInSurveyDetailsInfo(Long constituencyId);
	 public List<DcDvCollectedDataVO> getDcAndDvByConstituencyByUser(Long surveyUserId,Long userTypeId,Date fromDate,Date toDate);
	 public List<GenericVO> getDcorDvUsersByConstituency(Long userTypeId);
	
	 
}
