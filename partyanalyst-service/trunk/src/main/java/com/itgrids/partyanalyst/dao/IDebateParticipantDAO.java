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
	
	public List<Object[]> getPartiesAndCanidatesIds();
	
	public List<Object[]> getDebateCandidateCharacteristicsDetails();
	public List<Object[]> getDebateCandidateCharacteristicsDetailForSelection(Date fromDate,Date toDate,List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds);
	public List<Object[]> getDistinctDebateParties();
	
	public List<Object[]> getDistinctDebatePartiesForSelection(Date fromDate,Date toDate, List<Long> partyIds);
	
	public List<Object[]> getPartiesAndCanidatesIdForSelection(Date fromDate,Date toDate,List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds);
	
	public List<Object[]> getTotalAttendedDebatesOfCadre(Long tdpCadreId);
	public List<Object[]> getDebateParticipatedDetailsOfCadre(List<Long> debetIds);
	public List<Object[]> getPartyWiseDebateDetails(Date startDate,Date endDate,String state);
	public List<Object[]> getTotalDabtesCountsForEachCandidateNew(Date fromDate , Date toDate,String state);
	public List<Object[]> getChannelWiseDebateDetails(Date startDate,Date endDate,String state);

}
