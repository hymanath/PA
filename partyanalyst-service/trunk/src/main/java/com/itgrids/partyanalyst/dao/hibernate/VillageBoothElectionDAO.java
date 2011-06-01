package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVillageBoothElectionDAO;
import com.itgrids.partyanalyst.model.VillageBoothElection;

public class VillageBoothElectionDAO extends GenericDaoHibernate<VillageBoothElection, Long> implements IVillageBoothElectionDAO{

	public VillageBoothElectionDAO(){
		super(VillageBoothElection.class);
	}
	
	public List findTownshipWiseBoothDetailsForTehsil(Long tehsilId, Long electionId){
		Object[] params = {tehsilId, electionId};
		return getHibernateTemplate().find("select model.boothConstituencyElection.constituencyElection.constituency.constituencyId," +
				"model.boothConstituencyElection.constituencyElection.constituency.name," +
				"model.township.townshipId, model.township.townshipName, " +
				"model.boothConstituencyElection.booth.boothId, model.boothConstituencyElection.booth.partNo, " +
				"model.boothConstituencyElection.booth.totalVoters, model.boothConstituencyElection.boothResult.validVotes " +
				"from VillageBoothElection model where model.boothConstituencyElection.booth.tehsil.tehsilId = ? " +
				"and model.boothConstituencyElection.constituencyElection.election.electionId = ? group by " +
				"model.boothConstituencyElection.booth.boothId order by " +
				"model.township.townshipName, model.boothConstituencyElection.booth.boothId",params);
	}

	@SuppressWarnings("unchecked")
	public List<Long> findByTownshipAndBoothConstituencyElection(Long townshipId,
			Long boothConstituencyElectionId) {
		Object[] params = {townshipId, boothConstituencyElectionId};	
		return getHibernateTemplate().find("select model.villageBoothElectionId from VillageBoothElection model where " +
				"model.township.townshipId = ? and model.boothConstituencyElection.boothConstituencyElectionId = ?",params);
	}
	
	public List findElectionsForElectionType(Long electionTypeId){
		return getHibernateTemplate().find("select distinct model.boothConstituencyElection.constituencyElection.election.electionId, " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear from VillageBoothElection model where " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionTypeId = ?", electionTypeId);
	}
	
	public List findTownsBoothIdsInTehsilForElection(String townshipIds, Long electionId){
		return getHibernateTemplate().find("select model.township.townshipName, model.boothConstituencyElection.booth.totalVoters, " +
				"model.boothConstituencyElection.boothResult.validVotes, model.boothConstituencyElection.booth.boothId, " +
				"model.boothConstituencyElection.booth.partNo from VillageBoothElection model where model.township.townshipId in ("+townshipIds+") and " +
				"model.boothConstituencyElection.constituencyElection.election.electionId = ? " +
				"order by model.township.townshipName, model.boothConstituencyElection.booth.partNo",electionId); 
	}
	
	public List findTownshipAndBoothConstiElecIds(String townshipIds, Long electionId){
		return getHibernateTemplate().find("select model.township.townshipName, model.boothConstituencyElection.boothConstituencyElectionId " +
				"from VillageBoothElection model where model.township.townshipId in ("+townshipIds+") and " +
				"model.boothConstituencyElection.constituencyElection.election.electionId = ? " +
				"group by model.boothConstituencyElection.boothConstituencyElectionId", electionId);
	}
	

	
	public List findTownshipWiseVotingTrendsForATehsil(Long tehsilId, String electionIds){
		return getHibernateTemplate().find("select model.township.townshipId, model.township.townshipName, " +
				" sum(model.boothConstituencyElection.boothResult.validVotes), model.boothConstituencyElection.constituencyElection.election.electionId" +
				" from VillageBoothElection model where model.boothConstituencyElection.booth.tehsil.tehsilId = ? " +
				" and model.boothConstituencyElection.constituencyElection.election.electionId in ("+electionIds+
				" ) group by model.township.townshipId,model.boothConstituencyElection.constituencyElection.election.electionId",tehsilId);
	}
	
	public List findPolledVotesInAllElectionsOfMandalByRevenueVillages(Long tehsilId){
		return getHibernateTemplate().find("select model.boothConstituencyElection.constituencyElection.election.electionId," +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType, " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear, " +
				"model.township.townshipId, model.township.townshipName, " +
				"sum(model.boothConstituencyElection.boothResult.validVotes) " +
				"from VillageBoothElection model where model.township.tehsil.tehsilId=? group by " +
				"model.boothConstituencyElection.constituencyElection.election.electionId, " +
				"model.township.townshipId order by " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear desc, " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType," +
				"model.township.townshipName ", tehsilId);
	}
	
	public List findVillageAndBoothInfoForBoothConstituencyElection(Long boothConstituencyElectionId){
		return getHibernateTemplate().find("select model.township.townshipName, model.township.tehsil.tehsilName, " +
				"model.township.tehsil.district.districtName, model.boothConstituencyElection.booth.constituency.name, " +
				"model.boothConstituencyElection.booth.partNo from VillageBoothElection model where " +
				"model.boothConstituencyElection.boothConstituencyElectionId = ?",boothConstituencyElectionId);
	}
	
	public void flushAndclearSession(){
		getSession().flush();
		getSession().clear();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> findLatestElectionYearInARevenueVillageForElectionType(Long townshipId,String electionType)
	{
		Object[] params = {townshipId,electionType};
		return getHibernateTemplate().find("select max(model.boothConstituencyElection.constituencyElection.election.electionYear) from VillageBoothElection model where model.township.townshipId = ? " +
				" and model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType = ?",params);
	}
	
}

		
