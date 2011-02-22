/* 
 * Copyright (c) 2011 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 19, 2011
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemActivityDAO;
import com.itgrids.partyanalyst.model.ProblemActivity;

/**
 * @author Sai Krishna
 *
 */
public class ProblemActivityDAO extends GenericDaoHibernate<ProblemActivity, Long> implements
		IProblemActivityDAO {

	public ProblemActivityDAO() {
		super(ProblemActivity.class);
	}

	@SuppressWarnings("unchecked")
	public List<ProblemActivity> getProblemActivityByName(String activityName) {
		
		return getHibernateTemplate().find("from ProblemActivity model where model.activityDescription = ?",activityName);
	}

	

}
