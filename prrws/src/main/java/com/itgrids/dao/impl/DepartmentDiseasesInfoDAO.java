package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
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
	public List<Object[]> getCaseCountDiseasesWise(Date startDate, Date endDate,List<Long> diseasesIdList,List<Long> deptIdList){
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
		sb.append("group by departmentDiseasesInfo.diseases.diseasesId ");
		
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
	@Override
	public List<Object[]> getCaseCountLocationWise(Date startDate, Date endDate,List<Long> diseasesIdList,List<Long> deptIdList,Long scopeId){
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
	@Override
	public List<Object[]> getCaseCountDateWise(Date startDate, Date endDate,List<Long> diseasesIdList,List<Long> deptIdList){
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
}
