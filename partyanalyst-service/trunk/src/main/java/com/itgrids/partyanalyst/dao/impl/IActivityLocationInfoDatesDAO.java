package com.itgrids.partyanalyst.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityDateType;
import com.itgrids.partyanalyst.model.ActivityLocationInfoDates;

public interface IActivityLocationInfoDatesDAO extends
		GenericDao<ActivityLocationInfoDates, Long> {
	public List<Object[]> getActivityDatesByScope(Long activityScopeId,Date startDate,Date endDate);
	public List<ActivityLocationInfoDates> getActivityLocationInfoDates(Long activityLocationInfoId,Long day);

}
