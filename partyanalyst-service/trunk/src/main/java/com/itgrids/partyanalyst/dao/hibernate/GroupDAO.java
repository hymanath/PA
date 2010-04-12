package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGroupDAO;
import com.itgrids.partyanalyst.model.Group;

public class GroupDAO extends GenericDaoHibernate<Group, Long> implements IGroupDAO{

	public GroupDAO() {
		super(Group.class);
	}

}
