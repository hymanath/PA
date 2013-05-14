package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.VoterCastInfo;

public interface IVoterCastInfoDAO extends GenericDao<VoterCastInfo,Long>{
	
	public Integer deleteVotersCastInfoByReportLevelValue(Long reportLevelValue, 
			Long publicationDateId,Long userId);
	
	public List<VoterCastInfo>  getVotersCastInfo(Long levelId,Long levelValue,Long constituencyId,Long publicationId,Long userId);
	
	public Long getRecordsCountToCheckDataPresent(Long constituencyId);
	
	public List<VoterCastInfo> getVotersCastInfoByMultipleLevelValues(Long levelId,Set<Long> levelValues,Long constituencyId,Long publicationId,Long userId);
	
	public void saveAllObjects(List<VoterCastInfo> voterCastInfos);
	
	public Long  getVotersCastCount(Long levelId,Long levelValue,Long constituencyId,Long publicationId,Long userId);
	
	public void flushAndclearSession();
	
	public List<VoterCastInfo> getVotersCastInfoByMultipleLevelValuesAndCastIds(Long levelId,Set<Long> levelValues,List<Long> castStateIds,Long constituencyId,Long publicationId,Long userId);
	
	public List<Object[]> getCastAndPartyForSelectedLevel(Long userId,Long reportId ,List<Long> ids);
}
