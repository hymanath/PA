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
	
	
}
