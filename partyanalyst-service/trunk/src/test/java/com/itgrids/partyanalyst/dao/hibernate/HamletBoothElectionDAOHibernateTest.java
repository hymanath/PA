package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHamletBoothElectionDAO;
import com.itgrids.partyanalyst.model.HamletBoothElection;

public class HamletBoothElectionDAOHibernateTest extends BaseDaoTestCase{

	private IHamletBoothElectionDAO hamletBoothElectionDAO;

	public IHamletBoothElectionDAO getHamletBoothElectionDAO() {
		return hamletBoothElectionDAO;
	}

	public void setHamletBoothElectionDAO(
			IHamletBoothElectionDAO hamletBoothElectionDAO) {
		this.hamletBoothElectionDAO = hamletBoothElectionDAO;
	}
	
	/*public void test(){
		List list = hamletBoothElectionDAO.findByHamletAndBoothConstituencyElection(24l, 2548l);
		System.out.println(list.size());
	}*/
	
	/*public void testFindPanchayathsWiseBoothsAndHamletsDataInTehsilForElection(){
		List list = hamletBoothElectionDAO.findPanchayathsWiseBoothsAndHamletsDataInTehsilForElection(844l, 2l);
		for(Object[] values:(List<Object[]>)list)
			System.out.println(values[0]+"\t"+values[1]+"\t"+values[2]+"\t"+values[3]+"\t"+values[4]+"\t"+values[5]);
	}*/
	
	/*public void testFindPanchayathBoothIdsInTehsilForElection(){
		List list = hamletBoothElectionDAO.findPanchayathBoothIdsInTehsilForElection(844l, 2l);
		for(Object[] values:(List<Object[]>)list)
			System.out.println(values[0]+"\t"+values[1]);
	}*/
	
	public void testgetPanchayatWiseBoothDetails()
	{
		List<Object[]> list = hamletBoothElectionDAO.getPanchayatWiseBoothDetails(844l,38l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println("Constituency Id - "+params[0]+"  Name - "+params[1]+" Panchyat Id - "+params[2]+"  Panchayat name - "+params[3]+"hamlet Id - "+params[4]+"   Hamlet Name -  "+params[5]+"   Booth Id -  "+params[6]+"   Booth Name - "+params[7]+"  Total Voters - "+params[8]+"  Valid Votes - "+params[9]);
		}
	}
	
}
