package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Activity;

public interface IActivityDAO extends GenericDao<Activity, Long>{
	
	public List<Object[]> getAllActivities();

}
