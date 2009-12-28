/* 
 * Copyright (c) 2009 IT Grids Limited.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 23, 2009
 */
package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemClassificationDAO;
import com.itgrids.partyanalyst.model.ProblemClassification;

public class ProblemClassificationDAO extends GenericDaoHibernate<ProblemClassification, Long> implements IProblemClassificationDAO {

	public ProblemClassificationDAO() {
		super(ProblemClassification.class);
		
	}

}
