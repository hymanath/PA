package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGroupDAO;
import com.itgrids.partyanalyst.model.Group;

public class GroupDAO extends GenericDaoHibernate<Group,Long> implements IGroupDAO {

	public GroupDAO(){
		super(Group.class);
	}

	public List<Object[]> getAllGroups(){
		
		Query query = getSession().createQuery("select model.groupId,model.name from Group model");
		
		return query.list();
	}
}
