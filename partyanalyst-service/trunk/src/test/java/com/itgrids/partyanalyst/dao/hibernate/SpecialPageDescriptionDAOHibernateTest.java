package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.ISpecialPageDescriptionDAO;

public class SpecialPageDescriptionDAOHibernateTest extends BaseDaoTestCase{
	private ISpecialPageDescriptionDAO specialPageDescriptionDAO;
	
	
	public void setSpecialPageDescriptionDAO(
			ISpecialPageDescriptionDAO specialPageDescriptionDAO) {
		this.specialPageDescriptionDAO = specialPageDescriptionDAO;
	}


	public void test()
	{
		specialPageDescriptionDAO.getAll();
	}

}
