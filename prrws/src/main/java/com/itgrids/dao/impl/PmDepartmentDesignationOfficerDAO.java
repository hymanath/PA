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
		str.append(" select distinct model.pmDepartmentDesignationOfficerId, model.pmOfficer.name, model.pmOfficer.mobileNo from PmDepartmentDesignationOfficer model where model.pmDepartmentDesignationId =:deptDesignationId and " +
				" model.isActive ='Y' and model.pmOfficer.isActive ='Y' order by  ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("deptDesignationId", deptDesignationId);
		return query.list();
	}
}
