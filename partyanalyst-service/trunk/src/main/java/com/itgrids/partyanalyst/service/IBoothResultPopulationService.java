package com.itgrids.partyanalyst.service;

public interface IBoothResultPopulationService {
	
	public boolean readExcelAndInsertData(String electionYear, String constituencyName, String electionType, String districtName, String filePath);
	
}
