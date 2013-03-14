package com.itgrids.partyanalyst.dao.hibernate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		List<UserProblem> userProblem = userProblemDAO.getUserProblemId(5l,1l);
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
	List<Long> connecteduserIds = new ArrayList<Long>(0);
	connecteduserIds.add(611l);
	List list1 = userProblemDAO.getAllPostedProblemsByAnanymousUserId(2L, 0, 5, "desc", "model.problem.problemId", IConstants.TOTAL,connecteduserIds);
	System.out.println(list1.size());
}
	*/
	/*public void testgetAllPostedPRoblemCount()
	{
		 List list = userProblemDAO.getAllPostedProblemCount(2l);
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
			
			and model.problem.problemCompleteLocation.state.stateId = "+locationId;
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
	
	/*public void testGetUserProblemIdByUserIdAndProblemId(){
		List<Long> userPrbIdList=userProblemDAO.getUserProblemIdByUserIdAndProblemId(25L, 1L);
		if(userPrbIdList!=null && userPrbIdList.size()>0){
			System.out.println(userPrbIdList.get(0)); 
		}
		else{
			System.out.println("not available"); 
		}
	}*/
	
	/*public void testgetProblemDetailsOfUserToSendEmailAfterProblemApproval()
	{
		//List<Long> problemIds =  new ArrayList<Long>(0);
		//problemIds.add(1l);
		List<UserProblem> params = userProblemDAO.getProblemDetailsOfUserToSendEmailAfterProblemApproval(1l);
		for(UserProblem problem :params)
		{
			System.out.println(problem.getUser().getEmail().toString());
		}
	}*/

	/*public void testfindProblemsByDateAndLocation() throws Exception
	{
		 DateFormat format = 
				 new SimpleDateFormat("yyyy-MM-dd");
			 Date startDate = 
				 format.parse("2012-07-05");
			 Date endDate = 
				format.parse("2012-07-09");
		List list = userProblemDAO.findProblemsByDateAndLocation(2l,startDate,endDate);
		for(int i=0;i<list.size();i++)
		{
			Object[] params = (Object[])list.get(i);
			System.out.println(params[0]);
			System.out.println(params[1]);
		}
	}*/
	
	/*public void testfindProblemsByStatusDateAndLocation() throws Exception{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = format.parse("2012-07-05");
		Date endDate = format.parse("2012-07-09");
		List list = userProblemDAO.findProblemsByStatusDateAndLocation(2l,1l,startDate,endDate);
		for(int i=0;i<list.size();i++)
		{
			Object[] params = (Object[])list.get(i);
			System.out.println(params[0]);
			System.out.println(params[1]);
		}
	}*/
	
	/*public void testfindProblemDetailsByProblemId() 
	{
		List<UserProblem> userProblem = userProblemDAO.findProblemDetailsByProblemId(22l);
		for(UserProblem problemDetails : userProblem)
		{
			System.out.println(problemDetails.getProblem().getDescription());
		}
	}*/
