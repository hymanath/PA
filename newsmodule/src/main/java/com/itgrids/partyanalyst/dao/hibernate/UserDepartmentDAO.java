package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserDepartmentDAO;
import com.itgrids.partyanalyst.model.UserDepartment;

public class UserDepartmentDAO  extends GenericDaoHibernate<UserDepartment,Long> implements IUserDepartmentDAO {

	public  UserDepartmentDAO(){
		super(UserDepartment.class);
	}
}
