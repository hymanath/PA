package com.itgrids.partyanalyst.social.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.social.dao.IPartySocialDAO;

public class PartySocialDAOHibernateTest extends BaseDaoTestCase {

	private IPartySocialDAO partySocialDAO;

	public void setPartySocialDAO(IPartySocialDAO partySocialDAO) {
		this.partySocialDAO = partySocialDAO;
	}

	public void test()
	{
		partySocialDAO.getAll();
	}
	
	/*public void testGetAllAnswersForTheQuestion(){
		List l=partySocialDAO.getNames();
		System.out.println(l.size());
		System.out.println(l.get(0));
	}*/
	
}
