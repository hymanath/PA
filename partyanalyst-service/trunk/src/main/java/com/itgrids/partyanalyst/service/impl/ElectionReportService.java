/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on March 31,2010
 */
package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionResultDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionAllianceDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPartyElectionDistrictResultDAO;
import com.itgrids.partyanalyst.dao.IPartyElectionResultDAO;
import com.itgrids.partyanalyst.dao.IPartyElectionResultsWithAreaTypeDAO;
import com.itgrids.partyanalyst.dao.IPartyElectionResultsWithGenderAnalysisDAO;
import com.itgrids.partyanalyst.dao.IPartyElectionStateResultDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.IStateRegionDAO;
import com.itgrids.partyanalyst.dao.hibernate.PartyDAO;
import com.itgrids.partyanalyst.dto.AlliancePartyDistrictResultsVO;
import com.itgrids.partyanalyst.dto.AlliancePartyResultsVO;
import com.itgrids.partyanalyst.dto.DistrictWisePartyPositionsVO;
import com.itgrids.partyanalyst.dto.ElectionResultsInAllDistrictsVO;
import com.itgrids.partyanalyst.dto.ElectionResultsReportVO;
import com.itgrids.partyanalyst.dto.ElectionResultsVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultVO;
import com.itgrids.partyanalyst.dto.PartyPositionsInDistrictVO;
import com.itgrids.partyanalyst.dto.PartyPositionsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyElectionDistrictResult;
import com.itgrids.partyanalyst.model.PartyElectionResult;
import com.itgrids.partyanalyst.model.PartyElectionResultsWithAreaType;
import com.itgrids.partyanalyst.model.PartyElectionResultsWithGenderAnalysis;
import com.itgrids.partyanalyst.model.PartyElectionStateResult;
import com.itgrids.partyanalyst.service.IElectionReportService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.PartyElecDistrictNamesComparator;
import com.itgrids.partyanalyst.utils.PartyElecDistrictResultsComparator;
import com.itgrids.partyanalyst.utils.PartyElectionResultsComparator;
import com.itgrids.partyanalyst.utils.SortNameComparator;

public class ElectionReportService implements IElectionReportService {
	
	private IElectionDAO electionDAO;
	private IDistrictDAO districtDAO;
	private IStateDAO stateDAO;
	private INominationDAO nominationDAO;
	private IStaticDataService staticDataService;
	private IAllianceGroupDAO allianceGroupDAO;
	private IElectionAllianceDAO electionAllianceDAO;
	private IPartyElectionResultDAO partyElectionResultDAO;
	private IPartyElectionStateResultDAO partyElectionStateResultDAO;
	private IPartyElectionDistrictResultDAO partyElectionDistrictResultDAO;
	private IStateRegionDAO stateRegionDAO;
	private IConstituencyElectionResultDAO constituencyElectionResultDAO;
	private IPartyElectionResultsWithGenderAnalysisDAO partyElectionResultsWithGenderAnalysisDAO;
	private IPartyDAO partyDAO;
	private TransactionTemplate transactionTemplate;
	private IPartyElectionResultsWithAreaTypeDAO partyElectionResultsWithAreaTypeDAO;
	
	public IPartyElectionResultsWithAreaTypeDAO getPartyElectionResultsWithAreaTypeDAO() {
		return partyElectionResultsWithAreaTypeDAO;
	}

	public void setPartyElectionResultsWithAreaTypeDAO(
			IPartyElectionResultsWithAreaTypeDAO partyElectionResultsWithAreaTypeDAO) {
		this.partyElectionResultsWithAreaTypeDAO = partyElectionResultsWithAreaTypeDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}

	public IPartyElectionResultsWithGenderAnalysisDAO getPartyElectionResultsWithGenderAnalysisDAO() {
		return partyElectionResultsWithGenderAnalysisDAO;
	}

	public void setPartyElectionResultsWithGenderAnalysisDAO(
			IPartyElectionResultsWithGenderAnalysisDAO partyElectionResultsWithGenderAnalysisDAO) {
		this.partyElectionResultsWithGenderAnalysisDAO = partyElectionResultsWithGenderAnalysisDAO;
	}

	private final static Logger log = Logger.getLogger(ElectionReportService.class);
	
	public IConstituencyElectionResultDAO getConstituencyElectionResultDAO() {
		return constituencyElectionResultDAO;
	}

	public void setConstituencyElectionResultDAO(
			IConstituencyElectionResultDAO constituencyElectionResultDAO) {
		this.constituencyElectionResultDAO = constituencyElectionResultDAO;
	}

	public IStateRegionDAO getStateRegionDAO() {
		return stateRegionDAO;
	}

	public void setStateRegionDAO(IStateRegionDAO stateRegionDAO) {
		this.stateRegionDAO = stateRegionDAO;
	}

	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public IAllianceGroupDAO getAllianceGroupDAO() {
		return allianceGroupDAO;
	}

	public void setAllianceGroupDAO(IAllianceGroupDAO allianceGroupDAO) {
		this.allianceGroupDAO = allianceGroupDAO;
	}

	
	public IElectionAllianceDAO getElectionAllianceDAO() {
		return electionAllianceDAO;
	}

	public void setElectionAllianceDAO(IElectionAllianceDAO electionAllianceDAO) {
		this.electionAllianceDAO = electionAllianceDAO;
	}

	public IPartyElectionResultDAO getPartyElectionResultDAO() {
		return partyElectionResultDAO;
	}

	public void setPartyElectionResultDAO(
			IPartyElectionResultDAO partyElectionResultDAO) {
		this.partyElectionResultDAO = partyElectionResultDAO;
	}

	public IPartyElectionStateResultDAO getPartyElectionStateResultDAO() {
		return partyElectionStateResultDAO;
	}

	public void setPartyElectionStateResultDAO(
			IPartyElectionStateResultDAO partyElectionStateResultDAO) {
		this.partyElectionStateResultDAO = partyElectionStateResultDAO;
	}

	public IPartyElectionDistrictResultDAO getPartyElectionDistrictResultDAO() {
		return partyElectionDistrictResultDAO;
	}

