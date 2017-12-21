package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.DebateDetailsVO;
import com.itgrids.partyanalyst.dto.DebatePartyWiseCountVO;
import com.itgrids.partyanalyst.dto.DebateTopicVO;
import com.itgrids.partyanalyst.dto.DebateVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IDebateService {

	public DebateVO getDebateDetailsForSelected(Long debateId,Long stateId);
	
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
	
	//public List<SelectOptionVO> getDebateDetailsForSelectedDates(Date fromDate,Date toDate);
	
	public String saveDebateReportForPdf(final Long userId,final Long debateId,final String description,final String path,final Long stateId);
	
	public String deleteDebateReportUrl(String key);
	
	public List<SelectOptionVO> getCandidatesForDebate(List<Long> partyIds);
	
	//public DebateVO getDebateDetailsForSelectedCriteria(Date fromDate,Date toDate,String channel,String party,String candidate,String sortBy,String sort ,int minIndex,int maxIndex);
	
	public DebateVO getDebateDetailsForSelectedCriteria(Date fromDate,Date toDate,List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds,String sortBy,String sort ,int minIndex,int maxIndex,Long stateId);
	
	public List<SelectOptionVO> getDebateAnalysisBycandidateForScaling(Date fromDate , Date toDate,Long stateId);
	
	public List<SelectOptionVO> getDebateAnalysisByPartyForScaling(Date fromDate , Date toDate,Long stateId);
	
	public List<SelectOptionVO> getSearchriteriaForDebate(String searchString);
	
	 public List<SelectOptionVO> getDebateSMSQuestions(String fromDateStr , String toDateStr,Long stateId);
	 
	 public ResultStatus createCandidate(Long partyId,String name,Long stateId);
	 
	 public String deleteSelectedDEbate(Long debateId);
	 public List<DebateVO> getTotalAttendedDebatesOfCadre(Long tdpCadreId,Long stateId);
	 public List<DebatePartyWiseCountVO> getPartyWiseOverAllPerformance(Long cadreId,Long stateId);
	 public List<DebateTopicVO> getPartyWiseStrongAndWeakTopicAndCandidates(Long cadreId);
	 public Long getTotalAttendedDebatesOfCadreNew(Long tdpCadreId);
	 public void convertDebateContentToUnicode();
	 public List<DebateTopicVO> getEachCharacterWiseMaxScale();
	 
}
