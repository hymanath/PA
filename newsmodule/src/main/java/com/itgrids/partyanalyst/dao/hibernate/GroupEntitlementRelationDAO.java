package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGroupEntitlementRelationDAO;
import com.itgrids.partyanalyst.model.Entitlement;
import com.itgrids.partyanalyst.model.GroupEntitlementRelation;

public class GroupEntitlementRelationDAO extends GenericDaoHibernate<GroupEntitlementRelation,Long>
		implements IGroupEntitlementRelationDAO {

	public GroupEntitlementRelationDAO() {
		super(GroupEntitlementRelation.class);
	}

	@SuppressWarnings("unchecked")
	public List<Entitlement> getAllEntitlementsForAGroupByGroupId(Long groupId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.entitlement.entitlementId,model.entitlement.entitlementType from GroupEntitlementRelation model");
		sb.append(" where model.groupEntitlement.groupEntitlementId = ?");
		
		return getHibernateTemplate().find(sb.toString(),groupId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getAllEntitlementsBasedOnUserGroupId(Long groupId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.entitlement.entitlementId,model.entitlement.entitlementType from GroupEntitlementRelation model");
		sb.append(" where model.groupEntitlement.groupEntitlementId in ");
		sb.append(" (select model3.groupEntitlement.groupEntitlementId from UserGroupEntitlement model3 where model3.userGroup.userGroupId in (");
		sb.append(" select model2.userGroup.userGroupId from UserGroupRelation model2 where model2.userGroup.userGroupId = ?))");
		
		return getHibernateTemplate().find(sb.toString(),groupId);
	}
	
	@SuppressWarnings("unchecked")
	public Integer deleteAllRelations(Long groupId) {		
		StringBuilder query = new StringBuilder();
		query.append(" delete from GroupEntitlementRelation model ");
		query.append(" where model.groupEntitlement.groupEntitlementId = ? ");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, groupId);
		return queryObject.executeUpdate();		
	}

}
