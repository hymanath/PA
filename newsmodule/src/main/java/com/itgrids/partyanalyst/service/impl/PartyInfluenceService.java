/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on November 3, 2009
 */
package com.itgrids.partyanalyst.service.impl;

import com.itgrids.partyanalyst.service.IPartyInfluenceService;


public class PartyInfluenceService implements IPartyInfluenceService {/*
	
	private static Logger logger = Logger.getLogger(PartyInfluenceService.class);
	
	private IElectionDAO electionDAO;
	private INominationDAO nominationDAO;
	private IElectionScopeDAO electionScopeDAO;
	private IStaticDataService staticDataService;
	
	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	
	
	public IElectionScopeDAO getElectionScopeDAO() {
		return electionScopeDAO;
	}

	public void setElectionScopeDAO(IElectionScopeDAO electionScopeDAO) {
		this.electionScopeDAO = electionScopeDAO;
	}

	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	
	//Returns DistrictWiseConstituencyElectionResults VO
	public PartyInfluenceReportVO getPartyInfluenceReportResults(Long electionTypeId,Long partyId,Long newPartyId,String electionYear,Boolean includeAlliance,Long stateId){
		
		PartyInfluenceReportVO partyInfluenceReportVO =  new PartyInfluenceReportVO();
		List<ConstituencyElectionResults> constituencyElectionResultForYearOne = null;
		List<ConstituencyElectionResults> constituencyElectionResultForYearTwo = null;
		List<ConstituencyElectionsDetailedResultVO> constituencyElectionsDetailedResultVO = null;
		List<DistrictWiseConstituencyElectionResultsVO> districtWiseConstituencyElectionResultsVO = null;
		String previousElectionYear = "";
		Election election1 = null;
		Election election2 = null;
		
		if(logger.isDebugEnabled()){
			logger.debug("Election TypeId =" + electionTypeId);
			logger.debug("PartyId =" + partyId);
			logger.debug("New PartyId = " + newPartyId);
			logger.debug("Election Year = " + electionYear);
			logger.debug("StateId = " + stateId);
			logger.debug("Is Alliance Included ="  + includeAlliance);
		}
		
		try{
		  previousElectionYear = electionDAO.findPreviousElectionYear(electionYear, electionTypeId, stateId, new Long(1));
		  //List<ElectionScope> electionScope = electionScopeDAO.findByPropertyElectionTypeIdandStateId(electionTypeId, stateId);
		
	      if(previousElectionYear == null)
			return null;
	      if(logger.isDebugEnabled())
		     logger.debug("Prev Year Is" + previousElectionYear);
	    
		  election1 = getElection(new Long(2),electionYear);
		  election2 = getElection(new Long(2),previousElectionYear);
		
		}
		catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(" Elections Data Not Available" );
			partyInfluenceReportVO.setExceptionEncountered(ex);
			partyInfluenceReportVO.setResultCode(ResultCodeMapper.FAILURE);
			partyInfluenceReportVO.setResultPartial(true);
			return partyInfluenceReportVO;
		}
		
		try{
		  //year 1
		  List<Nomination> nominationsForPartyForYearOne = getNominationsForAPartyForAnElection(electionTypeId,partyId,electionYear,election1.getElectionId(),includeAlliance);
		  List<Nomination> nominationsForNewPartyForYearOne = getNominationsForAPartyForAnElection(electionTypeId,newPartyId,electionYear,election1.getElectionId(),includeAlliance);
		
		  if(nominationsForPartyForYearOne != null && nominationsForNewPartyForYearOne != null){
			if(logger.isDebugEnabled())
				logger.debug("Both Partys Have Nominations");
			constituencyElectionResultForYearOne = getConstituencyElectionResults(nominationsForPartyForYearOne,nominationsForNewPartyForYearOne);
		  }
		  else if(nominationsForPartyForYearOne != null && nominationsForNewPartyForYearOne == null){
			if(logger.isDebugEnabled())
				logger.debug(" Only Impacted Party Have Nominations");
			constituencyElectionResultForYearOne = getConstituencyElectionResults(nominationsForPartyForYearOne);
			constituencyElectionResultForYearOne = getDetailsOfPartysParticipated(constituencyElectionResultForYearOne,true);
		  }
		  else if(nominationsForPartyForYearOne == null && nominationsForNewPartyForYearOne != null){
			if(logger.isDebugEnabled())
				logger.debug(" Only New Party Have Nominations");
			constituencyElectionResultForYearOne = getConstituencyElectionResults(nominationsForNewPartyForYearOne);
			constituencyElectionResultForYearOne = getDetailsOfPartysParticipated(constituencyElectionResultForYearOne,false);
		  }
		
		
		  //year 2
		  List<Nomination> nominationsForPartyForYearTwo = getNominationsForAPartyForAnElection(electionTypeId,partyId,previousElectionYear,election2.getElectionId(),includeAlliance);
		  List<Nomination> nominationsForNewPartyForYearTwo = getNominationsForAPartyForAnElection(electionTypeId,newPartyId,previousElectionYear,election2.getElectionId(),includeAlliance);
	
		  if(nominationsForPartyForYearTwo != null && nominationsForNewPartyForYearTwo != null){
			if(logger.isDebugEnabled())
				logger.debug("Both Partys Have Nominations");
			constituencyElectionResultForYearTwo = getConstituencyElectionResults(nominationsForPartyForYearTwo,nominationsForNewPartyForYearTwo);
			
		  }
		  else if(nominationsForPartyForYearTwo != null && nominationsForNewPartyForYearTwo == null){
			if(logger.isDebugEnabled())
				logger.debug(" Only Impacted Party Have Nominations");
			constituencyElectionResultForYearTwo = getConstituencyElectionResults(nominationsForPartyForYearTwo);
			constituencyElectionResultForYearTwo = getDetailsOfPartysParticipated(constituencyElectionResultForYearTwo,true);
		  }
		  else if(nominationsForPartyForYearTwo == null && nominationsForNewPartyForYearTwo != null){
			if(logger.isDebugEnabled())
				logger.debug(" Only New Party Have Nominations");
			constituencyElectionResultForYearTwo = getConstituencyElectionResults(nominationsForNewPartyForYearTwo);
			constituencyElectionResultForYearTwo = getDetailsOfPartysParticipated(constituencyElectionResultForYearTwo,false);
		  }
		 
		}
		catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(" Elections Data Not Available" );
			partyInfluenceReportVO.setExceptionEncountered(ex);
			partyInfluenceReportVO.setResultCode(ResultCodeMapper.FAILURE);
			partyInfluenceReportVO.setResultPartial(true);
			return partyInfluenceReportVO;
		}
			
		if(constituencyElectionResultForYearOne != null && constituencyElectionResultForYearTwo != null){
		    if(logger.isDebugEnabled())
		    	logger.debug("Constituency wise Election Results For Both Years Are Not Null");
			constituencyElectionsDetailedResultVO = getDetailedConstituencyWiseElectionResultsForTwoYears(constituencyElectionResultForYearOne,constituencyElectionResultForYearTwo);
		}
		if(constituencyElectionsDetailedResultVO.size() > 0){
			if(logger.isDebugEnabled())
				logger.debug("Constituency Wise Results Successfull");
			districtWiseConstituencyElectionResultsVO = new ArrayList<DistrictWiseConstituencyElectionResultsVO>();
			List<Long> districtIds = getDistrictIds(constituencyElectionResultForYearOne);
			
			districtWiseConstituencyElectionResultsVO = getDistrictWiseResults(constituencyElectionsDetailedResultVO);
			Map<Long,String> totalDistrictWisePartyPercentage = getTotalDistrictWisePartyPercentage(constituencyElectionResultForYearOne,districtIds);
		    Map<Long,String> totalDistrictWisePartyPercentageforPrevYear = getTotalDistrictWisePartyPercentage(constituencyElectionResultForYearTwo,districtIds);
			
		    if(districtWiseConstituencyElectionResultsVO != null && districtWiseConstituencyElectionResultsVO.size() > 0){
		    	if(logger.isDebugEnabled())
					logger.debug("District Wise Results Successfull");
		    	//partyInfluenceReportVO = new PartyInfluenceReportVO();
		    	partyInfluenceReportVO = getPartyInfluenceReportFinalResults(districtWiseConstituencyElectionResultsVO,partyId,newPartyId,totalDistrictWisePartyPercentage,totalDistrictWisePartyPercentageforPrevYear);
		    	if(logger.isDebugEnabled())
					logger.debug("Returning The Final Results");
		    return partyInfluenceReportVO;
		    }
		 }

	return null;
	}
	
	public List<Long> getDistrictIds(List<ConstituencyElectionResults> constituencyElectionResults){
		
		List<Long> districtIds = new ArrayList<Long>();
		for(ConstituencyElectionResults electionResults:constituencyElectionResults){
			
			if(!districtIds.contains(electionResults.getElectionResultForParty().getDistrictId())){
				districtIds.add(electionResults.getElectionResultForParty().getDistrictId());
				if(logger.isDebugEnabled())
					logger.debug("districtId -->" + electionResults.getElectionResultForParty().getDistrictId());
			}
				
		}
		
	return districtIds;
	}
	
	public List<ConstituencyElectionResults> getDetailsOfPartysParticipated(List<ConstituencyElectionResults> constituencyElectionResults,Boolean flag){
		for(ConstituencyElectionResults result:constituencyElectionResults){
			if(flag.equals(true))
				result.setElectionResultForNewParty(null);
			else
				result.setElectionResultForParty(null);
		}
		
	return constituencyElectionResults;
	}
	
	public Map<Long,String> getTotalDistrictWisePartyPercentage(List<ConstituencyElectionResults> constituencyElectionResultForYearOne,List<Long> districtIds){
		Map<Long,String> districtWisePercentage = new HashMap<Long,String>();
		
		if(logger.isDebugEnabled())
			logger.debug("Entered Into getTotalDistrictWisePartyPercentage...");
		for(Long districtId:districtIds){
			Long votesEarned = new Long(0);
			Long validVotes = new Long(0);
			int count = 0;
			if(logger.isDebugEnabled())
				logger.debug("District Id -->" + districtId);
			for(ConstituencyElectionResults results:constituencyElectionResultForYearOne){
				if(results.getElectionResultForParty().getDistrictId().equals(districtId)){
					votesEarned += results.getElectionResultForParty().getVotesEarned();
					validVotes += results.getElectionResultForParty().getValidVotes();
					count++;
				}
			}
			if(count>0){
			Double percentage = getPercentageOfVotes(votesEarned.doubleValue(),validVotes.doubleValue());
			Double votesPercentRounded = getRoundedDoubleValue(percentage);
			districtWisePercentage.put(districtId, votesPercentRounded.toString());
			}
		}
	return districtWisePercentage;
	}
	
	public PartyInfluenceReportVO getPartyInfluenceReportFinalResults(List<DistrictWiseConstituencyElectionResultsVO> districtWiseConstituencyElectionResultsVO,Long partyId,Long newPartyId,Map<Long,String> totalDistrictVotesPercentages,Map<Long,String> totalDistrictVotesPercentagesforPrevYear){
		
		if(logger.isDebugEnabled())
			logger.debug("Entered Into getPartyInfluenceReportFinalResults Method");
		
		PartyInfluenceReportVO partyInfluenceReportVO = new PartyInfluenceReportVO();
		int constituencies = 0;
		Double totalVotesEarnedForPartyInDistrictsForYearOne = new Double(0);
		Double totalValidVotesForPartyInDistrictsForYearOne = new Double(0);
		Double totalVotesEarnedForNewPartyInDistrictsForYearOne = new Double(0);
		Double totalValidVotesForNewPartyInDistrictsForYearOne = new Double(0);
		Double totalVotesEarnedForPartyInDistrictsForYearTwo = new Double(0);
		Double totalValidVotesForPartyInDistrictsForYearTwo = new Double(0);
		
		Double percentageOfVotesForPartyInDistrictsForYearOne = new Double(0);
		Double percentageOfVotesForNewPartyInDistrictsForYearOne = new Double(0);
		Double percentageOfVotesForPartyInDistrictsForYearTwo = new Double(0);
		Double percentageDiffForParty = new Double(0);
		
		for(DistrictWiseConstituencyElectionResultsVO resultVO:districtWiseConstituencyElectionResultsVO){
			
			if(totalDistrictVotesPercentages.containsKey(resultVO.getDistrictId()))
				resultVO.setTotalVotesPercentageForDistrict(totalDistrictVotesPercentages.get(resultVO.getDistrictId()));
			
			if(totalDistrictVotesPercentagesforPrevYear.containsKey(resultVO.getDistrictId()))
				resultVO.setTotalVotesPercentageForDistrictforPrevYear(totalDistrictVotesPercentagesforPrevYear.get(resultVO.getDistrictId()));
			
			if(totalDistrictVotesPercentages.containsKey(resultVO.getDistrictId()) && totalDistrictVotesPercentagesforPrevYear.containsKey(resultVO.getDistrictId())){
				String percentforPresentYear = totalDistrictVotesPercentages.get(resultVO.getDistrictId());
				String percentforPrevYear = totalDistrictVotesPercentagesforPrevYear.get(resultVO.getDistrictId());
				Double diff = Double.valueOf(percentforPresentYear) - Double.valueOf(percentforPrevYear);
				resultVO.setTotalVotesPercentageDiffForDistrict(getRoundedDoubleValue(diff).toString());
			}
				
			
			if(resultVO.getPartyId().equals(partyId))
			 partyInfluenceReportVO.setImpactedPartyName(resultVO.getPartyName());
			if(resultVO.getNewPartyId().equals(newPartyId))
			 partyInfluenceReportVO.setNewPartyName(resultVO.getNewPartyName());
			partyInfluenceReportVO.setYear(resultVO.getYear());
			partyInfluenceReportVO.setPreviousYear(resultVO.getPreviousYear());
			partyInfluenceReportVO.setTotalDistrictsConsidered(districtWiseConstituencyElectionResultsVO.size());
			
			totalVotesEarnedForPartyInDistrictsForYearOne += resultVO.getTotalVotesEarnedInDistrictForPartyInYear1();
			totalValidVotesForPartyInDistrictsForYearOne += resultVO.getTotalValidVotesInDistrictForPartyInYear1();
			totalVotesEarnedForNewPartyInDistrictsForYearOne += resultVO.getTotalVotesEarnedInDistrictForNewPartyInYear1();
			totalValidVotesForNewPartyInDistrictsForYearOne += resultVO.getTotalValidVotesInDistrictForNewPartyInYear1();
			totalVotesEarnedForPartyInDistrictsForYearTwo += resultVO.getTotalVotesEarnedInDistrictForPartyInYear2();
			totalValidVotesForPartyInDistrictsForYearTwo += resultVO.getTotalValidVotesInDistrictForPartyInYear2();
			
			constituencies += resultVO.getConstituencyElectionsDetailedResults().size();
		}
		
		    percentageOfVotesForPartyInDistrictsForYearOne = getPercentageOfVotes(totalVotesEarnedForPartyInDistrictsForYearOne,totalValidVotesForPartyInDistrictsForYearOne);
		    percentageOfVotesForNewPartyInDistrictsForYearOne = getPercentageOfVotes(totalVotesEarnedForNewPartyInDistrictsForYearOne,totalValidVotesForNewPartyInDistrictsForYearOne);
		    percentageOfVotesForPartyInDistrictsForYearTwo = getPercentageOfVotes(totalVotesEarnedForPartyInDistrictsForYearTwo,totalValidVotesForPartyInDistrictsForYearTwo);
		    
		    partyInfluenceReportVO.setTotalDistrictsVotesPercentForPartyForYearOne(getRoundedDoubleValue(percentageOfVotesForPartyInDistrictsForYearOne).toString());
		    partyInfluenceReportVO.setTotalDistrictsVotesPercentForNewPartyForYearOne(getRoundedDoubleValue(percentageOfVotesForNewPartyInDistrictsForYearOne).toString());
		    partyInfluenceReportVO.setTotalDistrictsVotesPercentForPartyForYearTwo(getRoundedDoubleValue(percentageOfVotesForPartyInDistrictsForYearTwo).toString());
		    
		    percentageDiffForParty = percentageOfVotesForPartyInDistrictsForYearOne - percentageOfVotesForPartyInDistrictsForYearTwo;
		    partyInfluenceReportVO.setTotalDistrictsVotesPercentDiffForParty(getRoundedDoubleValue(percentageDiffForParty).toString());
		    
		    partyInfluenceReportVO.setTotalConstituenciesConsidered(constituencies);
		    partyInfluenceReportVO.setDistrictWiseConstituencyElectionResultsVO(districtWiseConstituencyElectionResultsVO);
	
		    if(logger.isDebugEnabled())
		    	logger.debug("total constituencies Size -->" + constituencies);
	return partyInfluenceReportVO;
	}
	
	
	public List<Nomination> getNominationsForAPartyForAnElection(Long electionTypeId,Long partyId,String electionYear,Long electionId,Boolean includeAlliance){
		
        List<Long> partyIds = null;
        if(logger.isDebugEnabled())
        	logger.debug("PartyIds at beginning -->" + partyIds);
		try{
		   if(includeAlliance){
			partyIds = getHasAllianceParties(electionYear,electionTypeId,partyId);
			if(logger.isDebugEnabled())
	        	logger.debug("PartyIds if alliance is true -->" + partyIds);
		   }
		   else{
			partyIds = new ArrayList<Long>();
			partyIds.add(partyId);
			if(logger.isDebugEnabled())
	        	logger.debug("PartyIds if alliance is false -->" + partyIds);
		   }
		
		   if(partyIds != null){
			if(logger.isDebugEnabled())
	        	logger.debug("PartyIds at end -->" + partyIds);
		   List<Nomination> nominations = getNominations(electionId,partyIds);
		   if(nominations != null && nominations.size() > 0)
			return nominations;
		  }
		}
		catch(Exception e){
			if(logger.isDebugEnabled())
	        	logger.debug("Exception -->" + e);
		}
		
	return null;
	}
	
	//Returns List Of Nominations for An Election For A Single or Alliance Party's.
	public List<Nomination> getNominations(Long electionId,List<Long> partyIds){
		
		if(logger.isDebugEnabled())
        	logger.debug("Entered Into getNominations method");
		List<Nomination> nominations = null;
		if(electionId != null && partyIds != null && partyIds.size() > 0){
			nominations = nominationDAO.findByElectionIdAndPartys(electionId, partyIds);
		}
	return 	nominations;
	}
	
	//Returns Election Object for A Election Year And ElectionType
	public Election getElection(Long electionScopeId,String electionYear){
		
		if(logger.isDebugEnabled())
        	logger.debug("Entered Into getElection method");
        Election election = electionDAO.findByElectionScopeIdElectionYear(electionScopeId, electionYear, IConstants.ELECTION_SUBTYPE_MAIN);
		
		if(election.getElectionId() != null)
		    return election;
		
	return null;
	}
	
	//Returns list Of Party Id's If a Party Has Alliance Party's
	public List<Long> getHasAllianceParties(String electionYear,Long electionType,Long partyId){
		
		
		if(logger.isDebugEnabled())
			logger.debug("Entered Into getAlliances method");
		
		List<SelectOptionVO> allianceParties = staticDataService.getAlliancePartiesAsVO(electionYear, electionType, partyId,0L);
		List<Long> partyIds = null;
		
		
		if(allianceParties != null && allianceParties.size() > 0){
			partyIds = new ArrayList<Long>();
			if(logger.isDebugEnabled())
	        	logger.debug("Partyid's" + partyIds);
			for(SelectOptionVO partys:allianceParties){
				partyIds.add(partys.getId());
				if(logger.isDebugEnabled())
		        	logger.debug("Partyid's alliances" + partys.getId());
			}
		}
		else{
			partyIds = new ArrayList<Long>();
			partyIds.add(partyId);	
			if(logger.isDebugEnabled())
	        	logger.debug("Partyid's at else Block" + partyIds);
		}
	return partyIds;
	}
	
	//Returns Two Parties  Participated ConstituencyElection Result VO's
	public List<ConstituencyElectionResults> getConstituencyElectionResults(List<Nomination> nominationsForPartyForYearOne,List<Nomination> nominationsForNewPartyForYearOne){
		
		List<ConstituencyElectionResults> constituencyElectionResults = new ArrayList<ConstituencyElectionResults>();
		Long votesEarnedforParty = new Long(0);
		Long validVotesforParty = new Long(0);
		String votesPercentageForParty = "";
		
		if(logger.isDebugEnabled())
			logger.debug("Entered Into getConstituencyElectionResults Merhod");
		
		for(Nomination nominationsForParty:nominationsForPartyForYearOne){
			Constituency constituencyOne = nominationsForParty.getConstituencyElection().getConstituency();
			Party partyOne = nominationsForParty.getParty();
			CandidateResult candidateResultOne = nominationsForParty.getCandidateResult();
			Candidate candidateOne = nominationsForParty.getCandidate();
			ConstituencyElectionResult constituencyElectionResultforParty = nominationsForParty.getConstituencyElection().getConstituencyElectionResult();
			
			votesEarnedforParty += candidateResultOne.getVotesEarned().longValue();
			validVotesforParty += constituencyElectionResultforParty.getValidVotes().longValue();
			
			for(Nomination nominationsForNewParty:nominationsForNewPartyForYearOne){
				Constituency constituencyTwo =	nominationsForNewParty.getConstituencyElection().getConstituency();
				Party partyTwo = nominationsForNewParty.getParty();
				CandidateResult candidateResultTwo = nominationsForNewParty.getCandidateResult();
				Candidate candidateTwo = nominationsForNewParty.getCandidate();
				
				if(constituencyTwo.getConstituencyId().equals(constituencyOne.getConstituencyId())){
					ConstituencyElectionResultVO constituencyElectionResultForParty = new ConstituencyElectionResultVO();
					ConstituencyElectionResultVO constituencyElectionResultForNewParty = new ConstituencyElectionResultVO();
					ConstituencyElectionResults constituencyElectionResult = new ConstituencyElectionResults();
					
					constituencyElectionResultForParty.setCandidateId(candidateOne.getCandidateId());
					constituencyElectionResultForParty.setCandidateName(candidateOne.getLastname().toUpperCase());
					constituencyElectionResultForParty.setConstituencyId(constituencyOne.getConstituencyId());
					constituencyElectionResultForParty.setConstituencyName(constituencyOne.getName().toUpperCase());
					constituencyElectionResultForParty.setDistrictId(constituencyOne.getDistrict().getDistrictId());
					constituencyElectionResultForParty.setDistrictName(constituencyOne.getDistrict().getDistrictName());
					constituencyElectionResultForParty.setVotesEarned(candidateResultOne.getVotesEarned().longValue());
					constituencyElectionResultForParty.setPercentageOfVotes(candidateResultOne.getVotesPercengate());
					constituencyElectionResultForParty.setYear(candidateResultOne.getNomination().getConstituencyElection().getElection().getElectionYear());
					constituencyElectionResultForParty.setValidVotes(nominationsForParty.getConstituencyElection().getConstituencyElectionResult().getValidVotes().longValue());
					constituencyElectionResultForParty.setPartyId(partyOne.getPartyId());
					if(partyOne.getShortName().trim() != null)
					 constituencyElectionResultForParty.setPartyName(partyOne.getShortName().trim());
					else
					 constituencyElectionResultForParty.setPartyName(partyOne.getLongName().trim());
					constituencyElectionResultForParty.setRank(candidateResultOne.getRank().intValue());
					
					constituencyElectionResultForNewParty.setCandidateId(candidateTwo.getCandidateId());
					constituencyElectionResultForNewParty.setCandidateName(candidateTwo.getLastname().toUpperCase());
					constituencyElectionResultForNewParty.setConstituencyId(constituencyTwo.getConstituencyId());
					constituencyElectionResultForNewParty.setConstituencyName(constituencyTwo.getName().toUpperCase());
					constituencyElectionResultForNewParty.setDistrictId(constituencyTwo.getDistrict().getDistrictId());
					constituencyElectionResultForNewParty.setDistrictName(constituencyTwo.getDistrict().getDistrictName());
					if(partyTwo.getShortName() != null)
					 constituencyElectionResultForNewParty.setPartyName(partyTwo.getShortName().trim());
					else
					 constituencyElectionResultForNewParty.setPartyName(partyTwo.getLongName().trim());
					constituencyElectionResultForNewParty.setVotesEarned(candidateResultTwo.getVotesEarned().longValue());
					constituencyElectionResultForNewParty.setPercentageOfVotes(candidateResultTwo.getVotesPercengate());
					constituencyElectionResultForNewParty.setValidVotes(nominationsForNewParty.getConstituencyElection().getConstituencyElectionResult().getValidVotes().longValue());
					constituencyElectionResultForNewParty.setPartyId(partyTwo.getPartyId());
					constituencyElectionResultForNewParty.setRank(candidateResultTwo.getRank().intValue());
					
					constituencyElectionResult.setElectionResultForParty(constituencyElectionResultForParty);
					constituencyElectionResult.setElectionResultForNewParty(constituencyElectionResultForNewParty);
					constituencyElectionResults.add(constituencyElectionResult);
					break;	
				}
				
			}
		}
		
		votesPercentageForParty = getRoundedDoubleValue(getPercentageOfVotes(votesEarnedforParty.doubleValue(),validVotesforParty.doubleValue())).toString();
		
		if(constituencyElectionResults.size() > 0 && constituencyElectionResults != null)
			return constituencyElectionResults;
		
	return null;
	}
	
	public List<ConstituencyElectionResults> getConstituencyElectionResults(List<Nomination> nominationsForPartyForYearOne){
		
		List<ConstituencyElectionResults> constituencyElectionResults = new ArrayList<ConstituencyElectionResults>();
		ConstituencyElectionResultVO constituencyElectionResultForParty = null;
		ConstituencyElectionResults constituencyElectionResult = null;
		
		for(Nomination nominationsForParty:nominationsForPartyForYearOne){
			Constituency constituencyOne = nominationsForParty.getConstituencyElection().getConstituency();
			Party partyOne = nominationsForParty.getParty();
			CandidateResult candidateResultOne = nominationsForParty.getCandidateResult();
			Candidate candidateOne = nominationsForParty.getCandidate();
			
			constituencyElectionResultForParty = new ConstituencyElectionResultVO();
			constituencyElectionResult = new ConstituencyElectionResults();
			
			constituencyElectionResultForParty.setCandidateId(candidateOne.getCandidateId());
			constituencyElectionResultForParty.setCandidateName(candidateOne.getLastname().toUpperCase());
			constituencyElectionResultForParty.setConstituencyId(constituencyOne.getConstituencyId());
			constituencyElectionResultForParty.setConstituencyName(constituencyOne.getName().toUpperCase());
			constituencyElectionResultForParty.setDistrictId(constituencyOne.getDistrict().getDistrictId());
			constituencyElectionResultForParty.setDistrictName(constituencyOne.getDistrict().getDistrictName());
			constituencyElectionResultForParty.setPartyId(partyOne.getPartyId());
			if(partyOne.getShortName().trim() != null)
			 constituencyElectionResultForParty.setPartyName(partyOne.getShortName().toUpperCase());
			else
			 constituencyElectionResultForParty.setPartyName(partyOne.getLongName().toUpperCase());
			constituencyElectionResultForParty.setVotesEarned(candidateResultOne.getVotesEarned().longValue());
			constituencyElectionResultForParty.setPercentageOfVotes(candidateResultOne.getVotesPercengate());
			constituencyElectionResultForParty.setYear(candidateResultOne.getNomination().getConstituencyElection().getElection().getElectionYear());
			constituencyElectionResultForParty.setValidVotes(nominationsForParty.getConstituencyElection().getConstituencyElectionResult().getValidVotes().longValue());
			constituencyElectionResultForParty.setRank(candidateResultOne.getRank().intValue());
			
			constituencyElectionResult.setElectionResultForParty(constituencyElectionResultForParty);
			constituencyElectionResults.add(constituencyElectionResult);
		}
		if(constituencyElectionResults.size() > 0 && constituencyElectionResults != null)
			return constituencyElectionResults;
		
	return null;
	}
	
	public List<ConstituencyElectionsDetailedResultVO> getDetailedConstituencyWiseElectionResultsForTwoYears(List<ConstituencyElectionResults> constituencyElectionResultForYearOne,List<ConstituencyElectionResults> constituencyElectionResultForYearTwo){
		
		List<ConstituencyElectionsDetailedResultVO> constituencyElectionsDetailedResultVO = new ArrayList<ConstituencyElectionsDetailedResultVO>();
		
		
		for(ConstituencyElectionResults constituencyElectionResultsforYearOne:constituencyElectionResultForYearOne){
			ConstituencyElectionResultVO partyYearOne = constituencyElectionResultsforYearOne.getElectionResultForParty();
						
			for(ConstituencyElectionResults constituencyElectionResultsforYearTwo:constituencyElectionResultForYearTwo){
				ConstituencyElectionResultVO partyYearTwo = constituencyElectionResultsforYearTwo.getElectionResultForParty();
						
				if(partyYearTwo.getConstituencyId().equals(partyYearOne.getConstituencyId())){
					ConstituencyElectionsDetailedResultVO constituencyElectionsDetailedResult = new ConstituencyElectionsDetailedResultVO();
					constituencyElectionsDetailedResult.setConstituencyId(partyYearOne.getConstituencyId());
					constituencyElectionsDetailedResult.setConstituencyName(partyYearOne.getConstituencyName());
					constituencyElectionsDetailedResult.setDistrictId(partyYearOne.getDistrictId());
					Double value1 = Double.valueOf(partyYearOne.getPercentageOfVotes());
					Double value2 = Double.valueOf(partyYearTwo.getPercentageOfVotes());
					Double diff = value1 - value2;
					BigDecimal bd = new BigDecimal(diff);
					BigDecimal bd1 =bd.setScale(2, BigDecimal.ROUND_HALF_UP);
					Double difference = bd1.doubleValue();
					constituencyElectionsDetailedResult.setVotesPercentageDiff(difference.toString());
					constituencyElectionsDetailedResult.setVotesPercntDiff(difference);
					constituencyElectionsDetailedResult.setConstituencyElectionResultsForYearOne(constituencyElectionResultsforYearOne);
					constituencyElectionsDetailedResult.setConstituencyElectionResultsForYearTwo(constituencyElectionResultsforYearTwo);
					
					constituencyElectionsDetailedResultVO.add(constituencyElectionsDetailedResult);
				break;
				}
			}
		}
		
		if(constituencyElectionsDetailedResultVO.size() > 0)
			return constituencyElectionsDetailedResultVO;
	
	return null;
	}
	
	
	
	public List<DistrictWiseConstituencyElectionResultsVO> getDistrictWiseResults(List<ConstituencyElectionsDetailedResultVO> constituencyElectionsDetailedResultVO){
		
		List<DistrictWiseConstituencyElectionResultsVO> districtWiseConstituencyElectionResultsVO = new ArrayList<DistrictWiseConstituencyElectionResultsVO>();
		DistrictWiseConstituencyElectionResultsVO districtWiseConstituencyElectionResults = null;
		List<Long> districtIds = new ArrayList<Long>();
		districtIds.add(new Long(0));
		int districtsCount = 0;
		
		for(ConstituencyElectionsDetailedResultVO constituencyElectionsDetailedResult:constituencyElectionsDetailedResultVO){
			if(districtIds.contains(constituencyElectionsDetailedResult.getDistrictId())){
				districtsCount++;
			}
			else{
				districtWiseConstituencyElectionResults = new DistrictWiseConstituencyElectionResultsVO();
				districtIds.add(constituencyElectionsDetailedResult.getDistrictId());
				districtWiseConstituencyElectionResults = getConstituencyResultsForADistrict(constituencyElectionsDetailedResultVO,constituencyElectionsDetailedResult.getDistrictId());
				if(districtWiseConstituencyElectionResults != null)
					districtWiseConstituencyElectionResultsVO.add(districtWiseConstituencyElectionResults);
			}
		}
		if(districtWiseConstituencyElectionResultsVO != null && districtWiseConstituencyElectionResultsVO.size() > 0){
			Collections.sort(districtWiseConstituencyElectionResultsVO, new VotesPercentageComparator());
			return districtWiseConstituencyElectionResultsVO;
		}
			
	
	return null;
	}
	
	public DistrictWiseConstituencyElectionResultsVO getConstituencyResultsForADistrict(List<ConstituencyElectionsDetailedResultVO> constituencyElectionsDetailedResultVO,Long districtId){
		
		DistrictWiseConstituencyElectionResultsVO districtWiseConstituencyElectionResults = new DistrictWiseConstituencyElectionResultsVO();
		List<ConstituencyElectionsDetailedResultVO> constituencyElectionsDetailedResults = new ArrayList<ConstituencyElectionsDetailedResultVO>();
		int count = 0;
		String districtName="";
		Long partyId = new Long(0);
		Long newPartyId = new Long(0);
		String partyName = "";
		String newPartyName = "";
		Double percentSumForPartyForYearOne = new Double(0); 
		Double percentSumForNewPartyForYearOne = new Double(0);
		Double percentSumForPartyForYearTwo = new Double(0); 
		Double votesPercentageDiffDouble = new Double(0);
		String year = "";
		String prevYear = "";
		Double totalVotesEarnedForPartyForYearOne =new Double(0);
		Double totalVotesEarnedForNewPartyForYearOne =new Double(0);
		Double totalVotesEarnedForPartyForYearTwo =new Double(0);
		Double totalValidVotesForPartyForYearOne = new Double(0);
		Double totalValidVotesForNewPartyForYearOne = new Double(0);
		Double totalValidVotesForPartyForYearTwo = new Double(0);
				
	if(constituencyElectionsDetailedResultVO != null && constituencyElectionsDetailedResultVO.get(0).getConstituencyElectionResultsForYearOne().getElectionResultForNewParty() != null){
		
		for(ConstituencyElectionsDetailedResultVO constituencyElectionsDetailedResult:constituencyElectionsDetailedResultVO){
			
			if(constituencyElectionsDetailedResult.getDistrictId().equals(districtId)){
						
					
				partyId = constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearOne().getElectionResultForParty().getPartyId();
				newPartyId = constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearOne().getElectionResultForNewParty().getPartyId();
				
				partyName = constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearOne().getElectionResultForParty().getPartyName();
				newPartyName = constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearOne().getElectionResultForNewParty().getPartyName();
				
				Double votesEarnedForPartyforYearOne = Double.valueOf(constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearOne().getElectionResultForParty().getVotesEarned());
				totalVotesEarnedForPartyForYearOne = totalVotesEarnedForPartyForYearOne + votesEarnedForPartyforYearOne;
				
				Double votesEarnedForNewPartyforYearOne = Double.valueOf(constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearOne().getElectionResultForNewParty().getVotesEarned());
				totalVotesEarnedForNewPartyForYearOne = totalVotesEarnedForNewPartyForYearOne + votesEarnedForNewPartyforYearOne;
				
				Double votesEarnedForPartyforYearTwo = Double.valueOf(constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearTwo().getElectionResultForParty().getVotesEarned());
				totalVotesEarnedForPartyForYearTwo = totalVotesEarnedForPartyForYearTwo + votesEarnedForPartyforYearTwo;
				
				Double validVotesForPartyForYearOne = Double.valueOf(constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearOne().getElectionResultForParty().getValidVotes());
				totalValidVotesForPartyForYearOne = totalValidVotesForPartyForYearOne + validVotesForPartyForYearOne;
				
				Double validVotesForNewPartyForYearOne = Double.valueOf(constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearOne().getElectionResultForNewParty().getValidVotes());
				totalValidVotesForNewPartyForYearOne  =  totalValidVotesForNewPartyForYearOne + validVotesForNewPartyForYearOne;
				
				Double validVotesForPartyForYearTwo = Double.valueOf(constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearTwo().getElectionResultForParty().getValidVotes());
				totalValidVotesForPartyForYearTwo = totalValidVotesForPartyForYearTwo + validVotesForPartyForYearTwo;
				
				year = constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearOne().getElectionResultForParty().getYear();
				prevYear = constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearTwo().getElectionResultForParty().getYear();
				
				constituencyElectionsDetailedResults.add(constituencyElectionsDetailedResult);
				districtName = constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearOne().getElectionResultForParty().getDistrictName();
				count++;
			}
		}
		if(count > 0 && constituencyElectionsDetailedResults.size() > 0){
			districtWiseConstituencyElectionResults.setDistrictId(districtId);
			districtWiseConstituencyElectionResults.setDistrictName(districtName);
			
			percentSumForPartyForYearOne = getPercentageOfVotes(totalVotesEarnedForPartyForYearOne,totalValidVotesForPartyForYearOne);
			percentSumForNewPartyForYearOne = getPercentageOfVotes(totalVotesEarnedForNewPartyForYearOne,totalValidVotesForNewPartyForYearOne);
			percentSumForPartyForYearTwo = getPercentageOfVotes(totalVotesEarnedForPartyForYearTwo,totalValidVotesForPartyForYearTwo);
			votesPercentageDiffDouble = percentSumForPartyForYearOne - percentSumForPartyForYearTwo;
		
			Double percentDiff = getRoundedDoubleValue(votesPercentageDiffDouble);
			Collections.sort(constituencyElectionsDetailedResults, new ConstituencyElectionVotesComparator());
			districtWiseConstituencyElectionResults.setConstituencyElectionsDetailedResults(constituencyElectionsDetailedResults);
			districtWiseConstituencyElectionResults.setDistrictVotesPercentageDiff(percentDiff.toString());
			districtWiseConstituencyElectionResults.setDistrictWiseVotesPercntDiff(percentDiff);
			districtWiseConstituencyElectionResults.setPartyVotesPercentage(getRoundedDoubleValue(percentSumForPartyForYearOne).toString());
			districtWiseConstituencyElectionResults.setNewPartyVotesPercentage(getRoundedDoubleValue(percentSumForNewPartyForYearOne).toString());
			districtWiseConstituencyElectionResults.setPartyVotesPercentForYear2(getRoundedDoubleValue(percentSumForPartyForYearTwo).toString());
			districtWiseConstituencyElectionResults.setYear(year);
			districtWiseConstituencyElectionResults.setPreviousYear(prevYear);
			districtWiseConstituencyElectionResults.setPartyId(partyId);
			districtWiseConstituencyElectionResults.setNewPartyId(newPartyId);
			districtWiseConstituencyElectionResults.setPartyName(partyName);
			districtWiseConstituencyElectionResults.setNewPartyName(newPartyName);
			districtWiseConstituencyElectionResults.setTotalVotesEarnedInDistrictForPartyInYear1(totalVotesEarnedForPartyForYearOne);
			districtWiseConstituencyElectionResults.setTotalValidVotesInDistrictForPartyInYear1(totalValidVotesForPartyForYearOne);
			districtWiseConstituencyElectionResults.setTotalVotesEarnedInDistrictForNewPartyInYear1(totalVotesEarnedForNewPartyForYearOne);
			districtWiseConstituencyElectionResults.setTotalValidVotesInDistrictForNewPartyInYear1(totalValidVotesForNewPartyForYearOne);
			districtWiseConstituencyElectionResults.setTotalVotesEarnedInDistrictForPartyInYear2(totalVotesEarnedForPartyForYearTwo);
			districtWiseConstituencyElectionResults.setTotalValidVotesInDistrictForPartyInYear2(totalValidVotesForPartyForYearTwo);
			
		return districtWiseConstituencyElectionResults;
		}
	}
	return null;
	}
	
	//Returns a String value for a Double value with Rounded Decimal
	public Double getRoundedDoubleValue(Double value){
			 
		try{
			BigDecimal bd = new BigDecimal(value);
			BigDecimal bd1 = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
			return bd1.doubleValue();
		}
		catch(Exception e){
			if(logger.isDebugEnabled())
				logger.debug("exception -->" + e);
		}
	return null;
	}
	
	//Returns percentage Of Votes
	public Double getPercentageOfVotes(Double totalvotesEarned,Double totalValidVotes){
		
		try{
			Double percentage = (totalvotesEarned/totalValidVotes)*100;
			return percentage;
		}
		catch(Exception e){
			if(logger.isDebugEnabled())
				logger.debug("exception -->" + e);
		}
	return null;
	}
*/}
