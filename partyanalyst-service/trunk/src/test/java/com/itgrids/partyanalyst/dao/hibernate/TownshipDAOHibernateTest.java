package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.model.Panchayat;

public class TownshipDAOHibernateTest extends BaseDaoTestCase {
	private ITownshipDAO townshipDAO;
	//Township t = new Township(new Long(2),null,"qqq",new Long(2500),"aaa",null); 
	
	public void setTownshipDAO(ITownshipDAO townshipDAO) {
		this.townshipDAO = townshipDAO;
	}
	//@Test
	/*public void testFindByTownshipName(){
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
	
	/*public void testFindByTownshipNameTehsilNameDistrictId(){
		List list = townshipDAO.findByTownshipNameTehsilNameDistrictId(19l, "Allur", "Allur");
		assertEquals(12, list.size());
		
	}
	*/
	
/*	@Test
	public void test(){
		Assert.assertEquals(1, 1);
	}*/
	
	
	/*public void testgetPAnchayathies()
	{
		
		List<Panchayat> list = townshipDAO.getPanchayathies(844l);
		System.out.println(list.size());
		for(Panchayat params : list)
		{
			System.out.println(params.getPanchayatName());
		}
	}*/
	
	public void test()
	{
		List<Long> ids = new ArrayList<Long>();
		ids.add(269l);
		ids.add(268l);
		List<String> values = townshipDAO.getTehsilIdsByTownShip(ids);
		System.out.println(values.size());
	}
}
