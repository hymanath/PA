package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmDepartmentDesignationOfficerDAO;
import com.itgrids.model.PmDepartmentDesignationOfficer;

@Repository
public class PmDepartmentDesignationOfficerDAO extends GenericDaoHibernate<PmDepartmentDesignationOfficer, Long>implements IPmDepartmentDesignationOfficerDAO {

	@Autowired
	SessionFactory sessionFactory; 
	public PmDepartmentDesignationOfficerDAO() {
		super(PmDepartmentDesignationOfficer.class);
	}

	public List<Object[]> getDeptDesignationOfficerDetailsByDeptDesignation(List<Long> designationIdsList,List<Long> deptIdsList){
		StringBuilder str = new StringBuilder();
		//str.append(" select distinct model.pmDepartmentDesignationOfficerId, model.pmOfficer.name," +
		str.append(" select distinct model.pmOfficer.pmOfficerId, model.pmOfficer.name," +
				"  model.pmOfficer.mobileNo,model.pmDepartmentDesignation.pmDepartment.department, " +
				" model.pmDepartmentDesignation.pmOfficerDesignation.designation " +
				" from PmDepartmentDesignationOfficer model" +
				//"  where model.pmDepartmentDesignationId =:deptDesignationId and " +
				"  where model.pmDepartmentDesignation.pmOfficerDesignationId in (:designationIdsList) and model.pmDepartmentDesignation.pmDepartmentId in (:deptIdsList) and " +
				" model.isActive ='Y' and model.pmOfficer.isActive ='Y' " +
				//"  group by model.pmOfficer.pmOfficerId order by model.pmDepartmentDesignation.pmDepartment.department,model.pmDepartmentDesignation.pmOfficerDesignation.designation ");
				"  group by model.pmOfficer.pmOfficerId order by model.pmOfficer.name ");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("designationIdsList", designationIdsList);
		query.setParameterList("deptIdsList", deptIdsList);
		return query.list();
	}
	
	public List<Object[]> getDeptDesignationOfficerDetailsByDeptAndOffId(Long officerDesignationId,Long pmDepartmentDesignationOfficerId){
		StringBuilder str = new StringBuilder();
		str.append(" select distinct model.pmDepartmentDesignationOfficerId,model.pmDepartmentDesignationId, model.pmOfficer.name," +
				"  model.pmOfficer.mobileNo,model.pmDepartmentDesignation.pmDepartment.department, " +
				" model.pmDepartmentDesignation.pmOfficerDesignation.designation" +
				",model.pmDepartmentDesignation.pmDepartment.shortName from PmDepartmentDesignationOfficer model" +
				"  where model.pmDepartmentDesignation.pmOfficerDesignationId =:officerDesignationId and model.pmOfficerId =:pmDepartmentDesignationOfficerId and " +
				" model.isActive ='Y' and model.pmOfficer.isActive ='Y' " +
				" order by model.pmDepartmentDesignation.pmDepartment.department,model.pmDepartmentDesignation.pmOfficerDesignation.designation ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("pmDepartmentDesignationOfficerId", pmDepartmentDesignationOfficerId);
		query.setParameter("officerDesignationId", officerDesignationId);
		return query.list();
	}
	
	public Long geOfficerIdByDeptDesigIds(List<Long> deptdesigIds) {
		StringBuilder str = new StringBuilder();
		str.append(" select distinct model.pmOfficer.pmOfficerId from PmDepartmentDesignationOfficer model where model.isActive ='Y' and model.pmOfficer.isActive ='Y' " );
		if(deptdesigIds != null && deptdesigIds.size() >0){
			str.append(" and model.pmDepartmentDesignation.pmDepartmentDesignationId in (:deptdesigIds) ");
		}
		Query query = getSession().createQuery(str.toString());
		if(deptdesigIds != null && deptdesigIds.size() >0){
			query.setParameterList("deptdesigIds", deptdesigIds);
		}
		return (Long)query.uniqueResult();
	}
	
}
