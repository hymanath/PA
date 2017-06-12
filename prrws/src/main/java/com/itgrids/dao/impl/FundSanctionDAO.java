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
import com.itgrids.model.FundSanctionLocation;
import com.itgrids.utils.IConstants;

@Repository
public class FundSanctionDAO extends GenericDaoHibernate<FundSanction, Long> implements IFundSanctionDAO {

	@Autowired
	SessionFactory sessionFactory;

	public FundSanctionDAO() {
		super(FundSanction.class);

	}
	
	/*
	 * Date : 06/06/2017
	 * Author :Srishailam Pittala
	 * @description : to get financial year  and scheme wise funds transaction details
	 */
	
	public List<Object[]> getFinancialYearWiseScheameDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,List<Long> schemeIdsList,Date startDate,Date endDate,Long searchScopeId,List<Long> searchScopeValuesList){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct  model.fundSanction.govtSchemeId, model.fundSanction.govtScheme.schemeName, "
				+ " model.fundSanction.financialYear.financialYearId, model.fundSanction.financialYear.yearDesc, count(model.fundSanction.govtSchemeId), sum(model.fundSanction.sactionAmount), "
				+ " model.locationScopeId, model.locationValue, model.locationAddress.districtId, model.locationAddress.district.districtName, "
				+ " model.locationAddress.constituencyId, model.locationAddress.constituency.name ");
		queryStr.append(" from FundSanctionLocation model ");
		queryStr.append(" where model.isDeleted ='N' ");
		if(financialYearIdsList != null && financialYearIdsList.size()>0)
			queryStr.append(" and model.fundSanction.financialYearId in (:financialYearIdsList) ");
		if(deptIdsList != null && deptIdsList.size()>0)
			queryStr.append(" and model.fundSanction.department.departmentId in (:deptIdsList) ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			queryStr.append("  ");
		if(schemeIdsList != null && schemeIdsList.size()>0)
			queryStr.append(" and model.fundSanction.govtSchemeId in (:schemeIdsList) ");
		if(startDate != null && endDate != null)
			queryStr.append(" and ( date(model.fundSanction.updatedTime) between :startDate and :endDate)  ");
		if(searchScopeId != null && searchScopeId.longValue()>0L)
			queryStr.append(" and model.locationScopeId =:searchScopeId ");
		if(searchScopeValuesList != null && searchScopeValuesList.size()>0)
			queryStr.append(" and model.locationValue in (:searchScopeValuesList) ");
		
		queryStr.append(" group by model.locationScopeId,model.locationValue,model.fundSanction.financialYearId,model.fundSanction.govtSchemeId ");
		queryStr.append(" order by model.locationScopeId,model.locationValue,model.fundSanction.financialYearId,model.fundSanction.govtSchemeId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		
		if(financialYearIdsList != null && financialYearIdsList.size()>0)
			query.setParameterList("financialYearIdsList", financialYearIdsList);
		if(deptIdsList != null && deptIdsList.size()>0)
			query.setParameterList("deptIdsList", deptIdsList);
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
	/*
	 * Date : 08/06/2017
	 * Author :Srishailam Pittala
	 * @description : to get financial year ,  Department wise and scheme wise funds transaction details
	 */
	public List<Object[]> getFinancialYearWiseDeptsWiseScheameDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,List<Long> schemeIdsList,Date startDate,Date endDate,Long searchScopeId,List<Long> searchScopeValuesList){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct  model.fundSanction.govtSchemeId, model.fundSanction.govtScheme.schemeName, "
				+ " model.fundSanction.financialYear.financialYearId, model.fundSanction.financialYear.yearDesc, count(model.fundSanction.govtSchemeId), sum(model.fundSanction.sactionAmount), "
				+ " model.locationScopeId, model.locationValue, model.locationAddress.districtId, model.locationAddress.district.districtName, "
				+ " model.locationAddress.constituencyId, model.locationAddress.constituency.name, model.fundSanction.department.departmentId, model.fundSanction.department.departmentName ");
		queryStr.append(" from FundSanctionLocation model ");
		queryStr.append(" where model.isDeleted ='N' ");
		if(financialYearIdsList != null && financialYearIdsList.size()>0)
			queryStr.append(" and model.fundSanction.financialYearId in (:financialYearIdsList) ");
		if(deptIdsList != null && deptIdsList.size()>0)
			queryStr.append(" and model.fundSanction.department.departmentId in (:deptIdsList) ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			queryStr.append("  ");
		if(schemeIdsList != null && schemeIdsList.size()>0)
			queryStr.append(" and model.fundSanction.govtSchemeId in (:schemeIdsList) ");
		if(startDate != null && endDate != null)
			queryStr.append(" and ( date(model.fundSanction.updatedTime) between :startDate and :endDate)  ");
		if(searchScopeId != null && searchScopeId.longValue()>0L)
			queryStr.append(" and model.locationScopeId =:searchScopeId ");
		if(searchScopeValuesList != null && searchScopeValuesList.size()>0)
			queryStr.append(" and model.locationValue in (:searchScopeValuesList) ");
		
		queryStr.append(" group by model.locationScopeId,model.locationValue,model.fundSanction.financialYearId,model.fundSanction.department.departmentId,model.fundSanction.govtSchemeId ");
		queryStr.append(" order by model.locationScopeId,model.locationValue,model.fundSanction.financialYearId,model.fundSanction.department.departmentId,model.fundSanction.govtSchemeId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		
		if(financialYearIdsList != null && financialYearIdsList.size()>0)
			query.setParameterList("financialYearIdsList", financialYearIdsList);
		if(deptIdsList != null && deptIdsList.size()>0)
			query.setParameterList("deptIdsList", deptIdsList);
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
	
	
	
	/**
	 * @author Srishailam Pittala <srishailam.pittala@itgrids.com>
	 * @date  6th June,2017
	 * @description  to get financial year wise fund details 
	 * @param public List<Object[]> getFinancialYearWiseFundDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList,List<Long> schemeIdsList,Long searchScopeId)
	 * @return List<Object[]>
	 */
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getFinancialYearWiseFundDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,List<Long> schemeIdsList,Long searchScopeId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct  model.financialYear.financialYearId,model.financialYear.yearDesc,  sum(model.sactionAmount),model.locationScopeId ");
		if(searchScopeId != null && searchScopeId.longValue()>0L){
			if(searchScopeId.longValue() ==3L)
				queryStr.append(" , model.locationAddress.districtId, model.locationAddress.district.districtName ");
			else if(searchScopeId.longValue() ==4L)
				queryStr.append(" , model.locationAddress.constituencyId, model.locationAddress.constituency.name ");
		}
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
		if(searchScopeId != null && searchScopeId.longValue()>0L)
			queryStr.append(" and model.locationScopeId =:searchScopeId ");

		queryStr.append(" group by model.locationScopeId,model.financialYearId ");
		if(searchScopeId != null && searchScopeId.longValue()>0L){
			if(searchScopeId.longValue() ==3L)
				queryStr.append(" , model.locationAddress.districtId ");
			else if(searchScopeId.longValue() ==4L)
				queryStr.append(" , model.locationAddress.constituencyId ");
		}
		
		queryStr.append(" order by model.locationScopeId,model.financialYearId ");
		if(searchScopeId != null && searchScopeId.longValue()>0L){
			if(searchScopeId.longValue() ==3L)
				queryStr.append(" , model.locationAddress.districtId ");
			else if(searchScopeId.longValue() ==4L)
				queryStr.append(" , model.locationAddress.constituencyId ");
		}
		
		Query query = getSession().createQuery(queryStr.toString());

		if(financialYearIdsList != null && financialYearIdsList.size()>0)
			query.setParameterList("financialYearIdsList", financialYearIdsList);
		if(deptIdsList != null && deptIdsList.size()>0)
			;
		if(sourceIdsList != null && sourceIdsList.size()>0)
			;
		if(schemeIdsList != null && schemeIdsList.size()>0)
			query.setParameterList("schemeIdsList", schemeIdsList);
		if(searchScopeId != null && searchScopeId.longValue()>0L)
			query.setParameter("searchScopeId", searchScopeId);

		return query.list();
	}
	
	public List<Object[]> getLocationsCountDetails(Long locatioinTypeId,List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList,Long searchLevlId,List<Long> searchLvlVals){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.locationScopeId ");
		if(locatioinTypeId != null && locatioinTypeId.longValue() >0l && locatioinTypeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			queryStr.append("  ,count( distinct model.locationAddress.district.districtId ) " );
		}else if(locatioinTypeId != null && locatioinTypeId.longValue() >0l && locatioinTypeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			queryStr.append("  ,count(distinct model.locationAddress.constituency.constituencyId)  " );
		}else if(locatioinTypeId != null && locatioinTypeId.longValue() >0l && locatioinTypeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			queryStr.append(" ,count(distinct model.locationAddress.tehsil.tehsilId ) " );
		}else if(locatioinTypeId != null && locatioinTypeId.longValue() >0l && locatioinTypeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			queryStr.append("  ,count(distinct model.locationAddress.panchayat.panchayatId ) " );
		}
		//queryStr.append(" ,count( distinct model.locationValue) ");
		queryStr.append(" ,sum(model.fundSanction.sactionAmount) from   ");
		queryStr.append(" FundSanctionLocation model  ");
		queryStr.append(" where model.isDeleted ='N' and model.locationAddress.district.stateId = 1 and model.fundSanction.isDeleted = 'N' ");
		/*if(locatioinTypeId != null && locatioinTypeId.longValue()>0L)
			queryStr.append(" and model.locationScopeId = :locatioinTypeId ");*/
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			queryStr.append(" and model.fundSanction.financialYearId in (:financialYearIdsList)  " );
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			queryStr.append(" and model.fundSanction.department.departmentId in (:deptIdsList) ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			queryStr.append("  ");
		if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			queryStr.append(" and model.locationAddress.district.districtId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			queryStr.append(" and model.locationAddress.constituency.constituencyId in (:searchLvlVals) ");
		}
		//queryStr.append(" group by model.locationScopeId ");
		//queryStr.append(" order by model.locationScopeId ");
		Query query = getSession().createQuery(queryStr.toString());
		/*if(locatioinTypeId != null && locatioinTypeId.longValue()>0L)
			query.setParameter("locatioinTypeId", locatioinTypeId);*/
		
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
		Criteria criteria=getSession().createCriteria(FundSanctionLocation.class);
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.distinct(Projections.property("locationValue")));
		criteria.setProjection(projectionList);
		criteria.add(Restrictions.eq("locationScopeId", scopeId));
		return criteria.list();
	}
	@Override
	public List<Object[]> getLocationWiseAmount(Long levelId, List<Long> levelValues,Date fromDate,Date toDate,List<Long> financialYrIdList,List<Long> deptIdList,List<Long> sourceIdList){
		StringBuilder sb = new StringBuilder();  
		sb.append(" select "
				+ " fundSanctionLocation.fundSanction.financialYear.financialYearId "//0
				+ " ,fundSanctionLocation.fundSanction.financialYear.yearDesc "//1
				+ " ,fundSanctionLocation.locationValue "//2
				+ " ,count(fundSanctionLocation.locationValue) "//3
				+ " ,sum(fundSanctionLocation.fundSanction.sactionAmount) "//4
				+ " from "
				+ " FundSanctionLocation fundSanctionLocation "
				+ " where "
				+ " fundSanctionLocation.isDeleted = 'N' "
				+ " and fundSanctionLocation.fundSanction.isDeleted = 'N' "
				+ " and fundSanctionLocation.locationScopeId = :levelId "
				+ " and fundSanctionLocation.locationValue in (:levelValues) "
				+ " and fundSanctionLocation.fundSanction.insertedTime between :fromDate and :toDate ");
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.financialYear.financialYearId in (:financialYrIdList) ");
		}
		if(deptIdList !=null && deptIdList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.department.departmentId in (:deptIdList) ");
		}
		if(sourceIdList !=null && sourceIdList.size() > 0){
			
		}
		sb.append(" group by fundSanctionLocation.fundSanction.financialYear.financialYearId, fundSanctionLocation.locationValue order by fundSanctionLocation.fundSanction.financialYear.financialYearId ");
		Query query = getSession().createQuery(sb.toString());
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			query.setParameterList("financialYrIdList", financialYrIdList);
		}
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		query.setParameter("levelId", levelId);
		query.setParameterList("levelValues", levelValues);
		if(deptIdList !=null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		return query.list();
	}
	@Override
	public List<Object[]> getLocationInfo(Long levelId, List<Long> levelValues){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct fundSanctionLocation.locationValue ");
		if(levelId != null && levelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" , district.districtName ");
		}else if(levelId != null && levelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" , constituency.name ");
		}
		sb.append(" from FundSanctionLocation fundSanctionLocation "
				+ " left outer join fundSanctionLocation.locationAddress locationAddress "
				+ " left outer join locationAddress.district district "
				+ " left outer join locationAddress.constituency constituency "
				+ " where "
				+ " fundSanctionLocation.isDeleted = 'N' "
				+ " and fundSanctionLocation.locationScopeId = :levelId "
				+ " and fundSanctionLocation.locationValue in (:levelValues) ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("levelId", levelId);
		query.setParameterList("levelValues", levelValues);
		return query.list();
	}
	
	public List<Object[]> getLocationWiseFundHighAndLow(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,Date sDate,Date eDate,Long locationScopeId,String type,Long searchLevlId,List<Long> searchLvlVals){
		StringBuilder sb = new StringBuilder();
		sb.append(" select sum(modal.fundSanction.sactionAmount) " );
		if(locationScopeId != null && locationScopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID ){
			sb.append(" ,modal.locationAddress.district.districtId,modal.locationAddress.district.districtName " );
		}else if(locationScopeId != null && locationScopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID ){
			sb.append(" ,modal.locationAddress.constituency.constituencyId,modal.locationAddress.constituency.name " );
		}else if(locationScopeId != null && locationScopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID ){
			sb.append(" ,modal.locationAddress.tehsil.tehsilId,modal.locationAddress.tehsil.tehsilName " );
		}else if(locationScopeId != null && locationScopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID ){
			sb.append(" ,modal.locationAddress.panchayat.panchayatId,modal.locationAddress.panchayat.panchayatName " );
		}
		sb.append(" from FundSanctionLocation modal where modal.isDeleted='N' ");
		
		/*if(locationScopeId != null && locationScopeId.longValue() >0l ){
			sb.append(" and modal.locationScopeId = :locationScopeId  " );
		}*/
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			sb.append(" and modal.fundSanction.financialYearId in (:financialYearIdsList)  " );
		}
		
		if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" and modal.locationAddress.district.districtId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" and modal.locationAddress.constituency.constituencyId in (:searchLvlVals) ");
		}
		
		if(deptIdsList != null && deptIdsList.size()>0)
			sb.append(" and modal.fundSanction.department.departmentId in (:deptIdsList) ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			sb.append("  ");
		if(sDate != null && eDate != null){
			sb.append(" and (date(modal.fundSanction.insertedTime) between  :sDate and :eDate) " );
		}
		
		if(locationScopeId != null && locationScopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID ){
			sb.append(" group by modal.locationAddress.district.districtId " );
		}else if(locationScopeId != null && locationScopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID ){
			sb.append(" group by modal.locationAddress.constituency.constituencyId " );
		}else if(locationScopeId != null && locationScopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID ){
			sb.append(" group by modal.locationAddress.tehsil.tehsilId ");
		}else if(locationScopeId != null && locationScopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID ){
			sb.append(" group by modal.locationAddress.panchayat.panchayatId ");
		}
		if(type != null && type.equalsIgnoreCase("highest")){
			sb.append("   order by sum(modal.fundSanction.sactionAmount) desc ");
		}else if(type != null && type.equalsIgnoreCase("lowest")){
			sb.append("  order by sum(modal.fundSanction.sactionAmount) asc ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		query.setFirstResult(0);
		query.setMaxResults(2);
		/*if(locationScopeId != null && locationScopeId.longValue() >0l ){
			query.setParameter("locationScopeId", locationScopeId);
		}*/
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
		sb.append(" select sum(modal.fundSanction.sactionAmount) " );
		
		sb.append(" ,modal.fundSanction.govtScheme.govtSchemeId,modal.fundSanction.govtScheme.schemeName " );
		
		sb.append(" from FundSanctionLocation modal where modal.isDeleted='N' and modal.fundSanction.isDeleted = 'N' ");
		
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			sb.append(" and modal.fundSanction.financialYearId in (:financialYearIdsList)  " );
		}
		if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" and  modal.locationAddress.district.districtId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" and modal.locationAddress.constituency.constituencyId in (:searchLvlVals) ");
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			sb.append(" and modal.fundSanction.department.departmentId in (:deptIdsList) ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			sb.append("  ");
		if(sDate != null && eDate != null){
			sb.append(" and date(modal.fundSanction.insertedTime) between  :sDate and :eDate " );
		}
		if(type != null && type.equalsIgnoreCase("highest")){
			sb.append(" group by modal.fundSanction.govtScheme.govtSchemeId order by sum(modal.fundSanction.sactionAmount) desc ");
		}else if(type != null && type.equalsIgnoreCase("lowest")){
			sb.append(" group by modal.fundSanction.govtScheme.govtSchemeId order by sum(modal.fundSanction.sactionAmount) asc ");
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
		sb.append(" select sum(modal.fundSanction.sactionAmount) " );
		sb.append(" from FundSanctionLocation modal where modal.isDeleted='N' and modal.fundSanction.isDeleted = 'N' ");
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			sb.append(" and modal.fundSanction.financialYearId in (:financialYearIdsList)  " );
		}
		if(sDate != null && eDate != null){
			sb.append(" and date(modal.fundSanction.insertedTime) between  :sDate and :eDate " );
		}
		if(scopeId != null && scopeId.longValue() >0l ){
			sb.append(" and modal.locationScopeId = :locationScopeId  " );
		}
		
		if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" and modal.locationAddress.district.districtId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" and modal.locationAddress.constituency.constituencyId in (:searchLvlVals) ");
		}
		
		if(deptIdsList != null && deptIdsList.size()>0)
			sb.append(" and modal.fundSanction.department.departmentId in (:deptIdsList) ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			sb.append("  ");
		
		/*if(scopeId != null && scopeId.longValue() >0l && scopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" group by modal.locationAddress.district.districtId " );
		}else if(scopeId != null && scopeId.longValue() >0l && scopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" group by modal.locationAddress.constituency.constituencyId " );
		}else if(scopeId != null && scopeId.longValue() >0l && scopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			sb.append(" group by modal.locationAddress.tehsil.tehsilId " );
		}else if(scopeId != null && scopeId.longValue() >0l && scopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			sb.append(" group by modal.locationAddress.panchayat.panchayatId " );
		}*/
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
		sb.append(" select sum(modal.fundSanction.sactionAmount) " );
		sb.append(" ,modal.fundSanction.grantType.grantTypeId,modal.fundSanction.grantType.type " );
		sb.append(" from FundSanctionLocation modal where modal.isDeleted='N' and modal.fundSanction.isDeleted = 'N'  ");
		/*if(locationScopeId != null && locationScopeId.longValue() >0l ){
			sb.append(" and modal.locationScopeId = :locationScopeId  " );
		}*/
		
		if(locationScopeId != null && locationScopeId.longValue() >0l && locationScopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID && locationVal != null && locationVal.longValue() >0l){
			sb.append(" and  modal.locationAddress.district.districtId  = :locationVal " );
		}else if(locationScopeId != null && locationScopeId.longValue() >0l && locationScopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID && locationVal != null && locationVal.longValue() >0l){
			sb.append(" and modal.locationAddress.constituency.constituencyId = :locationVal  " );
		}else if(locationScopeId != null && locationScopeId.longValue() >0l && locationScopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID && locationVal != null && locationVal.longValue() >0l){
			sb.append(" and modal.locationAddress.tehsil.tehsilId = :locationVal " );
		}else if(locationScopeId != null && locationScopeId.longValue() >0l && locationScopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID && locationVal != null && locationVal.longValue() >0l){
			sb.append(" and  modal.locationAddress.panchayat.panchayatId = :locationVal " );
		}
		/*if(locationVal != null && locationVal.longValue() >0l ){
			sb.append(" and modal.locationValue = :locationVal  " );
		}*/
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			sb.append(" and modal.fundSanction.financialYearId in (:financialYearIdsList)  " );
		}
		if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" and modal.locationAddress.district.districtId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" and modal.locationAddress.constituency.constituencyId in (:searchLvlVals) ");
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			sb.append(" and modal.fundSanction.department.departmentId in (:deptIdsList) ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			sb.append("  ");
		if(sDate != null && eDate != null){
			sb.append(" and (date(modal.fundSanction.insertedTime) between  :sDate and :eDate) " );
		}
		sb.append(" group by modal.fundSanction.grantType.grantTypeId  ");
		/*if(locationScopeId != null && locationScopeId.longValue() >0l && locationScopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" , modal.locationAddress.district.districtId " );
		}else if(locationScopeId != null && locationScopeId.longValue() >0l && locationScopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" , modal.locationAddress.constituency.constituencyId " );
		}else if(locationScopeId != null && locationScopeId.longValue() >0l && locationScopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			sb.append(" , modal.locationAddress.tehsil.tehsilId " );
		}else if(locationScopeId != null && locationScopeId.longValue() >0l && locationScopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			sb.append(" , modal.locationAddress.panchayat.panchayatId " );
		}*/
		
		Query query = getSession().createQuery(sb.toString());
		/*if(locationScopeId != null && locationScopeId.longValue() >0l ){
			query.setParameter("locationScopeId", locationScopeId);
		}*/
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
		sb.append(" select count(distinct modal.fundSanction.govtScheme.govtSchemeId ) " );
		sb.append(" from FundSanctionLocation modal where modal.isDeleted='N' and modal.fundSanction.isDeleted = 'N'  ");
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			sb.append(" and modal.fundSanction.financialYearId in (:financialYearIdsList)  " );
		}
		if(sDate != null && eDate != null){
			sb.append(" and date(modal.fundSanction.insertedTime) between  :sDate and :eDate " );
		}
		if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" and modal.locationAddress.district.districtId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" and modal.locationAddress.constituency.constituencyId in (:searchLvlVals) ");
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			sb.append(" and modal.fundSanction.department.departmentId in (:deptIdsList) ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			sb.append("  ");
		sb.append(" group by modal.fundSanction.govtScheme.govtSchemeId ");
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
	@Override
	public List<Object[]> getTotalFundAndCountDtls(List<Long> financialYrIdList,List<Long> departmentIdList,List<Long> sourceIdList,Date sDate,Date eDate,Long scopeId,String group){
		StringBuilder sb = new StringBuilder();
		sb.append(" select " );
		if(group.equalsIgnoreCase("two")){
			sb.append(" fundSanctionLocation.fundSanction.grantType.grantTypeId, fundSanctionLocation.fundSanction.grantType.type, " );
		}
		sb.append(" count(fundSanctionLocation.locationValue),sum(fundSanctionLocation.fundSanction.sactionAmount) ");
		sb.append(" from FundSanctionLocation fundSanctionLocation where fundSanctionLocation.isDeleted='N' ");
		sb.append(" and fundSanctionLocation.fundSanction.isDeleted='N' ");
		
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.financialYearId in (:financialYrIdList)  " );
		}
		if(sDate != null && eDate != null){
			sb.append(" and date(fundSanctionLocation.fundSanction.insertedTime) between  :sDate and :eDate " );
		}
		
		if(scopeId != null && scopeId.longValue() > 0l ){
			sb.append(" and fundSanctionLocation.locationScopeId = :locationScopeId  " );
		}
		if(departmentIdList != null && departmentIdList.size() > 0 ){
			sb.append(" and fundSanctionLocation.fundSanction.department.departmentId in (:departmentIdList)  " );
		}
		if(sourceIdList != null && sourceIdList.size() > 0 ){
			
		}
		sb.append(" group by ");
		if(group.equalsIgnoreCase("two")){
			sb.append(" fundSanctionLocation.fundSanction.grantType.grantTypeId,fundSanctionLocation.locationScopeId  " );
		}else{
			sb.append(" fundSanctionLocation.locationScopeId  " );
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
		if(departmentIdList != null && departmentIdList.size() > 0 ){
			query.setParameterList("departmentIdList", departmentIdList);
		}
		return query.list();
	}
	@Override
	public List<Object[]> getTotalFundForScheme(List<Long> financialYrIdList,List<Long> departmentIdList,List<Long> sourceIdLIst,List<Long> schemeIdList,Date sDate,Date eDate){
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(distinct fundSanctionLocation.fundSanction.govtScheme.govtSchemeId),sum(fundSanctionLocation.fundSanction.sactionAmount) " );
		
		sb.append(" from FundSanctionLocation fundSanctionLocation where fundSanctionLocation.isDeleted='N' ");
		sb.append(" and fundSanctionLocation.fundSanction.isDeleted='N' ");
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.financialYearId in (:financialYrIdList)  " );
		}
		if(schemeIdList != null && schemeIdList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.govtScheme.govtSchemeId in (:schemeIdList) " );
		}
		if(departmentIdList != null && departmentIdList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.department.departmentId in (:departmentIdList)  " );
		}
		if(sourceIdLIst != null && sourceIdLIst.size() > 0){
			//;
		}
		if(sDate != null && eDate != null){
			sb.append(" and date(fundSanctionLocation.fundSanction.insertedTime) between  :sDate and :eDate " );
		}
		Query query = getSession().createQuery(sb.toString());
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			query.setParameterList("financialYrIdList", financialYrIdList);
		}
		
		if(schemeIdList != null && schemeIdList.size() > 0){
			query.setParameter("schemeIdList", schemeIdList);
		}
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(departmentIdList != null && departmentIdList.size() > 0){
			query.setParameterList("departmentIdList", departmentIdList);
		}
		return query.list();
	}
	@Override
	public List<Long> getLocationBlockLevelIds(Long locationId,Long locationLevelId,Long blockLevelId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct ");
		if(blockLevelId != null && blockLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" district.districtId ");
		}else if(blockLevelId != null && blockLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" constituency.constituencyId ");
		}
		sb.append(" from "
				+ " FundSanctionLocation fundSanctionLocation "
				+ " left outer join fundSanctionLocation.fundSanction fundSanction "
				+ " left outer join fundSanctionLocation.locationAddress locationAddress "
				+ " left outer join locationAddress.district district ");
		if(blockLevelId != null && blockLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" left outer join locationAddress.constituency constituency ");
		}
		sb.append(" where fundSanctionLocation.isDeleted='N' and fundSanctionLocation.fundSanction.isDeleted='N' and fundSanctionLocation.locationScopeId = :blockLevelId ");
		if(locationLevelId != null && locationLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" and district.districtId = :locationId ");
		}else if(locationLevelId != null && locationLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" and constituency.constituencyId = :locationId ");
		}
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("blockLevelId", blockLevelId);
		query.setParameter("locationId", locationId);
		return query.list();
	}
	@Override
	public List<Object[]> getAllDistrictByStateId(Long superLocationId,List<Long> financialYrIdList,List<Long> deptIdList,List<Long> sourceIdList,Long locationScopeId,Date sDate,Date eDate){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct district.districtId, district.districtName");
		
		sb.append(" from "
				+ " FundSanctionLocation fundSanctionLocation "
				+ " left outer join fundSanctionLocation.locationAddress locationAddress "
				+ " left outer join locationAddress.district district ");
		sb.append(" where fundSanctionLocation.isDeleted='N' and fundSanctionLocation.locationScopeId = :locationScopeId ");
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.financialYearId in (:financialYrIdList)  " );
		}
		if(sDate != null && eDate != null){
			sb.append(" and date(fundSanctionLocation.fundSanction.insertedTime) between  :sDate and :eDate " );
		}
		if(deptIdList != null && deptIdList.size() > 0){
			sb.append(" fundSanctionLocation.fundSanction.department.departmentId in (:deptIdList) ");
		}
		if(sourceIdList != null && sourceIdList.size() > 0){
			
		}
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("locationScopeId", locationScopeId);
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
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
	public List<Object[]> getAllConstituencyByDistrictId(Long superLocationId,List<Long> financialYrIdList,List<Long> deptIdList,List<Long> sourceIdList,Long locationScopeId,Date sDate,Date eDate){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct constituency.constituencyId, constituency.name ");
		
		sb.append(" from "
				+ " FundSanctionLocation fundSanctionLocation "
				+ " left outer join fundSanctionLocation.locationAddress locationAddress "
				+ " left outer join locationAddress.district district "
				+ " left outer join locationAddress.constituency constituency ");
		sb.append(" where fundSanctionLocation.isDeleted='N' and fundSanctionLocation.locationScopeId = :locationScopeId "
				+ " and district.districtId = :superLocationId ");
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.financialYearId in (:financialYrIdList)  " );
		}
		if(sDate != null && eDate != null){
			sb.append(" and date(fundSanctionLocation.fundSanction.insertedTime) between  :sDate and :eDate " );
		}
		if(deptIdList != null && deptIdList.size() > 0){
			sb.append(" fundSanctionLocation.fundSanction.department.departmentId in (:deptIdList) ");
		}
		if(sourceIdList != null && sourceIdList.size() > 0){
			
		}
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("locationScopeId", locationScopeId);
		query.setParameter("superLocationId", superLocationId);
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
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
