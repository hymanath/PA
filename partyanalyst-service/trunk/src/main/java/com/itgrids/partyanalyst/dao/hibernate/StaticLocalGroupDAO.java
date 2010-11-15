/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 25, 2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IStaticLocalGroupDAO;
import com.itgrids.partyanalyst.model.StaticLocalGroup;

/**
 * @author Sai Krishna
 *
 */
public class StaticLocalGroupDAO extends GenericDaoHibernate<StaticLocalGroup, Long> implements
       IStaticLocalGroupDAO {

	public StaticLocalGroupDAO() {
		super(StaticLocalGroup.class);
	}

	@SuppressWarnings("unchecked")
	public List getAllStaticLocalGroups() {
		return getHibernateTemplate().find("select model.staticLocalGroupId,model.groupType,model.description from StaticLocalGroup model");
	}

	@SuppressWarnings("unchecked")
	public List getStaticLocalGroupsForAUser(Long userId) {
		return getHibernateTemplate().find("select model.staticLocalGroupId,model.groupType,model.description from StaticLocalGroup model "+
				"where model.user.registrationId = ?",userId);
	}

	
}
