package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IEntitlementsGroupEntitlementUrlDAO;
import com.itgrids.model.EntitlementsGroupEntitlementUrl;

@Repository
public class EntitlementsGroupEntitlementUrlDAO extends GenericDaoHibernate<EntitlementsGroupEntitlementUrl, Long> implements IEntitlementsGroupEntitlementUrlDAO {

	@Autowired
	SessionFactory sessionFactory; 
	public EntitlementsGroupEntitlementUrlDAO() {
		super(EntitlementsGroupEntitlementUrl.class);
		
	}
	
	public List<Object[]> getUserIdsByEntitlementsLogin(Long userId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.entitlementGroupId, model.entitlementGroup.groupType,model.entitlementUrlId,model.entitlementUrl.entitlementName,model.entitlementUrl.url,"
				+ " model.parentEntitlementsGroupId,model.orderNo "
				  + " from EntitlementsGroupEntitlementUrl model,UserGroupEntitlement model1 ");
		sb.append(" where model.entitlementGroupId = model1.entitlementGroupId ");
		if(userId != null && userId.longValue() >0l){
			sb.append(" and  model1.userId =:userId ");
		}
		sb.append(" and model.parentEntitlementsGroupId is not null ");
		sb.append(" and model.isDeleted ='N' and model1.isDeleted = 'N' and model.entitlementUrl.isDeleted = 'N' ");
		
		  Query qry = getSession().createQuery(sb.toString());
		  if(userId != null && userId.longValue() >0l){
			  qry.setParameter("userId", userId);
			}
		return qry.list();
	}

}
