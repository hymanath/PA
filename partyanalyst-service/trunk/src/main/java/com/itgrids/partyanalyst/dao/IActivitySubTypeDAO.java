package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivitySubType;

public interface IActivitySubTypeDAO extends GenericDao<ActivitySubType, Long>{

	public List<Object[]> activityTypeNames();
}
