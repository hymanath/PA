package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IAssignedProblemProgressDAO;
import com.itgrids.partyanalyst.model.AssignedProblemProgress;
import com.itgrids.partyanalyst.model.ProblemHistory;

public class AssignedProblemProgressDAOHibernateTest extends BaseDaoTestCase{

	private IAssignedProblemProgressDAO assignedProblemProgressDAO;

	public IAssignedProblemProgressDAO getAssignedProblemProgressDAO() {
		return assignedProblemProgressDAO;
	}

	public void setAssignedProblemProgressDAO(
			IAssignedProblemProgressDAO assignedProblemProgressDAO) {
		this.assignedProblemProgressDAO = assignedProblemProgressDAO;
	}
	
	/*public void testGetAll(){
		List<AssignedProblemProgress> list = assignedProblemProgressDAO.getAll();
		assertEquals(1, list.size());
	}	
		
	public void testByLocation(){
		List<AssignedProblemProgress> result = assignedProblemProgressDAO.getLatestProblemsByRegistrationIdAndStatusId(3l,4l,"true");
		assertEquals(1, result.size());
	}
	
	public void testAssigned(){
		List<AssignedProblemProgress> problemsUnderProgress = assignedProblemProgressDAO.findByRegistrationIdAndStatusId(3l, 5l);
		assertEquals(1, problemsUnderProgress.size());
	}	
	
	public void testDept(){
		List result =  assignedProblemProgressDAO.findProblemsForAHamletByHistoryId(538l);			
		assertEquals(1, result.size());
	}
	
	public void testGetAssignedProblemsProgressByLocation(){
		List list = assignedProblemProgressDAO.getAssignedProblemsProgressByLocation(1l);
		
		for(Object[] values:(List<Object[]>)list){
			System.out.println(values[0]+" "+values[1]+"\t"+values[2]+" "+values[3]+values[4]);
		}
		
		System.out.println(list.size());
	}*/
	
	/*@SuppressWarnings("unchecked")
	@Test
	public void testGetAssignedProblemProgressByRecentUpdate(){
		
		List<AssignedProblemProgress> result = assignedProblemProgressDAO.getProblemRecentUpdatesByProblemId(34L);
		
		System.out.println(" Result :" + result.size());
		
		for(AssignedProblemProgress res:result){
			
			System.out.println(" Id :" + res.getAssignedProblemProgressId());
			System.out.println(" Id :" + res.getAssignedProblemProgressId() + " On :" + res.getPerformedDate().toString());
			
			Date today = new Date();
			
			long diffDate = today.getTime() - res.getPerformedDate().getTime();
			
			System.out.println((diffDate / (1000 * 60 * 60 * 24)));
			
			
		}
		
		
	}*/
	
	/*public void testGetProblemsByDepartmentScope()
	{
		List<AssignedProblemProgress> list = assignedProblemProgressDAO.getProblemsByDepartmentScope(1l,1l);
		System.out.println(list.size());
	}*/
	
	/*public void testGetAssignedCadreProblemsCountInARegion()
	{
		List<Object> list = assignedProblemProgressDAO.getAssignedCadreProblemsCountInARegion(1l,3l,19l);
		System.out.println(list.size());
		for(Object obj:list)
		System.out.println(obj.toString());
	}*/
	
	/*public void testGetAssignedCadreProblemsCountForAnUser()
	{
		List<ProblemHistory> list = assignedProblemProgressDAO.getAssignedCadreProblemsInARegion(1l,3l,19l);
		System.out.println(list.size());
		for(Object obj:list)
		System.out.println(obj.toString());
	}*/
	
	/*public void testGetDepartmentWiseProblemStatus()
	{
		List<Object> list = assignedProblemProgressDAO.getDepartmentWiseProblemStatus(1l,2l);
		for(Object obj:list)
			System.out.println(obj.toString());
	}*/
	
	/*public void testGetDepartmentWiseProblemsBasedOnStatus()
	{
		String statusStr = "";
		List<ProblemHistory> list = assignedProblemProgressDAO.getDepartmentWiseProblemsBasedOnStatus(1l,2l,statusStr);
		for(ProblemHistory problemHistory : list)
		System.out.println(problemHistory.getProblemHistoryId());
		
	}*/
	/*public void testGetDepartmentWiseProblemStatusInARegion()
	{
		String deptLocationStr = " and model.departmentLocation.district.districtId = 19";
		List<Long> list = assignedProblemProgressDAO.getDeptWiseAssignedProblemProgressIds(1l,deptLocationStr);
		System.out.println(list.size());
		for(Long obj : list)
		{
			System.out.println(obj);
			//System.out.println("---"+obj[1].toString());
			//System.out.println("---"+obj[2].toString());
		}
	}*/
	
	/*public void testGetProblemsStatusBasedOnAssignedProblemProgressId()
	{
		List<Long> asPList = new ArrayList<Long>(0);
		asPList.add(77l);
		asPList.add(75l);
		asPList.add(78l);
		
		List<Object[]> list = assignedProblemProgressDAO.getProblemsStatusBasedOnAssignedProblemProgressId(1l,asPList);
		System.out.println(list.size());
		
		for(Object[] obj : list)
			for(Object b : obj)
			System.out.println(b.toString());
	}*/
	
	public void testGetProblemsBasedOnAssignedProblemProgressIdAndStatus()
	{
		List<Long> asPList = new ArrayList<Long>(0);
		asPList.add(77l);
		asPList.add(75l);
		asPList.add(78l);
		
		String deptStr = " and model.departmentOrganisation.departmentOrganisationId = 2";
		String status = "NEW";
		String statusStr = " and model.problemHistory.problemStatus.status = '"+status+"'";
		
		List<ProblemHistory> list = assignedProblemProgressDAO.getProblemsBasedOnAssignedProblemProgressIdAndStatus(1l,asPList,deptStr,statusStr);
		
		System.out.println(list.size());
		
		for(ProblemHistory problemHistory : list)
			System.out.println(problemHistory.getProblemStatus().getStatus());
	}
	
}
