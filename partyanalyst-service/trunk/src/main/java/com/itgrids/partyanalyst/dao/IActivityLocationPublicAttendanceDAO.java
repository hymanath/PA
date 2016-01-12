package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityLocationPublicAttendance;

public interface IActivityLocationPublicAttendanceDAO extends GenericDao<ActivityLocationPublicAttendance, Long>{
	public ActivityLocationPublicAttendance checkWhetherExistingOrNew(String uniqueKey);
}
