package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.excel.CsvException;

public interface IBoothResultPopulationService {
	
	public ResultStatus readExcelAndInsertData(String electionYear, Long electionScopeId, String filePath)throws CsvException;
	
}
