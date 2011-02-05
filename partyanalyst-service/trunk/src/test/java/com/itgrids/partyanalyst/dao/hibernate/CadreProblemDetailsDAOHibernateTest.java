package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICadreProblemDetailsDAO;

public class CadreProblemDetailsDAOHibernateTest extends BaseDaoTestCase{
	
	private ICadreProblemDetailsDAO cadreProblemDetailsDAO;

	public void setCadreProblemDetailsDAO(
			ICadreProblemDetailsDAO cadreProblemDetailsDAO) {
		this.cadreProblemDetailsDAO = cadreProblemDetailsDAO;
	}
	
	/*public void test(){
		cadreProblemDetailsDAO.getAll();		
	}*/
	
	public void testDeleteProblemDetailsByCadre()
	{
		int i = cadreProblemDetailsDAO.deleteProblemDetailsByCadre(43l);
		System.out.println(i);
	}

}
