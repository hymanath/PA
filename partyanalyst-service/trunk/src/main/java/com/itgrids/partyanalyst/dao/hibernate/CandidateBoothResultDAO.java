package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.model.CandidateBoothResult;
import com.itgrids.partyanalyst.model.Party;

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
	public List<CandidateBoothResult> findByConstituencyElection(Long constituencyElectionId) {
		return getHibernateTemplate().find("from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.constiElecId = ?", constituencyElectionId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Party> findPartiesByConstituencyAndElectionYear(Long constituencyId, String electionYear){
		Object[] params = {constituencyId, electionYear};
		return getHibernateTemplate().find("select distinct model.nomination.party from CandidateBoothResult model where " +
				"model.boothConstituencyElection.constituencyElection.constituency.constituencyId = ?" +
				" and model.boothConstituencyElection.constituencyElection.election.electionYear = ?",params);
	}
	
	public List findPartyElectionResultForMandal(Long tehsilID,String electionTypeIDs, String electionYears){
		return getHibernateTemplate().find("select model.nomination.party.shortName, " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType, " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear, " +
				"sum(model.votesEarned) from CandidateBoothResult model " +
				"where model.boothConstituencyElection.booth.tehsil.tehsilId=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear in (" + electionYears +
				") and model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionTypeId " +
				"in (" + electionTypeIDs + ") group by model.nomination.party.shortName," +
						"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType," +
						"model.boothConstituencyElection.constituencyElection.election.electionYear " +
						"order by model.nomination.party.shortName, " +
						"model.boothConstituencyElection.constituencyElection.election.electionYear, " +
						"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType desc" , tehsilID);
	}
	
	public List findPartyElectionResultForRevenueVillage(Long revenueVillageID,String electionTypeIDs, String electionYears){
		return getHibernateTemplate().find("select model.nomination.party.shortName, " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType, " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear, " +
				"sum(model.votesEarned) from CandidateBoothResult model " +
				"where model.boothConstituencyElection.boothConstituencyElectionId in (select distinct bcev.boothConstituencyElection.boothConstituencyElectionId " +
				"from BoothConstituencyElectionVoter bcev where bcev.voter.hamlet.township.townshipId=? ) and " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear in (" + electionYears +
				") and model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionTypeId " +
				"in (" + electionTypeIDs + ") group by model.nomination.party.shortName," +
						"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType," +
						"model.boothConstituencyElection.constituencyElection.election.electionYear " +
						"order by model.nomination.party.shortName, " +
						"model.boothConstituencyElection.constituencyElection.election.electionYear, " +
						"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType desc" , revenueVillageID);
	}
	
	public List findBoothWisePartiesAllElectionResultsByBooth(Long boothId){
		return getHibernateTemplate().find("select model.nomination.party.shortName, model.nomination.candidate.candidateId, model.nomination.candidate.firstname, " +
				"model.nomination.candidate.middlename, model.nomination.candidate.lastname, model.nomination.candidateResult.rank, model.boothConstituencyElection.booth, " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType, " +
				"model.nomination.constituencyElection.constituency.name, model.nomination.constituencyElection.constituency.constituencyId, " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear, model.votesEarned " +
				"from CandidateBoothResult model where model.boothConstituencyElection.booth.boothId = ? " +
				"group by model.candidateBoothResultId," +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType," +
				"model.boothConstituencyElection.constituencyElection.election.electionYear " +
				"order by model.candidateBoothResultId, " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear, " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType desc",boothId);		
	}
}
