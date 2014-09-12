package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IMobileNumbersDAO;
import com.itgrids.partyanalyst.utils.IConstants;

public class MobileNumbersDAOHibernateTest extends BaseDaoTestCase{

	private IMobileNumbersDAO mobileNumbersDAO;

	public void setMobileNumbersDAO(IMobileNumbersDAO mobileNumbersDAO) {
		this.mobileNumbersDAO = mobileNumbersDAO;
	}
	
	public void testgetMobileNumberDetailsByBoothId()
	{
	/*	List<Object[]> lsit  =  mobileNumbersDAO.getMobileNumberDetailsByBoothId(441704L,0L);
		
		System.out.println(lsit.size());*/
		
		//Set<String> list = mobileNumbersDAO.getVotersMobilenos(1l,1l,20);
		Set<String> list = mobileNumbersDAO.getIvrMobilenosBasedOnPriority(1l,1l,20,IConstants.PANCHAYAT);
		
		System.out.println(list.size());
	}
}
