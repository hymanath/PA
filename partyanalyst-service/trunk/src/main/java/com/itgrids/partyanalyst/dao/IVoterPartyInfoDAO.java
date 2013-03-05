package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterPartyInfo;

public interface IVoterPartyInfoDAO extends GenericDao<VoterPartyInfo, Long>{
	
	public Integer deleteVotersPartyInfoByConstituencyId(Long reportLevelId, Long constituencyId, 
			Long publicationDateId);
}
