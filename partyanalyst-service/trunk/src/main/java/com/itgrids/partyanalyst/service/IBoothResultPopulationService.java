package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.excel.booth.BoothResultValueObject;
import com.itgrids.partyanalyst.excel.booth.CandidateBoothWiseResult;
import com.itgrids.partyanalyst.model.ConstituencyElection;

public interface IBoothResultPopulationService {
	
	public boolean readExcelAndInsertData(String electionYear, Long electionScopeId, String filePath);
	
	public void checkAndInsertBoothResult(ConstituencyElection constiElecObj, List<CandidateBoothWiseResult> candidateResults, List<BoothResultValueObject> boothresults);
}
