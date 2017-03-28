package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.BoothConstituencyElectionVoter;
import com.itgrids.partyanalyst.model.Voter;

public interface IBoothConstituencyElectionVoterDAO extends GenericDao<BoothConstituencyElectionVoter, Long>{

	public List<BoothConstituencyElectionVoter> findByBoothConstituencyElection(Long boothConstituencyElectionId);

	public List<BoothConstituencyElectionVoter> findByBoothConstituencyElectionAndVoter(Long boothConstituencyElectionId, Long voterId);
	
	public List findTotalVotersCountByHamletAndElectionYear(Long hamletId, String year);
	
	public List findVotersForHamletAndElectionYearByStartAndMaxResults(Long hamletId, String year, 
			int startIndex, int maxResult, String order, String columnName);
	
	public List findVotersCastInfoByHamletAndElectionYear(Long hamletId, String year);

	public List findTotalVoterHousesCountByHamletAndElectionYear(Long hamletId, String year);
	
	public List<String> findVoterHouseNosInHamlet(Long hamletId, String year);

	public List<Voter> findVotersByHouseNoAndHamlet(String houseNo, Long hamletId,	String year);
	
	public List findTownshipWiseBoothDetailsForTehsil(Long tehsilId, Long electionId);
	
	public List findTotalVotersForHamlet(Long revenueVillageID, String year, String electionType);
	
	public List findHamletBoothsForRevenueVillage(Long revenueVillageID, String year, String electionType);
	
	public List findCastWiseVoterForRevenueVillage(Long revenueVillageID, String year, String electionType);
	
	public List findAgeWiseVotersForRevenueVillage(Long revenueVillageID, String year, String electionType, Long minAge, Long maxAge);
	
	//public List findAllBoothVotersForHamlet(Long hamletID, String year, String electionType);
	public List findCastWiseVoterForHamlet(Long hamletID, String year, String electionType);
	
	public List findAgeWiseVotersForHamlet(Long hamletID, String year, String electionType, Long minAge, Long maxAge);
	
	public List getTownshipVotesByTehsil(Long electionID, Long tehsilID);
	
	public List getAllCandidateBoothResultsForTownshipsForTehsil(Long tehsilId);
	
	public List getBoothsForTownship(Long townshipId);

	/*public List findVotersInfoForHamletAndElectionYear(Long hamletId,
			String year);
	*/
	public List<Object> getGenderOfVotersInALocation(String queryStr,Long locationId);
	
	public List<Object> getTotalNoOfVotersInALocation(String queryStr,Long locationId);
	
	public List<Object> getTotalNoOfMaleVotersInALocation(String queryStr,Long locationId);
	
	public List findVotersInfoForHamletAndElectionYear(Long panchayatId,
			String year);
	
	public List<Object[]> getVotersInfoForPanchayatAndElectionYear(Long panchayatId,
			String year);
	
	
	
	public List findVotersCastInfoByPanchayatAndElectionYear(Long panchayatId, String year);
	
	public List findVotersCastInfoByPollingStationAndElectionYear(Long hamletId, String year);
	
	
	public List findVotersInfoForPanchayatAndElectionYear(Long hamletId,
			String year);
	
	public Long getVotersAgeInfoForPanchayatAndElectionYear(Long panchayatId,
			String year,Long minAge,Long maxAge);
	
	public Long getVotersAboveAgeInfoForPanchayatAndElectionYear(Long panchayatId,
			String year);
	
	public List<Object[]>  getVotersBasicInfoByPanchayatId(Long panchayatId, String year);
	
	public List<Object[]> findVotersForPanchayatAndElectionYearByStartAndMaxResults(Long hamletId, String year, 
			int startIndex, int maxResult, String order, String columnName);
	
	public List<Object[]> findVotersForBoothAndElectionYearByStartAndMaxResults(Long hamletId, String year, 
			int startIndex, int maxResult, String order, String columnName);
	
	public List findTotalVotersCountByPanchayatAndElectionYear(Long panchayatId, String year);
	
		
}