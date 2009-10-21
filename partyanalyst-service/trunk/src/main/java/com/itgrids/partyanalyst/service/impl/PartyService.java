package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itgrids.partyanalyst.dao.IConstituencyElectionResultDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dto.ConstituencyPositionDetailVO;
import com.itgrids.partyanalyst.dto.ConstituencyPositionsVO;
import com.itgrids.partyanalyst.dto.PartyPerformanceReportVO;
import com.itgrids.partyanalyst.dto.PositionType;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.ConstituencyElectionResult;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.service.IPartyService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.ValueComparator;

public class PartyService implements IPartyService {

	private IElectionDAO electionDAO;
	private IStateDAO stateDAO;
	private State stateVO;
	private IPartyDAO partyDAO;
	private IStaticDataService staticDataService;
	private IConstituencyElectionResultDAO constituencyElectionResultDAO;
	
	private final static Log log = LogFactory.getLog(PartyService.class);
	
	public void setPartyDAO(IPartyDAO partyDAO){
		this.partyDAO = partyDAO;
	}
	
	public void setElectionDAO(IElectionDAO electionDAO){
		this.electionDAO = electionDAO;
	}
	
	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public void setConstituencyElectionResultDAO(
			IConstituencyElectionResultDAO constituencyElectionResultDAO) {
		this.constituencyElectionResultDAO = constituencyElectionResultDAO;
	}

