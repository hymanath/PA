package com.itgrids.partyanalyst.service;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.ICandidateProfileDescriptionDAO;
import com.itgrids.partyanalyst.service.impl.CandidateDetailsService;

public class CandidateDetailsServiceTest {

	private CandidateDetailsService candidateDetailsService;
	private ICandidateProfileDescriptionDAO candidateProfileDescriptionDAO;
	
	@Before
	public void init(){
		candidateProfileDescriptionDAO = EasyMock.createMock(ICandidateProfileDescriptionDAO.class);
		candidateDetailsService = new CandidateDetailsService();
	}
	
	@Test
	public void checkGetCandidateProfileDescriptionByCandidateID(){
		List<Object> results = new ArrayList<Object>(0);
		results.add("Ram is a very good boy");
		results.add("Ramesh is tall boy");
		EasyMock.expect(candidateProfileDescriptionDAO.getCandidateProfileDescription(10L)).andReturn(results);
		EasyMock.replay(candidateProfileDescriptionDAO);
		candidateDetailsService.setCandidateProfileDescriptionDAO(candidateProfileDescriptionDAO);
		
		List<String> returnResults = candidateDetailsService.getCandidateProfileDescriptionByCandidateID(10L);
		
		Assert.assertEquals(results, returnResults);
		EasyMock.verify(candidateProfileDescriptionDAO);
		
	}
	
}
