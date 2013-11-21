package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDesignationDAO;

public class DesignationDAOHibernateTest extends BaseDaoTestCase{
	
	private IDesignationDAO designationDAO;

	public void setDesignationDAO(IDesignationDAO designationDAO) {
		this.designationDAO = designationDAO;
	}
	
	/*public void test()
	{
	  designationDAO.getAll();
	}*/

	public void testGetDesignationsList()
	{
	  System.out.println(designationDAO.getDesignationsList().size());
	}
}
