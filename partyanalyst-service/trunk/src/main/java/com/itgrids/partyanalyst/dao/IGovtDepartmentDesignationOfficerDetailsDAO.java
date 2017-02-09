package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficerDetails;

public interface IGovtDepartmentDesignationOfficerDetailsDAO extends GenericDao<GovtDepartmentDesignationOfficerDetails, Long>{

	public List<Object[]> getOfficersByDesignationAndLevel(Long levelId,Long levelValue,Long designationId);
}
