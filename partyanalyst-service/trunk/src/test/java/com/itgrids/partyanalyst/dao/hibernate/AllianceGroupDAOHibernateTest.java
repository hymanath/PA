package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.model.AllianceGroup;
import com.itgrids.partyanalyst.model.Party;


public class AllianceGroupDAOHibernateTest extends BaseDaoTestCase {

	private IAllianceGroupDAO allianceGroupDAO;
	
	public void setAllianceGroupDAO(IAllianceGroupDAO allianceGroupDAO) {
		this.allianceGroupDAO = allianceGroupDAO;
	}

	/*public void testFinBYElectionYear(){
		List<AllianceGroup> res = allianceGroupDAO.findByGroupId(new Long(2));
	}*/
	
	/*public void testFindAlliancePartiesByElectionAndParty(){
		List list = allianceGroupDAO.findAlliancePartiesByElectionAndParty(2l, 361l);
		long start = System.currentTimeMillis();
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]);
		long end = System.currentTimeMillis();
		System.out.println((end - start));
	}*/
	
	/*@SuppressWarnings("unchecked")
	public void testGetAlliancParties(){
		List list = allianceGroupDAO.findPartiesByGroup(new Long(5));
		
		if(list != null && list.size() > 0){
			for(int i=0;i<list.size();i++){
				Object[] params = (Object[])list.get(i);
				System.out.println(" Party :" + params[1]);
			}
		}
	}*/
	
	/*public void testFindAlliancePartiesByElectionStateAndPartyExcludeParty(){
		List list = allianceGroupDAO.findAlliancePartiesByElectionStateAndPartyExcludeParty(2l, 24l, 1l);
		System.out.println(list.size());
	}
	*/
	
	/*public void testFindAlliancePartiesByElectionAndPartyForState(){
		List list = allianceGroupDAO.findAlliancePartiesByElectionAndPartyForState(3l, 361l, 1l);
		System.out.println(list.size());
	}*/
	
	/*public void testGetAlliancesAndPartiesForAnElection()
	{
		List<Object[]> list = allianceGroupDAO.getAlliancesAndPartiesForAnElection(38l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj.toString());
		}
	}*/

	public void testGetgetAlliancesAndPartiesForPartiesAndElections()
	{
		List<Long> Eids = new ArrayList<Long>();
		Eids.add(38L);
		Eids.add(3L);
		
		List<Long> Pids = new ArrayList<Long>();
		Pids.add(872L);
		
		
		List<Object[]> list = allianceGroupDAO.getAlliancesAndPartiesForPartiesAndElections(Eids,Pids);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj.toString());
		}
	}
}
