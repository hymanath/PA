package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;
import com.itgrids.partyanalyst.model.BoothResult;
import com.itgrids.partyanalyst.model.CandidateBoothResult;

public class BoothConstituencyElectionDAO extends GenericDaoHibernate<BoothConstituencyElection, Long> implements IBoothConstituencyElectionDAO{

	public BoothConstituencyElectionDAO(){
		super(BoothConstituencyElection.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Booth> findBoothsByConstituencyElection(Long constiElecId) {
		return getHibernateTemplate().find("select model.booth from BoothConstituencyElection model where model.constituencyElection.constiElecId = ?", constiElecId);
	}
	
	@SuppressWarnings("unchecked")
	public List<BoothConstituencyElection> findByTehsilElectionAndScope(String electionYear, Long electionScopeId, Long tehsilId) {
		Object[] params = {tehsilId, electionYear, electionScopeId};
		return getHibernateTemplate().find("from BoothConstituencyElection model where model.booth.tehsil.tehsilId = ? and model.constituencyElection.election.electionYear = ? and model.constituencyElection.election.electionScope.electionScopeId = ?", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<BoothConstituencyElection> findByBoothElectionAndScope(Long boothId, String electionYear, Long electionScopeId) {
		Object[] params = {boothId, electionYear, electionScopeId};
		return getHibernateTemplate().find("from BoothConstituencyElection model where model.booth.boothId = ? and model.constituencyElection.election.electionYear = ? and model.constituencyElection.election.electionScope.electionScopeId = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List<BoothConstituencyElection> findByConstituencyElectionTehsilAndPartNo(Long constituencyElectionId, Long tehsilId, String partNo) {
		Object[] params = {partNo, tehsilId, constituencyElectionId};
		return getHibernateTemplate().find("from BoothConstituencyElection model where model.booth.partNo=? and model.booth.tehsil.tehsilId = ? and model.constituencyElection.constiElecId = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List<BoothConstituencyElection> findByBoothAndConstiuencyElection(String partNo, Long constiElecId) {
		Object[] params = {partNo, constiElecId};
		return getHibernateTemplate().find("from BoothConstituencyElection model where model.booth.partNo=? and model.constituencyElection.constiElecId = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List<BoothConstituencyElection> findByConstituencyAndElection(String constituencyName, String electionYear, Long electionScopeId) {
		Object[] params = {constituencyName, electionScopeId, electionYear};
		return getHibernateTemplate().find("from BoothConstituencyElection model where model.constituencyElection.constituency.name = ? and model.constituencyElection.constituency.electionScope.electionScopeId = ? and model.constituencyElection.election.electionYear = ?", params);
	}
	
	public List getAllElectionBoothVotersForMandal(Long tehsilID){
		StringBuilder query = new StringBuilder();
		query.append("Select constituencyElection.election, ");
		query.append(" sum(model.booth.totalVoters) , sum(model.boothResult.validVotes), ");
		query.append(" sum(model.boothResult.rejectedVotes), sum(model.boothResult.tenderedVotes)");
		query.append(" from BoothConstituencyElection model ");
		query.append(" where model.booth.tehsil.tehsilId = ").append(tehsilID);
		query.append(" group by model.constituencyElection.election");
		query.append(" order by model.constituencyElection.election");
		return getHibernateTemplate().find(query.toString() );
	}
	
	public List getPartyVotesByMandal(Long tehsilID, String partyIDs, Long electionID){
		StringBuilder query = new StringBuilder();
		query.append("Select model.nomination.candidate.firstname,");
		query.append(" model.nomination.candidate.middlename,"); 
		query.append(" model.nomination.candidate.lastname,");		 
		query.append(" model.boothConstituencyElection.constituencyElection.election,");
		query.append(" sum(model.votesEarned), model.nomination.party.partyId, model.nomination.party.shortName"); 
		query.append(" from CandidateBoothResult model"); 
		query.append(" where model.boothConstituencyElection.booth.tehsil.tehsilId =").append(tehsilID);    
		query.append(" and model.boothConstituencyElection.constituencyElection.election.electionId=").append(electionID);
		query.append(" and model.nomination.party.partyId in (").append(partyIDs);
		query.append(") group by model.nomination.party.partyId");
		return getHibernateTemplate().find(query.toString());
	}
	


	public List getStatesByCountryFromBooth(Long countryID) {
		return getHibernateTemplate().find("select distinct model.constituencyElection.constituency.state.stateId, " +
				"model.constituencyElection.constituency.state.stateName from BoothConstituencyElection model " +
				"where model.constituencyElection.constituency.state.country.countryId=? " +
				"order by model.constituencyElection.constituency.state.stateName",countryID);
	}

	public List getDistrictsByStateIDFromBooth(Long stateID) {
		return getHibernateTemplate().find("select distinct model.booth.tehsil.district.districtId, " +
				"model.booth.tehsil.district.districtName from BoothConstituencyElection model " +
				"where model.booth.tehsil.district.state.stateId=? order by model.booth.tehsil.district.districtName",stateID);
	}

	public List getConstituenciesByDistrictIDFromBooth(Long districtID) {
		return getHibernateTemplate().find("select distinct model.constituencyElection.constituency.constituencyId, " +
				"model.constituencyElection.constituency.name from BoothConstituencyElection model " +
				"where model.constituencyElection.constituency.district.districtId=? " +
				"order by model.constituencyElection.constituency.name",districtID);
	}

	public List getMandalsByConstituencyIDFromBooth(Long constituencyID) {
		return getHibernateTemplate().find("select distinct model.booth.tehsil.tehsilId, model.booth.tehsil.tehsilName from BoothConstituencyElection model " +
				"where model.constituencyElection.constituency.constituencyId=? order by model.booth.tehsil.tehsilName",constituencyID);
	}
}
