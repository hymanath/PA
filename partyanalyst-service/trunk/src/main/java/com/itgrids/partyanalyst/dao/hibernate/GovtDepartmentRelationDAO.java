package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDepartmentRelationDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentRelation;

public class GovtDepartmentRelationDAO extends GenericDaoHibernate<GovtDepartmentRelation, Long> implements IGovtDepartmentRelationDAO{

	public GovtDepartmentRelationDAO() {
		super(GovtDepartmentRelation.class);
	}
	
	public List<Object[]> getAllMainDepartments(){
		
		Query query = getSession().createQuery(" SELECT distinct model.parentGovtDepartment.govtDepartmentId,model.parentGovtDepartment.departmentName " +
				" FROM GovtDepartmentRelation model " +
				" WHERE model.isDeleted = 'N' ");
		
		return query.list();
		
	}

}
