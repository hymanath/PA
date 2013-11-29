package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterDataAvailableConstituenciesDAO;

public class VoterDataAvailableConstituenciesHibernateTest extends BaseDaoTestCase{
private IVoterDataAvailableConstituenciesDAO voterDataAvailableConstituenciesDAO;

public void setVoterDataAvailableConstituenciesDAO(
		IVoterDataAvailableConstituenciesDAO voterDataAvailableConstituenciesDAO) {
	this.voterDataAvailableConstituenciesDAO = voterDataAvailableConstituenciesDAO;
}
public void test()
{
	voterDataAvailableConstituenciesDAO.getAll();
}
}
