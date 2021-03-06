package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DebateParticipant;

public interface IDebateParticipantDAO extends GenericDao<DebateParticipant, Long>{

	public List<Object[]> getDebatePaticepantsAndSummeryDetails(Long debateId,Long stateId);
	
	public List<Object[]> getPartyWiseDebateAnalysis(Long partyId);
	
	public List<Object[]> getCandidateWiseDebateAnalysis(Long partyId,Long candidateId);
	
	public List<Object[]> getBasicDebateAnalysis(Long partyId,Long candidateId,String type,StringBuffer queryString);
	
	public List<Object[]> getDebateTotalScaleForEachParty(Date fromDate,Date toDate,Long stateId);
	
	public List<Object[]> getTotalDabtesCountsForEachParty(Date fromDate , Date toDate,Long stateId);
	
	public List<Object[]> getDebateTotalScaleForEachCandidate(Date fromDate,Date toDate,Long stateId);
	
	public List<Object[]> getTotalDabtesCountsForEachCandidate(Date fromDate , Date toDate,Long stateId);
	
	public List<Object[]> getCanidatesListForDebateForSelectedDates(Date fromDate,Date toDate,Long stateId);
	
	public List<Object[]> getPartiesAndCanidatesIds();
	
	public List<Object[]> getDebateCandidateCharacteristicsDetails();
	public List<Object[]> getDebateCandidateCharacteristicsDetailForSelection(Date fromDate,Date toDate,List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds,Long stateId);
	public List<Object[]> getDistinctDebateParties();
	
	public List<Object[]> getDistinctDebatePartiesForSelection(Date fromDate,Date toDate, List<Long> partyIds, Long stateId);
	
	public List<Object[]> getPartiesAndCanidatesIdForSelection(Date fromDate,Date toDate,List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds,Long stateId);
	
	public List<Object[]> getTotalAttendedDebatesOfCadre(Long tdpCadreId,Long stateId);
	public List<Object[]> getDebateParticipatedDetailsOfCadre(List<Long> debetIds);
	public List<Object[]> getPartyWiseDebateDetails(Date startDate,Date endDate,String state,List<Long> debateLocationIdList);
	public List<Object[]> getTotalDabtesCountsForEachCandidateNew(Date fromDate , Date toDate,String state,List<Long> debateParticipantLocationIdList,List<Long> debateLocationIdList);
	public List<Object[]> getChannelWiseDebateDetails(Date startDate,Date endDate,String state,List<Long> debateLocationIdList);
	public List<Object[]> getDebatesCountOfCandidate(Date startDate,Date endDate,List<Long> roles,String state);
	public List<Object[]> getPartyWiseDebates(List<Long> partyIds,Date startDate,Date endDate,String state,String searchType,Long candidateId,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList,Long roleId,Long designationId,Long casteId,String type);
	public Long getTotalAttendedDebatesOfCadreNew(Long tdpCadreId);
	public List<Object[]> getPartyAndCandidateWiseDebates(List<Long> partyIds,Date startDate,Date endDate,String state,String searchType,List<Long> candidateIds,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList,Long roleId,Long designationId);
	public List<Integer> getDebateParticipantId(Long debateId);
	public List<Object[]> getPartyWiseDebateOtherDetails(Date startDate,Date endDate,String state,List<Long> debateLocationIdList);
	public List<Object[]> getChannelWiseOthersDebateDetails(Date startDate,Date endDate,String state,List<Long> debateLocationIdList);
	public List<Object[]> getPartyWiseOthersDebates(List<Long> partyIds,Date startDate,Date endDate,String state,String searchType,Long candidateId,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList,Long roleId,Long designationId,Long casteId,String type);
	public List<Object[]> getPartyWiseDebateParticipantOtherDetails(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList );
	public List<Object[]> getPartyWiseDebateParticipantDetails(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList);
	public List<Object[]> getPartyAndCandidateWiseOthersDebates(List<Long> partyIds,Date startDate,Date endDate,String state,String searchType,List<Long> candidateIds,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList,Long roleId,Long designationId);
	public List<Object[]> getTotalDabtesCountsForEachOtherCandidateNew(Date fromDate , Date toDate,String state,List<Long> debateParticipantLocationIdList,List<Long> debateLocationIdList);
	public List<Object[]> getTotalDabtesCountsForEachCandidateOthersNew(Date fromDate , Date toDate,String state,List<Long> debateParticipantLocationIdList,List<Long> debateLocationIdList);
	public List<Object[]> getPartyWiseDebateParticipantStateOtherDetails(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList );
	public List<Object[]> getPartyWiseStateDebateParticipantDetails(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList);
	public List<Object[]> getPartyWiseDebateParticipantsOtherDetails(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList);
	public List<Object[]> getPartyWiseDebatePartipantDetails(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList);
	public List<Object[]> getDesignationWiseDebateDetails(Date startDate,Date endDate,String state,List<Long> debateLocationIdList);
	public List<Object[]> getDesignationWiseDebateParticipantDetails(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList);
	public List<Object[]> getTotalDesignationsWiseDabtesCountsForEachCandidateNew(Date fromDate , Date toDate,String state,List<Long> debateParticipantLocationIdList,List<Long> debateLocationIdList);
	 public List<Long> getDesignationWiseDebateParticipantCandidatesDetails(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList);
	 public List<Object[]> getDebateDesignationWiseDeatils(Date startDate,Date endDate,String state,List<Long> candidateIds);
	 public List<Object[]> getTotalDesignationsWiseOthersDabtesCountsForEachCandidateNew(Date fromDate , Date toDate,String state,List<Long> debateParticipantLocationIdList,List<Long> debateLocationIdList,List<Long> candidateIds);
	 public List<Object[]> getDesignationWiseDebates(List<Long> partyIds,Date startDate,Date endDate,String state,String searchType,Long candidateId,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList,Long roleId,Long designationId,List<Long> candiadteIds);
	 public List<Object[]> getTotalDabtesCountsForPartyWiseCasteDetails(Date fromDate , Date toDate,String state,List<Long> debateLocationIdList);
	 public List<Object[]> getTotalDabteParticipantCountsForPartyWiseCasteDetails(Date fromDate , Date toDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList);
	 public List<Object[]> getTotalDabtesCountsForPartyWiseCasteDetailsClick(Date fromDate , Date toDate,String state,List<Long> debateLocationIdList,Long casteId,String type,Long partyId,List<Long> debateParticipantLocationIdList);
	 public Object[] getCandidateGroupMatchedDetails(Date fromDate,Date toDate,Long candidateId,String type,Long categoryId,Long partyId);

}
