package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IConstituencyLeadCandidateDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionGoverningBodyDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultVO;
import com.itgrids.partyanalyst.dto.ElectionLiveResultVO;
import com.itgrids.partyanalyst.dto.PositionManagementVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.service.IElectionLiveResultsAnalysisService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;

public class ElectionLiveResultsAnalysisService implements IElectionLiveResultsAnalysisService{

	private static final Logger log = Logger.getLogger(ElectionLiveResultsAnalysisService.class);
	
	private IConstituencyElectionDAO constituencyElectionDAO;
	private IConstituencyLeadCandidateDAO constituencyLeadCandidateDAO;
	private IElectionDAO electionDAO;
	private INominationDAO nominationDAO;
	private IConstituencyDAO constituencyDAO;
	private IStaticDataService staticDataService;
	private IElectionGoverningBodyDAO electionGoverningBodyDAO;
	
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}

	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public void setConstituencyElectionDAO(IConstituencyElectionDAO constituencyElectionDAO) {
		this.constituencyElectionDAO = constituencyElectionDAO;
	}

	public IConstituencyElectionDAO getConstituencyElectionDAO() {
		return constituencyElectionDAO;
	}

	public void setConstituencyLeadCandidateDAO(
			IConstituencyLeadCandidateDAO constituencyLeadCandidateDAO) {
		this.constituencyLeadCandidateDAO = constituencyLeadCandidateDAO;
	}

	public IConstituencyLeadCandidateDAO getConstituencyLeadCandidateDAO() {
		return constituencyLeadCandidateDAO;
	}

	public IElectionGoverningBodyDAO getElectionGoverningBodyDAO() {
		return electionGoverningBodyDAO;
	}

	public void setElectionGoverningBodyDAO(
			IElectionGoverningBodyDAO electionGoverningBodyDAO) {
		this.electionGoverningBodyDAO = electionGoverningBodyDAO;
	}

	public ElectionLiveResultVO getCountOfConstituenciesForAElection(Long electionId, String electionIsPartial) {
		
		ElectionLiveResultVO electionLiveResultVO = new ElectionLiveResultVO();
		List oldConstituenciesCount;
		List countOfLeadConstituences ;
		List newCostituenciesCount;
		try 
		 {
			if(log.isDebugEnabled())
				log.debug("Entered Into getCountOfConstituenciesForAElection()");
			
		List totalSeats = constituencyElectionDAO.findConstituenciesCountInAnElection(electionId);
			electionLiveResultVO.setTotalSeats((Long)totalSeats.get(0));
		if(electionIsPartial.equalsIgnoreCase("true"))
		{
			
			 countOfLeadConstituences = constituencyLeadCandidateDAO.getLeadingConstituenciesCount(electionId);
				electionLiveResultVO.setCountOfLeadConstituences((Long)countOfLeadConstituences.get(0));
			
			oldConstituenciesCount = constituencyLeadCandidateDAO.getCountOfOldConstituenciesInAElection(electionId);
				electionLiveResultVO.setOldConstituenciesCount((Long)oldConstituenciesCount.get(0));
			
			newCostituenciesCount = constituencyLeadCandidateDAO.getCountOfDelimitedConstituenciesInAElection(electionId);
				electionLiveResultVO.setNewConstituenciesCount((Long)newCostituenciesCount.get(0));
		}
		else if(electionIsPartial.equalsIgnoreCase("false"))
		{
			oldConstituenciesCount = constituencyElectionDAO.getCountOfOldConstituencies(electionId);
				electionLiveResultVO.setOldConstituenciesCount((Long)oldConstituenciesCount.get(0));
			newCostituenciesCount = constituencyElectionDAO.getCountOfDelimitedConstituencies(electionId);
				electionLiveResultVO.setNewConstituenciesCount((Long)newCostituenciesCount.get(0));
		}
			
		
			return electionLiveResultVO;
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Encountered in getCountOfConstituenciesForAElection() -------"+e);
			return electionLiveResultVO;
		}
	}
	
	public List<ElectionLiveResultVO> getLeadingOrWinningContituenciesForAParty(Long electionId){
		List<ElectionLiveResultVO> electionLiveResultVOList = new ArrayList<ElectionLiveResultVO>();
		ElectionLiveResultVO electionLiveResultVO = null;
		try {
			if(log.isDebugEnabled()){
				log.debug("--------- Entered Into getLeadingOrWinningContituenciesForAParty Method ----------");
				if(electionId !=0){
				 Election election = electionDAO.get(electionId);
				
				 if(election.getIsPartial()!=null && election.getIsPartial().equalsIgnoreCase("1"))
				 {
					String electionIsPartial = "true";
					 List<Object[]> list = constituencyLeadCandidateDAO.getPartyLeadingOrWinningConstituencies(electionId);
					 
					if(list != null && list.size() > 0)
					{
						Map<String,ElectionLiveResultVO> partyMap = new HashMap<String,ElectionLiveResultVO>(0);
						ElectionLiveResultVO countVO = null;
						for(Object[] params : list)
						{
							String party = params[0].toString();
							countVO = partyMap.get(party);
							
							if(countVO == null)
								countVO = new ElectionLiveResultVO();
							
							if(params[1] != null)
							{
								if(params[2].toString().equalsIgnoreCase("Lead"))
									countVO.setLeadCountInNew(params[3]!=null?(Long)params[3] :0);
								else if(params[2].toString().equalsIgnoreCase("Won"))
								countVO.setWonCountInNew(params[3]!=null?(Long)params[3] :0);
							}
							else
							{
								if(params[2].toString().equalsIgnoreCase("Lead"))
									countVO.setLeadCountInOld((Long)params[3]);
								else if(params[2].toString().equalsIgnoreCase("Won"))
								countVO.setWonCountInOld((Long)params[3]);
							}
							partyMap.put(party,countVO);
						}
						
						for(Map.Entry<String,ElectionLiveResultVO> entry : partyMap.entrySet())
						{
							electionLiveResultVO = new ElectionLiveResultVO();
							electionLiveResultVO.setPartyName(entry.getKey());
							electionLiveResultVO.setLeadCountInNew(entry.getValue().getLeadCountInNew()!=null ? entry.getValue().getLeadCountInNew():0l);
							electionLiveResultVO.setWonCountInNew(entry.getValue().getWonCountInNew()!=null ? entry.getValue().getWonCountInNew() :0l);
							electionLiveResultVO.setLeadCountInOld(entry.getValue().getLeadCountInOld()!=null ? entry.getValue().getLeadCountInOld() :0l);
							electionLiveResultVO.setWonCountInOld(entry.getValue().getWonCountInOld() !=null ? entry.getValue().getWonCountInOld() :0l);
							
							electionLiveResultVOList.add(electionLiveResultVO);
							
						}
					}
					electionLiveResultVO = getCountOfConstituenciesForAElection(electionId,electionIsPartial);
					electionLiveResultVOList.add(electionLiveResultVO);
					electionLiveResultVOList.get(0).setPartialResult(true);
					 
					 
					
				 }
				 else {
					 List<Object[]> winningConstituencies = constituencyElectionDAO.getPartyWinningConstituenciesCount(electionId);
					 String elecYear = election.getElectionYear();
					 List electionIds = new ArrayList<Long>(0);
					 if(election.getElectionScope().getElectionType().getElectionType().equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
						 electionIds = electionDAO.getElectionIdsBasedOnStateId(election.getElectionScope().getState().getStateId(),elecYear);
					 }
					 
					 List<Long> elecIdsList =new ArrayList<Long>(0) ;
					 if(electionIds.size()>0){
					 for(int i=0;i<electionIds.size();i++){
						 Long elecId  =(Long) electionIds.get(i);
						 elecIdsList.add(elecId);
					 	}
					 }
					 Map<String,ElectionLiveResultVO> partyMap = new HashMap<String,ElectionLiveResultVO>(0);
						ElectionLiveResultVO countVO = null;
						
						for(Object[] params : winningConstituencies)
						{
							if(elecIdsList.size()==1){
								String electionIsPartial = "false";
							String party = params[2].toString();
							countVO = partyMap.get(party);
							
							if(countVO == null)
								countVO = new ElectionLiveResultVO();
							
							if(params[0] != null)
							{
								countVO.setWonCountInNew(params[1]!=null?(Long)params[1] :0);
							}
							else
							{
								countVO.setWonCountInOld(params[1]!=null?(Long)params[1] :0);
							}
							partyMap.put(party,countVO);
						 }
							else if(elecIdsList.size()==0 || elecIdsList.size()>1) {
								String electionIsPartial = "";
								String party = params[2].toString();
								countVO = partyMap.get(party);
								
								if(countVO == null)
									countVO = new ElectionLiveResultVO();
								
								countVO.setCountOfWinningConstituencies(params[1]!=null?(Long)params[1] :0);
								countVO.setPartyName(params[2].toString());
								
								partyMap.put(party,countVO);
							   }
						  }
						
						
						for(Map.Entry<String,ElectionLiveResultVO> entry : partyMap.entrySet())
						{
							
							electionLiveResultVO = new ElectionLiveResultVO();
							electionLiveResultVO.setPartyName(entry.getKey());
							electionLiveResultVO.setWonCountInNew(entry.getValue().getWonCountInNew());
							electionLiveResultVO.setWonCountInOld(entry.getValue().getWonCountInOld());
							electionLiveResultVO.setCountOfWinningConstituencies(entry.getValue().getCountOfWinningConstituencies());
							electionLiveResultVOList.add(electionLiveResultVO);
							}
						if(electionLiveResultVOList.get(0).getCountOfWinningConstituencies()!=null && electionLiveResultVOList.get(0).getCountOfWinningConstituencies()>0){
							electionLiveResultVO = getCountOfConstituenciesForAElection(electionId,"");
						}
						else
							electionLiveResultVO = getCountOfConstituenciesForAElection(electionId,"false");
						
						electionLiveResultVOList.add(electionLiveResultVO);
						electionLiveResultVOList.get(0).setPartialResult(false);
						}
				 
				 /*  for(Object[] params:winningConstituencies){
						
						 if(electionIds.size()==1){
							 electionLiveResultVO = new ElectionLiveResultVO();
						 electionLiveResultVO.setPartyName(params[2].toString());
						 if(params[0]!=null)
							electionLiveResultVO.setWonCountInNew((Long)params[1]);
						 else
							electionLiveResultVO.setWonCountInOld((Long)params[1]);
					   }
					 
					   else if(electionIds.size()>1) {
						   electionLiveResultVO = new ElectionLiveResultVO();
						   	 electionLiveResultVO.setCountOfWinningConstituencies((Long)params[1]);
							 electionLiveResultVO.setPartyName(params[2].toString());
					   }
						electionLiveResultVO.setCountOfWinningConstituencies((Long)params[0]);
						 electionLiveResultVO.setPartyName(params[1].toString());
						;
						 electionLiveResultVOList.add(electionLiveResultVO);
					   }	 
					   electionLiveResultVOList.get(0).setPartialResult(false);*/
				
				
				}
				 
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Encountered In getLeadingOrWinningContituenciesForAParty()" +e);
		}
		return electionLiveResultVOList;
		
	}
	
	public List<ConstituencyElectionResultVO> getConstituencyWiseCandidatesStates(Long electionId)
	{
		log.debug("Entered into getConstituencyWiseCandidatesStates() Method");
		try{
			List<ConstituencyElectionResultVO> resultList = null;
			Election election = electionDAO.get(electionId);
			Boolean isPartial = null;
			List<Object[]> list = null;
			
			if(election.getIsPartial() != null && election.getIsPartial().equalsIgnoreCase("1"))
				isPartial = true;
			else
				isPartial = false;
			
			if(isPartial)
				list = constituencyLeadCandidateDAO.getConstituencyWiseCandidatesStates(electionId);
			else
				list = nominationDAO.getConstituencyWiseCandidatesStates(electionId);
			
			if(list != null && list.size() > 0)
			{
				resultList = new ArrayList<ConstituencyElectionResultVO>(0);
				ConstituencyElectionResultVO electionResultVO = null;
				for(Object[] params : list)
				{
					electionResultVO = new ConstituencyElectionResultVO();
					electionResultVO.setConstituencyId((Long)params[0]);
					electionResultVO.setConstituencyName(params[1].toString());
					electionResultVO.setDistrictId((Long)params[2]);
					electionResultVO.setDistrictName(params[3].toString());
					electionResultVO.setCandidateId((Long)params[4]);
					electionResultVO.setCandidateName(params[5].toString());
					electionResultVO.setPartyId((Long)params[6]);
					electionResultVO.setPartyName(params[7].toString());
					
					if(isPartial)
						electionResultVO.setStatus(params[8].toString());
					else
						electionResultVO.setStatus(IConstants.WON);
					
					resultList.add(electionResultVO);
				}
				
			}
			return resultList;
		}catch (Exception e) {
			log.error("Exception Encountered In getConstituencyWiseCandidatesStates()" +e);
			return null;
		}
	}
	public List<ElectionLiveResultVO> getPartiesGainAndLossInfo(Long electionId)
	{
		try{
			log.debug("Entered into getPartiesGainAndLossInfo() Method");
			List<ElectionLiveResultVO> resultList = null;
			Boolean isPartial = null;
			List<Object[]> list = null;
			
			Election election = electionDAO.get(electionId);
			Long prevElectionId = getPreviousElectionId(electionId);
			String electionType = election.getElectionScope().getElectionType().getElectionType();
			Long stateId = election.getElectionScope().getState().getStateId();
			boolean isFirstElectionAfterDelimtation = getIsFirstElectionAfterDelimtation(election.getElectionScope().getElectionScopeId(),Long.parseLong(election.getElectionYear()));
			if(prevElectionId == null)
				return null;
			
			if(election.getIsPartial() != null && election.getIsPartial().equalsIgnoreCase("1"))
				isPartial = true;
			else
				isPartial = false;
			
			if(isPartial)
				list = constituencyLeadCandidateDAO.getPartiesLeadingInfo(electionId);
			else
				list = nominationDAO.getPartiesWonInfo(electionId);
				
			if(list != null && list.size() > 0)
			{
				resultList = new ArrayList<ElectionLiveResultVO>(0);
				ElectionLiveResultVO resultVO = null;
				Map<Long,Long> pariiesPCMap = getPartiesParticipatedCount(electionId);
				Map<Long,List<SelectOptionVO>> partiesPCinfoMap = getpartiesPCinfo(electionId);
				List<Long> knownConstituencyIds = new ArrayList<Long>(0);
				List<SelectOptionVO> staticParties = getStaticParties(electionType,stateId);
				
				for(Object[] params : list)
				{
					Long partyId = (Long)params[0];
					boolean isNew = false;
					resultVO = getElectionLiveResultVOFromList(resultList,partyId);
					knownConstituencyIds.add((Long)params[2]);
					
					if(resultVO == null)
					{
						isNew = true;
						resultVO = new ElectionLiveResultVO();
						List<Long> constituencyIdsList = new ArrayList<Long>(0);
						resultVO.setPartyId(partyId);
						resultVO.setPartyName(params[1].toString());
						resultVO.setWonOrLeadCount(1L);
						resultVO.setTotalSeatsParticipated(pariiesPCMap.get(partyId));
						resultVO.setParticipatedConstituencies(partiesPCinfoMap.get(partyId));
						resultVO.setIsFirstElectionAfterDelimtation(isFirstElectionAfterDelimtation);
						
						if(params[4] == null)
							constituencyIdsList.add((Long)params[2]);
						
						resultVO.setConstituencyIdsList(constituencyIdsList);
						
						if(isPartial)
						{
							if(params[4] == null)
							{
								if(params[5].toString().equalsIgnoreCase(IConstants.LEAD))
									resultVO.setLeadCountInOld(1L);
								else
									resultVO.setWonCountInOld(1L);
							}
							else
							{
								if(params[5].toString().equalsIgnoreCase(IConstants.LEAD))
									resultVO.setLeadCountInNew(1L);
								else
									resultVO.setWonCountInNew(1L);
							}
						}
						else
						{
							if(params[4] == null)
								resultVO.setWonCountInOld(1L);
							
							else
								resultVO.setWonCountInNew(1L);
						}
						
					}
					
					else
					{
						List<Long> constituencyIdsList = resultVO.getConstituencyIdsList();
						
						if(params[4] == null)
							constituencyIdsList.add((Long)params[2]);
						
						resultVO.setConstituencyIdsList(constituencyIdsList);
						resultVO.setWonOrLeadCount(resultVO.getWonOrLeadCount() + 1L);
						
						if(isPartial)
						{
							if(params[4] == null)
							{
								if(params[5].toString().equalsIgnoreCase(IConstants.LEAD))
									resultVO.setLeadCountInOld(resultVO.getLeadCountInOld() != null ? 
											resultVO.getLeadCountInOld() + 1L : 1L);
								else
									resultVO.setWonCountInOld(resultVO.getWonCountInOld() != null ?
											resultVO.getWonCountInOld() + 1L : 1L);
							}
							else
							{
								if(params[5].toString().equalsIgnoreCase(IConstants.LEAD))
									resultVO.setLeadCountInNew(resultVO.getLeadCountInNew() != null ? 
											resultVO.getLeadCountInNew() + 1L : 1L);
								else
									resultVO.setWonCountInNew(resultVO.getWonCountInNew() != null ? 
											resultVO.getWonCountInNew() + 1L : 1L);
							}
						}
						else
						{
							if(params[4] == null)
								resultVO.setWonCountInOld(resultVO.getWonCountInOld() != null ?
											resultVO.getWonCountInOld() + 1L : 1L);
							else
								resultVO.setWonCountInNew(resultVO.getWonCountInNew() != null ? 
											resultVO.getWonCountInNew() + 1L : 1L);
						}
					}
						
					if(isNew)
						resultList.add(resultVO);
					
					if(isPartial)
					{
						for(SelectOptionVO selectOptionVO : staticParties)
							if(getElectionLiveResultVOFromList(resultList,selectOptionVO.getId()) == null)
							{
								ElectionLiveResultVO electionLiveResultVO = new ElectionLiveResultVO();
								electionLiveResultVO.setPartyId(selectOptionVO.getId());
								electionLiveResultVO.setPartyName(selectOptionVO.getName());
								electionLiveResultVO.setWonOrLeadCount(0L);
								electionLiveResultVO.setIsFirstElectionAfterDelimtation(isFirstElectionAfterDelimtation);
								electionLiveResultVO.setTotalSeatsParticipated(pariiesPCMap.get(selectOptionVO.getId()) != null ?
										pariiesPCMap.get(selectOptionVO.getId()) : 0L);
								electionLiveResultVO.setParticipatedConstituencies(partiesPCinfoMap.get(selectOptionVO.getId()));
								electionLiveResultVO.setConstituencyIdsList(new ArrayList<Long>(0));
								electionLiveResultVO.setLeadCountInOld(0L);
								electionLiveResultVO.setWonCountInOld(0L);
								electionLiveResultVO.setLeadCountInNew(0L);
								electionLiveResultVO.setWonCountInNew(0L);
								
								if(electionLiveResultVO.getTotalSeatsParticipated() > 0)
									resultList.add(electionLiveResultVO);
							}
					}
				}
				
				for(ElectionLiveResultVO electionLiveResultVO : resultList)
				{
					if(electionLiveResultVO.getConstituencyIdsList() != null && electionLiveResultVO.getConstituencyIdsList().size() > 0)
					{
						List<SelectOptionVO> rList = getPartywiseWonCount(prevElectionId,electionLiveResultVO.getConstituencyIdsList());
						List<SelectOptionVO> prevResult = new ArrayList<SelectOptionVO>();
						if(rList != null && rList.size() > 0)
						{
							for(SelectOptionVO optionVO : rList)
							{
								if(optionVO.getName().equalsIgnoreCase(electionLiveResultVO.getPartyName()))
									electionLiveResultVO.setRetainedCount(optionVO.getId());
								else
									prevResult.add(optionVO);
							}
						}
						electionLiveResultVO.setWonFromOtherParties(prevResult);
					}
				}
				
				for(ElectionLiveResultVO electionLiveResultVO : resultList)
				{
					if(isFirstElectionAfterDelimtation)
						electionLiveResultVO = setNewAndOldPatricipatedConstituenciesAndCount(electionLiveResultVO);
					
					electionLiveResultVO.setTotalKnownCount(getKnownCount(
							getSelectOptionVOListIds(electionLiveResultVO.getParticipatedConstituencies()),knownConstituencyIds));
					
					if(electionLiveResultVO.getTotalKnownCount() != null && electionLiveResultVO.getTotalKnownCount() > 0)
					{	electionLiveResultVO.setWinOrLeadPercent((new BigDecimal((electionLiveResultVO.getWonOrLeadCount().doubleValue()*100)/
							electionLiveResultVO.getTotalKnownCount().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
						electionLiveResultVO.setWinOrLeadPer(Double.parseDouble(electionLiveResultVO.getWinOrLeadPercent()));
					}
					if(isFirstElectionAfterDelimtation)
					{
						electionLiveResultVO.setOldKnownCount(getKnownCount(
								getSelectOptionVOListIds(electionLiveResultVO.getOldConstituenciesParticipated()),knownConstituencyIds));
						electionLiveResultVO.setNewKnownCount(getKnownCount(
								getSelectOptionVOListIds(electionLiveResultVO.getNewConstituenciesParticipated()),knownConstituencyIds));
						
						electionLiveResultVO.setWonOrLeadCountInOld(
								(electionLiveResultVO.getLeadCountInOld() != null ? electionLiveResultVO.getLeadCountInOld(): 0L) + 
								(electionLiveResultVO.getWonCountInOld() != null ? electionLiveResultVO.getWonCountInOld() : 0L));
						electionLiveResultVO.setWonOrLeadCountInNew(
								(electionLiveResultVO.getLeadCountInNew() != null ? electionLiveResultVO.getLeadCountInNew(): 0L) + 
								(electionLiveResultVO.getWonCountInNew() != null ? electionLiveResultVO.getWonCountInNew() : 0L));
						
						if(electionLiveResultVO.getOldKnownCount() != null && electionLiveResultVO.getOldKnownCount().longValue() > 0L)
						{
							electionLiveResultVO.setOldWinOrLeadPercent((new BigDecimal((electionLiveResultVO.getWonOrLeadCountInOld().doubleValue()*100)/
									electionLiveResultVO.getOldKnownCount().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
							electionLiveResultVO.setOldWinOrLeadPer(Double.parseDouble(electionLiveResultVO.getOldWinOrLeadPercent()));
						}
						
						if(electionLiveResultVO.getNewKnownCount() != null && electionLiveResultVO.getNewKnownCount().longValue() > 0L)
						{
							electionLiveResultVO.setNewWinOrLeadPercent((new BigDecimal((electionLiveResultVO.getWonOrLeadCountInNew().doubleValue()*100)/
									electionLiveResultVO.getNewKnownCount().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
							electionLiveResultVO.setNewWinOrLeadPer(Double.parseDouble(electionLiveResultVO.getNewWinOrLeadPercent()));
						}
					}
					
					if(electionLiveResultVO.getParticipatedConstituencies() != null && 
							electionLiveResultVO.getParticipatedConstituencies().size() > 0)
						electionLiveResultVO = getPartiesLostInfo(electionId,prevElectionId,electionLiveResultVO,isPartial);
				}
			}
			return resultList;
		}catch (Exception e){
			log.error("Exception occured in getPartiesGainAndLossInfo() Method, Exception is - "+e);
			return null;
		}
	}
	
	public Long getPreviousElectionId(Long electionId)
	{
		try{
			log.debug("Entered into getPreviousElectionId() Method");
			
			List<Object[]> list = electionDAO.getPreviousElectionIdAndYear(electionId);
			
			if(list != null && list.size() > 0)
				return (Long)list.get(0)[0];
			else
				return null;
			
		}catch (Exception e) {
			log.error("Exception occured in getPreviousElectionId() Method, Exception is - "+e);
			return null;
		}
	}
	
	public ElectionLiveResultVO getElectionLiveResultVOFromList(List<ElectionLiveResultVO> list,Long partyId)
	{
		try{
			for(ElectionLiveResultVO resultVO : list)
				if(resultVO.getPartyId().longValue() == partyId.longValue())
					return resultVO;
			return null;
		}catch (Exception e) {
			log.error("Exception occured in getElectionLiveResultVOFromList() Method, Exception is - "+e);
			return null;
		}
	}
	
	public Map<Long,Long> getPartiesParticipatedCount(Long electionId)
	{
		try{
			Map<Long,Long> partiesCount = null;
			List<Object[]> list = nominationDAO.getPartiwiseParticipatedCountInAElection(electionId);
			
			if(list != null && list.size() > 0)
			{
				partiesCount = new HashMap<Long,Long>(0);
				for(Object[] params : list)
					partiesCount.put((Long)params[0],(Long)params[1]);
			}
			return partiesCount;
		}catch (Exception e) {
			log.error("Exception occured in getPartiesParticipatedCount() Method, Exception is - "+e);
			return null;
		}
	}
	
	public Map<Long,List<SelectOptionVO>> getpartiesPCinfo(Long electionId)
	{
		log.debug("Entered into getpartiesPCinfo() Method");
		try{
			Map<Long,List<SelectOptionVO>> resultMap = null;
			List<Object[]> list = nominationDAO.getpartiesPCinfo(electionId);
			
			if(list != null && list.size() > 0)
			{
				resultMap = new HashMap<Long, List<SelectOptionVO>>();
				List<SelectOptionVO> constInfo = null;
				SelectOptionVO selectOptionVO = null;
				for(Object[] params :list)
				{
					Long partyId = (Long)params[0];
					constInfo = resultMap.get(partyId);
					
					if(constInfo == null)
						constInfo = new ArrayList<SelectOptionVO>(0);
					
					selectOptionVO = new SelectOptionVO((Long)params[1],params[2].toString());
					constInfo.add(selectOptionVO);
					resultMap.put(partyId,constInfo);
				}
			}
			return resultMap;
		}catch(Exception e) {
			log.error("Exception occured in getpartiesPCinfo() Method, Exception is - "+e);
			return null;
		}
	}
	
	public List<SelectOptionVO> getPartywiseWonCount(Long electionId,List<Long> constituenciesList)
	{
		try{
			List<SelectOptionVO> partiesResult = null;
			List<Object[]> list = nominationDAO.getPartywiseWonCount(electionId,constituenciesList);
			
			if(list != null && list.size() > 0)
			{
				partiesResult = new ArrayList<SelectOptionVO>(0);
				SelectOptionVO selectOptionVO = null;
				
				for(Object[] params : list)
				{
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setName(params[0].toString());
					selectOptionVO.setId((Long)params[1]);
					partiesResult.add(selectOptionVO);
				}
			}
			
			return partiesResult;
		}catch (Exception e) {
			log.error("Exception occured in getPartywiseWonCount() Method, Exception is - "+e);
			return null;
		}
	}
	
	public ElectionLiveResultVO getPartiesLostInfo(Long electionId,Long prevElectionId,ElectionLiveResultVO electionLiveResultVO,boolean isPartial)
	{
		try{
			List<Long> cIdList = new ArrayList<Long>(0);
			
			for(SelectOptionVO optionVO : electionLiveResultVO.getParticipatedConstituencies())
				cIdList.add(optionVO.getId());
			
			electionLiveResultVO = getPartyPresentLostConstituencies(electionId,electionLiveResultVO,cIdList,isPartial);
			
			electionLiveResultVO = setPreviousWinAndLostConstituencies(electionLiveResultVO,prevElectionId,electionLiveResultVO.getLostConstIdsList());
			
			if(isPartial)
			{
				electionLiveResultVO.setLostToOtherParties(getPartiesinfoInSpecifiedConstituencies(electionId,electionLiveResultVO.getPartyName(),electionLiveResultVO.getLostConstIdsList()));
				if(electionLiveResultVO.getPrevWonConstIds() != null && electionLiveResultVO.getPrevWonConstIds().size() > 0)
					electionLiveResultVO.setLostSeatsInPrevWonToOtherParties(
							getPartiesinfoInSpecifiedConstituencies(electionId,electionLiveResultVO.getPartyName(),electionLiveResultVO.getPrevWonConstIds()));
				if(electionLiveResultVO.getPrevLostConstIds() != null && electionLiveResultVO.getPrevLostConstIds().size() > 0)
					electionLiveResultVO.setLostSeatsInPrevLostToOtherParties(
							getPartiesinfoInSpecifiedConstituencies(electionId,electionLiveResultVO.getPartyName(),electionLiveResultVO.getPrevLostConstIds()));
			}
			else
			{
				electionLiveResultVO.setLostToOtherParties(getPartywiseWonCountExceptOneParty(electionLiveResultVO.getPartyName(),electionId,cIdList));
				if(electionLiveResultVO.getPrevWonConstIds() != null && electionLiveResultVO.getPrevWonConstIds().size() > 0)
					electionLiveResultVO.setLostSeatsInPrevWonToOtherParties(
							getPartywiseWonCountExceptOneParty(electionLiveResultVO.getPartyName(),electionId,electionLiveResultVO.getPrevWonConstIds()));
				
				if(electionLiveResultVO.getPrevLostConstIds() != null && electionLiveResultVO.getPrevLostConstIds().size() > 0)
					electionLiveResultVO.setLostSeatsInPrevLostToOtherParties(
							getPartywiseWonCountExceptOneParty(electionLiveResultVO.getPartyName(),electionId,electionLiveResultVO.getPrevLostConstIds()));	
			}
			return electionLiveResultVO;
		}catch (Exception e) {
			log.error("Exception occured in getPartiesLostInfo() Method, Exception is - "+e);
			return electionLiveResultVO;
		}
	}
	
	public List<SelectOptionVO> getPartiesinfoInSpecifiedConstituencies(Long electionId,String partyName,List<Long> constituenciesList)
	{
		try{
			List<SelectOptionVO> resultList = null;
			List<Object[]> list = constituencyLeadCandidateDAO.getPartiesWonCountInSpecifiedConstituencies(electionId, constituenciesList);
			
			if(list != null && list.size() > 0)
			{
				resultList = new ArrayList<SelectOptionVO>(0);
				SelectOptionVO selectOptionVO = null;
				for(Object[] params : list)
					if(!partyName.equalsIgnoreCase(params[0].toString()))
					{
						selectOptionVO = new SelectOptionVO((Long)params[1],params[0].toString());
						resultList.add(selectOptionVO);
					}
					
			}
			return resultList;
		}catch (Exception e) {
			log.error("Exception occured in getPartiesLostInfo() Method, Exception is - "+e);
			return null;
		}
	}
	
	private ElectionLiveResultVO setPreviousWinAndLostConstituencies(ElectionLiveResultVO electionLiveResultVO,Long prevElectionId, List<Long> constituenciesList)
	{
		try{
			List<Object[]> list = nominationDAO.getPartyRankConstituenciesInAElection(electionLiveResultVO.getPartyId(),prevElectionId, constituenciesList);
			List<SelectOptionVO> wonList = new ArrayList<SelectOptionVO>(0);
			List<SelectOptionVO> lostList = new ArrayList<SelectOptionVO>(0);
			List<Long> prevLostConstIds = new ArrayList<Long>(0);
			List<Long> prevWonConstIds = new ArrayList<Long>(0);
			if(list != null && list.size() > 0)
			{
				SelectOptionVO selectOptionVO = null;
				for(Object[] params :list)
				{
					selectOptionVO = new SelectOptionVO((Long)params[0],params[1].toString());
					if(((Long)params[2]).longValue() == 1L)
					{
						wonList.add(selectOptionVO);
						prevWonConstIds.add((Long)params[0]);
					}
					else
					{
						lostList.add(selectOptionVO);
						prevLostConstIds.add((Long)params[0]);
					}
				}
			}
			
			electionLiveResultVO.setPrevWonConstituenciesList(wonList);
			electionLiveResultVO.setPrevLostConstituenciesList(lostList);
			electionLiveResultVO.setPrevLostConstIds(prevLostConstIds);
			electionLiveResultVO.setPrevWonConstIds(prevWonConstIds);
			electionLiveResultVO.setLostCountInPrevWon((long)prevWonConstIds.size());
			electionLiveResultVO.setLostCountInPrevLost((long)prevLostConstIds.size());
			
			return electionLiveResultVO;
		}catch (Exception e) {
			log.error("Exception occured in setPreviousWinAndLostConstituencies() Method, Exception is - "+e);
			return electionLiveResultVO;
		}
	}
	
	public ElectionLiveResultVO getPartyPresentLostConstituencies(Long electionId,ElectionLiveResultVO electionLiveResultVO ,List<Long> cIdList,boolean isPartial)
	{
		try{
			List<SelectOptionVO> lostConstituencies = new ArrayList<SelectOptionVO>(0);
			List<Long> lostCIDList = new ArrayList<Long>(0);
			List<SelectOptionVO> wonConstituencies = new ArrayList<SelectOptionVO>(0);
			if(isPartial)
			{
				List<Object[]> list = constituencyLeadCandidateDAO.getPartyWinConstituencies(electionLiveResultVO.getPartyId(),electionId,cIdList);
				
				if(list != null && list.size() > 0)
				{
					SelectOptionVO selectOptionVO = null;
					for(Object[] params : list)
					{
						selectOptionVO = new SelectOptionVO((Long)params[0],params[1].toString());
						wonConstituencies.add(selectOptionVO);
					}
					for(SelectOptionVO optionVO : electionLiveResultVO.getParticipatedConstituencies())
					{
						if(!hasContains(optionVO.getId(),wonConstituencies))
						{
							lostConstituencies.add(optionVO);
							lostCIDList.add(optionVO.getId());
						}
					}
				}
				else
				{
					lostConstituencies = electionLiveResultVO.getParticipatedConstituencies();
					lostCIDList = cIdList;
				}
			}
			else
			{
				List<Object[]> list = nominationDAO.getPartyLostConstituencies(electionLiveResultVO.getPartyId(),electionId, cIdList);
				if(list != null && list.size() > 0)
				{
					SelectOptionVO selectOptionVO = null;
					for(Object[] params : list)
					{
						selectOptionVO = new SelectOptionVO((Long)params[0],params[1].toString());
						lostConstituencies.add(selectOptionVO);
						lostCIDList.add(selectOptionVO.getId());
					}
				}
			}
			
			electionLiveResultVO.setLostConstituenciesList(lostConstituencies);
			electionLiveResultVO.setLostConstIdsList(lostCIDList);
			electionLiveResultVO.setLostCount((long)lostCIDList.size());
			return electionLiveResultVO;
		}
		catch (Exception e) {
			log.error("Exception occured in setPreviousWinAndLostConstituencies() Method, Exception is - "+e);
			return electionLiveResultVO;
		}
	}
	
	public List<SelectOptionVO> getPartywiseWonCountExceptOneParty(String partyName,Long electionId,List<Long> cIdList)
	{
		try{
			List<SelectOptionVO> otherPartiesList = new ArrayList<SelectOptionVO>(0);
			List<SelectOptionVO> list = getPartywiseWonCount(electionId,cIdList);
			
			if(list != null)
			{
				otherPartiesList = new ArrayList<SelectOptionVO>(0);
				for(SelectOptionVO optionVO : list)
					if(!optionVO.getName().equalsIgnoreCase(partyName))
						otherPartiesList.add(optionVO);
			}
			
			return otherPartiesList;
		}catch (Exception e) {
			log.error("Exception occured in getPartywiseWonCountExceptOneParty() Method, Exception is - "+e);
			return null;
		}
	}
	
	public boolean hasContains(Long id, List<SelectOptionVO> list)
	{
		for(SelectOptionVO selectOptionVO : list)
		{
			if(selectOptionVO.getId().longValue() == id.longValue())
				return true;
		}
		return false;
	}
	
	public List<SelectOptionVO> getStaticParties(String electionType,Long stateId)
	{
		List<SelectOptionVO> parties = new ArrayList<SelectOptionVO>(0);
		try{
			if(electionType.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE))
				for(SelectOptionVO selectOptionVO : staticDataService.getAllNationalParties())
					parties.add(selectOptionVO);
			else if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE))
				for(SelectOptionVO selectOptionVO : staticDataService.getStaticPartiesListForAState(stateId))
					parties.add(selectOptionVO);
			
			return parties;
		}catch (Exception e) {
			log.error("Exception Occured in getStaticParties() Method - "+e);
			return parties;
		}
	}
	
	public boolean getIsFirstElectionAfterDelimtation(Long electionScopeId, Long year)
	{
		try{
			if(year.longValue() >= IConstants.DELIMITATION_YEAR)
			{
				Long count = (Long)electionDAO.getCountOfElectionsAfterDelimitation(electionScopeId);
				
				if(count.longValue() == 1L)
					return true;
				else
					return false;
			}
			else
				return false;
		}catch (Exception e) {
			log.error("Exception Occured in getIsFirstElectionAfterDelimtation() Method - "+e);
			return false;
		}
	}
	
	
	public ElectionLiveResultVO setNewAndOldPatricipatedConstituenciesAndCount(ElectionLiveResultVO electionLiveResultVO)
	{
		try{
			List<SelectOptionVO> oldConstituenciesParticipated = new ArrayList<SelectOptionVO>(0);
			List<SelectOptionVO> newConstituenciesParticipated = new ArrayList<SelectOptionVO>(0);
			Long oldConstituencyParticipatedCount = 0L;
			Long newConstituencyParticipatedCount = 0L;
			
			if(electionLiveResultVO.getParticipatedConstituencies() != null && electionLiveResultVO.getParticipatedConstituencies().size() > 0)
			{
				List<Object[]> list = constituencyDAO.getConstituencyInfoWithStartDateByConstituencyIdList(
						getSelectOptionVOListIds(electionLiveResultVO.getParticipatedConstituencies()));
				
				if(list != null && list.size() > 0)
				{
					SelectOptionVO selectOptionVO = null;
					for(Object[] params : list)
					{
						selectOptionVO = new SelectOptionVO((Long)params[0],params[1].toString());
						if(params[2] == null)
							oldConstituenciesParticipated.add(selectOptionVO);
						else
							newConstituenciesParticipated.add(selectOptionVO);
					}
					
					oldConstituencyParticipatedCount = (long)oldConstituenciesParticipated.size();
					newConstituencyParticipatedCount = (long)newConstituenciesParticipated.size();
				}
			}
			
			electionLiveResultVO.setOldConstituenciesParticipated(oldConstituenciesParticipated);
			electionLiveResultVO.setNewConstituenciesParticipated(newConstituenciesParticipated);
			electionLiveResultVO.setOldConstituencyParticipatedCount(oldConstituencyParticipatedCount);
			electionLiveResultVO.setNewConstituencyParticipatedCount(newConstituencyParticipatedCount);
			
			return electionLiveResultVO;
		}catch (Exception e) {
			log.error("Exception Occured in setNewAndOldPatricipatedCount() Method - "+e);
			return electionLiveResultVO;
		}
	}
	
	public List<Long> getSelectOptionVOListIds(List<SelectOptionVO> list)
	{
		List<Long> ids = new ArrayList<Long>(0);
		try{
			if(list != null && list.size() > 0)
				for(SelectOptionVO optionVO : list)
					ids.add(optionVO.getId());
			return ids;
		}catch (Exception e) {
			return ids;
		}
	}
	
	public Long getKnownCount(List<Long> list1,List<Long> list2)
	{
		Long count = 0L;
		try{
			if(list1 != null && list2 != null)
			{
				for(Long id : list1)
					if(list2.contains(id))
						count++;
			}
			return count;
		}catch (Exception e) {
			return count;
		}
	}	
  public List<PositionManagementVO> getCurrentMinistersDetailsForCurrentAndPrevEle(Long type,Long stateId,String year,Long elecId)
    {   
	     if(log.isDebugEnabled())
	    	 log.debug("Enter into getCurrentMinistersDetailsForCurrentAndPrevEle of ElectionLiveResultsAnalysisService");
	    Long prevElectionId = getPreviousElectionId(elecId);
	    List<PositionManagementVO> listData = new ArrayList<PositionManagementVO>();
	  try
	  {
	    List<Object[]> candidates = electionGoverningBodyDAO.getAllMinistersIdsAndMinistry(prevElectionId);
	    PositionManagementVO positionManagementVO = null;
	    for(Object[] candDetails :candidates)
	    {
	    	positionManagementVO = new PositionManagementVO();
	    	positionManagementVO.setCandidateId((Long)candDetails[0]);
	    	positionManagementVO.setCandidateName(candDetails[1] != null?candDetails[1].toString():"");
	    	positionManagementVO.setPositionName(candDetails[2] != null?candDetails[2].toString():"");
	    	// current election
	    	Election election = electionDAO.get(elecId);
	    	
	    	  PositionManagementVO currentResult = new PositionManagementVO();
	    	  List<PositionManagementVO> currentResultList = new ArrayList<PositionManagementVO>();
	    	  PositionManagementVO currentResultData = null;
		      if(election.getIsPartial() != null && election.getIsPartial().trim().equalsIgnoreCase("1"))
		      {
		    	 List<Object[]> constiList = nominationDAO.getConstituencyDetails((Long)candDetails[0], elecId);
		       for(Object[] constis : constiList)
		       {
		    	   List<Object[]> currentResultsList = constituencyLeadCandidateDAO.getCandidateResultsForPartialElec((Long)constis[0],elecId);
		    	   currentResultData = new PositionManagementVO();
		    	  if(currentResultsList.size() >0)
		    	  {
		    		  Object[] result =  currentResultsList.get(0);
		    		  
		    		  if(((Long)result[0]) ==  (Long)candDetails[0])
		    		  {
		    			  currentResultData.setResult(result[1].toString());
		    		  }
		    		  else
		    		  {
		    			  currentResultData.setResult("Preceed");
		    		  }
		    		  
		    	  }
		    	  else
		    	  {
		    		  currentResultData.setResult("Results Not Known");
		    	  }
		    	  
		    	  currentResultData.setConstituencyId((Long)constis[0]);
		    	  currentResultData.setConstituencyName(constis[1] != null?constis[1].toString():"");
		    	  currentResultList.add(currentResultData);
		       }
		       currentResult.setYear(election.getElectionYear());
		          currentResult.setIsPartial(1L);
		          currentResult.setPositionManagementVOList(currentResultList);
		         if(constiList.size() > 0)
		         {
		            Object[] partyDetails = constiList.get(0);
		            currentResult.setPartyId((Long)partyDetails[2]);
		            currentResult.setPartyName(partyDetails[3] != null?partyDetails[3].toString():"");
		         }
		    	  positionManagementVO.setCurrentResult(currentResult);
		      }
		      else
		      {
		    	  List<Object[]> currentResultsList = nominationDAO.getCandidateResultsForNormal((Long)candDetails[0],elecId);
		    	  for(Object[] currentResults:currentResultsList)
		    	  {
		    		  currentResultData = new PositionManagementVO();
		    		  currentResultData.setConstituencyId((Long)currentResults[0]);
		    		  currentResultData.setConstituencyName(currentResults[1] != null?currentResults[1].toString():"");
		    		  currentResultData.setVotesEarned(new BigDecimal((Double)currentResults[2]));
		    		  currentResultData.setVotesPercengate(currentResults[3] != null?currentResults[3].toString():"");
		    		  currentResultData.setRank((Long)currentResults[4]);
		    		  currentResultList.add(currentResultData);
		    	  }
		    	  currentResult.setYear(election.getElectionYear());
		    	  currentResult.setIsPartial(0L);
		    	  currentResult.setPositionManagementVOList(currentResultList);
		    	  if(currentResultsList.size() > 0)
		    	  {
		    		    Object[] partyDetails = currentResultsList.get(0);
		    		    currentResult.setPartyId((Long)partyDetails[5]);
		    		    currentResult.setPartyName(partyDetails[6] != null?partyDetails[6].toString():""); 
		    	  }
		    	  positionManagementVO.setCurrentResult(currentResult);
		      }
		      
		   // previous election
		    	Election electionPrev = electionDAO.get(prevElectionId);
		    	
		    	  PositionManagementVO prevResult = new PositionManagementVO();
		    	  List<PositionManagementVO> prevResultList = new ArrayList<PositionManagementVO>();
		    	  PositionManagementVO prevResultData = null;
			      if(electionPrev.getIsPartial() != null && electionPrev.getIsPartial().trim().equalsIgnoreCase("1"))
			      {
			    	 List<Object[]> constiList = nominationDAO.getConstituencyDetails((Long)candDetails[0], prevElectionId);
			       for(Object[] constis : constiList)
			       {
			    	   List<Object[]> prevResultsList = constituencyLeadCandidateDAO.getCandidateResultsForPartialElec((Long)constis[0],prevElectionId);
			    	   prevResultData = new PositionManagementVO();
			    	  if(prevResultsList.size() >0)
			    	  {
			    		  Object[] result =  prevResultsList.get(0);
			    		  
			    		  if(((Long)result[0]) ==  (Long)candDetails[0])
			    		  {
			    			  prevResultData.setResult(result[1].toString());
			    		  }
			    		  else
			    		  {
			    			  prevResultData.setResult("Preceed");
			    		  }
			    		  
			    	  }
			    	  else
			    	  {
			    		  prevResultData.setResult("Results Not Known");
			    	  }
			    	  
			    	  prevResultData.setConstituencyId((Long)constis[0]);
			    	  prevResultData.setConstituencyName(constis[1] != null?constis[1].toString():"");
			    	  prevResultList.add(prevResultData);
			       }
			       prevResult.setYear(election.getElectionYear());
			          prevResult.setIsPartial(1L);
			          prevResult.setPositionManagementVOList(prevResultList);
			          if(constiList.size() > 0)
				         {
				            Object[] partyDetails = constiList.get(0);
				            prevResult.setPartyId((Long)partyDetails[2]);
				            prevResult.setPartyName(partyDetails[3] != null?partyDetails[3].toString():"");
				         }
			    	  positionManagementVO.setPreviousResult(prevResult);
			      }
		      else
		      {
		    	  List<Object[]> prevResultsList = nominationDAO.getCandidateResultsForNormal((Long)candDetails[0],prevElectionId);
		    	  
		    	  for(Object[] prevResults:prevResultsList)
		    	  {
		    	    prevResultData = new PositionManagementVO();
		    	    prevResultData.setConstituencyId((Long)prevResults[0]);
		    	    prevResultData.setConstituencyName(prevResults[1] != null?prevResults[1].toString():"");
		    	    prevResultData.setVotesEarned(new BigDecimal((Double)prevResults[2]));
		    	    prevResultData.setVotesPercengate(prevResults[3] != null?prevResults[3].toString():"");
		    	    prevResultData.setRank((Long)prevResults[4]);
		    	    prevResultList.add(prevResultData);
		    	  }
		    	  prevResult.setIsPartial(0L);
		    	  prevResult.setYear(electionPrev.getElectionYear());
		    	  prevResult.setPositionManagementVOList(prevResultList);
		    	  if(prevResultsList.size() > 0)
		    	  {
		    		    Object[] partyDetails = prevResultsList.get(0);
			            prevResult.setPartyId((Long)partyDetails[5]);
			            prevResult.setPartyName(partyDetails[6] != null?partyDetails[6].toString():""); 
		    	  }
		    	  positionManagementVO.setPreviousResult(prevResult);
		      }
	    	listData.add(positionManagementVO);	 
	      }
	    }
	    catch(Exception e)
	    {
	    	log.debug("Exception rised in getCurrentMinistersDetailsForCurrentAndPrevEle of ElectionLiveResultsAnalysisService",e);
	    }
	    return listData;
	  }	      
}
