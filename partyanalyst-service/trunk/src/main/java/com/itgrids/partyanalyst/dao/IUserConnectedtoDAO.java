package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserConnectedto;

public interface IUserConnectedtoDAO extends GenericDao<UserConnectedto, Long> {

	public Integer deleteRejectedRequest(List<Long> senderId,List<Long> recipeintId);
	
	public List<UserConnectedto> checkForRelationBetweenUsers(List<Long> senderId,List<Long> recipeintId);
	
	public List<Object> getAllConnectedPeopleForUser(List<Long> senderId);
	
	public List<Object> getAllPeopleThatMayBeKnownForUser(Long userId);
	
	public String getCountOfAllConnectedPeopleForUser(List<Long> senderId);
	
	public List<Object[]> getAllConnectedPeopleForFreeUser(Long senderId);
	
	public Long getConnectedMembersCountForAFreeUser(Long userId);
	
	public List<Object[]> getAllConnectedPeoplesForFreeUser(Long recepientId);
	
	public Long getConnectedUsersCountForAUserInAFilterView(Long userId,List<Long> locationIds,String locationType,String nameStr);

	public List<Object> getConnectedUsersInSelectedLocations(Long userId, List<Long> locationIds,String locationType,Long retrivalCount,Long startIndex,String nameString);
	
	public List<Long> getConnectedUserIdsInSelectedLocations(Long userId, List<Long> locationIds,String locationType);
	
	public List<Object[]> getAllConnectedPeoplesForPublicProfile(Long recepientId);
	
	public List<Object[]> getAllConnectedPeopleForPublicProfile(Long userId);
	
	public List<Long> getUserConnectStatus(Long senderId,Long recipeintId);

	public List<Object[]> getCountOfAllConnectedPeopleForUserByDistrict(List<Long> senderId, List<Long> locationIds, String locationType,String constituencyType);
	
	public List<Object[]> getCountOfAllConnectedPeopleForUserInSameLocation(List<Long> senderId,List<Long> locationIds,String constituencyType);
	
}
