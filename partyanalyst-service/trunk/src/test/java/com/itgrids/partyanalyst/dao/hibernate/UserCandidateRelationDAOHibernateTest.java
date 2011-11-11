package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserCandidateRelationDAO;

public class UserCandidateRelationDAOHibernateTest extends BaseDaoTestCase{
	
	private IUserCandidateRelationDAO userCandidateRelationDAO;

	public void setUserCandidateRelationDAO(
			IUserCandidateRelationDAO userCandidateRelationDAO) {
		this.userCandidateRelationDAO = userCandidateRelationDAO;
	}
	
	/*public void test()
	{
		userCandidateRelationDAO.getAll();
	}*/
	/*public void testDeleteUserCandidateRelation()
	{
		userCandidateRelationDAO.deleteUserCandidateRelation(2L);
	}*/
	/*public void testDeleteUserCandidateRelation()
	{
		List<Object[]> result = userCandidateRelationDAO.getUserCandidateRelationDetails(1L);
		System.out.println(result.size());
	}*/
	/*public void testGetUserCandidateRelationCount()
	{
		List<Long> result = userCandidateRelationDAO.getUserCandidateRelationCount(2L,2757L);
		System.out.println(result.size());
		System.out.println(result.get(0));
	}*/
	
	public void testGetCandidatesOfAUser()
	{
		List<Object[]> list = userCandidateRelationDAO.getCandidatesOfAUser(1l);
		System.out.println(list.size());
	}
}
