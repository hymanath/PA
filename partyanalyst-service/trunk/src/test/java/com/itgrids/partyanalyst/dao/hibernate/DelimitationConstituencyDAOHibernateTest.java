package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import junit.framework.Assert;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.model.DelimitationConstituency;

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
		List list = delimitationConstituencyDAO.getLatestConstituenciesForDistrict(new Long(19));
		assertEquals(list.size(), 10);
	}*/
	
	public void testGetAssembliesInState(){
		List list = delimitationConstituencyDAO.getLatestAssemblyConstituenciesInState(1l);
		System.out.println(list.size());
	}
}
