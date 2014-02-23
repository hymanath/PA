package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterTagDAO;
import com.itgrids.partyanalyst.model.VoterTag;

public class VoterTagDAOHibernateTest extends BaseDaoTestCase{
	
	private IVoterTagDAO voterTagDAO;

	public void setVoterTagDAO(IVoterTagDAO voterTagDAO) {
		this.voterTagDAO = voterTagDAO;
	}
	
	/*public void test()
	{
		voterTagDAO.getAll();
	}*/
	
	public void testGetVoterTagByVoterIdAndUniqueCode()
	{
		VoterTag voterTag = voterTagDAO.getVoterTagByVoterIdAndUniqueCode(123556l,"TDP-221");
		System.out.println(voterTag.getVoterTagId());
	}
}
