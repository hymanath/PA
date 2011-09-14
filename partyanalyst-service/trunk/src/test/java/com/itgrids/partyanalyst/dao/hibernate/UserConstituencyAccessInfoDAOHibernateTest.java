package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserConstituencyAccessInfoDAO;

public class UserConstituencyAccessInfoDAOHibernateTest extends BaseDaoTestCase{

	private IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO;

	public IUserConstituencyAccessInfoDAO getUserConstituencyAccessInfoDAO() {
		return userConstituencyAccessInfoDAO;
	}

	public void setUserConstituencyAccessInfoDAO(
			IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO) {
		this.userConstituencyAccessInfoDAO = userConstituencyAccessInfoDAO;
	}
/*	
	public void testGetAll(){
		System.out.println(userConstituencyAccessInfoDAO.getAll().size());
	}*/
	
	/*public void testFindByUser(){
		List<Object[]> list = userConstituencyAccessInfoDAO.findByUser(7l);
		assertEquals(list.size() >= 0, true);
		for(Object[] o : list){
			System.out.println(o[2]);
			
		}
		
	}*/
	public void testFindByElectionTypeUserState(){
		 
	 
		List<Object[]> result =  userConstituencyAccessInfoDAO.findByElectionTypeUserState(2L,7L,1L);
		System.out.println(result.size());
	 }
	/*
	public void testFindByElectionScopeUserState(){
		List list = userConstituencyAccessInfoDAO.findByElectionScopeUserState(1L, 308L, 1L);
		System.out.println(list.size());
	}
	
	public void testFindByElectionScopeUser(){
		List list = userConstituencyAccessInfoDAO.findByElectionScopeUser(2L, 7L);
		System.out.println(list.size());
	}*/
}
