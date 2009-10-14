package com.itgrids.partyanalyst.service;


public interface IBoothPopulationService {

	public boolean readExcelFileAndPolpulate(String filePath, String electionYear, String electionType);

}
