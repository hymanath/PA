package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterCastBasicInfo;

public interface IVoterCastBasicInfoDAO extends GenericDao<VoterCastBasicInfo, Long>{
	
	public Integer deleteVotersCastInfoByReportLevelValue(Long reportLevelValue,Long publicationDateId,Long userId);
	
	public List<VoterCastBasicInfo>  getVotersCastBasicInfo(Long levelId,Long levelValue,Long constituencyId,Long publicationId,Long userId);
	
	public void saveAllObjects(List<VoterCastBasicInfo> voterCastBasicInfos);
	
	public List<Object[]> getToatlVotersForSelectedLevl(List<Long> levelValues,Long userId,Long publicationId,Long levelId,Long constituencyId);
	
	public List<VoterCastBasicInfo> getVoterCastBasicInfoList(Long constituencyId,Long publicationDateId, Long userId);
	
}
