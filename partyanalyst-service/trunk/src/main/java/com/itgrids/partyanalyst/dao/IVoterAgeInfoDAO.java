package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterAgeInfo;

public interface IVoterAgeInfoDAO extends GenericDao<VoterAgeInfo, Long>{
	
	public List<VoterAgeInfo> getVoterAgeInfoByPublicationDateAndReportLevelId(Long reportLevelId, Long reportLevelValue, Long publicationDateId,Long constituencyId);
	
	public List<VoterAgeInfo> getAgewiseVoterDetailsInSpecifiedRangeByAgeRangeId(Long reportLevelId, Long reportLevelValue, Long publicationDateId, Long ageRangeId);
	
	public Long getVoterInfoIdByReportLevelValueAndReportLevelId(Long reportLevelId, Long reportLevelValue, Long ageRangeId);
	
	public Integer deleteVoterAgeInfoByReportLevelIdAndReportLevelValue(Long reportLevelId, List<Long> reportLevelValue, Long publicationDateId);

	public List<VoterAgeInfo> getAgewiseVoterDetailsInAllRange(Long reportLevelId, Set<Long> reportLevelValue, Long publicationDateId,Long constituencyId);
}
