package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.BoothVillageCensus;

public interface IBoothVillageCensusDAO extends GenericDao<BoothVillageCensus, Long>{

	public List<BoothVillageCensus> findByBoothAndCensusCode(Long boothId, Long cencusCode);
	
}
