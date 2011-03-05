package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.ICadreProblemDetailsDAO;
import com.itgrids.partyanalyst.model.ProblemHistory;

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
	
	
	/*@Test
	@SuppressWarnings("unchecked")
	public void testGetCadreProblemDetails(Long problemHistoryId){
		
		List results = cadreProblemDetailsDAO.getCadreDetailsByProblemHistoryId(107L);
		
		System.out.println(" Results :" + results.size());
	}*/
	
/*	public void testGetCadreProblemsCountInARegion()
	{
		List<Object> list = cadreProblemDetailsDAO.getCadreProblemsCountInARegion(1l,3l,19l);
		System.out.println(list.get(0).toString());
	}*/
	
	/*public void testGetCadreProblemsCountForAnUser()
	{
		List<Object> list = cadreProblemDetailsDAO.getCadreProblemsCountForAnUser(1l);
		System.out.println(list.get(0).toString());
	}*/
	
	public void testGetCadreProblemsInARegion()
	{
		List<ProblemHistory> list = cadreProblemDetailsDAO.getCadreProblemsInARegion(1l,3l,19l);
		System.out.println(list.size());
	}
	
	

}
