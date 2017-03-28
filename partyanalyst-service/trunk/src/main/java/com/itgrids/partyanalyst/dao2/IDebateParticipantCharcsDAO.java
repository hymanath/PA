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
	
	public List<Object[]> getPartyCandidateDetailsTopicWiseForSelection(Date fromDate,Date toDate, List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds);
	
	public List<Object[]> getDebatePerformanceCountsForSelection(Date fromDate,Date toDate,List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds);
	
	public List<Object[]> getDebatePerformanceCountCharForSelection(Date fromDate,Date toDate,List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds);
	
	public List<Object[]> getPartyWiseTotalDebatesAndScalForSelection(Date fromDate, Date toDate, List<Long> channelIds, List<Long> partyIds,  List<Long> candidateIds);
	
	public List<Object[]> getPartyWiseDebatePartiCharsCountsForSelection(Date fromDate,Date toDate,List<Long> channelIds,List<Long>  partyIds,List<Long>  candidateIds);
	
	public List<Object[]> getPartyWiseEachDebateCharsCounttsForSelection(Date fromDate,Date toDate,List<Long> channelIds,List<Long>  partyIds,List<Long>  candidateIds);
	
	public List<Object[]> getTopicWiseStrongOrWeakCandidatsForSelection(Date fromDate,Date toDate, List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds,String sortOrder);
	public List<Object[]> getPartyWiseScalesOfEachCharecter(Date startDate,Date endDate,String state);
	public List<Object[]> getPartywiseCandidateScaling(Date startDate,Date endDate,String searchType,String state);
	public List<Object[]> getPartywiseCandidateCharectersScaling(Date startDate,Date endDate,String state);
	public List<Object[]> getChannelAndPartyWiseCharecter(Date startDate,Date endDate,String state);
	public List<Object[]> getRoleBasedPerformanceCohort(Date startDate,Date endDate,String state);
	public List<Object[]> getScaleOfCandidate(Date startDate,Date endDate,List<Long> roles,String state);
	public List<Object[]> getScaleOfCandidateNew(Date startDate,Date endDate,List<Long> roles,String state);
}
