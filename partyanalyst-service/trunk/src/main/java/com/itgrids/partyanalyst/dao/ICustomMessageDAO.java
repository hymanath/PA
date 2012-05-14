package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AnanymousUser;
import com.itgrids.partyanalyst.model.CustomMessage;

public interface ICustomMessageDAO extends GenericDao<CustomMessage, Long> {

	public List<Object> getRelationShipBetweenTheUsers(List<Long> userIds,Long logedUserId,String status);
	
	public List<CustomMessage> checkForRelationBetweenUsers(List<Long> senderId,List<Long> recipeintId);

	public List<CustomMessage> checkForRelationBetweenUsersBasedOnType(List<Long> senderId,List<Long> recipeintId,String type);
	
	public List<Object> getAllMessagesForUser(List<Long> senderId,String messageType);
	
	public int updateRelationBetweenUsers(List<Long> senderId,List<Long> recipeintId,Long messageTypeId,Date currentDate);
	
	public Long getPendingUsersCountForAUserInAFilterView(Long userId,List<Long> locationIds,String locationType,String nameStr);
}
