package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IDepartmentDiseasesInfoDAO;
import com.itgrids.model.DepartmentDiseasesInfo;
import com.itgrids.utils.IConstants;
@Repository
public class DepartmentDiseasesInfoDAO extends GenericDaoHibernate<DepartmentDiseasesInfo, Long> implements IDepartmentDiseasesInfoDAO {
	public DepartmentDiseasesInfoDAO(){
		super(DepartmentDiseasesInfo.class);
	}
	@Override
	public List<Object[]> getCaseCountDiseasesWise(Date startDate, Date endDate,List<Long> diseasesIdList,List<Long> deptIdList,String type){
		StringBuilder sb = new StringBuilder();
		sb.append(" select "
				+ " departmentDiseasesInfo.diseases.diseasesId, "
				+ " departmentDiseasesInfo.diseases.diseaseName, ");
		sb.append(" sum(departmentDiseasesInfo.noOfCases) "
				+ " from DepartmentDiseasesInfo departmentDiseasesInfo "
				+ " where departmentDiseasesInfo.isDeleted = 'N' ");
		if(startDate != null && endDate != null){
			sb.append(" and date(departmentDiseasesInfo.reportedDate) between :startDate and :endDate ");
		}
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.diseases.diseasesId in (:diseasesIdList) ");
		}
		if(deptIdList != null && deptIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.department.departmentId in (:deptIdList) ");
		}
		if(type != null && type.trim().equalsIgnoreCase("Rural")){
			sb.append(" and departmentDiseasesInfo.locationAddress.tehsil.tehsilId is not null");
		}else if(type != null && type.trim().equalsIgnoreCase("Urban")){
			sb.append(" and departmentDiseasesInfo.locationAddress.localElectionBody.localElectionBodyId is not null");
		}
		sb.append(" group by departmentDiseasesInfo.diseases.diseasesId ");
		
