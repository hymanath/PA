package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemTypeDAO;
import com.itgrids.partyanalyst.model.ProblemType;

public class ProblemTypeDAOHibernateTest extends BaseDaoTestCase{
	
	private IProblemTypeDAO problemTypeDAO;

	public void setProblemTypeDAO(IProblemTypeDAO problemTypeDAO) {
		this.problemTypeDAO = problemTypeDAO;
	}
	
	/*public void test()
	{
		List<ProblemType> list = problemTypeDAO.getAll();
		for(ProblemType prob : list){
		System.out.println(prob.getProblemType());
		}
	}*/
	public void testGetProblemTypes()
	{
		List<Object[]> list = problemTypeDAO.getProblemTypes();
		System.out.println(list.size());
		if(list != null && list.size() > 0)
		{
			for(Object[] params: list){
				System.out.println(params[0]+"----------"+params[1].toString());
			}
		}
	}
}
