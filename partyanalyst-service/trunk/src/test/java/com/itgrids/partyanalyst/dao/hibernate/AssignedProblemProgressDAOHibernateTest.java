package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAssignedProblemProgressDAO;
import com.itgrids.partyanalyst.model.Cadre;
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
		asPList.add(94l);
		asPList.add(22l);
		asPList.add(91l);
		asPList.add(84l);
		asPList.add(97l);
		asPList.add(24l);
		asPList.add(146l);
		asPList.add(54l);
		asPList.add(76l);
		asPList.add(165l);
		asPList.add(132l);
		asPList.add(95l);
		
		
		String deptStr = " and model.departmentLocation.problemCompleteLocationId is not null and model.problemActivity.problemActivityId=3";
		String status = "NEW";
		String statusStr = " and model.problemHistory.problemStatus.status = '"+status+"'";
		
		List<ProblemHistory> list = assignedProblemProgressDAO.getProblemsBasedOnAssignedProblemProgressIdAndStatus(1l,asPList,deptStr,statusStr);
		
		System.out.println(list.size());
		
		for(ProblemHistory problemHistory : list){
			System.out.println(problemHistory.getProblemStatus().getStatus());
			System.out.println(problemHistory.getIsApproved());
			}
		}
	
		
	/*public void testgetByCadreId()
	{
		List<AssignedProblemProgress> list = assignedProblemProgressDAO.getByCadreId(109l);
		System.out.println(list.size());
	}*/
	
	/*public void testGetCadreForCadreProblemsInARegion()
	{
		String locationStr = " and model.problemHistory.problemLocation.problemCompleteLocation.district.districtId = 19";
		List<Cadre> list = assignedProblemProgressDAO.getCadreForCadreProblemsInARegion(1l,locationStr);
		System.out.println(list.size());
		
		for(Cadre cadre :list)
		{
			System.out.println(cadre.getCadreId());
		}
	}
	
	public void testGetCadreForCadreProblemsForAnUser()
	{
		List<Cadre> list = assignedProblemProgressDAO.getCadreForCadreProblemsForAnUser(1l);
		System.out.println(list.size());
		for(Cadre cadre :list)
		{
			System.out.println(cadre.getCadreId());
		}
	}*/
	
	/*public void testGetProblemStatusOfACadre()
	{
		List<Object> list = assignedProblemProgressDAO.getProblemStatusOfACadre(88l);
		System.out.println(list.size());
		
		for(Object object : list)
			System.out.println(object.toString());
	}
	
	public void testGetCadreForCadreProblemsForAnUser()
	{
		List<Cadre> list = assignedProblemProgressDAO.getCadreForCadreProblemsForAnUser(1l);
		System.out.println("--------------"+list.size());
		
		for(Cadre cadre :list)
		{
			System.out.println(cadre.getCadreId());
		}
	}*/
	
}
