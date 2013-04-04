package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterReportLevelDAO;
import com.itgrids.partyanalyst.utils.IConstants;

 public class VoterReportLevelDAOHibernateTest extends BaseDaoTestCase{

	private IVoterReportLevelDAO voterReportLevelDAO;

	public void setVoterReportLevelDAO(IVoterReportLevelDAO voterReportLevelDAO) {
		this.voterReportLevelDAO = voterReportLevelDAO;
	}
	
	/*public void test()
	{
		voterReportLevelDAO.getAll();
	}*/
	
	
	/*public void testGetReportLevelIdByType()
	{
		Long id = voterReportLevelDAO.getReportLevelIdByType(IConstants.CONSTITUENCY);
		System.out.println(id);
	}*/
	
	
	public void testgetReportLevelTypeById()
	{
		System.out.println(voterReportLevelDAO.getReportLevelTypeById(1l));
	}
	
}
