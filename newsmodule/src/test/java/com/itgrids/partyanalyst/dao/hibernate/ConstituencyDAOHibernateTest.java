package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;

public class ConstituencyDAOHibernateTest extends BaseDaoTestCase{

	IConstituencyDAO constituencyDAO;

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	
	public void testfindByStateIdForUser(){
		
	List<Object[]> constituencies = constituencyDAO.findConstituencyByDistrictElectionType(7l,"Assembly");
	
		for (Object[] object : constituencies) {
			System.out.println(object[0]);
			System.out.println(object[1]);
			}
	}
	
}
