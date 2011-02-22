/* 
 * Copyright (c) 2011 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February, 2011
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDepartmentOrganisationDAO;
import com.itgrids.partyanalyst.model.DepartmentOrganisation;

/**
 * @author Sai Krishna
 *
 */
public class DepartmentOrganisationDAO extends GenericDaoHibernate<DepartmentOrganisation, Long> implements IDepartmentOrganisationDAO{

	public DepartmentOrganisationDAO() {
		super(DepartmentOrganisation.class);
	}

	@SuppressWarnings("unchecked")
	public List getDepartmentCategorysBasedOnProblemResolvingRegionScope(Long scopeId){
		
		return getHibernateTemplate().find("select model.problemDepartmentCategory.problemSourceScopeConcernedDepartmentId,"+
				"model.problemDepartmentCategory.department from DepartmentOrganisation model where model.problemSourceScope."+
				"problemSourceScopeId = ? order by model.problemDepartmentCategory.department asc", scopeId);
	}
	
	@SuppressWarnings("unchecked")
	public List getDepartmentOrganisationsForADepartment(Long departmentId){
		
		return getHibernateTemplate().find("select model.departmentOrganisationId,model.organisationName from DepartmentOrganisation model "+
				"where model.problemDepartmentCategory.problemSourceScopeConcernedDepartmentId = ? order by model.organisationName asc",departmentId);
	}

	@SuppressWarnings("unchecked")
	public List getDepartmentOrganisationsForADepartmentByScope(
			Long departmentId, Long scopeId) {
		
		Object[] params = {departmentId,scopeId};
		return getHibernateTemplate().find("select model.departmentOrganisationId,model.organisationName from DepartmentOrganisation model "+
				"where model.problemDepartmentCategory.problemSourceScopeConcernedDepartmentId = ? and model.problemSourceScope.problemSourceScopeId = ? "+
				"order by model.organisationName asc",params);
	}

	@SuppressWarnings("unchecked")
	public List getDepartmentsBasedOnProblemResolvingDepartmentScope(
			Long scopeId) {
		
		return getHibernateTemplate().find("select model.departmentOrganisationId,model.organisationName from "+
				"DepartmentOrganisation model where model.problemSourceScope.problemSourceScopeId = ? order by "+
				"model.organisationName asc",scopeId);
	}
}
