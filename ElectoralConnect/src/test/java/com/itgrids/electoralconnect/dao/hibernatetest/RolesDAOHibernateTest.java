package com.itgrids.electoralconnect.dao.hibernatetest;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.electoralconnect.dao.IRolesDAO;
import com.itgrids.electoralconnect.dao.IUserRolesDAO;
import com.itgrids.electoralconnect.dao.hibernate.RolesDAO;

public class RolesDAOHibernateTest extends BaseDaoTestCase{
	
	private IRolesDAO rolesDAO;

	public void setRolesDAO(IRolesDAO rolesDAO) {
		this.rolesDAO = rolesDAO;
	}
	
	public void test(){
		rolesDAO.getAll();
	}

	
}
