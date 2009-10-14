package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import junit.framework.Assert;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IConstituencyElectionResultObjectsDAO;
import com.itgrids.partyanalyst.model.ConstituencyElectionResult;

public class ConstituencyElectionResultObjectsDAOHibernateTest extends
		BaseDaoTestCase {

	private IConstituencyElectionResultObjectsDAO constituencyElectionResultObjectsDAO;

	public void setConstituencyElectionResultObjectsDAO(
			IConstituencyElectionResultObjectsDAO constituencyElectionResultObjectsDAO) {
		this.constituencyElectionResultObjectsDAO = constituencyElectionResultObjectsDAO;
	}
	
	@Test
	public void testFindConstituencyElectionResultObjects(){
		List<ConstituencyElectionResult> constituencyElectionResultObjects = constituencyElectionResultObjectsDAO.findConstituencyElectionResultObjects(new Long(1));
		if(constituencyElectionResultObjects != null){
			for(ConstituencyElectionResult result:constituencyElectionResultObjects){
				if(result.getConstiElecResultId().equals(new Long(1))){
					Assert.assertEquals(new Double(154795), result.getTotalVotes());
					Assert.assertEquals(new Double(154690),result.getValidVotes());
				}
				else if(result.getConstiElecResultId().equals(new Long(12))){
					Assert.assertEquals(new Double(163577), result.getTotalVotes());
					Assert.assertEquals(new Double(118179),result.getValidVotes());
				}
			}
		}
	}
}
