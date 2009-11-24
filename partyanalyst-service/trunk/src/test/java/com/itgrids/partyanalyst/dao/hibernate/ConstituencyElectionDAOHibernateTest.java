package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Assert;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElection;

public class ConstituencyElectionDAOHibernateTest extends BaseDaoTestCase {
	
	private IConstituencyElectionDAO constituencyElectionDAO;
	//ConstituencyElection constElec = new ConstituencyElection(new Long(4),null,null,new Date(27-8-2009),null,null);
	
	
	public void setConstituencyElectionDAO(IConstituencyElectionDAO constituencyElectionDAO){
		this.constituencyElectionDAO = constituencyElectionDAO;
	}
	
	
	public void testFindConstituency(){
		Constituency constituency = constituencyElectionDAO.get(new Long(1)).getConstituency();
		Assert.assertEquals("nellore", constituency.getName());
	}
	
	
	public void testFindByConstituencyElectionAndDistrict(){
		List<ConstituencyElection> list = constituencyElectionDAO.findByConstituencyElectionAndDistrict("2004", "Atmakur", new Long(2), new Long(19));
		for(ConstituencyElection obj:list){
			System.out.println(obj.getConstiElecId());
			System.out.println(obj.getConstituency().getName());
			System.out.println(obj.getConstituency().getDistrict().getDistrictId());
		}
		assertEquals(list.size(),2);
	}
}
