/* 
 * Copyright (c) 2011 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 19, 2011
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IProblemActivityDAO;
import com.itgrids.partyanalyst.model.ProblemActivity;

/**
 * @author Sai Krishna
 *
 */
public class ProblemActivityDAOHibernateTest extends BaseDaoTestCase {

	private IProblemActivityDAO problemActivityDAO;

	public IProblemActivityDAO getProblemActivityDAO() {
		return problemActivityDAO;
	}

	public void setProblemActivityDAO(IProblemActivityDAO problemActivityDAO) {
		this.problemActivityDAO = problemActivityDAO;
	}
	
	@Test
	public void testGetAllProblemActivities(){
		
		List<ProblemActivity> probActivitiesList = problemActivityDAO.getProblemActivityByName("PROBLEM_TYPE_UPDATE");
		
		System.out.println(" Results Size :" + probActivitiesList.size());
	}
}
