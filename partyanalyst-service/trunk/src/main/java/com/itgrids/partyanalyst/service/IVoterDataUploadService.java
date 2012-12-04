package com.itgrids.partyanalyst.service;

import java.io.File;
import java.util.Date;

import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IVoterDataUploadService {

	public ResultStatus readExcelAndInsertData(File filePath,
			String electionYear, Long stateId, Long electionTypeId,
			String publicationDate);
	
}