		sb.append("order by sum(departmentDiseasesInfo.noOfCases) ");
		Query query = getSession().createQuery(sb.toString());
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			query.setParameterList("diseasesIdList", diseasesIdList);
		}
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		return query.list();
	}
	//abc
	@Override
	public List<Object[]> getCaseCountLocationWise(Date startDate, Date endDate,List<Long> diseasesIdList,List<Long> deptIdList,Long scopeId, Long locationLevelId, Long locationId,String type,Long constituencyId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct"
				+ " departmentDiseasesInfo.diseasesId, ");//0
		if(scopeId != null && scopeId.longValue() > 0){
			if(scopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
				sb.append(" district.districtId, "//1
						+ " district.districtName, ");//2
			}else if(scopeId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" parliament.constituencyId, "
						+ " parliament.name, ");
			}else if(scopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" constituency.constituencyId, "
						+ " constituency.name, ");
			}else if(scopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
				sb.append(" tehsil.tehsilId, "
						+ " tehsil.tehsilName, ");
			}else if(scopeId.longValue() == IConstants.MUNICIPAL_CORP_GMC_LEVEL_SCOPE_ID){
				sb.append(" localElectionBody.localElectionBodyId , "
						+ " localElectionBody.name, ");
			}else if(scopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
				sb.append(" panchayat.panchayatId, "
						+ " panchayat.panchayatName, ");
			}
		}
		sb.append(" sum(departmentDiseasesInfo.noOfCases), "//3
				+ " district.districtId,"//4
				+ " district.districtName, "//5
				+ " parliament.constituencyId, "//6
				+ " parliament.name, "//7
				+ " constituency.constituencyId, "//8
				+ " constituency.name, "//9
				+ " tehsil.tehsilId, "//10
				+ " tehsil.tehsilName, "//11
				+ " panchayat.panchayatId, "//12
				+ " panchayat.panchayatName, "//13
				+ " localElectionBody.localElectionBodyId, "//14
				+ " localElectionBody.name ");//15
		sb.append(" from DepartmentDiseasesInfo departmentDiseasesInfo "
				+ " left join departmentDiseasesInfo.locationAddress locationAddress "
				+ " left join locationAddress.district district "
				+ " left join locationAddress.state state "
				+ " left join locationAddress.constituency constituency "
				+ " left join locationAddress.parliament parliament "
				+ " left join locationAddress.tehsil tehsil "
				+ " left join locationAddress.localElectionBody localElectionBody "
				+ " left join locationAddress.panchayat panchayat "
				+ " where departmentDiseasesInfo.isDeleted = 'N' ");
		
		
		if(startDate != null && endDate != null){
			sb.append(" and date(departmentDiseasesInfo.reportedDate) between :startDate and :endDate ");
		}
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.diseases.diseasesId in (:diseasesIdList) ");
		}
		if(deptIdList != null && deptIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.department.departmentId in (:deptIdList) ");
		}
		if(type != null && type.trim().equalsIgnoreCase("Rural")){
			sb.append(" and (constituency.areaType='RURAL' or constituency.areaType='RURAL-URBAN')");
		}else if(type != null && type.trim().equalsIgnoreCase("Urban")){
			sb.append(" and (constituency.areaType='RURAL-URBAN' or constituency.areaType='URBAN') ");
		}
		if((type != null && type.trim().equalsIgnoreCase("Rural") || type != null && type.trim().equalsIgnoreCase("Urban")) && (scopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID || scopeId.longValue() == IConstants.MUNICIPAL_CORP_GMC_LEVEL_SCOPE_ID)){
			if(type != null && type.trim().equalsIgnoreCase("Rural")){
				sb.append(" and tehsil.tehsilId is not null and localElectionBody is null ");
			}else if(type != null && type.trim().equalsIgnoreCase("Urban")){
				sb.append(" and localElectionBody.localElectionBodyId is not null and tehsil is null ");
			}
		}
		
		if(constituencyId != null && constituencyId.longValue() > 0L){
			sb.append(" and constituency.constituencyId = :constituencyId");
		}
		if(locationId != null && locationId.longValue() > 0){
			if(locationLevelId != null && locationLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
				sb.append(" and district.districtId =:locationId ");
			}else if(locationLevelId != null && locationLevelId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" and parliament.constituencyId =:locationId ");
			}else if(locationLevelId != null && locationLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" and constituency.constituencyId =:locationId ");
			}else if(locationLevelId != null && locationLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
				sb.append(" and tehsil.tehsilId =:locationId ");
			}else if(scopeId.longValue() == IConstants.MUNICIPAL_CORP_GMC_LEVEL_SCOPE_ID){
				sb.append(" and localElectionBody.localElectionBodyId =:locationId ");
			}else if(locationLevelId != null && locationLevelId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
				sb.append(" and panchayat.panchayatId =:locationId ");
			}
		}
		
		sb.append(" group by departmentDiseasesInfo.diseasesId, ");
		if(scopeId != null && scopeId.longValue() > 0){
			if(scopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
				sb.append(" district.districtId ");
			}else if(scopeId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" parliament.constituencyId ");
			}else if(scopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" constituency.constituencyId ");
			}else if(scopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
				sb.append(" constituency.constituencyId,tehsil.tehsilId ");
			}else if(scopeId.longValue() == IConstants.MUNICIPAL_CORP_GMC_LEVEL_SCOPE_ID){
				sb.append(" constituency.constituencyId,localElectionBody.localElectionBodyId ");
			}else if(scopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
				sb.append(" panchayat.panchayatId ");
			}
		}
		sb.append("order by departmentDiseasesInfo.diseasesId ");
		Query query = getSession().createQuery(sb.toString());
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			query.setParameterList("diseasesIdList", diseasesIdList);
		}
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		if(locationId != null && locationId.longValue() > 0 && locationLevelId != null && locationLevelId.longValue() > 0L){
			query.setParameter("locationId", locationId);
		}
		if(constituencyId != null && constituencyId.longValue() > 0L){
			query.setParameter("constituencyId", constituencyId);
		}
		return query.list();
	}
	@Override
	public List<Object[]> getCaseCountDateWise(Date startDate, Date endDate,List<Long> diseasesIdList,List<Long> deptIdList,String type){
		StringBuilder sb = new StringBuilder();
		sb.append(" select "
				+ " 1, "
				+ " date(departmentDiseasesInfo.reportedDate), ");
		sb.append(" sum(departmentDiseasesInfo.noOfCases) "
				+ " from DepartmentDiseasesInfo departmentDiseasesInfo "
				+ " where departmentDiseasesInfo.isDeleted = 'N' ");
		if(startDate != null && endDate != null){
			sb.append(" and date(departmentDiseasesInfo.reportedDate) between :startDate and :endDate ");
		}
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.diseases.diseasesId in (:diseasesIdList) ");
		}
		if(deptIdList != null && deptIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.department.departmentId in (:deptIdList) ");
		}
		if(type != null && type.trim().equalsIgnoreCase("Rural")){
			sb.append(" and departmentDiseasesInfo.locationScopeId = 5");
		}else if(type != null && type.trim().equalsIgnoreCase("Urban")){
			sb.append(" and departmentDiseasesInfo.locationScopeId = 7");
		}
		sb.append("group by date(departmentDiseasesInfo.reportedDate) ");
		
		sb.append("order by date(departmentDiseasesInfo.reportedDate) ");
		Query query = getSession().createQuery(sb.toString());
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			query.setParameterList("diseasesIdList", diseasesIdList);
		}
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		return query.list();
	}
	@Override
	public List<Object[]> getTotLocationsDiseasesWiseCount(Date startDate, Date endDate,List<Long> diseasesIdList,List<Long> deptIdList,Long scopeId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		if(scopeId != null && scopeId.longValue() > 0){
			if(scopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
				sb.append(" departmentDiseasesInfo.locationAddress.district.districtId, "
						+ " departmentDiseasesInfo.locationAddress.district.districtName, ");
			}else if(scopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" departmentDiseasesInfo.locationAddress.constituency.constituencyId, "
						+ " departmentDiseasesInfo.locationAddress.constituency.name, ");
			}else if(scopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
				sb.append(" departmentDiseasesInfo.locationAddress.tehsil.tehsilId, "
						+ " departmentDiseasesInfo.locationAddress.tehsil.tehsilName, ");
			}else if(scopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
				sb.append(" departmentDiseasesInfo.locationAddress.panchayat.panchayatId, "
						+ " departmentDiseasesInfo.locationAddress.panchayat.panchayatName, ");
			}
		}
		sb.append(" sum(departmentDiseasesInfo.noOfCases) "
				+ " from DepartmentDiseasesInfo departmentDiseasesInfo "
				+ " where departmentDiseasesInfo.isDeleted = 'N' ");
		if(startDate != null && endDate != null){
			sb.append(" and date(departmentDiseasesInfo.reportedDate) between :startDate and :endDate ");
		}
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.diseases.diseasesId in (:diseasesIdList) ");
		}
		if(deptIdList != null && deptIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.department.departmentId in (:deptIdList) ");
		}
		sb.append("group by ");
		if(scopeId != null && scopeId.longValue() > 0){
			if(scopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
				sb.append(" departmentDiseasesInfo.locationAddress.district.districtId ");
			}else if(scopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" departmentDiseasesInfo.locationAddress.constituency.constituencyId ");
			}else if(scopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
				sb.append(" departmentDiseasesInfo.locationAddress.tehsil.tehsilId ");
			}else if(scopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
				sb.append(" departmentDiseasesInfo.locationAddress.panchayat.panchayatId ");
			}
		}
		sb.append("order by sum(departmentDiseasesInfo.noOfCases) desc ");
		Query query = getSession().createQuery(sb.toString());
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			query.setParameterList("diseasesIdList", diseasesIdList);
		}
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		//query.setFirstResult(0);
		//query.setMaxResults(5);
		return query.list();
	}
	@Override
	public List<String> getMonthAndYear(Date fromDate,Date toDate){
        StringBuilder queryStr = new StringBuilder();
        
        queryStr.append(" select CONCAT(DATE_FORMAT(m1, '%M'),'-',year(m1)) as monthYear " +
                           "    from ( " +
                               "    select " +
                               "    (:fromDate - INTERVAL DAYOFMONTH(:fromDate)-1 DAY)  " +
                               "        +INTERVAL m MONTH as m1 " +
                               "    from ( " +
                                   "    select @rownum /*'*/:=/*'*/ @rownum+1 as m from " +
                                   "    (select 1 union select 2 union select 3 union select 4 union select 5) t1, " +
                                   "     (select 1 union select 2 union select 3 union select 4 union select 5) t2, " +
                                   "     (select 1 union select 2 union select 3 union select 4 union select 5) t3,  " +
                                   "     (select 1 union select 2 union select 3 union select 4 union select 5) t4, " +
                                   "     (select @rownum /*'*/:=/*'*/ -1) t0  " +
                                   "      ) d1 " +
                               " ) d2 " +
                               "  where m1<=:toDate " +
                               " order by m1");
        
          Session session = getSession();
          SQLQuery sqlQuery = session.createSQLQuery(queryStr.toString());
          sqlQuery.addScalar("monthYear",StandardBasicTypes.STRING);
          sqlQuery.setDate("fromDate", fromDate);
          sqlQuery.setDate("toDate", toDate);
          return sqlQuery.list();
   }
	@Override
	public List<Object[]> getLocationDtlsRankWise(Date startDate, Date endDate,List<Long> diseasesIdList,List<Long> deptIdList,Long scopeId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		if(scopeId != null && scopeId.longValue() > 0){
			if(scopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
				sb.append(" district.districtId, "//0
						+ " district.districtName, ");//1
			}else if(scopeId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" parliament.constituencyId, "
						+ " parliament.name, ");
			}else if(scopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" constituency.constituencyId, "
						+ " constituency.name, ");
			}else if(scopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
				sb.append(" tehsil.tehsilId, "
						+ " tehsil.tehsilName, ");
			}else if(scopeId.longValue() == IConstants.MUNICIPAL_CORP_GMC_LEVEL_SCOPE_ID){
				sb.append(" localElectionBody.localElectionBodyId , "
						+ " localElectionBody.name, ");
			}else if(scopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
				sb.append(" panchayat.panchayatId, "
						+ " panchayat.panchayatName, ");
			}
		}
		sb.append(" sum(departmentDiseasesInfo.noOfCases), "//2
				+ " district.districtId,"//3
				+ " district.districtName, "//4
				+ " parliament.constituencyId, "//5
				+ " parliament.name, "//6
				+ " constituency.constituencyId, "//7
				+ " constituency.name, "//8
				+ " tehsil.tehsilId, "//9
				+ " tehsil.tehsilName, "//10
				+ " panchayat.panchayatId, "//11
				+ " panchayat.panchayatName, "//12
				+ " localElectionBody.localElectionBodyId, "//13
				+ " localElectionBody.name ");//14
		sb.append(" from DepartmentDiseasesInfo departmentDiseasesInfo "
				+ " left join departmentDiseasesInfo.locationAddress locationAddress "
				+ " left join locationAddress.district district "
				+ " left join locationAddress.state state "
				+ " left join locationAddress.constituency constituency "
				+ " left join locationAddress.parliament parliament "
				+ " left join locationAddress.tehsil tehsil "
				+ " left join locationAddress.localElectionBody localElectionBody "
				+ " left join locationAddress.panchayat panchayat "
				+ " where departmentDiseasesInfo.isDeleted = 'N' ");
		
		
		if(startDate != null && endDate != null){
			sb.append(" and date(departmentDiseasesInfo.reportedDate) between :startDate and :endDate ");
		}
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.diseases.diseasesId in (:diseasesIdList) ");
		}
		if(deptIdList != null && deptIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.department.departmentId in (:deptIdList) ");
		}
		
		sb.append("group by ");
		if(scopeId != null && scopeId.longValue() > 0){
			if(scopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
				sb.append(" district.districtId ");
			}else if(scopeId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" parliament.constituencyId ");
			}else if(scopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" constituency.constituencyId ");
			}else if(scopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
				sb.append(" constituency.constituencyId,tehsil.tehsilId ");
			}else if(scopeId.longValue() == IConstants.MUNICIPAL_CORP_GMC_LEVEL_SCOPE_ID){
				sb.append(" constituency.constituencyId,localElectionBody.localElectionBodyId ");
			}else if(scopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
				sb.append(" panchayat.panchayatId ");
			}
		}
		sb.append("order by sum(departmentDiseasesInfo.noOfCases) desc ");
		Query query = getSession().createQuery(sb.toString());
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			query.setParameterList("diseasesIdList", diseasesIdList);
		}
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		return query.list();
	}
	@Override
	public List<Object[]> getAllParliamentByStateId(Date startDate,Date endDate,Long superLocationId,List<Long> diseasesIdList,List<Long> deptIdList){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct ");
		sb.append(" departmentDiseasesInfo.locationAddress.parliament.constituencyId, "
				+ " departmentDiseasesInfo.locationAddress.parliament.name "
				+ " from "
				+ " DepartmentDiseasesInfo departmentDiseasesInfo "
				+ " where departmentDiseasesInfo.isDeleted = 'N'  "
				+ " and departmentDiseasesInfo.locationAddress.state.stateId =:superLocationId ");
		if(startDate != null && endDate != null){
			sb.append(" and date(departmentDiseasesInfo.reportedDate) between :startDate and :endDate ");
		}
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.diseases.diseasesId in (:diseasesIdList) ");
		}
		if(deptIdList != null && deptIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.department.departmentId in (:deptIdList) ");
		}
		
		sb.append(" order by departmentDiseasesInfo.locationAddress.parliament.name ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("superLocationId", superLocationId);
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			query.setParameterList("diseasesIdList", diseasesIdList);
		}
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		return query.list();
	}
	@Override
	public List<Object[]> getAllDistrictByStateId(Date startDate,Date endDate,Long superLocationId,List<Long> diseasesIdList,List<Long> deptIdList){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct ");
		sb.append(" departmentDiseasesInfo.locationAddress.district.districtId, "
				+ " departmentDiseasesInfo.locationAddress.district.districtName "
				+ " from "
				+ " DepartmentDiseasesInfo departmentDiseasesInfo "
				+ " where departmentDiseasesInfo.isDeleted = 'N'  "
				+ " and departmentDiseasesInfo.locationAddress.state.stateId =:superLocationId ");
		if(startDate != null && endDate != null){
			sb.append(" and date(departmentDiseasesInfo.reportedDate) between :startDate and :endDate ");
		}
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.diseases.diseasesId in (:diseasesIdList) ");
		}
		if(deptIdList != null && deptIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.department.departmentId in (:deptIdList) ");
		}
		sb.append(" order by departmentDiseasesInfo.locationAddress.district.districtName ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("superLocationId", superLocationId);
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			query.setParameterList("diseasesIdList", diseasesIdList);
		}
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		return query.list();
	}
	@Override
	public List<Object[]> getAllConstituencyByDistrictId(Date startDate,Date endDate,Long superLocationId,List<Long> diseasesIdList,List<Long> deptIdList,String type){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct ");
		sb.append(" departmentDiseasesInfo.locationAddress.constituency.constituencyId, "
				+ " departmentDiseasesInfo.locationAddress.constituency.name "
				+ " from "
				+ " DepartmentDiseasesInfo departmentDiseasesInfo "
				+ " where departmentDiseasesInfo.isDeleted = 'N'  "
				+ " and departmentDiseasesInfo.locationAddress.district.districtId =:superLocationId ");
		if(startDate != null && endDate != null){
			sb.append(" and date(departmentDiseasesInfo.reportedDate) between :startDate and :endDate ");
		}
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.diseases.diseasesId in (:diseasesIdList) ");
		}
		if(deptIdList != null && deptIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.department.departmentId in (:deptIdList) ");
		}
		if(type != null && type.trim().equalsIgnoreCase("Rural")){
			sb.append(" and (departmentDiseasesInfo.locationAddress.constituency.areaType='RURAL' or departmentDiseasesInfo.locationAddress.constituency.areaType='RURAL-URBAN')");
		}else if(type != null && type.trim().equalsIgnoreCase("Urban")){
			sb.append(" and (departmentDiseasesInfo.locationAddress.constituency.areaType='RURAL-URBAN' or departmentDiseasesInfo.locationAddress.constituency.areaType='URBAN') ");
		}
		sb.append(" order by departmentDiseasesInfo.locationAddress.constituency.name ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("superLocationId", superLocationId);
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			query.setParameterList("diseasesIdList", diseasesIdList);
		}
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		return query.list();
	}
	@Override
	public List<Object[]> getAllTehsilByConstituencyId(Date startDate,Date endDate,Long superLocationId,List<Long> diseasesIdList,List<Long> deptIdList){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct ");
		sb.append(" departmentDiseasesInfo.locationAddress.tehsil.tehsilId, "
				+ " departmentDiseasesInfo.locationAddress.tehsil.tehsilName "
				+ " from "
				+ " DepartmentDiseasesInfo departmentDiseasesInfo "
				+ " where departmentDiseasesInfo.isDeleted = 'N'  "
				+ " and departmentDiseasesInfo.locationAddress.constituency.constituencyId =:superLocationId ");
		if(startDate != null && endDate != null){
			sb.append(" and date(departmentDiseasesInfo.reportedDate) between :startDate and :endDate ");
		}
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.diseases.diseasesId in (:diseasesIdList) ");
		}
		if(deptIdList != null && deptIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.department.departmentId in (:deptIdList) ");
		}
		sb.append(" order by departmentDiseasesInfo.locationAddress.tehsil.tehsilName ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("superLocationId", superLocationId);
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			query.setParameterList("diseasesIdList", diseasesIdList);
		}
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		return query.list();
	}
	@Override
	public List<Object[]> getAllPanchayatByTehsilId(Date startDate,Date endDate,Long superLocationId,List<Long> diseasesIdList,List<Long> deptIdList){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct ");
		sb.append(" departmentDiseasesInfo.locationAddress.panchayat.panchayatId, "
				+ " departmentDiseasesInfo.locationAddress.panchayat.panchayatName "
				+ " from "
				+ " DepartmentDiseasesInfo departmentDiseasesInfo "
				+ " where departmentDiseasesInfo.isDeleted = 'N'  "
				+ " and departmentDiseasesInfo.locationAddress.tehsil.tehsilId =:superLocationId ");
		if(startDate != null && endDate != null){
			sb.append(" and date(departmentDiseasesInfo.reportedDate) between :startDate and :endDate ");
		}
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.diseases.diseasesId in (:diseasesIdList) ");
		}
		if(deptIdList != null && deptIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.department.departmentId in (:deptIdList) ");
		}
		sb.append(" order by departmentDiseasesInfo.locationAddress.panchayat.panchayatName ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("superLocationId", superLocationId);
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			query.setParameterList("diseasesIdList", diseasesIdList);
		}
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		return query.list();
	}
	@Override
	public List<Object[]> getAllConstituencyByParliamentConstId(Date startDate,Date endDate,Long superLocationId,List<Long> diseasesIdList,List<Long> deptIdList, String type){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct ");
		sb.append(" departmentDiseasesInfo.locationAddress.constituency.constituencyId, "
				+ " departmentDiseasesInfo.locationAddress.constituency.name "
				+ " from "
				+ " DepartmentDiseasesInfo departmentDiseasesInfo "
				+ " where departmentDiseasesInfo.isDeleted = 'N'  "
				+ " and departmentDiseasesInfo.locationAddress.parliament.constituencyId =:superLocationId ");
		
		if(type != null && type.trim().equalsIgnoreCase("Rural")){
			sb.append(" and (departmentDiseasesInfo.locationAddress.constituency.areaType='RURAL' or departmentDiseasesInfo.locationAddress.constituency.areaType='RURAL-URBAN')");
		}else if(type != null && type.trim().equalsIgnoreCase("Urban")){
			sb.append(" and (departmentDiseasesInfo.locationAddress.constituency.areaType='RURAL-URBAN' or departmentDiseasesInfo.locationAddress.constituency.areaType='URBAN') ");
		}
		
		if(startDate != null && endDate != null){
			sb.append(" and date(departmentDiseasesInfo.reportedDate) between :startDate and :endDate ");
		}
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.diseases.diseasesId in (:diseasesIdList) ");
		}
		if(deptIdList != null && deptIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.department.departmentId in (:deptIdList) ");
		}
		sb.append(" order by departmentDiseasesInfo.locationAddress.constituency.name ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("superLocationId", superLocationId);
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			query.setParameterList("diseasesIdList", diseasesIdList);
		}
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		return query.list();
	}
	@Override
	public List<Object[]> getAllLocalElectionBodyByConstituencyId(Date startDate,Date endDate,Long superLocationId,List<Long> diseasesIdList,List<Long> deptIdList){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct ");
		sb.append(" departmentDiseasesInfo.locationAddress.localElectionBody.localElectionBodyId, "
				+ " departmentDiseasesInfo.locationAddress.localElectionBody.name "
				+ " from "
				+ " DepartmentDiseasesInfo departmentDiseasesInfo "
				+ " where departmentDiseasesInfo.isDeleted = 'N'  "
				+ " and departmentDiseasesInfo.locationAddress.constituency.constituencyId =:superLocationId ");
		if(startDate != null && endDate != null){
			sb.append(" and date(departmentDiseasesInfo.reportedDate) between :startDate and :endDate ");
		}
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.diseases.diseasesId in (:diseasesIdList) ");
		}
		if(deptIdList != null && deptIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.department.departmentId in (:deptIdList) ");
		}
		sb.append(" order by departmentDiseasesInfo.locationAddress.localElectionBody.name ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("superLocationId", superLocationId);
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			query.setParameterList("diseasesIdList", diseasesIdList);
		}
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		return query.list();
	}//xyz
	public List<Object[]> getCaseCountByLocationIds(List<Long> diseasesIdList,List<Long> deptIdList,Long scopeId,Set<Long> locationIdList,String type,Long constituencyId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");      
		if(scopeId != null && scopeId.longValue() > 0){
			if(scopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
				sb.append(" departmentDiseasesInfo.locationAddress.district.districtId, ");//0
			}else if(scopeId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" departmentDiseasesInfo.locationAddress.parliament.constituencyId, ");
			}else if(scopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" departmentDiseasesInfo.locationAddress.constituency.constituencyId, ");
			}else if(scopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
				sb.append(" departmentDiseasesInfo.locationAddress.tehsil.tehsilId, ");
			}else if(scopeId.longValue() == IConstants.MUNICIPAL_CORP_GMC_LEVEL_SCOPE_ID){
				sb.append(" departmentDiseasesInfo.locationAddress.localElectionBody.localElectionBodyId , ");
			}else if(scopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
				sb.append(" departmentDiseasesInfo.locationAddress.panchayat.panchayatId, ");
			}
		}
		sb.append(" sum(departmentDiseasesInfo.noOfCases) ");//1
		sb.append(" from DepartmentDiseasesInfo departmentDiseasesInfo "
				+ " where departmentDiseasesInfo.isDeleted = 'N' ");
		
		
		
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.diseases.diseasesId in (:diseasesIdList) ");
		}
		if(deptIdList != null && deptIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.department.departmentId in (:deptIdList) ");
		}
		if((type != null && type.trim().equalsIgnoreCase("Rural") || type != null && type.trim().equalsIgnoreCase("Urban")) && (scopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID || scopeId.longValue() == IConstants.MUNICIPAL_CORP_GMC_LEVEL_SCOPE_ID)){
			if(type != null && type.trim().equalsIgnoreCase("Rural")){
				sb.append(" and departmentDiseasesInfo.locationAddress.tehsil.tehsilId is not null and departmentDiseasesInfo.locationAddress.localElectionBody is null ");
			}else if(type != null && type.trim().equalsIgnoreCase("Urban")){
				sb.append(" and departmentDiseasesInfo.locationAddress.localElectionBody.localElectionBodyId is not null and departmentDiseasesInfo.locationAddress.tehsil is null");
			}
		}
		
		if(type != null && type.trim().equalsIgnoreCase("Rural")){
			sb.append(" and (departmentDiseasesInfo.locationAddress.constituency.areaType='RURAL' or departmentDiseasesInfo.locationAddress.constituency.areaType='RURAL-URBAN')");
		}else if(type != null && type.trim().equalsIgnoreCase("Urban")){
			sb.append(" and (departmentDiseasesInfo.locationAddress.constituency.areaType='RURAL-URBAN' or departmentDiseasesInfo.locationAddress.constituency.areaType='URBAN') ");
		}
		if(constituencyId != null && constituencyId.longValue() >0L){
			sb.append(" and departmentDiseasesInfo.locationAddress.constituency.constituencyId = :constituencyId");
		}
		
		sb.append(" group by ");
		if(scopeId != null && scopeId.longValue() > 0){
			if(scopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
				sb.append(" departmentDiseasesInfo.locationAddress.district.districtId ");
			}else if(scopeId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" departmentDiseasesInfo.locationAddress.parliament.constituencyId ");
			}else if(scopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" departmentDiseasesInfo.locationAddress.constituency.constituencyId ");
			}else if(scopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
				sb.append(" departmentDiseasesInfo.locationAddress.tehsil.tehsilId ");
			}else if(scopeId.longValue() == IConstants.MUNICIPAL_CORP_GMC_LEVEL_SCOPE_ID){
				sb.append(" departmentDiseasesInfo.locationAddress.localElectionBody.localElectionBodyId ");
			}else if(scopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
				sb.append(" departmentDiseasesInfo.locationAddress.panchayat.panchayatId ");
			}
		}
		Query query = getSession().createQuery(sb.toString());
		
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			query.setParameterList("diseasesIdList", diseasesIdList);
		}
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		if(constituencyId != null && constituencyId.longValue() >0L){
			query.setParameter("constituencyId", constituencyId);
		}
		return query.list();
	}
	
	public List<Date> getLatestUpdatedDate(){
		Query query = getSession().createQuery("select distinct date(model.reportedDate)"
				+ " from DepartmentDiseasesInfo model"
				+ " where model.isDeleted = 'N' order by date(model.reportedDate) desc");
		
		return query.list();
	}
	
	public Long getNoOfCasesFrDisease(Date startDate, Date endDate,List<Long> diseasesIdList,List<Long> deptIdList){
		StringBuilder sb = new StringBuilder();
		sb.append(" select "
				//+ " departmentDiseasesInfo.diseases.diseasesId, "
				//+ " departmentDiseasesInfo.diseases.diseaseName, ");
				+" sum(departmentDiseasesInfo.noOfCases) "
				+ " from DepartmentDiseasesInfo departmentDiseasesInfo "
				+ " where departmentDiseasesInfo.isDeleted = 'N' ");
		if(startDate != null && endDate != null){
			sb.append(" and date(departmentDiseasesInfo.reportedDate) between :startDate and :endDate ");
		}
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.diseases.diseasesId in (:diseasesIdList) ");
		}
		if(deptIdList != null && deptIdList.size() > 0){
			sb.append(" and departmentDiseasesInfo.department.departmentId in (:deptIdList) ");
		}
		
		//sb.append(" group by departmentDiseasesInfo.diseases.diseasesId ");
		
		//sb.append("order by sum(departmentDiseasesInfo.noOfCases) ");
		Query query = getSession().createQuery(sb.toString());
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(diseasesIdList != null && diseasesIdList.size() > 0){
			query.setParameterList("diseasesIdList", diseasesIdList);
		}
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		return (Long) query.uniqueResult();
	}
}
