package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficerNew;

public interface IGovtDepartmentDesignationOfficerNewDAO extends GenericDao<GovtDepartmentDesignationOfficerNew, Long> {

	public List<Object[]> getDeptAndDesignationNames(Long govtDepDesigOffcrId);
	public List<Object[]> getSubOrdinateLevels(Long govtDepartmentDesignationId);
	public List<Long> getGovtDepartmentDesignationOfficer(Long levelId,List<Long> levelValues,Long subDesignationId);
	public List<Long> getGovtDepartmentDesinationOfficerId(Long designationId,Long levelId,List<Long> levelValues,Long userId);
	public Long getUserIdCount(Long userId);
}
