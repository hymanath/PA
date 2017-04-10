package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationHierarchyDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignationHierarchy;

public class GovtDepartmentDesignationHierarchyDAO extends GenericDaoHibernate<GovtDepartmentDesignationHierarchy, Long> implements IGovtDepartmentDesignationHierarchyDAO {

	public GovtDepartmentDesignationHierarchyDAO(){
		super(GovtDepartmentDesignationHierarchy.class);
	}
	
	public List<Long> getParentDepartment(Long designationId){
		Query query = getSession().createQuery(" select model.parentDesignationId "
				+ " from GovtDepartmentDesignationHierarchy model "
				+ " where model.isDeleted='N' and model.subDesignationId=:designationId ");
		query.setParameter("designationId", designationId);
		return query.list();
	}
}
