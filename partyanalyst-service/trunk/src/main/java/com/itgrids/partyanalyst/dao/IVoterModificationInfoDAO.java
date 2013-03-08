package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterModificationInfo;

public interface IVoterModificationInfoDAO extends GenericDao<VoterModificationInfo, Long>{
	
	
	public Integer deletevotermodificationInfoByConstituencyId(Long constituencyId,Long publicationId);
	
	public List<Long> getVoterModificationInfoIds(Long constituencyId,Long publicationId);
}
