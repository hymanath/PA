package com.itgrids.partyanalyst.service;

import java.io.File;

import com.itgrids.partyanalyst.dto.MPTCElectionResultVO;

public interface IMptcElectionService {

	public MPTCElectionResultVO uploadMPTCElectionData(Long electionTypeID,
			Long countryID, Long stateID, Long districtID, String year,
			File file, String elecSubtype);
}
