/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 25, 2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ILocalGroupRegionDAO;
import com.itgrids.partyanalyst.model.LocalGroupRegion;

/**
 * @author Sai Krishna
 *
 */
public class LocalGroupRegionDAO extends GenericDaoHibernate<LocalGroupRegion, Long> implements
		ILocalGroupRegionDAO {

	public LocalGroupRegionDAO() {
		super(LocalGroupRegion.class);
	}

}
