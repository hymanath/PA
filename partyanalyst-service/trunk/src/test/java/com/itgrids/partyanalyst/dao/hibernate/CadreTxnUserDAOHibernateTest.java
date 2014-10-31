package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICadreTxnUserDAO;

public class CadreTxnUserDAOHibernateTest extends BaseDaoTestCase{
	
	private ICadreTxnUserDAO cadreTxnUserDAO;

	public void setCadreTxnUserDAO(ICadreTxnUserDAO cadreTxnUserDAO) {
		this.cadreTxnUserDAO = cadreTxnUserDAO;
	}
	
	public void testDetails()
	{
		List list = cadreTxnUserDAO.checkUserExists(1L, "9581434970");
		System.out.println(list);
	}
	

}
