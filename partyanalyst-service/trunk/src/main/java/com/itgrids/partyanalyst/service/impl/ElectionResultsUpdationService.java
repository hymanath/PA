package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IConstituencyLeadCandidateDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionGoverningBodyDAO;
import com.itgrids.partyanalyst.dao.IElectionGoverningBodyPositionDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPositionScopeDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.PartyElectionResultsVO;
import com.itgrids.partyanalyst.dto.PositionManagementVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.ConstituencyLeadCandidate;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionGoverningBody;
import com.itgrids.partyanalyst.model.ElectionGoverningBodyPosition;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.PositionScope;
import com.itgrids.partyanalyst.service.IElectionResultsUpdationService;
import com.itgrids.partyanalyst.utils.IConstants;

public class ElectionResultsUpdationService implements IElectionResultsUpdationService {

	  private IConstituencyLeadCandidateDAO constituencyLeadCandidateDAO;
	  private INominationDAO nominationDAO;
	  private ICandidateDAO candidateDAO;
	  private IConstituencyElectionDAO constituencyElectionDAO;
	  private static final Logger log = Logger.getLogger(ElectionResultsUpdationService.class);
	  private IElectionDAO electionDAO;
	  private IElectionGoverningBodyPositionDAO electionGoverningBodyPositionDAO;
	  private IElectionScopeDAO electionScopeDAO;
	  private IPositionScopeDAO positionScopeDAO;
	  private IElectionTypeDAO electionTypeDAO;
	  private IPartyDAO partyDAO;
	  private IDistrictDAO districtDAO;
	  private ITehsilDAO tehsilDAO;
	  private IElectionGoverningBodyDAO electionGoverningBodyDAO;
	  private ILocalElectionBodyDAO localElectionBodyDAO;
	  
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
	
	public IElectionGoverningBodyPositionDAO getElectionGoverningBodyPositionDAO() {
		return electionGoverningBodyPositionDAO;
	}

	public void setElectionGoverningBodyPositionDAO(
			IElectionGoverningBodyPositionDAO electionGoverningBodyPositionDAO) {
		this.electionGoverningBodyPositionDAO = electionGoverningBodyPositionDAO;
	}

	public IElectionScopeDAO getElectionScopeDAO() {
		return electionScopeDAO;
	}

	public void setElectionScopeDAO(IElectionScopeDAO electionScopeDAO) {
		this.electionScopeDAO = electionScopeDAO;
	}

	public IPositionScopeDAO getPositionScopeDAO() {
		return positionScopeDAO;
	}

	public void setPositionScopeDAO(IPositionScopeDAO positionScopeDAO) {
		this.positionScopeDAO = positionScopeDAO;
	}

	public IElectionTypeDAO getElectionTypeDAO() {
		return electionTypeDAO;
	}

