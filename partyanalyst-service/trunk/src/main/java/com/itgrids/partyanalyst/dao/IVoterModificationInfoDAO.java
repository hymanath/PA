package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterModificationInfo;

public interface IVoterModificationInfoDAO extends GenericDao<VoterModificationInfo, Long>{
	
	public Long getVoterModificationInfoIdByReportLevelValue(Long reportLevelId, Long reportLevelValue, Long publicationDateId,Long voterStatusId, Long constituencyId);
	
	public List<Object[]> getVoterModificationReportDetailsByReportLevelId(
			Long reportLevelId, List<Long> reportLevelValues,
			Long constituencyId,List<Long> publicationIdsList);
	
	
	public Integer deletevotermodificationInfoByConstituencyId(Long constituencyId,Long publicationId);
	
	public List<Long> getVoterModificationInfoIds(Long constituencyId,Long publicationId);
	
	public List<Object[]> getGenderWiseVoterModificationsBetweenPublications(Long locationLvl,Long locationValue,Long constituencyId,List<Long> publicationIdsList);
	
	public List<Object[]> getGenderWiseVoterModificationsForEachPublication(Long locationLvl,Long locationValue,Long constituencyId,List<Long> publicationIdsList);
	
	public List<Object[]> getVoterModificationGenderDetailsByLocationValuesList(List<Long> locationValuesList, List<Long> publicationIdsList, Long constituencyId, Long reportLevelId);

}
