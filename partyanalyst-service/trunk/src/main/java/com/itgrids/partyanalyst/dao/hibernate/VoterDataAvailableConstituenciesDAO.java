package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVoterDataAvailableConstituenciesDAO;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.model.VoterDataAvailableConstituencies;

public class VoterDataAvailableConstituenciesDAO extends GenericDaoHibernate<VoterDataAvailableConstituencies,Long> implements IVoterDataAvailableConstituenciesDAO{

	public VoterDataAvailableConstituenciesDAO() {
		super(VoterDataAvailableConstituencies.class);
		
	}

	

}
