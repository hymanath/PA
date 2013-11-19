package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.INominationDAO;

public class NominationDAOHibernateTest extends BaseDaoTestCase{
	
	private INominationDAO nominationDAO;

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}
	
	/*public void test()
	{
		nominationDAO.getAll();
	}*/
	
	public void testgetCandidatesListByPartyIdsList()
	{
		List<Long> partyIdsList = new ArrayList<Long>(0);
		partyIdsList.add(163L);
		List<Object[]> list = null;//nominationDAO.getCandidatesListByPartyIdsList(partyIdsList);
		System.out.println(list.size());
		for(Object[] params:list)
		 System.out.println(params[0]+" "+params[1]);
	}
	
	/*public void testgetCandidatesParticipatedInAssemblyAndParlimentElections()
	{
		
		List<Object[]> list = nominationDAO.getCandidatesParticipatedInAssemblyAndParlimentElections(163l);
		System.out.println(list.size());
		for(Object[] params:list)
		 System.out.println(params[0]+" "+params[1]);
	}*/

}
