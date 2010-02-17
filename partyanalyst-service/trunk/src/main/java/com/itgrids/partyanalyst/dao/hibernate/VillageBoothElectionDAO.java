package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVillageBoothElectionDAO;
import com.itgrids.partyanalyst.model.VillageBoothElection;

public class VillageBoothElectionDAO extends GenericDaoHibernate<VillageBoothElection, Long> implements IVillageBoothElectionDAO{

	public VillageBoothElectionDAO(){
		super(VillageBoothElection.class);
	}
	
}
