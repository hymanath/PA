package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficerDetails;
import com.itgrids.partyanalyst.model.GovtDepartmentLevelDetails;

public interface IGovtDepartmentLevelDetailsDAO extends GenericDao<GovtDepartmentLevelDetails, Long>{
	public List<Object[]> getLocationNamesByDepmntId(Long departmentId);
}
