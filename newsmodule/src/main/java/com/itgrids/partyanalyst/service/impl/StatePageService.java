/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 17, 2009
 */
package com.itgrids.partyanalyst.service.impl;

import com.itgrids.partyanalyst.service.IStatePageService;

public class StatePageService implements IStatePageService {/*
	
	private IStateDAO stateDAO;
	private IElectionDAO electionDAO;
	private ICensusDAO censusDAO;
	private INominationDAO nominationDAO;
	private IElectionGoverningBodyDAO electionGoverningBodyDAO;
	
	public IElectionGoverningBodyDAO getElectionGoverningBodyDAO() {
		return electionGoverningBodyDAO;
	}

	public void setElectionGoverningBodyDAO(
			IElectionGoverningBodyDAO electionGoverningBodyDAO) {
		this.electionGoverningBodyDAO = electionGoverningBodyDAO;
	}
	
	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}
	
	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
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
	@SuppressWarnings("unchecked")
	public List<StateElectionsVO> getStateElections(Long stateId) {
		
    	List<Long> electionTypeArrayList = new ArrayList<Long>(0);
    	List<StateElectionsVO> stateElectionsList = new ArrayList<StateElectionsVO>(0);
    	List<StateElectionsVO> stateElectionsSortedList = new ArrayList<StateElectionsVO>(0);  	
  	  	List elections = electionDAO.findElectionsByState(stateId);
    	    	    		
       	   if(elections.size()<1 || elections == null)
    		   return null;
    	   
       	   else{
    	        for(Object[] values:(List<Object[]>)elections){
    	      
    	    	  StateElectionsVO stateElections = new StateElectionsVO();

    	    	  stateElections.setElectionId((Long)values[0]);
    	    	  stateElections.setElectionTypeId((Long)values[1]);
    	    	  stateElections.setElectionType(values[2].toString());
    	    	  stateElections.setElectionSubtype(values[3].toString());
    	    	  stateElections.setYear(values[4].toString());
    	    	  
    	    	  StateElectionResultsVO stateElecResults = getStateElectionResults((Long)values[0]);
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
	 public ElectionGoverningBodyVO getChiefMinisterForAState(Long stateId)
	    {
		 ElectionGoverningBodyVO electionGoverningBodyVO = null;
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	
			try{
				 
				List<Object[]> list = electionGoverningBodyDAO.getChiefMinisters(stateId);
				if(list != null && list.size()>0){
					electionGoverningBodyVO =new ElectionGoverningBodyVO();
					for(Object[] params : list){
						 
						electionGoverningBodyVO.setCandidateId((Long)params[0]);
						electionGoverningBodyVO.setCandidateName(params[1].toString());
						electionGoverningBodyVO.setPartyId((Long)params[2]);
						electionGoverningBodyVO.setPartyName(params[3].toString());
						electionGoverningBodyVO.setStartDate((params[4] != null ? (sdf.format((Date)params[4])) :""));
						electionGoverningBodyVO.setElectionId((Long)params[5]);
						}
				}
				return electionGoverningBodyVO;
			}catch(Exception e)
			{
				e.printStackTrace();
				return electionGoverningBodyVO;
			}
	    	
	   }
	
*/}
