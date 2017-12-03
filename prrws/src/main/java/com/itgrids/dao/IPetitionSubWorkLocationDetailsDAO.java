package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PetitionSubWorkLocationDetails;

public interface IPetitionSubWorkLocationDetailsDAO extends GenericDao<PetitionSubWorkLocationDetails, Long> {
	public List<PetitionSubWorkLocationDetails> getSubWorkDetailsByWorkDetailsIdsList(List<Long> workDetailsIdsList);
}
