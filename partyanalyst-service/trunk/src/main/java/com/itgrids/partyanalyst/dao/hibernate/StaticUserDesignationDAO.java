/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 25, 2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

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

}
