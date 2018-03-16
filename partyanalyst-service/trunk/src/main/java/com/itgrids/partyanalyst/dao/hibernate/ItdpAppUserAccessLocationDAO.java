package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IItdpAppUserAccessLocationDAO;
import com.itgrids.partyanalyst.model.ItdpAppUserAccessLocation;

public class ItdpAppUserAccessLocationDAO extends GenericDaoHibernate<ItdpAppUserAccessLocation, Long> implements IItdpAppUserAccessLocationDAO{

	public ItdpAppUserAccessLocationDAO() {
		super(ItdpAppUserAccessLocation.class);
		
	}
	public Long getTabUserId(Long accussLevelId,Long accussValue){
		Query query = getSession().createQuery(" select distinct model.itdpAppUserId from ItdpAppUserAccessLocation model where  model.locationScopeId =:accussLevelId " +
				"and model.locationValue :=accussValue  and model.isDeleted ='N' ");
		query.setParameter("accussLevelId", accussLevelId);
		query.setParameter("accussValue", accussValue);
		return (Long)query.uniqueResult();
	}
	public Long getTabUserLocationId(Long userId){
		Query query = getSession().createQuery(" select distinct model.locationScopeId from ItdpAppUserAccessLocation model where  model.itdpAppUserId =:userId  and model.isDeleted ='N' ");
		query.setParameter("userId", userId);
		return (Long)query.uniqueResult();
	}
}
