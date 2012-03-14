package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Assert;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IConstituencyElectionResultDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.model.ConstituencyElectionResult;

public class ConstituencyElectionResultDAOHibernateTest extends BaseDaoTestCase {

    private IConstituencyElectionResultDAO constituencyElectionResultDAO; 
  //mock data for add and remove test cases
    ConstituencyElectionResult constElecRes = new ConstituencyElectionResult(new Long(2),null,new Double(6000),new Double(5000),new Double(500),new Double(200),new Double(10000),new Double(100));
    
	public void setconstituencyElectionResultDAO(IConstituencyElectionResultDAO constituencyElectionResultDAO){
		this.constituencyElectionResultDAO = constituencyElectionResultDAO;
	}
	
	/*public void testFindByConstituencyElections(){
		List<Long> list = new ArrayList<Long>();
		StringBuilder sb = new StringBuilder("1");
		for(long i=1;i<11;i++)
			sb.append(",").append(i);
		List<ConstituencyElectionResult> results =constituencyElectionResultDAO.findByConstituencyElections(sb.toString());
		for(ConstituencyElectionResult result:results){
			System.out.println("id=:"+result.getConstiElecResultId()+" result=:"+result.getTotalVotes());
		}
		Assert.assertEquals(10, results.size());
	}
	@Test
	public void test(){
		Assert.assertEquals(1, 1);
	}
	
	@Test
	public void testFindByElectionTypeAndYear(){
		List<ConstituencyElectionResult> results = constituencyElectionResultDAO.findByElectionTypeId_Year_StateId(new Long(2), "2009", new Long(1));
		Assert.assertEquals(279, results.size());
	}*/

	//@Test
	/*public void testFindByMissingVotes(){
		List<ConstituencyElectionResult> constElecResult = constituencyElectionResultDAO.findByMissingVotes(200);
		Assert.assertEquals(constElecResult.size(),2);
		Assert.assertEquals(constElecResult.get(0).getConstituencyElection().getConstiElecId(),new Long(1));
	}
	
	//@Test
	public void testFindByRejectedVotes(){
		List<ConstituencyElectionResult> constElecResult = constituencyElectionResultDAO.findByRejectedVotes(500);
		Assert.assertEquals(new Long(500),constElecResult.get(0).getRejectedVotes());
	}
	
	
	//@Test
	public void testFindByTenderedVotes(){
		List<ConstituencyElectionResult> constElecResult = constituencyElectionResultDAO.findByTenderedVotes(2000);
	    Assert.assertEquals(constElecResult.get(0).getTenderedVotes(), new Long(2000));
	}
	
	//@Test
	public void testFindByTotalVotes(){
		List<ConstituencyElectionResult> constElecResult = constituencyElectionResultDAO.findByTotalVotes(6000);
	    Assert.assertEquals(constElecResult.get(0).getTotalVotes(), new Long(6000));
	}

	//@Test
	public void testFindByTotalVotesPolled(){
		List<ConstituencyElectionResult> constElecResult = constituencyElectionResultDAO.findByTotalVotesPolled(10000);
	    Assert.assertEquals(constElecResult.get(0).getTotalVotesPolled(), new Long(10000));
	}
	
	//@Test
	public void testFindByValidVotes(){
		List<ConstituencyElectionResult> constElecResult = constituencyElectionResultDAO.findByValidVotes(5000);
	    Assert.assertEquals(constElecResult.get(0).getValidVotes(), new Long(5000));
	}*/
	
/*	//@Test
	public void testAddDetails(){
		constituencyElectionResultDAO.save(constElecRes);
		setComplete();
	}
	//@Test
	public void testRemoveDetails(){
		constituencyElectionResultDAO.remove(new Long(2));
		setComplete();
	}*/
	
	/*public void testFindByConstituency(){
		List<ConstituencyElectionResult> list = constituencyElectionResultDAO.findByConstituency(369L);
		System.out.println(list.size());
	}*/
	
	/*public void testFindTotalVotesAndValidVotesAndPolledVotesAndVotesPercentage()
	{
		List<Object[]> list = constituencyElectionResultDAO.findTotalVotesAndValidVotesAndPolledVotesAndVotesPercentage(3l);
		
		if(list != null && list.size() > 0)
		for(Object obj : list.get(0))
			System.out.println(obj);
	}*/
	
	/*public void testGetResultKnownConstituenciesCountInAElection()
	{
		Object object = constituencyElectionResultDAO.getResultKnownConstituenciesCountInAElection(38l);
		
		System.out.println(object.toString());
	}*/
	
	/*public void testGetConstituenciesOverviewInAElection()
	{
		List<Object[]> list = constituencyElectionResultDAO.getConstituenciesOverviewInAElection(38l);
		
		System.out.println(list.size());
		
		System.out.println(list.get(0)[0]+"---"+list.get(0)[1]+"---"+list.get(0)[2]);
	}*/
	
	public void testGetConstituenciesOverviewInAElection()
	{
		List<Object[]> list = constituencyElectionResultDAO.getConstituenciesAreaTypeOverviewInAElection(38l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
			System.out.println(params[0]+"---"+params[1]+"---"+params[2]+"---"+params[3]);
	}
	
}
