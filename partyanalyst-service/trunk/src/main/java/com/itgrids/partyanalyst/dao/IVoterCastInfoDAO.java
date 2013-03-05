package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.VoterCastInfo;

public interface IVoterCastInfoDAO extends GenericDao<VoterCastInfo,Long>{
	
	public Integer deleteVotersCastInfoByReportLevelValue(Long reportLevelId, Long reportLevelValue, 
			Long publicationDateId);
}
