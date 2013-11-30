package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.model.VoterDataAvailableConstituencies;

public interface IVoterDataAvailableConstituenciesDAO extends GenericDao<VoterDataAvailableConstituencies,Long>{
	public List<Object[]> getConstituencies();
	
	public List<Object[]> getPublicationDatesBasedOnConstituency(Long constituencyId);
}
