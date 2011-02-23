/* 
 * Copyright (c) 2011 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 23, 2011
 */
package com.itgrids.partyanalyst.service;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IStateRegionDAO;
import com.itgrids.partyanalyst.service.impl.StateRegionService;

/**
 * @author Suresh Jalli
 *
 */
public class StateRegionServiceTest {

	IStateRegionDAO stateRegionDAO;
	IStateRegionService stateRegionService;
	
	@Before
	public void init(){
		
		System.out.println("called init method..........");
		
		stateRegionDAO = EasyMock.createMock(IStateRegionDAO.class);
		stateRegionService = EasyMock.createMock(IStateRegionService.class);
	}
	
	@Test
	public void testGetStateRegionAvailability(){
		
		StateRegionService regionService = new StateRegionService();
		
		Long countValue = 0L;
		EasyMock.expect(stateRegionDAO.getTotalRegionsInAState(1L)).andReturn(countValue);
		EasyMock.replay(stateRegionDAO);
		
		regionService.setStateRegionDAO(stateRegionDAO);
		
		Boolean hasRegions = regionService.getStateRegionAvailability(1L);
		Boolean exp = false;
		
		Assert.assertEquals(exp, hasRegions);
			
	}
	
	
}
