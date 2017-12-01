package com.itgrids.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PetitionWorkDetails;

public interface IPetitionWorkDetailsDAO extends GenericDao<PetitionWorkDetails, Long> {
	public List<Object[]> getWorkLocationDetailsByPetitionMemberId(Set<Long> petitionMemberIdsLst);

}
