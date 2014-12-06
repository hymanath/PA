package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyPresidents;

public interface IPartyPresidentsDAO extends GenericDao<PartyPresidents, Long>{
	
	public List<Long> getMobileNumebrsBylocation(Long constituencyId, Long locatonId, String searchType);
	
}
