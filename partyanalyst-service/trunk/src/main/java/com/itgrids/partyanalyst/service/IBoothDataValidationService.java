package com.itgrids.partyanalyst.service;

import java.io.File;
import java.util.List;

import com.itgrids.partyanalyst.dto.UploadDataErrorMessageVO;
import com.itgrids.partyanalyst.excel.CsvException;

public interface IBoothDataValidationService {

	public List<String> readBoothDataExcelFileAndPolpulate(File filePath, String electionYear, Long electionScopeId) throws CsvException;
	
	public List<String> readAssemblyBoothResultExcelAndPopulate(File filePath, String electionYear, Long electionScopeId)throws CsvException;
	
	public List<String> readParliamentBoothResultExcelAndPopulate(File filePath, String electionYear, Long electionScopeId) throws CsvException;
	
	public UploadDataErrorMessageVO readVoterExcelDataAndValidate(File filePath, String electionYear, Long stateId, Long electionTypeId)throws CsvException;
}
