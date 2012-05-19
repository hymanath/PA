package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserConnectedtoDAO;
import com.itgrids.partyanalyst.model.UserConnectedto;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserConnectedtoDAO extends GenericDaoHibernate<UserConnectedto,Long> implements
		IUserConnectedtoDAO {

	public UserConnectedtoDAO() {
		super(UserConnectedto.class);
	}

	public Integer deleteRejectedRequest(List<Long> senderId,List<Long> recipeintId){
		StringBuilder query = new StringBuilder();	
		query.append(" delete from UserConnectedto model where ");
		query.append(" (model.senderId.userId in (:senderId) and  model.recepientId.userId in (:recipeintId) ) ");
		query.append(" or (model.senderId.userId in (:recipeintId) and  model.recepientId.userId in (:senderId)) ");
		Query queryObject = getSession().createQuery(query.toString());		
		queryObject.setParameterList("senderId", senderId);
		queryObject.setParameterList("recipeintId", recipeintId);
		queryObject.setParameterList("recipeintId", recipeintId);
		queryObject.setParameterList("senderId", senderId);
		return queryObject.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<UserConnectedto> checkForRelationBetweenUsers(List<Long> senderId,List<Long> recipeintId){
		StringBuilder query = new StringBuilder();				
		query.append(" from UserConnectedto model where ");
		query.append(" (model.senderId.userId in (:senderId) and  model.recepientId.userId in (:recipeintId)) or");
		query.append(" (model.senderId.userId in (:recipeintId) and model.recepientId.userId in (:senderId)) ");	
				
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("senderId", senderId);
		queryObject.setParameterList("recipeintId", recipeintId);
		queryObject.setParameterList("recipeintId", recipeintId);
		queryObject.setParameterList("senderId", senderId);
		return queryObject.list();
	}
	

	
	
	@SuppressWarnings("unchecked")
	public List<Object> getAllConnectedPeopleForUser(List<Long> senderId){
		StringBuilder query = new StringBuilder();	
		query.append(" select model.senderId.userId,model.recepientId.userId from UserConnectedto model where ");
		query.append(" (model.senderId.userId in (:senderId) or  model.recepientId.userId in (:senderId)) ");	
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("senderId", senderId);
		queryObject.setParameterList("senderId", senderId);
		return queryObject.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllConnectedPeopleForFreeUser(Long senderId){
		return getHibernateTemplate().find(" select model.recepientId.userId,model.recepientId.name,model.recepientId.lastName,model.recepientId.email from UserConnectedto model where "+
					"model.senderId.userId = ? ",senderId);	
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllConnectedPeoplesForFreeUser(Long recepientId)
	{
		return getHibernateTemplate().find("select model.senderId.userId,model.senderId.name,model.senderId.lastName,model.senderId.email from UserConnectedto model where "+
	"model.recepientId.userId = ?",recepientId);
	}
	@SuppressWarnings("unchecked")
	public List<Object> getAllPeopleThatMayBeKnownForUser(Long userId){
		StringBuilder query = new StringBuilder();				
		query.append(" select model.senderId.userId,model.recepientId.userId from UserConnectedto model where ");
		query.append(" (model.senderId.userId in (select model2.senderId.userId from UserConnectedto model2 where ");
		query.append(" (model2.senderId.userId =? or  model2.recepientId.userId = ?)) or");
		query.append("  model.recepientId.userId in (select model3.senderId.userId from UserConnectedto model3 where");
		query.append("(model3.senderId.userId =? or  model3.recepientId.userId = ?))) ");	
				
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setLong(0, userId);
		queryObject.setLong(1, userId);
		queryObject.setLong(2, userId);
		queryObject.setLong(3, userId);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public String getCountOfAllConnectedPeopleForUser(List<Long> senderId){
		StringBuilder query = new StringBuilder();	
		query.append(" select count(model.senderId.userId) from UserConnectedto model where ");
		query.append(" (model.senderId.userId in (:senderId) or  model.recepientId.userId in (:senderId)) ");	
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("senderId", senderId);
		queryObject.setParameterList("senderId", senderId);
		return queryObject.list().get(0).toString();
	}
	
	public Long getConnectedMembersCountForAFreeUser(Long userId)
	{
		Query query = getSession().createQuery("select count(model.userConnectedtoId) from UserConnectedto model where " +
				" model.senderId.userId = ? or model.recepientId.userId = ?");
		query.setParameter(0,userId);
		query.setParameter(1,userId);
		
		return (Long)query.uniqueResult();
		
	}
	
	public Long getConnectedUsersCountForAUserInAFilterView(Long userId,List<Long> locationIds,String locationType,String nameStr)
	{
		StringBuilder query = new StringBuilder();
		query.append("select count(model.userConnectedtoId)");
		query.append(" from UserConnectedto model where (model.senderId.userId = :userId or model.recepientId.userId = :userId) and ");
		if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
			query.append("(model.senderId.state.stateId in (:locationIds) or model.recepientId.state.stateId in (:locationIds)) ");
		}else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
			query.append("(model.senderId.district.districtId in (:locationIds) or model.recepientId.district.districtId in (:locationIds)) ");
		}else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
			query.append("(model.senderId.constituency.constituencyId in (:locationIds) or model.recepientId.constituency.constituencyId in (:locationIds)) ");
		}
		query.append("and model.state.stateId is not null and model.district.districtId is not null and model.constituency.constituencyId is not null ");
		if(nameStr != null && !nameStr.trim().equalsIgnoreCase(""))
		{
			query.append("and ((model.senderId.name like '"+nameStr+"%' or model.senderId.lastName like '"+nameStr+"%') or " +
					"(model.recepientId.name like '"+nameStr+"%' or model.recepientId.lastName like '"+nameStr+"%'))");
		}
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("locationIds", locationIds);
		queryObject.setParameter("userId", userId);
		
		return (Long)queryObject.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getConnectedUsersInSelectedLocations(Long userId, List<Long> locationIds,String locationType,Long retrivalCount,Long startIndex,String nameString) {
		StringBuilder query = new StringBuilder();
		query.append("select model.name,model.lastName,model.userId,model.constituency.name,model.constituency.constituencyId, model ");
		query.append("from AnanymousUser model, UserConnectedto model1 where (model1.senderId.userId = :userId or model1.recepientId.userId = :userId) and ");
		query.append("(model.userId = model1.recepientId or model.userId = model1.senderId) and model.userId != :userId and ");
		
		if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
			query.append("(model1.senderId.state.stateId in (:locationIds) and model1.recepientId.state.stateId in (:locationIds)) ");
		}else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
			query.append("(model1.senderId.district.districtId in (:locationIds) and model1.recepientId.district.districtId in (:locationIds)) ");
		}else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
			query.append("(model1.senderId.constituency.constituencyId in (:locationIds) and model1.recepientId.constituency.constituencyId in (:locationIds)) ");
		}
		if(nameString != null && !nameString.trim().equalsIgnoreCase(""))
		{
			query.append("and ((model1.senderId.name like '"+nameString+"%' or model1.senderId.lastName like '"+nameString+"%') or " +
					"(model1.recepientId.name like '"+nameString+"%' or model1.recepientId.lastName like '"+nameString+"%'))");
		}
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("locationIds", locationIds);
		queryObject.setParameter("userId", userId);

		if(startIndex!=null)
			queryObject.setFirstResult(startIndex.intValue());
		if(retrivalCount != null)
			queryObject.setMaxResults(retrivalCount.intValue());	
		
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getConnectedUserIdsInSelectedLocations(Long userId, List<Long> locationIds,String locationType) 
	{
		StringBuilder query = new StringBuilder();
		query.append("select model.userId from AnanymousUser model, UserConnectedto model1 where (model1.senderId.userId = :userId or model1.recepientId.userId = :userId) and ");
		query.append("(model.userId = model1.recepientId or model.userId = model1.senderId) and model.userId != :userId and ");
		
		if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
			query.append("(model1.senderId.state.stateId in (:locationIds) and model1.recepientId.state.stateId in (:locationIds)) ");
		}else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
			query.append("(model1.senderId.district.districtId in (:locationIds) and model1.recepientId.district.districtId in (:locationIds)) ");
		}else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
			query.append("(model1.senderId.constituency.constituencyId in (:locationIds) and model1.recepientId.constituency.constituencyId in (:locationIds)) ");
		}
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("locationIds", locationIds);
		queryObject.setParameter("userId", userId);

		return queryObject.list();
	}
}
