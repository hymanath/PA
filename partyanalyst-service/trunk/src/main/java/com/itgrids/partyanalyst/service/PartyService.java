package com.itgrids.partyanalyst.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionResultDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dto.ConstituencyPositionDetailVO;
import com.itgrids.partyanalyst.dto.PartyPerformanceReportVO;
import com.itgrids.partyanalyst.dto.PositionType;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.State;

public class PartyService implements IPartyService {

	ICountryDAO countryDAO;
	IStateDAO stateDAO;
	IDistrictDAO districtDAO;
	IElectionDAO electionDAO;
	IElectionTypeDAO electionTypeDAO;
	IElectionScopeDAO electionScopeDAO;
	IPartyDAO partyDAO;
	ICandidateDAO candidateDAO;
	INominationDAO nominationDAO;
	IConstituencyDAO constituencyDAO;
	IConstituencyElectionDAO constituencyElectionDAO;
	IConstituencyElectionResultDAO constituencyElectionResultDAO;
	
	
	public PartyPerformanceReportVO getPartyPerformanceReport(String state,
			String party, String year, String electionType, int noOfPositionDistribution,
			BigDecimal majorBand, BigDecimal minorBand) {
		
		int totalSeatsContested=0;
		int totalSeatsWon=0;
		
		
		BigDecimal totalPercentageOfVotesWon = null;
		double totalValidVotes=0;
		double totalVotesEarnedByParty=0;
		
		State stateObj = new State();
		stateObj.setStateName(state);
		List<Constituency> constituencies = constituencyDAO.findByProperty("state", stateObj);
		
		Set<Nomination> nominations = null;
		List<Nomination> nominationsWhichWonForTheParty = new ArrayList<Nomination>();
		
		int positionsWonMajorBand=0;
		int positionsWonMinorBand=0;
		Map<PositionType, List<ConstituencyPositionDetailVO>> constituencyPositionDetails = new HashMap<PositionType, List<ConstituencyPositionDetailVO>>();
		List<ConstituencyPositionDetailVO> positionDetailVOsForWonMajorBand = new ArrayList<ConstituencyPositionDetailVO>();
		List<ConstituencyPositionDetailVO> positionDetailVOsForWonMinorBand = new ArrayList<ConstituencyPositionDetailVO>();
		
		for(Constituency constituency:constituencies){
			Set<ConstituencyElection> constituencyElections = constituency.getConstituencyElections();
			for(ConstituencyElection constituencyElection:constituencyElections){
				if(year.equalsIgnoreCase(constituencyElection.getElection().getElectionYear()) && electionType.equalsIgnoreCase(constituencyElection.getElection().getElectionScope().getElectionType().getElectionType())){
					double validVotes = constituencyElection.getConstituencyElectionResult().getValidVotes();					
					totalValidVotes += validVotes; 
					nominations = constituencyElection.getNominations();
					
					Nomination partyNominationWhichWon = null;
					double secondHighestVotes=0;
					String oppositeParty = null;
					String oppositePartyCandidate = null;
					for(Nomination nomination:nominations){
						partyNominationWhichWon = null;
						if(party.equalsIgnoreCase(nomination.getParty().getLongName())){
							totalSeatsContested++;							
							totalVotesEarnedByParty+= nomination.getCandidateResult().getVotesEarned();						
							if(nomination.getCandidateResult().getRank() == 1){
								nominationsWhichWonForTheParty.add(nomination);
								partyNominationWhichWon = nomination;
								totalSeatsWon++;
							}else{
								;
							}
						}else{
							if(nomination.getCandidateResult().getRank() == 2){
								secondHighestVotes = nomination.getCandidateResult().getVotesEarned();
								oppositeParty = nomination.getParty().getLongName();
								oppositePartyCandidate = nomination.getCandidate().getFirstname();
							}
						}
					}
					if(partyNominationWhichWon != null){
						double votesWonByPartyWinner = partyNominationWhichWon.getCandidateResult().getVotesEarned();
						double percentWonByPartyWinner = (votesWonByPartyWinner*100)/validVotes;
						double percentWonBySecondRank = (secondHighestVotes*100)/validVotes;
						double diffInPercent = percentWonByPartyWinner - percentWonBySecondRank; 
						if(diffInPercent >=  majorBand.doubleValue()){
							positionsWonMajorBand++;							
							ConstituencyPositionDetailVO positionDetail= new ConstituencyPositionDetailVO();
							positionDetail.setCandidateName(partyNominationWhichWon.getCandidate().getFirstname());
							positionDetail.setConstiuencyName(constituencyElection.getConstituency().getName());
							positionDetail.setPercentageOfVotes(BigDecimal.valueOf(percentWonByPartyWinner));
							positionDetail.setOppositeParty(oppositeParty);
							positionDetail.setOppositePartyCandidate(oppositePartyCandidate);
							positionDetailVOsForWonMajorBand.add(positionDetail);
						}
						if(diffInPercent <=  minorBand.doubleValue()){
							positionsWonMinorBand++;							
							ConstituencyPositionDetailVO positionDetail= new ConstituencyPositionDetailVO();
							positionDetail.setCandidateName(partyNominationWhichWon.getCandidate().getFirstname());
							positionDetail.setConstiuencyName(constituencyElection.getConstituency().getName());
							positionDetail.setPercentageOfVotes(BigDecimal.valueOf(percentWonByPartyWinner));
							positionDetail.setOppositeParty(oppositeParty);
							positionDetail.setOppositePartyCandidate(oppositePartyCandidate);
							positionDetailVOsForWonMinorBand.add(positionDetail);
						}
					}
					
				}
			}
		}
		int totalSeatsLost = totalSeatsContested - totalSeatsWon;
		
		totalPercentageOfVotesWon = BigDecimal.valueOf((totalVotesEarnedByParty*100)/totalValidVotes);
		
		
		
		
		constituencyPositionDetails.put(PositionType.POSITIONS_WON_MAJOR_BAND, positionDetailVOsForWonMajorBand);
		constituencyPositionDetails.put(PositionType.POSITIONS_WON_MINOR_BAND, positionDetailVOsForWonMinorBand);


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
		// TODO Auto-generated method stub
		return null;
	}

}
