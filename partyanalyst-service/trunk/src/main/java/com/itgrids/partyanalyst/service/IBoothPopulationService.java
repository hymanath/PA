package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.excel.CsvException;


public interface IBoothPopulationService {

	public boolean readExcelFileAndPolpulate(String filePath, String electionYear, Long electionScopeId) throws CsvException;

}
