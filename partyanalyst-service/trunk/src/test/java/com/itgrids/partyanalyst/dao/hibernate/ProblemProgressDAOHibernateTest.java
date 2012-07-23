package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemProgressDAO;
import com.itgrids.partyanalyst.model.ProblemProgress;

public class ProblemProgressDAOHibernateTest extends BaseDaoTestCase{

	private IProblemProgressDAO problemProgressDAO;

	public void setProblemProgressDAO(IProblemProgressDAO problemProgressDAO) {
		this.problemProgressDAO = problemProgressDAO;
	}
	
	/*public void test()
	{
		problemProgressDAO.getAll();
	}
	public void testGetProblemPrograss()
	{
		List<ProblemProgress> list = problemProgressDAO.getProblemPrograss(2l);
		System.out.println(list.size());
	}*/
	public void testa(){
	int result=problemProgressDAO.updateActivityVisibility(2l,1l);
	System.out.println(result);

	}
}
