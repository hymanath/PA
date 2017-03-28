package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityTeamMember;

public interface IActivityTeamMemberDAO extends GenericDao<ActivityTeamMember, Long> {
	
	public List<Object[]> getTeamLeadersByActivityScope(List<Long> activityScopeIds);
	public List<Object[]> getTeamMembersByTeamLeaderAndActivityScope(List<Long> teamLeaderIds,List<Long> activityScopeIds);
	public List<Object[]> getTeamMembersByTeamLeaderId(Long leaderId);

}
