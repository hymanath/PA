package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
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

	public List<Object[]> getDeptDesignationOfficerDetailsByDeptDesignation(Long deptDesignationId){
		StringBuilder str = new StringBuilder();
		//str.append(" select distinct model.pmDepartmentDesignationOfficerId, model.pmOfficer.name," +
		str.append(" select distinct model.pmOfficer.pmOfficerId, model.pmOfficer.name," +
				"  model.pmOfficer.mobileNo,model.pmDepartmentDesignation.pmDepartment.department, " +
				" model.pmDepartmentDesignation.pmOfficerDesignation.designation from PmDepartmentDesignationOfficer model" +
				//"  where model.pmDepartmentDesignationId =:deptDesignationId and " +
				"  where model.pmDepartmentDesignation.pmOfficerDesignationId =:deptDesignationId and " +
				" model.isActive ='Y' and model.pmOfficer.isActive ='Y' " +
				//"  group by model.pmOfficer.pmOfficerId order by model.pmDepartmentDesignation.pmDepartment.department,model.pmDepartmentDesignation.pmOfficerDesignation.designation ");
				"  group by model.pmOfficer.pmOfficerId order by model.pmOfficer.name ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("deptDesignationId", deptDesignationId);
		return query.list();
	}
	
	public List<Object[]> getDeptDesignationOfficerDetailsByDeptAndOffId(Long officerDesignationId,Long pmDepartmentDesignationOfficerId){
		StringBuilder str = new StringBuilder();
		str.append(" select distinct model.pmDepartmentDesignationOfficerId,model.pmDepartmentDesignationId, model.pmOfficer.name," +
				"  model.pmOfficer.mobileNo,model.pmDepartmentDesignation.pmDepartment.department, " +
				" model.pmDepartmentDesignation.pmOfficerDesignation.designation from PmDepartmentDesignationOfficer model" +
				"  where model.pmDepartmentDesignation.pmOfficerDesignationId =:officerDesignationId and model.pmDepartmentDesignationOfficerId =:pmDepartmentDesignationOfficerId and " +
				" model.isActive ='Y' and model.pmOfficer.isActive ='Y' " +
				" order by model.pmDepartmentDesignation.pmDepartment.department,model.pmDepartmentDesignation.pmOfficerDesignation.designation ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("pmDepartmentDesignationOfficerId", pmDepartmentDesignationOfficerId);
		query.setParameter("officerDesignationId", officerDesignationId);
		return query.list();
	}
	
}
