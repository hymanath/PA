package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AgeRangeVO;
import com.itgrids.partyanalyst.dto.PartyElectionTrendsReportVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.PartyResultsVerVO;


public interface IStratagicReportsService {
	public List<AgeRangeVO> getBoothWiseAddedAndDeletedVoters(Long constituencyId,Long publicationDateId);
	public List<PartyElectionTrendsReportVO> getPreviousTrendsReport(Long constId);
	public List<PartyElectionTrendsReportVO> getPreviousTrendsReportParliament(Long constId);
	public PartyResultsVerVO getZptcMptcResultsOfConstituency(Long constiutencyId);
	public PartyResultsVerVO getMuncipalCorpPrevResultsInGHMC(Long constiutencyId);
	public PartyResultsVerVO getMuncipalCorpPrevResults(Long constiutencyId);

}
