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
	
	
	/*public void testFindByConstituencyElectionAndDistrict(){
		List<ConstituencyElection> list = constituencyElectionDAO.findByConstituencyElectionAndDistrict("2004", "Atmakur", new Long(2), new Long(19));
		for(ConstituencyElection obj:list){
			System.out.println(obj.getConstiElecId());
			System.out.println(obj.getConstituency().getName());
			System.out.println(obj.getConstituency().getDistrict().getDistrictId());
		}
		assertEquals(list.size(),2);
	}
	
	@Test
	public void testFindConstituencyFromConstituencyElection(){
		List<Constituency> constituencys = constituencyElectionDAO.findConstituencyByElectionAndDistrict(new Long(9), new Long(14));
		System.out.println("Count ::" + constituencys.size());
		for(Constituency con:constituencys){
			System.out.println("Constituency ::" + con.getName());
		}
	}*/
	
	@SuppressWarnings("unchecked")
	public void testFindValidVotedForAnElectionForAState(){
		List list = constituencyElectionDAO.findTotalValidVotesForAnElectionForAState(new Long(12));
		Object params = (Object)list.get(0);
		Double validVotes = (Double)params;
		System.out.println("Total ValidVotes For Assembly 2009(A.P) ::" + validVotes.longValue());
	}
	
	@SuppressWarnings("unchecked")
	public void testFindConstituenciesCount(){
		List list = constituencyElectionDAO.findTotalValidVotesForAnElectionForAStateAndDistrict(new Long(9),new Long(19));
		Object params = (Object)list.get(0);
		Double validVotes = (Double)params;
		System.out.println("Total ValidVotes For Assembly 2009(A.P) Nellore Dist ::" + validVotes.longValue());
	}
	
	@Test
	public void testByElectionType(){
		List list = constituencyElectionDAO.findTotalAssemblyConstituencies(9l,1l);
		for(int i=0;i<list.size();i++){
			Object[] parms = (Object[])list.get(i);
			System.out.println(parms[1]);
		}
		System.out.println(list.size());
		
	}
}
