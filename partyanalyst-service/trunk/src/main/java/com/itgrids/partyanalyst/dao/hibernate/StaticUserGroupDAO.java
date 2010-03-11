package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IStaticUserGroupDAO;
import com.itgrids.partyanalyst.model.StaticUserGroup;

public class StaticUserGroupDAO extends GenericDaoHibernate<StaticUserGroup, Long> implements IStaticUserGroupDAO {

	public StaticUserGroupDAO() {
		super(StaticUserGroup.class);
	}

	@SuppressWarnings("unchecked")
	public List getGroupMembersCountForAGroup(Long groupId,Long userId){
		Object[] params = {groupId,userId};
		return getHibernateTemplate().find("select count(model.staticUserGroupId) from StaticUserGroup model where model.personalUserGroup.personalUserGroupId = ? and model.staticUser.staticUserId = ?",params);
	}
	
}
