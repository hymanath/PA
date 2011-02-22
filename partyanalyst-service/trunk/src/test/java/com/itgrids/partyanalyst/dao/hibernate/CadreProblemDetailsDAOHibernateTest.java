package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.ICadreProblemDetailsDAO;

public class CadreProblemDetailsDAOHibernateTest extends BaseDaoTestCase{
	
	private ICadreProblemDetailsDAO cadreProblemDetailsDAO;

	public void setCadreProblemDetailsDAO(
			ICadreProblemDetailsDAO cadreProblemDetailsDAO) {
		this.cadreProblemDetailsDAO = cadreProblemDetailsDAO;
	}
	
	/*public void test(){
		cadreProblemDetailsDAO.getAll();		
	}
	
	public void testDeleteProblemDetailsByCadre()
	{
		int i = cadreProblemDetailsDAO.deleteProblemDetailsByCadre(43l);
		System.out.println(i);
	}*/
	
	
	@Test
	@SuppressWarnings("unchecked")
	public void testGetCadreProblemDetails(Long problemHistoryId){
		
		List results = cadreProblemDetailsDAO.getCadreDetailsByProblemHistoryId(107L);
		
		System.out.println(" Results :" + results.size());
	}

}
