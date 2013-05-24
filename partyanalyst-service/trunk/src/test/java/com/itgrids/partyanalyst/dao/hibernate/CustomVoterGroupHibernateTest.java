package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICustomVoterGroupDAO;

public class CustomVoterGroupHibernateTest extends BaseDaoTestCase{
	
	private ICustomVoterGroupDAO customVoterGroup;

	    public void setCustomVoterGroupDAO(ICustomVoterGroupDAO abusedCommentsDAO) {
		 this.customVoterGroup = abusedCommentsDAO;
	   }
}
