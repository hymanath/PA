package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IActivityReportFilesDAO;

public class ActivityReportFilesDAOHibernateTest  extends BaseDaoTestCase{
	IActivityReportFilesDAO activityReportFilesDAO;

	public void setActivityReportFilesDAO(
			IActivityReportFilesDAO activityReportFilesDAO) {
		this.activityReportFilesDAO = activityReportFilesDAO;
	}
	
	public void testGetAll(){
		activityReportFilesDAO.getAll();
	}
}
