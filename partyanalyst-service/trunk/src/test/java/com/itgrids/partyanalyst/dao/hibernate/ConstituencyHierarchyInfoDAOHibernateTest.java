package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IConstituencyHierarchyInfoDAO;

public class ConstituencyHierarchyInfoDAOHibernateTest extends BaseDaoTestCase{

	private IConstituencyHierarchyInfoDAO constituencyHierarchyInfoDAO;

	public void setConstituencyHierarchyInfoDAO(
			IConstituencyHierarchyInfoDAO constituencyHierarchyInfoDAO) {
		this.constituencyHierarchyInfoDAO = constituencyHierarchyInfoDAO;
	}
	
	/*public void test()
	{
		constituencyHierarchyInfoDAO.getAll();
	}*/
	
	public void testgetConstituencyHierarchyInfoList()
	{
		constituencyHierarchyInfoDAO.getConstituencyHierarchyInfoList(232l, 1l);
	}
}
