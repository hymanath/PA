package com.itgrids.partyanalyst.service;

import java.io.File;

import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IVoterDataUploadService {

	public ResultStatus readExcelAndInsertData(File filePath, String electionYear, Long stateId, Long electionTypeId);
	
}
