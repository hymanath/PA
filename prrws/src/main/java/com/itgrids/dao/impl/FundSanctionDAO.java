package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IFundSanctionDAO;
import com.itgrids.model.FundSanction;

@Repository
public class FundSanctionDAO extends GenericDaoHibernate<FundSanction, Long> implements IFundSanctionDAO {

	@Autowired
	SessionFactory sessionFactory;

	public FundSanctionDAO() {
		super(FundSanction.class);

	}
	
	public List<Object[]> getLocationsCountDetails(Long locatioinTypeId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.locationScopeId,count(model.locationScopeId), sum(model.amount) from   ");
		queryStr.append(" FundSanction model  ");
		queryStr.append(" where model.isDeleted ='N' and model.locationAddress.district.stateId = 1 ");
		if(locatioinTypeId != null && locatioinTypeId.longValue()>0L)
			queryStr.append(" and model.locationScopeId = :locatioinTypeId ");
		queryStr.append(" group by model.locationScopeId ");
		queryStr.append(" order by model.locationScopeId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("locatioinTypeId", locatioinTypeId);
		return query.list();
	}

	@Override
	public List<Long> getLocationValues(Long scopeId) {
		Criteria criteria=getSession().createCriteria(FundSanction.class);
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("locationValue"));
		criteria.setProjection(projectionList);
		criteria.add(Restrictions.eq("locationScopeId", scopeId));
		return criteria.list();
	}
}
