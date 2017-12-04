package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PetitionStatus;

public interface IPetitionStatusDAO extends GenericDao<PetitionStatus, Long> {
	public List<Object[]> getPetitionStatusList();

}
