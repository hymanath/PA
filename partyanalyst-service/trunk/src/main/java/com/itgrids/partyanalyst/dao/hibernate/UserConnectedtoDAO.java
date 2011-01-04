package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserConnectedtoDAO;
import com.itgrids.partyanalyst.model.UserConnectedto;

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
	
	
}
