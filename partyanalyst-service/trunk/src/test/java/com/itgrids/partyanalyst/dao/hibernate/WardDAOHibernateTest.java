package com.itgrids.partyanalyst.dao.hibernate;

import junit.framework.Assert;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IWardDAO;
import com.itgrids.partyanalyst.model.Ward;

public class WardDAOHibernateTest extends BaseDaoTestCase {
	/*private IWardDAO wardDAO;
	Ward w = new Ward(new Long(2),null,"qqq",new Long(100));

	public void setWardDAO(IWardDAO wardDAO) {
		this.wardDAO = wardDAO;
	}
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
	
	@Test
	public void test(){
		Assert.assertEquals(1, 1);
	}
}
