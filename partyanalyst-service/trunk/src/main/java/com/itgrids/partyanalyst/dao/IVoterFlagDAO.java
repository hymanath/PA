package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterFlag;

public interface IVoterFlagDAO extends GenericDao<VoterFlag, Long>{
	public Integer deleteVoterFlag(Long flagId);
	
	public List<Object[]> getFlagWiseVotersCountByLocationId(Long locationId,Long constituencyId,String locationType,Long publicationDateId);
	
	public List<Object[]> getFlagWiseVotersCountByLocationIdForHamlet(Long locationId,Long constituencyId,String locationType,Long publicationDateId);
}
