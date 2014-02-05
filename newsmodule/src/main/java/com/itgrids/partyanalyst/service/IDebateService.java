package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.DebateDetailsVO;
import com.itgrids.partyanalyst.dto.DebateVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IDebateService {

	public DebateVO getDebateDetailsForSelected(Long debateId);
	
	public ResultStatus saveDebateDetails(final DebateDetailsVO debateDetailsVO);
	
	public ResultStatus saveNewRole(Long userId, String newRole);
	
	public ResultStatus saveNewCharacteristic(Long userId, String newRole);
	
	public ResultStatus saveNewDebateQuestion(Long userId, String newRole);
	
	public ResultStatus insertChannelDetails(Long userId, String newRole);
	
	public ResultStatus insertObserverDetails(Long userId, String newRole);
	
	public List<SelectOptionVO> getChannelDetails();
	
	public List<SelectOptionVO> getTelecastTimeDetails();
	
	public List<SelectOptionVO> getObserverDetails();
	
	public List<SelectOptionVO> getDebateQuestionDetails();
	
	public List<SelectOptionVO> getDebateSmsQuestionDetails();
	
	public List<SelectOptionVO> getDebateParticipantRoleDetails();
	
	public List<SelectOptionVO> getCharacteristicsDetails();
	
	public List<SelectOptionVO> getRolesList();
	
	public String genearetUrl(Long reportId,Long userId,String path);
	
	public List<SelectOptionVO> getDebateDetailsForSelectedDates(Date fromDate,Date toDate);
	
	public String saveDebateReportForPdf(final Long userId,final Long debateId,final String description,final String path);
	
	public String deleteDebateReportUrl(String key);
	
	public List<SelectOptionVO> getCandidatesForDebate(Long partyId);
	
	public List<SelectOptionVO> getDebateDetailsForSelectedCriteria(Date fromDate,Date toDate,String channelId,String partyId,String candidateId);
	
	public List<SelectOptionVO> getDebateAnalysisBycandidateForScaling(Date fromDate , Date toDate);
	
	public List<SelectOptionVO> getDebateAnalysisByPartyForScaling(Date fromDate , Date toDate);
	
	public List<SelectOptionVO> getSearchriteriaForDebate(String searchString);
}
