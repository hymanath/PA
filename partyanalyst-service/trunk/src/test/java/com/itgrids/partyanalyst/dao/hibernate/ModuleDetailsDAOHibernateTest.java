package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IModuleDetailsDAO;
import com.itgrids.partyanalyst.utils.IConstants;

public class ModuleDetailsDAOHibernateTest extends BaseDaoTestCase{
	IModuleDetailsDAO moduleDetailsDAO;

	public IModuleDetailsDAO getModuleDetailsDAO() {
		return moduleDetailsDAO;
	}

	public void setModuleDetailsDAO(IModuleDetailsDAO moduleDetailsDAO) {
		this.moduleDetailsDAO = moduleDetailsDAO;
	}
	
	@SuppressWarnings("unchecked")
	public void testFindModuleIdByModuleName()
	{
		List result = moduleDetailsDAO.findModuleIdByModuleName(IConstants.ADD_NEW_PROBLEM);
		Long id  = Long.parseLong(result.get(0).toString());
		System.out.print("id"+id);			
	}
}
