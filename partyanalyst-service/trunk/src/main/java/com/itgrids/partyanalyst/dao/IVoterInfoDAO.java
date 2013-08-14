package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterInfo;

public interface IVoterInfoDAO extends GenericDao<VoterInfo, Long>{
	
	public List<VoterInfo> getVotersCount(Long reportLevelId, Long reportLevelValue, Long publicationDateId,Long constituencyId);
	
	public Long getTotalVotersByReportLevelValue(Long reportLevelId, Long reportLevelValue, Long publicationDateId,Long constituencyId);
	
	public Integer deleteVotersInfoByReportLevelValue(Long reportLevelId, List<Long> reportLevelValue, Long publicationDateId);
	
	public Long getVotersCountInALocation(Long reportLevelId, Long reportLevelValue, Long publicationDateId,Long constituencyId);
	
	public Long getFamiliesCountInALocation(Long reportLevelId, Long reportLevelValue, Long publicationDateId,Long constituencyId);
		
	public List<VoterInfo> getVotersMultipleCount(Long reportLevelId, Set<Long> reportLevelValues, Long publicationDateId,Long constituencyId);
	
	public List<Object[]> getVoterInfoByPublicationDateIds(Long reportLevelId, Long reportLevelValue, List<Long> publicationDateIds);
	
	public Integer deleteVotersInfoByConstituencyId(Long constituencyId, Long publicationDateId);
	
	public List<Object[]> getTotalVotersByPublicationDateIdsList(List<Long> publicationDateIdsList, Long reportLevelId, Long locationValue, Long constituencyId);
	
	public List<Object[]> getPublicationDetailsBasedOnConstituencyId(Long constituencyId);
	
	public List<Long> getConstituencyIds();
	
	public List<Long> getVoterPublicationIdsBetweenTwoPublications(Long fromPublicationDateId, Long toPublicationDateId);
	
	public List<Long> getPreviousPublicationIds(Long publicationDateId);
	
	public List<Object[]> getPanchayatWiseVotersCount(Long constituencyId, Long publicationDateId);
	
	public List<Long> getReportLevelValueByConstituencyId(Long constituencyId,Long publicationDateId,Long reportLevelValue);

	public List<Object[]> getVoterDetailedCountByLocation(Long reportLvlId,Long reportLevelValue,Long publicationDateId,Long constituencyId);
	
	public List<Long> getNONURBANConstituencyIds(Long electionTypeId,Long electionYear,Long countryId);
	
	public List<VoterInfo> getVoterInfoList(Long constituencyId);

}
