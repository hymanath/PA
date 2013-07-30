package com.itgrids.partyanalyst.service;


import java.util.List;

import com.itgrids.partyanalyst.dto.PartyPositionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.YouthLeaderSelectionVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
public interface ISuggestiveModelService {
	
	public List<SelectOptionVO> getConstituenciesForUserAccessByStateId(List<SelectOptionVO> ConstituenciesForUserAccessed,Long electionId,Long electionYear);
	
	public List<SelectOptionVO> getPartyDetailsByMandal(Long mandalId);
	
	public List<SelectOptionVO> getElectionIdsAndYearsBytehsilId(List<Long> electionScope,Long partyId,Long tehsilId);
	
	public List<PartyPositionVO> getPartyPerformenceReport(Long constituencyId,Long partyId,Long locationId,String locationType,Long electionId,String tempVar);
	
	public List<YouthLeaderSelectionVO> findingBoothInchargesForBoothLevel(Long mandalId,Long userid,Long constituencyId);
	
	 public List<VoterVO> getDeletedVoterInfo(List<Long> panchayatIds);

}
