package com.itgrids.partyanalyst.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/*import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionResultDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;*/


import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.columns.enums.PartyColumnNames;

import com.itgrids.partyanalyst.dto.ConstituencyPositionDetailVO;
import com.itgrids.partyanalyst.dto.ConstituencyPositionsVO;
import com.itgrids.partyanalyst.dto.PartyPerformanceReportVO;
import com.itgrids.partyanalyst.dto.PositionType;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.ConstituencyElectionResult;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.utils.ValueComparator;

import java.util.Collections;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PartyService implements IPartyService {

	private IConstituencyDAO constituencyDAO;
	private IElectionDAO electionDAO;
	private IStateDAO stateDAO;
	private State stateVO;
	private IPartyDAO partyDAO;
	
	private final static Log log = LogFactory.getLog(PartyService.class);
	
	public void setPartyDAO(IPartyDAO partyDAO){
		this.partyDAO = partyDAO;
	}
	
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO){
		this.constituencyDAO = constituencyDAO;
	}
	
	public void setElectionDAO(IElectionDAO electionDAO){
		this.electionDAO = electionDAO;
	}
	
	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public PartyPerformanceReportVO getPartyPerformanceReport(String state,
			String partyId, String year, String electionType, String countryID,
			int noOfPositionDistribution, BigDecimal majorBand,
			BigDecimal minorBand) {	
		
		String selectedPartyName = null;
		List<Constituency> constituencyList = constituencyDAO.getAll();
		stateVO = stateDAO.get(new Long(state));
		
		Party party = partyDAO.get(new Long(partyId));
		if(party != null){
			selectedPartyName = party.getLongName();
		}
		
		// To avoid duplicates while EAGER loading
		Set<Constituency> constituencies = new HashSet<Constituency>();
		constituencies.addAll(constituencyList);
		
		if(constituencies==null){
			log.debug("PartyService constituencies is null.....");
			System.out.println("PartyService constituencies is null.....");
		}else{
			log.debug("PartyService constituencies is not null....."+ constituencies.size());
			System.out.println("PartyService constituencies is not null....."+ constituencies.size());			
		}
		Map<String, List<ConstituencyPositionDetailVO>> presentElectionData = 
											getPartyConstituenciesData(constituencies, selectedPartyName, year, electionType);
        System.out.println("getPartyConstituencyiesData:...."+ presentElectionData.size());
		List<ConstituencyPositionDetailVO> presentPartyWinners = presentElectionData.get("PARTY_WINNER");
		List<ConstituencyPositionDetailVO> presentPartyLoosers = presentElectionData.get("PARTY_LOOSER");
		System.out.println("Narender.........................");
		System.out.println("constituencies.size():"+constituencies.size());
		System.out.println("presentPartyWinners.size():"+presentPartyWinners.size());
		System.out.println("presentPartyLoosers.size():"+presentPartyLoosers.size());

		int noOfConstitueciesPartyWon = presentPartyWinners.size();
		int noOfConstitueciesPartyLost = presentPartyLoosers.size();
		int totalSeatsPartyContested = noOfConstitueciesPartyWon + noOfConstitueciesPartyLost;
		
		List<String> years = getElectionYears(electionType, state, countryID);		
		String prevYear = getPreviousYear(years, year);
		
		Map<String, List<ConstituencyPositionDetailVO>> previousElectionData = 
											getPartyConstituenciesData(constituencies, selectedPartyName, prevYear, electionType);

		List<ConstituencyPositionDetailVO> previousPartyWinners = previousElectionData.get("PARTY_WINNER");
		List<ConstituencyPositionDetailVO> previousPartyLoosers = previousElectionData.get("PARTY_LOOSER");

		int noOfConstitueciesPartyPrevWon = previousPartyWinners.size();
		int noOfConstitueciesPartyPrevLost = previousPartyLoosers.size();
		int totalSeatsPartyPreviouslyContested = noOfConstitueciesPartyPrevWon + noOfConstitueciesPartyPrevLost;
		
		//double presentElectionTotalVotesWon = getTotalVotes(presentPartyWinners);
		BigDecimal presentElectionTotalPercentageOfVotesWon = getTotalPercentageOfVotesWon(constituencies, selectedPartyName, year, electionType);
		BigDecimal previousElectionTotalPercentageOfVotesWon = getTotalPercentageOfVotesWon(constituencies, selectedPartyName, prevYear, electionType);
		
		int differenceOfTotalWinWithPrevElection = noOfConstitueciesPartyWon - noOfConstitueciesPartyPrevWon;
		BigDecimal diffOfTotalPercentageWinWithPrevElection = new BigDecimal(
											presentElectionTotalPercentageOfVotesWon.doubleValue() - 
											previousElectionTotalPercentageOfVotesWon.doubleValue());
		int[] partyPositions = getPartyPosition(presentPartyLoosers);
		
		//Map<PositionType, List<ConstituencyPositionDetailVO>> constituencyPositionDetails = new HashMap<PositionType, List<ConstituencyPositionDetailVO>>();
		
		Map<String, List<ConstituencyPositionDetailVO>> marginDifferenceWinConstituencyPositions = 
				marginDifferenceWinData(presentPartyWinners, minorBand.doubleValue(),majorBand.doubleValue());
		//constituencyPositionDetails.put(PositionType.POSITIONS_WON_MAJOR_BAND, marginDifferenceWinConstituencyPositions.get("MAJOR_DIFF"));
		//constituencyPositionDetails.put(PositionType.POSITIONS_WON_MINOR_BAND, marginDifferenceWinConstituencyPositions.get("MINOR_DIFF"));
		
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
			marginDifferenceWinData(presentPartyLoosers, minorBand.doubleValue(),majorBand.doubleValue());
		//constituencyPositionDetails.put(PositionType.POSITIONS_LOST_MAJOR_BAND, marginDifferenceLossConstituencyPositions.get("MAJOR_DIFF"));
		//constituencyPositionDetails.put(PositionType.POSITIONS_LOST_MINOR_BAND, marginDifferenceLossConstituencyPositions.get("MINOR_DIFF"));
		
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
		
		int positiveSwing = 35;
		int negativeSwing = 5;
		List<ConstituencyPositionDetailVO> previousElectionWinners_Loosers = new ArrayList<ConstituencyPositionDetailVO>();
		previousElectionWinners_Loosers.addAll(previousPartyWinners);
		previousElectionWinners_Loosers.addAll(previousPartyLoosers);
		
		Map<String, List<ConstituencyPositionDetailVO>> swingDifferenceWinConstituencyPositions = 
				swingDifferenceWinData(presentPartyWinners, previousElectionWinners_Loosers, negativeSwing, positiveSwing);
		//constituencyPositionDetails.put(PositionType.POSITIONS_WON_WITH_POSITIVE_SWING, swingDifferenceWinConstituencyPositions.get("POSITIVE_SWING"));
		//constituencyPositionDetails.put(PositionType.POSITIONS_WON_WITH_NEGATIVE_SWING, swingDifferenceWinConstituencyPositions.get("NEGATIVE_SWING"));
		
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
			swingDifferenceWinData(presentPartyLoosers, previousElectionWinners_Loosers, negativeSwing, positiveSwing);
		//constituencyPositionDetails.put(PositionType.POSITIONS_LOST_WITH_POSITIVE_SWING, swingDifferenceLossConstituencyPositions.get("POSITIVE_SWING"));
		//constituencyPositionDetails.put(PositionType.POSITIONS_LOST_WITH_NEGATIVE_SWING, swingDifferenceLossConstituencyPositions.get("NEGATIVE_SWING"));
	
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
		//constituencyPositionDetails.put(PositionType.POSITIONS_LOST_BY_DROPPING_VOTES, positionsLostByDroppingVotes);
		
		constituencyPositionsList.add(new ConstituencyPositionsVO(
				PositionType.POSITIONS_LOST_BY_DROPPING_VOTES
				, positionsLostByDroppingVotes.size()
				, positionsLostByDroppingVotes
				, "Losing Positions with droping votes percentage"));		
		
		Map<String, BigDecimal> presentElectionPartyVotePerc = votesFlow(constituencies, year, electionType); 
		Map<String, BigDecimal> previousElectionPartyVotePerc = votesFlow(constituencies, prevYear, electionType);
		Map<String, String> partyVotesFlown = partyVotesFlow(presentElectionPartyVotePerc, previousElectionPartyVotePerc, selectedPartyName);
		
		PartyPerformanceReportVO partyPerformanceReportVO = new PartyPerformanceReportVO();
		partyPerformanceReportVO.setTotalSeatsContested(totalSeatsPartyContested);
		partyPerformanceReportVO.setTotalSeatsWon(noOfConstitueciesPartyWon);
		partyPerformanceReportVO.setTotalSeatsLost(noOfConstitueciesPartyLost);
		partyPerformanceReportVO.setDiffSeatsWon(differenceOfTotalWinWithPrevElection);
		partyPerformanceReportVO.setTotalPercentageOfVotesWon(presentElectionTotalPercentageOfVotesWon.setScale (2,BigDecimal.ROUND_HALF_UP));
		partyPerformanceReportVO.setTotalPercentageOfVotesWonPreviousElection(previousElectionTotalPercentageOfVotesWon.setScale (2,BigDecimal.ROUND_HALF_UP) );
		partyPerformanceReportVO.setDiffOfTotalPercentageWinWithPrevElection(diffOfTotalPercentageWinWithPrevElection.setScale (2,BigDecimal.ROUND_HALF_UP));
		partyPerformanceReportVO.setYear(year);
		partyPerformanceReportVO.setState(stateVO.getStateName());
		partyPerformanceReportVO.setParty(selectedPartyName);
		partyPerformanceReportVO.setToPartySwing(partyVotesFlown);
		//partyPerformanceReportVO.setConstituencyPositions( );
		//partyPerformanceReportVO.setReportsInfo(constituencyPositionDetails); 
		//partyPerformanceReportVO.setConstituencyPositions(partyPositions);
		partyPerformanceReportVO.setPartyVotesFlown(partyVotesFlown);
		partyPerformanceReportVO.setConstituencyPositions(constituencyPositionsList);
		
		//Map<Integer,Integer> positionDistribution = new LinkedHashMap<Integer,Integer>();
		SortedMap<String, Integer> positionDistribution = new TreeMap<String, Integer>();
		positionDistribution.put("1st Positions", noOfConstitueciesPartyWon);
		positionDistribution.put("2nd Positions", new Integer(partyPositions[0]));
		positionDistribution.put("3rd Positions", new Integer(partyPositions[1]));
		positionDistribution.put("4th Positions", new Integer(partyPositions[2]));
		positionDistribution.put("5th Positions", new Integer(partyPositions[3]));
		
		partyPerformanceReportVO.setPositionDistribution(positionDistribution);
		
		return partyPerformanceReportVO;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, String> partyVotesFlow(Map<String, BigDecimal> presentElectionPartyVotePerc, 
			Map<String, BigDecimal> previousElectionPartyVotePerc, String selectedParty){

		double selectedPartyPresentVotePerc = presentElectionPartyVotePerc.remove(selectedParty).doubleValue();
		
		double selectedPartyPreviousVotePerc = 0;
		if(previousElectionPartyVotePerc!=null && previousElectionPartyVotePerc.size()>0 && previousElectionPartyVotePerc.get(selectedParty)!=null)
			selectedPartyPreviousVotePerc = previousElectionPartyVotePerc.remove(selectedParty).doubleValue();
		double selectedPartyPercDiff = selectedPartyPresentVotePerc - selectedPartyPreviousVotePerc;
		Map<String, String> tempMap = new HashMap<String, String>();
	    SortedMap<String, Double> sortedVotesFlowMap = new TreeMap(new ValueComparator(presentElectionPartyVotePerc));
	    
		Set<Entry<String, BigDecimal>> set = presentElectionPartyVotePerc.entrySet();
		Iterator<Entry<String, BigDecimal>> itr = set.iterator();
		
		while(itr.hasNext()){
			Map.Entry<String, BigDecimal> entry = (Map.Entry<String, BigDecimal>)itr.next();
			String partyName = entry.getKey();
			double presentValue = entry.getValue().doubleValue();
			double previousValue = (previousElectionPartyVotePerc.get(partyName)==null)? 0:(previousElectionPartyVotePerc.get(partyName).setScale (2,BigDecimal.ROUND_HALF_UP)).doubleValue();
			double differences = new BigDecimal(presentValue - previousValue).setScale (2,BigDecimal.ROUND_HALF_UP).doubleValue();
			StringBuilder sb = new StringBuilder();
			sb.append(presentValue).append("%");
			if(differences>=0 && selectedPartyPercDiff<0){
				sb.append(" (Gain: ").append(Math.abs(differences)).append(")");
				tempMap.put(partyName, sb.toString());
				sortedVotesFlowMap.put(partyName, presentValue);
			}
			else if(differences<0 && selectedPartyPercDiff>=0){
				sb.append(" (Loss: ").append(Math.abs(differences)).append(")");
				tempMap.put(partyName, sb.toString());
				sortedVotesFlowMap.put(partyName, presentValue);
			} 
		}

		 Map<String, String> votesFlow = new LinkedHashMap<String, String>();
			
		 Set<Entry<String, Double>> resultSet = sortedVotesFlowMap.entrySet();
		 Iterator<Entry<String, Double>> itrResult = resultSet.iterator();
		 int count = 0;
		 while(itrResult.hasNext() && count < 4) {
			 Map.Entry<String, Double> entry = (Map.Entry<String, Double>)itrResult.next();
			 String partyName = entry.getKey();
				System.out.println(partyName);
			 if(partyName!=null && tempMap.get(partyName)!=null){
					System.out.println(partyName);
				 votesFlow.put(partyName, tempMap.get(partyName));
				 count ++;
				 
			 }
		 }
		System.out.println("Votes Flow1");
		return votesFlow;
	}
	@SuppressWarnings("unchecked")
	public Map<String, String> partyVotesFlow2(Map<String, BigDecimal> presentElectionPartyVotePerc, 
			Map<String, BigDecimal> previousElectionPartyVotePerc, String selectedParty){

		double selectedPartyPresentVotePerc = presentElectionPartyVotePerc.remove(selectedParty).doubleValue();
		double selectedPartyPreviousVotePerc = previousElectionPartyVotePerc.remove(selectedParty).doubleValue();
		double selectedPartyPercDiff = selectedPartyPresentVotePerc - selectedPartyPreviousVotePerc;
		Map<String, String> tempMap = new HashMap<String, String>();
	   // SortedMap<String, Double> sortedVotesFlowMap = new TreeMap(new ValueComparator(presentElectionPartyVotePerc));
	    
		Set<Entry<String, BigDecimal>> set = presentElectionPartyVotePerc.entrySet();
		Iterator<Entry<String, BigDecimal>> itr = set.iterator();
		
		while(itr.hasNext()){
			Map.Entry<String, BigDecimal> entry = (Map.Entry<String, BigDecimal>)itr.next();
			String partyName = entry.getKey();
			double presentValue = entry.getValue().doubleValue();
			double previousValue = (previousElectionPartyVotePerc.get(partyName)==null)? 0:(previousElectionPartyVotePerc.get(partyName).setScale (2,BigDecimal.ROUND_HALF_UP)).doubleValue();
			double differences = new BigDecimal(presentValue - previousValue).setScale (2,BigDecimal.ROUND_HALF_UP).doubleValue();
			StringBuilder sb = new StringBuilder();
			sb.append(presentValue).append("%");
			if(differences>=0 && selectedPartyPercDiff<0){
				sb.append(" (Gain: ").append(Math.abs(differences)).append(")");
				tempMap.put(partyName, sb.toString());
				//sortedVotesFlowMap.put(partyName, presentValue);
			}
			else if(differences<0 && selectedPartyPercDiff>=0){
				sb.append(" (Loss: ").append(Math.abs(differences)).append(")");
				tempMap.put(partyName, sb.toString());
				//sortedVotesFlowMap.put(partyName, presentValue);
			} 
		}

		 /*Map<String, String> votesFlow = new LinkedHashMap<String, String>();
			
		 Set<Entry<String, Double>> resultSet = sortedVotesFlowMap.entrySet();
		 Iterator<Entry<String, Double>> itrResult = resultSet.iterator();
		 int count = 0;
		 while(itrResult.hasNext() && count < 4) {
			 Map.Entry<String, Double> entry = (Map.Entry<String, Double>)itrResult.next();
			 String partyName = entry.getKey();
			 votesFlow.put(partyName, tempMap.get(partyName));
			 count ++;
		 }
		System.out.println("Votes Flow1");
		return votesFlow;*/
		return tempMap;
	}

	public Map<String, BigDecimal> votesFlow(Set<Constituency> constituencies,String year,String electionType)
	{
		Map<String, BigDecimal> partiesList = new HashMap<String, BigDecimal>();
		Set<Nomination> nominations = null;
		
		double totalValidVotes = 0;
		for(Constituency constituency:constituencies){
			Set<ConstituencyElection> constituencyElections = constituency.getConstituencyElections();
			for(ConstituencyElection constituencyElection:constituencyElections){
				if(year.equalsIgnoreCase(constituencyElection.getElection().getElectionYear()) && electionType.equalsIgnoreCase(constituencyElection.getElection().getElectionScope().getElectionType().getElectionTypeId().toString())){
					double validVotes = constituencyElection.getConstituencyElectionResult().getValidVotes();					
					totalValidVotes += validVotes; 
					nominations = constituencyElection.getNominations();
					for(Nomination nomination:nominations){
						String partyName = nomination.getParty().getLongName();
						double votesEarned = nomination.getCandidateResult().getVotesEarned().doubleValue();
						if(partiesList.containsKey(nomination.getParty().getLongName()))
							partiesList.put(partyName,new BigDecimal(partiesList.remove(partyName).doubleValue() + votesEarned).setScale (2,BigDecimal.ROUND_HALF_UP));
						else
							partiesList.put(partyName, new BigDecimal(votesEarned).setScale (2,BigDecimal.ROUND_HALF_UP));
					}
				}
			}
		}
		for(String partyName:partiesList.keySet()){
			partiesList.put(partyName, new BigDecimal((partiesList.get(partyName).doubleValue()*100)/totalValidVotes).setScale (2,BigDecimal.ROUND_HALF_UP));
		}
		return partiesList;
	}
	
	
	public int[] getPartyPosition(List<ConstituencyPositionDetailVO> partyLoosers){
		int[] partyPositions = {0,0,0,0};
		for(ConstituencyPositionDetailVO valueObject : partyLoosers){
			int rank = valueObject.getRank();
			if(rank>4){
				partyPositions[3]++;
			}else{
				partyPositions[rank-2]++;
			}
		}
		System.out.println("2nd: " + partyPositions[0] + "3rd:" + partyPositions[1] + "4th: " + partyPositions[2] + "nth: " + partyPositions[3]);
		return partyPositions;
	}
	
	public BigDecimal getTotalPercentageOfVotesWon(Set<Constituency> constituencies, String party, String year, String electionType){
		double totalValidVotes=0;
		double totalVotesEarnedByParty=0;
		for(Constituency constituency:constituencies){
			Set<ConstituencyElection> constituencyElections = constituency.getConstituencyElections();
			for(ConstituencyElection constituencyElection:constituencyElections){
				if(year.equalsIgnoreCase(constituencyElection.getElection().getElectionYear()) && electionType.equalsIgnoreCase(constituencyElection.getElection().getElectionScope().getElectionType().getElectionTypeId().toString())){
					double validVotes = constituencyElection.getConstituencyElectionResult().getValidVotes();					
					totalValidVotes += validVotes; 
					Set<Nomination> nominations = constituencyElection.getNominations();
					for(Nomination nomination:nominations){
						if(party.equalsIgnoreCase(nomination.getParty().getLongName())){
							totalVotesEarnedByParty+= nomination.getCandidateResult().getVotesEarned();
							break;
						}
					}
				}
			}
		}
		BigDecimal totalPercentageOfVotesWon;
		if(totalValidVotes==0){
			totalPercentageOfVotesWon= new BigDecimal(0).setScale (2,BigDecimal.ROUND_HALF_UP);
		}else{
			totalPercentageOfVotesWon= new BigDecimal((totalVotesEarnedByParty*100)/totalValidVotes).setScale (2,BigDecimal.ROUND_HALF_UP);
		}
		return totalPercentageOfVotesWon;
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
						presentValueObject.setPrevElectionPercentageOfVotesPolled(previousValueObject.getPercentageOfVotesPolled());//.setScale (2,BigDecimal.ROUND_HALF_UP));
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
						presentValueObject.setPrevElectionPercentage(previousValueObject.getPercentageOfVotes());//.setScale (2,BigDecimal.ROUND_HALF_UP));
						positionDetailVOsForMajorSwingWin.add(presentValueObject);
					}else if(swingDifference <0){
						swingDifference = -swingDifference;
						if(swingDifference >= negativeSwing){
							presentValueObject.setPrevElectionPercentage(previousValueObject.getPercentageOfVotes());//.setScale (2,BigDecimal.ROUND_HALF_UP));
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
	
	public Map<String, List<ConstituencyPositionDetailVO>> getPartyConstituenciesData(Set<Constituency> constituencies, 
			String party, String year, String electionType){
				
		Set<Nomination> nominations = null;		
		double firstHighestVotes=0;
		double secondHighestVotes=0;
		String oppositePartyWhichLost = null;
		String oppositePartyLongNameWhichLost = null;
		String oppositePartyCandidateWhoLost = null;
		String oppositePartyWhichWon = null;
		String oppositePartyLongNameWhichWon = null;
		String oppositePartyCandidateWhoWon = null;
		
		List<ConstituencyPositionDetailVO> positionDetailVOsForPartyWinners = new ArrayList<ConstituencyPositionDetailVO>();
		List<ConstituencyPositionDetailVO> positionDetailVOsForPartyLoosers = new ArrayList<ConstituencyPositionDetailVO>();
		Map<String, List<ConstituencyPositionDetailVO>> resultData = new HashMap<String, List<ConstituencyPositionDetailVO>>();
		for(Constituency constituency:constituencies){
			Set<ConstituencyElection> constituencyElections = constituency.getConstituencyElections();
			for(ConstituencyElection constituencyElection:constituencyElections){
				Election ele = constituencyElection.getElection();
				System.out.println("ElectionIDIDID.................."+ele.getElectionId());
				if(ele!=null){
					ElectionScope scope = ele.getElectionScope();
					if(scope!=null){
						ElectionType type = scope.getElectionType();
						if(type!=null){
							if(type.getElectionTypeId()==null)
								System.out.println("type.getElectionTypeId" + type.getElectionTypeId());
							if(year.equalsIgnoreCase(ele.getElectionYear()) 
								&& electionType.equalsIgnoreCase(type.getElectionTypeId().toString())){
								ConstituencyElectionResult constElectionResult = constituencyElection.getConstituencyElectionResult();
								if(constElectionResult != null){
									double validVotes = constElectionResult.getValidVotes();					
									double totalVotes = constElectionResult.getTotalVotes();
									double votingPercentage = 0;
									if(totalVotes!=0)
										votingPercentage = (validVotes*100)/totalVotes;
									
									nominations = constituencyElection.getNominations();
									Nomination partyNominationWhoWon = null;
									Nomination partyNominationWhoLost = null;
									for(Nomination nomination:nominations){
											if(party.equalsIgnoreCase(nomination.getParty().getLongName())){
												System.out.println("NominationID:" + nomination.getNominationId() + " PartyName:" +nomination.getParty().getLongName() 
														+ " Rank="+nomination.getCandidateResult().getRank()
														+ " Constituency="+constituency.getName()
														+ " Year="+ year
														);
												if(nomination.getCandidateResult().getRank() == 1){
													partyNominationWhoWon = nomination;
												}else{
													partyNominationWhoLost = nomination;
												}
											}else{
												if(nomination.getCandidateResult().getRank() == 1){
													firstHighestVotes = nomination.getCandidateResult().getVotesEarned();
													oppositePartyWhichWon = nomination.getParty().getLongName();
													oppositePartyLongNameWhichWon = nomination.getParty().getLongName();
													oppositePartyCandidateWhoWon = nomination.getCandidate().getFirstname();
												}
												else if(nomination.getCandidateResult().getRank() == 2){
													secondHighestVotes = nomination.getCandidateResult().getVotesEarned();
													oppositePartyWhichLost = nomination.getParty().getLongName();
													oppositePartyLongNameWhichLost = nomination.getParty().getLongName();
													oppositePartyCandidateWhoLost = nomination.getCandidate().getFirstname();
												}
											}
									}
							
									if(partyNominationWhoWon != null){
										double votesWonByPartyWinner = partyNominationWhoWon.getCandidateResult().getVotesEarned();
										double percentWonByPartyWinner = 0;
										double percentWonBySecondRank = 0;
										if(validVotes!=0)
										{
											 percentWonByPartyWinner = (votesWonByPartyWinner*100)/validVotes;
											 percentWonBySecondRank = (secondHighestVotes*100)/validVotes;											
										}
										
										
										double diffInPercent = percentWonByPartyWinner - percentWonBySecondRank; 
										ConstituencyPositionDetailVO positionDetail = extractPositionDetail(constituencyElection,
												partyNominationWhoWon, oppositePartyWhichLost, oppositePartyCandidateWhoLost, percentWonByPartyWinner,oppositePartyLongNameWhichLost);
										positionDetail.setPercentageDiffBetweenTop2(new BigDecimal(diffInPercent).setScale(2, BigDecimal.ROUND_HALF_UP));
										positionDetail.setPercentageOfVotesPolled(new BigDecimal(votingPercentage).setScale (2,BigDecimal.ROUND_HALF_UP));// voting percentage for the constituency
										positionDetail.setPresentElectionVotes(Math.round(totalVotes));
										positionDetailVOsForPartyWinners.add(positionDetail);
									}
									
									if(partyNominationWhoLost != null){
										double votesWonByPartyLoser = partyNominationWhoLost.getCandidateResult().getVotesEarned();
										double percentWonByPartyLoser = 0;
										double percentWonByFirstRank = 0;
										if(validVotes!=0){
										 percentWonByPartyLoser = (votesWonByPartyLoser*100)/validVotes;
										 percentWonByFirstRank = (firstHighestVotes*100)/validVotes;
										} 
										
										
										double diffInPercent = percentWonByFirstRank - percentWonByPartyLoser;
										ConstituencyPositionDetailVO positionDetail = extractPositionDetail(constituencyElection,
												partyNominationWhoLost, oppositePartyWhichWon, oppositePartyCandidateWhoWon, percentWonByPartyLoser,oppositePartyLongNameWhichWon);
										positionDetail.setPercentageDiffBetweenTop2(new BigDecimal(diffInPercent).setScale(2, BigDecimal.ROUND_HALF_UP));
										positionDetail.setPercentageOfVotesPolled(new BigDecimal(votingPercentage).setScale (2,BigDecimal.ROUND_HALF_UP));
										positionDetail.setPresentElectionVotes(totalVotes);
										positionDetail.setRank(partyNominationWhoLost.getCandidateResult().getRank().intValue());
										positionDetailVOsForPartyLoosers.add(positionDetail);
									}
								}
							}
						}
					}
				}
			}
		}
		resultData.put("PARTY_WINNER", positionDetailVOsForPartyWinners);
		resultData.put("PARTY_LOOSER", positionDetailVOsForPartyLoosers);
		return resultData;
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

	private ConstituencyPositionDetailVO extractPositionDetail(
			ConstituencyElection constituencyElection,
			Nomination partyNominationWhoWon, String oppositePartyWhichLost,
			String oppositePartyCandidateWhoLost, double percentWonByPartyWinner, String oppPartyLongName) {
		ConstituencyPositionDetailVO positionDetail= new ConstituencyPositionDetailVO();
		positionDetail.setCandidateName(partyNominationWhoWon.getCandidate().getFirstname());
		positionDetail.setConstiuencyName(constituencyElection.getConstituency().getName());
		positionDetail.setPercentageOfVotes(new BigDecimal(percentWonByPartyWinner).setScale(2, BigDecimal.ROUND_HALF_UP));
		positionDetail.setOppositeParty(oppositePartyWhichLost);
		positionDetail.setOppositePartyCandidate(oppositePartyCandidateWhoLost);
		positionDetail.setOppositePartyLongName(oppPartyLongName);
		System.out.println("Extract Details for : "+partyNominationWhoWon.getCandidate().getFirstname());
		return positionDetail;
	}

	public String getPreviousYear(List<String> years, String presentYear){
		Collections.sort(years);
		String prevYear = new String();
		for(int index = years.size()-1 ; index > 0; index--){
			if((years.get(index)).equals(presentYear)){
				prevYear = years.get(index-1);
				break;
			}
		}
		return ((prevYear == null)? "0" : prevYear);
	}
	
	public List<String> getElectionYears(String electionTypeID, String stateID, String countryID){
		Long typeID = new Long(electionTypeID);
		Long state_Id = new Long(stateID);
		Long country_ID = new Long(countryID);
		//List<Election> elections = electionDAO.getAll();
		List<Election> elections = electionDAO.findByPropertyTypeId_CountryId_StateId(typeID, state_Id, country_ID);
		System.out.println("Elections:" + elections.size());
		List<String> electionYears = new ArrayList<String>();
		for(Election election: elections){
			ElectionScope scope = election.getElectionScope();
			System.out.print("ElectionScopeId:" + scope.getElectionScopeId());
			System.out.print(" countryID:" + election.getElectionScope().getCountry().getCountryId());
			System.out.print(" StateID:" + election.getElectionScope().getState().getStateId());
			System.out.print(" Year:" + election.getElectionYear());
			System.out.println("__");
			if(scope.getCountry().getCountryId().equals(country_ID)){
				if(scope.getState().getStateId().equals(state_Id)){
					ElectionType type = scope.getElectionType();
					if(typeID.equals(type.getElectionTypeId())){
						electionYears.add(election.getElectionYear()); 
					}
				}
			}
			 
		}
		return electionYears;
	}
	
}
