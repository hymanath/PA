/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 08,2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Assert;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IPartyElectionResultDAO;
import com.itgrids.partyanalyst.model.PartyElectionResult;
import com.itgrids.partyanalyst.utils.IConstants;

public class PartyElectionResultDAOHibernateTest extends BaseDaoTestCase {

	private IPartyElectionResultDAO partyElectionResultDAO;

	public void setPartyElectionResultDAO(
			IPartyElectionResultDAO partyElectionResultDAO) {
		this.partyElectionResultDAO = partyElectionResultDAO;
	}
	
	/*@Test
	public void testFindByElectionAndParty()
	{
		List<PartyElectionResult> partyElectionResults = partyElectionResultDAO.getByElectionAndParty(new Long(1), new Long(34));
		if(partyElectionResults != null && partyElectionResults.size() > 0){
			for(PartyElectionResult partyElecResult:partyElectionResults){
				Assert.assertEquals("170", partyElecResult.getTotalSeatsWon());
				System.out.println("votes percentage ::" + partyElecResult.getVotesPercentage());
			}
		}
		else if(partyElectionResults.equals(null) || partyElectionResults.size() == 0){
			System.out.println("No Results Available.....");
		}
	}*/
	
	/*@SuppressWarnings("unchecked")
	@Test
	public void testFindSeatsWonByAPartyInAnElection(){
	 List resultsList = partyElectionResultDAO.getBasicPartyElectionResultForAPartyInAnElection(new Long(9), new Long(62));	
	 if(resultsList != null && resultsList.size() > 0){
		 Object[] params = (Object[])resultsList.get(0);
		 Long partyId = (Long)params[0];
		 String partyName = (String)params[1];
		 String totSeatsWon = (String)params[2];
		 
		 System.out.print(" Party :" + partyName);
		 System.out.print(" Seats Won :" + totSeatsWon);
	 }
	 else if(resultsList == null || resultsList.size() == 0){
		 System.out.println("No Results Available.....");
	 }
	}*/
	
	/*public void testGetPartyBasicResultForAnElection()
	{
		List<Object[]> list = partyElectionResultDAO.getPartyBasicResultForAnElection(38l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj);
		}
	}*/
	
	/*public void test()
	{
		List<Object[]> list = partyElectionResultDAO.getBasicPartiesForAnElection(38l);
		
		System.out.println(list.size());
		
		for(Object params : list)
		{
			System.out.println(params);
		}
	}*/
	
	/*public void testGetPartiesBasicResultForAnElection()
	{
		List<Long> partiesList = new ArrayList<Long>(0);
		
		partiesList.add(872l);
		partiesList.add(886l);
		partiesList.add(269l);
		partiesList.add(265l);
		
		List<Object[]> list = partyElectionResultDAO.getPartiesBasicResultForAnElection(38l, partiesList);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj);
		}
	}
	
	public void testGetPartyElectionResultsBasedOnPartyId()
	{
		List<PartyElectionResult> elecList = partyElectionResultDAO.getPartyElectionResultsBasedOnPartyId(239l,IConstants.ASSEMBLY_ELECTION_TYPE,true);
		for(PartyElectionResult list : elecList)
		{
			System.out.println(list.getElection().getElectionYear() +" In \t"+list.getElection().getElectionScope().getState().getStateName() +"\t"+list.getTotalSeatsWon()+"\t"+list.getTotalConstiParticipated());
			//System.out.println(list.getTotalConstiParticipated());
		}
			
	}*/
	
	public void testGetPartiesParticipatedMoreThanOnce()
	{
		List<Object[]> partiesList = partyElectionResultDAO.getPartiesParticipatedMoreThanOnce(1l,IConstants.PARLIAMENT_ELECTION_TYPE);
		System.out.println(partiesList.size());
		for(Object[] params : partiesList)
			System.out.println(params[0]+" -- "+params[1]);
	}
	
}
