package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserEmailDepartmentDAO;
import com.itgrids.partyanalyst.model.UserEmailDepartment;

public class UserEmailDepartmentDAO extends GenericDaoHibernate<UserEmailDepartment, Long> implements IUserEmailDepartmentDAO {
	public UserEmailDepartmentDAO(){
		super(UserEmailDepartment.class);
	}
	
}
