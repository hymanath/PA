package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityMemberAccessLevel;

public interface IActivityMemberAccessLevelDAO extends GenericDao<ActivityMemberAccessLevel,Long>{
	public List<Object[]> getActivityMemberUserAccessLevelAndValues(Long userId);
	public List<Object[]> getLocationsByActivityMemberId(Long activityMemberId);
	public List<Object[]> getLocationLevelAndValuesByActivityMembersId(Long activityMemberId);
	public List<Object[]> getMemberIdMemberLvlAndLocationValueByTdpCadre(Long tdpCadreId);
	
	public List<Object[]> getLocationLevelAndValuesByActivityMembersIdForOrganization(Long activityMemberId);
	public List<Object[]> getMemberDetailsByActivityMemberId(Long activityMemberId);
}
