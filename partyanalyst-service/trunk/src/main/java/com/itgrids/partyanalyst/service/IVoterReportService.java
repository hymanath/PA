package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.VoterReportVO;

public interface IVoterReportService {
	
	public VoterReportVO getVoterDetailsInaLocation(String range,Long rangeValue);
	
	public ResultStatus insertVotersPartyDataToIntermediateTables(Long reportLevelValue, Long publicationDateId,Long userId);
	
	public ResultStatus deletevotermodificationFromIntermediateTables(Long constituencyId,Long publicationId);
	
	public ResultStatus insertVotersCasteDataInIntermediateTables(Long reportLevelValue, Long publicationDateId,Long userId);
	
	 public ResultStatus deleteVoterModifiedData(Long constituencyId,Long publicationDateId);
	
}
