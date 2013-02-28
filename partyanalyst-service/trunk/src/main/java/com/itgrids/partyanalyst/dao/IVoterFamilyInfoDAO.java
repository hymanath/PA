package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterFamilyInfo;

public interface IVoterFamilyInfoDAO extends GenericDao<VoterFamilyInfo, Long>{

	public List<VoterFamilyInfo> getVoterFamilyDetails(Long reportLevelId, Long reportLevelValue, Long publicationDateId,Long constituencyId);
	
	public Integer deleteVoterFamilyDetByReportLevelValAndVoterAgeRange(Long reportLevelId, List<Long> reportLevelValue, Long publicationDateId);
	
	public List getTotalFamiliesCountByReportLevelValue(Long reportLevelId, Long reportLevelValue, Long publicationDateId);
	
	public List<VoterFamilyInfo> getMultipleVoterFamilyDetails(Long reportLevelId, Set<Long> reportLevelValues, Long publicationDateId,Long constituencyId);
}
