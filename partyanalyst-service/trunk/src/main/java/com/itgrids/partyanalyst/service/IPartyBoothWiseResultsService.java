package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultListVO;
import com.itgrids.partyanalyst.dto.ResultWithExceptionVO;
import com.itgrids.partyanalyst.excel.booth.PartyBoothPerformanceVO;

public interface IPartyBoothWiseResultsService {
	
	public List<PartyBoothPerformanceVO> getBoothWiseResultsForParty(Long partyId, Long constituencyId, String electionYear);
	
	public ResultWithExceptionVO getBoothPageInfo(Long boothId);
	
	public ElectionWiseMandalPartyResultListVO getPartyGenderWiseBoothVotesForMandal(Long tehsilID, String locationType);
}
