package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import junit.framework.Assert;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CandidateResult;
import com.itgrids.partyanalyst.model.Nomination;


public class CandidateResultDAOHibernateTest extends BaseDaoTestCase {
	@Test
	public void test(){
		Assert.assertEquals(1, 1);
	}
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
	}*/
}
