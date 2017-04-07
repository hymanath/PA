package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficerNew;

public interface IGovtDepartmentDesignationOfficerNewDAO extends GenericDao<GovtDepartmentDesignationOfficerNew, Long> {

	public List<Object[]> getDeptAndDesignationNames(Long govtDepDesigOffcrId);
}
