package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtDepartmentScope;

public interface IGovtDepartmentScopeDAO extends GenericDao<GovtDepartmentScope, Long>{
	public List<Object[]> getgovtDepatScopeDetails(Long scopeId);
	public List<Object[]> getGovtDepartmenttScopeDetails(Long scopeId);
	public List<Object[]> getGovtDeptScopeDetails();
	public List<Object[]> getGovtDepartmenttScopeDetailsBasedOnScopeIds(List<Long> scopeIds);
	
}
