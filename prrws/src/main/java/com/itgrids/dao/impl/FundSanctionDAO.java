package com.itgrids.dao.impl;

import java.util.Date;
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
	
	public List<Object[]> getLocationsCountDetails(Long locatioinTypeId,Long financialYrId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.locationScopeId,count(model.locationScopeId), sum(model.amount) from   ");
		queryStr.append(" FundSanction model  ");
		queryStr.append(" where model.isDeleted ='N' and model.locationAddress.district.stateId = 1 ");
		if(locatioinTypeId != null && locatioinTypeId.longValue()>0L)
			queryStr.append(" and model.locationScopeId = :locatioinTypeId ");
		
		if(financialYrId != null && financialYrId.longValue() >0)
			queryStr.append(" and model.financialYearId = :financialYrId ");
		queryStr.append(" group by model.locationScopeId ");
		queryStr.append(" order by model.locationScopeId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(locatioinTypeId != null && locatioinTypeId.longValue()>0L)
			query.setParameter("locatioinTypeId", locatioinTypeId);
		
		if(financialYrId != null && financialYrId.longValue() >0)
			query.setParameter("financialYrId", financialYrId);
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
	
	public List<Object[]> getLocationWiseFundHighAndLow(Long financialYrId,Long departmentId,Long sourceId,Date sDate,Date eDate
			,Long locationScopeId,String type){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select sum(modal.sactionAmount) " );
		if(locationScopeId != null && locationScopeId.longValue() == 3l ){
			sb.append(" ,modal.locationAddress.district.districtId,modal.locationAddress.district.districtName " );
		}else if(locationScopeId != null && locationScopeId.longValue() == 4l ){
			sb.append(" ,modal.locationAddress.constituency.constituencyId,modal.locationAddress.constituency.name " );
		}
		sb.append(" from FundSanction modal where modal.isDeleted='N' ");
		
		if(locationScopeId != null && locationScopeId.longValue() >0l ){
			sb.append(" and modal.locationScopeId = :locationScopeId  " );
		}
		if(financialYrId != null && financialYrId.longValue() >0l ){
			sb.append(" and modal.financialYearId = :financialYrId  " );
		}
		if(sDate != null && eDate != null){
			sb.append(" and date(modal.insertedTime) between  :sDate and :eDate " );
		}
		if(type != null && type.equalsIgnoreCase("highest")){
			sb.append(" group by modal.locationValue order by sum(modal.sactionAmount) desc ");
		}else if(type != null && type.equalsIgnoreCase("lowest")){
			sb.append(" group by modal.locationValue order by sum(modal.sactionAmount) asc ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		query.setFirstResult(1);
		query.setMaxResults(2);
		if(locationScopeId != null && locationScopeId.longValue() >0l ){
			query.setParameter("locationScopeId", locationScopeId);
		}
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(financialYrId != null && financialYrId.longValue() >0l ){
			query.setParameter("financialYrId", financialYrId);
		}
		return query.list();
		
	}
	public List<Object[]> getSchemeWiseFundHighAndLow(Long financialYrId,Long departmentId,Long sourceId,Date sDate,Date eDate
			,String type){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select sum(modal.sactionAmount) " );
		
			sb.append(" ,modal.govtScheme.govtSchemeId,modal.govtScheme.schemeName " );
		
			
		sb.append(" from FundSanction modal where modal.isDeleted='N' ");
		
		
		if(financialYrId != null && financialYrId.longValue() >0l ){
			sb.append(" and modal.financialYearId = :financialYrId  " );
		}
		if(sDate != null && eDate != null){
			sb.append(" and date(modal.insertedTime) between  :sDate and :eDate " );
		}
		if(type != null && type.equalsIgnoreCase("highest")){
			sb.append(" group by modal.govtScheme.govtSchemeId order by sum(modal.sactionAmount) desc ");
		}else if(type != null && type.equalsIgnoreCase("lowest")){
			sb.append(" group by modal.govtScheme.govtSchemeId order by sum(modal.sactionAmount) asc ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		
		query.setFirstResult(1);
		query.setMaxResults(2);
		
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(financialYrId != null && financialYrId.longValue() >0l ){
			query.setParameter("financialYrId", financialYrId);
		}
		return query.list();
		
	}
	
	public Long getTotalFund(Long financialYrId,Long departmentId,Long sourceId,Date sDate,Date eDate,Long scopeId){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select sum(modal.sactionAmount) " );
		
		sb.append(" from FundSanction modal where modal.isDeleted='N' ");
		
		
		if(financialYrId != null && financialYrId.longValue() >0l ){
			sb.append(" and modal.financialYearId = :financialYrId  " );
		}
		if(sDate != null && eDate != null){
			sb.append(" and date(modal.insertedTime) between  :sDate and :eDate " );
		}
		
		if(scopeId != null && scopeId.longValue() >0l ){
			sb.append(" and modal.locationScopeId = :locationScopeId  " );
		}
		
		Query query = getSession().createQuery(sb.toString());
		
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(financialYrId != null && financialYrId.longValue() >0l ){
			query.setParameter("financialYrId", financialYrId);
		}
		
		if(scopeId != null && scopeId.longValue() >0l ){
			query.setParameter("locationScopeId", scopeId);
		}
		return (Long)query.uniqueResult();
		
	}
	
	public List<Object[]> getLocationWiseGrantTypesFund(Long financialYrId,Long departmentId,Long sourceId,Date sDate,Date eDate
			,Long locationScopeId,Long locationVal){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select sum(modal.sactionAmount) " );
		
		sb.append(" ,grantType.grantTypeId,'' " );
		
		sb.append(" from FundSanction modal, GrantType grantType where modal.isDeleted='N' and grantType.grantTypeId = modal.grantType.grantTypeId ");
		
		if(locationScopeId != null && locationScopeId.longValue() >0l ){
			sb.append(" and modal.locationScopeId = :locationScopeId  " );
		}
		if(locationVal != null && locationVal.longValue() >0l ){
			sb.append(" and modal.locationValue = :locationVal  " );
		}
		if(financialYrId != null && financialYrId.longValue() >0l ){
			sb.append(" and modal.financialYearId = :financialYrId  " );
		}
		if(sDate != null && eDate != null){
			sb.append(" and (date(modal.insertedTime) between  :sDate and :eDate) " );
		}
		
		sb.append(" group by modal.locationValue,modal.grantType.grantTypeId  ");
		
		
		Query query = getSession().createQuery(sb.toString());
		
		
		if(locationScopeId != null && locationScopeId.longValue() >0l ){
			query.setParameter("locationScopeId", locationScopeId);
		}
		if(locationVal != null && locationVal.longValue() >0l ){
			query.setParameter("locationVal", locationVal);
		}
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(financialYrId != null && financialYrId.longValue() >0l ){
			query.setParameter("financialYrId", financialYrId);
		}
		return query.list();
		
	}
}
