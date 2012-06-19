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
		query.append(" select model.recepient.registrationId,model.messageType.messageType ");
		query.append(" from CustomMessage model ");
		query.append(" where model.sender.registrationId = ? and");		
		if(!status.equalsIgnoreCase(IConstants.ALL)){
			query.append(" model.messageType.messageType = ? and");
		}		
		query.append(" model.recepient.registrationId in (:userIds)");	
		
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
		query.append(" (model.sender.registrationId in (:senderId) and model.recepient.registrationId in (:recipeintId) )");
		query.append(" or ( model.recepient.registrationId in (:senderId)  and model.sender.registrationId in (:recipeintId) ) ");
				
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
		query.append(" (model.sender.registrationId in (:senderId) and model.recepient.registrationId in (:recipeintId) )");
		query.append(" or (model.sender.registrationId in (:recipeintId) and model.recepient.registrationId in (:senderId) ) ");	
	
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setString(0,type);
		queryObject.setParameterList("senderId", senderId);
		queryObject.setParameterList("recipeintId", recipeintId);
		queryObject.setParameterList("recipeintId", recipeintId);
		queryObject.setParameterList("senderId", senderId);
		
		return queryObject.list();
	}
	
	/*@SuppressWarnings("unchecked")
	public List<Object> getAllMessagesForUser(List<Long> senderId,String messageType){
		StringBuilder query = new StringBuilder();				
		query.append(" select model.subject, model.sender.registrationId, model.sender.firstName, model.sender.lastName, ");
		query.append(" model.sender.state.stateName, model.sender.district.districtName, model.sender.constituency.name, " +
				"model.customMessageId, model.status, model.sentDate, model.recepient.registrationId from CustomMessage");//7,8,9
		query.append(" model where model.messageType.messageType = ? and model.recepient.registrationId in (:senderId)");
		query.append(" and model.recepient.registrationId in (select model1.user.registrationId from UserRoles model1 where model1.role.roleType = :role ) ");
		query.append(" and model.sender.registrationId in (select model2.user.registrationId from UserRoles model2 where model2.role.roleType = :role ) ");
		query.append(" order by model.customMessageId desc ");
		Query queryObject = getSession().createQuery(query.toString());	
		queryObject.setString(0,messageType);
		queryObject.setParameterList("senderId", senderId);
		queryObject.setParameter("role", IConstants.FREE_USER);
		
		return queryObject.list();
	}*/
	
	@SuppressWarnings("unchecked")
	public int updateRelationBetweenUsers(List<Long> senderId,List<Long> recipeintId,Long messageTypeId,Date currentDate){
		StringBuilder query = new StringBuilder();				
		query.append(" update CustomMessage model set model.messageType.messageTypeId = ? where ");
		query.append(" (model.sender.registrationId in (:senderId) and model.recepient.registrationId in (:recipeintId) )");
		query.append(" or ( model.recepient.registrationId in (:senderId)  and model.sender.registrationId in (:recipeintId) ) ");
	
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
		query.append("select count(model.sender.registrationId)");
		query.append(" from CustomMessage model where model.sender.registrationId = :userId and ");
		query.append("model.messageType.messageType = :messageType  ");
		if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
			query.append(" and model.recepient.state.stateId in (:locationIds) ");
		}else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
			query.append(" and model.recepient.district.districtId in (:locationIds) ");
		}else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
			query.append(" and model.recepient.constituency.constituencyId in (:locationIds)) ");
		}
		if(nameStr != null && !nameStr.trim().equalsIgnoreCase(""))
		{
			query.append("and (model.recepient.firstName like '"+nameStr+"%' or model.recepient.lastName like '"+nameStr+"%')");
		}
		 query.append(" and model.userTarget.registrationId in (select model2.user.registrationId from UserRoles model2 where model2.role.roleType = :role ) ");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("locationIds", locationIds);
		queryObject.setParameter("userId", userId);
		queryObject.setParameter("messageType", IConstants.PENDING);
		queryObject.setParameter("role", IConstants.FREE_USER);
		
		return (Long)queryObject.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getPendingUsersInSelectedLocations(Long userId, List<Long> locationIds,String locationType,Long retrivalCount,Long startIndex,String nameString) {
		StringBuilder query = new StringBuilder();
		query.append(" select model.recepient.firstName,model.recepient.lastName,model.recepient.registrationId,model.recepient.constituency.name,model.recepient.constituency.constituencyId, model ");
		query.append(" from CustomMessage model where model.sender.registrationId = :userId and ");
		query.append(" model.messageType.messageType = :messageType and ");
		
		if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
			query.append(" model.recepient.state.stateId in (:locationIds) ");
		}else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
			query.append(" model.recepient.district.districtId in (:locationIds) ");
		}else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
			query.append(" model.recepient.constituency.constituencyId in (:locationIds)) ");
		}

		if(nameString != null && !nameString.trim().equalsIgnoreCase(""))
		{
			query.append("and (model.recepient.firstName like '"+nameString+"%' or model.recepient.lastName like '"+nameString+"%')");
		}
		
		query.append("and model.recepient.registrationId in (select model1.user.registrationId from UserRoles model1 where model1.role.roleType = :role ) order by model.recepient.registrationId ");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("locationIds", locationIds);
		queryObject.setParameter("userId", userId);
		queryObject.setParameter("messageType", IConstants.PENDING);
		queryObject.setParameter("role", IConstants.FREE_USER);
		if(startIndex != null)
			queryObject.setFirstResult(startIndex.intValue());
		if(retrivalCount != null)
			queryObject.setMaxResults(retrivalCount.intValue());	
		
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getPendingUserIdsInSelectedLocations(Long userId, List<Long> locationIds,String locationType)
	{
		StringBuilder query = new StringBuilder();
		query.append(" select model.recepient.registrationId from CustomMessage model where model.sender.registrationId = :userId and ");
		query.append(" model.messageType.messageType = :messageType  ");
		
		if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
			query.append(" and model.recepient.state.stateId in (:locationIds) ");
		}else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
			query.append(" and model.recepient.district.districtId in (:locationIds) ");
		}else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
			query.append(" and model.recepient.constituency.constituencyId in (:locationIds)) ");
		}
		query.append(" and model.recepient.registrationId in (select model1.user.registrationId from UserRoles model1 where model1.role.roleType = :role ) order by model.recepient.registrationId");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("locationIds", locationIds);
		queryObject.setParameter("userId", userId);
		queryObject.setParameter("messageType", IConstants.PENDING);
		queryObject.setParameter("role", IConstants.FREE_USER);
		
		return queryObject.list();
	}
}
