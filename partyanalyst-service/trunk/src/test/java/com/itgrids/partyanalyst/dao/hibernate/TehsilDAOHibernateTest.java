package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import junit.framework.Assert;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.Tehsil;

public class TehsilDAOHibernateTest extends BaseDaoTestCase {
	private ITehsilDAO tehsilDAO;
	//Tehsil t = new Tehsil(new Long(2),null,"ppp",new Long(200),java.sql.Date.valueOf("2005-5-8"),"2009-6-8",null);
	
	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}
	
	/*//@Test
	public void testFindByTehsilName(){
		Tehsil t = tehsilDAO.findByTehsilName("xxx").get(0);
		Assert.assertEquals(new Long(1), t.getTehsilId());
	}
	//@Test
	public void testFindByTehsilCode(){
		Tehsil t = tehsilDAO.findByTehsilCode("100").get(0);
		Assert.assertEquals(new Long(1), t.getTehsilId());		
	}
	//@Test
	public void testFindByDeformDate(){
		Tehsil t = tehsilDAO.findByDeformDate("2009-8-9").get(0);
		Assert.assertEquals(new Long(1), t.getTehsilId());
	}
	//@Test
	public void testFindDistricts(){
		District d = tehsilDAO.get(new Long(1)).getDistrict();
		Assert.assertEquals("Adilabad", d.getDistrictName());
	}
	
	//@Test
	public void testSetTehsil(){
		tehsilDAO.save(t);
		setComplete();
	}
	
	public void testDeleteTehsil(){
		tehsilDAO.remove(new Long(2));
		setComplete();
	}*/
	
	/*@Test
	public void test(){
		Assert.assertEquals(1, 1);
	}
	
	@Test
	public void testFindByTehsilsByDistrict(){
		List tehsil =  tehsilDAO.findTehsilsByDistrict(new Long(17));
		Assert.assertEquals(57, tehsil.size());
	}*/
	
	/*public void testfindTehsilsByConstituencyIdAndPublicationDateId()
	{
		List<Object[]> list = tehsilDAO.findTehsilsByConstituencyIdAndPublicationDateId(232l, 7l);
		for(Object[] params : list)
		{
			System.out.println(params[0]);
		}
	}*/
	
	public void testGetTehsilNameByTehsilId()
	{
		System.out.println(tehsilDAO.getTehsilNameByTehsilId(844l));
	}
	
}
