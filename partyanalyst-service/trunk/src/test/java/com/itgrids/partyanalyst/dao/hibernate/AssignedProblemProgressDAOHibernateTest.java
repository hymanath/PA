package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAssignedProblemProgressDAO;
import com.itgrids.partyanalyst.model.AssignedProblemProgress;

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
	}*/
	
	public void testGetAssignedProblemsProgressByLocation(){
		List list = assignedProblemProgressDAO.getAssignedProblemsProgressByLocation(1l);
		
		for(Object[] values:(List<Object[]>)list){
			System.out.println(values[0]+" "+values[1]+"\t"+values[2]+" "+values[3]+values[4]);
		}
		
		System.out.println(list.size());
	}
}
