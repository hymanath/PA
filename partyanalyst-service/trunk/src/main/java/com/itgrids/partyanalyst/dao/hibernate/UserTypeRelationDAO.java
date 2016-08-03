package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserTypeRelationDAO;
import com.itgrids.partyanalyst.model.UserTypeRelation;

public class UserTypeRelationDAO extends GenericDaoHibernate<UserTypeRelation,Long> implements IUserTypeRelationDAO {
	
	public UserTypeRelationDAO() {
		super(UserTypeRelation.class);
	}
	
	

}
