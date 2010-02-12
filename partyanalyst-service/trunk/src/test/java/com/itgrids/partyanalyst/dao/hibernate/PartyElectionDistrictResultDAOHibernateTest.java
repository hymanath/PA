/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 09,2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Assert;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IPartyElectionDistrictResultDAO;
import com.itgrids.partyanalyst.model.PartyElectionDistrictResult;

public class PartyElectionDistrictResultDAOHibernateTest extends BaseDaoTestCase {
 
	private IPartyElectionDistrictResultDAO partyElectionDistrictResultDAO;

	public void setPartyElectionDistrictResultDAO(
			IPartyElectionDistrictResultDAO partyElectionDistrictResultDAO) {
		this.partyElectionDistrictResultDAO = partyElectionDistrictResultDAO;
	}
	
	@Test
	public void testFindPartyElectionDistrictResult(){
		List<PartyElectionDistrictResult> partyElectionDistrictResult = partyElectionDistrictResultDAO.getByPartyIdElectionIdAndDistrict(new Long(1), new Long(24), new Long(1), new Long(19));
		if(partyElectionDistrictResult != null){
			for(PartyElectionDistrictResult resultDetails:partyElectionDistrictResult){
				Assert.assertEquals(new Long(19), resultDetails.getDistrict().getDistrictId());
				Assert.assertEquals("5", resultDetails.getTotalSeatsWon());
			}
		}
	}
	
}
