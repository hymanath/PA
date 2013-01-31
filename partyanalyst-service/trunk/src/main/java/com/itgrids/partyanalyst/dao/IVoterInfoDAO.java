package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterInfo;

public interface IVoterInfoDAO extends GenericDao<VoterInfo, Long>{
	
	public List<VoterInfo> getVotersCount(Long reportLevelId, Long reportLevelValue, Long publicationDateId);
	
	public Long getTotalVotersByReportLevelValue(Long reportLevelId, Long reportLevelValue, Long publicationDateId);
	
	public Integer deleteVotersInfoByReportLevelValue(Long reportLevelId, Long reportLevelValue, Long publicationDateId);
	
	public Long getVotersCountInALocation(Long reportLevelId, Long reportLevelValue, Long publicationDateId);
	
}
