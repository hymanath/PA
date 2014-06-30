package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityReport;

public interface IActivityReportDAO extends GenericDao<ActivityReport, Long>{

	public String getCategoeryIds(String key);
	 public Object[] getCategoeryAndPartyIds(String key);
}
