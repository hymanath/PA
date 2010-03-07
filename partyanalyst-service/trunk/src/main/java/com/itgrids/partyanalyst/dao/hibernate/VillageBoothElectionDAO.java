package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVillageBoothElectionDAO;
import com.itgrids.partyanalyst.model.VillageBoothElection;

public class VillageBoothElectionDAO extends GenericDaoHibernate<VillageBoothElection, Long> implements IVillageBoothElectionDAO{

	public VillageBoothElectionDAO(){
		super(VillageBoothElection.class);
	}
	
	public List findTownshipWiseBoothDetailsForTehsil(Long tehsilId){
		return getHibernateTemplate().find("select model.township.townshipName, " +
				"model.boothConstituencyElection.booth.partNo, model.boothConstituencyElection.booth.totalVoters, " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear, model.hamlet.hamletName," +
				"model.hamlet.township.townshipId from VillageBoothElection model " +
				"where model.boothConstituencyElection.booth.tehsil.tehsilId = ? group by " +
				"model.boothConstituencyElection.booth.partNo, model.hamlet.hamletName order by " +
				"model.hamlet.township.townshipName, model.boothConstituencyElection.booth.partNo",tehsilId);
	}
	
}

		
