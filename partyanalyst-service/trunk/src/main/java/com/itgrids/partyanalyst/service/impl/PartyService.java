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

import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dto.ConstituencyPositionDetailVO;
import com.itgrids.partyanalyst.dto.ConstituencyPositionsVO;
import com.itgrids.partyanalyst.dto.PartyPerformanceReportVO;
import com.itgrids.partyanalyst.dto.PartyPositionDisplayVO;
import com.itgrids.partyanalyst.dto.PartyPositionsVO;
import com.itgrids.partyanalyst.dto.PartyPostionInfoVO;
import com.itgrids.partyanalyst.dto.PositionType;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyElectionDistrictResult;
import com.itgrids.partyanalyst.model.PartyElectionResult;
import com.itgrids.partyanalyst.model.PartyElectionStateResult;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.service.IPartyService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.PartyPostionInfoVOComparator;
import com.itgrids.partyanalyst.utils.ValueComparator;

public class PartyService implements IPartyService {

	private IElectionDAO electionDAO;
	private IElectionTypeDAO electionTypeDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IPartyDAO partyDAO;
	private INominationDAO nominationDAO;
	private IStaticDataService staticDataService;
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
	
	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}

	public IElectionTypeDAO getElectionTypeDAO() {
		return electionTypeDAO;
	}

	public void setElectionTypeDAO(IElectionTypeDAO electionTypeDAO) {
		this.electionTypeDAO = electionTypeDAO;
	}

	public PartyPerformanceReportVO getPartyPerformanceReport(String state, String district,String party, String year,
			String elecType, String countryID, int noOfPositionDistribution, BigDecimal majorBand,
			BigDecimal minorBand, Boolean includeAllianceParties,String reportLevel) {	
		log.debug("getPartyPerformanceReport start.....");
		
		Long stateId = new Long(state);
		Long districtId = (district != null) ? new Long(district): 0;
		Long partyId = new Long(party);
		Long electionType = new Long(elecType);
		String prevYear = null;
		Boolean reportSuccess = false;
		
		ElectionType electnType = electionTypeDAO.get(electionType);
		
		if(electnType.getElectionType().equals(IConstants.PARLIAMENT_ELECTION_TYPE))
		    prevYear = electionDAO.findPreviousParliamentElectionYear(year, electionType, new Long(countryID));
		else 
			prevYear = electionDAO.findPreviousElectionYear(year, electionType, stateId, new Long(countryID));
			
		Long countryId = new Long(countryID);
				
		PartyPerformanceReportVO presentYearPartyPerformanceReportVO = getElectionResults(stateId, districtId,countryId, year, electionType, partyId, includeAllianceParties, false,reportLevel);
		PartyPerformanceReportVO previousYearPartyPerformanceReportVO = getElectionResults(stateId, districtId,countryId, prevYear, electionType, partyId, includeAllianceParties, true,reportLevel);
				
		setConstituencyPositionsForDetailedReport(presentYearPartyPerformanceReportVO, previousYearPartyPerformanceReportVO, majorBand, minorBand);
		
		BigDecimal presentElectionTotalPercentageOfVotesWon = presentYearPartyPerformanceReportVO.getTotalPercentageOfVotesWon();
		BigDecimal prevElectionTotalPercentageOfVotesWon = previousYearPartyPerformanceReportVO.getTotalPercentageOfVotesWon();
		BigDecimal diffOfTotalPercentageWinWithPrevElection = new BigDecimal(
											presentElectionTotalPercentageOfVotesWon.doubleValue() - 
											prevElectionTotalPercentageOfVotesWon.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
		boolean positiveVotesFlowFlag = true;
		if(diffOfTotalPercentageWinWithPrevElection.doubleValue()<0)
			positiveVotesFlowFlag = false;
		Map<String, String> partyVotesFlown = new LinkedHashMap<String, String>();
		
		if(!new Double(presentElectionTotalPercentageOfVotesWon.doubleValue()).equals(new Double(0)) && !new Double(diffOfTotalPercentageWinWithPrevElection.doubleValue()).equals(new Double(0))){
		   partyVotesFlown = partyVotesFlow(presentYearPartyPerformanceReportVO.getVotesFlown()
				, previousYearPartyPerformanceReportVO.getVotesFlown(),positiveVotesFlowFlag,presentYearPartyPerformanceReportVO.getAllianceParties(),previousYearPartyPerformanceReportVO.getAllianceParties());
		  reportSuccess = true;
		}
		
		presentYearPartyPerformanceReportVO.setPrevYear(previousYearPartyPerformanceReportVO.getYear());
		presentYearPartyPerformanceReportVO.setPrevYearTotalSeatsWon(previousYearPartyPerformanceReportVO.getTotalSeatsWon());
		presentYearPartyPerformanceReportVO.setPrevYeartotalPercentageOfVotesWon(previousYearPartyPerformanceReportVO.getTotalPercentageOfVotesWon());
		presentYearPartyPerformanceReportVO.setDiffSeatsWon(presentYearPartyPerformanceReportVO.getTotalSeatsWon() 
				- previousYearPartyPerformanceReportVO.getTotalSeatsWon());
		presentYearPartyPerformanceReportVO.setTotalPercentageOfVotesWonPreviousElection(prevElectionTotalPercentageOfVotesWon);
		presentYearPartyPerformanceReportVO.setDiffOfTotalPercentageWinWithPrevElection(diffOfTotalPercentageWinWithPrevElection);
		presentYearPartyPerformanceReportVO.setToPartySwing(partyVotesFlown);
		presentYearPartyPerformanceReportVO.setReportSuccessOrFailure(reportSuccess); //report success or failure status
		
		if(previousYearPartyPerformanceReportVO.getAllianceParties() != null && previousYearPartyPerformanceReportVO.getAllianceParties().size() > 0)
			presentYearPartyPerformanceReportVO.setPreviousYearAllianceParties(previousYearPartyPerformanceReportVO.getAllianceParties());
		else
			presentYearPartyPerformanceReportVO.setPreviousYearAllianceParties(null);
	
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

	@SuppressWarnings("unchecked")
	public PartyPerformanceReportVO getElectionResults(Long stateId,
			Long districtId,Long countryId, String year, Long electionType, Long partyId,
			Boolean includeAllianceParties, boolean b,String reportLevel){
		
		log.debug("getElectionData start.....for year.." + year);
		log.debug(" Party Performance Report For " +  year );
		PartyPerformanceReportVO partyPerformanceReportVO = new PartyPerformanceReportVO();
		Election election = null;
		ElectionType electionTyp = null;
		String elecType = null;
		List<Election> elections = null;
		
		Party selectedParty = partyDAO.get(partyId); 
		State stateVO = null;
		if(stateId != null && !stateId.equals(0L))
		stateVO = stateDAO.get(stateId);
		
		//get ElectionType Model Object From ElectionTypeId
		if(electionType != null && !electionType.equals(0l))
		   electionTyp = electionTypeDAO.get(electionType);
		
		log.error(" Election Type :" + electionTyp.getElectionType());
		//for Parliament ElectionType stateId is null
		if(electionTyp.getElectionType().equals(IConstants.PARLIAMENT_ELECTION_TYPE))
			elections = electionDAO.findByElectionTypeYearAndCountryForParliament(electionType,year,countryId);
		else
		    elections = electionDAO.findByElectionTypeYearAndState(electionType,year,stateId,countryId);
		log.error(" Election Results List :" + elections.size());
		
		if(elections != null && elections.size() > 0){
		election = elections.get(0);
		elecType = election.getElectionScope().getElectionType().getElectionType();
		partyPerformanceReportVO.setElectionId(election.getElectionId());
		}
		District districtVO = null;
		List electionResultList = null;
		List<Party> allianceParties = new ArrayList<Party>();
		
		List<PartyPositionsVO> partyElectionResultsList = new ArrayList<PartyPositionsVO>();
		PartyElectionResult partyElectionResultforSelectedParty = null;
		PartyElectionResult partyElectionResultforAllianceParty = null;
		PartyElectionDistrictResult partyElectionDistrictResultforSelectedParty = null;
		PartyElectionDistrictResult partyElectionDistrictResultforAllianceParty = null;
		PartyElectionStateResult partyElectionStateResultForSelectedParty = null;
		PartyElectionStateResult partyElectionStateResultForAllianceParty = null;
		PartyPositionsVO partyPositionVOforSelectedParty = null;
		PartyPositionsVO partyPositionVOforAllianceParty = null;
		// Process Selected Election Year Data
		
		partyPerformanceReportVO.setParty(selectedParty.getShortName());
		if(stateVO != null)
		partyPerformanceReportVO.setState(stateVO.getStateName());
		partyPerformanceReportVO.setYear(year);
		
		if("3".equals(reportLevel)){
			//constElectionResultList = constituencyElectionResultDAO.findByElectionTypeAndYearAndCountry(electionType, year, countryId);
			electionResultList = nominationDAO.findElectionResultsForAllCostituenciesByElectionTypeYearAndCountryId(new Long(electionType), year, countryId);
			log.debug("Inside countryLevel report Service...");
		}
		else if(!districtId.equals(new Long(0))){
			districtVO = districtDAO.get(districtId);
			partyPerformanceReportVO.setDistrict(districtVO.getDistrictName());
			//constElectionResultList = constituencyElectionResultDAO.findByElectionTypeId_Year_StateId_DistrictId(new Long(electionType), year, stateId, districtId);
			electionResultList = nominationDAO.findElectionResultsForAllCostituenciesByElectionTypeYearAndDistrictId(new Long(electionType), year, districtId);
			log.debug("Inside districtLevel report Service...");
		} else if("1".equals(reportLevel)){
			//constElectionResultList = constituencyElectionResultDAO.findByElectionTypeId_Year_StateId(new Long(electionType), year, stateId);
			electionResultList = nominationDAO.findElectionResultsForAllCostituenciesByElectionTypeYearAndStateId(new Long(electionType), year, stateId);
			log.debug("Inside stateLevel report Service...");
		}
	    if(election != null && elecType != null && elecType.equals(IConstants.PARLIAMENT_ELECTION_TYPE) && "1".equals(reportLevel)){
	    	log.debug(" Inside State Level For Parliament .. ");
	    	partyElectionStateResultForSelectedParty = staticDataService.getPartyElectionResultsForAPartyStateLevelInParliamentElection(election.getElectionId(), selectedParty.getPartyId(),stateVO.getStateId());
	    	if(partyElectionStateResultForSelectedParty == null)
	    	partyElectionStateResultForSelectedParty = staticDataService.savePartyElectionResultForAPartyForAParliamentElectionStateLevel(election.getElectionId(), selectedParty.getPartyId(),stateVO.getStateId());
	    	if(partyElectionStateResultForSelectedParty != null){
	    		partyPositionVOforSelectedParty = getPositionDetailsForAPartyForState(partyElectionStateResultForSelectedParty,selectedParty.getShortName());
				partyElectionResultsList.add(partyPositionVOforSelectedParty);
	    	}
	    }
	    else if(election != null && !districtId.equals(new Long(0))){
			log.debug("District Id(selected party)::" + districtId);
			partyElectionDistrictResultforSelectedParty = staticDataService.getPartyElectionResultsForAPartyDistrictLevel(election.getElectionId(), selectedParty.getPartyId(), districtId);
			if(partyElectionDistrictResultforSelectedParty == null)
			partyElectionDistrictResultforSelectedParty = staticDataService.savePartyElectionResultForAPartyForAElectionDistrictLevel(election.getElectionId(), selectedParty.getPartyId(),districtId);
			if(partyElectionDistrictResultforSelectedParty != null){
			partyPositionVOforSelectedParty = getPositionDetailsForAPartyForDistrict(partyElectionDistrictResultforSelectedParty,selectedParty.getShortName());
			partyElectionResultsList.add(partyPositionVOforSelectedParty);
			}
		}
		else if(election != null && districtId.equals(new Long(0))){
			partyElectionResultforSelectedParty = staticDataService.getPartyElectionResultsForAParty(election.getElectionId(), selectedParty.getPartyId());
			if(partyElectionResultforSelectedParty == null)
			partyElectionResultforSelectedParty = staticDataService.savePartyElectionResultForAPartyForAElection(election.getElectionId(), selectedParty.getPartyId());
			 if(partyElectionResultforSelectedParty != null){
			 partyPositionVOforSelectedParty = getPositionDetailsForAParty(partyElectionResultforSelectedParty,selectedParty.getShortName());
			 partyElectionResultsList.add(partyPositionVOforSelectedParty);
			 }
		}
		if(includeAllianceParties){
			allianceParties = staticDataService.getAllianceParties(year, electionType, selectedParty.getPartyId(),stateId);
			if(allianceParties != null){
			allianceParties.remove(selectedParty);
			partyPerformanceReportVO.setAllianceParties(allianceParties);
			partyPerformanceReportVO.setParty(selectedParty.getShortName().concat(" & Alliance Parties"));
			}
			
			if(election != null && elecType != null && elecType.equals(IConstants.PARLIAMENT_ELECTION_TYPE) && "1".equals(reportLevel) && allianceParties != null){
				for(Party alliancParty:allianceParties){
				partyElectionStateResultForAllianceParty = staticDataService.getPartyElectionResultsForAPartyStateLevelInParliamentElection(election.getElectionId(), alliancParty.getPartyId(),stateVO.getStateId());
				if(partyElectionStateResultForAllianceParty == null)
				partyElectionStateResultForAllianceParty = staticDataService.savePartyElectionResultForAPartyForAParliamentElectionStateLevel(election.getElectionId(), alliancParty.getPartyId(),stateVO.getStateId());
				if(partyElectionStateResultForAllianceParty != null){
					partyPositionVOforAllianceParty = getPositionDetailsForAPartyForState(partyElectionStateResultForAllianceParty,alliancParty.getShortName());
					partyElectionResultsList.add(partyPositionVOforAllianceParty);
				}
				}
			}
			else if(election != null && !districtId.equals(new Long(0)) && allianceParties != null){
				log.debug("District Id(Alliance Party)::" + districtId);
				for(Party alliancParty:allianceParties){
				 partyElectionDistrictResultforAllianceParty = staticDataService.getPartyElectionResultsForAPartyDistrictLevel(election.getElectionId(), alliancParty.getPartyId(), districtId);
				 if(partyElectionDistrictResultforAllianceParty == null)
				 partyElectionDistrictResultforAllianceParty = staticDataService.savePartyElectionResultForAPartyForAElectionDistrictLevel(election.getElectionId(), alliancParty.getPartyId(),districtId);
				  if(partyElectionDistrictResultforAllianceParty != null){
				  partyPositionVOforAllianceParty = getPositionDetailsForAPartyForDistrict(partyElectionDistrictResultforAllianceParty,alliancParty.getShortName());
				  partyElectionResultsList.add(partyPositionVOforAllianceParty);
				  }
				}
			}
			else if(election != null && districtId.equals(new Long(0)) && allianceParties != null){
			   for(Party alliancParty:allianceParties){
				partyElectionResultforAllianceParty = staticDataService.getPartyElectionResultsForAParty(election.getElectionId(), alliancParty.getPartyId());
				if(partyElectionResultforAllianceParty == null)
				partyElectionResultforAllianceParty = staticDataService.savePartyElectionResultForAPartyForAElection(election.getElectionId(), alliancParty.getPartyId());
				 if(partyElectionResultforAllianceParty != null){
				 partyPositionVOforAllianceParty = getPositionDetailsForAParty(partyElectionResultforAllianceParty,alliancParty.getShortName());
				 partyElectionResultsList.add(partyPositionVOforAllianceParty);
				 }
			   }
			}
		}  
		
		partyPerformanceReportVO.setPartyPositionsVO(partyElectionResultsList);
		getPartyConstituenciesData(electionResultList, selectedParty, includeAllianceParties, partyPerformanceReportVO);
        
		log.debug("getElectionData Ended for year..." + year);
		return partyPerformanceReportVO;
	}

	public PartyPositionsVO getPositionDetailsForAParty(PartyElectionResult partyElectionResult,String partyName){
		PartyPositionsVO partyPositionVO = new PartyPositionsVO();
		partyPositionVO.setPartyId(partyElectionResult.getParty().getPartyId());
		partyPositionVO.setPartyName(partyName);
		partyPositionVO.setTotalSeatsWon(new Long(partyElectionResult.getTotalSeatsWon()));
		partyPositionVO.setSecondPosWon(new Long(partyElectionResult.getSecondPosWon()));
		partyPositionVO.setThirdPosWon(new Long(partyElectionResult.getThirdPosWon()));
		partyPositionVO.setFourthPosWon(new Long(partyElectionResult.getFourthPosWon()));
		partyPositionVO.setNthPosWon(new Long(partyElectionResult.getNthPosWon()));
		partyPositionVO.setTotalConstiParticipated(new Long(partyElectionResult.getTotalConstiParticipated()));
		partyPositionVO.setCompleteVotesPercent(partyElectionResult.getCompleteVotesPercent());
		partyPositionVO.setVotesPercentage(partyElectionResult.getVotesPercentage());
		
	return partyPositionVO;
	}
	
	public PartyPositionsVO getPositionDetailsForAPartyForDistrict(PartyElectionDistrictResult partyElectionDistrictResult,String partyName){
		PartyPositionsVO partyPositionVO = new PartyPositionsVO();
		partyPositionVO.setPartyId(partyElectionDistrictResult.getParty().getPartyId());
		partyPositionVO.setPartyName(partyName);
		partyPositionVO.setTotalSeatsWon(new Long(partyElectionDistrictResult.getTotalSeatsWon()));
		partyPositionVO.setSecondPosWon(new Long(partyElectionDistrictResult.getSecondPosWon()));
		partyPositionVO.setThirdPosWon(new Long(partyElectionDistrictResult.getThirdPosWon()));
		partyPositionVO.setFourthPosWon(new Long(partyElectionDistrictResult.getFourthPosWon()));
		partyPositionVO.setNthPosWon(new Long(partyElectionDistrictResult.getNthPosWon()));
		partyPositionVO.setTotalConstiParticipated(new Long(partyElectionDistrictResult.getTotalConstiParticipated()));
		partyPositionVO.setCompleteVotesPercent(partyElectionDistrictResult.getCompleteVotesPercent());
		partyPositionVO.setVotesPercentage(partyElectionDistrictResult.getVotesPercentage());
		
	return partyPositionVO;
	}
	
	public PartyPositionsVO getPositionDetailsForAPartyForState(PartyElectionStateResult partyElectionStateResult,String partyName){
		PartyPositionsVO partyPositionVO = new PartyPositionsVO();
		partyPositionVO.setPartyId(partyElectionStateResult.getParty().getPartyId());
		partyPositionVO.setPartyName(partyName);
		partyPositionVO.setTotalSeatsWon(new Long(partyElectionStateResult.getTotalSeatsWon()));
		partyPositionVO.setSecondPosWon(new Long(partyElectionStateResult.getSecondPosWon()));
		partyPositionVO.setThirdPosWon(new Long(partyElectionStateResult.getThirdPosWon()));
		partyPositionVO.setFourthPosWon(new Long(partyElectionStateResult.getFourthPosWon()));
		partyPositionVO.setNthPosWon(new Long(partyElectionStateResult.getNthPosWon()));
		partyPositionVO.setTotalConstiParticipated(new Long(partyElectionStateResult.getTotalConstiParticipated()));
		partyPositionVO.setCompleteVotesPercent(partyElectionStateResult.getCompleteVotesPercent());
		partyPositionVO.setVotesPercentage(partyElectionStateResult.getVotesPercentage());
		
	return partyPositionVO;
	}
	
	public void getPartyConstituenciesData(
			List electionResultList, 
			Party selectedParty, Boolean includeAllianceParties, 
			PartyPerformanceReportVO partyPerformanceReportVO){
		
		Long startHeap = Runtime.getRuntime().freeMemory();
		
		Map<Long, List<Object[]>> constituenciesInfoById = new HashMap<Long, List<Object[]>>();
		Long constituencyId = 0l;
		String constituencyName = "";
		Long partyId = null;
		Long rank = null;
		List<Object[]> constituencyResult = null;
		List<Long> alliancePartiesIds = new ArrayList<Long>();
		
		//Group All Nominations By Constituency
		for(Object[] values:(List<Object[]>)electionResultList){
			constituencyId = (Long)values[0];
			constituencyResult = constituenciesInfoById.get(constituencyId);
			if(constituencyResult == null)
				constituencyResult = new ArrayList<Object[]>();
				constituencyResult.add(values);
			constituenciesInfoById.put(constituencyId, constituencyResult);
		}
		
		List<ConstituencyPositionDetailVO> positionDetailVOsForPartyWinners = new ArrayList<ConstituencyPositionDetailVO>();
		List<ConstituencyPositionDetailVO> positionDetailVOsForPartyLoosers = new ArrayList<ConstituencyPositionDetailVO>();
		List<ConstituencyPositionDetailVO> positionDetailVOForAllianceRebelParties = new ArrayList<ConstituencyPositionDetailVO>();
		
		List<Party> allianceParties = partyPerformanceReportVO.getAllianceParties();
		if(allianceParties != null)
		for(Party party:allianceParties)
			alliancePartiesIds.add(party.getPartyId());
		
		Map<String, BigDecimal> votesFlow = new HashMap<String, BigDecimal>();
		SortedMap<String, Integer> positionDistribution = new TreeMap<String, Integer>();
		positionDistribution.put("1",0);
		positionDistribution.put("2",0);
		positionDistribution.put("3",0);
		positionDistribution.put("4",0);
		positionDistribution.put("5",0);
		double totalValidVotes = 0;
		double totalVotesEarnedByParty = 0;
		
		Object[] partyNominationWhoWon = null;
		Object[] partyNominationWhoLost = null;
		Object[] oppositePartyWhichWon = null;
		Object[] oppositePartyWhichLost = null;
		
		Object[] rebelAllianceParty = null;
		Object[] selectedPartyNomination = null;
		Object[] alliancePartyNomination = null;
		
		for(Map.Entry<Long, List<Object[]>> entry:constituenciesInfoById.entrySet()){
			oppositePartyWhichWon = null;
			oppositePartyWhichLost = null;
			partyNominationWhoWon = null;
			partyNominationWhoLost = null;
			
			constituencyResult = entry.getValue();//List Of nominations with Results For A Constituency In An Election 
			constituencyId = entry.getKey();
			constituencyName = (constituencyResult.get(0)[4]).toString();
			
			double validVotes = (Double)constituencyResult.get(0)[11];					
			double totalVotes = (Double)constituencyResult.get(0)[10];
			totalValidVotes += validVotes;
			
			rebelAllianceParty = (includeAllianceParties) ? getNominatedPartiesAsRebels(constituencyResult, alliancePartiesIds, selectedParty): null;
			selectedPartyNomination = getSelectedPartyNomination(constituencyResult,selectedParty);
			alliancePartyNomination = null;
	        	        			
		    for(Object[] nomination : constituencyResult){
				partyId = (Long)nomination[1];
				rank = (Long)nomination[9];
	         
				boolean isSelectedParty = (selectedParty.getPartyId().equals(partyId))? true : false;
				boolean isAllianceParty = (includeAllianceParties && allianceParties != null && alliancePartiesIds.contains(partyId))? true : false;
				boolean isRebelAllianceParty = (rebelAllianceParty != null && partyId.equals(rebelAllianceParty[1]))? true : false;
				double votesEarned = (Double)nomination[8];
                Boolean flag = null;
                String rankSelected = null;
				if(isSelectedParty || (isAllianceParty && !isRebelAllianceParty)){
					
					if(isSelectedParty && flag == null){
					// Party Positions
					String position = (rank > 4)? "5" : rank.toString();
					int positionCount = positionDistribution.get(position);
					positionDistribution.put(position, ++positionCount );
					flag = true;
					}
					if(isAllianceParty && flag == null){
						// Party Positions
						String position = (rank > 4)? "5" : rank.toString();
						int positionCount = positionDistribution.get(position);
						positionDistribution.put(position, ++positionCount );
						rankSelected = position;
						flag = false;
					}
					if(isSelectedParty && flag == false){
						// Party Positions
						String position = (rank > 4)? "5" : rank.toString();
						int rankSelectedCount = positionDistribution.get(rankSelected);
						positionDistribution.put(rankSelected, --rankSelectedCount);
						int positionCount = positionDistribution.get(position);
						positionDistribution.put(position, ++positionCount );
						flag = true;
					}
											
					log.debug("Selected Parties...NominationID:" + nomination[3] + " PartyName:" +nomination[5]);
					if(rank.intValue() == 1) 
						partyNominationWhoWon = nomination;
					else 
						partyNominationWhoLost = nomination;
					
					totalVotesEarnedByParty+= votesEarned;

				} else if(!isAllianceParty || isRebelAllianceParty) {
					
					if(rank.intValue() == 1) 
						oppositePartyWhichWon = nomination;
					else if(rank.intValue() == 2) 
						oppositePartyWhichLost = nomination;
					
					if(isRebelAllianceParty) {
						try{
						//Getting the alliance party nomination if the selected party has not participated w r to rebel party
						alliancePartyNomination = getAlliancePartyNomination(constituencyResult, nomination, alliancePartiesIds);
						
						ConstituencyPositionDetailVO  positionDetail = new ConstituencyPositionDetailVO();
						positionDetail.setCandidateName(nomination[7].toString());
						positionDetail.setConstiuencyName(constituencyName);
						positionDetail.setConstituencyId(constituencyId);
						double percentageOfVotesEarned = (((Double)nomination[8]) * 100)/validVotes;
						positionDetail.setPercentageOfVotes(new BigDecimal(percentageOfVotesEarned).setScale(2, BigDecimal.ROUND_HALF_UP));
						positionDetail.setPresentElectionVotes(totalVotes);
						positionDetail.setRank(rank.intValue());
						// Using OppositeParty field as Rebel Candidate Party to display in front end
						positionDetail.setPartyName(nomination[5].toString());
						//Set details for selected party along with rebel party details
						if(selectedPartyNomination != null){
							positionDetail.setOppositeParty(selectedPartyNomination[5].toString());
							positionDetail.setOppositePartyCandidate(selectedPartyNomination[7].toString());
							double selectedPartyPercentageOfVotesEarned = (((Double)selectedPartyNomination[8]) * 100)/validVotes;
							positionDetail.setOppositePartyPercentageOfVotes(new BigDecimal(selectedPartyPercentageOfVotesEarned).setScale(2, BigDecimal.ROUND_HALF_UP));
							positionDetail.setOppositePartyRank(Integer.parseInt(selectedPartyNomination[9].toString()));
						}
						else if(alliancePartyNomination != null){
							positionDetail.setOppositeParty(alliancePartyNomination[5].toString());
							positionDetail.setOppositePartyCandidate(alliancePartyNomination[7].toString());
							double selectedPartyPercentageOfVotesEarned = ((Double)alliancePartyNomination[8] * 100)/validVotes;
							positionDetail.setOppositePartyPercentageOfVotes(new BigDecimal(selectedPartyPercentageOfVotesEarned).setScale(2, BigDecimal.ROUND_HALF_UP));
							positionDetail.setOppositePartyRank(Integer.parseInt(alliancePartyNomination[9].toString()));
						}
						positionDetailVOForAllianceRebelParties.add(positionDetail);	
						}
						catch(Exception ex){
							log.debug("Exception Raised ::" + ex);
						}
					}
					
				}
				if(!isSelectedParty && !isRebelAllianceParty){
				if(votesFlow.containsKey(nomination[5].toString()))
					votesEarned += votesFlow.get(nomination[5].toString()).doubleValue();
					votesFlow.put(nomination[5].toString(), new BigDecimal(votesEarned).setScale (2,BigDecimal.ROUND_HALF_UP));
				}
			}
		    
			if(partyNominationWhoWon != null) {
				positionDetailVOsForPartyWinners.add(
						getConstituencyPositionDetails(constituencyId, validVotes, totalVotes, constituencyName, 
								partyNominationWhoWon, oppositePartyWhichLost));
			}

			if(partyNominationWhoLost != null){
				positionDetailVOsForPartyLoosers.add(
						getConstituencyPositionDetails(constituencyId, validVotes, totalVotes, constituencyName, 
								partyNominationWhoLost, oppositePartyWhichWon));
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
		partyPerformanceReportVO.setTotalSeatsLost(positionDetailVOsForPartyLoosers.size());
		partyPerformanceReportVO.setTotalSeatsContested(positionDetailVOsForPartyWinners.size() + positionDetailVOsForPartyLoosers.size());
		
		log.debug("positionDistribution.get(1)::"+positionDistribution.get("1"));
		log.debug("positionDistribution.get(2)::"+positionDistribution.get("2"));
		log.debug("positionDistribution.get(3)::"+positionDistribution.get("3"));
		log.debug("positionDistribution.get(4)::"+positionDistribution.get("4"));
		log.debug("positionDistribution.get(5)::"+positionDistribution.get("5"));
		log.debug("getPartyConstituenciesData....end");
		
		Long endHeap = Runtime.getRuntime().freeMemory();
		
		System.out.println("Memory Consumed By Party Service::::::::::::::::"+(startHeap - endHeap));
		
	}
	
	public Object[] getAlliancePartyNomination(List<Object[]> nominations, Object[] nomination, List<Long> parties){
		List<Object[]> allianceNominations = new ArrayList<Object[]>();
		Object[] selectedNomination = null;
		int rank = ((Long)nomination[9]).intValue();
		for(Object[] allianceNomination:nominations){
			if(parties.contains(allianceNomination[1]))
				allianceNominations.add(allianceNomination);
		}
		if(allianceNominations != null && allianceNominations.size() > 0){
			for(Object[] alNomination:allianceNominations)
				if(((Long)alNomination[9]).intValue() < rank)
					selectedNomination = alNomination;
		}
			
		return selectedNomination;
	}

	public ConstituencyPositionDetailVO getConstituencyPositionDetails(Long constituencyId, Double validVotes ,					
			Double totalVotes, String constituencyName, Object[] partyNominationWhoWonOrLost,
			Object[] oppositePartyWhichLostOrWon) {
		log.debug("getConstituencyPositionDetails...started");
		
		double firstOrSecondHighestVotes = 0;
		String oppositePartyShortNameWhichLostOrWon = "";
		String oppositePartyLongNameWhichLostOrWon = "";
		String oppositePartyCandidateWhoLostOrWon = "";
		
		double votingPercentage = 0;
		if(totalVotes!=0)
			votingPercentage = (validVotes*100)/totalVotes;
		
		if(oppositePartyWhichLostOrWon != null) {
			firstOrSecondHighestVotes = (Double)oppositePartyWhichLostOrWon[8];
			oppositePartyShortNameWhichLostOrWon = oppositePartyWhichLostOrWon[5].toString();
			oppositePartyLongNameWhichLostOrWon = oppositePartyWhichLostOrWon[6].toString();
			oppositePartyCandidateWhoLostOrWon = oppositePartyWhichLostOrWon[7].toString();
		}
		
		ConstituencyPositionDetailVO positionDetail = new ConstituencyPositionDetailVO();
		
		double votesWonByPartyWinner = (Double)partyNominationWhoWonOrLost[8];
		double percentWonByPartyWinner = 0;
		double percentWonBySecondRank = 0;
		
		if(validVotes!=0)
		{
			 percentWonByPartyWinner = (votesWonByPartyWinner*100)/validVotes;
			 percentWonBySecondRank = (firstOrSecondHighestVotes*100)/validVotes;											
		}
		
		double diffInPercent = percentWonByPartyWinner - percentWonBySecondRank; 
		
		positionDetail.setConstituencyId(constituencyId);
		positionDetail.setCandidateName(partyNominationWhoWonOrLost[7].toString());
		positionDetail.setConstiuencyName(constituencyName);
		positionDetail.setPartyName(partyNominationWhoWonOrLost[5].toString());
		positionDetail.setPercentageOfVotes(new BigDecimal(percentWonByPartyWinner).setScale(2, BigDecimal.ROUND_HALF_UP));
		positionDetail.setOppositePartyPercentageOfVotes(new BigDecimal(percentWonBySecondRank).setScale(2, BigDecimal.ROUND_HALF_UP));
		positionDetail.setOppositeParty(oppositePartyShortNameWhichLostOrWon);
		positionDetail.setOppositePartyCandidate(oppositePartyCandidateWhoLostOrWon);
		positionDetail.setOppositePartyLongName(oppositePartyLongNameWhichLostOrWon);
		positionDetail.setPercentageDiffBetweenTop2(new BigDecimal(diffInPercent).setScale(2, BigDecimal.ROUND_HALF_UP));
		positionDetail.setPercentageOfVotesPolled(new BigDecimal(votingPercentage).setScale (2,BigDecimal.ROUND_HALF_UP));// voting percentage for the constituency
		positionDetail.setPresentElectionVotes(Math.round(totalVotes));
		positionDetail.setRank(((Long)partyNominationWhoWonOrLost[9]).intValue());
		positionDetail.setMarginVotesPercentage(partyNominationWhoWonOrLost[12] != null ? new BigDecimal(partyNominationWhoWonOrLost[12].toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString():"0.00");
		log.debug("getConstituencyPositionDetails...end");
		return positionDetail;
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> partyVotesFlow(Map<String, BigDecimal> presentElectionPartyVotePerc, 
			Map<String, BigDecimal> previousElectionPartyVotePerc, boolean isPositiveVotesFlow,
			List<Party> presentAlliancParties,List<Party> previousAlliancParties){
		
		Map<String, String> votesFlow = new LinkedHashMap<String, String>();
		Map<String, String> tempMap = new HashMap<String, String>();
		Set<String> presentAlliancPartiesSet = null;
		Set<String> previousAlliancPartiesSet = null;
		
	    SortedMap<String, Double> sortedVotesFlowMap = new TreeMap(new ValueComparator(presentElectionPartyVotePerc));
		int count = 0;
		if(presentAlliancParties != null)
			presentAlliancPartiesSet = getAlliancPartiesMap(presentAlliancParties);
		if(previousAlliancParties != null)
			previousAlliancPartiesSet = getAlliancPartiesMap(previousAlliancParties);
		 
	    for (Map.Entry<String, BigDecimal> entry : presentElectionPartyVotePerc.entrySet()) {
	    	String partyName = entry.getKey();
	    	
	    	if(presentAlliancParties != null && previousAlliancParties != null){
	    		if(presentAlliancPartiesSet != null && presentAlliancPartiesSet.contains(partyName) && previousAlliancPartiesSet != null && previousAlliancPartiesSet.contains(partyName))
                  continue;
	    	}
	    	
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
				log.debug("partyNames ::"+partyName);
				 String value = tempMap.get(partyName);
				 if(value.length()>0)
					 votesFlow.put(partyName, value);
				 count ++;
	    }
		
		return votesFlow;
	}
	
	public Set<String> getAlliancPartiesMap(List<Party> partiesList){
		Set<String> partiesSet = null;
		if(partiesList != null){
			partiesSet = new HashSet<String>();
			for(Party party:partiesList){
				partiesSet.add(party.getShortName());
			}
		}
	 return partiesSet;
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

	public Object[] getNominatedPartiesAsRebels(List<Object[]> nominations, List<Long> alliancePartyIds, Party selectedParty){

		if(alliancePartyIds.size() == 0)
			return null;

		List<Long> nominatedPartyIds = new ArrayList<Long>();
		
		for(Object[] nomination : nominations) 
			nominatedPartyIds.add((Long)nomination[1]);
		
		if(nominatedPartyIds.contains(selectedParty.getPartyId())) {
			for(Object[] partyInfo : nominations) {
				if(alliancePartyIds.contains(partyInfo[1]) && !partyInfo[1].equals(selectedParty.getPartyId()))
					return partyInfo;
			}
		}
		
		if(!nominatedPartyIds.contains(selectedParty.getPartyId())){
			List<Long> tempPartys = new ArrayList<Long>();
			int rank=0;
			Object[] resultParty = null;
			for(Long partyId:alliancePartyIds){
				if(nominatedPartyIds.contains(partyId))
					tempPartys.add(partyId);
			}
			
			if(tempPartys.size() > 1){
			   for(Object[] partyInfo:nominations){
				 if(tempPartys.contains(partyInfo[1])){
					if(((Long)partyInfo[9]).intValue() > rank){
						rank = ((Long)partyInfo[9]).intValue();
						resultParty = partyInfo;
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
	
	public List<PartyPositionDisplayVO> getNthPositionPartyDetails(Long electionTypeID, Long stateID, Long districtID, Long year, Long partyID, boolean alliances, int rank,String reportLevel){
		List<PartyPositionDisplayVO>  partyPositionDisplayVOList = new ArrayList<PartyPositionDisplayVO>();
		
		if(log.isDebugEnabled())
			log.debug(" Inside getNthPositionPartyDetails Method ....");
		
		Long electionID = getElectionID(electionTypeID, 1L, stateID, year);
		List<ConstituencyElection> constituencyElectionList = null;
		List<SelectOptionVO> parties = getAlliancePartiesAsVO(year,electionTypeID,partyID,alliances,stateID);
		
		if(log.isDebugEnabled())
			log.debug("ElectionId -->" + electionID);
		Long countryId = new Long(1);
		if("3".equals(reportLevel)){
			constituencyElectionList = staticDataService.getConstituencyElectionsFromNominationForCountry(electionID,stateID,countryId,new Long(rank),partyID);
			log.debug("Report Level Inside getNthPositionPartyDetails method (service) :" + reportLevel);
		}
		else{
		 if(parties != null)
		  if(log.isDebugEnabled())
			log.debug("Alliance Parties -->" + parties.size());
		 //List<ConstituencyElection> constituencyElectionList =  staticDataService.getConstituencyElections(electionID,stateID,districtID);
		  if(alliances == false){
			  constituencyElectionList =  staticDataService.getConstituencyElectionsFromNomination(electionID, stateID, districtID, new Long(rank), partyID,reportLevel);
		  }
		  else if(alliances == true){
			  constituencyElectionList =  staticDataService.getConstituencyElectionsFromNominationWithAlliances(electionID, stateID, districtID, new Long(rank), parties);
		  }
		}	
		if(constituencyElectionList != null)
			if(log.isDebugEnabled())
				log.debug("Inside getNth position -->" + constituencyElectionList.size());
				
		for(ConstituencyElection constituencyElection : constituencyElectionList){
			PartyPositionDisplayVO partyPositionDisplayVO = getPartyPositionDisplayVO(constituencyElection, parties, partyID, rank,alliances);
			if(partyPositionDisplayVO!=null)
				partyPositionDisplayVOList.add(partyPositionDisplayVO);
		}
		return partyPositionDisplayVOList;
	}
	
	public PartyPositionDisplayVO getPartyPositionDisplayVO(ConstituencyElection constituencyElection,List<SelectOptionVO> parties, Long partyID, int rank,Boolean alliances){
		PartyPositionDisplayVO partyPositionDisplayVO = new PartyPositionDisplayVO();
		Set<Nomination> nominations = constituencyElection.getNominations();
		
		if(log.isDebugEnabled())
			log.debug("Inside getPartyPositionDisplayVO -->" + nominations.size());
				
		Nomination selectedNomination = partyParticipatedNomination(nominations , parties, rank,partyID,alliances);
		if(selectedNomination == null)
			return null;
		partyPositionDisplayVO.setConstituencyId(constituencyElection.getConstituency().getConstituencyId());
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
		if(parties != null && parties.size() > 0 && alliances == true)
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
			if(selectedPartyAndItsAlliances.size() == 1 && rank == -1 && selectedPartyAndItsAlliances.get(0).getCandidateResult().getRank().intValue() > 4){
				selectedNomination = selectedPartyAndItsAlliances.get(0);
				return selectedNomination;
			}
						
		  for(Nomination selectdNomination:selectedPartyAndItsAlliances){
			  /*//my
			  if(selectdNomination.getParty().getPartyId().equals(partyID) && selectdNomination.getCandidateResult().getRank().intValue() == rank)
				  return selectdNomination;
			  if(selectdNomination.getCandidateResult().getRank().intValue() == rank)
				  selectedNomination = selectdNomination;
			  if((rank == -1 && selectdNomination.getCandidateResult().getRank().intValue() > 4))
				  return selectdNomination;;
			  //*/
			  if(selectdNomination.getCandidateResult().getRank().intValue() < rank)
				  return null;
			  if(rank == -1 && selectdNomination.getCandidateResult().getRank() < 5)
				  return null;
			if((rank == -1 && selectdNomination.getCandidateResult().getRank().intValue() > 4) || selectdNomination.getCandidateResult().getRank().intValue() == rank)
				return selectdNomination;
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
	
	public List<SelectOptionVO> getAlliancePartiesAsVO(Long year, Long electionTypeID, Long partyID, boolean alliances,Long stateId){

		List<SelectOptionVO> parties = null;
		
		if(log.isDebugEnabled())
			log.debug("Entered Into getAllianceParties Method .....");
		if(alliances){
			parties = staticDataService.getAlliancePartiesAsVO(year.toString(), electionTypeID, partyID,stateId);
			if(parties != null){
				for(SelectOptionVO option:parties)
					if(log.isDebugEnabled())
						log.debug("Alliance Parties ::" + option.getName());
			}
		}
		if(parties == null){
			    parties = new ArrayList<SelectOptionVO>();
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
		
		List<Election> elections = null;
		ElectionType electionType = electionTypeDAO.get(electionTypeID);
		if(electionType != null && electionType.getElectionType().equals(IConstants.PARLIAMENT_ELECTION_TYPE))
			elections = electionDAO.findByElectionTypeCountry(electionTypeID, countryID);
		else
		    elections = electionDAO.findByPropertyTypeId_CountryId_StateId(electionTypeID, countryID, stateID);
		Long electionID = 0L;
		for(Election election : elections){
			if(year.toString().equals(election.getElectionYear())){
				electionID = election.getElectionId();
				break;
			}
		}
		return electionID;
	}
	
	public Object[] getSelectedPartyNomination(List<Object[]> nominations,Party selectedParty){
		for(Object[] partyInfo:nominations)
			if(partyInfo[1].equals(selectedParty.getPartyId()))
				return partyInfo;
		
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
	
	public List<PartyPositionDisplayVO> getPartyPositionDetailsForAnElection(Long electionTypeID, Long stateID, Long districtID, Long year, Long partyID, boolean alliances, int rank,String reportLevel){
		List<PartyPositionDisplayVO>  partyPositionDisplayVOList = null;
		Long electionID = null;
		if(log.isDebugEnabled())
			log.debug(" Inside getPartyPositionDetailsForAnElection Method ....");
		if(electionTypeID != null && stateID != null)
		electionID = getElectionID(electionTypeID, 1L, stateID, year);
		List<ConstituencyElection> constituencyElectionList = null;
		if(electionID != null && districtID != null && partyID != null)
		constituencyElectionList =  staticDataService.getConstituencyElectionsFromNomination(electionID, stateID, districtID, new Long(rank), partyID,reportLevel);
		if(constituencyElectionList != null){
			partyPositionDisplayVOList = new ArrayList<PartyPositionDisplayVO>();
			for(ConstituencyElection constituencyElectionResults:constituencyElectionList){
				PartyPositionDisplayVO partyPositionDisplayVO = null;
				Set<Nomination> nominations = constituencyElectionResults.getNominations();
				partyPositionDisplayVO = getPartyPositionForAConstituency(nominations,partyID,rank);
				partyPositionDisplayVOList.add(partyPositionDisplayVO);
			}
		}
		
		return partyPositionDisplayVOList;
	}
	
	public List<PartyPositionDisplayVO> getPartyPositionsDetailsInAnElection(Long electionId, Long partyId, Long rank){
		List<PartyPositionDisplayVO>  partyPositionDisplayVOList = new ArrayList<PartyPositionDisplayVO>();
		List<ConstituencyElection> constituencyElections = nominationDAO.findConstituencyElectionsByElectionPartyAndPosition(electionId, partyId, rank);
		for(ConstituencyElection constituencyElectionResults:constituencyElections){
			PartyPositionDisplayVO partyPositionDisplayVO = null;
			Set<Nomination> nominations = constituencyElectionResults.getNominations();
			partyPositionDisplayVO = getPartyPositionForAConstituency(nominations,partyId,rank.intValue());
			partyPositionDisplayVOList.add(partyPositionDisplayVO);
		}
		return partyPositionDisplayVOList;
	}
	
	public PartyPositionDisplayVO getPartyPositionForAConstituency(Set<Nomination> nominations,Long partyId,int rank){
		
		if(log.isDebugEnabled())
			log.debug(" Inside getPartyPositionForAConstituency Method ....");
		PartyPositionDisplayVO partyPositionDisplayVO = null;
		List<PartyPostionInfoVO> oppPartyPositionInfoList = null;
		if(nominations != null && nominations.size() > 0 && partyId != null){
			partyPositionDisplayVO = new PartyPositionDisplayVO();
			oppPartyPositionInfoList = new ArrayList<PartyPostionInfoVO>();
			for(Nomination nominationForAParty:nominations){
				if(nominationForAParty.getParty().getPartyId().equals(partyId)){
					partyPositionDisplayVO.setConstituencyId(nominationForAParty.getConstituencyElection().getConstituency().getConstituencyId());
					partyPositionDisplayVO.setPartyId(partyId);
					if(nominationForAParty.getParty().getShortName() != null)
					partyPositionDisplayVO.setPartyName(nominationForAParty.getParty().getShortName());
					else
					partyPositionDisplayVO.setPartyName(nominationForAParty.getParty().getLongName());
					partyPositionDisplayVO.setElectionId(nominationForAParty.getConstituencyElection().getElection().getElectionId());
					partyPositionDisplayVO.setCandidateName(nominationForAParty.getCandidate().getLastname().trim());
					partyPositionDisplayVO.setConstituencyName(nominationForAParty.getConstituencyElection().getConstituency().getName());
					partyPositionDisplayVO.setRank(nominationForAParty.getCandidateResult().getRank());
					partyPositionDisplayVO.setVotePercentage(nominationForAParty.getCandidateResult().getVotesPercengate());
				}
				if(nominationForAParty.getCandidateResult().getRank() < new Long(rank)){
					PartyPostionInfoVO partyPostionInfoVO = new PartyPostionInfoVO();
					partyPostionInfoVO.setCandidateName(nominationForAParty.getCandidate().getLastname());
					partyPostionInfoVO.setPartyName(nominationForAParty.getParty().getShortName());
					partyPostionInfoVO.setRank(nominationForAParty.getCandidateResult().getRank().intValue());
					partyPostionInfoVO.setVotePercentage(nominationForAParty.getCandidateResult().getVotesPercengate());
					oppPartyPositionInfoList.add(partyPostionInfoVO);
				}
			}
			
			Collections.sort(oppPartyPositionInfoList, new PartyPostionInfoVOComparator());
			partyPositionDisplayVO.setOppPartyPositionInfoList(oppPartyPositionInfoList);
		}
		
		return partyPositionDisplayVO;
	}

}
