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
import com.itgrids.dto.InputVO;
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
			List<Long> sourceIdsList,List<Long> schemeIdsList,Date startDate,Date endDate,Long searchScopeId,List<Long> searchScopeValuesList,Long searchLevelId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct  "
					  + " model.fundSanction.govtSchemeId, "//0
					  + " model.fundSanction.govtScheme.schemeName, "//1
					  + " model.fundSanction.financialYear.financialYearId, "//2
					  + " model.fundSanction.financialYear.yearDesc, "//3
					  + " count(model.fundSanction.govtSchemeId), "//4
					  + " sum(model.fundSanction.sactionAmount), ");//5
		if(searchLevelId != null && searchLevelId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
			queryStr.append(" model.locationAddress.district.stateId, "//6
					  + " 'Andhra Pradesh' ");//7
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			queryStr.append(" model.locationAddress.district.districtId, "//6
					  + " model.locationAddress.district.districtName ");//7
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			queryStr.append(" model.locationAddress.constituency.constituencyId, "//6
					  + " model.locationAddress.constituency.name ");//7
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			queryStr.append(" model.locationAddress.tehsil.tehsilId, "//6
					  + " model.locationAddress.tehsil.tehsilName ");//7
		}
		else if(searchLevelId != null && searchLevelId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			queryStr.append(" model.locationAddress.panchayat.panchayatId, "//6
					  + " model.locationAddress.panchayat.panchayatName ");//7
		}
		
		queryStr.append(" from FundSanctionLocation model ");
		queryStr.append(" where model.isDeleted ='N' ");
		
		if(searchScopeValuesList != null && searchScopeValuesList.size()>0){
			if(searchScopeId != null && searchScopeId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
				queryStr.append(" and model.locationAddress.district.stateId in (:searchScopeValuesList) ");//7
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
				queryStr.append("  and model.locationAddress.district.districtId in (:searchScopeValuesList) ");//7
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
				queryStr.append(" and  model.locationAddress.constituency.constituencyId in (:searchScopeValuesList) ");//7
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
				queryStr.append("  and model.locationAddress.tehsil.tehsilId in (:searchScopeValuesList) ");//7
			}
			else if(searchScopeId != null && searchScopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
				queryStr.append(" and  model.locationAddress.panchayat.panchayatId in (:searchScopeValuesList) ");//7
			}
		}
		
		
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
		
		queryStr.append(" group by ");
		if(searchLevelId != null && searchLevelId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
			queryStr.append(" model.locationAddress.district.stateId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			queryStr.append(" model.locationAddress.district.districtId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			queryStr.append(" model.locationAddress.constituency.constituencyId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			queryStr.append(" model.locationAddress.tehsil.tehsilId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			queryStr.append(" model.locationAddress.panchayat.panchayatId, ");
		}
		queryStr.append(" model.fundSanction.financialYearId,model.fundSanction.govtSchemeId ");
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
			List<Long> sourceIdsList,List<Long> schemeIdsList,Date startDate,Date endDate,Long searchScopeId,List<Long> searchScopeValuesList,Long searchLevelId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct  "
				+ " model.fundSanction.govtSchemeId, "//0
				+ " model.fundSanction.govtScheme.schemeName, "//1
				+ " model.fundSanction.financialYear.financialYearId, "//2
				+ " model.fundSanction.financialYear.yearDesc, "//3
				+ " count(model.fundSanction.govtSchemeId), "//4
				+ " sum(model.fundSanction.sactionAmount), ");//5
		if(searchLevelId != null && searchLevelId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
			queryStr.append(" model.locationAddress.district.stateId, "//6
					  + " 'Andhra Pradesh' ");//7
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			queryStr.append(" model.locationAddress.district.districtId, "//6
					  + " model.locationAddress.district.districtName ");//7
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			queryStr.append(" model.locationAddress.constituency.constituencyId, "//6
					  + " model.locationAddress.constituency.name ");//7
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			queryStr.append(" model.locationAddress.tehsil.tehsilId, "//6
					  + " model.locationAddress.tehsil.tehsilName ");//7
		}
		else if(searchLevelId != null && searchLevelId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			queryStr.append(" model.locationAddress.panchayat.panchayatId, "//6
					  + " model.locationAddress.panchayat.panchayatName ");//7
		}
		
		queryStr.append(" ,model.fundSanction.department.departmentId, "//8
				+ " model.fundSanction.department.departmentName ");//9
		queryStr.append(" from FundSanctionLocation model ");
		queryStr.append(" where model.isDeleted ='N' ");
		
		if(searchScopeValuesList != null && searchScopeValuesList.size()>0){
			if(searchScopeId != null && searchScopeId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
				queryStr.append("  and  model.locationAddress.district.stateId in (:searchScopeValuesList) ");//7
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
				queryStr.append(" and  model.locationAddress.district.districtId in (:searchScopeValuesList) ");//7
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
				queryStr.append("  and model.locationAddress.constituency.constituencyId in (:searchScopeValuesList) ");//7
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
				queryStr.append("  and model.locationAddress.tehsil.tehsilId in (:searchScopeValuesList) ");//7
			}
			else if(searchScopeId != null && searchScopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
				queryStr.append("  and model.locationAddress.panchayat.panchayatId in (:searchScopeValuesList) ");//7
			}
		}
		
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
		/*if(searchScopeId != null && searchScopeId.longValue()>0L)
			queryStr.append(" and model.locationScopeId =:searchScopeId ");
		if(searchScopeValuesList != null && searchScopeValuesList.size()>0)
			queryStr.append(" and model.locationValue in (:searchScopeValuesList) ");*/
		
		/*queryStr.append(" group by model.locationScopeId,model.locationValue,model.fundSanction.financialYearId,model.fundSanction.department.departmentId,model.fundSanction.govtSchemeId ");
		queryStr.append(" order by model.locationScopeId,model.locationValue,model.fundSanction.financialYearId,model.fundSanction.department.departmentId,model.fundSanction.govtSchemeId ");
		*/
		queryStr.append(" group by ");
		if(searchLevelId != null && searchLevelId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
			queryStr.append(" model.locationAddress.district.stateId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			queryStr.append(" model.locationAddress.district.districtId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			queryStr.append(" model.locationAddress.constituency.constituencyId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			queryStr.append(" model.locationAddress.tehsil.tehsilId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			queryStr.append(" model.locationAddress.panchayat.panchayatId, ");
		}
		
		queryStr.append(" model.fundSanction.financialYearId,model.fundSanction.department.departmentId,model.fundSanction.govtSchemeId ");
		
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
	
	public List<Object[]> getLocationsCountDetails(Long locatioinTypeId,List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList
			,Long searchLevlId,List<Long> searchLvlVals,Date fromDate,Date toDate){
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
		if(fromDate != null && toDate != null)
			queryStr.append(" and ( date(model.fundSanction.updatedTime) between :startDate and :endDate)  ");
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
		if(fromDate != null && toDate != null){
			query.setDate("startDate", fromDate);
			query.setDate("endDate", toDate);
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
	public List<Object[]> getLocationWiseAmount(Long BlocklevelId,Long locationId,Long locationLevelId ,Date fromDate,Date toDate,List<Long> financialYrIdList,List<Long> deptIdList,List<Long> sourceIdList){
		StringBuilder sb = new StringBuilder();  
		sb.append(" select "
				+ " fundSanctionLocation.fundSanction.financialYear.financialYearId "//0
				+ " ,fundSanctionLocation.fundSanction.financialYear.yearDesc ");//1
		if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.district.stateId "//2
					+ " ,'Andhra Pradesh' "//3
					+ " ,count(fundSanctionLocation.locationAddress.district.stateId) ");//4
		}else if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.district.districtId "//2
					+ " ,fundSanctionLocation.locationAddress.district.districtName "//3
					+ " ,count(fundSanctionLocation.locationAddress.district.districtId) ");//4
		}else if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.constituency.constituencyId "//2
					+ " ,fundSanctionLocation.locationAddress.constituency.name"//3
					+ " ,count(fundSanctionLocation.locationAddress.constituency.constituencyId) ");//4
		}else if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.tehsil.tehsilId "//2
					+ " ,fundSanctionLocation.locationAddress.tehsil.tehsilName"//3
					+ " ,count(fundSanctionLocation.locationAddress.tehsil.tehsilId) ");//4
		}else if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.panchayat.panchayatId "//2
					+ " ,fundSanctionLocation.locationAddress.panchayat.panchayatName"//3
					+ " ,count(fundSanctionLocation.locationAddress.panchayat.panchayatId) ");//4
		}
		
		sb.append(" ,sum(fundSanctionLocation.fundSanction.sactionAmount) "//5
				+ " from "
				+ " FundSanctionLocation fundSanctionLocation "
				+ " where "
				+ " fundSanctionLocation.isDeleted = 'N' "
				+ " and fundSanctionLocation.fundSanction.isDeleted = 'N' "
				+ " and fundSanctionLocation.fundSanction.insertedTime between :fromDate and :toDate ");
		if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID ){
			if(locationLevelId != null && locationLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID && locationId != null && locationId.longValue() > 0L){
				sb.append(" and fundSanctionLocation.locationAddress.district.districtId = :locationId ");
			}
		}else if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			if(locationLevelId != null && locationLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID && locationId != null && locationId.longValue() > 0L){
				sb.append(" and fundSanctionLocation.locationAddress.district.districtId = :locationId ");
			}else if(locationLevelId != null && locationLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID && locationId != null && locationId.longValue() > 0L){
				sb.append(" and fundSanctionLocation.locationAddress.constituency.constituencyId = :locationId ");
			}
		}else if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			if(locationLevelId != null && locationLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID && locationId != null && locationId.longValue() > 0L){
				sb.append(" and fundSanctionLocation.locationAddress.district.districtId = :locationId ");
			}else if(locationLevelId != null && locationLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID && locationId != null && locationId.longValue() > 0L){
				sb.append(" and fundSanctionLocation.locationAddress.constituency.constituencyId = :locationId ");
			}else if(locationLevelId != null && locationLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID && locationId != null && locationId.longValue() > 0L){
				sb.append(" and fundSanctionLocation.locationAddress.tehsil.tehsilId = :locationId ");
			}
		}else if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			if(locationLevelId != null && locationLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID && locationId != null && locationId.longValue() > 0L){
				sb.append(" and fundSanctionLocation.locationAddress.district.districtId = :locationId ");
			}else if(locationLevelId != null && locationLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID && locationId != null && locationId.longValue() > 0L){
				sb.append(" and fundSanctionLocation.locationAddress.constituency.constituencyId = :locationId ");
			}else if(locationLevelId != null && locationLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID && locationId != null && locationId.longValue() > 0L){
				sb.append(" and fundSanctionLocation.locationAddress.tehsil.tehsilId = :locationId ");
			}else if(locationLevelId != null && locationLevelId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID && locationId != null && locationId.longValue() > 0L){
				sb.append(" and fundSanctionLocation.locationAddress.panchayat.panchayatId = :locationId ");
			}
		}
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.financialYear.financialYearId in (:financialYrIdList) ");
		}
		if(deptIdList !=null && deptIdList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.department.departmentId in (:deptIdList) ");
		}
		if(sourceIdList !=null && sourceIdList.size() > 0){
			
		}
		sb.append(" group by fundSanctionLocation.fundSanction.financialYear.financialYearId ");
		if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.district.stateId ");
		}else if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.district.districtId ");
		}else if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.constituency.constituencyId ");
		}else if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.tehsil.tehsilId ");
		}else if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.panchayat.panchayatId ");
		}
		sb.append(" order by fundSanctionLocation.fundSanction.financialYear.financialYearId ");
		Query query = getSession().createQuery(sb.toString());
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			query.setParameterList("financialYrIdList", financialYrIdList);
		}
		if(locationId != null && locationId.longValue() > 0L){
			query.setParameter("locationId", locationId);
		}
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		if(deptIdList !=null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
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
		sb.append(" from FundSanctionLocation modal where modal.isDeleted='N' and modal.fundSanction.isDeleted = 'N' " +
				" and modal.locationAddress.state.stateId=1");
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			sb.append(" and modal.fundSanction.financialYearId in (:financialYearIdsList)  " );
		}
		if(sDate != null && eDate != null){
			sb.append(" and date(modal.fundSanction.insertedTime) between  :sDate and :eDate " );
		}
		/*if(scopeId != null && scopeId.longValue() >0l ){
			sb.append(" and modal.locationScopeId = :locationScopeId  " );
		}*/
		
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
		
		/*if(scopeId != null && scopeId.longValue() >0l ){
			query.setParameter("locationScopeId", scopeId);
		}*/
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
		sb.append(" from FundSanctionLocation modal where modal.isDeleted='N' and modal.fundSanction.isDeleted = 'N' " +
				" and modal.locationAddress.state.stateId=1  ");
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
		
		if(scopeId != null && scopeId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
			sb.append(" count(distinct fundSanctionLocation.locationAddress.district.stateId) ");
		}else if(scopeId != null && scopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" count(distinct fundSanctionLocation.locationAddress.district.districtId) ");
		}else if(scopeId != null && scopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" count(distinct fundSanctionLocation.locationAddress.constituency.constituencyId) ");
		}else if(scopeId != null && scopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			sb.append(" count(distinct fundSanctionLocation.locationAddress.tehsil.tehsilId) ");
		}else if(scopeId != null && scopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			sb.append(" count(distinct fundSanctionLocation.locationAddress.panchayat.panchayatId) ");
		}
		
		sb.append(" ,sum(fundSanctionLocation.fundSanction.sactionAmount) ");
		sb.append(" from FundSanctionLocation fundSanctionLocation where fundSanctionLocation.isDeleted='N' ");
		sb.append(" and fundSanctionLocation.fundSanction.isDeleted='N' ");
		if(scopeId != null && scopeId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
			sb.append(" and fundSanctionLocation.locationAddress.district.districtId in ("+IConstants.TOTAL_AT_DISTRICT_IDS+")) ");
		}
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.financialYearId in (:financialYrIdList)  " );
		}
		if(sDate != null && eDate != null){
			sb.append(" and date(fundSanctionLocation.fundSanction.insertedTime) between  :sDate and :eDate " );
		}
		if(departmentIdList != null && departmentIdList.size() > 0 ){
			sb.append(" and fundSanctionLocation.fundSanction.department.departmentId in (:departmentIdList)  " );
		}
		if(sourceIdList != null && sourceIdList.size() > 0 ){
			
		}
		sb.append(" group by ");
		if(group.equalsIgnoreCase("two")){
			sb.append(" fundSanctionLocation.fundSanction.grantType.grantTypeId ");
		}else{
			if(scopeId != null && scopeId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
				sb.append(" fundSanctionLocation.locationAddress.district.stateId ");
			}else if(scopeId != null && scopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
				sb.append(" fundSanctionLocation.locationAddress.district.districtId ");
			}else if(scopeId != null && scopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" fundSanctionLocation.locationAddress.constituency.constituencyId ");
			}else if(scopeId != null && scopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
				sb.append(" fundSanctionLocation.locationAddress.tehsil.tehsilId ");
			}else if(scopeId != null && scopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
				sb.append(" fundSanctionLocation.locationAddress.panchayat.panchayatId ");
			}
		}
		
	
		
		Query query = getSession().createQuery(sb.toString());
		
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			query.setParameterList("financialYrIdList", financialYrIdList);
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
	public List<Object[]> getAllDistrictByStateId(Long superLocationId,List<Long> financialYrIdList,List<Long> deptIdList,List<Long> sourceIdList,Date sDate,Date eDate){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct district.districtId, district.districtName");
		
		sb.append(" from "
				+ " FundSanctionLocation fundSanctionLocation "
				+ " left outer join fundSanctionLocation.locationAddress locationAddress "
				+ " left outer join locationAddress.district district ");
		sb.append(" where fundSanctionLocation.isDeleted='N' ");
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
		sb.append("order by district.districtName asc ");
		Query query = getSession().createQuery(sb.toString());
		
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
	public List<Object[]> getAllConstituencyByDistrictId(Long superLocationId,List<Long> financialYrIdList,List<Long> deptIdList,List<Long> sourceIdList,Date sDate,Date eDate){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct constituency.constituencyId, constituency.name ");
		
		sb.append(" from "
				+ " FundSanctionLocation fundSanctionLocation "
				+ " left outer join fundSanctionLocation.locationAddress locationAddress "
				+ " left outer join locationAddress.district district "
				+ " left outer join locationAddress.constituency constituency ");
		sb.append(" where fundSanctionLocation.isDeleted='N' "
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
		sb.append("order by constituency.name asc ");
		Query query = getSession().createQuery(sb.toString());
		
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
	@Override
	public List<Object[]> getAllTehsilByConstituencyId(Long superLocationId,List<Long> financialYrIdList,List<Long> deptIdList,List<Long> sourceIdList,Date sDate,Date eDate){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct tehsil.tehsilId, tehsil.tehsilName ");
		
		sb.append(" from "
				+ " FundSanctionLocation fundSanctionLocation "
				+ " left outer join fundSanctionLocation.locationAddress locationAddress "
				+ " left outer join locationAddress.district district "
				+ " left outer join locationAddress.constituency constituency "
				+ " left outer join locationAddress.tehsil tehsil ");
		sb.append(" where fundSanctionLocation.isDeleted='N' "
				+ " and constituency.constituencyId = :superLocationId ");
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
		sb.append("order by tehsil.tehsilName asc ");
		Query query = getSession().createQuery(sb.toString());
		
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
	@Override
	public List<Object[]> getAllPanchayatByTehsilId(Long superLocationId,List<Long> financialYrIdList,List<Long> deptIdList,List<Long> sourceIdList,Date sDate,Date eDate){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct panchayat.panchayatId, panchayat.panchayatName ");
		
		sb.append(" from "
				+ " FundSanctionLocation fundSanctionLocation "
				+ " left outer join fundSanctionLocation.locationAddress locationAddress "
				+ " left outer join locationAddress.district district "
				+ " left outer join locationAddress.constituency constituency "
				+ " left outer join locationAddress.tehsil tehsil "
				+ " left outer join locationAddress.panchayat panchayat ");
		sb.append(" where fundSanctionLocation.isDeleted='N' "
				+ " and tehsil.tehsilId = :superLocationId ");
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
		sb.append("order by  panchayat.panchayatName asc ");
		Query query = getSession().createQuery(sb.toString());
		
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
	
	@Override
	public List<Object[]> getLocationWiseAmountAndCountDetails(Date fromDate,Date toDate,InputVO inputVO){
		StringBuilder sb = new StringBuilder();  
		sb.append(" select "
				+ " fundSanctionLocation.fundSanction.financialYear.financialYearId "//0
				+ " ,fundSanctionLocation.fundSanction.financialYear.yearDesc ");//1
		if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.district.stateId "//2
					+ " ,'Andhra Pradesh' "//3
					+ " ,count(fundSanctionLocation.locationAddress.district.stateId) ");//4
		}else if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.district.districtId "//2
					+ " ,fundSanctionLocation.locationAddress.district.districtName "//3
					+ " ,count(fundSanctionLocation.locationAddress.district.districtId) ");//4
		}else if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.constituency.constituencyId "//2
					+ " ,fundSanctionLocation.locationAddress.constituency.name"//3
					+ " ,count(fundSanctionLocation.locationAddress.constituency.constituencyId) ");//4
		}else if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.tehsil.tehsilId "//2
					+ " ,fundSanctionLocation.locationAddress.tehsil.tehsilName"//3
					+ " ,count(fundSanctionLocation.locationAddress.tehsil.tehsilId) ");//4
		}else if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.panchayat.panchayatId "//2
					+ " ,fundSanctionLocation.locationAddress.panchayat.panchayatName"//3
					+ " ,count(fundSanctionLocation.locationAddress.panchayat.panchayatId) ");//4
		}
		
		sb.append(" ,sum(fundSanctionLocation.fundSanction.sactionAmount) "//5
				+ " from "
				+ " FundSanctionLocation fundSanctionLocation "
				+ " where "
				+ " fundSanctionLocation.isDeleted = 'N' "
				+ " and fundSanctionLocation.fundSanction.isDeleted = 'N' "
				+ " and fundSanctionLocation.fundSanction.insertedTime between :fromDate and :toDate ");
		
			if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
				sb.append(" and fundSanctionLocation.locationAddress.district.districtId in (:locationIds) ");
			}else if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" and fundSanctionLocation.locationAddress.constituency.constituencyId in (:locationIds) ");
			}else if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
				sb.append(" and fundSanctionLocation.locationAddress.tehsil.tehsilId in (:locationIds) ");
			}else if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
				sb.append(" and fundSanctionLocation.locationAddress.panchayat.panchayatId in (:locationIds) ");
			}
		
		if(inputVO.getFinancialYrIdList() != null && inputVO.getFinancialYrIdList().size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.financialYear.financialYearId in (:financialYrIdList) ");
		}
		if(inputVO.getDeptIdsList()  !=null && inputVO.getDeptIdsList() .size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.department.departmentId in (:deptIdList) ");
		}
		if(inputVO.getSourceIdsList() !=null && inputVO.getSourceIdsList().size() > 0){
			
		}
		sb.append(" group by fundSanctionLocation.fundSanction.financialYear.financialYearId ");
		if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.district.stateId ");
		}else if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.district.districtId ");
		}else if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.constituency.constituencyId ");
		}else if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.tehsil.tehsilId ");
		}else if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.panchayat.panchayatId ");
		}
		sb.append(" order by fundSanctionLocation.fundSanction.financialYear.financialYearId ");
		Query query = getSession().createQuery(sb.toString());
		if(inputVO.getFinancialYrIdList() != null && inputVO.getFinancialYrIdList().size() > 0){
			query.setParameterList("financialYrIdList", inputVO.getFinancialYrIdList());
		}
		if(inputVO.getLevelValues() != null && inputVO.getLevelValues().size() > 0L){
			query.setParameterList("locationIds", inputVO.getLevelValues());
		}
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		if(inputVO.getDeptIdsList() !=null && inputVO.getDeptIdsList() .size() > 0){
			query.setParameterList("deptIdList", inputVO.getDeptIdsList() );
		}
		return query.list();
	}
	
}
