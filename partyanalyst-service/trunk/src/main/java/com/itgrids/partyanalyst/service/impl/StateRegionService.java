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

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IStateRegionDAO;
import com.itgrids.partyanalyst.service.IStateRegionService;

public class StateRegionService implements IStateRegionService {
	
	private final static Logger log = Logger.getLogger(ElectionReportService.class);

	private IStateRegionDAO stateRegionDAO;
	
	public IStateRegionDAO getStateRegionDAO() {
		return stateRegionDAO;
	}
	public void setStateRegionDAO(IStateRegionDAO stateRegionDAO) {
		this.stateRegionDAO = stateRegionDAO;
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

}
