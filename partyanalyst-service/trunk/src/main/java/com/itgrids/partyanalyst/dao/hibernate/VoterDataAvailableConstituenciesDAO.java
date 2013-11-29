package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVoterDataAvailableConstituenciesDAO;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.model.VoterDataAvailableConstituencies;

public class VoterDataAvailableConstituenciesDAO extends GenericDaoHibernate<VoterDataAvailableConstituencies,Long> implements IVoterDataAvailableConstituenciesDAO{

	public VoterDataAvailableConstituenciesDAO() {
		super(VoterDataAvailableConstituencies.class);
		
	}
	
	public List<Object[]> getConstituencies()
	{
		return getHibernateTemplate().find("select distinct model.constituency.constituencyId,model.constituency.name from VoterDataAvailableConstituencies model");
	}

	

}
