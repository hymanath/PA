/* 
 * Copyright (c) 2011 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 23, 2011
 */

/**
 * @author Suresh Jalli
 */  

package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IPartyElectionDistrictResultDAO;
import com.itgrids.partyanalyst.dao.IStateRegionDAO;
import com.itgrids.partyanalyst.dao.IStateRegionDistrictDAO;
import com.itgrids.partyanalyst.dto.PartyResultsInRegionVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IStateRegionService;

public class StateRegionService implements IStateRegionService {
	
	private final static Logger log = Logger.getLogger(ElectionReportService.class);

	private IStateRegionDAO stateRegionDAO;
	private IStateRegionDistrictDAO stateRegionDistrictDAO;
	private IPartyElectionDistrictResultDAO partyElectionDistrictResultDAO;
	
	public IStateRegionDAO getStateRegionDAO() {
		return stateRegionDAO;
	}
	public void setStateRegionDAO(IStateRegionDAO stateRegionDAO) {
		this.stateRegionDAO = stateRegionDAO;
	}
	
		
	public IStateRegionDistrictDAO getStateRegionDistrictDAO() {
		return stateRegionDistrictDAO;
	}
	public void setStateRegionDistrictDAO(
			IStateRegionDistrictDAO stateRegionDistrictDAO) {
		this.stateRegionDistrictDAO = stateRegionDistrictDAO;
	}
	public IPartyElectionDistrictResultDAO getPartyElectionDistrictResultDAO() {
		return partyElectionDistrictResultDAO;
	}
	public void setPartyElectionDistrictResultDAO(
			IPartyElectionDistrictResultDAO partyElectionDistrictResultDAO) {
		this.partyElectionDistrictResultDAO = partyElectionDistrictResultDAO;
	}
	/**
	 * This method returns wheather state has regions data or not
	 * 
	 * @param stateId //refers state ID
	 * @return boolean
	 */
	public boolean getStateRegionAvailability(Long stateId) {
		
		log.debug("Entered to check region availability for state ..");
		
		boolean hasRegions = false;	
		
		try{
			
			//DAO call to get regions count
			Long regionsCount = stateRegionDAO.getTotalRegionsInAState(stateId);
			
			if(regionsCount > 0)
				hasRegions = true;
			
		}catch(Exception ex){
			
			log.error("Exception Raised :" + ex);
			ex.printStackTrace();
		}
		
		
	 return hasRegions;
	
	}
	
	/**
	 * Method to get region wise all participated party results in an election
	 * 
	 * @param stateId
	 * @param electionId
	 * 
	 * @return PartyResultsInRegionVO
	 */
	@SuppressWarnings("unchecked")
	public List<PartyResultsInRegionVO> getRegionWisePartyResultsInState(Long stateId,Long electionId) {
		
		if(log.isDebugEnabled())
			log.debug("Started Executing Method To Get Region wise All Party Results In An Election ..");
		
		List<PartyResultsInRegionVO> partyResultsInRegionVOLst = new ArrayList<PartyResultsInRegionVO>();
		
		try{
			
			// get regions in a state
			List<SelectOptionVO> regionsInState = getRegionsInAState(stateId);
			
			for(SelectOptionVO stateRegions:regionsInState){
				
				PartyResultsInRegionVO partyResultsInRegionVO = new PartyResultsInRegionVO();
				partyResultsInRegionVO.setRegionId(stateRegions.getId());
				partyResultsInRegionVO.setRegionName(stateRegions.getName());
				
				
				//get districts for a region
				List<Long> districtsInRegion = getDistrictsInARegion(stateRegions.getId(),stateRegions.getName());
				
				//DAO Call to results party results in a region
				List partyResults = partyElectionDistrictResultDAO.findPartyResultsForARegionInState(districtsInRegion, electionId);
				partyResultsInRegionVO.setPartyResultsInRegion(setPartyResultsInaRegionToVO(partyResults));
				
				partyResultsInRegionVOLst.add(partyResultsInRegionVO);
			}
			
			
		}catch(Exception ex){
			
			log.error("Exception Raised :" + ex);
			PartyResultsInRegionVO partyRegionResult = new PartyResultsInRegionVO();
			ResultStatus rs = new ResultStatus();
			
			rs.setExceptionEncountered(ex);
			rs.setExceptionMsg(ex.getMessage());
			rs.setResultCode(ResultCodeMapper.FAILURE);
			
			partyRegionResult.setRs(rs);
			partyResultsInRegionVOLst.add(partyRegionResult);
			
		 return partyResultsInRegionVOLst;
		}
		
     return partyResultsInRegionVOLst;
	}
	
	/**
	 * Method to set party results In a region to vo
	 * 
	 * @param resultsLst
	 * @return List<PartyResultsVO>
	 */
	@SuppressWarnings("unchecked")
	private List<PartyResultsVO> setPartyResultsInaRegionToVO(List resultsLst) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Setting Party results to VO ..");
		
		List<PartyResultsVO> partyResults = new ArrayList<PartyResultsVO>();
		
		Iterator lstItr = resultsLst.listIterator();
		while(lstItr.hasNext()){
			
			PartyResultsVO resultVO = new PartyResultsVO();
			
			Object[] values = (Object[])lstItr.next();
			
			resultVO.setPartyId((Long)values[0]);
			resultVO.setPartyName((String)values[1]);
			
			String totSeatsWon = (String)values[2];
			resultVO.setTotalSeatsWon(Integer.parseInt(totSeatsWon));
			
			partyResults.add(resultVO);
		}
		
	 return partyResults;
	}
	
	/**
	 * Method to get regions in state
	 * 
	 * @param stateId
	 * @return List<SelectOptionVO>
	 */
	@SuppressWarnings("unchecked")
	private List<SelectOptionVO> getRegionsInAState(Long stateId) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Getting Regions In State ..");
		
		List<SelectOptionVO> regionsInState = new ArrayList<SelectOptionVO>();
		
		//DAO call to get regions
		List resultsLst = stateRegionDAO.getStateRegionByType(stateId);
		
		if(resultsLst != null && resultsLst.size() > 0){
			
			Iterator lstItr = resultsLst.listIterator();
			while(lstItr.hasNext()){
				
				Object[] values= (Object[])lstItr.next();
				
				Long regionId     = (Long)values[1];
				String regionName = (String)values[0];
				
				regionsInState.add(new SelectOptionVO(regionId,regionName));
			}
		}
		
	 return regionsInState;
	}
	
	/**
	 * Method to get districts in a region
	 * 
	 * @param regionId
	 * @return List<SelectOptionVO>
	 */
	@SuppressWarnings("unchecked")
	private List<Long> getDistrictsInARegion(Long regionId,String regionName) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Getting Districts In A Region ..");
		
		List<Long> districtsInRegion = new ArrayList<Long>();
		
		//DAO Call to get districts in region
		List districtsLst = stateRegionDistrictDAO.getDistrictsInARegion(regionId);
		
		if(districtsLst != null && districtsLst.size() > 0){
			
			Iterator lstItr = districtsLst.listIterator();
			while(lstItr.hasNext()){
				
				Object[] values= (Object[])lstItr.next();
				
				Long districtId     = (Long)values[1];
				String districtName = (String)values[0];
				
				districtsInRegion.add(districtId);
				
				log.info(districtName + " Is In " + regionName);
			}
		}
		
	 return districtsInRegion;
	}

}
