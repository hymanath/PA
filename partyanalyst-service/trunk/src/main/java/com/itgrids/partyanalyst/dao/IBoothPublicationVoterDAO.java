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
	public List getVotersCountForPanchayat(Long panchayatId,Long publicationDateId);
	public List<Object[]> getVotersCountByPublicationId(String type,Long id,Long publicationDateId);
	public List<Object[]> getVotersCountForPanchayatByPublicationId(Long panchayatId,Long publicationDateId);
	public List<Object[]> getVotersCountFromLocalElectionBody(Long assemblyLclElecBodyId,Long publicationDateId);
	public List<Object[]> getPublicationDetailsBasedOnConstituency(Long constituencyId);
	
	public List<Object[]> findImpFamilesBasedOnPanchayat(Long PanchayatId,Long publicationDateId);
	
	public List<Object[]> findImpFamilesBasedOnConstituencyId(Long constituencyId,Long publicationDateId);
	
	public List<Object[]> findImpFamilesBasedOnBoothId(Long boothId,Long publicationDateId);

}
