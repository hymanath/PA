package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.DelimitationConstituency;
import com.itgrids.partyanalyst.utils.IConstants;

public class DelimitationConstituencyDAOHibernateTest extends BaseDaoTestCase {

	IDelimitationConstituencyDAO delimitationConstituencyDAO;

	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}
	/*public void testGetAll(){
		delimitationConstituencyDAO.getAll();
	}*/
	/*
	public void testDelimitationConstituencies(){
		List<DelimitationConstituency> list = delimitationConstituencyDAO.findByElectionScopeIdStateIdAndElectionYear(new Long(1), new Long(1), new Long(2009));
		System.out.println(list.size());
		for(DelimitationConstituency constituency:list)
		{
			System.out.println("name:"+constituency.getConstituency().getName());
			System.out.println("name:"+constituency.getConstituency().getConstituencyId());
			
		}
	} */
	
	/*@SuppressWarnings("unchecked")
	public void testGetDelimitationConstituenciesByDistrictID(){
		List result = delimitationConstituencyDAO.getDelimitationConstituenciesByDistrictID(17l, 2009l);
		Assert.assertEquals(13, result.size());
	}*/
	
	/*public void testLatestDelimitationYear(){
		List result =  delimitationConstituencyDAO.getLatestDelimitationYear();
		System.out.println(result.get(0).toString());
		Assert.assertEquals(1, result.size());
	}
	*/
	/*public void testGetConstituenciesForDistrict(){
		List list = delimitationConstituencyDAO.getLatestConstituenciesForDistrict(new Long(23));
		assertEquals(list.size(), 10);
	}*/
	
	/*public void testGetAssembliesInState(){
		List list = delimitationConstituencyDAO.getLatestDelimitationYear();
		System.out.println(Long.parseLong(list.get(0).toString()));
		System.out.println(list.size());
	}*/
	
/*	public void testGetConstituenciesByAreaTypeInDist(){
		List list = delimitationConstituencyDAO.getConstituenciesByAreaTypeInDist(19l, "RURAL");
		System.out.println(list.size());
		for(int i = 0; i<list.size();i++)
		{
			Object[] obj = (Object[])list.get(i);
		System.out.println("id:"+Long.parseLong(obj[0].toString()));
		System.out.println("name:"+obj[1].toString());
		System.out.println("area:"+obj[2].toString());
		}
	}*/
	
	/*public void testGetLatestConstituenciesByDistrictIds(){
		List list = delimitationConstituencyDAO.getLatestConstituenciesByDistrictIds("7,8");
		System.out.println(list.size());
		for(int i = 0; i<list.size();i++)
		{
			Object[] obj = (Object[])list.get(i);
		System.out.println("id:"+obj[0]);
		System.out.println("name:"+obj[1]);
		
		}
	}*/
	
	/*public void testGetLatestConstituenciesByElectionTypeInState()
	{
		List list = delimitationConstituencyDAO.getLatestConstituenciesByElectionTypeInState(1l,1l);
		System.out.println(list.size());
		for(int i = 0; i<list.size();i++)
		{
			Object[] obj = (Object[])list.get(i);
		System.out.println("id:"+obj[0]);
		System.out.println("name:"+obj[1]);
		
		}
	}*/
	
	/*public void testFindDelimitationConstituencyByConstituencyIDForCensus()
	{
	  List<Object> list = delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyIDForCensus(235l, 2009l);
	  
	  if(list != null)
	  System.out.println(list.size());
	 
	}*/
	/*public void testGetLatestConstituenciesForDistrictBasedOnYear()
	{
		List<Long>list =  delimitationConstituencyDAO.getLatestConstituenciesForDistrictBasedOnYear(1l,2004l);
		System.out.println(list.size());
		
		for(Long id:list)
		{
			System.out.println("   ======"+id);
		}
	}*/
	
	/*@SuppressWarnings("unchecked")
	public void testGetLatestConstituenciesByElectionTypeInDistrict()
	{
		List<Object[]>list =  delimitationConstituencyDAO.getLatestConstituenciesByElectionTypeInState(2l,1l);
		
		System.out.println("--------------"+list.size());
		for(Object[] obj : list)
		{
			System.out.println("constituencyId == "+obj[0]+"=====constituency name"+obj[1]);
		}
		
		Long y = 0l;
		int total = 0;
		for(int i=1;i<=23;i++)
		{
			
			List<Object[]>list2 =  delimitationConstituencyDAO.getLatestConstituenciesByElectionTypeInDistrict(2l,++y);
			
			total += list2.size();
			System.out.println(i+"--------------"+list2.size());
			for(Object[] obj : list2)
			{
				System.out.println("constituencyId == "+obj[0]+"=====constituency name"+obj[1]);
			}
		}
		System.out.println("==========="+total+"================");
	}
	*/
	/*public void testGetLatestConstituenciesByElectionTypeAndYearInADistrict()
	{
		List<Object[]> list = delimitationConstituencyDAO.getLatestConstituenciesByElectionTypeAndYearInADistrict(1l,19l,2009l);
		
		if(list != null && list.size() > 0)
			System.out.println(list.size());
		}*/
	/*public void testGetLatestConstituenciesByElectionTypeAndYearInState()
	{
		List<Object[]> list = delimitationConstituencyDAO.getLatestConstituenciesByElectionTypeAndYearInState(1l,1l,2004l);
		
		if(list != null && list.size() >0)
		{
			for(Object[] param : list)
			{
				System.out.println(param[0]);
				System.out.println(param[1]);
			}
		}
	}*/
	
	public void testfindDelimitationConstituencyByConstituencyIDs(){
		List<Long> idsList =new ArrayList<Long>(0);
		idsList.add(282l);
		idsList.add(232l);
		List<DelimitationConstituency> value = delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyIDs(idsList);
		System.out.println(value.size());
	}
}