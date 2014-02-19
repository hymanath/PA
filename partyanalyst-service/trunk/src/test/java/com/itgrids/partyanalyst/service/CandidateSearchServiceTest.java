package com.itgrids.partyanalyst.service;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.service.impl.CandidateSearchService;

public class CandidateSearchServiceTest {

	private ICandidateDAO candidateDAO;
	private CandidateSearchService candidateSearchService;

	@Before
	public void setUp() throws Exception {
		candidateDAO = EasyMock.createMock(ICandidateDAO.class);
		candidateSearchService = new CandidateSearchService();
		candidateSearchService.setCandidateDAO(candidateDAO);		
	}
	
	/*@Test
	public void checkConstituencyNamesSize(){
		
		EasyMock.expect(candidateDAO.getAll()).andReturn(DummyCandidates.getCandidates()).times(1);
		EasyMock.replay(candidateDAO);
		//Assert.assertEquals(5,candidateSearchService.getCandidateNamesAndIds().size());
		EasyMock.verify(candidateDAO);
	}*/
	/*
	@Test
	public void checkCandidateNamesAppending(){
		EasyMock.expect(candidateDAO.getAll()).andReturn(DummyCandidates.getCandidates()).times(1);
		EasyMock.replay(candidateDAO);
		List<SelectOptionVO> candidateNamesAndIds = candidateSearchService.getCandidateNamesAndIds();
		List<String> actualCandidateNames = new ArrayList<String>();
		for(SelectOptionVO selectOptionVO:candidateNamesAndIds){
			actualCandidateNames.add(selectOptionVO.getName());	
		}
		Assert.assertEquals(actualCandidateNames,DummyCandidates.getExpectedCandidateNames());
	}*/

	/*@Test
	public void checkLastestYearReturnVallue(){
		Long actualLastestYear = candidateSearchService.getLastestYear(DummyNominations.getNominations());
		Assert.assertEquals(new Long("2009"),actualLastestYear);
	}*/
	
	@Test 
	public void checkGetCandidateFullNameWithoutMiddle(){
		Candidate candidate = new Candidate();
		candidate.setFirstname("aaa");
		candidate.setMiddlename(null);
		candidate.setLastname("bbb");
		Assert.assertEquals("aaa bbb", candidateSearchService.getCandidateFullName(candidate));
	}
	
	@Test 
	public void checkGetCandidateFullNameWithMiddle(){
		Candidate candidate = new Candidate();
		candidate.setFirstname("aaa");
		candidate.setMiddlename("c");
		candidate.setLastname("bbb");
		Assert.assertEquals("aaa c bbb", candidateSearchService.getCandidateFullName(candidate));
	}
	
	@Test
	public void checkCandidateDetails(){
		
	}
	
}
