package com.itgrids.partyanalyst.dao.hibernate;

import java.sql.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Assert;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.utils.IConstants;

public class ElectionDAOHibernateTest extends BaseDaoTestCase {
	
	private IElectionDAO electionDAO; 
	
	//mock data for add and remove test cases
	Election election1 = new Election(4L,null,new Date(27-8-2009),new Date(27-8-2010),"27-08-2009","2010",null,null,null,null,null);
	
	
	public void setElectionDAO(IElectionDAO electionDAO){
		this.electionDAO = electionDAO;
	}
	/*
	//@Test
	public void testfindByEndDate(){
		List<Election> election = electionDAO.findByEndDate("2010-10-01");
		Assert.assertEquals(election.get(0).getEndDate(),"2010-10-01");
		
	}
	//@Test
	public void testfindByElectionYear(){
		List<Election> election = electionDAO.findByElectionYear("2010");
		Assert.assertEquals(election.get(0).getEndDate(),"2010-10-01");
		assertEquals(2,2);
		
	}
	//@Test
	public void testGetAll(){
		List<Election> election1 = electionDAO.getAll();
		System.out.println(election1.size());
		Assert.assertEquals(election1.size(), 1);
	}
	//@Test
	public void testSaveDetails(){
		electionDAO.save(election1);
	    setComplete();
	}
	//@Test
	public void testRemoveDetails(){
		electionDAO.remove(new Long(4));
		setComplete();
	}*/

	/*@Test
	public void test(){
		Assert.assertEquals(1, 1);
	}*/
	
	/*@Test
	public void testFindByPropertyTypeIdCountry_StateId(){
		List<Election> list = electionDAO.findByPropertyTypeId_CountryId_StateId(new Long(1), new Long(1), new Long(1));
		for(Election e: list){
			System.out.println(e.getElectionYear());
		}
		Assert.assertEquals(2, list.size());
		list = electionDAO.findByPropertyTypeId_CountryId_StateId(new Long(1), new Long(1),null);
		Assert.assertEquals(0, list.size());
	}*/

	/*@Test
	public void testFindPreviousYear(){
		String prevYear = electionDAO.findPreviousElectionYear("2009", new Long(2), new Long(1), new Long(1));
		System.out.println(prevYear);
	}
	
	@Test
	public void testFindElection(){
		List<Election> election = electionDAO.findByElectionTypeYearAndState(new Long(2), "2009", new Long(1), new Long(1));
		System.out.println("Election ::"  + election.get(0).getElectionId());
	}
	@Test
	public void testFindElectionType(){
		List result = electionDAO.findElectionIdAndYear(1l,1l);
		Assert.assertEquals(8, result.size());
	}
	@Test
	public void testFindAllElectionYearsForAnElectionType(){
		List result = electionDAO.findElectionAndYearForElectionTypeAndState(3l,1l);
		Assert.assertEquals(2, result.size());
	}
	
	public void testElectionYears(){
		List result = electionDAO.findElectionAndYearForElectionTypeAndState(3l,1l);
		Assert.assertEquals(2, result.size());		
	}*/
	
	/*public void testYears(){
		List<Election> result = electionDAO.findByElectionScopeId(1l, IConstants.ELECTION_SUBTYPE_BYE);
		for(Election ele:result)
			System.out.println(ele.getElectionYear());
	}
	
	@Test
	public void testFindGetElections(){
		String prevYear = electionDAO.findPreviousParliamentElectionYear("2009", 1l, 1l);
		
		System.out.println(" Previous Year :" + prevYear);
		
	}*/
	
	/*public void testFindLatestElectionIdAndYear(){
		System.out.println(electionDAO.findLatestElectionAssemblyElectionYearForState(IConstants.ASSEMBLY_ELECTION_TYPE, 12l));
		
	}*/
	
	/*public void testFindStatesByElectionType(){
		List result = electionDAO.findStatesByElectionType(6l);
		
		for(int i=0;i<result.size();i++)
		{
			Object[] parms = (Object[])result.get(i);
			System.out.print(Long.parseLong(parms[0].toString()));
			System.out.print(parms[1].toString());
				
		}
			
	}*/
	
}

