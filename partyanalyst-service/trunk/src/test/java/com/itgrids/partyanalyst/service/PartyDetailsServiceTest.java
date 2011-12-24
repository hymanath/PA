package com.itgrids.partyanalyst.service;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.itgrids.partyanalyst.model.PartyProfileDescription;
import com.itgrids.partyanalyst.dao.IPartyProfileDescriptionDAO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.service.impl.PartyDetailsService;

public class PartyDetailsServiceTest {
	private PartyDetailsService partyDetailsService;
	private IPartyProfileDescriptionDAO partyProfileDescriptionDAO;
    private PartyProfileDescription partyProfileDescription;
	@Before
	public void init() {
		partyDetailsService = new PartyDetailsService();
		partyProfileDescriptionDAO = EasyMock
				.createMock(IPartyProfileDescriptionDAO.class);

	}

	@Test
	public void checkGetPartyProfileInfo() {
	List<Object> result=new ArrayList<Object>(0);
	result.add("this is description");
    EasyMock.expect(partyProfileDescriptionDAO.getPartyProfileDescription(10l)).andReturn(result);
    EasyMock.replay(partyProfileDescriptionDAO);
    partyDetailsService.setPartyProfileDescriptionDAO(partyProfileDescriptionDAO);
    List<String> results=partyDetailsService.getPartyProfileDescriptionById(10l);
    Assert.assertEquals(result, results);
    EasyMock.verify(partyProfileDescriptionDAO);
	}
	//@Test
	/*public void checkgetMaxOrderNo(){
		List<Object> result=new ArrayList<Object>(0);
		result.add(4);
		EasyMock.expect(partyProfileDescriptionDAO.getMaxOrderNo(5l)).andReturn(result);
		EasyMock.replay(partyProfileDescriptionDAO);
		partyDetailsService.setPartyProfileDescriptionDAO(partyProfileDescriptionDAO);
		List<Object> results = partyProfileDescriptionDAO
		.getMaxOrderNo(5l);
		Assert.assertEquals(result, results);
	    EasyMock.verify(partyProfileDescriptionDAO);
		
	}*/
	
}
