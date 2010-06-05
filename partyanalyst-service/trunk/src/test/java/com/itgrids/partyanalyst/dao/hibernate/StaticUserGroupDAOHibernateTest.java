package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IStaticUserGroupDAO;
import com.itgrids.partyanalyst.model.StaticUserGroup;

public class StaticUserGroupDAOHibernateTest extends BaseDaoTestCase  {

	private IStaticUserGroupDAO staticUserGroupDAO;

	public IStaticUserGroupDAO getStaticUserGroupDAO() {
		return staticUserGroupDAO;
	}

	public void setStaticUserGroupDAO(IStaticUserGroupDAO staticUserGroupDAO) {
		this.staticUserGroupDAO = staticUserGroupDAO;
	}
	/*
	public void testGetAll(){
		List<StaticUserGroup> list = staticUserGroupDAO.getAll();
		System.out.println(list.size());
	}*/
	@SuppressWarnings("unchecked")
	/*public void testGetGroupMembersCountForAGroup()
	{
		List list = staticUserGroupDAO.getGroupMembersCountForAGroup(7L);
		System.out.println(list.size());
		System.out.println(Long.parseLong(list.get(0).toString()));
		
	}*/
	
	public void testGetGroupMembersByName()
	{
		List list = staticUserGroupDAO.getGroupMembersByName(10L, "Siva Reddivari");
		System.out.println("List Size"+list.size());
		System.out.println("Count"+Long.parseLong(list.get(0).toString()));
		
	}
}
