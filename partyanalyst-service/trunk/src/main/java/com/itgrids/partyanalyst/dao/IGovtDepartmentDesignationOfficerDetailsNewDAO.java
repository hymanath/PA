package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficerDetailsNew;

public interface IGovtDepartmentDesignationOfficerDetailsNewDAO extends GenericDao<GovtDepartmentDesignationOfficerDetailsNew, Long> {

	public List<Object[]> getGovtDeptDesigOffrDetlsIdAndGovtOfcrId(Long userId,List<Long> levelValues , Long levelId);
	public List<Long> getDesignationOfficerIdsNew(Long levelId,Long levelValue,Long designationId,Long officerId);
	public List<Object[]> getDesignationsForDepartmentAndLevelLocation(Long govtDepartmentId,Long levelId,Long levelValue);
	public List<Object[]> getOfficersByDesignationAndLevel(Long levelId,Long levelValue,Long designationId);
	
	public List<Object[]> getOldDesignationsForDepartmentAndLevelLocation(Long govtDepartmentId,Long levelId,Long levelValue);
	public List<Object[]> getOldOfficersByDesignationAndLevel(Long levelId,Long levelValue,Long designationId);
	public List<Long> getOldDesignationOfficerIdsNew(Long levelId,Long levelValue,Long designationId,Long officerId);
}
