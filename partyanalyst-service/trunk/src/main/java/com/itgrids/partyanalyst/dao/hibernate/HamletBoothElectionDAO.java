package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IHamletBoothElectionDAO;
import com.itgrids.partyanalyst.model.HamletBoothElection;

public class HamletBoothElectionDAO extends GenericDaoHibernate<HamletBoothElection, Long> implements IHamletBoothElectionDAO{

	public HamletBoothElectionDAO(){
		super(HamletBoothElection.class);
	}
	
}
