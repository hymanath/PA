package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IStaticUserGroupDAO;
import com.itgrids.partyanalyst.model.StaticUserGroup;

public class StaticUserGroupDAO extends GenericDaoHibernate<StaticUserGroup, Long> implements IStaticUserGroupDAO {

	public StaticUserGroupDAO() {
		super(StaticUserGroup.class);

	}

	
}
