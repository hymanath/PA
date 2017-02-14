package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficer;

public interface IGovtDepartmentDesignationOfficerDAO extends GenericDao<GovtDepartmentDesignationOfficer, Long>{

	public List<Long> getDesignationOfficerIds(Long levelId,Long levelValue,Long designationId);
}
