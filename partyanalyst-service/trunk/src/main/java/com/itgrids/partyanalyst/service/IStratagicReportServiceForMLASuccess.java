package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.CasteStratagicReportVO;
import com.itgrids.partyanalyst.dto.HouseHoldsVO;
import com.itgrids.partyanalyst.dto.StrategicCensusVO;
import com.itgrids.partyanalyst.dto.VoterStratagicReportVo;

public interface IStratagicReportServiceForMLASuccess {

	public HouseHoldsVO getHouseHoldInfoByConstituency(Long userId,Long constituencyId,Long publicationDateId);
	
	public VoterStratagicReportVo getVotersInfoByConstituency(Long userId,Long constituencyId,Long publicationDateId);
	
	public VoterStratagicReportVo getFirstTimeVotersInfoByConstituency(Long userId,Long constituencyId,Long publicationDateId);
	
	public VoterStratagicReportVo getAgeWiseVotersInfoByConstituency(Long userId,Long constituencyId,Long publicationDateId);
	
	public CasteStratagicReportVO getCasteWiseVotersInfoByConstituency(Long userId,Long constituencyId,Long publicationDateId);

	public StrategicCensusVO getCensusDetailsForAConstituency(Long constituencyId);
}
