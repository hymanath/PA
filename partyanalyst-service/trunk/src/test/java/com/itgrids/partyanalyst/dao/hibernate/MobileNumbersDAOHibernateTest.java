package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IMobileNumbersDAO;

public class MobileNumbersDAOHibernateTest extends BaseDaoTestCase{

	private IMobileNumbersDAO mobileNumbersDAO;

	public void setMobileNumbersDAO(IMobileNumbersDAO mobileNumbersDAO) {
		this.mobileNumbersDAO = mobileNumbersDAO;
	}
	
	public void testgetMobileNumberDetailsByBoothId()
	{
		List<Object[]> lsit  =  mobileNumbersDAO.getMobileNumberDetailsByBoothId(441704L,0L);
		
		System.out.println(lsit.size());
	}
}
