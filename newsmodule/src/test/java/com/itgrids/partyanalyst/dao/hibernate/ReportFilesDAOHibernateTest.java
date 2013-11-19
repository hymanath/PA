package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IReportFilesDAO;

public class ReportFilesDAOHibernateTest  extends BaseDaoTestCase {
	private IReportFilesDAO reportFilesDAO;

	public void setReportFilesDAO(IReportFilesDAO reportFilesDAO) {
		this.reportFilesDAO = reportFilesDAO;
	}
	
	public void testGetData(){
		reportFilesDAO.getOtherLvlReportDetails(1l,1l);
	}
}
