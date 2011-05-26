/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 15,2010.
 */
package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionAllianceDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPartyElectionDistrictResultDAO;
import com.itgrids.partyanalyst.dao.IPartyElectionDistrictResultWithAllianceDAO;
import com.itgrids.partyanalyst.dao.IPartyElectionStateResultDAO;
import com.itgrids.partyanalyst.dao.IPartyElectionStateResultWithAllianceDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dto.ComparedConstituencyElectionVO;
import com.itgrids.partyanalyst.dto.ComparedElectionResultVO;
import com.itgrids.partyanalyst.dto.ComparedReportVO;
import com.itgrids.partyanalyst.dto.DistrictWisePartyResultVO;
import com.itgrids.partyanalyst.dto.ElectionComparisonReportVO;
import com.itgrids.partyanalyst.dto.PartyPositionsVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Group;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyElectionDistrictResult;
import com.itgrids.partyanalyst.model.PartyElectionDistrictResultWithAlliance;
import com.itgrids.partyanalyst.model.PartyElectionResult;
import com.itgrids.partyanalyst.model.PartyElectionStateResult;
import com.itgrids.partyanalyst.model.PartyElectionStateResultWithAlliance;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.service.IElectionComparisonReportService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.NominatResultsComparator;

public class ElectionComparisonReportService implements IElectionComparisonReportService {
	
	private IElectionDAO electionDAO;
	private IStaticDataService staticDataService;
	private IPartyDAO partyDAO;
	private INominationDAO nominationDAO;
	private IPartyElectionStateResultWithAllianceDAO partyElectionStateResultWithAllianceDAO;
	private IPartyElectionDistrictResultWithAllianceDAO partyElectionDistrictResultWithAllianceDAO;
	private TransactionTemplate transactionTemplate;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IElectionAllianceDAO electionAllianceDAO; 
	private IPartyElectionDistrictResultDAO partyElectionDistrictResultDAO;
	private IPartyElectionStateResultDAO partyElectionStateResultDAO;
	private IAllianceGroupDAO allianceGroupDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	
	public IPartyElectionStateResultWithAllianceDAO getPartyElectionStateResultWithAllianceDAO() {
		return partyElectionStateResultWithAllianceDAO;
	}

	public void setPartyElectionStateResultWithAllianceDAO(
			IPartyElectionStateResultWithAllianceDAO partyElectionStateResultWithAllianceDAO) {
		this.partyElectionStateResultWithAllianceDAO = partyElectionStateResultWithAllianceDAO;
	}

	public IPartyElectionDistrictResultWithAllianceDAO getPartyElectionDistrictResultWithAllianceDAO() {
		return partyElectionDistrictResultWithAllianceDAO;
	}

	public void setPartyElectionDistrictResultWithAllianceDAO(
			IPartyElectionDistrictResultWithAllianceDAO partyElectionDistrictResultWithAllianceDAO) {
		this.partyElectionDistrictResultWithAllianceDAO = partyElectionDistrictResultWithAllianceDAO;
	}

	public IAllianceGroupDAO getAllianceGroupDAO() {
		return allianceGroupDAO;
	}

	public void setAllianceGroupDAO(IAllianceGroupDAO allianceGroupDAO) {
		this.allianceGroupDAO = allianceGroupDAO;
	}

	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public IElectionAllianceDAO getElectionAllianceDAO() {
		return electionAllianceDAO;
	}

	public void setElectionAllianceDAO(IElectionAllianceDAO electionAllianceDAO) {
		this.electionAllianceDAO = electionAllianceDAO;
	}
	private final static Logger log = Logger.getLogger(ElectionComparisonReportService.class);
	
	
	public IConstituencyElectionDAO getConstituencyElectionDAO() {
		return constituencyElectionDAO;
	}

