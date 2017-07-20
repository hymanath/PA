package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Activity;

public interface IActivityDAO extends GenericDao<Activity, Long>{
	
	public List<Object[]> getAllActivities();
	public List<Object[]> getActivitysTotal(Date fromDate, Date toDate, String year, List<Long> locationValues,Long locationTypeId);
	public List<Object[]> getActivitysConductedCount(Date fromDate, Date toDate, String year, List<Long> locationValues,Long locationTypeId);
	public List<Object[]> getConductedInfoTotal(Date fromDate, Date toDate, String year, List<Long> locationValues,Long locationTypeId);
	public List<Object[]> getConductedInfoCount(Date fromDate, Date toDate, String year, List<Long> locationValues,Long locationTypeId);

}
