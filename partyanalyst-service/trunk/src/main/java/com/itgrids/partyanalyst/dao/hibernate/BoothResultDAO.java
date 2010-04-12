package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IBoothResultDAO;
import com.itgrids.partyanalyst.model.BoothResult;

public class BoothResultDAO extends GenericDaoHibernate<BoothResult, Long> implements IBoothResultDAO{
	public BoothResultDAO(){
		super(BoothResult.class);
	}

	@SuppressWarnings("unchecked")
	public List<BoothResult> findByBoothConstituencyElection(Long boothConstituencyElectionId) {
		return getHibernateTemplate().find("from BoothResult model where model.boothConstituencyElection.boothConstituencyElectionId =?", boothConstituencyElectionId);
	}

	@SuppressWarnings("unchecked")
	public List<BoothResult> findByConstituencyAndElection(String constituencyName, String electionYear, Long electionScopeId) {
		Object[] params = {constituencyName,  electionYear, electionScopeId};
		return getHibernateTemplate().find("from BoothResult model where model.boothConstituencyElection.constituencyElection.constituency.name = ? and model.boothConstituencyElection.constituencyElection.election.electionYear = ? and model.boothConstituencyElection.constituencyElection.constituency.electionScope.electionScopeId = ?", params);
	}
	
	public List getAllPolledVotesByElectionsInDistrict(Long districtId, String electionType){
		Object[] params = {districtId, electionType};
		return getHibernateTemplate().find("select distinct model.boothConstituencyElection.constituencyElection.election.electionYear," +
				" sum(model.validVotes) from BoothResult model where " +
				"model.boothConstituencyElection.booth.tehsil.district.districtId = ? " +
				"and model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType = ? " +
				"group by model.boothConstituencyElection.constituencyElection.election.electionId", params);
	}
}
