package com.itgrids.partyanalyst.service.impl;

import com.itgrids.partyanalyst.service.IPartyService;

public class PartyServiceStub implements IPartyService {/*
	
	public PartyPerformanceReportVO getPartyPerformanceReport(String state, String district,
			String party, String year, String electionType, String countryID, int noOfPositionDistribution,
			BigDecimal majorBand, BigDecimal minorBand, Boolean includeAlliances,String reportLevel) {

		PartyPerformanceReportVO vo = new PartyPerformanceReportVO();
		vo.setParty(party);
		vo.setYear(year);
		vo.setState(state);
		vo.setTotalSeatsContested(60);
		vo.setTotalSeatsWon(40);
		vo.setDiffSeatsWon(20);
		vo.setTotalSeatsLost(20);
		vo.setTotalPercentageOfVotesWon(new BigDecimal("67"));
		vo.setTotalPercentageOfVotesWonPreviousElection(new BigDecimal("77"));
		Map<Integer, Integer> positions = new HashMap<Integer, Integer>();
		positions.put(2,23);
		positions.put(3,10);
		positions.put(4,9);
		
		//vo.setPositionDistribution(positions);
		
		Map<String, String> toPartySwing = new HashMap<String, String>();
		toPartySwing.put("TDP", "16.66");
		vo.setToPartySwing(toPartySwing);
		
		List<ConstituencyPositionsVO> constituencyPositionsList = new ArrayList<ConstituencyPositionsVO>();
		constituencyPositionsList.add(new ConstituencyPositionsVO(
				PositionType.POSITIONS_WON_MINOR_BAND, 7, getPositionsWonMinorBand(), "Winning Positions with lower percentage margin"));
		constituencyPositionsList.add(new ConstituencyPositionsVO(
				PositionType.POSITIONS_LOST_MINOR_BAND, 4, getPositionsLostMinorBand(), "Losing Positions with lower percentage margin"));
		constituencyPositionsList.add(new ConstituencyPositionsVO(
				PositionType.POSITIONS_WON_WITH_POSITIVE_SWING, 156, getPositionsWonPositiveSwing(), "Winning Positions with Positive Swing"));
		constituencyPositionsList.add(new ConstituencyPositionsVO(
				PositionType.POSITIONS_LOST_WITH_POSITIVE_SWING, 12, getPositionsLostPositiveSwing(), "Losing Positions with Positive Swing"));
		constituencyPositionsList.add(new ConstituencyPositionsVO(
				PositionType.POSITIONS_WON_WITH_NEGATIVE_SWING, 9, getPsoitionsWonNegativeSwing(), "Winning Positions with Negative Swing"));
		constituencyPositionsList.add(new ConstituencyPositionsVO(
				PositionType.POSITIONS_LOST_WITH_NEGATIVE_SWING, 20, getPositionsLostNegativeSwing(), "Loosing Positions with Negative Swing"));
		constituencyPositionsList.add(new ConstituencyPositionsVO(
				PositionType.POSITIONS_LOST_BY_DROPPING_VOTES, 16, getPositionsLostByDroppingVotes(), "Losing Positions with doping voting percentage"));

		vo.setConstituencyPositions(constituencyPositionsList);
		return vo;
	}

	private List<ConstituencyPositionDetailVO> getPositionsLostByDroppingVotes() {
		ArrayList<ConstituencyPositionDetailVO> constiuencyList = new ArrayList<ConstituencyPositionDetailVO>();
		
		ConstituencyPositionDetailVO marginForConstiuency1 = new ConstituencyPositionDetailVO();
		marginForConstiuency1.setConstiuencyName("Hyderabad");
		marginForConstiuency1.setCandidateName("Venu Gopal Nayak");
		marginForConstiuency1.setPrevElectionCandidateName("Jaya Chandra");
		marginForConstiuency1.setPrevElectionPercentageOfVotesPolled(new BigDecimal("34"));
		marginForConstiuency1.setPercentageOfVotes(new BigDecimal("0.67"));
		marginForConstiuency1.setPrevElectionPercentage(new BigDecimal("0.20"));
		marginForConstiuency1.setPercentageOfVotesPolled(new BigDecimal("76"));
		
		ConstituencyPositionDetailVO marginForConstiuency2 = new ConstituencyPositionDetailVO();
		marginForConstiuency2.setConstiuencyName("Medak");
		marginForConstiuency2.setCandidateName("Harish");
		marginForConstiuency2.setPrevElectionCandidateName("Chandra");
		marginForConstiuency2.setPrevElectionPercentageOfVotesPolled(new BigDecimal("98"));
		marginForConstiuency2.setPercentageOfVotes(new BigDecimal("7"));
		marginForConstiuency2.setPrevElectionPercentage(new BigDecimal("89"));
		marginForConstiuency2.setPercentageOfVotesPolled(new BigDecimal("70"));
		
		constiuencyList.add(marginForConstiuency1);
		constiuencyList.add(marginForConstiuency2);
		
		return constiuencyList;
	}

	private List<ConstituencyPositionDetailVO> getPositionsLostMajorBand() {
		ArrayList<ConstituencyPositionDetailVO> constiuencyList = new ArrayList<ConstituencyPositionDetailVO>();
		
		ConstituencyPositionDetailVO marginForConstiuency1 = new ConstituencyPositionDetailVO();
		marginForConstiuency1.setConstiuencyName("Hyderabad");
		marginForConstiuency1.setCandidateName("Venu Gopal Nayak");
		marginForConstiuency1.setOppositeParty("TDP");
		marginForConstiuency1.setOppositePartyCandidate("Raja Reddy");
		marginForConstiuency1.setPercentageOfVotes(new BigDecimal("0.67"));
		marginForConstiuency1.setPrevElectionPercentage(new BigDecimal("0.20"));
		
		ConstituencyPositionDetailVO marginForConstiuency2 = new ConstituencyPositionDetailVO();
		marginForConstiuency2.setConstiuencyName("Tirupathi");
		marginForConstiuency2.setCandidateName("Kiran Kumar Reddy");
		marginForConstiuency2.setOppositeParty("PRP");
		marginForConstiuency2.setOppositePartyCandidate("Chiranjeevi");
		marginForConstiuency2.setPercentageOfVotes(new BigDecimal("0.77"));
		marginForConstiuency2.setPrevElectionPercentage(new BigDecimal("0.23"));
		
		constiuencyList.add(marginForConstiuency1);
		constiuencyList.add(marginForConstiuency2);
		
		return constiuencyList;
	}

	private List<ConstituencyPositionDetailVO> getPositionsWonMajorBand() {
		ArrayList<ConstituencyPositionDetailVO> constiuencyList = new ArrayList<ConstituencyPositionDetailVO>();
		
		ConstituencyPositionDetailVO marginForConstiuency1 = new ConstituencyPositionDetailVO();
		marginForConstiuency1.setConstiuencyName("Hyderabad");
		marginForConstiuency1.setCandidateName("Venu Gopal Nayak");
		marginForConstiuency1.setOppositeParty("TDP");
		marginForConstiuency1.setOppositePartyCandidate("Raja Reddy");
		marginForConstiuency1.setPercentageOfVotes(new BigDecimal("0.67"));
		marginForConstiuency1.setPrevElectionPercentage(new BigDecimal("0.20"));
		
		ConstituencyPositionDetailVO marginForConstiuency2 = new ConstituencyPositionDetailVO();
		marginForConstiuency2.setConstiuencyName("Tirupathi");
		marginForConstiuency2.setCandidateName("Kiran Kumar Reddy");
		marginForConstiuency2.setOppositeParty("PRP");
		marginForConstiuency2.setOppositePartyCandidate("Chiranjeevi");
		marginForConstiuency2.setPercentageOfVotes(new BigDecimal("0.77"));
		marginForConstiuency2.setPrevElectionPercentage(new BigDecimal("0.23"));
		
		constiuencyList.add(marginForConstiuency1);
		constiuencyList.add(marginForConstiuency2);
		
		return constiuencyList;
	}

	private ArrayList<ConstituencyPositionDetailVO> getPositionsLostPositiveSwing() {
		ArrayList<ConstituencyPositionDetailVO> constiuencyList = new ArrayList<ConstituencyPositionDetailVO>();
		
		ConstituencyPositionDetailVO marginForConstiuency1 = new ConstituencyPositionDetailVO();
		marginForConstiuency1.setConstiuencyName("Hyderabad");
		marginForConstiuency1.setCandidateName("Venu Gopal Nayak");
		marginForConstiuency1.setOppositeParty("TDP");
		marginForConstiuency1.setOppositePartyCandidate("Raja Reddy");
		marginForConstiuency1.setPercentageOfVotes(new BigDecimal("0.67"));
		marginForConstiuency1.setPrevElectionPercentage(new BigDecimal("0.20"));
		
		ConstituencyPositionDetailVO marginForConstiuency2 = new ConstituencyPositionDetailVO();
		marginForConstiuency2.setConstiuencyName("Tirupathi");
		marginForConstiuency2.setCandidateName("Kiran Kumar Reddy");
		marginForConstiuency2.setOppositeParty("PRP");
		marginForConstiuency2.setOppositePartyCandidate("Chiranjeevi");
		marginForConstiuency2.setPercentageOfVotes(new BigDecimal("0.77"));
		marginForConstiuency2.setPrevElectionPercentage(new BigDecimal("0.23"));
		
		constiuencyList.add(marginForConstiuency1);
		constiuencyList.add(marginForConstiuency2);
		
		return constiuencyList;
	}

	private ArrayList<ConstituencyPositionDetailVO> getPositionsLostNegativeSwing() {
		ArrayList<ConstituencyPositionDetailVO> constiuencyList = new ArrayList<ConstituencyPositionDetailVO>();
		
		ConstituencyPositionDetailVO marginForConstiuency1 = new ConstituencyPositionDetailVO();
		marginForConstiuency1.setConstiuencyName("Kurnool");
		marginForConstiuency1.setCandidateName("Reddy Naidu");
		marginForConstiuency1.setOppositeParty("TRS");
		marginForConstiuency1.setOppositePartyCandidate("Aruna Kumari Galla");
		marginForConstiuency1.setPercentageOfVotes(new BigDecimal("0.23"));
		marginForConstiuency1.setPrevElectionPercentage(new BigDecimal("0.77"));
		
		ConstituencyPositionDetailVO marginForConstiuency2 = new ConstituencyPositionDetailVO();
		marginForConstiuency2.setConstiuencyName("Cuddhapa");
		marginForConstiuency2.setCandidateName("Kiran Kumar Reddy");
		marginForConstiuency2.setOppositeParty("PRP");
		marginForConstiuency2.setOppositePartyCandidate("Venkataramana");
		marginForConstiuency2.setPercentageOfVotes(new BigDecimal("0.34"));
		marginForConstiuency2.setPrevElectionPercentage(new BigDecimal("0.85"));
		
		constiuencyList.add(marginForConstiuency1);
		constiuencyList.add(marginForConstiuency2);
		
		return constiuencyList;
	}

	private ArrayList<ConstituencyPositionDetailVO> getPsoitionsWonNegativeSwing() {
		ArrayList<ConstituencyPositionDetailVO> constiuencyList = new ArrayList<ConstituencyPositionDetailVO>();
		
		ConstituencyPositionDetailVO marginForConstiuency1 = new ConstituencyPositionDetailVO();
		marginForConstiuency1.setConstiuencyName("Pulivendula");
		marginForConstiuency1.setCandidateName("Mourya Reddy");
		marginForConstiuency1.setOppositeParty("TDP");
		marginForConstiuency1.setOppositePartyCandidate("Kranthi Reddy");
		marginForConstiuency1.setPercentageOfVotes(new BigDecimal("0.54"));
		marginForConstiuency1.setPrevElectionPercentage(new BigDecimal("0.88"));
		
		ConstituencyPositionDetailVO marginForConstiuency2 = new ConstituencyPositionDetailVO();
		marginForConstiuency2.setConstiuencyName("Chandragiri");
		marginForConstiuency2.setCandidateName("Radha Kumari");
		marginForConstiuency2.setOppositeParty("TRS");
		marginForConstiuency2.setOppositePartyCandidate("Karunakar");
		marginForConstiuency2.setPercentageOfVotes(new BigDecimal("0.49"));
		marginForConstiuency2.setPrevElectionPercentage(new BigDecimal("0.74"));
		
		constiuencyList.add(marginForConstiuency1);
		constiuencyList.add(marginForConstiuency2);
		
		return constiuencyList;
	}

	private ArrayList<ConstituencyPositionDetailVO> getPositionsWonPositiveSwing() {
		ArrayList<ConstituencyPositionDetailVO> constiuencyList = new ArrayList<ConstituencyPositionDetailVO>();
		
		ConstituencyPositionDetailVO marginForConstiuency1 = new ConstituencyPositionDetailVO();
		marginForConstiuency1.setConstiuencyName("Hyderabad");
		marginForConstiuency1.setCandidateName("Venu Gopal Nayak");
		marginForConstiuency1.setOppositeParty("TDP");
		marginForConstiuency1.setOppositePartyCandidate("Raja Reddy");
		marginForConstiuency1.setPercentageOfVotes(new BigDecimal("0.67"));
		marginForConstiuency1.setPrevElectionPercentage(new BigDecimal("0.20"));
		
		ConstituencyPositionDetailVO marginForConstiuency2 = new ConstituencyPositionDetailVO();
		marginForConstiuency2.setConstiuencyName("Tirupathi");
		marginForConstiuency2.setCandidateName("Kiran Kumar Reddy");
		marginForConstiuency2.setOppositeParty("PRP");
		marginForConstiuency2.setOppositePartyCandidate("Chiranjeevi");
		marginForConstiuency2.setPercentageOfVotes(new BigDecimal("0.77"));
		marginForConstiuency2.setPrevElectionPercentage(new BigDecimal("0.23"));
		
		constiuencyList.add(marginForConstiuency1);
		constiuencyList.add(marginForConstiuency2);
		
		return constiuencyList;
	}

	private ArrayList<ConstituencyPositionDetailVO> getPositionsLostMinorBand() {
		ArrayList<ConstituencyPositionDetailVO> constiuencyList = new ArrayList<ConstituencyPositionDetailVO>();
		
		ConstituencyPositionDetailVO marginForConstiuency1 = new ConstituencyPositionDetailVO();
		marginForConstiuency1.setConstiuencyName("Kuppam");
		marginForConstiuency1.setCandidateName("Kuthuhalamma");
		marginForConstiuency1.setOppositeParty("CPM");
		marginForConstiuency1.setOppositePartyCandidate("Chandra Babdu Naidu");
		marginForConstiuency1.setPercentageOfVotes(new BigDecimal("0.78"));
		marginForConstiuency1.setPrevElectionPercentage(new BigDecimal("0.87"));
		
		ConstituencyPositionDetailVO marginForConstiuency2 = new ConstituencyPositionDetailVO();
		marginForConstiuency2.setConstiuencyName("Pakala");
		marginForConstiuency2.setCandidateName("Kiran Kumar Reddy");
		marginForConstiuency2.setOppositeParty("PRP");
		marginForConstiuency2.setOppositePartyCandidate("Kuthuhalamma");
		marginForConstiuency2.setPercentageOfVotes(new BigDecimal("0.62"));
		marginForConstiuency2.setPrevElectionPercentage(new BigDecimal("0.74"));
		
		constiuencyList.add(marginForConstiuency1);
		constiuencyList.add(marginForConstiuency2);
		
		return constiuencyList;
	}

	public PartyPerformanceReportVO getPartyPerformanceReport(String state,
			String party, String year, int noOfPositionDistribution) {
		return getPartyPerformanceReport(state, party, year,
				noOfPositionDistribution, new BigDecimal(5), new BigDecimal(5));
		return null;
	}

	public PartyPerformanceReportVO getPartyPerformanceReport(String state,
			String party, String year) {
		return getPartyPerformanceReport(state, party, year, 5);
	}

	public List<ConstituencyPositionDetailVO> getPositionsWonMinorBand(){
		List<ConstituencyPositionDetailVO> constiuencyList = new ArrayList<ConstituencyPositionDetailVO>();
		
		ConstituencyPositionDetailVO marginForConstiuency1 = new ConstituencyPositionDetailVO();
		marginForConstiuency1.setConstiuencyName("Chittoor");
		marginForConstiuency1.setCandidateName("Kesav Reddy");
		marginForConstiuency1.setOppositeParty("PRP");
		marginForConstiuency1.setOppositePartyCandidate("Chenna Reddy");
		marginForConstiuency1.setPercentageOfVotes(new BigDecimal("0.45"));
		marginForConstiuency1.setPrevElectionPercentage(new BigDecimal("0.23"));
		
		ConstituencyPositionDetailVO marginForConstiuency2 = new ConstituencyPositionDetailVO();
		marginForConstiuency2.setConstiuencyName("Nellor");
		marginForConstiuency2.setCandidateName("Siva Reddy");
		marginForConstiuency2.setOppositeParty("TDP");
		marginForConstiuency2.setOppositePartyCandidate("Vijay Kumar");
		marginForConstiuency2.setPercentageOfVotes(new BigDecimal("0.67"));
		marginForConstiuency2.setPrevElectionPercentage(new BigDecimal("0.52"));
		
		
		constiuencyList.add(marginForConstiuency1);
		constiuencyList.add(marginForConstiuency2);
		
		return constiuencyList;
	}

	public PartyPerformanceReportVO getPartyPerformanceReport(String state,
			String party, String year, String electionType,
			int noOfPositionDistribution, BigDecimal majorBand,
			BigDecimal minorBand) {
		// TODO Auto-generated method stub
		return null;
	}

	public PartyPerformanceReportVO getPartyPerformanceReport(String state,
			String party, String year, String electionType,
			int noOfPositionDistribution) {
		// TODO Auto-generated method stub
		return null;
	}

	public PartyPerformanceReportVO getPartyPerformanceReport(String state,
			String party, String year, String electionType) {
		return getPartyPerformanceReport(state, party, year, 5);
	}

	public String getBlah(String str) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getBlah(int str) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getBlo(String str) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PartyPositionDisplayVO> getNthPositionPartyDetails(Long electionType, Long stateID, Long districtID, Long year, Long partyID, boolean alliances, int rank,String reportLevel){
		return null;
	}
	
	public List<PartyPositionDisplayVO> getPartyPositionDetailsForAnElection(Long electionTypeID, Long stateID, Long districtID, Long year, Long partyID, boolean alliances, int rank,String reportLevel){
		return null;
	}

	
	public List<PartyPositionDisplayVO> getPartyPositionsDetailsInAnElection(
			Long electionId, Long partyId, Long rank) {
		// TODO Auto-generated method stub
		return null;
	}
*/}

