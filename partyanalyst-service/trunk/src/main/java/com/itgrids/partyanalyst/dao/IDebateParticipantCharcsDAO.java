package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DebateParticipantCharcs;

public interface IDebateParticipantCharcsDAO extends GenericDao<DebateParticipantCharcs, Long>{

	public List<Object[]> getDebateCharcsDetails(Long debateId);
	public List<Object[]> getDebatePerformanceCount();
	public List<Object[]> getDebatePerformanceCountCharcs();
	public List<Object[]> getPartyWiseTotalDebatesAndScales();
	public List<Object[]> getPartyWiseDebatePartiCharsCount();
	public List<Object[]> getPartyWiseEachDebateCharsCount();
	public List<Object[]> getPartyCandidateDetailsTopicWise();
	public List<Object[]> getTopicWiseStrongOrWeakCandidates(String sortOrder);
	
	public List<Object[]> getTopicWiseStrongOrWeakCandidatesForSelection(Date fromDate,Date toDate, List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds);
	
	public List<Object[]> getPartyCandidateDetailsTopicWiseForSelection(Date fromDate,Date toDate, List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds,Long stateId);
	
	public List<Object[]> getDebatePerformanceCountsForSelection(Date fromDate,Date toDate,List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds,Long stateId);
	
	public List<Object[]> getDebatePerformanceCountCharForSelection(Date fromDate,Date toDate,List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds,Long stateId);
	
	public List<Object[]> getPartyWiseTotalDebatesAndScalForSelection(Date fromDate, Date toDate, List<Long> channelIds, List<Long> partyIds,  List<Long> candidateIds,Long stateId);
	
	public List<Object[]> getPartyWiseDebatePartiCharsCountsForSelection(Date fromDate,Date toDate,List<Long> channelIds,List<Long>  partyIds,List<Long>  candidateIds,Long stateId);
	
	public List<Object[]> getPartyWiseEachDebateCharsCounttsForSelection(Date fromDate,Date toDate,List<Long> channelIds,List<Long>  partyIds,List<Long>  candidateIds,Long stateId);
	
	public List<Object[]> getTopicWiseStrongOrWeakCandidatsForSelection(Date fromDate,Date toDate, List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds,String sortOrder,Long stateId);
	public List<Object[]> getPartyWiseScalesOfEachCharecter(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList);
	public List<Object[]> getPartywiseCandidateScaling(Date startDate,Date endDate,String searchType,String state,List<Long> debateParticipantLocationIdList,List<Long> debateLocationIdList);
	public List<Object[]> getPartywiseCandidateCharectersScaling(Date startDate,Date endDate,String state,List<Long> debateParticipantLocationIdList,List<Long> debateLocationIdList);
	public List<Object[]> getChannelAndPartyWiseCharecter(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocIdList);
	public List<Object[]> getRoleBasedPerformanceCohort(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList);
	public List<Object[]> getScaleOfCandidate(Date startDate,Date endDate,List<Long> roles,String state,List<Long> participantLocationIdList,List<Long> debateLocationIdList);
	public List<Object[]> getScaleOfCandidateNew(Date startDate,Date endDate,List<Long> roles,String state,List<Long> participantLocationIdList,List<Long> debateLocationIdList);
	public List<Object[]> getPartyWiseScalesOfOthersEachCharecter(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList);
	public List<Object[]> getChannelAndPartyWiseOthersCharecter(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocIdList);
	public List<Object[]> getRoleBasedOthersPerformanceCohort(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList);
	public List<Object[]> getScaleOfCandidateOthersNew(Date startDate,Date endDate,List<Long> roles,String state,List<Long> participantLocationIdList,List<Long> debateLocationIdList);
	public List<Object[]> getScaleOfOthersCandidate(Date startDate,Date endDate,List<Long> roles,String state,List<Long> participantLocationIdList,List<Long> debateLocationIdList);
	public List<Object[]> getPartywiseOthersCandidateCharectersScaling(Date startDate,Date endDate,String state,List<Long> debateParticipantLocationIdList,List<Long> debateLocationIdList);
	public List<Object[]> getPartywiseCandidateOthersScaling(Date startDate,Date endDate,String searchType,String state,List<Long> debateParticipantLocationIdList,List<Long> debateLocationIdList);
	public List<Object[]> getDesignationWiseScalesOfEachCharecter(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList);
	public List<Object[]> getDesignationwiseCandidateCharectersScaling(Date startDate,Date endDate,String state,List<Long> debateParticipantLocationIdList,List<Long> debateLocationIdList);
}
