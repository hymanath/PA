/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 23, 2009
 */
package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemSourceDAO;
import com.itgrids.partyanalyst.model.ProblemSource;

public class ProblemSourceDAO extends GenericDaoHibernate<ProblemSource, Long> implements IProblemSourceDAO {

	public ProblemSourceDAO() {
		super(ProblemSource.class);
		
	}

	
}