	public void setConstituencyElectionDAO(
			IConstituencyElectionDAO constituencyElectionDAO) {
		this.constituencyElectionDAO = constituencyElectionDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
		
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

	public IPartyElectionDistrictResultDAO getPartyElectionDistrictResultDAO() {
		return partyElectionDistrictResultDAO;
	}

	public void setPartyElectionDistrictResultDAO(
			IPartyElectionDistrictResultDAO partyElectionDistrictResultDAO) {
		this.partyElectionDistrictResultDAO = partyElectionDistrictResultDAO;
	}

	public IPartyElectionStateResultDAO getPartyElectionStateResultDAO() {
		return partyElectionStateResultDAO;
	}

	public void setPartyElectionStateResultDAO(
			IPartyElectionStateResultDAO partyElectionStateResultDAO) {
		this.partyElectionStateResultDAO = partyElectionStateResultDAO;
	}

	public ElectionComparisonReportVO getDistrictWiseElectionResultsForAParty(Long partyId,Long firstElectionId,
			Long secondElectionId, Boolean hasAlliances){
		ElectionComparisonReportVO electionComparisonReportVO = new ElectionComparisonReportVO();
		Long stateOrCountryId = null;
		boolean isAssembly;
		electionComparisonReportVO.setPartyId(partyId);
		electionComparisonReportVO.setElecIdYearOne(firstElectionId);
		electionComparisonReportVO.setElecIdYearTwo(secondElectionId);
		Election election1 = electionDAO.get(firstElectionId);
		Election election2 = electionDAO.get(secondElectionId);
		
		if(Integer.parseInt(election1.getElectionYear()) < Integer.parseInt(election2.getElectionYear())){
			Election temp;
			temp = election1;
			election1 = election2;
			election2 = temp;
		}
		
		firstElectionId = election1.getElectionId(); secondElectionId = election2.getElectionId();
		electionComparisonReportVO.setYearOne(election1.getElectionYear());
		electionComparisonReportVO.setYearTwo(election2.getElectionYear());
		electionComparisonReportVO.setElectionType(election1.getElectionScope().getElectionType().getElectionType());
		electionComparisonReportVO.setHasAlliances(hasAlliances);
		
		if(IConstants.ASSEMBLY_ELECTION_TYPE.equalsIgnoreCase(election1.getElectionScope().getElectionType().getElectionType())){
			stateOrCountryId = election1.getElectionScope().getState().getStateId();
			isAssembly = true;
		}
		else{
			stateOrCountryId = election1.getElectionScope().getCountry().getCountryId();
			isAssembly = false;
		}
		
		getStatewiseResultsForAssembly(electionComparisonReportVO, firstElectionId, secondElectionId, partyId, hasAlliances, isAssembly);
		Map<Long, DistrictWisePartyResultVO> resultOne = getAssembliesDistrictwiseResults(partyId, stateOrCountryId, firstElectionId, 
				isAssembly, hasAlliances);
		Map<Long, DistrictWisePartyResultVO> resultTwo = getAssembliesDistrictwiseResults(partyId, stateOrCountryId, secondElectionId,
				isAssembly, hasAlliances);
		if(resultTwo.size() > resultOne.size())
			calculateDifferencesFromLatest(electionComparisonReportVO, resultTwo, resultOne, false);
		else
			calculateDifferencesFromLatest(electionComparisonReportVO, resultOne, resultTwo, true);
		//long heapSizeEnd = Runtime.getRuntime().totalMemory();
		
		//System.out.println("Heap Size :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"+(heapSizeEnd - heapSize));
		
		return electionComparisonReportVO;
	}
	
	public void getStatewiseResultsForAssembly(ElectionComparisonReportVO resultVO, Long electionId1, 
			Long electionId2, Long partyId, Boolean hasAlliance, Boolean isAssembly){
		
		List<PartyPositionsVO> partyPositionsList1 = new ArrayList<PartyPositionsVO>();
		List<PartyPositionsVO> partyPositionsList2 = new ArrayList<PartyPositionsVO>();
		
		PartyPositionsVO partyPosition1 = getPartyPositions(electionId1, partyId);
    	PartyPositionsVO partyPosition2 = getPartyPositions(electionId2, partyId);
    	partyPositionsList1.add(partyPosition1);
    	partyPositionsList2.add(partyPosition2);
    	
    	resultVO.setSeatsDiff(partyPosition1.getTotalSeatsWon() - partyPosition2.getTotalSeatsWon());
    	Double percentDiff = new Double(partyPosition1.getVotesPercentage()) - new Double(partyPosition2.getVotesPercentage());
    	resultVO.setVotesPercentDiff(new BigDecimal(percentDiff).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
    	
    	if(hasAlliance){
    		getpartyPositionsListForAllianceParties(partyPositionsList1, electionId1, partyId);
    		getpartyPositionsListForAllianceParties(partyPositionsList2, electionId2, partyId);
		}

    	resultVO.setPositionsForYearOne(partyPositionsList1);
    	resultVO.setPositionsForYearTwo(partyPositionsList2);
	}
	
	private void getpartyPositionsListForAllianceParties(List<PartyPositionsVO> partyPositionsList, Long electionId, Long partyId){
		List allianceparites = allianceGroupDAO.findAlliancePartiesByElectionAndPartyExcludeParty(electionId, partyId);
		Set<Long> allianceParties = new HashSet<Long>();
		for(Object[] values:(List<Object[]>) allianceparites)
			allianceParties.add(Long.parseLong(values[1].toString()));
		for(Long alPartyId:allianceParties)
			partyPositionsList.add(getPartyPositions(electionId, alPartyId));
	}
	
	private void calculateDifferencesFromLatest(ElectionComparisonReportVO electionComparisonReportVO, 
			Map<Long, DistrictWisePartyResultVO> resultOne, Map<Long, DistrictWisePartyResultVO> resultTwo, Boolean isBiggerMapLatest){
		DistrictWisePartyResultVO districtVO1 = null;
		DistrictWisePartyResultVO districtVO2 = null;
		DistrictWisePartyResultVO temp = null;
		List<DistrictWisePartyResultVO> districts1 = new ArrayList<DistrictWisePartyResultVO>();
		List<DistrictWisePartyResultVO> districts2 = new ArrayList<DistrictWisePartyResultVO>();
		for(Map.Entry<Long, DistrictWisePartyResultVO> entry1:resultOne.entrySet()){
			districtVO1 = entry1.getValue();
			districtVO2 = resultTwo.get(entry1.getKey());
			
			if(districtVO2 == null){
				districtVO2 = new DistrictWisePartyResultVO();
				districtVO2.setDistrictId(entry1.getKey());
				districtVO2.setDistrictName(districtVO1.getDistrictName());
				districtVO2.setConstiParticipated(0l);
				districtVO2.setSeatsWon(0l);
				districtVO2.setVotesPercent(0d);
				districtVO2.setTotalPercentage(0d);
				districtVO2.setConstiCount(0l);
			}
			
			if(isBiggerMapLatest){
				districtVO1.setVotesPercentDiff(new BigDecimal(districtVO1.getVotesPercent() - districtVO2.getVotesPercent()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				districtVO1.setSeatsWonDiff(districtVO1.getSeatsWon() - districtVO2.getSeatsWon());
				
				districtVO2.setVotesPercentDiff(new BigDecimal(districtVO1.getVotesPercent() - districtVO2.getVotesPercent()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				districtVO2.setSeatsWonDiff(districtVO1.getSeatsWon() - districtVO2.getSeatsWon());
			}else{
				districtVO1.setVotesPercentDiff(new BigDecimal(districtVO2.getVotesPercent() - districtVO1.getVotesPercent()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				districtVO1.setSeatsWonDiff(districtVO2.getSeatsWon() - districtVO1.getSeatsWon());
				
				districtVO2.setVotesPercentDiff(new BigDecimal(districtVO2.getVotesPercent() - districtVO1.getVotesPercent()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				districtVO2.setSeatsWonDiff(districtVO2.getSeatsWon() - districtVO1.getSeatsWon());	
			}
			
			if(!isBiggerMapLatest){
				temp = districtVO2;
				districtVO2 = districtVO1;
				districtVO1 = temp;
			}
			
			districts1.add(districtVO1);
			districts2.add(districtVO2);
			
		}
		
		electionComparisonReportVO.setDistrictWisePartyResultsForYearOne(districts1);
		electionComparisonReportVO.setDistrictWisePartyResultsForYearTwo(districts2);
	}
	
	@SuppressWarnings("unchecked")
	public Map<Long, DistrictWisePartyResultVO> getAssembliesDistrictwiseResults(Long partyId, Long stateOrCountryId, 
			Long electionId, boolean isAssembly, boolean hasAlliance){
		List result = null;
		List constiCount = null;
		DistrictWisePartyResultVO districtWisePartyResultVO = null;
		Map<Long, Long> constiCountInSateOrDist = new HashMap<Long, Long>();
		Map<Long, DistrictWisePartyResultVO> districtsResultMap = new HashMap<Long, DistrictWisePartyResultVO>();
		List constiCountList = null;
		
		if(isAssembly){
			constiCountList = constituencyElectionDAO.getConstituenciesCountByDistrictForStateAndElection(stateOrCountryId, electionId);
			fillConstiCountInSateOrDistMap(constiCountInSateOrDist, constiCountList);
		}else{
			constiCountList = constituencyElectionDAO.getConstituenciesCountByStateForCountryAndElection(stateOrCountryId, electionId);
			fillConstiCountInSateOrDistMap(constiCountInSateOrDist, constiCountList);
		}
		
		if(hasAlliance){
			String parties = getAlliancePartiesIdsWithComma(allianceGroupDAO.findAlliancePartiesByElectionAndParty(electionId, partyId));
			String alliancePartiesLength = parties;
			
			if(parties.length() == 0)
				parties = partyId.toString();
			if(isAssembly){
				constiCount = nominationDAO.getConstituenciesCountByDistrictForElectionStateAndParties(electionId, stateOrCountryId, parties);
				
				if(alliancePartiesLength.length() == 0)
					result = partyElectionDistrictResultDAO.findDistrictWiseElectionResultsForStatePartyAndElection(partyId, stateOrCountryId, electionId);
				else
					result = partyElectionDistrictResultWithAllianceDAO.findDistrictWiseElectionResultsForStatePartyAndElection(partyId, 
						stateOrCountryId, electionId);
				if(result.size() < constiCount.size()){
					electionComparision(partyId, electionId, true, stateOrCountryId);
					
					if(alliancePartiesLength.length() == 0)
						result = partyElectionDistrictResultDAO.findDistrictWiseElectionResultsForStatePartyAndElection(partyId, stateOrCountryId, electionId);
					else
						result = partyElectionDistrictResultWithAllianceDAO.findDistrictWiseElectionResultsForStatePartyAndElection(partyId, 
							stateOrCountryId, electionId);
				}
				
			}else{
				constiCount = nominationDAO.getConstituenciesCountByStateForElectionCountryAndParties(electionId, stateOrCountryId, parties);
				
				if(alliancePartiesLength.length() == 0)
					result = partyElectionStateResultDAO.findStatewiseResultsForPartyElectionAndCountry(partyId, stateOrCountryId, electionId);
				else
					result = partyElectionStateResultWithAllianceDAO.findStatewiseResultsForPartyElectionAndCountry(partyId, 
						stateOrCountryId, electionId);
				if(result.size() < constiCount.size()){
					electionComparision(partyId, electionId, false, stateOrCountryId);
					
					if(alliancePartiesLength.length() == 0)
						result = partyElectionStateResultDAO.findStatewiseResultsForPartyElectionAndCountry(partyId, stateOrCountryId, electionId);
					else
						result = partyElectionStateResultWithAllianceDAO.findStatewiseResultsForPartyElectionAndCountry(partyId, 
							stateOrCountryId, electionId);
				}
			}
		}else{
			if(isAssembly){
				constiCount = nominationDAO.getConstituenciesCountByDistrictForElectionStateAndParties(electionId, stateOrCountryId, partyId.toString());
				result = partyElectionDistrictResultDAO.findDistrictWiseElectionResultsForStatePartyAndElection(partyId, stateOrCountryId, electionId);
				if(result.size() < constiCount.size()){
					List<SelectOptionVO> stateOrDistList = staticDataService.getDistricts(stateOrCountryId);
					for(SelectOptionVO district:stateOrDistList)
						staticDataService.savePartyElectionResultForAPartyForAElectionDistrictLevel(electionId, partyId, district.getId());
					result = partyElectionDistrictResultDAO.findDistrictWiseElectionResultsForStatePartyAndElection(partyId, stateOrCountryId, electionId);
				}	
			}else{
				constiCount = nominationDAO.getConstituenciesCountByStateForElectionCountryAndParties(electionId, stateOrCountryId, partyId.toString());
				result = partyElectionStateResultDAO.findStatewiseResultsForPartyElectionAndCountry(partyId, stateOrCountryId, electionId);
				if(result.size() < constiCount.size()){
					List<State> stateList = staticDataService.getAllStates();
					for(State state:stateList)
						staticDataService.savePartyElectionResultForAPartyForAParliamentElectionStateLevel(electionId, partyId, state.getStateId());
					result = partyElectionStateResultDAO.findStatewiseResultsForPartyElectionAndCountry(partyId, stateOrCountryId, electionId);
				}
			}
		}
		
			
		for (Object[] values : (List<Object[]>)result) {
			districtWisePartyResultVO = new DistrictWisePartyResultVO();
			districtWisePartyResultVO.setDistrictId(new Long(values[0].toString()));
			districtWisePartyResultVO.setDistrictName(values[1].toString());
			districtWisePartyResultVO.setConstiParticipated(new Long(values[2].toString()));
			districtWisePartyResultVO.setSeatsWon(new Long(values[3].toString()));
			districtWisePartyResultVO.setVotesPercent(new Double(values[4].toString()));
			districtWisePartyResultVO.setTotalPercentage(new Double(values[5].toString()));
			districtWisePartyResultVO.setConstiCount(constiCountInSateOrDist.get(new Long(values[0].toString())));
			districtsResultMap.put(new Long(values[0].toString()), districtWisePartyResultVO);
		}
		
		for(Object[] values : (List<Object[]>)constiCountList)
		{
			if(districtsResultMap.get((Long)values[0]) != null)
				continue;
			else
			{
				districtWisePartyResultVO = new DistrictWisePartyResultVO();
				districtWisePartyResultVO.setDistrictId((Long)values[0]);
				districtWisePartyResultVO.setDistrictName(((List<Object[]>)districtDAO.getDistrictNameByDistrictId((Long)values[0])).get(0)[0].toString());
				districtWisePartyResultVO.setConstiParticipated(0l);
				districtWisePartyResultVO.setSeatsWon(0l);
				districtWisePartyResultVO.setVotesPercent(0d);
				districtWisePartyResultVO.setTotalPercentage(0d);
				districtWisePartyResultVO.setConstiCount((Long)values[1]);
				districtsResultMap.put((Long)values[0], districtWisePartyResultVO);
			}
		}
		
		return districtsResultMap;
	}
	
	private void fillConstiCountInSateOrDistMap(
			Map<Long, Long> constiCountInSateOrDist,
			List constiCountList) {
		for(Object[] values:(List<Object[]>)constiCountList)
			constiCountInSateOrDist.put(Long.parseLong(values[0].toString()), Long.parseLong(values[1].toString()));
			
	}

	public PartyPositionsVO getPartyPositions(Long electionId, Long partyId){
		PartyElectionResult partyElecResults = staticDataService.getPartyElectionResultsForAParty(electionId, partyId);
    	if(partyElecResults == null)
    		partyElecResults = staticDataService.savePartyElectionResultForAPartyForAElection(electionId,partyId);
    	PartyPositionsVO partyPositions = new PartyPositionsVO();
		partyPositions.setTotalSeatsWon(new Long(partyElecResults.getTotalSeatsWon()));
    	partyPositions.setSecondPosWon(new Long(partyElecResults.getSecondPosWon()));
    	partyPositions.setThirdPosWon(new Long(partyElecResults.getThirdPosWon()));
    	partyPositions.setFourthPosWon(new Long(partyElecResults.getFourthPosWon()));
    	partyPositions.setNthPosWon(new Long(partyElecResults.getNthPosWon()));
    	partyPositions.setTotalConstiParticipated(new Long(partyElecResults.getTotalConstiParticipated()));
    	partyPositions.setPartyId(partyElecResults.getParty().getPartyId());
    	partyPositions.setPartyName(partyElecResults.getParty().getShortName());
    	partyPositions.setVotesPercentage(partyElecResults.getVotesPercentage());
    	partyPositions.setCompleteVotesPercent(partyElecResults.getCompleteVotesPercent());
    	
		return partyPositions;
	}
	
	public ComparedReportVO getComparedElectionResults(Long partyId,Long elecIdOne,
			Long elecIdTwo,Long stateOrDistrictId,Boolean hasAlliances){
		
		log.debug("Inside getComparedElectionResults Method .......");
		
		ComparedReportVO comparedResultVO = null;
		Election elecYearOne = electionDAO.get(elecIdOne);
		Election elecYearTwo = electionDAO.get(elecIdTwo);
		
		if(Integer.parseInt(elecYearOne.getElectionYear()) < Integer.parseInt(elecYearTwo.getElectionYear())){
			Election temp;
			temp = elecYearOne;
			elecYearOne = elecYearTwo;
			elecYearTwo = temp;
		}
		
		String yearOne = elecYearOne.getElectionYear();
		String yearTwo = elecYearTwo.getElectionYear();
		String electionType = elecYearTwo.getElectionScope().getElectionType().getElectionType();
		List<Party> alliancPartiesForYearOne = null;
		List<Party> alliancPartiesForYearTwo = null;
		List<Long> partyIdsYearOne = null;
		List<Long> partyIdsYearTwo = null;
		List<PartyPositionsVO> posDetailsYearOne;
		List<PartyPositionsVO> posDetailsYearTwo;
		List<ConstituencyElection> constiElectionsForYearOne = null;
		List<ConstituencyElection> constiElectionsForYearTwo = null;
		Long stateId = stateOrDistrictId;
		String locationName="";
		if(IConstants.ASSEMBLY_ELECTION_TYPE.equalsIgnoreCase(electionType)){
			stateId = elecYearOne.getElectionScope().getState().getStateId();
			District district = districtDAO.get(stateOrDistrictId);
			locationName = district.getDistrictName();
		}
		else if(IConstants.PARLIAMENT_ELECTION_TYPE.equalsIgnoreCase(electionType)){
			State state = stateDAO.get(stateOrDistrictId);
			locationName = state.getStateName();
		}

		try{
			partyIdsYearOne = new ArrayList<Long>();
			partyIdsYearTwo = new ArrayList<Long>();
			
			posDetailsYearOne = new ArrayList<PartyPositionsVO>();
			posDetailsYearTwo = new ArrayList<PartyPositionsVO>();
			
		    if(elecYearOne != null && elecYearTwo != null){
			comparedResultVO = new ComparedReportVO();
			Party party = partyDAO.get(partyId);
			
			if(hasAlliances){
				alliancPartiesForYearOne = allianceGroupDAO.findAlliancePartiesByElectionAndPartyForState(elecIdOne, partyId, stateId);
				alliancPartiesForYearTwo = allianceGroupDAO.findAlliancePartiesByElectionAndPartyForState(elecIdTwo, partyId, stateId);
				if(alliancPartiesForYearOne == null || alliancPartiesForYearOne.size() == 0){
					alliancPartiesForYearOne = new ArrayList<Party>();
					alliancPartiesForYearOne.add(party);
				}
				if(alliancPartiesForYearTwo == null || alliancPartiesForYearTwo.size() == 0){
					alliancPartiesForYearTwo =  new ArrayList<Party>();
					alliancPartiesForYearTwo.add(party);
				}
			}
			else if(!hasAlliances){
				alliancPartiesForYearOne = new ArrayList<Party>();
				alliancPartiesForYearTwo =  new ArrayList<Party>();
				alliancPartiesForYearOne.add(party);
				alliancPartiesForYearTwo.add(party);
			}
			partyIdsYearOne = getAlliancPartyIds(alliancPartiesForYearOne);
			partyIdsYearTwo = getAlliancPartyIds(alliancPartiesForYearTwo);
			
			posDetailsYearOne = getPartyPositionsForADistrict(electionType, elecYearOne.getElectionId(),stateOrDistrictId,partyIdsYearOne);
			posDetailsYearTwo = getPartyPositionsForADistrict(electionType, elecYearTwo.getElectionId(),stateOrDistrictId,partyIdsYearTwo);
			
			if(IConstants.ASSEMBLY_ELECTION_TYPE.equalsIgnoreCase(electionType)){				
				constiElectionsForYearOne = nominationDAO.findConstituencyElectionByElectionIdAndDistrictIdAndPartys(
						elecYearOne.getElectionId(), stateOrDistrictId, partyIdsYearOne);
				constiElectionsForYearTwo = nominationDAO.findConstituencyElectionByElectionIdAndDistrictIdAndPartys(
						elecYearTwo.getElectionId(), stateOrDistrictId, partyIdsYearTwo);
			}else if(IConstants.PARLIAMENT_ELECTION_TYPE.equalsIgnoreCase(electionType)){
				constiElectionsForYearOne = nominationDAO.findConstituencyElectionByElectionIdAndStateAndPartys(
						elecYearOne.getElectionId(), stateOrDistrictId, partyIdsYearOne);
				constiElectionsForYearTwo = nominationDAO.findConstituencyElectionByElectionIdAndStateAndPartys(
						elecYearTwo.getElectionId(), stateOrDistrictId, partyIdsYearTwo);
			}
			
			
			log.debug("Total Consti In " + yearOne + " :" + constiElectionsForYearOne.size());
			log.debug("Total Consti In " + yearTwo + " :" + constiElectionsForYearTwo.size());
			
			if(constiElectionsForYearOne != null && constiElectionsForYearTwo != null)
			comparedResultVO = 	getDetailedComparedResults(yearOne,constiElectionsForYearOne,yearTwo,constiElectionsForYearTwo,
					partyId,partyIdsYearOne,partyIdsYearTwo,hasAlliances);
			comparedResultVO.setPositionsYearOne(posDetailsYearOne);
			comparedResultVO.setPositionsYearTwo(posDetailsYearTwo);
			comparedResultVO.setLocName(locationName);
		    }
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		return comparedResultVO;
	}
	
	public List<Long> getAlliancPartyIds(List<Party> alliancPartys) throws Exception{
		List<Long> partyIds = new ArrayList<Long>();
		for(Party party:alliancPartys){
			partyIds.add(party.getPartyId());
		}
	  return partyIds;
	}
	
	public List<PartyPositionsVO> getPartyPositionsForADistrict(String electionType, Long electionId, Long stateOrDistrictId, List<Long> partyIds){
		List<PartyPositionsVO> partyPositionsList = null;
		PartyPositionsVO partyPositionsVO = null;
		PartyElectionDistrictResult partyElectionResultParty = null;
		PartyElectionStateResult partyElectionStateResult = null;
		Long constiCount = 0l;
		List tempList = null;
				
		if(electionId != null && stateOrDistrictId != null){
			partyPositionsList = new ArrayList<PartyPositionsVO>();
			if(IConstants.ASSEMBLY_ELECTION_TYPE.equalsIgnoreCase(electionType)){	
				tempList = constituencyElectionDAO.findTotalConstituenciesCountInADistrictForAnElection(electionId, stateOrDistrictId);
				if(tempList.size() > 0)
					constiCount = Long.parseLong(((Object)tempList.get(0)).toString());
				for(Long partyId:partyIds){				
					partyElectionResultParty = staticDataService.getPartyElectionResultsForAPartyDistrictLevel(electionId, partyId, stateOrDistrictId);
				    if(partyElectionResultParty == null)
				    partyElectionResultParty = staticDataService.savePartyElectionResultForAPartyForAElectionDistrictLevel(electionId, partyId, stateOrDistrictId);
				    if(partyElectionResultParty != null){
				    	partyPositionsVO = getPartyPositionsForADistrict(partyElectionResultParty);
				    	partyPositionsVO.setTotalConstituencies(constiCount);
					    partyPositionsList.add(partyPositionsVO);
				    }
			     }
			}else if(IConstants.PARLIAMENT_ELECTION_TYPE.equalsIgnoreCase(electionType)){
				tempList = constituencyElectionDAO.findTotalAssemblyConstituencies(electionId, stateOrDistrictId);
				constiCount = Long.parseLong(tempList.size()+"");
				for(Long partyId:partyIds){				
					partyElectionStateResult = staticDataService.getPartyElectionResultsForAPartyStateLevelInParliamentElection(electionId, partyId, stateOrDistrictId);
				    if(partyElectionStateResult == null)
				    	partyElectionStateResult = staticDataService.savePartyElectionResultForAPartyForAParliamentElectionStateLevel(electionId, partyId, stateOrDistrictId);
				    if(partyElectionStateResult != null){
				    	partyPositionsVO = getPartyPositionsForAState(partyElectionStateResult);
				    	partyPositionsVO.setTotalConstituencies(constiCount);
				    	partyPositionsList.add(partyPositionsVO);
				    }   
			     }
			}
			
		}
    	
		return partyPositionsList;
	}
		
	private PartyPositionsVO getPartyPositionsForAState(PartyElectionStateResult partyElectionStateResult) {
		PartyPositionsVO partyPositions = new PartyPositionsVO();
		partyPositions.setTotalSeatsWon(new Long(partyElectionStateResult.getTotalSeatsWon()));
    	partyPositions.setSecondPosWon(new Long(partyElectionStateResult.getSecondPosWon()));
    	partyPositions.setThirdPosWon(new Long(partyElectionStateResult.getThirdPosWon()));
    	partyPositions.setFourthPosWon(new Long(partyElectionStateResult.getFourthPosWon()));
    	partyPositions.setNthPosWon(new Long(partyElectionStateResult.getNthPosWon()));
    	partyPositions.setTotalConstiParticipated(new Long(partyElectionStateResult.getTotalConstiParticipated()));
    	partyPositions.setPartyId(partyElectionStateResult.getParty().getPartyId());
    	partyPositions.setPartyName(partyElectionStateResult.getParty().getShortName());
    	partyPositions.setVotesPercentage(partyElectionStateResult.getVotesPercentage());
    	partyPositions.setCompleteVotesPercent(partyElectionStateResult.getCompleteVotesPercent());
    	
		return partyPositions;
	}

	public PartyPositionsVO getPartyPositionsForADistrict(PartyElectionDistrictResult partyElecResults){
		PartyPositionsVO partyPositions = new PartyPositionsVO();
		partyPositions.setTotalSeatsWon(new Long(partyElecResults.getTotalSeatsWon()));
    	partyPositions.setSecondPosWon(new Long(partyElecResults.getSecondPosWon()));
    	partyPositions.setThirdPosWon(new Long(partyElecResults.getThirdPosWon()));
    	partyPositions.setFourthPosWon(new Long(partyElecResults.getFourthPosWon()));
    	partyPositions.setNthPosWon(new Long(partyElecResults.getNthPosWon()));
    	partyPositions.setTotalConstiParticipated(new Long(partyElecResults.getTotalConstiParticipated()));
    	partyPositions.setPartyId(partyElecResults.getParty().getPartyId());
    	partyPositions.setPartyName(partyElecResults.getParty().getShortName());
    	partyPositions.setVotesPercentage(partyElecResults.getVotesPercentage());
    	partyPositions.setCompleteVotesPercent(partyElecResults.getCompleteVotesPercent());
    	
		return partyPositions;
	}
	
	@SuppressWarnings("unchecked")
	public ComparedReportVO getDetailedComparedResults(String yearOne,List<ConstituencyElection> constiElectionsForYearOne,
			String yearTwo,List<ConstituencyElection> constiElectionsForYearTwo,Long partyId,
			List<Long> alliancPartyIdsYearOne,List<Long> alliancPartyIdsYearTwo,Boolean hasAlliances){
		
		log.debug("Inside getDetailedComparedResults Method .......");
		
		ComparedReportVO comparedReportVO = null;
		ComparedReportVO comparedReportNewVO = null;
		List<PartyResultVO> notConsideredYearOneResults = null;
		List<PartyResultVO> notConsideredYearTwoResults = null;
		ComparedConstituencyElectionVO comparedConstituencyElectionVO = null;
		
		if(constiElectionsForYearOne != null && constiElectionsForYearTwo != null){
			comparedReportVO    = new ComparedReportVO();
			comparedReportNewVO = new ComparedReportVO();
						
			Map<Long,ComparedConstituencyElectionVO> comparedConstiElecMap = new HashMap<Long,ComparedConstituencyElectionVO>();
			Map<Long,Nomination> rebelsMap = new HashMap<Long,Nomination>();
						
			List<Long> constituencyIds = new ArrayList<Long>();
			for(ConstituencyElection constiElecOne:constiElectionsForYearOne){
				for(ConstituencyElection constiElecTwo:constiElectionsForYearTwo){
					if(constiElecTwo.getConstituency().getConstituencyId().equals(constiElecOne.getConstituency().getConstituencyId())){
						comparedConstituencyElectionVO = new ComparedConstituencyElectionVO();
						comparedConstituencyElectionVO.setConstiElecForYearOne(constiElecOne);
						comparedConstituencyElectionVO.setConstiElecForYearTwo(constiElecTwo);
						comparedConstituencyElectionVO.setYearOne(yearOne);
						comparedConstituencyElectionVO.setYearTwo(yearTwo);
						
						if(comparedConstiElecMap.isEmpty() || !comparedConstiElecMap.containsKey(constiElecOne.getConstituency().
								getConstituencyId()))
						comparedConstiElecMap.put(constiElecOne.getConstituency().getConstituencyId(), comparedConstituencyElectionVO);
									
						log.debug("Constituency ::" + constiElecOne.getConstituency().getName());
						constituencyIds.add(constiElecOne.getConstituency().getConstituencyId());
						break;
					}
				}
			}
			if(!comparedConstiElecMap.isEmpty()){
				List<ComparedConstituencyElectionVO> compConstiElecList = new ArrayList<ComparedConstituencyElectionVO>();
				Set entries = comparedConstiElecMap.entrySet();
				Iterator iterator = entries.iterator();
				while (iterator.hasNext()){
					Map.Entry entry = (Map.Entry)iterator.next();
					ComparedConstituencyElectionVO compConstiElec= (ComparedConstituencyElectionVO)entry.getValue();
					compConstiElecList.add(compConstiElec);
			    }
				comparedReportNewVO = getResultsForVotesGainedOrLost(compConstiElecList,yearOne,yearTwo,
						partyId,alliancPartyIdsYearOne,alliancPartyIdsYearTwo,hasAlliances);
				
			}
				
			notConsideredYearOneResults = getNotconsideredConstituencies(constiElectionsForYearOne,yearOne,constituencyIds,
					partyId,alliancPartyIdsYearOne,hasAlliances, yearTwo, true);
			notConsideredYearTwoResults = getNotconsideredConstituencies(constiElectionsForYearTwo,yearTwo,constituencyIds,
					partyId,alliancPartyIdsYearTwo,hasAlliances, yearOne, false);
			
			comparedReportVO.setVotesPercentGainedResults(comparedReportNewVO.getVotesPercentGainedResults());
			comparedReportVO.setVotesPercentLostResults(comparedReportNewVO.getVotesPercentLostResults());
			comparedReportVO.setNotConsideredYearOneResults(notConsideredYearOneResults);
			comparedReportVO.setNotConsideredYearTwoResults(notConsideredYearTwoResults);
			comparedReportVO.setYearOne(yearOne);
			comparedReportVO.setYearTwo(yearTwo);
		}
		
		return comparedReportVO;
	}
	
	public ComparedReportVO getResultsForVotesGainedOrLost(List<ComparedConstituencyElectionVO> compConstiElecList ,
			String elecYearOne,String elecYearTwo,Long partyId,List<Long> alliancPartyIdsYearOne,
			List<Long> alliancPartyIdsYearTwo,Boolean hasAlliances){
		ComparedReportVO comparedReportVO = null;
		
		log.debug("Inside getResultsForVotesGainedOrLost Method .......");
		
		if(compConstiElecList != null){
			Set<Nomination> nominationsForYearOne = null;
			Set<Nomination> nominationsForYearTwo = null;
			List<ComparedElectionResultVO> votesPercentGainedResultsList = new ArrayList<ComparedElectionResultVO>();
		    List<ComparedElectionResultVO> votesPercentLostResultsList = new ArrayList<ComparedElectionResultVO>();
			comparedReportVO = new ComparedReportVO();
			for(ComparedConstituencyElectionVO compConstiResults:compConstiElecList){
				nominationsForYearOne = compConstiResults.getConstiElecForYearOne().getNominations();
				nominationsForYearTwo = compConstiResults.getConstiElecForYearTwo().getNominations();
				Nomination selectedNominationYearOne = null;
				Nomination selectedNominationYearTwo = null;
				
				if(!hasAlliances){
					selectedNominationYearOne = getSelectedNominationWithoutAllianc(nominationsForYearOne,partyId);
					selectedNominationYearTwo = getSelectedNominationWithoutAllianc(nominationsForYearTwo,partyId);
				}
				else if(hasAlliances){
				    selectedNominationYearOne = getSelectedNominationWithAllianc(nominationsForYearOne,partyId,
				    		alliancPartyIdsYearOne,hasAlliances);
					selectedNominationYearTwo = getSelectedNominationWithAllianc(nominationsForYearTwo,partyId,
							alliancPartyIdsYearTwo,hasAlliances);
				}	
			    if(selectedNominationYearOne != null && selectedNominationYearTwo != null){
					Double votesEarnedYearOne = selectedNominationYearOne.getCandidateResult().getVotesEarned();
					Double votesEarnedYearTwo = selectedNominationYearTwo.getCandidateResult().getVotesEarned();
					Double validVotesYearOne = selectedNominationYearOne.getConstituencyElection()
						.getConstituencyElectionResult().getValidVotes();
					Double validVotesYearTwo = selectedNominationYearTwo.getConstituencyElection()
						.getConstituencyElectionResult().getValidVotes();
					
					Double votesPercentYearOne = validVotesYearOne > 0 ?new BigDecimal((votesEarnedYearOne/validVotesYearOne)*100).setScale(
							2,BigDecimal.ROUND_HALF_UP).doubleValue():0.0;
					Double votesPercentYearTwo = validVotesYearTwo > 0 ?new BigDecimal((votesEarnedYearTwo/validVotesYearTwo)*100).setScale(
							2,BigDecimal.ROUND_HALF_UP).doubleValue():0.0;
					Double votesPercentDiff = new BigDecimal(votesPercentYearOne - votesPercentYearTwo).setScale(
							2,BigDecimal.ROUND_HALF_UP).doubleValue();

					log.debug("Votes% One" + votesPercentYearOne);
					log.debug("Votes% Two" + votesPercentYearTwo);
					
					if(votesPercentDiff > 0){
						ComparedElectionResultVO votesPercentGainedResults = getResultDetailsIntoVO(selectedNominationYearOne,
								selectedNominationYearTwo,votesPercentYearOne,votesPercentYearTwo,votesPercentDiff);
						votesPercentGainedResultsList.add(votesPercentGainedResults);
					}
					else if(votesPercentDiff <= 0){
						ComparedElectionResultVO votesPercentLostResults = 	getResultDetailsIntoVO(selectedNominationYearOne,
								selectedNominationYearTwo,votesPercentYearOne,votesPercentYearTwo,votesPercentDiff);
						votesPercentLostResultsList.add(votesPercentLostResults);
					}
			    }
			}
			comparedReportVO.setVotesPercentGainedResults(votesPercentGainedResultsList);
			comparedReportVO.setVotesPercentLostResults(votesPercentLostResultsList);
		}
		
		return comparedReportVO;
	}
	
	public ComparedElectionResultVO getResultDetailsIntoVO(Nomination selectedNominationYearOne,
			Nomination selectedNominationYearTwo,Double votesPercentYearOne,Double votesPercentYearTwo,Double votesPercentDiff){
		ComparedElectionResultVO votesPercentResults = new ComparedElectionResultVO();
		
		log.debug("Inside getResultDetailsIntoVO Method .......");
		
		votesPercentResults.setCandName(selectedNominationYearOne.getCandidate().getLastname());
		votesPercentResults.setConstiName(selectedNominationYearOne.getConstituencyElection().getConstituency().getName());
		votesPercentResults.setConstituencyId(selectedNominationYearOne.getConstituencyElection().getConstituency().getConstituencyId());
		votesPercentResults.setElectionType(selectedNominationYearOne.getConstituencyElection().getConstituency().
				getElectionScope().getElectionType().getElectionType());
		votesPercentResults.setVotesEarned(selectedNominationYearOne.getCandidateResult().getVotesEarned().longValue());
		votesPercentResults.setVotesPercent(votesPercentYearOne.toString());
		votesPercentResults.setPartyName(selectedNominationYearOne.getParty().getShortName());
		votesPercentResults.setRank(selectedNominationYearOne.getCandidateResult().getRank());
		votesPercentResults.setSecndCandName(selectedNominationYearTwo.getCandidate().getLastname());
		votesPercentResults.setSecndConstiName(selectedNominationYearTwo.getConstituencyElection().getConstituency().getName());
		votesPercentResults.setVotesEarnedBySecnd(selectedNominationYearTwo.getCandidateResult().getVotesEarned().longValue());
		votesPercentResults.setSecndVotesPercent(votesPercentYearTwo.toString());
		votesPercentResults.setSecndCandPartyName(selectedNominationYearTwo.getParty().getShortName());
		votesPercentResults.setSecndCandRank(selectedNominationYearTwo.getCandidateResult().getRank());
		votesPercentResults.setVotesPercentDiff(votesPercentDiff.toString());
		
		return votesPercentResults;
	}
	
	public Nomination getSelectedNominationWithoutAllianc(Set<Nomination> nominations,Long partyId){
		Nomination nomination = null;
		for(Nomination nominatn:nominations){
		if(nominatn.getParty().getPartyId().equals(partyId))
		return nominatn;
		}
	return nomination;
	}
	
	public Nomination getSelectedNominationWithAllianc(Set<Nomination> nominations,Long partyId,List<Long> alliancPartyIds,
			Boolean hasAlliances){
		Nomination nomination = null;

		for(Nomination nominatn:nominations){
			if(nominatn.getParty().getPartyId().equals(partyId))
				return nominatn;
			
			if(alliancPartyIds.contains(nominatn.getParty().getPartyId())){
				System.out.println(nomination+"::"+nominatn.getConstituencyElection().getConstituency().getName());
				if(nomination == null){
					nomination = nominatn;	
				}
				else 
				if(nomination != null && nomination.getCandidateResult().getRank() > nominatn.getCandidateResult().getRank()){
					nomination = nominatn;	
				}
			}
		}

		return nomination;
	}
	
	public List<PartyResultVO> getNotconsideredConstituencies(List<ConstituencyElection> constiElections,String elecYear,
			List<Long> comparedConstiIds,Long partyId,List<Long> alliancPartyIds,Boolean hasAllianc, String elecYearTest, Boolean isLatestYear){
		List<PartyResultVO> notConsideredResults = null;
		log.debug("Inside getNotconsideredConstituencies Method (for" + elecYear +" ).......");
		
		if(constiElections != null){
			notConsideredResults = new ArrayList<PartyResultVO>();
			for(ConstituencyElection constiElecResults:constiElections)
				if(!comparedConstiIds.contains(constiElecResults.getConstituency().getConstituencyId()))
					notConsideredResults.add(getPartyResultsForNotConsideredConsti(constiElecResults,partyId,alliancPartyIds,hasAllianc,elecYearTest, isLatestYear));
		}
		
		return notConsideredResults;
	}
	
	public PartyResultVO getPartyResultsForNotConsideredConsti(ConstituencyElection constiElection,Long partyId,List<Long> alliancPartyIds,
			Boolean hasAllianc, String elecYearTest, Boolean isLatestYear){
		PartyResultVO partyResultVO = null;
		String constiName = "";
		List list = nominationDAO.getResultsForElectionInConstituency(constiElection.getConstituency().getConstituencyId(), elecYearTest);
		if(constiElection != null){
			Nomination selectdNomination = null;
			partyResultVO = new PartyResultVO();
			
			Set<Nomination> selectedNominations = constiElection.getNominations();
			if(hasAllianc)
			selectdNomination = getSelectedNominationWithAllianc(selectedNominations,partyId,alliancPartyIds,hasAllianc);
			else if(!hasAllianc)
			selectdNomination = getSelectedNominationWithoutAllianc(selectedNominations,partyId);
			
			if(selectdNomination != null){
				constiName = selectdNomination.getConstituencyElection().getConstituency().getName();
				partyResultVO.setCandidateName(selectdNomination.getCandidate().getLastname());
				partyResultVO.setConstituencyName(constiName);
				partyResultVO.setVotesEarned(selectdNomination.getCandidateResult().getVotesEarned().longValue());
				partyResultVO.setVotesPercent(selectdNomination.getCandidateResult().getVotesPercengate());
				partyResultVO.setPartyName(selectdNomination.getParty().getShortName());
				partyResultVO.setRank(selectdNomination.getCandidateResult().getRank());
				if(list.size() == 0 && isLatestYear)
					partyResultVO.setReason(IConstants.NEW_CONSTITUENCY);
				else if(list.size() == 0 && !isLatestYear)
					partyResultVO.setReason(IConstants.DEFORMED_CONSTITUENCY);
				else
					partyResultVO.setReason(IConstants.PARTY_NOT_PARTICIPATED);
			}
		}
		
		return partyResultVO;
	}
	
	public void electionComparision(Long partyId, Long electionId, Boolean isAssembly, Long stateOrCountryId){
		List allianceParties = null;
		PartyElectionStateResult partyStateResult;
		PartyElectionDistrictResult partyDistrictResult;
		Group group = null;
		if(isAssembly){
			List<SelectOptionVO> stateOrDistList = staticDataService.getDistricts(stateOrCountryId);
			allianceParties = allianceGroupDAO.findAlliancePartiesByElectionStateAndPartyExcludeParty(electionId, partyId, stateOrCountryId);
			if(allianceParties.size() > 0) 
				group = (Group)((Object[])allianceParties.get(0))[0];
			for(SelectOptionVO district:stateOrDistList){
				partyDistrictResult = staticDataService.getPartyElectionResultsForAPartyDistrictLevel(electionId, 
						partyId, district.getId());
				if(partyDistrictResult == null)
					partyDistrictResult = staticDataService.savePartyElectionResultForAPartyForAElectionDistrictLevel(electionId, 
							partyId, district.getId());
				if(partyDistrictResult != null && group != null)
					getAllAllianceCandidatesForAPartyInAState(null, partyDistrictResult, group, getAlliancePartiesIdsWithComma(allianceParties), false);
			}
		}else{
			List states = stateDAO.findStatesByCountryId(stateOrCountryId);
			for(Object[] state:(List<Object[]>)states){
				allianceParties = allianceGroupDAO.findAlliancePartiesByElectionStateAndPartyExcludeParty(electionId, partyId, Long.parseLong(state[0].toString()));
				if(allianceParties.size() > 0) 
					group = (Group)((Object[])allianceParties.get(0))[0];
				partyStateResult = staticDataService.getPartyElectionResultsForAPartyStateLevelInParliamentElection(electionId, 
						partyId, Long.parseLong(state[0].toString()));
				if(partyStateResult == null)
					partyStateResult = staticDataService.savePartyElectionResultForAPartyForAParliamentElectionStateLevel(electionId, 
							partyId, Long.parseLong(state[0].toString()));
				if(partyStateResult != null && group != null)
					getAllAllianceCandidatesForAPartyInAState(partyStateResult, null, group, getAlliancePartiesIdsWithComma(allianceParties), true);	
			}
		}
		
	}
	
	public String getAlliancePartiesIdsWithComma(List parties){
		StringBuilder partiesIds = new StringBuilder();
		if(parties != null)
			for(Object[] values:(List<Object[]>)parties)
				partiesIds.append(",").append(values[2].toString());
		
		if(partiesIds.length() > 0)
			return partiesIds.toString().substring(1);
		else
			return "";
	}
	
	public void getAllAllianceCandidatesForAPartyInAState(PartyElectionStateResult partyElectionStateResult, 
			PartyElectionDistrictResult partyElectionDistrictResult, Group group, String alliancePartiesIds, Boolean isState){
			Double totalVotesEarned=0d;
			Double totalParticiptedValidVotes=0d;
			Double totalValidVotes = 0d;
			Long firstPosition=0l;
			Long secondPosition=0l;
			Long thirdPosition=0l;
			Long fourthPosition=0l;
			Long nthPosition=0l;
			Double totalParticipatedPercentage = new Double(0);
			Double totalConstiPercentage = new Double(0);
			Long totalConstituenciesParticipated=0l;
			List<ConstituencyElection> allianceConstituencies = null;
			Election election = null;
			Party party = null;
			State state = null;
			District district = null;
			
			if(isState){
				election = partyElectionStateResult.getElection();
				party = partyElectionStateResult.getParty();
				state = partyElectionStateResult.getState();
				List checkList = partyElectionStateResultWithAllianceDAO.getPartyResultsByStateIdAndElectionId(election.getElectionId(), 
						party.getPartyId(), state.getStateId());
				if(checkList.size() > 0)
					return;
				totalValidVotes = (Double)constituencyElectionDAO.findTotalValidVotesForAnElectionForAnState(election.getElectionId(), 
						state.getStateId()).get(0);
				if(alliancePartiesIds.length() > 0)
					allianceConstituencies = nominationDAO.getAllAllianceConstituenciesForAPartyInAStateForAnElection(election.getElectionId(),
						alliancePartiesIds,party.getPartyId(), state.getStateId());
				
			}else{
				election = partyElectionDistrictResult.getElection();
				party = partyElectionDistrictResult.getParty();
				district = partyElectionDistrictResult.getDistrict();
				List checkList = partyElectionDistrictResultWithAllianceDAO.getPartyResultsForADistrictByPartyIdAndElectionId(election.getElectionId(), 
						party.getPartyId(), district.getDistrictId());
				if(checkList.size() > 0)
					return;
				totalValidVotes = (Double)constituencyElectionDAO.findTotalValidVotesForAnElectionForAStateAndDistrict(election.getElectionId(),
						district.getDistrictId()).get(0);
				if(alliancePartiesIds.length() > 0)
					allianceConstituencies = nominationDAO.getAllAllianceConstituenciesForAPartyInADistrict(election.getElectionId(), alliancePartiesIds, 
						party.getPartyId(), district.getDistrictId());	
			}
			
			if(allianceConstituencies != null)
				for(ConstituencyElection consti : allianceConstituencies){			
					Set<Nomination> list = consti.getNominations();				
					
					Nomination highestRankParty =  getNominationForAlliancePartyThatGotHighestRank(list,alliancePartiesIds);
					
					totalConstituenciesParticipated++;
					
					totalVotesEarned+=highestRankParty.getCandidateResult().getVotesEarned();
					totalParticiptedValidVotes+=consti.getConstituencyElectionResult().getValidVotes();
					
					
					switch(highestRankParty.getCandidateResult().getRank().intValue()){
							 
							case 1 		: 	firstPosition++;
							  		 		break;
							  		 	
							case 2 		: 	secondPosition++;
				  		 					break;
				  		 				
							case 3 		: 	thirdPosition++;
											break;
			
							case 4 		: 	fourthPosition++;
				  		 					break;
				  		 					
				  		 	default 	:	nthPosition++;
				  		 					break;
					}			
				}
			
			if(isState){
				firstPosition+=new Long(partyElectionStateResult.getTotalSeatsWon());
				secondPosition+=new Long(partyElectionStateResult.getSecondPosWon());
				thirdPosition+=new Long(partyElectionStateResult.getThirdPosWon());
				fourthPosition+=new Long(partyElectionStateResult.getFourthPosWon());
				nthPosition+=new Long(partyElectionStateResult.getNthPosWon());
				totalConstituenciesParticipated+=new Long(partyElectionStateResult.getTotalConstiParticipated());
				totalVotesEarned+=partyElectionStateResult.getTotalVotesGained();
				totalParticiptedValidVotes+=partyElectionStateResult.getTotalValidVotes();
				totalParticipatedPercentage = new BigDecimal((totalVotesEarned*100)/totalParticiptedValidVotes).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				totalConstiPercentage = new BigDecimal((totalVotesEarned*100)/totalValidVotes).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				savePartyElectionStateResult(election , party, state, firstPosition,secondPosition,thirdPosition,fourthPosition,nthPosition,
						totalConstituenciesParticipated,totalParticipatedPercentage,totalConstiPercentage,totalVotesEarned,totalParticiptedValidVotes,totalValidVotes, group);
			}else{
				firstPosition+=new Long(partyElectionDistrictResult.getTotalSeatsWon());
				secondPosition+=new Long(partyElectionDistrictResult.getSecondPosWon());
				thirdPosition+=new Long(partyElectionDistrictResult.getThirdPosWon());
				fourthPosition+=new Long(partyElectionDistrictResult.getFourthPosWon());
				nthPosition+=new Long(partyElectionDistrictResult.getNthPosWon());
				totalConstituenciesParticipated+=new Long(partyElectionDistrictResult.getTotalConstiParticipated());
				totalVotesEarned+=partyElectionDistrictResult.getTotalVotesGained();
				totalParticiptedValidVotes+=partyElectionDistrictResult.getTotalValidVotes();
				totalParticipatedPercentage = new BigDecimal((totalVotesEarned*100)/totalParticiptedValidVotes).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				totalConstiPercentage = new BigDecimal((totalVotesEarned*100)/totalValidVotes).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				savePartyElectionDistrictResult(election, party, district, firstPosition, secondPosition, thirdPosition, fourthPosition, 
						nthPosition, totalConstituenciesParticipated,totalParticipatedPercentage,
						totalConstiPercentage,totalVotesEarned,totalParticiptedValidVotes,totalValidVotes, group);
			}
			
	}	
	

	public Nomination getNominationForAlliancePartyThatGotHighestRank(Set<Nomination> list,String allianceParties){
		List<Nomination> selectedList = new ArrayList<Nomination>(0);
		Long rank=0l;
		for(Nomination nomination : list)		
			if(allianceParties.contains(nomination.getParty().getPartyId().toString()))
				selectedList.add(nomination);	
		Collections.sort(selectedList, new NominatResultsComparator());
		return selectedList.get(0);		
	}
	
	public PartyElectionDistrictResultWithAlliance savePartyElectionDistrictResult(final Election election,final Party party,final District district,
			final Long totalSeatsWon,final Long secPos,final Long thirdPos,final Long fourthPos,
			final Long nthPos,final Long totConstiParticipated,final Double totalVotesPercentage,
			final Double completeVotesPercent,final Double totalVotesEarned,final Double totalValidVotes,
			final Double completeConstiValidVotes,final Group group){
		
		PartyElectionDistrictResultWithAlliance partyResultWithAlliance = (PartyElectionDistrictResultWithAlliance)transactionTemplate.execute(new TransactionCallback() {

				public Object doInTransaction(TransactionStatus status) {
					PartyElectionDistrictResultWithAlliance partyElectionStateResult = null;
					try{
						java.util.Date updatedDate = new java.util.Date();
						String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
						SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
						String strDateNew = sdf.format(updatedDate) ;
						updatedDate = sdf.parse(strDateNew);
						
						partyElectionStateResult = new PartyElectionDistrictResultWithAlliance();
						partyElectionStateResult.setParty(party);
						partyElectionStateResult.setElection(election);
						partyElectionStateResult.setDistrict(district);					
						partyElectionStateResult.setGroup(group);					
						partyElectionStateResult.setTotalSeatsWon(totalSeatsWon);
						partyElectionStateResult.setSecondPosWon(secPos);
						partyElectionStateResult.setThirdPosWon(thirdPos);
						partyElectionStateResult.setFourthPosWon(fourthPos);
						partyElectionStateResult.setNthPosWon(nthPos);
						partyElectionStateResult.setTotalVotesGained(totalVotesEarned);
						partyElectionStateResult.setTotalValidVotes(totalValidVotes);
						partyElectionStateResult.setCompleteConstiValidVotes(completeConstiValidVotes);
						partyElectionStateResult.setCompleteVotesPercent(completeVotesPercent.toString());
						partyElectionStateResult.setVotesPercentage(totalVotesPercentage.toString());
						partyElectionStateResult.setTotalConstiParticipated(totConstiParticipated);
						partyElectionStateResult.setLastUpdated(updatedDate);
						
						partyElectionStateResult = partyElectionDistrictResultWithAllianceDAO.save(partyElectionStateResult);
						
					}catch(Exception ex){
						ex.printStackTrace();
			        	log.debug("Exception Raised : " + ex);
			        	status.setRollbackOnly();
					}
				 return partyElectionStateResult;
				}
				
			});
		  return partyResultWithAlliance;
		}
	
	public PartyElectionStateResultWithAlliance savePartyElectionStateResult(final Election election,final Party party,final State state,final Long totalSeatsWon,final Long secPos,final Long thirdPos,final Long fourthPos,final Long nthPos,final Long totConstiParticipated,final Double totalVotesPercentage,final Double completeVotesPercent,final Double totalVotesGained,final Double totalValidVotes,final Double completeConstiValidVotes,final Group group){
		
	PartyElectionStateResultWithAlliance partyResultWithAlliance = (PartyElectionStateResultWithAlliance)transactionTemplate.execute(new TransactionCallback() {

			public Object doInTransaction(TransactionStatus status) {
				PartyElectionStateResultWithAlliance partyElectionStateResult = null;
				try{
					java.util.Date updatedDate = new java.util.Date();
					String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
					SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
					String strDateNew = sdf.format(updatedDate) ;
					updatedDate = sdf.parse(strDateNew);
					
					partyElectionStateResult = new PartyElectionStateResultWithAlliance();
					partyElectionStateResult.setParty(party);
					partyElectionStateResult.setElection(election);
					partyElectionStateResult.setState(state);					
					partyElectionStateResult.setGroup(group);					
					partyElectionStateResult.setTotalSeatsWon(totalSeatsWon);
					partyElectionStateResult.setSecondPosWon(secPos);
					partyElectionStateResult.setThirdPosWon(thirdPos);
					partyElectionStateResult.setFourthPosWon(fourthPos);
					partyElectionStateResult.setNthPosWon(nthPos);
					partyElectionStateResult.setTotalVotesGained(totalVotesGained);
					partyElectionStateResult.setTotalValidVotes(totalValidVotes);
					partyElectionStateResult.setCompleteConstiValidVotes(completeConstiValidVotes);
					partyElectionStateResult.setCompleteVotesPercent(completeVotesPercent.toString());
					partyElectionStateResult.setVotesPercentage(totalVotesPercentage.toString());
					partyElectionStateResult.setTotalConstiParticipated(totConstiParticipated);
					partyElectionStateResult.setLastUpdated(updatedDate);
					
					partyElectionStateResult = partyElectionStateResultWithAllianceDAO.save(partyElectionStateResult);
					
				}catch(Exception ex){
					ex.printStackTrace();
		        	log.debug("Exception Raised : " + ex);
		        	status.setRollbackOnly();
				}
			 return partyElectionStateResult;
			}
			
		});
	  return partyResultWithAlliance;
	}

}
