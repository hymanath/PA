package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityTeamLocation;

public interface IActivityTeamLocationDAO extends GenericDao<ActivityTeamLocation, Long> {
	
	public List<Object[]> getActivityLocationsForTeamLeders(List<Long> teamLeaderIds);
	public List<Long> getAssignedLocationsForTeamMembers(Long teamMemberId);
	public List<Object[]> getTeamLeaderDetailsAndAssignedLocationsByLeaderId(Long leaderId);
	public List<Long> getAssignedConstituenciesForTeamMembers(Long memberId);
	public List<Long> getAssignedLocationsForTeamMembersByTeamLeaderId(Long teamLeaderId);

}
