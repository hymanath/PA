package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmPetitionAssignedOfficer;

public interface IPmPetitionAssignedOfficerDAO extends GenericDao<PmPetitionAssignedOfficer, Long> {
	public List<Object[]> getAssignedPetitionforPetitionDeptDesignationOfficer(List<Long> pmDeptDesignationOfficerIdsList);
}
