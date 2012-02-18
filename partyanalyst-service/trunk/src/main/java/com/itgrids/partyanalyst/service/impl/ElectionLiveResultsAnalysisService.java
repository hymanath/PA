package com.itgrids.partyanalyst.service.impl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IConstituencyLeadCandidateDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dto.ElectionLiveResultVO;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.service.IElectionLiveResultsAnalysisService;

public class ElectionLiveResultsAnalysisService implements IElectionLiveResultsAnalysisService{

	private static final Logger log = Logger.getLogger(ElectionLiveResultsAnalysisService.class);
	
	private IConstituencyElectionDAO constituencyElectionDAO;
	private IConstituencyLeadCandidateDAO constituencyLeadCandidateDAO;
	private IElectionDAO electionDAO;
	
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

	public ElectionLiveResultVO getCountOfConstituenciesForAElection(Long electionId) {
		
		ElectionLiveResultVO electionLiveResultVO = new ElectionLiveResultVO();
		try {
			if(log.isDebugEnabled())
				log.debug("Entered Into getCountOfConstituenciesForAElection()");
			
		List totalSeats = constituencyElectionDAO.findConstituenciesCountInAnElection(electionId);
			electionLiveResultVO.setTotalSeats((Long)totalSeats.get(0));
		
		List countOfLeadConstituences = constituencyLeadCandidateDAO.getLeadingConstituenciesCount(electionId);
			electionLiveResultVO.setCountOfLeadConstituences((Long)countOfLeadConstituences.get(0));
		
		List oldConstituenciesCount = constituencyLeadCandidateDAO.getCountOfOldConstituenciesInAElection(electionId);
			electionLiveResultVO.setOldConstituenciesCount((Long)oldConstituenciesCount.get(0));
		
		List newCostituenciesCount = constituencyLeadCandidateDAO.getCountOfDelimitedConstituenciesInAElection(electionId);
			electionLiveResultVO.setNewConstituenciesCount((Long)newCostituenciesCount.get(0));
		
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
					
					electionLiveResultVOList.get(0).setPartialResult(true);
					 
					 
					
				 }
				 else {
					 
					 List<Object[]> winningConstituencies = constituencyElectionDAO.getPartyWinningConstituenciesCount(electionId);
					 List electionIds = electionDAO.getElectionIdsBasedOnStateId(election.getElectionScope().getState().getStateId());
					 
					 Map<String,ElectionLiveResultVO> partyMap = new HashMap<String,ElectionLiveResultVO>(0);
						ElectionLiveResultVO countVO = null;
						for(Object[] params : winningConstituencies)
						{
							if(electionIds.size()==1){
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
							else if(electionIds.size()>1) {
								
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

}
