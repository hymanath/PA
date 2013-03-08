package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterStatusDAO;
import com.itgrids.partyanalyst.utils.IConstants;

public class VoterStatusDAOHibernateTest extends BaseDaoTestCase{

	private IVoterStatusDAO voterStatusDAO;

	public void setVoterStatusDAO(IVoterStatusDAO voterStatusDAO) {
		this.voterStatusDAO = voterStatusDAO;
	}
	
	/*public void test()
	{
		voterStatusDAO.getAll();
	}*/
	
	public void testGetVoterStatusIdByStatus()
	{
		System.out.println(voterStatusDAO.getVoterStatusIdByStatus(IConstants.STATUS_ADDED));
	}
}
