package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dto.ConstituenciesStatusVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.utils.IConstants;


public class ConstituencyDAOHibernateTest extends BaseDaoTestCase {
	
	private IConstituencyDAO constituencyDAO;

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
/*	
 	//@Test
	public void testFindStates() {
		State state = constituencyDAO.get(new Long(1)).getState();
		System.out.println(state.getStateName());
		Assert.assertEquals(state.getStateName(), "Andhra Pradesh");
	} 

	//@Test
	public void testFindByCountryId(){
		List<Constituency> constList = constituencyDAO.findByCountryId(new Long(1));
		Assert.assertEquals(13, constList.size());
		
		Constituency constituency = constList.get(0);
		String actualConstituencyName = constituency.getName();
		Assert.assertEquals("Kavali",actualConstituencyName);
		
	    ElectionScope election = constituency.getElectionScope();
		Assert.assertEquals(new Long(2), election.getElectionScopeId());
	}
	
	public void testFindByStateName(){
		List<Constituency> constituencies = constituencyDAO.findByStateId(new Long(1));
		Assert.assertEquals(13, constituencies.size());
	}
	
	public void testFindByElectionScope(){
		List<Constituency> parlCons = constituencyDAO.findByElectionScope(1L);
		List<Constituency> assemCons = constituencyDAO.findByElectionScope(2L);
		Assert.assertNotNull(parlCons);
		Assert.assertNotNull(assemCons);
	}
	
	public void testGetConstituencies() {
		List<Constituency> constList = constituencyDAO.findByStateId(new Long(1));
		
		for(Constituency constituency: constList){
			System.out.println(constituency.getName());
		}
	}
	*/
	//@Test
	
	public void testFindDistrictIdByConstituencyID(){
		List<Long> districtId = constituencyDAO.getDistrictIdByConstituencyId(new Long(1234));
		Assert.assertEquals(1, districtId.size());
	}
	
	public void testFindStateIdByConstituencyID(){
		List<Long> stateId = constituencyDAO.getStateIdByConstituencyId(new Long(1234));
		Assert.assertEquals(1, stateId.size());
	}
	
	public void testFindById(){
		Constituency constituency  = constituencyDAO.get(new Long(1));
		System.out.println("Constituency Name ::" + constituency.getName());
	}
}
