package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyUserRelation;

public interface ISurveyUserRelationDAO  extends GenericDao<SurveyUserRelation, Long>
{

	public List<Object[]> getLeadersByConstituency();
	
	public List<Object[]> getUsersByConstituencyAndLeader(Long leaderId,Long constituencyId);
	
	public List<Object[]> getUsersList(Long usertypeId);
	
	public List<Object[]> getUserForAssignedUser(Long leaderId,Long userType);
	
	public int updateUserLeaderRelations(Long userTypeId,List<Long> surveyUserIds,Long leaderId);
	public List<Object[]> getConstituencyForSurveyUser(List<Long> surveyUserIds);
	public List<Object[]> getUsersForAssignedUser(Long leaderId,Long userType);
	public List<Object[]> getUserForAssignedUsers(Long leaderId);
	public int updateActiveStatusByIDs(List<Long> Ids);
}
