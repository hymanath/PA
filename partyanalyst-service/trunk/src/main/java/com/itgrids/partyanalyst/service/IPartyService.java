package com.itgrids.partyanalyst.service;

import java.math.BigDecimal;

import com.itgrids.partyanalyst.dto.StateLevelPartyReportVO;

public interface IPartyService {
	
	public StateLevelPartyReportVO getStateLevelPartyReport(String state, String party, String year, int noOfPositionDistribution, BigDecimal majorBand, BigDecimal minorBand);
	
	public StateLevelPartyReportVO getStateLevelPartyReport(String state, String party, String year, int noOfPositionDistribution);
	
	public StateLevelPartyReportVO getStateLevelPartyReport(String state, String party, String year);

}