	public void setElectionTypeDAO(IElectionTypeDAO electionTypeDAO) {
		this.electionTypeDAO = electionTypeDAO;
	}

	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}
	
	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}
	
	public IElectionGoverningBodyDAO getElectionGoverningBodyDAO() {
		return electionGoverningBodyDAO;
	}

	public void setElectionGoverningBodyDAO(
			IElectionGoverningBodyDAO electionGoverningBodyDAO) {
		this.electionGoverningBodyDAO = electionGoverningBodyDAO;
	}

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
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
	
   public ResultStatus addNewPosition(String position)
   {
	   ResultStatus resultStatus = new ResultStatus();
	   if(log.isDebugEnabled())
			log.debug("Enter into addNewPosition method of ElectionResultsUpdationService");
	   try
	   {
		   List<ElectionGoverningBodyPosition> results = electionGoverningBodyPositionDAO.getPositionsByValue(position);
		   if(!(results.size()>0))
		   {   
		     ElectionGoverningBodyPosition electionGoverningBodyPosition = new ElectionGoverningBodyPosition();
		     electionGoverningBodyPosition.setGoverningBodyPosition(position);
		     electionGoverningBodyPositionDAO.save(electionGoverningBodyPosition);
		     resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		   }
		   else
		   {
			   resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			   resultStatus.setExceptionMsg("Already A Position With This Name "+position+" Exists ");
		   }
			   
	   }
      catch(Exception e)
	  {
		log.error("Exception Rised in addNewPosition method of ElectionResultsUpdationService",e);
		resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		resultStatus.setExceptionEncountered(e);
	  }
       return resultStatus;
   }
   
   public List<SelectOptionVO> getAllPosition()
   {
	   List<SelectOptionVO> returnValue = new ArrayList<SelectOptionVO>();
	   SelectOptionVO selectOptionVO = null;
	   if(log.isDebugEnabled())
			log.debug("Enter into getAllPosition method of ElectionResultsUpdationService");
	   try
	   {
		   
		   List<Object[]> positionsList = electionGoverningBodyPositionDAO.getAllPositions();
		   for(Object[] position : positionsList)
		   {
			   selectOptionVO = new SelectOptionVO();
			   selectOptionVO.setId((Long)position[0]);
			   selectOptionVO.setName(position[1]!=null?position[1].toString():"");
			   returnValue.add(selectOptionVO);
		   }
	   }
      catch(Exception e)
	  {
		log.error("Exception Rised in getAllPosition method of ElectionResultsUpdationService",e);
	  }
      return returnValue;
   }
   
   public ResultStatus assignScopeToPosition(Long electionGoverningBodyPositionId,Long electionTypeId,Long stateId,String type)
   {
	   
	   if(log.isDebugEnabled())
			log.debug("Enter into assignScopeToPosition method of ElectionResultsUpdationService");
	   try
	   {
		   List<ElectionScope> elecScopList = electionScopeDAO.findByTypeIdStateId(electionTypeId, stateId);
		   if(elecScopList.size() > 0)
		   {
			   ElectionScope electionScope = elecScopList.get(0);
			   List<PositionScope> positionScopeList  = positionScopeDAO.getPositionScopes(electionScope.getElectionScopeId(), electionGoverningBodyPositionId, type);
			   
			   if(!(positionScopeList.size() >0))
			   {
			        ElectionGoverningBodyPosition electionGoverningBodyPosition = electionGoverningBodyPositionDAO.get(electionGoverningBodyPositionId);
			        PositionScope positionScope = new PositionScope();
				  
			        positionScope.setElectionScope(electionScope);
			        positionScope.setElectionGoverningBodyPosition(electionGoverningBodyPosition);
			        positionScope.setStatus(type);
			        positionScopeDAO.save(positionScope);
			   
			        ResultStatus resultStatus = new ResultStatus();
			        resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			        return resultStatus;
			   }
			   else
			   {
				    ResultStatus resultStatus = new ResultStatus();
			        resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			        resultStatus.setExceptionMsg("Already Same Scope Exists For This Position");
			        return resultStatus;
			   }
		   }
		   else
		   {
			   ResultStatus resultStatus = new ResultStatus();
			   resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			   resultStatus.setExceptionMsg("No Election Data Exists With This Scope");
			   return resultStatus;
		   }
		   
	   }
      catch(Exception e)
	  {
		log.error("Exception Rised in assignScopeToPosition method of ElectionResultsUpdationService",e);
		 ResultStatus resultStatus = new ResultStatus();
		   resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		   resultStatus.setExceptionEncountered(e);
		   return resultStatus;
	  }
   }
   public List<SelectOptionVO> getAllElectionTypes()
   {
	   List<SelectOptionVO> returnValue = new ArrayList<SelectOptionVO>();
	   SelectOptionVO selectOptionVO = null;
	   if(log.isDebugEnabled())
			log.debug("Enter into getAllElectionTypes method of ElectionResultsUpdationService");
	   try
	   {
		   
		   List<ElectionType> electionTypeList = electionTypeDAO.getAll();
		   for(ElectionType electionType : electionTypeList)
		   {
			   selectOptionVO = new SelectOptionVO();
			   selectOptionVO.setId(electionType.getElectionTypeId());
			   selectOptionVO.setName(electionType.getElectionType());
			   returnValue.add(selectOptionVO);
		   }
	   }
      catch(Exception e)
	  {
		log.error("Exception Rised in getAllElectionTypes method of ElectionResultsUpdationService",e);
	  }
      return returnValue;
   }
   
   public ResultStatus assignCandidateToAPosition(PositionManagementVO positionManagementVO)
   {
	   if(log.isDebugEnabled())
			log.debug("Enter into assignCandidateToAPosition method of ElectionResultsUpdationService");
	   ResultStatus resultStatus = null;
	 try
	 {
	   List<ElectionScope> elecScopList = electionScopeDAO.findByTypeIdStateId(positionManagementVO.getElectionTypeId(), positionManagementVO.getStateId());
	   ElectionScope electionScope = null;
	   
	   if(elecScopList.size() > 0)
	   {
		   electionScope = elecScopList.get(0);
	   }
	   else
	   {
		   resultStatus = new ResultStatus();
		   resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		   resultStatus.setExceptionMsg("No Election Scope Exists With Given Information");
		   return resultStatus;
	   }
	   Election election = electionDAO.findByElectionScopeIdElectionYear(electionScope.getElectionScopeId(),positionManagementVO.getYear(),IConstants.ELECTION_SUBTYPE_MAIN);
	   ElectionGoverningBody electionGoverningBody = new ElectionGoverningBody();
	   electionGoverningBody.setCandidate(candidateDAO.get(positionManagementVO.getCandidateId()));
	   electionGoverningBody.setElection(election);
	   if(positionManagementVO.getElectionTypeId() == 5l || positionManagementVO.getElectionTypeId() == 6l || positionManagementVO.getElectionTypeId() == 7l)
	   {
		   electionGoverningBody.setLocalElectionBody(localElectionBodyDAO.get(positionManagementVO.getLocalElecBodyId()));
	   }
	   electionGoverningBody.setParty(partyDAO.get(positionManagementVO.getPartyId()));
	   
	   if(positionManagementVO.getElectionTypeId() == 3l || positionManagementVO.getElectionTypeId() == 4l)
	   electionGoverningBody.setDistrict(districtDAO.get(positionManagementVO.getDistrictId()));
	   
	   if(positionManagementVO.getElectionTypeId() == 3l )
	   electionGoverningBody.setTehsil(tehsilDAO.get(positionManagementVO.getTehilId()));
	   
	   List<PositionScope> positionScope = positionScopeDAO.getPositionScopes(electionScope.getElectionScopeId(),positionManagementVO.getElectionGovBodyPosId(),positionManagementVO.getType());
	    if(positionScope.size() > 0)
	      electionGoverningBody.setPositionScope(positionScope.get(0));
	    else
	    {
	    	   resultStatus = new ResultStatus();
			   resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			   resultStatus.setExceptionMsg("No Position Exists With Given Scope Information");
			   return resultStatus;
	    }
	   electionGoverningBody.setFromDate(positionManagementVO.getFromDate());
	   electionGoverningBody.setToDate(positionManagementVO.getToDate());
	   electionGoverningBody.setStatus(positionManagementVO.getStatus());
	   
	   electionGoverningBodyDAO.save(electionGoverningBody);
	   
	   resultStatus = new ResultStatus();
	   resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
	   
	 }
	 catch(Exception e)
	 {
		 log.error("Exception Rised in assignCandidateToAPosition method of ElectionResultsUpdationService",e); 
		   resultStatus = new ResultStatus();
		   resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		   resultStatus.setExceptionEncountered(e);
	 }
	 return resultStatus;
   }
   public List<SelectOptionVO> getAllElectionYears(Long stateId, String electionType)
	{
		if(log.isDebugEnabled())
			log.debug("Enter into getAllElectionYears method of ElectionResultsUpdationService");
		List<SelectOptionVO> yearsList = new ArrayList<SelectOptionVO>(0);
		try{
			
			List<Object[]> electionYears = electionDAO.getElectionYearsBasedOnElectionTypeAndState(stateId,electionType);
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
			log.error("Exception Rised in getAllElectionYears method of ElectionResultsUpdationService",e);
		}
		return yearsList;
	}
   public List<SelectOptionVO> getCandidates(PositionManagementVO positionManagementVO)
   {
	   if(log.isDebugEnabled())
			log.debug("Enter into getCandidates method of ElectionResultsUpdationService");
		List<SelectOptionVO> candidatesList = new ArrayList<SelectOptionVO>(0);
		try{
			
			List<Object[]> candidates = nominationDAO.getCandidatesDetailsByGivenDetails(positionManagementVO);
			if(candidates != null && candidates.size() > 0)
			{
				
				SelectOptionVO selectOptionVO = null;
				for(Object[] params : candidates)
				{
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)params[0]);
					selectOptionVO.setName(params[1].toString());
					candidatesList.add(selectOptionVO);
				}
			}
			
			
		}catch(Exception e)
		{
			log.error("Exception Rised in getCandidates method of ElectionResultsUpdationService",e);
		}
		return candidatesList;
   }
}
