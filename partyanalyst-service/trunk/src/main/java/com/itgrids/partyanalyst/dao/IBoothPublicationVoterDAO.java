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
	
	public Long getTotalVotersCount(Long id,Long publicationDateId,String type);
	
	public Long getVotersCountForLocalElectionBody(Long assemblyLclElecBodyId,Long publicationDateId);
	
	public List<Object[]> findAllImpFamiles(Long id,Long publicationDateId,String type,String queryString);

	public List<Object[]> getVotersImpFamilesForLocalElectionBody(Long assemblyLclElecBodyId,Long publicationDateId,String queryString);
	
	public Long findTotalVotersCastInfoByConstituencyAndPublicationDate(Long constituencyId, Long publicationDate);
	
	public List findVotersCastInfoByMandalAndPublicationDate(Long mandalId, Long publicationDate);
	
	public List findVotersCastInfoByPanchayatAndPublicationDate(Long panchayatId, Long publicationDateId);
	
	public List findVotersCastInfoByBoothIdAndPublicationDate(Long boothId, Long publicationDateId);
	
	public List getGenderWiseCountInConstituency(Long constituencyId, Long publicationDate);
	
	public List<Object[]> getAgewiseVoterDetailsInSpecifiedRangeByGenderAndConstituncyId(
			 Long constituencyId ,Long publicationDateId, Long startAge,
			Long endAge);

	public List<Object[]> getAgewiseVoterDetailsInSpecifiedRangeByGenderAndMandalId(
			Long tehsilId, Long publicationDateId, Long startAge,
			Long endAge);
	
	public List<Object[]> getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationId(
			Long panchayatId, Long publicationDateId , Long startAge, Long endAge);
	
	public List<Object[]> getVotersCountDetailsInSpecifiedRangeForBoothByPublicationDateId(
			Long boothId, Long publicationDateId, Long startAge, Long endAge) ;
	
	public List<Object[]> getVotersCountDetailsInSpecifiedRangeForLocalElectionBodyByPublicationDateId(
			Long localElectionBodyId, Long publicationDateId, Long startAge, Long endAge);



	public List<Object[]> getImpFamilesForPanchayatByPublicationId(Long panchayatId,Long publicationDateId,String queryString);
}
