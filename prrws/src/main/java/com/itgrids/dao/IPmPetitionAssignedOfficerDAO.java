package com.itgrids.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.dto.InputVO;
import com.itgrids.model.PmPetitionAssignedOfficer;

public interface IPmPetitionAssignedOfficerDAO extends GenericDao<PmPetitionAssignedOfficer, Long> {
	public List<Object[]> getAssignedPetitionforPetitionDeptDesignationOfficer(List<Long> pmDeptDesignationOfficerIdsList);
	public List<Long> getAssingedOfficerStatusIdsList(List<Long> deptDesignationIdsList);
	public List<Object[]> getActionTypeDetailsForDeptDesiOfficerId(List<Long> deptDesignationOfficerIdsList,Long petitionId);
	public List<Object[]> getPetitionAssignedPrinciplSecretoryDetails(Long petitionId);
	public List<Object[]> getOfficerDetailsForOfficerIdsList(List<Long> assignedToOfficerIdsList);
	public List<Object[]> getLatestUpdatedDetailsOfPetition(Set<Long> petitionIds);
	public List<Object[]> getPmOfficerAssignedPetitionDetails(InputVO inputVO);
}
