package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficerDetails;

public interface IGovtDepartmentDesignationOfficerDetailsDAO extends GenericDao<GovtDepartmentDesignationOfficerDetails, Long>{

	public List<Object[]> getOfficersByDesignationAndLevel(Long levelId,Long levelValue,Long designationId);
	public List<Long> getDesignationOfficerIds(Long levelId,Long levelValue,Long designationId);
	public List<Object[]> getDeptDesigOfficerIdAndGovtOfficerIdForUserId(Long userId,List<Long> deptIdList, Long locValue, List<Long> locIdList);
	public List<String> getDesignationsForUser(Long userId);
	public List<Long> getDesignationOfficerIdsNew(Long levelId,Long levelValue,Long designationId,Long officerId);
	public List<Object[]> getDesignationsForDepartmentAndLevelLocation(Long govtDepartmentId,Long levelId,Long levelValue);
	public List<Object[]> getLocationInfoOfUser(Long userId);
	public List<Long> getDesignationInfoForUser(Long userId);
	public List<String> getHigherOfficerMobileNums(List<Long> designationId);
	public List<String> getOldHigherOfficerMobileNums(List<Long> designationIds);
}
