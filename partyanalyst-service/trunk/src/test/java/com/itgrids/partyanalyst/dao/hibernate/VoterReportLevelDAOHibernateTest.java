package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterReportLevelDAO;

 public class VoterReportLevelDAOHibernateTest extends BaseDaoTestCase{

	private IVoterReportLevelDAO voterReportLevelDAO;

	public void setVoterReportLevelDAO(IVoterReportLevelDAO voterReportLevelDAO) {
		this.voterReportLevelDAO = voterReportLevelDAO;
	}
	
	public void test()
	{
		voterReportLevelDAO.getAll();
	}
}
