package com.itgrids.partyanalyst.service;

import java.util.Collection;
import java.util.List;

import com.itgrids.partyanalyst.dto.AgeRangeVO;
import com.itgrids.partyanalyst.dto.PartyElectionTrendsReportVO;


public interface IStratagicReportsService {
	public List<AgeRangeVO> getBoothWiseAddedAndDeletedVoters(Long constituencyId,Long publicationDateId);
	public List<PartyElectionTrendsReportVO> getPreviousTrendsReport(Long constId);
	public List<PartyElectionTrendsReportVO> getPreviousTrendsReportParliament(Long constId);

}
