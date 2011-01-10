package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemCompleteLocationDAO;
import com.itgrids.partyanalyst.model.ProblemCompleteLocation;

public class ProblemCompleteLocationDAOHibernateTest extends BaseDaoTestCase {

	private IProblemCompleteLocationDAO problemCompleteLocationDAO;

	public IProblemCompleteLocationDAO getProblemCompleteLocationDAO() {
		return problemCompleteLocationDAO;
	}

	public void setProblemCompleteLocationDAO(
			IProblemCompleteLocationDAO problemCompleteLocationDAO) {
		this.problemCompleteLocationDAO = problemCompleteLocationDAO;
	}
	
	public void testGetAllData(){
		List<ProblemCompleteLocation> result =  problemCompleteLocationDAO.getAll();
		System.out.println(result.size());
	}
}
