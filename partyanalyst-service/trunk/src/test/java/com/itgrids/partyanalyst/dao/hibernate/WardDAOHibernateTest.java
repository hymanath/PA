package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import junit.framework.Assert;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IWardDAO;
import com.itgrids.partyanalyst.model.Ward;

public class WardDAOHibernateTest extends BaseDaoTestCase {
	private IWardDAO wardDAO;

	public void setWardDAO(IWardDAO wardDAO) {
		this.wardDAO = wardDAO;
	}
	/*Ward w = new Ward(new Long(2),null,"qqq",new Long(100));

	public void setWardDAO(IWardDAO wardDAO) {
		this.wardDAO = wardDAO;
	}*/
	/*
	//@Test
	public void testFindByWardName(){
		Ward w = wardDAO.findByWardName("aaa").get(0);
		Assert.assertEquals(new Long(1), w.getWardId());
	}
	//@Test
	public void testFindByWardCode(){
		Ward w = wardDAO.findByWardCode("110").get(0);
		Assert.assertEquals(new Long(1), w.getWardId());
	}
	
	public void testSetWard(){
		wardDAO.save(w);
		setComplete();		
	}
	
	public void testDeleteWard(){
		wardDAO.remove(new Long(2));
		setComplete();
	}*/
	
	/*@Test
	public void test(){
		Assert.assertEquals(1, 1);
	}*/
	
	public void testgetWardsListByLocalEleBodyIdAndConstituencyId()
	{
		List<Object[]> list = wardDAO.getWardsListByLocalEleBodyIdAndConstituencyId(20l, 8l, 347l);
		if(list!= null && list.size() > 0)
		{
			for(Object[] params : list)
				System.out.println(params[0]+" "+params[1]);
		}
		
	public void testfindByWardsByAssemblyLocalElectionBodyId(){
		List<Object[]> list = wardDAO.findByWardsByAssemblyLocalElectionBodyId(31l,8l);
		System.out.println(list.size());
		for(Object[] params : list){
		System.out.println(params[1]);
		}
	}
}
