package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICasteDAO;
import com.itgrids.partyanalyst.model.Caste;

public class CasteDAOHibernateTest extends BaseDaoTestCase{
	
	private ICasteDAO casteDAO;

	public void setCasteDAO(ICasteDAO casteDAO) {
		this.casteDAO = casteDAO;
	}
	
	
	/*List<Long> ids = new ArrayList<Long>();
	ids.add(53l);ids.add(265l);
	List<ConstituencyElection> list = nominationDAO.findConstituencyElectionByElectionIdAndDistrictIdAndPartys(107l, 90l,ids);
	
	if(list != null && list.size() > 0)
		for(ConstituencyElection election : list)
			System.out.println(election.getConstituency().getConstituencyId());*/
	
/*	
	
		
		 // List list = casteDAO.getCasteNames(1l);
		 // System.out.println(list.size());
	*/
	
	/*public void testgetCasteNamesByCasteId() {
		
		List<Object[]> list=casteDAO.getCasteNamesByCasteId("assembly",1l,5l,47l,"winner");
			for(int i=1;i<=list.size();i++){	
				System.out.println(list.get(i));
			}
			
	}*/
	
	/*public void test()
	{
		casteDAO.getAll();
	}*/
	
	
	
	/*public void testgetCasteByCastName()
	{
		Caste caste = casteDAO.getCasteByCastName("OC");
		System.out.println(caste);
		if(caste != null)
			System.out.println(caste.getCasteName());
	}*/
	
	public void testgetCasteList()
	{
		casteDAO.getCasteList();
	}

}

