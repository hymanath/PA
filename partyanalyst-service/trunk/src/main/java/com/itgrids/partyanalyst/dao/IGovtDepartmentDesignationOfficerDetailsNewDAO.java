package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficerDetailsNew;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficerNew;

public interface IGovtDepartmentDesignationOfficerDetailsNewDAO extends GenericDao<GovtDepartmentDesignationOfficerDetailsNew, Long> {

	public List<Object[]> getGovtDeptDesigOffrDetlsIdAndGovtOfcrId(Long userId,List<Long> levelValues , Long levelId);
	public List<GovtDepartmentDesignationOfficerNew> getDesignationOfficerIdsNew(Long levelId,Long levelValue,Long designationId,Long officerId);
	public List<Object[]> getDesignationsForDepartmentAndLevelLocation(Long govtDepartmentId,Long levelId,Long levelValue);
	public List<Object[]> getOfficersByDesignationAndLevel(Long levelId,Long levelValue,Long designationId);
	
	public List<Object[]> getOldDesignationsForDepartmentAndLevelLocation(Long govtDepartmentId,Long levelId,Long levelValue);
	public List<Object[]> getOldOfficersByDesignationAndLevel(Long levelId,Long levelValue,Long designationId);
	public List<Long> getOldDesignationOfficerIdsNew(Long levelId,Long levelValue,Long designationId,Long officerId);
	public List<Object[]> getDesignationsNameForUser(Long userId);
	public List<Long> getGovtDeptDesigOfficerIdListByUserId(Long userId);
	public List<Long> getGovtDeptDesigIdListByUserId(Long userId);
	public List<Long> getGovtDeptScopeIdsForUserId(Long userId);
	public List<Object[]> getGovtAllDepartmentDetails();
	public List<Long> getOldDesignationOfficerIds(Long levelId,Long levelValue,Long designationId,Long officerId);
	public List<Object[]> getDesigNameForUser(Long userId);
	public List<Object[]> getNewDesignationsForDepartmentAndLevelLocation(Long govtDepartmentId,Long levelId,Long levelValue);
	public List<Object[]> getNewOfficersByDesignationAndLevel(Long levelId,Long levelValue,Long designationId);
	public List<String> getHigherOfficerMobileNums(List<Long> designationIds,Long locationTypeId,List<Long> levelValuesList);
	public List<Long> getuserIdDtlsForDesignationOfficerId(Long assignedOfficerId);
	public List<Object[]> getLocationNameByAssignedOficer(Long officerId);
	public List<Object[]> getDeptListForGreivanceReport(Set<Long> deptIdList);
	public List<Object[]> getGovtDepaDesigIdForLoginOfficer(Long userId, Long govtDeptId);
	public List<Object[]> getGovtDepaDesigOfficerDtls(List<Long> govtDepartmentDesignationIds, Long govtDepartmentScopeId, Long levelValue);
	public List<String> getGovtOfficerMobileNums(Long locationid,String locationType);
	public List<Object[]> getDesigAndDepartForUser(List<Long> userIds);
	public List<Object[]> getLoginUserDetails(Long userId);
	public List<Object[]> getDesignationNameForUsers(List<Long> userIdsList);
	public List<Long> getNewDesignationOfficerIdsNew(Long levelId,Long levelValue,Long designationId,Long officerId);
	public List<String> getOfficerMobilenNo(Long userId);
}
