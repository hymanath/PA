package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PetitionRefferer;

public interface IPetitionReffererDAO extends GenericDao<PetitionRefferer, Long> {
	public List<Object[]> getPetitionReffererDetailsByMemberId(Long requestMemberId);
}
