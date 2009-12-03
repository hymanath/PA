package com.itgrids.partyanalyst.service;

import java.io.File;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.excel.CsvException;

public interface IParliamentBoothResultPopulationService {

	public ResultStatus readExcel(File filePath, Long electionScopeId, String electionYear) throws CsvException;
}
