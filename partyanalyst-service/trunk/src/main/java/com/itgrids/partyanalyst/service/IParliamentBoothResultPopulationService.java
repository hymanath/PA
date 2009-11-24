package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.excel.CsvException;

public interface IParliamentBoothResultPopulationService {

	public ResultStatus readExcel(String filePath, Long electionScopeId, String electionYear) throws CsvException;
}
