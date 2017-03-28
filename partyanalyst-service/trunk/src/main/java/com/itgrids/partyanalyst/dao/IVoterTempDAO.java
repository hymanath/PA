package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterTemp;

public interface IVoterTempDAO extends GenericDao<VoterTemp,Long>{
	
	public List<VoterTemp> getVotersInAConstituency(Long constituencyId,Integer startIndex, Integer maxResults);
	
	public List<Object[]> getConstituencyList();
	
	public List<Object> getconstituencyNames();
	
	public List<Object[]> getConstituencies(List<String> constituencyNames);

}
