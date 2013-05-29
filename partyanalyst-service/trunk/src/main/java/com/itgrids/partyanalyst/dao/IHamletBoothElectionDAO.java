package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.HamletBoothElection;

public interface IHamletBoothElectionDAO extends GenericDao<HamletBoothElection, Long>{

	public List<Long> findByHamletAndBoothConstituencyElection(Long hamletId,
			Long boothConstituencyElectionId) ;
	
	public List findPanchayathsWiseBoothsAndHamletsDataInTehsilForElection(Long tehsilId, Long electionId);
	
	public List findPanchayathBoothIdsInTehsilForElection(Long tehsilId, Long electionId);
	
	public List<Object[]> getPanchayatWiseBoothDetails(Long tehsilId,Long electionId);
	
	public List<Object[]> findPanchayatWiseVotingTrendsForATehsil(Long tehsilId, String electionIds);
	
	public List<Object[]> getVotersCountInAPanchayat(Long electionId,Long panchayatId);
		
	public List<Long> getBoothIdsByPanchayatId(Long PanchayatId, Long publicationDateId);
	
	public List<Object[]> getBoothsInAPanchayat(Long electionId,Long panchayatId);
	
	public List<Object[]> getPanchayatByBoothElec(Long electionId,String partNo,Long constituencyId);
	
	public List<Object[]> findElectionsHappendInAPanchayat(Long panchayatId, String electionType);
	
	public List<Object[]> findAllElectionsHappendInAPanchayat(Long panchayatId);
	
	public List<Object[]> findAllElectionsHappendInAMandal(Long mandalId,List<Long> constituencyIds);
	
	public List<Long> getBoothIdsByMandalId(Long mandalId, Long electionId);
	
	public List<Object[]> findAllElectionsHappendInAConstituency(Long constituencyId);
	
	public List<Long> getBoothIdsByConstituencyId(Long constituencyId, Long electionId);
	
	public List<Object[]> findAllElectionsHappendInALocalElectionBody(Long localElectionBodyId);
	
	public List<Object[]> getPanchayatBoothDetailsByPanchayat(Long tehsilId,Long electionId);
	
	public List findPolledVotesInAllElectionsOfPanchayat(Long panchayatId);
	
	public List<Object[]> getBoothDetailsByPanchayat(Long panchayatId,Long electionId);
	
	public List<Object[]> getElectionsHappendinPanchayat(Long panchayatId);
	
	public List<Object[]> getPartiesParticipatedInAllElectionOfAPanchayat(Long panchayatId);
	
	public List<Object[]> getPanchayatBoothDetailsByPanchayatIds(List<Long> panchayatIds,List<Long> electionIds,List<Long> partyIds);
	
	public List<Object[]> getPanchayatBoothDetailsByPanchayat(List<Long> panchayatIds,List<Long> electionIds);
	
	public List<Object[]> getBoothWiseVotersCountInAPanchayat(Long electionId,Long panchayatId);
	
	public List<Object[]> getBoothIdsByMandalIdsElectionIds(List<Long> mandalIds, List<Long> electionIds,Long constituencyId);
	
	public List<Object[]> getParticipatedPartiesByEleId(Long panchayatId,Long electionId);
	
	public List<Long> getPanchayatIdsByEleIdAndMandalIdsList(List<Long> mandalIdsList, Long electionId);
	
	public List<Long> getBoothIdsByElectionId(Long electionId);

}
