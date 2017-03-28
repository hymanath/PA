package com.itgrids.partyanalyst.dao;

import java.util.Date;
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

	
	public List<Object[]> getExistedSurveyUsersByUserType(Long userTypeId);
	
	public List<Object[]> getSurveyUsersByLeaderWise(Long leaderId);
	
	public List<Object[]> getAllUserForAssignedUsers(List<Long> leaderId);
	public List<Object[]> getAssignedUsersOfAConstituency(Long constituencyId);
	public List<Long> getAssignedUsersForLeader(Long leaderId);
	public List<Object[]> getUserLeaderIds(List<Long> surveyUserIds);
	public List<Object[]> getusersBysurveyUserIds(List<Long> surveyUserIds,Long userTypeId);

	public List<Object[]> getLeadersBysurveyUserIds(List<Long> surveyUserIds,Long surveyUsertypeId);
	public List<Long> getUsersForLeader(Long leaderId);

	public List<Object[]> getLeadersByForUsers(List<Long> userIds);
	public List<Object[]> getAllUserForLeader(List<Long> leaderId);
	public List<Object[]> getAllUsersCountForLeaders(List<Long> leaderIds);
	public List<Object[]> getSurveyConstituencyLeadersList(Long surveyUsertypeId,List<Long> constituencyIds);

	public List<Object[]> getSurveyUserAndLeaderInfo(Long surveyUserId);
}
