package com.itgrids.partyanalyst.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.itgrids.partyanalyst.dto.StateLevelPartyReportVO;

public class PartyServiceStub implements IPartyService {

	public StateLevelPartyReportVO getStateLevelPartyReport(String state,
			String party, String year, int noOfPositionDistribution,
			BigDecimal majorBand, BigDecimal minorBand) {

		StateLevelPartyReportVO vo = new StateLevelPartyReportVO();
		vo.setTotalSeatsContested(60);
		vo.setTotalSeatsWon(40);
		vo.setTotalSeatsLost(20);
		vo.setTotalPercentageOfVotesWon(new BigDecimal("67.00"));
		Map<Integer, Integer> positionDistribution = new HashMap<Integer, Integer>();
		positionDistribution.put(2, 23);
		positionDistribution.put(3, 10);
		positionDistribution.put(4, 9);
		vo.setPositionDistribution(positionDistribution);
		vo.setPositionsLostMajorBand(13);
		vo.setPositionsLostMinorBand(4);
		vo.setPositionsWonMajorBand(17);
		vo.setPositionsWonMinorBand(7);
		Map<String, BigDecimal> toPartySwing = new HashMap<String, BigDecimal>();
		toPartySwing.put("TDP", new BigDecimal("16.66"));
		vo.setToPartySwing(toPartySwing);
		return vo;
	}

	public StateLevelPartyReportVO getStateLevelPartyReport(String state,
			String party, String year, int noOfPositionDistribution) {
		return getStateLevelPartyReport(state, party, year,
				noOfPositionDistribution, new BigDecimal(5), new BigDecimal(5));
	}

	public StateLevelPartyReportVO getStateLevelPartyReport(String state,
			String party, String year) {
		return getStateLevelPartyReport(state, party, year, 5);
	}

}
