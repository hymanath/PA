package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.BoothConstituencyElectionVoter;

public interface IBoothConstituencyElectionVoterDAO extends GenericDao<BoothConstituencyElectionVoter, Long>{

	public List<BoothConstituencyElectionVoter> findByBoothConstituencyElection(Long boothConstituencyElectionId);

	public List<BoothConstituencyElectionVoter> findByBoothConstituencyElectionAndVoter(Long boothConstituencyElectionId, Long voterId);
	
	public List findCastFromVoterByConstituencyAndElection(Long constituencyID, String electionYear);
	
	public List findVotersByConstituencyAndElectionForGender(Long constituencyID, String electionYear);


}
