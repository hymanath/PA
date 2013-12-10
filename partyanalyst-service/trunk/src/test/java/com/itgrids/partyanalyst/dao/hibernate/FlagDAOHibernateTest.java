package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFlagDAO;

public class FlagDAOHibernateTest extends BaseDaoTestCase{
	private IFlagDAO flagDAO;

	
	public IFlagDAO getFlagDAO() {
		return flagDAO;
	}


	public void setFlagDAO(IFlagDAO flagDAO) {
		this.flagDAO = flagDAO;
	}


	/*public void test()
	{
		flagDAO.getA();
	}
*/
}
