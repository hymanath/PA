package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHamletBoothElectionDAO;

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
	
	/*public void testgetPanchayatWiseBoothDetails()
	{
		List<Object[]> list = hamletBoothElectionDAO.getPanchayatWiseBoothDetails(844l,38l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println("Constituency Id - "+params[0]+"  Name - "+params[1]+" Panchyat Id - "+params[2]+"  Panchayat name - "+params[3]+"hamlet Id - "+params[4]+"   Hamlet Name -  "+params[5]+"   Booth Id -  "+params[6]+"   Booth Name - "+params[7]+"  Total Voters - "+params[8]+"  Valid Votes - "+params[9]);
		}
	}*/
	
	/*public void testtFindPanchayatWiseVotingTrendsForATehsil()
	{
		List<Object[]> list = hamletBoothElectionDAO.findPanchayatWiseVotingTrendsForATehsil(844l,"38");
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj.toString());
		}
	}*/
	/*public void testGetVotersCountInAPanchayat()
	{
		List<Object[]> list = hamletBoothElectionDAO.getVotersCountInAPanchayat(38l,1l);
		System.out.println(list.size());
		
		for(Object[] data : list)
		{
			System.out.println(data[0]+" - "+data[1]+" - "+data[2]);
		}
	}	*/
	/*public void testGetBoothIdsByPanchayatId()
	{
		List<Long> list = hamletBoothElectionDAO.getBoothIdsByPanchayatId(1l, 116l);
		System.out.println(list.size());
		if(list != null && list.size() > 0)
			System.out.println(list);
	}*/
	
	/*public void testFindElectionsHappendInAPanchayat()
	{
		List<Object[]> list = hamletBoothElectionDAO.findElectionsHappendInAPanchayat(1l, IConstants.ASSEMBLY_ELECTION_TYPE);
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println(params[0]+"  "+params[1]);
		}
	}*/
	
	/*public void testFindAllElectionsHappendInAPanchayat()
	{
		List<Object[]> list = hamletBoothElectionDAO.findAllElectionsHappendInAPanchayat(1l);
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println(params[0]+"  "+params[1]);
		}
	}*/
	
	public void testFindAllElectionsHappendInAPanchayat()
	{
		List<Long> constiIds = new ArrayList<Long>();
		constiIds.add(476l);
		constiIds.add(299l);
		List<Object[]> list = hamletBoothElectionDAO.findAllElectionsHappendInAMandal(1007l,constiIds);
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println(params[0]+"  "+params[1]);
		}
	}
	
	/*public void testGetBoothIdsByMandalId()
	{
		List<Long> list = hamletBoothElectionDAO.getBoothIdsByMandalId(844l, 38l);
		System.out.println(list.size());
		if(list != null && list.size() > 0)
			System.out.println(list.get(0));
	}*/
	
	/*public void testGetAllBoothsByConstituencyId()
	{
		List<Long> list = hamletBoothElectionDAO.getBoothIdsByConstituencyId(232l, 38l);
			System.out.println(list.size());
			if(list != null && list.size() > 0)
				for(Long ids : list)
				System.out.println(ids);
	}*/
	
	/*public void testfindAllElectionsHappendInAConstituency()
	{
		List<Object[]> list = hamletBoothElectionDAO.findAllElectionsHappendInAConstituency(232l);
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
				System.out.println(params[0]+" "+params[1]);
		}
	}*/
		
	/*public void testfindAllElectionsHappendInALocalElectionBody()
	{
		List<Object[]> list = hamletBoothElectionDAO.findAllElectionsHappendInALocalElectionBody(83l);
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
				System.out.println(params[0]+" "+params[1]);
		}
	}
	*/
	
	
}
