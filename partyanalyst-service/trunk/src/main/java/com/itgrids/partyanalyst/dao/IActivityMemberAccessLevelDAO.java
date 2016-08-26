package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityMemberAccessLevel;

public interface IActivityMemberAccessLevelDAO extends GenericDao<ActivityMemberAccessLevel,Long>{
	public List<Object[]> getActivityMemberUserAccessLevelAndValues(Long userId);
	public List<Object[]> getLocationsByActivityMemberId(Long activityMemberId);
}
