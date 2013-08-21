package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IPanchayatHamletDAO;
import com.itgrids.partyanalyst.utils.IConstants;

public class PanchayatHamletDAOHibernateTest extends BaseDaoTestCase{

	private IPanchayatHamletDAO panchayatHamletDAO;

	public void setPanchayatHamletDAO(IPanchayatHamletDAO panchayatHamletDAO) {
		this.panchayatHamletDAO = panchayatHamletDAO;
	}
	
	/*public void test()
	{
		panchayatHamletDAO.getAll();
	}*/
	
	/*public void testGetHamletsOfAPanchayat()
	{
		List<Object[]> list = panchayatHamletDAO.getHamletsOfAPanchayat(4l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println(params[0].toString() +"---"+params[1].toString());
		}
	}*/
	
	/*public void testGetHamletsByPanchayatsLis()
	{
		List<Long> panchayatsList = new ArrayList<Long>(0);
		panchayatsList.add(1l);
		List<Object[]> list = panchayatHamletDAO.getHamletsByPanchayatsList(panchayatsList);
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.print(params[0].toString() +"---"+params[1].toString());
			System.out.println("---"+params[2].toString() +"---"+params[3].toString());
		}
		
	}*/
	
	/*public void testgetHamletsListByConstituencyId()
	{
		List<Object[]> list = panchayatHamletDAO.getHamletsListByConstituencyId(232l, 8l);
		System.out.println(list.size());
	}*/
	
	/*public void testgetPanchayatsListByMandalIdsList()
	{
		List<Long> mandalIdsList = new ArrayList<Long>(0);
		mandalIdsList.add(844l);
		List<Object[]> list = panchayatHamletDAO.getPanchayatsListByMandalIdsList(mandalIdsList);
		for(Object[] params : list)
		 System.out.println(params[0]+" "+params[1]);
	}*/
	
	/*public void testGetHamletsCountByLocationId()
	{
		List<Long> list = panchayatHamletDAO.getHamletsCountByLocationId(IConstants.PANCHAYAT, 232l, 1l, 8l);
		System.out.println(list.get(0));
	}*/
	/*public void testgetHamletDetailsByPanchayatIds(){
		List<Long> panchayatIds = new ArrayList<Long>();
		panchayatIds.add(5344l);
		
		List<Object[]> values = panchayatHamletDAO.getHamletsByPanchayatsList(panchayatIds);
		System.out.println(values.size());
		for (Object[] parms : values) {
			System.out.println(parms[2] +":"+ parms[3]);
		}
	}*/
	
	/*public void testgetHamletDetailsByPanchayatIds(){
	List<Long> panchayatIds = new ArrayList<Long>();
	panchayatIds.add(5344l);
	
	List<Object[]> values = panchayatHamletDAO.getHamletDetailsByPanchayatIds(panchayatIds,8l,1l);
	System.out.println(values.size());
	for (Object[] parms : values) {
		System.out.println(parms[2] +":"+ parms[4]);
	}
}*/
	
	/*public void testgetHamletCount()
	 {
		 List<Long> locationIdsList = new ArrayList<Long>(0);
		 locationIdsList.add(1L);
		 List<Object[]> list = panchayatHamletDAO.getHamletCount(locationIdsList, "panchayatHamlets");
		 System.out.println(list.size());
		 for(Object[] params:list)
		  System.out.println(params[0]+" "+params[1]);
	 }*/
	
	public void testGethamletsInAState()
	{
		List<Object[]> list = panchayatHamletDAO.gethamletsInAState(1l);
		System.out.println(list.size());
	}
}
