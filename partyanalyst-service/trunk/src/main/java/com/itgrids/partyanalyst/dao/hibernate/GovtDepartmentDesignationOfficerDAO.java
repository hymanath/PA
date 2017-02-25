package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficer;

public class GovtDepartmentDesignationOfficerDAO extends GenericDaoHibernate<GovtDepartmentDesignationOfficer, Long> implements IGovtDepartmentDesignationOfficerDAO{

	public GovtDepartmentDesignationOfficerDAO() {
		super(GovtDepartmentDesignationOfficer.class);
		
	}
	
	public List<Long> getDesignationOfficerIds(Long levelId,Long levelValue,Long designationId){
		Query query = getSession().createQuery("select distinct model.govtDepartmentDesignationOfficerId" +
											" from GovtDepartmentDesignationOfficer model" +
											" where model.govtDepartmentLevelId = :levelId" +
											" and model.levelValue = :levelValue" +
											" and model.govtDepartmentDesignationId = :designationId");
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		query.setParameter("designationId", designationId);
		return query.list();
	}
	
	public List<Object[]> getDeptDesignationsForUser(Long userId){
		Query query = getSession().createQuery("select distinct model.govtDepartmentDesignation.govtDepartmentDesignationId," +
												" model.govtDepartmentDesignation.designationName," +
												" model.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
												" model.govtDepartmentDesignation.govtDepartment.departmentName" +
												" from GovtDepartmentDesignationOfficer model" +
												" where model.userId = :userId");
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<Object[]> getLevelsForUser(Long userId,Long designationId){
		Query query = getSession().createQuery("select distinct model.govtDepartmentLevel.govtDepartmentLevelId," +
												" model.govtDepartmentLevel.levelName," +
												" model.levelValue" +
												" from GovtDepartmentDesignationOfficer model" +
												" where model.govtDepartmentDesignation.govtDepartmentDesignationId = :designationId" +
												" and model.userId = :userId");
		query.setParameter("designationId", designationId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<Long> getLevelIdForDesignation(Long userId,Long designationId){
		Query query = getSession().createQuery("select distinct model.govtDepartmentLevel.govtDepartmentLevelId" +
												" from GovtDepartmentDesignationOfficer model" +
												" where model.govtDepartmentDesignation.govtDepartmentDesignationId = :designationId" +
												" and model.userId = :userId");
		query.setParameter("designationId", designationId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<Object[]> getLevelsForUserAndDepartment(Long userId,Long departmentId){
		Query query = getSession().createQuery( " select distinct model.govtDepartmentLevel.govtDepartmentLevelId," +
												" model.govtDepartmentLevel.levelName" +
												" from GovtDepartmentDesignationOfficer model" +
												" where model.govtDepartmentDesignation.govtDepartment.govtDepartmentId = :departmentId" +
												" and model.userId = :userId" +
												" order by model.govtDepartmentLevel.govtDepartmentLevelId");
		query.setParameter("departmentId", departmentId);
		query.setParameter("userId", userId);
		return query.list();
	}
}
