/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 17, 2009
 */
package com.itgrids.partyanalyst.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.itgrids.partyanalyst.dao.ICandidateResultObjectsDAO;
import com.itgrids.partyanalyst.dao.ICensusDAO;
import com.itgrids.partyanalyst.dao.IElectionObjectsDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dto.CensusVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.StateElectionResultsVO;
import com.itgrids.partyanalyst.dto.StatePageVO;
import com.itgrids.partyanalyst.model.CandidateResult;
import com.itgrids.partyanalyst.model.Census;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.State;

public class StatePageService implements IStatePageService {

	
	private IStateDAO stateDAO;
	private IElectionObjectsDAO electionObjectsDAO;
	private ICandidateResultObjectsDAO candidateResultObjectsDAO;
	private IPartyDAO partyDAO;
	private ICensusDAO censusDAO;
	
	
	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	
	public void setElectionObjectsDAO(IElectionObjectsDAO electionObjectsDAO) {
		this.electionObjectsDAO = electionObjectsDAO;
	}


	public void setCandidateResultObjectsDAO(ICandidateResultObjectsDAO candidateResultObjectsDAO) {
		this.candidateResultObjectsDAO = candidateResultObjectsDAO;
	}


	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}

	public void setCensusDAO(ICensusDAO censusDAO) {
		this.censusDAO = censusDAO;
	}

	//method that returns election years and election type for a particular state
	public List<StateElectionResultsVO> getStateElectionResults(Long stateId) {
		
		List<StateElectionResultsVO> stateElectionResultsVO = new ArrayList<StateElectionResultsVO>();
    	List<Election> elections = new ArrayList<Election>();
    	List<Long> electionTypeArrayList = new ArrayList<Long>(0);
    	List<StateElectionResultsVO> stateResultsVO = new ArrayList<StateElectionResultsVO>(0);
    	List<StateElectionResultsVO> stateFinalResultsVO = new ArrayList<StateElectionResultsVO>(0);
    	    	
    	
    	elections = electionObjectsDAO.findElections(stateId);
    	    	    		
       	   if(elections.size()<1 || elections == null)
    		   return null;
    	   
       	   else{
    	        for(Election election:elections){
    	      
    	    	  List<PartyResultsVO>  partyResultsVO = new ArrayList<PartyResultsVO>();
    	    	  partyResultsVO = getElectionResults(election.getElectionId());
    	    	
    	    	  StateElectionResultsVO stateElectionResult = new StateElectionResultsVO();
    	    	  ElectionType electionType = null;
    	    	  electionType = election.getElectionScope().getElectionType();
    	    	  
    	    	  stateElectionResult.setElectionId(election.getElectionId());
    	    	  stateElectionResult.setElectionTypeId(electionType.getElectionTypeId());
    	    	  stateElectionResult.setElectionType(electionType.getElectionType());
    	    	  stateElectionResult.setElectionYear(election.getElectionYear());
    	    	  
    	    	  stateElectionResult.setPartyResultsVO(partyResultsVO);
    	    	
    	    	stateElectionResultsVO.add(stateElectionResult);
    	        }
    	  
		    }
    	   
    	   electionTypeArrayList = getElectionTypeArrayList(stateElectionResultsVO);
    	   if(electionTypeArrayList!=null){
    		   for(int i=0;i<electionTypeArrayList.size();i++){
    			 stateResultsVO = getStateResultsVO(stateElectionResultsVO,electionTypeArrayList.get(i));
    			   for(StateElectionResultsVO finalResults:stateResultsVO)
    				 stateFinalResultsVO.add(finalResults);
    		   }
    	   }
    	   return stateFinalResultsVO;
	}

    
    //method that returns total info for given state
	public StatePageVO getStateDetails(Long stateId) {
		
		StatePageVO state = new StatePageVO();
		List<State> states = new ArrayList<State>();
		
		states = stateDAO.findByStateId(stateId);
		
		if(states!=null){
			 state = convertToTransferObject(states);
		     return state;
		}
		else{
			state.setStateName("NoState");
			return state;
		}
		
	}
	
	public StatePageVO convertToTransferObject(List<State> states) {
		
		StatePageVO state = new StatePageVO();
		for(State stateDetails:states){
		   state.setStateId(stateDetails.getStateId());
		   state.setStateName(stateDetails.getStateName());
		   state.setAdminCapital(stateDetails.getAdminCapital());
		   state.setLegisCapital(stateDetails.getLegisCapital());
		   state.setJudiciaryCapital(stateDetails.getJudiciaryCapital());
		   state.setYearEstablished(stateDetails.getYearEstablished());
		   state.setStateLanguage(stateDetails.getStateLanguage());
		   state.setStateSong(stateDetails.getStateSong());
		   state.setStateSymbol(stateDetails.getStateSymbol());
		   state.setStateAnimal(stateDetails.getStateAnimal());
		   state.setStateBird(stateDetails.getStateBird());
		   state.setStateTree(stateDetails.getStateTree());
		   state.setStateSport(stateDetails.getStateSport());
		   state.setStateDance(stateDetails.getStateDance());
		   state.setStateFlower(stateDetails.getStateFlower());
		   state.setStartDate(stateDetails.getStartDate());
		   state.setDeformDate(stateDetails.getDeformDate());
		}
		return state;
	}

	
	public List<PartyResultsVO> getElectionResults(Long electionId){
		
		
        List<CandidateResult> candidateResults = candidateResultObjectsDAO.findCandidateResultObjects(electionId);
		
		List<PartyResultsVO> partyResultsVO = new ArrayList<PartyResultsVO>(0);
		Long partyId;
		String partyName;
		int count=0;
		
		if(candidateResults != null){
		   for(CandidateResult result:candidateResults){
			   
				if(result.getRank().equals(new Long(1))){
					Party party= result.getNomination().getParty();
					partyId = party.getPartyId();
					partyName=party.getLongName();
			    
				     
					if(partyResultsVO.size()>0){
						for(PartyResultsVO resultsVO:partyResultsVO){
							if(resultsVO.getPartyId().equals(partyId)){
								resultsVO.setPartyId(partyId);
								resultsVO.setPartyName(partyName);
								resultsVO.setTotalSeatsWon(resultsVO.getTotalSeatsWon() + 1);
								count++;
							}
						}
					}
					if(partyResultsVO.size() == 0 || count == 0 ){
					  PartyResultsVO partyResult = new PartyResultsVO();
					  partyResult.setPartyId(partyId);
					  partyResult.setPartyName(partyName);
					  partyResult.setTotalSeatsWon(new Long(1));
					  
					  partyResultsVO.add(partyResult);
					  
					}
				   			
				count = 0;
			    }
		    }
				
		 }
		 else
			return null;
		 
		
	return partyResultsVO;
	}
	
	
	public List<Long> getElectionTypeArrayList(List<StateElectionResultsVO> stateElectionResults){
		
		     List<Long> electionTypeList = new ArrayList<Long>(0);
		     List<Long> electionTypeArrayList = new ArrayList<Long>(0);
		     int count=0;
		     
		     for(StateElectionResultsVO electionResult:stateElectionResults){
		    	 if(electionTypeList.contains(electionResult.getElectionTypeId()))
		    		 count++;
		    	 if(count == 0)
		    		 electionTypeList.add(electionResult.getElectionTypeId());
		    	 count=0;
		     }
		   
		     if(electionTypeList.contains(new Long(2))){
		    	 electionTypeArrayList.add(new Long(2));
		    	 electionTypeList.remove(new Long(2));
		     }
		     if(electionTypeList.contains(new Long(1))){
		    	 electionTypeArrayList.add(new Long(1));
		    	 electionTypeList.remove(new Long(1));
		     }
		     for(int i=0;i<electionTypeList.size();i++)
		    	 electionTypeArrayList.add(electionTypeList.get(i));
		    	 
     return electionTypeArrayList;
	}
	
	
	public List<StateElectionResultsVO> getStateResultsVO(List<StateElectionResultsVO> stateElectionResults,Long electionTypeId){
		
		List<StateElectionResultsVO> stateResultsVO = new ArrayList<StateElectionResultsVO>(0);
		
		for(StateElectionResultsVO electionResult:stateElectionResults){
			if(electionResult.getElectionTypeId().equals(electionTypeId)){
				stateResultsVO.add(electionResult);
			}
		}
		stateResultsVO = getSortedResults(stateResultsVO);
		return stateResultsVO;
	}
	
	
	//sort code
	public List<StateElectionResultsVO> getSortedResults(List<StateElectionResultsVO> stateElectionResults){
	
		List<StateElectionResultsVO> stateResultsVO = new ArrayList<StateElectionResultsVO>(0);
		
		if(stateElectionResults != null){
		  do{
			int count=0,max=0;
			 for(int i=0;i<stateElectionResults.size();i++){
				 if(Integer.parseInt(stateElectionResults.get(i).getElectionYear()) > max){
					 max=Integer.parseInt(stateElectionResults.get(i).getElectionYear());
					 count = i;
				 }
			 }
			 stateResultsVO.add(stateElectionResults.get(count));
			 stateElectionResults.remove(count);
	       }while(stateElectionResults.size()>0);
		  
		 return stateResultsVO;
		}
		else
		 return null;
	}
	
	
	//census data
	public List<CensusVO> getCensusDetails(Long stateId,int year){
		
		Set<Census> census = new HashSet<Census>(0);
		List<CensusVO> censusDataVO = new ArrayList<CensusVO>(0);
				
		census = censusDAO.findByStateIdAndYear(stateId, year);
		if(census!=null){
			for(Census censusData:census){
				 CensusVO censusVO = new CensusVO();
				 censusVO.setCensusId(censusData.getCensusId());
				 censusVO.setStateId(censusData.getStateId());
				 censusVO.setTotalPopulation(censusData.getTotalPopulation());
				 censusVO.setMalePopulation(censusData.getTotalMalePopulation());
				 censusVO.setFemalePopulation(censusData.getTotalFemalePopulation());
				 censusVO.setTru(censusData.getTru());
				 censusVO.setYear(censusData.getYear());
				 
				 censusDataVO.add(censusVO);
			 }
		}
		 
		return censusDataVO;
	}
	
}
