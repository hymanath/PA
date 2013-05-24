package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICustomVoterDAO;

public class CustomVoterHibernateTest  extends BaseDaoTestCase{
	
	private ICustomVoterDAO customVoterDAO;

    public void setCustomVoterGroupDAO(ICustomVoterDAO customVoterDAO) {
	 this.customVoterDAO = customVoterDAO;
   }

}
