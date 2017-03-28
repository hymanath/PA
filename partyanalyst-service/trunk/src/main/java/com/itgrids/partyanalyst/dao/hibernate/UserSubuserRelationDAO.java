package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IUserSubuserRelationDAO;
import com.itgrids.partyanalyst.model.UserSubuserRelation;

public class UserSubuserRelationDAO extends GenericDaoHibernate<UserSubuserRelation, Long> implements IUserSubuserRelationDAO {

	public UserSubuserRelationDAO()
	{
		super(UserSubuserRelation.class);
	}

}
