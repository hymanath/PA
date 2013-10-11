package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ConstituencyCensusDetails;

public interface IConstituencyCensusDetailsDAO extends GenericDao<ConstituencyCensusDetails,Long>{
	
	public List<Object[]> findConstituencyWiseCensusDetails(Long stateId,Long constituencyId,Long year);
	
	public List<Long> checkForConstituencyExistance(Long constituencyId);
	
	public List<Object[]> getConstituencyIdsAndPercentages(String censusParam,Long stateId);
	
	public List<Object[]> getConstituencyIdsAndPercentagesOfADistrict(String censusParam,List<Long> constituencyIds);
	
	public List<ConstituencyCensusDetails> getCensusConstituencyByConstituencyId(Long constituencyId);
	
	public List<ConstituencyCensusDetails> getCensusConstituencyByConstituencyIdAndYears(
			Long constituencyId, List<Long> years);

}
