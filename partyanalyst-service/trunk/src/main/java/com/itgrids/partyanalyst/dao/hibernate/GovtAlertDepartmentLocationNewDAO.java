package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtAlertDepartmentLocationNewDAO;
import com.itgrids.partyanalyst.model.GovtAlertDepartmentLocationNew;

public class GovtAlertDepartmentLocationNewDAO extends GenericDaoHibernate<GovtAlertDepartmentLocationNew, Long> implements IGovtAlertDepartmentLocationNewDAO {

	public GovtAlertDepartmentLocationNewDAO(){
		super(GovtAlertDepartmentLocationNew.class);
	}
	public List<Object[]> getUserAccessLevels(Long userId){
		Query query = getSession().createQuery(" select distinct " +  
												" model.govtDepartmentScope.govtDepartmentScopeId," +
												" model.levelValue,model.govtDepartmentScope.levelName  " +
												" from GovtAlertDepartmentLocationNew model" +
												" where model.user.userId = :userId" +
												" and model.isDeleted = 'N'");
		query.setParameter("userId", userId);
		return query.list();
	}
	public List<Object[]> getDeptIdAndNameForUserAccessLevel(Long userId){
 		Query query = getSession().createQuery(" select distinct " +
											   " model.govtDepartment.govtDepartmentId, " +
											   " model.govtDepartment.departmentName " +
											   " from " +
											   " GovtAlertDepartmentLocationNew model  " +
											   " where " +
											   " model.user.userId = :userId" +
											   " and model.isDeleted = 'N'");  
		query.setParameter("userId", userId);
		return query.list();
	}  
	public List<Long> getDeptNameForUserAccessLevel(Long userId){
 		Query query = getSession().createQuery(" select distinct " +
											   " model.govtDepartment.govtDepartmentId, " +
											   " model.govtDepartment.departmentName " +
											   " from " +
											   " GovtAlertDepartmentLocationNew model  " +
											   " where " +
											   " model.user.userId = :userId" +
											   " and model.isDeleted = 'N'");  
		query.setParameter("userId", userId);
		return query.list();
	}
	public List<String> getAccessDepartmentList(Long assignedUserId){
		Query query = getSession().createQuery(" select distinct " +
											   " model.govtDepartment.shortName " +
											   " from " +
											   " GovtAlertDepartmentLocationNew model  " +
											   " where " +
											   " model.user.userId = :assignedUserId" +
											   " and model.isDeleted = 'N'");  
		query.setParameter("assignedUserId", assignedUserId);
		return query.list();
	}
	
	public List<Object[]> getRegionScopeValuesForUser(Long userId,Long deptId){
		Query query = getSession().createQuery(" select distinct " +  
												" model.govtDepartmentWorkLocation.alertRegionScopesId," +
												" model.govtDepartmentWorkLocation.locationValue," +
												" model.govtDepartmentScopeId," +
												" model.levelValue" +
												" from GovtAlertDepartmentLocationNew model" +
												" where model.user.userId = :userId" +
												" and model.govtDepartmentId = :deptId" +
												" and model.isDeleted = 'N'" +
												" and model.govtDepartmentWorkLocation.isDeleted = 'N'");
		query.setParameter("userId", userId);
		query.setParameter("deptId", deptId);
		return query.list();
	}
}
