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
		Query query = getSession().createQuery(" select model.departmentId, model.deptName from Departments model where model.postTypeId = :postType ");
		query.setParameter("postType", postType);
		return query.list();
	}
}
