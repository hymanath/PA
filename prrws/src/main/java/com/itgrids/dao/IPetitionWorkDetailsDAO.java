package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PetitionWorkDetails;

public interface IPetitionWorkDetailsDAO extends GenericDao<PetitionWorkDetails, Long> {
	public List<Object[]> getWorkLocationDetailsByPetitionMemberId(List<Long> petitionMemberIdsLst);

}
