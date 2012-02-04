package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyElectionResultsWithAreaTypeDAO;

public class PartyElectionResultsWithAreaTypeDAOHibernateTest extends BaseDaoTestCase{

	IPartyElectionResultsWithAreaTypeDAO partyElectionResultsWithAreaTypeDAO;

	public void setPartyElectionResultsWithAreaTypeDAO(
			IPartyElectionResultsWithAreaTypeDAO partyElectionResultsWithAreaTypeDAO) {
		this.partyElectionResultsWithAreaTypeDAO = partyElectionResultsWithAreaTypeDAO;
	}
	
	/*public void test()
	{
		partyElectionResultsWithAreaTypeDAO.getAll();
	}*/
	
	/*public void testGetCountOfPartiesInAElection()
	{
		List<Long> partiesList = new ArrayList<Long>(0);
		partiesList.add(362l);
		partiesList.add(872l);
		
		Object object = partyElectionResultsWithAreaTypeDAO.getCountOfPartiesInAElection(38l,partiesList);
		System.out.println(object);
	}*/
	
	public void testGetPartiesElectionResultInConstituencyAreaTypes()
	{
		List<Long> partiesList = new ArrayList<Long>(0);
		partiesList.add(362l);
		partiesList.add(872l);
		
		List<Object[]> list = partyElectionResultsWithAreaTypeDAO.getPartiesElectionResultInConstituencyAreaTypes(38l,partiesList);
		
		System.out.println(list.size());
	}
}
