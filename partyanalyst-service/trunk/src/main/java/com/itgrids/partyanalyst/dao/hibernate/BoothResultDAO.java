package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IBoothResultDAO;
import com.itgrids.partyanalyst.model.BoothResult;
import com.itgrids.partyanalyst.utils.IConstants;

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
	
	public List getParliamentResultHappenedInAssembly(String ac, Long districtId, Long electionScopeId, String electionYear){
		Object[] params = {ac, districtId, electionYear, electionScopeId, electionYear};
		return getHibernateTemplate().find("select count(model.boothResultId) from BoothResult model where " +
				"model.boothConstituencyElection.booth.boothId in( select model1.booth.boothId from BoothConstituencyElection model1 " +
				"where model1.constituencyElection.constituency.name = ? and model1.constituencyElection.constituency.district.districtId = ? " +
				"and model1.constituencyElection.constituency.electionScope.electionType.electionType = '"+IConstants.ASSEMBLY_ELECTION_TYPE+"' " +
				"and model1.constituencyElection.election.electionYear = ?) and model.boothConstituencyElection.constituencyElection." +
				"constituency.electionScope.electionScopeId = ? and model.boothConstituencyElection.constituencyElection.election.electionYear = ?", params);
	}
	
	public List getAllPolledVotesForMandalsInAnElection(String mandalIds, String electionYear, String electionType){
		Object[] params = {electionYear, electionType};
		return getHibernateTemplate().find("select model.boothConstituencyElection.constituencyElection.election.electionId, " +
				"model.boothConstituencyElection.booth.tehsil.tehsilId, model.boothConstituencyElection.booth.tehsil.tehsilName, " +
				"sum(model.validVotes) from BoothResult model where model.boothConstituencyElection.booth.tehsil.tehsilId in " +
				"("+mandalIds+") and model.boothConstituencyElection.constituencyElection.election.electionYear = ? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType = ? " +
				"group by model.boothConstituencyElection.constituencyElection.election.electionId, " +
				"model.boothConstituencyElection.booth.tehsil.tehsilId", params);
	}
	
	public List getMandalwiseValidVotesForAMappedConstituency(Long constituencyId, String elecYear, String elecType){
		Object[] params = {constituencyId, elecYear, elecType};
		return getHibernateTemplate().find("select model.boothConstituencyElection.booth.tehsil.tehsilId, " +
				"model.boothConstituencyElection.booth.tehsil.tehsilName, sum(model.validVotes) from " +
				"BoothResult model where model.boothConstituencyElection.mappedConstituency.constituencyId = ? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear = ? " +
				"and model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType = ? " +
				"group by model.boothConstituencyElection.booth.tehsil.tehsilId",params);
	}
	
}