//	public void testgetCadreProblemsInARegionByUserProblemId()
//	{
//		List<UserProblem> list = userProblemDAO.getCadreProblemsInARegionByUserProblemId(1l,30l);
//		for(UserProblem problem : list)
//		{
//			System.out.println(problem.getProblem().getDescription());
//		}
//	}
//	
//	public void testGetTotalProblemsCountForAnUserInARegion()
//	{
//		List<Object> result = userProblemDAO.getTotalProblemsCountForAnUserInARegion(1l, "and model.problem.problemCompleteLocation.state.stateId = "+1l+"");
//		System.out.println(result.size());
//	}
//	
	/*public void testGetUserProblemIdForCadreProblems()
	{
		List<UserProblem> result = userProblemDAO.getUserProblemIdForCadreProblems(8l, 1219l);
		System.out.println(result.get(0).getUser().getFirstName());
	}
	*/
	/*public void testGetProblemDetailsOfUserToSendEmailAfterProblemApproval()
	{
		List<UserProblem> userProblemDet = userProblemDAO.getProblemDetailsOfUserToSendEmailAfterProblemApproval(7l);
		if(userProblemDet != null && userProblemDet.size() > 0)
		{
			for(UserProblem userDetails : userProblemDet)
			{
				System.out.println(userDetails.getUser().getFirstName());
			}
		}
	}
	public void testgetStatusWiseProblemsForAnUserInARegion()
	{
		String locationStr=" and model.problem.problemCompleteLocation.state.stateId = 1";
		String statusStr = " and model.problem.problemStatus.status = 'PROGRESS' ";
		List<UserProblem> list = userProblemDAO.getStatusWiseProblemsForAnUserInARegion(1l,locationStr,statusStr);
		System.out.println(list.size());
	}
	
	public void testGetProblemDetailsByProblemReferenceNo()
	{
		List<Object[]> result = userProblemDAO.getProblemDetailsByProblemReferenceNo("FU01296");
		if(result != null && result.size() > 0)
		{
			for(Object[] params : result)
			{
				System.out.println(params[0]);
				System.out.println(params[1]);
				System.out.println(params[2]);
				System.out.println(params[3]);
			}
		}
	}*/
	/*public void testMakeProblemPublic()
	{
		int result=userProblemDAO.makeProblemPublic(182l,1l);
		System.out.println(result);
	}*/
	/*public void testCheckIsPublicProblem(){
		List<Long> probCountList = userProblemDAO.checkIsPublicProblem(1L);
		System.out.println(probCountList.get(0));
	}*/
	/*public void testCheckIsTakenUpProblem(){
		List<Long> probCountList = userProblemDAO.checkIsTakenUpProblem(1L,1L);
		System.out.println(probCountList.get(0));
	}*/
	
	/*public void testGetAllProblemPostedUserDetails(){
		List<Object[]> usersList = userProblemDAO.getAllProblemPostedUserDetails(null);
		System.out.println(usersList.size());
	}*/
	/*public void testGetUserProblemByUserAndProblemId(){
		List<UserProblem> userProblemList = userProblemDAO.getUserProblemByUserAndProblemId(1L,2L);
		System.out.println(userProblemList.size());
		if(userProblemList.size() > 0)
		System.out.println(userProblemList.get(0).getVisibility().getType());
	}*/	
	/*public void testGetAllProblemContainStates(){
		List<Object[]> statesList = userProblemDAO.getAllProblemContainStates(1l);
		System.out.println(statesList.size());
		for(Object[] state :statesList)
		System.out.println(state[0].toString()+","+state[1].toString()+","+state[2].toString());
	}*/
	/*public void testGetCadrePostedProblems(){
		List<Long> problenIdsList = userProblemDAO.getCadrePostedProblems(null,true);
		System.out.println(problenIdsList.size());
		
	}*/
	/*public void testGetAllProblemCount(){
		List<Long> countList = userProblemDAO.getAllProblemCount(1l,1l," and model.problem.problemCompleteLocation.district.districtId != null ");
		
		for(Long count :countList)
		System.out.println(count);
	}*/
	
	/*public void testgetConnectedUsersProblemCount()
	{
		List<Long> connectedUserIds = new ArrayList<Long>(0);
		connectedUserIds.add(611l);
		connectedUserIds.add(1l);
		 List list = userProblemDAO.getConnectedUsersProblemCount(connectedUserIds);
		 System.out.println(list.size());
		 if(list != null && list.size() > 0)
		 {
			 for(int i=0;i<list.size();i++)
			 {
			 Object[] params = (Object[])list.get(i); 
			 for(Object o : params)
			 System.out.println("\t"+o.toString());
			 }
		 }
	}*/
	/*
*/
	
	/*public void testgetAllPublicProblemsByLocation()
	{
		//List<Long> problemIds = userProblemDAO.getAllPublicProblemsByLocation(1l,4l,232l,"PROGRESS");
		 //System.out.println(problemIds);
	}*/
	
	
	public void testgetProblemDetailsByLocationValuesList()
	{
		List<Long> locationValuesList = new ArrayList<Long>(0);
		locationValuesList.add(83l);
		
		List list = userProblemDAO.getProblemDetailsByLocationValuesList(locationValuesList, "MUNICIPAL-CORP-GMC", IConstants.PUBLIC);
		
		 System.out.println(list.size());
		
	}
}
