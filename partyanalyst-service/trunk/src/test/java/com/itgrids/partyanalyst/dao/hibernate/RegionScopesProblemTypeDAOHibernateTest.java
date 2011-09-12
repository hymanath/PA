package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IRegionScopesProblemTypeDAO;

public class RegionScopesProblemTypeDAOHibernateTest extends BaseDaoTestCase{

	IRegionScopesProblemTypeDAO regionScopesProblemTypeDAO;

	public void setRegionScopesProblemTypeDAO(
			IRegionScopesProblemTypeDAO regionScopesProblemTypeDAO) {
		this.regionScopesProblemTypeDAO = regionScopesProblemTypeDAO;
	}

	/*public void test()
	{
		regionScopesProblemTypeDAO.getAll();
	}*/
	
	public void testGetProblemTypesByRegionScopeId()
	{
		List<Object[]> list = regionScopesProblemTypeDAO.getProblemTypesByRegionScopeId(6l);
		System.out.println(list.size());
		
		for(Object[] objects : list)
		{
			System.out.print(objects[0].toString());
			System.out.println(objects[1].toString());
		}
	}
}
