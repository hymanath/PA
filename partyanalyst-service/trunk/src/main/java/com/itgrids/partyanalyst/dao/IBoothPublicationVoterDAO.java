package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.BoothPublicationVoter;
import com.itgrids.partyanalyst.model.Voter;

public interface IBoothPublicationVoterDAO extends
		GenericDao<BoothPublicationVoter, Long> {
	
	public List<BoothPublicationVoter> checkForVoterExistenceInBoothPublicationVoter(
			Long boothId, Long voterId);
	
	public List<Voter> getVotersDetailsByBoothId(Long boothId, Integer startIndex,
			Integer maxRecords, String order, String columnName);
	
	public List<Voter> getVotersDetailsForPanchayatByPublicationId(
			Long panchayatId, Long publicationDateId, Integer startIndex,
			Integer maxRecords, String order, String columnName);
	
	public List getVotersCountByBoothId(Long boothId);
	public List getVotersCountForPanchayatByPublicationId(Long panchayatId,Long publicationDateId);

}
