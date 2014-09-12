package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IIvrMobileDAO;

public class IvrMobileDAOHibernateTest extends BaseDaoTestCase{
	
	private IIvrMobileDAO ivrMobileDAO;

	
	
	public void setIvrMobileDAO(IIvrMobileDAO ivrMobileDAO) {
		this.ivrMobileDAO = ivrMobileDAO;
	}



	public void test()
	{
	List<Long>	list = ivrMobileDAO.getMobilenos(1l, 1l, 0);
	 System.out.println(list);
	}

}
 