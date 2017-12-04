package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PetitionGrant;

public interface IPetitionGrantDAO extends GenericDao<PetitionGrant, Long> {
	public List<Object[]> getPetitionGrantList();

}
