package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.InfluencingPeople;

public interface IInfluencingPeopleDAO extends GenericDao<InfluencingPeople, Long> {
	
	public List<InfluencingPeople> findByHamletId(Long hamletId);
	public List<InfluencingPeople> findByTehsils(String tehsilIds);	

}
