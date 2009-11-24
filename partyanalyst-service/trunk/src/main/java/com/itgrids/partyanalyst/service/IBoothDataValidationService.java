package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.excel.CsvException;

public interface IBoothDataValidationService {

	public List<String> readBoothDataExcelFileAndPolpulate(String filePath, String electionYear, Long electionScopeId) throws CsvException;
	
	public List<String> readAssemblyBoothResultExcelAndPopulate(String filePath, String electionYear, Long electionScopeId)throws CsvException;
	
	public List<String> readParliamentBoothResultExcelAndPopulate(String filePath, String electionYear, Long electionScopeId) throws CsvException;
}
