package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.model.CandidateBoothResult;

public class CandidateBoothResultDAO extends GenericDaoHibernate<CandidateBoothResult, Long> implements ICandidateBoothResultDAO{

	public CandidateBoothResultDAO(){
		super(CandidateBoothResult.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<CandidateBoothResult> findByProperty(Object value) {
		return getHibernateTemplate().find("from CandidateBoothResult model where model.nomination.nominationId = ?", value);		
	}
	
	@SuppressWarnings("unchecked")
	public List<CandidateBoothResult> findByNominationAndBoothConstituencyElection(Long nominationId, Long boothConstituencyElectionId) {
		Object[] params = {nominationId, boothConstituencyElectionId};
		return getHibernateTemplate().find("from CandidateBoothResult model where model.nomination.nominationId = ? and model.boothConstituencyElection.boothConstituencyElectionId = ?", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<CandidateBoothResult> findByBoothElectionScopeAndParty(Long boothId, String electionYear, Long electionScopeId, Long partyId) {
		Object[] params = {boothId, electionYear, electionScopeId, partyId};
		return getHibernateTemplate().find("from CandidateBoothResult model where model.boothConstituencyElection.booth.boothId = ? and model.nomination.constituencyElection.election.electionYear = ? and model.nomination.constituencyElection.election.electionScope.electionScopeId = ? and model.nomination.party.partyId = ?", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<CandidateBoothResult> findByBoothConstituencyElectionAndParty(Long boothConstituencyElectionId, Long partyId) {
		Object[] params = {boothConstituencyElectionId, partyId};
		return getHibernateTemplate().find("from CandidateBoothResult model where model.boothConstituencyElection.boothConstituencyElectionId = ? and model.nomination.party.partyId = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List<CandidateBoothResult> findByConstituencyElection(Long constituencyElectionId) {
		return getHibernateTemplate().find("from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.constiElecId = ?", constituencyElectionId);
	}
}
