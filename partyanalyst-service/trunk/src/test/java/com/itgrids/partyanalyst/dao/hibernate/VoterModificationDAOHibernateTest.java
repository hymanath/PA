package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IVoterModificationDAO;

public class VoterModificationDAOHibernateTest extends BaseDaoTestCase{

	private IVoterModificationDAO voterModificationDAO;

	public void setVoterModificationDAO(IVoterModificationDAO voterModificationDAO) {
		this.voterModificationDAO = voterModificationDAO;
	}
	
	public void test()
	{
		voterModificationDAO.getAll();
	}
}
