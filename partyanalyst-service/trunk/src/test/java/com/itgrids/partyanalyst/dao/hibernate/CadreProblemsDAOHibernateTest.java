package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICadreProblemsDAO;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.CadreProblems;
import com.itgrids.partyanalyst.model.Problem;

public class CadreProblemsDAOHibernateTest extends BaseDaoTestCase{

	private ICadreProblemsDAO cadreProblemsDAO;

	public void setCadreProblemsDAO(ICadreProblemsDAO cadreProblemsDAO) {
		this.cadreProblemsDAO = cadreProblemsDAO;
	}
	
	public void test()
	{
		cadreProblemsDAO.getAll();
	}
	
	/*public void testgetCadreProblemsCountForAnUser()
	{
	List<Object> value = cadreProblemsDAO.getCadreProblemsCountForAnUser(1l);
	System.out.println(value.size());
	}*/
	
	/*public void testgetCadreProblemsCountInARegion()
	{
		String locationStr = "and model.problem.problemCompleteLocation.state.stateId = 10";
		List<Object> value = cadreProblemsDAO.getCadreProblemsCountInARegion(1l,locationStr);
		System.out.println(value.size());
	}*/
	
	/*public void testgetCadreProblemsInARegion(){
		String locationStr = "and model.problem.problemCompleteLocation.state.stateId = 10l";
		List<Problem> list = cadreProblemsDAO.getCadreProblemsInARegion(1l, locationStr) ;
		
		for(Problem problem : list)
		{
			System.out.println(problem.getDescription());
		}
	}*/
	
	/*public void testgetCadreProblemsForAnUser()
	{
		List<Problem> list =cadreProblemsDAO.getCadreProblemsForAnUser(1l);
		for(Problem problem : list)
		{
			System.out.println(problem.getDescription());
		}
	}*/
	
	/*public void testgetCadreForCadreProblemsInARegion()
	{
		String locationStr = "and model.problem.problemCompleteLocation.state.stateId = 1";
		List<Cadre> cadre = cadreProblemsDAO.getCadreForCadreProblemsInARegion(1l,locationStr);
		for(Cadre list : cadre)
		{
			System.out.println(list.getFirstName());
		}
	}
	*/
	
	/*public void testgetCadreForCadreProblemsForAnUser()
	{
		List<Cadre> cadreDetails = cadreProblemsDAO.getCadreForCadreProblemsForAnUser(1l);
		for(Cadre cadre : cadreDetails)
		{
			System.out.println(cadre.getFirstName());
		}
	}*/
	
	/*public void testgetProblemStatusOfACadre()
	{
		List<Object> status = cadreProblemsDAO.getProblemStatusOfACadre(145l);
		System.out.println(status);
	}*/
	
	/*public void testgetCadreProblemDetailsByProblemId()
	{
	List<CadreProblems> params = cadreProblemsDAO.getCadreProblemDetailsByProblemId(65l);
	System.out.println(params.get(0).getCadre().getFirstName());
	}*/
	
	public void testdeleteCadreProblem()
	{
	Integer params = cadreProblemsDAO.deleteCadreProblem(65l);
	System.out.println(params);
	}
	
	
		
	}
