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
	
	public List<Object[]> getFinancialYearWiseScheameDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,List<Long> schemeIdsList,Date startDate,Date endDate,Long searchScopeId,List<Long> searchScopeValuesList){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct  model.govtSchemeId, model.govtScheme.schemeName, "
				+ " model.financialYear.financialYearId, model.financialYear.yearDesc, count(model.govtSchemeId), sum(model.sactionAmount), "
				+ " model.locationScopeId, model.locationValue, model.locationAddress.districtId, model.locationAddress.district.districtName, "
				+ " model.locationAddress.constituencyId, model.locationAddress.constituency.name ");
		queryStr.append(" from FundSanction model ");
		queryStr.append(" where model.isDeleted ='N' ");
		if(financialYearIdsList != null && financialYearIdsList.size()>0)
			queryStr.append(" and model.financialYearId in (:financialYearIdsList) ");
		if(deptIdsList != null && deptIdsList.size()>0)
			queryStr.append("  ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			queryStr.append("  ");
		if(schemeIdsList != null && schemeIdsList.size()>0)
			queryStr.append(" and model.govtSchemeId in (:schemeIdsList) ");
		if(startDate != null && endDate != null)
			queryStr.append(" and ( date(model.updatedTime) between :startDate and :endDate)  ");
		if(searchScopeId != null && searchScopeId.longValue()>0L)
			queryStr.append(" and model.locationScopeId =:searchScopeId ");
		if(searchScopeValuesList != null && searchScopeValuesList.size()>0)
			queryStr.append(" and model.locationValue in (:searchScopeValuesList) ");
		
		queryStr.append(" group by model.locationScopeId,model.locationValue,model.financialYearId,model.govtSchemeId ");
		queryStr.append(" order by model.locationScopeId,model.locationValue,model.financialYearId,model.govtSchemeId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		
		if(financialYearIdsList != null && financialYearIdsList.size()>0)
			query.setParameterList("financialYearIdsList", financialYearIdsList);
		if(deptIdsList != null && deptIdsList.size()>0)
			;
		if(sourceIdsList != null && sourceIdsList.size()>0)
			;
		if(schemeIdsList != null && schemeIdsList.size()>0)
			query.setParameterList("schemeIdsList", schemeIdsList);
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(searchScopeId != null && searchScopeId.longValue()>0L)
			query.setParameter("searchScopeId", searchScopeId);
		if(searchScopeValuesList != null && searchScopeValuesList.size()>0)
			query.setParameterList("searchScopeValuesList", searchScopeValuesList);
		
		return query.list();
	}
	public List<Object[]> getLocationsCountDetails(Long locatioinTypeId,List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.locationScopeId,count( distinct model.locationValue), sum(model.sactionAmount) from   ");
		queryStr.append(" FundSanction model  ");
		queryStr.append(" where model.isDeleted ='N' and model.locationAddress.district.stateId = 1 ");
		if(locatioinTypeId != null && locatioinTypeId.longValue()>0L)
			queryStr.append(" and model.locationScopeId = :locatioinTypeId ");
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			queryStr.append(" and modal.financialYearId in (:financialYearIdsList)  " );
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			queryStr.append("  ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			queryStr.append("  ");
		queryStr.append(" group by model.locationScopeId ");
		queryStr.append(" order by model.locationScopeId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(locatioinTypeId != null && locatioinTypeId.longValue()>0L)
			query.setParameter("locatioinTypeId", locatioinTypeId);
		
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			query.setParameterList("financialYearIdsList", financialYearIdsList);
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			;
		if(sourceIdsList != null && sourceIdsList.size()>0)
			;
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
	@Override
	public List<Object[]> getLocationWiseAmount(Long levelId, List<Long> levelValues,Date fromDate,Date toDate,List<Long> financialYrIdList){
		StringBuilder sb = new StringBuilder();  
		sb.append(" select "
				+ " model.financialYear.financialYearId "//0
				+ " ,model.financialYear.yearDesc "//1
				+ " ,model.locationValue "//2
				+ " ,count(model.locationValue) "//3
				+ " ,sum(model.sactionAmount) "//4
				+ " from "
				+ " FundSanction model "
				+ " left outer join model.locationAddress locationAddress "
				+ " left outer join locationAddress.district district "
				+ " left outer join locationAddress.constituency constituency "
				+ " where "
				+ " model.isDeleted = 'N' "
				+ " and model.locationScopeId = :levelId "
				+ " and model.locationValue in (:levelValues) "
				+ " and model.insertedTime between :fromDate and :toDate ");
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			sb.append(" and model.financialYearId = :financialYrIdList ");
		}
		sb.append(" group by model.financialYear.financialYearId, model.locationValue order by model.financialYear.financialYearId ");
		Query query = getSession().createQuery(sb.toString());
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			query.setParameterList("financialYrIdList", financialYrIdList);
		}
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		query.setParameter("levelId", levelId);
		query.setParameterList("levelValues", levelValues);
		
		return query.list();
	}
	@Override
	public List<Object[]> getLocationInfo(Long levelId, List<Long> levelValues){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct model.locationValue ");
		if(levelId != null && levelId.longValue() == 3L){
			sb.append(" , district.districtName ");
		}else if(levelId != null && levelId.longValue() == 4L){
			sb.append(" , constituency.name ");
		}
		sb.append(" from FundSanction model "
				+ " left outer join model.locationAddress locationAddress "
				+ " left outer join locationAddress.district district "
				+ " left outer join locationAddress.constituency constituency "
				+ " where "
				+ " model.isDeleted = 'N' "
				+ " and model.locationScopeId = :levelId "
				+ " and model.locationValue in (:levelValues) ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("levelId", levelId);
		query.setParameterList("levelValues", levelValues);
		return query.list();
	}
	
	public List<Object[]> getLocationWiseFundHighAndLow(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,Date sDate,Date eDate,Long locationScopeId,String type){
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
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			sb.append(" and modal.financialYearId = :financialYrId  " );
		}
		
		if(deptIdsList != null && deptIdsList.size()>0)
			sb.append("  ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			sb.append("  ");
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
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			query.setParameterList("financialYearIdsList", financialYearIdsList);
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			;
		if(sourceIdsList != null && sourceIdsList.size()>0)
			;
		return query.list();
		
	}
	public List<Object[]> getSchemeWiseFundHighAndLow(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,Date sDate,Date eDate
			,String type){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select sum(modal.sactionAmount) " );
		
		sb.append(" ,modal.govtScheme.govtSchemeId,modal.govtScheme.schemeName " );
		
		sb.append(" from FundSanction modal where modal.isDeleted='N' ");
		
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			sb.append(" and modal.financialYearId in (:financialYearIdsList)  " );
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			sb.append("  ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			sb.append("  ");
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
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			query.setParameterList("financialYearIdsList", financialYearIdsList);
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			;
		if(sourceIdsList != null && sourceIdsList.size()>0)
			;
		return query.list();
		
	}
	
	public Long getTotalFund(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,Date sDate,Date eDate,Long scopeId){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select sum(modal.sactionAmount) " );
		sb.append(" from FundSanction modal where modal.isDeleted='N' ");
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			sb.append(" and modal.financialYearId in (:financialYearIdsList)  " );
		}
		if(sDate != null && eDate != null){
			sb.append(" and date(modal.insertedTime) between  :sDate and :eDate " );
		}
		if(scopeId != null && scopeId.longValue() >0l ){
			sb.append(" and modal.locationScopeId = :locationScopeId  " );
		}
		
		if(deptIdsList != null && deptIdsList.size()>0)
			sb.append("  ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			sb.append("  ");
		Query query = getSession().createQuery(sb.toString());
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			query.setParameterList("financialYearIdsList", financialYearIdsList);
		}
		
		if(scopeId != null && scopeId.longValue() >0l ){
			query.setParameter("locationScopeId", scopeId);
		}
		return (Long)query.uniqueResult();
	}
	
	public List<Object[]> getLocationWiseGrantTypesFund(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,Date sDate,Date eDate
			,Long locationScopeId,Long locationVal){
		StringBuilder sb = new StringBuilder();
		sb.append(" select sum(modal.sactionAmount) " );
		sb.append(" ,modal.grantType.grantTypeId,modal.grantType.type " );
		sb.append(" from FundSanction modal where modal.isDeleted='N'  ");
		if(locationScopeId != null && locationScopeId.longValue() >0l ){
			sb.append(" and modal.locationScopeId = :locationScopeId  " );
		}
		if(locationVal != null && locationVal.longValue() >0l ){
			sb.append(" and modal.locationValue = :locationVal  " );
		}
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			sb.append(" and modal.financialYearId in (:financialYearIdsList)  " );
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			sb.append("  ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			sb.append("  ");
		if(sDate != null && eDate != null){
			sb.append(" and (date(modal.insertedTime) between  :sDate and :eDate) " );
		}
		sb.append(" group by modal.grantType.grantTypeId  ");
		if(locationVal != null && locationVal.longValue() >0l ){
			sb.append(" ,modal.locationValue ");
		}
		
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
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			query.setParameterList("financialYearIdsList", financialYearIdsList);
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			;
		if(sourceIdsList != null && sourceIdsList.size()>0)
			;
		return query.list();
	}
	
	public Long getTotalSchemes(List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList,Date sDate,Date eDate){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(distinct modal.govtScheme.govtSchemeId ) " );
		sb.append(" from FundSanction modal where modal.isDeleted='N' ");
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			sb.append(" and modal.financialYearId in (:financialYearIdsList)  " );
		}
		if(sDate != null && eDate != null){
			sb.append(" and date(modal.insertedTime) between  :sDate and :eDate " );
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			sb.append("  ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			sb.append("  ");
		sb.append(" group by modal.govtScheme.govtSchemeId ");
		Query query = getSession().createQuery(sb.toString());
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			query.setParameterList("financialYearIdsList", financialYearIdsList);
		}
		
		if(deptIdsList != null && deptIdsList.size()>0)
			;
		if(sourceIdsList != null && sourceIdsList.size()>0)
			;
		return (Long)query.uniqueResult();
	}
	
	public List<Object[]> getSchemeWiseGrantTypesFund(Long financialYrId,Long departmentId,Long sourceId,Date sDate,Date eDate
			,Long schemeId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select sum(modal.sactionAmount) " );
		sb.append(" ,modal.govtScheme.govtSchemeId,'' " );
		sb.append(" from FundSanction modal where modal.isDeleted='N'  ");
		if(schemeId != null && schemeId.longValue() >0l ){
			sb.append(" and modal.govtScheme.govtSchemeId = :schemeId  " );
		}
		if(financialYrId != null && financialYrId.longValue() >0l ){
			sb.append(" and modal.financialYearId = :financialYrId  " );
		}
		if(sDate != null && eDate != null){
			sb.append(" and (date(modal.insertedTime) between  :sDate and :eDate) " );
		}
		sb.append(" group by modal.locationValue,modal.govtScheme.govtSchemeId  ");
		Query query = getSession().createQuery(sb.toString());
		if(schemeId != null && schemeId.longValue() >0l ){
			query.setParameter("schemeId", schemeId);
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
