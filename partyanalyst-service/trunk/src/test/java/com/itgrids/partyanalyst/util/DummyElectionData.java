/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 18,2010.
 */
package com.itgrids.partyanalyst.util;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.model.Country;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.State;

public class DummyElectionData {
	
	public static State getAPState(){
		State state = new State();
		state.setStateId(new Long(1));
		state.setStateName("Andhra Pradesh");
	return state;
	}
	
	public static Country getIndia(){
		Country country = new Country();
		country.setCountryId(new Long(1));
		country.setCountryName("India");
	 return country;
	}

	public static Election getElectionForAP2004(){
		
		ElectionType electionType = new ElectionType();
		electionType.setElectionTypeId(new Long(2));
		electionType.setElectionType("Assembly");
		electionType.setScope("State Level");
		
		ElectionScope electionScope = new ElectionScope();
		electionScope.setElectionScopeId(new Long(2));
		electionScope.setElectionType(electionType);
		electionScope.setState(getAPState());
		electionScope.setCountry(getIndia());
		
		Election election = new Election();
		election.setElectionId(new Long(1));
		election.setElectionScope(electionScope);
		election.setElectionYear("2004");
				
		return election;
	}
	
    public static Election getElectionForAP2009(){
		
		ElectionType electionType = new ElectionType();
		electionType.setElectionTypeId(new Long(2));
		electionType.setElectionType("Assembly");
		electionType.setScope("State Level");
		
		ElectionScope electionScope = new ElectionScope();
		electionScope.setElectionScopeId(new Long(2));
		electionScope.setElectionType(electionType);
		electionScope.setState(getAPState());
		electionScope.setCountry(getIndia());
		
		Election election = new Election();
		election.setElectionId(new Long(1));
		election.setElectionScope(electionScope);
		election.setElectionYear("2009");
				
		return election;
	}
    
    public static List<Election> getElectionsList2004(){
    	List<Election> electionList = new ArrayList<Election>();
    	electionList.add(getElectionForAP2004());
      	return electionList;
    }
    
    public static List<Election> getElectionsList2009(){
    	List<Election> electionList = new ArrayList<Election>();
    	electionList.add(getElectionForAP2009());
      	return electionList;
    }
}
