/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on March 06,2010
 */
package com.itgrids.partyanalyst.service.impl;

import com.itgrids.partyanalyst.service.IElectionTrendzService;

public class ElectionTrendzService implements IElectionTrendzService {/*
	
	private final static Logger log = Logger.getLogger(ElectionTrendzService.class);
	
	private ICensusDAO censusDAO;
	private ITehsilDAO tehsilDAO;
	private IElectionDAO electionDAO;
	private IConstituencyDAO constituencyDAO;
	private IStaticDataService staticDataService;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;

	public IElectionDAO getElectionDAO() {
		retursn electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public ICensusDAO getCensusDAO() {
		return censusDAO;
	}

	public void setCensusDAO(ICensusDAO censusDAO) {
		this.censusDAO = censusDAO;
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public ICandidateBoothResultDAO getCandidateBoothResultDAO() {
		return candidateBoothResultDAO;
	}

	public void setCandidateBoothResultDAO(
			ICandidateBoothResultDAO candidateBoothResultDAO) {
		this.candidateBoothResultDAO = candidateBoothResultDAO;
	}

	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}

	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}

	
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IElectionTrendzService#getBasicElectionInfoFromConstituencyId(java.lang.Long)
	 * Returns basic Info like electionId,year,type ... for a Particular Election.
	 
	@SuppressWarnings("unchecked")
	public ElectionBasicInfoVO getBasicElectionInfoFromConstituencyId(Long constituencyId){
		
		ElectionBasicInfoVO electionBasicInfoVO = new ElectionBasicInfoVO();
		if(constituencyId != null){
			List election = boothConstituencyElectionDAO.findBoothwiseResultsConstituency(constituencyId);
			if(election != null && election.size() > 0){
			Object[] params = (Object[])election.get(0);
			String elecYear = (String)params[0];
			Long elecId = (Long)params[1];
			Long elecTypeId = (Long)params[2];
			String electionType = (String)params[3];
			
			electionBasicInfoVO.setElectionId(elecId);
			electionBasicInfoVO.setElectionYear(elecYear);
			electionBasicInfoVO.setElectionTypeId(elecTypeId);
			electionBasicInfoVO.setElectionType(electionType);
			}
		}
		return electionBasicInfoVO;
	}
	
	
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IElectionTrendzService#getCompleteVotingTrendzForAMandal(java.lang.Long, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long)
	 * This Method gives complete mandal level voting trendz for different parties which participated in an election
	 * Returns complete details of male booths voting trendz,female booths voting trendz and mixed trendz
	 
	@SuppressWarnings("unchecked")
	public MandalCompleteElectionTrendzVO getCompleteVotingTrendzForAMandal(Long electionType, String electionYear, 
			                                                                Long stateId,Long districtId,Long countryId,
			                                                                Long mandalId,Long maleTrendz, Long femaleTrendz) {
		
		MandalCompleteElectionTrendzVO mandalCompleteVotingTrendzVO = new MandalCompleteElectionTrendzVO();
		mandalCompleteVotingTrendzVO.setMandalId(mandalId);
		
		MandalElectionTrendzVO completeVotingTrendzVO = null;
		MandalElectionTrendzVO maleVotingTrendzVO = null;
		MandalElectionTrendzVO femaleVotingTrendzVO = null;
		MandalElectionTrendzVO maleAndFemaleVotingTrendzVO = null;
		
		List<CensusVO> censusInfo = null;
		
		ResultStatus resultStatus = new ResultStatus();
		log.debug(" Entered Into getCompleteVotingTrendzForAMandal Method ..... ");
		
		Long electionId = null;
		Election election = null;
		List<Election> electionList = null;
		
		Tehsil tehsil = tehsilDAO.get(mandalId);
		if(tehsil != null){
		mandalCompleteVotingTrendzVO.setMandalName(tehsil.getTehsilName());
		mandalCompleteVotingTrendzVO.setDistrictName(tehsil.getDistrict().getDistrictName());
		mandalCompleteVotingTrendzVO.setStateName(tehsil.getDistrict().getState().getStateName());
		}
		
		if(electionType != null && electionYear != null){
			electionList = electionDAO.findByElectionTypeYearAndState(electionType, electionYear, stateId, countryId);
			if(electionList != null && electionList.size() > 0){
			election = electionList.get(0);
			electionId = election.getElectionId();
			}
		}
		
		if(electionId == null){
			resultStatus.setResultPartial(true);
			resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			mandalCompleteVotingTrendzVO.setResultStatus(resultStatus);
		}
		
		try{
		 if(electionId != null && mandalId != null){
			 
			List censusDetails = censusDAO.findCensusDetailsForAMandal(stateId, districtId, mandalId, IConstants.LATEST_CENSUS_YEAR, IConstants.TEHSIL);
			if(censusDetails != null && censusDetails.size() > 0)
			censusInfo = getCensusDetailsForAMandal(censusDetails);
			
			if(censusInfo != null)
			mandalCompleteVotingTrendzVO.setCensusInfo(censusInfo);
			
			completeVotingTrendzVO = getOverallMandalVotingTrendz(electionId,mandalId);
			maleVotingTrendzVO     = getMandalVotingTrendzForMale(electionId,mandalId,maleTrendz,femaleTrendz);
			femaleVotingTrendzVO   = getMandalVotingTrendzForFemale(electionId,mandalId,maleTrendz,femaleTrendz);
			maleAndFemaleVotingTrendzVO = getMandalVotingTrendzForMaleAndFemale(electionId,mandalId,maleTrendz,femaleTrendz);
			
			if(completeVotingTrendzVO != null)
			mandalCompleteVotingTrendzVO.setCompleteVotingTrendz(completeVotingTrendzVO);
			if(maleVotingTrendzVO != null)
			mandalCompleteVotingTrendzVO.setMaleVotingTrendz(maleVotingTrendzVO);
			if(femaleVotingTrendzVO != null)
			mandalCompleteVotingTrendzVO.setFemaleVotingTrendz(femaleVotingTrendzVO);
			if(maleAndFemaleVotingTrendzVO != null)
			mandalCompleteVotingTrendzVO.setMaleAndFemaleVotingTrendz(maleAndFemaleVotingTrendzVO);
		 }
		}catch(Exception ex){
			resultStatus.setResultPartial(true);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(ex);
			mandalCompleteVotingTrendzVO.setResultStatus(resultStatus);
		}
		
	  return mandalCompleteVotingTrendzVO;
	}
	
	@SuppressWarnings("unchecked")
	public List<CensusVO> getCensusDetailsForAMandal(List censusData){
		List<CensusVO> censusDetails = null;
		if(censusData != null){
			censusDetails = new ArrayList<CensusVO>();
			for(int i=0;i<censusData.size();i++){
				Object[] params = (Object[])censusData.get(i);
				String tru = (String)params[0];
				String level = (String)params[1];
				Integer year = (Integer)params[2];
				Long malePop = (Long)params[3];
				Long femalePop = (Long)params[4];
				Long totPop = (Long)params[5];	
				
				CensusVO censusVO = new CensusVO();
				censusVO.setTru(tru);
				censusVO.setYear(year);
				censusVO.setMalePopulation(malePop);
				censusVO.setFemalePopulation(femalePop);
				censusVO.setTotalPopulation(totPop);
				
				censusDetails.add(censusVO);
			}
		}
		return censusDetails;
	}
	
	
	 * This method gives complete voting trendz for a mandal
	 
	@SuppressWarnings("unchecked")
	public MandalElectionTrendzVO getOverallMandalVotingTrendz(Long electionId,Long mandalId) throws Exception{
		
		MandalElectionTrendzVO mandalElecTrendzList = null;
		List electionVotingTrendzList = null;
		List boothDetails = null;
		log.debug(" Inside getOverallMandalVotingTrendz Method ..... ");
		
		if(electionId != null && mandalId != null){
		boothDetails = boothConstituencyElectionDAO.findMandalWiseAllBoothsVotingTrendsInAnElection(electionId, mandalId);
		electionVotingTrendzList = candidateBoothResultDAO.findMandalWisePartiesResultForAllBoothsInAnElection(electionId, mandalId);
		if(electionVotingTrendzList != null && electionVotingTrendzList.size() > 0 && boothDetails != null && boothDetails.size() > 0){
		log.debug("Total Size Of Booths Details List :" + boothDetails.size());
		log.debug("Total Size Of Results List :" + electionVotingTrendzList.size());
		Map<Long,List<BoothTotalVotesVO>> boothsData = getProcessedResultsForAMandal(boothDetails,IConstants.COMPLETE_MANDAL);
		 if(!boothsData.isEmpty())
		 mandalElecTrendzList = getConstituencyWisePartyResultsForMandal(boothsData,electionVotingTrendzList,IConstants.COMPLETE_MANDAL);
		}
		}
		
		return mandalElecTrendzList;
	}
	
	
	 * This method gives male voting trendz for a mandal
	 
	@SuppressWarnings("unchecked")
	public MandalElectionTrendzVO getMandalVotingTrendzForMale(Long electionId,Long mandalId,Long maleTrendz, Long femaleTrendz) throws Exception{
		
		MandalElectionTrendzVO mandalElecTrendzList = null;
		List electionVotingTrendzList = null;
		List boothDetails = null;
		log.debug(" Inside getMandalVotingTrendzForMale Method ..... ");
		
		if(electionId != null && mandalId != null){
		boothDetails = boothConstituencyElectionDAO.findMandalWiseMaleBoothsVotingTrendsInAnElection(electionId, mandalId, femaleTrendz);
		electionVotingTrendzList = candidateBoothResultDAO.findMandalWisePartiesResultForMaleBoothsVotingTrendsInAnElection(electionId, mandalId, femaleTrendz);
		if(electionVotingTrendzList != null && electionVotingTrendzList.size() > 0 && boothDetails != null && boothDetails.size() > 0){
		log.debug("Total Size Of Booths Details List :" + boothDetails.size());
		log.debug("Total Size Of Results List :" + electionVotingTrendzList.size());
		Map<Long,List<BoothTotalVotesVO>> boothsData = getProcessedResultsForAMandal(boothDetails,IConstants.MALE_TRENDZ);
		 if(!boothsData.isEmpty())
		 mandalElecTrendzList = getConstituencyWisePartyResultsForMandal(boothsData,electionVotingTrendzList,IConstants.MALE_TRENDZ);
	    }
		}
		
		return mandalElecTrendzList;
	}
	
	
	 * This method gives female voting trendz for a mandal
	 
	@SuppressWarnings("unchecked")
	public MandalElectionTrendzVO getMandalVotingTrendzForFemale(Long electionId,Long mandalId,Long maleTrendz, Long femaleTrendz) throws Exception{
		
		MandalElectionTrendzVO mandalElecTrendzList = null;
		List electionVotingTrendzList = null;
		List boothDetails = null;
		log.debug(" Inside getMandalVotingTrendzForFemale Method ..... ");
		
		if(electionId != null && mandalId != null){
		boothDetails = boothConstituencyElectionDAO.findMandalWiseFemaleBoothsVotingTrendsInAnElection(electionId, mandalId, maleTrendz);
		electionVotingTrendzList = candidateBoothResultDAO.findMandalWisePartiesResultForFemaleBoothsVotingTrendsInAnElection(electionId, mandalId, maleTrendz);
		if(electionVotingTrendzList != null && electionVotingTrendzList.size() > 0 && boothDetails != null && boothDetails.size() > 0){
		log.debug("Total Size Of Booths Details List :" + boothDetails.size());
		log.debug("Total Size Of Results List :" + electionVotingTrendzList.size());
		Map<Long,List<BoothTotalVotesVO>> boothsData = getProcessedResultsForAMandal(boothDetails,IConstants.FEMALE_TRENDZ);
		 if(!boothsData.isEmpty())
		 mandalElecTrendzList = getConstituencyWisePartyResultsForMandal(boothsData,electionVotingTrendzList,IConstants.FEMALE_TRENDZ);
  		}
		}
		
		return mandalElecTrendzList;
	}
	
	
	 * This method gives male & female voting trendz for a mandal
	 
	@SuppressWarnings("unchecked")
	public MandalElectionTrendzVO getMandalVotingTrendzForMaleAndFemale(Long electionId,Long mandalId,Long maleTrendz, Long femaleTrendz) throws Exception{
		
		MandalElectionTrendzVO mandalElecTrendzList = null;
		List electionVotingTrendzList = null;
		List boothDetails = null;
		log.debug(" Inside getMandalVotingTrendzForMaleAndFemale Method ..... ");
		 
		if(electionId != null && mandalId != null){
		boothDetails = boothConstituencyElectionDAO.findMandalWiseMaleAndFemaleBoothsVotingTrendsInAnElection(electionId, mandalId, maleTrendz, femaleTrendz);
		electionVotingTrendzList = candidateBoothResultDAO.findMandalWisePartiesResultForMaleAndFemaleBoothsVotingTrendsInAnElection(electionId, mandalId, maleTrendz, femaleTrendz);
		if(electionVotingTrendzList != null && electionVotingTrendzList.size() > 0 && boothDetails != null && boothDetails.size() > 0){
		log.debug("Total Size Of Booths Details List :" + boothDetails.size());
		log.debug("Total Size Of Results List :" + electionVotingTrendzList.size());
		Map<Long,List<BoothTotalVotesVO>> boothsData = getProcessedResultsForAMandal(boothDetails,IConstants.MALE_FEMALE_TRENDZ);
		 if(!boothsData.isEmpty())
		 mandalElecTrendzList = getConstituencyWisePartyResultsForMandal(boothsData,electionVotingTrendzList,IConstants.MALE_FEMALE_TRENDZ);
		}
		}
		
		return mandalElecTrendzList;
	}
	
	
	 * This Method processes the result maps and returns the results for booths and party results..
	 
	@SuppressWarnings("unchecked")
	public Map<Long,List<BoothTotalVotesVO>> getProcessedResultsForAMandal(List boothDetailsList,String trendzType) throws Exception{
		
		Map<Long,List<BoothTotalVotesVO>> constiWiseBoothsMap = new HashMap<Long,List<BoothTotalVotesVO>>();
		List<BoothTotalVotesVO> boothsListData = null;
		
		if(boothDetailsList != null){
		  for(int i=0;i<boothDetailsList.size();i++){
			Object[] bootsInfo = (Object[])boothDetailsList.get(i);
			Long boothId       = (Long)bootsInfo[0];
			String partNo      = (String)bootsInfo[1];
			String locatn      = (String)bootsInfo[2];
			String villCovrd   = (String)bootsInfo[3];
			Long maleVoters    = (Long)bootsInfo[4];
			Long femaleVoters  = (Long)bootsInfo[5];
			Long totalVoters   = (Long)bootsInfo[6];
			Long constiId      = (Long)bootsInfo[7];
			String constiName  = (String)bootsInfo[8];
						
			BoothTotalVotesVO boothData = new BoothTotalVotesVO();
			boothData.setBoothID(boothId);
			boothData.setPartNo(new Long(partNo));
			boothData.setVillagesCovered(villCovrd);
			boothData.setLocation(locatn);
			boothData.setTotalVotes(totalVoters);
			if(trendzType.equals(IConstants.COMPLETE_MANDAL) || 
					trendzType.equals(IConstants.MALE_FEMALE_TRENDZ)){
			boothData.setMaleVotes(maleVoters);
			boothData.setFemaleVotes(femaleVoters);
			}
			else if(trendzType.equals(IConstants.MALE_TRENDZ)){
			boothData.setMaleVotes(maleVoters);
			boothData.setFemaleVotes(new Long(0));
			}
            else if(trendzType.equals(IConstants.FEMALE_TRENDZ)){
            boothData.setMaleVotes(new Long(0));
    		boothData.setFemaleVotes(femaleVoters);
			}
            
			if(constiWiseBoothsMap.isEmpty() || !constiWiseBoothsMap.containsKey(constiId)){
			boothsListData = new ArrayList<BoothTotalVotesVO>();
			}
			else if(constiWiseBoothsMap.containsKey(constiId)){
			boothsListData = constiWiseBoothsMap.get(constiId);
			}			
			boothsListData.add(boothData);
			constiWiseBoothsMap.put(constiId, boothsListData);
			log.debug("Booth Details For Constituency :" + constiName);
		  }
		}
		
		return constiWiseBoothsMap;
	}
	
	@SuppressWarnings("unchecked")
	public MandalElectionTrendzVO getConstituencyWisePartyResultsForMandal(Map<Long,List<BoothTotalVotesVO>> constiWiseBoothsMap,List votingTrendz,String trendzType){
		
		MandalElectionTrendzVO mandalElectionTrendz = new MandalElectionTrendzVO();
		Map<Long,List<PartyElectionResultVO>> votingTrendzResultsMap = new HashMap<Long,List<PartyElectionResultVO>>();
		List<PartyElectionResultVO> partyElectionResults = null;
		
		if(votingTrendz != null){
			for(int i=0;i<votingTrendz.size();i++){
				Object[] votingTrends = (Object[])votingTrendz.get(i);
				Long constiId     = (Long)votingTrends[0];
				String constiName = (String)votingTrends[1];
				Long partyId      = (Long)votingTrends[2];
				String partyName  = (String)votingTrends[3];
				String partyLogo  = (String)votingTrends[4];
				String partyFlag  = (String)votingTrends[5];
				Long candId       = (Long)votingTrends[6];
				String candName   = (String)votingTrends[7];
				Long votesEarned  = (Long)votingTrends[8];
				Long validVotes   = (Long)votingTrends[9];
				Long maleVoters   = (Long)votingTrends[10];
				Long femaleVoters = (Long)votingTrends[11];
				Long totalVoters  = (Long)votingTrends[12];
				Long noOfBooths   = (Long)votingTrends[13];
				Double votesPercent = new BigDecimal((votesEarned/validVotes)*100.0).setScale (2,BigDecimal.ROUND_HALF_UP).doubleValue();
				
				PartyElectionResultVO partyResult = new PartyElectionResultVO();
				partyResult.setCandidateId(candId);
				partyResult.setCandidateName(candName);
				partyResult.setConstiId(constiId);
				partyResult.setConstiName(constiName);
				partyResult.setPartyId(partyId);
				partyResult.setPartyName(partyName);
				partyResult.setPartyFlag(partyFlag);
				partyResult.setPartyLogo(partyLogo);
				partyResult.setVotesEarned(votesEarned);
				partyResult.setValidVotes(validVotes);
				partyResult.setTotalVoters(totalVoters);
				partyResult.setTotalBooths(noOfBooths);
				partyResult.setVotesPercentage(votesPercent.toString());
				
				
				if(trendzType.equals(IConstants.COMPLETE_MANDAL) || 
						trendzType.equals(IConstants.MALE_FEMALE_TRENDZ)){
				partyResult.setMaleVoters(maleVoters);
				partyResult.setFemaleVoters(femaleVoters);
				}
				else if(trendzType.equals(IConstants.MALE_TRENDZ)){
				partyResult.setMaleVoters(maleVoters);
				partyResult.setFemaleVoters(new Long(0));
				}
				else if(trendzType.equals(IConstants.FEMALE_TRENDZ)){
				partyResult.setMaleVoters(new Long(0));
				partyResult.setFemaleVoters(femaleVoters);	
				}
				if(votingTrendzResultsMap.isEmpty() || !votingTrendzResultsMap.containsKey(constiId)){
					partyElectionResults = new ArrayList<PartyElectionResultVO>();
				}
				else if(votingTrendzResultsMap.containsKey(constiId)){
					partyElectionResults = votingTrendzResultsMap.get(constiId);
				}
				partyElectionResults.add(partyResult);
				votingTrendzResultsMap.put(constiId, partyElectionResults);
				log.debug("Booth Details For Constituency :" + constiName);
			}
			
			if(!votingTrendzResultsMap.isEmpty() && votingTrendzResultsMap.size() > 0){
				
				List<BoothTotalVotesVO> boothsData = null;
				List<PartyElectionResultVO> partyResultForAConstituency = null;
				List<ConstituencyWiseBoothsInfoVO> constiWiseBoothInfo = new ArrayList<ConstituencyWiseBoothsInfoVO>();
				List<ConstituencyWisePartyResultsForMandal> constiWisePartyResults = new ArrayList<ConstituencyWisePartyResultsForMandal>();
				
				Set entries = votingTrendzResultsMap.entrySet();
				Iterator iterator = entries.iterator();
				while (iterator.hasNext()){
				ConstituencyWisePartyResultsForMandal constituencyWisePartyResultsForMandal = null;
				ConstituencyWiseBoothsInfoVO constituencyWiseBoothsInfoVO = null;
				
				Map.Entry entry = (Map.Entry)iterator.next();
				Long constituencyId = (Long)entry.getKey();
				partyResultForAConstituency = (List<PartyElectionResultVO>)entry.getValue();
				constituencyWisePartyResultsForMandal = getDetailsFromProcessedMap(partyResultForAConstituency,constituencyId);
				if(constituencyWisePartyResultsForMandal != null)
				constiWisePartyResults.add(constituencyWisePartyResultsForMandal);
				  if(constiWiseBoothsMap.containsKey(constituencyId)){
				 	boothsData = constiWiseBoothsMap.get(constituencyId);
				 	constituencyWiseBoothsInfoVO = new ConstituencyWiseBoothsInfoVO();
				 	constituencyWiseBoothsInfoVO.setConstituencyId(constituencyId);
				 	constituencyWiseBoothsInfoVO.setConstiName(constituencyWisePartyResultsForMandal.getConstiName());
				 	constituencyWiseBoothsInfoVO.setBoothsDetails(boothsData);
				 	constiWiseBoothInfo.add(constituencyWiseBoothsInfoVO);
				  }
			    }
				mandalElectionTrendz.setConstiWiseBoothInfo(constiWiseBoothInfo);
				mandalElectionTrendz.setConstiWisePartyResults(constiWisePartyResults);
				
			}
			
		}
		
		return mandalElectionTrendz;
	}
	
	public ConstituencyWisePartyResultsForMandal getDetailsFromProcessedMap(List<PartyElectionResultVO> partyResults,Long constiId){
		ConstituencyWisePartyResultsForMandal constituencyWisePartyResultsForMandal = null;
		if(partyResults != null && partyResults.size() > 0){
			constituencyWisePartyResultsForMandal = new ConstituencyWisePartyResultsForMandal();
			PartyElectionResultVO partyResultsData = partyResults.get(0);
			constituencyWisePartyResultsForMandal.setConstituencyId(constiId);
			constituencyWisePartyResultsForMandal.setConstiName(partyResultsData.getConstiName());
			constituencyWisePartyResultsForMandal.setMaleVoters(partyResultsData.getMaleVoters());
			constituencyWisePartyResultsForMandal.setFemaleVoters(partyResultsData.getFemaleVoters());
			constituencyWisePartyResultsForMandal.setNoOfVoters(partyResultsData.getTotalVoters());
			constituencyWisePartyResultsForMandal.setNoOfBooths(partyResultsData.getTotalBooths());
			constituencyWisePartyResultsForMandal.setPartyElecResults(partyResults);
		}
		
		return constituencyWisePartyResultsForMandal;
	}
	
	
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IElectionTrendzService#getVotingTrendzForAnElection(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long)
	 * Returns complete VotingTrendz for a constituency,complete details of male,female,male&female votes and also results for all parties that participated in 
	 * that constituency.
	 
	@SuppressWarnings("unchecked")
	public ElectionTrendzReportVO getVotingTrendzForAnElection(Long electionId,Long elecType,String elecYear,Long stateId,Long countryId,Long constituencyId,Long maleTrendz,Long femaleTrendz){
		
		ElectionTrendzReportVO electionTrendzReportVO = new ElectionTrendzReportVO();
		ResultStatus resultStatus = new ResultStatus();
		
		List latestElection = null;
		Long latestElecId = null;
		String latestElecYear = null;
		List<SelectOptionVO> prevElecYears = new ArrayList<SelectOptionVO>();
		
		try{
		if(elecYear.equals("0") && electionId.equals(new Long(0)))
		latestElection = electionDAO.findLatestElectionIdAndYear(elecType);
		if(latestElection != null && latestElection.size() > 0){
		Object[] params = (Object[])latestElection.get(0);
		latestElecId   = (Long)params[0];
		latestElecYear = (String)params[1];
		
		log.debug("Latest Election Id   :" + latestElecId);
		log.debug("Latest Election Year :" + latestElecYear);
		
		List prevYears = boothConstituencyElectionDAO.findAllElectionsForAConstituency(constituencyId);
		log.debug("After DAO (1) ....");
		if(prevYears != null && prevYears.size() > 0){
		for(int i=0;i<prevYears.size();i++){
			log.debug("Inside Prev Years ...");
			Object[] elec = (Object[])prevYears.get(i);
			Long electId = (Long)elec[0];
			String electYear = (String)elec[1];
			if(!electId.equals(latestElecId)){
			SelectOptionVO	optionVO = new SelectOptionVO();
			optionVO.setId(electId);
			optionVO.setName(electYear);
			prevElecYears.add(optionVO);
			log.debug(" ElectionId :" + electId);
			}
		}
		log.debug(" PrevElec Years :" + prevElecYears.size());
		}
		
		electionTrendzReportVO = getVotingTrendzForAConstituency(latestElecId,elecType,latestElecYear,constituencyId,maleTrendz,femaleTrendz);
		electionTrendzReportVO.setOtherElectionYears(prevElecYears);
		}
		
		}catch(Exception ex){
			log.debug("Exception Raised ... : " + ex);
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultPartial(true);
			resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			electionTrendzReportVO.setResultStatus(resultStatus);
		}
		
		
		return electionTrendzReportVO;
	}
	
	
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IElectionTrendzService#getVotingTrendzForAConstituency(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long)
	 * This method returns a voting trendz for an election for a constituency.
	 
	@SuppressWarnings("unchecked")
	public ElectionTrendzReportVO getVotingTrendzForAConstituency(Long electionId,Long elecType,String elecYear,Long constituencyId,Long maleTrendz,Long femaleTrendz) throws Exception{
		
		log.debug("Inside getVotingTrendzForAConstituency method ....");
		
		ElectionTrendzReportVO electionTrendzReportVO = new ElectionTrendzReportVO();
		ResultStatus resultStatus = new ResultStatus();
		
		ElectionTrendzInfoVO completeTrendzVO = null;
		ElectionTrendzInfoVO maleTrendzVO = null;
		ElectionTrendzInfoVO femaleTrendzVO = null;
		ElectionTrendzInfoVO maleAndFemaleTrendzVO = null;
		
		log.debug(" ElectionId     :" + electionId);
		log.debug(" ConstituencyId :" + constituencyId);
		try{
			//complete voting trendz
			//List completeBoothDetails = boothConstituencyElectionDAO.findConstituencyWiseAllBoothsVotingTrendsInAnElection(electionId, constituencyId);
			List completeElecResults  = candidateBoothResultDAO.findConstituencyWiseVotingTrendz(electionId, constituencyId);
			if(completeElecResults != null)
			completeTrendzVO = getProcessedResultsForVotingTrendz(null,completeElecResults);
			
			//male voting trendz
			//List maleBoothDetails = boothConstituencyElectionDAO.findConstituencyWiseMaleBoothsVotingTrendsInAnElection(electionId, constituencyId, femaleTrendz);
			List maleElecResults  = candidateBoothResultDAO.findConstituencyWiseMaleVotingTrendz(electionId, constituencyId, femaleTrendz);
			if(maleElecResults != null)
			maleTrendzVO = getProcessedResultsForVotingTrendz(null,maleElecResults);
			
			//female voting trendz
			//List femaleBoothDetails = boothConstituencyElectionDAO.findConstituencyWiseFemaleBoothsVotingTrendsInAnElection(electionId, constituencyId, maleTrendz);
			List femaleElecResults  = candidateBoothResultDAO.findConstituencyWiseFemaleVotingTrendz(electionId, constituencyId, maleTrendz);
			if(femaleElecResults != null)
			femaleTrendzVO = getProcessedResultsForVotingTrendz(null,femaleElecResults);
			
			//male&female voting trendz
			//List maleAndFemaleBoothDetails = boothConstituencyElectionDAO.findConstituencyWiseMaleAndFemaleBoothsVotingTrendsInAnElection(electionId, constituencyId, maleTrendz, femaleTrendz);
			List maleAndFemaleElecResults  = candidateBoothResultDAO.findConstituencyWiseMaleAndFemaleVotingTrendz(electionId, constituencyId, maleTrendz, femaleTrendz);
			if(maleAndFemaleElecResults != null)
			maleAndFemaleTrendzVO = getProcessedResultsForVotingTrendz(null,maleAndFemaleElecResults);
			
			
			
			List basicDetails = constituencyDAO.basicElecDetailsForAConstituency(constituencyId);
			if(basicDetails != null && basicDetails.size() > 0){
			Object[] params = (Object[])basicDetails.get(0);
			String elecTypeDetails = (String)params[0];
			String constiNameDetails = (String)params[1];
			String stateName = (String)params[2];
			
			electionTrendzReportVO.setConstituencyName(constiNameDetails);
			electionTrendzReportVO.setElectionType(elecTypeDetails);
			electionTrendzReportVO.setState(stateName);
			}
			electionTrendzReportVO.setConstituencyId(constituencyId);
			electionTrendzReportVO.setElectionYear(elecYear);
			electionTrendzReportVO.setCompleteTrendz(completeTrendzVO);
			electionTrendzReportVO.setMaleTrendz(maleTrendzVO);
			electionTrendzReportVO.setFemaleTrendz(femaleTrendzVO);
			electionTrendzReportVO.setMaleAndFemaleTrendz(maleAndFemaleTrendzVO);
			
			//getting partywise complete trendz ..
			ElectionTrendzOverviewVO electionTrendzOverviewVO = getConstituencyVotingTrendzForAnElection(constituencyId,electionTrendzReportVO.getConstituencyName(),elecType,elecYear,completeTrendzVO,maleTrendzVO,femaleTrendzVO,maleAndFemaleTrendzVO);
			if(electionTrendzOverviewVO != null)
			electionTrendzReportVO.setElectionTrendzOverviewVO(electionTrendzOverviewVO);
			
		}catch(Exception ex){
			log.debug("Exception Raised ... : " + ex);
			ex.printStackTrace();
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultPartial(true);
			resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			electionTrendzReportVO.setResultStatus(resultStatus);
		}
		
		return electionTrendzReportVO;
	}
	
	@SuppressWarnings("unchecked")
	public ElectionTrendzInfoVO getProcessedResultsForVotingTrendz(List completeBoothDetails,List completeElecResults) throws Exception{
		
		log.debug("Inside getProcessedResultsForVotingTrendz Method ....");
		//log.debug("BoothDetails Size :" + completeBoothDetails.size());
		log.debug("ElecResults Size :" + completeElecResults.size());
		
		ElectionTrendzInfoVO electionTrendzInfoVO = null;
		if(completeElecResults != null && completeElecResults.size() > 0){
			log.debug(" Inside (2) ...");
		electionTrendzInfoVO = new ElectionTrendzInfoVO();
		//electionTrendzInfoVO.setBoothsDetails(getBoothDetails(completeBoothDetails));
		electionTrendzInfoVO.setConstituencyWiseResults(getElectionResultsForAllParties(completeElecResults));
		}
		return electionTrendzInfoVO;
	}
	
	@SuppressWarnings("unchecked")
	public List<BoothTotalVotesVO> getBoothDetails(List completeBoothDetails) throws Exception{
		
		log.debug("Inside getBoothDetails Method ...");
		
		List<BoothTotalVotesVO> boothTotalVotesVO = null;
		if(completeBoothDetails != null && completeBoothDetails.size() > 0){
			
			boothTotalVotesVO = new ArrayList<BoothTotalVotesVO>();
			for(int i=0;i<completeBoothDetails.size();i++){
				Object[] params = (Object[])completeBoothDetails.get(i);
				Long boothId       = (Long)params[0];
				String partNo      = (String)params[1];
				String location    = (String)params[2];
				String villagesCov = (String)params[3];
				Long maleVotes    = (Long)params[4];
				Long femaleVotes  = (Long)params[5];
				Long totalVotes   = (Long)params[6];
				
				BoothTotalVotesVO booths = new BoothTotalVotesVO();
				booths.setBoothID(boothId);
				booths.setPartNo(new Long(partNo));
				booths.setLocation(location);
				booths.setVillagesCovered(villagesCov);
				booths.setMaleVotes(maleVotes);
				booths.setFemaleVotes(femaleVotes);
				booths.setTotalVotes(totalVotes);
				
				boothTotalVotesVO.add(booths);
			}
		}
		return boothTotalVotesVO;
	}
	
	
	 * Method Returns Complete Election Results For All Parties.
	 
	@SuppressWarnings("unchecked")
	public ConstituencyWisePartyResultsForMandal getElectionResultsForAllParties(List completeElecResults){
		
		log.debug("Inside getElectionResultsForAllParties Method ...");
		
		ConstituencyWisePartyResultsForMandal constituencyWisePartyResults = null;
		List<PartyElectionResultVO> partyElecResults = null;
		
		if(completeElecResults != null && completeElecResults.size() > 0){
			
			constituencyWisePartyResults = new ConstituencyWisePartyResultsForMandal();
			partyElecResults = new ArrayList<PartyElectionResultVO>();
			Long maleVoters = null;
			Long femaleVoters = null;
			Long totalVoters = null;
			Long noOfBooths = null;
			Long totValidVotes = null;
			 
			for(int i=0;i<completeElecResults.size();i++){
				Object[] params = (Object[])completeElecResults.get(i);
				Long partyId = (Long)params[0];
				String partyName = (String)params[1];
				String partyFlag = (String)params[2];
				Long candId = (Long)params[3];
				String candName = (String)params[4];
				Long votesEarned = (Long)params[5];
				Long validVotes = (Long)params[6];
				Long maleVotersCount = (Long)params[7];
				Long femaleVotersCount = (Long)params[8];
				Long totalVotersCount = (Long)params[9];
				Long boothsCount = (Long)params[10];
				Long rank = (Long)params[11];
				
				log.debug("Votes Earned :" + votesEarned);
				log.debug("Valid Votes  :" + validVotes);
				
				Double votesPercent = new BigDecimal((new Double(votesEarned)/validVotes)*100.0).setScale (2,BigDecimal.ROUND_HALF_UP).doubleValue();
				PartyElectionResultVO  elecResults = new PartyElectionResultVO();
				elecResults.setCandidateId(candId);
				elecResults.setCandidateName(candName);
				elecResults.setPartyId(partyId);
				elecResults.setPartyName(partyName);
				elecResults.setPartyFlag(partyFlag);
				elecResults.setVotesEarned(votesEarned);
				elecResults.setValidVotes(validVotes);
				elecResults.setVotesPercentage(votesPercent.toString());
				elecResults.setRank(rank);
				if(rank.equals(new Long(1)))
				elecResults.setStatus("Won");
				else if(rank.equals(new Long(2)))
				elecResults.setStatus("Runner-Up");
				else
				elecResults.setStatus("Lost");
				
				if(i==0){
				maleVoters   = maleVotersCount;
				femaleVoters = femaleVotersCount;
				totalVoters  = totalVotersCount;
				noOfBooths   = boothsCount;
				totValidVotes = validVotes;
				}
				partyElecResults.add(elecResults);			
			}
			constituencyWisePartyResults.setMaleVoters(maleVoters);
			constituencyWisePartyResults.setFemaleVoters(femaleVoters);
			constituencyWisePartyResults.setNoOfVoters(totalVoters);
			constituencyWisePartyResults.setNoOfBooths(noOfBooths);
			constituencyWisePartyResults.setPolledVotes(totValidVotes);
			constituencyWisePartyResults.setPartyElecResults(partyElecResults);
		}
		
		return constituencyWisePartyResults;
	}
	
	
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IElectionTrendzService#getConstituencyVotingTrendzForAnElection(java.lang.Long, java.lang.String, java.lang.Long, java.lang.String, com.itgrids.partyanalyst.dto.ElectionTrendzReportVO, com.itgrids.partyanalyst.dto.ElectionTrendzReportVO, com.itgrids.partyanalyst.dto.ElectionTrendzReportVO, com.itgrids.partyanalyst.dto.ElectionTrendzReportVO)
	 * Returns Voting trendz for a constituency for an election for all parties.
	 
	public ElectionTrendzOverviewVO getConstituencyVotingTrendzForAnElection(Long constituencyId,String constituencyName,Long elecType,String elecYear,ElectionTrendzInfoVO completeTrendz,ElectionTrendzInfoVO maleTrendz,ElectionTrendzInfoVO femaleTrendz,ElectionTrendzInfoVO maleAFemaleTrendz) throws Exception{
		
		ElectionTrendzOverviewVO electionTrendzOverviewVO = null;
		List<PartyResultsTrendzVO> partyResultsTrendzVO = null;
		
		if(completeTrendz != null && maleTrendz != null && femaleTrendz != null && maleAFemaleTrendz != null){
			
			electionTrendzOverviewVO = new ElectionTrendzOverviewVO();
			partyResultsTrendzVO = new ArrayList<PartyResultsTrendzVO>();
			
			electionTrendzOverviewVO.setTotalVoters(completeTrendz.getConstituencyWiseResults().getNoOfVoters());
			electionTrendzOverviewVO.setTotalPolledVotes(completeTrendz.getConstituencyWiseResults().getPolledVotes());
			
			electionTrendzOverviewVO.setMaleVoters(maleTrendz.getConstituencyWiseResults().getNoOfVoters());
			electionTrendzOverviewVO.setMalePolledVotes(maleTrendz.getConstituencyWiseResults().getPolledVotes());
			electionTrendzOverviewVO.setMaleVotersInConstituency(completeTrendz.getConstituencyWiseResults().getMaleVoters());
			electionTrendzOverviewVO.setMaleVotersPercent(getPollingPercent(maleTrendz.getConstituencyWiseResults().getNoOfVoters(),completeTrendz.getConstituencyWiseResults().getNoOfVoters()).toString());
			electionTrendzOverviewVO.setMaleVotersPercentInConsti(getPollingPercent(maleTrendz.getConstituencyWiseResults().getNoOfVoters(),completeTrendz.getConstituencyWiseResults().getMaleVoters()).toString());
			
			electionTrendzOverviewVO.setFemaleVoters(femaleTrendz.getConstituencyWiseResults().getNoOfVoters());
			electionTrendzOverviewVO.setFemalePolledVotes(femaleTrendz.getConstituencyWiseResults().getPolledVotes());
			electionTrendzOverviewVO.setFemaleVotersInConstituency(completeTrendz.getConstituencyWiseResults().getFemaleVoters());
			electionTrendzOverviewVO.setFemaleVotersPercent(getPollingPercent(femaleTrendz.getConstituencyWiseResults().getNoOfVoters(),completeTrendz.getConstituencyWiseResults().getNoOfVoters()).toString());
			electionTrendzOverviewVO.setFemaleVotersPercentInConsti(getPollingPercent(femaleTrendz.getConstituencyWiseResults().getNoOfVoters(),completeTrendz.getConstituencyWiseResults().getFemaleVoters()).toString());
			
			electionTrendzOverviewVO.setMaleAndFemaleVoters(maleAFemaleTrendz.getConstituencyWiseResults().getNoOfVoters());
			electionTrendzOverviewVO.setMaleAndFemalePolledVotes(maleAFemaleTrendz.getConstituencyWiseResults().getPolledVotes());
			electionTrendzOverviewVO.setMaleOrFemaleVotersPercent(getPollingPercent(maleAFemaleTrendz.getConstituencyWiseResults().getNoOfVoters(),completeTrendz.getConstituencyWiseResults().getNoOfVoters()).toString());
			
			electionTrendzOverviewVO.setPollingPercent(getPollingPercent(completeTrendz.getConstituencyWiseResults().getPolledVotes(),completeTrendz.getConstituencyWiseResults().getNoOfVoters()).toString());
			electionTrendzOverviewVO.setMalePollingPercent(getPollingPercent(maleTrendz.getConstituencyWiseResults().getPolledVotes(),maleTrendz.getConstituencyWiseResults().getNoOfVoters()).toString());
			electionTrendzOverviewVO.setFemalePollingPercent(getPollingPercent(femaleTrendz.getConstituencyWiseResults().getPolledVotes(),femaleTrendz.getConstituencyWiseResults().getNoOfVoters()).toString());
			electionTrendzOverviewVO.setMaleAndFemalePollingPercent(getPollingPercent(maleAFemaleTrendz.getConstituencyWiseResults().getPolledVotes(),maleAFemaleTrendz.getConstituencyWiseResults().getNoOfVoters()).toString());
			electionTrendzOverviewVO.setOverallMalePollPercent(getPollingPercent(maleTrendz.getConstituencyWiseResults().getPolledVotes(),completeTrendz.getConstituencyWiseResults().getNoOfVoters()).toString());
			electionTrendzOverviewVO.setOverallFemalePollPercent(getPollingPercent(femaleTrendz.getConstituencyWiseResults().getPolledVotes(),completeTrendz.getConstituencyWiseResults().getNoOfVoters()).toString());
			electionTrendzOverviewVO.setOverallMaleOrFemalePollPercent(getPollingPercent(maleAFemaleTrendz.getConstituencyWiseResults().getPolledVotes(),completeTrendz.getConstituencyWiseResults().getNoOfVoters()).toString());
			
			electionTrendzOverviewVO.setMalePollingPercentInTotalPolledVotes(getPollingPercent(maleTrendz.getConstituencyWiseResults().getPolledVotes(),completeTrendz.getConstituencyWiseResults().getPolledVotes()).toString());
			electionTrendzOverviewVO.setFemalePollingPercentInTotalPolledVotes(getPollingPercent(femaleTrendz.getConstituencyWiseResults().getPolledVotes(),completeTrendz.getConstituencyWiseResults().getPolledVotes()).toString());
			electionTrendzOverviewVO.setMaleOrFemalePollingPercentInTotalPolledVotes(getPollingPercent(maleAFemaleTrendz.getConstituencyWiseResults().getPolledVotes(),completeTrendz.getConstituencyWiseResults().getPolledVotes()).toString());

			
			for(PartyElectionResultVO partyTrendz:completeTrendz.getConstituencyWiseResults().getPartyElecResults()){
				Long candId = partyTrendz.getCandidateId();
				PartyElectionResultVO maleTrendzResult   = getPartyResultTrendzForACandidate(maleTrendz.getConstituencyWiseResults().getPartyElecResults(),candId);
				PartyElectionResultVO femaleTrendzResult = getPartyResultTrendzForACandidate(femaleTrendz.getConstituencyWiseResults().getPartyElecResults(),candId);
				PartyElectionResultVO maleAFemaleResult  = getPartyResultTrendzForACandidate(maleAFemaleTrendz.getConstituencyWiseResults().getPartyElecResults(),candId);
				
				PartyResultsTrendzVO partyResultsTrendz = getVotingTrendzForACandidate(partyTrendz,maleTrendzResult,femaleTrendzResult,maleAFemaleResult,completeTrendz.getConstituencyWiseResults().getPolledVotes());
								
				if(partyResultsTrendz != null){
				partyResultsTrendzVO.add(partyResultsTrendz);
				if(partyResultsTrendz.getRank().equals(new Long(1)))
				electionTrendzOverviewVO.setWonCandidateResultTrendz(partyResultsTrendz);
				}
			}
			electionTrendzOverviewVO.setPartyElectionTrendzVO(partyResultsTrendzVO);
		}
				
		return electionTrendzOverviewVO;
	}
	
	 * Calculates Percentage
	 
	public Double getPollingPercent(Long polledVotes,Long totalVoters) throws Exception{
		Double pollingPercent = new BigDecimal((new Double(polledVotes)/totalVoters)*100.0).setScale (2,BigDecimal.ROUND_HALF_UP).doubleValue();
		return pollingPercent;
	}
	
	
	 * Selects a Candidate Result From List Of Candidates
	 
	public PartyElectionResultVO getPartyResultTrendzForACandidate(List<PartyElectionResultVO> partyElecResults,Long candidateId) throws Exception{
		PartyElectionResultVO partyElecResultVO = null;
		if(partyElecResults != null && partyElecResults.size() > 0){
			for(PartyElectionResultVO partyTrendz:partyElecResults){
				if(partyTrendz.getCandidateId().equals(candidateId))
				return partyTrendz;
			}
		}
	   return partyElecResultVO;
	}
	
	
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IElectionTrendzService#getPreviousElectionsInfoForAConstituency(java.lang.String, java.lang.Long)
	 
	public ElectionDetailsVO getPreviousElectionsInfoForAConstituency(String electionYear,Long constituencyId){
		ElectionDetailsVO electionDetailsVO = null;
		if(electionYear != null && constituencyId != null){
			electionDetailsVO = new ElectionDetailsVO();
			List<ElectionBasicInfoVO> electionsInfoForAssembly = staticDataService.getAssemblyElectionsInfoForAConstituency(electionYear,constituencyId);
			List<ElectionBasicInfoVO> electionsInfoForParliament = staticDataService.getParliamentElectionsInfoForAConstituency(constituencyId);
			
			if(electionsInfoForAssembly != null)
			electionDetailsVO.setAssemblyElections(electionsInfoForAssembly);
			if(electionsInfoForParliament != null)
			electionDetailsVO.setParliamentElections(electionsInfoForParliament);
			
			log.debug("Assembly ..");
			for(ElectionBasicInfoVO info:electionsInfoForAssembly){
				log.debug("Prev ElecId:" + info.getElectionId());
				log.debug("Prev ElecTypeId:" + info.getElectionTypeId());
				log.debug("Prev Elec Year:" + info.getElectionYear());
			}
			log.debug("Parliament ..");
			for(ElectionBasicInfoVO info1:electionsInfoForParliament){
				log.debug("Prev ElecId:" + info1.getElectionId());
				log.debug("Prev ElecTypeId:" + info1.getElectionTypeId());
				log.debug("Prev Elec Year:" + info1.getElectionYear());
			}
			
		}
		return electionDetailsVO;
	}
	
	
	 * Populating the results to VO
	 
	public PartyResultsTrendzVO getVotingTrendzForACandidate(PartyElectionResultVO completeTrendz,PartyElectionResultVO maleTrendz,PartyElectionResultVO femaleTrendz,PartyElectionResultVO mAFTrendz,Long constiTotalPolledVotes) throws Exception{
		PartyResultsTrendzVO partyResultsTrendzVO = null;
		if(completeTrendz != null && maleTrendz != null && femaleTrendz != null && mAFTrendz != null){
			partyResultsTrendzVO = new PartyResultsTrendzVO();
			partyResultsTrendzVO.setPartyId(completeTrendz.getPartyId());
			partyResultsTrendzVO.setPartyName(completeTrendz.getPartyName());
			partyResultsTrendzVO.setPartyFlag(completeTrendz.getPartyFlag());
			partyResultsTrendzVO.setRank(completeTrendz.getRank());
			if(completeTrendz.getRank().equals(new Long(1)))
			partyResultsTrendzVO.setStatus("Won");
			else if(completeTrendz.getRank().equals(new Long(2)))
			partyResultsTrendzVO.setStatus("RunnerUp");
			else
			partyResultsTrendzVO.setStatus("Lost");	
			partyResultsTrendzVO.setCandidateId(completeTrendz.getCandidateId());
			partyResultsTrendzVO.setCandidateName(completeTrendz.getCandidateName());
			partyResultsTrendzVO.setTotalVotes(completeTrendz.getVotesEarned());
			partyResultsTrendzVO.setMaleVotes(maleTrendz.getVotesEarned());
			partyResultsTrendzVO.setFemaleVotes(femaleTrendz.getVotesEarned());
			partyResultsTrendzVO.setMaleAndFemaleVotes(mAFTrendz.getVotesEarned());
			partyResultsTrendzVO.setConstiTotalVotes(constiTotalPolledVotes);
			partyResultsTrendzVO.setTotalVotesPercent(getPollingPercent(completeTrendz.getVotesEarned(),completeTrendz.getValidVotes()).toString());
			partyResultsTrendzVO.setMaleVotesPercent(getPollingPercent(maleTrendz.getVotesEarned(),maleTrendz.getValidVotes()).toString());
			partyResultsTrendzVO.setOverallMaleVotesPercent(getPollingPercent(maleTrendz.getVotesEarned(),completeTrendz.getVotesEarned()).toString());
			partyResultsTrendzVO.setFemaleVotesPercent(getPollingPercent(femaleTrendz.getVotesEarned(),femaleTrendz.getValidVotes()).toString());
			partyResultsTrendzVO.setOverallFemaleVotesPercent(getPollingPercent(femaleTrendz.getVotesEarned(),completeTrendz.getVotesEarned()).toString());
			partyResultsTrendzVO.setMaleAndFemaleVotesPercent(getPollingPercent(mAFTrendz.getVotesEarned(),mAFTrendz.getValidVotes()).toString());
			partyResultsTrendzVO.setOverallMaleOrFemaleVotesPercent(getPollingPercent(mAFTrendz.getVotesEarned(),completeTrendz.getVotesEarned()).toString());
			partyResultsTrendzVO.setMaleVotesPercentInConstiVotes(getPollingPercent(maleTrendz.getVotesEarned(),constiTotalPolledVotes).toString());
			partyResultsTrendzVO.setFemaleVotesPercentInConstiVotes(getPollingPercent(femaleTrendz.getVotesEarned(),constiTotalPolledVotes).toString());
			partyResultsTrendzVO.setMaleOrFemaleVotesPercentInConstiVotes(getPollingPercent(mAFTrendz.getVotesEarned(),constiTotalPolledVotes).toString());
		}
		
		return partyResultsTrendzVO;
	}

*/}
