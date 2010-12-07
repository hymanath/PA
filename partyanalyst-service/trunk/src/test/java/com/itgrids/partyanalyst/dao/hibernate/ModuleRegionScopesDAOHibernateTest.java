package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IModuleRegionScopesDAO;

public class ModuleRegionScopesDAOHibernateTest extends BaseDaoTestCase{
	IModuleRegionScopesDAO moduleRegionScopesDAO;

	public IModuleRegionScopesDAO getModuleRegionScopesDAO() {
		return moduleRegionScopesDAO;
	}

	public void setModuleRegionScopesDAO(
			IModuleRegionScopesDAO moduleRegionScopesDAO) {
		this.moduleRegionScopesDAO = moduleRegionScopesDAO;
	}
	
	@SuppressWarnings("unchecked")
	public void testFindRegionScopesForModuleByState()
	{
		List result = moduleRegionScopesDAO.findRegionScopesForModuleByState(1L,1l);
		System.out.println("Results Size:"+result.size());
		for(int i = 0;i<result.size();i++)
		{
			Object[] obj = (Object[])result.get(i);
			System.out.println("id:"+Long.parseLong(obj[0].toString()));
			System.out.println("name:"+obj[1].toString());	
		}		
	}
}
