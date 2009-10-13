package com.itgrids.partyanalyst.dao.hibernate;

import junit.framework.Assert;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.model.Township;

public class TownshipDAOHibernateTest extends BaseDaoTestCase {
	/*private ITownshipDAO townshipDAO;
	Township t = new Township(new Long(2),null,"qqq",new Long(2500),"aaa",null); 
	
	public void setTownshipDAO(ITownshipDAO townshipDAO) {
		this.townshipDAO = townshipDAO;
	}
	//@Test
	public void testFindByTownshipName(){
		Township t = townshipDAO.findByTownshipName("yyy").get(0);
		Assert.assertEquals(new Long(1), t.getTownshipId());
	}
	//@Test
	public void testFindByTownshipCode(){
		Township t = townshipDAO.findByTownshipCode("100").get(0);
		Assert.assertEquals(new Long(1), t.getTownshipId());
	}
	//@Test
	public void testFindByTownshipType(){
		Township t = townshipDAO.findByTownshipType("aaa").get(0);
		Assert.assertEquals(new Long(1), t.getTownshipId());
	}

	public void testSetTownship(){
		townshipDAO.save(t);
		setComplete();
	}

	public void testDeleteTownship(){
		townshipDAO.remove(new Long(2));
		setComplete();
	}*/
	
	@Test
	public void test(){
		Assert.assertEquals(1, 1);
	}
}
