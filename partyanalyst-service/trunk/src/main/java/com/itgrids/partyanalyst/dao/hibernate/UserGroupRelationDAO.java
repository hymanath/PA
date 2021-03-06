/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 10,2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserGroupRelationDAO;
import com.itgrids.partyanalyst.model.UserGroupRelation;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserGroupRelationDAO extends GenericDaoHibernate<UserGroupRelation, Long> implements
		IUserGroupRelationDAO {

	public UserGroupRelationDAO() {
		super(UserGroupRelation.class);
	}

	@SuppressWarnings("unchecked")
	public List<UserGroupRelation> findByUserId(Long userId) {
		return getHibernateTemplate().find("from UserGroupRelation model where model.user.userId = ?",userId);
	}
	
	@SuppressWarnings("unchecked")
	public List checkTheRelationBetweenUserAndGroup(Long userId) {
		
		StringBuilder query = new StringBuilder();
		query.append(" select model.userGroup.userGroupId,model.user.userId from UserGroupRelation model ");
		query.append(" where model.user.userId = ? order by model.user.userId asc");
		Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setLong(0,userId);		
		return queryObject.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public Integer deleteAllUser(Long userId) {
		
		StringBuilder query = new StringBuilder();
		query.append(" delete from UserGroupRelation model ");
		query.append(" where model.user.userId = ? ");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, userId);
		return queryObject.executeUpdate();
		
	}
	public List<String> getUserIdCount(Long userId){
		Query query = getSession().createQuery("select distinct model.userGroup.notes from UserGroupRelation model where " +
				" model.user.userId = :userId " );
		query.setParameter("userId", userId);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<String> getEntitlements(Long userId){
		Query query = getSession().createSQLQuery(" select E.entitlement_type " +
				" from user_group_relation UGR,user_group_entitlement UGE," +
				" entitlement E,group_entitlement_relation GER where user_id =:userId " +
				" and UGE.user_group_id = UGR.user_group_id " +
				" and GER.group_entitlement_id = UGE.group_entitlement_id  " +
				" and E.entitlement_id = GER.entitlement_id " +
				" group by E.entitlement_id ");
		query.setParameter("userId", userId);
		return query.list();
		
	}
	public Long getUserGroupId(Long userId){
		Query query = getSession().createQuery("select distinct model.userGroup.userGroupId from UserGroupRelation model where " +
				" model.user.userId = :userId and model.userGroup.userGroupId in(35,188) " );
		query.setParameter("userId", userId);
		return (Long)query.uniqueResult();
	}
}
