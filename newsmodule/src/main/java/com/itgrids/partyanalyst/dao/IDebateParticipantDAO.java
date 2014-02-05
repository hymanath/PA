package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DebateParticipant;

public interface IDebateParticipantDAO extends GenericDao<DebateParticipant, Long>{

	public List<Object[]> getDebatePaticepantsAndSummeryDetails(Long debateId);
	
	public List<Object[]> getPartyWiseDebateAnalysis(Long partyId);
	
	public List<Object[]> getCandidateWiseDebateAnalysis(Long partyId,Long candidateId);
	
	public List<Object[]> getBasicDebateAnalysis(Long partyId,Long candidateId,String type,StringBuffer queryString);
	
	public List<Object[]> getDebateTotalScaleForEachParty(Date fromDate,Date toDate);
	
	public List<Object[]> getTotalDabtesCountsForEachParty(Date fromDate , Date toDate);
	
	public List<Object[]> getDebateTotalScaleForEachCandidate(Date fromDate,Date toDate);
	
	public List<Object[]> getTotalDabtesCountsForEachCandidate(Date fromDate , Date toDate);
	
	public List<Object[]> getCanidatesListForDebateForSelectedDates(Date fromDate,Date toDate);
}
