package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.excel.CsvException;


public interface IBoothPopulationService {

	public ResultStatus readExcelFileAndPolpulate(String filePath, String electionYear, Long electionScopeId) throws CsvException;

}
