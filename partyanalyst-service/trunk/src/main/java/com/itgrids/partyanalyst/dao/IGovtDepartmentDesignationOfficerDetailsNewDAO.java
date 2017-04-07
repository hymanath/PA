package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficerDetailsNew;

public interface IGovtDepartmentDesignationOfficerDetailsNewDAO extends GenericDao<GovtDepartmentDesignationOfficerDetailsNew, Long> {

	public List<Object[]> getGovtDeptDesigOffrDetlsIdAndGovtOfcrId(Long userId,List<Long> levelValues , Long levelId);
}
