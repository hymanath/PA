package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import junit.framework.Assert;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.ICandidateResultObjectsDAO;
import com.itgrids.partyanalyst.model.CandidateResult;

public class CandidateResultObjectsDAOHibernateTest extends BaseDaoTestCase {

	private ICandidateResultObjectsDAO candidateResultObjectsDAO;

	public void setCandidateResultObjectsDAO(
			ICandidateResultObjectsDAO candidateResultObjectsDAO) {
		this.candidateResultObjectsDAO = candidateResultObjectsDAO;
	}
	
	@Test
	public void testFindCandidateResultObjects(){
		List<CandidateResult> candidateResult = candidateResultObjectsDAO.findCandidateResultObjects(new Long(3));
		if(candidateResult!=null){
			for(CandidateResult result:candidateResult){
				
				if(result.getCandidateResultId().equals(new Long(121))){
					Assert.assertEquals(new Double(68167), result.getVotesEarned());
					Assert.assertEquals(new Long(1), result.getRank());
				}
				else if(result.getCandidateResultId().equals(new Long(122))){
					Assert.assertEquals(new Double(47018), result.getVotesEarned());
					Assert.assertEquals(new Long(2), result.getRank());
				}
					
			}
		}
	}
}
