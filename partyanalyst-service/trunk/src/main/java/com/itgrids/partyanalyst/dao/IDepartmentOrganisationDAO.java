/* 
 * Copyright (c) 2011 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February, 2011
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DepartmentOrganisation;

/**
 * @author Sai Krishna
 *
 */
public interface IDepartmentOrganisationDAO extends GenericDao<DepartmentOrganisation, Long> {

	@SuppressWarnings("unchecked")
	public List getDepartmentCategorysBasedOnProblemResolvingRegionScope(Long scopeId);
	
	@SuppressWarnings("unchecked")
	public List getDepartmentOrganisationsForADepartment(Long departmentId);
	
	@SuppressWarnings("unchecked")
	public List getDepartmentOrganisationsForADepartmentByScope(Long departmentId,Long scopeId);
	
	@SuppressWarnings("unchecked")
	public List getDepartmentsBasedOnProblemResolvingDepartmentScope(Long scopeId);
}
