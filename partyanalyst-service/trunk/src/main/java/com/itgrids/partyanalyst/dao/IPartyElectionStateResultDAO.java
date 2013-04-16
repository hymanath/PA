/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 12,2010
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyElectionStateResult;

public interface IPartyElectionStateResultDAO extends GenericDao<PartyElectionStateResult, Long> {

	public List<PartyElectionStateResult> getByPartyIdElectionIdAndStateId(Long partyId,Long electionId,Long stateId);
	
	@SuppressWarnings("unchecked")
	public List getParticipatedPartysCountForAnElection(Long electionId);
	
	public List<PartyElectionStateResult> getStateWiseAllPartiesResults(Long electionId,String votesPercentMargin);
	
	public List<PartyElectionStateResult> getResultsInAllStatesForAParty(Long electionId,Long partyId);
	
	public List findStatewiseResultsForPartyElectionAndCountry(Long partyId, Long countryId, Long electionId);
	
	public List<PartyElectionStateResult> getParticipatedPartiesDetailsInStates(Long electionId , List<Long> stateIdList);
	
	public List<PartyElectionStateResult> getByPartyIdsElectionIdsAndStateIds(Long partyId,Long electionId,Long stateId);
	
}
