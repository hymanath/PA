package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmPetitionType;

public interface IPmPetitionTypeDAO extends GenericDao<PmPetitionType, Long> {
	public List<Object[]> getPmPetitionList();

}
