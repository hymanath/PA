package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserProblemApprovalDAO;

public class UserProblemApprovalDAOHibernateTest extends BaseDaoTestCase {
	IUserProblemApprovalDAO userProblemApprovalDAO;

	public IUserProblemApprovalDAO getUserProblemApprovalDAO() {
		return userProblemApprovalDAO;
	}

	public void setUserProblemApprovalDAO(
			IUserProblemApprovalDAO userProblemApprovalDAO) {
		this.userProblemApprovalDAO = userProblemApprovalDAO;
	}
	
	/*public void testFindProblemApprovalsByUser()
	{
		List result = userProblemApprovalDAO.findProblemApprovalsByUser(2l,59l );
		System.out.println(result.size());
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			System.out.print(parms[0].toString());
			System.out.println(parms[1].toString());
			System.out.println(parms[2].toString());
			
		}
		
	}*/
	/*
	public void testFindApprovalInfoForProblem()
	{
		List result = userProblemApprovalDAO.findApprovalInfoForProblem(67l);
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			System.out.print(parms[0].toString());
			System.out.println(parms[1].toString());
			System.out.println(parms[2].toString());
			System.out.println(parms[3].toString());
			System.out.println(parms[4].toString());
			
		}
		
	}*/
	

	public void testFindCountOfPosts()
	{
		List result = userProblemApprovalDAO.findCountOfPosts(65l);
		System.out.println(result.size());
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			System.out.println(parms[0].toString());
			System.out.println(parms[1].toString());
			System.out.println(parms[2].toString());
						
		}		
	}

}
