package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IConstituencyLeadCandidateDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dto.PartyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.ConstituencyLeadCandidate;
import com.itgrids.partyanalyst.service.IElectionResultsUpdationService;
import com.itgrids.partyanalyst.service.IStaticDataService;

public class ElectionResultsUpdationService implements IElectionResultsUpdationService {

	  private IConstituencyLeadCandidateDAO constituencyLeadCandidateDAO;
	  private INominationDAO nominationDAO;
	  private ICandidateDAO candidateDAO;
	  private IConstituencyElectionDAO constituencyElectionDAO;
	  private static final Logger log = Logger.getLogger(ElectionResultsUpdationService.class);
	  private IElectionDAO electionDAO;
	  
	public IConstituencyLeadCandidateDAO getConstituencyLeadCandidateDAO() {
		return constituencyLeadCandidateDAO;
	}

	public void setConstituencyLeadCandidateDAO(
			IConstituencyLeadCandidateDAO constituencyLeadCandidateDAO) {
		this.constituencyLeadCandidateDAO = constituencyLeadCandidateDAO;
	}

	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}
	
	public ICandidateDAO getCandidateDAO() {
		return candidateDAO;
	}

	public void setCandidateDAO(ICandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}

	public IConstituencyElectionDAO getConstituencyElectionDAO() {
		return constituencyElectionDAO;
	}

	public void setConstituencyElectionDAO(
			IConstituencyElectionDAO constituencyElectionDAO) {
		this.constituencyElectionDAO = constituencyElectionDAO;
	}	
	
	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}
    
	
	public List<PartyElectionResultsVO> getCandidateResults(String electionType,Long constituencyId,Long stateId,String electionYear){
		if(log.isDebugEnabled())
			log.debug("Enter into getCandidateResults method of ElectionResultsUpdationService");
		List<PartyElectionResultsVO> returnVal = new ArrayList<PartyElectionResultsVO>();
		PartyElectionResultsVO partyElectionResultsVO = null;
	 try
	 {
		List<Object[]>  results = nominationDAO.getCandidateDetails(electionType,constituencyId,stateId,electionYear);
		 for(Object[] candidateDetais : results)
		 {
			 partyElectionResultsVO = new PartyElectionResultsVO();
			 partyElectionResultsVO.setCandidateId((Long)candidateDetais[0]);
			 partyElectionResultsVO.setCandidateName(candidateDetais[1] != null?candidateDetais[1].toString():"");
			 partyElectionResultsVO.setPartyId((Long)candidateDetais[2]);
			 partyElectionResultsVO.setPartyName(candidateDetais[3] != null?candidateDetais[3].toString():"");
			 partyElectionResultsVO.setConstituencyId((Long)candidateDetais[4]);//constituency election Id(constiElecId)
			 
			 List<Object>  candidtStatusList = constituencyLeadCandidateDAO.getCandidateResultStatus((Long)candidateDetais[0], (Long)candidateDetais[4]);
			 
			 if(candidtStatusList.size() >0)
			 {
				 
				 
				 partyElectionResultsVO.setElectionType(candidtStatusList.get(0) != null?candidtStatusList.get(0).toString():"");
			 }
			 else
				 partyElectionResultsVO.setElectionType("");
			 returnVal.add(partyElectionResultsVO);
		 }
	 }
	 catch(Exception e)
	 {
		 log.error("Exception Rised in getCandidateResults method of ElectionResultsUpdationService",e);
		 
	 }
	  return returnVal;
	}
	
	public List<PartyElectionResultsVO> updateCandidateResults(Long candidateId,Long constiElecId,String status)
	{
		if(log.isDebugEnabled())
			log.debug("Enter into updateCandidateResults method of ElectionResultsUpdationService");
		List<PartyElectionResultsVO> returnVal = new ArrayList<PartyElectionResultsVO>();
		PartyElectionResultsVO partyElectionResultsVO = null;
	  try
	  {
		List<Object>  results = constituencyLeadCandidateDAO.checkCandidateResultExist(constiElecId);
		ConstituencyLeadCandidate constituencyLeadCandidate = null;
		if(results.size() > 0)
		{
			 constituencyLeadCandidate = constituencyLeadCandidateDAO.get((Long)results.get(0));
			 constituencyLeadCandidate.setCandidate(candidateDAO.get(candidateId));
			 constituencyLeadCandidate.setConstituencyElection(constituencyElectionDAO.get(constiElecId));
			 constituencyLeadCandidate.setStatus(status);
			 constituencyLeadCandidateDAO.save(constituencyLeadCandidate);
		}
		else
		{
			constituencyLeadCandidate = new ConstituencyLeadCandidate();
			constituencyLeadCandidate.setCandidate(candidateDAO.get(candidateId));
			constituencyLeadCandidate.setConstituencyElection(constituencyElectionDAO.get(constiElecId));
			constituencyLeadCandidate.setStatus(status);
			constituencyLeadCandidateDAO.save(constituencyLeadCandidate);
		}
		partyElectionResultsVO = new PartyElectionResultsVO();
		partyElectionResultsVO.setResultCode(ResultCodeMapper.SUCCESS);
	  }
	  catch(Exception e)
	  {
		  log.error("Exception Rised in updateCandidateResults method of ElectionResultsUpdationService",e);
		    partyElectionResultsVO = new PartyElectionResultsVO();
			partyElectionResultsVO.setResultCode(ResultCodeMapper.FAILURE);
			partyElectionResultsVO.setExceptionEncountered(e);
	  }
	       returnVal.add(partyElectionResultsVO);
	  return returnVal;
	}
	public List<SelectOptionVO> getElectionYears(Long stateId, String electionType)
	{
		if(log.isDebugEnabled())
			log.debug("Enter into getElectionYears method of ElectionResultsUpdationService");
		List<SelectOptionVO> yearsList = new ArrayList<SelectOptionVO>(0);
		try{
			
			List<Object[]> electionYears = electionDAO.getElectionYears(stateId,electionType);
			if(electionYears != null && electionYears.size() > 0)
			{
				
				SelectOptionVO selectOptionVO = null;
				for(Object[] params : electionYears)
				{
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)params[0]);
					selectOptionVO.setName(params[1].toString());
					yearsList.add(selectOptionVO);
				}
			}
			
			
		}catch(Exception e)
		{
			log.error("Exception Rised in getElectionYears method of ElectionResultsUpdationService",e);
		}
		return yearsList;
	}
}
