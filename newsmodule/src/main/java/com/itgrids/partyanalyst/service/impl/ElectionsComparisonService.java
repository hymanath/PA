/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 23, 2009
 */
package com.itgrids.partyanalyst.service.impl;

import com.itgrids.partyanalyst.service.IElectionsComparisonService;

public class ElectionsComparisonService implements IElectionsComparisonService {/*

	
	private IStateDAO stateDAO;
	private IPartyDAO partyDAO;
	private IElectionDAO electionDAO;
	private ICandidateResultDAO candidateResultDAO;
	private IElectionScopeDAO electionScopeDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private IStaticDataService staticDataService;
	
	private static Logger logger = Logger.getLogger(ElectionsComparisonService.class);
	

	public IElectionScopeDAO getElectionScopeDAO() {
		return electionScopeDAO;
	}


	public void setElectionScopeDAO(IElectionScopeDAO electionScopeDAO) {
		this.electionScopeDAO = electionScopeDAO;
	}


	public void setCandidateResultDAO(ICandidateResultDAO candidateResultDAO) {
		this.candidateResultDAO = candidateResultDAO;
	}
	
	
	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}
	

	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}
	
	 public IStaticDataService getStaticDataService() {
		return staticDataService;
	}


	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	
	public IConstituencyElectionDAO getConstituencyElectionDAO() {
		return constituencyElectionDAO;
	}


	public void setConstituencyElectionDAO(IConstituencyElectionDAO constituencyElectionDAO) {
		this.constituencyElectionDAO = constituencyElectionDAO;
	}


	public Long getElectionScopeId(Long electionTypeId,Long stateId){
		 
		 List<ElectionScope> electionScope = electionScopeDAO.findByPropertyElectionTypeIdandStateId(electionTypeId, stateId);
		 if(electionScope != null){
			 
		 return electionScope.get(0).getElectionScopeId(); 
		 }
		 
	 return null;
	 }

	public List<SelectOptionVO> getStatesList() {
		List<SelectOptionVO> selectOptionList = new ArrayList<SelectOptionVO>();
		SelectOptionVO selectOption = null;
		List<State> states = null;
		 states = stateDAO.getAll();
		 if(states != null){
			 for(State state:states){
				 selectOption = new SelectOptionVO();
				 selectOption.setId(state.getStateId());
				 selectOption.setName(state.getStateName());
				 selectOptionList.add(selectOption);
			 }
		 return selectOptionList;
		 }
		 
	return null;
	}

	public List<SelectOptionVO> getPartiesList() {
		List<SelectOptionVO> selectOptionList = new ArrayList<SelectOptionVO>();
		SelectOptionVO selectOption = null;
		List<Party> parties = null;
		 parties = partyDAO.getAll();
		 if(parties != null){
			 for(Party party:parties){
				 selectOption = new SelectOptionVO();
				 selectOption.setId(party.getPartyId());
				 selectOption.setName(party.getLongName());
				 selectOptionList.add(selectOption);
			 }
		 return selectOptionList;
		 }
	return null;
	}
	
	public List<SelectOptionVO> getYearsList(){
		List<SelectOptionVO> selectOptionList = new ArrayList<SelectOptionVO>();
		SelectOptionVO selectOption = null;
		Long i = new Long(1);
		List<String> years = new ArrayList<String>();
		years.add("");
		
		 List<Election> elections = electionDAO.getAll();
			 if(elections!=null){
				 for(Election election:elections){
					Boolean flag = false;
					if(years.contains(election.getElectionYear().trim()))
						flag = true;
					if(flag == false){
					 years.add(election.getElectionYear().trim());
					 selectOption = new SelectOptionVO();
					 selectOption.setId(i);
					 selectOption.setName(election.getElectionYear());
					 selectOptionList.add(selectOption);
					 i++;
					}
				 }
			 return selectOptionList;
			 }
		 
		 
	return null;
	}
	
	
	public boolean IsPartyParticipated(Long electionScopeId, Long partyId,String year) {
		
	    List<Long> status  = candidateResultDAO.findCandidateResultsCount(electionScopeId, partyId, year);
	    Long count = null;
	    if(status != null && status.size() >= 1){
	    	for(Long c:status){
	    		count = c;
	    	}
	    	if(count.equals(new Long(0)))
	    		return false;
	    }
	return true;
    }


    public List<ElectionComparisonResultVO> getPartyElectionResults(Long electionScopeId,List<Long> partyIds,String year){
	
	   List<ElectionComparisonResultVO> electionComparisonResultVO = new ArrayList<ElectionComparisonResultVO>();
	
	   List<CandidateResult> candidateResults = getCandidateElectionResults(electionScopeId, partyIds, year);
	
	   if(candidateResults != null){
		   List<Long> districtIds = getDistrictIds(candidateResults);
		    for(Long districtId:districtIds){
		    	logger.debug("DistrictId's -->" + districtId);
		     ElectionComparisonResultVO resultVO = getElectionResults(candidateResults,districtId);
		     electionComparisonResultVO.add(resultVO);
		    }
	        return electionComparisonResultVO;	   
	   }
	
   return null;	
   }
    
    public List<CandidateResult> getCandidateElectionResults(Long electionScopeId,List<Long> partyIds,String year){
    	
    	//List<CandidateResult> candidateResults = candidateResultDAO.findCandidateResults(electionScopeId, partyId, year);
    	List<CandidateResult> candidateResults = candidateResultDAO.findCandidateResults(electionScopeId, partyIds, year);
    	if(candidateResults != null)
    		return candidateResults;
    	else 
    		return null;
    }

   public List<Long> getDistrictIds(List<CandidateResult> candidateResults){
	
	List<Long> districtIds = new ArrayList<Long>();
	District district = null;
	int count=0;
	
	if(candidateResults!=null){
		
		    for(CandidateResult result:candidateResults){
			  district = result.getNomination().getConstituencyElection().getConstituency().getDistrict();
			  if(districtIds.contains(district.getDistrictId()))
			   count++;
			  if(count == 0)
			   districtIds.add(district.getDistrictId());
		      count = 0;
		    }
	        return districtIds;
	}
	  
   return null;
   }

   public ElectionComparisonResultVO getElectionResults(List<CandidateResult> candidateResults,Long districtId){
	
	ElectionComparisonResultVO electionComparisonResultVO = new ElectionComparisonResultVO();
	List<PartyElectionResultsVO> partyElectionResultsVO = new ArrayList<PartyElectionResultsVO>();
	List<CandidateOppositionVO> candidateOppositionVO = new ArrayList<CandidateOppositionVO>();
	District district = null;
	Candidate candidate = null;
	Constituency constituency = null;
	ConstituencyElectionResult constiElectionResult = null;
	Party party = null;
	
	if(candidateResults != null){
		
		 
		for(CandidateResult candidateResult:candidateResults){
			
			district = candidateResult.getNomination().getConstituencyElection().getConstituency().getDistrict();
					
			if(district.getDistrictId().equals(districtId)){
				
				electionComparisonResultVO.setDistrictId(district.getDistrictId());
				electionComparisonResultVO.setDistrictName(district.getDistrictName());
				electionComparisonResultVO.setStateId(district.getState().getStateId());
				candidate = candidateResult.getNomination().getCandidate();
				constituency = candidateResult.getNomination().getConstituencyElection().getConstituency();
				constiElectionResult = candidateResult.getNomination().getConstituencyElection().getConstituencyElectionResult();
				party = candidateResult.getNomination().getParty();
				
				PartyElectionResultsVO  partyResults = new PartyElectionResultsVO();
				partyResults.setCandidateId(candidate.getCandidateId());
				partyResults.setCandidateName(candidate.getLastname());
				partyResults.setConstituencyId(constituency.getConstituencyId());
				partyResults.setConstituencyName(constituency.getName());
				partyResults.setPartyId(party.getPartyId());
				partyResults.setPartyName(party.getLongName());
				partyResults.setRank(candidateResult.getRank());
				partyResults.setVotesEarned(candidateResult.getVotesEarned().longValue());
				partyResults.setVotesPercentage(candidateResult.getVotesPercengate());
				partyResults.setTotalElectors(constiElectionResult.getTotalVotes());
			partyElectionResultsVO.add(partyResults);
			}
		}
		
		electionComparisonResultVO.setPartyElectionResultsVO(partyElectionResultsVO);
	return electionComparisonResultVO;
	}
   return null;
   }

   public List<Long> getAlliancePartysAsVO(Long electionTypeId,Long partyId,String year, Long stateId){
	   
	   logger.debug("Entered Into getAlliances method");
	   
	   List<SelectOptionVO> allianceParties = staticDataService.getAlliancePartiesAsVO(year, electionTypeId, partyId,stateId);
	   List<Long> partyIds = null;
			
		if(allianceParties != null && allianceParties.size() > 0){
			partyIds = new ArrayList<Long>();
			 	logger.debug("Partyid's" + partyIds);
			for(SelectOptionVO partys:allianceParties){
				partyIds.add(partys.getId());
				logger.debug("Partyid's alliances" + partys.getId());
			}
		}
		else{
			partyIds = new ArrayList<Long>();
			partyIds.add(partyId);	
			logger.debug("Partyid's at else Block" + partyIds);
		}
	   
   return partyIds;
   }
   
   
   public int getConstiNotParticipatedCount(Long electionScopeId,List<Long> partyIds,String year){
	   
	   List<ConstituencyElection> constituencyElection = constituencyElectionDAO.findByElectionScopeAndYear(electionScopeId, year);
	   int count = 0;
	   Set<Nomination> nominations = null;
	   Boolean flag;
	   logger.debug("constituencyElection Count" + constituencyElection.size());
	     for(ConstituencyElection constiElection:constituencyElection){
		   nominations = constiElection.getNominations();
		   flag = false;
		   for(Nomination nominatn:nominations){
			   Party party = nominatn.getParty();
			   if(partyIds.contains(party.getPartyId()))
				   flag = true;
		   }
		   if(flag == false){
			   count++;
		       
		   }
	     }
	   
   return count;
   }
   public ElectionsComparisonVO getPartyElectionComparedResults(Long electionScopeId,Long electionTypeId,Long partyId,String firstYear,String secondYear,Boolean includeAlliance){
	
	List<ElectionComparisonResultVO> resultForFirstYear = null;
	List<ElectionComparisonResultVO> resultForSecondYear = null;
	List<ElectionComparisonResultVO> resultsForVotesGained = new ArrayList<ElectionComparisonResultVO>();
	List<ElectionComparisonResultVO> resultsForVotesLost = new ArrayList<ElectionComparisonResultVO>();
	List<ElectionComparisonResultVO> constituenciesNotConsidered = new ArrayList<ElectionComparisonResultVO>();
	List<ElectionComparisonResultVO> constituenciesNotConsideredForYearOne = new ArrayList<ElectionComparisonResultVO>();
	List<ElectionComparisonResultVO> constituenciesNotConsideredForYearTwo = new ArrayList<ElectionComparisonResultVO>();
	
	List<PartyElectionResultsVO> constituenciesNotConsideredForFirstYear = new ArrayList<PartyElectionResultsVO>();
	List<PartyElectionResultsVO> constituenciesNotConsideredForSecondYear = new ArrayList<PartyElectionResultsVO>();
	
	ElectionsComparisonVO electionsComparisionVO = new ElectionsComparisonVO();
	
	List<Long> partyIdsforYearOne = new ArrayList<Long>();
	List<Long> partyIdsforYearTwo = new ArrayList<Long>();
	
	ElectionScope electionScope = electionScopeDAO.get(electionScopeId);
	Long stateId = 0L;
	
	if(!electionScope.getElectionType().getElectionType().equals(IConstants.PARLIAMENT_ELECTION_TYPE))
		stateId = electionScope.getState().getStateId();
	
	if(includeAlliance != null && includeAlliance == true){
		
		partyIdsforYearOne = getAlliancePartysAsVO(electionTypeId,partyId,firstYear,stateId);
		partyIdsforYearTwo = getAlliancePartysAsVO(electionTypeId,partyId,secondYear,stateId);
	}
	else if(includeAlliance != null && includeAlliance == false){
		partyIdsforYearOne.add(partyId);
		partyIdsforYearTwo.add(partyId);
	}
	
	resultForFirstYear = getPartyElectionResults(electionScopeId,partyIdsforYearOne,firstYear);
	resultForSecondYear = getPartyElectionResults(electionScopeId,partyIdsforYearTwo,secondYear);
	
	int constiNotParticipatedForYearOne = getConstiNotParticipatedCount(electionScopeId,partyIdsforYearOne,firstYear);
	int constiNotParticipatedForYearTwo = getConstiNotParticipatedCount(electionScopeId,partyIdsforYearTwo,secondYear);
	
	logger.debug("constiNotPartiForYearOne -->" + constiNotParticipatedForYearOne);
	logger.debug("constiNotPartiForYearTwo -->" + constiNotParticipatedForYearTwo);
	
	//constituenciesNotConsideredForFirstYear = getDetailsForConstituenciesNotConsidered(electionScopeId,partyIdsforYearOne,firstYear);
	//constituenciesNotConsideredForSecondYear = getDetailsForConstituenciesNotConsidered(electionScopeId,partyIdsforYearTwo,secondYear);
	
	 if(resultForFirstYear != null && resultForSecondYear != null){
		 
		 int votesGainedCount = 0;
		 int votesLostCount = 0;
		 int count = 0;
		 int countForConstiNotConsiForYearOne = 0;
		 int countForConstiNotConsiForYearTwo = 0;
		 int seatsWonWhenVotesGainedForFirstYear = 0;
		 int seatsWonWhenVotesGainedForSecondYear = 0;
		 int seatsLostWhenVotesGainedForFirstYear = 0;
		 int seatsLostWhenVotesGainedForSecondYear = 0;
		 int seatsWonWhenVotesLostForFirstYear = 0;
		 int seatsWonWhenVotesLostForSecondYear = 0;
		 int seatsLostWhenVotesLostForFirstYear = 0;
		 int seatsLostWhenVotesLostForSecondYear = 0;
		 int constiNotConsideredForYearOneSeatsWon = 0;
		 int constiNotConsideredForYearOneSeatsLost = 0;
		 int constiNotConsideredForYearTwoSeatsWon = 0;
		 int constiNotConsideredForYearTwoSeatsLost = 0;
		 String partyName = null;
		 electionsComparisionVO.setFirstYear(firstYear);
		 electionsComparisionVO.setSecondYear(secondYear);
		 		 
		 for(ElectionComparisonResultVO resultFirst:resultForFirstYear){
			 Boolean flags = false;
			 List<Long> constituencyIds = new ArrayList<Long>();
			 constituencyIds.add(new Long(0));
			 
			  for(ElectionComparisonResultVO resultSecond:resultForSecondYear){
				 
				 if(resultSecond.getDistrictId().equals(resultFirst.getDistrictId())){
					 
					 flags = true;
					 List<PartyElectionResultsVO> partyElectionResultsForFirst = new ArrayList<PartyElectionResultsVO>();
					 List<PartyElectionResultsVO> partyElectionResultsForSecond = new ArrayList<PartyElectionResultsVO>();
					 List<PartyElectionResultsVO> partyElectionResultsNotConsidered = new ArrayList<PartyElectionResultsVO>();
					 List<PartyElectionResultsVO> partyElectionResultsNotConsideredForYearOne = new ArrayList<PartyElectionResultsVO>();
					 List<PartyElectionResultsVO> partyElectionResultsNotConsideredForYearTwo = new ArrayList<PartyElectionResultsVO>();
					 List<PartyElectionResultsVO> partyElectionResultsNotConsideredForSecond = new ArrayList<PartyElectionResultsVO>();
					 int statusForGained = 0;
					 int statusForLost = 0;
					 int statusForNotConsidered = 0;
					 					 
					 for(PartyElectionResultsVO firstPartyResults:resultFirst.getPartyElectionResultsVO()){
						 Boolean flag = false;
						 
						 for(PartyElectionResultsVO secondPartyResults:resultSecond.getPartyElectionResultsVO()){
							 
							if(firstPartyResults.getConstituencyId().equals(secondPartyResults.getConstituencyId()) && Double.parseDouble(firstPartyResults.getVotesPercentage()) < Double.parseDouble(secondPartyResults.getVotesPercentage())){
								 
								if(constituencyIds.contains(firstPartyResults.getConstituencyId()))
									break;
								constituencyIds.add(firstPartyResults.getConstituencyId());								
								if(firstPartyResults.getPartyId().equals(partyId))
									partyName = firstPartyResults.getPartyName();
								if(secondPartyResults.getPartyId().equals(partyId))
							    	 partyName = secondPartyResults.getPartyName();
							    PartyElectionResultsVO partyResultVO = new PartyElectionResultsVO();
							    partyResultVO = getResult(firstPartyResults,secondPartyResults);
							    if(partyResultVO.getRank() != null && partyResultVO.getRank().equals(new Long(1)))
							    	seatsWonWhenVotesGainedForFirstYear++;
							    else if(partyResultVO.getRank() != null)
							    	seatsLostWhenVotesGainedForFirstYear++;
							    if(partyResultVO.getRankBySecond() != null && partyResultVO.getRankBySecond().equals(new Long(1)))
							    	seatsWonWhenVotesGainedForSecondYear++;
							    else if(partyResultVO.getRankBySecond() != null)
							    	seatsLostWhenVotesGainedForSecondYear++;
							    Double votesDiff = Double.parseDouble(partyResultVO.getVotesPercentageBySecond()) - Double.parseDouble(partyResultVO.getVotesPercentage());
							    partyResultVO.setVoteDiff(votesDiff);
							    partyResultVO.setVotesDiff(getRoundedDoubleValue(votesDiff).toString());
							    Double electorsPercentDiff = getElectorsPercentage(firstPartyResults.getTotalElectors(),secondPartyResults.getTotalElectors());
							    partyResultVO.setElectorsPercentageDiff(getRoundedDoubleValue(electorsPercentDiff).toString());
							    partyElectionResultsForFirst.add(partyResultVO);
							    partyElectionResultsNotConsideredForSecond.add(secondPartyResults);
							    flag = true;
							    statusForGained = 1;
							    votesGainedCount++;
							    break;
							 }
							 else if(firstPartyResults.getConstituencyId().equals(secondPartyResults.getConstituencyId()) && Double.parseDouble(firstPartyResults.getVotesPercentage()) > Double.parseDouble(secondPartyResults.getVotesPercentage())){
								 
								 if(constituencyIds.contains(firstPartyResults.getConstituencyId()))
										break;
							     constituencyIds.add(firstPartyResults.getConstituencyId());
							     if(firstPartyResults.getPartyId().equals(partyId))
							    	 partyName = firstPartyResults.getPartyName();
							     if(secondPartyResults.getPartyId().equals(partyId))
							    	 partyName = secondPartyResults.getPartyName();
								 PartyElectionResultsVO partyResultVO = new PartyElectionResultsVO();
								 partyResultVO = getResult(firstPartyResults,secondPartyResults);
								 if(partyResultVO.getRank() != null && partyResultVO.getRank().equals(new Long(1)))
									 seatsWonWhenVotesLostForFirstYear++;
								 else if(partyResultVO.getRank() != null)
									 seatsLostWhenVotesLostForFirstYear++;
								 if(partyResultVO.getRankBySecond() != null && partyResultVO.getRankBySecond().equals(new Long(1)))
									 seatsWonWhenVotesLostForSecondYear++;
								 else if(partyResultVO.getRankBySecond() != null)
									 seatsLostWhenVotesLostForSecondYear++;
								 Double votesDiff = Double.parseDouble(partyResultVO.getVotesPercentage()) - Double.parseDouble(partyResultVO.getVotesPercentageBySecond());
								 partyResultVO.setVoteDiff(votesDiff);
								 partyResultVO.setVotesDiff(getRoundedDoubleValue(votesDiff).toString());
								 Double electorsPercentDiff = getElectorsPercentage(firstPartyResults.getTotalElectors(),secondPartyResults.getTotalElectors());
								 partyResultVO.setElectorsPercentageDiff(getRoundedDoubleValue(electorsPercentDiff).toString());
								 partyElectionResultsForSecond.add(partyResultVO);
								 partyElectionResultsNotConsideredForSecond.add(secondPartyResults);
								 flag = true;
								 statusForLost = 1;
								 votesLostCount++;
								 break;
							 }
												 
						 }
						 if(flag == false){
							 if(constituencyIds.contains(firstPartyResults.getConstituencyId()))
								logger.debug("Exists Already");
							 else{
						     constituencyIds.add(firstPartyResults.getConstituencyId());
							 PartyElectionResultsVO partyResultVO = new PartyElectionResultsVO();
							 partyResultVO = getResults(firstPartyResults);
							 if(partyResultVO.getRank()!= null && partyResultVO.getRank().equals(new Long(1)))
								 constiNotConsideredForYearOneSeatsWon++;
							 else if(partyResultVO.getRank()!= null)
								 constiNotConsideredForYearOneSeatsLost++;
							 partyElectionResultsNotConsideredForYearOne.add(partyResultVO);
							 flag = true;
							 statusForNotConsidered = 1;
							 count++;
							 }
						 }
						 
						 //partyName = firstPartyResults.getPartyName();
	    				 
					 }
					 
					 if(statusForGained == 1){
						 ElectionComparisonResultVO resultVO = new ElectionComparisonResultVO();
						 ElectionComparisonResultVO resultVOforSecond = new ElectionComparisonResultVO();
						 resultVO.setDistrictId(resultSecond.getDistrictId());
						 resultVO.setDistrictName(resultSecond.getDistrictName());
						 resultVO.setStateId(resultSecond.getStateId());
						 resultVOforSecond.setDistrictId(resultFirst.getDistrictId());
						 resultVOforSecond.setDistrictName(resultFirst.getDistrictName());
						 resultVOforSecond.setStateId(resultFirst.getStateId());
						 
						 logger.debug("Gained -- " + partyElectionResultsForFirst.size());
						 if(partyElectionResultsForFirst != null && partyElectionResultsForFirst.size() > 1)
						  Collections.sort(partyElectionResultsForFirst,new VotesDifferenceComparator());
						 resultVO.setPartyElectionResultsVO(partyElectionResultsForFirst);
						 resultVO.setConstituenciesCount(partyElectionResultsForFirst.size());
						 resultsForVotesGained.add(resultVO);
					 }
					 if(statusForLost == 1){
						 ElectionComparisonResultVO resultVO = new ElectionComparisonResultVO();
						 ElectionComparisonResultVO resultVOforSecond = new ElectionComparisonResultVO();
						 resultVO.setDistrictId(resultSecond.getDistrictId());
						 resultVO.setDistrictName(resultSecond.getDistrictName());
						 resultVO.setStateId(resultSecond.getStateId());
						 resultVOforSecond.setDistrictId(resultFirst.getDistrictId());
						 resultVOforSecond.setDistrictName(resultFirst.getDistrictName());
						 resultVOforSecond.setStateId(resultFirst.getStateId());
						
						 logger.debug("Lost -- " + partyElectionResultsForSecond.size());
						 if(partyElectionResultsForSecond != null && partyElectionResultsForSecond.size() > 1)
							 Collections.sort(partyElectionResultsForSecond,new VotesDifferenceComparator());
						 resultVOforSecond.setPartyElectionResultsVO(partyElectionResultsForSecond);
						 resultVOforSecond.setConstituenciesCount(partyElectionResultsForSecond.size());
						 resultsForVotesLost.add(resultVOforSecond);
					 }
					 if(statusForNotConsidered == 1){
						 ElectionComparisonResultVO resultVOforConstituenciesNotConsi = new ElectionComparisonResultVO();
						 resultVOforConstituenciesNotConsi.setDistrictId(resultFirst.getDistrictId());
						 resultVOforConstituenciesNotConsi.setDistrictName(resultFirst.getDistrictName());
						 resultVOforConstituenciesNotConsi.setStateId(resultFirst.getStateId());
						 logger.debug("NotConsidered One--" + partyElectionResultsNotConsidered.size());
						 resultVOforConstituenciesNotConsi.setPartyElectionResultsVO(partyElectionResultsNotConsideredForYearOne);
						 resultVOforConstituenciesNotConsi.setConstituenciesCount(partyElectionResultsNotConsideredForYearOne.size());
						 constituenciesNotConsidered.add(resultVOforConstituenciesNotConsi);
						 constituenciesNotConsideredForYearOne.add(resultVOforConstituenciesNotConsi);
						 countForConstiNotConsiForYearOne = countForConstiNotConsiForYearOne + partyElectionResultsNotConsideredForYearOne.size();
						 
						 ElectionComparisonResultVO resultVOforConstituenciesNotConsiFoYearTwo = new ElectionComparisonResultVO();
						 resultVOforConstituenciesNotConsiFoYearTwo.setDistrictId(resultSecond.getDistrictId());
						 resultVOforConstituenciesNotConsiFoYearTwo.setDistrictName(resultSecond.getDistrictName());
						 resultVOforConstituenciesNotConsiFoYearTwo.setStateId(resultSecond.getStateId());
						 logger.debug("NotConsidered Two -- " + partyElectionResultsNotConsidered.size());
						 for(PartyElectionResultsVO result:resultSecond.getPartyElectionResultsVO()){
							 int flag = 0;
							 for(PartyElectionResultsVO resultVO:partyElectionResultsNotConsideredForSecond){
							  if(result.getConstituencyId().equals(resultVO.getConstituencyId()) || constituencyIds.contains(result.getConstituencyId())){
								flag = 1;
							  break;
							  }
							 }
							 if(flag == 0){
								 if(result.getRank() != null && result.getRank().equals(new Long(1)))
									 constiNotConsideredForYearTwoSeatsWon++;
								 else
									 constiNotConsideredForYearTwoSeatsLost++;
								 partyElectionResultsNotConsideredForYearTwo.add(result);
							 }
						 }
						 resultVOforConstituenciesNotConsiFoYearTwo.setPartyElectionResultsVO(partyElectionResultsNotConsideredForYearTwo);
						 resultVOforConstituenciesNotConsiFoYearTwo.setConstituenciesCount(partyElectionResultsNotConsideredForYearTwo.size());
						 constituenciesNotConsideredForYearTwo.add(resultVOforConstituenciesNotConsiFoYearTwo);
						 countForConstiNotConsiForYearTwo = countForConstiNotConsiForYearTwo + partyElectionResultsNotConsideredForYearTwo.size();
					 }
				 break;
				 }
			}
			  if(flags == false)
				  logger.debug("flag is false" + resultFirst.getDistrictName());
		 }
		 electionsComparisionVO.setPartyName(partyName);
		 electionsComparisionVO.setVotesGainedCount(votesGainedCount);
		 electionsComparisionVO.setVotesLostCount(votesLostCount);
		 electionsComparisionVO.setCount(count);
		 electionsComparisionVO.setVotesGained(resultsForVotesGained);
		 electionsComparisionVO.setVotesLost(resultsForVotesLost);
		 electionsComparisionVO.setConstituenciesNotConsidered(constituenciesNotConsidered);
		 electionsComparisionVO.setSeatsWonInFirstYearForVotesGained(seatsWonWhenVotesGainedForFirstYear);
		 electionsComparisionVO.setSeatsWonInSecondYearForVotesGained(seatsWonWhenVotesGainedForSecondYear);
		 electionsComparisionVO.setSeatsLostInFirstYearForVotesGained(seatsLostWhenVotesGainedForFirstYear);
		 electionsComparisionVO.setSeatsLostInSecondYearForVotesGained(seatsLostWhenVotesGainedForSecondYear);
		 electionsComparisionVO.setSeatsWonInFirstYearForVotesLost(seatsWonWhenVotesLostForFirstYear);
		 electionsComparisionVO.setSeatsWonInSecondYearForVotesLost(seatsWonWhenVotesLostForSecondYear);
		 electionsComparisionVO.setSeatsLostInFirstYearForVotesLost(seatsLostWhenVotesLostForFirstYear);
		 electionsComparisionVO.setSeatsLostInSecondYearForVotesLost(seatsLostWhenVotesLostForSecondYear);
		 electionsComparisionVO.setConstituenciesNotConsideredForYearOne(constituenciesNotConsideredForYearOne);
		 electionsComparisionVO.setConstituenciesNotConsideredForYearTwo(constituenciesNotConsideredForYearTwo);
		 electionsComparisionVO.setConstiNotConstiCountForYearOne(countForConstiNotConsiForYearOne);
		 electionsComparisionVO.setConstiNotConstiCountForYearTwo(countForConstiNotConsiForYearTwo);
		 electionsComparisionVO.setConstiNotConsideredForYearOneSeatsWon(constiNotConsideredForYearOneSeatsWon);
		 electionsComparisionVO.setConstiNotConsideredForYearOneSeatsLost(constiNotConsideredForYearOneSeatsLost);
		 electionsComparisionVO.setConstiNotConsideredForYearTwoSeatsWon(constiNotConsideredForYearTwoSeatsWon);
		 electionsComparisionVO.setConstiNotConsideredForYearTwoSeatsLost(constiNotConsideredForYearTwoSeatsLost);
		 
		 if(constituenciesNotConsideredForFirstYear!= null && constituenciesNotConsideredForFirstYear.size() > 0)
			 electionsComparisionVO.setConstituenciesNotConsideredInFirstYear(constituenciesNotConsideredForFirstYear);
		 if(constituenciesNotConsideredForSecondYear != null && constituenciesNotConsideredForSecondYear.size() >0)
			 electionsComparisionVO.setConstituenciesNotConsideredInSecondYear(constituenciesNotConsideredForSecondYear);
		 electionsComparisionVO.setConstiNotParticipatedForYearOne(constiNotParticipatedForYearOne);
		 electionsComparisionVO.setConstiNotParticipatedForYearTwo(constiNotParticipatedForYearTwo);
		 
	 return electionsComparisionVO;
	 }
	
   return null;
   }

   public PartyElectionResultsVO getResult(PartyElectionResultsVO firstPartyResults,PartyElectionResultsVO secondPartyResults){

	PartyElectionResultsVO partyResultVO = new PartyElectionResultsVO();
	partyResultVO.setCandidateId(firstPartyResults.getCandidateId());
	partyResultVO.setCandidateName(firstPartyResults.getCandidateName());
	partyResultVO.setConstituencyId(firstPartyResults.getConstituencyId());
	partyResultVO.setConstituencyName(firstPartyResults.getConstituencyName());
	partyResultVO.setRank(firstPartyResults.getRank());
	partyResultVO.setVotesEarned(firstPartyResults.getVotesEarned());
	partyResultVO.setVotesPercentage(firstPartyResults.getVotesPercentage());
	partyResultVO.setTotalElectors(firstPartyResults.getTotalElectors());
	partyResultVO.setSecondCandidateName(secondPartyResults.getCandidateName());
	partyResultVO.setRankBySecond(secondPartyResults.getRank());
	partyResultVO.setVotesEarnedBySecond(secondPartyResults.getVotesEarned());
	partyResultVO.setVotesPercentageBySecond(secondPartyResults.getVotesPercentage());
	partyResultVO.setTotalElectorsForSecond(secondPartyResults.getTotalElectors());
	
   return partyResultVO;
   }
   
   public PartyElectionResultsVO getResults(PartyElectionResultsVO firstPartyResults){

		PartyElectionResultsVO partyResultVO = new PartyElectionResultsVO();
		partyResultVO.setCandidateId(firstPartyResults.getCandidateId());
		partyResultVO.setCandidateName(firstPartyResults.getCandidateName());
		partyResultVO.setConstituencyId(firstPartyResults.getConstituencyId());
		partyResultVO.setConstituencyName(firstPartyResults.getConstituencyName());
		partyResultVO.setRank(firstPartyResults.getRank());
		partyResultVO.setVotesEarned(firstPartyResults.getVotesEarned());
		partyResultVO.setVotesPercentage(firstPartyResults.getVotesPercentage());
		
	return partyResultVO;
	}
   
   
   public PartyResultsPercentageVO getPartyResultsPercentage(Long electionScopeId,Long electionTypeId,Long partyId,String year,Boolean includeAlliance){

	   List<Long> partyIds = new ArrayList<Long>();
	   
	   Long stateId = 0L;
	   
	   ElectionScope electionScope = electionScopeDAO.get(electionScopeId);
	   if(!electionScope.getElectionType().getElectionType().equals(IConstants.PARLIAMENT_ELECTION_TYPE))
		   stateId = electionScope.getState().getStateId();
	   
	   if(includeAlliance != null && includeAlliance == true){
		   partyIds = getAlliancePartysAsVO(electionTypeId,partyId,year,stateId);
	   }
	   else
		   partyIds.add(partyId);
	   
	   List<CandidateResult> candidateResults = getCandidateElectionResults(electionScopeId,partyIds,year);
	   
	   if(candidateResults != null){
		   PartyResultsPercentageVO resultsPercentage = new PartyResultsPercentageVO();
		   Double validVotes = new Double(0);
		   Double votesEarned = new Double(0);
		   Long totalSeatsWon = new Long(0);
		   
		   for(CandidateResult result:candidateResults){
			   ConstituencyElectionResult constituencyElectionResult = result.getNomination().getConstituencyElection().getConstituencyElectionResult();
			   if(result.getRank().equals(new Long(1))){
				   totalSeatsWon++;
			   }
			   
			   votesEarned = votesEarned + result.getVotesEarned();
			   validVotes = validVotes + constituencyElectionResult.getValidVotes();
		   }
		   
		   logger.debug("VotesEarned --" + votesEarned);
		   logger.debug("ValidVotes --" + validVotes);
		   
		   String percentage = getPercentage(validVotes,votesEarned);
		   resultsPercentage.setPercentage(percentage);
		   resultsPercentage.setYear(year);
		   resultsPercentage.setSeatsWOn(totalSeatsWon);
		   
	   return resultsPercentage;
	   }
	   
   return null;
   }
   
   public String getPercentage(Double validVotes,Double votesEarned){
	   
	   Double percentage = getPercentageOfVotes(votesEarned.doubleValue(),validVotes.doubleValue());
	   percentage = getRoundedDoubleValue(percentage);
	    
   return percentage.toString();
   }
   
 //Returns a Double value for a Double value with Rounded Decimal
	public Double getRoundedDoubleValue(Double value){
			 
		try{
			BigDecimal bd = new BigDecimal(value);
			BigDecimal bd1 = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
			return bd1.doubleValue();
		}
		catch(Exception e){
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
			logger.debug("exception -->" + e);
		}
	return null;
	}

	//
	public Double getElectorsPercentage(Double electorsInPrevYear,Double electorsInPresYear){
		
		try{
			Double electorsDiff = electorsInPresYear - electorsInPrevYear;
			Double percentageOfElectors = (electorsDiff/electorsInPrevYear)*100;
			return percentageOfElectors;
			
		}
		catch(Exception e){
			logger.debug("exception -->" + e);
		}
	return null;
	}

	public List<PartyElectionResultsVO> getDetailsForConstituenciesNotConsidered(Long electionScopeId,List<Long> partyIds,String year){
		
		List<PartyElectionResultsVO> partyElectionResultsVOList = new ArrayList<PartyElectionResultsVO>();
		List<CandidateResult> candidateResults = getCandidateElectionResults(electionScopeId,partyIds,year);
		
		if(candidateResults != null)
			logger.debug("Total Participated Size-->" + candidateResults.size());
		List<ConstituencyElection> constituencyElections = constituencyElectionDAO.findByElectionScopeAndYear(electionScopeId, year);
		if(constituencyElections != null)
		   logger.debug("Total Size-->" + constituencyElections.size());
		
		for(ConstituencyElection constiElections:constituencyElections){
			Constituency constituency = constiElections.getConstituency();
			Boolean flag = false;
			for(CandidateResult candidResults:candidateResults){
				Constituency consti = candidResults.getNomination().getConstituencyElection().getConstituency();
				if(constituency.getConstituencyId().equals(consti.getConstituencyId())){
					flag = true;
				    break;
				}
			}
			if(flag == false){
				PartyElectionResultsVO partyResultsVO = new PartyElectionResultsVO();
				partyResultsVO.setConstituencyId(constituency.getConstituencyId());
				partyResultsVO.setConstituencyName(constituency.getName());
				Set<Nomination> nominations = constiElections.getNominations();
				Nomination wonCandNomination = getWonCandidateDetails(nominations);
				if(wonCandNomination != null){
					CandidateResult candResult = wonCandNomination.getCandidateResult();
					Candidate candidate = wonCandNomination.getCandidate();
					Party party = wonCandNomination.getParty();
					partyResultsVO.setCandidateId(candidate.getCandidateId());
					partyResultsVO.setCandidateName(candidate.getLastname());
					partyResultsVO.setPartyId(party.getPartyId());
					partyResultsVO.setPartyName(party.getLongName());
					partyResultsVO.setVotesEarned(candResult.getVotesEarned().longValue());
					partyResultsVO.setVotesPercentage(candResult.getVotesPercengate());
					partyElectionResultsVOList.add(partyResultsVO);
				}
				
			}
				
		}
		
	return partyElectionResultsVOList;
	}
	
	public Nomination getWonCandidateDetails(Set<Nomination> nominations){
		for(Nomination nomination:nominations){
			CandidateResult candResult = nomination.getCandidateResult();
			if(candResult.getRank().equals(new Long(1)))
				return nomination;
		}
		
	return null;
	}

*/}
