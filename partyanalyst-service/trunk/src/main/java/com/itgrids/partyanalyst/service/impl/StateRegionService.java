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
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IPartyElectionDistrictResultDAO;
import com.itgrids.partyanalyst.dao.IStateRegionDAO;
import com.itgrids.partyanalyst.dao.IStateRegionDistrictDAO;
import com.itgrids.partyanalyst.dto.PartyInfoVO;
import com.itgrids.partyanalyst.dto.PartyResultsInRegionVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.StateElectionsVO;
import com.itgrids.partyanalyst.service.IStateRegionService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.PartyResultsVOComparator;

public class StateRegionService implements IStateRegionService {
	
	private final static Logger log = Logger.getLogger(ElectionReportService.class);

	private IStateRegionDAO stateRegionDAO;
	private IStateRegionDistrictDAO stateRegionDistrictDAO;
	private IPartyElectionDistrictResultDAO partyElectionDistrictResultDAO;
	private IStaticDataService staticDataService;
	
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
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
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
	public StateElectionsVO getRegionWisePartyResultsInState(Long stateId,Long electionId) {
		
		if(log.isDebugEnabled())
			log.debug("Started Executing Method To Get Region wise All Party Results In An Election ..");
		
		StateElectionsVO stateElectionsVO = new StateElectionsVO();
		
		List<PartyResultsInRegionVO> partyResultsInRegionVOLst = new ArrayList<PartyResultsInRegionVO>();
		
		try{
			
			// get regions in a state
			List<SelectOptionVO> regionsInState = getRegionsInAState(stateId);
			List<SelectOptionVO> parties = staticDataService.getStaticPartiesListForAState(stateId);
			List<Long> partyIds = new ArrayList<Long>();
			List<String> partyNames = new ArrayList<String>();
			PartyResultsInRegionVO partyResultsInRegionVO = null;
			List<PartyInfoVO> partyAndAVGSeatsWon = new ArrayList<PartyInfoVO>();
			PartyInfoVO partySeatsInfoVO = null;
			
			for(SelectOptionVO party:parties){
				partyIds.add(party.getId());
				partyNames.add(party.getName());
			}
			
			Collections.sort(partyNames);
			
			for(SelectOptionVO stateRegions:regionsInState){
				
				partyResultsInRegionVO = new PartyResultsInRegionVO();
				partyResultsInRegionVO.setRegionId(stateRegions.getId());
				partyResultsInRegionVO.setRegionName(stateRegions.getName());
				
				//get districts for a region
				List<Long> districtsInRegion = getDistrictsInARegion(stateRegions.getId(),stateRegions.getName());
				
				List<Long> constituenciesCount = stateRegionDistrictDAO.getConstituenciesCountByDistrictRegion(stateRegions.getId());
				partyResultsInRegionVO.setConstituenciesCount(constituenciesCount.get(0));
				//DAO Call to results party results in a region
				List partyResults = partyElectionDistrictResultDAO.findPartyResultsForARegionInState(districtsInRegion, electionId, partyIds);
				partyResultsInRegionVO.setPartyResultsInRegion(setPartyResultsInaRegionToVO(partyResults, parties));
				
				partyResultsInRegionVOLst.add(partyResultsInRegionVO);
			}
			
			for(int i=0; i<parties.size(); i++){
				int seatsWon = 0;
				for(int j=0; j<partyResultsInRegionVOLst.size(); j++)
					seatsWon += partyResultsInRegionVOLst.get(j).getPartyResultsInRegion().get(i).getTotalSeatsWon();
					
				partySeatsInfoVO = new PartyInfoVO();
				partySeatsInfoVO.setPartyShortName(parties.get(i).getName());
				partySeatsInfoVO.setAverageSeatsWon(seatsWon*1.0f/partyResultsInRegionVOLst.size());
				partyAndAVGSeatsWon.add(partySeatsInfoVO);
			}
			
			stateElectionsVO.setStaticParties(partyNames);
			stateElectionsVO.setPartyResultsInRegionVOLst(partyResultsInRegionVOLst);
			stateElectionsVO.setPartyAndAVGSeatsWon(partyAndAVGSeatsWon);
			
		}catch(Exception ex){
			
			log.error("Exception Raised :" + ex);
			PartyResultsInRegionVO partyRegionResult = new PartyResultsInRegionVO();
			ResultStatus rs = new ResultStatus();
			
			rs.setExceptionEncountered(ex);
			rs.setExceptionMsg(ex.getMessage());
			rs.setResultCode(ResultCodeMapper.FAILURE);
			
			partyRegionResult.setRs(rs);
			partyResultsInRegionVOLst.add(partyRegionResult);
			
			return stateElectionsVO;
		}
		
		
		return stateElectionsVO;
	}
	
	/**
	 * Method to set party results In a region to vo
	 * 
	 * @param resultsLst
	 * @return List<PartyResultsVO>
	 */
	@SuppressWarnings("unchecked")
	private List<PartyResultsVO> setPartyResultsInaRegionToVO(List resultsLst, List<SelectOptionVO> staticParties) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Setting Party results to VO ..");
		
		List<PartyResultsVO> partyResults = new ArrayList<PartyResultsVO>();
		Set<String> partiesContesting = new HashSet<String>(0); 
		Iterator lstItr = resultsLst.listIterator();
		PartyResultsVO resultVO = null;
		while(lstItr.hasNext()){
			
			resultVO = new PartyResultsVO();
			
			Object[] values = (Object[])lstItr.next();
			
			resultVO.setPartyId((Long)values[0]);
			resultVO.setPartyName((String)values[1]);
			partiesContesting.add((String)values[1]);
			
			String totSeatsWon = (String)values[2];
			resultVO.setTotalSeatsWon(Integer.parseInt(totSeatsWon));
			
			String totConstiParticipated = (String)values[3];
			resultVO.setSeatsParticipated(Integer.parseInt(totConstiParticipated));
			
			partyResults.add(resultVO);
		}
			
		for(SelectOptionVO staticParty:staticParties)
			if(!partiesContesting.contains(staticParty.getName())){
				resultVO = new PartyResultsVO();
				resultVO.setPartyName(staticParty.getName());
				resultVO.setTotalSeatsWon(0);
				partyResults.add(resultVO);
			}
		
		Collections.sort(partyResults, new PartyResultsVOComparator());
		
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
				
				Long districtId     = (Long)values[0];
				String districtName = (String)values[1];
				
				districtsInRegion.add(districtId);
				
				log.info(districtName + " Is In " + regionName);
			}
		}
		
	 return districtsInRegion;
	}

}
