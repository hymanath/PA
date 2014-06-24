package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHHLeaderDAO;

public class HHLeaderDAOHibernateTest extends BaseDaoTestCase{
	
	private IHHLeaderDAO hhLeaderDAO;

	
	public void setHhLeaderDAO(IHHLeaderDAO hhLeaderDAO) {
		this.hhLeaderDAO = hhLeaderDAO;
	}

	public void test(){
		//System.out.println("hhOptionsDAO");
		List<Object[]> list = hhLeaderDAO.getAllLeadersOfConstituency(228l);
		System.out.println(list.size());
	}

}
