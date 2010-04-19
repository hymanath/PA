/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 12,2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyElectionStateResultDAO;
import com.itgrids.partyanalyst.model.PartyElectionStateResult;

public class PartyElectionStateResultDAO extends GenericDaoHibernate<PartyElectionStateResult, Long>
		implements IPartyElectionStateResultDAO {
	
	
	public PartyElectionStateResultDAO() {
		super(PartyElectionStateResult.class);
	}

	@SuppressWarnings("unchecked")
	public List<PartyElectionStateResult> getByPartyIdElectionIdAndStateId(
			Long partyId, Long electionId, Long stateId) {
		Object[] params = {partyId,electionId,stateId};
		return getHibernateTemplate().find("from PartyElectionStateResult model where model.party.partyId = ? and model.election.electionId = ? and model.state.stateId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getParticipatedPartysCountForAnElection(Long electionId) {
		return getHibernateTemplate().find("select count(distinct model.party.partyId) from PartyElectionStateResult model where model.election.electionId = ?",electionId);
	}

	@SuppressWarnings("unchecked")
	public List<PartyElectionStateResult> getStateWiseAllPartiesResults(
			Long electionId, String votesPercentMargin) {
	    Object[] params = {electionId,votesPercentMargin};
		return getHibernateTemplate().find("from PartyElectionStateResult model where model.election.electionId = ? and model.completeVotesPercent > ? order by model.party.partyId",params);
	}

	@SuppressWarnings("unchecked")
	public List<PartyElectionStateResult> getResultsInAllStatesForAParty(
			Long electionId, Long partyId) {
		 Object[] params = {electionId,partyId};
		return getHibernateTemplate().find("from PartyElectionStateResult model where model.election.electionId = ? and model.party.partyId = ?",params);
	}
	
}
