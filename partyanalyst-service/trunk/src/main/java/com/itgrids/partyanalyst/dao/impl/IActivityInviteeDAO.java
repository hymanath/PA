package com.itgrids.partyanalyst.dao.impl;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityInvitee;
import com.itgrids.partyanalyst.model.ActivityLocationInfoDates;

public interface IActivityInviteeDAO extends GenericDao<ActivityInvitee, Long> {
	public List<Object[]> getActivityScopeAndLevels(Long tdpCadreId,Long activityLevelId);
}
