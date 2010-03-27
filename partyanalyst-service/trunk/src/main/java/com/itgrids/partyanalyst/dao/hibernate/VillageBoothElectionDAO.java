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
		return getHibernateTemplate().find("select model.township.townshipId, model.township.townshipName, " +
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
	
	
}

		
