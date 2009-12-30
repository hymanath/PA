package com.itgrids.partyanalyst.service;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.service.impl.ConstituencySearchService;
import com.itgrids.partyanalyst.util.DummyConstituencies;

public class ConstituencySearchServiceTest{
	
	private IConstituencyDAO constituencyDAO;
	private ConstituencySearchService constituencySearchService;
	private IElectionScopeDAO electionScopeDAO;
	
	

	@Before
	public void setUp() throws Exception {

		electionScopeDAO = EasyMock.createMock(IElectionScopeDAO.class);
		constituencyDAO = EasyMock.createMock(IConstituencyDAO.class);
		constituencySearchService = new ConstituencySearchService();
		constituencySearchService.setConstituencyDAO(constituencyDAO);
	}
	
	@Test
	public void checkConstituencyNamesSize(){
		EasyMock.expect(constituencyDAO.getAll()).andReturn(DummyConstituencies.getConstituencies());
		EasyMock.replay(constituencyDAO);	
		Assert.assertEquals(5,constituencySearchService.getConstituencyNamesAndIds().size());
		EasyMock.verify(constituencyDAO);
		
	}
	
	
	@Test
	public void checkConstituencyNames(){
		EasyMock.expect(constituencyDAO.getAll()).andReturn(DummyConstituencies.getConstituencies());
		EasyMock.replay(constituencyDAO);	
		Assert.assertEquals("Madanapalli",constituencySearchService.getConstituencyNamesAndIds().get(0).getName());
		EasyMock.verify(constituencyDAO);
	}
	
/*	@Test
	public void testConstituencyNamesByElectionScope(){
		ElectionScope scope = new ElectionScope(5L);
		List<ElectionScope> scopes = new ArrayList<ElectionScope>();
		scopes.add(scope);
		EasyMock.expect(electionScopeDAO.findByTypeIdCountryIdStateId(1L,1L,2L)).andReturn(scopes);
		EasyMock.replay(electionScopeDAO);
		
	}*/


	
}
