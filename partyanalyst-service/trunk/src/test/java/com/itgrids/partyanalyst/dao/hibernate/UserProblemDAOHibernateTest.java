package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IUserProblemDAO;
import com.itgrids.partyanalyst.model.UserProblem;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserProblemDAOHibernateTest extends BaseDaoTestCase{

	private IUserProblemDAO userProblemDAO;

	public void setUserProblemDAO(IUserProblemDAO userProblemDAO) {
		this.userProblemDAO = userProblemDAO;
	}
	
	/*public void test()
	{
		userProblemDAO.getAll();
	}
	public void testgetAllValidProblemIds()
	{
		
		List<Long> list = userProblemDAO.getAllValidProblemIds(1, 3);
		System.out.println(list.size());
		
		
	}*/
	
public void testgetAllValidProblemsCount()
	{
		List<Long> list = userProblemDAO.getAllValidProblemIdsCount();
		System.out.println(list.size());
	}
	
/*public void testgetProblemCompleteInfo()
	{
		List<Object[]> list = userProblemDAO.getProblemCompleteInfo(1l);
		for(Object[] params : list)
		{
			System.out.println(params[0]);
			System.out.println(params[1].toString());
		}
	}*/
	
	/*public void testgetAllPRoblemDetails()
	{
		List<UserProblem> userproblem = userProblemDAO.getAllProblemDetails(1l);
		for(UserProblem list : userproblem)
		{
			System.out.println(list.getProblem().getTitle());
		}
	}
*/
	/*public void testgetAllPostedProblemsByAnanymousUserId(){
	
	List list1 = userProblemDAO.getAllPostedProblemsByAnanymousUserId(2L, 0, 5, "desc", "model.problem.problemId", IConstants.TOTAL);
	System.out.println(list1.size());
}
	*/
	/*public void testgetAllPostedPRoblemCount()
	{
		 List list = userProblemDAO.public List getAllPostedProblemCount(2l);
		 System.out.println(list.size());
	}*/
	
	/*public void testgetAllPostedProblemCountOtherThanLoggedInUser()
	{
		List list = userProblemDAO.getAllPostedProblemCountOtherThanLoggedInUser(2l);
		System.out.println(list.size());
	}
*/
	
	/*public void testgetCommonDataForAllProblems()
	{
		String result = userProblemDAO.getCommonDataForAllProblems();
		System.out.println(result);
	}*/
	
	/*public void testgetAllProblemHistoryIdsForGivenLocationByTheirIds(){
		
		List<Long> locationIds =  new ArrayList<Long>(0);
		locationIds.add(10l);
		String impactLevel = "District";
		String isApproved = "true";
		List list = userProblemDAO.getAllProblemHistoryIdsForGivenLocationByTheirIds(locationIds,impactLevel,isApproved);
		System.out.println(list.size());
		
		
		
	}*/
}
