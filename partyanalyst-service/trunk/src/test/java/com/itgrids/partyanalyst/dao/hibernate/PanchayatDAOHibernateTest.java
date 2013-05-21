package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.Panchayat;
import com.itgrids.partyanalyst.model.PanchayatHamlet;

public class PanchayatDAOHibernateTest extends BaseDaoTestCase{
	
	private IPanchayatDAO panchayatDAO;

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}
	
	/*public void test()
	{
		panchayatDAO.getAll();
	}
	*/
	
	/*public void testGetPanchayatsByTehsilId()
	{
		List<Object[]> list = panchayatDAO.getPanchayatsByTehsilId(844l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println(params[0].toString()+"------"+params[1].toString());
		}
	}*/
	
	/*public void testgetPanchayatsByConstituencyId()
	{
	List<Object[]>  list =panchayatDAO.getPanchayatsByConstituencyId(232l);
	for(Object[] params : list)
	{
		System.out.println(params[1].toString());
	}
	}*/
	
	/*public void testgetPanchayatsBymandalId()
	{
		List<Object> list = panchayatDAO.getPanchayatsBymandalId1(295l);
		List<Hamlet> lis=new ArrayList<Hamlet>();
		 Panchayat p=(Panchayat) list.get(0);
		Set<PanchayatHamlet> h  = p.getPanchayatHamlets();
		 Iterator itr=  h.iterator();
		 PanchayatHamlet ph=null;
		 while(itr.hasNext()){
			 ph=(PanchayatHamlet) itr.next();
		  lis.add(ph.getHamlet());}
		//PanchayatHamlet p1=(PanchayatHamlet) h.iterator()
		System.out.println(list.size());
	}*/

	/*public void testgetPanchayatiesCount()
	{
	 //Long value = panchayatDAO.getPanchayatiesCount(844l,"constituency");
	 //System.out.println(value);
	}*/
	
	/*public void testGetPanchayatIdsByTehsilId()
	{
		List<Long> list = panchayatDAO.getPanchayatIdsByTehsilId(844l);
		System.out.println(list.size());
		
		for(Long id : list)
			System.out.println(id);
	}*/
	
	/*public void testGetPanchayatIdsByMandalIdsList()
	{
		List<Long> mandalIdsList = new ArrayList<Long>(0);
		mandalIdsList.add(844l);
		List<Object[]> list = panchayatDAO.getPanchayatIdsByMandalIdsList(mandalIdsList);
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
				System.out.println(params[0]);
		}
	}*/
	
	/*public void testGetPanchayatIdsBytehsilIdsList()
	{
		List<Long> mandalIdsList = new ArrayList<Long>(0);
		mandalIdsList.add(844l);
		List<Long> panchayatIdsList = panchayatDAO.getPanchayatIdsBytehsilIdsList(mandalIdsList);
		System.out.println(panchayatIdsList);
	}*/
	
	/*public void testgetPanchayatIdsListByMandalId()
	{
		List<Long> ids = new ArrayList<Long>();
		ids.add(844l);
		ids.add(836l);
		ids.add(843l);
		ids.add(835l);
		List<Object[]> values = panchayatDAO.getAllPanchayatsInAListOfMandals(ids);
	
		System.out.println(values.size());
		for (Object[] parms : values) {
			
			System.out.println(parms[0] +":"+ parms[1] +":"+ parms[2]);
			
		}
	}*/
	
	/*public void testGetPanchayatsByPanchayatIdsList()
	{
		List<Long> panchayatIdsList = new ArrayList<Long>(0);
		panchayatIdsList.add(1l);
		List<Object[]> list = panchayatDAO.getPanchayatsByPanchayatIdsList(panchayatIdsList);
		for(Object[] params : list)
			System.out.println(params[0]+" "+params[1]);
	}*/
	
	public void testGetPanchayatNameById()
	{
		System.out.println(panchayatDAO.getPanchayatNameById(408l));
	}
	
}
