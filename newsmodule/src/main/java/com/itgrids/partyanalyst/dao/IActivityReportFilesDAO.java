package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityReportFiles;

public interface IActivityReportFilesDAO  extends GenericDao<ActivityReportFiles, Long>{
	public List<Object[]> getActivitiesList(String key);
}
