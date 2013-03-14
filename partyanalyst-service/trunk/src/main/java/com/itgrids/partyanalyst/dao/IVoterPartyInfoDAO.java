package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterPartyInfo;

public interface IVoterPartyInfoDAO extends GenericDao<VoterPartyInfo, Long>{
	
	public Integer deleteVotersPartyInfoByConstituencyId(Long constituencyId, 
			Long publicationDateId,Long userId);
	
	public List<VoterPartyInfo>  getVotersPartyInfo(Long levelId,Long levelValue,Long constituencyId,Long publicationId,Long userId);
	
	public Long getRecordsCountToCheckDataPresent(Long constituencyId);
	
	public void saveAllObjects(List<VoterPartyInfo> voterPartyInfos);
}
