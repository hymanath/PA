/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 08,2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyElectionResultDAO;
import com.itgrids.partyanalyst.model.PartyElectionResult;

public class PartyElectionResultDAO extends GenericDaoHibernate<PartyElectionResult, Long>
		implements IPartyElectionResultDAO {

	public PartyElectionResultDAO() {
		super(PartyElectionResult.class);
	}

	@SuppressWarnings("unchecked")
	public List<PartyElectionResult> getByElectionAndParty(Long electionId,Long partyId) {
		Object[] params = {electionId,partyId};
		return getHibernateTemplate().find("from PartyElectionResult model where model.election.electionId = ? and model.party.partyId = ?", params);
	}
	
	@SuppressWarnings("unchecked")
	public List getParticipatedPartysCountForAnElection(Long electionId){
		return getHibernateTemplate().find("select count(model) from PartyElectionResult model where model.election.electionId = ?", electionId);
	}

	@SuppressWarnings("unchecked")
	public List<PartyElectionResult> getByElectionIdAndVotesPercentMargin(Long electionId,String votesPercentMargin){
		Object[] params = {votesPercentMargin,electionId};
		return getHibernateTemplate().find("from PartyElectionResult model where model.completeVotesPercent > ? and model.election.electionId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getBasicPartyElectionResultForAPartyInAnElection(
			Long electionId, Long partyId) {
		Object[] params = {electionId,partyId};
		return getHibernateTemplate().find("select model.party.partyId,model.party.shortName,model.totalSeatsWon,model.totalConstiParticipated"+
				" from PartyElectionResult model where model.election.electionId = ? and model.party.partyId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPartyBasicResultForAnElection(Long electionId)
	{
		return getHibernateTemplate().find("select model.party.partyId,model.party.shortName,model.totalConstiParticipated,model.totalSeatsWon,model.completeVotesPercent," +
				" model.votesPercentage from PartyElectionResult model where model.election.electionId = ? and model.completeVotesPercent > 0.5 order by model.party.shortName ",electionId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getBasicPartiesForAnElection(Long electionId)
	{
		return getHibernateTemplate().find("select model.party.partyId,model.party.shortName from PartyElectionResult model where model.election.electionId = ? and model.completeVotesPercent > 0.5 order by model.party.shortName ",electionId);
	}
	
}
