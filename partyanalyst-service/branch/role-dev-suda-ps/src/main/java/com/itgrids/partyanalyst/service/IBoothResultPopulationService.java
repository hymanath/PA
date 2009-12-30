package com.itgrids.partyanalyst.service;

import java.io.File;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.excel.CsvException;

public interface IBoothResultPopulationService {
	
	public ResultStatus readExcelAndInsertData(String electionYear, Long electionScopeId, File filePath)throws CsvException;
	
}
