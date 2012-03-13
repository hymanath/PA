package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyLeadCandidateDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionGoverningBodyDAO;
import com.itgrids.partyanalyst.dao.IKeyCandidateDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dto.AssignKeyCandidateVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultVO;
import com.itgrids.partyanalyst.dto.ElectionGoverningBodyVO;
import com.itgrids.partyanalyst.dto.ElectionLiveResultVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultVO;
import com.itgrids.partyanalyst.dto.PositionManagementVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionGoverningBody;
import com.itgrids.partyanalyst.model.KeyCandidate;
import com.itgrids.partyanalyst.model.Party;
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
	private IKeyCandidateDAO keyCandidateDAO;
	private ICandidateDAO candidateDAO;
	private IConstituencyElectionResultDAO constituencyElectionResultDAO;	
	private IPartyDAO partyDAO;
	private IAllianceGroupDAO allianceGroupDAO;
	
	public IConstituencyElectionResultDAO getConstituencyElectionResultDAO() {
		return constituencyElectionResultDAO;
	}

	public void setConstituencyElectionResultDAO(
			IConstituencyElectionResultDAO constituencyElectionResultDAO) {
		this.constituencyElectionResultDAO = constituencyElectionResultDAO;
	}

	public ICandidateDAO getCandidateDAO() {
		return candidateDAO;
	}

	public void setCandidateDAO(ICandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}

	public IKeyCandidateDAO getKeyCandidateDAO() {
		return keyCandidateDAO;
	}

	public void setKeyCandidateDAO(IKeyCandidateDAO keyCandidateDAO) {
		this.keyCandidateDAO = keyCandidateDAO;
	}

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
   
	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}

	public IAllianceGroupDAO getAllianceGroupDAO() {
		return allianceGroupDAO;
	}

	public void setAllianceGroupDAO(IAllianceGroupDAO allianceGroupDAO) {
		this.allianceGroupDAO = allianceGroupDAO;
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
	
	
	public ElectionLiveResultVO getOverViewCount(Long electionId)
	{
		try{
			ElectionLiveResultVO electionLiveResultVO = new ElectionLiveResultVO();
			Election election = electionDAO.get(electionId);
			Boolean isPartial = null;
			Boolean isFirstElectionAfterDelimtation = null;
			Long totalSeats = 0L;
			Long totalKnownCount = 0L;
			Long oldCount = 0L;
			Long newCount = 0L;
			
			if(election.getIsPartial() != null && election.getIsPartial().equalsIgnoreCase("1"))
				isPartial = true;
			else
				isPartial = false;
			
			isFirstElectionAfterDelimtation = getIsFirstElectionAfterDelimtation(election.getElectionScope().getElectionScopeId(),Long.parseLong(election.getElectionYear()));
			
			electionLiveResultVO.setPartialResult(isPartial);
			electionLiveResultVO.setIsFirstElectionAfterDelimtation(isFirstElectionAfterDelimtation);
			
			totalSeats = (Long)constituencyElectionDAO.getPCCountInAElection(electionId);
			
			electionLiveResultVO.setTotalSeats(totalSeats);
			
			if(isPartial)
				totalKnownCount = (Long)constituencyLeadCandidateDAO.getResultKnownConstituenciesCountInAElection(electionId);
			else
				totalKnownCount = (Long)constituencyElectionResultDAO.getResultKnownConstituenciesCountInAElection(electionId);
			
			electionLiveResultVO.setTotalKnownCount(totalKnownCount);
			
			if(isFirstElectionAfterDelimtation)
			{
				
				
				List<Object[]> list = constituencyElectionDAO.getOldAndNewConstituenciesInAElection(electionId);
				if(list != null && list.size() > 0)
				{
					for(Object[] params : list)
						if(params[1] == null)
							oldCount++;
						else
							newCount++;
				}
			}
			
			electionLiveResultVO.setOldConstituenciesCount(oldCount);
			electionLiveResultVO.setNewConstituenciesCount(newCount);
			return electionLiveResultVO;
			
		}catch (Exception e) {
			log.error("Exception Occured in getOverViewCount() Method, Exception is - "+e);
			return null;
		}
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
			{
				if(election.getElectionScope().getElectionType().getElectionType().equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE))
				{
					list = 	nominationDAO.getConstituencyWiseCandidatesParliament(electionId);
				}
				else
					list = nominationDAO.getConstituencyWiseCandidatesStates(electionId);
			}
			if(list != null && list.size() > 0)
			{
				resultList = new ArrayList<ConstituencyElectionResultVO>(0);
				ConstituencyElectionResultVO electionResultVO = null;
				for(Object[] params : list)
				{
					electionResultVO = new ConstituencyElectionResultVO();
					electionResultVO.setConstituencyId((Long)params[0]);
					electionResultVO.setConstituencyName(WordUtils.capitalize(params[1].toString().toLowerCase()));
					if(election.getElectionScope().getElectionType().getElectionType().equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE))
					{
						electionResultVO.setCandidateId((Long)params[2]);
						electionResultVO.setCandidateName(params[3].toString());
						electionResultVO.setPartyId((Long)params[4]);
						electionResultVO.setPartyName(params[5].toString());
					}
					else
					{
						electionResultVO.setDistrictId((Long)params[2]);
						electionResultVO.setDistrictName(params[3].toString());
						electionResultVO.setCandidateId((Long)params[4]);
						electionResultVO.setCandidateName(params[5].toString());
						electionResultVO.setPartyId((Long)params[6]);
						electionResultVO.setPartyName(params[7].toString());
					}
					
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
			Long stateId = 0l;
			Election election = electionDAO.get(electionId);
			Long prevElectionId = getPreviousElectionId(electionId);
			String electionType = election.getElectionScope().getElectionType().getElectionType();
			if(election.getElectionScope().getState()!=null){
				stateId = election.getElectionScope().getState().getStateId();
			}
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
								{
									prevResult.add(optionVO);
									electionLiveResultVO.setWonFromOtherPartiesCount(electionLiveResultVO.getWonFromOtherPartiesCount() + 
											optionVO.getId());
								}
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
			
			if(electionLiveResultVO.getLostConstIdsList() != null && electionLiveResultVO.getLostConstIdsList().size() > 0)
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
  public List<PositionManagementVO> getCurrentMinistersDetailsForCurrentAndPrevEle(Long type,Long stateId,String year,Long elecId,String reqtype)
    {   
	     if(log.isDebugEnabled())
	    	 log.debug("Enter into getCurrentMinistersDetailsForCurrentAndPrevEle of ElectionLiveResultsAnalysisService");
	    Long prevElectionId = getPreviousElectionId(elecId);
	    List<PositionManagementVO> listData = new ArrayList<PositionManagementVO>();
	  try
	  {
		  List<Object[]> candidates = null ;
		if(!(reqtype.trim().equalsIgnoreCase("ImportantCandidates")))
		{	
			//retrieving all the previous election ministers id's  
	        candidates = electionGoverningBodyDAO.getAllMinistersIdsAndMinistry(prevElectionId);
	        candidates = getUniqueCandidates(candidates);
		}
		else
		{
			//retrieving all the important candidates which are nominated in previous election by using previous electionId
			candidates = nominationDAO.getimportantCandidatesDetails(elecId,prevElectionId);
			candidates = getUniqueInImpCandidates(candidates);
		}
	    PositionManagementVO positionManagementVO = null;
	    // iterating individual candidate and getting their current and previous election performance details
	    for(Object[] candDetails :candidates)
	    {
	    	positionManagementVO = new PositionManagementVO();
	    	positionManagementVO.setCandidateId((Long)candDetails[0]);
	    	positionManagementVO.setCandidateName(candDetails[1] != null?candDetails[1].toString():"");
	    	if(!(reqtype.trim().equalsIgnoreCase("ImportantCandidates")))
	    	positionManagementVO.setPositionName(candDetails[2] != null?candDetails[2].toString():"");
	    	// getting all current election performance details
	    	Election election = electionDAO.get(elecId);
	    	
	    	  PositionManagementVO currentResult = new PositionManagementVO();
	    	  List<PositionManagementVO> currentResultList = new ArrayList<PositionManagementVO>();
	    	  PositionManagementVO currentResultData = null;
	    	  //checking whether the result is partial or not
		      if(election.getIsPartial() != null && election.getIsPartial().trim().equalsIgnoreCase("1"))
		      {
		    	  //partial result
		    	 List<Object[]> constiList = nominationDAO.getConstituencyDetails((Long)candDetails[0], elecId);
		       for(Object[] constis : constiList)
		       {
		    	   List<Object[]> currentResultsList = constituencyLeadCandidateDAO.getCandidateResultsForPartialElec((Long)constis[0],elecId);
		    	   currentResultData = new PositionManagementVO();
		    	  if(currentResultsList.size() >0)
		    	  {
		    		  Object[] result =  currentResultsList.get(0);
		    		  
		    		  if(((Long)result[0]).longValue() ==  ((Long)candDetails[0]).longValue())
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
		      
		   // getting all previous election performance details
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
			    		  
			    		  if(((Long)result[0]).longValue() ==  ((Long)candDetails[0]).longValue())
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
			       prevResult.setYear(electionPrev.getElectionYear());
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
  public List<SelectOptionVO> getCandidatesBasedOnSelection(String candidateName ,Long stateId , Long partyId){
	  List<SelectOptionVO> selectOptionList = new ArrayList<SelectOptionVO>(0);
		SelectOptionVO optionVO = new SelectOptionVO();
		List<Object[]> candidatesList = keyCandidateDAO.getCandidatesBasedOnPartyId(candidateName,stateId,partyId);
		
		try{
			if(candidatesList!=null){
				for(Object[] params : candidatesList){
			optionVO = new SelectOptionVO();
			optionVO.setId(new Long(params[0].toString()));
			optionVO.setName(WordUtils.capitalize(params[1].toString().toLowerCase()));
			selectOptionList.add(optionVO);
		}
	}
		 return selectOptionList;
	}catch(Exception e){
		e.printStackTrace();
		return selectOptionList;
	}
	  
  }
  
  public ResultStatus saveKeyCandidates(AssignKeyCandidateVO assignKeyCandidateVO){
	  log.debug("Enter into saveKeyCandidates of ElectionLiveResultsAnalysisService");
	  ResultStatus resultStatus = new ResultStatus();
	  try{
	  KeyCandidate assignKeyCandidate = null;
	  Long count = (Long)keyCandidateDAO.getCountCandidate(assignKeyCandidateVO.getKeyCandidateId());
	  
	  if(count.longValue() == 1L)
		  assignKeyCandidate = keyCandidateDAO.getCandidateById(assignKeyCandidateVO.getKeyCandidateId()).get(0);
	  else
		  assignKeyCandidate = new KeyCandidate();
	  assignKeyCandidate.setCandidate(candidateDAO.get(assignKeyCandidateVO.getKeyCandidateId()));
	  assignKeyCandidate.setDescription(assignKeyCandidateVO.getDescription());
	  keyCandidateDAO.save(assignKeyCandidate);
  }catch(Exception e){
	  log.error("Exception Raised :" + e);
	  resultStatus.setExceptionEncountered(e);
	  resultStatus.setExceptionMsg(e.getMessage());
	  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		
	 return resultStatus;  
  }
  return resultStatus;  
  }
	
  public List<PositionManagementVO> getDistrictWisePartyPerfDetails(Long electionId,Long stateId)
  {
	 if(log.isDebugEnabled())
    	log.debug("Enter into getDistrictWisePartyPerfDetails of ElectionLiveResultsAnalysisService");
	    List<PositionManagementVO> returnVal = new ArrayList<PositionManagementVO>();
	    try
	    {	    
	      List<Object[]> districtsList = nominationDAO.getAllDistrictsForAnElection(electionId);
		  Long prevElectionId = getPreviousElectionId(electionId);
		  boolean currentPartial = false;
		  boolean previousPartial = false;
		  if(prevElectionId != null)
		   {			    	
			  Election currentElection = electionDAO.get(electionId);
		      Election PrevElection = electionDAO.get(prevElectionId);
		      
		      if(currentElection.getIsPartial() != null && currentElection.getIsPartial().trim().equalsIgnoreCase("1"))
		      {
		    	  currentPartial = true;
		      }
		      if(PrevElection.getIsPartial() != null && PrevElection.getIsPartial().trim().equalsIgnoreCase("1"))
		      {
		    	  previousPartial = true;
		      }
		      
    		  List<SelectOptionVO> presentPartiesList = getAllParties(currentElection,stateId,currentPartial);
    		  
			  List<SelectOptionVO> previousPartiesList = getAllParties(PrevElection,stateId,previousPartial);
			  
			  PositionManagementVO district = null;
			  for(Object[] districts: districtsList)
			   {
				  district = new PositionManagementVO();
				  district.setDistrictId((Long)districts[0]);
				  district.setDistrictName(districts[1]!=null?districts[1].toString():"");
				  if(currentPartial)
				    district.setCurrResPartial(1l);
				  if(previousPartial)
				    district.setPrevResPartial(1l);
				  
				  List<Long> currentConstiList = nominationDAO.getAllConstituenciesInADistrict((Long)districts[0],electionId);
			      List<Long> prevConstiList = nominationDAO.getAllConstituenciesInADistrict((Long)districts[0],prevElectionId);
			    	 
			      List<PositionManagementVO> currentDistPartRes = new ArrayList<PositionManagementVO>();
			      PositionManagementVO partyWise = null;
			      
			      for(SelectOptionVO party : presentPartiesList)
			      {
			    	  partyWise = new PositionManagementVO();
			    	  partyWise.setPartyId(party.getId());
			    	  partyWise.setPartyName(party.getName());
			    	  if(currentPartial)
			    	  {
			    		 partyWise.setTotalCount(new Long(currentConstiList.size()));
				    	 List<Long> knownResultsCount =  constituencyLeadCandidateDAO.getTotalResultsKnown(electionId,currentConstiList);
				    	 partyWise.setKnownResultsCount(knownResultsCount.get(0));
				    	 List<Object[]> partyWinConstis = constituencyLeadCandidateDAO.getPartyWinConst(party.getId(),electionId,currentConstiList);
				    	 partyWise.setPartyCount(new Long(partyWinConstis.size()));
				    	 currentDistPartRes.add(partyWise);   			    		  
			    	  }
			    	  else
			    	  {
			    		  partyWise.setTotalCount(new Long(currentConstiList.size()));
			    		  List<Long> partyWonCount =   nominationDAO.getAllWonCountPartyWise((Long)districts[0],electionId,party.getId());
			    		  partyWise.setPartyCount(partyWonCount.get(0));
			    		  currentDistPartRes.add(partyWise);  
			    	  }			    	  			    	  
			      }
			      List<PositionManagementVO> prevDistPartRes = new ArrayList<PositionManagementVO>();
			      for(SelectOptionVO party : previousPartiesList)
			      {
			    	  partyWise = new PositionManagementVO();
			    	  partyWise.setPartyId(party.getId());
			    	  partyWise.setPartyName(party.getName());
			    	  if(previousPartial)
			    	  {
			    		partyWise.setTotalCount(new Long(prevConstiList.size()));
			    		List<Long> knownResultsCount =  constituencyLeadCandidateDAO.getTotalResultsKnown(prevElectionId,prevConstiList);
			    		partyWise.setKnownResultsCount(knownResultsCount.get(0));
			    		List<Object[]> partyWinConstis = constituencyLeadCandidateDAO.getPartyWinConst(party.getId(),prevElectionId,prevConstiList);
			    		partyWise.setPartyCount(new Long(partyWinConstis.size()));
			    		prevDistPartRes.add(partyWise); 
			    	  }
			    	  else
			    	  {
			    	    partyWise.setTotalCount(new Long(prevConstiList.size()));
			    		List<Long> partyWonCount =   nominationDAO.getAllWonCountPartyWise((Long)districts[0],prevElectionId,party.getId());
			    		partyWise.setPartyCount(partyWonCount.get(0));
			    		prevDistPartRes.add(partyWise); 
			    		  
			    	  }		
			      }
			      
			      district.setCurrentResultList(currentDistPartRes);
			      district.setPreviousResultList(prevDistPartRes);
			      district.setPresentYear(currentElection.getElectionYear());
			      district.setPrevYear(PrevElection.getElectionYear());
			      returnVal.add(district);
			   }
		   }
	    }
	    catch(Exception e)
	    {
	       log.debug("Exception rised in getDistrictWisePartyPerfDetails of ElectionLiveResultsAnalysisService",e);
	    }
	  return returnVal;
  }
  
  private List<SelectOptionVO> getAllParties(Election election,Long stateId,boolean partialOrFull)
  {
	  List<SelectOptionVO> returnVal = new ArrayList<SelectOptionVO>();
	  SelectOptionVO selectOptionVO = null;
	  if(partialOrFull)
	  {
		   returnVal = staticDataService.getStaticPartiesListForAState(stateId);
		   List<Object[]> partiesList = nominationDAO.getAllPartiesForPartialElec(election.getElectionId());
		   if(!(partiesList.size()>0))
			   return returnVal;
		    for(Object[] party:partiesList)
		    {
		    	selectOptionVO = new SelectOptionVO();
		    	selectOptionVO.setId((Long)party[0]);
		    	selectOptionVO.setName(party[1]!=null?party[1].toString():"");
		    	returnVal.add(selectOptionVO);		    	
		    }
		   
		   Map<String,SelectOptionVO> partiesMap = new HashMap<String,SelectOptionVO>();
		   for(SelectOptionVO selOpVo: returnVal)
		   {
			   partiesMap.put(selOpVo.getName(), selOpVo);
		   }
		   List<SelectOptionVO> parties = new ArrayList<SelectOptionVO>();
		   Set<String> keys = partiesMap.keySet();
		   List<String> partyKeys = new ArrayList<String>();
		   for(String partykey:keys)
		   {
			   partyKeys.add(partykey);
		   }
		   Collections.sort(partyKeys);
		   for(String val :partyKeys)
		   {
			   parties.add(partiesMap.get(val));
		   }
		   returnVal = parties;
	  }
	  else
	  {
		  List<Object[]> partiesList = nominationDAO.getAllPartiesWithAtleastOneWon(election.getElectionId());
		    for(Object[] party:partiesList)
		    {
		    	selectOptionVO = new SelectOptionVO();
		    	selectOptionVO.setId((Long)party[0]);
		    	selectOptionVO.setName(party[1]!=null?party[1].toString():"");
		    	returnVal.add(selectOptionVO);
		    	
		    }
	  }
		  
	  return returnVal;
  }
  public List<PositionManagementVO> getWinCountForGraphs(Long electionId,Long stateId,Long districtId)
  {
	 if(log.isDebugEnabled())
	    log.debug("Enter into getWinCountForGraphs of ElectionLiveResultsAnalysisService");
	  List<PositionManagementVO> returnVal = new ArrayList<PositionManagementVO>();
	try
	{
	  Long prevElectionId = getPreviousElectionId(electionId);
	  Election currentElection = electionDAO.get(electionId);
      Election PrevElection = electionDAO.get(prevElectionId);
      List<Long> currentConstiList = nominationDAO.getAllConstituenciesInADistrict(districtId,electionId);
      List<Long> prevConstiList = nominationDAO.getAllConstituenciesInADistrict(districtId,prevElectionId);
      boolean currentPartial = false;
	  boolean previousPartial = false;
	  List<SelectOptionVO> partiesList = new ArrayList<SelectOptionVO>();
	  if(currentElection.getIsPartial() != null && currentElection.getIsPartial().trim().equalsIgnoreCase("1"))
      {
    	  currentPartial = true;
      }
      if(PrevElection.getIsPartial() != null && PrevElection.getIsPartial().trim().equalsIgnoreCase("1"))
      {
    	  previousPartial = true;
      }
      List<Object[]> presentParties =null; 
	  
	  List<Object[]> previousParties =null; 
	  if(currentPartial)
	  {
		  presentParties = nominationDAO.getAllPartiesForPartialElecForADistrict(electionId,districtId);
		  partiesList = staticDataService.getStaticPartiesListForAState(stateId);
	  }
	  else
	  {
		  presentParties = nominationDAO.getAllPartiesWithAtleastOneWonForADistrict(electionId,districtId);
		  
	  }
	  if(previousPartial)
	  {
		  previousParties = nominationDAO.getAllPartiesForPartialElecForADistrict(prevElectionId, districtId);
		  partiesList = staticDataService.getStaticPartiesListForAState(stateId);
	  }
	  else
	  {
		  previousParties = nominationDAO.getAllPartiesWithAtleastOneWonForADistrict(prevElectionId, districtId);
	  }
	  SelectOptionVO selectOptionVO = null;
	  for(Object[] parties:presentParties)
	  {
		  selectOptionVO = new SelectOptionVO();
		  selectOptionVO.setId((Long)parties[0]);
		  selectOptionVO.setName(parties[1]!=null?parties[1].toString():"");
		  partiesList.add(selectOptionVO);
	  }
	  for(Object[] parties:previousParties)
	  {
		  selectOptionVO = new SelectOptionVO();
		  selectOptionVO.setId((Long)parties[0]);
		  selectOptionVO.setName(parties[1]!=null?parties[1].toString():"");
		  partiesList.add(selectOptionVO);
	  }
	  
	  Map<String,SelectOptionVO> partiesMap = new HashMap<String,SelectOptionVO>();
	   for(SelectOptionVO selOpVo: partiesList)
	   {
		   partiesMap.put(selOpVo.getName(), selOpVo);
	   }
	   List<SelectOptionVO> parties = new ArrayList<SelectOptionVO>();
	   Set<String> keys = partiesMap.keySet();
	   List<String> partyKeys = new ArrayList<String>();
	   for(String partykey:keys)
	   {
		   partyKeys.add(partykey);
	   }
	   Collections.sort(partyKeys);
	   for(String val :partyKeys)
	   {
		   parties.add(partiesMap.get(val));
	   }
	   PositionManagementVO positionManagementVO = null;
	   for(SelectOptionVO  party: parties)
	   {
		   positionManagementVO = new PositionManagementVO();
		   positionManagementVO.setPartyId(party.getId());
		   positionManagementVO.setPartyName(party.getName());
		   positionManagementVO.setPresentYear(currentElection.getElectionYear());
		   positionManagementVO.setPrevYear(PrevElection.getElectionYear());
		   if(currentPartial)
			{
			   List<Object[]> partyWinConstis = constituencyLeadCandidateDAO.getPartyWinConst(party.getId(),electionId,currentConstiList);
			   positionManagementVO.setPresentCount(new Long(partyWinConstis.size()));
			}
		   else
		   {
			   List<Long> partyWonCount =   nominationDAO.getAllWonCountPartyWise(districtId,electionId,party.getId());
			   positionManagementVO.setPresentCount(partyWonCount.get(0));
		   }
		   if(previousPartial)
			{
			   List<Object[]> partyWinConstis = constituencyLeadCandidateDAO.getPartyWinConst(party.getId(),prevElectionId,prevConstiList);
			   positionManagementVO.setPrevCount(new Long(partyWinConstis.size()));
			}
		   else
		   {
			   List<Long> partyWonCount =   nominationDAO.getAllWonCountPartyWise(districtId,prevElectionId,party.getId());
			   positionManagementVO.setPrevCount(partyWonCount.get(0));
		   }
		   returnVal.add(positionManagementVO);
	   }
	}
	catch(Exception e)
	{
		 log.debug("Exception rised in getWinCountForGraphs of ElectionLiveResultsAnalysisService",e);
	}
	return returnVal;
  }
  public List<PositionManagementVO> getAllPartiesForPartialElection(Long electionId,Long stateId)
  {
	  List<PositionManagementVO> returnVal = new ArrayList<PositionManagementVO>();
	  if(log.isDebugEnabled())
		   log.debug("Enter into getAllPartiesForPartialElection of ElectionLiveResultsAnalysisService");
	  try
	   { 
		  Election election = electionDAO.get(electionId);
		  if(election.getIsPartial() != null && election.getIsPartial().trim().equalsIgnoreCase("1"))
	      {
			  List<SelectOptionVO> partiesList = getAllParties(election,stateId,true);
			  PositionManagementVO positionManagementVO = null;
			  for(SelectOptionVO party:partiesList)
			  {
				  positionManagementVO = new PositionManagementVO();
				  positionManagementVO.setPartyId(party.getId());
				  positionManagementVO.setPartyName(party.getName());
				  returnVal.add(positionManagementVO);
			  }
	      }
	   }
	  catch(Exception e)
	   {
		 log.debug("Exception rised in getAllPartiesForPartialElection of ElectionLiveResultsAnalysisService",e);
	   }
	  return returnVal;
  }
  public  List<PositionManagementVO> getAllPartiesPerfoDistWiseForPartialEle(Long electionId,List<Long> partiesList)
  {
	 if(log.isDebugEnabled())
	   log.debug("Enter into getAllPartiesPerfoDistWiseForPartialEle of ElectionLiveResultsAnalysisService");
	 List<PositionManagementVO> returnVal = new ArrayList<PositionManagementVO>();
	 PositionManagementVO positionManagementVO = null;
	 List<PositionManagementVO> partiesInDist = null;
	 try
	 { 
	  List<Object[]> districtsList = nominationDAO.getAllDistrictsForAnElection(electionId);
	  Election election = electionDAO.get(electionId);
    	for(Object[] districts:districtsList)
    	 {
    		positionManagementVO = new PositionManagementVO();
    		positionManagementVO.setDistrictId((Long)districts[0]);
    		positionManagementVO.setDistrictName(districts[1]!=null?districts[1].toString():"");
    		partiesInDist = new ArrayList<PositionManagementVO>();
    		for(Long partyId:partiesList)
    		{
    		   PositionManagementVO partyWise = new PositionManagementVO();
    		   Party party = partyDAO.get(partyId);
    		   partyWise.setPartyName(party.getShortName());
    		   partyWise.setPartyId(partyId);
    		   List<Long> constiList = nominationDAO.getAllConstituenciesInADistrict((Long)districts[0],electionId);
    		   if(election.getIsPartial() != null && election.getIsPartial().trim().equalsIgnoreCase("1"))
    		   {  
        	       List<Object[]> partyWinConstis = constituencyLeadCandidateDAO.getPartyWinConstituencies(partyId,electionId,constiList);
        	       partyWise.setPartyCount(new Long(partyWinConstis.size()));
    		   }
        	   else
        	   {
        		   List<Long> partyPrevWonCount =   nominationDAO.getAllWonCountPartyWise((Long)districts[0],electionId,partyId); 
        		   partyWise.setPartyCount(new Long(partyPrevWonCount.get(0)));
        	   }
	    	   
	    	   
	    	   partiesInDist.add(partyWise);
    		}
    		positionManagementVO.setPositionManagementVOList(partiesInDist);
    		returnVal.add(positionManagementVO);
    	 }
	 }
	 catch(Exception e)
		{
			 log.debug("Exception rised in getAllPartiesPerfoDistWiseForPartialEle of ElectionLiveResultsAnalysisService",e);
		}
	  return returnVal;
  }
  

  public List<PartyElectionResultVO> getGenderAnalysisInElection(Long electionId){
	  
	 log.debug("Entered Into getGenderAnalysisInElection() in ElectionLiveResultsAnalysisService" );
	  try{
		  List<PartyElectionResultVO> partyResult = null;
		  Election election = electionDAO.get(electionId);
		  if(election.getIsPartial().equalsIgnoreCase("1"))
		  {
		  
		  List<Object[]> result = constituencyLeadCandidateDAO.getGenderAnalysisElectionresults(electionId);
		  if(result !=null && result.size() >0)
		  {
			  partyResult = new ArrayList<PartyElectionResultVO>(0);
			  for(Object[] params: result)
			  {
				  Long partyId = (Long)params[0];
				  boolean isNew = false;
				  PartyElectionResultVO partyElectionResultVO = getPartyElectionResultVOFromList(partyResult,partyId);
				  
				  if(partyElectionResultVO == null)
				  {
					  partyElectionResultVO = new PartyElectionResultVO();
					  partyElectionResultVO.setPartyId((Long)params[0]);
					  partyElectionResultVO.setPartyName(params[1].toString());
					  partyElectionResultVO.setTotalSeatsWon(1L);
					  isNew = true;
					  
					  if(params[2].toString() != null)
					  {
						  if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
							  partyElectionResultVO.setMaleWon(1L);
						  else
							  partyElectionResultVO.setFemaleWon(1L);
					  }
				  }
				  else
				  {
					  partyElectionResultVO.setTotalSeatsWon(partyElectionResultVO.getTotalSeatsWon() + 1L);
					  if(params[2].toString() != null)
					  {
						  if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
							  partyElectionResultVO.setMaleWon(partyElectionResultVO.getMaleWon() != null?
									  partyElectionResultVO.getMaleWon()+1L : 1L);
						  else
							  partyElectionResultVO.setFemaleWon(partyElectionResultVO.getFemaleWon() != null ?
									  partyElectionResultVO.getFemaleWon() + 1L : 1L);
					  }
				  }
				  
				  if(isNew)
					  partyResult.add(partyElectionResultVO);
		  }
			  
		  List<Object[]> list = nominationDAO.getGenderDetailsOfParties(electionId);
		  
		  if(list != null && list.size() > 0)
		  {
			 for(Object[] params : list)
			 {
				 Long partyId = (Long)params[0];
				 PartyElectionResultVO partyElectionResultVO = getPartyElectionResultVOFromList(partyResult,partyId);
				 
				 if(partyElectionResultVO != null)
				 {
					 partyElectionResultVO.setTotalParticipated(partyElectionResultVO.getTotalParticipated() != null ?
						partyElectionResultVO.getTotalParticipated() + 1L : 1L);
					 
					 if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
						partyElectionResultVO.setMalePerticipated(partyElectionResultVO.getMalePerticipated() != null ?
						partyElectionResultVO.getMalePerticipated() + 1L : 1L);
					 else
						partyElectionResultVO.setFemalePerticipated(partyElectionResultVO.getFemalePerticipated() != null ?
						partyElectionResultVO.getFemalePerticipated() + 1L : 1L);
				 }
			 }
		  }
			  
			
		 }
		  
		}
		  return partyResult;
	  }catch(Exception e){
		  e.printStackTrace();
		  return null;
	  }
	 
  }
  
  public PartyElectionResultVO getPartyElectionResultVOFromList(List<PartyElectionResultVO> list,Long partyId)
	{
		try{
			for(PartyElectionResultVO resultVO : list)
				if(resultVO.getPartyId().longValue() == partyId.longValue())
					return resultVO;
			return null;
		}catch (Exception e) {
			log.error("Exception occured in getPartyElectionResultVOFromList() Method, Exception is - "+e);
			return null;
		}
	}
  public List<Object[]> getUniqueCandidates(List<Object[]> candidates)
  {
	  List<Object[]> returnval = new ArrayList<Object[]>();
	  Map<Long,Object[]> data = new HashMap<Long,Object[]>();
	  for(Object[] candidate:candidates)
	  {
		  if(data.get(((Long)candidate[0]).longValue())!= null)
		  {
			  Object[] value = data.get(((Long)candidate[0]).longValue());
			  value[2] = value[2].toString()+" , "+candidate[2].toString();
			  data.put(((Long)candidate[0]).longValue(), value);
		  }
		  else
		  {
			  data.put(((Long)candidate[0]).longValue(), candidate);
		  }
	  }
	  Set<Long> keys = data.keySet();
	  for(Long key:keys)
	  {
		  returnval.add(data.get(key));
	  }
	  return returnval;
  }
  
  public List<Object[]> getUniqueInImpCandidates(List<Object[]> candidates)
  {
	  log.debug("Entered into getUniqueInImpCandidates() Method");
	  try{
		  List<Object[]> result = new ArrayList<Object[]>(0);
		  for(Object[] candidate : candidates)
		  {
			  boolean flag = false;
			  for(Object[] param : result)
			  {
				  if(((Long)param[0]).longValue() == ((Long)candidate[0]).longValue())
					  flag = true;
			  }
			  if(!flag)
				  result.add(candidate);  
		  }
		  return result;
	  }catch (Exception e) {
		  log.error("Exception Occured in getUniqueInImpCandidates() Method, Exception is - "+e);
		  return candidates;
	  }
	  
  }
  
  public List<PositionManagementVO> getDistrictWisePartyPerfDetailsNew(Long electionId,Long stateId,List<Long> partyIds)
  {
	 if(log.isDebugEnabled())
    	log.debug("Enter into getDistrictWisePartyPerfDetailsNew of ElectionLiveResultsAnalysisService");
	    List<PositionManagementVO> returnVal = new ArrayList<PositionManagementVO>();
	    try
	    {	    
	      List<Object[]> districtsList = nominationDAO.getAllDistrictsForAnElection(electionId);
	      List<SelectOptionVO> partiesList = getAllPartiesAsList(partyIds);
	      
	     /* SelectOptionVO selectOptionVO = new SelectOptionVO();
	      selectOptionVO.setName("Others");
	      partiesList.add(selectOptionVO);
	      List<Long> partyIdslist = getPartiesIdsAsList(partiesList);*/
		  Long prevElectionId = getPreviousElectionId(electionId);
		  boolean currentPartial = false;
		  boolean previousPartial = false;
		  if(prevElectionId != null)
		   {			    	
			  Election currentElection = electionDAO.get(electionId);
		      Election PrevElection = electionDAO.get(prevElectionId);
		      
		      if(currentElection.getIsPartial() != null && currentElection.getIsPartial().trim().equalsIgnoreCase("1"))
		      {
		    	  currentPartial = true;
		      }
		      if(PrevElection.getIsPartial() != null && PrevElection.getIsPartial().trim().equalsIgnoreCase("1"))
		      {
		    	  previousPartial = true;
		      }
		      
			  
			  PositionManagementVO district = null;
			  for(Object[] districts: districtsList)
			   {
				  district = new PositionManagementVO();
				  district.setDistrictId((Long)districts[0]);
				  district.setDistrictName(districts[1]!=null?districts[1].toString():"");
				  district.setPresentYear(currentElection.getElectionYear());
			      district.setPrevYear(PrevElection.getElectionYear());
				  
				  List<Long> currentConstiList = nominationDAO.getAllConstituenciesInADistrict((Long)districts[0],electionId);
			      List<Long> prevConstiList = nominationDAO.getAllConstituenciesInADistrict((Long)districts[0],prevElectionId);
			   
				  if(currentPartial)
				   {
				    district.setCurrResPartial(1l);
					district.setTotalCount(new Long(currentConstiList.size()));
					List<Long> knownResultsCount =  constituencyLeadCandidateDAO.getTotalResultsKnown(electionId,currentConstiList);
					district.setPresentCount(knownResultsCount.get(0));
				   }
				  else
				   {
				     district.setTotalCount(new Long(currentConstiList.size()));
					 district.setPresentCount(new Long(currentConstiList.size()));
				   }
				  if(previousPartial)
				   {
				     district.setPrevResPartial(1l);
					 district.setPrevTotalCount(new Long(prevConstiList.size()));
			    	 List<Long> knownResultsCount =  constituencyLeadCandidateDAO.getTotalResultsKnown(prevElectionId,prevConstiList);
			    	 district.setPrevCount(knownResultsCount.get(0));
				   }
				  else
				   {
				     district.setPrevTotalCount(new Long(prevConstiList.size()));
					 district.setPrevCount(new Long(prevConstiList.size()));
				   }
				   
			      List<PositionManagementVO> distPartiesResult = new ArrayList<PositionManagementVO>();
			      PositionManagementVO partyWise = null;
			      
			      for(SelectOptionVO party : partiesList)
			      {
			    	  partyWise = new PositionManagementVO();
			    	  partyWise.setPartyId(party.getId());
			    	  partyWise.setPartyName(party.getName());
			    	  boolean partyPrevPresent = false;
			    	  List<Object> list =  nominationDAO.getPartyPresenceInAnElection(party.getId(),prevElectionId);
			    	  if(((Long)list.get(0)).longValue() > 0l)
						{
			    		  partyPrevPresent = true;
						}
			    	/*if(!(party.getName().equalsIgnoreCase("Others")))
			    	{*/	 
			    	  if(currentPartial)
			    	  {
				    	 List<Object[]> partyWinConstis = constituencyLeadCandidateDAO.getPartyWinConst(party.getId(),electionId,currentConstiList);
				    	 partyWise.setPartyCount(new Long(partyWinConstis.size()));		    		  
			    	  }
			    	  else
			    	  {
			    		  List<Long> partyWonCount =   nominationDAO.getAllWonCountPartyWise((Long)districts[0],electionId,party.getId());
			    		  partyWise.setPartyCount(partyWonCount.get(0));
			    	  }
                      if(previousPartial)
			    	  {
			    		List<Object[]> partyWinConstis = constituencyLeadCandidateDAO.getPartyWinConst(party.getId(),prevElectionId,prevConstiList);
			    		partyWise.setPartyPrevCount(new Long(partyWinConstis.size()));
			    		if(!partyPrevPresent)
			    			partyWise.setPartyPrevPresence(1l);
			    	  }
			    	  else
			    	  {
			    		List<Long> partyWonCount =   nominationDAO.getAllWonCountPartyWise((Long)districts[0],prevElectionId,party.getId());
			    		partyWise.setPartyPrevCount(partyWonCount.get(0));
			    		if(!partyPrevPresent)
			    			partyWise.setPartyPrevPresence(1l);
			    	  }	
			    	 //}
			    	/*else
			    	{
			    	  
			    	  if(currentPartial)
			    	  {
				    	 List<Long> partyWinConstis = constituencyLeadCandidateDAO.getOtherPartiesWinCount(partyIdslist,electionId,currentConstiList);
				    	 partyWise.setPartyCount(partyWinConstis.get(0));		    		  
			    	  }
			    	  else
			    	  {
			    		  List<Long> partyWonCount = nominationDAO.getAllOtherPartiesWonCount((Long)districts[0],electionId,partyIdslist);
			    		  partyWise.setPartyCount(partyWonCount.get(0));
			    	  }
                      if(previousPartial)
			    	  {
                    	  List<Long> partyWinConstis = constituencyLeadCandidateDAO.getOtherPartiesWinCount(partyIdslist,prevElectionId,prevConstiList);
			    		partyWise.setPartyPrevCount(partyWinConstis.get(0));	
			    	  }
			    	  else
			    	  {
			    		List<Long> partyWonCount = nominationDAO.getAllOtherPartiesWonCount((Long)districts[0],prevElectionId,partyIdslist);
			    		partyWise.setPartyPrevCount(partyWonCount.get(0));
			    		  
			    	  }	
			    	}*/
                    distPartiesResult.add(partyWise);					  
			      }	
                   district.setPositionManagementVOList(distPartiesResult);				  
			      
			      returnVal.add(district);
			   }
		   }
	    }
	    catch(Exception e)
	    {
	       log.error("Exception rised in getDistrictWisePartyPerfDetailsNew of ElectionLiveResultsAnalysisService",e);
	    }
	  return returnVal;
  }
  public List<SelectOptionVO> getAllPresentStaticParties(List<SelectOptionVO> staticParties,Long electionId)
  {
	  List<SelectOptionVO> returnVal = new ArrayList<SelectOptionVO>();
	   for(SelectOptionVO selectOptionVO:staticParties)
	   {
		   List<Object> list =  nominationDAO.getPartyPresenceInAnElection(selectOptionVO.getId(),electionId);
			if(((Long)list.get(0)).longValue() > 0l)
			{
				returnVal.add(selectOptionVO);
			}
	   }
	  return returnVal;
  }
  public List<Long> getPartiesIdsAsList(List<SelectOptionVO> staticParties)
  {
	  List<Long> partyIds = new ArrayList<Long>();
	    for(SelectOptionVO selectOptionVO:staticParties)
	    {
	    	if(!(selectOptionVO.getName().equalsIgnoreCase("Others")))
	    	   partyIds.add(selectOptionVO.getId());
	    }	  
	  return partyIds;
  }
  public List<PositionManagementVO> getAllPartiesForAnElec(Long electionId,Long stateId)
  {
	  if(log.isDebugEnabled())
	    log.debug("Enter into getAllPartiesForAnElec of ElectionLiveResultsAnalysisService");
	  List<PositionManagementVO> returnVal = new ArrayList<PositionManagementVO>();
	  PositionManagementVO positionManagementVO = null;
	 try
	 {
	  //List<Object[]> parties = nominationDAO.getAllPartiesForAnElec(electionId);
	  List<SelectOptionVO> partiesList = getAllPresentStaticParties(staticDataService.getStaticPartiesListForAState(stateId),electionId);
	   for(SelectOptionVO selectOptionVO:partiesList)
	   {
		   positionManagementVO = new PositionManagementVO();
		   positionManagementVO.setPartyId(selectOptionVO.getId());
		   positionManagementVO.setPartyName(selectOptionVO.getName());
		   returnVal.add(positionManagementVO);
	   }
	   
	 }
	 catch(Exception e)
	 {
		 log.error("Exception rised in getAllPartiesForAnElec of ElectionLiveResultsAnalysisService",e);
	 }
	 return returnVal;
  }
  
  public List<SelectOptionVO> getAllPartiesAsList(List<Long> parties)
  {
	  if(log.isDebugEnabled())
	    log.debug("Enter into getAllPartiesAsList of ElectionLiveResultsAnalysisService");
	  List<SelectOptionVO> returnVal = new ArrayList<SelectOptionVO>();
	 try
	 {
	  SelectOptionVO selectOptionVO = null;
	    for(Long partyId:parties)
	    {
	    	selectOptionVO = new SelectOptionVO();
	    	Party party = partyDAO.get(partyId);
	    	selectOptionVO.setId(partyId);
	    	selectOptionVO.setName(party.getShortName());
	    	returnVal.add(selectOptionVO);
	    }
	 }
	 catch(Exception e)
	 {
		 log.error("Exception rised in getAllPartiesAsList of ElectionLiveResultsAnalysisService",e);
	 }
	 return returnVal;
  }
  public  List<PositionManagementVO>  getAllPartyCountsDistrictWise(Long electionId,List<Long> partyIds,boolean includeAlliances,String type)
  {
	  if(log.isDebugEnabled())
	    	log.debug("Enter into getAllPartyCountsDistrictWise of ElectionLiveResultsAnalysisService");
	  List<SelectOptionVO> partiesList = null;
		    List<PositionManagementVO> returnVal = new ArrayList<PositionManagementVO>();
		    try
		    {	    
		      List<Object[]> districtsList = nominationDAO.getAllDistrictsForAnElection(electionId);
		      if(includeAlliances)
		      {
		    	  Set<Long> partyIdNew = new HashSet<Long>();
		    	  for(Long partyId:partyIds)
		    	  {
		    		  partyIdNew.add(partyId);
		    		  List allianceList = allianceGroupDAO.findAlliancePartiesByElectionAndPartyExcludeParty(electionId, partyId);
		    		   for(int i=0;i<allianceList.size();i++)
		    		   {
		    			   Object[] allianceParty = (Object[])allianceList.get(i);
		    			   partyIdNew.add((Long)allianceParty[1]);
		    		   }
		    	  }
		    	 List<Long> partyList = new ArrayList<Long>();
		    	  for(Long partyId :partyIdNew)
		    		  partyList.add(partyId);
		    	  partiesList = getAllPartiesAsList(partyList); 
		      }
		      else
		      {
		         partiesList = getAllPartiesAsList(partyIds);
		      }
		      
			  Long prevElectionId = getPreviousElectionId(electionId);
			  boolean currentPartial = false;
			  if(prevElectionId != null)
			   {			    	
				  Election currentElection = electionDAO.get(electionId);
			      Election PrevElection = electionDAO.get(prevElectionId);
			      
			      if(currentElection.getIsPartial() != null && currentElection.getIsPartial().trim().equalsIgnoreCase("1"))
			      {
			    	  currentPartial = true;
			      }			      
				  
				  PositionManagementVO district = null;
				  for(Object[] districts: districtsList)
				   {
					  district = new PositionManagementVO();
					  district.setDistrictId((Long)districts[0]);
					  district.setDistrictName(districts[1]!=null?districts[1].toString():"");
					  district.setPresentYear(currentElection.getElectionYear());
				      district.setPrevYear(PrevElection.getElectionYear());
					  
					  List<Long> currentConstiList = nominationDAO.getAllConstituenciesInADistrict((Long)districts[0],electionId);
				      List<Long> prevConstiList = nominationDAO.getAllConstituenciesInADistrict((Long)districts[0],prevElectionId);
				   					   
				      List<PositionManagementVO> distPartiesResult = new ArrayList<PositionManagementVO>();
				      PositionManagementVO partyWise = null;
				      
				      for(SelectOptionVO party : partiesList)
				      {
				    	  partyWise = new PositionManagementVO();
				    	  partyWise.setPartyId(party.getId());
				    	  partyWise.setPartyName(party.getName());
				    	  boolean partyPrevPresent = false;
				    	  List<Object> list =  nominationDAO.getPartyPresenceInAnElection(party.getId(),prevElectionId);
				    	  if(((Long)list.get(0)).longValue() > 0l)
							{
				    		  partyPrevPresent = true;
							}
				    	if(type.equalsIgnoreCase("seats") || type.equalsIgnoreCase("seatsandvotes"))
                         { 						 
				    	  if(currentPartial)
				    	  {
				    		 
				    		  List<Object[]> partyWinConstis = constituencyLeadCandidateDAO.getPartyWinConst(party.getId(),electionId,currentConstiList);
						    	 partyWise.setPartyCount(new Long(partyWinConstis.size()));
						    	if(partyPrevPresent)
						    	{
						    		List<Long> partyWonCount =   nominationDAO.getAllWonCountPartyWise((Long)districts[0],prevElectionId,party.getId());
							    	partyWise.setPartyPrevCount(partyWonCount.get(0));
							    	if(partyWinConstis.size()-partyWonCount.get(0) >=0 )
							    	 {
							    		 partyWise.setTotalCount(partyWinConstis.size()-partyWonCount.get(0));
								    	   partyWise.setStatus("increased");
							    	 }
							    	else
							    	{
							    		partyWise.setTotalCount(0l);
								    	   partyWise.setStatus("increased");
							    	}
						    	}
					    	 
				    	  }
				    	  else
				    	  {
				    		  List<Long> partyWonCount =   nominationDAO.getAllWonCountPartyWise((Long)districts[0],electionId,party.getId());
				    		  partyWise.setPartyCount(partyWonCount.get(0));
				    		  if(partyPrevPresent)
				    		  {
				    		    List<Long> partyPrevWonCount =   nominationDAO.getAllWonCountPartyWise((Long)districts[0],prevElectionId,party.getId());
						       partyWise.setPartyPrevCount(partyPrevWonCount.get(0));
						       if((partyWonCount.get(0)-partyPrevWonCount.get(0)) >=0l)
						       {
						    	   partyWise.setTotalCount(partyWonCount.get(0)-partyPrevWonCount.get(0));
						    	   partyWise.setStatus("increased");
						       }
						       else
						       {
						    	   partyWise.setTotalCount(partyPrevWonCount.get(0)-partyWonCount.get(0));
						    	   partyWise.setStatus("decreased"); 
						       }
				    		  }
				    		  else
				    		  {
				    			  partyWise.setTotalCount(0l);
						    	  partyWise.setStatus("increased");
				    		  }
				    	  }	
                       }
                       if(type.equalsIgnoreCase("votes") || type.equalsIgnoreCase("seatsandvotes"))
                         { 						 
				    		  List<Double> partyPrevVotes = nominationDAO.getTotalVotesEarned((Long)districts[0],prevElectionId,party.getId());
						       
				    		  List<Double> partyCurrVotes =   nominationDAO.getTotalVotesEarned((Long)districts[0],electionId,party.getId());
				    		  if(partyPrevVotes.get(0) !=null)
				    		  {
				    			  partyWise.setPrevVotesEarned(new BigDecimal(partyPrevVotes.get(0)));
				    		  }
				    		  if(partyCurrVotes.get(0) !=null)
				    		  {
				    			  partyWise.setVotesEarned(new BigDecimal(partyCurrVotes.get(0)));
				    		  }
				    		  
				    		 if(partyPrevVotes.get(0) !=null && partyCurrVotes.get(0)!=null)
				    		 {
						       if(partyCurrVotes.get(0)-partyPrevVotes.get(0) >=0)
						       {
						    	   partyWise.setVotesEarnedDiff(new BigDecimal(partyCurrVotes.get(0)-partyPrevVotes.get(0)));
						    	   partyWise.setType("increased");
						       }
						       else
						       {
						    	   partyWise.setVotesEarnedDiff(new BigDecimal(partyPrevVotes.get(0)-partyCurrVotes.get(0)));
						    	   partyWise.setType("decreased"); 
						       }
				    		 }
				    		 else
				    		 {
				    			 partyWise.setVotesEarnedDiff(new BigDecimal(0));
						    	   partyWise.setType("increased");
				    		 }
                       }			   
				    	 			    		  
	                    distPartiesResult.add(partyWise);					  
				      }	
	                   district.setPositionManagementVOList(distPartiesResult);				  
				      
				      returnVal.add(district);
				   }
			   }
		    }
		    catch(Exception e)
		    {
		       log.error("Exception rised in getAllPartyCountsDistrictWise of ElectionLiveResultsAnalysisService",e);
		    }
		  return returnVal;
	  
  }
  
  public List<ElectionGoverningBodyVO> getAllCandidateDetailsForMinisterProfile(Long candidateId)
  {
	  List<ElectionGoverningBodyVO> resultList = null;
	  try{
		  List<ElectionGoverningBody> list = electionGoverningBodyDAO.getAllCandidateDetails(candidateId);
		  
		  if(list != null && list.size() > 0)
		  {
			  resultList = new ArrayList<ElectionGoverningBodyVO>(0);
			  ElectionGoverningBodyVO governingBodyVO = null;
			  for(ElectionGoverningBody governingBody : list)
			  {
				  governingBodyVO = new ElectionGoverningBodyVO();
				  governingBodyVO.setFromDate(governingBody.getFromDate());
				  governingBodyVO.setToDate(governingBody.getToDate());
				  governingBodyVO.setMinistry(governingBody.getPositionScope().getElectionGoverningBodyPosition().getGoverningBodyPosition());
				  governingBodyVO.setStatus(governingBody.getStatus().toString());
				  governingBodyVO.setMinisterType(governingBody.getPositionScope().getMinisterType().getMinisterType());
				  
				  if(governingBody.getElection().getElectionScope().getState() != null)
				  governingBodyVO.setStateName(governingBody.getElection().getElectionScope().getState().getStateName());
				
				  resultList.add(governingBodyVO);
			  }
		  }
		  return resultList;
	  }catch (Exception e) {
		  log.error("Exception occured in getAllCandidateDetailsForMinisterProfile(), Exception is - "+e);
		  return resultList;
	  }
  }
}
