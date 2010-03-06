/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February, 2010
 * Author Saikrishna.g
 */

package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPersonalUserGroupDAO;
import com.itgrids.partyanalyst.model.PersonalUserGroup;

public class PersonalUserGroupDAO extends GenericDaoHibernate<PersonalUserGroup, Long> implements
		IPersonalUserGroupDAO {

	public PersonalUserGroupDAO()
	{
		super(PersonalUserGroup.class);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<PersonalUserGroup> getAllMyGroupsByUserId(Long userId){		
		return getHibernateTemplate().find("from PersonalUserGroup model where model.createdUserId.registrationId = ? and model.staticGroup.staticGroupId = null and model.parentGroupId = null"  ,userId);
	}
	
	@SuppressWarnings("unchecked")
	public List findSubGroupsCountInSystemGroupsByUserId(Long userId)
	{
		return getHibernateTemplate().find("select model.staticGroup.staticGroupId, model.staticGroup.groupName, count(model.personalUserGroupId) from PersonalUserGroup model where model.createdUserId.registrationId = ? and model.staticGroup.staticGroupId != null group by model.staticGroup.staticGroupId", userId);
	}
	
	@SuppressWarnings("unchecked")
	public List getSubGroupsCountInMyGroupsByUserId(Long userId, Long myGroupId)
	{
		Object[] params = {userId, myGroupId};	
		return getHibernateTemplate().find("select model.parentGroupId.personalUserGroupId, model.parentGroupName, count(model.personalUserGroupId) from PersonalUserGroup model where model.createdUserId.registrationId = ? and model.myGroup.myGroupId is not null and model.staticGroup.staticGroupId is null and model.parentGroupId.personalUserGroupId = ? group by model.parentGroupId.personalUserGroupId", params);
	}
}
