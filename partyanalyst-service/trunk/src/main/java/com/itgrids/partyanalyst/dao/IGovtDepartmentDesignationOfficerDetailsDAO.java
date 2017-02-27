package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficerDetails;

public interface IGovtDepartmentDesignationOfficerDetailsDAO extends GenericDao<GovtDepartmentDesignationOfficerDetails, Long>{

	public List<Object[]> getOfficersByDesignationAndLevel(Long levelId,Long levelValue,Long designationId);
	public List<Long> getDesignationOfficerIds(Long levelId,Long levelValue,Long designationId);
	public List<Object[]> getDeptDesigOfficerIdAndGovtOfficerIdForUserId(Long userId,List<Long> deptIdList, Long locValue, List<Long> locIdList);
	public List<String> getDesignationsForUser(Long userId);
}
