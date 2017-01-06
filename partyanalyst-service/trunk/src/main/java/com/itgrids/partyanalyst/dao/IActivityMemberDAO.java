package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityMember;

public interface IActivityMemberDAO extends GenericDao<ActivityMember,Long>{
	
	public List<Object[]> getLoggedInUserBasicDetails(Long userId);
	public List<Object[]> getActivityMemberDetails(Long activityMemberId);
	public Long findActivityMemberIdByUserId(Long userId);
	public List<Long> checkForLeader(Long caderId);
}
