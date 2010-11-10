package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import junit.framework.Assert;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
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
	
	/*public void testDelimitationConstituencies(){
		List<DelimitationConstituency> list = delimitationConstituencyDAO.findByElectionScopeIdStateIdAndElectionYear(new Long(1), new Long(1), new Long(2009));
		assertEquals(42,list.size());
	}*/ 
	
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
	
	public void testGetLatestConstituenciesByDistrictIds(){
		List list = delimitationConstituencyDAO.getLatestConstituenciesByDistrictIds("7,8");
		System.out.println(list.size());
		for(int i = 0; i<list.size();i++)
		{
			Object[] obj = (Object[])list.get(i);
		System.out.println("id:"+obj[0]);
		System.out.println("name:"+obj[1]);
		
		}
	}
}
