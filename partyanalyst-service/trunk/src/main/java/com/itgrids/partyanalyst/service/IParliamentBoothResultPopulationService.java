package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.excel.CsvException;
import com.itgrids.partyanalyst.excel.booth.ConstituencyBoothBlock;

public interface IParliamentBoothResultPopulationService {

	public void readExcel(String filePath, Long electionScopeId, String electionYear) throws CsvException;
	
	public void insertParliamentBoothConstiElection(ConstituencyBoothBlock constituencyBoothBlock, Long electionScopeId, String electionYear);

}
