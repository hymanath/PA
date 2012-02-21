package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.utils.IConstants;

public class ElectionScopeDAOHibernateTest extends BaseDaoTestCase {
	
	private IElectionScopeDAO electionScopeDAO; 
	
	public void setElectionScopeDAO(ElectionScopeDAO electionScopeDAO){
		this.electionScopeDAO = electionScopeDAO;
	}
	/*
	@Test
	public void testFindByTypeIdCountryIdStateId(){
		List<ElectionScope> actualResult =electionScopeDAO.findByTypeIdCountryIdStateId(new Long(1),new Long(1),new Long(1));
		System.out.println(actualResult.get(0).getElectionScopeId());
		//Assert.assertEquals(new Long(2), actualResult.get(0).getElectionScopeId());
	}

	@Test
	public void testStates(){
		List<ElectionScope> electionScopes = electionScopeDAO.findByPropertyElectionTypeId(2l);
		assertEquals(2l,electionScopes.size());
	}
	
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
		
	}*/
	
	/*public void testGetData(){
		
		List list = electionScopeDAO.getAllStatesAndTheirIds(IConstants.ASSEMBLY_ELECTION_TYPE);
		for(int i=0;i<list.size();i++){
			Object[] parms = (Object[])list.get(i);
			System.out.println(parms[0]+"\t\t"+parms[1]);
		}
	}*/
public void testGetData(){
		
	List<ElectionScope> list = electionScopeDAO.findByTypeIdStateId(2l,3l);
	System.out.println(list.size());
	}
}
