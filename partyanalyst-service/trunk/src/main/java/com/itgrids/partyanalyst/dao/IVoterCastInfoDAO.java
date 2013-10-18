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
	
	public List<Object[]> getCastAndPartyForSelectedLevel(Long userId,Long reportId ,List<Long> ids,Long publicationId);
	
	public List<Object[]> getAllCasteInfoByUserId(Long userId);
	
	public List<Object[]> getTopThreeCasteFoeSelctedLevel(Long id,Long reportId,Long publicationId,Long userId);
	
	public List<Object[]> getTopCasteFoeSelctedLevel(List<Long> ids,Long reportId,Long publicationId,Long userId,Set<Long> casteIds);
	
	public List<Object[]> getTopThreeCasteForPanchayat(Long panchayatId,Long reportId,Long publicationId,Long userId);
	
	public List<VoterCastInfo> getVoterCasteInfoList(Long constituencyId,Long publicationDateId,Long userId);
	
	public List<Object[]> getCasteWiseCountDetails(Long levelId,Long levelValue,Long publicationDateId,Long constituencyId,List<Long> casteId,Long userId);
	
	public List<Object[]> getCasteAvaliableConstituencyes(List<Long> constituencyIds,Long userId);
	
	//public List<Object[]> getPanchayatWiseCasteForSuggestiveModel(Long constituencyId,Long publicationId,Long levelId,Long userId);
}
