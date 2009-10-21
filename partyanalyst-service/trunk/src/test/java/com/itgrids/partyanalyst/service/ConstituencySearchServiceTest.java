package com.itgrids.partyanalyst.service;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.service.impl.ConstituencySearchService;
import com.itgrids.partyanalyst.util.DummyConstituencies;

public class ConstituencySearchServiceTest{
	
	private IConstituencyDAO constituencyDAO;
	private ConstituencySearchService constituencySearchService;
	
	

	@Before
	public void setUp() throws Exception {
		
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

}
