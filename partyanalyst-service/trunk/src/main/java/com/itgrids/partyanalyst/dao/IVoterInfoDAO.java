package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterInfo;

public interface IVoterInfoDAO extends GenericDao<VoterInfo, Long>{
	
	public List<VoterInfo> getVotersCount(Long reportLevelId, Long reportLevelValue, Long publicationDateId);
	
	public Long getTotalVotersByReportLevelValue(Long reportLevelId, Long reportLevelValue, Long publicationDateId);
	
	public Integer deleteVotersInfoByReportLevelValue(Long reportLevelId, List<Long> reportLevelValue, Long publicationDateId);
	
	public Long getVotersCountInALocation(Long reportLevelId, Long reportLevelValue, Long publicationDateId);
	
	public Long getFamiliesCountInALocation(Long reportLevelId, Long reportLevelValue, Long publicationDateId);
		
	public List<VoterInfo> getVotersMultipleCount(Long reportLevelId, Set<Long> reportLevelValues, Long publicationDateId);
	
	public List<Object[]> getVoterInfoByPublicationDateIds(Long reportLevelId, Long reportLevelValue, List<Long> publicationDateIds);
	
}
