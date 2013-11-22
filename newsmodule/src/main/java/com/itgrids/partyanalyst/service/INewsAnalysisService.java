package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.ResultStatus;

public interface INewsAnalysisService {
	
	
	public ResultStatus saveDesignationDetails(final String designationString);
	
	public ResultStatus savePartyDetails(final String partyShortName,final String PartyLongName);

}
