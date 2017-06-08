package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
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
	public List<Object[]> getLocationsCountDetails(Long locatioinTypeId,List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList,Long searchLevlId,List<Long> searchLvlVals){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.locationScopeId,count( distinct model.locationValue), sum(model.sactionAmount) from   ");
		queryStr.append(" FundSanction model  ");
		queryStr.append(" where model.isDeleted ='N' and model.locationAddress.district.stateId = 1 ");
		if(locatioinTypeId != null && locatioinTypeId.longValue()>0L)
			queryStr.append(" and model.locationScopeId = :locatioinTypeId ");
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			queryStr.append(" and model.financialYearId in (:financialYearIdsList)  " );
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			queryStr.append(" and model.department.departmentId in (:deptIdsList) ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			queryStr.append("  ");
		if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == 3l){
			queryStr.append(" and model.locationAddress.district.districtId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == 4l){
			queryStr.append(" and model.locationAddress.constituency.constituencyId in (:searchLvlVals) ");
		}
		queryStr.append(" group by model.locationScopeId ");
		queryStr.append(" order by model.locationScopeId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(locatioinTypeId != null && locatioinTypeId.longValue()>0L)
			query.setParameter("locatioinTypeId", locatioinTypeId);
		
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			query.setParameterList("financialYearIdsList", financialYearIdsList);
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			query.setParameterList("deptIdsList", deptIdsList);
		if(sourceIdsList != null && sourceIdsList.size()>0)
			;
		if(searchLevlId != null && searchLevlId.longValue() > 0l){
			query.setParameterList("searchLvlVals", searchLvlVals);
		}
		return query.list();
	}

	@Override
	public List<Long> getLocationValues(Long scopeId) {
		Criteria criteria=getSession().createCriteria(FundSanction.class);
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.distinct(Projections.property("locationValue")));
		criteria.setProjection(projectionList);
		criteria.add(Restrictions.eq("locationScopeId", scopeId));
		return criteria.list();
	}
	@Override
	public List<Object[]> getLocationWiseAmount(Long levelId, List<Long> levelValues,Date fromDate,Date toDate,List<Long> financialYrIdList,Long deptId,Long sourceId){
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
			sb.append(" and model.financialYearId in (:financialYrIdList) ");
		}
		if(deptId !=null && deptId.longValue() > 0L){
			sb.append(" and model.department.departmentId = :deptId ");
		}
		if(sourceId !=null && sourceId.longValue() > 0L){
			
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
		if(deptId !=null && deptId.longValue() > 0L){
			query.setParameter("deptId", deptId);
		}
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
			List<Long> sourceIdsList,Date sDate,Date eDate,Long locationScopeId,String type,Long searchLevlId,List<Long> searchLvlVals){
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
			sb.append(" and modal.financialYearId in (:financialYearIdsList)  " );
		}
		
		if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == 3l){
			sb.append(" and modal.locationAddress.district.districtId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == 4l){
			sb.append(" and modal.locationAddress.constituency.constituencyId in (:searchLvlVals) ");
		}
		
		if(deptIdsList != null && deptIdsList.size()>0)
			sb.append(" and modal.department.departmentId in (:deptIdsList) ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			sb.append("  ");
		if(sDate != null && eDate != null){
			sb.append(" and (date(modal.insertedTime) between  :sDate and :eDate) " );
		}
		
		if(locationScopeId != null && locationScopeId.longValue() == 3l ){
			sb.append(" group by modal.locationAddress.district.districtId " );
		}else if(locationScopeId != null && locationScopeId.longValue() == 4l ){
			sb.append(" group by modal.locationAddress.constituency.constituencyId " );
		}
		if(type != null && type.equalsIgnoreCase("highest")){
			sb.append("   order by sum(modal.sactionAmount) desc ");
		}else if(type != null && type.equalsIgnoreCase("lowest")){
			sb.append("  order by sum(modal.sactionAmount) asc ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		query.setFirstResult(0);
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
			query.setParameterList("deptIdsList", deptIdsList);
		if(sourceIdsList != null && sourceIdsList.size()>0)
			;
		if(searchLevlId != null && searchLevlId.longValue() > 0l){
			query.setParameterList("searchLvlVals", searchLvlVals);
		}
		return query.list();
		
	}
	public List<Object[]> getSchemeWiseFundHighAndLow(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,Date sDate,Date eDate,String type,Long searchLevlId,List<Long> searchLvlVals){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select sum(modal.sactionAmount) " );
		
		sb.append(" ,modal.govtScheme.govtSchemeId,modal.govtScheme.schemeName " );
		
		sb.append(" from FundSanction modal where modal.isDeleted='N' ");
		
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			sb.append(" and modal.financialYearId in (:financialYearIdsList)  " );
		}
		if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == 3l){
			sb.append(" and  modal.locationAddress.district.districtId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == 4l){
			sb.append(" and modal.locationAddress.constituency.constituencyId in (:searchLvlVals) ");
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			sb.append(" and modal.department.departmentId in (:deptIdsList) ");
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
		
		query.setFirstResult(0);
		query.setMaxResults(2);
		
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(searchLevlId != null && searchLevlId.longValue() > 0l){
			query.setParameterList("searchLvlVals", searchLvlVals);
		}
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			query.setParameterList("financialYearIdsList", financialYearIdsList);
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			query.setParameterList("deptIdsList", deptIdsList);
		if(sourceIdsList != null && sourceIdsList.size()>0)
			;
		return query.list();
		
	}
	
	public Long getTotalFund(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,Date sDate,Date eDate,Long scopeId,Long searchLevlId,List<Long> searchLvlVals){
		
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
		
		if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == 3l){
			sb.append(" and modal.locationAddress.district.districtId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == 4l){
			sb.append(" and modal.locationAddress.constituency.constituencyId in (:searchLvlVals) ");
		}
		
		if(deptIdsList != null && deptIdsList.size()>0)
			sb.append(" and modal.department.departmentId in (:deptIdsList) ");
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
		if(deptIdsList != null && deptIdsList.size()>0){
			query.setParameterList("deptIdsList", deptIdsList);
		}
		if(searchLevlId != null && searchLevlId.longValue() > 0l){
			query.setParameterList("searchLvlVals", searchLvlVals);
		}
		return (Long)query.uniqueResult();
	}
	
	public List<Object[]> getLocationWiseGrantTypesFund(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,Date sDate,Date eDate,Long locationScopeId,Long locationVal,Long searchLevlId,List<Long> searchLvlVals){
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
		if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == 3l){
			sb.append(" and modal.locationAddress.district.districtId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == 4l){
			sb.append(" and modal.locationAddress.constituency.constituencyId in (:searchLvlVals) ");
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			sb.append(" and modal.department.departmentId in (:deptIdsList) ");
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
		if(searchLevlId != null && searchLevlId.longValue() > 0l){
			query.setParameterList("searchLvlVals", searchLvlVals);
		}
		if(deptIdsList != null && deptIdsList.size()>0){
			query.setParameterList("deptIdsList", deptIdsList);
		}
		if(sourceIdsList != null && sourceIdsList.size()>0)
			;
		return query.list();
	}
	
	public Long getTotalSchemes(List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList,Date sDate,Date eDate,Long searchLevlId,List<Long> searchLvlVals){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(distinct modal.govtScheme.govtSchemeId ) " );
		sb.append(" from FundSanction modal where modal.isDeleted='N' ");
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			sb.append(" and modal.financialYearId in (:financialYearIdsList)  " );
		}
		if(sDate != null && eDate != null){
			sb.append(" and date(modal.insertedTime) between  :sDate and :eDate " );
		}
		if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == 3l){
			sb.append(" and modal.locationAddress.district.districtId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == 4l){
			sb.append(" and modal.locationAddress.constituency.constituencyId in (:searchLvlVals) ");
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			sb.append(" and modal.department.departmentId in (:deptIdsList) ");
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
		if(searchLevlId != null && searchLevlId.longValue() > 0l){
			query.setParameterList("searchLvlVals", searchLvlVals);
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			query.setParameterList("deptIdsList", deptIdsList);
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
	
	public List<Object[]> getTotalFundAndCountDtls(List<Long> financialYrIdList,Long departmentId,Long sourceId,Date sDate,Date eDate,Long scopeId,String group,Long deptId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select " );
		if(group.equalsIgnoreCase("two")){
			sb.append(" modal.grantType.grantTypeId, modal.grantType.type, " );
		}
		sb.append(" count(modal.locationValue),sum(modal.sactionAmount) ");
		sb.append(" from FundSanction modal where modal.isDeleted='N' ");
		
		
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			sb.append(" and modal.financialYearId in (:financialYrIdList)  " );
		}
		if(sDate != null && eDate != null){
			sb.append(" and date(modal.insertedTime) between  :sDate and :eDate " );
		}
		
		if(scopeId != null && scopeId.longValue() > 0l ){
			sb.append(" and modal.locationScopeId = :locationScopeId  " );
		}
		if(deptId != null && deptId.longValue() > 0l ){
			sb.append(" and modal.department.departmentId = :deptId  " );
		}
		if(sourceId != null && sourceId.longValue() > 0l ){
			
		}
		sb.append(" group by ");
		if(group.equalsIgnoreCase("two")){
			sb.append(" modal.grantType.grantTypeId,modal.locationScopeId  " );
		}else{
			sb.append(" modal.locationScopeId  " );
		}
		
		Query query = getSession().createQuery(sb.toString());
		
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			query.setParameterList("financialYrIdList", financialYrIdList);
		}
		
		if(scopeId != null && scopeId.longValue() >0l ){
			query.setParameter("locationScopeId", scopeId);
		}
		if(deptId != null && deptId.longValue() > 0l ){
			query.setParameter("deptId", deptId);
		}
		return query.list();
	}
	@Override
	public List<Object[]> getTotalFundForScheme(List<Long> financialYrIdList,Long departmentId,Long sourceId,Long schemeId,Date sDate,Date eDate,Long deptId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(distinct modal.govtScheme.govtSchemeId),sum(modal.sactionAmount) " );
		
		sb.append(" from FundSanction modal where modal.isDeleted='N' ");
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			sb.append(" and modal.financialYearId in (:financialYrIdList)  " );
		}
		if(schemeId != null && schemeId.longValue() > 0L){
			sb.append(" and modal.govtScheme.govtSchemeId = :schemeId " );
		}
		if(deptId != null && deptId.longValue() > 0L){
			sb.append(" and modal.department.departmentId = :deptId  " );
		}
		if(sourceId != null && sourceId.longValue() > 0L){
			//;
		}
		if(sDate != null && eDate != null){
			sb.append(" and date(modal.insertedTime) between  :sDate and :eDate " );
		}
		Query query = getSession().createQuery(sb.toString());
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			query.setParameterList("financialYrIdList", financialYrIdList);
		}
		
		if(schemeId != null && schemeId.longValue() > 0L){
			query.setParameter("schemeId", schemeId);
		}
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(deptId != null && deptId.longValue() > 0L){
			query.setParameter("deptId", deptId);
		}
		return query.list();
	}
	@Override
	public List<Long> getLocationBlockLevelIds(Long locationId,Long locationLevelId,Long blockLevelId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct ");
		if(blockLevelId != null && blockLevelId.longValue() == 3L){
			sb.append(" district.districtId ");
		}else if(blockLevelId != null && blockLevelId.longValue() == 4L){
			sb.append(" constituency.constituencyId ");
		}
		sb.append(" from "
				+ " FundSanction fundSanction "
				+ " left outer join fundSanction.locationAddress locationAddress "
				+ " left outer join locationAddress.district district ");
		if(blockLevelId != null && blockLevelId.longValue() == 4L){
			sb.append(" left outer join locationAddress.constituency constituency ");
		}
		sb.append(" where fundSanction.isDeleted='N' and fundSanction.locationScopeId = :blockLevelId ");
		if(locationLevelId != null && locationLevelId.longValue() == 3L){
			sb.append(" and district.districtId = :locationId ");
		}else if(locationLevelId != null && locationLevelId.longValue() == 4L){
			sb.append(" and constituency.constituencyId = :locationId ");
		}
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("blockLevelId", blockLevelId);
		query.setParameter("locationId", locationId);
		return query.list();
	}
	@Override
	public List<Object[]> getAllDistrictByStateId(Long superLocationId,List<Long> financialYrIdList,Long deptId,Long sourceId,Long locationScopeId,Date sDate,Date eDate){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct district.districtId, district.districtName");
		
		sb.append(" from "
				+ " FundSanction fundSanction "
				+ " left outer join fundSanction.locationAddress locationAddress "
				+ " left outer join locationAddress.district district ");
		sb.append(" where fundSanction.isDeleted='N' and fundSanction.locationScopeId = :locationScopeId ");
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			sb.append(" and fundSanction.financialYearId in (:financialYrIdList)  " );
		}
		if(sDate != null && eDate != null){
			sb.append(" and date(fundSanction.insertedTime) between  :sDate and :eDate " );
		}
		if(deptId != null && deptId.longValue() > 0L){
			sb.append(" fundSanction.department.departmentId = :deptId ");
		}
		if(sourceId != null && sourceId.longValue() > 0L){
			
		}
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("locationScopeId", locationScopeId);
		if(deptId != null && deptId.longValue() > 0L){
			query.setParameter("deptId", deptId);
		}
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			query.setParameterList("financialYrIdList", financialYrIdList);
		}
		return query.list();
	}
	@Override
	public List<Object[]> getAllConstituencyByDistrictId(Long superLocationId,List<Long> financialYrIdList,Long deptId,Long sourceId,Long locationScopeId,Date sDate,Date eDate){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct constituency.constituencyId, constituency.name ");
		
		sb.append(" from "
				+ " FundSanction fundSanction "
				+ " left outer join fundSanction.locationAddress locationAddress "
				+ " left outer join locationAddress.district district "
				+ " left outer join locationAddress.constituency constituency ");
		sb.append(" where fundSanction.isDeleted='N' and fundSanction.locationScopeId = :locationScopeId "
				+ " and district.districtId = :superLocationId ");
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			sb.append(" and fundSanction.financialYearId in (:financialYrIdList)  " );
		}
		if(sDate != null && eDate != null){
			sb.append(" and date(fundSanction.insertedTime) between  :sDate and :eDate " );
		}
		if(deptId != null && deptId.longValue() > 0L){
			sb.append(" fundSanction.department.departmentId = :deptId ");
		}
		if(sourceId != null && sourceId.longValue() > 0L){
			
		}
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("locationScopeId", locationScopeId);
		query.setParameter("superLocationId", superLocationId);
		if(deptId != null && deptId.longValue() > 0L){
			query.setParameter("deptId", deptId);
		}
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			query.setParameterList("financialYrIdList", financialYrIdList);
		}
		return query.list();
	}
}
