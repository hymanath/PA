package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.BoothVillageCensus;

public interface IBoothVillageCensusDAO extends GenericDao<BoothVillageCensus, Long>{

	public BoothVillageCensus findByBoothAndCensusCode(Long boothId, Long cencusCode);
	
}
