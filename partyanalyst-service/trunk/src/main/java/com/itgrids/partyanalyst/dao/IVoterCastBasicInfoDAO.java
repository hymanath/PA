package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterCastBasicInfo;

public interface IVoterCastBasicInfoDAO extends GenericDao<VoterCastBasicInfo, Long>{
	
	public Integer deleteVotersCastInfoByReportLevelValue(Long reportLevelValue, Long publicationDateId);
}
