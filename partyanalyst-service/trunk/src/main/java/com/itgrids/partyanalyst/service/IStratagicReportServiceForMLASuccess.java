package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CasteStratagicReportVO;
import com.itgrids.partyanalyst.dto.HouseHoldsVO;
import com.itgrids.partyanalyst.dto.PartyElectionTrendsReportVO;
import com.itgrids.partyanalyst.dto.StrategicCensusVO;
import com.itgrids.partyanalyst.dto.VoterStratagicReportVo;

public interface IStratagicReportServiceForMLASuccess {

	public HouseHoldsVO getHouseHoldInfoByConstituency(Long userId,Long constituencyId,Long publicationDateId);
	
	public VoterStratagicReportVo getVotersInfoByConstituency(Long userId,Long constituencyId,Long publicationDateId);
	
	public VoterStratagicReportVo getFirstTimeVotersInfoByConstituency(Long userId,Long constituencyId,Long publicationDateId);
	
	public VoterStratagicReportVo getAgeWiseVotersInfoByConstituency(Long userId,Long constituencyId,Long publicationDateId);
	
	public CasteStratagicReportVO getCasteWiseVotersInfoByConstituency(Long userId,Long constituencyId,Long publicationDateId);

	public StrategicCensusVO getCensusDetailsForAConstituency(Long constituencyId);
	
	
	


	public List<PartyElectionTrendsReportVO> getPreviousTrendsReport(Long constId);
	public List<PartyElectionTrendsReportVO> getPreviousTrendsReportParliament(Long constId);
}
