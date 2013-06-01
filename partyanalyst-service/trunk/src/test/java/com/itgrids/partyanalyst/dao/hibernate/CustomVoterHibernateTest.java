package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICustomVoterDAO;

public class CustomVoterHibernateTest  extends BaseDaoTestCase{
	
	private ICustomVoterDAO customVoterDAO;

    
    public void setCustomVoterDAO(ICustomVoterDAO customVoterDAO) {
		this.customVoterDAO = customVoterDAO;
	}

	public void testGetVotersInfoBycustomVoterGroupId()
    {
    	//List<Object[]> list = customVoterDAO.getVotersInfoBycustomVoterGroupId(1l,1l);
    	//System.out.println(list.size());
    }

}
