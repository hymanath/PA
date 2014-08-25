package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDepartmentDAO;

public class DepartmentDAOHibernateTest  extends BaseDaoTestCase{
	private IDepartmentDAO departmentDAO;
	
	
	
	public IDepartmentDAO getDepartmentDAO() {
		return departmentDAO;
	}



	public void setDepartmentDAO(IDepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}

	public void test(){
		List<Object[]> list = departmentDAO.getAllDepartments();
		System.out.println(list.size());
	}

	
}
