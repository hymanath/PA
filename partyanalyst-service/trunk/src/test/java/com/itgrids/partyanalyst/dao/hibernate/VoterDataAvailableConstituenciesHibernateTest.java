package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterDataAvailableConstituenciesDAO;
import com.itgrids.partyanalyst.model.VoterDataAvailableConstituencies;

public class VoterDataAvailableConstituenciesHibernateTest extends BaseDaoTestCase{
private IVoterDataAvailableConstituenciesDAO voterDataAvailableConstituenciesDAO;

public void setVoterDataAvailableConstituenciesDAO(
		IVoterDataAvailableConstituenciesDAO voterDataAvailableConstituenciesDAO) {
	this.voterDataAvailableConstituenciesDAO = voterDataAvailableConstituenciesDAO;
}
public void test()
{
	List<VoterDataAvailableConstituencies> list = voterDataAvailableConstituenciesDAO.getPublicationDatesBasedOnConstituency();
	for(VoterDataAvailableConstituencies params : list)
		System.out.println(params.getConstituency().getConstituencyId() +" --" +params.getPublicationDate().getPublicationDateId());
	
	
	
}
}
