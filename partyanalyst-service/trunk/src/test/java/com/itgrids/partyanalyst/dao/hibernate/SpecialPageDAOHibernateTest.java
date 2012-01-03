package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.ISpecialPageDAO;
import com.itgrids.partyanalyst.utils.IConstants;

public class SpecialPageDAOHibernateTest extends BaseDaoTestCase{
	
	private ISpecialPageDAO specialPageDAO;

	public void setSpecialPageDAO(ISpecialPageDAO specialPageDAO) {
		this.specialPageDAO = specialPageDAO;
	}
	
	/*public void test()
	{
		specialPageDAO.getAll();
	}*/
	
	
	public void testGetSpecialPageDetails(){
		List<Object[]> result = specialPageDAO.getSpecialPageDetails(1l);
		System.out.println(result.size());
		for(Object[] resultObj : result){
			
			System.out.println("Title --------->" +resultObj[0].toString());
			System.out.println("Heading --------->" +resultObj[1].toString());
			System.out.println("Image Path --------->" +resultObj[2].toString());
			System.out.println("Title --------->" +resultObj[3].toString());
	
		}
	}

}