	public void setPartyElectionDistrictResultDAO(
			IPartyElectionDistrictResultDAO partyElectionDistrictResultDAO) {
		this.partyElectionDistrictResultDAO = partyElectionDistrictResultDAO;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IElectionReportService#getBasicResultsForAnElection(java.lang.String, java.lang.String, java.lang.Long)
	 * Method returns all parties election results,such as seats won,participated,lost,2nd pos,3rd pos ....
	 */
	@SuppressWarnings("unchecked")
	public ElectionResultsReportVO getBasicResultsForAnElection(
			String electionType, String electionYear, Long stateId,String votesPercentMargin) {
		
		if(log.isDebugEnabled())
		    log.debug("Successfully Entered Into getBasicResultsForAnElection() Method ....");
		
		Long electionID = null;
		List partiesParticipated = null;
		ResultStatus resultStatus = new ResultStatus();
		List<PartyPositionsVO> allPartiesResults = null;
		List<DistrictWisePartyPositionsVO> allPartiesResultsInDistricts = null;
		ElectionResultsReportVO electionResultsReportVO = null;
		Map<Long,PartyElectionResult> participatedPartysResults = new HashMap<Long,PartyElectionResult>();
		Map<Long,PartyElectionResult> participatedPartysTotalResults = new HashMap<Long,PartyElectionResult>();
		
		Map<Long,List<PartyElectionDistrictResult>> participatedDistrictWiseResults = new HashMap<Long,List<PartyElectionDistrictResult>>();
		Map<Long,List<PartyElectionDistrictResult>> participatedDistrictWiseTotalResults = new HashMap<Long,List<PartyElectionDistrictResult>>();
		
		Map<Long,List<PartyElectionStateResult>> participatedStateWiseResults = new HashMap<Long,List<PartyElectionStateResult>>();
		Map<Long,List<PartyElectionStateResult>> participatedStateWiseTotalResults = new HashMap<Long,List<PartyElectionStateResult>>();

		List<PartyPositionsVO> overallPartyResultsWithoutAllianc = null;
		List<DistrictWisePartyPositionsVO> overallPartyResultsDistrictwiseWithoutAllianc = null;
		
		
		try{
			allPartiesResults = new ArrayList<PartyPositionsVO>();
			allPartiesResultsInDistricts = new ArrayList<DistrictWisePartyPositionsVO>();
			electionID = getElectionIdForAElectionTypeAndYear(electionType,electionYear,stateId);
			
			//get Participated Parties in an election
			if(electionID != null)
			partiesParticipated = nominationDAO.getPartyIdAndShortNameForThatParticipatedInAElection(electionID);
			log.debug("Participated Parties Size :" + partiesParticipated.size());
			
			List partysCountInPartyElecResultTable = partyElectionResultDAO.getParticipatedPartysCountForAnElection(electionID);
			Long countVal = getRawListCount(partysCountInPartyElecResultTable);
			List partysCountInPartyElecDistrictResultTable = partyElectionDistrictResultDAO.getParticipatedPartysCountForAnElection(electionID);
			List partysCountInPartyElecStateResultTable = partyElectionStateResultDAO.getParticipatedPartysCountForAnElection(electionID);
			Long countDistrictVal = getRawListCount(partysCountInPartyElecDistrictResultTable);
			Long countStateVal = getRawListCount(partysCountInPartyElecStateResultTable);
			List<SelectOptionVO> districtsInfo = null;
			List<SelectOptionVO> statesInfo = null;
			SelectOptionVO parties = null;
			
			if(!electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE)){
			districtsInfo = getDistrictsInfoInAState(stateId);
			}
			
			if(electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE)){
			statesInfo = getStatesInfoInACountry(IConstants.COUNTRY_INDIA_ID);
			}
			
			log.debug("PartyElectionResults Count    :" + countVal);
			log.debug("PartyDistElectionResults Count:" + countDistrictVal);
			log.debug("PartyStateElectionResults Count:" + countStateVal);

			
			//check and insert data into intermediate tables for PartyElectionResult & PartyElectionDistrictResult
		if(partiesParticipated != null && countVal != null && countDistrictVal != null && countStateVal != null){
		    if(partiesParticipated.size() != countVal.intValue() || partiesParticipated.size() != countDistrictVal.intValue() || partiesParticipated.size() != countStateVal.intValue()){
				  for(int i=0;i<partiesParticipated.size();i++){
					Object[] params = (Object[])partiesParticipated.get(i);
					
					parties = new SelectOptionVO();
					parties.setId((Long)params[0]);
					parties.setName((String)params[1]);
				   
				   if(parties.getId() != null){
					 if(partiesParticipated.size() != countVal.intValue()){
					 PartyElectionResult partyElecResult = staticDataService.getPartyElectionResultsForAParty(electionID,parties.getId());
					 if(partyElecResult == null)
					 staticDataService.savePartyElectionResultForAPartyForAElection(electionID, parties.getId());
					 }
					 //district wise results for non parliament elections
					 if(!electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE)){
					   if(partiesParticipated.size() != countDistrictVal.intValue()){
					     if(districtsInfo != null && districtsInfo.size() > 0){
					     for(SelectOptionVO districtWiseResults:districtsInfo){
					     PartyElectionDistrictResult partyElecDistrictResult = staticDataService.getPartyElectionResultsForAPartyDistrictLevel(electionID, parties.getId(), districtWiseResults.getId());
						 if(partyElecDistrictResult == null)
					     staticDataService.savePartyElectionResultForAPartyForAElectionDistrictLevel(electionID, parties.getId(), districtWiseResults.getId());
						 }
						 }
					   }
					 }
					//state wise results for  parliament elections
					if(electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE)){
						if(partiesParticipated.size() != countStateVal.intValue()){
							if(statesInfo != null && statesInfo.size() > 0){
								for(SelectOptionVO stateWiseDetails:statesInfo){
								PartyElectionStateResult partyElectionStateResult = staticDataService.getPartyElectionResultsForAPartyStateLevelInParliamentElection(electionID, parties.getId(), stateWiseDetails.getId());
								if(partyElectionStateResult == null)
								staticDataService.savePartyElectionResultForAPartyForAParliamentElectionStateLevel(electionID, parties.getId(), stateWiseDetails.getId());
								}
							}
						}
					}
					 
				   }
				  }
			  }
			      
		  }
			
		    //populate election results
			List<PartyElectionDistrictResult> partyElectionDistrictResultsForParties = null;
			List<PartyElectionStateResult> partyElectionStateResultsForParties = null;
			
			List<PartyElectionResult> partyElectionResultsForParties = partyElectionResultDAO.getByElectionIdAndVotesPercentMargin(electionID, votesPercentMargin);
			if(!electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE)){
				partyElectionDistrictResultsForParties = partyElectionDistrictResultDAO.getDistrictWiseAllPartiesResults(electionID, votesPercentMargin);
			}
			if(electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE)){
				partyElectionStateResultsForParties = partyElectionStateResultDAO.getStateWiseAllPartiesResults(electionID, votesPercentMargin);
			}
			
			if(partyElectionResultsForParties != null && partyElectionResultsForParties.size() > 0){
				participatedPartysResults = getPartyElectionResultsProcessedToMap(partyElectionResultsForParties);
				participatedPartysTotalResults.putAll(participatedPartysResults);
			}
			
			if(partyElectionDistrictResultsForParties != null && partyElectionDistrictResultsForParties.size() > 0){
				participatedDistrictWiseResults = getPartyElectionDistrictResultsProcessedToMap(partyElectionDistrictResultsForParties);
				participatedDistrictWiseTotalResults.putAll(participatedDistrictWiseResults);
			}
			
			if(partyElectionStateResultsForParties != null && partyElectionStateResultsForParties.size() > 0){
				participatedStateWiseResults = getPartyElectionStateResultsProcessedToMap(partyElectionStateResultsForParties);
				participatedStateWiseTotalResults.putAll(participatedStateWiseResults);
			}
						
			//get allianc Parties Details and results
			List<AlliancePartyResultsVO> partiesInAlliancResults = new ArrayList<AlliancePartyResultsVO>();
			List<AlliancePartyDistrictResultsVO> districtwisePartiesInAlliancResults = new ArrayList<AlliancePartyDistrictResultsVO>();
			List alliancGroups = electionAllianceDAO.findGroupIdByElection(electionID);
			AlliancePartyResultsVO alliancePartyResults = null;
			AlliancePartyDistrictResultsVO alliancePartyDistrictwiseResults = new AlliancePartyDistrictResultsVO();
			List<PartyPositionsVO> alliancPartyResultsForAGroup =  new ArrayList<PartyPositionsVO>();
			List<DistrictWisePartyPositionsVO> districtWiseAlliancPartysResults = new ArrayList<DistrictWisePartyPositionsVO>();
			PartyPositionsVO partyPositions = null;
			PartyElectionResult partyElectionResult = null;
			List<PartyElectionDistrictResult> partyElecDistResultList = null;
			DistrictWisePartyPositionsVO distWisePartyResults = null;
			List<PartyElectionStateResult> partyElecStateResultList = null;
			DistrictWisePartyPositionsVO stateWisePartyResults = null;
			
			if(alliancGroups != null && alliancGroups.size() > 0){
				List<SelectOptionVO> alliancPartys = null;
				
				for(int i=0;i<alliancGroups.size();i++){
					Object[] params = (Object[])alliancGroups.get(i);
					Long groupID = (Long)params[0];
					String groupName = (String)params[1];
					
					alliancPartys = getPartiesAsSelectOptionVOForAnAlliancGroup(groupID,electionID);
					if(alliancPartys != null){
						
						alliancePartyResults = new AlliancePartyResultsVO();
						alliancePartyDistrictwiseResults = new AlliancePartyDistrictResultsVO();
						alliancPartyResultsForAGroup =  new ArrayList<PartyPositionsVO>();
						districtWiseAlliancPartysResults = new ArrayList<DistrictWisePartyPositionsVO>();
						
						log.debug("Alliance Partys ......" + alliancPartys.size());
						for(SelectOptionVO alliancParty:alliancPartys){
							
							//Overall results map processing
							if(!participatedPartysResults.isEmpty()){
								if(participatedPartysResults.containsKey(alliancParty.getId())){
									partyElectionResult = participatedPartysResults.get(alliancParty.getId());
									partyPositions = getElectionResultsForAParty(partyElectionResult);
									alliancPartyResultsForAGroup.add(partyPositions);
									//participatedPartysResults.remove(alliancParty.getId());
								}
								else{
									partyElectionResult = staticDataService.getPartyElectionResultsForAParty(electionID,alliancParty.getId());
									partyPositions = getElectionResultsForAParty(partyElectionResult);
									alliancPartyResultsForAGroup.add(partyPositions);
								}
							}
							
							//District wise Results map processing for non parliament elections
							if(!participatedDistrictWiseResults.isEmpty() && !electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE)){
								if(participatedDistrictWiseResults.containsKey(alliancParty.getId())){
									partyElecDistResultList = participatedDistrictWiseResults.get(alliancParty.getId());
									distWisePartyResults = getDistrictWiseProcessedResultsForAParty(partyElecDistResultList);
									Collections.sort(distWisePartyResults.getPartyResultsInDistricts(), new PartyElecDistrictNamesComparator());
									districtWiseAlliancPartysResults.add(distWisePartyResults);
									//participatedDistrictWiseResults.remove(alliancParty.getId());
								}
								else{
									partyElecDistResultList = partyElectionDistrictResultDAO.getPartyElecResultsInAllDistsForAParty(electionID,alliancParty.getId());
									distWisePartyResults = getDistrictWiseProcessedResultsForAParty(partyElecDistResultList);
									Collections.sort(distWisePartyResults.getPartyResultsInDistricts(), new PartyElecDistrictNamesComparator());
									districtWiseAlliancPartysResults.add(distWisePartyResults);
								}
							}
							
							//state wise Results map processing for parliament elections
							if(!participatedStateWiseResults.isEmpty() && electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE)){
								if(participatedStateWiseResults.containsKey(alliancParty.getId())){
									partyElecStateResultList = participatedStateWiseResults.get(alliancParty.getId());
									stateWisePartyResults = getStateWiseProcessedResultsForAParty(partyElecStateResultList);
									Collections.sort(stateWisePartyResults.getPartyResultsInDistricts(), new PartyElecDistrictNamesComparator());
									districtWiseAlliancPartysResults.add(stateWisePartyResults);
									//participatedStateWiseResults.remove(alliancParty.getId());
								}
								else{
									partyElecStateResultList	 = partyElectionStateResultDAO.getResultsInAllStatesForAParty(electionID, alliancParty.getId());
									stateWisePartyResults = getStateWiseProcessedResultsForAParty(partyElecStateResultList);
									Collections.sort(stateWisePartyResults.getPartyResultsInDistricts(), new PartyElecDistrictNamesComparator());
									districtWiseAlliancPartysResults.add(stateWisePartyResults);
								}
							}
						}
						
						log.debug("Size :" + alliancPartyResultsForAGroup.size());
						//overall results
						alliancePartyResults.setYear(electionYear);
						alliancePartyResults.setAllianceGroupName(groupName);
						alliancePartyResults.setElectionType(electionType);
						alliancePartyResults.setPartiesInAlliance(alliancPartyResultsForAGroup);
						partiesInAlliancResults.add(alliancePartyResults);
						
						//district wise results
						alliancePartyDistrictwiseResults.setAllianceGroupName(groupName);
						alliancePartyDistrictwiseResults.setPartiesInAlliance(districtWiseAlliancPartysResults);
						districtwisePartiesInAlliancResults.add(alliancePartyDistrictwiseResults);
						allPartiesResults.add(getElectionResultsGrandTotalForAlliancParty(alliancePartyResults));
						allPartiesResultsInDistricts.add(getDistrictWiseElectionResultsGrandTotalForAlliancParty(alliancePartyDistrictwiseResults));
					}
				}
				//Overall Results Processing into List<PartyPositionsVO> without grouping of alliance
			    overallPartyResultsWithoutAllianc = getProcessedMapForAllPartiesResults(participatedPartysTotalResults);
			    if(overallPartyResultsWithoutAllianc != null)
			     Collections.sort(overallPartyResultsWithoutAllianc, new PartyElectionResultsComparator());
			    if(!electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE))
				 overallPartyResultsDistrictwiseWithoutAllianc = getProcessedMapForAllPartiesDistrictwiseResults(participatedDistrictWiseTotalResults);
			    else if(electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE))
			     overallPartyResultsDistrictwiseWithoutAllianc = getProcessedMapForAllPartiesStatewiseResults(participatedStateWiseTotalResults);
			    if(overallPartyResultsDistrictwiseWithoutAllianc != null)
                 Collections.sort(overallPartyResultsDistrictwiseWithoutAllianc, new PartyElecDistrictResultsComparator());
			}
			
			//Overall Results Processing into List<PartyPositionsVO> by grouping of alliance 
			List<PartyPositionsVO> overallPartyResults = getProcessedMapForAllPartiesResults(participatedPartysResults);
			if(overallPartyResults != null && overallPartyResults.size() > 0){
				for(PartyPositionsVO results:overallPartyResults){
				if(results.getPartyName().equals("IND"))
				results.setVotesPercentage("--");
				allPartiesResults.add(results);
				}
			}
			
			//Overall District wise Results Processing into List<PartyPositionsVO> by grouping of alliance
			List<DistrictWisePartyPositionsVO> overallPartyResultsDistrictwise = null;
			if(!electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE))
			overallPartyResultsDistrictwise = getProcessedMapForAllPartiesDistrictwiseResults(participatedDistrictWiseResults);
			else if(electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE))
			overallPartyResultsDistrictwise = getProcessedMapForAllPartiesStatewiseResults(participatedStateWiseResults);
			
			if(overallPartyResultsDistrictwise != null && overallPartyResultsDistrictwise.size() > 0){
				for(DistrictWisePartyPositionsVO results:overallPartyResultsDistrictwise){
				for(PartyPositionsInDistrictVO result:results.getPartyResultsInDistricts()){
				if(results.getPartyName().equals("IND"))
				result.setVotesPercentage("--");
				}
				allPartiesResultsInDistricts.add(results);
				}
			}
			
						
			electionResultsReportVO = new ElectionResultsReportVO();
			ElectionResultsVO electionBasicResultsVO = new ElectionResultsVO();
			
			Collections.sort(allPartiesResults, new PartyElectionResultsComparator());
			electionBasicResultsVO.setElectionType(electionType);
			electionBasicResultsVO.setElectionYear(electionYear);
			electionBasicResultsVO.setAllPartiesResults(allPartiesResults);
			electionBasicResultsVO.setAlliancePartiesList(partiesInAlliancResults);
			electionBasicResultsVO.setAllPartiesResultsWithoutGroupingOfAllianc(overallPartyResultsWithoutAllianc);
			
						
			//////////////////////////////////////////////////////////////////////////////////////////
			log.debug("District Wise Results List Size :" + allPartiesResultsInDistricts.size());
			List<SelectOptionVO> partiesList = new ArrayList<SelectOptionVO>();
			SelectOptionVO partyOption = null;
			for(DistrictWisePartyPositionsVO resultData:allPartiesResultsInDistricts){
				partyOption = new SelectOptionVO();
				partyOption.setId(resultData.getPartyId());
				partyOption.setName(resultData.getPartyName());
				partiesList.add(partyOption);
			}
			
			//set participated districts list to mainVO
			if(!electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE)){
			Collections.sort(districtsInfo, new SortNameComparator());
			electionResultsReportVO.setPartiDistList(districtsInfo);
			}
			//set participated states list to mainVO
			if(electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE)){
			Collections.sort(statesInfo, new SortNameComparator());
			electionResultsReportVO.setPartiDistList(statesInfo);
			}
			//set participated parties list to main vo
			Collections.sort(partiesList, new SortNameComparator());
			electionResultsReportVO.setPartiPartiesList(partiesList);
			
			Collections.sort(allPartiesResultsInDistricts, new PartyElecDistrictResultsComparator());
			ElectionResultsInAllDistrictsVO electionResultsInAllDistrictsVO = new ElectionResultsInAllDistrictsVO();
			electionResultsInAllDistrictsVO.setAllPartiesResults(allPartiesResultsInDistricts);
			electionResultsInAllDistrictsVO.setAlliancePartiesList(districtwisePartiesInAlliancResults);
			electionResultsInAllDistrictsVO.setAllPartiesResultsWithoutGroupingOfAllianc(overallPartyResultsDistrictwiseWithoutAllianc);
						
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			electionResultsReportVO.setElectionType(electionType);
			electionResultsReportVO.setElectionYear(electionYear);
			electionResultsReportVO.setResultStatus(resultStatus);
			electionResultsReportVO.setElectionBasicResultsVO(electionBasicResultsVO);
			electionResultsReportVO.setElectionResultsInDistricts(electionResultsInAllDistrictsVO);
			
			setVOForDistrictWiseChart(electionResultsReportVO);
			
			log.debug("Parties Results Size:" + allPartiesResults.size());
			log.debug("Parties Results DistrictWise Size:" + allPartiesResultsInDistricts.size());
				
		}catch(Exception ex){
			ex.printStackTrace();
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			electionResultsReportVO = new ElectionResultsReportVO();
			electionResultsReportVO.setResultStatus(resultStatus);
		}
		
		participatedPartysResults = null;
		participatedPartysTotalResults = null;
		
		participatedDistrictWiseResults = null;
		participatedDistrictWiseTotalResults = null;
		
		participatedStateWiseResults = null;
		participatedStateWiseTotalResults = null;
		
		partiesParticipated = null;
		allPartiesResults = null;
		
		allPartiesResultsInDistricts = null;
		overallPartyResultsWithoutAllianc = null;
		overallPartyResultsDistrictwiseWithoutAllianc = null;
		
		return electionResultsReportVO;
	}
	
	@SuppressWarnings("unchecked")
	public Long getRawListCount(List value){
		Long count = new Long(0);
		if(value != null){
			Object params = (Object)value.get(0);
			count = (Long)params;
		}
		return count;
	}
	
	
	public void setVOForDistrictWiseChart(ElectionResultsReportVO electionResultsReportVO){
		
		List<SelectOptionVO> participatedParties = new ArrayList<SelectOptionVO>();
		Map<String,String> participatedDistrictsMap = new HashMap<String,String>();
		List<DistrictWisePartyPositionsVO> partyResultsDistWiseForChart = new ArrayList<DistrictWisePartyPositionsVO>();
		DistrictWisePartyPositionsVO partyResInADistrict = null;
		List<PartyPositionsInDistrictVO> partyResultsInDistricts = null;
		PartyPositionsInDistrictVO partyResInSubRegion = null;
		
		if(electionResultsReportVO.getElectionResultsInDistricts() != null){
			
			int index = -1;
			int subRegionsCount = 0;
			if(electionResultsReportVO.getElectionResultsInDistricts().getAllPartiesResults() != null && electionResultsReportVO.getElectionResultsInDistricts().getAllPartiesResults().size() > 0){
				
				for(DistrictWisePartyPositionsVO partiesParticipat:electionResultsReportVO.getElectionResultsInDistricts().getAllPartiesResults()){
					participatedParties.add(new SelectOptionVO(partiesParticipat.getPartyId(),partiesParticipat.getPartyName()));
					
					if(partiesParticipat.getPartyResultsInDistricts().size() > subRegionsCount){
						index++;
						subRegionsCount = partiesParticipat.getPartyResultsInDistricts().size();
					}
				}
			}
			
			//Districts Info
			if(subRegionsCount > 0){
				DistrictWisePartyPositionsVO partiDistricts = electionResultsReportVO.getElectionResultsInDistricts().getAllPartiesResults().get(index);
				if(partiDistricts != null && partiDistricts.getPartyResultsInDistricts() != null){
					for(PartyPositionsInDistrictVO districtsPart:partiDistricts.getPartyResultsInDistricts()){
						participatedDistrictsMap.put(districtsPart.getDistrictName(), districtsPart.getDistrictName());
					}
				}
			}
			
						
			for(String districts:participatedDistrictsMap.keySet()){
				
				partyResInADistrict = new DistrictWisePartyPositionsVO();
				partyResultsInDistricts = new ArrayList<PartyPositionsInDistrictVO>();
				
				String regionName = participatedDistrictsMap.get(districts);
				partyResInADistrict.setPartyId(0L);
				partyResInADistrict.setPartyName(regionName);
				
				for(DistrictWisePartyPositionsVO processedRes:electionResultsReportVO.getElectionResultsInDistricts().getAllPartiesResults()){
					
					partyResInSubRegion = new PartyPositionsInDistrictVO();
					partyResInSubRegion.setDistrictId(processedRes.getPartyId());
					partyResInSubRegion.setDistrictName(processedRes.getPartyName());
					Boolean partyFlag = false;
					
					for(PartyPositionsInDistrictVO dist:processedRes.getPartyResultsInDistricts()){
						
						if(dist.getDistrictName().equalsIgnoreCase(regionName)){
							partyFlag = true;
							partyResInSubRegion.setSeatsWon(dist.getSeatsWon());
							partyResInSubRegion.setCompleteVotesPercentDouble(new Double(dist.getCompleteVotesPercent()));
							dist.setCompleteVotesPercentDouble(new Double(dist.getCompleteVotesPercent()));
						}
					}
					
					if(partyFlag.equals(false)){
						partyResInSubRegion.setSeatsWon(0L);
						partyResInSubRegion.setCompleteVotesPercentDouble(new Double(0));
					}
					partyResultsInDistricts.add(partyResInSubRegion);
				}
				
				partyResInADistrict.setPartyResultsInDistricts(partyResultsInDistricts);
				
				partyResultsDistWiseForChart.add(partyResInADistrict);
			}
			
			electionResultsReportVO.setPartiesDistLevel(participatedParties);
			electionResultsReportVO.setPartyResultsforDistWiseChart(partyResultsDistWiseForChart);
		}
	}
	/*
	 * All Parties Results are Processed and set to PartyPositionsVO
	 */
	@SuppressWarnings("unchecked")
	public List<PartyPositionsVO> getProcessedMapForAllPartiesResults(Map<Long,PartyElectionResult> participatedPartysResults){
		List<PartyPositionsVO> allPartiesOverallResults = null;
		
		if(!participatedPartysResults.isEmpty()){
			allPartiesOverallResults = new ArrayList<PartyPositionsVO>();
			Set entries = participatedPartysResults.entrySet();
			Iterator iterator = entries.iterator();
			while(iterator.hasNext()){
			Map.Entry entry = (Map.Entry)iterator.next();
			PartyElectionResult partyElectionResult = (PartyElectionResult)entry.getValue();
			PartyPositionsVO partyPositions = getElectionResultsForAParty(partyElectionResult);
			allPartiesOverallResults.add(partyPositions);
			}
		}
		return allPartiesOverallResults;
	}
	
	/*
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<DistrictWisePartyPositionsVO> getProcessedMapForAllPartiesDistrictwiseResults(Map<Long,List<PartyElectionDistrictResult>> participatedDistrictWiseResults){
		List<DistrictWisePartyPositionsVO> allPartiesResultsInDistricts = null;
		
		if(!participatedDistrictWiseResults.isEmpty()){
			allPartiesResultsInDistricts = new ArrayList<DistrictWisePartyPositionsVO>();
			Set entries = participatedDistrictWiseResults.entrySet();
			Iterator iterator = entries.iterator();
			while(iterator.hasNext()){
			Map.Entry entry = (Map.Entry)iterator.next();
			List<PartyElectionDistrictResult> partyElecDistResult = (List<PartyElectionDistrictResult>)entry.getValue();
			DistrictWisePartyPositionsVO distWisePartyResults = getDistrictWiseProcessedResultsForAParty(partyElecDistResult);
			Collections.sort(distWisePartyResults.getPartyResultsInDistricts(), new PartyElecDistrictNamesComparator());
			allPartiesResultsInDistricts.add(distWisePartyResults);
			} 
		}
		
		
		return allPartiesResultsInDistricts;
	}
	
	/*
	 * Processing Map For StateWise Results For Parliament election
	 */
	@SuppressWarnings("unchecked")
	public List<DistrictWisePartyPositionsVO> getProcessedMapForAllPartiesStatewiseResults(Map<Long,List<PartyElectionStateResult>> participatedStateWiseResults){
		List<DistrictWisePartyPositionsVO> allPartiesResultsInStates = null;
		
		if(!participatedStateWiseResults.isEmpty()){
			allPartiesResultsInStates = new ArrayList<DistrictWisePartyPositionsVO>();
			Set entries = participatedStateWiseResults.entrySet();
			Iterator iterator = entries.iterator();
			while(iterator.hasNext()){
			Map.Entry entry = (Map.Entry)iterator.next();
			List<PartyElectionStateResult> partyElecStateResult = (List<PartyElectionStateResult>)entry.getValue();
			DistrictWisePartyPositionsVO stateWisePartyResults = getStateWiseProcessedResultsForAParty(partyElecStateResult);
			Collections.sort(stateWisePartyResults.getPartyResultsInDistricts(), new PartyElecDistrictNamesComparator());
			allPartiesResultsInStates.add(stateWisePartyResults);
			} 
		}
		
		
		return allPartiesResultsInStates;
	}
	
	
	
	/*
	 * Returns PartyElectionResults list processed to map
	 */
	public Map<Long,PartyElectionResult> getPartyElectionResultsProcessedToMap(List<PartyElectionResult> partyElectionResultsForParties){
		Map<Long,PartyElectionResult> partyElecResults = null;
		if(partyElectionResultsForParties != null && partyElectionResultsForParties.size() > 0){
			partyElecResults = new HashMap<Long,PartyElectionResult>();
			for(PartyElectionResult elecResults:partyElectionResultsForParties){
				partyElecResults.put(elecResults.getParty().getPartyId(), elecResults);
			}
		}
	 return partyElecResults;
	}
	
	/*
	 * Returns PartyElectionDistrictResults list processed to map
	 */
	public Map<Long,List<PartyElectionDistrictResult>> getPartyElectionDistrictResultsProcessedToMap(List<PartyElectionDistrictResult> partyElectionDistrictResultsForParties){
		Map<Long,List<PartyElectionDistrictResult>> partyElecDistResults = null;
		if(partyElectionDistrictResultsForParties != null && partyElectionDistrictResultsForParties.size() > 0){
			partyElecDistResults = new HashMap<Long,List<PartyElectionDistrictResult>>();
			for(PartyElectionDistrictResult elecResults:partyElectionDistrictResultsForParties){
				Long partyId = elecResults.getParty().getPartyId();
				if(partyElecDistResults.containsKey(partyId)){
					List<PartyElectionDistrictResult> partyElecDistrictResults = partyElecDistResults.get(partyId);
					partyElecDistrictResults.add(elecResults);
					partyElecDistResults.put(partyId, partyElecDistrictResults);
				}
				else{
					List<PartyElectionDistrictResult> partyElecDistrictResults = new ArrayList<PartyElectionDistrictResult>();
					partyElecDistrictResults.add(elecResults);
					partyElecDistResults.put(partyId, partyElecDistrictResults);
				}
			}
		}
	 return partyElecDistResults;
	}
	
	/*
	 * Returns PartyElectionStateResults list processed to map
	 */
	public Map<Long,List<PartyElectionStateResult>> getPartyElectionStateResultsProcessedToMap(List<PartyElectionStateResult> partyElectionStateResultsForParties){
		Map<Long,List<PartyElectionStateResult>> partyElecStateResults = null;
		if(partyElectionStateResultsForParties != null && partyElectionStateResultsForParties.size() > 0){
			partyElecStateResults = new HashMap<Long,List<PartyElectionStateResult>>();
			for(PartyElectionStateResult elecResults:partyElectionStateResultsForParties){
				Long partyId = elecResults.getParty().getPartyId();
				if(partyElecStateResults.containsKey(partyId)){
					List<PartyElectionStateResult> partyElectionStateResults = partyElecStateResults.get(partyId);
					partyElectionStateResults.add(elecResults);
					partyElecStateResults.put(partyId, partyElectionStateResults);
				}
				else{
					List<PartyElectionStateResult> partyElectionStateResults = new ArrayList<PartyElectionStateResult>();
					partyElectionStateResults.add(elecResults);
					partyElecStateResults.put(partyId, partyElectionStateResults);
				}
			}
		}
		
		return partyElecStateResults;
	}
	
	/*
	 * Returns ElectionID for a Particular Election Of ElectionType and ElectionYear
	 */
	@SuppressWarnings("unchecked")
	public Long getElectionIdForAElectionTypeAndYear(String electionType,String electionYear,Long stateId){
		
		if(log.isDebugEnabled())
	        log.debug("Inside getElectionIdForAElectionTypeAndYear() Method ..");
		
		List elec = null;
		Long electionID = null;
		if(electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE))
		elec = electionDAO.findParliamentElectionIdByElectionTypeAndYear(electionType, electionYear);
		else elec = electionDAO.findElectionIdByElectionTypeAndYear(electionType, electionYear, stateId);
		
		if(elec != null && elec.size() > 0){
			Object params = (Object)elec.get(0);
			electionID = (Long)params;
		}
		return electionID;
	}
	
	/*
	 * Returns Set Of Parties As SelectOptionVO In A Particular Allianc Group And For A Particular Election
	 */
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getPartiesAsSelectOptionVOForAnAlliancGroup(Long groupId,Long electionId){
		
		if(log.isDebugEnabled())
		    log.debug("Inside getPartiesAsSelectOptionVOForAnAlliancGroup() Method ..");
		
		List<SelectOptionVO> allaincParties = null;
		if(groupId != null && electionId != null){
			List partys = allianceGroupDAO.findPartysByGroupId(groupId);
			if(partys != null && partys.size() > 0){
				allaincParties = new ArrayList<SelectOptionVO>();
				for(int i=0;i<partys.size();i++){
					Object[] params = (Object[])partys.get(i);
					SelectOptionVO partySelectOptn = new SelectOptionVO();
					partySelectOptn.setId((Long)params[0]);
					partySelectOptn.setName((String)params[1]);
					
					allaincParties.add(partySelectOptn);
				}
			}
		}
		return allaincParties;
	}
	
	/*
	 * Returns Basic Election Results For A Party
	 */
	public PartyPositionsVO getElectionResultsForAParty(PartyElectionResult partyElectionResult){
		
		if(log.isDebugEnabled())
		    log.debug("Inside getElectionResultsForAParty() Method ..");
		PartyPositionsVO partyResults = null;
			
		if(partyElectionResult != null){
			partyResults = new PartyPositionsVO();
			Party party = partyElectionResult.getParty();
			
			partyResults.setPartyId(party.getPartyId());
			if(party.getShortName() != null)
			partyResults.setPartyName(party.getShortName());
			else
			partyResults.setPartyName(party.getLongName());
			partyResults.setNthPosWon(new Long(partyElectionResult.getNthPosWon()));
			partyResults.setVotesPercentage(partyElectionResult.getVotesPercentage());
			partyResults.setThirdPosWon(new Long(partyElectionResult.getThirdPosWon()));
			partyResults.setFourthPosWon(new Long(partyElectionResult.getFourthPosWon()));
			partyResults.setSecondPosWon(new Long(partyElectionResult.getSecondPosWon()));
			partyResults.setTotalSeatsWon(new Long(partyElectionResult.getTotalSeatsWon()));
			partyResults.setCompleteVotesPercent(partyElectionResult.getCompleteVotesPercent());
			partyResults.setTotalConstiParticipated(new Long(partyElectionResult.getTotalConstiParticipated()));
			partyResults.setTotalVotesEarned(partyElectionResult.getTotalVotesGained().longValue());
			partyResults.setTotalValidVotes(partyElectionResult.getTotalValidVotes().longValue());
			partyResults.setTotalConstiValidVotes(partyElectionResult.getCompleteConstiValidVotes().longValue());
					
		}
	 return partyResults;
	}
	
	/*
	 * Returns sum of totalValidVotes,polledVotes and votingPercentage for statewise  Results ...
	 */
	
	public PartyPositionsVO getCompleteStatewiseVotersInfoForAnElection(Long electionId)
	{
		try
		{
		PartyPositionsVO partyResults =null;
		
		List<Object[]> totalCount = constituencyElectionResultDAO.findTotalVotesAndValidVotesAndPolledVotesAndVotesPercentage(electionId);
		
		if(totalCount != null && totalCount.size() > 0)
		{
			partyResults = new PartyPositionsVO();
			
			Object[] params = totalCount.get(0);
			partyResults.setTotalVotesForState(new Double((Double)params[0]).longValue());
			partyResults.setTotalValidVotesForState(new Double((Double)params[1]).longValue());
			partyResults.setTotalPolledVotesForState(new Double((Double)params[2]).longValue());
			
			if(partyResults.getTotalVotesForState() < partyResults.getTotalPolledVotesForState())
				  return null;
			
			partyResults.setTotalVotingPercentageForState(new BigDecimal((Double)params[2]*100/(Double)params[0]).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			partyResults.setTotalSeatsParticipated((Long)(Long)params[3]);
		}
		
		return partyResults;
		}catch(Exception e)
		{
			return null;
		}
	}
	
	public List<PartyPositionsVO> getVotersDataOfTwoElections(Long electionId)
	{
		try
		{
			List<PartyPositionsVO> votersList = null;
			PartyPositionsVO voterData1 = null;
			PartyPositionsVO voterData2 = null;
			votersList = new ArrayList<PartyPositionsVO>(0);
			
			Election elect = electionDAO.get(electionId);
			
			if(elect.getElecSubtype().equalsIgnoreCase(IConstants.ELECTION_SUBTYPE_BYE))
				return null;
			voterData1 = getCompleteStatewiseVotersInfoForAnElection(electionId);
			
			if(voterData1 != null)
			{
				voterData1.setPartyName(elect.getElectionYear());
				votersList.add(voterData1);
			}
			
			List<Object[]> list = electionDAO.getPreviousElectionIdAndYear(electionId);
			
			if(list != null && list.size() > 0)
			{
				voterData2 = getCompleteStatewiseVotersInfoForAnElection((Long)list.get(0)[0]);
				
				if(voterData2 != null)
				{
					voterData2.setPartyName(list.get(0)[1].toString());
					votersList.add(voterData2);
				}
			}
			return votersList;
		}catch(Exception e)
		{
			return null;
		}
	}
	
	/*
	 * Returns Aggregate Sum Of Allianc Party Results ...
	 */
	public PartyPositionsVO getElectionResultsGrandTotalForAlliancParty(AlliancePartyResultsVO alliancePartyResultsVO){
		
		if(log.isDebugEnabled())
		    log.debug("Inside getElectionResultsGrandTotalForAlliancParty() Method ..");
		PartyPositionsVO partyResults = null;
		
		Long seatsWonAgg = new Long(0);
		Long secndPosAgg = new Long(0);
		Long thirdPosAgg = new Long(0);
		Long fourthPosAgg = new Long(0);
		Long nthPosAgg = new Long(0);
		Double votesPercent = new Double(0);
		Double overallVotesPercent = new Double(0);
		Long totalVotesEarned = new Long(0);
		Long totalValidVotes = new Long(0);
		Long totalConstiValidVotes = new Long(0);
					
		if(alliancePartyResultsVO != null){
			partyResults = new PartyPositionsVO();
			
			log.debug("Allianc Partys Results Size :" + alliancePartyResultsVO.getPartiesInAlliance().size());
			
			for(PartyPositionsVO partyPos:alliancePartyResultsVO.getPartiesInAlliance()){
				log.debug("Inside Loop :" + partyPos);
				seatsWonAgg+=partyPos.getTotalSeatsWon();
				secndPosAgg+=partyPos.getSecondPosWon();
				thirdPosAgg+=partyPos.getThirdPosWon();
				fourthPosAgg+=partyPos.getFourthPosWon();
				nthPosAgg+=partyPos.getNthPosWon();
				votesPercent+=new Double(partyPos.getVotesPercentage());
				overallVotesPercent+=new Double(partyPos.getCompleteVotesPercent());
				totalVotesEarned+=partyPos.getTotalVotesEarned();
				totalValidVotes+=partyPos.getTotalValidVotes();
				
				totalConstiValidVotes = partyPos.getTotalConstiValidVotes();
			}
			partyResults.setPartyName(alliancePartyResultsVO.getAllianceGroupName());
			partyResults.setTotalSeatsWon(seatsWonAgg);
			partyResults.setSecondPosWon(secndPosAgg);
			partyResults.setThirdPosWon(thirdPosAgg);
			partyResults.setFourthPosWon(fourthPosAgg);
			partyResults.setNthPosWon(nthPosAgg);

			Double votesPercnt = null;
			Double completVotesPercnt = null;
			
			if(!totalValidVotes.equals(new Long(0)))
			votesPercnt = new BigDecimal((totalVotesEarned.doubleValue()/totalValidVotes.doubleValue())*100.0).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			if(!totalConstiValidVotes.equals(new Long(0)))
			completVotesPercnt = new BigDecimal((totalVotesEarned.doubleValue()/totalConstiValidVotes.doubleValue())*100.0).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			
			if(votesPercnt != null && completVotesPercnt != null){
			partyResults.setVotesPercentage(votesPercnt.toString());
			partyResults.setCompleteVotesPercent(completVotesPercnt.toString());
			}
		}
	 return partyResults;
	}
	
	/*
	 * Returns district wise Aggregate Sum Of Allianc Party Results for all districts...
	 */
	public DistrictWisePartyPositionsVO getDistrictWiseElectionResultsGrandTotalForAlliancParty(AlliancePartyDistrictResultsVO alliancePartyResultsVO){
		
		if(log.isDebugEnabled())
		    log.debug("Inside getDistrictWiseElectionResultsGrandTotalForAlliancParty() Method ..");
		DistrictWisePartyPositionsVO partyPositionsVO = null;
		
		if(alliancePartyResultsVO != null){
			Map<Long,PartyPositionsInDistrictVO> partyPosInDistrict = new HashMap<Long,PartyPositionsInDistrictVO>();			
			partyPositionsVO = new DistrictWisePartyPositionsVO();
			partyPositionsVO.setPartyName(alliancePartyResultsVO.getAllianceGroupName());
			for(DistrictWisePartyPositionsVO partyPositions:alliancePartyResultsVO.getPartiesInAlliance()){
				for(PartyPositionsInDistrictVO partyPosInDist:partyPositions.getPartyResultsInDistricts()){
					if(partyPosInDistrict.containsKey(partyPosInDist.getDistrictId())){
						PartyPositionsInDistrictVO partyResultsInDistrict = partyPosInDistrict.get(partyPosInDist.getDistrictId());
						partyResultsInDistrict = getProcessedResults(partyResultsInDistrict,partyPosInDist);
						partyPosInDistrict.put(partyPosInDist.getDistrictId(), partyResultsInDistrict);
					}
					else if(partyPosInDistrict.isEmpty() || !partyPosInDistrict.containsKey(partyPosInDist.getDistrictId())){
						partyPosInDistrict.put(partyPosInDist.getDistrictId(), partyPosInDist);
					}
				}
			}
			if(!partyPosInDistrict.isEmpty()){
			List<PartyPositionsInDistrictVO> partyPosInDis = getDistrictWiseResultsList(partyPosInDistrict);
			Collections.sort(partyPosInDis, new PartyElecDistrictNamesComparator());
			partyPositionsVO.setPartyResultsInDistricts(partyPosInDis);
			partyPositionsVO.setTotSeatsWonInAllPartiDistricts(new Long(100));
			}
		}
		return partyPositionsVO;
	}
	
	/*
	 * Map Processing
	 */
	@SuppressWarnings("unchecked")
	public List<PartyPositionsInDistrictVO> getDistrictWiseResultsList(Map<Long,PartyPositionsInDistrictVO> resultsMap){
		List<PartyPositionsInDistrictVO> partyPositionsResult = null;
		if(!resultsMap.isEmpty()){
			partyPositionsResult = new ArrayList<PartyPositionsInDistrictVO>();
			Set entries = resultsMap.entrySet();
			Iterator iterator = entries.iterator();
			while(iterator.hasNext()){
			Map.Entry entry = (Map.Entry)iterator.next();
			PartyPositionsInDistrictVO partyPositionResult = (PartyPositionsInDistrictVO)entry.getValue();
			partyPositionsResult.add(partyPositionResult);
			}
		}
	 return partyPositionsResult;
	}
	/*
	 * Returns Processed Results From Map To PartyPositionsInDistrictVO
	 */
	public PartyPositionsInDistrictVO getProcessedResults(PartyPositionsInDistrictVO partyResultsOneInDistrict,PartyPositionsInDistrictVO partyResultsTwoInDistrict){
		PartyPositionsInDistrictVO partyResults = null;
		if(partyResultsOneInDistrict != null && partyResultsTwoInDistrict != null){
			partyResults = new PartyPositionsInDistrictVO();
			partyResults.setDistrictName(partyResultsOneInDistrict.getDistrictName());
			partyResults.setSeatsWon(partyResultsOneInDistrict.getSeatsWon() + partyResultsTwoInDistrict.getSeatsWon());
			partyResults.setSecondPos(partyResultsOneInDistrict.getSecondPos() + partyResultsTwoInDistrict.getSecondPos());
			partyResults.setThirdPos(partyResultsOneInDistrict.getThirdPos() + partyResultsTwoInDistrict.getThirdPos());
			partyResults.setFourthPos(partyResultsOneInDistrict.getFourthPos() + partyResultsTwoInDistrict.getFourthPos());
			partyResults.setNthPos(partyResultsOneInDistrict.getNthPos() + partyResultsTwoInDistrict.getNthPos());
			
			Long totVotesEarned = partyResultsOneInDistrict.getTotalVotesEarned() + partyResultsTwoInDistrict.getTotalVotesEarned();
		    Long totValidVotes  = partyResultsOneInDistrict.getTotalValidVotes();
		    Long totConstiValidVotes  = partyResultsOneInDistrict.getTotalConstiValidVotes();
		    
			partyResults.setTotalVotesEarned(totVotesEarned);
			partyResults.setTotalValidVotes(totValidVotes);
			partyResults.setTotalConstiValidVotes(totConstiValidVotes);
			
			Double votesPercnt  = null;
			Double completVotesPercnt = null;
			if(!totValidVotes.equals(new Long(0)))
			votesPercnt = new BigDecimal((totVotesEarned.doubleValue()/totValidVotes.doubleValue())*100.0).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			if(!totConstiValidVotes.equals(new Long(0)))
			completVotesPercnt = new BigDecimal((totVotesEarned.doubleValue()/totConstiValidVotes.doubleValue())*100.0).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

			partyResults.setVotesPercentage(votesPercnt.toString());
			partyResults.setCompleteVotesPercent(completVotesPercnt.toString());
		}
		return partyResults;
	}

	/*
	 * returns districts in a state as SelectOptionVO
	 */
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getDistrictsInfoInAState(Long stateId){
		List<SelectOptionVO> districts = null;
		if(stateId != null){
			List dists = districtDAO.getDistrictIdAndNameByState(stateId);
			if(dists != null && dists.size() > 0){
				districts = new ArrayList<SelectOptionVO>();
				for(int i=0;i<dists.size();i++){
				Object[] params = (Object[])dists.get(i);
				SelectOptionVO districtDetails = new SelectOptionVO();
				districtDetails.setId((Long)params[0]);
				districtDetails.setName((String)params[1]);
				districts.add(districtDetails);
				}
			}
		}
	 return districts;
	}
	
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getStatesInfoInACountry(Long countryId){
		log.debug("Inside getStatesInfoInACountry ....");
		log.debug("Country Id :" + countryId);
		List<SelectOptionVO> states = null;
		if(countryId != null){
			List statesList = stateDAO.findStatesByCountryId(countryId);
			if(statesList != null && statesList.size() > 0){
			states = new ArrayList<SelectOptionVO>();
			for(int i=0;i<statesList.size();i++){
			Object[] params = (Object[])statesList.get(i);
			SelectOptionVO stateDetails = new SelectOptionVO();
			stateDetails.setId((Long)params[0]);
			stateDetails.setName((String)params[1]);
			states.add(stateDetails);
			}
			}
		}
		log.debug("states size:" + states.size());
	 return states;
	}
	
	/*
	 * Returns District wise Election Results for a Party
	 */
	public DistrictWisePartyPositionsVO getDistrictWiseProcessedResultsForAParty(List<PartyElectionDistrictResult> partyElecDistResultList){
		
		if(log.isDebugEnabled())
		    log.debug("Inside getDistrictWiseProcessedResultsForAParty() Method ..");
		
		Long partyId = new Long(0);
		String partyName = null;
		DistrictWisePartyPositionsVO districtWisePartyPositionsForAParty = null;
		List<PartyPositionsInDistrictVO> partyResultsInDistricts = null;
		
		if(partyElecDistResultList != null && partyElecDistResultList.size() > 0){
			districtWisePartyPositionsForAParty = new DistrictWisePartyPositionsVO();
			partyResultsInDistricts = new ArrayList<PartyPositionsInDistrictVO>();
			Long totalseatsWonInDistrict = new Long(0);
			for(PartyElectionDistrictResult partyResult:partyElecDistResultList){
				PartyPositionsInDistrictVO partyPositions = new PartyPositionsInDistrictVO();
				partyId = partyResult.getParty().getPartyId();
				if(partyResult.getParty().getShortName() != null)
				partyName = partyResult.getParty().getShortName();
				else
				partyName = partyResult.getParty().getLongName();
				totalseatsWonInDistrict+=new Long(partyResult.getTotalSeatsWon());
				
				partyPositions.setNthPos(new Long(partyResult.getNthPosWon()));
				partyPositions.setSeatsWon(new Long(partyResult.getTotalSeatsWon()));
				partyPositions.setThirdPos(new Long(partyResult.getThirdPosWon()));
				partyPositions.setSecondPos(new Long(partyResult.getSecondPosWon()));
				partyPositions.setFourthPos(new Long(partyResult.getFourthPosWon()));
				partyPositions.setDistrictId(partyResult.getDistrict().getDistrictId());
				partyPositions.setDistrictName(partyResult.getDistrict().getDistrictName());
				partyPositions.setVotesPercentage(partyResult.getVotesPercentage());
				partyPositions.setTotalValidVotes(partyResult.getTotalValidVotes().longValue());
				partyPositions.setTotalVotesEarned(partyResult.getTotalVotesGained().longValue());
				partyPositions.setCompleteVotesPercent(partyResult.getCompleteVotesPercent());
				partyPositions.setTotalConstiValidVotes(partyResult.getCompleteConstiValidVotes().longValue());
				partyPositions.setTotalConstiParticipated(new Long(partyResult.getTotalConstiParticipated()));
				
				partyResultsInDistricts.add(partyPositions);
			}
			
			districtWisePartyPositionsForAParty.setPartyId(partyId);
			districtWisePartyPositionsForAParty.setPartyName(partyName);
			districtWisePartyPositionsForAParty.setPartyResultsInDistricts(partyResultsInDistricts);
			districtWisePartyPositionsForAParty.setTotSeatsWonInAllPartiDistricts(totalseatsWonInDistrict);
			
		}
		
	  return districtWisePartyPositionsForAParty;
	}
	
	/*
	 * Returns State wise Election Results for a Party
	 */
	public DistrictWisePartyPositionsVO getStateWiseProcessedResultsForAParty(List<PartyElectionStateResult> partyElecStateResultList){
		
		if(log.isDebugEnabled())
		    log.debug("Inside getStateWiseProcessedResultsForAParty() Method ..");
		
		Long partyId = new Long(0);
		String partyName = null;
		DistrictWisePartyPositionsVO districtWisePartyPositionsForAParty = null;
		List<PartyPositionsInDistrictVO> partyResultsInDistricts = null;
		
		if(partyElecStateResultList != null && partyElecStateResultList.size() > 0){
			districtWisePartyPositionsForAParty = new DistrictWisePartyPositionsVO();
			partyResultsInDistricts = new ArrayList<PartyPositionsInDistrictVO>();
			Long totalseatsWonInDistrict = new Long(0);
			for(PartyElectionStateResult partyResult:partyElecStateResultList){
				PartyPositionsInDistrictVO partyPositions = new PartyPositionsInDistrictVO();
				partyId = partyResult.getParty().getPartyId();
				if(partyResult.getParty().getShortName() != null)
				partyName = partyResult.getParty().getShortName();
				else
				partyName = partyResult.getParty().getLongName();
				totalseatsWonInDistrict+=new Long(partyResult.getTotalSeatsWon());
				
				partyPositions.setNthPos(new Long(partyResult.getNthPosWon()));
				partyPositions.setSeatsWon(new Long(partyResult.getTotalSeatsWon()));
				partyPositions.setThirdPos(new Long(partyResult.getThirdPosWon()));
				partyPositions.setSecondPos(new Long(partyResult.getSecondPosWon()));
				partyPositions.setFourthPos(new Long(partyResult.getFourthPosWon()));
				partyPositions.setDistrictId(partyResult.getState().getStateId());
				partyPositions.setDistrictName(partyResult.getState().getStateName());
				partyPositions.setVotesPercentage(partyResult.getVotesPercentage());
				partyPositions.setTotalValidVotes(partyResult.getTotalValidVotes().longValue());
				partyPositions.setTotalVotesEarned(partyResult.getTotalVotesGained().longValue());
				partyPositions.setCompleteVotesPercent(partyResult.getCompleteVotesPercent());
				partyPositions.setTotalConstiValidVotes(partyResult.getCompleteConstiValidVotes().longValue());
				partyPositions.setTotalConstiParticipated(new Long(partyResult.getTotalConstiParticipated()));
				
				partyResultsInDistricts.add(partyPositions);
			}
			
			districtWisePartyPositionsForAParty.setPartyId(partyId);
			districtWisePartyPositionsForAParty.setPartyName(partyName);
			districtWisePartyPositionsForAParty.setPartyResultsInDistricts(partyResultsInDistricts);
			districtWisePartyPositionsForAParty.setTotSeatsWonInAllPartiDistricts(totalseatsWonInDistrict);
			
		}
		
	  return districtWisePartyPositionsForAParty;
	}
	
	public List<Long> getPartiesListFromPartyElectionResultVOList(List<PartyElectionResultVO> resultList)
	{
		log.debug("Entered into getPartiesListFromPartyElectionResultVOList() Method");
		try{
			List<Long> partiesList = null;
			
			if(resultList != null && resultList.size() > 0)
			{
				partiesList = new ArrayList<Long>(0);
				for(PartyElectionResultVO partyElectionResultVO : resultList)
					partiesList.add(partyElectionResultVO.getPartyId());
			}
			return partiesList;
		}catch (Exception e) {
			log.error("Exception Occured in getPartiesListFromPartyElectionResultVOList() Method, Exception is - "+e);
			return null;
		}
	}
	
	public List<PartyElectionResultVO> getPartyBasicDetailsForAElection(Long electionId)
	{
		log.debug("Entered into getPartyBasicDetailsForAElection() Method");
		try
		{
			List<PartyElectionResultVO> partyResult = null;
			
			List<Object[]> result = partyElectionResultDAO.getPartyBasicResultForAnElection(electionId);
			
			if(result != null && result.size() > 0)
			{
				partyResult = new ArrayList<PartyElectionResultVO>(0);
				for(Object[] params : result)
				{
					PartyElectionResultVO partyElectionResultVO = new PartyElectionResultVO();
					partyElectionResultVO.setPartyId((Long)params[0]);
					partyElectionResultVO.setPartyName(params[1].toString());
					partyElectionResultVO.setTotalParticipated(Long.parseLong(params[2].toString()));
					partyElectionResultVO.setTotalSeatsWon(Long.parseLong(params[3].toString()));
					partyElectionResultVO.setCompleteVotesPercent(params[4].toString());
					partyElectionResultVO.setPVotesPercent(params[5].toString());
					
					partyResult.add(partyElectionResultVO);
				}
			}
			return partyResult;
		}catch (Exception e) {
			log.error("Exception Occured in getPartyBasicDetailsForAElection() Method, Exception is - "+e);
			return null;
		}
	}
	
	public List<PartyElectionResultVO> getPartyBasicDetailsWithGenderInfoForAnElection(Long electionId)
	{
		log.debug("Entered into getPartyBasicDetailsWithGenderInfoForAnElection() Method");
		try{
			List<PartyElectionResultVO> partyResult = getPartyBasicDetailsForAElection(electionId);
						
			if(partyResult != null && partyResult.size() > 0)
			{
				List<Long> partiesList = getPartiesListFromPartyElectionResultVOList(partyResult);
												
				if(checkDataForGenderAnalysis(electionId,partiesList))
					partyResult = getGenderWiseElectionResultOfParties(partyResult,electionId,partiesList);
				
				else
				{
					ResultStatus resultStatus = populateGenderWiseElectionResultOfParties(electionId);
					
					if(resultStatus.getResultCode() == ResultCodeMapper.SUCCESS)
						partyResult = getGenderWiseElectionResultOfParties(partyResult,electionId,partiesList);
					else 
						partyResult = null;
				}
			}
			
			return partyResult;
		}catch(Exception e)
		{
			log.error("Exception Occured in getPartyBasicDetailsWithGenderInfoForAnElection() Method, Exception is - "+e);
			return null;
		}
	}
	
	public List<PartyElectionResultVO> getPartyElectionResultWithConstituencyAreaType(Long electionId)
	{
		log.debug("Entered into getPartyElectionResultWithConstituencyAreaType() Method ");
		List<PartyElectionResultVO> partyResult = null;
		try{
			 partyResult = getPartyBasicDetailsForAElection(electionId);
			
			if(partyResult != null && partyResult.size() > 0)
			{
				List<Long> partiesList = getPartiesListFromPartyElectionResultVOList(partyResult);
				
				if(checkDataForConstituencyAreaType(electionId,partiesList))
					partyResult = getConstituencyAreaTypeElectionResultOfParties(partyResult,electionId,partiesList);
				
				else
				{
					deleteRecordsFromPartyElectionResultsWithAreaTypeForAElection(electionId);
					ResultStatus resultStatus = populateConstituencyAreaTypeWiseElectionResultOfParties(electionId);
					
					if(resultStatus.getResultCode() == ResultCodeMapper.SUCCESS)
						partyResult = getConstituencyAreaTypeElectionResultOfParties(partyResult,electionId,partiesList);
					else 
						partyResult = null;
				}
			}
			return partyResult;
		}catch (Exception e) {
			log.error("Exception encountered in getPartyElectionResultWithConstituencyAreaType() Method, Exception is -"+e);
			return null;
		}
	}
	
	public PartyElectionResultVO getPartyElectionResultVO(Long partyId,List<PartyElectionResultVO> list)
	{
		for(PartyElectionResultVO partyElectionResultVO : list)
		{
			if(partyElectionResultVO.getPartyId().longValue() == partyId.longValue())
				return partyElectionResultVO;
		}
		return null;
	}
	
	public Boolean checkDataForGenderAnalysis(Long electionId,List<Long> partiesList)
	{
		log.debug("Entered into checkDataForGenderAnalysis() Method");
		try{
			Long count = (Long)partyElectionResultsWithGenderAnalysisDAO.getCountOfPartiesInAElection(electionId, partiesList);
			
			if(count == null || count.intValue() < partiesList.size())
				return false;
			else
				return true;
		}catch (Exception e) {
			log.error("Exception Occured in checkDataForGenderAnalysis() Method, Exception is -"+e);
			return false;
		}
	}
	
	public Boolean checkDataForConstituencyAreaType(Long electionId,List<Long> partiesList)
	{
		log.debug("Entered into checkDataForGenderAnalysis() Method");
		try{
			Long count = (Long)partyElectionResultsWithAreaTypeDAO.getCountOfPartiesInAElection(electionId, partiesList);
			
			if(count == null || count.intValue() < partiesList.size())
				return false;
			else
				return true;
		}catch (Exception e) {
			log.error("Exception Occured in checkDateForConstituencyAreaType() Method, Exception is -"+e);
			return false;
		}
	}
	
	public ResultStatus populateGenderWiseElectionResultOfParties(final Long electionId)
	{
		log.debug("Entered into populateGenderWiseElectionResultOfParties() Method");
		ResultStatus resultStatus = new ResultStatus();
		try{
			List<Object[]> resultList = nominationDAO.getGenderWiseElectionResultOfParties(electionId);
			List<PartyElectionResultVO> partyResult = new ArrayList<PartyElectionResultVO>(0);
			
			if(resultList != null && resultList.size() > 0)
			{
				PartyElectionResultVO partyElectionResultVO = null;
				for(Object[] params : resultList)
				{
					Boolean isNull = false;
					partyElectionResultVO = getPartyElectionResultVO((Long)params[0],partyResult);
					
					if(partyElectionResultVO == null)
					{
						partyElectionResultVO = new PartyElectionResultVO();
						isNull = true;
					}
					
					if(partyElectionResultVO != null && params[2] != null)
					{
						partyElectionResultVO.setPartyId((Long)params[0]);
						if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
						{
							partyElectionResultVO.setMalePerticipated(
									partyElectionResultVO.getMalePerticipated() == null ? 1L : partyElectionResultVO.getMalePerticipated()+1L);
							if(((Long)params[3]).longValue() == 1)
								partyElectionResultVO.setMaleWon(
										partyElectionResultVO.getMaleWon() == null ? 1L : partyElectionResultVO.getMaleWon() + 1L);
							
							partyElectionResultVO.setMVotesPolled(partyElectionResultVO.getMVotesPolled() != null ?
									(partyElectionResultVO.getMVotesPolled() + ((Double)params[4]).longValue()) : ((Double)params[4]).longValue());
							
							partyElectionResultVO.setMaleVoters(partyElectionResultVO.getMaleVoters() != null ?
									(partyElectionResultVO.getMaleVoters()+ ((Double)params[5]).longValue()) : ((Double)params[5]).longValue());
						}
						else
						{
							partyElectionResultVO.setFemalePerticipated(
									partyElectionResultVO.getFemalePerticipated() == null ? 1L : partyElectionResultVO.getFemalePerticipated()+1L);
							
							if(((Long)params[3]).longValue() == 1)
								partyElectionResultVO.setFemaleWon(
										partyElectionResultVO.getFemaleWon() == null ? 1L : partyElectionResultVO.getFemaleWon() + 1L);
							
							partyElectionResultVO.setFVotesPolled(partyElectionResultVO.getFVotesPolled() != null ?
									(partyElectionResultVO.getFVotesPolled() + ((Double)params[4]).longValue()) : ((Double)params[4]).longValue());
							
							partyElectionResultVO.setFemaleVoters(partyElectionResultVO.getFemaleVoters() != null ?
									(partyElectionResultVO.getFemaleVoters()+ ((Double)params[5]).longValue()) : ((Double)params[5]).longValue());
						}
					}
					
					if(isNull)
						partyResult.add(partyElectionResultVO);
				}
			}
			
			for(PartyElectionResultVO partyElectionResultVO : partyResult)
			{
				Double mper = 0.0D;
				Double fper = 0.0D;
				try{
				mper = (Double.parseDouble(partyElectionResultVO.getMVotesPolled().toString())*100)/
									Double.parseDouble(partyElectionResultVO.getMaleVoters().toString());
				partyElectionResultVO.setMVotesPercent(new BigDecimal(mper).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				
				fper = (Double.parseDouble(partyElectionResultVO.getFVotesPolled().toString())*100)/
				Double.parseDouble(partyElectionResultVO.getFemaleVoters().toString());
				partyElectionResultVO.setFVotesPercent(new BigDecimal(fper).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				
				}catch (Exception ex) {
					partyElectionResultVO.setMVotesPercent(new BigDecimal(mper).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					partyElectionResultVO.setFVotesPercent(new BigDecimal(fper).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
			}
			
			if(partyResult != null && partyResult.size() > 0)
			{
				for(final PartyElectionResultVO partyElectionResultVO : partyResult)
				{
					transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					public void doInTransactionWithoutResult(TransactionStatus status) {
					PartyElectionResultsWithGenderAnalysis genderAnalysis = new PartyElectionResultsWithGenderAnalysis();
					
					genderAnalysis.setElection(electionDAO.get(electionId));
					genderAnalysis.setParty(partyDAO.get(partyElectionResultVO.getPartyId()));
					genderAnalysis.setMaleParticipated(partyElectionResultVO.getMalePerticipated() != 
						null ? partyElectionResultVO.getMalePerticipated() : 0L);
					genderAnalysis.setMaleWon(partyElectionResultVO.getMaleWon() != null ?
							partyElectionResultVO.getMaleWon() : 0L);
					genderAnalysis.setMaleCandidateVotesGainedPercetage(partyElectionResultVO.getMVotesPercent());
					genderAnalysis.setFemaleParticipated(partyElectionResultVO.getFemalePerticipated() != null ?
							partyElectionResultVO.getFemalePerticipated() : 0L);
					genderAnalysis.setFemaleWon(partyElectionResultVO.getFemaleWon() != null ? 
							partyElectionResultVO.getFemaleWon() : 0L);
					genderAnalysis.setFemaleCandidateVotesGainedPercetage(partyElectionResultVO.getFVotesPercent());
					
					partyElectionResultsWithGenderAnalysisDAO.save(genderAnalysis);
					}
					});
				}
				
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				
			}
			return resultStatus;	
			
		}catch (Exception e) {
			log.error("Exception Occured in populateGenderWiseElectionResultOfParties() Method, Exception is  - "+e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
			return resultStatus;
		}
	}
	
	
	ResultStatus populateConstituencyAreaTypeWiseElectionResultOfParties(Long electionId)
	{
		log.debug("Entered into populateConstituencyAreaTypeWiseElectionResultOfParties() Method");
		ResultStatus resultStatus = new ResultStatus();
		try{
			List<Object[]> resultList = nominationDAO.getConstituencyAreaTypeWiseElectionResultOfParties(electionId);
			
			List<PartyElectionResultVO> partyResult = new ArrayList<PartyElectionResultVO>(0);
			
			if(resultList != null && resultList.size() > 0)
			{
				PartyElectionResultVO partyElectionResultVO = null;
				for(Object[] params : resultList)
				{
					Boolean isNull = false;
					partyElectionResultVO = getPartyElectionResultVO((Long)params[0],partyResult);
					
					if(partyElectionResultVO == null)
					{
						partyElectionResultVO = new PartyElectionResultVO();
						isNull = true;
					}
					
					if(partyElectionResultVO != null && params[2] != null)
					{
						partyElectionResultVO.setPartyId((Long)params[0]);
						
						if(params[2].toString().equalsIgnoreCase(IConstants.RURAL))
						{
							partyElectionResultVO.setRuralParticipated(
									partyElectionResultVO.getRuralParticipated() == null ? 1L : partyElectionResultVO.getRuralParticipated()+1L);
							
							if(((Long)params[3]).longValue() == 1)
								partyElectionResultVO.setRuralWon(
										partyElectionResultVO.getRuralWon() == null ? 1L : partyElectionResultVO.getRuralWon() + 1L);
							
							partyElectionResultVO.setRuralVotesPolled(partyElectionResultVO.getRuralVotesPolled() != null ?
									(partyElectionResultVO.getRuralVotesPolled() + ((Double)params[4]).longValue()) : ((Double)params[4]).longValue());
							
							partyElectionResultVO.setRuralVoters(partyElectionResultVO.getRuralVoters() != null ?
									(partyElectionResultVO.getRuralVoters()+ ((Double)params[5]).longValue()) : ((Double)params[5]).longValue());
							
						}
						else if(params[2].toString().equalsIgnoreCase(IConstants.URBAN))
						{
							partyElectionResultVO.setUrbanParticipated(
									partyElectionResultVO.getUrbanParticipated() == null ? 1L : partyElectionResultVO.getUrbanParticipated()+1L);
							
							if(((Long)params[3]).longValue() == 1)
								partyElectionResultVO.setUrbanWon(
										partyElectionResultVO.getUrbanWon() == null ? 1L : partyElectionResultVO.getUrbanWon() + 1L);
							
							partyElectionResultVO.setUrbanVotesPolled(partyElectionResultVO.getUrbanVotesPolled() != null ?
									(partyElectionResultVO.getUrbanVotesPolled() + ((Double)params[4]).longValue()) : ((Double)params[4]).longValue());
							
							partyElectionResultVO.setUrbanVoters(partyElectionResultVO.getUrbanVoters() != null ?
									(partyElectionResultVO.getUrbanVoters()+ ((Double)params[5]).longValue()) : ((Double)params[5]).longValue());
							
						}
						else if(params[2].toString().equalsIgnoreCase(IConstants.RURALURBAN))
						{
							partyElectionResultVO.setRuralUrbanParticipated(
									partyElectionResultVO.getRuralUrbanParticipated() == null ? 1L : partyElectionResultVO.getRuralUrbanParticipated()+1L);
							
							if(((Long)params[3]).longValue() == 1)
								partyElectionResultVO.setRuralUrbanWon(
										partyElectionResultVO.getRuralUrbanWon() == null ? 1L : partyElectionResultVO.getRuralUrbanWon() + 1L);
							
							partyElectionResultVO.setRuralUrbanVotesPolled(partyElectionResultVO.getRuralUrbanVotesPolled() != null ?
									(partyElectionResultVO.getRuralUrbanVotesPolled() + ((Double)params[4]).longValue()) : ((Double)params[4]).longValue());
							
							partyElectionResultVO.setRuralUrbanVoters(partyElectionResultVO.getRuralUrbanVoters() != null ?
									(partyElectionResultVO.getRuralUrbanVoters()+ ((Double)params[5]).longValue()) : ((Double)params[5]).longValue());
							
						}
					}
					
					if(isNull)
						partyResult.add(partyElectionResultVO);
				}
					
			}
			
			for(PartyElectionResultVO partyElectionResultVO : partyResult)
			{
				Double ruralPer = 0.0D;
				Double urbanPer = 0.0D;
				Double ruralUrbanPer = 0.0D;
				try{
				ruralPer = (Double.parseDouble(partyElectionResultVO.getRuralVotesPolled().toString())*100)/
									Double.parseDouble(partyElectionResultVO.getRuralVoters().toString());
				partyElectionResultVO.setRuralVotesPercent(new BigDecimal(ruralPer).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				
				urbanPer = (Double.parseDouble(partyElectionResultVO.getUrbanVotesPolled().toString())*100)/
				Double.parseDouble(partyElectionResultVO.getUrbanVoters().toString());
				partyElectionResultVO.setUrbanVotesPercent(new BigDecimal(urbanPer).setScale(2, BigDecimal.ROUND_HALF_UP).toString());

				ruralUrbanPer = (Double.parseDouble(partyElectionResultVO.getRuralUrbanVotesPolled().toString())*100)/
				Double.parseDouble(partyElectionResultVO.getRuralUrbanVoters().toString());
				partyElectionResultVO.setRuralUrbanVotesPercent(new BigDecimal(ruralUrbanPer).setScale(2, BigDecimal.ROUND_HALF_UP).toString());

				}catch (Exception ex) {
					partyElectionResultVO.setRuralVotesPercent(new BigDecimal(ruralPer).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					partyElectionResultVO.setUrbanVotesPercent(new BigDecimal(urbanPer).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					partyElectionResultVO.setRuralUrbanVotesPercent(new BigDecimal(ruralUrbanPer).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
			}
			
			return savePartyElectionResultsWithAreaTypeDetails(partyResult,electionId);
					
		}catch (Exception e) {
			log.error("Exception Occured in populateConstituencyAreaTypeWiseElectionResultOfParties() Method, Exception is  - "+e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
			return resultStatus;
		}
	}
	
	ResultStatus savePartyElectionResultsWithAreaTypeDetails(List<PartyElectionResultVO> partyResult, final Long electionId)
	{
		log.debug("Entered into savePartyElectionResultsWithAreaTypeDetails() Method");
		ResultStatus resultStatus = new ResultStatus();
		try{
			
			if(partyResult != null && partyResult.size() > 0)
			{
				for(final PartyElectionResultVO partyElectionResultVO : partyResult)
				{
					transactionTemplate.execute(new TransactionCallbackWithoutResult() {
						public void doInTransactionWithoutResult(TransactionStatus status)
						{
							
							PartyElectionResultsWithAreaType partyElectionResultsWithAreaType = new PartyElectionResultsWithAreaType();
							
							partyElectionResultsWithAreaType.setElection(electionDAO.get(electionId));
							partyElectionResultsWithAreaType.setParty(partyDAO.get(partyElectionResultVO.getPartyId()));
							partyElectionResultsWithAreaType.setRuralParticipated(partyElectionResultVO.getRuralParticipated()
									!= null ? partyElectionResultVO.getRuralParticipated() : 0L);
							partyElectionResultsWithAreaType.setRuralWon(partyElectionResultVO.getRuralWon() != null ? 
									partyElectionResultVO.getRuralWon() : 0L);
							partyElectionResultsWithAreaType.setRuralVotesGainedPercentage(partyElectionResultVO.getRuralVotesPercent() != null ?
									partyElectionResultVO.getRuralVotesPercent() : "0.0");
							partyElectionResultsWithAreaType.setUrbanParticipated(partyElectionResultVO.getUrbanParticipated()
									!= null ? partyElectionResultVO.getUrbanParticipated() : 0L);
							partyElectionResultsWithAreaType.setUrbanWon(partyElectionResultVO.getUrbanWon() != null ? 
									partyElectionResultVO.getUrbanWon() : 0L);
							partyElectionResultsWithAreaType.setUrbanVotesGainedPercentage(partyElectionResultVO.getUrbanVotesPercent() != null ?
									partyElectionResultVO.getUrbanVotesPercent() : "0.0");
							partyElectionResultsWithAreaType.setRuralUrbanParticipated(partyElectionResultVO.getRuralUrbanParticipated()
									!= null ? partyElectionResultVO.getRuralUrbanParticipated() : 0L);
							partyElectionResultsWithAreaType.setRuralUrbanWon(partyElectionResultVO.getRuralUrbanWon() != null ? 
									partyElectionResultVO.getRuralUrbanWon() : 0L);
							partyElectionResultsWithAreaType.setRuralUrbanVotesGainedPercentage(partyElectionResultVO.getRuralUrbanVotesPercent() != null ?
									partyElectionResultVO.getRuralUrbanVotesPercent() : "0.0");
							
							partyElectionResultsWithAreaTypeDAO.save(partyElectionResultsWithAreaType);
						}
						});
				}
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  }
			return resultStatus;
			}
			catch (Exception e) {
				log.error("Exception Occured in savePartyElectionResultsWithAreaTypeDetails() Method, Exception is  - "+e);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				resultStatus.setExceptionEncountered(e);
				return resultStatus;
			}
	}
	
	public Integer deleteRecordsFromPartyElectionResultsWithAreaTypeForAElection(final Long electionId)
	{
		log.debug("Entered into deleteRecordsFromPartyElectionResultsWithAreaTypeForAElection");
		try{
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status)
				{
					partyElectionResultsWithAreaTypeDAO.deleteRecordsForAElection(electionId);
				}
			});
			return ResultCodeMapper.SUCCESS;
		}catch (Exception e) {
			log.error("Exception occured in deleteRecordsFromPartyElectionResultsWithAreaTypeForAElection() Method, Exception is - "+e);
			return ResultCodeMapper.FAILURE;
		}
	}
	
	List<PartyElectionResultVO> getGenderWiseElectionResultOfParties(List<PartyElectionResultVO> partyResult,Long electionId,List<Long> partiesList)
	{
		log.debug("Enterd into getGenderWiseElectionResultOfParties() Method");
		try{
		List<Object[]> resultList = partyElectionResultsWithGenderAnalysisDAO.getPartyWiseGenderDetails(electionId,partiesList);
		
		PartyElectionResultVO partyElectionResultVO = null;
		for(Object[] params : resultList)
		{
			partyElectionResultVO = getPartyElectionResultVO((Long)params[0],partyResult);
			
			if(partyElectionResultVO != null)
			{
				partyElectionResultVO.setMalePerticipated((Long)params[1]);
				partyElectionResultVO.setMaleWon((Long)params[2]);
				partyElectionResultVO.setMVotesPercent(params[3] != null ? params[3].toString() : "");
				partyElectionResultVO.setFemalePerticipated((Long)params[4]);
				partyElectionResultVO.setFemaleWon((Long)params[5]);
				partyElectionResultVO.setFVotesPercent(params[6] != null ? params[6].toString() : "");
			}
		}
		return partyResult;
		
		}catch (Exception e) {
			log.error("Exception occured in getGenderWiseElectionResultOfParties() Method, Exception is - "+e);
			return null;
		}
	}
	
	
	List<PartyElectionResultVO> getConstituencyAreaTypeElectionResultOfParties(List<PartyElectionResultVO> partyResult,Long electionId,List<Long> partiesList)
	{
		log.debug("Enterd into getConstituencyAreaTypeElectionResultOfParties() Method");
		try{
			List<Object[]> resultList = partyElectionResultsWithAreaTypeDAO.getPartiesElectionResultInConstituencyAreaTypes(electionId,partiesList);
			
			PartyElectionResultVO partyElectionResultVO = null;
			for(Object[] params : resultList)
			{
				partyElectionResultVO = getPartyElectionResultVO((Long)params[0],partyResult);
				
				if(partyElectionResultVO != null)
				{
					partyElectionResultVO.setRuralParticipated((Long)params[1]);
					partyElectionResultVO.setRuralWon((Long)params[2]);
					partyElectionResultVO.setRuralVotesPercent(params[3] != null ? params[3].toString() : "0.0");
					partyElectionResultVO.setUrbanParticipated((Long)params[4]);
					partyElectionResultVO.setUrbanWon((Long)params[5]);
					partyElectionResultVO.setUrbanVotesPercent(params[6] != null ? params[6].toString() : "0.0");
					partyElectionResultVO.setRuralUrbanParticipated((Long)params[7]);
					partyElectionResultVO.setRuralUrbanWon((Long)params[8]);
					partyElectionResultVO.setRuralUrbanVotesPercent(params[9] != null ? params[9].toString() : "0.0");
				}
			}
			return partyResult;	
		}catch (Exception e) {
			log.error("Exception occured in getConstituencyAreaTypeElectionResultOfParties() Method, Exception is - "+e);
			return null;
		}
	}
	
	public List<PartyElectionResultVO> getTopVotesMarginVotesDetails(Long electionId,int maxResult,String type)
	{    
		List<PartyElectionResultVO> returnList = new ArrayList<PartyElectionResultVO>();
		 if(log.isDebugEnabled())
		  log.debug("Enterd into getTopVotesMarginVotesDetails() Method");
		 try
		 {
		  if(type.trim().equalsIgnoreCase("TopVotesGained"))
		  {
			 List<Object[]> topVotesList = nominationDAO.getTopVotesGainedCandidates(electionId,maxResult);
			 populateDataToVO(returnList,topVotesList,type);
		  }
		  else if(type.trim().equalsIgnoreCase("HighestMarginGained") || type.trim().equalsIgnoreCase("LowestMarginGained"))
		  {
			 List<Object[]> marginVotesList = nominationDAO.getHighestLowestMarginVotesInanElection(electionId,maxResult,type);
			 populateDataToVO(returnList,marginVotesList,type);
		  }
		  else if(type.trim().equalsIgnoreCase("TopVotesGainedPerc"))
		  {
			 List<Object[]> topVotesPercList = nominationDAO.getTopVotesGainedPercentage(electionId,maxResult);
			 populateDataToVO(returnList,topVotesPercList,type);
		  }
		 }
		 catch(Exception e)
		 {
			 log.error("Exception occured in getTopVotesMarginVotesDetails() Method, Exception is - "+e); 
		 }
		return returnList;
	}
	
	private void populateDataToVO(List<PartyElectionResultVO> returnList,List<Object[]> resultList,String type)
	{
		PartyElectionResultVO partyElectionResultVO = null;
		for(Object[] data: resultList)
		{
			partyElectionResultVO = new PartyElectionResultVO();
			
			partyElectionResultVO.setCandidateId((Long)data[0]);
			partyElectionResultVO.setCandidateName(data[1] != null?data[1].toString():"");
			partyElectionResultVO.setPartyId((Long)data[2]);
			partyElectionResultVO.setPartyName(data[3] != null?data[3].toString():"");
			partyElectionResultVO.setVotesEarned(Math.round((Double)data[4]));
			partyElectionResultVO.setValidVotes(Math.round((Double)data[5]));
            partyElectionResultVO.setConstiId((Long)data[6]);
			partyElectionResultVO.setConstiName(data[7] != null?data[7].toString():"");		
			if(type.trim().equalsIgnoreCase("TopVotesGained") || type.trim().equalsIgnoreCase("TopVotesGainedPerc"))
			  partyElectionResultVO.setVotesPercentage(data[8] != null?data[8].toString():"");
			else
			  partyElectionResultVO.setTotalVotes(Math.round((Double)data[8]));
			returnList.add(partyElectionResultVO);
		}
	}
}
