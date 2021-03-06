/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 08,2010
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyElectionResult;

public interface IPartyElectionResultDAO extends GenericDao<PartyElectionResult, Long> {
	
	public List<PartyElectionResult> getByElectionAndParty(Long electionId,Long partyId);
	
	@SuppressWarnings("unchecked")
	public List getParticipatedPartysCountForAnElection(Long electionId);
	
	public List<PartyElectionResult> getByElectionIdAndVotesPercentMargin(Long electionId,String votesPercentMargin);
	
	@SuppressWarnings("unchecked")
	public List getBasicPartyElectionResultForAPartyInAnElection(Long electionId,Long partyId);
	
	public List<Object[]> getPartyBasicResultForAnElection(Long electionId);
	
	public List<Object[]> getBasicPartiesForAnElection(Long electionId);
	
	public List<Object[]> getPartiesBasicResultForAnElection(Long electionId,List<Long> partiesList);
	
	public List<PartyElectionResult> getPartyElectionResultsBasedOnPartyId(Long partyId,String electionType,boolean includeBielections);
	
	public List<PartyElectionResult> getPartyElectionResultsBasedOnPartyIdAndElecId(Long partyId,String electionType,Long electionId);
	
	public List<Object[]> getPartiesParticipatedMoreThanOnce(Long stateId, String electionType);
	
	//public List<Object[]> getAllPartiesParticapatedInMainElections(Long stateId);
	
	public String getVotesPercentageByElectionAndParty(Long electionId,Long partyId);

}
