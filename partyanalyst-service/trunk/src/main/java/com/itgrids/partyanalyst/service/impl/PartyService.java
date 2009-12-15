package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IConstituencyElectionResultDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dto.ConstituencyPositionDetailVO;
import com.itgrids.partyanalyst.dto.ConstituencyPositionsVO;
import com.itgrids.partyanalyst.dto.PartyPerformanceReportVO;
import com.itgrids.partyanalyst.dto.PartyPositionDisplayVO;
import com.itgrids.partyanalyst.dto.PartyPostionInfoVO;
import com.itgrids.partyanalyst.dto.PositionType;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.ConstituencyElectionResult;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.service.IPartyService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ValueComparator;

public class PartyService implements IPartyService {

	private IElectionDAO electionDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IPartyDAO partyDAO;
	private IStaticDataService staticDataService;
	private IConstituencyElectionResultDAO constituencyElectionResultDAO;
	private static int POSITIVE_SWING = 10;
	private static int NEGATIVE_SWING = 10;
	
	private final static Logger log = Logger.getLogger(PartyService.class);
	
	public void setPartyDAO(IPartyDAO partyDAO){
		this.partyDAO = partyDAO;
	}
	
	public void setElectionDAO(IElectionDAO electionDAO){
		this.electionDAO = electionDAO;
	}
	
	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO){
		this.districtDAO = districtDAO;
	}
	
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	
	public void setConstituencyElectionResultDAO(
			IConstituencyElectionResultDAO constituencyElectionResultDAO) {
		this.constituencyElectionResultDAO = constituencyElectionResultDAO;
	}

	public PartyPerformanceReportVO getPartyPerformanceReport(String state, String district,
			String party, String year, String elecType, String countryID,
			int noOfPositionDistribution, BigDecimal majorBand,
			BigDecimal minorBand, Boolean includeAllianceParties) {	
		log.debug("getPartyPerformanceReport start.....");
		
		Long stateId = new Long(state);
		Long districtId = (district != null) ? new Long(district): 0;
		Long partyId = new Long(party);
		Long electionType = new Long(elecType);
		String prevYear = electionDAO.findPreviousElectionYear(year, electionType, stateId, new Long(countryID));
				
		PartyPerformanceReportVO presentYearPartyPerformanceReportVO = getElectionResults(stateId, districtId, year, electionType, partyId, includeAllianceParties, false);
		PartyPerformanceReportVO previousYearPartyPerformanceReportVO = getElectionResults(stateId, districtId, prevYear, electionType, partyId, includeAllianceParties, true);
				
		setConstituencyPositionsForDetailedReport(presentYearPartyPerformanceReportVO, previousYearPartyPerformanceReportVO, majorBand, minorBand);
		
		/*Map<String, String> partyVotesFlown = partyVotesFlow(presentYearPartyPerformanceReportVO.getVotesFlown()
				, previousYearPartyPerformanceReportVO.getVotesFlown());*/
		
		BigDecimal presentElectionTotalPercentageOfVotesWon = presentYearPartyPerformanceReportVO.getTotalPercentageOfVotesWon();
		BigDecimal prevElectionTotalPercentageOfVotesWon = previousYearPartyPerformanceReportVO.getTotalPercentageOfVotesWon();
		BigDecimal diffOfTotalPercentageWinWithPrevElection = new BigDecimal(
											presentElectionTotalPercentageOfVotesWon.doubleValue() - 
											prevElectionTotalPercentageOfVotesWon.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
		boolean positiveVotesFlowFlag = true;
		if(diffOfTotalPercentageWinWithPrevElection.doubleValue()<0)
			positiveVotesFlowFlag = false;
		Map<String, String> partyVotesFlown = partyVotesFlow(presentYearPartyPerformanceReportVO.getVotesFlown()
				, previousYearPartyPerformanceReportVO.getVotesFlown(),positiveVotesFlowFlag);
		
		presentYearPartyPerformanceReportVO.setDiffSeatsWon(presentYearPartyPerformanceReportVO.getTotalSeatsWon() 
				- previousYearPartyPerformanceReportVO.getTotalSeatsWon());
		presentYearPartyPerformanceReportVO.setTotalPercentageOfVotesWonPreviousElection(prevElectionTotalPercentageOfVotesWon);
		presentYearPartyPerformanceReportVO.setDiffOfTotalPercentageWinWithPrevElection(diffOfTotalPercentageWinWithPrevElection);
		presentYearPartyPerformanceReportVO.setToPartySwing(partyVotesFlown);
	
		SortedMap<String, Integer> partyPositions = presentYearPartyPerformanceReportVO.getPositionDistribution();
		SortedMap<String, Integer> positionDistribution = new TreeMap<String, Integer>();
		positionDistribution.put("1st Positions", (partyPositions.get("1") != null)? partyPositions.get("1") : 0);
		positionDistribution.put("2nd Positions", (partyPositions.get("2") != null)? partyPositions.get("2") : 0);
		positionDistribution.put("3rd Positions", (partyPositions.get("3") != null)? partyPositions.get("3") : 0);
		positionDistribution.put("4th Positions", (partyPositions.get("4") != null)? partyPositions.get("4") : 0);
		positionDistribution.put("5th Positions", (partyPositions.get("5") != null)? partyPositions.get("5") : 0);
		
		
		presentYearPartyPerformanceReportVO.setPositionDistribution(positionDistribution);
		
		log.debug("positionDistribution.get(1)::"+positionDistribution.get("1st Positions"));
		log.debug("positionDistribution.get(2)::"+positionDistribution.get("2nd Positions"));
		log.debug("positionDistribution.get(3)::"+positionDistribution.get("3rd Positions"));
		log.debug("positionDistribution.get(4)::"+positionDistribution.get("4th Positions"));
		log.debug("positionDistribution.get(5)::"+positionDistribution.get("5th Positions"));
		log.debug("getPartyConstituenciesData....end");
		
		log.debug("getPartyPerformanceReport ended.....");
		
		return presentYearPartyPerformanceReportVO;
	}


	private void setConstituencyPositionsForDetailedReport(
			PartyPerformanceReportVO presentYearPartyPerformanceReportVO,
			PartyPerformanceReportVO previousYearPartyPerformanceReportVO, 
			BigDecimal majorBand, BigDecimal minorBand) {
		log.debug("comparing present and previous results for detailed report..");
		
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

		List<ConstituencyPositionDetailVO> previousElectionWinners_Loosers = new ArrayList<ConstituencyPositionDetailVO>();
		previousElectionWinners_Loosers.addAll(previousYearPartyPerformanceReportVO.getPartyWinners());
		previousElectionWinners_Loosers.addAll(previousYearPartyPerformanceReportVO.getPartyLosers());

		Map<String, List<ConstituencyPositionDetailVO>> swingDifferenceWinConstituencyPositions = 
			swingDifferenceWinData(presentPartyWinners, previousElectionWinners_Loosers);

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
			swingDifferenceWinData(presentPartyLoosers, previousElectionWinners_Loosers);

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

		presentYearPartyPerformanceReportVO.setConstituencyPositions(constituencyPositionsList);
	}

	public PartyPerformanceReportVO getElectionResults(Long stateId,
			Long districtId, String year, Long electionType, Long partyId,
			Boolean includeAllianceParties, boolean b) {
		
		log.debug("getElectionData start.....for year.." + year);
		
		Party selectedParty = partyDAO.get(partyId); 
		State stateVO = stateDAO.get(stateId);
		District districtVO = null;
		List<ConstituencyElectionResult> constElectionResultList = null;
		List<Party> allianceParties = new ArrayList<Party>();
		PartyPerformanceReportVO partyPerformanceReportVO = new PartyPerformanceReportVO();
		// Process Selected Election Year Data
		
		partyPerformanceReportVO.setParty(selectedParty.getShortName());
		partyPerformanceReportVO.setState(stateVO.getStateName());
		partyPerformanceReportVO.setYear(year);
		
		if(districtId != 0) {
			districtVO = districtDAO.get(districtId);
			partyPerformanceReportVO.setDistrict(districtVO.getDistrictName());
			constElectionResultList = constituencyElectionResultDAO.findByElectionTypeId_Year_StateId_DistrictId(new Long(electionType), year, stateId, districtId);
		} else {
			constElectionResultList = constituencyElectionResultDAO.findByElectionTypeId_Year_StateId(new Long(electionType), year, stateId);
		}
	
		if(includeAllianceParties){
			allianceParties = staticDataService.getAllianceParties(year, electionType, selectedParty.getPartyId());
			allianceParties.remove(selectedParty);
			partyPerformanceReportVO.setAllianceParties(allianceParties);
			partyPerformanceReportVO.setParty(selectedParty.getShortName().concat(" & Alliance Parties"));
		}  

		getPartyConstituenciesData(constElectionResultList, selectedParty, includeAllianceParties, partyPerformanceReportVO);
        
		log.debug("getElectionData Ended for year..." + year);
		return partyPerformanceReportVO;
	}

	public void getPartyConstituenciesData(
			List<ConstituencyElectionResult> constElectionResultList, 
			Party selectedParty, Boolean includeAllianceParties, 
			PartyPerformanceReportVO partyPerformanceReportVO){
		log.debug("getPartyConstituenciesData....start");
		
		Set<Nomination> nominations = null;			
		List<ConstituencyPositionDetailVO> positionDetailVOsForPartyWinners = new ArrayList<ConstituencyPositionDetailVO>();
		List<ConstituencyPositionDetailVO> positionDetailVOsForPartyLoosers = new ArrayList<ConstituencyPositionDetailVO>();
		List<ConstituencyPositionDetailVO> positionDetailVOForAllianceRebelParties = new ArrayList<ConstituencyPositionDetailVO>();
		
		List<Party> allianceParties = partyPerformanceReportVO.getAllianceParties();
		Map<String, BigDecimal> votesFlow = new HashMap<String, BigDecimal>();
		SortedMap<String, Integer> positionDistribution = new TreeMap<String, Integer>();
		positionDistribution.put("1",0);
		positionDistribution.put("2",0);
		positionDistribution.put("3",0);
		positionDistribution.put("4",0);
		positionDistribution.put("5",0);
		double totalValidVotes = 0;
		double totalVotesEarnedByParty=0;
		
		
		//
		
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
	        Nomination selectedPartyNomination = getSelectedPartyNomination(nominations,selectedParty);
	        Nomination alliancePartyNomination = null;
	        			
		    for(Nomination nomination : nominations){
				Party party = nomination.getParty();
				Long rank = nomination.getCandidateResult().getRank();
	         
				boolean isSelectedParty = (selectedParty.equals(party))? true : false;
				boolean isAllianceParty = (includeAllianceParties && allianceParties.contains(party))? true : false;
				boolean isRebelAllianceParty = (rebelAllianceParty != null && party.equals(rebelAllianceParty))? true : false;
				double votesEarned = nomination.getCandidateResult().getVotesEarned().doubleValue();

				// Party Positions
				/*if(isSelectedParty){
					String position = (rank > 4)? "5" : rank.toString();
					//int positionCount = (positionDistribution.get(position) != null )? positionDistribution.get(position).intValue() :  0;
					int positionCount = positionDistribution.get(position);
					positionDistribution.put(position, ++positionCount );
				}*/
				
				if(isSelectedParty || (isAllianceParty && !isRebelAllianceParty)){
					
					// Party Positions
					String position = (rank > 4)? "5" : rank.toString();
					//int positionCount = (positionDistribution.get(position) != null )? positionDistribution.get(position).intValue() :  0;
					int positionCount = positionDistribution.get(position);
					positionDistribution.put(position, ++positionCount );
					
					log.debug("Selected Parties...NominationID:" + nomination.getNominationId() + " PartyName:" +nomination.getParty().getShortName() 
							+ " Rank="+nomination.getCandidateResult().getRank()
							+ " Constituency=" + constituencyElection.getConstituency().getName()
							+ " Year="+ partyPerformanceReportVO.getYear()
					);
					if(rank == 1) 
						partyNominationWhoWon = nomination;
					else 
						partyNominationWhoLost = nomination;
					
					totalVotesEarnedByParty+= votesEarned;

				} else if(!isAllianceParty || isRebelAllianceParty) {
					
					if(rank == 1) 
						oppositePartyWhichWon = nomination;
					else if(rank == 2) 
						oppositePartyWhichLost = nomination;
					
					if(isRebelAllianceParty) {
						
						//Getting the alliance party nomination if the selected party has not participated w r to rebel party
						alliancePartyNomination = getAlliancePartyNomination(nominations,nomination,allianceParties);
						
						ConstituencyPositionDetailVO  positionDetail = new ConstituencyPositionDetailVO();
						positionDetail.setCandidateName(nomination.getCandidate().getLastname());
						positionDetail.setConstiuencyName(constituencyName);
						double percentageOfVotesEarned = (nomination.getCandidateResult().getVotesEarned() * 100)/validVotes;
						positionDetail.setPercentageOfVotes(new BigDecimal(percentageOfVotesEarned).setScale(2, BigDecimal.ROUND_HALF_UP));
						positionDetail.setPresentElectionVotes(totalVotes);
						positionDetail.setRank(rank.intValue());
						// Using OppositeParty field as Rebel Candidate Party to display in front end
						positionDetail.setPartyName(party.getShortName());
						//Set details for selected party along with rebel party details
						if(selectedPartyNomination != null){
							Party selectdParty = selectedPartyNomination.getParty();
							Candidate selectdPartyCandidate = selectedPartyNomination.getCandidate();
							positionDetail.setOppositeParty(selectdParty.getShortName());
							positionDetail.setOppositePartyCandidate(selectdPartyCandidate.getLastname());
							double selectedPartyPercentageOfVotesEarned = (selectedPartyNomination.getCandidateResult().getVotesEarned() * 100)/validVotes;
							positionDetail.setOppositePartyPercentageOfVotes(new BigDecimal(selectedPartyPercentageOfVotesEarned).setScale(2, BigDecimal.ROUND_HALF_UP));
							positionDetail.setOppositePartyRank(selectedPartyNomination.getCandidateResult().getRank().intValue());
						}
						else if(alliancePartyNomination != null){
							Party selectdParty = alliancePartyNomination.getParty();
							Candidate selectdPartyCandidate = alliancePartyNomination.getCandidate();
							positionDetail.setOppositeParty(selectdParty.getShortName());
							positionDetail.setOppositePartyCandidate(selectdPartyCandidate.getLastname());
							double selectedPartyPercentageOfVotesEarned = (alliancePartyNomination.getCandidateResult().getVotesEarned() * 100)/validVotes;
							positionDetail.setOppositePartyPercentageOfVotes(new BigDecimal(selectedPartyPercentageOfVotesEarned).setScale(2, BigDecimal.ROUND_HALF_UP));
							positionDetail.setOppositePartyRank(alliancePartyNomination.getCandidateResult().getRank().intValue());
						}
						positionDetailVOForAllianceRebelParties.add(positionDetail);		
					}
					// Set Votes Flown to other parties
					//if(votesFlow.get(party.getShortName())!= null)
					if(votesFlow.containsKey(party.getShortName()))
						votesEarned+=votesFlow.get(party.getShortName()).doubleValue();
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
		double votesPercentage = 0;
		if(totalValidVotes!=0){
			votesPercentage = (totalVotesEarnedByParty*100)/totalValidVotes ;
		}
		partyPerformanceReportVO.setTotalPercentageOfVotesWon(new BigDecimal(votesPercentage).setScale(2,BigDecimal.ROUND_HALF_UP));
		partyPerformanceReportVO.setVotesFlown(votesFlow);
		partyPerformanceReportVO.setPositionDistribution(positionDistribution);
		partyPerformanceReportVO.setPartyWinners(positionDetailVOsForPartyWinners);
		partyPerformanceReportVO.setPartyLosers(positionDetailVOsForPartyLoosers);
		partyPerformanceReportVO.setRebelPartyCandidates(positionDetailVOForAllianceRebelParties);
		partyPerformanceReportVO.setTotalSeatsWon(positionDetailVOsForPartyWinners.size());
		//partyPerformanceReportVO.setTotalSeatsWon(positionDistribution.get("1"));
		partyPerformanceReportVO.setTotalSeatsLost(positionDetailVOsForPartyLoosers.size());
		partyPerformanceReportVO.setTotalSeatsContested(positionDetailVOsForPartyWinners.size() + positionDetailVOsForPartyLoosers.size());
		
		log.debug("positionDistribution.get(1)::"+positionDistribution.get("1"));
		log.debug("positionDistribution.get(2)::"+positionDistribution.get("2"));
		log.debug("positionDistribution.get(3)::"+positionDistribution.get("3"));
		log.debug("positionDistribution.get(4)::"+positionDistribution.get("4"));
		log.debug("positionDistribution.get(5)::"+positionDistribution.get("5"));
		log.debug("getPartyConstituenciesData....end");
	}
	
	public Nomination getAlliancePartyNomination(Set<Nomination> nominations,Nomination nomination,List<Party> parties){
		//nominations.remove(nomination);
		List<Nomination> allianceNominations = new ArrayList<Nomination>();
		Nomination selectedNomination = null;
		int rank = nomination.getCandidateResult().getRank().intValue();
		for(Nomination allianceNomination:nominations){
			if(parties.contains(allianceNomination.getParty()))
				allianceNominations.add(allianceNomination);
		}
		if(allianceNominations != null && allianceNominations.size() > 0){
			for(Nomination nominatn:allianceNominations)
				if(nominatn.getCandidateResult().getRank().intValue() < rank)
					selectedNomination = nominatn;
		}
			
	return selectedNomination;
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
			Map<String, BigDecimal> previousElectionPartyVotePerc, boolean isPositiveVotesFlow){
		
		Map<String, String> votesFlow = new LinkedHashMap<String, String>();
		Map<String, String> tempMap = new HashMap<String, String>();
		
	    SortedMap<String, Double> sortedVotesFlowMap = new TreeMap(new ValueComparator(presentElectionPartyVotePerc));
		int count = 0;
		 
	    for (Map.Entry<String, BigDecimal> entry : presentElectionPartyVotePerc.entrySet()) {
	    	String partyName = entry.getKey();
			double presentValue = entry.getValue().doubleValue();
			double previousValue = (previousElectionPartyVotePerc.get(partyName)== null)? 0 :
										(previousElectionPartyVotePerc.get(partyName).setScale (2,BigDecimal.ROUND_HALF_UP)).doubleValue();
			double differences = new BigDecimal(presentValue - previousValue).setScale (2,BigDecimal.ROUND_HALF_UP).doubleValue();
			if(Math.abs(differences)<1)
				continue;
			StringBuilder sb = new StringBuilder();
			if(isPositiveVotesFlow && differences<0 ){
				sb.append(presentValue).append("%").append(" (Loss: ").append(Math.abs(differences)).append(")");
			}
			else if(!isPositiveVotesFlow && differences>=0){
				sb.append(presentValue).append("%").append(" (Gain: ").append(Math.abs(differences)).append(")");
			}
			tempMap.put(partyName, sb.toString());
			sortedVotesFlowMap.put(partyName, presentValue);
	    }
	
		for(Map.Entry<String, Double> entry : sortedVotesFlowMap.entrySet()) {
			 String partyName = entry.getKey();
			if(log.isDebugEnabled())
				log.debug("partyNames::::"+partyName);
				 String value = tempMap.get(partyName);
				 if(value.length()>0)
					 votesFlow.put(partyName, value);
				 count ++;
		 }
		
		return votesFlow;
	}
	
	public List<ConstituencyPositionDetailVO> getListLostByDroppingVotes(
			List<ConstituencyPositionDetailVO> presentPartyLoosers,
			List<ConstituencyPositionDetailVO> previousElectionWinners_Loosers){
		Set<ConstituencyPositionDetailVO> listOfVotesByDroppingVotes = new HashSet<ConstituencyPositionDetailVO>();
		for(ConstituencyPositionDetailVO presentValueObject : presentPartyLoosers){
			for(ConstituencyPositionDetailVO previousValueObject : previousElectionWinners_Loosers){
				if(presentValueObject.getConstiuencyName().equals(previousValueObject.getConstiuencyName())){
					int differences = previousValueObject.getPercentageOfVotesPolled().intValue()
					- presentValueObject.getPercentageOfVotesPolled().intValue();
					if(differences >= IConstants.LOOSING_BY_DROPPING_VOTES_CONSTANTS){
						presentValueObject.setPrevElectionPercentage(previousValueObject.getPercentageOfVotes().setScale (2,BigDecimal.ROUND_HALF_UP));
						presentValueObject.setPrevElectionPercentageOfVotesPolled(previousValueObject.getPercentageOfVotesPolled());
						presentValueObject.setPrevElectionVotes(previousValueObject.getPresentElectionVotes());
						presentValueObject.setPrevElectionCandidateName(previousValueObject.getCandidateName());
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
			List<ConstituencyPositionDetailVO> previousElectionWinners_Loosers){
		List<ConstituencyPositionDetailVO> positionDetailVOsForMajorSwingWin = new ArrayList<ConstituencyPositionDetailVO>();
		List<ConstituencyPositionDetailVO> positionDetailVOsForMinorSwingWin = new ArrayList<ConstituencyPositionDetailVO>();
		for(ConstituencyPositionDetailVO presentValueObject : presentPartyWinners){
			for(ConstituencyPositionDetailVO previousValueObject : previousElectionWinners_Loosers){
				if(presentValueObject.getConstiuencyName().equals(previousValueObject.getConstiuencyName())){
					double swingDifference = presentValueObject.getPercentageOfVotes().doubleValue()
					- previousValueObject.getPercentageOfVotes().doubleValue();
					if(swingDifference >= POSITIVE_SWING){
						presentValueObject.setPrevElectionPercentage(previousValueObject.getPercentageOfVotes());
						positionDetailVOsForMajorSwingWin.add(presentValueObject);
					}else if(swingDifference <0){
						swingDifference = -swingDifference;
						if(swingDifference >= NEGATIVE_SWING){
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
			if(percentageDifference < 0) 
				percentageDifference = -percentageDifference;
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
			for(Party party : nominatedParties) {
				if(allianceParties.contains(party) && !party.getPartyId().equals(selectedParty.getPartyId()))
					return party;
			}
		}
		else if(!nominatedParties.contains(selectedParty)){
			List<Party> tempPartys = new ArrayList<Party>();
			int rank=0;
			Party resultParty = null;
			for(Party party:allianceParties){
				if(nominatedParties.contains(party))
					tempPartys.add(party);
			}
			if(tempPartys.size() > 1){
			   for(Nomination nomination:nominations){
				 if(tempPartys.contains(nomination.getParty())){
					if(nomination.getCandidateResult().getRank().intValue()>rank){
						rank = nomination.getCandidateResult().getRank().intValue();
						resultParty = nomination.getParty();
					}
				 }
			   }
			}
			return resultParty;
		}
		return null;
	}
	
	public String getBlo(String str) {
		String reply = "In getBlo-String";
		System.out.println(reply);
		return reply+"+Reply";
	}

	public String getBlah(String str) {
		String reply = "In getBlah-String";
		System.out.println(reply);
		return reply+"+Reply";
	}

	public String getBlah(int str) {
		String reply = "In getBlah-int";
		System.out.println(reply);
		return reply+"+Reply";
	}
	
	public List<PartyPositionDisplayVO> getNthPositionPartyDetails(Long electionTypeID, Long stateID, 
			Long districtID, Long year, Long partyID, boolean alliances, int rank){
		List<PartyPositionDisplayVO>  partyPositionDisplayVOList = new ArrayList<PartyPositionDisplayVO>();
		
		if(log.isDebugEnabled())
			log.debug(" Inside getNthPositionPartyDetails Method ....");
		
		Long electionID = getElectionID(electionTypeID, 1L, stateID, year);
		
		if(log.isDebugEnabled())
			log.debug("ElectionId -->" + electionID);
		
		List<SelectOptionVO> parties = getAlliancePartiesAsVO(year, electionTypeID, partyID,alliances);
		
		if(log.isDebugEnabled())
			log.debug("Alliance Parties -->" + parties.size());
		List<ConstituencyElection> constituencyElectionList =  staticDataService.getConstituencyElections(electionID,stateID, districtID);
		
		if(constituencyElectionList != null)
			if(log.isDebugEnabled())
				log.debug("inside getNth position -->" + constituencyElectionList.size());
				
		for(ConstituencyElection constituencyElection : constituencyElectionList){
			PartyPositionDisplayVO partyPositionDisplayVO = getPartyPositionDisplayVO(constituencyElection, parties, partyID, rank,alliances);
			if(partyPositionDisplayVO!=null)
				partyPositionDisplayVOList.add(partyPositionDisplayVO);
		}
		return partyPositionDisplayVOList;
	}
	
	public PartyPositionDisplayVO getPartyPositionDisplayVO(ConstituencyElection constituencyElection, 
			List<SelectOptionVO> parties, Long partyID, int rank,Boolean alliances){
		PartyPositionDisplayVO partyPositionDisplayVO = new PartyPositionDisplayVO();
		Set<Nomination> nominations = constituencyElection.getNominations();
		
		if(log.isDebugEnabled())
			log.debug("Inside getPartyPositionDisplayVO -->" + nominations.size());
				
		Nomination selectedNomination = partyParticipatedNomination(nominations , parties, rank,partyID,alliances);
		if(selectedNomination == null)
			return null;
		partyPositionDisplayVO.setConstituencyName(constituencyElection.getConstituency().getName());
		StringBuilder fullName = new StringBuilder();
		if(selectedNomination.getCandidate().getFirstname()!=null)
			fullName.append(selectedNomination.getCandidate().getFirstname()).append(" ");
		if(selectedNomination.getCandidate().getMiddlename()!=null)
			fullName.append(selectedNomination.getCandidate().getMiddlename()).append(" ");
		if(selectedNomination.getCandidate().getLastname()!=null)
			fullName.append(selectedNomination.getCandidate().getLastname());
		if(fullName.length()>0)
			partyPositionDisplayVO.setCandidateName(fullName.toString());
		partyPositionDisplayVO.setRank(selectedNomination.getCandidateResult().getRank());
		partyPositionDisplayVO.setVotePercentage(selectedNomination.getCandidateResult().getVotesPercengate());
		
		List<PartyPostionInfoVO>  partyPostionInfoVOList = new ArrayList<PartyPostionInfoVO>();
		for(Nomination nomination : nominations){
			Long pID = nomination.getParty().getPartyId();
			if(pID.equals(partyID))
				continue;
			
			if((rank ==-1 && nomination.getCandidateResult().getRank().intValue() <3) 
					|| nomination.getCandidateResult().getRank().intValue() < rank){
				PartyPostionInfoVO partyPostionInfoVO = new PartyPostionInfoVO();
				String partyName = nomination.getParty().getShortName();
				if(partyName==null || partyName.length()==0)	
					partyName = nomination.getParty().getLongName();
				partyPostionInfoVO.setPartyName(partyName);
				StringBuilder name = new StringBuilder();
				if(nomination.getCandidate().getFirstname()!=null)
					name.append(nomination.getCandidate().getFirstname()).append(" ");
				if(nomination.getCandidate().getMiddlename()!=null)
					name.append(nomination.getCandidate().getMiddlename()).append(" ");
				if(nomination.getCandidate().getLastname()!=null)
					name.append(nomination.getCandidate().getLastname());
				
				
				partyPostionInfoVO.setCandidateName(name.toString());//rank vote %
				partyPostionInfoVO.setRank(Integer.valueOf(nomination.getCandidateResult().getRank().intValue()));
				partyPostionInfoVO.setVotePercentage(nomination.getCandidateResult().getVotesPercengate());
				partyPostionInfoVOList.add(partyPostionInfoVO);
				
			}
		}
		if(log.isDebugEnabled()){
			log.debug("partyPostionInfoVOList::::"+partyPostionInfoVOList.size());
			log.debug("rank::::"+rank);
		}
		Collections.sort(partyPostionInfoVOList);
		partyPositionDisplayVO.setOppPartyPositionInfoList(partyPostionInfoVOList);
		
		
		return partyPositionDisplayVO;
	}
	
	public Nomination partyParticipatedNomination(Set<Nomination> nominations, List<SelectOptionVO> parties, int rank,Long partyID,boolean alliances){
		
		
		if(log.isDebugEnabled())
			log.debug("partyParticipatedNomination method starts....");
		
		Nomination selectedNomination = null;
		List<Long> alliancePartyIds = null;
		if(parties != null && parties.size() >0 && alliances == true)
		alliancePartyIds = getAlliancePartyIds(parties);
		
		if(alliancePartyIds != null)
			if(log.isDebugEnabled())
				log.debug("allianceParty's size-->" + alliancePartyIds.size());
		
		List<Nomination> selectedPartyAndItsAlliances = new ArrayList<Nomination>();
		
		for(Nomination nomination : nominations){
			
			if(log.isDebugEnabled())
				log.debug("Party Name -->" + nomination.getParty().getLongName());
			log.debug("Party Id -->" + nomination.getParty().getPartyId());
			log.debug("Input Party Id -->" + partyID);
			log.debug("Rank Obtained -->" + nomination.getCandidateResult().getRank());
			log.debug("Has Alliances -->" + alliances);
			
			if(alliances == false && nomination.getParty().getPartyId().equals(partyID) && nomination.getCandidateResult().getRank().intValue() == rank){
				if(log.isDebugEnabled())
					log.debug("Constituency Selected -->" + nomination.getConstituencyElection().getConstituency().getName());
				return nomination;
			}
			
			if(alliancePartyIds != null && alliancePartyIds.size() > 0 && alliances == true){
			  if(alliancePartyIds.contains(nomination.getParty().getPartyId())) 
				 selectedPartyAndItsAlliances.add(nomination);
			}
			
		}
		if(selectedPartyAndItsAlliances != null && selectedPartyAndItsAlliances.size() > 0 && alliances == true){
			
			if(selectedPartyAndItsAlliances.size() == 1 && selectedPartyAndItsAlliances.get(0).getCandidateResult().getRank().intValue() == rank){
				selectedNomination = selectedPartyAndItsAlliances.get(0);
				return selectedNomination;
			}
		  for(Nomination selectdNomination:selectedPartyAndItsAlliances){
			   
			  if(selectdNomination.getCandidateResult().getRank().intValue() < rank)
				  return null;
			  if(rank == -1 && selectdNomination.getCandidateResult().getRank() < 5)
				  return null;
			if((rank==-1 && selectdNomination.getCandidateResult().getRank().intValue() > 4) || selectdNomination.getCandidateResult().getRank().intValue() == rank)
				selectedNomination = selectdNomination;
		  }
		}
		/*
		for(Nomination nomination : nominations){
			if((rank==-1 && nomination.getCandidateResult().getRank().intValue() > 4)
					|| nomination.getCandidateResult().getRank().intValue() == rank){
				if(partyID.equals(nomination.getParty().getPartyId()))
					return nomination;
				for(SelectOptionVO party : parties){
					if(nomination.getParty().getPartyId().equals(party.getId())){
						selectedNomination = nomination;
						return selectedNomination;	
					}
				}
			}
			
		}*/
		if(log.isDebugEnabled())
			log.debug("selectedNomination  return");
		
	return selectedNomination;		
	}
	
	public List<SelectOptionVO> getAlliancePartiesAsVO(Long year, Long electionTypeID, Long partyID, boolean alliances){

		List<SelectOptionVO> parties = new ArrayList<SelectOptionVO>();
		
		if(log.isDebugEnabled())
			log.debug("Entered Into getAllianceParties Method .....");
		if(alliances){
			parties = staticDataService.getAlliancePartiesAsVO(year.toString(), electionTypeID, partyID);
			if(parties != null){
				for(SelectOptionVO option:parties)
					if(log.isDebugEnabled())
						log.debug("Alliance Parties ::" + option.getName());
			}
		}
		if(parties.size() == 0){
			Party party = partyDAO.get(partyID);
			SelectOptionVO obj = new SelectOptionVO();
			obj.setId(partyID);
			String name = party.getShortName();
			if(name==null || name.length()==0)
				name = party.getLongName();
			parties.add(obj);
		}
		return parties;
	}
	
	public Long getElectionID(Long electionTypeID, Long countryID, Long stateID,Long year){
		List<Election> elections = electionDAO.findByPropertyTypeId_CountryId_StateId(electionTypeID, countryID, stateID);
		Long electionID = 0L;
		for(Election election : elections){
			if(year.toString().equals(election.getElectionYear())){
				electionID = election.getElectionId();
				break;
			}
		}
		return electionID;
	}
	
	public Nomination getSelectedPartyNomination(Set<Nomination> nominations,Party selectedParty){
		for(Nomination nomination:nominations){
			if(nomination.getParty().getPartyId().equals(selectedParty.getPartyId()))
				return nomination;
		}
	return null;
	}
	
	public List<Long> getAlliancePartyIds(List<SelectOptionVO> allianceParties){
		List<Long> partyIds = null;
		if(allianceParties != null && allianceParties.size() > 0){
		 partyIds = new ArrayList<Long>();
		 for(SelectOptionVO resultVO:allianceParties)
			partyIds.add(resultVO.getId());
		}
	return partyIds;
	}

}
