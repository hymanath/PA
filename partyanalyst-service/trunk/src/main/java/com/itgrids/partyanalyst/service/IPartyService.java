package com.itgrids.partyanalyst.service;

import java.math.BigDecimal;

import com.itgrids.partyanalyst.dto.PartyPerformanceReportVO;

public interface IPartyService {
	
	public PartyPerformanceReportVO getPartyPerformanceReport(String state, String district, String party, String year, String electionType, 
			String countryID, int noOfPositionDistribution, BigDecimal majorBand, BigDecimal minorBand, Boolean includeAllianceParties);

	public PartyPerformanceReportVO getPartyPerformanceReport(String state, String party, String year, String electionType, int noOfPositionDistribution);

	public PartyPerformanceReportVO getPartyPerformanceReport(String state, String party, String year, String electionType);
	
	public String getBlo(String str);
	public String getBlah(String str);
	public String getBlah(int str);

}
