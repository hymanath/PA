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
}
