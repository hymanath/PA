package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;
import com.itgrids.partyanalyst.model.Constituency;

public interface IBoothConstituencyElectionDAO extends GenericDao<BoothConstituencyElection, Long>{

	public List<Booth> findBoothsByConstituencyElection(Long constiElecId);
	
	public List<BoothConstituencyElection> findByElectionConstituencyAndBooth(Long boothId, String electionYear, Long constituencyId);

	public List<BoothConstituencyElection> findByElectionConstituencyAndTehsil(String electionYear, Long tehsilId, Long constituencyId);

	public List<BoothConstituencyElection> findByConstituencyElectionTehsilAndPartNo(Long constituencyElectionId, Long tehsilId, String partNo);

	public List<BoothConstituencyElection> findByBoothAndConstiuencyElection(String partNo, Long constiElecId);
	
	public List<BoothConstituencyElection> findByConstituencyAndElection(String constituencyName, String electionYear, Long electionScope);

	public List getAllElectionBoothVotersForMandal(Long tehsilID);
	
	public List getPartyVotesByMandal(Long tehsilID, String partyIDs, Long electionID);
	
	public List<Constituency> findConstituencyByElectionYearAndElectionScope(String electionYear, Long electionScopeId);
	
	public List<String> findElectionYearsForBoothData();
	
	public List getStatesByCountryFromBooth(Long countryID);
	public List getDistrictsByStateIDFromBooth(Long stateID);
	public List getConstituenciesByDistrictIDFromBooth(Long districtID);
	public List getMandalsByConstituencyIDFromBooth(Long constituencyID);
	
	public List<BoothConstituencyElection> findByConstituencyIdAndElectionYear(Long constituencyId, String electionYear);
	
	public List<BoothConstituencyElection> findByElectionElectionTypeConstituencyAndPartNo(
			Long stateId, Long districtId, String constituencyName,
			Long electionTypeId, String electionYear, String partNo);

	public List findPartNoConstituencyNameForTehsil(Long tehsilId,
			String assemblyElectionType, String electionYear);
	
	public List findVoterInformationByMandalIdsAndDelimitationYear (String mandalsIds,String year, Long constituencyId);
}
