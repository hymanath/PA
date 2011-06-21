package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.VoterReportVO;

public interface IVoterReportService {
	
	public VoterReportVO getVoterDetailsInaLocation(String range,Long rangeValue);
}
