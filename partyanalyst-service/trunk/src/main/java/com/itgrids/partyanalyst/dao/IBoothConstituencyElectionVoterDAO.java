package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.BoothConstituencyElectionVoter;
import com.itgrids.partyanalyst.model.Voter;

public interface IBoothConstituencyElectionVoterDAO extends GenericDao<BoothConstituencyElectionVoter, Long>{

	public List<BoothConstituencyElectionVoter> findByBoothConstituencyElection(Long boothConstituencyElectionId);

	public List<BoothConstituencyElectionVoter> findByBoothConstituencyElectionAndVoter(Long boothConstituencyElectionId, Long voterId);
	
	public List<Voter> findVotersByHamletAndElectionYear(Long hamletId, String year);
	
	public List findVotersCastInfoByHamletAndElectionYear(Long hamletId, String year);

	public List<String> findVoterHouseNosInHamlet(Long hamletId, String year);

	public List<Voter> findVotersByHouseNoAndHamlet(String houseNo, Long hamletId,	String year);
	
	public List<Voter> findVotersGroupByHouseNoAndAgeForHamletAndYear(Long hamletId, String year);

	public List findTotalVotersForHamlet(Long revenueVillageID, String year, String electionType);
	
	public List findHamletBoothsForRevenueVillage(Long revenueVillageID, String year, String electionType);
}