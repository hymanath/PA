package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICallTrackingDetailDAO;

public class CallTrackingDetailDAOHibernateTest extends BaseDaoTestCase {

	   private ICallTrackingDetailDAO callTrackingDetailDAO;

	   public void setCallTrackingDetailDAO(ICallTrackingDetailDAO callTrackingDetailDAO) {
		this.callTrackingDetailDAO = callTrackingDetailDAO;
	}
	   public void test()
		{
		   callTrackingDetailDAO.getAll();
		}
	   
	
}
