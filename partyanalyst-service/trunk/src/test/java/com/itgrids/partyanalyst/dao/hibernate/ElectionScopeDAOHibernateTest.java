package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.model.ElectionScope;

public class ElectionScopeDAOHibernateTest extends BaseDaoTestCase {
	
	private IElectionScopeDAO electionScopeDAO; 
	
	public void setElectionScopeDAO(ElectionScopeDAO electionScopeDAO){
		this.electionScopeDAO = electionScopeDAO;
	}
	
	/*@Test
	public void testFindByTypeIdCountryIdStateId(){
		List<ElectionScope> actualResult =electionScopeDAO.findByTypeIdCountryIdStateId(new Long(2),new Long(1),new Long(1));
		System.out.println(actualResult.get(0).getElectionScopeId());
		Assert.assertEquals(new Long(2), actualResult.get(0).getElectionScopeId());
	}

	@Test
	public void testStates(){
		List<ElectionScope> electionScopes = electionScopeDAO.findByPropertyElectionTypeId(2l);
		assertEquals(2l,electionScopes.size());
	}*/
	
	public void testGetElectionTypes(){
		ElectionScope obj = electionScopeDAO.get(0l);
		System.out.println(obj);
	}
	
	@Test
	public void testGetElectionScope(){
		List<ElectionScope> scopes = electionScopeDAO.getElectionScopes(12L);
		System.out.println(" Size :" + scopes.size());
		for(ElectionScope scope:scopes){
			System.out.println(" Election Types :" + scope.getElectionType().getElectionType());
		}
		
	}
}
