package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import junit.framework.Assert;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CandidateResult;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.utils.IConstants;


public class CandidateResultDAOHibernateTest extends BaseDaoTestCase {
	
	private ICandidateResultDAO candidateResultDAO;
	
	public void setCandidateResultDAO(ICandidateResultDAO candidateResultDAO) {
		this.candidateResultDAO = candidateResultDAO;
	}


/*	@Test
	public void test(){
		Assert.assertEquals(1, 1);
	}*/
	/*private ICandidateResultDAO candidateResultDAO;
	CandidateResult candRes = new CandidateResult(new Long(4),null,new Double(50000),new Long(4));
	
	public void setCandidateResultDAO(ICandidateResultDAO candidateResultDAO){
		this.candidateResultDAO = candidateResultDAO;
	}
	
	//@Test
	public void testFindByRank(){
		List<CandidateResult> candresult = candidateResultDAO.findByRank(new Long(2));
		Assert.assertEquals(2, candresult.size());
		
		CandidateResult candidateResult = candresult.get(0);
		Nomination nomination = candidateResult.getNomination();
		Assert.assertEquals(new Long(1),nomination.getNominationId());
	}
	
	//@Test
	public void testFindByVotesEarned(){
		List<CandidateResult> candresult = candidateResultDAO.findByVotesEarned(new Double(20000));
		Assert.assertEquals(1, candresult.size());
		
		CandidateResult candidateResult = candresult.get(0);
		Nomination nomination = candidateResult.getNomination();
		Assert.assertEquals(new Long(1),nomination.getNominationId());
		Assert.assertEquals(20000.0,candidateResult.getVotesEarned());
	}
	
	//@Test
	public void testAddDetails(){
		candidateResultDAO.save(candRes);
		setComplete();
	}
	
	//@Test
	public void testRemoveDetails(){
		candidateResultDAO.remove(new Long(4));
		setComplete();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetVotesPercentForACandInAnElection(){
		List votesPercent = candidateResultDAO.getVotesPercentOfACandidateInAnElection(new Long(9),new Long(232),new Long(1));
		System.out.println("Size :" + votesPercent.size());
		if(votesPercent != null && votesPercent.size() > 0){
			Object params = (Object)votesPercent.get(0);
			System.out.println("Votes Percent :" + (String)params);
		}
	}*/
	
	/*public void testGetElectionResults(){
		List results = candidateResultDAO.getElectionResultsForAllPartiesInAMandal(new Long(835),"MPTC","2006");
		
		System.out.println("Size :" + results.size());
		
		for(int i=0;i<results.size();i++){
			Object[] params = (Object[])results.get(i);
			System.out.println("Party :" + (String)params[1]);
			System.out.print("  Votes Earned :" + (Double)params[2]);
			System.out.print( "  Valid Votes :" + (Double)params[3]);
			
			System.out.println("..");
		}
	}*/
	
	public void testGetData(){	
		int list = candidateResultDAO.updateMarginVotesAndPercentage("10.20",30.54d,"2009",IConstants.ASSEMBLY_ELECTION_TYPE,232l,15404l);
		System.out.println(list);
	//	setComplete();
	}
	
	
	
}
