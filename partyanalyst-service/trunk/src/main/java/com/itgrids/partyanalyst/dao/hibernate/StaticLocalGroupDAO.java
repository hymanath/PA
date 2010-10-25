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

	
}
