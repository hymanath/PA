package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IActivityReportDAO;

public class ActivityReportDAOHibernateTest  extends BaseDaoTestCase{
	IActivityReportDAO activityReportDAO;

	public void setActivityReportDAO(IActivityReportDAO activityReportDAO) {
		this.activityReportDAO = activityReportDAO;
	}
	
	public void testGetAll(){
		activityReportDAO.getAll();
	}
}
