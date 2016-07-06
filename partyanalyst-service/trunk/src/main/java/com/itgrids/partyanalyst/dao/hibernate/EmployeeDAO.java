package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEmployeeDAO;
import com.itgrids.partyanalyst.model.Employee;

public class EmployeeDAO extends GenericDaoHibernate<Employee, Long> implements IEmployeeDAO {
	public EmployeeDAO(){
		super(Employee.class);
	}
}
