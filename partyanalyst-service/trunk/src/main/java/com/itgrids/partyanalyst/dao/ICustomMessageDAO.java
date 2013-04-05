package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CustomMessage;

public interface ICustomMessageDAO extends GenericDao<CustomMessage, Long> {

	public List<Object> getRelationShipBetweenTheUsers(List<Long> userIds,Long logedUserId,String status);
	
	public List<CustomMessage> checkForRelationBetweenUsers(List<Long> senderId,List<Long> recipeintId);

	public List<CustomMessage> checkForRelationBetweenUsersBasedOnType(List<Long> senderId,List<Long> recipeintId,String type);
	
	public List<Object> getAllMessagesForUser(List<Long> senderId,String messageType);
	
	public List<Object> getAllSentMessagesForUser(List<Long> senderId,String messageType);
	
	public int updateRelationBetweenUsers(List<Long> senderId,List<Long> recipeintId,Long messageTypeId,Date currentDate);
	
	public Long getPendingUsersCountForAUserInAFilterView(Long userId,List<Long> locationIds,String locationType,String nameStr);
	
	public List<Object> getPendingUsersInSelectedLocations(Long userId, List<Long> locationIds,String locationType,Long retrivalCount,Long startIndex,String nameString);
	
	public List<Long> getPendingUserIdsInSelectedLocations(Long userId, List<Long> locationIds,String locationType);
	
	public List<Object[]> getUserStatus(Long profileId, Long userId);
	
	public List<Object[]> getUserConnectStatus(Long profileId, Long userId);
	
	public Integer updateIsRecePient(Long userId,Long senderId,Long typeId,Long customMessageId);
	
	public Integer updateIsSender(Long userId,Long senderId,Long typeId,Long customMessageId);

	public List<Object> getAllMessagesForUser(List<Long> senderId,String messageType,Integer startIndex, Integer maxResults);
}
