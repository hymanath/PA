package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IUserProblemDAO;
import com.itgrids.partyanalyst.dto.ProblemSearchVO;
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
	}*/
	/*public void testGetUserProblemId()
	{
		List<UserProblem> userProblem = userProblemDAO.getUserProblemId(1l,1l);
		System.out.println(userProblem.get(0));
	}*/
	/*public void testGetAllProblemsOfCurrentDateByFreeUser()throws Exception
	{
		SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN_YYYY_MM_DD);
		List<Object> list = userProblemDAO.getAllProblemsOfCurrentDateByFreeUser(sdf.parse("2012/07/01"),sdf.parse("2012/07/05"),IConstants.FALSE);
		System.out.println("size" +list.size());
		for(int i=0;i<list.size();i++){
	     Object[] obj =(Object[])list.get(i);
		
			System.out.println("1"+obj[0]);
			System.out.println("1"+obj[1]);
			System.out.println("1"+obj[2]);
			System.out.println("1"+obj[3]);
			System.out.println("1"+obj[4]);
			System.out.println("1"+obj[5]);
			System.out.println("1"+obj[6]);
		}
	}*/
	/*public void testgetAllValidProblemIds()
	{
		
		List<Long> list = userProblemDAO.getAllValidProblemIds(1, 3);
		System.out.println(list.size());
		
		
	}*/
	
/*public void testgetAllValidProblemsCount()
	{
		List<Long> list = userProblemDAO.getAllValidProblemIdsCount();
		System.out.println(list.size());
	}*/
	
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
	}*/

	
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

	/*public void testGetStates()
	{
		List<Object[]> list = userProblemDAO.getStates();
		System.out.println(list.size());
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
			{
				System.out.println(params[0]);
				System.out.println(params[1]);
			}
		}
		
	}*/

/*public void testgetProblemPostedUserDetails()
{
	 List<Object[]> list = userProblemDAO.getProblemPostedUserDetails();
	 for(Object[] params : list)
	 {
		 System.out.println(params[0]);
		 System.out.println(params[1].toString());
	 }
}*/

/*public void testgetFreeUserProblemsInSearch()
{
	ProblemSearchVO problemSearchVO = new ProblemSearchVO();
	problemSearchVO.setScopeAll(true);
	problemSearchVO.setTypeAll(true);
	problemSearchVO.setStatusAll(true);
	problemSearchVO.setUsersAll(true);
	problemSearchVO.setUserId(2l);
	problemSearchVO.setScopeId(3l);
	problemSearchVO.setTypeId(1l);
	problemSearchVO.setLocationValue(223l);
	List<Object[]> list = userProblemDAO.getFreeUserProblemsInSearch(problemSearchVO, 0, 3, false);
			System.out.println(list.size());
			for(Object[] params : list)
			{
				System.out.println(params[0]);
				System.out.println(params[1].toString());
			}
		
}*/
	
	/*public void testgetFreeUserIdOfAProblem()
	{
		Long value = userProblemDAO.getFreeUserIdOfAProblem(1l);
		System.out.println(value);
	}*/
	
	/*public void testcheckUserFileUploadRight(){
		Long userId = 7l;
		Long problemHistoryId = 20l;
		List<Object[]> list = userProblemDAO.checkUserFileUploadRight(userId, problemHistoryId);
		System.out.println(list.size());
		
		for(Object obj:list)
		{
			System.out.println(obj.toString());
		}
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

	/*public void testGetStates()
	{
		List<Object[]> list = userProblemDAO.getStates();
		System.out.println(list.size());
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
			{
				System.out.println(params[0]);
				System.out.println(params[1]);
			}
		}
		
	}*/

}
