package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.DebateAnalysisVO;
import com.itgrids.partyanalyst.dto.DebateCandidateCharcVO;
import com.itgrids.partyanalyst.dto.DebatePartyWiseCountVO;
import com.itgrids.partyanalyst.dto.DebateTopicVO;

public interface IDebateAnalysisService {
	//public DebateCandidateCharcVO getPartyWiseCandidateCharacteristicsDetails();
	public DebateCandidateCharcVO getPartyWiseCandidateCharacteristicsDetails(Date fromDate, Date toDate, List<Long> channelIds, List<Long> partyIdsList,  List<Long> candidatesIds,Long stateId);
	//public List<DebateAnalysisVO> partyWiseCandidatePerformance();
	//public List<DebateTopicVO> getPartyCandidatePerfortmanceTopicWise();
	public List<DebateTopicVO> getPartyCandidatePerfortmanceTopicWise(Date fromDate, Date toDate, List<Long> channelIds, List<Long> partyIdsList,  List<Long> candidatesIds ,Long stateId);
	//public List<DebatePartyWiseCountVO> getPartyWiseOverAllPerformance();
	//public List<DebateTopicVO> getPartyWiseStrongAndWeakTopicAndCandidates();	
	public List<DebateAnalysisVO> partyWiseCandidatePerformance(Date fromDate, Date toDate, List<Long> channelIds, List<Long> partyIdsList,  List<Long> candidatesIds,Long stateId);
	public List<DebatePartyWiseCountVO> getPartyWiseOverAllPerformance(Date fromDate, Date toDate, List<Long> channelIds, List<Long> partyIdsList,  List<Long> candidatesIds,Long stateId);
	//public Map<Long,DebatePartyWiseCountVO> getPartyWiseTotalDebatesAndScalesService(Date fromDate, Date toDate, List<Long> channelIds, List<Long> partyIdsList,  List<Long> candidatesIds);
	
	public List<DebateTopicVO> getPartyWiseStrongAndWeakTopicAndCandidates(Date fromDate, Date toDate, List<Long> channelIds, List<Long> partyIdsList,  List<Long> candidatesIds,Long stateId);
}
