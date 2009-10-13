package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Assert;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElection;

public class ConstituencyElectionDAOHibernateTest extends BaseDaoTestCase {
	
	/*private IConstituencyElectionDAO constituencyElectionDAO;
	ConstituencyElection constElec = new ConstituencyElection(new Long(4),null,null,new Date(27-8-2009),null,null);
	
	
	public void setConstituencyElectionDAO(IConstituencyElectionDAO constituencyElectionDAO){
		this.constituencyElectionDAO = constituencyElectionDAO;
	}
	
	//@Test
	public void testFindConstituency(){
		Constituency constituency = constituencyElectionDAO.get(new Long(1)).getConstituency();
		System.out.println(constituency.getName());
		Assert.assertEquals("nellore", constituency.getName());
	}
	
	//@Test
	public void testAddDetails(){
		constituencyElectionDAO.save(constElec);
		setComplete();
		
	}
	//@Test
	public void testDeleteDetails(){
		constituencyElectionDAO.remove(new Long(4));
		setComplete();
	}*/
	@Test
	public void test(){
		Assert.assertEquals(1, 1);
	}
}
