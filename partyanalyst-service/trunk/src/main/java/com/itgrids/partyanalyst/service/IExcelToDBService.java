package com.itgrids.partyanalyst.service;

public interface IExcelToDBService {

	public void readCSVFileAndStoreIntoDB(String excelFilePath,String countryName,String stateName,String districtName, String typeOfElection, String electionYear) throws Exception;
}