	public PartyPerformanceReportVO getPartyPerformanceReport(String state,
			String partyId, String year, String elecType, String countryID,
			int noOfPositionDistribution, BigDecimal majorBand,
			BigDecimal minorBand, Boolean includeAllianceParties) {	
		log.debug("getPartyPerformanceReport start.....");
		
		Party selectedParty = partyDAO.get(new Long(partyId)); 
		Long electionType = new Long(elecType);
		stateVO = stateDAO.get(new Long(state));
		String prevYear = electionDAO.findPreviousElectionYear(year, electionType, new Long(state), new Long(countryID));
				
		PartyPerformanceReportVO presentYearPartyPerformanceReportVO = getElectionData(year, electionType, includeAllianceParties, selectedParty, false);
		PartyPerformanceReportVO previousYearPartyPerformanceReportVO = getElectionData(prevYear, electionType, includeAllianceParties, selectedParty, true);
				
		List<ConstituencyPositionDetailVO> presentPartyWinners = presentYearPartyPerformanceReportVO.getPartyWinners();
		List<ConstituencyPositionDetailVO> presentPartyLoosers = presentYearPartyPerformanceReportVO.getPartyLosers();				

		Map<String, List<ConstituencyPositionDetailVO>> marginDifferenceWinConstituencyPositions = 
				marginDifferenceWinData(presentPartyWinners, minorBand.doubleValue(), majorBand.doubleValue());
		
		List<ConstituencyPositionsVO> constituencyPositionsList = new ArrayList<ConstituencyPositionsVO>();
		constituencyPositionsList.add(new ConstituencyPositionsVO(
										PositionType.POSITIONS_WON_MAJOR_BAND
										, marginDifferenceWinConstituencyPositions.get("MAJOR_DIFF").size()
										, marginDifferenceWinConstituencyPositions.get("MAJOR_DIFF")
										, "Winning Positions with higher percentage margin"));
		
		constituencyPositionsList.add(new ConstituencyPositionsVO(
										PositionType.POSITIONS_WON_MINOR_BAND
										, marginDifferenceWinConstituencyPositions.get("MINOR_DIFF").size()
										, marginDifferenceWinConstituencyPositions.get("MINOR_DIFF")
										, "Winning Positions with lower percentage margin"));

		Map<String, List<ConstituencyPositionDetailVO>> marginDifferenceLossConstituencyPositions = 
			marginDifferenceWinData(presentPartyLoosers, minorBand.doubleValue(), majorBand.doubleValue());
		
		constituencyPositionsList.add(new ConstituencyPositionsVO(
										PositionType.POSITIONS_LOST_MINOR_BAND
										, marginDifferenceLossConstituencyPositions.get("MINOR_DIFF").size()
										, marginDifferenceLossConstituencyPositions.get("MINOR_DIFF")
										, "Losing Positions with lower percentage margin"));
		constituencyPositionsList.add(new ConstituencyPositionsVO(
										PositionType.POSITIONS_LOST_MAJOR_BAND
										, marginDifferenceLossConstituencyPositions.get("MAJOR_DIFF").size()
										, marginDifferenceLossConstituencyPositions.get("MAJOR_DIFF")
										, "Losing Positions with higher percentage margin"));
		
		int positiveSwing = 10;
		int negativeSwing = 10;
		List<ConstituencyPositionDetailVO> previousElectionWinners_Loosers = new ArrayList<ConstituencyPositionDetailVO>();
		previousElectionWinners_Loosers.addAll(previousYearPartyPerformanceReportVO.getPartyWinners());
		previousElectionWinners_Loosers.addAll(previousYearPartyPerformanceReportVO.getPartyLosers());
		
		Map<String, List<ConstituencyPositionDetailVO>> swingDifferenceWinConstituencyPositions = 
				swingDifferenceWinData(presentPartyWinners, previousElectionWinners_Loosers, negativeSwing, positiveSwing);
		
		constituencyPositionsList.add(new ConstituencyPositionsVO(
										PositionType.POSITIONS_WON_WITH_POSITIVE_SWING
										, swingDifferenceWinConstituencyPositions.get("POSITIVE_SWING").size()
										, swingDifferenceWinConstituencyPositions.get("POSITIVE_SWING")
										, "Winning Positions with Positive Swing"));
		constituencyPositionsList.add(new ConstituencyPositionsVO(
										PositionType.POSITIONS_WON_WITH_NEGATIVE_SWING
										, swingDifferenceWinConstituencyPositions.get("NEGATIVE_SWING").size()
										, swingDifferenceWinConstituencyPositions.get("NEGATIVE_SWING")
										, "Winning Positions with Negative Swing"));
		
		Map<String, List<ConstituencyPositionDetailVO>> swingDifferenceLossConstituencyPositions = 
			swingDifferenceWinData(presentPartyWinners, previousElectionWinners_Loosers, negativeSwing, positiveSwing);
		
		constituencyPositionsList.add(new ConstituencyPositionsVO(
										PositionType.POSITIONS_LOST_WITH_POSITIVE_SWING
										, swingDifferenceLossConstituencyPositions.get("POSITIVE_SWING").size()
										, swingDifferenceLossConstituencyPositions.get("POSITIVE_SWING")
										, "Losing Positions with Positive Swing"));
		
		constituencyPositionsList.add(new ConstituencyPositionsVO(
										PositionType.POSITIONS_LOST_WITH_NEGATIVE_SWING
										, swingDifferenceLossConstituencyPositions.get("NEGATIVE_SWING").size()
										, swingDifferenceLossConstituencyPositions.get("NEGATIVE_SWING")
										, "Loosing Positions with Negative Swing"));
		
		List<ConstituencyPositionDetailVO> positionsLostByDroppingVotes = getListLostByDroppingVotes(presentPartyLoosers,previousElectionWinners_Loosers);
				
		constituencyPositionsList.add(new ConstituencyPositionsVO(
				PositionType.POSITIONS_LOST_BY_DROPPING_VOTES
				, positionsLostByDroppingVotes.size()
				, positionsLostByDroppingVotes
				, "Losing Positions with droping votes percentage"));		
		
		Map<String, String> partyVotesFlown = partyVotesFlow(presentYearPartyPerformanceReportVO.getVotesFlown()
				, previousYearPartyPerformanceReportVO.getVotesFlown());
		
		BigDecimal presentElectionTotalPercentageOfVotesWon = presentYearPartyPerformanceReportVO.getTotalPercentageOfVotesWon();
		BigDecimal prevElectionTotalPercentageOfVotesWon = previousYearPartyPerformanceReportVO.getTotalPercentageOfVotesWon();
		BigDecimal diffOfTotalPercentageWinWithPrevElection = new BigDecimal(
											presentElectionTotalPercentageOfVotesWon.doubleValue() - 
											prevElectionTotalPercentageOfVotesWon.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
		
		presentYearPartyPerformanceReportVO.setDiffSeatsWon(presentYearPartyPerformanceReportVO.getTotalSeatsWon() 
				- previousYearPartyPerformanceReportVO.getTotalSeatsWon());
		presentYearPartyPerformanceReportVO.setTotalPercentageOfVotesWon(presentElectionTotalPercentageOfVotesWon);
		presentYearPartyPerformanceReportVO.setTotalPercentageOfVotesWonPreviousElection(prevElectionTotalPercentageOfVotesWon);
		presentYearPartyPerformanceReportVO.setDiffOfTotalPercentageWinWithPrevElection(diffOfTotalPercentageWinWithPrevElection);
		presentYearPartyPerformanceReportVO.setState(stateVO.getStateName());
		presentYearPartyPerformanceReportVO.setToPartySwing(partyVotesFlown);
		presentYearPartyPerformanceReportVO.setPartyVotesFlown(partyVotesFlown);
		presentYearPartyPerformanceReportVO.setConstituencyPositions(constituencyPositionsList);

		SortedMap<String, Integer> partyPositions = presentYearPartyPerformanceReportVO.getPositionDistribution();
		SortedMap<String, Integer> positionDistribution = new TreeMap<String, Integer>();
		positionDistribution.put("1st Positions", partyPositions.get("1"));
		positionDistribution.put("2nd Positions", partyPositions.get("2"));
		positionDistribution.put("3rd Positions", partyPositions.get("3"));
		positionDistribution.put("4th Positions", partyPositions.get("4"));
		positionDistribution.put("5th Positions", (partyPositions.get("5") != null)? partyPositions.get("5") : 0);
		
		
		presentYearPartyPerformanceReportVO.setPositionDistribution(positionDistribution);
		
		log.debug("getPartyPerformanceReport ended.....");
		
		return presentYearPartyPerformanceReportVO;
	}
	
	public PartyPerformanceReportVO getElectionData(String year, Long electionType, Boolean includeAllianceParties, 
											Party selectedParty, boolean isPrevYear) {
		log.debug("getElectionData start.....for year.." + year);
		
		List<Party> allianceParties = new ArrayList<Party>();
		PartyPerformanceReportVO partyPerformanceReportVO = new PartyPerformanceReportVO();
		// Process Selected Election Year Data
		List<ConstituencyElectionResult> constElectionResultList = constituencyElectionResultDAO.findByElectionTypeIdAndYear(new Long(electionType), year);
		partyPerformanceReportVO.setParty(selectedParty.getShortName());
		
		if(includeAllianceParties){
			allianceParties = staticDataService.getAllianceParties(year);
			allianceParties.remove(selectedParty);
			partyPerformanceReportVO.setAllianceParties(allianceParties);
			partyPerformanceReportVO.setYear(year);
			partyPerformanceReportVO.setParty(selectedParty.getShortName().concat(" & Alliance Parties"));
		}  
		
		Map<String, List<ConstituencyPositionDetailVO>> presentElectionData = 
											getPartyConstituenciesData(constElectionResultList, selectedParty, 
													includeAllianceParties, partyPerformanceReportVO);
        
		List<ConstituencyPositionDetailVO> partyWinners = presentElectionData.get("PARTY_WINNER");
		List<ConstituencyPositionDetailVO> partyLosers = presentElectionData.get("PARTY_LOOSER");
		List<ConstituencyPositionDetailVO> rebels = presentElectionData.get("REBEL_ALLIANCE_PARTIES");
		
		int totalSeatsWon = partyWinners.size();
		int totalSeatsLost = partyLosers.size();
		
		partyPerformanceReportVO.setPartyLosers(partyLosers);
		partyPerformanceReportVO.setPartyWinners(partyWinners);
		partyPerformanceReportVO.setRebelPartyCandidates(rebels);
		partyPerformanceReportVO.setTotalSeatsWon(totalSeatsWon);
		partyPerformanceReportVO.setTotalSeatsLost(totalSeatsLost);
		partyPerformanceReportVO.setTotalSeatsContested(totalSeatsWon + totalSeatsLost);
		
		log.debug("getElectionData Ended for year..." + year);
		return partyPerformanceReportVO;
	}

	public Map<String, List<ConstituencyPositionDetailVO>> getPartyConstituenciesData(
			List<ConstituencyElectionResult> constElectionResultList, 
			Party selectedParty, Boolean includeAllianceParties, 
			PartyPerformanceReportVO partyPerformanceReportVO){
		log.debug("getPartyConstituenciesData....start");
		
		Set<Nomination> nominations = null;			
		List<ConstituencyPositionDetailVO> positionDetailVOsForPartyWinners = new ArrayList<ConstituencyPositionDetailVO>();
		List<ConstituencyPositionDetailVO> positionDetailVOsForPartyLoosers = new ArrayList<ConstituencyPositionDetailVO>();
		List<ConstituencyPositionDetailVO> positionDetailVOForAllianceRebelParties = new ArrayList<ConstituencyPositionDetailVO>();
		Map<String, List<ConstituencyPositionDetailVO>> resultData = new HashMap<String, List<ConstituencyPositionDetailVO>>();
		List<Party> allianceParties = partyPerformanceReportVO.getAllianceParties();
		Map<String, BigDecimal> votesFlow = new HashMap<String, BigDecimal>();
		SortedMap<String, Integer> positionDistribution = new TreeMap<String, Integer>();
		double totalValidVotes = 0;
		double totalVotesEarnedByParty=0;
		
		for(ConstituencyElectionResult constElectionResult : constElectionResultList) {
			
			Nomination partyNominationWhoWon = null;
			Nomination partyNominationWhoLost = null;
			Nomination oppositePartyWhichWon = null;
			Nomination oppositePartyWhichLost = null;
			double validVotes = constElectionResult.getValidVotes();					
			double totalVotes = constElectionResult.getTotalVotes();
			totalValidVotes += validVotes;

			ConstituencyElection constituencyElection = constElectionResult.getConstituencyElection();
			String constituencyName = constituencyElection.getConstituency().getName();
			nominations = constituencyElection.getNominations();
			Party rebelAllianceParty = (includeAllianceParties) ? getNominatedPartiesAsRebels(nominations, allianceParties, selectedParty): null;
	
			for(Nomination nomination : nominations){
				Party party = nomination.getParty();
				Long rank = nomination.getCandidateResult().getRank();
	         
				boolean isSelectedParty = (selectedParty.equals(party))? true : false;
				boolean isAllianceParty = (includeAllianceParties && allianceParties.contains(party))? true : false;
				boolean isRebelAllianceParty = (includeAllianceParties && rebelAllianceParty != null && party.equals(rebelAllianceParty))? true : false;
				double votesEarned = nomination.getCandidateResult().getVotesEarned().doubleValue();
				
				if(isSelectedParty || (isAllianceParty && !isRebelAllianceParty)){
					log.debug("Selected Parties...NominationID:" + nomination.getNominationId() + " PartyName:" +nomination.getParty().getShortName() 
							+ " Rank="+nomination.getCandidateResult().getRank()
							+ " Constituency=" + constituencyElection.getConstituency().getName()
							+ " Year="+ partyPerformanceReportVO.getYear()
					);
					if(rank == 1) 
						partyNominationWhoWon = nomination;
					else 
						partyNominationWhoLost = nomination;
					
					// Party Positions
					String position = (rank > 4)? "5" : rank.toString();
					int positionCount = (positionDistribution.get(position) != null )? positionDistribution.get(position).intValue() :  0;
					positionDistribution.put(position, ++positionCount );
					
					totalVotesEarnedByParty+= votesEarned;

				} else if(!isAllianceParty || isRebelAllianceParty) {
					
					if(rank == 1) 
						oppositePartyWhichWon = nomination;
					else if(rank == 2) 
						oppositePartyWhichLost = nomination;
					
					if(isRebelAllianceParty) {
						ConstituencyPositionDetailVO  positionDetail = new ConstituencyPositionDetailVO();
						positionDetail.setCandidateName(nomination.getCandidate().getLastname());
						positionDetail.setConstiuencyName(constituencyName);
						double percentageOfVotesEarned = (nomination.getCandidateResult().getVotesEarned() * 100)/validVotes;
						positionDetail.setPercentageOfVotes(new BigDecimal(percentageOfVotesEarned).setScale(2, BigDecimal.ROUND_HALF_UP));
						positionDetail.setPresentElectionVotes(totalVotes);
						positionDetail.setRank(rank.intValue());
						// Using OppositeParty field as Rebel Candidate Party to display in front end
						positionDetail.setOppositeParty(party.getShortName());
						positionDetailVOForAllianceRebelParties.add(positionDetail);		
					}
					// Set Votes Flown to other parties
					votesFlow.put(party.getShortName(), new BigDecimal(votesEarned).setScale (2,BigDecimal.ROUND_HALF_UP));
				}
			}

			if(partyNominationWhoWon != null) {
				positionDetailVOsForPartyWinners.add(
						getConstituencyPositionDetails(constElectionResult, constituencyName, partyNominationWhoWon, oppositePartyWhichLost));
			}

			if(partyNominationWhoLost != null){
				positionDetailVOsForPartyLoosers.add(
						getConstituencyPositionDetails(constElectionResult, constituencyName, partyNominationWhoLost, oppositePartyWhichWon));
			}
									
		}
		// Set Votes Flown to other parties
		for(String partyName: votesFlow.keySet()){
			votesFlow.put(partyName, new BigDecimal((votesFlow.get(partyName).doubleValue()*100)/totalValidVotes).setScale(2,BigDecimal.ROUND_HALF_UP));
		}
		partyPerformanceReportVO.setTotalPercentageOfVotesWon(new BigDecimal((totalVotesEarnedByParty*100)/totalValidVotes).setScale(2,BigDecimal.ROUND_HALF_UP));
		partyPerformanceReportVO.setVotesFlown(votesFlow);
		partyPerformanceReportVO.setPositionDistribution(positionDistribution);
		resultData.put("PARTY_WINNER", positionDetailVOsForPartyWinners);
		resultData.put("PARTY_LOOSER", positionDetailVOsForPartyLoosers);
		resultData.put("REBEL_ALLIANCE_PARTIES", positionDetailVOForAllianceRebelParties);
		log.debug("getPartyConstituenciesData....end");
		return resultData;
	}

	public ConstituencyPositionDetailVO getConstituencyPositionDetails(ConstituencyElectionResult constElectionResult, 
			String constituencyName, Nomination partyNominationWhoWonOrLost,
			Nomination oppositePartyWhichLostOrWon) {
		log.debug("getConstituencyPositionDetails...started");
		
		double firstOrSecondHighestVotes = 0;
		String oppositePartyShortNameWhichLostOrWon = "";
		String oppositePartyLongNameWhichLostOrWon = "";
		String oppositePartyCandidateWhoLostOrWon = "";
		
		double validVotes = constElectionResult.getValidVotes();					
		double totalVotes = constElectionResult.getTotalVotes();
		double votingPercentage = 0;
		if(totalVotes!=0)
			votingPercentage = (validVotes*100)/totalVotes;
		
		if(oppositePartyWhichLostOrWon != null) {
			firstOrSecondHighestVotes = oppositePartyWhichLostOrWon.getCandidateResult().getVotesEarned();
			oppositePartyShortNameWhichLostOrWon = oppositePartyWhichLostOrWon.getParty().getShortName();
			oppositePartyLongNameWhichLostOrWon = oppositePartyWhichLostOrWon.getParty().getShortName();
			oppositePartyCandidateWhoLostOrWon = oppositePartyWhichLostOrWon.getCandidate().getLastname();
		}
		ConstituencyPositionDetailVO positionDetail = new ConstituencyPositionDetailVO();
		
		double votesWonByPartyWinner = partyNominationWhoWonOrLost.getCandidateResult().getVotesEarned();
		double percentWonByPartyWinner = 0;
		double percentWonBySecondRank = 0;
		
		if(validVotes!=0)
		{
			 percentWonByPartyWinner = (votesWonByPartyWinner*100)/validVotes;
			 percentWonBySecondRank = (firstOrSecondHighestVotes*100)/validVotes;											
		}
		
		double diffInPercent = percentWonByPartyWinner - percentWonBySecondRank; 
		
		positionDetail.setCandidateName(partyNominationWhoWonOrLost.getCandidate().getLastname());
		positionDetail.setConstiuencyName(constituencyName);
		positionDetail.setPercentageOfVotes(new BigDecimal(percentWonByPartyWinner).setScale(2, BigDecimal.ROUND_HALF_UP));
		positionDetail.setOppositePartyPercentageOfVotes(new BigDecimal(percentWonBySecondRank).setScale(2, BigDecimal.ROUND_HALF_UP));
		positionDetail.setOppositeParty(oppositePartyShortNameWhichLostOrWon);
		positionDetail.setOppositePartyCandidate(oppositePartyCandidateWhoLostOrWon);
		positionDetail.setOppositePartyLongName(oppositePartyLongNameWhichLostOrWon);
		positionDetail.setPercentageDiffBetweenTop2(new BigDecimal(diffInPercent).setScale(2, BigDecimal.ROUND_HALF_UP));
		positionDetail.setPercentageOfVotesPolled(new BigDecimal(votingPercentage).setScale (2,BigDecimal.ROUND_HALF_UP));// voting percentage for the constituency
		positionDetail.setPresentElectionVotes(Math.round(totalVotes));
		positionDetail.setRank(partyNominationWhoWonOrLost.getCandidateResult().getRank().intValue());
		log.debug("getConstituencyPositionDetails...end");
		return positionDetail;
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> partyVotesFlow(Map<String, BigDecimal> presentElectionPartyVotePerc, 
			Map<String, BigDecimal> previousElectionPartyVotePerc){
		
		Map<String, String> votesFlow = new LinkedHashMap<String, String>();
		Map<String, String> tempMap = new HashMap<String, String>();
		
	    SortedMap<String, Double> sortedVotesFlowMap = new TreeMap(new ValueComparator(presentElectionPartyVotePerc));
		int count = 0;
		 
	    for (Map.Entry<String, BigDecimal> entry : presentElectionPartyVotePerc.entrySet()) {
	    	String partyName = entry.getKey();
			double presentValue = entry.getValue().doubleValue();
			double previousValue = (previousElectionPartyVotePerc.get(partyName)==null)? 0 :
										(previousElectionPartyVotePerc.get(partyName).setScale (2,BigDecimal.ROUND_HALF_UP)).doubleValue();
			double differences = new BigDecimal(presentValue - previousValue).setScale (2,BigDecimal.ROUND_HALF_UP).doubleValue();
			StringBuilder sb = new StringBuilder();
			sb.append(presentValue).append("%");
			if(differences>=0)
				sb.append(" (Gain: ");
			else
				sb.append(" (Loss: ");
			sb.append(Math.abs(differences)).append(")");
			tempMap.put(partyName, sb.toString());
			sortedVotesFlowMap.put(partyName, presentValue);
	    }
	
		for(Map.Entry<String, Double> entry : sortedVotesFlowMap.entrySet()) {
			 if(count < 4) {
				 String partyName = entry.getKey();
				 votesFlow.put(partyName, tempMap.get(partyName));
				 count ++;
			 }
		 }
		
		return votesFlow;
	}
	
	public List<ConstituencyPositionDetailVO> getListLostByDroppingVotes(
								List<ConstituencyPositionDetailVO> presentPartyLoosers,
								List<ConstituencyPositionDetailVO> previousElectionWinners_Loosers){
		int lostPositionsLimitByDroppingVotes = 1;
		Set<ConstituencyPositionDetailVO> listOfVotesByDroppingVotes = new HashSet<ConstituencyPositionDetailVO>();
		for(ConstituencyPositionDetailVO presentValueObject : presentPartyLoosers){
			for(ConstituencyPositionDetailVO previousValueObject : previousElectionWinners_Loosers){
				if(presentValueObject.getConstiuencyName().equals(previousValueObject.getConstiuencyName())){
					int differences = presentValueObject.getPercentageOfVotesPolled().intValue()
					- previousValueObject.getPercentageOfVotesPolled().intValue();
					if(differences >= lostPositionsLimitByDroppingVotes){
						presentValueObject.setPrevElectionPercentage(previousValueObject.getPercentageOfVotes().setScale (2,BigDecimal.ROUND_HALF_UP));
						presentValueObject.setPrevElectionPercentageOfVotesPolled(previousValueObject.getPercentageOfVotesPolled());
						presentValueObject.setPrevElectionVotes(previousValueObject.getPresentElectionVotes());
						listOfVotesByDroppingVotes.add(presentValueObject);
					}
					break;
				}
			}
		}
		List<ConstituencyPositionDetailVO> constPosDetailVOlist = new ArrayList<ConstituencyPositionDetailVO>();
		constPosDetailVOlist.addAll(listOfVotesByDroppingVotes);
		return constPosDetailVOlist;
	}
	
	public Map<String, List<ConstituencyPositionDetailVO>> swingDifferenceWinData(
														List<ConstituencyPositionDetailVO> presentPartyWinners, 
														List<ConstituencyPositionDetailVO> previousElectionWinners_Loosers, 
														int negativeSwing, int positiveSwing){
		List<ConstituencyPositionDetailVO> positionDetailVOsForMajorSwingWin = new ArrayList<ConstituencyPositionDetailVO>();
		List<ConstituencyPositionDetailVO> positionDetailVOsForMinorSwingWin = new ArrayList<ConstituencyPositionDetailVO>();
		for(ConstituencyPositionDetailVO presentValueObject : presentPartyWinners){
			for(ConstituencyPositionDetailVO previousValueObject : previousElectionWinners_Loosers){
				if(presentValueObject.getConstiuencyName().equals(previousValueObject.getConstiuencyName())){
					double swingDifference = presentValueObject.getPercentageOfVotes().doubleValue()
											- previousValueObject.getPercentageOfVotes().doubleValue();
					if(swingDifference >= positiveSwing){
						presentValueObject.setPrevElectionPercentage(previousValueObject.getPercentageOfVotes());
						positionDetailVOsForMajorSwingWin.add(presentValueObject);
					}else if(swingDifference <0){
						swingDifference = -swingDifference;
						if(swingDifference >= negativeSwing){
							presentValueObject.setPrevElectionPercentage(previousValueObject.getPercentageOfVotes());
							positionDetailVOsForMinorSwingWin.add(presentValueObject);
						}
					}
					break;
				}
				
			}
		}
		Map<String, List<ConstituencyPositionDetailVO>> swingDiffWinData = new HashMap<String, List<ConstituencyPositionDetailVO>>();
		swingDiffWinData.put("POSITIVE_SWING",positionDetailVOsForMajorSwingWin);
		swingDiffWinData.put("NEGATIVE_SWING",positionDetailVOsForMinorSwingWin);
		return swingDiffWinData;
	}
	
	public Map<String, List<ConstituencyPositionDetailVO>> marginDifferenceWinData(
												List<ConstituencyPositionDetailVO> presentPartyWinners, 
												double minorBand ,double majorBand){
		List<ConstituencyPositionDetailVO> positionDetailVOsForWonMajorBand = new ArrayList<ConstituencyPositionDetailVO>();
		List<ConstituencyPositionDetailVO> positionDetailVOsForWonMinorBand = new ArrayList<ConstituencyPositionDetailVO>();
		for(ConstituencyPositionDetailVO valueObject : presentPartyWinners){
			double percentageDifference = valueObject.getPercentageDiffBetweenTop2().doubleValue();
			if(percentageDifference >= majorBand)
				positionDetailVOsForWonMajorBand.add(valueObject);
			else if(minorBand>= percentageDifference)
				positionDetailVOsForWonMinorBand.add(valueObject);
		}
		Map<String, List<ConstituencyPositionDetailVO>> marginDiffWinData = new HashMap<String, List<ConstituencyPositionDetailVO>>();
		marginDiffWinData.put("MAJOR_DIFF",positionDetailVOsForWonMajorBand);
		marginDiffWinData.put("MINOR_DIFF",positionDetailVOsForWonMinorBand);
		return marginDiffWinData;
	}
	
	
	public PartyPerformanceReportVO getPartyPerformanceReport(String state,
			String party, String year, String electionType,
			int noOfPositionDistribution) {
		// TODO Auto-generated method stub
		return null;
	}

	public PartyPerformanceReportVO getPartyPerformanceReport(String state,
			String party, String year, String electionType) {
		// TODO Auto-generated method stub
		return null;
	}

	public Party getNominatedPartiesAsRebels(Set<Nomination> nominations, List<Party> allianceParties, Party selectedParty){
		List<Party> nominatedParties = new ArrayList<Party>();
		for(Nomination nomination : nominations) {
				nominatedParties.add(nomination.getParty());
		}
		if(nominatedParties.contains(selectedParty)) {
			for(Party party : allianceParties) {
				if(nominatedParties.contains(party) )
					return party;
			}
		}
		return null;
	}

}
