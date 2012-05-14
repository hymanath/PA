package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICustomMessageDAO;
import com.itgrids.partyanalyst.model.CustomMessage;
import com.itgrids.partyanalyst.utils.IConstants;

public class CustomMessageDAO extends GenericDaoHibernate<CustomMessage, Long> implements
		ICustomMessageDAO {

	public CustomMessageDAO() {
		super(CustomMessage.class);
	}

	@SuppressWarnings("unchecked")
	public List<Object> getRelationShipBetweenTheUsers(List<Long> userIds,Long logedUserId,String status){
		StringBuilder query = new StringBuilder();		
		query.append(" select model.recepientId.userId,model.messageType.messageType ");
		query.append(" from CustomMessage model ");
		query.append(" where model.senderId.userId = ? and");		
		if(!status.equalsIgnoreCase(IConstants.ALL)){
			query.append(" model.messageType.messageType = ? and");
		}		
		query.append(" model.recepientId.userId in (:userIds)");	
		
		Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setLong(0,logedUserId);
		if(!status.equalsIgnoreCase(IConstants.ALL)){
			queryObject.setString(1,status);
		}
		queryObject.setParameterList("userIds", userIds);
	
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<CustomMessage> checkForRelationBetweenUsers(List<Long> senderId,List<Long> recipeintId){
		StringBuilder query = new StringBuilder();				
		query.append(" from CustomMessage model where ");
		query.append(" (model.senderId.userId in (:senderId) and model.recepientId.userId in (:recipeintId) )");
		query.append(" or ( model.recepientId.userId in (:senderId)  and model.senderId.userId in (:recipeintId) ) ");
				
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("senderId", senderId);
		queryObject.setParameterList("recipeintId", recipeintId);
		queryObject.setParameterList("senderId", senderId);
		queryObject.setParameterList("recipeintId", recipeintId);
		
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<CustomMessage> checkForRelationBetweenUsersBasedOnType(List<Long> senderId,List<Long> recipeintId,String type){
		StringBuilder query = new StringBuilder();				
		query.append("  from CustomMessage model where model.messageType.messageType = ? and");
		query.append(" (model.senderId.userId in (:senderId) and model.recepientId.userId in (:recipeintId) )");
		query.append(" or (model.senderId.userId in (:recipeintId) and model.recepientId.userId in (:senderId) ) ");	
	
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setString(0,type);
		queryObject.setParameterList("senderId", senderId);
		queryObject.setParameterList("recipeintId", recipeintId);
		queryObject.setParameterList("recipeintId", recipeintId);
		queryObject.setParameterList("senderId", senderId);
		
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getAllMessagesForUser(List<Long> senderId,String messageType){
		StringBuilder query = new StringBuilder();				
		query.append(" select model.subject, model.senderId.userId, model.senderId.name, model.senderId.lastName, ");
		query.append(" model.senderId.state.stateName, model.senderId.district.districtName, model.senderId.constituency.name, " +
				"model.customMessageId, model.status, model.sentDate, model.recepientId.userId from CustomMessage");//7,8,9
		query.append(" model where model.messageType.messageType = ? and model.recepientId.userId in (:senderId) order by customMessageId desc ");
				
		Query queryObject = getSession().createQuery(query.toString());	
		queryObject.setString(0,messageType);
		queryObject.setParameterList("senderId", senderId);
		
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public int updateRelationBetweenUsers(List<Long> senderId,List<Long> recipeintId,Long messageTypeId,Date currentDate){
		StringBuilder query = new StringBuilder();				
		query.append(" update CustomMessage model set model.messageType.messageTypeId = ? where ");
		query.append(" (model.senderId.userId in (:senderId) and model.recepientId.userId in (:recipeintId) )");
		query.append(" or ( model.recepientId.userId in (:senderId)  and model.senderId.userId in (:recipeintId) ) ");
	
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, messageTypeId);
		queryObject.setParameterList("senderId", senderId);
		queryObject.setParameterList("recipeintId", recipeintId);
		queryObject.setParameterList("senderId", senderId);
		queryObject.setParameterList("recipeintId", recipeintId);
		return queryObject.executeUpdate();
	}
	
	public Long getAllUsersCountInSelectedLocationsInFilterView(List<Long> locationIds,String locationType,String status,String name)
	{
		StringBuilder query = new StringBuilder();
		query.append("select count(model.userId)");
		query.append(" from CustomMessage model where ");
		if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
			query.append("model.state.stateId in (:locationIds)");
		}else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
			query.append("model.district.districtId in (:locationIds)");
		}else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
			query.append("model.constituency.constituencyId in (:locationIds)");
		}	
		query.append("order by model.userId desc");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("locationIds", locationIds);
		
		return (Long)queryObject.uniqueResult();
	}
	
	public Long getPendingUsersCountForAUserInAFilterView(Long userId,List<Long> locationIds,String locationType,String nameStr)
	{
		StringBuilder query = new StringBuilder();
		query.append("select count(model.customMessageId)");
		query.append(" from CustomMessage model where model.senderId.userId = :userId and ");
		query.append("model.messageType.messageType = :messageType and ");
		if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
			query.append("model.recepientId.state.stateId in (:locationIds) ");
		}else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
			query.append("model.recepientId.district.districtId in (:locationIds) ");
		}else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
			query.append("model.recepientId.constituency.constituencyId in (:locationIds)) ");
		}
		query.append("and model.state.stateId is not null and model.district.districtId is not null and model.constituency.constituencyId is not null ");
		if(nameStr != null && !nameStr.trim().equalsIgnoreCase(""))
		{
			query.append("and (model.recepientId.name like '"+nameStr+"%' or model.recepientId.lastName like '"+nameStr+"%')");
		}
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("locationIds", locationIds);
		queryObject.setParameter("userId", userId);
		queryObject.setParameter("messageType", IConstants.PENDING);
		
		return (Long)queryObject.uniqueResult();
	}	
}
