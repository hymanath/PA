package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.util.List;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.model.Election;

public class ElectionDAOHibernateTest extends BaseDaoTestCase {
	
	private IElectionDAO electionDAO; 
	
	//mock data for add and remove test cases
	Election election1 = new Election(4L,null,new Date(27-8-2009),new Date(27-8-2010),"27-08-2009","2010",null);
	
	
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
	@Test
	public void testFindByPropertyTypeIdCountry_StateId(){
		List<Election> list = electionDAO.findByPropertyTypeId_CountryId_StateId(new Long(1), new Long(1), new Long(1));
		for(Election e: list){
			System.out.println(e.getElectionYear());
		}
		Assert.assertEquals(2, list.size());
		list = electionDAO.findByPropertyTypeId_CountryId_StateId(new Long(1), new Long(1),null);
		Assert.assertEquals(0, list.size());
	}
	

	@Test
	public void testFindByElectionScope(){
		List<Election> list = electionDAO.findByElectionScope(new Long(2));
		for(Election e: list){
			System.out.println("NEW>>>>>>>>>>>"+e.getElectionYear());
		}
		Assert.assertEquals(2, list.size()); //here data is available for 2004 and 2009
	}
	
}

