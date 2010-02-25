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
	public List<PersonalUserGroup> findByGroupName(String groupName) {
		List<PersonalUserGroup>personalUserGroup=getHibernateTemplate().find("from PersonalUserGroup model where model.groupName= ?",groupName);
		return personalUserGroup;
	}

	@Override
	public boolean exists(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PersonalUserGroup get(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonalUserGroup> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Long arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public PersonalUserGroup save(PersonalUserGroup arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
