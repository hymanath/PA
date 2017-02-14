package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtAlertDepartmentLocationDAO;
import com.itgrids.partyanalyst.model.GovtAlertDepartmentLocation;

public class GovtAlertDepartmentLocationDAO extends GenericDaoHibernate<GovtAlertDepartmentLocation, Long> implements IGovtAlertDepartmentLocationDAO{

	public GovtAlertDepartmentLocationDAO() {
		super(GovtAlertDepartmentLocation.class);
		
	}
	public List<Object[]> getDeptListForUser(Long userId){
		Query query = getSession().createQuery(" select distinct " +
											   " model.govtDepartment.govtDepartmentId," +//0
											   " state.stateId, " +//1
											   " state.stateName," +//2
											   " district.districtId, " +//3
											   " district.districtName" +//4
											   " from " +
											   " GovtAlertDepartmentLocation model  " +
											   " left join model.userAddress.state state "+
											   " left join model.userAddress.district district "+
											   " left join model.userAddress.constituency constituency "+
											   " left join model.userAddress.tehsil tehsil "+
											   " left join model.userAddress.localElectionBody localElectionBody "+
											   " left join model.userAddress.panchayat panchayat "+
											   " left join model.userAddress.ward ward "+
											   " where " +
											   " model.user.userId = :userId");  
		query.setParameter("userId", userId);
		return query.list();
	}  
	public List<Object[]> getGovtDeptLevelForDeptAndUser(Long departmentId,Long userId){  
		StringBuilder queryStr = new StringBuilder();     
		queryStr.append(" select distinct " +
						" model.govtDepartmentLevel.govtDepartmentLevelId, " +
						" model.govtDepartmentLevel.levelName " +  
						" from GovtAlertDepartmentLocation model " +
						" where " +
						" model.govtDepartment.govtDepartmentId = :departmentId  and " +
						" model.user.userId = :userId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("departmentId", departmentId);
		query.setParameter("userId", userId);
		return query.list();
	}
	public List<Object[]> getDeptIdAndNameListForUser(Long userId){
		Query query = getSession().createQuery(" select distinct " +
											   " model.govtDepartment.govtDepartmentId," + 
											   " model.govtDepartment.departmentName" +
											   " from " +
											   " GovtAlertDepartmentLocation model " +
											   " where " +
											   " model.userId = :userId");
		query.setParameter("userId", userId);
		return query.list();
	}
}
