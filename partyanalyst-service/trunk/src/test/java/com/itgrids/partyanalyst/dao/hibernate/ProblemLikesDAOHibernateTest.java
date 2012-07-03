package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemLikesDAO;

public class ProblemLikesDAOHibernateTest extends BaseDaoTestCase{

	private IProblemLikesDAO problemLikesDAO;
	
	public void setProblemLikesDAO(IProblemLikesDAO problemLikesDAO) {
		this.problemLikesDAO = problemLikesDAO;
	}
	/*public void test()
	{
		problemLikesDAO.getAll();
	}*/
	
	public void testgetAllLikes()
	{
		List<Long> list = problemLikesDAO.getAllLikes(1l);
		System.out.println(list.size());
	}
}