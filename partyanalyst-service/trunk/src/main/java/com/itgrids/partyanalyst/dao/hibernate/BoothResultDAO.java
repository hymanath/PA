package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

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
		Object[] params = {ac, districtId, new Long(electionYear), electionScopeId};
		return getHibernateTemplate().find("select count(model.boothResultId) from BoothResult model where " +
				"model.boothConstituencyElection.booth.constituency.name = ? and model.boothConstituencyElection.booth.constituency.district.districtId = ? " +
				"and model.boothConstituencyElection.booth.year = ? and model.boothConstituencyElection.constituencyElection." +
				"constituency.electionScope.electionScopeId = ?", params);
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
	
	
	public List<Object[]> getAfterDelimitationEffectBasedOnVoters(Long electionId,Long constituencyid)
	{
		Query query = getSession().createQuery("select sum(model.boothConstituencyElection.booth.totalVoters), " +
				" sum(model.validVotes) from BoothResult model where " +
				" model.boothConstituencyElection.constituencyElection.constituency.constituencyId = :constituencyid " +
				" and model.boothConstituencyElection.constituencyElection.election.electionId = :electionId");
		query.setParameter("electionId", electionId);
		query.setParameter("constituencyid", constituencyid);
		return query.list();
	}
	
	public List<Object[]> getBeforeDelimitationEffectBasedOnVoters(Long electionId,List<Long> boothIds)
	{
		Query query = getSession().createQuery("select sum(model.boothConstituencyElection.booth.totalVoters)," +
				"  sum(model.validVotes) from BoothResult model where " +
				" model.boothConstituencyElection.booth.boothId in (:boothIds) " +
				" and model.boothConstituencyElection.constituencyElection.election.electionId = :electionId");
		query.setParameter("electionId", electionId);
		query.setParameterList("boothIds", boothIds);
		return query.list();
	}
	
}
