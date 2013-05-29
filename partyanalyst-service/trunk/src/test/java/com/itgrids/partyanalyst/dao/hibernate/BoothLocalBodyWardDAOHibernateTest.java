package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBoothLocalBodyWardDAO;

public class BoothLocalBodyWardDAOHibernateTest extends BaseDaoTestCase{

	private IBoothLocalBodyWardDAO boothLocalBodyWardDAO;

	public IBoothLocalBodyWardDAO getBoothLocalBodyWardDAO() {
		return boothLocalBodyWardDAO;
	}

	public void setBoothLocalBodyWardDAO(
			IBoothLocalBodyWardDAO boothLocalBodyWardDAO) {
		this.boothLocalBodyWardDAO = boothLocalBodyWardDAO;
	}
	
	/*public void testGetAll(){
		List list = boothLocalBodyWardDAO.getAll();
		System.out.println(list.size());
	}*/
	
	/*public void testGetBoothsForWard(){
		List<Long> list = boothLocalBodyWardDAO.getBoothsForWard(31884l,1l);
		for(Long booth:list){
			System.out.println(booth);
		}
	}*/
	
	public void testgetBoothIds()
	{
		List<Long> list = boothLocalBodyWardDAO.getBoothIds();
		System.out.println(list.size());
	}
}
