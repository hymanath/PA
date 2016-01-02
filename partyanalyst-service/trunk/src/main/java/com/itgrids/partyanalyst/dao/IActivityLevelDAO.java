package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityLevel;

public interface IActivityLevelDAO extends GenericDao<ActivityLevel, Long>{

	public List<Object[]> actvityLvlOrder();
}
