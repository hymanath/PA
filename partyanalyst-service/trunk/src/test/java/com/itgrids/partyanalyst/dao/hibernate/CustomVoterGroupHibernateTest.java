package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICustomVoterGroupDAO;

public class CustomVoterGroupHibernateTest extends BaseDaoTestCase{
	
private ICustomVoterGroupDAO customVoterGroupDAO;

public void setCustomVoterGroupDAO(ICustomVoterGroupDAO customVoterGroupDAO) {
	this.customVoterGroupDAO = customVoterGroupDAO;
}


		public void test()
	    {
	    	List<Object[]> list = customVoterGroupDAO.checkDuplicateGroupName(1l,844l,"j");
	    	System.out.println(list.size());
	    }
}
