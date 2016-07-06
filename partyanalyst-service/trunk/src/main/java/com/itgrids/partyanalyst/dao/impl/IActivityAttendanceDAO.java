package com.itgrids.partyanalyst.dao.impl;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityAttendance;
import com.itgrids.partyanalyst.model.ActivityLocationInfoDates;

public interface IActivityAttendanceDAO extends
		GenericDao<ActivityAttendance, Long> {
	
	public List<Object[]> getActivityScopeAndLevels(Long tdpCadreId,Long activityLevelId);
	public List<Object[]> getCadreAttendanceDetls(Long tdpCadreId) ;
	public List<Object[]> getCanditeActivtyAttendanceLocationsDtls(Long tdpCadreId,Long activityLevelId,Long activityScopeId);
}
