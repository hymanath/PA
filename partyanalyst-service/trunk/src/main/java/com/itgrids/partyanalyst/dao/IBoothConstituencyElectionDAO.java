package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;

public interface IBoothConstituencyElectionDAO extends GenericDao<BoothConstituencyElection, Long>{

	public List<Booth> findBoothsByConstituencyElection(Long constiElecId);
	
	public List<BoothConstituencyElection> findByBoothElectionAndScope(Long boothId, String electionYear, Long electionScopeId);

	public List<BoothConstituencyElection> findByTehsilElectionAndScope(String electionYear, Long electionScopeId, Long tehsilId);

	public List<BoothConstituencyElection> findByConstituencyElectionTehsilAndPartNo(Long constituencyElectionId, Long tehsilId, String partNo);

	public List<BoothConstituencyElection> findByBoothAndConstiuencyElection(String partNo, Long constiElecId);
	
	public List<BoothConstituencyElection> findByConstituencyAndElection(String constituencyName, String electionYear, Long electionScope);

	public List getAllElectionBoothVotersForMandal(Long tehsilID);
	
	public List getPartyVotesByMandal(Long tehsilID, String partyIDs, Long electionID);
	
	public List getStatesByCountryFromBooth(Long countryID);
	public List getDistrictsByStateIDFromBooth(Long stateID);
	public List getConstituenciesByDistrictIDFromBooth(Long districtID);
	public List getMandalsByConstituencyIDFromBooth(Long constituencyID);
}
