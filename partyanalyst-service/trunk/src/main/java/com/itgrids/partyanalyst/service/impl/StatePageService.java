/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 17, 2009
 */
package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.itgrids.partyanalyst.dao.ICensusDAO;
import com.itgrids.partyanalyst.dao.IElectionObjectsDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dto.CensusVO;
import com.itgrids.partyanalyst.dto.PartyWiseResultVO;
import com.itgrids.partyanalyst.dto.StateElectionResultsVO;
import com.itgrids.partyanalyst.dto.StateElectionsVO;
import com.itgrids.partyanalyst.dto.StatePageVO;
import com.itgrids.partyanalyst.model.Census;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.service.IStatePageService;
import com.itgrids.partyanalyst.utils.ElectionResultsForPartiesBySeats;
import com.itgrids.partyanalyst.utils.IConstants;

public class StatePageService implements IStatePageService {

	
	private IStateDAO stateDAO;
	private IElectionObjectsDAO electionObjectsDAO;
	private ICensusDAO censusDAO;
	private INominationDAO nominationDAO;		
	
	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}
	
	public void setElectionObjectsDAO(IElectionObjectsDAO electionObjectsDAO) {
		this.electionObjectsDAO = electionObjectsDAO;
	}

	public void setCensusDAO(ICensusDAO censusDAO) {
		this.censusDAO = censusDAO;
	}
	
	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}

	//method that returns election years and election type for a particular state
	public List<StateElectionsVO> getStateElections(Long stateId) {
		
		List<Election> elections = new ArrayList<Election>();
    	List<Long> electionTypeArrayList = new ArrayList<Long>(0);
    	List<StateElectionsVO> stateElectionsList = new ArrayList<StateElectionsVO>(0);
    	List<StateElectionsVO> stateElectionsSortedList = new ArrayList<StateElectionsVO>(0);
  	  	ElectionType electionType = null;    	    	
    	elections = nominationDAO.getElectionsInState(stateId);
    	    	    		
       	   if(elections.size()<1 || elections == null)
    		   return null;
    	   
       	   else{
    	        for(Election election:elections){
    	      
    	    	  StateElectionsVO stateElections = new StateElectionsVO();
    	    	  electionType = election.getElectionScope().getElectionType();
    	    	  
    	    	  stateElections.setElectionId(election.getElectionId());
    	    	  stateElections.setElectionTypeId(electionType.getElectionTypeId());
    	    	  stateElections.setElectionType(electionType.getElectionType());
    	    	  stateElections.setElectionSubtype(election.getElecSubtype());
    	    	  stateElections.setYear(election.getElectionYear());
    	    	  
    	    	  StateElectionResultsVO stateElecResults = getStateElectionResults(election.getElectionId());
    	    	  if(stateElecResults != null)
    	    	  stateElections.setPartyResultsVO(stateElecResults.getPartyResultsVO());     	    	
    	    	  stateElectionsList.add(stateElections);
    	        }
    	  
		    }
    	   
    	   electionTypeArrayList = getElectionTypeArrayList(stateElectionsList);
    	   
    	   stateElectionsSortedList = getSortedElectionDetails(stateElectionsList,electionTypeArrayList);
    
    return stateElectionsSortedList;
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
		   state.setAdmin_control(stateDetails.getAdminControl());
		}
		return state;
	}

	
	public List<Long> getElectionTypeArrayList(List<StateElectionsVO> stateElections){
		
		     List<Long> electionTypeList = new ArrayList<Long>(0);
		     List<Long> electionTypeArrayList = new ArrayList<Long>(0);
		     int count=0;
		     
		     for(StateElectionsVO elections:stateElections){
		    	 if(electionTypeList.contains(elections.getElectionTypeId()))
		    		 count++;
		    	 if(count == 0)
		    		 electionTypeList.add(elections.getElectionTypeId());
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
	
	
	//sort code
	public List<StateElectionsVO> getSortedElectionDetails(List<StateElectionsVO> stateElectionsList,List<Long> electionTypeArrayList){
	
		List<StateElectionsVO> stateElectionsVO = new ArrayList<StateElectionsVO>(0);
		
		 if(stateElectionsList != null){
			
			 for(int i=0;i<electionTypeArrayList.size();i++){
				 
				 for(StateElectionsVO elections:stateElectionsList){
					 if(elections.getElectionTypeId().equals(electionTypeArrayList.get(i))){
						 Collections.sort(elections.getPartyResultsVO(), new ElectionResultsForPartiesBySeats());
						 stateElectionsVO.add(elections);
					 }
				 }
				 
			 }
		 return	stateElectionsVO;		 
		 }
		  
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
				 censusVO.setYear(censusData.getYear().intValue());
				 
				 censusDataVO.add(censusVO);
			 }
		return censusDataVO;
		}
		 
	return null;
	}
	
	 @SuppressWarnings("unchecked")
		public StateElectionResultsVO getStateElectionResults(Long electionId){
			
			List<PartyWiseResultVO> partyResultsVO = null;
			StateElectionResultsVO stateElectionResults = new StateElectionResultsVO();
			Long partyId = new Long(0);
			String partyName = "";
			String partyFlag = "";
			Long seatsWon=new Long(0);
			
			List elecResults = nominationDAO.findElectionDataByElectionId(electionId);
			if(elecResults != null){
			partyResultsVO = new ArrayList<PartyWiseResultVO>(0);
			for(int i=0; i<elecResults.size(); i++){
				Object[] params = (Object[])elecResults.get(i);
				partyId  = (Long)params[0];
				partyName = (String)params[1];			 
				seatsWon = (Long)params[2];
				partyFlag = (String)params[3];
				
				PartyWiseResultVO partyResults = new PartyWiseResultVO();
				partyResults.setPartyId(partyId);
				partyResults.setPartyName(partyName);
				partyResults.setTotalSeatsWon(seatsWon);
				partyResults.setPartyFlag(partyFlag);
				
				partyResultsVO.add(partyResults);
			}
			Collections.sort(partyResultsVO,new ElectionResultsForPartiesBySeats());
			}
			stateElectionResults.setPartyResultsVO(partyResultsVO);
					  
		return stateElectionResults;
		}
	
}
