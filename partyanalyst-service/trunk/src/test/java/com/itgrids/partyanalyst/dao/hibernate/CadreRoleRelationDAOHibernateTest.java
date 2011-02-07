package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICadreRoleRelationDAO;

public class CadreRoleRelationDAOHibernateTest extends BaseDaoTestCase{
	
	private ICadreRoleRelationDAO cadreRoleRelationDAO;

	public void setCadreRoleRelationDAO(ICadreRoleRelationDAO cadreRoleRelationDAO) {
		this.cadreRoleRelationDAO = cadreRoleRelationDAO;
	}
	
	/*public void test(){
		cadreRoleRelationDAO.getAll();
	}*/
	
	/*public void testGetRolesByCadreId(){
		List<Object[]> list = cadreRoleRelationDAO.getRolesByCadreId(111l);
		System.out.println(list.size());
	}*/
	
	public void testDeleteRolesByCadreId()
	{
		int deletedRows = cadreRoleRelationDAO.deleteRolesByCadreId(119l);
		System.out.println("deletedRows===="+deletedRows);
	}

}
