package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDepartmentsDAO;
import com.itgrids.partyanalyst.model.Departments;

public class DepartmentsDAO extends GenericDaoHibernate<Departments, Long> implements IDepartmentsDAO{

	public DepartmentsDAO() {
		super(Departments.class);
		
	}

	public List<Object[]> getDepartments(Long postType){
		Query query = getSession().createQuery(" select model.departmentId, model.deptName from Departments model where model.postTypeId = :postType order by model.deptName  ");
		query.setParameter("postType", postType);
		return query.list();
	}
	
	public List<Object[]> getDepartmentByIdsList(Long postType,List<Long> deptIds){
		Query query = getSession().createQuery(" select distinct model.departmentId, model.deptName from Departments model where model.postTypeId = :postType and " +
				" model.departmentId in (:deptIds) order by model.deptName ");
		query.setParameter("postType", postType);
		query.setParameterList("deptIds", deptIds);
		return query.list();
	}
}
