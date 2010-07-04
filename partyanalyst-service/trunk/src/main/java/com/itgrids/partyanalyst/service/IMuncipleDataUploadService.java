package com.itgrids.partyanalyst.service;

import java.io.File;

import com.itgrids.partyanalyst.dto.MPTCElectionResultVO;

public interface IMuncipleDataUploadService {

	public MPTCElectionResultVO readExcelDataForMuncipalities(File file, Long electionTypeId, 
			Long stateId, String electionYear, String elecSubtype);
	
}
