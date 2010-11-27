package com.itgrids.partyanalyst.service;

import java.io.File;

import com.itgrids.partyanalyst.dto.ResultWithExceptionVO;


public interface IBoothPopulationService {

	public ResultWithExceptionVO readExcelAndPopulateBoothData(File filePath, String electionYear, Long electionScopeId, Boolean isValidate);
	
	public ResultWithExceptionVO readExcelAndPopulateBoothResult(String electionYear, Long electionScopeId, File filePath, Boolean isValidate);	

}
