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

import com.itgrids.partyanalyst.dao.IStaticUserDesignationDAO;
import com.itgrids.partyanalyst.model.StaticUserDesignation;

/**
 * @author Sai Krishna
 *
 */
public class StaticUserDesignationDAO extends GenericDaoHibernate<StaticUserDesignation, Long>
		implements IStaticUserDesignationDAO {

	public StaticUserDesignationDAO() {
		super(StaticUserDesignation.class);
	}

	@SuppressWarnings("unchecked")
	public List getDesignationsByStaticLocalGroupId(Long staticLocalGroupId) {
		
		return getHibernateTemplate().find("select model.staticUserDesignationId,model.designationType from "+
				"StaticUserDesignation model where model.staticLocalGroup.staticLocalGroupId = ?",staticLocalGroupId);
	}

}
