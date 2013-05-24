package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAreaTypeDAO;

public class AreaTypeDAOHibernateTest extends BaseDaoTestCase{
	
	private IAreaTypeDAO areaTypeDAO;
	
	 public void setAreaTypeDAO(IAreaTypeDAO areaTypeDAO) {
			this.areaTypeDAO = areaTypeDAO;
		}

	 
		public void testGetAll()
		{
			System.out.println(areaTypeDAO.getAll().size());
			
		}
}
